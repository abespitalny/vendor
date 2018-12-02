-- mysql -u root -h 35.237.254.109 -p

USE vendor;

-- Relational Model
CREATE TABLE Auction (
    AuctionId INTEGER(9),
    ItemId INTEGER(9) NOT NULL,
    SellerId INTEGER(9) NOT NULL,
    BuyerId INTEGER(9),
    OpenDateTime DATETIME NOT NULL,
    CloseDateTime DATETIME,
    MinBidPrice DECIMAL(13, 2) UNSIGNED NOT NULL,
    ReservePrice DECIMAL(13, 2) UNSIGNED,
    CurrentHighestBidPrice DECIMAL(13, 2) UNSIGNED,
    CurrentMaxBidPrice DECIMAL(13, 2) UNSIGNED,
	BidIncrement DECIMAL(13, 2) UNSIGNED,
	EmployeeId CHAR(9) NOT NULL,
	PRIMARY KEY (AuctionId),
	FOREIGN KEY (ItemId) REFERENCES Item,
	FOREIGN KEY (SellerId) REFERENCES Customer(CustomerId),
	FOREIGN KEY (BuyerId) REFERENCES Customer(CustomerId),
	FOREIGN KEY (EmployeeId) REFERENCES Employee(SSN),
	CHECK (SellerId <> BuyerId),
	CHECK (OpenDateTime < CloseDateTime),
	CHECK (MinBidPrice < ReservePrice)
);

CREATE TABLE Item (
    ItemId INTEGER(9),
    Name VARCHAR(255),
    Type VARCHAR(255),
    YearManufactured YEAR,
	Quantity INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (ItemId)
);

CREATE TABLE Customer (
    CustomerId INTEGER(9),
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    State CHAR(2),
    ZipCode CHAR(5),
    Telephone CHAR(10),
    Email VARCHAR(255) NOT NULL,
    CreditCardNumber CHAR(16) NOT NULL,
    ItemsSold INTEGER UNSIGNED DEFAULT 0,
    ItemsPurchased INTEGER UNSIGNED DEFAULT 0,
    Rating DECIMAL(5, 2) UNSIGNED DEFAULT 100.0,
    PRIMARY KEY (CustomerId)
);

CREATE TABLE Bid (
    BidderId INTEGER(9),
    AuctionId INTEGER(9),
    Price DECIMAL(13, 2) UNSIGNED,
    BidDate DATETIME NOT NULL,
    PRIMARY KEY (BidderId, AuctionId, Price),
    FOREIGN KEY (BidderId) REFERENCES Customer(CustomerId),
    FOREIGN KEY (AuctionId) REFERENCES Auction
);

CREATE TABLE Employee (
    SSN CHAR(9),
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Address VARCHAR(255),
    City VARCHAR(255),
    State CHAR(2),
    ZipCode CHAR(5),
    Telephone CHAR(10),
    StartDate DATE NOT NULL,
    EmployeeType ENUM('manager', 'representative') NOT NULL,
    HourlyRate DECIMAL(13, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (SSN)
);

-- CREATE ASSERTION MustBeOneManager 
-- CHECK ((SELECT COUNT(*) 
-- 			FROM Employee 
--           WHERE EmployeeType = 'manager') = 1);



