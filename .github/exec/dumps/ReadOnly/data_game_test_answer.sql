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
-- Table structure for table `game_test_answer`
--

DROP TABLE IF EXISTS `game_test_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_test_answer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `answer1` varchar(100) NOT NULL,
  `answer2` varchar(100) NOT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_test_answer`
--

LOCK TABLES `game_test_answer` WRITE;
/*!40000 ALTER TABLE `game_test_answer` DISABLE KEYS */;
INSERT INTO `game_test_answer` VALUES (1,'이번엔 어떤 조합을 할까?친구에게 게임 한 판 하자고 한다.','나만의 소중한 시간… 지난 주에 사 놨던 게임을 드디어 켜 본다.',1),(2,'딱 봐도 내 취향이 아니니 안한다고 한다','같이 하면 재밌겠지? 친구 말 듣고 같이 해본다',2),(3,'자기소개에 출석체크까지? 나에겐 너무 버겁다','좋은 정보가 있기 때문에 3일 뒤에 등업이 되어도 상관없다',3),(4,'다른 사람의 리뷰, 트레일러, 가격 등을 꼼곰하게 살펴보고 구매한다','괜찮아 보이는 게임이 있으면 나의 촉을 믿고 게임을 구매한다',4),(5,'오히려 좋아. 나의 멋진 퍼포먼스를 보여준다','원래는 더 잘하는데… 부담스러워서 괜히 스킬이 빗나간다',5),(6,'나중에 뭔가 놓쳐서 괜히 후회하느니 튜토리얼부터 차근차근 해본다','예전에 해본 그 게임이랑 비슷하네. 튜토리얼은 건너 뛴다',6),(7,'다년간의 게임 경험을 바탕으로 한 나의 빅데이터가 말하고 있다. 저놈이 범인이다','좀 의심스럽게 생기긴 했지만 범인이라는 증거는 아직 없으니 찬찬히 진행해 보자',7),(8,'0.01 별로 큰 수치도 아니고 친구들이 기다리니 그냥 간다','잠깐만 기다려줘, 잠깐 나가서 룬을 장착하고 온다',8),(9,'치료제를 찾을 수도 있으니 당분간 상태를 지켜보며 함께 다니는 쪽을 선택','우리 그룹 사람들이 다 감염될 수 있으니 눈물을 머금고(저세상) 보내는 쪽을 선택',9),(10,'오늘 컨디션이 좀 안좋은가? 친구의 기를 북돋아 준다.','친구가 하는 캐릭터의 공략을 링크하며 이대로 해보면 어떠냐고 한다.',10),(11,'아쉽지만 성능을 고려하면 파티에 넣을 수 없다.','나의 최애캐는 어떻게든 파티에 넣어야 한다.',11),(12,'바탕화면에 아이콘이 정-말 많지만 게임 하는 데는 전혀 지장 없다','정신사나운건 딱 질색이다. 바탕화면은 깔끔하게 정리했다.',12),(13,'혹시라도 도전과제를 빼먹으면 안되니까 검색해 보고 게임을 시작한다','도전과제를 신경쓰며 게임해 본 적이 없다',13),(14,'게임의 홍수 속에서 그때 그때 내키는 게임을 한다','무슨 게임을 언제까지 끝낼지 계획을 세워 둬야 마음이 편하다',14);
/*!40000 ALTER TABLE `game_test_answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:38:39
