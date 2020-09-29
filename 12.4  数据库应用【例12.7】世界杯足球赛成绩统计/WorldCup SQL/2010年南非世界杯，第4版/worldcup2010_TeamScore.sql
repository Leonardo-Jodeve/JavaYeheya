/*《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
  §12.4 数据库应用
  【例12.7】 世界杯足球赛成绩统计。
  （1）小组赛成绩表
*/

/*
CREATE DATABASE if not exists worldcup2010;

use worldcup2010;

/*小组赛成绩（组别，球队，国旗，场次，胜，平，负，进球，失球，净胜球，积分,排名）
CREATE TABLE TeamScore 
(                
     xgroup char(1) NOT NULL,      
     teamname char(20) NOT NULL,          
     completed int(4) DEFAULT '0',    
     win int(4) DEFAULT '0',          
     tie int(4) DEFAULT '0',          
     loss int(4) DEFAULT '0',         
     goalsfor int(4) DEFAULT '0',     
     goalsagaint int(4) DEFAULT '0',  
     netvalue int(4) DEFAULT '0',     
     score int(4) DEFAULT '0',        
     rank int(4) DEFAULT '0',         
   PRIMARY KEY (teamname)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

INSERT INTO TeamScore(xgroup,teamname)
  VALUES ('A', '南非'),('A', '墨西哥'),('A', '乌拉圭'),('A', '法国'),
         ('B', '阿根廷'),('B', '尼日利亚'),('B', '韩国'),('B', '希腊'),
         ('C', '英格兰'),('C', '美国'),('C', '阿尔及利亚'),('C', '斯洛文尼亚'),
         ('D', '德国'),('D', '澳大利亚'),('D', '塞尔维亚'),('D', '加纳'),
         ('E', '荷兰'),('E', '丹麦'),('E', '日本'),('E', '喀麦隆'),
         ('F', '意大利'),('F', '巴拉圭'),('F', '新西兰'),('F', '斯洛伐克'),
         ('G', '巴西'),('G', '朝鲜'),('G', '科特迪瓦'),('G', '葡萄牙'),
         ('H', '西班牙'),('H', '瑞士'),('H', '洪都拉斯'),('H', '智利');
         */
         
/*数据查询*/
SELECT * FROM teamscore ORDER BY xgroup,teamname;



