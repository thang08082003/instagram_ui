/*
SQLyog Enterprise - MySQL GUI v6.15
MySQL - 5.0.82-community-nt : Database - quizmanager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `quizmanager`;

USE `quizmanager`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `answerid` varchar(20) NOT NULL,
  `answername` varchar(3000) default NULL,
  `questionid` varchar(20) default NULL,
  `isCorrect` tinyint(1) default NULL,
  PRIMARY KEY  (`answerid`),
  KEY `FK_answer` (`questionid`),
  CONSTRAINT `FK_answer` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `answer` */

insert  into `answer`(`answerid`,`answername`,`questionid`,`isCorrect`) values ('AS003','thật tốt để có thể lập kế hoạch cho phù hợp','QT002',1),('AS004','một chút khó chịu vì bị ràng buộc','QT002',0),('AS007','1','QT005',0),('AS008','2','QT005',0),('AS009','7','QT005',0),('AS010','12','QT005',1),('AS011','13','QT006',1),('AS012','10','QT006',0),('AS013','15','QT006',0),('AS014','22','QT006',0),('AS015','1','QT007',0),('AS016','2','QT007',0),('AS017','3','QT007',1),('AS018','4','QT007',0),('AS019','Việt Nam','QT008',0),('AS020','Trung Quốc','QT008',0),('AS021','Ở nước nào thì chôn ở nước đó','QT008',0),('AS022','Không nên chôn ở nước nào','QT008',1),('AS023','5000','QT009',0),('AS024','5100','QT009',0),('AS025','4100','QT009',1),('AS026','3900','QT009',0),('AS027','t2','QT012',1),('AS028','aasd','QT012',0),('AS029','không biết','QT011',0),('AS030','chưa biết','QT011',0),('AS034','sa','QT014',1),('AS035','asa','QT014',0),('AS036','as','QT014',0);

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `classid` varchar(20) NOT NULL,
  `classname` varchar(200) default NULL,
  PRIMARY KEY  (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class` */

insert  into `class`(`classid`,`classname`) values ('CL001','151101a'),('CL002','151101b'),('CL003','151101c'),('CL004','VLDC01'),('CL005','WPCT7'),('CL006','Hóa hữu cơ');

/*Table structure for table `class_exam` */

DROP TABLE IF EXISTS `class_exam`;

CREATE TABLE `class_exam` (
  `examid` varchar(20) NOT NULL default '',
  `classid` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`examid`,`classid`),
  KEY `FK_class_exam_class` (`classid`),
  CONSTRAINT `FK_class_exam` FOREIGN KEY (`examid`) REFERENCES `exam` (`examid`) ON DELETE CASCADE,
  CONSTRAINT `FK_class_exam_class` FOREIGN KEY (`classid`) REFERENCES `class` (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_exam` */

insert  into `class_exam`(`examid`,`classid`) values ('EX001','CL001'),('EX003','CL001'),('EX001','CL002'),('EX003','CL002'),('EX001','CL003'),('EX003','CL003'),('EX001','CL004'),('EX001','CL005'),('EX001','CL006');

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `contentid` varchar(20) NOT NULL,
  `contentname` varchar(200) NOT NULL,
  `subid` varchar(20) default NULL,
  `datecreate` datetime default NULL,
  `creator` varchar(50) default NULL,
  PRIMARY KEY  (`contentid`),
  KEY `FK_content` (`subid`),
  KEY `FK_content_user` (`creator`),
  CONSTRAINT `FK_content` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE SET NULL,
  CONSTRAINT `FK_content_user` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `content` */

insert  into `content`(`contentid`,`contentname`,`subid`,`datecreate`,`creator`) values ('CT001','Cơ học','SJ001','2017-11-26 02:01:16','15110378'),('CT002','Điện từ trường','SJ001','2017-11-26 02:01:16','15110378'),('CT003','Servlet','SJ002','2017-11-26 02:01:16','15110378'),('CT004','Mô hình hóa yêu cầu','SJ003','2017-11-26 02:01:16','15110378'),('CT005','Dao động sóng','SJ001','2017-10-20 21:05:00','15110379'),('CT006','Truyền âm - Tần số','SJ001','2017-10-20 19:00:00','15110300'),('CT007','JSP','SJ002','2017-12-14 19:30:23','15110378'),('CT008','JQuery nâng cao','SJ002','2017-12-14 19:30:23','15110378'),('CT009','Mô hình MVC2','SJ002','2017-12-14 19:30:23','15110378'),('CT010','JDBC - MySQL','SJ002','2017-12-14 19:30:23','15110378'),('CT011','Phân tích yêu cầu','SJ003','2017-12-14 19:30:23','15110378'),('CT012','Thiết kế giao diện','SJ003','2017-12-14 19:30:23','15110378'),('CT013','Xử lý chức năng','SJ003','2017-12-14 19:30:23','15110378'),('CT014','Security','SJ003','2017-12-14 19:30:23','15110378'),('CT015','Khoa học','SJ004','2017-12-14 19:30:23','15110378'),('CT016','Tự nhiên','SJ004','2017-12-14 19:30:23','15110378'),('CT017','Tổng hợp','SJ004','2017-12-15 01:05:38','15110378');

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `examid` varchar(20) NOT NULL,
  `testid` varchar(20) default NULL,
  `examname` varchar(300) default NULL,
  `timetest` int(11) default NULL,
  `datestart` datetime default NULL,
  `datecreate` datetime default NULL,
  `creator` varchar(50) default NULL,
  PRIMARY KEY  (`examid`),
  KEY `FK_exam` (`testid`),
  KEY `FK_exam_user` (`creator`),
  CONSTRAINT `FK_exam` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`) ON DELETE SET NULL,
  CONSTRAINT `FK_exam_user` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`examid`,`testid`,`examname`,`timetest`,`datestart`,`datecreate`,`creator`) values ('EX001','TS006','Thi giữa kỳ CNPM',75,'2017-12-24 19:53:40','2017-12-24 19:06:11','15110378'),('EX003','TS004','Thi cuối kỳ VLDC1',60,'2018-01-20 15:40:00','2017-12-24 18:49:05','15110378');

/*Table structure for table `list_class` */

DROP TABLE IF EXISTS `list_class`;

CREATE TABLE `list_class` (
  `classid` varchar(20) NOT NULL default '',
  `username` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`classid`,`username`),
  KEY `FK_list_class_user` (`username`),
  CONSTRAINT `FK_list_class` FOREIGN KEY (`classid`) REFERENCES `class` (`classid`),
  CONSTRAINT `FK_list_class_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `list_class` */

insert  into `list_class`(`classid`,`username`) values ('CL005','123456'),('CL001','15110300'),('CL005','15110300'),('CL001','15110310'),('CL003','15110311'),('CL001','15110378'),('CL004','15110378'),('CL001','15110379'),('CL002','15110379');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `questionid` varchar(20) NOT NULL,
  `questionname` varchar(4000) default NULL,
  `contentid` varchar(20) default NULL,
  `datecreate` datetime default NULL,
  `creator` varchar(50) default NULL,
  PRIMARY KEY  (`questionid`),
  KEY `FK_question_user` (`creator`),
  KEY `FK_question_cnt` (`contentid`),
  CONSTRAINT `FK_question_cnt` FOREIGN KEY (`contentid`) REFERENCES `content` (`contentid`) ON DELETE SET NULL,
  CONSTRAINT `FK_question_user` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`questionid`,`questionname`,`contentid`,`datecreate`,`creator`) values ('QT002','Khi bạn được ủy nhiệm để làm một điều gì đó(bắt buộc) vào một thời điểm nhất định, bạn cảm thấy?','CT015','2017-11-26 02:01:16','15110378'),('QT005','Trong 1 năm, tháng thì có ngày 31, tháng thì có ngày 30, vậy có bao nhiêu tháng có ngày 28?','CT016','2017-11-26 02:05:04','15110300'),('QT006','Điền vào chỗ trống : 1, 2, 3, 5, 8 , (…)','CT015','2017-12-14 18:06:00','15110378'),('QT007','Có tất cả 4 trái xoài trên bàn, bạn lấy đi 3, hiện giờ bạn có bao nhiêu quả xoài?','CT016','2017-12-14 18:23:19','15110378'),('QT008','Hai chiếc xe đò (xe buýt) đụng nhau ở biên giới Việt-Hoa.  Hành khách trên cả 2 xe đều là khách du lịch từ khắp năm châu như (Mỹ, Á, Âu, Úc, Phi).  Sau tại nạn, những người thoát nạn nên được chôn ở nước nào?','CT016','2017-12-14 19:06:00','15110378'),('QT009','Bạn có 1000 cộng thêm 40. Bây giờ bạn cộng thêm 1000 khác. Và giờ thì cộng thêm 30...Cộng thêm 1000 nữa. Cộng thêm 20. Cộng thêm 1000 khác. Cộng thêm 10.Tổng là bao nhiêu vậy? ','CT016','2017-12-14 19:17:23','15110378'),('QT010','Liệu một người bạn mới gặp có thể nói(chỉ ra) ngay được những điều bạn thích thú(quan tâm) đến?','CT015','2017-12-15 05:15:30','15110378'),('QT011',' Khi làm những việc mà nhiều người khác vẫn thường hay làm, thì ý nào (dưới đây) hấp dẫn bạn hơn?','CT015','2017-12-15 05:38:25','15110378'),('QT012','testss','CT014','2017-12-15 05:44:52','15110378'),('QT014','asadasd','CT008','2017-12-15 06:18:41','15110378');

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
  `examid` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `mark` decimal(4,2) default NULL,
  `datetest` datetime default NULL,
  `timefinish` int(11) default NULL,
  PRIMARY KEY  (`examid`,`username`),
  KEY `FK_resultUS` (`username`),
  CONSTRAINT `FK_result` FOREIGN KEY (`examid`) REFERENCES `exam` (`examid`) ON DELETE CASCADE,
  CONSTRAINT `FK_resultUS` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `result` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL auto_increment,
  `rolename` varchar(200) NOT NULL,
  PRIMARY KEY  (`roleid`),
  UNIQUE KEY `rolename` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleid`,`rolename`) values (4,'Học viên'),(3,'QL câu hỏi'),(2,'QL kỳ thi'),(1,'Quản trị viên');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subid` varchar(20) NOT NULL,
  `subname` varchar(200) default NULL,
  PRIMARY KEY  (`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`subid`,`subname`) values ('SJ001','Vật lý đại cương 1'),('SJ002','Lập trình Web'),('SJ003','Công nghệ phần mềm'),('SJ004','Khoa học tự nhiên'),('SJ005','Lập trình NodeJS');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `testid` varchar(20) NOT NULL,
  `testname` varchar(200) default NULL,
  `datecreate` datetime default NULL,
  `creator` varchar(50) default NULL,
  `subid` varchar(20) default NULL,
  PRIMARY KEY  (`testid`),
  KEY `FK_test_sub` (`subid`),
  CONSTRAINT `FK_test_sub` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test` */

insert  into `test`(`testid`,`testname`,`datecreate`,`creator`,`subid`) values ('TS001','Đề thi cuối kỳ VLDC 1 2017-2018','2017-12-14 13:00:26','15110378','SJ001'),('TS002','Đề thi lập trình Web sử dụng Spring MVC và Hibernate','2017-12-14 12:15:52','15110378','SJ002'),('TS003','Đề thi mô hình hóa dữ liệu CLC','2017-11-28 10:30:16','15110300','SJ003'),('TS004','Đề thi tổng kết phần Điện từ trường','2017-11-23 08:39:45','15110310','SJ001'),('TS005','Đề thi đố vui 2017','2017-12-24 18:20:40','15110378','SJ002'),('TS006','de test02','2017-12-24 18:28:49','15110378','SJ004');

/*Table structure for table `test_question` */

DROP TABLE IF EXISTS `test_question`;

CREATE TABLE `test_question` (
  `testid` varchar(20) NOT NULL default '',
  `questionid` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`testid`,`questionid`),
  KEY `FK_test_questionqq` (`questionid`),
  CONSTRAINT `FK_test_question` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`) ON DELETE CASCADE,
  CONSTRAINT `FK_test_questionqq` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test_question` */

insert  into `test_question`(`testid`,`questionid`) values ('TS001','QT002'),('TS006','QT002'),('TS006','QT005'),('TS001','QT006'),('TS006','QT006'),('TS006','QT007'),('TS006','QT008'),('TS006','QT009'),('TS006','QT010'),('TS006','QT011'),('TS005','QT014');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `fullname` varchar(100) default NULL,
  `gender` tinyint(1) default NULL,
  `birthday` date default NULL,
  `phonenumber` varchar(13) default NULL,
  `avatar` varchar(225) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`username`,`pwd`,`fullname`,`gender`,`birthday`,`phonenumber`,`avatar`) values ('123444','YZENNY','ZENNY97hihuaa',1,'1996-08-16','01682075449','avt.jpg'),('123456','44c5d6348bcc8de387735fe70728a6b8','Trần Quốc Toản',1,'1998-12-06','01232214787','avt.jpg'),('151100000000','66120b32b31fd750401a4bfac685bbcd','nhuy dep troai',1,'1997-12-21','01682074499','avt.jpg'),('15110300','123','KK',0,'1997-08-16','123','avt.jpg'),('15110310','2306d74dbde30689ca39734e5d6c5ec','Nguyễn Thiên Ân',0,'1997-08-16','01685457852',NULL),('15110311','2306d74dbde30689ca39734e5d6c5ec','Lâm Chi Khanh',0,'1997-08-16','0125458546',NULL),('15110378','202cb962ac59075b964b07152d234b70','Trần Nguyễn Thanh Như Ý',1,'1997-08-16','01682075449','1.jpg'),('15110379','2306d74dbde30689ca39734e5d6c5ec','Trần Quốc Bảo',1,'1997-08-16','0962532145',NULL),('17110381','effb4ab3d4ae3eba3e5d2d89c61494ec','Nguyễn Ngọc Hoài Nhiên',0,'1997-10-18','01682075449','avt.jpg'),('17110385','4880e10577c672abe7c7dbb4da12b22f','Bùi Thế Bá',1,'1997-08-16','01682075449','avt.jpg'),('17110387','effb4ab3d4ae3eba3e5d2d89c61494ec','Huỳnh Quốc Sang',1,'1997-10-18','01682075449','avt.jpg'),('nguyenhien','202cb962ac59075b964b07152d234b70','Nguyễn Thị Hiền',0,'1997-09-16','0981146193','avt.jpg'),('phungtuong','14c97793734d53373596a55d5ec2ae93','Tuong Nguyen',1,'1995-04-10','','avt.jpg'),('sad','1acd94c23790ed26b8b7cddcef44e5e0','ssss',0,'2017-12-06','12322','avt.jpg');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `username` varchar(50) NOT NULL default '',
  `roleid` int(11) NOT NULL default '0',
  `datecreate` datetime default NULL,
  `creator` varchar(50) default NULL,
  PRIMARY KEY  (`username`,`roleid`),
  KEY `FK_user_role_role` (`roleid`),
  KEY `FK_usercrt` (`creator`),
  CONSTRAINT `FK_usercrt` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE SET NULL,
  CONSTRAINT `FK_user_role` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`username`,`roleid`,`datecreate`,`creator`) values ('123444',1,'2017-12-23 00:00:00','15110378'),('123444',4,'2017-12-23 00:00:00','15110378'),('123456',3,'2017-12-24 00:00:00','15110378'),('123456',4,'2017-12-24 00:00:00','15110378'),('151100000000',1,'2017-12-08 00:00:00','15110378'),('151100000000',2,'2017-12-08 00:00:00','15110378'),('151100000000',3,'2017-12-08 00:00:00','15110378'),('151100000000',4,'2017-12-08 00:00:00','15110378'),('15110300',1,'2017-11-26 02:01:16','15110378'),('15110300',3,'2017-12-05 00:00:00','15110378'),('15110310',2,NULL,NULL),('15110310',3,'2017-11-26 02:01:16','15110378'),('15110311',1,NULL,NULL),('15110311',2,NULL,NULL),('15110311',4,'2017-11-26 02:01:16','15110378'),('15110378',1,'2017-11-26 02:01:16','15110378'),('15110378',2,'2017-11-26 02:01:16','15110378'),('15110378',3,'2017-11-26 02:01:16','15110378'),('15110378',4,'2017-11-26 02:01:16','15110378'),('15110379',2,'2017-11-26 02:01:16','15110378'),('17110381',1,'2017-12-24 00:00:00','15110378'),('17110385',1,'2017-12-24 00:00:00','15110378'),('17110387',1,'2017-12-24 00:00:00','15110378'),('nguyenhien',1,'2017-12-15 00:00:00','15110378'),('nguyenhien',2,'2017-12-15 00:00:00','15110378'),('nguyenhien',3,'2017-12-15 00:00:00','15110378'),('nguyenhien',4,'2017-12-15 00:00:00','15110378'),('phungtuong',1,'2017-12-15 00:00:00','nguyenhien');

/* Procedure structure for procedure `spDeleteContent` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteContent` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteContent`(contentid varchar(20))
begin
delete from content where content.contentid = contentid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spDeleteExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteExam`(examid varchar(20))
begin
delete from exam where exam.examid = examid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spDeleteQuestion` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteQuestion` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteQuestion`(questionid varchar(20))
begin
delete from question where question.questionid = questionid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spDeleteTest` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteTest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteTest`(testid varchar(20))
begin
delete from test where test.testid = testid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spDeleteUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteUser`(username varchar(50))
begin
delete from user where user.username = username;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spDeleteUserRole` */

/*!50003 DROP PROCEDURE IF EXISTS  `spDeleteUserRole` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteUserRole`(username varchar(50))
begin
DELETE from user_role where user_role.username = username;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetAllAnswer` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetAllAnswer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetAllAnswer`()
begin
select * from answer;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetALLExamForStudent` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetALLExamForStudent` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetALLExamForStudent`(username varchar(50))
begin
select exam.examid, exam.testid,exam.examname,exam.timetest,exam.datestart,exam.datecreate,exam.creator from exam, class_exam, list_class where exam.examid = class_exam.examid and class_exam.classid = list_class.classid and list_class.username = username order by exam.datestart;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetAnswer` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetAnswer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetAnswer`(questionid varchar(20))
begin
select * from answer where answer.questionid = questionid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetClass` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetClass` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetClass`()
begin
select * from class;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetClassExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetClassExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetClassExam`()
begin
select * from class_exam;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetClassUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetClassUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetClassUser`()
begin
select * from list_class;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetContent` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetContent` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetContent`()
begin
select * from content;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetExam`()
begin
select * from exam order by exam.datestart desc;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetLevel` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetLevel` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetLevel`()
begin
select * from level;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetQuestion` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetQuestion` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetQuestion`()
begin
select * from question;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetResult` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetResult` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetResult`(username varchar(50))
begin
select exam.examid,exam.examname,result.mark,result.timefinish,result.datetest from result,exam where result.username = username and result.examid = exam.examid order by result.datetest desc;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetRole` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetRole` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetRole`()
begin 
select * from role;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetSubject` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetSubject` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetSubject`()
begin
select * from subject;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetTest` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetTest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetTest`()
begin
select * from test order by test.datecreate desc;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetTestQuestion` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetTestQuestion` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetTestQuestion`()
begin
select * from test_question;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetTopExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetTopExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetTopExam`()
begin
select * from exam order by exam.datecreate desc LIMIT 4;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetTopTest` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetTopTest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetTopTest`()
begin
select * from test order by test.datecreate desc LIMIT 4;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetUser`()
begin 
select * from user;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spGetUserRole` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetUserRole` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetUserRole`()
begin 
select * from user_role;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertAnswer` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertAnswer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertAnswer`(answerid varchar(20), answername varchar(3000), questionid varchar(20), iscorect tinyint)
begin
insert into answer values(answerid,answername, questionid,iscorect);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertClass` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertClass` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertClass`( classid varchar(20),classname varchar(200))
begin
insert into class values(classid,classname);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertClassExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertClassExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertClassExam`(testid varchar(20), classid varchar(20))
begin
insert into class_exam values(testid,classid);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertClassUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertClassUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertClassUser`( classid varchar(20),username varchar(50))
begin
insert into list_class values(classid,username);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertContent` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertContent` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertContent`(contentid varchar(20), contentname varchar(200), subid varchar(20), datecreate datetime ,creator varchar(50))
begin
insert into content values(contentid,contentname,subid,datecreate,creator);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertExam` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertExam` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertExam`(examid varchar(20), testid varchar(20), examname varchar(20), timetest int, datestart datetime, datecreate datetime, creator varchar(50))
begin
insert into exam values(examid,testid,examname,timetest,datestart,datecreate,creator);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertQuestion` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertQuestion` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertQuestion`(questionid varchar(20), questionname varchar(4000), contentid varchar(20), datecreate datetime ,creator varchar(50))
begin
insert into question values(questionid,questionname,contentid,datecreate,creator);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertResult` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertResult` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertResult`(examid varchar(20), username varchar(50),mark decimal,datetest datetime,timefinish int)
begin
insert into result values(examid,username,mark,datetest,timefinish);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertSubject` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertSubject` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertSubject`( subid varchar(20),subname varchar(200))
begin
insert into subject values(subid,subname);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertTest` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertTest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertTest`(testid varchar(20), testname varchar(200), datecreate datetime, creator varchar(50), subid varchar(20))
begin
insert into test values(testid, testname, datecreate, creator,subid);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertTestQues` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertTestQues` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertTestQues`(testid varchar(20),questid varchar(20))
begin
insert into test_question values(testid,questid);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertUser`(username varchar(50), password varchar(200), fullname varchar(100), gender TINYINT, birthday date, phonenumber varchar(13),avatar varchar(225))
begin
insert into user values(username, password, fullname, gender,birthday,phonenumber,avatar);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spInsertUserRole` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInsertUserRole` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertUserRole`(username varchar(50), roleid int, datecreate datetime , creator varchar(50))
begin
insert into user_role values(username, roleid, datecreate, creator);
end */$$
DELIMITER ;

/* Procedure structure for procedure `spUpdateContent` */

/*!50003 DROP PROCEDURE IF EXISTS  `spUpdateContent` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spUpdateContent`(contentid varchar(20), contentname varchar(200),subid varchar(20))
begin
update content set content.contentname = contentname, content.subid = subid where content.contentid = contentid;
end */$$
DELIMITER ;

/* Procedure structure for procedure `spUpdateUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `spUpdateUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spUpdateUser`(username varchar(50), password varchar(200), fullname varchar(100), gender TINYINT, birthday date, phonenumber varchar(13),avatar varchar(225))
begin
update user set user.pwd = password,user.fullname= fullname,user.gender= gender,user.birthday = birthday,user.phonenumber = phonenumber,user.avatar = avatar where user.username = username;
end */$$
DELIMITER ;

/* Procedure structure for procedure `while_example` */

/*!50003 DROP PROCEDURE IF EXISTS  `while_example` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `while_example`()
wholeblock:BEGIN
  declare str VARCHAR(255) default '';
  declare x INT default 15110380;
  SET x = 15110380;
  WHILE x <= 15110480 DO
    insert into user(username,fullname) values(concat(str,x),'Y Zenny');
    SET x = x + 1;
  END WHILE;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
