-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        5.7.8-rc-log - MySQL Community Server (GPL)
-- 伺服器操作系統:                      Win64
-- HeidiSQL 版本:                  9.5.0.5292
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


CREATE DATABASE IF NOT EXISTS `mysql_shoppingcart` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mysql_shoppingcart`;

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `order_` (
  `orderId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `dateOrdered` datetime DEFAULT NULL,
  `datePaid` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `total` float unsigned NOT NULL,
  `deliverMethod` tinyint(3) unsigned NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `order_item` (
  `orderItemId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL,
  `userId` int(11) unsigned NOT NULL,
  `productId` int(11) unsigned NOT NULL,
  `quantity` int(11) unsigned NOT NULL,
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `originalPrice` float unsigned NOT NULL,
  `promotionalPrice` float unsigned NOT NULL,
  PRIMARY KEY (`orderItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `inventory` int(10) unsigned NOT NULL DEFAULT '0',
  `price` float unsigned NOT NULL,
  `dateAdded` datetime DEFAULT NULL,
  `categoryId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product_detail` (
  `productDetailId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(10) unsigned NOT NULL,
  `field` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`productDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `subscription` (
  `subscriptionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`subscriptionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `promotion` (
  `promotionId` int(11) NOT NULL AUTO_INCREMENT,
  `discountType` int(10) unsigned DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`promotionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `promotion_item` (
  `promotionItemId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `promotionId` int(11) unsigned NOT NULL,
  `productId` int(11) unsigned NOT NULL,
  `minQuantity` int(11) unsigned NOT NULL,
  `discountOf` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`promotionItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product_image` (
  `productImageId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(10) unsigned NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`productImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
