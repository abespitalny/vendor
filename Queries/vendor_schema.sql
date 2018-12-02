-- DROP DATABASE IF EXISTS vendor;
-- CREATE DATABASE vendor;
USE vendor;

-- Relational Model

CREATE TABLE IF NOT EXISTS User (
    Username VARCHAR(16),
    PasswordHash BINARY(32),
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
    FOREIGN KEY (CustomerID) REFERENCES User(Username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Employee (
    EmployeeID VARCHAR(16),
	SSN CHAR(9) NOT NULL,
	StartDate DATE NOT NULL DEFAULT (CURRENT_DATE()),
    EmployeeLevel INTEGER NOT NULL,
    HourlyRate DECIMAL(13, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (EmployeeID),
    UNIQUE (SSN),
    FOREIGN KEY (EmployeeID) REFERENCES User(Username) ON DELETE CASCADE ON UPDATE CASCADE
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
    Monitor VARCHAR(16) NOT NULL,
    ItemID BIGINT UNSIGNED NOT NULL,
    OpenDate DATETIME NOT NULL DEFAULT (CURRENT_DATE()),
    EndDate DATETIME NOT NULL,
    -- The ID of the winning bid
    WinningBidID BIGINT UNSIGNED,
    PRIMARY KEY (AuctionID),
    FOREIGN KEY (Monitor) REFERENCES Employee(EmployeeID) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Bid (
    -- As per the documentation, LAST_INSERT_ID() function is maintained on a per-connection basis so there isn't a race condition if 
    -- multiple threads insert and call LAST_INSERT_ID()
    BidID SERIAL,
	CustomerID BIGINT UNSIGNED,
    AuctionID BIGINT UNSIGNED,
    BidTime DATETIME,
    BidPrice DECIMAL(13, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (BidID),
    -- A bidder cannot bid twice on the same auction at the same time
    UNIQUE (CustomerID, AuctionID, BidTime),
    -- A bidder cannot bid on the same auction at the same price
    UNIQUE (CustomerID, AuctionID, BidPrice),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (AuctionID) REFERENCES Auction(AuctionID) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Auction ADD
FOREIGN KEY (WinningBidID) REFERENCES Bid(BidID) ON DELETE SET NULL ON UPDATE CASCADE;