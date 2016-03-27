CREATE DATABASE  IF NOT EXISTS `cooperative_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cooperative_db`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: cooperative_db
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `LanguageId` int(11) NOT NULL AUTO_INCREMENT,
  `LanguageCode` varchar(2) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`LanguageId`,`LanguageCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'EN',''),(2,'TR','');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `MemberId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Picture` varchar(32) DEFAULT NULL,
  `ProfileName` varchar(100) DEFAULT NULL,
  `VolunteerForSelling` bit(1) NOT NULL,
  `VolunteerForPackaging` bit(1) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`MemberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `MenuId` int(11) NOT NULL AUTO_INCREMENT,
  `PermissionId` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `ResourceKey` varchar(30) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  `Icon` varchar(30) DEFAULT NULL,
  `Href` varchar(200) DEFAULT NULL,
  `ParentMenuId` int(11) DEFAULT NULL,
  `Order` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MenuId`),
  KEY `menu_menu_MenuId_fk` (`ParentMenuId`),
  CONSTRAINT `menu_menu_MenuId_fk` FOREIGN KEY (`ParentMenuId`) REFERENCES `menu` (`MenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,2,'System Management','menu.systemmanagement','',NULL,NULL,NULL,1),(2,4,'Users','menu.users','',NULL,'/su/users',1,1),(3,5,'Definations','menu.definations','',NULL,NULL,NULL,1),(4,7,'Product Categories','menu.productcategories','',NULL,'/su/product-categories',3,1),(5,6,'Packagings','menu.packagings','',NULL,'/su/packagings',3,2),(6,8,'Product  Features','menu.productfeatures','',NULL,'/su/product-features',3,3),(7,9,'Sale Types','menu.saletypes','',NULL,'/su/sale-types',3,4),(8,10,'Units','menu.units','',NULL,'/su/units',3,5);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packaging`
--

DROP TABLE IF EXISTS `packaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packaging` (
  `PackagingId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `ResourceKey` varchar(50) DEFAULT NULL,
  `IsActive` varchar(45) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`PackagingId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packaging`
--

LOCK TABLES `packaging` WRITE;
/*!40000 ALTER TABLE `packaging` DISABLE KEYS */;
/*!40000 ALTER TABLE `packaging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `PermissionId` int(11) NOT NULL AUTO_INCREMENT,
  `PermissionName` varchar(100) NOT NULL,
  `PermissionCode` varchar(100) NOT NULL,
  `ResourceKey` varchar(45) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Role Index','PERM_ROLE_INDEX','permission.role.index','','2016-02-27 18:09:53',1,NULL,NULL),(2,'System Management','PERM_SYSTEM_MNGMNT','permission.systemmanagement.index','','2016-02-27 18:12:27',1,NULL,NULL),(3,'Menu Index','PERM_MENU_INDEX','permission.menu.index','','2016-02-28 00:43:13',1,NULL,NULL),(4,'User Index','PERM_USER_INDEX','permission.user.index','','2016-03-14 00:00:00',1,NULL,NULL),(5,'Definations','PERM_DEFINATIONS','permission.defination.index','','2016-01-01 00:00:00',1,NULL,NULL),(6,'Packaging Index','PERM_PCKING_INDEX','permission.packaging.index','','2016-01-01 00:00:00',1,NULL,NULL),(7,'Product Category Index','PERM_PRD_CAT_INDEX','permission.productcategory.index','','2016-01-01 00:00:00',1,NULL,NULL),(8,'Product Feature Index','PERM_PRD_FEATURE_INDEX','permission.productfeateture.index','','2016-01-01 00:00:00',1,NULL,NULL),(9,'Sale Type Index','PERM_SALE_TYPE_INDEX','permission.saletype.index','','2016-01-01 00:00:00',1,NULL,NULL),(10,'Unit Index','PERM_UNIT_INDEX','permission.unit.index','','2016-01-01 00:00:00',1,NULL,NULL),(11,'User Create','PERM_USER_CREATE','permission.user.create','','2016-03-20 22:48:32',1,NULL,NULL),(12,'User Update','PERM_USER_UPDATE','permission.user.update','','2016-03-24 22:21:22',1,NULL,NULL),(13,'Product Categories Index','PERM_PROD_CAT_INDEX','permission.productcategories.index','','2016-03-27 17:35:44',1,NULL,NULL),(15,'Product Categories Create','PERM_PROD_CAT_CREATE','permission.productcategories.create','','2016-03-27 17:35:44',1,NULL,NULL),(16,'Product Categories Update','PERM_PROD_CAT_UPDATE','permission.productcategories.update','','2016-03-27 17:35:44',1,NULL,NULL),(17,'Product Categories Delete','PERM_PROD_CAT_DELETE','permission.productcategories.delete','','2016-03-27 17:35:44',1,NULL,NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `ProductCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `ResourceKey` varchar(50) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'Dasd','adsas','\0','2016-03-27 19:40:11',1,'2016-03-27 21:23:50',1),(2,'Test','Test','\0','2016-03-27 19:42:14',1,'2016-03-27 21:27:11',1),(3,'Taner','asdas','','2016-03-27 19:42:30',1,'2016-03-27 21:23:41',1),(4,'Sfd','dsfdsf','\0','2016-03-27 19:44:20',1,'2016-03-27 21:46:38',1),(5,'Fadfdf','sdfsdf','\0','2016-03-27 19:45:47',1,'2016-03-27 21:33:48',1),(6,'Asd','asd','\0','2016-03-27 21:24:01',1,NULL,1),(7,'Asd','asd','\0','2016-03-27 21:24:04',1,NULL,NULL),(8,'Asd','sdaas','','2016-03-27 21:24:07',1,'2016-03-27 22:40:23',1),(9,'Dasdasd','asdasd','','2016-03-27 21:24:10',1,'2016-03-27 21:46:41',1),(10,'Taner','sadasd','\0','2016-03-27 21:24:12',1,'2016-03-27 22:37:55',1),(11,'Asdas','asdgfsef','\0','2016-03-27 21:24:16',1,NULL,NULL);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_feature`
--

DROP TABLE IF EXISTS `product_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_feature` (
  `ProductFeatureId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `ImageUrl` varchar(200) DEFAULT NULL,
  `TitleResourceKey` varchar(50) DEFAULT NULL,
  `NameResourceKey` varchar(50) DEFAULT NULL,
  `IsActive` varchar(45) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductFeatureId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_feature`
--

LOCK TABLES `product_feature` WRITE;
/*!40000 ALTER TABLE `product_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `RoleId` tinyint(4) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(30) NOT NULL,
  `RoleCode` varchar(20) NOT NULL,
  `new_column` int(11) DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'System Admin','SYSTEM_ADMIN',NULL),(2,'Admin','ADMIN',NULL),(3,'Member','MEMBER',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `RolePermissionId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` tinyint(4) NOT NULL,
  `PermissionId` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `CreateDate` datetime NOT NULL,
  PRIMARY KEY (`RolePermissionId`),
  KEY `FK_Permission_Role_PermmisonId_Permission_PermissionId` (`PermissionId`),
  KEY `role_permission_role_RoleId_fk` (`RoleId`),
  CONSTRAINT `FK_Permission_Role_PermmisonId_Permission_PermissionId` FOREIGN KEY (`PermissionId`) REFERENCES `permission` (`PermissionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_permission_role_RoleId_fk` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1,'',1,'2016-02-27 18:31:11'),(2,1,2,'',1,'2016-02-27 18:31:27'),(3,1,3,'',1,'2016-02-28 00:43:47'),(4,1,4,'',1,'2016-03-14 00:00:00'),(5,1,5,'',1,'2016-03-14 00:00:00'),(6,1,6,'',1,'2016-03-14 00:00:00'),(7,1,7,'',1,'2016-03-14 00:00:00'),(8,1,8,'',1,'2016-03-14 00:00:00'),(9,1,9,'',1,'2016-03-14 00:00:00'),(10,1,10,'',1,'2016-03-14 00:00:00'),(11,1,11,'',1,'2016-03-20 22:51:44'),(12,1,12,'',1,'2016-03-20 22:51:44'),(13,1,13,'',1,'2016-03-20 22:51:44'),(14,1,15,'',1,'2016-03-20 22:51:44'),(15,1,16,'',1,'2016-03-20 22:51:44'),(16,1,17,'',1,'2016-03-20 22:51:44');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_type`
--

DROP TABLE IF EXISTS `sale_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_type` (
  `SaleTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `ResourceKey` varchar(50) DEFAULT NULL,
  `IsActive` varchar(45) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`SaleTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_type`
--

LOCK TABLES `sale_type` WRITE;
/*!40000 ALTER TABLE `sale_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `UnitId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Abbrevation` varchar(5) DEFAULT NULL,
  `ResourceKey` varchar(50) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`UnitId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `FullName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` binary(64) NOT NULL,
  `RoleId` tinyint(4) NOT NULL,
  `MemberId` int(11) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  `CreateUserId` int(11) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_User_RoleId_Role_RoleId` (`RoleId`),
  KEY `user_member_MemberId_fk` (`MemberId`),
  CONSTRAINT `FK_User_RoleId_Role_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_member_MemberId_fk` FOREIGN KEY (`MemberId`) REFERENCES `member` (`MemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Taner YiÄŸit','taneryigit@live.com','¹µ2\çL\à\äBÌ™`nŽnXE\ä\Âdxhk(V,òòaò¢k\áúk  `f\ìr\ìõ>h»\nc\Ýv’ç³©°%Ÿ',1,NULL,'',0,'2016-02-24 01:29:53',1,'2016-03-23 02:54:16'),(2,'Sale User','sale-user@boun.edu.tr','1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,NULL,'',1,'2016-03-20 05:01:22',1,'2016-03-25 00:25:46'),(3,'Test User','sale-user2@boun.edu.tr','¹µ2\çL\à\äBÌ™`nŽnXE\ä\Âdxhk(V,òòaò¢k\áúk  `f\ìr\ìõ>h»\nc\Ýv’ç³©°%Ÿ',3,NULL,'',0,'2016-03-21 04:01:12',1,'2016-03-25 02:25:38'),(4,'Taner ÅŸÅŸÅŸ','taner.yigit@boun.edu.tr','˜ù$‡\é\ê‡ã´¿\ÌPg³[Ã·xE“¿n7ûôy\ÑbQs”<	\ì\Ôö\Ø’¿õšfµœÀ¡\Ü\ëh{Qø®Z\Ö\æ\Þ',1,NULL,'\0',0,'2016-03-21 04:05:07',1,'2016-03-25 01:47:50'),(5,'Sale','asdasd@boun.edu.tr',' \ÙIŒZ\ÜªMµ\æ—\êº*™6\Ã~!§“\Õ6|M0HÁ\î\ÛÅ¡\Ä‘‰6õEJý»šx\\£Á\ÖCS¶?	P\Ó',2,NULL,'\0',0,'2016-03-21 04:11:36',1,'2016-03-27 14:50:30'),(6,'Sale','sale-user5@boun.edu.tr','\ÞöPN­ž\0t˜Õ»\í\áS\Æ-¯4<\à•¥±ˆOQºÔ¼¬\ì7´ee*»ž&!L\èheýï¾’·\íË $\í%®',2,NULL,'',0,'2016-03-24 20:41:28',1,'2016-03-25 02:24:41');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cooperative_db'
--
/*!50003 DROP FUNCTION IF EXISTS `f_exists_email` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `f_exists_email`(p_email VARCHAR(100)) RETURNS bit(1)
BEGIN
    DECLARE p_exists BIT DEFAULT 0;

    SELECT if(exists(SELECT 1
                     FROM user
                     WHERE Email = lower(trim(p_email))), 1, 0)
    INTO p_exists;
    RETURN p_exists;

  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_exists_email` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_exists_email`(p_email varchar(100))
BEGIN
    SELECT f_exists_email(p_email) p_exists;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_exists_role_permission` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_exists_role_permission`(p_roleId INT, p_permissionId INT)
BEGIN
    IF (EXISTS(SELECT 1
               FROM role_permission
               WHERE p_permissionId = PermissionId AND p_roleId = RoleId))
    THEN
      SELECT 1;
    ELSE
      SELECT 0;
    END IF;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_get_user_by_email` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_user_by_email`(p_email NVARCHAR(100))
BEGIN
    SELECT
      UserId,
      FullName,
      Password,
      u.RoleId,
      r.RoleCode,
      r.RoleName
    FROM user u
      INNER JOIN role r ON u.RoleId = r.RoleId
    WHERE Email = p_email;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_list_menu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_menu`()
BEGIN
    SELECT
      m.MenuId,
      m.Href,
      m.Icon,
      m.Name,
      p.PermissionCode,
      m.ResourceKey,
      m.ParentMenuId
    FROM menu m
      INNER JOIN permission p ON p.PermissionId = m.PermissionId
    WHERE m.IsActive = 1 AND p.IsActive = 1
    ORDER BY 'Order';
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_list_product_category_grid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_product_category_grid`(
      p_name           NVARCHAR(50),
      p_is_active      INT,
      p_start          INT,
      p_page_size      INT,
      p_order_by       VARCHAR(2),
      p_order_dir      VARCHAR(4),
  OUT p_filtered_total BIGINT,
  OUT p_total          BIGINT
)
BEGIN
    SET p_filtered_total = (
      SELECT count(1)
      FROM product_category pc
      WHERE (p_name = '' OR pc.Name LIKE concat('%', p_name, '%'))
            AND (p_is_active IS NULL OR pc.IsActive = p_is_active)
    );
    SET p_filtered_total = coalesce(p_filtered_total, 0);

    SET p_total = (SELECT count(1)
                   FROM product_category);
    SET p_total = coalesce(p_total, 0);

    SET @query = '
     SELECT
      pc.Name,
      pc.ResourceKey,
      pc.IsActive,
      pc.ProductCategoryId
    FROM product_category pc
    WHERE 1=1
    ';
    IF (p_name != '')
    THEN
      SET @query = concat(@query, ' and pc.Name like ''%', p_name, '%''');
    END IF;
    IF (p_is_active IS NOT NULL)
    THEN
      SET @query = concat(@query, ' and pc.IsActive =', p_is_active);
    END IF;

    IF (p_order_by = '0')
    THEN
      SET p_order_by = '1';
    END IF;

    SET @query = concat(@query, ' order by ', p_order_by, ' ', p_order_dir, ' limit ', p_start, ',', p_page_size);

    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;


  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_list_roles_for_combo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_roles_for_combo`()
BEGIN
    SELECT
      cast(RoleId AS CHAR(11)) value,
      RoleName                 text
    FROM role;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_list_role_permissions` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_role_permissions`()
BEGIN
    SELECT
      rp.PermissionId,
      rp.RoleId,
      r.RoleCode,
      p.PermissionCode
    FROM role_permission rp
      INNER JOIN role r ON rp.RoleId = r.RoleId
      INNER JOIN permission p ON rp.PermissionId = p.PermissionId
    WHERE p.IsActive AND rp.IsActive;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `p_list_users_grid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_users_grid`(
      p_full_name      NVARCHAR(50),
      p_is_active      INT,
      p_start          INT,
      p_page_size      INT,
      p_order_by       VARCHAR(2),
      p_order_dir      VARCHAR(4),
  OUT p_filtered_total BIGINT,
  OUT p_total          BIGINT
)
BEGIN

    SET p_filtered_total = (
      SELECT count(1)
      FROM user
      WHERE (p_full_name = '' OR FullName LIKE concat('%', p_full_name, '%'))
            AND (p_is_active IS NULL OR user.IsActive = p_is_active)
    );
    SET p_filtered_total = coalesce(p_filtered_total, 0);

    SET p_total = (SELECT count(1)
                   FROM user);
    SET p_total = coalesce(p_total, 0);

    SET @query = '
     SELECT
      u.FullName,
      u.Email,
      r.RoleName,
      u.IsActive,
      r.RoleId,
      u.UserId
    FROM user u
      INNER JOIN role r ON u.RoleId = r.RoleId
    WHERE 1=1
    ';
    IF (p_full_name != '')
    THEN
      SET @query = concat(@query, ' and u.FullName like ''%', p_full_name, '%''');
    END IF;
    IF (p_is_active IS NOT NULL)
    THEN
      SET @query = concat(@query, ' and u.IsActive =', p_is_active);
    END IF;

    IF (p_order_by = '0')
    THEN
      SET p_order_by = '1';
    END IF;

    SET @query = concat(@query, ' order by ', p_order_by, ' ', p_order_dir, ' limit ', p_start, ',', p_page_size);

    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-27 22:59:46
