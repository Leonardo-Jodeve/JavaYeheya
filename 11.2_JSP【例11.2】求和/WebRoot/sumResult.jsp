<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【实验11-1】求和，HTML文档输入，JSP文档计算。
（2）JSP动态文档，获得输入数据，计算并以表达式形式显示结果。
        使用request对象获得HTML文档提交的输入数据，字符串形式，
        计算，并将结果嵌入到HTML文档显示。
--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head><title>求和结果</title></head>
  <body>
    <%String str = request.getParameter("n");    //使用request对象获得变量n值，字符串
      int n=Integer.parseInt(str),s=0;           //将str字符串转换成int整数，未处理异常%>
      Sum(<%=n%>)=
      <%for(int i=1; i<n; i++)  {%>
        <%=i%>+
        <%s+=i;
      }%>
      <%=n%>=<%=(s+n)%>
  </body>
</html>
<!--@author：Yeheya，2018年3月22日-->