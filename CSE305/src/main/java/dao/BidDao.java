package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Auction;

import model.Bid;
import model.Item;


public class BidDao {
    public List<Bid> getBidHistory(int auctionID) {
        List<Bid> bids = new ArrayList<>();
        
        String sql = "SELECT AuctionID, CustomerID, BidTime, BidPrice" +
                    " FROM Bid" +
                    " WHERE AuctionID = ?" +
                    " ORDER BY BidTime DESC";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, auctionID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid(rs.getString(2), rs.getInt(1), rs.getDate(3), rs.getBigDecimal(4));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return bids;
    }

    public List<Bid> getAuctionHistory(String customerID) {
        List<Bid> bids = new ArrayList<>();

        String sql = "SELECT AuctionID, CustomerID, MAX(BidTime) AS LastBidTime, MAX(BidPrice) AS LastBidPrice" +
                    " FROM Bid INNER JOIN Auction USING (AuctionID)" +
                    " WHERE CustomerID = ? GROUP BY AuctionID" +
                    " ORDER BY LastBidTime DESC";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid(rs.getString(2), rs.getInt(1), rs.getDate(3), rs.getBigDecimal(4));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return bids;
    }

    /**
     * 
     * @param auctionID
     * @param maxBid
     * @param customerID
     * @return true if successful; otherwise, in case of any errors, false
     */
    public boolean submitBid(int auctionID, BigDecimal maxBid, String customerID) {
        String sqlInsertBid = "INSERT INTO Bid (CustomerID, AuctionID, BidTime, BidPrice)" +
                             " SELECT ? AS CustomerID, AuctionID, NOW() AS BidTime, ? AS BidPrice" +
                             " FROM Auction" +
                             " WHERE AuctionID = ? AND IF(CurrentHighestBidPrice IS NULL, ? >= MinBidPrice, ? > CurrentHighestBidPrice) AND Seller <> ?";
        
        String sqlGetBidID = "SELECT LAST_INSERT_ID() AS BidID";
        
        String sqlUpdateAuction = "UPDATE Auction" +
                                 " SET CurrentHighestBidPrice = IF(CurrentHighestBidPrice IS NULL, MinBidPrice, IF(? > CurrentMaxBidPrice, LEAST(?, CurrentMaxBidPrice + BidIncrement), LEAST(? + BidIncrement, CurrentMaxBidPrice)))," +
                                 "     CurrentMaxBidPrice = IF(CurrentMaxBidPrice IS NULL, ?, IF(? > CurrentMaxBidPrice, ?, CurrentMaxBidPrice))," +
                                 "     BidIncrement = IF(CurrentHighestBidPrice IS NULL, BidIncrement, GREATEST(ROUND(0.02 * CurrentHighestBidPrice, 2), BidIncrement))," +
                                 "     WinningBidID = IF(WinningBidID IS NULL, ?, IF(? > CurrentMaxBidPrice, ?, WinningBidID))" +
                                 " WHERE AuctionID = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementInsertBid = conn.prepareStatement(sqlInsertBid);
                PreparedStatement statementGetBidID = conn.prepareStatement(sqlGetBidID);
                PreparedStatement statementUpdateAuction = conn.prepareStatement(sqlUpdateAuction);
        ) {
            statementInsertBid.setString(1, customerID);
            statementInsertBid.setBigDecimal(2, maxBid);
            statementInsertBid.setInt(3, auctionID);
            statementInsertBid.setBigDecimal(4, maxBid);
            statementInsertBid.setBigDecimal(5, maxBid);
            statementInsertBid.setString(6, customerID);
            
            for (int i = 1; i <= 6; i++)
                statementUpdateAuction.setBigDecimal(i, maxBid);
            
            statementUpdateAuction.setBigDecimal(8, maxBid);
            statementUpdateAuction.setInt(10, auctionID);
            
            // start transaction
            conn.setAutoCommit(false);
            try {
                int affected = statementInsertBid.executeUpdate();
                // If the bid was not inserted into the Bid table above then do not proceed and rollback the transaction
                if (affected != 1)
                    throw new Exception("Unable to insert bid into database.");
                
                int bidID = 0;
                try(ResultSet rs = statementGetBidID.executeQuery()) {
                    if (rs.next())
                        bidID = rs.getInt(1);
                    else
                        throw new Exception("Could not get the bidID of the new bid");
                }
                
                statementUpdateAuction.setInt(7, bidID);
                statementUpdateAuction.setInt(9, bidID);
                affected = statementUpdateAuction.executeUpdate();
                if (affected != 1)
                    throw new Exception("Unable to update auction after submitting bid.");
                
                conn.commit();
                // transaction committed
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Submit bid transaction was rolled back");
                System.err.println(e);
                return false;
            }          
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return false;
        }

        return true;
    }

    public List<?>[] getSalesListing(String searchBy, String searchKeyword) {
        List<Bid> bids = new ArrayList<>();
        List<Auction> auctions = new ArrayList<>();

        String sql;
        if (searchBy.equals("item"))
            sql = "SELECT CustomerID AS Buyer, A.AuctionID, Seller, BidPrice, CurrentHighestBidPrice AS SoldPrice, BidTime" +
                 " FROM (Auction AS A INNER JOIN Bid ON WinningBidID = BidID) INNER JOIN Item USING (ItemID)" +
                 " WHERE ItemName LIKE CONCAT('%', ?, '%') AND SaleStatus = 'Paid'";
        else if (searchBy.equals("buyer"))
            sql = "SELECT CustomerID AS Buyer, A.AuctionID, Seller, BidPrice, CurrentHighestBidPrice AS SoldPrice, BidTime" +
                 " FROM (Auction AS A INNER JOIN Bid ON WinningBidID = BidID) INNER JOIN VendorUser ON CustomerID = Username" +
                 " WHERE (FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%')) AND SaleStatus = 'Paid'";
        else
            sql = "SELECT CustomerID AS Buyer, A.AuctionID, Seller, BidPrice, CurrentHighestBidPrice AS SoldPrice, BidTime" +
                 " FROM (Auction AS A INNER JOIN Bid ON WinningBidID = BidID) INNER JOIN VendorUser ON Seller = Username" +
                 " WHERE (FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%')) AND SaleStatus = 'Paid'";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            if (searchBy.equals("item"))
                statement.setString(1, searchKeyword);
            else {
                statement.setString(1, searchKeyword);
                statement.setString(2, searchKeyword);
            }
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid(rs.getString(1), rs.getInt(2), rs.getDate(6), rs.getBigDecimal(4));
                    bids.add(bid);
                    Auction auction = new Auction();
                    auction.setSeller(rs.getString(3));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(5));
                    auctions.add(auction);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return new List<?>[]{bids, auctions};
    }
    
    public List<Item> getSalesReport(int month, int year) {
        List<Item> items = new ArrayList<>();
        
        String sql = "SELECT ItemName, CurrentHighestBidPrice AS SoldPrice" +
                    " FROM Auction INNER JOIN Item USING (ItemID)" +
                    " WHERE MONTH(EndDate) = ? AND YEAR(EndDate) = ? AND SaleStatus = 'Paid'";
           
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setName(rs.getString(1));
                    // using revenue field to store SoldPrice
                    item.setRevenue(rs.getBigDecimal(2));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
				
        return items;
    }
}
