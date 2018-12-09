USE vendor;

-- SET FOREIGN_KEY_CHECKS = 0;
-- TRUNCATE VendorUser;
-- TRUNCATE Customer;
-- TRUNCATE Employee;
-- TRUNCATE Item;
-- TRUNCATE Auction;
-- TRUNCATE Bid;
-- SET FOREIGN_KEY_CHECKS = 1;

-- Customers
INSERT INTO VendorUser VALUES ('shiyong', '1234', 'ShiYong', 'Lu', '123 Success Street', 'Stony Brook', 'NY', '11790', '5166328959', 'shiyong@cs.sunysb.edu');
INSERT INTO Customer(CustomerID, CreditCardNum) VALUES ('shiyong', '1234567812345678');

INSERT INTO VendorUser VALUES ('haixia', '2018', 'Haixia', 'Du', '456 Fortune Road', 'Stony Brook', 'NY', '11790', '5166324360', 'dhaixia@cs.sunysb.edu');
INSERT INTO Customer(CustomerID, CreditCardNum) VALUES ('haixia', '5678123456781234');

INSERT INTO VendorUser VALUES ('john', '2000', 'John', 'Smith', '789 Peace Blvd.', 'Los Angeles', 'CA', '12345', '4124434321', 'shlu@ic.sunysb.edu');
INSERT INTO Customer(CustomerID, CreditCardNum) VALUES ('john', '2345678923456789');

INSERT INTO VendorUser VALUES ('phil', '2001', 'Phil', 'Lewis', '135 Knowledge Lane', 'Stony Brook', 'NY', '11790', '5166668888', 'pml@cs.sunysb.edu');
INSERT INTO Customer(CustomerID, CreditCardNum) VALUES ('phil', '6789234567892345');

-- Employees
INSERT INTO VendorUser VALUES ('david', '2001', 'David', 'Smith', '123 College Road', 'Stony Brook', 'NY', '11790', '5162152345', 'david@smith.com');
INSERT INTO Employee(EmployeeID, SSN, StartDate, EmployeeLevel, HourlyRate) VALUES ('david', '123456789', '1998-11-01', 1, 60.00);

INSERT INTO VendorUser VALUES ('davidw', '2001', 'David', 'Warren', '456 Sunken Street', 'Stony Brook', 'NY', '11794', '5166329987', 'david@warren.com');
INSERT INTO Employee(EmployeeID, SSN, StartDate, EmployeeLevel, HourlyRate) VALUES ('davidw', '789123456', '1999-02-02', 2, 50.00);

-- Items
INSERT INTO Item(ItemName, ItemType, Description, Quantity) VALUES ('Titanic', 'DVD', '2005', 4);
SET @item_id1 = LAST_INSERT_ID();

INSERT INTO Item(ItemName, ItemType, Description, Quantity) VALUES ('Nissan Sentra', 'Car', '2007', 1);
SET @item_id2 = LAST_INSERT_ID();

INSERT INTO Item (ItemName, ItemType, Description, Quantity) VALUES ('Cars 2', 'DVD', '2012', 3);
SET @item_id3 = LAST_INSERT_ID();

INSERT INTO Item (ItemName, ItemType, Description, Quantity) VALUES ('Bee Movie', 'DVD', '2004', 13);
SET @item_id4 = LAST_INSERT_ID();

-- Auctions
INSERT INTO Auction(BidIncrement, MinBidPrice, ReservePrice, NumCopies, Seller, Monitor, ItemID, OpenDate, EndDate)
VALUES (1.00, 5.00, 10.00, 4, 'phil', 'davidw', @item_id1, '2008-12-13 13:00:00', '2008-12-16 13:00:00');
SET @auction_id1 = LAST_INSERT_ID();

INSERT INTO Auction(BidIncrement, MinBidPrice, ReservePrice, NumCopies, Seller, Monitor, ItemID, OpenDate, EndDate)
VALUES (10.00, 1000.00, 2000.00, 1, 'john', 'david', @item_id2, '2008-12-11 13:00:00', '2008-12-16 13:00:00');

INSERT INTO Auction(BidIncrement, MinBidPrice, ReservePrice, NumCopies, Seller, Monitor, ItemID, OpenDate, EndDate)
VALUES (1.00, 5.00, 10.00, 1, 'phil', 'david', @item_id3, '2008-12-13 13:00:00', '2008-12-16 13:00:00');
SET @auction_id3 = LAST_INSERT_ID();

INSERT INTO Auction(BidIncrement, MinBidPrice, ReservePrice, NumCopies, Seller, Monitor, ItemID, OpenDate, EndDate)
VALUES (1.00, 5.00, 10.00, 7, 'haixia', 'david', @item_id4, '2008-12-13 13:00:00', '2008-12-16 13:00:00');
SET @auction_id4 = LAST_INSERT_ID();

-- Bids
INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('haixia', @auction_id1, '2008-12-16 12:00:02', 10.00);

INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('shiyong', @auction_id1, '2008-12-16 12:47:41', 9.00);

INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('shiyong', @auction_id1, '2008-12-16 12:53:13', 10.00);

INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('shiyong', @auction_id1, '2008-12-16 12:59:41', 15.00);

INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('shiyong', @auction_id3, '2008-12-16 12:53:13', 10.00);

INSERT INTO Bid(CustomerID, AuctionID, BidTime, BidPrice) VALUES ('shiyong', @auction_id4, '2008-12-16 12:59:41', 15.00);

-- SQL Dumps
SELECT * FROM VendorUser;
SELECT * FROM Customer;
SELECT * FROM Employee;
SELECT * FROM Item;
SELECT * FROM Auction;
SELECT * FROM Bid;
