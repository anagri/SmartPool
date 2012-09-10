-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
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
-- Current Database: `smartpool`
--

DROP database IF EXISTS `smartpool`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `smartpool` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `smartpool`;

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
  `preferred_pickup_location` varchar(20) DEFAULT NULL,
  `preferred_pickup_time` time DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buddies`
--

LOCK TABLES `buddies` WRITE;
/*!40000 ALTER TABLE `buddies` DISABLE KEYS */;
INSERT INTO `buddies` VALUES ('arnavku','Arnav Kumar','M-58, Diamond District, Bangalore','arnavku@thoughtworks.com','1234567890',NULL,NULL),('ayu','Anna Yu','G-31, Diamond District, Bangalore','ayu@thoughtworks.com','1234567890',NULL,NULL),('ayusht','Ayush Tulsyan','H-81, Diamond District, Bangalore','ayusht@thoughtworks.com','1234567890',NULL,NULL),('govindm','Govind Menon','P-81, Diamond District, Bangalore','govindm@thoughtworks.com','1234567890',NULL,NULL),('ishak','Isha Khanna','H-71, Diamond District, Bangalore','ishak@thoughtworks.com','0987654321',NULL,NULL),('mdaliej','Md Ali Ejaz','C-82, Diamond District, Bangalore','mdaliej@thoughtworks.com','0987654321',NULL,NULL),('mzhao','Ming Zhao','J-41, Diamond District, Bangalore','mzhao@thoughtworks.com','1234567890',NULL,NULL),('nibub','Nibu Baby','#659, Jayanthi Residency, Flat# 301, 1st Main, C Block,AECS Layout, Kundalahalli, Bangalore','nibub@thoughtworks.com','9686190831',NULL,NULL),('prithvin','Prithvi Nambiar','H-81, Diamond District, Bangalore','prithvin@thoughtworks.com','1234567890',NULL,NULL),('ssquire','Samuel Michael Squir','P-81, Diamond District, Bangalore','ssquire@thoughtworks.com','0987654321',NULL,NULL),('suganthk','Suganthi T','L-82, Diamond District, Bangalore','suganthit@thoughtworks.com','1234567890',NULL,NULL),('test.twu','Test User','Somewhere, Bangalore','test@thoughtworks.com','1234567890',NULL,NULL),('vfranca','Vitor Franca','F-75, Diamond District, Bangalore','vfranca@thoughtworks.com','1234567890',NULL,NULL);
/*!40000 ALTER TABLE `buddies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carpool`
--

DROP TABLE IF EXISTS `carpool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carpool` (
  `name` varchar(50) NOT NULL DEFAULT '',
  `start_date` date DEFAULT NULL,
  `cab_type` enum('PERSONAL','COMPANY') DEFAULT NULL,
  `office_eta` time DEFAULT NULL,
  `office_etd` time DEFAULT NULL,
  `status` enum('ACTIVE','NOT_STARTED') DEFAULT NULL,
  `total_cab_charge` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpool`
--

LOCK TABLES `carpool` WRITE;
/*!40000 ALTER TABLE `carpool` DISABLE KEYS */;
INSERT INTO `carpool` VALUES ('carpool-1','2012-06-02','COMPANY','11:30:00','18:30:00','ACTIVE',100,4),('carpool-2','2012-08-02','PERSONAL','09:30:00','19:30:00','NOT_STARTED',600,8);
/*!40000 ALTER TABLE `carpool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carpoolbuddy`
--

DROP TABLE IF EXISTS `carpoolbuddy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carpoolbuddy` (
  `buddy_username` varchar(11) NOT NULL DEFAULT '',
  `carpool_name` varchar(50) NOT NULL DEFAULT '',
  `pickup_point` varchar(50) DEFAULT NULL,
  `pickup_time` time DEFAULT NULL,
  PRIMARY KEY (`buddy_username`,`carpool_name`),
  KEY `carpool_name` (`carpool_name`),
  CONSTRAINT `carpoolbuddy_ibfk_1` FOREIGN KEY (`buddy_username`) REFERENCES `buddies` (`username`),
  CONSTRAINT `carpoolbuddy_ibfk_2` FOREIGN KEY (`carpool_name`) REFERENCES `carpool` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpoolbuddy`
--

LOCK TABLES `carpoolbuddy` WRITE;
/*!40000 ALTER TABLE `carpoolbuddy` DISABLE KEYS */;
INSERT INTO `carpoolbuddy` VALUES ('arnavku','carpool-1','Diamond District','10:50:00'),
                                  ('ayusht','carpool-1','Manipal Hospital','10:55:00'),
                                  ('nibub','carpool-1','Kundalahalli','11:10:00'),
                                  ('mdaliej','carpool-1','Taco Bell','11:30:00'),
                                  ('ishak','carpool-2','Command Hospital','08:30:00'),
                                  ('mdaliej','carpool-2','Trinity Church','08:45:00'),
                                  ('mzhao','carpool-2','barbeque nation','09:00:00');
/*!40000 ALTER TABLE `carpoolbuddy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `username` varchar(11) NOT NULL,
  `carpoolname` varchar(50) NOT NULL,
  `preferredPickupTime` time NOT NULL,
  `preferredPickupPoint` varchar(25) NOT NULL,
  `address` varchar(100) NOT NULL,
  KEY `username` (`username`),
  KEY `carpoolname` (`carpoolname`),
  UNIQUE KEY `user_request` (`username`, `carpoolname`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`username`) REFERENCES `buddies` (`username`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`carpoolname`) REFERENCES `carpool` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES ('ishak','carpool-1','00:00:00','here','diamond district somewhere');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `carpoolName` varchar(50) NOT NULL DEFAULT '',
  `location` varchar(25) NOT NULL DEFAULT '',
  `sequenceNumber` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`carpoolName`,`location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES ('carpool-1','Dell Office',1),('carpool-1','diamond district',2),('carpool-1','Domlur Flyover',3),('carpool-1','Manipal Hospital',4),('carpool-1','Sony Centre',5),('carpool-2','Barbeque Nation',1),('carpool-2','Command Hospital',2),('carpool-2','Dell Office',3),('carpool-2','Sony Centre',4),('carpool-2','Trinity Church',5);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-06 15:17:16
