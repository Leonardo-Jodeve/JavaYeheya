<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.2����͡�
��˼����11-2�� �� ʹ��HTML���<font></font>�������ֺ�������ֱ����óɲ�ͬ����ɫ��
--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head>
    <title>���</title>
  </head>
  <body>
    <% int n=(int)(Math.random()*10),s=0;%>
    Sum(<%=n%>)=
    <%for(int i=1;i<n;i++)  {%>
        <font color="blue"><%=i%></font>
        <%s=s+i;%>
        <font color="red">+</font>��ʽ
    <% } %>
    <font color="blue"><%=n%></font>
    <font color="red"> = </font>
    <font color="blue"><%=(s+n)%></font>
  </body>
</html>
<!--@author��Yeheya��2018��3��22��-->  