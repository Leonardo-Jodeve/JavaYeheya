/*比赛记录（场次，比赛时间，组别，球队1，球队2，队1进球数，队2进球数，比赛地点）*/
CREATE TABLE MatchRecord 
(             
     number int(4) DEFAULT '0',         
     fixture datetime DEFAULT NULL,       
     xgroup char(1) NOT NULL,             
     teamname1 char(20) NOT NULL,         
     teamname2 char(20) NOT NULL,         
     goalsfor1 int(4) DEFAULT NULL,      
     goalsfor2 int(4) DEFAULT NULL,
	 venue char(20) DEFAULT NULL,  
   PRIMARY KEY (teamname1,teamname2),
   FOREIGN KEY(teamname1) REFERENCES TeamScore(teamname),
   FOREIGN KEY(teamname2) REFERENCES TeamScore(teamname)
);

/*
DELETE FROM MatchRecord;*/