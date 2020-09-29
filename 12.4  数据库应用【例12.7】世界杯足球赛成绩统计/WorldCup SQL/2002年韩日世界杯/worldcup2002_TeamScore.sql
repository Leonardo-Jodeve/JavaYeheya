/*小组赛成绩（组别，球队，国旗，场次，胜，平，负，进球，失球，净胜球，积分,排名）
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
)*/

INSERT INTO TeamScore(xgroup,teamname)
  VALUES ('A', '法国'),('A', '塞内加尔'),('A', '乌拉圭'),('A', '丹麦'),
         ('B', '西班牙'),('B', '斯洛文尼亚'),('B', '巴拉圭'),('B', '南非'),
         ('C', '巴西'),('C', '土耳其'),('C', '哥斯达黎加'),('C', '中国'),
         ('D', '葡萄牙'),('D', '美国'),('D', '韩国'),('D', '波兰'),
         ('E', '德国'),('E', '爱尔兰'),('E', '喀麦隆'),('E', '沙特'),
         ('F', '阿根廷'),('F', '英格兰'),('F', '瑞典'),('F', '尼日利亚'),
         ('G', '意大利'),('G', '墨西哥'),('G', '克罗地亚'),('G', '厄瓜多尔'),
         ('H', '比利时'),('H', '俄罗斯'),('H', '日本'),('H', '突尼斯');

