<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.3 JSP
【例11.】发送和接收当前时间。
        使用JSP的session隐含对象在两个JSP文档之间传递数据
        接收时间
--> 
 
<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html;charset=GBK" %>              <%--JSP编译指令--%>
<html>
  <head>
    <title>接收时间</title>
  </head>
  <body>
    <strong><font color="blue" face="华文楷体" size="4">       <%--设置字体和颜色--%>
    <%int height=((Integer)application.getAttribute("height")).intValue();
      out.print("height="+height+"<br>");
      String datestr=(String)session.getAttribute("datestr");
      SimpleDateFormat datef = (SimpleDateFormat)session.getAttribute("datef");
      Date time = (Date)session.getAttribute("time");                 
      out.print("time="+datef.format(time)+"<br>");
      out.println("session.getId()="+session.getId());
    %><br></font></strong>
  </body>
</html>   

<!--@author：Yeheya，2018年3月23日-->