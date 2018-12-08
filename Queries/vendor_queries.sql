-- Manager Level Transactions

-- Add new employee
INSERT INTO VendorUser VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO Employee VALUES (?, ?, ?, ?, ?);

-- (Manager access-level) Edit the info. of employees below him
UPDATE VendorUser INNER JOIN Employee ON Username = EmployeeID
SET Username = ?,
    UserPassword = ?,
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
SELECT *
FROM (Item LEFT JOIN 
(SELECT ItemID, COUNT(*) AS NumAuctionsSold,
        FORMAT(MAX(CurrentHighestBidPrice / NumCopies), 2) AS MaxSoldPrice,
        FORMAT(MIN(CurrentHighestBidPrice / NumCopies), 2) AS MinSoldPrice,
        FORMAT(AVG(CurrentHighestBidPrice / NumCopies), 2) AS AvgSoldPrice,
        SUM(NumCopies) AS TotalNumSold,
        SUM(CurrentHighestBidPrice) AS TotalRevenue
 FROM Auction
 WHERE EndDate < NOW() AND WinningBidID IS NOT NULL GROUP BY ItemID
) AS A1 USING (ItemID)) LEFT JOIN
(SELECT ItemID, COUNT(*) AS NumAuctionsActive
 FROM Auction
 WHERE EndDate >= NOW() OR WinningBidID IS NULL GROUP BY ItemID
) AS A2 USING (ItemID);

-- Produce a list of sales by item name
SELECT AuctionID, ItemName, CurrentHighestBidPrice AS SoldPrice
FROM Auction INNER JOIN Item USING (ItemID)
WHERE ItemName LIKE CONCAT('%', ?, '%') AND EndDate < NOW() AND WinningBidID IS NOT NULL;

-- Produce a list of sales by customer name (seller)
SELECT AuctionID, Seller, FirstName, LastName, CurrentHighestBidPrice AS SoldPrice
FROM Auction AS A INNER JOIN Customer AS C ON A.Seller = C.CustomerID
WHERE FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%') AND EndDate < NOW() AND WinningBidID IS NOT NULL;

-- Produce a list of sales by customer name (seller)
SELECT AuctionID, Seller, FirstName, LastName, CurrentHighestBidPrice AS SoldPrice
FROM Auction AS A INNER JOIN Customer AS C ON A.Seller = C.CustomerID
WHERE FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%');

-- Customer-representative Level Transactions

-- Customer Level Transactions
