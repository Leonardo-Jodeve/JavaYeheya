<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.1】显示当前日期时间的JSP文档
-->                                                        <%--JSP标记注释，HTML文档中不可见--%> 

<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html; charset=GBK" %>         <%--JSP编译指令--%>
<html>
  <head><title>当前日期时间</title></head>
  <body>
    <strong><font color="blue" face="华文楷体" size="4">    <%--设置字体和颜色--%>
      <%=new SimpleDateFormat("yyyy年MM月dd日E HH时mm分ss秒").
             format(new Date())%><br></font></strong>    <%--常量和表达式 --%>
  </body>
</html>

<!--@author：Yeheya，2018年4月1日-->