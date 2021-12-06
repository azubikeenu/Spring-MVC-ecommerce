-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eshop
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  KEY `FK709eickf3kc0dujx3ub9i7btf` (`user_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK709eickf3kc0dujx3ub9i7btf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (8,'S704GCjm61SxsA48JvRLF0LVcqNaJc','Affordable sports wears ',_binary '','Sports wear','sports-wear'),(9,'iujCzqKh4H2XH9JETD5lOlF4D3VQ5J','Affordable shirts for men',_binary '','Shirts','shirts'),(10,'uHFh9hnZK6CYKIGBSKQFJK3h5JJ6i6','Classic denim jeans ',_binary '','Jeans','jeans'),(11,'C26Ftopdrod7Kzs7PJqJATaJgSecj6','Affordable shoes for men ',_binary '','Shoes','shoes'),(12,'KncS8IAWwsi8MF5ASXBt8eXr8qt4Mp','Cheap accessories for men ',_binary '','Accessories','accessories'),(13,'f22BNmMqvIYWFXMaSSAU6HNzNCQn6f','Affordable Socks',_binary '','Socks','socks');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `sub_total` double NOT NULL,
  `unit_price` double NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (3,1,25,25,3,18),(14,2,91.8,45.9,13,16),(15,2,50,25,14,18),(16,3,75,25,15,18),(17,7,321.3,45.9,16,16),(18,7,105,15,17,24),(22,1,25,25,20,19),(23,1,15,15,20,24),(24,1,45.9,45.9,20,16),(25,1,45.9,45.9,21,16),(27,2,50,25,23,19),(29,4,227.2,56.8,25,22),(30,2,50,25,25,18),(31,7,105,15,26,37);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `delivery_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `order_time` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` bit(1) NOT NULL,
  `tax` double NOT NULL,
  `total` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `cancelled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-05 21:56:49.649000','enuazubike88@gmail.com','Azubike','Enu','ECMV36jm4RwtACJGuuOwwG7obsjsU7','PROCESSING','2021-12-03 21:56:49.649000','DEBIT_CARD',_binary '',12,47,27,0),(13,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 02:51:31.172000','cruz@gmail.com','Santa','Cruz','I4yRVgpM2EekVjhuRsCRHydMdqMU5k','PROCESSING','2021-12-05 02:51:31.172000','DEBIT_CARD',_binary '',12,113.8,32,0),(14,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 03:13:35.941000','stanley_green@gmail.com','Stanley','Green','Sce7DZXNpYjrULiv2AR5Dgs3PjQGua','DELIVERED','2021-12-05 03:13:35.941000','COD',_binary '',12,72,33,0),(15,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 03:40:25.355000','cruz@gmail.com','Santa','Cruz','wUsH35QXkJXMwBs0RXGSbdmQTMgV87','CANCELLED','2021-12-05 03:40:25.355000','COD',_binary '\0',12,97,32,1),(16,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 04:03:54.775000','cruz@gmail.com','Santa','Cruz','4L26nfvbPEWJxVTK4qOtdmjneHyvma','CANCELLED','2021-12-05 04:03:54.776000','COD',_binary '\0',12,343.3,32,1),(17,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 04:05:36.286000','enuazubike88@gmail.com','Azubike','Enu','joanCTvodPSAmvVo22316O9Er8hzy1','PROCESSING','2021-12-05 04:05:36.286000','COD',_binary '\0',12,127,27,0),(20,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 04:30:57.237000','cruz@gmail.com','Santaboy','Cruz','38tQ0FEJ6TdmokaIhjooRafm1wF1xy','PROCESSING','2021-12-05 04:30:57.237000','COD',_binary '\0',12,107.9,32,0),(21,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 14:12:34.166000','maham@gmail.com','Maham','Manja','ebukLbVQY5LCHixNo2b1FtGk5JciKB','DELIVERED','2021-12-05 14:12:34.166000','DEBIT_CARD',_binary '',12,67.9,35,0),(23,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-07 14:25:47.948000','maham@gmail.com','Maham','Manja','FlWa3cPiAETdxOEi2zvpzJvU3JYEvA','PROCESSING','2021-12-05 14:25:47.948000','DEBIT_CARD',_binary '',12,72,35,0),(25,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-08 12:19:09.750000','example@gmail.com','Enu','James','h4LdlDAlqGTjrI4kgwSpGoUGI7snko','DELIVERED','2021-12-06 12:19:09.750000','DEBIT_CARD',_binary '',12,299.2,36,0),(26,'7th avenue V close Block 3 Flat 8 FESTAC','2021-12-08 12:26:57.380000','enuazubike88@gmail.com','Azubike','Enu','TPaRXJ7d1qE0XBZ2OA3h3g8bgk44gw','CANCELLED','2021-12-06 12:26:57.380000','COD',_binary '\0',12,127,27,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `image` varchar(255) DEFAULT 'default.png',
  `in_stock` tinyint(1) DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT '1',
  `alias` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`),
  UNIQUE KEY `UK_daoicienexsx3it5e4xs3pgg` (`product_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (16,'2 In 1 Men\'s Ice Silk Short Sleeve Suit-Blue Gradient',1,'0w8TDVu',1,'2 in 1 men\'s ice silk short sleeve suit-blue gradient','hIVcl8WTQru8srveOXAugT0xss8ssz',8,45.9,5,'2-in-1-men\'s-ice-silk-short-sleeve-suit-blue-gradient'),(18,'Black Gym Wear',1,'IQWxI6pAfC.jpg',1,'Black gym wear','FJFnCUTHeLot3a3Vpcu6Agtkly8apv',8,25,3,'black-gym-wear'),(19,'Black Jogger With White Patch',1,'TJk8NEb9x9.jpg',1,'Black jogger with white patch','BbJZgAOtqyNwtsT0isEmxXk9TmGp50',8,25,7,'black-jogger-with-white-patch'),(20,'Pack Of 2 Joggers Pant (LIGHT GREY & NAVY BLUE)\r\n',1,'38g08PrCbH.jpg',1,'Pack of 2 joggers pant (light grey & navy blue)','DxnaEzQhGELkgrdbPosExGKbq1poPu',8,45.9,7,'pack-of-2-joggers-pant-(light-grey-&-navy-blue)'),(21,'Up And Down Men\'s Casual Sports Suit-White',1,'x87OnM2v9c.jpg',1,'Up and down men\'s casual sports suit-white','Vk3nkLrQ5QfG8VT5eYliTZkMUVh2ZU',8,67,11,'up-and-down-men\'s-casual-sports-suit-white'),(22,'Valnaya 3 In 1 Tshirt, Shorts And Sweatpants',1,'iJcrwvlEm3.jpg',1,'Valnaya 3 in 1 tshirt, shorts and sweatpants','qm1ccl3PTyycMDBfCuK5Jy3Yi9wd9k',8,56.8,4,'valnaya-3-in-1-tshirt,-shorts-and-sweatpants'),(23,'BLACK SWEATSHIRT AND JOGGERS (Anti Social)',1,'FE8LqEu4nX.jpg',1,'Black sweatshirt and joggers (anti social)','JswWh55whwsAW5IIDftzKPSfPATjdY',8,89,6,'black-sweatshirt-and-joggers-(anti-social)'),(24,'Africa Destination Tee',1,'Z1fPA2tlIw.jpg',1,'Africa destination tee','CZU3gJige47xdzhXlKYvnRqiHIibZX',9,15,17,'africa-destination-tee'),(25,'African Mask Cartoon Tee',1,'bu39DMIeXj.jpg',1,'African mask cartoon tee','AF5PEy1lJRd6iPVDigTj0pYgsxbz0g',9,14.35,10,'african-mask-cartoon-tee'),(26,'2in1 Quality Men\'s Plain Jeans Straight Cut',1,'pnvj8GXOZi.jpg',1,'2in1 quality men\'s plain jeans straight cut','ddUAOZX0EfMw8EsFAKc4zQ0Gw5VQTZ',10,34.8,9,'2in1-quality-men\'s-plain-jeans-straight-cut'),(28,'Trendy Men\'s Relaxed Slim Fit Denim Jean-blue',1,'b2LNVnSRLe.jpg',1,'Trendy men\'s relaxed slim fit denim jean-blue','QMGZ20vNuzhVX6qR1Ku1Rv0AXPyyhz',10,34,6,'trendy-men\'s-relaxed-slim-fit-denim-jean-blue'),(29,'Men Sneakers Running Shoes Lace-up Sport Shoes',1,'yazAHVlENQ.jpg',1,'Men sneakers running shoes lace-up sport shoes','MFW1Dl5F8N5UTIYRBFqin37NOFAa9j',11,34,5,'men-sneakers-running-shoes-lace-up-sport-shoes'),(30,'Shark Bottom Tide Shoes Men\'s Personality Sneakers -White',1,'bxoTHPAjpS.jpg',1,'Shark bottom tide shoes men\'s personality sneakers -white','pQDe8bSyoHJnfLyOsIjK4Zr1KergdF',11,340,10,'shark-bottom-tide-shoes-men\'s-personality-sneakers--white'),(31,'Mens Casual High Top Shoes Fashion Sneakers -Khaki\r\n4.4 out of 5',1,'mEHh24cI1W.jpg',1,'Mens casual high top shoes fashion sneakers -khaki 4.4 out of 5','DxZfJ0G5KweipeJ1B7cV7TphX8nZZy',11,250,12,'mens-casual-high-top-shoes-fashion-sneakers--khaki-4.4-out-of-5'),(32,'Unisex Anti Blue Light Protective Computer Screen Glasses',1,'sxBKdoiagv.jpg',1,'Unisex anti blue light protective computer screen glasses','hgDietTtkxABy6drDvPYWasiEX2aYL',12,12,14,'unisex-anti-blue-light-protective-computer-screen-glasses'),(33,'Bamboo Sunglasses',1,'H6I7IJW1AL.jpg',1,'Bamboo sunglasses','2VtMZsp6bIbya7JN7KVUHZmNrBJm38',12,14,12,'bamboo-sunglasses'),(34,'Vintage Square Sunglasses Male UV400 Polarized Lens Sun Glasses(Black)',1,'l6c79lR4YO.jpg',1,'Vintage square sunglasses male uv400 polarized lens sun glasses(black)','BFkrSWSJ2oV4EskQHRTQITETee4xZT',12,45,15,'vintage-square-sunglasses-male-uv400-polarized-lens-sun-glasses(black)'),(37,'Affordable socks',1,'BytZm6YMxF.jpg',1,'Mens socks','h7OIW5SGK3sxx3VDlIhLb4ANLdngFx',13,15,7,'mens-socks');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(150) NOT NULL,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Manages Everything','ADMIN'),(2,'Minimum functionality','USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(120) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (13,'boma@gmail.com','Boma','Ogan','$2a$10$sa2IrWGJ4W9InKsAOSnLWuyfbn1lvuwCLvwGhhRJO.l5E7iFTBdEi','bRyaJB1VV8jisP29Jj2tw8ohiGPu7l',_binary ''),(27,'enuazubike88@gmail.com','Azubike','Enu','$2a$10$4nDBTJqABBRYOTXhizXewelLexgX3D8fZhp4/y/B1E7Ce9vOaNAE2','vPdQNbKPYxvJqc126Zj2qOpbqY5BZW',_binary ''),(28,'test@gmail.com','Test','Tease','$2a$10$ZgsBmc3L8LhLftlIU7Wm6.Snb4bEICNVbp6xbXL5zffTUDII5.rva','4EVi8ZIioJwFAppdmg22y0nemL3waD',_binary ''),(30,'soji@gmail.com','Bamidele','Soji','$2a$10$Ay3KrLpmksgxMIAn2RFG/uIYzzs869kgI.EdR3J2JBeTIWirimr7e','gjLi4n22HjOkFzVR6Km8S2fkTCGBXY',_binary '\0'),(31,'style@gmail.com','Ribon','Richardson','$2a$10$C7sbozjMY70jIa2gAHmkiOsDE.U6gqLq4T1ZwLt2j2GRFTMmFYuu2','LE6Ii7T5jNzAIA4j8FJZLXRBBstaKX',_binary ''),(32,'cruz@gmail.com','Santaboy','Cruz','$2a$10$bqR1uf2dX9O/F6nTlJ3E8ekorJ8CiS.Iush5XkyZYTTgkJ5XB94lC','9U1TnCwnEUpdfJ4ucSauiADTQXFCGw',_binary ''),(33,'stanley_green@gmail.com','Stanley','Green','$2a$10$eTggSecMzPJ4TivfR4vATeKcC3zKbyoF86no.ncsw2BnubOrIemxq','cDInYgD52mHELzVjFH82HoFCgJHzc6',_binary ''),(34,'noah@gmail.com','Noah','Trevor','$2a$10$FQ0EwdVrrcpv3MN4rqCKUes9LnCU5T9osK7H79.TXJhRc56IqQ2BG','9tjaI3OAEqs47DLl8vpTZs1Hlkmqri',_binary '\0'),(35,'maham@gmail.com','Maham','Manja','$2a$10$H516nNhtHn/.gFVl4wBa2uLWYeHfbapmm41zyW3Vi0YcToS1B35J2','Z2sraUka61e0HlqRf9bRrIjSUV7qOK',_binary ''),(36,'example@gmail.com','Enu','James','$2a$10$98gdmef7jM17yq60mVIaROrURlvQUl.rzum2fHP5lFjYVRA1Uw28S','VdWeTYjQRKdheVXCIPJ9yxuOtkz9bQ',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (13,1),(27,1),(13,2),(28,2),(30,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-06 12:59:42
