/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.11 : Database - mydb01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb01` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mydb01`;

/*Table structure for table `ac_dish` */

DROP TABLE IF EXISTS `ac_dish`;

CREATE TABLE `ac_dish` (
  `dishid` int(4) NOT NULL AUTO_INCREMENT,
  `dishname` varchar(20) DEFAULT NULL COMMENT '菜名',
  `feature` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '特色',
  `ingredients` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '食材',
  `price` int(4) DEFAULT NULL COMMENT '价格',
  `typeid` int(5) DEFAULT NULL,
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '照片名字',
  `clickrote` int(4) DEFAULT '0' COMMENT '点击次数',
  `remark` int(4) DEFAULT '0' COMMENT '0-无 ；1-今日特价；2-厨师推荐',
  PRIMARY KEY (`dishid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `ac_dish` */

insert  into `ac_dish`(`dishid`,`dishname`,`feature`,`ingredients`,`price`,`typeid`,`photo`,`clickrote`,`remark`) values 
(1,'韭菜炒鸡蛋','工艺：炒 口味：清香味','主料：韭菜4两（约160克），大鸡蛋3只',8,2,'01.jpg',10,0),
(3,'渝味辣白菜','特色：色泽红亮、酸甜微辣，脆爽可口。','主料：娃娃菜200克。',6,3,'F_1660464481143.jpg',8,-2),
(4,'清蒸桂鱼','色泽淡稚悦目，味似蟹肉，鲜香馥郁。','辅料：熟火腿3片（25克）， 熟笋6片（60克），水发大香菇3朵，姜片2.5克、葱结1个',40,4,'F_1660543738922.jpg',36,0),
(5,'爆炒腰花','特色：腰花鲜嫩，造形美观，味道醇厚，滑润不腻。','主料：猪腰子200克。辅料： 冬笋片、水发木耳各50克；酱油10克、精盐3克、味精1克、绍酒20克、湿粉15克。',12,1,'F_1660533337153.jpg',6,-1),
(6,'肉末豆腐','特色：鲜嫩可口。功效：强壮身体，改善体质。','暂无主料：嫩豆腐600 克，肉末150 克，酱油10 克，细盐3 克，味精3 克，黄酒5克，姜末5 克，葱花3 克，胡椒粉1 克，鲜汤300 克。',7,8,'F_1660543801024.jpg',2,-2),
(7,'泡椒鸡爪','暂无','主料：凤爪1000克，野山椒50克。辅料：泡菜酸水一些，芹菜、花椒、味精、鸡精、盐、胡椒少许。',20,6,'F_1660543980265.jpg',2,-1),
(8,'姜撞奶','暂无','主料：鲜姜一大块，牛奶一袋，白糖，捣蒜缸',17,4,'10.jpg',0,-1);

/*Table structure for table `ac_dish_type` */

DROP TABLE IF EXISTS `ac_dish_type`;

CREATE TABLE `ac_dish_type` (
  `typeid` int(4) NOT NULL AUTO_INCREMENT,
  `typename` varchar(20) DEFAULT NULL COMMENT '菜品类别',
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `ac_dish_type` */

insert  into `ac_dish_type`(`typeid`,`typename`) values 
(1,'主食'),
(2,'家常'),
(3,'凉菜'),
(4,'饮品'),
(6,'火锅'),
(8,'冒菜');

/*Table structure for table `ac_order_info` */

DROP TABLE IF EXISTS `ac_order_info`;

CREATE TABLE `ac_order_info` (
  `userid` int(5) NOT NULL,
  `dishid` int(5) NOT NULL,
  `orderNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '订单id,0-未生成订单',
  `state` int(4) DEFAULT '0' COMMENT '0-点餐中；1-已吃',
  PRIMARY KEY (`userid`,`dishid`,`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ac_order_info` */

insert  into `ac_order_info`(`userid`,`dishid`,`orderNumber`,`state`) values 
(1,1,'36719350850',0),
(1,1,'60451574047',0),
(1,1,'63937577983',1),
(1,1,'91919721972',1),
(1,1,'92190715869',0),
(1,1,'94070458268',1),
(1,3,'25647492510',0),
(1,3,'41334186264',1),
(1,3,'60451574047',0),
(1,3,'63937577983',1),
(1,3,'92190715869',0),
(1,3,'94070458268',1),
(1,3,'99940821679',0),
(1,4,'25647492510',0),
(1,4,'45359210234',0),
(1,4,'60451574047',0),
(1,4,'84573770378',1),
(1,6,'65249625226',1),
(1,7,'65249625226',1);

/*Table structure for table `ac_user` */

DROP TABLE IF EXISTS `ac_user`;

CREATE TABLE `ac_user` (
  `userid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `power` int(5) DEFAULT '0' COMMENT '权限（0-普通用户，1-管理员）',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `ac_user` */

insert  into `ac_user`(`userid`,`username`,`account`,`password`,`tel`,`address`,`power`) values 
(1,'张三','ds','521BEA8994986BD1390EC188C2D418E7','46545154','北京市dsd',0),
(2,'都市','asd','984CBA73CDA2B6BBEA48576D677B7EE1','46541651216','hhih今年dsd dsds',1),
(3,'哈哈','hj','191120C9B9B31CB37474838E1EC38F40','46541651209','hhih今年45',0),
(5,'王五','hgyj','0824AAF0521F01B7A173201BCCE28DF9','456985','四川',0),
(6,'六子','opi','951F2914D95774B4CA7E0FDCD61698B2','1569875156','湖南省',0),
(7,'哈哈搜索','jkl','22E23856426158E9DC2B2613AAF76A47','1652','shc啊',0),
(8,'手打','sdaad','5F2BBB4E643D022535D37AAC9282E852','495681','dsa阿萨法',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
