<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
��ʵ��11-1����ͣ�HTML�ĵ����룬JSP�ĵ����㡣
��2��JSP��̬�ĵ�������������ݣ����㲢�Ա��ʽ��ʽ��ʾ�����
        ʹ��request������HTML�ĵ��ύ���������ݣ��ַ�����ʽ��
        ���㣬�������Ƕ�뵽HTML�ĵ���ʾ��
--> 

<%@ page contentType="text/html;charset=GBK" %>
<html>
  <head><title>��ͽ��</title></head>
  <body>
    <%String str = request.getParameter("n");    //ʹ��request�����ñ���nֵ���ַ���
      int n=Integer.parseInt(str),s=0;           //��str�ַ���ת����int������δ�����쳣%>
      Sum(<%=n%>)=
      <%for(int i=1; i<n; i++)  {%>
        <%=i%>+
        <%s+=i;
      }%>
      <%=n%>=<%=(s+n)%>
  </body>
</html>
<!--@author��Yeheya��2018��3��22��-->