<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【实验11-1】求和，JSP文档输入、计算并显示结果表达式，调用类中方法。
--> 

<%@ page language="java" import="design.*" contentType="text/html; charset=GBK"%>
<html>
  <head><title>求和</title></head>
  <body>
    <%String str = request.getParameter("n");    //使用request对象获得变量n值，字符串
      int n=10; 
      if(str!=null)   
          n=Integer.parseInt(str);               //将str字符串转换成int整数，未处理异常%>

    <form name="form1" method="post" action="sumCalcu.jsp"> <%--表单，提交给自己--%>
      Sum(1+……+<input type=text name="n" value=<%=n%>>)    <%--输入n表示求和范围--%>
      <input type=submit value="="><br>                    <%--单击“=”提交n变量值--%>
      <%=design.Calculation.sumToString(n)%>
    </form>
  </body>
</html>

<!--@author：Yeheya，2018年3月25日-->