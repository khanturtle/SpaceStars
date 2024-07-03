-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 15.165.68.220    Database: auth
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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `gender` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
  `info_agree` bit(1) NOT NULL,
  `is_profile` bit(1) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `unregister` enum('BLACKLIST','DELETED','MEMBER') NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,NULL,NULL,'1990-01-01','abc123@example.com','MALE',_binary '',_binary '','용사123','MEMBER','abc123'),(2,NULL,NULL,'1992-03-15','def456@example.com','FEMALE',_binary '',_binary '','전사456','MEMBER','def456'),(3,NULL,NULL,'1985-07-20','ghi789@example.com','MALE',_binary '',_binary '','마법사789','MEMBER','ghi789'),(4,NULL,NULL,'1998-05-10','jkl012@example.com','FEMALE',_binary '',_binary '','도적012','MEMBER','jkl012'),(5,NULL,NULL,'1993-12-25','mno345@example.com','MALE',_binary '',_binary '','치료기능사345','MEMBER','mno345'),(6,NULL,NULL,'1996-09-30','pqr678@example.com','FEMALE',_binary '',_binary '','궁수678','MEMBER','pqr678'),(7,NULL,NULL,'1994-11-18','stu901@example.com','MALE',_binary '',_binary '','사서함901','MEMBER','stu901'),(8,NULL,NULL,'1991-04-05','vwx234@example.com','FEMALE',_binary '',_binary '','마법사234','MEMBER','vwx234'),(9,NULL,NULL,'1997-08-12','yza567@example.com','MALE',_binary '',_binary '','포용567','MEMBER','yza567'),(10,NULL,NULL,'1999-02-28','bcd890@example.com','FEMALE',_binary '',_binary '','소위890','MEMBER','bcd890'),(11,NULL,NULL,'1995-06-22','efg123@example.com','MALE',_binary '',_binary '','도적123','MEMBER','efg123'),(12,NULL,NULL,'1990-10-08','hij456@example.com','FEMALE',_binary '',_binary '','자유로운궁수456','MEMBER','hij456'),(13,NULL,NULL,'1993-03-03','klm789@example.com','MALE',_binary '',_binary '','왕789','MEMBER','klm789'),(14,NULL,NULL,'1998-07-17','nop012@example.com','FEMALE',_binary '',_binary '','별사탕012','MEMBER','nop012'),(15,NULL,NULL,'1992-09-25','qrs345@example.com','MALE',_binary '',_binary '','탱크345','MEMBER','qrs345'),(16,NULL,NULL,'1996-01-12','tuv678@example.com','FEMALE',_binary '',_binary '','마법사서678','MEMBER','tuv678'),(17,NULL,NULL,'1994-04-30','wxy901@example.com','MALE',_binary '',_binary '','잠복한마법사901','MEMBER','wxy901'),(18,NULL,NULL,'1997-11-05','zab234@example.com','FEMALE',_binary '',_binary '','코스프레234','MEMBER','zab234'),(19,NULL,NULL,'1991-12-15','cde567@example.com','MALE',_binary '',_binary '','파티시커먼궁수567','MEMBER','cde567'),(20,NULL,NULL,'1999-08-03','fgh890@example.com','FEMALE',_binary '',_binary '','광휘의요정890','MEMBER','fgh890'),(21,NULL,NULL,'1995-02-20','ijk123@example.com','MALE',_binary '',_binary '','소위123','MEMBER','ijk123'),(22,NULL,NULL,'1992-06-10','lmn456@example.com','FEMALE',_binary '',_binary '','고무오리광전사456','MEMBER','lmn456'),(23,NULL,NULL,'1993-10-28','opq789@example.com','MALE',_binary '',_binary '','반역자789','MEMBER','opq789'),(24,NULL,NULL,'1997-04-15','rst012@example.com','FEMALE',_binary '',_binary '','가리려는마법사012','MEMBER','rst012'),(25,NULL,NULL,'1994-08-22','uvw345@example.com','MALE',_binary '',_binary '','소위삼345','MEMBER','uvw345'),(26,NULL,NULL,'1998-12-01','xyz678@example.com','FEMALE',_binary '',_binary '','폭죽678','MEMBER','xyz678'),(27,NULL,NULL,'1996-02-14','123901@example.com','MALE',_binary '',_binary '','죽음이하는재간901','MEMBER','123901'),(28,NULL,NULL,'1990-07-09','456234@example.com','FEMALE',_binary '',_binary '','반역자234','MEMBER','456234'),(29,NULL,NULL,'1993-11-23','789567@example.com','MALE',_binary '',_binary '','나라지킨아레스567','MEMBER','789567'),(30,NULL,NULL,'1992-01-30','012890@example.com','FEMALE',_binary '',_binary '','발사체890','MEMBER','012890'),(31,NULL,NULL,'1995-05-18','345123@example.com','MALE',_binary '',_binary '','마법의여신123','MEMBER','345123'),(32,NULL,NULL,'1997-09-07','678456@example.com','FEMALE',_binary '',_binary '','고무오리대장456','MEMBER','678456'),(33,NULL,NULL,'1994-03-25','901789@example.com','MALE',_binary '',_binary '','병사789','MEMBER','901789'),(34,NULL,NULL,'1998-06-13','234012@example.com','FEMALE',_binary '',_binary '','마법과전투012','MEMBER','234012'),(35,NULL,NULL,'1991-09-10','567345@example.com','MALE',_binary '',_binary '','발사체345','MEMBER','567345'),(36,NULL,NULL,'1996-12-27','890678@example.com','FEMALE',_binary '',_binary '','빛나는일주민678','MEMBER','890678'),(38,'2024-06-26 01:14:22.098949','2024-06-26 01:14:22.098949','2024-06-25','gamin3182@gmail.com','FEMALE',_binary '',_binary '','이쁜이','MEMBER','c1be880b-e783-488b-a132-36b3dbe3c860'),(59,'2024-06-27 01:40:14.664120','2024-06-27 01:40:14.664120','1998-03-09','chojunho9803@gmail.com','MALE',_binary '',_binary '','조준호','MEMBER','900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(60,'2024-06-27 06:32:50.240560','2024-06-27 06:32:50.240560','2001-07-29','dkdjfuqek5@naver.com','FEMALE',_binary '',_binary '','신다혜','MEMBER','44a534fe-fe5f-4198-9f12-d1186117bfd7'),(61,'2024-06-27 06:36:14.340386','2024-06-27 06:36:14.340386','2024-05-28','dahye13@naver.com','MALE',_binary '',_binary '\0','dahye3e','MEMBER','0f98bbd9-c8be-4538-af28-69b539c15f15'),(62,'2024-06-28 07:22:50.654892','2024-06-28 07:22:50.654892','2014-06-10','iseonju745@gmail.com','FEMALE',_binary '',_binary '','하빙','MEMBER','dec2b368-7edd-489d-b4b6-374648a787d1'),(63,'2024-06-28 07:38:56.405686','2024-06-28 07:38:56.405686','1997-11-02','kjinabc@naver.com','MALE',_binary '',_binary '','김현진','MEMBER','6612f181-3e02-4f23-b789-192df91a0d97'),(64,'2024-06-29 21:12:22.438844','2024-06-29 21:12:22.438844','2001-07-30','sindahye730@naver.com','FEMALE',_binary '',_binary '\0','2뿌이','MEMBER','d53fae28-ff39-4a9f-b799-c2f12ff526cf'),(65,'2024-06-29 21:22:43.151456','2024-06-29 21:22:43.151456','2001-07-30','sindahye731@naver.com','FEMALE',_binary '',_binary '\0','2뿌이2','MEMBER','3d6d8d56-3686-4466-9620-ceee9732fb36'),(66,'2024-06-29 21:26:30.098818','2024-06-29 21:26:30.098818','2001-07-30','sindahye732@naver.com','FEMALE',_binary '',_binary '\0','2뿌이3','MEMBER','ca6d16bd-8d90-4c00-a994-734f68c079de'),(67,'2024-06-30 09:11:38.826278','2024-06-30 09:11:38.826278','2001-07-30','sindahye733@naver.com','FEMALE',_binary '',_binary '\0','2뿌이4','MEMBER','8c93bbeb-a6c5-4188-8340-626e0dffa7e0'),(68,'2024-06-30 09:42:54.938667','2024-06-30 09:42:54.938667','2001-07-30','sindahye734@naver.com','FEMALE',_binary '',_binary '\0','2뿌이5','MEMBER','9f21e9fa-cda7-4048-bd5b-fa1366c7953f'),(69,'2024-06-30 09:43:37.129509','2024-06-30 09:43:37.129509','2001-07-30','sindahye735@naver.com','FEMALE',_binary '',_binary '\0','2뿌이6','MEMBER','aefaa1ff-dac2-420e-98fe-36eba5104ac3'),(70,'2024-07-01 02:41:19.493604','2024-07-01 02:41:19.493604','2024-05-28','sindahye7312@naver.com','MALE',_binary '',_binary '\0','dahye500','MEMBER','90afa2c6-622e-4224-b43e-1552f3da2953'),(71,'2024-07-01 05:38:27.055195','2024-07-01 05:38:27.055195','1998-05-27','lotus0028@kakao.com','FEMALE',_binary '',_binary '','희영','MEMBER','a2d2a621-625e-4c2b-8537-aa61c8b942fd');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03  9:39:00
