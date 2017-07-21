# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.11)
# Database: projetjee
# Generation Time: 2017-04-24 21:22:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table categories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;

INSERT INTO `categories` (`id`, `nom`)
VALUES
	(1,'Homme'),
	(2,'Femme'),
	(8,'Enfant');

/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table commande_produit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `commande_produit`;

CREATE TABLE `commande_produit` (
  `id_commande` int(11) unsigned DEFAULT NULL,
  `id_produit` int(11) unsigned DEFAULT NULL,
  `qnt` int(11) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `commande_produit` WRITE;
/*!40000 ALTER TABLE `commande_produit` DISABLE KEYS */;

INSERT INTO `commande_produit` (`id_commande`, `id_produit`, `qnt`)
VALUES
	(1,1,1),
	(6,8,1),
	(6,14,1);

/*!40000 ALTER TABLE `commande_produit` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table commandes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `commandes`;

CREATE TABLE `commandes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `addr1` varchar(100) DEFAULT NULL,
  `addr2` varchar(100) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  `totale` float DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `commandes` WRITE;
/*!40000 ALTER TABLE `commandes` DISABLE KEYS */;

INSERT INTO `commandes` (`id`, `user_id`, `nom`, `prenom`, `addr1`, `addr2`, `ville`, `notes`, `totale`, `date`)
VALUES
	(2,1,'Kheder','Marwen','Avenue qweqweqw','','ewert','wqertzh',80,'2017-04-23 16:09:13'),
	(3,1,'Tweqe','qwe','qweqw','qewreqw','qewrwq','qwerqw',80,'2017-04-23 16:12:32'),
	(4,1,'qweqw','qwedqw','qweqw','qwe','qewqw','qwerqw',30,'2017-04-23 16:13:19'),
	(5,1,'qwewq','ewrew','werew','wrew','werew','werwerew',30,'2017-04-23 16:14:00'),
	(6,1,'qwewq','erewr','werew','werew','rewrew','werew',30,'2017-04-23 17:15:58'),
	(7,1,'test','ewr','werw','werew','werw','qwerwe',635,'2017-04-24 17:50:49');

/*!40000 ALTER TABLE `commandes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table encheres
# ------------------------------------------------------------

DROP TABLE IF EXISTS `encheres`;

CREATE TABLE `encheres` (
  `id_user` int(11) unsigned NOT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `encheres` WRITE;
/*!40000 ALTER TABLE `encheres` DISABLE KEYS */;

INSERT INTO `encheres` (`id_user`, `id_produit`, `prix`, `date`)
VALUES
	(1,1,10,NULL),
	(1,16,40,'2017-04-23 19:25:41'),
	(0,16,50,'2017-04-23 19:40:05'),
	(0,16,60,'2017-04-23 19:40:10'),
	(0,16,60.1,'2017-04-23 19:59:51'),
	(1,16,100,'2017-04-23 20:03:54'),
	(1,16,100,'2017-04-23 20:03:57'),
	(1,16,100,'2017-04-23 20:04:35'),
	(0,16,100,'2017-04-23 20:04:58'),
	(0,16,100,'2017-04-23 20:05:53'),
	(0,16,102,'2017-04-23 20:06:28'),
	(0,16,103,'2017-04-23 20:11:03'),
	(1,15,332,'2017-04-23 20:57:35'),
	(1,16,104,'2017-04-23 22:11:25'),
	(1,16,105,'2017-04-24 11:04:40'),
	(1,14,302,'2017-04-24 15:50:50'),
	(1,14,303,'2017-04-24 15:51:56');

/*!40000 ALTER TABLE `encheres` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PanierEnchaire
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PanierEnchaire`;

CREATE TABLE `PanierEnchaire` (
  `idUser` int(11) unsigned NOT NULL,
  `idproduit` int(11) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table produits
# ------------------------------------------------------------

DROP TABLE IF EXISTS `produits`;

CREATE TABLE `produits` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `description` varchar(600) DEFAULT NULL,
  `datefin` timestamp NULL DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `image1` varchar(300) DEFAULT NULL,
  `image2` varchar(300) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_sous_cat` int(11) DEFAULT NULL,
  `etat` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `produits` WRITE;
/*!40000 ALTER TABLE `produits` DISABLE KEYS */;

INSERT INTO `produits` (`id`, `nom`, `description`, `datefin`, `prix`, `type`, `image1`, `image2`, `id_user`, `id_sous_cat`, `etat`)
VALUES
	(6,'Pentalen','SDwkmweqlk lkjmqwk jpoiqwjelkqwmlknj opiqwjer q','2019-10-10 00:00:00',20,1,'/uploads/14927068617551.jpg','/uploads/14927068617551-1.jpg',1,1,0),
	(7,'Pentalen Vert','Qwertz wert ertzu','2019-10-10 00:00:00',20,1,'/uploads/14927069759285.jpg','/uploads/14927069759285-1.jpg',1,1,0),
	(8,'tShirt Noir','Polo noir a la mode','2019-10-10 00:00:00',30,2,'/uploads/14927070476044.jpg','/uploads/14927070476044-1.jpg',1,3,0),
	(13,'Robe','weqe','2020-07-10 00:00:00',30,2,'/uploads/14928941637271.jpg','/uploads/14928941637271-1.jpg',5,4,0),
	(14,'Montre','weqqweqwewq','2017-04-24 15:54:00',303,1,'/uploads/14929650208401.jpg','/uploads/14929650208401-1.jpg',1,1,1),
	(15,'qweqwq','weqw','2017-04-29 17:43:00',332,1,'/uploads/14929657863582-1.jpg','/uploads/14929657863582-2.jpg',1,1,1),
	(16,'eqweqw','qwewqe','2017-04-29 17:46:04',32,1,'/uploads/14929659702982.jpg','/uploads/14929659702982-1.jpg',1,2,1),
	(17,'Montre','weqqweqwewq','2018-04-24 15:54:00',300,1,'/uploads/14929650208401.jpg','/uploads/14929650208401-1.jpg',2,1,0),
	(18,'Robe','weqe','2020-07-10 00:00:00',30,2,'/uploads/14928941637271.jpg','/uploads/14928941637271-1.jpg',2,4,0),
	(19,'qweqwq','weqw','2018-04-20 17:43:00',332,1,'/uploads/14929657863582-1.jpg','/uploads/14929657863582-2.jpg',1,1,0),
	(20,'Montre','weqqweqwewq','2018-04-24 15:54:00',300,1,'/uploads/14929650208401.jpg','/uploads/14929650208401-1.jpg',2,1,0),
	(21,'qweqwq','weqw','2018-04-20 17:43:00',332,1,'/uploads/14929657863582-1.jpg','/uploads/14929657863582-2.jpg',1,1,0),
	(22,'Montre','weqqweqwewq','2018-04-24 15:54:00',300,1,'/uploads/14929650208401.jpg','/uploads/14929650208401-1.jpg',2,1,0);

/*!40000 ALTER TABLE `produits` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table souscategories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `souscategories`;

CREATE TABLE `souscategories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `souscategories` WRITE;
/*!40000 ALTER TABLE `souscategories` DISABLE KEYS */;

INSERT INTO `souscategories` (`id`, `nom`, `categorie_id`)
VALUES
	(1,'sous cat1',1),
	(2,'sous cat1',1),
	(3,'cat2222',2),
	(4,'catt 4444',2),
	(5,'test',8);

/*!40000 ALTER TABLE `souscategories` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `nom`, `password`, `email`, `prenom`, `tel`)
VALUES
	(1,'Marwen','test','test@test.com',NULL,NULL),
	(2,'Ali','test','test2@test.com','Khd','20000'),
	(5,'Kh','test','test3@test.com','Med','1234567'),
	(6,'tessst','test','test4@test.com','tewewr','2345678'),
	(8,'ewqeqw','qweqwe','eqwewq@twe.com','qweqw','3456'),
	(9,'test','test','testt@test.com','test','23232');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
