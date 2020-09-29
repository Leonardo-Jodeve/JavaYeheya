/*《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月5日
   第10章  数据库应用
   【例10.1】 连接MySQL数据库服务端，获得studentmis数据库属性信息。
   【例10.2】 显示数据查询结果集。
   【例10.3】 交叉统计表。
*/

/*创建数据库
CREATE DATABASE StudentMIS;
use StudentMIS;

/*person表，包含姓名（不空）、出生日期（有默认值）、性别、省份、城市列；
性别列检查用户定义完整性；姓名列是主键，表级实体完整性约束
CREATE TABLE person
(
    name varchar(20) NOT NULL PRIMARY KEY,
    birthdate date DEFAULT '1997-1-1',
    gender char(2) DEFAULT '男' CHECK(gender IN('男','女')),
    province varchar(20) DEFAULT NULL,
    city varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*插入数据，每执行INSERT INTO一句，插入一行，执行触发器1次
INSERT INTO person  VALUES ('陈珊',  '1997-02-01','女','广东','广州');
INSERT INTO person  VALUES ('李言谦','1998-10-03','男','湖北','武汉');
INSERT INTO person  VALUES ('杨柳',  '1999-05-10','男','湖南','长沙');
INSERT INTO person  VALUES ('傅玲玲','1997-10-08','女','浙江','绍兴');


/*插入数据，插入多行。触发器只执行1次，设置断点，可单步调试触发器。？？
INSERT INTO person
  VALUES ('陈珊',  '1994-02-01','女','广东','广州'),
         ('李言谦','1994-10-03','男','湖北','武汉'),
         ('杨柳',  '1994-05-10','男','湖南','长沙'),
         ('傅玲玲','1992-10-08','女','浙江','绍兴');
*/

/*数据更新，插入行、删除行和更新行，执行SQL语句，返回执行成功影响的行数
use StudentMIS;
UPDATE person
  SET province='湖北', city='武汉'
  WHERE name='陈珊';
  
  
/*
INSERT INTO person  VALUES ('齐小天','2000-01-1','男','江苏','南京');
       
DELETE FROM person WHERE(name='齐小天');
        
INSERT INTO person  VALUES ('齐小天','2000-01-1','男','江苏','扬州');
        
UPDATE person
  SET province='江苏', city='南京'
  WHERE name='齐小天';


DELETE FROM person WHERE(name='齐小天' or  name='陈珊');
*/

/*数据查询
SELECT * FROM person ORDER BY province;
*/

/*数据查询，将每列标题以别名显示。其中AS可省略
SELECT name AS 姓名, birthdate AS 出生日期, gender AS 性别, province AS 省份, city AS 城市
    FROM  person
    ORDER BY birthdate

/*数据查询
SELECT *
  FROM person 
  WHERE (gender='男') AND (province like '湖%') OR (province like '%江%')
  ORDER BY province;


/*按省份分组统计各省人数，按省份排序
SELECT province AS 省份, COUNT(name) AS 人数
  FROM person
  GROUP BY province
  ORDER BY 人数;
*/


/*数据查询，增加年龄计算列，SQL Server 2012语法成功，MySQL不行，没有YEAR(GETDATE())
SELECT name, birthdate, gender, province, city, YEAR(GETDATE())-YEAR(birthdate) AS age
    FROM  person
    ORDER BY age 
*/
/*@author：Yeheya，2018年3月14日*/