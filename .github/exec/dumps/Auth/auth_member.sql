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
INSERT INTO `member` VALUES (1,NULL,NULL,'1990-01-01','abc123@example.com','MALE',_binary '',_binary '','용사123','MEMBER','abc123'),(2,NULL,NULL,'1992-03-15','def456@example.com','FEMALE',_binary '',_binary '','전사456','MEMBER','def456'),(3,NULL,NULL,'1985-07-20','ghi789@example.com','MALE',_binary '',_binary '','마법사789','MEMBER','ghi789'),(4,NULL,NULL,'1998-05-10','jkl012@example.com','FEMALE',_binary '',_binary '','도적012','MEMBER','jkl012'),(5,NULL,NULL,'1993-12-25','mno345@example.com','MALE',_binary '',_binary '','치료기능사345','MEMBER','mno345'),(6,NULL,NULL,'1996-09-30','pqr678@example.com','FEMALE',_binary '',_binary '','궁수678','MEMBER','pqr678'),(7,NULL,NULL,'1994-11-18','stu901@example.com','MALE',_binary '',_binary '','사서함901','MEMBER','stu901'),(8,NULL,NULL,'1991-04-05','vwx234@example.com','FEMALE',_binary '',_binary '','마법사234','MEMBER','vwx234'),(9,NULL,NULL,'1997-08-12','yza567@example.com','MALE',_binary '',_binary '','포용567','MEMBER','yza567'),(10,NULL,NULL,'1999-02-28','bcd890@example.com','FEMALE',_binary '',_binary '','소위890','MEMBER','bcd890'),(11,NULL,NULL,'1995-06-22','efg123@example.com','MALE',_binary '',_binary '','도적123','MEMBER','efg123'),(12,NULL,NULL,'1990-10-08','hij456@example.com','FEMALE',_binary '',_binary '','자유로운궁수456','MEMBER','hij456'),(13,NULL,NULL,'1993-03-03','klm789@example.com','MALE',_binary '',_binary '','왕789','MEMBER','klm789'),(14,NULL,NULL,'1998-07-17','nop012@example.com','FEMALE',_binary '',_binary '','별사탕012','MEMBER','nop012'),(15,NULL,NULL,'1992-09-25','qrs345@example.com','MALE',_binary '',_binary '','탱크345','MEMBER','qrs345'),(16,NULL,NULL,'1996-01-12','tuv678@example.com','FEMALE',_binary '',_binary '','마법사서678','MEMBER','tuv678'),(17,NULL,NULL,'1994-04-30','wxy901@example.com','MALE',_binary '',_binary '','잠복한마법사901','MEMBER','wxy901'),(18,NULL,NULL,'1997-11-05','zab234@example.com','FEMALE',_binary '',_binary '','코스프레234','MEMBER','zab234'),(19,NULL,NULL,'1991-12-15','cde567@example.com','MALE',_binary '',_binary '','파티시커먼궁수567','MEMBER','cde567'),(20,NULL,NULL,'1999-08-03','fgh890@example.com','FEMALE',_binary '',_binary '','광휘의요정890','MEMBER','fgh890'),(21,NULL,NULL,'1995-02-20','ijk123@example.com','MALE',_binary '',_binary '','소위123','MEMBER','ijk123'),(22,NULL,NULL,'1992-06-10','lmn456@example.com','FEMALE',_binary '',_binary '','고무오리광전사456','MEMBER','lmn456'),(23,NULL,NULL,'1993-10-28','opq789@example.com','MALE',_binary '',_binary '','반역자789','MEMBER','opq789'),(24,NULL,NULL,'1997-04-15','rst012@example.com','FEMALE',_binary '',_binary '','가리려는마법사012','MEMBER','rst012'),(25,NULL,NULL,'1994-08-22','uvw345@example.com','MALE',_binary '',_binary '','소위삼345','MEMBER','uvw345'),(26,NULL,NULL,'1998-12-01','xyz678@example.com','FEMALE',_binary '',_binary '','폭죽678','MEMBER','xyz678'),(27,NULL,NULL,'1996-02-14','123901@example.com','MALE',_binary '',_binary '','죽음이하는재간901','MEMBER','123901'),(28,NULL,NULL,'1990-07-09','456234@example.com','FEMALE',_binary '',_binary '','반역자234','MEMBER','456234'),(29,NULL,NULL,'1993-11-23','789567@example.com','MALE',_binary '',_binary '','나라지킨아레스567','MEMBER','789567'),(30,NULL,NULL,'1992-01-30','012890@example.com','FEMALE',_binary '',_binary '','발사체890','MEMBER','012890'),(31,NULL,NULL,'1995-05-18','345123@example.com','MALE',_binary '',_binary '','마법의여신123','MEMBER','345123'),(32,NULL,NULL,'1997-09-07','678456@example.com','FEMALE',_binary '',_binary '','고무오리대장456','MEMBER','678456'),(33,NULL,NULL,'1994-03-25','901789@example.com','MALE',_binary '',_binary '','병사789','MEMBER','901789'),(34,NULL,NULL,'1998-06-13','234012@example.com','FEMALE',_binary '',_binary '','마법과전투012','MEMBER','234012'),(35,NULL,NULL,'1991-09-10','567345@example.com','MALE',_binary '',_binary '','발사체345','MEMBER','567345'),(36,NULL,NULL,'1996-12-27','890678@example.com','FEMALE',_binary '',_binary '','빛나는일주민678','MEMBER','890678');
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
