-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: supermarket
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `supermarket`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `supermarket` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `supermarket`;

--
-- Temporary view structure for view `check_info`
--

DROP TABLE IF EXISTS `check_info`;
/*!50001 DROP VIEW IF EXISTS `check_info`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `check_info` AS SELECT 
 1 AS `work_date`,
 1 AS `employee_no`,
 1 AS `clock_in_time`,
 1 AS `clock_off_time`,
 1 AS `diff_in_status`,
 1 AS `diff_off_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `check_info_son`
--

DROP TABLE IF EXISTS `check_info_son`;
/*!50001 DROP VIEW IF EXISTS `check_info_son`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `check_info_son` AS SELECT 
 1 AS `work_date`,
 1 AS `employee_no`,
 1 AS `clock_in_time`,
 1 AS `clock_off_time`,
 1 AS `diff_in_time`,
 1 AS `diff_off_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `clock_info`
--

DROP TABLE IF EXISTS `clock_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clock_info` (
  `clock_id` int NOT NULL AUTO_INCREMENT,
  `employee_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clock_in_time` datetime DEFAULT NULL,
  `clock_off_time` datetime DEFAULT NULL,
  `clock_date` date DEFAULT NULL,
  PRIMARY KEY (`clock_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clock_info`
--

LOCK TABLES `clock_info` WRITE;
/*!40000 ALTER TABLE `clock_info` DISABLE KEYS */;
INSERT INTO `clock_info` VALUES (1,'S0002','2018-08-01 11:30:52','2018-08-13 18:17:20','2018-08-01'),(2,'S0004','2018-08-02 09:46:52','2018-08-02 17:30:30','2018-08-02'),(3,'S0005','2018-08-03 08:46:52','2018-08-03 17:30:30','2018-08-03'),(4,'S0003','2018-08-04 09:13:52','2018-08-04 18:30:30','2018-08-04'),(5,'S0004','2018-08-05 09:46:52','2018-08-05 18:30:30','2018-08-05'),(6,'S0005','2018-08-06 08:00:00','2018-08-06 18:30:30','2018-08-06'),(7,'S0005','2018-08-01 08:46:52','2018-08-01 18:30:30','2018-08-01'),(8,'S0002','2018-08-02 09:46:52','2021-03-13 04:17:20','2018-08-02'),(9,'S0006','2018-08-03 08:46:52','2018-08-03 17:30:30','2018-08-03'),(10,'S0003','2018-08-01 09:46:52','2018-08-01 18:30:30','2018-08-01'),(11,'S0004','2018-08-02 09:46:52','2018-08-02 17:30:30','2018-08-02'),(12,'S0003','2018-08-03 08:46:52','2018-08-03 17:30:30','2018-08-03'),(13,'S0004','2018-08-04 09:13:52','2018-08-04 18:30:30','2018-08-04'),(14,'S0005','2018-08-05 09:46:52','2018-08-05 14:30:30','2018-08-05'),(15,'S0006','2018-08-06 09:46:52','2018-08-06 18:30:30','2018-08-06'),(16,'S0003',NULL,'2018-08-07 18:30:30','2018-08-07');
/*!40000 ALTER TABLE `clock_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role` int NOT NULL,
  `remark` int DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `role_id_FK` (`role`) USING BTREE,
  CONSTRAINT `role_id_FK` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('S0001','洪七公','admin','男','15919754435',1,1),('S0002','杨过','1234','男','15919764435',2,1),('S0003','黄蓉','1111','女','15949754435',2,1),('S0004','小龙女','1234','女','15919757435',2,1),('S0005','郭靖','admin','男','15919754485',1,1),('S0006','郭襄','1234','男','15919759485',3,1),('S0007','黄老邪','1111','男','15919754425',3,1),('S0008','段誉','1111','男','15919724485',3,1),('S0009','段誉','1111','男','15919724985',2,1),('S0010','乔峰','1234','男','15919754415',2,1),('S0011','扫地僧','admin','男','15919751415',1,1),('S0012','xxx','1234','男','12345678998',2,1),('S0013','xxx','12345678912','男','12345678912',2,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `c_number` int NOT NULL AUTO_INCREMENT,
  `c_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `c_price` decimal(10,2) DEFAULT NULL,
  `vip_price` decimal(10,2) DEFAULT NULL,
  `inventory` int DEFAULT NULL,
  PRIMARY KEY (`c_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'洗衣粉',10.50,8.40,990),(2,'洗衣机',1200.00,960.00,300),(3,'电视机',5000.00,4000.00,6),(4,'空调',3400.00,2720.00,45),(5,'电磁炉',120.00,96.00,3),(6,'牙膏',12.00,9.60,5000),(7,'洗面奶',10.00,8.00,30),(8,'香皂',4.00,3.20,800),(9,'奶粉',100.00,80.00,5000),(10,'苹果',10.00,8.00,400),(11,'大米',3.00,2.40,60000),(12,'烂白菜',12.00,9.60,5000),(13,'口香糖',11.00,8.80,1008),(14,'大白兔',10.00,8.00,4500),(15,'味精',1.00,0.80,50000);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `r_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员'),(2,'收银员'),(3,'采购员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_info`
--

DROP TABLE IF EXISTS `sell_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_info` (
  `s_c_number` int DEFAULT NULL,
  `s_quantity` int DEFAULT NULL,
  `s_time` datetime DEFAULT NULL,
  `s_e_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_vip_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  KEY `s_c_number_FK` (`s_c_number`) USING BTREE,
  KEY `s_e_number_FK` (`s_e_number`) USING BTREE,
  KEY `s_e_vip_number` (`s_vip_number`) USING BTREE,
  CONSTRAINT `s_c_number_FK` FOREIGN KEY (`s_c_number`) REFERENCES `goods` (`c_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_e_number_FK` FOREIGN KEY (`s_e_number`) REFERENCES `employee` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_e_vip_number` FOREIGN KEY (`s_vip_number`) REFERENCES `vip` (`v_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_info`
--

LOCK TABLES `sell_info` WRITE;
/*!40000 ALTER TABLE `sell_info` DISABLE KEYS */;
INSERT INTO `sell_info` VALUES (2,2,'2019-01-17 23:35:33','S0002',NULL),(2,100,'2019-04-01 14:17:24','s0002','vip201901020001'),(2,100,'2019-04-01 14:18:28','s0002','vip201901020001'),(1,10,'2021-03-04 15:36:42','S0009',NULL),(1,10,'2021-03-04 15:37:02','S0009',NULL);
/*!40000 ALTER TABLE `sell_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t1` AFTER INSERT ON `sell_info` FOR EACH ROW begin
update goods set inventory=inventory-new.s_quantity where c_number=new.s_c_number;
update VIP set v_score =v_score+(new.s_quantity*10) where v_number=new.s_vip_number;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `vip`
--

DROP TABLE IF EXISTS `vip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vip` (
  `v_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `v_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_score` int DEFAULT NULL,
  `v_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `v_date` date DEFAULT NULL,
  PRIMARY KEY (`v_number`) USING BTREE,
  UNIQUE KEY `v_phone` (`v_phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip`
--

LOCK TABLES `vip` WRITE;
/*!40000 ALTER TABLE `vip` DISABLE KEYS */;
INSERT INTO `vip` VALUES ('null','null',NULL,'null','0000-00-00'),('vip201901020001','东方不败',2025,'15844760501','2019-01-17'),('vip201901020002','令狐冲',150,'15844760502','2019-01-17'),('vip201901020003','任盈盈',1200,'15844760401','2019-01-17'),('vip201901020004','加藤惠',520,'13580491013','2021-02-27'),('vip201901020005','亚丝娜',NULL,'13229229394','2021-02-27'),('vip201901020006','星',0,'15767377519','2021-03-05'),('vip201901020007','gg',0,'12345678901','2021-03-05');
/*!40000 ALTER TABLE `vip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_date`
--

DROP TABLE IF EXISTS `work_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_date` (
  `work_date_id` int NOT NULL AUTO_INCREMENT,
  `work_date` date NOT NULL,
  PRIMARY KEY (`work_date_id`,`work_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_date`
--

LOCK TABLES `work_date` WRITE;
/*!40000 ALTER TABLE `work_date` DISABLE KEYS */;
INSERT INTO `work_date` VALUES (1,'2018-08-01'),(2,'2018-08-02'),(3,'2018-08-03'),(4,'2018-08-04'),(5,'2018-08-06'),(6,'2018-08-07'),(7,'2018-08-08'),(8,'2018-08-09'),(9,'2018-08-10'),(10,'2018-08-13'),(11,'2018-08-14'),(12,'2018-08-15'),(13,'2018-08-16'),(14,'2018-08-17'),(15,'2018-08-18'),(16,'2018-08-20'),(17,'2018-08-21'),(18,'2018-08-22'),(19,'2018-08-23'),(20,'2018-08-24'),(21,'2018-08-27'),(22,'2018-08-28'),(23,'2019-01-17'),(24,'2019-01-18'),(25,'2019-01-19'),(26,'2019-01-20'),(27,'2019-01-21'),(28,'2019-01-22'),(29,'2019-01-23'),(30,'2021-03-13'),(31,'2021-03-14'),(32,'2021-03-15'),(33,'2021-03-16'),(34,'2021-03-17'),(35,'2021-03-18');
/*!40000 ALTER TABLE `work_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `supermarket`
--

USE `supermarket`;

--
-- Final view structure for view `check_info`
--

/*!50001 DROP VIEW IF EXISTS `check_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `check_info` AS select `t`.`work_date` AS `work_date`,`t`.`employee_no` AS `employee_no`,`t`.`clock_in_time` AS `clock_in_time`,`t`.`clock_off_time` AS `clock_off_time`,(case when (`t`.`diff_in_time` < -(120)) then '旷工' when (`t`.`diff_in_time` < 0) then '迟到' when (`t`.`diff_in_time` > 0) then '正常' else '忘记打卡' end) AS `diff_in_status`,(case when (`t`.`diff_off_time` > 120) then '旷工' when (`t`.`diff_off_time` > 0) then '早退' when (`t`.`diff_off_time` < 0) then '正常' else '忘记打卡' end) AS `diff_off_time` from `check_info_son` `t` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `check_info_son`
--

/*!50001 DROP VIEW IF EXISTS `check_info_son`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `check_info_son` AS select `w`.`work_date` AS `work_date`,`t`.`employee_no` AS `employee_no`,`t`.`clock_in_time` AS `clock_in_time`,`t`.`clock_off_time` AS `clock_off_time`,timestampdiff(MINUTE,`t`.`clock_in_time`,concat(`t`.`clock_date`,' 09:00:00')) AS `diff_in_time`,timestampdiff(MINUTE,`t`.`clock_off_time`,concat(`t`.`clock_date`,' 18:00:00')) AS `diff_off_time` from (`work_date` `w` left join `clock_info` `t` on((`t`.`clock_date` = `w`.`work_date`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-22 11:05:49
