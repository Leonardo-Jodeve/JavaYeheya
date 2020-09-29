
CREATE TABLE course                            /*创建课程表*/
(
	course_id char(10) NOT NULL,  
    course_name char(20) DEFAULT NULL,    
    credit_hour float(5,1) DEFAULT NULL,  
    period int(11) DEFAULT NULL,          
    PRIMARY KEY (course_id),
    CHECK(credit_hour>=0),     /*指定credit_hour列的用户定义完整性约束条件为值大于0*/
    CHECK(period>=0)            /*指定period列的用户定义完整性约束条件为数据值大于0*/
);


CREATE TABLE stugrade
(
     stu_id     char(10) NOT NULL,
     course_id  char(10) NOT NULL,
     grade      float(5,2) DEFAULT NULL,
  PRIMARY KEY(stu_id, course_id),
  FOREIGN KEY(stu_id) REFERENCES stuinfo(stu_id),     /*外键*/
  FOREIGN KEY(course_id) REFERENCES course(course_id),
  CHECK(grade BETWEEN 0 AND 100)                     /*指定grade列取值范围是0～100*/
);
