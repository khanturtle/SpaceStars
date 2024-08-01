-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: chat
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
-- Table structure for table `team_chat_room`
--

DROP TABLE IF EXISTS `team_chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_chat_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  `is_finished` bit(1) DEFAULT NULL,
  `is_password` bit(1) DEFAULT NULL,
  `max_members` int NOT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `room_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_chat_room`
--

LOCK TABLES `team_chat_room` WRITE;
/*!40000 ALTER TABLE `team_chat_room` DISABLE KEYS */;
INSERT INTO `team_chat_room` VALUES (43,NULL,NULL,1,_binary '',_binary '',4,'레벨 10 이상의 유저를 찾습니다. 함께 던전 돌아요!','1234','팀원 모집','c8f8f58b-7896-47df-86f1-779f78a5b2a7'),(44,NULL,NULL,2,_binary '',_binary '\0',3,'이번 주 레이드 공략법 공유합니다. 참여하세요!',NULL,'공략 공유','df0b22b9-828b-4d36-8f71-795ecf3f5f8c'),(45,NULL,NULL,3,_binary '',_binary '',5,'가볍게 즐길 사람들 모여요. 저녁에 같이 게임해요.','5678','캐주얼 플레이','f6d013c8-c8f6-4b77-812d-e77b8db3fda2'),(46,NULL,NULL,4,_binary '',_binary '\0',6,'활발한 활동을 하는 클랜원 모집합니다. 함께 성장해요.',NULL,'클랜원 모집','a2f0cb99-6f96-4c77-917f-1c58f5b5f6f8'),(47,NULL,NULL,5,_binary '',_binary '',3,'다음 주 경기 전술 논의. 참여 필수!','4321','전술 토론','f9c2f27a-8f6a-4f16-9a7b-8c7f6d3f5b2a'),(48,NULL,NULL,6,_binary '',_binary '\0',4,'금요일 밤 게임 함께 즐길 사람 모집합니다.',NULL,'게임의 밤','cf5d8f2e-8b7c-4d76-9f6b-7f6d2e1a5b8f'),(49,NULL,NULL,7,_binary '',_binary '',5,'주말에 레이드 같이 할 팀원 구해요. 경험자 우대.','8765','주말 레이드','8f9c5d2a-7e6b-4c7a-9d6f-6f5d3b2e7c8f'),(50,NULL,NULL,1,_binary '\0',_binary '\0',6,'초보자도 환영합니다. 함께 게임 배워요!',NULL,'초보 환영','5c8f2d3b-7c9f-4d6a-8e7b-6d5f3c2e1a8f'),(51,NULL,NULL,2,_binary '\0',_binary '',4,'신규 패치 이후 전략 연구합니다. 같이 해봐요.','5678','전략 연구','9d6f3c5b-8e7f-4d6a-9c7b-7d5f2e1a5b3f'),(52,NULL,NULL,3,_binary '\0',_binary '\0',3,'모험가 길드 멤버 모집. 함께 모험 떠나요.',NULL,'모험가 길드','6f5d8e2a-7c6f-4d7a-9e6b-5d3f2a8c7b9f'),(53,NULL,NULL,4,_binary '\0',_binary '',5,'오늘 밤새 게임할 사람들 모집합니다.','4321','밤샘 파티','5d3f2e8c-7c6b-4d7a-8e7f-6f5d3a2b9c8f'),(54,NULL,NULL,5,_binary '\0',_binary '\0',6,'경험치 파밍 함께 할 팀원 구합니다.',NULL,'경험치 파밍','7c6d2e8b-5d7f-4d7a-8e6b-5d3f2c8a9b7f'),(55,NULL,NULL,6,_binary '\0',_binary '',4,'보스 레이드 팀 모집. 화력 좋으신 분 환영.','8765','보스 레이드','8e7c6f5b-6d5f-4d7a-9e6b-5d3f2b8a7c9f'),(56,NULL,NULL,7,_binary '\0',_binary '\0',5,'주말 동안 전투 함께 하실 분?',NULL,'주말 전투','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(57,NULL,NULL,1,_binary '',_binary '',6,'레벨업 파티 모집. 빠르게 성장해요.','1234','레벨업 파티','4f5b7e2a-9d6c-4d7a-8e6f-5f3c2a9e7d8f'),(58,NULL,NULL,2,_binary '',_binary '\0',4,'하드코어하게 플레이할 팀원 모집합니다.',NULL,'하드코어 팀','3f6d8b2a-9c7e-4d6a-8e7f-5d3f2a8b9c7f'),(59,NULL,NULL,3,_binary '',_binary '',3,'친목 클랜 멤버 모집. 함께 즐겨요.','9876','친목 클랜','7e9c5d2a-6f8b-4d7a-9e6f-5d3f2c8a1b6f'),(60,NULL,NULL,4,_binary '',_binary '\0',5,'게임 이야기 나눌 사람 구해요.',NULL,'게임 토크','2f8d6e3a-9b7c-4d6a-8e6f-5f3a2b9d7c8f'),(61,NULL,NULL,5,_binary '',_binary '',6,'레이드 팀원 구합니다. 경험자 우대.','4321','레이드 팀','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(62,NULL,NULL,6,_binary '',_binary '\0',4,'신규 던전 공략법 공유합니다.',NULL,'전략 공유','8e7c6d2b-5d7f-4d7a-9e6b-5d3f2a8b9c6f'),(63,NULL,NULL,7,_binary '',_binary '',5,'파티 플레이 함께 하실 분?','5678','파티 플레이','9d6f3b2a-8e7f-4d6a-9c7b-5d3f2e1a5b8f'),(64,NULL,NULL,1,_binary '\0',_binary '\0',6,'초보자들 모여서 함께 게임해요.',NULL,'초보자 팀','6f5d8e2a-7c6f-4d7a-9e6b-5d3f2a8c7b9f'),(65,NULL,NULL,2,_binary '\0',_binary '',4,'게임 친구 찾습니다. 즐겁게 플레이해요.','8765','친구 구함','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(66,NULL,NULL,3,_binary '\0',_binary '\0',3,'초보자들 위한 가이드 세션.',NULL,'초보자 가이드','5d3f2e8c-7c6b-4d7a-8e7f-6f5d3a2b9c8f'),(67,NULL,NULL,4,_binary '\0',_binary '',5,'빠른 레벨업 원하시는 분들 모집.','9876','빠른 레벨업','7c6d2e8b-5d7f-4d7a-8e6b-5d3f2c8a9b7f'),(68,NULL,NULL,5,_binary '\0',_binary '\0',6,'주간 퀘스트 같이 할 사람?',NULL,'주간 퀘스트','8e7c6f5b-6d5f-4d7a-9e6b-5d3f2b8a7c9f'),(69,NULL,NULL,6,_binary '\0',_binary '',4,'보스 토벌전 팀원 구해요.','1234','보스 토벌','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(70,NULL,NULL,7,_binary '\0',_binary '\0',5,'친목 도모할 게임 친구 찾습니다.',NULL,'친목 도모','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(71,NULL,NULL,1,_binary '\0',_binary '',6,'전략 논의할 사람들 모여요.','4321','전략 세션','7e9c5d2a-6f8b-4d7a-9e6f-5d3f2c8a1b6f'),(72,NULL,NULL,2,_binary '\0',_binary '\0',4,'모험 떠날 동반자 찾습니다.',NULL,'모험 동반자','2f8d6e3a-9b7c-4d6a-8e6f-5f3a2b9d7c8f'),(73,NULL,NULL,3,_binary '\0',_binary '',3,'최신 공략법 공유합니다.','8765','공략 공유','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(74,NULL,NULL,4,_binary '\0',_binary '\0',5,'게임 친구 모집해요. 함께 즐겨요.',NULL,'친구 모집','8e7c6d2b-5d7f-4d7a-9e6b-5d3f2a8b9c6f'),(75,NULL,NULL,5,_binary '\0',_binary '',6,'빠른 레벨업 팁 공유합니다.','5678','레벨업 팁','9d6f3b2a-8e7f-4d6a-9c7b-5d3f2e1a5b8f'),(76,NULL,NULL,6,_binary '\0',_binary '\0',4,'던전 클리어 목표로 함께해요.',NULL,'던전 클리어','6f5d8e2a-7c6f-4d7a-9e6b-5d3f2a8c7b9f'),(77,NULL,NULL,7,_binary '\0',_binary '',5,'전투 연습 팀원 모집.','4321','전투 연습','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(78,NULL,NULL,1,_binary '\0',_binary '\0',6,'레이드 공략 토론할 사람들 모여요.',NULL,'공략 토론','5d3f2e8c-7c6b-4d7a-8e7f-6f5d3a2b9c8f'),(79,NULL,NULL,2,_binary '\0',_binary '',4,'퀘스트 클리어 도와주실 분?','9876','퀘스트 클리어','7c6d2e8b-5d7f-4d7a-8e6b-5d3f2c8a9b7f'),(80,NULL,NULL,3,_binary '\0',_binary '\0',3,'파티원 모집합니다. 빠르게 클리어해요.',NULL,'파티원 모집','8e7c6f5b-6d5f-4d7a-9e6b-5d3f2b8a7c9f'),(81,NULL,NULL,4,_binary '\0',_binary '',5,'레벨링 팀원 구해요. 함께 성장해요.','1234','레벨링 팀','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(82,NULL,NULL,5,_binary '\0',_binary '\0',6,'전술 연구 세션. 참여하세요!',NULL,'전술 연구','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(83,NULL,NULL,6,_binary '\0',_binary '',4,'게임 동료 구해요. 즐겁게 플레이해요.','9876','게임 동료','7e9c5d2a-6f8b-4d7a-9e6f-5d3f2c8a1b6f'),(84,NULL,NULL,7,_binary '\0',_binary '\0',5,'던전 탐험 함께 할 사람?',NULL,'던전 탐험','2f8d6e3a-9b7c-4d6a-8e6f-5f3a2b9d7c8f'),(85,NULL,NULL,1,_binary '\0',_binary '',6,'주말에 레이드 같이 하실 분?','5678','주말 레이드','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(86,NULL,NULL,2,_binary '\0',_binary '\0',4,'길드원 모집합니다. 함께 성장해요.',NULL,'길드 모집','8e7c6d2b-5d7f-4d7a-9e6b-5d3f2a8b9c6f'),(87,NULL,NULL,3,_binary '\0',_binary '',3,'퀘스트 도움 주실 분?','8765','퀘스트 도움','9d6f3b2a-8e7f-4d6a-9c7b-5d3f2e1a5b8f'),(88,NULL,NULL,4,_binary '\0',_binary '\0',5,'레이드 클리어 목표로 함께해요.',NULL,'레이드 클리어','6f5d8e2a-7c6f-4d7a-9e6b-5d3f2a8c7b9f'),(89,NULL,NULL,5,_binary '\0',_binary '',6,'전투 전략 논의. 참여하세요!','4321','전투 전략','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(90,NULL,NULL,6,_binary '\0',_binary '\0',4,'친목 클랜 멤버 모집합니다.',NULL,'친목 클랜','5d3f2e8c-7c6b-4d7a-8e7f-6f5d3a2b9c8f'),(91,NULL,NULL,7,_binary '\0',_binary '',5,'초보자 길드원 모집합니다. 함께 성장해요.','5678','초보자 길드','7c6d2e8b-5d7f-4d7a-8e6b-5d3f2c8a9b7f'),(92,NULL,NULL,1,_binary '\0',_binary '\0',6,'팀원 모집합니다. 함께 레벨업해요.',NULL,'팀원 모집','8e7c6f5b-6d5f-4d7a-9e6b-5d3f2b8a7c9f'),(93,NULL,NULL,2,_binary '\0',_binary '',4,'주말 이벤트 참여할 사람?','9876','주말 이벤트','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(94,NULL,NULL,3,_binary '\0',_binary '\0',3,'게임 친구 구합니다. 함께 즐겨요.',NULL,'게임 친구','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(95,NULL,NULL,4,_binary '\0',_binary '',5,'초보자 모임. 함께 게임 배워요.','1234','초보자 모임','7e9c5d2a-6f8b-4d7a-9e6f-5d3f2c8a1b6f'),(96,NULL,NULL,5,_binary '\0',_binary '\0',6,'전략 논의할 사람 구합니다.',NULL,'전략 토론','2f8d6e3a-9b7c-4d6a-8e6f-5f3a2b9d7c8f'),(97,NULL,NULL,6,_binary '\0',_binary '',4,'주간 퀘스트 함께 할 사람?','8765','주간 퀘스트','3f6d8b2a-9e7f-4d6a-8e7f-5d3f2a8b9c7f'),(98,NULL,NULL,7,_binary '\0',_binary '\0',5,'친목 도모할 게임 친구 찾습니다.',NULL,'친목 도모','8e7c6d2b-5d7f-4d7a-9e6b-5d3f2a8b9c6f'),(99,NULL,NULL,1,_binary '\0',_binary '',6,'전투 연습 팀원 모집합니다.','5678','전투 연습','9d6f3b2a-8e7f-4d6a-9c7b-5d3f2e1a5b8f'),(100,NULL,NULL,2,_binary '\0',_binary '\0',4,'퀘스트 도움 주실 분 구해요.',NULL,'퀘스트 도움','6f5d8e2a-7c6f-4d7a-9e6b-5d3f2a8c7b9f'),(101,NULL,NULL,3,_binary '\0',_binary '',3,'길드원 모집합니다. 함께 성장해요.','8765','길드 모집','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(102,NULL,NULL,4,_binary '\0',_binary '\0',5,'던전 탐험 함께 할 사람?',NULL,'던전 탐험','5d3f2e8c-7c6b-4d7a-8e7f-6f5d3a2b9c8f'),(103,NULL,NULL,5,_binary '\0',_binary '',6,'게임 동료 구해요. 즐겁게 플레이해요.','4321','게임 동료','7c6d2e8b-5d7f-4d7a-8e6b-5d3f2c8a9b7f'),(104,NULL,NULL,6,_binary '\0',_binary '\0',4,'친목 클랜 멤버 모집합니다.',NULL,'친목 클랜','8e7c6f5b-6d5f-4d7a-9e6b-5d3f2b8a7c9f'),(105,NULL,NULL,7,_binary '\0',_binary '',5,'레벨업 팀원 구해요. 함께 성장해요.','5678','레벨업 팀','1f8b3e2d-6f9c-4d7a-9e7f-6f5d2b8a3c7f'),(106,'2024-07-01 01:58:58.929950','2024-07-01 01:58:58.929950',1,_binary '\0',_binary '\0',5,'같이 게임해요 즐겜환영',NULL,'롤 5인큐 하실분','7b47fc96-d4e3-4259-995a-6f2ae04feaf8'),(107,'2024-07-01 03:09:43.080119','2024-07-01 03:09:43.080119',4,_binary '\0',_binary '\0',3,'검마 30분컷 ',NULL,'메이플 검마팟','ddc751cc-b45a-4aa5-9774-c7824e8bb65d'),(108,'2024-07-02 01:16:37.677030','2024-07-02 01:16:37.677030',3,_binary '\0',_binary '\0',5,'ㅎㅇ',NULL,'발로 ㄱ','2bc99b95-e4a5-49b5-aa5a-53b261394b17');
/*!40000 ALTER TABLE `team_chat_room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:40:12
