CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emprestimo` (
  `id_usuario` int DEFAULT NULL,
  `id_exemplar` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livro` int NOT NULL,
  `data_emprestimo` date NOT NULL,
  `data_entrega` date NOT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_livro` (`id_livro`),
  KEY `emprestimo_ibfk_2` (`id_exemplar`),
  CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`id_exemplar`) REFERENCES `exemplar` (`id`),
  CONSTRAINT `emprestimo_ibfk_3` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplar`
--

DROP TABLE IF EXISTS `exemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exemplar` (
  `id_livro` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `disponivel` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `id_livro` (`id_livro`),
  CONSTRAINT `exemplar_ibfk_1` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplar`
--

LOCK TABLES `exemplar` WRITE;
/*!40000 ALTER TABLE `exemplar` DISABLE KEYS */;
INSERT INTO `exemplar` VALUES (1,1,1),(1,2,1),(1,3,1),(2,4,1),(2,5,1),(3,6,1),(3,7,1),(3,8,1),(3,9,1),(4,10,1),(4,11,1),(5,12,1),(5,13,1),(5,14,1),(6,15,1),(6,16,1),(6,17,1),(6,18,1),(6,19,1),(7,20,1),(7,21,1),(7,22,1),(7,23,1),(7,24,1),(8,25,1),(8,26,1),(9,27,1),(9,28,1),(9,29,1),(9,30,1),(9,31,1),(10,32,1),(10,33,1),(11,34,1),(11,35,1),(11,36,1),(11,37,1),(11,38,1),(12,39,1),(12,40,1),(13,41,1),(13,42,1),(13,43,1),(13,44,1),(13,45,1),(14,46,1),(14,47,1),(14,48,1),(15,49,1),(15,50,1),(15,51,1),(16,52,1),(16,53,1),(17,54,1),(17,55,1),(17,56,1),(18,57,1),(18,58,1),(18,59,1),(18,60,1),(18,61,1),(19,62,1),(19,63,1),(20,64,1),(20,65,1),(20,66,1),(20,67,1),(20,68,1);
/*!40000 ALTER TABLE `exemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro` (
  `titulo` varchar(100) DEFAULT NULL,
  `autor` varchar(60) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `data_publicacao` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES ('A Hora da Estrela','Clarice Lispector',1,'1977-10-01'),('O Problema dos Três Corpos','Liu Cixin',2,'2006-01-01'),('A Floresta Sombria','Liu Cixin',3,'2008-01-01'),('O Fim da Morte','Liu Cixin',4,'2010-01-01'),('Memórias Póstumas de Brás Cubas','Machado de Assis',5,'1881-01-01'),('Dom Casmurro','Machado de Assis',6,'1899-01-01'),('1984','George Orwell',7,'1949-06-08'),('Crime e Castigo','Fiódor Dostoiévski',8,'1866-01-01'),('O Senhor dos Anéis','J.R.R. Tolkien',9,'1954-07-29'),('Neuromancer','William Gibson',10,'1984-07-01'),('Duna','Frank Herbert',11,'1965-08-01'),('Fundação','Isaac Asimov',12,'1951-06-01'),('Admirável Mundo Novo','Aldous Huxley',13,'1932-01-01'),('Fahrenheit 451','Ray Bradbury',14,'1953-10-19'),('O Nome do Vento','Patrick Rothfuss',15,'2007-03-27'),('Drácula','Bram Stoker',16,'1897-05-26'),('Frankenstein','Mary Shelley',17,'1818-01-01'),('O Hobbit','J.R.R. Tolkien',18,'1937-09-21'),('Ensaio sobre a Cegueira','José Saramago',19,'1995-01-01'),('A Revolução dos Bichos','George Orwell',20,'1945-08-17');
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('aluno','professor') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-10 14:03:46
