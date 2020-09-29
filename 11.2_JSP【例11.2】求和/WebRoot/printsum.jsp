<!--《Java程序设计实用教程（第4版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.2】求和，输出计算表达式。
没有输入，使用随机数作为输入值，输出计算表达式。--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head>
    <title>求和</title>
  </head>
  <body>
    <%int n=(int)(Math.random()*10),s=0;%>
    Sum(<%=n%>) =
    <%for(int i=1;i<n;i++)  {%>
       <%=i%> + 
       <%s+=i;
    } %>
    <%=n%> = <%=(s+n)%>
  </body>
</html>
<!--@author：Yeheya，2018年3月22日-->  