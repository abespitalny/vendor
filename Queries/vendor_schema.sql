-- DROP DATABASE IF EXISTS vendor;
-- CREATE DATABASE vendor;
USE vendor;

CREATE TABLE IF NOT EXISTS VendorUser (
    Username VARCHAR(16),
    UserPassword VARCHAR(32) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    City VARCHAR(255),
    State CHAR(2),
    ZipCode CHAR(5),
    Telephone CHAR(10),
    Email VARCHAR(255) NOT NULL,
    PRIMARY KEY (Username)
);

CREATE TABLE IF NOT EXISTS Customer (
	CustomerID VARCHAR(16),
    Rating DECIMAL(4, 1) UNSIGNED DEFAULT 100.0,
    CreditCardNum CHAR(16),
    ItemsSold INTEGER UNSIGNED DEFAULT 0,
    ItemsPurchased INTEGER UNSIGNED DEFAULT 0,
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (CustomerID) REFERENCES VendorUser(Username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Employee (
    EmployeeID VARCHAR(16),
	SSN CHAR(9) NOT NULL,
	StartDate DATE NOT NULL,
    -- Note: you have to use strings as ENUM values but it doesn't take up more space than an INT since
    -- the values are auto. converted to indices for you
    EmployeeLevel ENUM('CustomerRep', 'Manager') NOT NULL,
    HourlyRate DECIMAL(13, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (EmployeeID),
    UNIQUE (SSN),
    FOREIGN KEY (EmployeeID) REFERENCES VendorUser(Username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Item (
	ItemID SERIAL,
    ItemName VARCHAR(255) NOT NULL,
    ItemType VARCHAR(255),
    Description TEXT,
    Quantity INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (ItemID),
    FULLTEXT INDEX KeywordSearch (ItemName)
);

CREATE TABLE IF NOT EXISTS Auction (
	AuctionID SERIAL,
    BidIncrement DECIMAL(13, 2) UNSIGNED NOT NULL,
    MinBidPrice DECIMAL(13, 2) UNSIGNED NOT NULL,
    ReservePrice DECIMAL(13, 2) UNSIGNED,
    CurrentHighestBidPrice DECIMAL(13, 2) UNSIGNED,
    CurrentMaxBidPrice DECIMAL(13, 2) UNSIGNED,
    NumCopies INTEGER UNSIGNED NOT NULL,
    -- I cannot add the NOT NULL modifier because if the seller's account is deleted then this value will be set to NULL
    Seller VARCHAR(16),
    Monitor VARCHAR(16) NOT NULL,
    ItemID BIGINT UNSIGNED NOT NULL,
    OpenDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    -- It's NULL unless there was a sale that was fully completed, that is the customer paid for the item 
    SaleID BIGINT UNSIGNED,
    PRIMARY KEY (AuctionID),
    FOREIGN KEY (Seller) REFERENCES Customer(CustomerID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Monitor) REFERENCES Employee(EmployeeID) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Bid (
    -- As per the documentation, LAST_INSERT_ID() function is maintained on a per-connection basis so there isn't a race condition if 
    -- multiple threads insert and call LAST_INSERT_ID()
    BidID SERIAL,
    -- ID of bidder
	CustomerID VARCHAR(16) NOT NULL,
    AuctionID BIGINT UNSIGNED NOT NULL,
    BidTime DATETIME NOT NULL,
    BidPrice DECIMAL(13, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (BidID),
    -- A bidder cannot bid twice on the same auction at the same time
    UNIQUE (CustomerID, AuctionID, BidTime),
    -- A bidder cannot bid on the same auction at the same price
    UNIQUE (CustomerID, AuctionID, BidPrice),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (AuctionID) REFERENCES Auction(AuctionID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Sale (
    WinningBidID BIGINT UNSIGNED,
    -- SaleStatus values:
    -- 0: Pending, that is it was recorded by employee but the customer has not been able to perform an action
    -- 1: Paid, the customer fulfilled the auction
    -- 2: Cancelled, the customer cancelled his order
    SaleStatus ENUM('Pending', 'Paid', 'Cancelled') NOT NULL,
    SaleDate DATETIME NOT NULL,
    -- Employee that recorded the sale
    -- I cannot add the NOT NULL modifier because if the employee's account is deleted then this value will be set to NULL
    Recorder VARCHAR(16),
    PRIMARY KEY (WinningBidID),
    FOREIGN KEY (Recorder) REFERENCES Employee(EmployeeID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (WinningBidID) REFERENCES Bid(BidID) ON DELETE NO ACTION ON UPDATE CASCADE
);

ALTER TABLE Auction ADD
FOREIGN KEY (SaleID) REFERENCES Sale(WinningBidID) ON DELETE SET NULL ON UPDATE CASCADE;