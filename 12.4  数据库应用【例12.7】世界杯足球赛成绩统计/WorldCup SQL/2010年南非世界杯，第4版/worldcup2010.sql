/*
SQLyog Enterprise - MySQL GUI v6.5 Beta1
MySQL - 5.1.39-community : Database - worldcup2010
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

create database if not exists `worldcup2010`;

USE `worldcup2010`;

/*Table structure for table `matchrecord` */

DROP TABLE IF EXISTS `matchrecord`;

CREATE TABLE `matchrecord` (
  `number` int(11) DEFAULT NULL,
  `fixture` datetime DEFAULT NULL,
  `xgroup` char(1) NOT NULL,
  `teamname1` char(20) NOT NULL,
  `teamname2` char(20) NOT NULL,
  `goalsfor1` int(11) DEFAULT NULL,
  `goalsfor2` int(11) DEFAULT NULL,
  PRIMARY KEY (`teamname1`,`teamname2`),
  KEY `teamname2` (`teamname2`),
  CONSTRAINT `matchrecord_ibfk_1` FOREIGN KEY (`teamname1`) REFERENCES `teamscore` (`teamname`),
  CONSTRAINT `matchrecord_ibfk_2` FOREIGN KEY (`teamname2`) REFERENCES `teamscore` (`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `matchrecord` */

insert  into `matchrecord`(`number`,`fixture`,`xgroup`,`teamname1`,`teamname2`,`goalsfor1`,`goalsfor2`) values (NULL,NULL,'C','阿尔及利亚','美国',NULL,NULL),(NULL,NULL,'D','澳大利亚','德国',NULL,NULL),(NULL,NULL,'D','澳大利亚','塞尔维亚',NULL,NULL),(NULL,NULL,'F','巴拉圭','斯洛伐克',NULL,NULL),(NULL,NULL,'G','巴西','朝鲜',NULL,NULL),(NULL,NULL,'G','巴西','科特迪瓦',NULL,NULL),(NULL,NULL,'G','巴西','葡萄牙',NULL,NULL),(NULL,NULL,'G','朝鲜','科特迪瓦',NULL,NULL),(NULL,NULL,'G','朝鲜','葡萄牙',NULL,NULL),(NULL,NULL,'E','丹麦','日本',NULL,NULL),(NULL,NULL,'A','法国','南非',NULL,NULL),(NULL,NULL,'A','法国','乌拉圭',NULL,NULL),(NULL,NULL,'B','韩国','阿根廷',NULL,NULL),(NULL,NULL,'B','韩国','尼日利亚',NULL,NULL),(NULL,NULL,'B','韩国','希腊',NULL,NULL),(NULL,NULL,'E','荷兰','丹麦',NULL,NULL),(NULL,NULL,'E','荷兰','喀麦隆',NULL,NULL),(NULL,NULL,'E','荷兰','日本',NULL,NULL),(NULL,NULL,'H','洪都拉斯','智利',NULL,NULL),(NULL,NULL,'D','加纳','澳大利亚',NULL,NULL),(NULL,NULL,'D','加纳','德国',NULL,NULL),(NULL,NULL,'D','加纳','塞尔维亚',NULL,NULL),(NULL,NULL,'E','喀麦隆','丹麦',NULL,NULL),(NULL,NULL,'E','喀麦隆','日本',NULL,NULL),(NULL,NULL,'G','科特迪瓦','葡萄牙',NULL,NULL),(NULL,NULL,'A','墨西哥','法国',NULL,NULL),(NULL,NULL,'A','墨西哥','南非',NULL,NULL),(NULL,NULL,'A','墨西哥','乌拉圭',NULL,NULL),(NULL,NULL,'A','南非','乌拉圭',NULL,NULL),(NULL,NULL,'B','尼日利亚','阿根廷',NULL,NULL),(NULL,NULL,'B','尼日利亚','希腊',NULL,NULL),(NULL,NULL,'H','瑞士','洪都拉斯',NULL,NULL),(NULL,NULL,'H','瑞士','智利',NULL,NULL),(NULL,NULL,'D','塞尔维亚','德国',NULL,NULL),(NULL,NULL,'C','斯洛文尼亚','阿尔及利亚',NULL,NULL),(NULL,NULL,'C','斯洛文尼亚','美国',NULL,NULL),(NULL,NULL,'C','斯洛文尼亚','英格兰',NULL,NULL),(NULL,NULL,'H','西班牙','洪都拉斯',NULL,NULL),(NULL,NULL,'H','西班牙','瑞士',NULL,NULL),(NULL,NULL,'H','西班牙','智利',NULL,NULL),(NULL,NULL,'B','希腊','阿根廷',NULL,NULL),(NULL,NULL,'F','新西兰','巴拉圭',NULL,NULL),(NULL,NULL,'F','新西兰','斯洛伐克',NULL,NULL),(NULL,NULL,'F','意大利','巴拉圭',NULL,NULL),(NULL,NULL,'F','意大利','斯洛伐克',NULL,NULL),(NULL,NULL,'F','意大利','新西兰',NULL,NULL),(NULL,NULL,'C','英格兰','阿尔及利亚',NULL,NULL),(NULL,NULL,'C','英格兰','美国',NULL,NULL);

/*Table structure for table `teamscore` */

DROP TABLE IF EXISTS `teamscore`;

CREATE TABLE `teamscore` (
  `xgroup` char(1) NOT NULL,
  `teamname` char(20) NOT NULL,
  `completed` int(11) DEFAULT '0',
  `win` int(11) DEFAULT '0',
  `tie` int(11) DEFAULT '0',
  `loss` int(11) DEFAULT '0',
  `goalsfor` int(11) DEFAULT '0',
  `goalsagaint` int(11) DEFAULT '0',
  `netvalue` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `rank` int(11) DEFAULT '0',
  PRIMARY KEY (`teamname`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `teamscore` */

insert  into `teamscore`(`xgroup`,`teamname`,`completed`,`win`,`tie`,`loss`,`goalsfor`,`goalsagaint`,`netvalue`,`score`,`rank`) values ('C','阿尔及利亚',0,0,0,0,0,0,0,0,0),('B','阿根廷',0,0,0,0,0,0,0,0,0),('D','澳大利亚',0,0,0,0,0,0,0,0,0),('F','巴拉圭',0,0,0,0,0,0,0,0,0),('G','巴西',0,0,0,0,0,0,0,0,0),('G','朝鲜',0,0,0,0,0,0,0,0,0),('E','丹麦',0,0,0,0,0,0,0,0,0),('D','德国',0,0,0,0,0,0,0,0,0),('A','法国',0,0,0,0,0,0,0,0,0),('B','韩国',0,0,0,0,0,0,0,0,0),('E','荷兰',0,0,0,0,0,0,0,0,0),('H','洪都拉斯',0,0,0,0,0,0,0,0,0),('D','加纳',0,0,0,0,0,0,0,0,0),('E','喀麦隆',0,0,0,0,0,0,0,0,0),('G','科特迪瓦',0,0,0,0,0,0,0,0,0),('C','美国',0,0,0,0,0,0,0,0,0),('A','墨西哥',0,0,0,0,0,0,0,0,0),('A','南非',0,0,0,0,0,0,0,0,0),('B','尼日利亚',0,0,0,0,0,0,0,0,0),('G','葡萄牙',0,0,0,0,0,0,0,0,0),('E','日本',0,0,0,0,0,0,0,0,0),('H','瑞士',0,0,0,0,0,0,0,0,0),('D','塞尔维亚',0,0,0,0,0,0,0,0,0),('F','斯洛伐克',0,0,0,0,0,0,0,0,0),('C','斯洛文尼亚',0,0,0,0,0,0,0,0,0),('A','乌拉圭',0,0,0,0,0,0,0,0,0),('H','西班牙',0,0,0,0,0,0,0,0,0),('B','希腊',0,0,0,0,0,0,0,0,0),('F','新西兰',0,0,0,0,0,0,0,0,0),('F','意大利',0,0,0,0,0,0,0,0,0),('C','英格兰',0,0,0,0,0,0,0,0,0),('H','智利',0,0,0,0,0,0,0,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
