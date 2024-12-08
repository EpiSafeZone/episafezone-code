-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: episafezone
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `crisis`
--

DROP TABLE IF EXISTS `crisis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crisis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  `emergency` bit(1) DEFAULT NULL,
  `manifestation` int NOT NULL,
  `patient` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `suffered_by_idx` (`patient`),
  KEY `of_type_idx` (`manifestation`),
  CONSTRAINT `occurred_to` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `of_type` FOREIGN KEY (`manifestation`) REFERENCES `manifestation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crisis`
--

LOCK TABLES `crisis` WRITE;
/*!40000 ALTER TABLE `crisis` DISABLE KEYS */;
INSERT INTO `crisis` VALUES (1,3,'2024-10-28',NULL,NULL,NULL,1,1),(2,3,'2024-10-23',NULL,NULL,NULL,2,1),(3,4,'2024-09-17',NULL,NULL,NULL,2,1),(4,4,'2024-09-05',NULL,NULL,NULL,1,1),(5,2,'2024-09-07',NULL,NULL,NULL,1,1),(6,2,'2024-09-21',NULL,NULL,NULL,1,2),(7,2,'2024-09-03',NULL,NULL,NULL,2,2),(8,5,'2024-10-26',NULL,NULL,NULL,1,2),(9,5,'2024-10-09',NULL,NULL,NULL,2,2);
/*!40000 ALTER TABLE `crisis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` int NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `used_by_idx` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `has_manifestation`
--

DROP TABLE IF EXISTS `has_manifestation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `has_manifestation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient` int NOT NULL,
  `manifestation` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `suffered_by_idx` (`patient`),
  KEY `suffers_idx` (`manifestation`),
  CONSTRAINT `suffered_by` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `suffers` FOREIGN KEY (`manifestation`) REFERENCES `manifestation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `has_manifestation`
--

LOCK TABLES `has_manifestation` WRITE;
/*!40000 ALTER TABLE `has_manifestation` DISABLE KEYS */;
INSERT INTO `has_manifestation` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2);
/*!40000 ALTER TABLE `has_manifestation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manifestation`
--

DROP TABLE IF EXISTS `manifestation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manifestation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manifestation`
--

LOCK TABLES `manifestation` WRITE;
/*!40000 ALTER TABLE `manifestation` DISABLE KEYS */;
INSERT INTO `manifestation` VALUES (1,'tonico-clonica','Convulsiones'),(2,'ausencias','El paciente se que en blanco mirando un punto fijo y no responde'),(3,'nueva manifestacion','esto es una descripción'),(4,'nueva','nueva manifestacion');
/*!40000 ALTER TABLE `manifestation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medication` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dosis` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `alarm` bit(1) DEFAULT NULL,
  `patient_medicated` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `medication_of_idx` (`patient_medicated`),
  CONSTRAINT `medication_of` FOREIGN KEY (`patient_medicated`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'ibuprofeno',400,'mg',_binary '\0',1),(2,'paracetamol',500,'mg',_binary '\0',1),(3,'eferalgan',500,'mg',_binary '\0',1),(4,'dalsy',50,'ml',_binary '\0',1);
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notify_hours`
--

DROP TABLE IF EXISTS `notify_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notify_hours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tutor` int NOT NULL,
  `patient` int NOT NULL,
  `notify_from` time DEFAULT NULL,
  `notify_to` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notified_tutor_idx` (`tutor`),
  KEY `notify_from_patient_idx` (`patient`),
  CONSTRAINT `notified_tutor` FOREIGN KEY (`tutor`) REFERENCES `tutor` (`id`),
  CONSTRAINT `notify_from_patient` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notify_hours`
--

LOCK TABLES `notify_hours` WRITE;
/*!40000 ALTER TABLE `notify_hours` DISABLE KEYS */;
INSERT INTO `notify_hours` VALUES (1,1,1,NULL,NULL),(2,1,2,'08:00:00','18:00:00'),(3,2,1,NULL,NULL),(4,3,2,NULL,NULL),(8,3,1,NULL,NULL);
/*!40000 ALTER TABLE `notify_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `height` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `birthdate` datetime(6) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Pepe','Garcia',150,50,NULL,NULL,NULL),(2,'Cesar','Gimeno',143,42,NULL,NULL,NULL);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remainder`
--

DROP TABLE IF EXISTS `remainder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remainder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time_of_reminder` int DEFAULT NULL,
  `next_alarm` datetime DEFAULT NULL,
  `medication` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `remider_of_idx` (`medication`),
  CONSTRAINT `remider_of` FOREIGN KEY (`medication`) REFERENCES `medication` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remainder`
--

LOCK TABLES `remainder` WRITE;
/*!40000 ALTER TABLE `remainder` DISABLE KEYS */;
/*!40000 ALTER TABLE `remainder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shared_with`
--

DROP TABLE IF EXISTS `shared_with`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shared_with` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tutor_sharing` int NOT NULL,
  `tutor_receiving` int NOT NULL,
  `patient` int NOT NULL,
  `register_crisis_permision` tinyint DEFAULT '1',
  `profile_permision` tinyint DEFAULT '0',
  `medicine_permision` tinyint DEFAULT '0',
  `tutor_permision` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `shered_by_idx` (`tutor_sharing`),
  KEY `shared_to_idx` (`tutor_receiving`),
  KEY `patient_shared_idx` (`patient`),
  CONSTRAINT `patient_shared` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shared_to` FOREIGN KEY (`tutor_receiving`) REFERENCES `tutor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shered_by` FOREIGN KEY (`tutor_sharing`) REFERENCES `tutor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shared_with`
--

LOCK TABLES `shared_with` WRITE;
/*!40000 ALTER TABLE `shared_with` DISABLE KEYS */;
INSERT INTO `shared_with` VALUES (1,3,1,2,1,0,0,0),(2,1,2,1,1,0,0,1),(7,1,3,1,1,1,1,1);
/*!40000 ALTER TABLE `shared_with` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(15) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `notifications` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES (1,'Mario','Garcia','MG','a@a','1',1),(2,'Luisa','Garcia','Lu','b@b','luisa',1),(3,'Antonio','Pardo','Antopato','Antopato@gmail.com','Antopato',0);
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor_of`
--

DROP TABLE IF EXISTS `tutor_of`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor_of` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tutor` int NOT NULL,
  `patient` int NOT NULL,
  `master` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tutored_by_idx` (`tutor`),
  KEY `encharge_of_idx` (`patient`),
  CONSTRAINT `encharge_of` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tutored_by` FOREIGN KEY (`tutor`) REFERENCES `tutor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor_of`
--

LOCK TABLES `tutor_of` WRITE;
/*!40000 ALTER TABLE `tutor_of` DISABLE KEYS */;
INSERT INTO `tutor_of` VALUES (1,1,1,_binary ''),(2,2,1,_binary '\0'),(3,3,2,_binary ''),(9,3,1,_binary '\0');
/*!40000 ALTER TABLE `tutor_of` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-08  2:06:09
