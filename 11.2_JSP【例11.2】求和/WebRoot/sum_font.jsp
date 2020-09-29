<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.2】求和。
【思考题11-2】 ① 使用HTML标记<font></font>，将数字和运算符分别设置成不同的颜色。
--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head>
    <title>求和</title>
  </head>
  <body>
    <% int n=(int)(Math.random()*10),s=0;%>
    Sum(<%=n%>)=
    <%for(int i=1;i<n;i++)  {%>
        <font color="blue"><%=i%></font>
        <%s=s+i;%>
        <font color="red">+</font>公式
    <% } %>
    <font color="blue"><%=n%></font>
    <font color="red"> = </font>
    <font color="blue"><%=(s+n)%></font>
  </body>
</html>
<!--@author：Yeheya，2018年3月22日-->  