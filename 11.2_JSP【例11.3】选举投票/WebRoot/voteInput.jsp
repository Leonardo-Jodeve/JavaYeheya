<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.3��ѡ��ͶƱ��
��2����ʾ��ѡ�˽���ͶƱ����ҳ��
--> 

<%@ page language="java"  import="java.util.*"  import="java.text.*" 
         contentType="text/html; charset=GBK" %>
<html>
  <head><title>ѡ��ͶƱ</title>
  </head>
  
  <body>
    <form name="form1" method="post" action="voteResult.jsp">      <%--�ύ����һJSP����--%>
                   ��ѡ�ˣ�<br>
    <%
//     List<String> candidates = new ArrayList<String>();  //���list����ֵ
//       candidates.add("Mars");            //���ǣ�ս��
//       candidates.add("Helios");          //̫����
 //      candidates.add("Venus");           //ά��˹

       //���²��õ�ѡ��ť��ʾ���к�ѡ�ˣ�ѡ��һ�˻�����������ѡ�˺��ύ
       List<String> candidates = (ArrayList<String>)application.getAttribute("candidates");//�������ֵ
       for(int i=0; i<candidates.size(); i++) {%>
           <input type="radio" name="name" value="<%=candidates.get(i)%>">
                  <%out.print(candidates.get(i));%><br><% }%>
               ������ѡ��<input type=text name="other"><br>
       <input type="submit" value="�ύ"><br>
    </form>
    <%String time=new SimpleDateFormat("yyyy��MM��dd��HHʱmm��").format(new Date());
      session.setAttribute("time", time);  //���ύʱ��ͨ��session���󴫵ݸ���һ��JSP�ĵ�%>
  </body>
</html>
<!--@author��Yeheya��2018��4��7��-->