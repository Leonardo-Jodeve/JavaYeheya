DELIMITER $$


CREATE	TRIGGER trigger_update_match BEFORE/AFTER INSERT/UPDATE/DELETE on match	//创建触发器
FOR EACH ROW BEGIN
  for update
  as 
    if @@rowcount>0
      begin 
		  UPDATE "groupscore"  								//积分
	   	  SET "score" = win * 3+ tie  
        
		  UPDATE "groupscore"  
		     SET "netvalue" = goalsfor - goalsagainst  //净胜球

      end
END$$

DELIMITER ;