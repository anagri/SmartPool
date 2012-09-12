DROP database IF EXISTS `smartpool`;

CREATE DATABASE `smartpool`;

USE `smartpool`;

DROP TABLE IF EXISTS `buddies`;

CREATE TABLE `buddies` (
  `username` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `preferred_pickup_location` varchar(20) DEFAULT NULL,
  `preferred_pickup_time` time DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `carpool`;

CREATE TABLE `carpool` (
  `name` varchar(50) NOT NULL DEFAULT '',
  `start_date` date DEFAULT NULL,
  `cab_type` enum('PERSONAL','COMPANY') DEFAULT NULL,
  `office_eta` time DEFAULT NULL,
  `office_etd` time DEFAULT NULL,
  `status` enum('ACTIVE','NOT_STARTED') DEFAULT NULL,
  `total_cab_charge` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `request_sent` TINYINT(1) DEFAULT false,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `carpoolbuddy`;

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


DROP TABLE IF EXISTS `requests`;

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


DROP TABLE IF EXISTS `route`;

CREATE TABLE `route` (
  `carpoolName` varchar(50) NOT NULL DEFAULT '',
  `location` varchar(25) NOT NULL DEFAULT '',
  `sequenceNumber` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`carpoolName`,`location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
