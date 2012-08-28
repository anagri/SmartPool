--
-- Table structure for table `buddies`
--
Drop DATABASE IF EXISTS smartpool;
create DATABASE smartpool;
use smartpool;


DROP TABLE IF EXISTS `requests`;
DROP TABLE IF EXISTS `carpool`;
DROP TABLE IF EXISTS `buddies`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `buddies` VALUES ('prithvin','Prithvi Nambiar','H-81, Diamond District, Bangalore','prithvin@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('ayusht','Ayush Tulsyan','H-81, Diamond District, Bangalore','ayusht@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('arnavku','Arnav Kumar','M-58, Diamond District, Bangalore','arnavku@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('ishak','Isha Khanna','H-71, Diamond District, Bangalore','ishak@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('mdalie','Md Ali Ejaz','C-82, Diamond District, Bangalore','mdalie@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('ssquire','Samuel Michael Squir','P-81, Diamond District, Bangalore','ssquire@thoughtworks.com','0987654321',NULL,NULL,NULL,NULL,NULL),('govindm','Govind Menon','P-81, Diamond District, Bangalore','govindm@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('suganthk','Suganthi T','L-82, Diamond District, Bangalore','suganthit@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL),('mzhao','Ming Zhao','J-41, Diamond District, Bangalore','mzhao@thoughtworks.com','1234567890',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `buddies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carpool`
--


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


-- Dumping data for table `pickupPoints`
--

--
-- Table structure for table `requests`
--
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
