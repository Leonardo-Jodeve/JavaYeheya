/* 创建student数据库，创建stuinfo、course和stugrade表，stuinfo表中插入数据
SQLyog Enterprise - MySQL GUI v6.5 Beta1
MySQL - 5.1.39-community : Database - student
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

create database if not exists `student`;

USE `student`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` char(10) NOT NULL,
  `course_name` char(20) DEFAULT NULL,
  `credit_hour` float(5,1) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `course` */

/*Table structure for table `stugrade` */

DROP TABLE IF EXISTS `stugrade`;

CREATE TABLE `stugrade` (
  `stu_id` char(10) NOT NULL,
  `course_id` char(10) NOT NULL,
  `grade` float(5,2) DEFAULT NULL,
  PRIMARY KEY (`stu_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `stugrade_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `stuinfo` (`stu_id`),
  CONSTRAINT `stugrade_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `stugrade` */

/*Table structure for table `stuinfo` */

DROP TABLE IF EXISTS `stuinfo`;

CREATE TABLE `stuinfo` (
  `stu_id` char(10) NOT NULL,
  `stu_name` char(10) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `province` char(20) DEFAULT NULL,
  `area` char(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nation` char(10) DEFAULT '汉',
  `member` tinyint(1) DEFAULT '1',
  `department` char(20) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `stuinfo` */

insert  into `stuinfo`(`stu_id`,`stu_name`,`sex`,`province`,`area`,`birthday`,`nation`,`member`,`department`) values ('98111001','蔡尧强','男','浙江','金华','1980-01-20','汉',1,'计算机系'),('98111002','冯正亚','女','江苏','连云港','1979-01-09','汉',1,'计算机系'),('98111003','傅建玲','女','浙江','绍兴','1980-05-22','汉',1,'计算机系'),('98111004','韩家雄','男','江苏','南通','1979-04-30','汉',1,'计算机系'),('98111006','江海山','男','安徽','合肥','1981-02-03','汉',1,'计算机系'),('98111007','金明','男','安徽','马鞍山','1980-01-01','汉',0,'计算机系'),('98111008','李翠','女','陕西','延安','1981-03-01','汉',1,'计算机系'),('98111009','李维华','女','青海','西宁','1979-11-06','汉',1,'计算机系'),('98111011','林水宝','男','福建','泉州','1981-02-10','汉',1,'计算机系'),('98111012','刘洪星','男','山东','日照','1979-10-01','汉',1,'计算机系'),('98111013','刘绍军','男','湖南','常德','1980-02-03','汉',1,'计算机系'),('98111014','吕修福','男','江苏','连云港','1981-11-01','汉',1,'计算机系'),('98111015','祁晶','男','甘肃','白银','1981-04-02','汉',1,'计算机系'),('98111016','钦海蓉','男','江苏','江阴','1980-01-01','汉',1,'计算机系'),('98111017','邵斯林','男','广西','梧州','1980-01-01','汉',1,'计算机系'),('98111018','沈瀚斐','男','江苏','无锡','1980-01-01','汉',1,'计算机系'),('98111019','盛梅兰','女','浙江','金华','1980-01-01','汉',1,'计算机系'),('98111020','石健','男','贵州','毕节','1980-01-01','汉',1,'计算机系'),('98111021','王雄川','男','浙江','金华','1980-01-01','汉',1,'计算机系'),('98111022','魏晓宇','男','江苏','扬州','1980-01-01','汉',1,'计算机系'),('98111023','肖颖','女','福建','南平','1980-01-01','汉',1,'计算机系'),('98111024','谢吉吉','男','广东','韶关','1980-01-01','汉',1,'计算机系'),('98111025','徐丹','女','江苏','南通','1980-01-01','汉',1,'计算机系'),('98111026','徐远哲','男','安徽','宿县','1980-01-01','汉',1,'计算机系'),('98111027','许明','男','山东','日照','1980-01-01','汉',1,'计算机系'),('98111028','严伟','男','浙江','衢州','1981-02-01','汉',1,'计算机系'),('98111029','杨旭','男','贵州','铜仁','1980-10-09','土家',1,'计算机系'),('98111030','杨训','男','安徽','宿县','1979-03-02','汉',1,'计算机系'),('98111031','姚远秀','男','陕西','商洛','1980-01-01','汉',1,'计算机系'),('98111032','易蝉鸣','女','广东','湛江','1980-01-01','汉',1,'计算机系'),('98111033','张建','男','江苏','淮阴','1980-01-01','汉',1,'计算机系'),('98111034','张苏荣','男','安徽','宣城','1980-01-01','汉',1,'计算机系'),('98111037','周雷','男','江苏','徐州','1980-01-01','汉',1,'计算机系'),('98111038','朱强光','男','江苏','泗阳','1980-01-01','汉',1,'计算机系'),('98111040','邹志敏','男','江西','九江','1980-01-01','汉',1,'计算机系');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
