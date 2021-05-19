-- MariaDB dump 10.17  Distrib 10.5.5-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: trackid
-- ------------------------------------------------------
-- Server version	10.5.5-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion` (
  `UPC` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(200) NOT NULL,
  `AUTORIA` varchar(200) NOT NULL,
  `GENERO` varchar(20) NOT NULL,
  `FECHA_LANZAMIENTO` date NOT NULL,
  `SELLO` varchar(100) NOT NULL,
  `C_EXPLICITO` varchar(20) NOT NULL,
  `DURACION` int(11) NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  `AUDIO` varchar(200) NOT NULL,
  `IMAGEN` varchar(200) NOT NULL,
  PRIMARY KEY (`UPC`),
  UNIQUE KEY `AUDIO_UNIQUE` (`AUDIO`),
  KEY `fk_usuario_idx` (`USUARIO_ID`),
  CONSTRAINT `fk_cancion` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`IDUSUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` VALUES (11,'Mob Ties','Drake','HipHop','2019-09-18','OVO Sound','Si',3,1,'Mob Ties - Drake.mp3','Mob Ties - Drake.jpg'),(13,'The Less I Know The Better','Tame Impala','Pop','1981-09-25','RCA Music Pub','No',3,1,'The Less I Know The Better - Tame Impala.mp3','The Less I Know The Better - Tame Impala.jpg');
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_USUARIO` varchar(20) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `PREGUNTA_SEGURIDAD` varchar(45) NOT NULL,
  `RESPUESTA_SEGURIDAD` varchar(45) NOT NULL,
  PRIMARY KEY (`IDUSUARIO`),
  UNIQUE KEY `NOMBRE_USUARIO_UNIQUE` (`NOMBRE_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ismaelism4','123456789','hola','adios'),(2,'ismaelism5','123456789','pregunta','respuesta'),(3,'ismaelism6','123456789A','hola','adios');
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

-- Dump completed on 2021-05-18 13:14:17
