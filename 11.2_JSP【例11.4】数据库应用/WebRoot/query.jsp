<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��4��9��
��11.2 JSP
����11.3��ѡ��ͶƱ��
��1�������ѡ��ҳ��
--> 
<!--����11.8������JSP�����ݿ�Ӧ�á�-->

<%@ page language="java" import="java.sql.*" contentType="text/html; charset=GBK" %>
<html>
  <head>
    <title>���ݿ�Ӧ��</title>
  </head>
  <body>
    <% //��������driver��urlָ�����ݿ⣬���table������ݲ�ѯ�����
       String driver="com.mysql.jdbc.Driver";     //MySQL JDBC��������
       String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//ָ�����ݿ�URL
       String table = "person";                   //����
       String sql="SELECT * FROM "+table+";";     //��ѯ���
       try
       {
          Class.forName(driver);                           //ָ��JDBC��������
          Connection conn=DriverManager.getConnection(url);//�������Ӷ���
          Statement stmt=conn.createStatement();           //����������
          ResultSet rset=stmt.executeQuery(sql);           //ִ�����ݲ�ѯSELECT���
          ResultSetMetaData rsmd = rset.getMetaData();     //���ر����Զ���
          int count = rsmd.getColumnCount();               //�������
       %>
       <center><%=table%>��<br>                             <!--��ʾ����-->
       <table border=1>                                     <!--��񣬲���ָ���߿���-->
          <tr><% for(int j=1; j<=count; j++) {%>            <!--��������-->
                   <td><%=rsmd.getColumnLabel(j)%></td>     <!--����б���-->
              <%}%>  
          </tr>
          <% while(rset.next()) {%>                         <!--���������-->
             <tr><% for(int j=1; j<=count; j++){%>          <!--��ʾ���ݿ����һ������-->
                      <td><%=rset.getString(j)%></td>       <!--һ������-->
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
<!--@author��Yeheya��2018��4��9��-->