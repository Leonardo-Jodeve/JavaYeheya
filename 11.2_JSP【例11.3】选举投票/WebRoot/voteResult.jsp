<!--��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��22��
��11.2 JSP
����11.3��ѡ��ͶƱ��
��3����ʾͶƱ���ҳ��
--> 

<%@ page language="java" import="java.util.*" import="design.*"
         contentType="text/html; charset=GBK" %>
<html>
  <head>
  <title>ͶƱ���</title>
  </head>
  
  <body>
    <% //����ʹ��request�����ñ��ύ��nameֵ����nameΪ�գ�����������ѡ������
       String name = request.getParameter("name");
       if(name==null)
           name = request.getParameter("other"); 
    
       //����ʹ����ӳ��map�洢ͶƱ�����mapԪ��Ϊ(����,����ֵ)����name�ļ���ֵ+1
       Map<String,Integer> map = (TreeMap)application.getAttribute("map"); //���map����ֵ
       if(map==null)
           map=new TreeMap<String,Integer>();         //��ӳ�䣬�洢ͶƱ���
         
       if(name!=null && !name.equals(""))
       {
           Integer value = map.get(name);             //��ùؼ���nameӳ�����������
           int count = value==null ? 1 : value.intValue()+1; //ת����int����������+1
           map.put(name, new Integer(count));         //ӳ������Ԫ�أ��ؼ�����ͬʱ���滻ֵ
           application.setAttribute("map", map);      //����map����ֵ
       }
    %>   
          ͶƱ�����
    <table border="1">                                <%--���border����ָ���߿���--%>
       <tr><td width="200"><b>��ѡ������</b></td>      <%--width����ָ����Ԫ����--%>
           <td width="100"><b>��Ʊ��</b></td>
           <td width="100"><b>��Ʊ��</b></td>
       </tr>
    <% Set<String> set=map.keySet();                   //���عؼ��ּ���
       Iterator<String> it = set.iterator();           //���ص�����������Ԫ��������String
       while(it.hasNext())                            //�������ϣ����к��Ԫ��
       {
    	   String key=it.next();
           Integer value = map.get(key);    %>
           <tr><td width="200"><b><%=key%></b></td>
               <td width="100"><b><%=value%></b></td>
               <td width="100"><b></b></td>
           </tr>
    <% }%>
    </table>
    
    <br>
    <% //���²��������ϴ洢�ҵ�ѡƱ���ϲ��б���ʾ��һ��ѡƱBallot(ͶƱ��,ʱ��,ѡ����)
       String time=(String)session.getAttribute("time");
       Set<Ballot> record = (TreeSet<Ballot>)session.getAttribute("record");
       if(record==null)
           record = new TreeSet<Ballot>();              //�����ϣ��洢�ҵ�ѡƱ����
       if(name!=null && !name.equals(""))
       {
           record.add(new Ballot(session.getId()+"", time, name));//ѡƱ�������һ��ѡƱ
           session.setAttribute("record", record);      //����record����ֵ
       }%>
         �ҵ�ͶƱ��¼��<br>
    <%=design.MyCollection.toString(record)%>           <%//���÷��������ؼ���Ԫ���ַ���%>
  </body>
</html>
<!--@author��Yeheya��2018��4��9��-->