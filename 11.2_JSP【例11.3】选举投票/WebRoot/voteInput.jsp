<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.3】选举投票。
（2）显示候选人进行投票操作页面
--> 

<%@ page language="java"  import="java.util.*"  import="java.text.*" 
         contentType="text/html; charset=GBK" %>
<html>
  <head><title>选举投票</title>
  </head>
  
  <body>
    <form name="form1" method="post" action="voteResult.jsp">      <%--提交给另一JSP处理--%>
                   候选人：<br>
    <%
//     List<String> candidates = new ArrayList<String>();  //获得list属性值
//       candidates.add("Mars");            //火星，战神
//       candidates.add("Helios");          //太阳神
 //      candidates.add("Venus");           //维纳斯

       //以下采用单选按钮显示所有候选人，选中一人或输入其他候选人后提交
       List<String> candidates = (ArrayList<String>)application.getAttribute("candidates");//获得属性值
       for(int i=0; i<candidates.size(); i++) {%>
           <input type="radio" name="name" value="<%=candidates.get(i)%>">
                  <%out.print(candidates.get(i));%><br><% }%>
               其他候选人<input type=text name="other"><br>
       <input type="submit" value="提交"><br>
    </form>
    <%String time=new SimpleDateFormat("yyyy年MM月dd日HH时mm分").format(new Date());
      session.setAttribute("time", time);  //将提交时间通过session对象传递给下一个JSP文档%>
  </body>
</html>
<!--@author：Yeheya，2018年4月7日-->