<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
��ʵ��11-1����ͣ�JSP�ĵ����롢���㲢��ʾ������ʽ���������з�����
--> 

<%@ page language="java" import="design.*" contentType="text/html; charset=GBK"%>
<html>
  <head><title>���</title></head>
  <body>
    <%String str = request.getParameter("n");    //ʹ��request�����ñ���nֵ���ַ���
      int n=10; 
      if(str!=null)   
          n=Integer.parseInt(str);               //��str�ַ���ת����int������δ�����쳣%>

    <form name="form1" method="post" action="sumCalcu.jsp"> <%--�����ύ���Լ�--%>
      Sum(1+����+<input type=text name="n" value=<%=n%>>)    <%--����n��ʾ��ͷ�Χ--%>
      <input type=submit value="="><br>                    <%--������=���ύn����ֵ--%>
      <%=design.Calculation.sumToString(n)%>
    </form>
  </body>
</html>

<!--@author��Yeheya��2018��3��25��-->