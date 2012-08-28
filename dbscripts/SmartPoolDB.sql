-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: smartpool
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buddies`
--

DROP TABLE IF EXISTS `buddies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buddies` (
  `username` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `pickup_location` varchar(20) DEFAULT NULL,
  `pickup_time` time DEFAULT NULL,
  `preferred_pickup_location` varchar(20) DEFAULT NULL,
  `preferred_pickup_time` time DEFAULT NULL,
  `carpoolid` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buddies`
--

LOCK TABLES `buddies` WRITE;
/*!40000 ALTER TABLE `buddies` DISABLE KEYS */;
INSERT INTO `buddies` VALUES ('1','Prithvi Nambiar','H-81, Diamond District, Bangalore','prithvin@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('2','Ayush Tulsyan','H-81, Diamond District, Bangalore','ayusht@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('4','Arnav Kumar','M-58, Diamond District, Bangalore','arnavku@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('5','Isha Khanna','H-71, Diamond District, Bangalore','ishak@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('6','Md Ali Ejaz','C-82, Diamond District, Bangalore','mdalie@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('7','Samuel Michael Squir','P-81, Diamond District, Bangalore','ssquire@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('8','Govind Menon','P-81, Diamond District, Bangalore','govindm@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('suganthk','Suganthi T','L-82, Diamond District, Bangalore','suganthit@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `buddies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carpool`
--

DROP TABLE IF EXISTS `carpool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carpool` (
  `name` varchar(15) NOT NULL DEFAULT '',
  `start_location` varchar(20) DEFAULT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `buddy1` varchar(20) DEFAULT NULL,
  `buddy2` varchar(20) DEFAULT NULL,
  `buddy3` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpool`
--

LOCK TABLES `carpool` WRITE;
/*!40000 ALTER TABLE `carpool` DISABLE KEYS */;
INSERT INTO `carpool` VALUES ('carpool-1','Whitefield','8.00','1','2','3');
/*!40000 ALTER TABLE `carpool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickupPoints`
--

DROP TABLE IF EXISTS `pickupPoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pickupPoints` (
  `username` varchar(11) NOT NULL DEFAULT '',
  `pickupPoint` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`,`pickupPoint`),
  CONSTRAINT `pickupPoints_ibfk_1` FOREIGN KEY (`username`) REFERENCES `buddies` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickupPoints`
--

LOCK TABLES `pickupPoints` WRITE;
/*!40000 ALTER TABLE `pickupPoints` DISABLE KEYS */;
/*!40000 ALTER TABLE `pickupPoints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `username` varchar(11) DEFAULT NULL,
  `carpoolname` varchar(11) DEFAULT NULL,
  `preferredPickupTime` varchar(10) DEFAULT NULL,
  `preferredPickupPoint` varchar(25) DEFAULT NULL,
  KEY `username` (`username`),
  KEY `carpoolname` (`carpoolname`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`username`) REFERENCES `buddies` (`username`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`carpoolname`) REFERENCES `carpool` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES ('1','carpool-1','',''),('1','carpool-1','dfg','gs'),('1','carpool-1','Domlur','9:00'),(NULL,'carpool-1','now','now'),(NULL,'carpool-1','now','now'),(NULL,'carpool-1','now','now'),('1','carpool-1','Domlur','9:00'),(NULL,'carpool-1','now','now'),('1','carpool-1','Domlur','9:00'),('1','carpool-1','Domlur','9:00'),('1','carpool-1','Domlur','9:00');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-08-28 13:53:17
