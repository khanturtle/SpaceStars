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
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `exp` bigint DEFAULT NULL,
  `game_preference_id` bigint DEFAULT NULL,
  `introduce` varchar(50) DEFAULT NULL,
  `main_game_profile` bit(1) NOT NULL,
  `mbti_id` bigint DEFAULT NULL,
  `report_count` int NOT NULL,
  `swipe` bit(1) NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,NULL,NULL,78,5,'안녕하세요, 열정적인 게이머입니다!',_binary '\0',12,3,_binary '','abc123'),(2,NULL,NULL,45,8,'새로운 게임을 탐험하고 새로운 사람들을 만나는 것을 좋아합니다.',_binary '\0',9,1,_binary '','def456'),(3,NULL,NULL,92,3,'심장으로 게이머, 직업적으로 엔지니어입니다.',_binary '\0',7,7,_binary '','ghi789'),(4,NULL,NULL,67,14,'전략 게임과 퍼즐을 즐깁니다.',_binary '\0',4,5,_binary '','jkl012'),(5,NULL,NULL,85,10,'스포츠 게임을 좋아합니다. 함께 게임해요!',_binary '\0',2,2,_binary '','mno345'),(6,NULL,NULL,55,6,'RPG에서 팀원을 찾고 있어요.',_binary '\0',10,4,_binary '','pqr678'),(7,NULL,NULL,34,1,'인디 게임 세계를 탐험 중입니다.',_binary '\0',6,0,_binary '','stu901'),(8,NULL,NULL,78,11,'기술 애호가이자 게이머입니다.',_binary '\0',13,8,_binary '','vwx234'),(9,NULL,NULL,42,16,'다양한 장르를 즐기는 캐주얼 게이머입니다.',_binary '\0',15,9,_binary '','yza567'),(10,NULL,NULL,70,12,'최고의 랭킹을 목표로 하는 경쟁적인 게이머입니다.',_binary '\0',1,6,_binary '','bcd890'),(11,NULL,NULL,88,7,'소셜라이징하고 멀티플레이 게임을 즐기는 사람입니다.',_binary '\0',14,3,_binary '','efg123'),(12,NULL,NULL,60,9,'전략 게임을 즐기는 사람입니다.',_binary '\0',8,2,_binary '','hij456'),(13,NULL,NULL,25,2,'어드벤처 게임을 내 피해자로 삼습니다.',_binary '\0',11,1,_binary '','klm789'),(14,NULL,NULL,37,13,'긴 하루 끝에 느긋하게 게임합니다.',_binary '\0',5,0,_binary '','nop012'),(15,NULL,NULL,49,4,'싱글 플레이어와 멀티플레이어 경험을 모두 즐기는 게이머입니다.',_binary '\0',3,4,_binary '','qrs345'),(16,NULL,NULL,83,15,'새로운 코옵 게임을 함께 탐험할 친구를 찾고 있습니다.',_binary '\0',16,7,_binary '','tuv678'),(17,NULL,NULL,94,8,'FPS 애호가이자 e스포츠 팔로워입니다.',_binary '\0',7,5,_binary '','wxy901'),(18,NULL,NULL,72,6,'게임 커뮤니티에서 스트리머 및 콘텐츠 제작자입니다.',_binary '\0',12,3,_binary '','zab234'),(19,NULL,NULL,62,11,'주간 개발자, 밤새 게이머입니다.',_binary '\0',4,1,_binary '','cde567'),(20,NULL,NULL,30,1,'레트로 게임은 어린 시절의 추억을 떠올립니다.',_binary '\0',10,0,_binary '','fgh890'),(21,NULL,NULL,53,14,'VR 게임은 미래입니다!',_binary '\0',2,2,_binary '','ijk123'),(22,NULL,NULL,87,3,'최신 게임 기술을 탐색하기를 고대합니다.',_binary '\0',6,6,_binary '','lmn456'),(23,NULL,NULL,40,16,'협동 게임을 위한 동질적인 게이머를 찾고 있습니다.',_binary '\0',9,9,_binary '','opq789'),(24,NULL,NULL,75,5,'내가 게임할 때마다 모든 게임을 마스터하고 있습니다.',_binary '\0',1,4,_binary '','rst012'),(25,NULL,NULL,48,7,'느긋한 게임 경험을 즐기는 캐주얼 게이머입니다.',_binary '\0',13,7,_binary '','uvw345'),(26,NULL,NULL,63,10,'경쟁 게임에서 도전을 항상 준비하고 있습니다.',_binary '\0',15,5,_binary '','xyz678'),(27,NULL,NULL,58,2,'스토리 중심 게임이 저를 사로잡습니다.',_binary '\0',8,2,_binary '','123901'),(28,NULL,NULL,31,12,'파트 타임 게이머, 풀 타임 탐험가입니다.',_binary '\0',3,0,_binary '','456234'),(29,NULL,NULL,84,9,'퍼즐 해결 게임을 즐기는 게이머입니다.',_binary '\0',11,8,_binary '','789567'),(30,NULL,NULL,51,4,'모험은 제가 게임을 할 때마다 기다립니다.',_binary '\0',5,6,_binary '','012890'),(31,NULL,NULL,69,13,'경쟁 게임과 전략에 집중하는 경쟁 게이머입니다.',_binary '\0',14,3,_binary '','345123'),(32,NULL,NULL,90,15,'게임 기술을 개선하고 싶습니다.',_binary '\0',16,1,_binary '','678456'),(33,NULL,NULL,73,6,'게임 세계를 정복하기 위해 팀을 짜보세요!',_binary '\0',12,5,_binary '','901789'),(34,NULL,NULL,39,11,'게임을 통해 친구와 가족과 연결하고 싶습니다.',_binary '\0',4,9,_binary '','234012'),(35,NULL,NULL,54,1,'에픽 게임 세션을 위해 팀을 짜 봅시다!',_binary '\0',10,7,_binary '','567345'),(36,NULL,NULL,65,14,'가상 및 실제 세계에서 모험을 찾고 있습니다.',_binary '\0',2,4,_binary '','890678'),(37,NULL,NULL,27,3,'새로운 장르와 게임 메커니즘을 탐색합니다.',_binary '\0',6,2,_binary '','123901'),(38,'2024-06-26 01:08:58.026816','2024-06-26 01:09:35.343833',0,NULL,NULL,_binary '\0',8,0,_binary '\0','9b97c783-7646-4d8a-8a98-7bb1ef55e192'),(39,'2024-06-27 01:40:15.056496','2024-06-27 01:40:35.909391',0,NULL,NULL,_binary '\0',5,0,_binary '\0','900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(40,'2024-06-27 06:32:50.404767','2024-06-27 06:33:19.293145',0,NULL,NULL,_binary '\0',13,0,_binary '\0','44a534fe-fe5f-4198-9f12-d1186117bfd7'),(41,'2024-06-28 03:22:41.435693','2024-07-01 05:45:30.652872',0,NULL,'같이 게임해요 ㅎㅎ',_binary '\0',3,0,_binary '\0','c1be880b-e783-488b-a132-36b3dbe3c860'),(42,'2024-06-28 07:22:50.988064','2024-06-28 07:23:18.323486',0,NULL,NULL,_binary '\0',1,0,_binary '\0','dec2b368-7edd-489d-b4b6-374648a787d1'),(43,'2024-06-28 07:38:56.715495','2024-06-28 07:39:18.340554',0,NULL,NULL,_binary '\0',12,0,_binary '\0','6612f181-3e02-4f23-b789-192df91a0d97'),(44,'2024-07-01 05:38:27.211848','2024-07-01 05:39:01.646852',0,NULL,NULL,_binary '\0',3,0,_binary '\0','a2d2a621-625e-4c2b-8537-aa61c8b942fd');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:39:32
