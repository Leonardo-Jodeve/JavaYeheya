<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.3��ѡ��ͶƱ��
��1�������ѡ��ҳ��
--> 

<%@ page language="java"  import="java.util.*"  import="design.*" 
         contentType="text/html; charset=GBK" %>
<html>
  <head><title>�����ѡ��</title>
  </head>
  
  <body>
    <form name="form1" method="post" action="candidates.jsp"> <%--�����ύ���Լ�--%>
                  �����ѡ�ˣ� <input type=text name="name"><br>         <%--����name--%>
         <input type="submit" value="�ύ"><br>                <%--���ύ����ť������ʱ�ύname����ֵ--%>
    </form>
    <% //������Ӧ������ʾ��������к�ѡ�ˡ��¾�������ֵ
       List<String> candidates = (ArrayList<String>)application.getAttribute("candidates");
       if(candidates==null)
           candidates=new ArrayList<String>();          //�����б��洢��ѡ�˼���
         
       String name = request.getParameter("name");      //ʹ��request�����ñ��ύ��nameֵ���ַ�������
       if(name!=null && !name.equals(""))
       {
           candidates.add(name);                        //�б�����ӵ�ǰ�����һ����ѡ��
           application.setAttribute("candidates", candidates);   //����application�����candidates����ֵ
       }%>
         ��ѡ�ˣ�<br>
    <%=design.MyCollection.toString(candidates)%>       <%//���÷��������ؼ���Ԫ���ַ���%>
  </body>
</html>
<!--@author��Yeheya��2018��4��7��-->