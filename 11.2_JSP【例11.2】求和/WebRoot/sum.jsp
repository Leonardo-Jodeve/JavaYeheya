<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.2】求和，JSP文档中声明函数。
--> 

<%@ page contentType="text/html; charset=GBK" %>
<html>
  <head><title>求和</title></head>
  <body> 
    <%! int sum(int n)                           //声明函数
        {
    	    int s=0;
            for(int i=1; i<=n; i++)
                s+=i;
            return s;
        } %>
    <%  int n=(int)(Math.random()*10);%>         <%//声明变量，产生随机数%>
    Sum(1+…+<%=n%>) = <%=sum(n)%>                <%/*声明函数*/%>
  </body>
</html>

<!--【思考题11-2】 ② 增加输入功能，以表达式形式显示计算过程和结果。
说明：可以声明函数，但想在函数里显示计算过程和结果表达式，未成功。
  -->
<!--@author：Yeheya，2018年3月22日-->  