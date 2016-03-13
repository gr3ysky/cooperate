-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: cooperative_db
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
  `IsActive` bit(1) NOT NULL,
  `Icon` varchar(30) DEFAULT NULL,
  `Href` varchar(200) DEFAULT NULL,
  `ParentMenuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`MenuId`),
  KEY `menu_menu_MenuId_fk` (`ParentMenuId`),
  CONSTRAINT `menu_menu_MenuId_fk` FOREIGN KEY (`ParentMenuId`) REFERENCES `menu` (`MenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,2,'System Management','',NULL,NULL,NULL),(2,1,'Roles Management','',NULL,'/su/roles',1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_lang`
--

DROP TABLE IF EXISTS `menu_lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_lang` (
  `MenuId` int(11) NOT NULL,
  `LanguageId` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  KEY `menu_lang_menu_MenuId_fk` (`MenuId`),
  KEY `menu_lang_language_LanguageId_fk` (`LanguageId`),
  CONSTRAINT `menu_lang_language_LanguageId_fk` FOREIGN KEY (`LanguageId`) REFERENCES `language` (`LanguageId`),
  CONSTRAINT `menu_lang_menu_MenuId_fk` FOREIGN KEY (`MenuId`) REFERENCES `menu` (`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_lang`
--

LOCK TABLES `menu_lang` WRITE;
/*!40000 ALTER TABLE `menu_lang` DISABLE KEYS */;
INSERT INTO `menu_lang` VALUES (1,1,'System Management'),(1,2,'Sistem YÃ¶netimi'),(2,2,'Roller'),(2,1,'Roles');
/*!40000 ALTER TABLE `menu_lang` ENABLE KEYS */;
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
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  `CreateDate` datetime NOT NULL,
  `CreateUserId` int(11) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUserId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Role Index','PERM_ROLE_INDEX','','2016-02-27 18:09:53',1,NULL,NULL),(2,'System Management','PERM_SYSTEM_MNGMNT','','2016-02-27 18:12:27',1,NULL,NULL),(3,'Menu Index','PERM_MENU_INDEX','','2016-02-28 00:43:13',1,NULL,NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_lang`
--

DROP TABLE IF EXISTS `permission_lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_lang` (
  `PermissionId` int(11) NOT NULL,
  `LanguageId` int(11) NOT NULL,
  `PermissionName` varchar(200) NOT NULL,
  KEY `permission_lang_permission_PermissionId_fk` (`PermissionId`),
  KEY `permission_lang_language_LanguageId_fk` (`LanguageId`),
  CONSTRAINT `permission_lang_language_LanguageId_fk` FOREIGN KEY (`LanguageId`) REFERENCES `language` (`LanguageId`),
  CONSTRAINT `permission_lang_permission_PermissionId_fk` FOREIGN KEY (`PermissionId`) REFERENCES `permission` (`PermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_lang`
--

LOCK TABLES `permission_lang` WRITE;
/*!40000 ALTER TABLE `permission_lang` DISABLE KEYS */;
INSERT INTO `permission_lang` VALUES (1,1,'Roles'),(1,2,'Roller'),(2,1,'System Management');
/*!40000 ALTER TABLE `permission_lang` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1,'',1,'2016-02-27 18:31:11'),(2,1,2,'',1,'2016-02-27 18:31:27'),(3,1,3,'',1,'2016-02-28 00:43:47');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
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
  `CreateUserId` int(11) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `UpdateUserId` int(11) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_User_RoleId_Role_RoleId` (`RoleId`),
  KEY `user_member_MemberId_fk` (`MemberId`),
  CONSTRAINT `FK_User_RoleId_Role_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_member_MemberId_fk` FOREIGN KEY (`MemberId`) REFERENCES `member` (`MemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Taner YiÄŸit','taneryigit@live.com','¹µ2\çL\à\äBÌ™`nŽnXE\ä\Âdxhk(V,òòaò¢k\áúk  `f\ìr\ìõ>h»\nc\Ýv’ç³©°%Ÿ',1,NULL,0,'2016-02-24 01:29:53',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cooperative_db'
--

--
-- Dumping routines for database 'cooperative_db'
--
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_menu`()
BEGIN
    SELECT
      m.MenuId,
      m.Href,
      m.Icon,
      ml.Name,
      p.PermissionCode,
      m.ParentMenuId,
      l.LanguageCode
    FROM menu m
      INNER JOIN menu_lang ml ON m.MenuId = ml.MenuId
      INNER JOIN language l ON ml.LanguageId = l.LanguageId
      INNER JOIN permission p ON p.PermissionId = m.PermissionId
    WHERE m.IsActive = 1 AND p.IsActive = 1;
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_list_roles_for_combo`()
BEGIN
    SELECT
      RoleId   AS Value,
      RoleCode AS Text
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-13 16:19:52
