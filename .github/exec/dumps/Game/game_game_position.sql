-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: game
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
-- Table structure for table `game_position`
--

DROP TABLE IF EXISTS `game_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_position_image` text,
  `game_position_name` varchar(20) DEFAULT NULL,
  `game_position_name_kor` varchar(20) DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb08o3jjacad8anhsu650bok7d` (`game_id`),
  CONSTRAINT `FKb08o3jjacad8anhsu650bok7d` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_position`
--

LOCK TABLES `game_position` WRITE;
/*!40000 ALTER TABLE `game_position` DISABLE KEYS */;
INSERT INTO `game_position` VALUES (1,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/lol-top.svg','Top','탑',1),(2,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/lol-jug.svg','Jungle','정글',1),(3,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/lol-mid.svg','Mid','미드',1),(4,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/lol-ad.svg','AD Carry','원딜',1),(5,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/lol-sup.svg','Supporter','서폿',1),(6,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Valorant-Duelist.svg','Duelist','타격대',3),(7,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Valorant-Initiator.svg','Initiator','척후대',3),(8,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Valorant-Sentinel.svg','Sentinel','감시자',3),(9,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Valorant-Controller.svg','Controller','전략가',3),(10,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Overwatch-Tanker.png','Tanker','탱커',6),(11,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Overwatch-Dealer.png','Dealer','딜러',6),(12,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GamePosition/Overwatch-Healer.png','Healer','힐러',6);
/*!40000 ALTER TABLE `game_position` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:37:09
