<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.3 JSP
【例11.】发送和接收当前时间。
        使用JSP的session隐含对象在两个JSP文档之间传递数据
        发送时间
--> 
 
<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html;charset=GBK" %>             <%--JSP编译指令--%>
<html>
  <head>
    <title>发送时间</title>
  </head>
  <body>
    <form name="form1" method="post" action="printTime.jsp">
    <strong><font color="blue" face="华文楷体" size="4">       <%--设置字体和颜色--%>
            当前日期时间:
    <%String datestr="yyyy年MM月dd日E ahh时mm分ss秒";
      SimpleDateFormat datef = new SimpleDateFormat(datestr);
      Date time = new Date();
      out.println(datef.format(time)+"<br>");
      out.println("session.getId()="+session.getId());
    
      application.setAttribute("height", 182);
      session.setAttribute("datestr", datestr);
      session.setAttribute("datef", datef);
      session.setAttribute("time", time); 
    %><br></font></strong>
    <td colspan="2">
    <input type="submit" value="提交"></form>
  </body>
</html>   

<!--@author：Yeheya，2018年3月23日-->