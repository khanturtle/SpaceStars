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
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_image` text NOT NULL,
  `game_logo_image` text NOT NULL,
  `is_class` bit(1) NOT NULL,
  `is_position` bit(1) NOT NULL,
  `is_server` bit(1) NOT NULL,
  `is_tier` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `name_kor` varchar(255) NOT NULL,
  `game_genre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsm5ypms6pv275wre0ge0uvu9o` (`game_genre_id`),
  CONSTRAINT `FKsm5ypms6pv275wre0ge0uvu9o` FOREIGN KEY (`game_genre_id`) REFERENCES `game_genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/LeagueOfLegends.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/LeagueOfLegendsLogo.png',_binary '\0',_binary '',_binary '\0',_binary '','League Of Legends','롤',1),(2,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/TemaFightTactics.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/TemaFightTacticsLogo.png',_binary '\0',_binary '\0',_binary '\0',_binary '','Teamfight Tactics','전략적 팀 전투',4),(3,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/Valorant.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/ValorantLogo.png',_binary '\0',_binary '',_binary '\0',_binary '','Valorant','발로란트',2),(4,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/MapleStory.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/MapleStoryLogo.png',_binary '',_binary '\0',_binary '',_binary '\0','Maple Story','메이플 스토리',3),(5,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/LostArk.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/LostArkLogo.png',_binary '',_binary '\0',_binary '',_binary '\0','Lost Ark','로스트아크',3),(6,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/OverWatch2.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/OverWatch2Logo.png',_binary '\0',_binary '',_binary '\0',_binary '','OverWatch2','오버워치2',2),(7,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/BattleGrounds.png','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameImage/BattleGroundsLogo2.png',_binary '\0',_binary '\0',_binary '',_binary '','BattleGround','배틀그라운드',2);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:37:08
