-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: vendor
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Auction`
--

DROP TABLE IF EXISTS `Auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Auction` (
  `AuctionID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `BidIncrement` decimal(13,2) unsigned NOT NULL,
  `MinBidPrice` decimal(13,2) unsigned NOT NULL,
  `ReservePrice` decimal(13,2) unsigned DEFAULT NULL,
  `CurrentHighestBidPrice` decimal(13,2) unsigned DEFAULT NULL,
  `CurrentMaxBidPrice` decimal(13,2) unsigned DEFAULT NULL,
  `NumCopies` int(10) unsigned NOT NULL,
  `Seller` varchar(16) DEFAULT NULL,
  `Monitor` varchar(16) NOT NULL,
  `ItemID` bigint(20) unsigned NOT NULL,
  `OpenDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `WinningBidID` bigint(20) unsigned DEFAULT NULL,
  `SaleStatus` enum('Pending','Paid','Cancelled') DEFAULT NULL,
  PRIMARY KEY (`AuctionID`),
  UNIQUE KEY `AuctionID` (`AuctionID`),
  KEY `Seller` (`Seller`),
  KEY `Monitor` (`Monitor`),
  KEY `ItemID` (`ItemID`),
  KEY `WinningBidID` (`WinningBidID`),
  CONSTRAINT `auction_ibfk_1` FOREIGN KEY (`Seller`) REFERENCES `customer` (`customerid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `auction_ibfk_2` FOREIGN KEY (`Monitor`) REFERENCES `employee` (`employeeid`) ON UPDATE CASCADE,
  CONSTRAINT `auction_ibfk_3` FOREIGN KEY (`ItemID`) REFERENCES `item` (`itemid`) ON UPDATE CASCADE,
  CONSTRAINT `auction_ibfk_4` FOREIGN KEY (`WinningBidID`) REFERENCES `bid` (`bidid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auction`
--

LOCK TABLES `Auction` WRITE;
/*!40000 ALTER TABLE `Auction` DISABLE KEYS */;
INSERT INTO `Auction` VALUES (1,1.00,5.00,10.00,NULL,NULL,4,'phil','davidw',1,'2008-12-13 13:00:00','2008-12-16 13:00:00',NULL,NULL),(2,10.00,1000.00,2000.00,NULL,NULL,1,'john','david',2,'2008-12-11 13:00:00','2008-12-16 13:00:00',NULL,NULL),(3,1.00,5.00,10.00,NULL,NULL,1,'phil','david',3,'2008-12-13 13:00:00','2008-12-16 13:00:00',NULL,NULL),(4,1.00,5.00,10.00,NULL,NULL,7,'haixia','david',4,'2008-12-13 13:00:00','2008-12-16 13:00:00',NULL,NULL);
/*!40000 ALTER TABLE `Auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bid`
--

DROP TABLE IF EXISTS `Bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Bid` (
  `BidID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `CustomerID` varchar(16) NOT NULL,
  `AuctionID` bigint(20) unsigned NOT NULL,
  `BidTime` datetime NOT NULL,
  `BidPrice` decimal(13,2) unsigned NOT NULL,
  PRIMARY KEY (`BidID`),
  UNIQUE KEY `BidID` (`BidID`),
  UNIQUE KEY `AuctionID` (`AuctionID`,`BidTime`,`BidPrice`),
  UNIQUE KEY `CustomerID` (`CustomerID`,`AuctionID`,`BidPrice`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`AuctionID`) REFERENCES `auction` (`auctionid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bid`
--

LOCK TABLES `Bid` WRITE;
/*!40000 ALTER TABLE `Bid` DISABLE KEYS */;
INSERT INTO `Bid` VALUES (1,'haixia',1,'2008-12-16 12:00:02',10.00),(2,'shiyong',1,'2008-12-16 12:47:41',9.00),(3,'shiyong',1,'2008-12-16 12:53:13',10.00),(4,'shiyong',1,'2008-12-16 12:59:41',15.00),(5,'shiyong',3,'2008-12-16 12:53:13',10.00),(6,'shiyong',4,'2008-12-16 12:59:41',15.00);
/*!40000 ALTER TABLE `Bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Customer` (
  `CustomerID` varchar(16) NOT NULL,
  `Rating` decimal(4,1) unsigned DEFAULT '100.0',
  `CreditCardNum` char(16) DEFAULT NULL,
  `ItemsSold` int(10) unsigned DEFAULT '0',
  `ItemsPurchased` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`CustomerID`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `vendoruser` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES ('haixia',100.0,'5678123456781234',0,0),('john',100.0,'2345678923456789',0,0),('phil',100.0,'6789234567892345',0,0),('shiyong',100.0,'1234567812345678',0,0);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Employee` (
  `EmployeeID` varchar(16) NOT NULL,
  `SSN` char(9) NOT NULL,
  `StartDate` date NOT NULL,
  `EmployeeLevel` enum('CustomerRep','Manager') NOT NULL,
  `HourlyRate` decimal(13,2) unsigned NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `SSN` (`SSN`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `vendoruser` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('david','123456789','1998-11-01','CustomerRep',60.00),('davidw','789123456','1999-02-02','Manager',50.00);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Item` (
  `ItemID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ItemName` varchar(255) NOT NULL,
  `ItemType` varchar(255) DEFAULT NULL,
  `Description` text,
  `YearManufactured` year(4) DEFAULT NULL,
  `Quantity` int(10) unsigned NOT NULL,
  `NumSold` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ItemID`),
  UNIQUE KEY `ItemID` (`ItemID`),
  FULLTEXT KEY `KeywordSearch` (`ItemName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (1,'Titanic','DVD',NULL,2005,4,0),(2,'Nissan Sentra','Car',NULL,2007,1,0),(3,'Cars 2','DVD',NULL,2012,3,0),(4,'Bee Movie','DVD',NULL,2004,13,0);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VendorUser`
--

DROP TABLE IF EXISTS `VendorUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `VendorUser` (
  `Username` varchar(16) NOT NULL,
  `UserPassword` varchar(32) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `State` char(2) DEFAULT NULL,
  `ZipCode` char(5) DEFAULT NULL,
  `Telephone` char(10) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VendorUser`
--

LOCK TABLES `VendorUser` WRITE;
/*!40000 ALTER TABLE `VendorUser` DISABLE KEYS */;
INSERT INTO `VendorUser` VALUES ('david','2001','David','Smith','123 College Road','Stony Brook','NY','11790','5162152345','david@smith.com'),('davidw','2001','David','Warren','456 Sunken Street','Stony Brook','NY','11794','5166329987','david@warren.com'),('haixia','2018','Haixia','Du','456 Fortune Road','Stony Brook','NY','11790','5166324360','dhaixia@cs.sunysb.edu'),('john','2000','John','Smith','789 Peace Blvd.','Los Angeles','CA','12345','4124434321','shlu@ic.sunysb.edu'),('phil','2001','Phil','Lewis','135 Knowledge Lane','Stony Brook','NY','11790','5166668888','pml@cs.sunysb.edu'),('shiyong','1234','ShiYong','Lu','123 Success Street','Stony Brook','NY','11790','5166328959','shiyong@cs.sunysb.edu');
/*!40000 ALTER TABLE `VendorUser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-14 11:43:46
