-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: chat
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat_member`
--

DROP TABLE IF EXISTS `chat_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_member` (
  `chat_room_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_uuid` varchar(255) DEFAULT NULL,
  `particpation_type` enum('BANNED','JOINED','LEFT') DEFAULT NULL,
  `participation_type` enum('BANNED','JOINED','LEFT') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp3ov6ys5mw1i7e9va4nniwa5q` (`chat_room_id`),
  CONSTRAINT `FKp3ov6ys5mw1i7e9va4nniwa5q` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_member`
--

LOCK TABLES `chat_member` WRITE;
/*!40000 ALTER TABLE `chat_member` DISABLE KEYS */;
INSERT INTO `chat_member` VALUES (22,'2024-06-27 08:35:45.425533',43,'2024-06-27 08:35:45.425533','9b97c783-7646-4d8a-8a98-7bb1ef55e192',NULL,'JOINED'),(22,'2024-06-27 08:35:45.443556',44,'2024-06-27 08:35:45.443556','zab234',NULL,'JOINED'),(23,'2024-07-01 06:48:57.227855',45,'2024-07-01 06:48:57.227855','44a534fe-fe5f-4198-9f12-d1186117bfd7',NULL,'JOINED'),(23,'2024-07-01 06:48:57.245618',46,'2024-07-01 06:48:57.245618','8c93bbeb-a6c5-4188-8340-626e0dffa7e0',NULL,'JOINED'),(24,'2024-07-02 06:54:27.657875',47,'2024-07-02 06:54:27.657875','900f15ca-3bf8-46a2-9354-29f77a9d1fc0',NULL,'JOINED'),(24,'2024-07-02 06:54:27.664205',48,'2024-07-02 06:54:27.664205','678456',NULL,'JOINED');
/*!40000 ALTER TABLE `chat_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:40:13
