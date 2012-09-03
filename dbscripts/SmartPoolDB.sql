
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
-- Current Database: `smartpool`
--

GRANT ALL PRIVILEGES ON *.* TO 'root'@'10.%.%.%' WITH GRANT OPTION;

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
  `pickup_location` varchar(20) DEFAULT NULL,
  `pickup_time` time DEFAULT NULL,
  `preferred_pickup_location` varchar(20) DEFAULT NULL,
  `preferred_pickup_time` time DEFAULT NULL,
  `carpoolname` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buddies`
--

LOCK TABLES `buddies` WRITE;
/*!40000 ALTER TABLE `buddies` DISABLE KEYS */;
INSERT INTO `buddies` VALUES ('arnavku','Arnav Kumar','M-58, Diamond District, Bangalore','arnavku@thoughtworks.com','1234567890','Diamond District','10:50',NULL,NULL,'carpool-1'),
('ayusht','Ayush Tulsyan','H-81, Diamond District, Bangalore','ayusht@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,'carpool-1'),
('govindm','Govind Menon','P-81, Diamond District, Bangalore','govindm@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,'carpool-1'),
('ishak','Isha Khanna','H-71, Diamond District, Bangalore','ishak@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,'carpool-2'),
('mdaliej','Md Ali Ejaz','C-82, Diamond District, Bangalore','mdaliej@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,'carpool-2'),
('mzhao','Ming Zhao','J-41, Diamond District, Bangalore','mzhao@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,'carpool-2'),
('prithvin','Prithvi Nambiar','H-81, Diamond District, Bangalore','prithvin@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),
('ssquire','Samuel Michael Squir','P-81, Diamond District, Bangalore','ssquire@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),
('suganthk','Suganthi T','L-82, Diamond District, Bangalore','suganthit@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),
('test.twu','Test User','Somewhere, Bangalore','test@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),
('nibub','Nibu Baby','#659, Jayanthi Residency, Flat# 301, 1st Main, C Block,AECS Layout, Kundalahalli, Bangalore','nibub@thoughtworks.com','9686190831','Kundalahalli','11:10',NULL,NULL,'carpool-1'),
('vfranca','Vitor Franca','F-75, Diamond District, Bangalore','vfranca@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL);
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
  `start_date` date,
  `cab_type` enum('PERSONAL','COMPANY'),
  `office_eta` time,
  `office_etd` time,
  `status` enum('RUNNING','PENDING'),
  `total_cab_charge` int,
  `capacity` int,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpool`
--

LOCK TABLES `carpool` WRITE;
/*!40000 ALTER TABLE `carpool` DISABLE KEYS */;
INSERT INTO `carpool` VALUES ('carpool-1', '2012-06-02', 'COMPANY', '11:30', '18:30', 'RUNNING', 100, 4);
INSERT INTO `carpool` VALUES ('carpool-2', '2012-08-02', 'PERSONAL', '09:30', '19:30', 'PENDING', 600, 8);
/*!40000 ALTER TABLE `carpool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `username` varchar(11) NOT NULL,
  `carpoolname` varchar(11) NOT NULL,
  `preferredPickupTime` varchar(10) DEFAULT NULL,
  `preferredPickupPoint` varchar(25) NOT NULL,
  KEY `username` (`username`),
  KEY `carpoolname` (`carpoolname`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`username`) REFERENCES `buddies` (`username`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`carpoolname`) REFERENCES `carpool` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE requests ADD PRIMARY KEY (username,carpoolname);
--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES ('ishak','carpool-1','now','here');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `carpoolName` varchar(20) NOT NULL DEFAULT '',
  `location` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`carpoolName`,`location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES ('carpool-1','diamond district');
INSERT INTO `route` VALUES ('carpool-1','Manipal Hospital');
INSERT INTO `route` VALUES ('carpool-1','Domlur Flyover');
INSERT INTO `route` VALUES ('carpool-1','Dell Office');
INSERT INTO `route` VALUES ('carpool-1','Sony Centre');
INSERT INTO `route` VALUES ('carpool-2','Command Hospital');
INSERT INTO `route` VALUES ('carpool-2','Trinity Church');
INSERT INTO `route` VALUES ('carpool-2','Barbeque Nation');
INSERT INTO `route` VALUES ('carpool-2','Dell Office');
INSERT INTO `route` VALUES ('carpool-2','Sony Centre');

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

-- Dump completed on 2012-08-29 16:55:05


