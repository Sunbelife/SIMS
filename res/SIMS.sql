-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: localhost    Database: SIMS
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `People`
--

DROP TABLE IF EXISTS `People`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `People` (
  `ID` int(11) NOT NULL,
  `NAME` char(255) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  `Phone` char(13) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `PicPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `People`
--

LOCK TABLES `People` WRITE;
/*!40000 ALTER TABLE `People` DISABLE KEYS */;
INSERT INTO `People` VALUES (1,'孙甜甜',1,'18600536420',21,'/Users/sun/Documents/StuSystem/src/Pics/1.png'),(3,'梅德韦杰夫',1,'1432980',42,'/Users/sun/Documents/StuSystem/src/Pics/3.png'),(4,'史蒂夫乔布斯',1,'1132840',45,'/Users/sun/Desktop/123423.png'),(5,'鲍尔默',1,'19834310',94,'/Users/sun/Documents/StuSystem/src/Pics/5.png'),(6,'比尔盖茨',1,'4539110',56,'/Users/sun/Documents/StuSystem/src/Pics/6.png'),(7,'盛田昭夫',0,'38903110',32,'/Users/sun/Desktop/123423.png'),(9,'特朗普',0,'48904110',71,'/Users/sun/Documents/StuSystem/src/Pics/9.png'),(10,'希拉里',0,'54839110',43,'/Users/sun/Documents/StuSystem/src/Pics/18.png'),(11,'马英九',1,'4235110',34,'/Users/sun/Desktop/123423.png'),(13,'胡锦涛',1,'2342110',54,'/Users/sun/Documents/StuSystem/src/Pics/2.png'),(14,'冯绍峰',0,'543110',74,'/Users/sun/Documents/StuSystem/src/Pics/18.png'),(15,'杨幂',0,'523110',34,'/Users/sun/Documents/StuSystem/src/Pics/15.png'),(16,'陈奕迅',0,'432110',37,'/Users/sun/Documents/StuSystem/src/Pics/16.png'),(17,'江泽明',0,'7876110',64,'/Users/sun/Documents/StuSystem/src/Pics/17.png'),(18,'梅德韦杰夫',0,'6575110',73,'/Users/sun/Documents/StuSystem/src/Pics/18.png'),(19,'山海经',0,'456110',75,NULL),(20,'奥巴马',0,'7684110',70,'/Users/sun/Documents/StuSystem/src/Pics/20.png'),(21,'老布什',1,'445110',43,'/Users/sun/Documents/StuSystem/src/Pics/21.png'),(22,'小布什',1,'845110',54,'/Users/sun/Documents/StuSystem/src/Pics/22.png'),(23,'戈尔巴乔夫',0,'945110',74,'/Users/sun/Documents/StuSystem/src/Pics/23.png'),(24,'卡巴斯基',1,'24110',34,'/Users/sun/Documents/StuSystem/src/Pics/24.png'),(25,'秃了光机',0,'543110',35,NULL),(26,'巴普洛夫',0,'765110',36,'/Users/sun/Documents/StuSystem/src/Pics/26.png'),(27,'弗洛伊德',0,'654110',39,'/Users/sun/Documents/StuSystem/src/Pics/27.png');
/*!40000 ALTER TABLE `People` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pic`
--

DROP TABLE IF EXISTS `Pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pic` (
  `ID` int(100) NOT NULL,
  `PIC` longblob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pic`
--

LOCK TABLES `Pic` WRITE;
/*!40000 ALTER TABLE `Pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userandpassword`
--

DROP TABLE IF EXISTS `userandpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userandpassword` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `TYPE` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userandpassword`
--

LOCK TABLES `userandpassword` WRITE;
/*!40000 ALTER TABLE `userandpassword` DISABLE KEYS */;
INSERT INTO `userandpassword` VALUES ('Sunbelife','haha0308',0),('Teacher','Tea',1),('Hey','haha0308haha0308',0),('Test','12345678',1),('Test','Test123456',1),('史蒂夫','sh sh sh ',0),('Sunbele','asd asd ',0),('撒旦','sd,sd,',0),('sdf','sdf,sdf,',0);
/*!40000 ALTER TABLE `userandpassword` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-18 19:06:55
