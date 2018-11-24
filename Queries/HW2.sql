-- mysql -u root -h 35.237.254.109 -p

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
  


---- SQL Dumps
SELECT * FROM Auction;
SELECT * FROM Bid;
SELECT * FROM Customer;
SELECT * FROM Employee;
SELECT * FROM Item;
SELECT * FROM Post;





---- Manager Level Transactions

-- Add new employee
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
		123123123,
		'Doe',
        'John',
        '321 AppleCourt, Smithtown, NY',
        '11984',
        '6311234567',
        'john@doe.com',
        '2001-03-08',
        1,
        40.00);



-- Edit employee information
UPDATE 
		Employee 
	SET 
		StartDate = '2001-03-09', 
        EmployeeLevel = 3, 
        HourlyRate = 45
	WHERE 
		SSN = 123123123;



-- Delete an employee
DELETE FROM 
		Employee
	WHERE 
		SSN = 123123123;



-- Obtain a sales report for a particular month
SELECT 
		SUM(A.CurrentHighestBidPrice) AS TotalRevenue,
		COUNT(*) AS TotalItemsSold
	FROM
		Item I,
		Auction A,
		Post P
	WHERE
		I.ItemID = A.ItemID
        AND A.AuctionID = P.AuctionID
        AND MONTH(P.EndDate) = 12
        AND P.EndDate < CURDATE();



-- Produce a comprehensive listing of all items
CREATE VIEW items_comprehensive 
	AS SELECT 
		I.ItemID as ItemID, 
		I.ItemName as ItemName, 
		I.ItemType as ItemType, 
		I.Description as Description, 
        I.Quantity as Quantity, 
        (SELECT COUNT(A.ItemID) 
			FROM Auction A 
            WHERE A.ItemID = I.ItemID) as NumberOfAuctions, 
		(SELECT MAX(A.CurrentHighestBidPrice) 
			FROM Auction A, Post P 
			WHERE A.ItemID = I.ItemID AND 
				P.AuctionID = A.AuctionID AND 
				P.EndDate < CURDATE()) as ‘MinEndBid’, 
			(SELECT FORMAT(AVG(A.CurrentHighestBidPrice), 2) 
				FROM Auction A, Post P 
                WHERE A.ItemID = I.ItemID 
					AND P.AuctionID = A.AuctionID 
					AND P.EndDate < CURDATE()) as ‘AvgEndBid’, 
			(SELECT MIN(A.CurrentHighestBidPrice) 
				FROM Auction A, Post P 
                WHERE A.ItemID = I.ItemID 
					AND P.AuctionID = A.AuctionID 
                    AND P.EndDate < CURDATE()) as ‘MaxEndBid’ 
	FROM Item I;



-- Produce a list of sales by item name
SELECT 
		I.ItemID,
        I.ItemName,
        A.AuctionID,
        A.CurrentHighestBidPrice,
        P.OpenDate,
        P.EndDate
	FROM 
		Auction A, 
        Item I, 
        Post P
	WHERE 
		A.ItemID = I.ItemID AND 
        A.AuctionID = P.AuctionID AND 
        A.ItemID = 987654321;



-- Produce a list of sales by customer name
SELECT 
		P.CustomerID, 
        I.ItemName, 
        A.AuctionID, 
        A.CurrentHighestBidPrice, 
        P.OpenDate, 
        P.EndDate
	FROM 
		Auction A, 
        Item I, 
        Post P
	WHERE 
		A.ItemID = I.ItemID AND 
        A.AuctionID = P.AuctionID AND 
        P.CustomerID = 333333333;



-- Produce a summary listing of revenue generated by a particular item
SELECT 
		I.ItemName, 
        SUM(A.CurrentHighestBidPrice) AS TotalRevenue
	FROM 
		Auction A,
        Item I, 
        Post P
	WHERE 
		I.ItemName = 'Titanic' AND 
        I.ItemID = A.ItemID AND 
        A.AuctionID = P.AuctionID AND 
        P.EndDate < CURDATE();



-- Produce a summary listing of revenue generated by a particular item type
SELECT 
		I.ItemType, 
        SUM(A.CurrentHighestBidPrice) AS TotalRevenue
	FROM 
		Auction A, 
        Item I, 
        Post P
	WHERE 
		I.ItemType = 'DVD' AND 
		I.ItemID = A.ItemID AND 
        A.AuctionID = P.AuctionID AND 
        P.EndDate < CURDATE();



-- Produce a summary listing of revenue generated by a particular customer 
SELECT 
		C.CustomerID, 
        CONCAT(C.FirstName, ' ', C.LastName) AS Name, 
        SUM(A.CurrentMaxBidPrice) AS TotalRevenue
	FROM 
		Auction A, 
        Customer C, 
        Post P
	WHERE 
		C.CustomerID = 333333333 AND 
        C.CustomerID = P.CustomerID AND 
        A.AuctionID = P.AuctionID AND 
        P.EndDate < CURDATE();



-- Determine which customer representative generated the most total revenue
SELECT 
		C.CustomerID, 
        CONCAT(C.FirstName, ‘ ‘, C.LastName) AS Name, 
        C.Rating, 
        SUM(A.CurrentHighestBidPrice) AS TotalRevenue
	FROM 
		Customer C, 
        Auction A, 
        Post P
	WHERE 
		C.CustomerID = P.CustomerID AND 
        P.AuctionID = A.AuctionID AND 
        P.EndDate < CURDATE() 
	GROUP BY 1 
    ORDER BY TotalRevenue DESC;



-- Determine which customer generated the most total revenue
SELECT 
		C.CustomerID, 
        CONCAT(C.FirstName, ‘ ‘, C.LastName) AS Name, 
        C.Rating, SUM(A.CurrentHighestBidPrice) AS TotalRevenue
	FROM 
		Customer C, 
        Auction A, 
        Post P
	WHERE 
		C.CustomerID = P.CustomerID AND 
        P.AuctionID = A.AuctionID AND 
        P.EndDate < CURDATE() 
	GROUP BY 
		TotalRevenue
    ORDER BY 
		TotalRevenue DESC;



-- Produce a Best-Sellers list of items
SELECT 
        * 
	FROM 
        Item 
    ORDER BY
        Quantity DESC;



---- Customer Level Transactions

-- Record a sale
--CREATE VIEW highestBid AS
--    SELECT Bid.CustomerID, Post.AuctionID,  Auction.ItemID, Bid.BidPrice
--    FROM Post 
--    INNER JOIN Auction ON (Post.AuctionID = Auction.AuctionID) 
--    INNER JOIN Bid on (Post.AuctionID =Bid.AuctionID)
--    Where Post.EndDate <= NOW() AND Auction.CurrentHighestBidPrice > Auction.ReservePrice;
--
--  Insert  into Sale 
--  Select customerID, AuctionID, ItemID, BidPrice
--   FROM (
--         select s.*
--    from highestBid s
--where s.BidPrice = (select max(s2.BidPrice) from highestBid s2 where s2.customerID = s.customerID)
--    );
--Where Max(BidPrice)

-- I don't know what happened, but this is from the Google doc



-- Add information for a customer
UPDATE 
		Customer
	SET 
		FirstName = 'Tom',
        LastName = 'Trump',
		Address = '30 Walnut Drive, Bohemia, NY',
        ZipCode = '13503',
        Telephone = '1112223333',
        Email = 'tom@smolka.com',
        Rating = 2,
        CreditCardNum = '1000000696969696',
        ItemsSold = 1,
		items_purchased = 1
	WHERE CustomerID = 555555555;



-- Delete information for a customer
DELETE FROM 
		Customer
	WHERE 
		CustomerID = 555555555;



-- Produce customer mailing lists
SELECT 
		Customer.Email
	FROM 
		Customer;



-- Produce a list of item suggestions for a given customer (based on that customer's past purchases)
SELECT 
		AuctionID, 
        MinBidPrice, 
        IF(ReservePrice IS NULL, 0, IF(CurrentMaxBidPrice >= ReservePrice, 1, 2)) AS ReserveStatus, CurrentHighestBidPrice, NumCopies, ItemID, ItemName, ItemType, Description, OpenDate, EndDate
	FROM 
		(Auction NATURAL JOIN Item) NATURAL JOIN Post
	WHERE 
		ItemType = ? AND OpenDate <= NOW() AND NOW() < EndDate;








---- Customer-Level Transactions

-- Place bid
-- Transaction starts here
INSERT INTO 
		Bid
	SELECT 
		'222222222' 
	AS 
		CustomerID, 
        AuctionID, 
        NOW() AS BidTime, 
        12.00 AS BidPrice
	FROM 
		Auction
	WHERE 
		AuctionID = 1357 AND 
        IF(CurrentHighestBidPrice IS NULL, 12.00 >= MinBidPrice, 12.00 > CurrentHighestBidPrice);
    
    -- If the bid is not inserted into the Bid table above then do not
    -- proceed and rollback the transaction

-- Assuming that :bidPrice is >= MinBidPrice
-- and > CurrentHighestBidPrice, if applicable
UPDATE 
		Auction
	SET 
		CurrentHighestBidPrice = IF(CurrentHighestBidPrice IS NULL, MinBidPrice, IF(12.00 > CurrentMaxBidPrice, LEAST(12.00, CurrentMaxBidPrice + BidIncrement), LEAST(12.00 + BidIncrement, CurrentMaxBidPrice))),
		CurrentMaxBidPrice = IF(CurrentMaxBidPrice IS NULL, 12.00, IF(12.00 > CurrentMaxBidPrice, 12.00, CurrentMaxBidPrice)),
		BidIncrement = 1.00
	WHERE 
		AuctionID = 1357;
-- Transaction ends here



-- Bid history for each auction
SELECT 
		CustomerID, 
        AuctionID, 
        BidTime 
	FROM 
		Bid
	WHERE 
		AuctionID = 1357h
	ORDER BY 
		BidTime DESC;



-- History of all current and past auctions a customer has taken part in
SELECT 
		CustomerID, 
        AuctionID, 
        MAX(BidTime) AS LastBidTime, 
        MAX(BidPrice) AS LastBidPrice
	FROM 
		Bid NATURAL JOIN Auction
	WHERE 
		CustomerID = 111111111
	GROUP BY 
		AuctionID
	ORDER BY 
		LastBidTime DESC;



-- Items sold by a given seller and corresponding auction info
SELECT 
		P.CustomerID AS Seller, 
        S.CustomerID AS Buyer, 
        AuctionID, 
        CurrentHighestBidPrice AS SoldFor, 
        NumCopies, 
        I.ItemID, 
        ItemName, 
        ItemType, 
        Description, 
        OpenDate, 
        EndDate
	FROM ((
		Sale AS S INNER JOIN Auction AS A USING (AuctionID)) 
        INNER JOIN Post AS P USING (AuctionID)) 
        INNER JOIN Item AS I ON I.ItemID = A.ItemID
	WHERE P.CustomerID = 444444444;
    


-- Items available of a particular type and corresponding auction info
SELECT 
		AuctionID, 
        MinBidPrice, 
        IF(ReservePrice IS NULL, 0, IF(CurrentMaxBidPrice >= ReservePrice, 1, 2)) AS ReserveStatus, 
        CurrentHighestBidPrice, 
        NumCopies, 
        ItemID, 
        ItemName, 
        ItemType, 
        Description, 
        OpenDate, 
        EndDate
	FROM 
		(Auction NATURAL JOIN Item) NATURAL JOIN Post
	WHERE 
		ItemType = 'Car' AND 
        OpenDate <= NOW() AND 
        NOW() < EndDate;



-- Items available with a particular keyword or set of keywords in the item name, and corresponding auction info
SELECT 
		AuctionID, 
        MinBidPrice, 
        IF(ReservePrice IS NULL, 0, IF(CurrentMaxBidPrice >= ReservePrice, 1, 2)) AS ReserveStatus, 
        CurrentHighestBidPrice,
        NumCopies, 
        ItemID, 
        ItemName, 
        ItemType, 
        Description, 
        OpenDate, 
        EndDate
	FROM 
		(Auction NATURAL JOIN Item) NATURAL JOIN Post
	WHERE 
		OpenDate <= NOW() AND 
		NOW() < EndDate AND 
        MATCH (ItemName) AGAINST ('se nissan' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION);
        
	-- Note: I changed NOW() to a specific date in order to demonstrate the keyword 
    --   search.
	-- I used a FULLTEXT index, which is a neat device MySQL supports in order to 
    --   allow for complex queries on text-based data, on ItemName. 
    --   (https://dev.mysql.com/doc/refman/8.0/en/fulltext-natural-language.html).



-- Best-Seller list
SELECT 
		ItemID, 
        ItemName, 
        ItemType,
        Description, 
        COUNT(*) AS NumSold
	FROM 
		Item NATURAL JOIN Sale
	GROUP BY 
		ItemID
	ORDER BY 
		NumSold DESC;
        


-- Personalized item suggestion list
SELECT 
		*
	FROM 
		Item
	WHERE 
		ItemType IN ('DVD') AND 
        MATCH (ItemName) AGAINST ('titanic movie' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION);

