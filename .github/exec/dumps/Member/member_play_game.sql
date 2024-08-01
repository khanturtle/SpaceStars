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
-- Table structure for table `play_game`
--

DROP TABLE IF EXISTS `play_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_game` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_id` bigint DEFAULT NULL,
  `game_id` bigint NOT NULL,
  `game_nickname` varchar(50) DEFAULT NULL,
  `main` bit(1) NOT NULL,
  `position_id` bigint DEFAULT NULL,
  `server_id` bigint DEFAULT NULL,
  `tier_id` bigint DEFAULT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_game`
--

LOCK TABLES `play_game` WRITE;
/*!40000 ALTER TABLE `play_game` DISABLE KEYS */;
INSERT INTO `play_game` VALUES (50,NULL,3,NULL,_binary '',6,NULL,29,'9b97c783-7646-4d8a-8a98-7bb1ef55e192'),(51,NULL,3,NULL,_binary '',8,NULL,27,'c1be880b-e783-488b-a132-36b3dbe3c860'),(132,NULL,1,NULL,_binary '',3,NULL,5,'ecb1242e-969a-49a3-aef0-cbacac11885a'),(133,NULL,1,'용사준1',_binary '',3,NULL,2,'abc123'),(134,NULL,1,'마법사종1',_binary '',2,NULL,10,'vwx234'),(135,NULL,1,'탱크김1',_binary '',4,NULL,3,'qrs345'),(136,NULL,1,'고무오리 광전사1',_binary '',1,NULL,5,'lmn456'),(137,NULL,1,'나라 지킨 아레스1',_binary '',5,NULL,5,'789567'),(138,NULL,1,'빛나는 일주민1',_binary '',3,NULL,8,'890678'),(139,NULL,1,'가시 사슴1',_binary '',3,NULL,1,'901789'),(140,NULL,1,'전사영2',_binary '\0',5,NULL,10,'def456'),(141,NULL,1,'포용2',_binary '\0',4,NULL,10,'yza567'),(142,NULL,1,'마법사서2',_binary '\0',1,NULL,1,'tuv678'),(143,NULL,1,'반역자2',_binary '\0',2,NULL,2,'opq789'),(144,NULL,1,'발사체2',_binary '\0',2,NULL,3,'012890'),(145,NULL,1,'계략2',_binary '\0',5,NULL,6,'123901'),(146,NULL,1,'마법사승3',_binary '\0',4,NULL,4,'ghi789'),(147,NULL,2,'전사영2',_binary '',NULL,NULL,15,'def456'),(148,NULL,2,'포용2',_binary '',NULL,NULL,13,'yza567'),(149,NULL,2,'마법사서2',_binary '',NULL,NULL,11,'tuv678'),(150,NULL,2,'반역자2',_binary '',NULL,NULL,13,'opq789'),(151,NULL,2,'발사체2',_binary '',NULL,NULL,14,'012890'),(152,NULL,2,'계략2',_binary '',NULL,NULL,17,'123901'),(153,NULL,2,'용사준1',_binary '\0',NULL,NULL,15,'abc123'),(154,NULL,2,'마법사종1',_binary '\0',NULL,NULL,13,'vwx234'),(155,NULL,2,'탱크김1',_binary '\0',NULL,NULL,11,'qrs345'),(156,NULL,2,'고무오리 광전사1',_binary '\0',NULL,NULL,13,'lmn456'),(157,NULL,2,'나라 지킨 아레스1',_binary '\0',NULL,NULL,12,'789567'),(158,NULL,2,'빛나는 일주민1',_binary '\0',NULL,NULL,20,'890678'),(159,NULL,3,'마법사승3',_binary '',6,NULL,24,'ghi789'),(160,NULL,3,'소위3',_binary '',9,NULL,25,'bcd890'),(161,NULL,3,'잠복한 마법사3',_binary '',7,NULL,22,'wxy901'),(162,NULL,3,'가리려는 마법사3',_binary '',8,NULL,29,'rst012'),(163,NULL,3,'코스프레3',_binary '',8,NULL,28,'zab234'),(164,NULL,3,'탈출해야 하는 소위3',_binary '',8,NULL,24,'567345'),(165,NULL,3,'가시 사슴1',_binary '\0',7,NULL,23,'901789'),(166,NULL,3,'도적민4',_binary '\0',6,NULL,21,'jkl012'),(167,NULL,3,'도적헌4',_binary '\0',7,NULL,24,'efg123'),(168,NULL,3,'소위 삼4',_binary '\0',7,NULL,29,'uvw345'),(169,NULL,3,'고무오리 대장4',_binary '\0',9,NULL,29,'678456'),(170,NULL,3,'팔콘4',_binary '\0',9,NULL,25,'567345'),(171,31,4,'도적민4',_binary '',NULL,12,NULL,'jkl012'),(172,10,4,'도적헌4',_binary '',NULL,7,NULL,'efg123'),(173,36,4,'소위 삼4',_binary '',NULL,7,NULL,'uvw345'),(174,42,4,'고무오리 대장4',_binary '',NULL,4,NULL,'678456'),(175,17,4,'팔콘4',_binary '',NULL,2,NULL,'890678'),(176,28,4,'치료기능사5',_binary '\0',NULL,9,NULL,'mno345'),(177,10,4,'자유로운 궁수5',_binary '\0',NULL,4,NULL,'hij456'),(178,22,4,'파티 시커먼 궁수5',_binary '\0',NULL,10,NULL,'cde567'),(179,4,4,'폭죽5',_binary '\0',NULL,5,NULL,'xyz678'),(180,13,4,'병사5',_binary '\0',NULL,7,NULL,'901789'),(181,57,5,'치료기능사5',_binary '',NULL,20,NULL,'mno345'),(182,58,5,'자유로운 궁수5',_binary '',NULL,15,NULL,'hij456'),(183,67,5,'파티 시커먼 궁수5',_binary '',NULL,22,NULL,'cde567'),(184,47,5,'폭죽5',_binary '',NULL,18,NULL,'xyz678'),(185,56,5,'병사5',_binary '',NULL,17,NULL,'901789'),(186,56,5,'궁수재6',_binary '\0',NULL,15,NULL,'pqr678'),(187,70,5,'왕2',_binary '\0',NULL,19,NULL,'klm789'),(188,70,5,'광휘의 요정8',_binary '\0',NULL,22,NULL,'fgh890'),(189,70,5,'죽음이 하는 재간6',_binary '\0',NULL,21,NULL,'123901'),(190,57,5,'공룡 사냥꾼6',_binary '\0',NULL,19,NULL,'345123'),(191,NULL,6,'궁수재6',_binary '',12,NULL,36,'pqr678'),(192,NULL,6,'왕2',_binary '',11,NULL,32,'klm789'),(193,NULL,6,'광휘의 요정8',_binary '',11,NULL,34,'fgh890'),(194,NULL,6,'죽음이 하는 재간6',_binary '',12,NULL,33,'123901'),(195,NULL,6,'공룡 사냥꾼6',_binary '',12,NULL,33,'345123'),(196,NULL,6,'사서함7',_binary '\0',11,NULL,32,'stu901'),(197,NULL,6,'별사탕7',_binary '\0',12,NULL,31,'nop012'),(198,NULL,6,'소위2',_binary '\0',12,NULL,32,'ijk123'),(199,NULL,6,'반역자7',_binary '\0',10,NULL,35,'456234'),(200,NULL,6,'공룡 사냥꾼7',_binary '\0',12,NULL,33,'678456'),(201,NULL,7,'사서함7',_binary '',NULL,23,39,'stu901'),(202,NULL,7,'별사탕7',_binary '',NULL,24,40,'nop012'),(203,NULL,7,'소위2',_binary '',NULL,23,41,'ijk123'),(204,NULL,7,'반역자7',_binary '',NULL,24,39,'456234'),(205,NULL,7,'공룡 사냥꾼7',_binary '',NULL,24,39,'678456'),(206,NULL,7,'발사체7',_binary '',NULL,23,44,'567345'),(207,NULL,7,'가시 사슴1',_binary '\0',NULL,23,43,'901789'),(208,NULL,7,'소위3',_binary '\0',NULL,24,42,'bcd890'),(209,NULL,7,'잠복한 마법사3',_binary '\0',NULL,24,41,'wxy901'),(210,NULL,7,'가리려는 마법사3',_binary '\0',NULL,23,42,'rst012'),(211,NULL,7,'코스프레3',_binary '\0',NULL,23,44,'zab234'),(212,NULL,7,'탈출해야 하는 소위3',_binary '\0',NULL,24,43,'567345'),(213,NULL,1,NULL,_binary '',3,NULL,5,'900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(214,NULL,6,NULL,_binary '',10,NULL,30,'44a534fe-fe5f-4198-9f12-d1186117bfd7'),(215,NULL,6,NULL,_binary '',11,NULL,31,'dec2b368-7edd-489d-b4b6-374648a787d1'),(216,3,4,NULL,_binary '',NULL,3,NULL,'06848107-0c71-4e80-928c-2eca7e5d3ffd'),(217,NULL,1,NULL,_binary '',3,NULL,6,'6612f181-3e02-4f23-b789-192df91a0d97'),(218,NULL,3,NULL,_binary '',9,NULL,29,'a2d2a621-625e-4c2b-8537-aa61c8b942fd');
/*!40000 ALTER TABLE `play_game` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:39:33
