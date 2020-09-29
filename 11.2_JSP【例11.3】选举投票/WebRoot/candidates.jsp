<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.3】选举投票。
（1）输入候选人页面
--> 

<%@ page language="java"  import="java.util.*"  import="design.*" 
         contentType="text/html; charset=GBK" %>
<html>
  <head><title>输入候选人</title>
  </head>
  
  <body>
    <form name="form1" method="post" action="candidates.jsp"> <%--表单，提交给自己--%>
                  输入候选人： <input type=text name="name"><br>         <%--输入name--%>
         <input type="submit" value="提交"><br>                <%--“提交”按钮，单击时提交name变量值--%>
    </form>
    <% //以下响应请求，显示输入的所有候选人。下句获得属性值
       List<String> candidates = (ArrayList<String>)application.getAttribute("candidates");
       if(candidates==null)
           candidates=new ArrayList<String>();          //数组列表，存储候选人集合
         
       String name = request.getParameter("name");      //使用request对象获得表单提交的name值，字符串类型
       if(name!=null && !name.equals(""))
       {
           candidates.add(name);                        //列表中添加当前输入的一个候选人
           application.setAttribute("candidates", candidates);   //设置application对象的candidates属性值
       }%>
         候选人：<br>
    <%=design.MyCollection.toString(candidates)%>       <%//调用方法，返回集合元素字符串%>
  </body>
</html>
<!--@author：Yeheya，2018年4月7日-->