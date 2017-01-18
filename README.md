# SIMS
### This is a SIMS.(Student Information Management System)
<br/> Login UI:
![image](https://github.com/Sunbelife/SIMS/blob/master/info/login.png)
<br/> Manage UI:
![image](https://github.com/Sunbelife/SIMS/blob/master/info/manage.png)
<br/> Watch UI:
![image](https://github.com/Sunbelife/SIMS/blob/master/info/watch.png)
<br/>
What I have done yet:</br>
1.Insert, modify, delete and add data to your database.</br>
2.Swing GUI.</br>
To Use this Project, you need to prepare two tables in your database:</br>
</br>
UserandPassword</br>
+----------+--------------+------+-----+---------+-------+</br>
| Field    | Type         | Null | Key | Default | Extra |</br>
+----------+--------------+------+-----+---------+-------+</br>
| Username | varchar(255) | NO   |     | NULL    |       |</br>
| Password | varchar(255) | NO   |     | NULL    |       |</br>
| TYPE     | int(2)       | NO   |     | NULL    |       |</br>
+----------+--------------+------+-----+---------+-------+</br>
</br>
People</br>
+-------+-----------+------+-----+---------+-------+</br>
| Field | Type      | Null | Key | Default | Extra |</br>
+-------+-----------+------+-----+---------+-------+</br>
| ID    | int(11)   | NO   | PRI | NULL    |       |</br>
| NAME  | char(255) | YES  |     | NULL    |       |</br>
| Type  | int(11)   | YES  |     | NULL    |       |</br>
| Phone | char(13)  | YES  |     | NULL    |       |</br>
| Age   | int(11)   | YES  |     | NULL    |       |</br>
+-------+-----------+------+-----+---------+-------+</br>
and Insert some test data:</br>
</br>
INSERT INTO People values(1,'江泽民',0,'32934232',21),</br>
(2,'胡锦涛',0,'110',74),</br>
(3,'梅德韦杰夫',0,'1432980',42),</br>
(4,'史蒂夫乔布斯',0,'1132840',45),</br>
(5,'鲍尔默',0,'19834310',94),</br>
(6,'比尔盖茨',0,'4539110',56),</br>
(7,'盛田昭夫',0,'38903110',32),</br>
(8,'朴槿惠',0,'149810',43),</br>
(9,'特朗普',0,'48904110',73),</br>
(10,'希拉里',0,'54839110',43),</br>
(11,'马英九',0,'4235110',34),</br>
(12,'阿长',0,'34532110',44),</br>
(13,'胡金秀',0,'2342110',54),</br>
(14,'冯绍峰',0,'543110',74),</br>
(15,'杨幂',0,'523110',34),</br>
(16,'陈奕迅',0,'432110',37),</br>
(17,'张泽明',0,'7876110',64),</br>
(18,'梅德韦杰夫',0,'6575110',73),</br>
(19,'山海经',0,'456110',75),</br>
(20,'奥巴马',0,'7684110',70),</br>
(21,'老布什',0,'445110',43),</br>
(22,'小布什',0,'845110',54),</br>
(23,'戈尔巴乔夫',0,'945110',74),</br>
(24,'卡巴斯基',0,'24110',34),</br>
(25,'秃了光机',0,'543110',35),</br>
(26,'巴普洛夫',0,'765110',36),</br>
(27,'弗洛伊德',0,'654110',39);</br>
</br>
And then, run the Main.java.
