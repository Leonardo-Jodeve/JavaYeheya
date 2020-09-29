<!--《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月22日
§11.2 JSP
【例11.3】选举投票。
（3）显示投票结果页面
--> 

<%@ page language="java" import="java.util.*" import="design.*"
         contentType="text/html; charset=GBK" %>
<html>
  <head>
  <title>投票结果</title>
  </head>
  
  <body>
    <% //以下使用request对象获得表单提交的name值，若name为空，则获得其他候选人姓名
       String name = request.getParameter("name");
       if(name==null)
           name = request.getParameter("other"); 
    
       //以下使用树映射map存储投票结果，map元素为(姓名,计数值)，将name的计数值+1
       Map<String,Integer> map = (TreeMap)application.getAttribute("map"); //获得map属性值
       if(map==null)
           map=new TreeMap<String,Integer>();         //树映射，存储投票结果
         
       if(name!=null && !name.equals(""))
       {
           Integer value = map.get(name);             //获得关键字name映射的整数对象
           int count = value==null ? 1 : value.intValue()+1; //转换成int整数，计数+1
           map.put(name, new Integer(count));         //映射增加元素，关键字相同时，替换值
           application.setAttribute("map", map);      //设置map属性值
       }
    %>   
          投票结果：
    <table border="1">                                <%--表格，border参数指定边框风格--%>
       <tr><td width="200"><b>候选人姓名</b></td>      <%--width参数指定单元格宽度--%>
           <td width="100"><b>得票数</b></td>
           <td width="100"><b>得票率</b></td>
       </tr>
    <% Set<String> set=map.keySet();                   //返回关键字集合
       Iterator<String> it = set.iterator();           //返回迭代器，集合元素类型是String
       while(it.hasNext())                            //遍历集合，若有后继元素
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
    <% //以下采用树集合存储我的选票集合并列表显示，一张选票Ballot(投票人,时间,选修人)
       String time=(String)session.getAttribute("time");
       Set<Ballot> record = (TreeSet<Ballot>)session.getAttribute("record");
       if(record==null)
           record = new TreeSet<Ballot>();              //树集合，存储我的选票集合
       if(name!=null && !name.equals(""))
       {
           record.add(new Ballot(session.getId()+"", time, name));//选票集合添加一张选票
           session.setAttribute("record", record);      //设置record属性值
       }%>
         我的投票记录：<br>
    <%=design.MyCollection.toString(record)%>           <%//调用方法，返回集合元素字符串%>
  </body>
</html>
<!--@author：Yeheya，2018年4月9日-->