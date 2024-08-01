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
-- Table structure for table `profile_image`
--

DROP TABLE IF EXISTS `profile_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `main` bit(1) NOT NULL,
  `profile_image_url` varchar(255) NOT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_image`
--

LOCK TABLES `profile_image` WRITE;
/*!40000 ALTER TABLE `profile_image` DISABLE KEYS */;
INSERT INTO `profile_image` VALUES (2,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%9A%A9%EC%82%AC123new.webp','abc123'),(3,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%A0%84%EC%82%AC456.webp','def456'),(4,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%A7%88%EB%B2%95%EC%82%AC789.webp','ghi789'),(5,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%8F%84%EC%A0%81012.webp','jkl012'),(6,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%B9%98%EB%A3%8C%EA%B8%B0%EB%8A%A5%EC%82%AC345.webp','mno345'),(7,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EA%B6%81%EC%88%98678.webp','pqr678'),(8,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%82%AC%EC%84%9C%ED%95%A8901.webp','stu901'),(9,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%A7%88%EB%B2%95%EC%82%AC234.webp','vwx234'),(10,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%8F%AC%EC%9A%A9.png','yza567'),(11,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%86%8C%EC%9C%84890.png','bcd890'),(12,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%8F%84%EC%A0%81123.webp','efg123'),(13,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%9E%90%EC%9C%A0%EB%A1%9C%EC%9A%B4%EA%B6%81%EC%88%98.webp','hij456'),(14,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%99%95789.webp','klm789'),(15,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B3%84%EC%82%AC%ED%83%95012.webp','nop012'),(16,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%83%B1%ED%81%AC345.png','qrs345'),(17,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%A7%88%EB%B2%95%EC%82%AC%EC%84%9C678.png','tuv678'),(18,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%9E%A0%EB%B3%B5%ED%95%9C%EB%A7%88%EB%B2%95%EC%82%AC901.png','wxy901'),(19,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%BD%94%EC%8A%A4%ED%94%84%EB%A0%88234.png','zab234'),(20,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%8C%8C%ED%8B%B0%EC%8B%9C%EC%BB%A4%EB%A8%BC%EA%B6%81%EC%88%98.png','cde567'),(21,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EA%B4%91%ED%9C%98%EC%9D%98%EC%9A%94%EC%A0%95890.png','fgh890'),(22,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%86%8C%EC%9C%84123.webp','ijk123'),(23,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EA%B3%A0%EB%AC%B4%EC%98%A4%EB%A6%AC%EA%B4%91%EC%A0%84%EC%82%AC456.webp','lmn456'),(24,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B0%98%EC%97%AD%EC%9E%90.png','opq789'),(25,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EA%B0%80%EB%A6%AC%EB%A0%A4%EB%8A%94%EB%A7%88%EB%B2%95%EC%82%AC.png','rst012'),(26,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%86%8C%EC%9C%84%EC%82%BC345.png','uvw345'),(27,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%8F%AD%EC%A3%BD678.png','xyz678'),(28,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%A3%BD%EC%9D%8C%EC%9D%B4%ED%95%98%EB%8A%94%EC%9E%AC%EA%B0%84.png','123901'),(29,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B0%98%EC%97%AD%EC%9E%90234.png','456234'),(30,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%82%98%EB%9D%BC%EC%A7%80%ED%82%A8%EC%95%84%EB%A0%88%EC%8A%A4.png','789567'),(31,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B0%9C%EC%82%AC%EC%B2%B4890.png','012890'),(32,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%A7%88%EB%B2%95%EC%9D%98%EC%97%AC%EC%8B%A0123.png','345123'),(33,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EA%B3%A0%EB%AC%B4%EC%98%A4%EB%A6%AC%EB%8C%80%EC%9E%A5456.png','678456'),(34,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B3%91%EC%82%AC789.png','901789'),(35,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%A7%88%EB%B2%95%EA%B3%BC%EC%A0%84%ED%88%AC012.png','234012'),(36,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B0%9C%EC%82%AC%EC%B2%B4345.png','567345'),(37,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%B9%9B%EB%82%98%EB%8A%94%EC%9D%BC%EC%A3%BC%EB%AF%BC678.png','890678'),(38,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%95%98%EB%8A%98.png','c1be880b-e783-488b-a132-36b3dbe3c860'),(40,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg','900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(41,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg','44a534fe-fe5f-4198-9f12-d1186117bfd7'),(42,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg','dec2b368-7edd-489d-b4b6-374648a787d1'),(43,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%ED%98%84%EC%A7%842.jpg','6612f181-3e02-4f23-b789-192df91a0d97'),(44,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/IMG_9902-ss_1719768585456.jpg','6612f181-3e02-4f23-b789-192df91a0d97'),(45,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EB%8B%A4%ED%98%9C.jpg','44a534fe-fe5f-4198-9f12-d1186117bfd7'),(46,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/DummyProfile/%EC%A4%80%ED%98%B8.jpg','900f15ca-3bf8-46a2-9354-29f77a9d1fc0'),(47,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg','a2d2a621-625e-4c2b-8537-aa61c8b942fd'),(48,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/image 1685_1718847928258_1719814889473.png','a2d2a621-625e-4c2b-8537-aa61c8b942fd'),(49,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/image 1685_1718847928258_1719815337448.png','a2d2a621-625e-4c2b-8537-aa61c8b942fd'),(50,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/image 1685_1718847928258_1719815400546.png','a2d2a621-625e-4c2b-8537-aa61c8b942fd'),(51,_binary '\0','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/배경화면_1719815463485.png','a2d2a621-625e-4c2b-8537-aa61c8b942fd'),(52,_binary '','https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/image 1685_1718847928258_1719815475504.png','a2d2a621-625e-4c2b-8537-aa61c8b942fd');
/*!40000 ALTER TABLE `profile_image` ENABLE KEYS */;
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
