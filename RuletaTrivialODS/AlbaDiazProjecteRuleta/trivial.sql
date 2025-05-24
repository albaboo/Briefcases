CREATE DATABASE  IF NOT EXISTS `trivial` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trivial`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: trivial
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `partides`
--

DROP TABLE IF EXISTS `partides`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partides` (
  `idpartides` int(11) NOT NULL AUTO_INCREMENT,
  `descripcio` varchar(200) NOT NULL,
  PRIMARY KEY (`idpartides`),
  UNIQUE KEY `idpartides_UNIQUE` (`idpartides`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partides`
--

LOCK TABLES `partides` WRITE;
/*!40000 ALTER TABLE `partides` DISABLE KEYS */;
/*!40000 ALTER TABLE `partides` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partides_usuaris`
--

DROP TABLE IF EXISTS `partides_usuaris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partides_usuaris` (
  `idpartides_usuaris` int(11) NOT NULL AUTO_INCREMENT,
  `idusuari` int(11) DEFAULT NULL,
  `idpartida` int(11) DEFAULT NULL,
  `formatges` text,
  PRIMARY KEY (`idpartides_usuaris`),
  UNIQUE KEY `idpartides_usuaris_UNIQUE` (`idpartides_usuaris`),
  KEY `idusuari_idx` (`idusuari`),
  KEY `idpartides_idx` (`idpartida`),
  CONSTRAINT `idpartides` FOREIGN KEY (`idpartida`) REFERENCES `partides` (`idpartides`),
  CONSTRAINT `idusuari` FOREIGN KEY (`idusuari`) REFERENCES `usuaris_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partides_usuaris`
--

LOCK TABLES `partides_usuaris` WRITE;
/*!40000 ALTER TABLE `partides_usuaris` DISABLE KEYS */;
/*!40000 ALTER TABLE `partides_usuaris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntes`
--

DROP TABLE IF EXISTS `preguntes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntes` (
  `idpreguntes` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcio` tinytext NOT NULL,
  `tema` int(11) NOT NULL,
  `resposta1` tinytext,
  `resposta2` tinytext,
  `resposta3` tinytext,
  `resposta4` tinytext,
  `solucio` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpreguntes`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntes`
--

LOCK TABLES `preguntes` WRITE;
/*!40000 ALTER TABLE `preguntes` DISABLE KEYS */;
INSERT INTO `preguntes` VALUES (1,'Quin és l\'ODS1?',1,'Fam zero','Educació de qualitat',' Salut i benestar','Fi de la pobresa',4),(2,'Quin és l\'ODS2?',1,'Fam zero','Educació de qualitat',' Salut i benestar','Fi de la pobresa',1),(3,'Quin és l\'ODS3?',1,'Fam zero','Educació de qualitat',' Salut i benestar','Fi de la pobresa',3),(4,'Quin és l\'ODS4?',1,'Fam zero','Educació de qualitat',' Salut i benestar','Fi de la pobresa',2),(5,'Quin és l\'ODS5?',1,'Energia assequible i no contaminant','Aigua neta i sanejament','Treball decent i creixement econòmic','Igualtat de gènere',4),(6,'Quin és l\'ODS6?',1,'Energia assequible i no contaminant','Aigua neta i sanejament','Treball decent i creixement econòmic','Igualtat de gènere',2),(7,'Quin és l\'ODS7?',1,'Energia assequible i no contaminant','Aigua neta i sanejament','Treball decent i creixement econòmic','Igualtat de gènere',1),(8,'Quin és l\'ODS8?',1,'Energia assequible i no contaminant','Aigua neta i sanejament','Treball decent i creixement econòmic','Igualtat de gènere',3),(9,'Quin és l\'ODS9?',1,'Reducció de les desigualtats','Ciutats i comunitats sostenibles','Indústria, innovació i infraestructura','Acció pel clima',3),(10,'Quin és l\'ODS12?',1,'Consum i producció responsables','Vida d\'ecosistemes terrestres','Aliances per assolir els objectius','Vida submarina',1),(11,'Quin és un aspecte clau de les aliances i sinèrgies públic-privat?',2,'Foment de la tecnologia','Foment de la Innovació','Responsabilitat econòmica corporativa','Programes de formació',2),(12,'Quina és una caràcterística de l\'economica circular',2,'Compromís amb la Sostenibilitat','Inclusió Social i Igualtat d\'Oportunitats','Reciclatge i Valorització de Residus','Implementació de Projectes Conjunts',3),(13,'Quin és un compromís social?',2,'Col·laboració en la presa de decisions','Col·laboració amb la Comunitat','Estratègies de Procés Eficients','Reciclatge i Valorització de Residus',2),(14,'Quin és un repte social?',2,'Desplaçament de Poblacions','Contaminació Plàstica',' Desafiaments Urbans','Escassetat d\'Aigua',1),(15,'Quin és un repte ambiental?',2,'Desigualtats Socials','Pobresa i Inclusió Social','Escassetat d\'Aigua','Desplaçament de Poblacions',3),(16,'Quin és un impacte en el sector productiu/industrial?',2,'Oci en la qualitat de vida de les persones','Accés equitatiu a la sanitat','La ocupació i les condicions laborals','Garantir l\'equitat i l\'accés als serveis',3),(17,'Quin no és un repte en el canvi en la crisi climàtica?',2,'Enfortir la resilència','Millorar l\'educació','Promoure l\'economia baixa en carboni','Pèrdua de biodiversitat',4),(18,'Quin és un ODS important en el sector agrícola?',3,'1','2','3','4',2),(19,'Quin és un ODS important en el sector manufacturer?',3,'8','9','10','11',2),(20,'Quin és un ODS important en el sector energètic?',3,'8','9','10','11',2),(21,'Quin és un ODS important en el sector turístic?',3,'8','9','10','11',1),(22,'Quin és un ODS important en el sector tecnològic?',3,'1','2','3','4',4),(23,'Quin és un ODS important en el sector cura i atenció a les persones?',3,'8','9','10','11',3),(24,'Quin no és un risc associat als ODS',3,'Inacció o progrés insuficient','Desigualtats exacerbades','Interessos contraposats','Accés a finançament sostenible',4),(25,'Quina no és una oportunitat associada als ODS',3,'Inacció o progrés insuficient','Col·laboració i innovació','Millora de la reputació i la marca','Accés a finançament sostenible',1),(26,'Quina no és una mesura orientada a enfortir els aspectes de sostenibilitat i contribució de les actituds personals en la cosecució dels ODS?',3,'Educació i Conscienciació','Canvis en el Consum','Estalvi d\'Energia i Aigua','Accés a finançament sostenible',4),(27,'Quina és una activitat/s sostenible ambiental?',5,'Diversitat i inclusió','Energia renovables','Responsabilitat Financera','Innovació i millora continua',2),(28,'Quina és una activitat/s sostenible social?',5,'Diversitat i inclusió','Energia renovables','Responsabilitat Financera','Innovació i millora continua',1),(29,'Quina és una activitat/s sostenible econòmica?',5,'Diversitat i inclusió','Energia renovables','Responsabilitat Financera','Reducció d\'emissions',3),(30,'Quin són els objectius d\'una economica circular?',5,'Justicia Social','Energia Renovable','Economia de serveis','Sostenibilitat ambientat',3),(31,'Quin són els objectius d\'una economica verda?',5,'Justicia Social','Cicle tancat','Economia de serveis','Disseny per a la longevitat',1),(32,'Quin no és un pas d\'un pla d\'acció basat en economia verda i circular?',5,'Anàlisi de la situació actual','Definició d\'objectius','Implementació de canvis','Pluja d\'idees',4),(33,'Quina no és una clau en el model de negoci d\'economia verda i circular?',4,'Compromís amb la Reducció d\'Impacte Ambiental','Auditories i certificacions','Implicació dels Clients','Innovació Contínua',2),(34,'Quin no és un aspecte clau per a les empreses indústrials',4,'Simbiosi Industrial','Producte com a Servei','Energia Renovable','Producció Circular',2),(35,'Quin no és un aspecte clau per a les empreses de serveis?',4,'Serveis Basats en la Eficiència de Recursos','Producte com a Servei','Energia Renovable','Reciclatge i Reutilització en el Lloc de Treball',3),(36,'Quin és un model de negoci destacat?',4,'Manufacture-Service System, MSS','Agricultura i Alimentació intensiva','Construcció i Edificació amb vidre','Cradle to Cradle',4),(37,'Què signifiquen les sigles ASG?',5,'Ambiental, Social i de Governança','Ambiental, Social i de Govern','Ambiental, Societat i de Governança','Ambiental, Social i de Guany',1),(38,'Què significa KPI?',4,'Indicadors de producció clau','Indicador de serveis clau','Indicadors claus de rendiment','Indicadors de producció de bens clau',3),(39,'Quin és un avantatge d\'un pla de sostenibilitat',6,'Millorar impacte ambiental','Innovació en regulacions i noramtives','Augmenta la productivitat i redueix costos','Millora d\'inversions',3),(40,'Quin no és un component clau en un pla de sostenibilitat',6,'Anàlisi de la situació actual','Indicadors de rendiment','Avaluació i millora continua','Estratègia i accions',1),(41,'Quins és un pas d\'un informe de sostenibilitat?',6,'Anàlisi de la situació actual','Identificació dels grups d\'interès','Pluja d\'idees','Estratègia i accions',2);
/*!40000 ALTER TABLE `preguntes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuaris_app`
--

DROP TABLE IF EXISTS `usuaris_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuaris_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `punts` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuaris_app`
--

LOCK TABLES `usuaris_app` WRITE;
/*!40000 ALTER TABLE `usuaris_app` DISABLE KEYS */;
INSERT INTO `usuaris_app` VALUES (1,'marc@ies-sabadell.cat','tetris_123',0),(2,'hector@ies-sabadell.cat','caillou_123',0),(3,'gregorio@ies-sabadell.cat','escacs_123',0),(4,'david@ies-sabadell.cat','pescamines_123',0),(5,'antonio@ies-sabadell.cat','animacio_123',0),(6,'nico@ies-sabadell.cat','bicicleta_123',0),(7,'dani@ies-sabadell.cat','sistemes_123',0);
/*!40000 ALTER TABLE `usuaris_app` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 11:10:56
