-- MySQL dump 10.13  Distrib 8.0.32, for macos12.6 (x86_64)
--
-- Host: localhost    Database: jetdevs_test
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contents`
--

DROP TABLE IF EXISTS `contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contents` (
                          `content_id` bigint NOT NULL AUTO_INCREMENT,
                          `created_by` varchar(150) NOT NULL,
                          `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `deleted_at` bigint NOT NULL,
                          `last_modified_by` varchar(255) DEFAULT NULL,
                          `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `content` longtext,
                          `file_id` bigint NOT NULL,
                          `header` varchar(200) NOT NULL,
                          `row_no` int NOT NULL,
                          `sheet_id` bigint NOT NULL,
                          PRIMARY KEY (`content_id`),
                          KEY `FKrye02j6ws7f0y1ss2kl2g49u7` (`file_id`),
                          KEY `FK2klp08t3sp4pe5wbvguwkb1d1` (`sheet_id`),
                          CONSTRAINT `FK2klp08t3sp4pe5wbvguwkb1d1` FOREIGN KEY (`sheet_id`) REFERENCES `sheets` (`sheet_id`),
                          CONSTRAINT `FKrye02j6ws7f0y1ss2kl2g49u7` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contents`
--

LOCK TABLES `contents` WRITE;
/*!40000 ALTER TABLE `contents` DISABLE KEYS */;
/*!40000 ALTER TABLE `contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `files` (
                       `file_id` bigint NOT NULL AUTO_INCREMENT,
                       `created_by` varchar(150) NOT NULL,
                       `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `deleted_at` bigint NOT NULL,
                       `last_modified_by` varchar(255) DEFAULT NULL,
                       `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                       `name` varchar(150) DEFAULT NULL,
                       `status` varchar(15) DEFAULT 'UNREVIEWED',
                       `checking_percentage` int DEFAULT NULL,
                       PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
                             `permission_id` bigint NOT NULL AUTO_INCREMENT,
                             `created_by` varchar(150) NOT NULL,
                             `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `deleted_at` bigint NOT NULL,
                             `last_modified_by` varchar(255) DEFAULT NULL,
                             `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `name` varchar(255) NOT NULL,
                             PRIMARY KEY (`permission_id`),
                             UNIQUE KEY `UK_pnvtwliis6p05pn6i3ndjrqt2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'system','2023-11-08 13:13:57',0,NULL,'2023-11-08 13:13:57','READ'),(2,'system','2023-11-08 13:14:14',0,NULL,'2023-11-08 13:14:14','UPLOAD'),(3,'system','2023-11-08 13:15:09',0,NULL,'2023-11-08 13:15:09','DELETE'),(4,'system','2023-11-08 13:16:10',0,NULL,'2023-11-08 13:16:10','REVIEW'),(5,'system','2023-11-08 13:16:52',0,NULL,'2023-11-08 13:16:52','CHECK');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permissions` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_by` varchar(150) NOT NULL,
                                  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `deleted_at` bigint NOT NULL,
                                  `last_modified_by` varchar(255) DEFAULT NULL,
                                  `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `permission_id` bigint NOT NULL,
                                  `role_id` bigint NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKegdk29eiy7mdtefy5c7eirr6e` (`permission_id`),
                                  KEY `FKn5fotdgk8d1xvo8nav9uv3muc` (`role_id`),
                                  CONSTRAINT `FKegdk29eiy7mdtefy5c7eirr6e` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`),
                                  CONSTRAINT `FKn5fotdgk8d1xvo8nav9uv3muc` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',1,1),(2,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',2,1),(3,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',3,1),(4,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',4,1),(5,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',5,1),(6,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',1,2),(7,'system','2023-11-08 13:19:12',0,NULL,'2023-11-08 13:19:12',4,2);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                       `role_id` bigint NOT NULL AUTO_INCREMENT,
                       `created_by` varchar(150) NOT NULL,
                       `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `deleted_at` bigint NOT NULL,
                       `last_modified_by` varchar(255) DEFAULT NULL,
                       `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                       `code` varchar(255) NOT NULL,
                       `description` varchar(255) DEFAULT NULL,
                       PRIMARY KEY (`role_id`),
                       UNIQUE KEY `UK_ch1113horj4qr56f91omojv8` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'system','2023-11-08 13:11:36',0,NULL,'2023-11-08 13:11:36','ADMIN','Admin Role'),(2,'system','2023-11-08 13:12:12',0,NULL,'2023-11-08 13:12:12','USER','User Role');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sheets`
--

DROP TABLE IF EXISTS `sheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sheets` (
                        `sheet_id` bigint NOT NULL AUTO_INCREMENT,
                        `created_by` varchar(150) NOT NULL,
                        `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `deleted_at` bigint NOT NULL,
                        `last_modified_by` varchar(255) DEFAULT NULL,
                        `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        `file_id` bigint NOT NULL,
                        `header` varchar(255) DEFAULT NULL,
                        `name` varchar(150) NOT NULL,
                        PRIMARY KEY (`sheet_id`),
                        KEY `FKkoqyn64qb9an2lmqqdy3lx85x` (`file_id`),
                        CONSTRAINT `FKkoqyn64qb9an2lmqqdy3lx85x` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sheets`
--

LOCK TABLES `sheets` WRITE;
/*!40000 ALTER TABLE `sheets` DISABLE KEYS */;
/*!40000 ALTER TABLE `sheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                            `user_role_id` bigint NOT NULL AUTO_INCREMENT,
                            `created_by` varchar(150) NOT NULL,
                            `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `deleted_at` bigint NOT NULL,
                            `last_modified_by` varchar(255) DEFAULT NULL,
                            `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `role_id` bigint NOT NULL,
                            `user_id` bigint NOT NULL,
                            PRIMARY KEY (`user_role_id`),
                            KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                            KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
                            CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
                            CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'system','2023-11-08 13:24:32',0,NULL,'2023-11-08 13:24:32',1,1),(2,'system','2023-11-08 13:24:32',0,NULL,'2023-11-08 13:24:32',2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                       `user_id` bigint NOT NULL AUTO_INCREMENT,
                       `created_by` varchar(150) NOT NULL,
                       `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `deleted_at` bigint NOT NULL,
                       `last_modified_by` varchar(255) DEFAULT NULL,
                       `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                       `email` varchar(255) NOT NULL,
                       `name` varchar(255) NOT NULL,
                       `password` varchar(255) NOT NULL,
                       `username` varchar(150) NOT NULL,
                       PRIMARY KEY (`user_id`),
                       UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'system','2023-11-08 13:23:55',0,NULL,'2023-11-08 13:23:55','agung_admin@mail.com','Agung','-','agung_admin'),(2,'system','2023-11-08 13:23:55',0,NULL,'2023-11-08 13:23:55','rexy_user@mail.com','Rexy','-','rexy_user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jetdevs_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-09 17:29:45
