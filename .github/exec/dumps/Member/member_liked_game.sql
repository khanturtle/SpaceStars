-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: member
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
-- Table structure for table `liked_game`
--

DROP TABLE IF EXISTS `liked_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked_game` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_id` bigint DEFAULT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked_game`
--

LOCK TABLES `liked_game` WRITE;
/*!40000 ALTER TABLE `liked_game` DISABLE KEYS */;
INSERT INTO `liked_game` VALUES (1,1,'abc123'),(2,2,'def456'),(3,3,'ghi789'),(4,4,'jkl012'),(5,5,'mno345'),(6,6,'pqr678'),(7,7,'stu901'),(8,1,'vwx234'),(9,2,'yza567'),(10,3,'bcd890'),(11,4,'efg123'),(12,5,'hij456'),(13,6,'klm789'),(14,7,'nop012'),(15,1,'qrs345'),(16,2,'tuv678'),(17,3,'wxy901'),(18,4,'zab234'),(19,5,'cde567'),(20,6,'fgh890'),(21,7,'ijk123'),(22,1,'lmn456'),(23,2,'opq789'),(24,3,'rst012'),(25,4,'uvw345'),(26,5,'xyz678'),(27,6,'123901'),(28,7,'456234'),(29,1,'789567'),(30,2,'012890'),(31,3,'345123'),(32,4,'678456'),(33,5,'901789'),(34,6,'234012'),(35,7,'567345'),(36,1,'890678'),(37,2,'123901'),(38,3,'456234'),(39,4,'789567'),(40,5,'012890'),(41,6,'345123'),(42,7,'678456'),(43,1,'901789'),(44,2,'234012'),(45,3,'567345'),(46,4,'890678'),(47,5,'123901'),(48,6,'456234'),(49,7,'789567'),(51,1,'c1be880b-e783-488b-a132-36b3dbe3c860'),(52,2,'c1be880b-e783-488b-a132-36b3dbe3c860'),(53,3,'c1be880b-e783-488b-a132-36b3dbe3c860'),(54,1,'ecb1242e-969a-49a3-aef0-cbacac11885a'),(55,1,'900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(56,6,'44a534fe-fe5f-4198-9f12-d1186117bfd7'),(57,6,'dec2b368-7edd-489d-b4b6-374648a787d1'),(58,4,'06848107-0c71-4e80-928c-2eca7e5d3ffd'),(59,1,'6612f181-3e02-4f23-b789-192df91a0d97'),(60,3,'a2d2a621-625e-4c2b-8537-aa61c8b942fd');
/*!40000 ALTER TABLE `liked_game` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:39:34
