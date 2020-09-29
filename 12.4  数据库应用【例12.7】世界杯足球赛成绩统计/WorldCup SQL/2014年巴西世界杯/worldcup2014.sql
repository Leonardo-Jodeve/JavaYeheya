create database if not exists `worldcup2014`;

USE `worldcup2014`;

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