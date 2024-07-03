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
-- Table structure for table `game_class`
--

DROP TABLE IF EXISTS `game_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_class_image` text,
  `game_class_name` varchar(50) DEFAULT NULL,
  `game_class_name_kor` varchar(50) DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpc9lmh0r58k6qqx9mce6cthbl` (`game_id`),
  CONSTRAINT `FKpc9lmh0r58k6qqx9mce6cthbl` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_class`
--

LOCK TABLES `game_class` WRITE;
/*!40000 ALTER TABLE `game_class` DISABLE KEYS */;
INSERT INTO `game_class` VALUES (1,NULL,'Hero','히어로',4),(2,NULL,'Paladin','팔라딘',4),(3,NULL,'Dark Knight','다크나이트',4),(4,NULL,'Arch Mage(Fire,Poison)','아크메이지(불,독)',4),(5,NULL,'Arch Mage(Ice,Lightning)','아크메이지(썬,콜)',4),(6,NULL,'Bishop','비숍',4),(7,NULL,'Bow Master','보우마스터',4),(8,NULL,'Marksman','신궁',4),(9,NULL,'PathFinder','패스파인더',4),(10,NULL,'NightRoad','나이트로드',4),(11,NULL,'Shadower','섀도어',4),(12,NULL,'Dual Blade','듀얼블레이드',4),(13,NULL,'Viper','바이퍼',4),(14,NULL,'Captain','캡틴',4),(15,NULL,'Canon Shooter','캐논슈터',4),(16,NULL,'Soul Master','소울마스터',4),(17,NULL,'Mihile','미하일',4),(18,NULL,'Flame Wizard','플레임위자드',4),(19,NULL,'Wind Breaker','윈드브레이커',4),(20,NULL,'Night Walker','나이트워커',4),(21,NULL,'Striker','스트라이커',4),(22,NULL,'Blaster','블래스터',4),(23,NULL,'Demon Slayer','데몬슬레이어',4),(24,NULL,'Demon Avenger','데몬어벤져',4),(25,NULL,'Battle Mage','배틀메이지',4),(26,NULL,'Wild Hunter','와일드헌터',4),(27,NULL,'Xenon','제논',4),(28,NULL,'Mechanic','메카닉',4),(29,NULL,'Aran','아란',4),(30,NULL,'Evan','에반',4),(31,NULL,'Luminous','루미너스',4),(32,NULL,'Mercedes','메르세데스',4),(33,NULL,'Phantom','팬텀',4),(34,NULL,'Eunwol','은월',4),(35,NULL,'Kaiser','카이저',4),(36,NULL,'Kain','카인',4),(37,NULL,'Cadena','카데나',4),(38,NULL,'Angelic Buster','엔젤릭버스터',4),(39,NULL,'Adele','아델',4),(40,NULL,'Illium','일리움',4),(41,NULL,'Khali','칼리',4),(42,NULL,'Ark','아크',4),(43,NULL,'Lara','라라',4),(44,NULL,'Ho Young','호영',4),(45,NULL,'Zero','제로',4),(46,NULL,'Kinesis','키네시스',4),(47,NULL,'Destroyer','디스트로이어',5),(48,NULL,'Warlord','워로드',5),(49,NULL,'Burserker','버서커',5),(50,NULL,'Holy Knight','홀리나이트',5),(51,NULL,'Slayer','슬레이어',5),(52,NULL,'Striker','스트라이커',5),(53,NULL,'Breaker','브레이커',5),(54,NULL,'Battle Master','배틀마스터',5),(55,NULL,'Infighter','인파이터',5),(56,NULL,'Soul Master','기공사',5),(57,NULL,'Lance Master','창술사',5),(58,NULL,'Devil Hunter','데빌헌터',5),(59,NULL,'Blaster','블래스터',5),(60,NULL,'Hawk Eye','호크아이',5),(61,NULL,'Scouter','스카우터',5),(62,NULL,'Gunslinger','건슬링어',5),(63,NULL,'Bard','바드',5),(64,NULL,'Summoner','서머너',5),(65,NULL,'Arcana','아르카나',5),(66,NULL,'Sorceress','소서리스',5),(67,NULL,'Blade','블레이드',5),(68,NULL,'Demonic','데모닉',5),(69,NULL,'Reaper','리퍼',5),(70,NULL,'Soul Eater','소울이터',5),(71,NULL,'Artist','도화가',5),(72,NULL,'Aeromancer','기상술사',5);
/*!40000 ALTER TABLE `game_class` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:37:11
