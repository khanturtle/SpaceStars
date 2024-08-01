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
-- Table structure for table `game_server`
--

DROP TABLE IF EXISTS `game_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_server` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_server_image` text,
  `game_server_name` varchar(50) DEFAULT NULL,
  `game_server_name_kor` varchar(50) DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK53riuhpvsnylvjqk8rmalqwx8` (`game_id`),
  CONSTRAINT `FK53riuhpvsnylvjqk8rmalqwx8` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_server`
--

LOCK TABLES `game_server` WRITE;
/*!40000 ALTER TABLE `game_server` DISABLE KEYS */;
INSERT INTO `game_server` VALUES (1,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Scania.png','Scania','스카니아',4),(2,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Bera.png','Bera','베라',4),(3,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Luna.png','Luna','루나',4),(4,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Zenice.png','Zenice','제니스',4),(5,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Kroa.png','Kroa','크로아',4),(6,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Union.png','Union','유니온',4),(7,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Elisium.png','Elisium','엘리시움',4),(8,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Enosis.png','Enosis','이노시스',4),(9,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Red.png','Red','레드',4),(10,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Orora.png','Orora','오로라',4),(11,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Arcane.png','Arcane','아케인',4),(12,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Nova.png','Nova','노바',4),(13,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Reboot.png','Reboot','리부트',4),(14,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/MapleStory-Reboot2.png','Reboot2','리부트2',4),(15,NULL,'Rupeon','루페온',5),(16,NULL,'Silian','실리안',5),(17,NULL,'Aman','아만',5),(18,NULL,'Kamain','카마인',5),(19,NULL,'Kazeros','카제로스',5),(20,NULL,'Abrelshud','아브렐슈드',5),(21,NULL,'Kadan','카단',5),(22,NULL,'Ninav','니나브',5),(23,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/BattleGrounds-Kakao.jpg','Kakao','카카오',7),(24,'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/GameOptions/GameServer/BattleGrounds-Steam.png','Steam','스팀',7);
/*!40000 ALTER TABLE `game_server` ENABLE KEYS */;
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
