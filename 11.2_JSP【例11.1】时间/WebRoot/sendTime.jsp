<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.3 JSP
����11.�����ͺͽ��յ�ǰʱ�䡣
        ʹ��JSP��session��������������JSP�ĵ�֮�䴫������
        ����ʱ��
--> 
 
<%@ page language="java" import="java.util.*" import="java.text.*" 
         contentType="text/html;charset=GBK" %>             <%--JSP����ָ��--%>
<html>
  <head>
    <title>����ʱ��</title>
  </head>
  <body>
    <form name="form1" method="post" action="printTime.jsp">
    <strong><font color="blue" face="���Ŀ���" size="4">       <%--�����������ɫ--%>
            ��ǰ����ʱ��:
    <%String datestr="yyyy��MM��dd��E ahhʱmm��ss��";
      SimpleDateFormat datef = new SimpleDateFormat(datestr);
      Date time = new Date();
      out.println(datef.format(time)+"<br>");
      out.println("session.getId()="+session.getId());
    
      application.setAttribute("height", 182);
      session.setAttribute("datestr", datestr);
      session.setAttribute("datef", datef);
      session.setAttribute("time", time); 
    %><br></font></strong>
    <td colspan="2">
    <input type="submit" value="�ύ"></form>
  </body>
</html>   

<!--@author��Yeheya��2018��3��23��-->