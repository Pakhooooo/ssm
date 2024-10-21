-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.1.99    Database: ssm
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `t_announcement`
--

DROP TABLE IF EXISTS `t_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_announcement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `announcement_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `announcement_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `publisher_id` int NOT NULL COMMENT '发布人ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_announcement`
--

LOCK TABLES `t_announcement` WRITE;
/*!40000 ALTER TABLE `t_announcement` DISABLE KEYS */;
INSERT INTO `t_announcement` VALUES (1,'测试公告标题1','测试公告内容2',1,'2024-09-28 14:36:01','2024-09-28 15:02:34',0);
/*!40000 ALTER TABLE `t_announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_competition`
--

DROP TABLE IF EXISTS `t_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_competition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `competition_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '比赛名称',
  `competition_date` date NOT NULL COMMENT '比赛日期',
  `competition_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '比赛地点',
  `competition_person_number` bigint NOT NULL COMMENT '参赛人数',
  `competition_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '比赛描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='比赛信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_competition`
--

LOCK TABLES `t_competition` WRITE;
/*!40000 ALTER TABLE `t_competition` DISABLE KEYS */;
INSERT INTO `t_competition` VALUES (1,'test name','2024-09-29','test location',49,'test description','2024-09-28 15:11:15','2024-10-03 08:49:50',0),(2,'12','2024-10-02','1123',22,NULL,'2024-10-02 14:26:26','2024-10-03 08:53:35',0),(3,'1213','2024-10-03','111',22,'11123','2024-10-02 14:27:46','2024-10-03 08:53:39',0),(4,'tes','2024-10-05','1',2,'1','2024-10-03 08:16:51','2024-10-03 08:51:56',0),(5,'测试','2024-10-18','1',2,'1','2024-10-03 08:22:11','2024-10-03 08:22:11',0),(6,'test1','2024-10-10','1',1,'1','2024-10-03 08:54:10','2024-10-03 08:54:10',0);
/*!40000 ALTER TABLE `t_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_competition_register`
--

DROP TABLE IF EXISTS `t_competition_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_competition_register` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `competition_id` int NOT NULL COMMENT '赛事ID',
  `audit_status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'PENDING' COMMENT '审核状态',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='比赛报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_competition_register`
--

LOCK TABLES `t_competition_register` WRITE;
/*!40000 ALTER TABLE `t_competition_register` DISABLE KEYS */;
INSERT INTO `t_competition_register` VALUES (1,17,5,'PENDING',NULL,'2024-09-29 04:37:06','2024-10-04 05:37:58',0),(2,16,6,'PENDING',NULL,'2024-10-04 08:13:36','2024-10-04 08:13:50',1),(3,1,1,'PENDING',NULL,'2024-10-04 08:13:47','2024-10-04 08:14:02',0);
/*!40000 ALTER TABLE `t_competition_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_competition_score`
--

DROP TABLE IF EXISTS `t_competition_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_competition_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `competition_id` int NOT NULL COMMENT '赛事ID',
  `competition_score` decimal(5,2) NOT NULL COMMENT '比赛成绩',
  `competition_rank` int NOT NULL COMMENT '比赛排名',
  `score_status` enum('VALID','INVALID') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VALID' COMMENT '成绩状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='比赛成绩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_competition_score`
--

LOCK TABLES `t_competition_score` WRITE;
/*!40000 ALTER TABLE `t_competition_score` DISABLE KEYS */;
INSERT INTO `t_competition_score` VALUES (4,16,1,50.55,10,'VALID','2024-09-29 07:55:22','2024-09-30 13:45:21',1),(5,16,1,50.55,10,'VALID','2024-09-29 07:57:06','2024-09-30 13:45:21',1),(6,16,1,50.55,10,'VALID','2024-09-29 07:58:14','2024-09-30 13:45:21',1),(7,16,1,50.55,10,'VALID','2024-09-29 07:59:54','2024-09-30 13:45:21',1),(8,16,1,88.88,2,'VALID','2024-09-29 08:03:42','2024-09-30 13:45:21',1);
/*!40000 ALTER TABLE `t_competition_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `permission_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限KEY',
  `permission_type` enum('API','MENU') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'API' COMMENT '权限类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_permission_name_unique` (`permission_name`,`del_status`),
  UNIQUE KEY `t_permission_key_unique` (`permission_key`,`del_status`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'查看用户列表','user:list','API','2024-09-24 14:42:15','2024-10-11 07:20:40',0),(2,'删除用户','user:delete','API','2024-09-25 03:45:02','2024-09-25 03:45:02',0),(3,'新增比赛信息','competition:add','API','2024-10-07 03:42:35','2024-10-07 03:42:35',0),(4,'删除比赛信息','competition:delete','API','2024-10-07 03:43:46','2024-10-07 03:43:46',0),(5,'修改比赛信息','competition:update','API','2024-10-07 03:46:40','2024-10-08 13:55:08',0),(6,'查询比赛信息','competition:get','API','2024-10-07 03:53:32','2024-10-07 03:53:32',0),(7,'查询比赛信息列表','competition:list','API','2024-10-07 04:02:20','2024-10-07 04:02:20',0),(8,'新增报名信息','register:add','API','2024-10-07 04:04:24','2024-10-07 04:04:24',0),(9,'删除报名信息','register:delete','API','2024-10-07 04:05:12','2024-10-07 04:05:12',0),(10,'修改报名信息','register:update','API','2024-10-07 04:06:26','2024-10-08 13:55:08',0),(11,'查询报名信息','register:get','API','2024-10-07 04:19:32','2024-10-07 04:19:32',0),(12,'查询报名信息列表','register:list','API','2024-10-07 04:20:29','2024-10-07 04:20:29',0),(13,'新增比赛成绩','score:add','API','2024-10-07 04:21:53','2024-10-07 04:21:53',0),(14,'删除比赛成绩','score:delete','API','2024-10-07 04:22:41','2024-10-07 04:22:41',0),(15,'修改比赛成绩','score:update','API','2024-10-07 04:24:27','2024-10-08 13:55:08',0),(16,'查询比赛成绩','score:get','API','2024-10-07 04:25:12','2024-10-07 04:25:12',0),(17,'查询比赛成绩列表','score:list','API','2024-10-07 04:25:52','2024-10-07 04:25:52',0),(18,'新增公告信息','announcement:add','API','2024-10-07 04:27:52','2024-10-07 04:27:52',0),(19,'删除公告信息','announcement:delete','API','2024-10-07 04:28:21','2024-10-07 04:28:21',0),(20,'修改公告信息','announcement:update','API','2024-10-07 04:28:53','2024-10-08 13:55:08',0),(21,'查询公告信息','announcement:get','API','2024-10-07 04:29:24','2024-10-07 04:29:24',0),(22,'查询公告信息列表','announcement:list','API','2024-10-07 04:29:58','2024-10-07 04:29:58',0),(23,'系统首页','system:index','MENU','2024-10-10 03:56:52','2024-10-12 02:42:50',0),(24,'系统管理','system:management','MENU','2024-10-10 03:59:43','2024-10-12 02:42:50',0),(25,'用户管理','system:user','MENU','2024-10-10 04:00:08','2024-10-12 02:42:50',0),(26,'角色管理','system:role','MENU','2024-10-10 04:00:40','2024-10-12 02:42:50',0),(27,'权限管理','system:permission','MENU','2024-10-10 04:01:08','2024-10-12 02:42:50',0),(28,'比赛管理','competition:management','MENU','2024-10-10 04:14:00','2024-10-12 02:42:50',0),(29,'比赛信息','competition:info','MENU','2024-10-10 04:14:43','2024-10-12 02:42:50',0),(30,'比赛报名','competition:register','MENU','2024-10-10 04:15:15','2024-10-12 02:42:50',0),(31,'比赛成绩','competition:score','MENU','2024-10-10 04:15:53','2024-10-12 02:42:50',0);
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色标识',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_code_unique` (`role_code`,`del_status`),
  UNIQUE KEY `t_role_name_unique` (`role_name`,`del_status`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'ADMIN','系统管理员','2024-09-22 05:25:47','2024-10-08 04:35:10',0),(2,'USER','普通用户','2024-09-22 05:26:01','2024-09-22 05:26:01',0),(10,'UMPIRE','裁判员','2024-10-07 07:12:30','2024-10-07 07:17:41',0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (132,1,23,'2024-10-13 10:28:22','2024-10-13 10:28:29',1),(133,1,24,'2024-10-13 10:28:22','2024-10-13 10:28:29',1),(134,1,25,'2024-10-13 10:28:22','2024-10-13 10:28:29',1),(135,1,26,'2024-10-13 10:28:22','2024-10-13 10:28:29',1),(136,1,27,'2024-10-13 10:28:22','2024-10-13 10:28:29',1),(137,1,24,'2024-10-13 10:28:29','2024-10-13 10:29:20',1),(138,1,25,'2024-10-13 10:28:29','2024-10-13 10:29:20',1),(139,1,26,'2024-10-13 10:28:29','2024-10-13 10:29:20',1),(140,1,27,'2024-10-13 10:28:29','2024-10-13 10:29:20',1),(141,1,24,'2024-10-13 10:29:20','2024-10-13 10:29:44',1),(142,1,25,'2024-10-13 10:29:20','2024-10-13 10:29:44',1),(143,1,1,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(144,1,2,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(145,1,3,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(146,1,4,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(147,1,5,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(148,1,6,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(149,1,7,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(150,1,8,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(151,1,9,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(152,1,10,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(153,1,11,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(154,1,12,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(155,1,13,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(156,1,14,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(157,1,15,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(158,1,16,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(159,1,17,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(160,1,18,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(161,1,19,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(162,1,20,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(163,1,21,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(164,1,22,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(165,1,23,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(166,1,24,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(167,1,25,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(168,1,26,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(169,1,27,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(170,1,28,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(171,1,29,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(172,1,30,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(173,1,31,'2024-10-13 10:29:44','2024-10-13 10:29:44',0),(174,2,1,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(175,2,6,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(176,2,7,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(177,2,8,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(178,2,11,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(179,2,12,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(180,2,16,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(181,2,17,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(182,2,21,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(183,2,22,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(184,2,23,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(185,2,24,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(186,2,25,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(187,2,28,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(188,2,29,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(189,2,30,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(190,2,31,'2024-10-13 10:31:03','2024-10-13 10:31:03',0),(191,10,23,'2024-10-13 10:48:06','2024-10-13 10:50:46',1),(192,10,24,'2024-10-13 10:48:06','2024-10-13 10:50:46',1),(193,10,25,'2024-10-13 10:48:06','2024-10-13 10:50:46',1),(194,10,23,'2024-10-13 10:50:46','2024-10-13 10:52:17',1),(195,10,24,'2024-10-13 10:50:46','2024-10-13 10:52:17',1),(196,10,25,'2024-10-13 10:50:46','2024-10-13 10:52:17',1),(197,10,28,'2024-10-13 10:50:46','2024-10-13 10:52:17',1),(198,10,29,'2024-10-13 10:50:46','2024-10-13 10:52:17',1),(199,10,23,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(200,10,24,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(201,10,25,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(202,10,28,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(203,10,29,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(204,10,30,'2024-10-13 10:52:17','2024-10-13 10:52:25',1),(205,10,23,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(206,10,24,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(207,10,25,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(208,10,28,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(209,10,29,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(210,10,30,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(211,10,31,'2024-10-13 10:52:25','2024-10-13 10:52:48',1),(212,10,23,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(213,10,24,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(214,10,25,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(215,10,28,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(216,10,29,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(217,10,30,'2024-10-13 10:52:49','2024-10-13 10:52:54',1),(218,10,23,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(219,10,24,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(220,10,25,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(221,10,28,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(222,10,29,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(223,10,31,'2024-10-13 10:52:54','2024-10-13 10:53:32',1),(224,10,23,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(225,10,24,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(226,10,25,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(227,10,28,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(228,10,29,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(229,10,30,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(230,10,31,'2024-10-13 10:53:32','2024-10-13 10:53:57',1),(231,10,23,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(232,10,24,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(233,10,25,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(234,10,28,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(235,10,29,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(236,10,30,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(237,10,31,'2024-10-13 10:53:57','2024-10-13 10:54:13',1),(238,10,23,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(239,10,24,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(240,10,25,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(241,10,28,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(242,10,29,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(243,10,30,'2024-10-13 10:54:13','2024-10-13 10:55:05',1),(244,10,23,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(245,10,24,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(246,10,25,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(247,10,28,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(248,10,29,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(249,10,30,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(250,10,31,'2024-10-13 10:55:05','2024-10-13 10:55:16',1),(251,10,23,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(252,10,24,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(253,10,25,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(254,10,28,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(255,10,29,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(256,10,30,'2024-10-13 10:55:16','2024-10-13 10:55:27',1),(257,10,1,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(258,10,23,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(259,10,24,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(260,10,25,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(261,10,28,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(262,10,29,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(263,10,30,'2024-10-13 10:55:27','2024-10-13 10:55:55',1),(264,10,1,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(265,10,23,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(266,10,24,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(267,10,25,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(268,10,28,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(269,10,29,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(270,10,30,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(271,10,31,'2024-10-13 10:55:55','2024-10-13 10:57:02',1),(272,10,1,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(273,10,3,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(274,10,23,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(275,10,24,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(276,10,25,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(277,10,28,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(278,10,29,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(279,10,30,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(280,10,31,'2024-10-13 10:57:02','2024-10-13 10:57:07',1),(281,10,1,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(282,10,3,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(283,10,4,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(284,10,23,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(285,10,24,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(286,10,25,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(287,10,28,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(288,10,29,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(289,10,30,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(290,10,31,'2024-10-13 10:57:07','2024-10-13 10:57:56',1),(291,10,1,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(292,10,3,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(293,10,4,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(294,10,23,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(295,10,24,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(296,10,25,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(297,10,11,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(298,10,28,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(299,10,29,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(300,10,30,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(301,10,31,'2024-10-13 10:57:56','2024-10-13 10:59:56',1),(302,10,1,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(303,10,3,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(304,10,4,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(305,10,23,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(306,10,7,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(307,10,24,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(308,10,25,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(309,10,11,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(310,10,28,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(311,10,29,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(312,10,30,'2024-10-13 10:59:56','2024-10-13 10:59:56',0),(313,10,31,'2024-10-13 10:59:56','2024-10-13 10:59:56',0);
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `age` int NOT NULL COMMENT '年龄',
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `real_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户真实姓名',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `status` enum('ACTIVE','INACTIVE','PENDING') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'PENDING' COMMENT '审核状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_unique` (`username`),
  UNIQUE KEY `t_user_unique_1` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','$2a$10$E38w6uxWsgZY0EpEDUJr5ug3FpErltBUBK7KDDEOip35vzxt2g4.u',28,'男','admin','19920524384','ACTIVE','2024-09-20 04:59:24','2024-10-08 13:33:35',0),(16,'test','$2a$10$gI/jxAQsk8F/ilf0WpBb5./OttnwfYHgqa0dOKYDzEEzlM1bCVFoi',18,'女','测试账号','13711337782','ACTIVE','2024-09-22 10:38:39','2024-10-03 04:26:52',0),(17,'ceshi','$2a$10$uvkelGfuxKz86I8NFkfMEuixQCctH8UahedXP6Qky5CMGBmuuMDy2',22,'男','测试用户','13879124539','ACTIVE','2024-09-28 14:34:46','2024-09-28 14:34:46',0),(18,'ceshi1','$2a$10$5ctbAboxJiaPUYnGxEujve/GtdOi3UjwNCTrjJZHGl0mrAedJX8j6',18,'男','测试111','13711417777','PENDING','2024-09-30 14:43:59','2024-09-30 14:43:59',0);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_role_unique` (`user_id`,`role_id`,`del_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,1,'2024-09-22 05:31:19','2024-09-22 05:31:19',0),(2,16,2,'2024-09-22 05:31:28','2024-09-22 05:31:28',0),(3,16,1,'2024-09-22 19:55:45','2024-09-22 19:55:45',1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ssm'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-18 18:47:59
