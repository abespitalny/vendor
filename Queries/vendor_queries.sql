-- Manager Level Transactions

-- Add new employee
INSERT INTO VendorUser VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO Employee VALUES (?, ?, ?, ?, ?);

-- (Manager access-level) Edit the info. of employees below him
UPDATE VendorUser INNER JOIN Employee ON Username = EmployeeID
SET Username = ?,
    FirstName = ?,
    LastName = ?,
    Address = ?,
    City = ?,
    State = ?,
    ZipCode = ?,
    Telephone = ?,
    Email = ?,
    SSN = ?,
    HourlyRate = ?
WHERE
    Username = ? AND EmployeeLevel < ?;

-- Manager can reset password of an employee below him
UPDATE VendorUser INNER JOIN Employee ON Username = EmployeeID
SET PasswordHash = UNHEX(SHA2(?, 256))
WHERE Username = ? AND EmployeeLevel < ?;

-- (Customer-representative access-level) Employees can edit their own information
UPDATE VendorUser INNER JOIN Employee ON Username = EmployeeID
SET FirstName = ?,
    LastName = ?,
    Address = ?,
    City = ?,
    State = ?,
    ZipCode = ?,
    Telephone = ?,
    Email = ?,
    SSN = ?
WHERE 
    Username = ?;

-- Manager can delete an employee below him
DELETE FROM VendorUser INNER JOIN Employee ON Username = EmployeeID
WHERE Username = ? AND EmployeeLevel < ?;

-- Obtain a sales report for a particular month
SELECT AuctionID, ItemName, CurrentHighestBidPrice AS SoldPrice
FROM Auction INNER JOIN Item USING (ItemID)
WHERE MONTH(EndDate) = ? AND EndDate < NOW() AND WinningBidID IS NOT NULL;

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

SELECT *
FROM Item INNER JOIN 
(SELECT A1.ItemID, COUNT(*) AS NumAuctionsSold,
        FORMAT(MAX(CurrentHighestBidPrice / NumCopies), 2) AS MaxSoldPrice,
        FORMAT(MIN(CurrentHighestBidPrice / NumCopies), 2) AS MinSoldPrice,
        FORMAT(AVG(CurrentHighestBidPrice / NumCopies), 2) AS AvgSoldPrice,
        SUM(NumCopies) AS TotalNumSold,
        SUM(CurrentHighestBidPrice) AS TotalRevenue
 FROM Auction AS A1 INNER JOIN
 (SELECT A2.ItemID, COUNT(*) AS NumActiveAuctions
  FROM Auction AS A2
  WHERE A2.EndDate >= NOW() OR A2.WinningBidID IS NULL GROUP BY A2.ItemID
 ) USING (A1.ItemID)
 WHERE A1.EndDate < NOW() AND A1.WinningBidID IS NOT NULL GROUP BY A1.ItemID
) USING (ItemID);


-- Customer-representative Level Transactions

-- Customer Level Transactions
