/*创建stuinfo表，并插入数据 */
/*create database student;                          创建数据库*/
CREATE TABLE stuinfo                              /*创建表*/
(
    stu_id char(10) NOT NULL,                     /*学号，不空*/
    stu_name char(10) DEFAULT NULL,               /*姓名，默认空值*/
    sex char(2) DEFAULT NULL,                     /*性别*/
    province char(10) DEFAULT NULL,               /*省份*/
    city char(10) DEFAULT NULL,                   /*城市*/
    birthday date DEFAULT NULL,                   /*出生日期*/
    department char(20) DEFAULT NULL,             /*系*/
    speciality char(20) DEFAULT NULL,             /*专业*/
    member int(1) DEFAULT '1',                /*团员，默认1*/
    PRIMARY KEY (stu_id)                          /*声明学号列为主键*/
);
/*
INSERT INTO stuinfo                               /*插入数据，为所有列赋值*/
  VALUES ('1012012001','陈珊',  '女','广东','广州','1994-02-01','计算机','计算机科学与技术',1),
         ('1012012002','李言谦','男','湖北','武汉','1994-10-03','计算机','计算机科学与技术',1),
         ('1012012003','杨柳',  '男','湖南','长沙','1994-05-10','计算机','计算机科学与技术',1);

INSERT INTO stuinfo(stu_id, stu_name, sex, province, city, birthday)  /*指定列，其余列取默认值*/
  VALUES ('0922011002','傅玲玲','女','浙江','绍兴','1992-10-08');

UPDATE stuinfo  
  SET department = '经济',
      speciality = '信息管理与信息系统'
  WHERE stu_id = '0922011002';
/*
DELETE FROM stuinfo
  WHERE stu_id = '0922011002';*/

SELECT *
  FROM stuinfo;  

SELECT stu_name, sex, province, city
  FROM stuinfo  
  WHERE (sex='女') AND (province='广东') OR (province like '湖%')
  ORDER BY province;

SELECT COUNT(DISTINCT province)
  FROM stuinfo;

SELECT province, COUNT(stu_name) 
  FROM stuinfo  
  GROUP BY province
  ORDER BY COUNT(stu_name);

/*
INSERT INTO stuinfo (stu_id, stu_name, sex, province, city, birthday)
  VALUES ('1012012001', '蔡尧强', '男', '浙江', '金华',   '1994-01-20'),
         ('1012012002', '冯正亚', '女', '江苏', '连云港', '1994-01-09'), 
         ('1012012003', '傅建玲', '女', '浙江', '绍兴',   '1994-05-22'),
         ('1012012004', '韩家雄', '男', '江苏', '南通',   '1994-04-30'),
         ('1012012006', '江海山', '男', '安徽', '合肥',   '1994-02-03'),
         ('1012012007', '金明',   '男', '安徽', '马鞍山', '1993-11-05'),
         ('1012012008', '李翠',   '女', '陕西', '延安',   '1994-03-01'),
         ('1012012009', '李维华', '女', '青海', '西宁',   '1993-11-06'),
         ('1012012010', '梁小麟', '男', '广东', '茂名',   '1994-03-05'),
         ('1012012011', '林水宝', '男', '福建', '泉州',   '1994-02-10'),
         ('1012012012', '刘洪星', '男', '山东', '日照',   '1993-10-01'),
         ('1012012013', '刘绍军', '男', '湖南', '常德',   '1994-02-13'),
         ('1012012014', '吕修福', '男', '江苏', '连云港', '1993-12-16'),
         ('1012012015', '祁晶',   '男', '甘肃', '白银',   '1994-04-02'),
         ('1012012016', '钦海蓉', '男', '江苏', '江阴',   '1993-09-10'),
         ('1012012017', '邵斯林', '男', '广西', '梧州',   '1992-11-18'),
         ('1012012018', '沈瀚斐', '男', '江苏', '无锡',   '1995-09-01'),
         ('1012012019', '盛梅兰', '女', '浙江', '金华',   '1994-10-15'),
         ('1012012020', '石健',   '男', '贵州', '毕节',   '1993-10-09'),
         ('1012012021', '王雄川', '男', '浙江', '金华',   '1994-08-01'),
         ('1012012022', '魏晓宇', '男', '江苏', '扬州',   '1993-12-21'),
         ('1012012023', '肖颖',   '女', '福建', '南平',   '1994-03-15'),
         ('1012012024', '谢吉吉', '男', '广东', '韶关',   '1994-05-10'),
         ('1012012025', '徐丹',   '女', '江苏', '南通',   '1993-09-21'),
         ('1012012026', '徐远哲', '男', '安徽', '宿县',   '1994-07-10'),
         ('1012012027', '许明',   '男', '山东', '日照',   '1993-11-12'),
         ('1012012028', '严伟',   '男', '浙江', '衢州',   '1993-12-19'),
         ('1012012029', '杨旭',   '男', '贵州', '铜仁',   '1994-10-09'),
         ('1012012030', '杨训',   '男', '安徽', '宿县',   '1994-03-02'),
         ('1012012031', '姚远秀', '男', '陕西', '商洛',   '1994-10-13'),
         ('1012012032', '易蝉鸣', '女', '广东', '湛江',   '1993-09-16'),
         ('1012012033', '张建',   '男', '江苏', '淮阴',   '1994-04-11'),
         ('1012012034', '张苏荣', '男', '安徽', '宣城',   '1994-10-23'),
         ('1012012037', '周雷',   '男', '江苏', '徐州',   '1995-01-02'),
         ('1012012038', '朱强光', '男', '江苏', '泗阳',   '1993-10-19'),
         ('1012012040', '邹志敏', '男', '江西', '九江',   '1994-06-01');

UPDATE stuinfo  
  SET department = '计算机系',
      speciality = '计算机科学与技术'
  WHERE stu_id >='1012012001';

SELECT stu_name, sex, province, city
  FROM stuinfo  
  WHERE (sex='女') AND (province='江苏');

SELECT stu_name, sex, province, city
  FROM stuinfo  
  WHERE (sex='女') AND (province='江苏') OR (province like '广%')
  ORDER BY province

SELECT province, COUNT(stu_name)
  FROM stuinfo  
  GROUP BY province HAVING COUNT(stu_name)>4
  ORDER BY COUNT(stu_name)

SELECT COUNT(province)
  FROM stuinfo  

SELECT COUNT(*)
  FROM stuinfo  

  

SELECT DISTINCT province
  FROM stuinfo  
*/

