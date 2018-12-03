USE vendor;

-- Customers
INSERT INTO 
	Customer (
		CustomerID, 
        LastName, 
		FirstName, 
		Address, 
        ZipCode, 
        Telephone, 
        Email, 
        Rating, 
		CreditCardNum)
	VALUES (
		111111111, 
        'Lu', 
        'ShiYong', 
        '123 Success Street, Stony Brook, NY', 
        '11790',
        '5166328959', 
        'shiyong@cs.sunysb.edu', 
        1, 
        '1234567812345678');

INSERT INTO 
	Customer (
		CustomerID, 
        LastName, 
        FirstName, 
        Address, 
        ZipCode, 
        Telephone, 
        Email,
        Rating,
        CreditCardNum)
	VALUES (
		222222222, 
        'Du', 
        'Haixia', 
        '456 fortune Road, Stony Brook, NY', 
        '11790', 
        '5166324360', 
        'dhaixia@cs.sunysb.edu', 
        1, 
        '5678123456781234');
  
INSERT INTO 
	Customer (
		CustomerID, 
        LastName, 
        FirstName,
        Address, 
        ZipCode, 
        Telephone, 
        Email, 
        Rating, 
        CreditCardNum)
	VALUES (
		333333333, 
		'Smith', 
		'John', 
		'789 Peace Blvd., Los Angeles, CA', 
		'12345',
		'4124434321',
		'shlu@ic.sunysb.edu', 
		1,
		'2345678923456789');
  
INSERT INTO 	
	Customer (
		CustomerID, 
        LastName, 
        FirstName,
        Address, 
        ZipCode, 
        Telephone, 
        Email,
        Rating, 
        CreditCardNum)
	VALUES (
		444444444, 
		'Paul', 
		'Lewis', 
        '135 Knowledge Lane, Stony Brook, NY', 
        '11790', 
        '5166668888', 
        'pml@cs.sunysb.edu',
        1, 
        '6789234567892345');



-- Employees
INSERT INTO 
	Employee (
		SSN,
        LastName, 
        FirstName,
        Address,
        ZipCode, 
        Telephone, 
        Email,
        StartDate, 
        EmployeeLevel, 
        HourlyRate)
	VALUES (
		123456789, 
        'Smith', 
        'David', 
        '123 College Road, Stony Brook, NY',
        '11790', 
        '5162152345',
        'david@smith.com',
        '1998-11-01',
        1, 
        60.00);
  
INSERT INTO 
	Employee (
		SSN, 
        LastName, 
        FirstName, 
        Address,
        ZipCode,
        Telephone, 
        Email,
        StartDate,
        EmployeeLevel, 
        HourlyRate)
	VALUES (
		789123456,
        'Warren', 
        'David',
        '456 Sunken Street, Stony Brook, NY', 
        '11794',
        '5166329987', 
        'david@smith.com', 
        '1999-02-02', 
        2,
        50.00);
  
  
  
-- Items
INSERT INTO 
	Item (
		ItemID, 
		ItemName, 
        ItemType, 
        Description,
        Quantity)
	VALUES (
		987654321, 
        'Titanic', 
        'DVD', 
        '2005',
        4);
  
INSERT INTO 
	Item (
		ItemID, 
		ItemName, 
		ItemType, 
		Description,
		Quantity)
	VALUES (
		876543219, 
        'Nissan Sentra', 
        'Car', 
        '2007', 
        1);
  
INSERT INTO 
	Item (
		ItemID,
        ItemName, 
        ItemType,
        Description,
        Quantity)
	VALUES (
		134739572, 	
        'Cars 2', 
        'DVD', 
        '2012', 
        3);

INSERT INTO 
	Item (
		ItemID, 
        ItemName, 
        ItemType, 
        Description,
        Quantity)
	VALUES (
		476318495, 
        'Bee Movie', 
        'DVD',
        '2004',
        13);
  


-- Auctions
INSERT INTO 
	Auction (
		AuctionID,
		BidIncrement, 
		MinBidPrice, 
		ReservePrice, 
		CurrentHighestBidPrice, 
		CurrentMaxBidPrice,
		NumCopies, 
		Monitor, 
		ItemID)
	VALUES (
		1357, 
		1, 
		5, 
		10,
		11, 
		15, 
		4, 
		'123456789', 
		987654321);

INSERT INTO 
	Auction (
		AuctionID,
        BidIncrement,
        MinBidPrice,
        ReservePrice,
        CurrentHighestBidPrice,
        CurrentMaxBidPrice, 
        NumCopies, 
        Monitor, 
        ItemID)
	VALUES (
		2468, 
        10, 
        1000, 
        2000, 
        1000, 
        1000, 
        1,
        '789123456', 
        876543219);
  
INSERT INTO 
	Auction (
		AuctionID,
		BidIncrement, 
		MinBidPrice,
		ReservePrice,
		CurrentHighestBidPrice, 
		CurrentMaxBidPrice,
		NumCopies,
		Monitor, 
		ItemID)
	VALUES (
		3579,
        1, 
        5, 
        10, 
        11, 
        15, 
        1,
        '123456789', 
        134739572);

INSERT INTO 
	Auction (	
		AuctionID, 
        BidIncrement,
        MinBidPrice, 
        ReservePrice,
        CurrentHighestBidPrice, 
        CurrentMaxBidPrice, 
        NumCopies, 
        Monitor, 
        ItemID)
	VALUES (
		1234,
        1,
        5,
        10, 
        11, 
        15,
        7,
        '789123456',
        476318495);
  


-- Bids
INSERT INTO 
	Bid (
		CustomerID,
        AuctionID, 
        BidTime,
        BidPrice)
	VALUES (
		222222222, 
        1357, 
        '2008-12-16 12:00:02', 
        10);

INSERT INTO 
	Bid (
		CustomerID, 
        AuctionID, 
        BidTime,
        BidPrice)
	VALUES (
		111111111, 
        1357, 
        '2008-12-16 12:47:41', 
        9);
  
INSERT INTO 
	Bid (
		CustomerID,
		AuctionID, 
		BidTime,
		BidPrice)
	VALUES (
		111111111, 
        1357, 
        '2008-12-16 12:53:13', 
        10);

INSERT INTO 
	Bid (
		CustomerID, 
        AuctionID, 
        BidTime, 
        BidPrice)
	VALUES (
		111111111, 
        1357, 
        '2008-12-16 12:59:41',
        15);
  
INSERT INTO 
	Bid (
		CustomerID, 
        AuctionID, 
        BidTime, 
        BidPrice)
	VALUES (
		111111111,
        3579, 
        '2008-12-16 12:53:13', 
        10);

INSERT INTO 
	Bid (
		CustomerID,
        AuctionID,
        BidTime, 
        BidPrice)
	VALUES (
		111111111,
        1234, 
        '2008-12-16 12:59:41', 
        15);  
  


-- Posts
INSERT INTO 
	Post (
		EndDate,
        OpenDate,
        CustomerID, 
        AuctionID)
	VALUES (
		'2008-12-16 13:00:00', 
        '2008-12-13 13:00:00', 
        444444444, 
        1357);
  
INSERT INTO 
	Post (
		EndDate, 
        OpenDate,
        CustomerID, 
        AuctionID)
	VALUES (
		'2008-12-16 13:00:00', 
        '2008-12-11 13:00:00',
        333333333, 
        2468);  

INSERT INTO 
	Post (
		EndDate, 
        OpenDate,
        CustomerID, 
        AuctionID)
	VALUES (
		'2008-12-16 13:00:00',
        '2008-12-13 13:00:00', 
        444444444,
        1234);
  
INSERT INTO 
	Post (
		EndDate, 
        OpenDate,
        CustomerID, 
        AuctionID)
	VALUES (
		'2008-12-16 13:00:00',
		'2008-12-13 13:00:00', 
		444444444, 
		3579);

-- SQL Dumps
SELECT * FROM Auction;
SELECT * FROM Bid;
SELECT * FROM Customer;
SELECT * FROM Employee;
SELECT * FROM Item;
SELECT * FROM Post;
