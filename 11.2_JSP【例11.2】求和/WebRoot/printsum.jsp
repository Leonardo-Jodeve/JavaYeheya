<!--��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.2����ͣ����������ʽ��
û�����룬ʹ���������Ϊ����ֵ�����������ʽ��--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head>
    <title>���</title>
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
<!--@author��Yeheya��2018��3��22��-->  