<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.3 JSP
����11.�����ͺͽ��յ�ǰʱ�䡣
        ʹ��JSP��session��������������JSP�ĵ�֮�䴫������
        ����ʱ��
--> 
 
<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html;charset=GBK" %>              <%--JSP����ָ��--%>
<html>
  <head>
    <title>����ʱ��</title>
  </head>
  <body>
    <strong><font color="blue" face="���Ŀ���" size="4">       <%--�����������ɫ--%>
    <%int height=((Integer)application.getAttribute("height")).intValue();
      out.print("height="+height+"<br>");
      String datestr=(String)session.getAttribute("datestr");
      SimpleDateFormat datef = (SimpleDateFormat)session.getAttribute("datef");
      Date time = (Date)session.getAttribute("time");                 
      out.print("time="+datef.format(time)+"<br>");
      out.println("session.getId()="+session.getId());
    %><br></font></strong>
  </body>
</html>   

<!--@author��Yeheya��2018��3��23��-->