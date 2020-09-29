<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年4月9日
§11.2 JSP
【例11.3】选举投票。
（1）输入候选人页面
--> 
<!--【例11.8】基于JSP的数据库应用。-->

<%@ page language="java" import="java.sql.*" contentType="text/html; charset=GBK" %>
<html>
  <head>
    <title>数据库应用</title>
  </head>
  <body>
    <% //以下连接driver、url指定数据库，获得table表的数据查询结果集
       String driver="com.mysql.jdbc.Driver";     //MySQL JDBC驱动程序
       String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//指定数据库URL
       String table = "person";                   //表名
       String sql="SELECT * FROM "+table+";";     //查询语句
       try
       {
          Class.forName(driver);                           //指定JDBC驱动程序
          Connection conn=DriverManager.getConnection(url);//创建连接对象
          Statement stmt=conn.createStatement();           //创建语句对象
          ResultSet rset=stmt.executeQuery(sql);           //执行数据查询SELECT语句
          ResultSetMetaData rsmd = rset.getMetaData();     //返回表属性对象
          int count = rsmd.getColumnCount();               //获得列数
       %>
       <center><%=table%>表<br>                             <!--显示表名-->
       <table border=1>                                     <!--表格，参数指定边框风格-->
          <tr><% for(int j=1; j<=count; j++) {%>            <!--表格标题行-->
                   <td><%=rsmd.getColumnLabel(j)%></td>     <!--表格列标题-->
              <%}%>  
          </tr>
          <% while(rset.next()) {%>                         <!--遍历结果集-->
             <tr><% for(int j=1; j<=count; j++){%>          <!--显示数据库表中一行数据-->
                      <td><%=rset.getString(j)%></td>       <!--一列数据-->
                 <%}%> 
             </tr>
          <% }%> 
       </table></center>
       <% rset.close();
          stmt.close();
          conn.close(); 
       }
       catch(Exception ex)
       {
    	  ex.printStackTrace();
       } %>
  </body>
</html>
<!--@author：Yeheya，2018年4月9日-->