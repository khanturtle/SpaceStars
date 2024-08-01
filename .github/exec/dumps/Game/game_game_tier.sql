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
-- Table structure for table `game_tier`
--

DROP TABLE IF EXISTS `game_tier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_tier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_tier_image` text,
  `game_tier_name` varchar(20) DEFAULT NULL,
  `game_tier_name_kor` varchar(20) DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8oxowoyoq1vjlxqnjeop0j1d0` (`game_id`),
  CONSTRAINT `FK8oxowoyoq1vjlxqnjeop0j1d0` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_tier`
--

LOCK TABLES `game_tier` WRITE;
/*!40000 ALTER TABLE `game_tier` DISABLE KEYS */;
INSERT INTO `game_tier` VALUES (1,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-GrandMaster.png','Iron','아이언',1),(2,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Bronze.png','Bronze','브론즈',1),(3,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol_Silver.png','Silver','실버',1),(4,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Gold.png','Gold','골드',1),(5,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Platinum.png','Platinum','플래티넘',1),(6,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Emerald.png','Emerald','에메랄드',1),(7,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Diamond.png','Diamond','다이아',1),(8,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Master.png','Master','마스터',1),(9,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-GrandMaster.png','GrandMaster','그랜드마스터',1),(10,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/lol-Challenger.png','Challenger','챌린저',1),(11,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Iron.png','Iron','아이언',2),(12,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Bronze.png','Bronze','브론즈',2),(13,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Silver.png','Silver','실버',2),(14,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Gold.png','Gold','골드',2),(15,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Platinum.png','Platinum','플래티넘',2),(16,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Emerald.png','Emerald','에메랄드',2),(17,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Diamond.png','Diamond','다이아',2),(18,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Master.png','Master','마스터',2),(19,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-GrandMaster.png','GrandMaster','그랜드마스터',2),(20,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/tft-Challenger.png','Challenger','챌린저',2),(21,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Iron.png','Iron','아이언',3),(22,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Bronze.png','Bronze','브론즈',3),(23,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Silver.png','Silver','실버',3),(24,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Gold.png','Gold','골드',3),(25,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Platinum.png','Platinum','플래티넘',3),(26,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Diamond.png','Diamond','다이아',3),(27,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Ascendant.png','Ascendant','초월자',3),(28,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Immortal.png','Immortal','불멸자',3),(29,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/Valorant-Radiant.png','Radiant','래디언트',3),(30,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Bronze.webp','Bronze','브론즈',6),(31,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Silver.webp','Silver','실버',6),(32,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Gold.webp','Gold','골드',6),(33,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Platinum.webp','Platinum','플래티넘',6),(34,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Diamond.webp','Diamond','다이아',6),(35,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Master.webp','Master','마스터',6),(36,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-GrandMaster.webp','GrandMaster','그랜드마스터',6),(37,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Champion.webp','Champion','챔피언',6),(38,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/OverWatch-Top500.webp','Top 500','상위 500위',6),(39,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Bronze.webp','Bronze','브론즈',7),(40,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Silver.webp','Silver','실버',7),(41,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Gold.webp','Gold','골드',7),(42,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Platinum.webp','Platinum','플래티넘',7),(43,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Diamond.webp','Diamond','다이아',7),(44,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameTier/BattleGrounds-Master.webp','Master','마스터',7);
/*!40000 ALTER TABLE `game_tier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:37:10
