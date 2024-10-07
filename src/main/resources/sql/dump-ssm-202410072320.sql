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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `competition_score` decimal(5,2) DEFAULT NULL COMMENT '比赛成绩',
  `competition_rank` int DEFAULT NULL COMMENT '比赛排名',
  `score_status` enum('VALID','INVALID') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VALID' COMMENT '成绩状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `permission_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '权限URL',
  `method` enum('GET','POST','PUT','DELETE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限方法',
  `parent_id` int DEFAULT '0' COMMENT '父级权限ID',
  `permission_type` enum('MENU','BUTTON','API') DEFAULT 'MENU' COMMENT '权限类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_key` (`permission_key`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'查看用户列表','user:list','/users','GET',0,'API','用户查看用户列表','2024-09-24 14:42:15','2024-09-24 14:42:15',0),(2,'删除用户','user:delete','','DELETE',0,'API','删除用户','2024-09-25 03:45:02','2024-09-25 03:45:02',0),(3,'新增比赛信息','competition:add','/competition/add','POST',0,'API','新增比赛信息','2024-10-07 03:42:35','2024-10-07 03:42:35',0),(4,'删除比赛信息','competition:delete','','DELETE',0,'API','删除比赛信息','2024-10-07 03:43:46','2024-10-07 03:43:46',0),(5,'修改比赛信息','competition:edit','/competition/edit','PUT',0,'API','修改比赛信息','2024-10-07 03:46:40','2024-10-07 03:46:40',0),(6,'查询比赛信息','competition:get','','GET',0,'API','查询比赛信息','2024-10-07 03:53:32','2024-10-07 03:53:32',0),(7,'查询比赛信息列表','competition:list','/competitions','POST',0,'API','查询比赛信息列表','2024-10-07 04:02:20','2024-10-07 04:02:20',0),(8,'新增报名信息','register:add','/register/add','POST',0,'API','新增报名信息','2024-10-07 04:04:24','2024-10-07 04:04:24',0),(9,'删除报名信息','register:delete','','DELETE',0,'API','删除报名信息','2024-10-07 04:05:12','2024-10-07 04:05:12',0),(10,'修改报名信息','register:edit','/register/edit','PUT',0,'API','修改报名信息','2024-10-07 04:06:26','2024-10-07 04:06:26',0),(11,'查询报名信息','register:get','','GET',0,'API','查询报名信息','2024-10-07 04:19:32','2024-10-07 04:19:32',0),(12,'查询报名信息列表','register:list','/registers','POST',0,'API','查询报名信息列表','2024-10-07 04:20:29','2024-10-07 04:20:29',0),(13,'新增比赛成绩','score:add','/score/add','GET',0,'API','新增比赛成绩','2024-10-07 04:21:53','2024-10-07 04:21:53',0),(14,'删除比赛成绩','score:delete','','DELETE',0,'API','删除比赛成绩','2024-10-07 04:22:41','2024-10-07 04:22:41',0),(15,'修改比赛成绩','score:edit','/score/edit','PUT',0,'API','修改比赛成绩','2024-10-07 04:24:27','2024-10-07 04:24:27',0),(16,'查询比赛成绩','score:get','','GET',0,'API','查询比赛成绩','2024-10-07 04:25:12','2024-10-07 04:25:12',0),(17,'查询比赛成绩列表','score:list','/scores','POST',0,'API','查询比赛成绩列表','2024-10-07 04:25:52','2024-10-07 04:25:52',0),(18,'新增公告信息','announcement:add','/announcement/add','POST',0,'API','新增公告信息','2024-10-07 04:27:52','2024-10-07 04:27:52',0),(19,'删除公告信息','announcement:delete','','DELETE',0,'API','删除公告信息','2024-10-07 04:28:21','2024-10-07 04:28:21',0),(20,'修改公告信息','announcement:edit','/announcement/edit','PUT',0,'API','修改公告信息','2024-10-07 04:28:53','2024-10-07 04:28:53',0),(21,'查询公告信息','announcement:get','','GET',0,'API','查询公告信息','2024-10-07 04:29:24','2024-10-07 04:29:24',0),(22,'查询公告信息列表','announcement:list','/announcements','POST',0,'API','查询公告信息列表','2024-10-07 04:29:58','2024-10-07 04:29:58',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'ADMIN','系统管理员','2024-09-22 13:25:47','2024-09-22 13:25:47',0),(2,'USER','普通用户','2024-09-22 13:26:01','2024-09-22 13:26:01',0),(10,'UMPIRE','裁判员','2024-10-07 15:12:30','2024-10-07 15:17:41',0);
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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (1,1,1,'2024-09-25 03:27:43','2024-09-25 03:27:43',0),(2,1,2,'2024-09-25 03:47:26','2024-09-25 03:47:26',0);
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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_unique` (`username`),
  UNIQUE KEY `t_user_unique_1` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','$2a$10$E38w6uxWsgZY0EpEDUJr5ug3FpErltBUBK7KDDEOip35vzxt2g4.u',28,'男','admin','15920520419','ACTIVE','2024-09-20 04:59:24','2024-10-04 02:25:21',0),(16,'test','$2a$10$gI/jxAQsk8F/ilf0WpBb5./OttnwfYHgqa0dOKYDzEEzlM1bCVFoi',18,'女','测试账号','13711337782','ACTIVE','2024-09-22 10:38:39','2024-10-03 04:26:52',0),(17,'ceshi','$2a$10$uvkelGfuxKz86I8NFkfMEuixQCctH8UahedXP6Qky5CMGBmuuMDy2',22,'男','测试用户','13879124539','ACTIVE','2024-09-28 14:34:46','2024-09-28 14:34:46',0),(18,'ceshi1','$2a$10$5ctbAboxJiaPUYnGxEujve/GtdOi3UjwNCTrjJZHGl0mrAedJX8j6',18,'男','测试111','13711417777','PENDING','2024-09-30 14:43:59','2024-09-30 14:43:59',0);
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
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,1,'2024-09-22 13:31:19','2024-09-22 13:31:19',0),(2,16,2,'2024-09-22 13:31:28','2024-09-22 13:31:28',0),(3,16,1,'2024-09-23 03:55:45','2024-09-23 03:55:45',1);
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

-- Dump completed on 2024-10-07 23:20:06
