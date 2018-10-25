-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        10.1.34-MariaDB - mariadb.org binary distribution
-- 伺服器操作系統:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 傾印 mysql_shoppingcart 的資料庫結構
CREATE DATABASE IF NOT EXISTS `mysql_shoppingcart` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mysql_shoppingcart`;

-- 傾印  表格 mysql_shoppingcart.brand 結構
CREATE TABLE IF NOT EXISTS `brand` (
  `brandId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `categoryId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.brand 的資料：~8 rows (大約)
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
REPLACE INTO `brand` (`brandId`, `name`, `categoryId`) VALUES
	(1, 'Elephant Brand', 1),
	(2, 'Kitten Brand', 1),
	(3, 'Doggy Brand', 2),
	(4, 'Bird Brand', 2),
	(5, 'Jaguar Brand', 3),
	(6, 'Lion Brand', 3),
	(7, 'Zebra Brand', 4),
	(8, 'peacock Brand', 4);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.category 結構
CREATE TABLE IF NOT EXISTS `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `segmentId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.category 的資料：~4 rows (大約)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`categoryId`, `name`, `segmentId`) VALUES
	(1, 'Cold kettle Category', 1),
	(2, 'Thermos Category', 1),
	(3, 'Sport Bottle Category', 2),
	(4, 'Trendy kettle Category', 2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.order_ 結構
CREATE TABLE IF NOT EXISTS `order_` (
  `orderId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `dateOrdered` datetime DEFAULT NULL,
  `datePaid` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `total` float unsigned NOT NULL,
  `deliverMethod` tinyint(3) unsigned NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.order_ 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `order_` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.order_item 結構
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

-- 正在傾印表格  mysql_shoppingcart.order_item 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.product 結構
CREATE TABLE IF NOT EXISTS `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `inventory` int(10) unsigned NOT NULL DEFAULT '0',
  `price` float unsigned NOT NULL,
  `dateAdded` datetime DEFAULT NULL,
  `brandId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.product 的資料：~16 rows (大約)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
REPLACE INTO `product` (`productId`, `name`, `inventory`, `price`, `dateAdded`, `brandId`) VALUES
	(1, 'Elephant High quality small kettle (500ml)', 1000, 100, NULL, 1),
	(2, 'Elephant High quality big kettle (1000ml)', 1000, 100, NULL, 1),
	(3, 'Kitten High quality small kettle (500ml)', 1000, 100, NULL, 2),
	(4, 'Kitten High quality big kettle (1000ml)', 1000, 100, NULL, 2),
	(5, 'Doggy High quality small kettle (500ml)', 1000, 100, NULL, 3),
	(6, 'Doggy High quality big kettle (1000ml)', 1000, 100, NULL, 3),
	(7, 'Bird High quality small kettle (500ml)', 1000, 100, NULL, 4),
	(8, 'Bird High quality big kettle (1000ml)', 1000, 100, NULL, 4),
	(9, 'Jaguar High quality small kettle (500ml)', 1000, 100, NULL, 5),
	(10, 'Jaguar High quality big kettle (1000ml)', 1000, 100, NULL, 5),
	(11, 'Lion High quality small kettle (500ml)', 1000, 100, NULL, 6),
	(12, 'Lion High quality big kettle (1000ml)', 1000, 100, NULL, 6),
	(13, 'Zebra High quality small kettle (500ml)', 1000, 100, NULL, 7),
	(14, 'Zebra High quality big kettle (1000ml)', 1000, 100, NULL, 7),
	(15, 'Peacock High quality small kettle (500ml)', 1000, 100, NULL, 8),
	(16, 'Peacock High quality big kettle (1000ml)', 1000, 100, NULL, 8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.product_detail 結構
CREATE TABLE IF NOT EXISTS `product_detail` (
  `productDetailId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(10) unsigned NOT NULL,
  `field` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`productDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.product_detail 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.product_image 結構
CREATE TABLE IF NOT EXISTS `product_image` (
  `productImageId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(10) unsigned NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`productImageId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.product_image 的資料：~16 rows (大約)
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
REPLACE INTO `product_image` (`productImageId`, `productId`, `type`) VALUES
	(1, 1, 'type_single'),
	(2, 2, 'type_single'),
	(3, 3, 'type_single'),
	(4, 4, 'type_single'),
	(5, 5, 'type_single'),
	(6, 6, 'type_single'),
	(7, 7, 'type_single'),
	(8, 8, 'type_single'),
	(9, 9, 'type_single'),
	(10, 10, 'type_single'),
	(11, 11, 'type_single'),
	(12, 12, 'type_single'),
	(13, 13, 'type_single'),
	(14, 14, 'type_single'),
	(15, 15, 'type_single'),
	(16, 16, 'type_single');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.promotion 結構
CREATE TABLE IF NOT EXISTS `promotion` (
  `promotionId` int(11) NOT NULL AUTO_INCREMENT,
  `discountType` int(10) unsigned DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`promotionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.promotion 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
REPLACE INTO `promotion` (`promotionId`, `discountType`, `name`, `dateFrom`, `dateTo`, `state`) VALUES
	(1, 1, 'XYZ Discount', '2018-10-25 11:31:00', NULL, 1),
	(2, 2, '100 Units Discount', '2018-10-25 11:31:00', NULL, 1),
	(3, 3, 'Last Year More Than $100K', '2018-10-25 11:32:00', NULL, 1),
	(4, 4, 'Buy 2 Get 1 Free', '2018-10-25 11:33:00', NULL, 1);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.promotion_item 結構
CREATE TABLE IF NOT EXISTS `promotion_item` (
  `promotionItemId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `promotionId` int(11) unsigned NOT NULL,
  `productId` int(11) unsigned NOT NULL,
  `minQuantity` int(11) unsigned NOT NULL,
  `discountOf` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`promotionItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.promotion_item 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `promotion_item` DISABLE KEYS */;
REPLACE INTO `promotion_item` (`promotionItemId`, `promotionId`, `productId`, `minQuantity`, `discountOf`) VALUES
	(1, 1, 1, 1, 5),
	(2, 1, 2, 1, 5),
	(3, 1, 3, 1, 5),
	(4, 2, 4, 100, 15),
	(5, 3, 5, 100000, 20),
	(6, 4, 6, 2, 0),
	(7, 4, 7, 1, 100);
/*!40000 ALTER TABLE `promotion_item` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.segment 結構
CREATE TABLE IF NOT EXISTS `segment` (
  `segmentId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`segmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.segment 的資料：~2 rows (大約)
/*!40000 ALTER TABLE `segment` DISABLE KEYS */;
REPLACE INTO `segment` (`segmentId`, `name`) VALUES
	(1, 'ShaSha Segment'),
	(2, 'Kitty Segment');
/*!40000 ALTER TABLE `segment` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.subscription 結構
CREATE TABLE IF NOT EXISTS `subscription` (
  `subscriptionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`subscriptionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.subscription 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;

-- 傾印  表格 mysql_shoppingcart.user 結構
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在傾印表格  mysql_shoppingcart.user 的資料：~0 rows (大約)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
