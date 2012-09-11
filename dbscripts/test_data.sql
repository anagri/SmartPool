USE `smartpool`;


LOCK TABLES `buddies` WRITE;

INSERT INTO `buddies` VALUES
('arnavku','Arnav Kumar','M-58, Diamond District, Bangalore','arnavku@thoughtworks.com','1234567890',NULL,NULL),
('ayu','Anna Yu','G-31, Diamond District, Bangalore','ayu@thoughtworks.com','1234567890',NULL,NULL),
('ayusht','Ayush Tulsyan','H-81, Diamond District, Bangalore','ayusht@thoughtworks.com','1234567890',NULL,NULL),
('govindm','Govind Menon','P-81, Diamond District, Bangalore','govindm@thoughtworks.com','1234567890',NULL,NULL),
('ishak','Isha Khanna','H-71, Diamond District, Bangalore','ishak@thoughtworks.com','0987654321',NULL,NULL),
('mdaliej','Md Ali Ejaz','C-82, Diamond District, Bangalore','mdaliej@thoughtworks.com','0987654321',NULL,NULL),
('mzhao','Ming Zhao','J-41, Diamond District, Bangalore','mzhao@thoughtworks.com','1234567890',NULL,NULL),
('nibub','Nibu Baby','#659, Jayanthi Residency, Flat# 301, 1st Main, C Block,AECS Layout, Kundalahalli, Bangalore','nibub@thoughtworks.com','9686190831',NULL,NULL),
('prithvin','Prithvi Nambiar','H-81, Diamond District, Bangalore','prithvin@thoughtworks.com','1234567890',NULL,NULL),
('ssquire','Samuel Michael Squir','P-81, Diamond District, Bangalore','ssquire@thoughtworks.com','0987654321',NULL,NULL),
('suganthk','Suganthi T','L-82, Diamond District, Bangalore','suganthk@thoughtworks.com','1234567890',NULL,NULL),
('test.twu','Test User','Somewhere, Bangalore','test@thoughtworks.com','1234567890',NULL,NULL),
('vfranca','Vitor Franca','F-75, Diamond District, Bangalore','vfranca@thoughtworks.com','1234567890',NULL,NULL);

UNLOCK TABLES;


LOCK TABLES `carpool` WRITE;

INSERT INTO `carpool` VALUES
('carpool-1','2012-06-02','COMPANY','11:30:00','18:30:00','ACTIVE',100,4),
('carpool-2','2012-08-02','PERSONAL','09:30:00','19:30:00','NOT_STARTED',600,8);

UNLOCK TABLES;


LOCK TABLES `carpoolbuddy` WRITE;

INSERT INTO `carpoolbuddy` VALUES
('arnavku','carpool-1','Diamond District','10:50:00'),
('ayusht','carpool-1','Manipal Hospital','10:55:00'),
('nibub','carpool-1','Kundalahalli','11:10:00'),
('mdaliej','carpool-1','Taco Bell','11:30:00'),
('ishak','carpool-2','Command Hospital','08:30:00'),
('mdaliej','carpool-2','Trinity Church','08:45:00'),
('mzhao','carpool-2','barbeque nation','09:00:00');

UNLOCK TABLES;


LOCK TABLES `requests` WRITE;

INSERT INTO `requests` VALUES
('ishak','carpool-1','00:00:00','here','diamond district somewhere');

UNLOCK TABLES;


LOCK TABLES `route` WRITE;

INSERT INTO `route` VALUES
('carpool-1','Dell Office',1),
('carpool-1','diamond district',2),
('carpool-1','Domlur Flyover',3),
('carpool-1','Manipal Hospital',4),
('carpool-1','Sony Centre',5),
('carpool-2','Barbeque Nation',1),
('carpool-2','Command Hospital',2),
('carpool-2','Dell Office',3),
('carpool-2','Sony Centre',4),
('carpool-2','Trinity Church',5);

UNLOCK TABLES;
