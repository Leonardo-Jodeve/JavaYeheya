<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.1����ʾ��ǰ����ʱ���JSP�ĵ�
-->                                                        <%--JSP���ע�ͣ�HTML�ĵ��в��ɼ�--%> 

<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html; charset=GBK" %>         <%--JSP����ָ��--%>
<html>
  <head><title>��ǰ����ʱ��</title></head>
  <body>
    <strong><font color="blue" face="���Ŀ���" size="4">    <%--�����������ɫ--%>
      <%=new SimpleDateFormat("yyyy��MM��dd��E HHʱmm��ss��").
             format(new Date())%><br></font></strong>    <%--�����ͱ��ʽ --%>
  </body>
</html>

<!--@author��Yeheya��2018��4��1��-->