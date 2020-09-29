/*《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月5日
   第10章  数据库应用
*/

use StudentMIS;

/*城市表，包含省份、城市属性，省份、城市都不空，作为主键
CREATE TABLE cities
(
    province varchar(20) NOT NULL,
    city     varchar(20) NOT NULL,
    PRIMARY KEY (province, city)
);

/*插入数据，为所有列赋值，添加多行
INSERT INTO cities
  VALUES ('安徽', '合肥'), ('安徽', '马鞍山'), ('安徽', '宿县'), ('安徽', '宣城'),
         ('福建', '南平'), ('福建', '泉州'),
         ('甘肃', '白银'),
         ('广东', '茂名'), ('广东', '韶关'), ('广东', '湛江'),
         ('广西', '梧州'),
         ('贵州', '毕节'), ('贵州', '铜仁'),
         ('湖南', '常德'),
         ('江苏', '淮阴'), ('江苏', '江阴'), ('江苏', '连云港'), ('江苏', '南通'), ('江苏', '泗阳'), 
         ('江苏', '无锡'), ('江苏', '徐州'), ('江苏', '扬州'),
         ('江西', '九江'),
         ('青海', '西宁'),
         ('山东', '日照'),
         ('陕西', '商洛'), ('陕西', '延安'),
         ('浙江', '金华'), ('浙江', '衢州'), ('浙江', '绍兴');
*/

/*查询cities表中所有列，按省份、城市排序
SELECT *
  FROM cities
  ORDER BY province,city
*/
/*
查询cities表中省份列的不重复值，按省份排序 
SELECT DISTINCT province
  FROM cities
  ORDER BY province
  
  
-- 查询cities表，统计各省的城市数，按省份分组统计，按省份排序 
SELECT DISTINCT province AS 省, count(city) AS 城市数
  FROM cities
  GROUP By province
  ORDER BY province
*/