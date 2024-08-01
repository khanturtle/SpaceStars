-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: data
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
-- Table structure for table `game_test_question`
--

DROP TABLE IF EXISTS `game_test_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_test_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_test_question`
--

LOCK TABLES `game_test_question` WRITE;
/*!40000 ALTER TABLE `game_test_question` DISABLE KEYS */;
INSERT INTO `game_test_question` VALUES (1,'힘들었던 한 주, 드디어 주말이다'),(2,'새로나온 게임, 내 취향이 아니지만 친구가 소개하며 같이 하자고 한다'),(3,'게임 정보를 얻으려고 가입 조건이 까다로운 커뮤니티에 가입하려는 당신'),(4,'게임을 구매하기 전'),(5,'게임을 하는 중, 뒤에서 누가 나의 플레이를 쳐다본다면'),(6,'새로 나온 게임을 처음 플레이하는 당신'),(7,'추리 게임을 하는 당신'),(8,'보상으로 얻은 방어력 0.01% 상승 룬을 장착하려면 잠깐 로비에 다녀와야 하는 상황,'),(9,'게임 중, 여태까지 함께 지낸 동료 캐릭터가 좀비에 물려버렸다.'),(10,'내색은 하지 않지만 내 친구 때문에 자꾸 지는 것 같다.'),(11,'성능은 별로지만 외형이 정말 내 취향인 캐릭터'),(12,'나의 컴퓨터 바탕화면'),(13,'게임 하기 전 나는 '),(14,'게임을 정말 많이 하는 당신,');
/*!40000 ALTER TABLE `game_test_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:38:40
