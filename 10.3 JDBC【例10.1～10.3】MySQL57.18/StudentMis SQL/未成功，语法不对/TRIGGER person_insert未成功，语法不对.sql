/* 触发器，当在person表中插入一行数据时，将其中(省,市)插入到cities表，不重复。
   限制：每条INSERT INTO语句只插入一行数据，触发“插入触发器”一次，插入一行的(省,市)。
   没有主外键关系，也可以创建触发器。*/  
CREATE TRIGGER person_insert
  ON person
  AFTER INSERT 
AS
BEGIN
    IF @@ROWCOUNT=0
        RETURN
    DECLARE @province varchar(20), @city varchar(20)
    
    SELECT @province=province, @city=city
      FROM inserted
      
    IF NOT EXISTS
       ( SELECT province, city 
          FROM cities
          WHERE province=@province AND city=@city)
      INSERT INTO cities
         VALUES (@province, @city)
End;
/*其中，在inserted表中查找刚插入的一个(省,市)元组；此时已执行person表的插入操作，inserted表存储插入行副本；
  先在cities表中查询(@province, @city)元组，若不存在则插入；
  若无IF语句，当插入cities表不成功时，则终止触发器，不插入当前行到person表*/
