package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {
    public List<Auction> getAllAuctions() {
        List<Auction> auctions = new ArrayList<>();
        
        String sql = "SELECT AuctionID, MinBidPrice, CurrentHighestBidPrice, NumCopies, Seller, ItemID, OpenDate, EndDate FROM Auction";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction(rs.getInt(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getInt(4), 
                        rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));
                    
                    auctions.add(auction);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return auctions;
    }

    /**
     * Show auctions where the customer bidded in. This is different than the auction history since it's sent to a
     * with the bid history button
     * @param customerID
     * @return 
     */
    public List<Auction> getAuctions(String customerID) {
        List<Auction> auctions = new ArrayList<>();

        String sql = "SELECT AuctionID, MinBidPrice, CurrentHighestBidPrice, NumCopies, Seller, ItemID, OpenDate, EndDate" +
                    " FROM Auction AS A" +
                    " WHERE EXISTS(SELECT 1" +
                    "              FROM Bid AS B" +
                    "              WHERE B.AuctionID = A.AuctionID AND CustomerID = ?)";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction(rs.getInt(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getInt(4), 
                        rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));

                    auctions.add(auction);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return auctions;
    }

    /**
     * Get list of auctions to be recorded by a customer rep. who is monitoring the auction 
     * @param employeeID
     * @return 
     */
    public List<?>[] getOpenAuctions(String employeeID) {
        List<Auction> auctions = new ArrayList<>();
        List<Bid> bids = new ArrayList<>();
        
        String sql = "SELECT A.AuctionID, ItemID, NumCopies, CustomerID AS WinningBidder, BidTime, BidPrice, CurrentHighestBidPrice AS SoldPrice, EndDate" +
                    " FROM Auction AS A INNER JOIN Bid AS B ON A.WinningBidID = B.BidID" +
                    " WHERE EndDate <= NOW() AND WinningBidID IS NOT NULL AND SaleStatus IS NULL AND Monitor = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, employeeID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt(1));
                    auction.setItemID(rs.getInt(2));
                    auction.setNumCopies(rs.getInt(3));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(7));
                    auction.setEndDate(rs.getDate(8));
                    auctions.add(auction);
                    Bid bid = new Bid(rs.getString(4), rs.getDate(5), rs.getBigDecimal(6));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return new List<?>[]{auctions, bids};
    }

    public String recordSale(int auctionID) {
        // auction is immediately considered to be paid upon recording
        String sql = "UPDATE Auction SET SaleStatus = 'Paid' WHERE AuctionID = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, auctionID);
            int affected = statement.executeUpdate();
            
            if (affected == 1)
                return "success";
            else
                return "failure";
            
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }        
    }

    public List<Object> getAuctionData(int auctionID) {
        String sql = "SELECT A.AuctionID, A.ItemID, ItemType, ItemName, Description, BidIncrement, MinBidPrice, CurrentHighestBidPrice, CustomerID, FirstName, LastName" +
                    " FROM (Auction AS A INNER JOIN Item USING (ItemID)) LEFT JOIN (Bid INNER JOIN VendorUser ON Username = CustomerID) ON BidID = WinningBidID" +
                    " WHERE NOW() < EndDate AND A.AuctionID = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, auctionID);

            List<Object> data = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt("AuctionID"));
                    auction.setBidIncrement(rs.getBigDecimal("BidIncrement"));
                    auction.setMinBidPrice(rs.getBigDecimal("MinBidPrice"));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal("CurrentHighestBidPrice"));
                    data.add(auction);
                    Item item = new Item(rs.getInt("ItemID"), rs.getString("ItemName"), rs.getString("ItemType"),
                        rs.getString("Description"));
                    data.add(item);
                    Customer customer = new Customer();
                    customer.setUsername(rs.getString("CustomerID"));
                    customer.setFirstName(rs.getString("FirstName"));
                    customer.setLastName(rs.getString("LastName"));
                    data.add(customer);
                } else
                    return null;
            }
            return data;
            
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return null;
        }
    }
}
