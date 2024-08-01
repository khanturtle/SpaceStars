-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: swipe
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
-- Table structure for table `swipe`
--

DROP TABLE IF EXISTS `swipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `swipe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `match_from_member` varchar(255) NOT NULL,
  `match_to_member` varchar(255) NOT NULL,
  `memo` varchar(30) DEFAULT NULL,
  `status` enum('AGREE','REJECT','WAIT') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `swipe`
--

LOCK TABLES `swipe` WRITE;
/*!40000 ALTER TABLE `swipe` DISABLE KEYS */;
INSERT INTO `swipe` VALUES (1,'2024-06-27 16:25:05.071719','2024-06-27 16:25:05.071719','abc123','a378ed19-55f0-4d4b-963f-de923d2b9429','하이염','WAIT'),(2,'2024-06-27 07:52:22.244806','2024-06-27 07:52:22.244806','abc123','f88ccb89-efc3-41bf-a897-90364f6898e6','하이염33','WAIT'),(3,'2024-06-27 08:38:08.657533','2024-06-27 08:38:08.657533','abc123','456234','하이염33','WAIT'),(4,'2024-06-27 10:47:37.921644','2024-06-27 10:47:37.921644','44a534fe-fe5f-4198-9f12-d1186117bfd7','vwx234','안녕?','WAIT'),(6,'2024-06-28 07:25:10.972431','2024-06-28 07:25:10.972431','dec2b368-7edd-489d-b4b6-374648a787d1','c1be880b-e783-488b-a132-36b3dbe3c860','해','WAIT'),(7,'2024-06-28 07:34:53.836529','2024-06-28 07:34:53.836529','06848107-0c71-4e80-928c-2eca7e5d3ffd','c1be880b-e783-488b-a132-36b3dbe3c860','rr','WAIT'),(15,'2024-07-01 02:43:37.967177','2024-07-01 02:43:37.967177','c1be880b-e783-488b-a132-36b3dbe3c860','c1be880b-e783-488b-a132-36b3dbe3c860','어? 나네?','WAIT'),(16,'2024-07-01 02:44:06.901197','2024-07-01 02:44:06.901197','9b97c783-7646-4d8a-8a98-7bb1ef55e192','lmn456','같이 게임해요 ㅎㅎ','WAIT'),(22,'2024-07-01 05:30:18.086418','2024-07-01 05:30:18.086418','mno345','44a534fe-fe5f-4198-9f12-d1186117bfd','안녕 ! 반가워','WAIT'),(23,'2024-07-01 05:31:27.978903','2024-07-01 05:31:27.978903','nop012','44a534fe-fe5f-4198-9f12-d1186117bfd','안녕 ! 반가워','WAIT'),(24,'2024-07-01 05:33:08.030611','2024-07-01 05:33:08.030611','qrs345','44a534fe-fe5f-4198-9f12-d1186117bfd','안녕 ! 반가워','WAIT'),(25,'2024-07-01 05:39:00.075014','2024-07-01 05:39:00.075014','qrs345','c1be880b-e783-488b-a132-36b3dbe3c860','안녕 ! 반가워','WAIT'),(26,'2024-07-01 05:42:43.674338','2024-07-01 05:42:43.674338','890678','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','WAIT'),(27,'2024-07-01 06:02:52.696744','2024-07-01 06:02:52.696744','pqr678','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','AGREE'),(28,'2024-07-01 06:12:06.134361','2024-07-01 06:12:06.134361','bcd890','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','WAIT'),(29,'2024-07-01 06:30:33.506865','2024-07-01 06:30:33.506865','3d6d8d56-3686-4466-9620-ceee9732fb36','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','WAIT'),(30,'2024-07-01 06:33:24.356633','2024-07-01 06:33:24.356633','ca6d16bd-8d90-4c00-a994-734f68c079de','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','WAIT'),(31,'2024-07-01 06:45:20.465234','2024-07-01 06:45:20.465234','8c93bbeb-a6c5-4188-8340-626e0dffa7e0','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','AGREE'),(32,'2024-07-01 06:48:50.699899','2024-07-01 06:48:50.699899','9f21e9fa-cda7-4048-bd5b-fa1366c7953f','44a534fe-fe5f-4198-9f12-d1186117bfd7','안녕 ! 반가워','WAIT'),(33,'2024-07-02 04:36:25.423196','2024-07-02 04:36:25.423196','678456','900f15ca-3bf8-46a2-9354-29f77a9d1fc0','같이 게임할래?','AGREE');
/*!40000 ALTER TABLE `swipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:36:41