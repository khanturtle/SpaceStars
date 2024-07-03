-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: rate
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
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_exp` int DEFAULT NULL,
  `level` int NOT NULL,
  `level_icon` text,
  `start_exp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (11,11,1,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level1.png',0),(12,26,2,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level2.png',12),(13,46,3,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level3.png',27),(14,73,4,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level4.png',47),(15,109,5,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level5.png',74),(16,156,6,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level6.png',110),(17,216,7,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level7.png',157),(18,291,8,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level8.png',217),(19,383,9,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level9.png',292),(20,NULL,10,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/level/Level10.png',384);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:39:52
