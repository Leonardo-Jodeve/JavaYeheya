/*《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月10日
  §12.4 数据库应用
  【例12.7】 世界杯足球赛成绩统计。
  （1）小组赛成绩表
*/

/*
CREATE DATABASE if not exists worldcup2014;
*/
USE worldcup2014;

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
