/*《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年8月26日
  §12.4 数据库应用
  【例12.7】 世界杯足球赛成绩统计。
  （1）小组赛成绩表
*/

/*
CREATE DATABASE worldcup2018;
*/
use worldcup2018;

/*小组赛成绩（组别，球队，国旗，场次，胜，平，负，进球，失球，净胜球，积分,排名）*/
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
  VALUES ('A', '俄罗斯'), ('A', '乌拉圭'), ('A', '埃及'), ('A', '沙特'),
          ('B', '葡萄牙'), ('B', '西班牙'), ('B', '伊朗'), ('B', '摩洛哥'),
          ('C','法国'), ('C','丹麦'), ('C','秘鲁'), ( 'C','澳大利亚'),
          ('D', '阿根廷'), ('D', '克罗地亚'), ('D', '冰岛'), ('D', '尼日利亚'),
          ('E', '巴西'), ('E', '瑞士'), ('E', '哥斯达黎加'), ('E', '塞尔维亚'),
          ('F', '德国'), ('F', '墨西哥'), ('F', '瑞典'), ('F', '韩国'),
          ('G', '比利时'), ('G', '英格兰'), ('G', '突尼斯'), ('G', '巴拿马'),
          ('H', '波兰'), ('H', '哥伦比亚'), ('H', '塞内加尔'), ('H', '日本');



/*不能执行以下语句，什么安全模式
DELETE FROM TeamScore;


DELETE FROM TeamScore
    WHERE xgroup='A';



  UPDATE TeamScore
     SET teamname='沙特'
  WHERE teamname='沙特阿拉伯';
  
/*  
  DROP TABLE TeamScore;

DELETE FROM TeamScore;
         
/*数据查询
SELECT * FROM teamscore ORDER BY xgroup,teamname;
*/
/*@author：Yeheya，2018年8月27日*/