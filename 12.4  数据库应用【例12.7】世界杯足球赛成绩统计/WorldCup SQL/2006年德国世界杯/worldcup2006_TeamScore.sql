create database if not exists `worldcup2006`;

USE `worldcup2006`;

/*小组赛成绩（组别，球队，国旗，场次，胜，平，负，进球，失球，净胜球，积分,排名）*/
CREATE TABLE TeamScore 
(                
     xgroup char(1) NOT NULL,      
     teamname char(20) NOT NULL,          
     completed int(11) DEFAULT '0',    
     win int(11) DEFAULT '0',          
     tie int(11) DEFAULT '0',          
     loss int(11) DEFAULT '0',         
     goalsfor int(11) DEFAULT '0',     
     goalsagaint int(11) DEFAULT '0',  
     netvalue int(11) DEFAULT '0',     
     score int(11) DEFAULT '0',        
     rank int(11) DEFAULT '0',         
   PRIMARY KEY (teamname)
);


INSERT INTO TeamScore(xgroup,teamname)
  VALUES ('A', '德国'),('A', '哥斯达黎加'),('A', '波兰'),('A', '厄瓜多尔'),
         ('B', '英格兰'),('B', '瑞典'),('B', '巴拉圭'),('B', '特立尼达和多巴哥'),
         ('C', '阿根廷'),('C', '荷兰'),('C', '科特迪瓦'),('C', '塞黑'),
         ('D', '墨西哥'),('D', '葡萄牙'),('D', '伊朗'),('D', '安哥拉'),
         ('E', '意大利'),('E', '捷克'),('E', '加纳'),('E', '美国'),
         ('F', '巴西'),('F', '克罗地亚'),('F', '澳大利亚'),('F', '日本'),
         ('G', '法国'),('G', '瑞士'),('G', '多哥'),('G', '韩国'),
         ('H', '西班牙'),('H', '乌克兰'),('H', '突尼斯'),('H', '沙特');
/*
SELECT COUNT(xgroup)
  FROM TeamScore
 WHERE xgroup='A'


SELECT *
  FROM TeamScore
 ORDER BY xgroup
 
SELECT * FROM TeamScore ORDER BY xgroup,rank

/*
DELETE FROM TeamScore



/*
//参赛队（组别，球队，国旗）
ALTER TABLE TeamScore
    ADD jack image

//     icon bigint(20) DEFAULT NULL,    
*/


/*
  INSERT INTO TeamScore  VALUES (1, 'A', '德国', null, 3, 3, 0, 0, 8, 2, 6, 9)
  INSERT INTO TeamScore  VALUES (2, 'A', '厄瓜多尔', null, 3, 2, 0, 1, 5, 3, 2, 6)
  INSERT INTO TeamScore  VALUES (3, 'A', '波兰', null, 3, 1, 0, 2, 2, 4, -2, 3)
  INSERT INTO TeamScore  VALUES (4, 'A', '哥斯达黎加', null, 3, 0, 0, 3, 3, 9, -6, 0)
  INSERT INTO TeamScore  VALUES (1, 'B', '英格兰', null, 3, 2, 1, 0, 5, 2, 3, 7)
  INSERT INTO TeamScore  VALUES (2, 'B', '瑞典', null, 3, 1, 2, 0, 3, 2, 1, 5)
  INSERT INTO TeamScore  VALUES (3, 'B', '巴拉圭', null, 3, 1, 0, 2, 2, 2, 0, 3)
  INSERT INTO TeamScore  VALUES (4, 'B', '特立尼达和多巴哥', null, 3, 0, 1, 2, 0, 4, -4, 1)
  INSERT INTO TeamScore  VALUES (1, 'C', '阿根廷', null, 3, 2, 1, 0, 8, 1, 7, 7)
  INSERT INTO TeamScore  VALUES (2, 'C', '荷兰', null, 3, 2, 1, 0, 3, 1, 2, 7)
  INSERT INTO TeamScore  VALUES (3, 'C', '科特迪瓦', null, 3, 1, 0, 2, 5, 6, -1, 3)
  INSERT INTO TeamScore  VALUES (4, 'C', '塞黑', null, 3, 0, 0, 3, 2, 10, -8, 0)
  INSERT INTO TeamScore  VALUES (1, 'D', '葡萄牙', null, 3, 3, 0, 0, 5, 1, 4, 9)
  INSERT INTO TeamScore  VALUES (2, 'D', '墨西哥', null, 3, 1, 1, 1, 4, 3, 1, 4)
  INSERT INTO TeamScore  VALUES (3, 'D', '安哥拉', null, 3, 0, 2, 1, 1, 2, -1, 2)
  INSERT INTO TeamScore  VALUES (4, 'D', '伊朗', null, 3, 0, 1, 2, 2, 6, -4, 1)
  INSERT INTO TeamScore  VALUES (1, 'E', '意大利', null, 3, 2, 1, 0, 5, 1, 4, 7)
  INSERT INTO TeamScore  VALUES (2, 'E', '加纳', null, 3, 2, 0, 1, 4, 3, 1, 6)
  INSERT INTO TeamScore  VALUES (3, 'E', '捷克', null, 3, 1, 0, 2, 3, 4, -1, 3)
  INSERT INTO TeamScore  VALUES (4, 'E', '美国', null, 3, 0, 1, 2, 2, 6, -4, 1)
  INSERT INTO TeamScore  VALUES (1, 'F', '巴西', null, 3, 3, 0, 0, 7, 1, 6, 9)
  INSERT INTO TeamScore  VALUES (2, 'F', '澳大利亚', null, 3, 1, 1, 1, 5, 5, 0, 4)
  INSERT INTO TeamScore  VALUES (3, 'F', '克罗地亚', null, 3, 0, 2, 1, 2, 3, -1, 2)
  INSERT INTO TeamScore  VALUES (4, 'F', '日本', null, 3,  0, 1, 2, 2, 7, -5, 1)
  INSERT INTO TeamScore  VALUES (1, 'G', '瑞士', null, 3, 2, 1, 0, 4, 0, 4, 7)
  INSERT INTO TeamScore  VALUES (2, 'G', '法国', null, 3, 1, 2, 0, 3, 1, 2, 5)
  INSERT INTO TeamScore  VALUES (3, 'G', '韩国', null, 3, 1, 1, 1, 3, 4, -1, 4)
  INSERT INTO TeamScore  VALUES (4, 'G', '多哥', null, 3, 0, 0, 3, 1, 6, -5, 0)
  INSERT INTO TeamScore  VALUES (1, 'H', '西班牙', null, 3, 3, 0, 0, 8, 1, 7, 9)
  INSERT INTO TeamScore  VALUES (2, 'H', '乌克兰', null, 3, 2, 0, 1, 5, 4, 1, 6)
  INSERT INTO TeamScore  VALUES (3, 'H', '突尼斯', null, 3, 0, 1, 2, 3, 6, -3, 1)
  INSERT INTO TeamScore  VALUES (4, 'H', '沙特', null, 3, 0, 1, 2, 2, 7, -5, 1)

*/
/*小组排名
SELECT *
  FROM TeamScore
 WHERE xgroup='A'
 ORDER BY score DESC, netvalue DESC, goalsfor DESC     */
/*
UPDATE TeamScore SET rank=1 WHERE teamname='德国';
UPDATE TeamScore SET rank=2 WHERE teamname='厄瓜多尔';
UPDATE TeamScore SET rank=3 WHERE teamname='哥斯达黎加';
UPDATE TeamScore SET rank=4 WHERE teamname='波兰';

SELECT *
  FROM TeamScore
 ORDER BY xgroup, rank    
*/
/*没起作用????
   CHECK(xgroup BETWEEN 'A' AND 'H')               
*/