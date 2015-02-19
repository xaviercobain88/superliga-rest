CREATE DATABASE  IF NOT EXISTS `rest_services_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rest_services_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: rest_services_db
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `distributed_service_token`
--

DROP TABLE IF EXISTS `distributed_service_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributed_service_token` (
  `token` varchar(255) NOT NULL,
  `expire_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`token`),
  KEY `FK_qoxldva9n3ifgxuh0sr7oli12` (`user_id`),
  CONSTRAINT `FK_qoxldva9n3ifgxuh0sr7oli12` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributed_service_token`
--

LOCK TABLES `distributed_service_token` WRITE;
/*!40000 ALTER TABLE `distributed_service_token` DISABLE KEYS */;
INSERT INTO `distributed_service_token` VALUES ('MTIwMTUtMDItMDcgMDg6MDI6MDQuNjg2','2015-02-07 08:02:05',1),('MTIwMTUtMDItMDEgMDM6NDE6MTguNDMz','2015-02-01 03:41:18',1),('MTIwMTUtMDItMDggMjM6MTI6MTEuMDk4','2015-02-08 23:12:11',1),('MTIwMTUtMDItMDkgMDA6Mjc6MDcuMDQ=','2015-02-09 00:27:07',1),('MTIwMTUtMDItMDkgMDA6MjY6MzkuOTcx','2015-02-09 00:26:40',1),('MTIwMTUtMDItMDkgMDA6NDc6MTMuNjAx','2015-02-09 00:47:14',1),('MTIwMTUtMDItMDkgMDA6NDY6MjUuMTc1','2015-02-09 00:46:25',1),('MTIwMTUtMDItMDkgMDI6MjA6NDguMDIx','2015-02-09 02:20:48',1),('MTIwMTUtMDItMDkgMDI6MjE6MzAuNzg2','2015-02-09 02:21:31',1),('MTIwMTUtMDItMDkgMDI6MTk6NTkuMzM2','2015-02-09 02:19:59',1),('MTIwMTUtMDItMDkgMDI6Mzg6NDcuMDU1','2015-02-09 02:38:47',1),('MTIwMTUtMDItMDkgMDM6Mzk6MTEuMjAx','2015-02-09 03:39:11',1),('MTIwMTUtMDItMDkgMDM6NTM6MDkuMzQ5','2015-02-09 03:53:09',1),('MTIwMTUtMDItMDkgMDQ6MDM6MzcuMTI0','2015-02-09 04:03:37',1),('MTIwMTUtMDItMDkgMDQ6MzA6MDAuMjE3','2015-02-09 04:30:00',1),('MTIwMTUtMDItMDMgMTE6NTc6MDkuODcy','2015-02-03 11:57:10',1),('MTIwMTUtMDItMDMgMTg6MTE6MDMuNjA5','2015-02-03 18:11:04',1),('MTIwMTUtMDItMDMgMTg6MTI6MDAuNzAy','2015-02-03 18:12:01',1),('MTIwMTUtMDItMDQgMDc6MjE6MDAuNDkz','2015-02-04 07:21:00',1),('MTIwMTUtMDItMDQgMDY6NDg6NDQuNjcz','2015-02-04 06:48:45',1),('MTIwMTUtMDItMDQgMTE6Mjc6MzkuMjcy','2015-02-04 11:27:39',1),('MTIwMTUtMDItMDYgMDk6MjY6MTIuMTk1','2015-02-06 09:26:12',1),('MTIwMTUtMDItMTkgMDc6MzA6MjQuNTY0','2015-02-19 07:30:25',1),('MTIwMTUtMDItMTkgMDc6MzE6NDYuMw==','2015-02-19 07:31:46',1);
/*!40000 ALTER TABLE `distributed_service_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (63);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `input_output_stage`
--

DROP TABLE IF EXISTS `input_output_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `input_output_stage` (
  `input_source_stage_id` bigint(20) NOT NULL,
  `output_target_stage_id` bigint(20) NOT NULL,
  KEY `FK_19qvkyy5r0lhu3j72my7ym9lv` (`output_target_stage_id`),
  KEY `FK_93r46cjo7ncx079eas3kvgbwo` (`input_source_stage_id`),
  CONSTRAINT `FK_19qvkyy5r0lhu3j72my7ym9lv` FOREIGN KEY (`output_target_stage_id`) REFERENCES `stage` (`id`),
  CONSTRAINT `FK_93r46cjo7ncx079eas3kvgbwo` FOREIGN KEY (`input_source_stage_id`) REFERENCES `stage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_output_stage`
--

LOCK TABLES `input_output_stage` WRITE;
/*!40000 ALTER TABLE `input_output_stage` DISABLE KEYS */;
INSERT INTO `input_output_stage` VALUES (2,13),(7,13),(9,13),(11,13),(13,15);
/*!40000 ALTER TABLE `input_output_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `id` bigint(20) NOT NULL,
  `polymorphic_resource_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `recipient_user_id` bigint(20) DEFAULT NULL,
  `sender_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ig4fery0avtatsayh4mtvc5rx` (`recipient_user_id`),
  KEY `FK_k2jkj8rwerbdkv459snccv0p7` (`sender_user_id`),
  CONSTRAINT `FK_ig4fery0avtatsayh4mtvc5rx` FOREIGN KEY (`recipient_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_k2jkj8rwerbdkv459snccv0p7` FOREIGN KEY (`sender_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (18,1,'REFUSED','TOURNAMENT_TEAM',17,1),(20,1,'REFUSED','TOURNAMENT_TEAM',1,17),(22,1,'ACCEPTED','TOURNAMENT_TEAM',1,21),(24,1,'PENDING','TOURNAMENT_TEAM',23,1);
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5q11flfd61t4n9eepixi8ltup` (`team_id`),
  KEY `FK_fpxwfe7n29rwsbyu5p1wl2mq1` (`user_id`),
  CONSTRAINT `FK_5q11flfd61t4n9eepixi8ltup` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_fpxwfe7n29rwsbyu5p1wl2mq1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (28,'ACTIVE',27,1);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage` (
  `id` bigint(20) NOT NULL,
  `groups_number` int(11) DEFAULT NULL,
  `input_teams` int(11) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `output_teams` int(11) DEFAULT NULL,
  `next_stage_id` bigint(20) DEFAULT NULL,
  `tournament_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jmq6wkqsalgc9o2s9mp7vhect` (`next_stage_id`),
  KEY `FK_fq24rgmhm92kvtbbk57mri9qh` (`tournament_id`),
  CONSTRAINT `FK_fq24rgmhm92kvtbbk57mri9qh` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_jmq6wkqsalgc9o2s9mp7vhect` FOREIGN KEY (`next_stage_id`) REFERENCES `stage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage`
--

LOCK TABLES `stage` WRITE;
/*!40000 ALTER TABLE `stage` DISABLE KEYS */;
INSERT INTO `stage` VALUES (2,4,16,'GROUPS',4,7,1),(7,1,16,'ROUND_ROBIN',4,9,1),(9,1,16,'ROUND_ROBIN',4,11,1),(11,1,16,'ROUND_ROBIN',4,13,1),(13,1,16,'ROUND_ROBIN',8,15,1),(15,1,8,'ROUND_ROBIN',1,NULL,1);
/*!40000 ALTER TABLE `stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stage_group`
--

DROP TABLE IF EXISTS `stage_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage_group` (
  `id` bigint(20) NOT NULL,
  `input_teams` int(11) DEFAULT NULL,
  `group_mode` varchar(255) DEFAULT NULL,
  `group_order` int(11) DEFAULT NULL,
  `ouput_teams` int(11) DEFAULT NULL,
  `stage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ow8pg4xn5jjsyuq7su2k6ho0o` (`stage_id`),
  CONSTRAINT `FK_ow8pg4xn5jjsyuq7su2k6ho0o` FOREIGN KEY (`stage_id`) REFERENCES `stage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_group`
--

LOCK TABLES `stage_group` WRITE;
/*!40000 ALTER TABLE `stage_group` DISABLE KEYS */;
INSERT INTO `stage_group` VALUES (3,4,'ROUND_ROBIN',0,NULL,2),(4,4,'ROUND_ROBIN',1,NULL,2),(5,4,'ROUND_ROBIN',2,NULL,2),(6,4,'ROUND_ROBIN',3,NULL,2),(8,16,'ROUND_ROBIN',0,NULL,7),(10,16,'ROUND_ROBIN',0,NULL,9),(12,16,'ROUND_ROBIN',0,NULL,11),(14,16,'ROUND_ROBIN',0,NULL,13),(16,8,'ROUND_ROBIN',0,NULL,15);
/*!40000 ALTER TABLE `stage_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (25,NULL),(26,NULL),(27,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `id` bigint(20) NOT NULL,
  `discipline` varchar(255) DEFAULT NULL,
  `input_teams` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (1,'FOOTBALL',16,'Este es un torneo'),(29,'FOOTBALL',NULL,'Este es un torneo'),(30,'FOOTBALL',NULL,'Nuevo_Campeonato'),(31,'FOOTBALL',NULL,'Nuevo_Campeonato'),(32,'FOOTBALL',NULL,'Nuevo_Campeonato'),(33,'FOOTBALL',NULL,'Nuevo_Campeonato'),(34,'FOOTBALL',NULL,'Nuevo_Campeonato'),(35,'FOOTBALL',NULL,'Nuevo_Campeonato'),(36,'FOOTBALL',NULL,'Nuevo_Campeonato'),(37,'FOOTBALL',NULL,'Nuevo_Campeonato'),(38,'FOOTBALL',NULL,'Nuevo_Campeonato'),(39,'FOOTBALL',NULL,'Nuevo_Campeonato'),(40,'FOOTBALL',NULL,'Nuevo_Campeonato'),(41,'FOOTBALL',NULL,'Nuevo_Campeonato'),(42,'FOOTBALL',NULL,'Nuevo_Campeonato'),(43,'FOOTBALL',NULL,'Nuevo_Campeonato'),(44,'FOOTBALL',NULL,'Nuevo_Campeonato'),(45,'FOOTBALL',NULL,'Nuevo_Campeonato'),(46,'FOOTBALL',NULL,'Nuevo_Campeonato'),(47,'FOOTBALL',NULL,'Nuevo_Campeonato'),(48,'FOOTBALL',NULL,'Nuevo_Campeonato'),(49,'FOOTBALL',NULL,'Nuevo_Campeonato'),(50,'FOOTBALL',NULL,'Nuevo_Campeonato'),(51,'FOOTBALL',NULL,'Nuevo_Campeonato'),(52,'FOOTBALL',NULL,'Nuevo_Campeonato'),(53,'FOOTBALL',NULL,'Nuevo_Campeonato'),(54,'FOOTBALL',NULL,'Nuevo_Campeonato'),(55,'FOOTBALL',NULL,'Nuevo_Campeonato'),(56,'FOOTBALL',NULL,'Nuevo_Campeonato'),(57,'FOOTBALL',NULL,'Nuevo_Campeonato'),(58,'FOOTBALL',NULL,'Nuevo_Campeonato'),(59,'FOOTBALL',NULL,'Nuevo_Campeonato'),(60,'FOOTBALL',NULL,'Nuevo_Campeonato'),(61,'FOOTBALL',NULL,'Nuevo_Campeonato'),(62,'FOOTBALL',NULL,'Nuevo_Campeonato');
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_team`
--

DROP TABLE IF EXISTS `tournament_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_team` (
  `tournament_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  KEY `FK_f396nfhc3wga5ntd35mvsywme` (`team_id`),
  KEY `FK_92fme6hwhli7l93hmhn6n737` (`tournament_id`),
  CONSTRAINT `FK_92fme6hwhli7l93hmhn6n737` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_f396nfhc3wga5ntd35mvsywme` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_team`
--

LOCK TABLES `tournament_team` WRITE;
/*!40000 ALTER TABLE `tournament_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `tournament_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Xavier','Ramirez','153a2510941024e195bdc2f9a9a0e113','ACTIVE','','xaviercobain88'),(17,NULL,NULL,NULL,'INVITATION',NULL,'abcalcuadrado@gmail.com'),(19,NULL,NULL,NULL,'INVITATION',NULL,'juanita@hotmail.com'),(21,NULL,NULL,NULL,'INVITATION',NULL,'verito_0321@hotmail.com'),(23,NULL,NULL,NULL,'INVITATION',NULL,'xaviercobain88@hotmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_secure_manageable`
--

DROP TABLE IF EXISTS `user_secure_manageable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_secure_manageable` (
  `user_id` bigint(20) NOT NULL,
  `secure_manageable_type` varchar(255) DEFAULT NULL,
  `secure_manageable_id` bigint(20) NOT NULL,
  KEY `FK_qpof740l2kdfjfp4buur2v6fb` (`user_id`),
  CONSTRAINT `FK_qpof740l2kdfjfp4buur2v6fb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_secure_manageable`
--

LOCK TABLES `user_secure_manageable` WRITE;
/*!40000 ALTER TABLE `user_secure_manageable` DISABLE KEYS */;
INSERT INTO `user_secure_manageable` VALUES (17,'TEAM',25),(19,'TEAM',26),(1,'TOURNAMENT',1),(1,'PLAYER',28),(1,'TOURNAMENT',29),(1,'PLAYER',28),(1,'TOURNAMENT',30),(1,'PLAYER',28),(1,'TOURNAMENT',31),(1,'PLAYER',28),(1,'TOURNAMENT',32),(1,'PLAYER',28),(1,'TOURNAMENT',33),(1,'PLAYER',28),(1,'TOURNAMENT',34),(1,'PLAYER',28),(1,'TOURNAMENT',35),(1,'PLAYER',28),(1,'TOURNAMENT',36),(1,'PLAYER',28),(1,'TOURNAMENT',37),(1,'PLAYER',28),(1,'TOURNAMENT',38),(1,'PLAYER',28),(1,'TOURNAMENT',39),(1,'PLAYER',28),(1,'TOURNAMENT',40),(1,'PLAYER',28),(1,'TOURNAMENT',41),(1,'PLAYER',28),(1,'TOURNAMENT',42),(1,'PLAYER',28),(1,'TOURNAMENT',43),(1,'PLAYER',28),(1,'TOURNAMENT',44),(1,'PLAYER',28),(1,'TOURNAMENT',45),(1,'PLAYER',28),(1,'TOURNAMENT',46),(1,'PLAYER',28),(1,'TOURNAMENT',47),(1,'PLAYER',28),(1,'TOURNAMENT',48),(1,'PLAYER',28),(1,'TOURNAMENT',49),(1,'PLAYER',28),(1,'TOURNAMENT',50),(1,'PLAYER',28),(1,'TOURNAMENT',51),(1,'PLAYER',28),(1,'TOURNAMENT',52),(1,'PLAYER',28),(1,'TOURNAMENT',53),(1,'PLAYER',28),(1,'TOURNAMENT',54),(1,'PLAYER',28),(1,'TOURNAMENT',55),(1,'PLAYER',28),(1,'TOURNAMENT',56),(1,'PLAYER',28),(1,'TOURNAMENT',57),(1,'PLAYER',28),(1,'TOURNAMENT',58),(1,'PLAYER',28),(1,'TOURNAMENT',59),(1,'PLAYER',28),(1,'TOURNAMENT',60),(1,'PLAYER',28),(1,'TOURNAMENT',61),(1,'PLAYER',28),(1,NULL,1),(1,'TOURNAMENT',62);
/*!40000 ALTER TABLE `user_secure_manageable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-18 22:12:32