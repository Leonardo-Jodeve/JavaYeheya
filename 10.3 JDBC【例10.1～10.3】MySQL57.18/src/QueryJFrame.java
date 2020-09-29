//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年4月27日
//§10.2 MySQL数据库
//§10.3 JDBC
//§10.3.5 处理数据查询结果集
//【例10.2】 显示数据查询结果集。
//使用表格模型

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Calendar;

public class QueryJFrame extends JFrame          //数据查询框架类
{
    protected Connection conn;                   //数据库连接对象
    private DefaultTableModel tablemodel;        //表格模型

    //构造方法，driver指定JDBC驱动程序，url指定数据库URL，table指定数据库表，按sortColumn排序
    public QueryJFrame(String driver, String url, String table, String sortColumn) 
            throws ClassNotFoundException, SQLException
    {
        super("浏览"+table+"表");
        this.setBounds(300,240,600,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Class.forName(driver);                             //指定JDBC驱动程序
        this.conn=DriverManager.getConnection(url);        //返回数据库连接对象

        this.tablemodel = new DefaultTableModel();         //默认表格模型，没有列标题，0行
        JTable jtable = new JTable(this.tablemodel);       //创建表格，指定表格模型
        this.getContentPane().add(new JScrollPane(jtable));//框架内容窗格添加滚动窗格（包含表格）
        query(table, sortColumn, this.tablemodel);         //执行数据查询，结果集显示在tablemodel
        addAge(this.tablemodel);                 //表格模型增加年龄计算列
        this.setVisible(true);
    }

    //执行table表数据查询，按sortColumn排序，查询结果集显示在tablemodel表格。通用方法
    public void query(String table, String sortColumn, DefaultTableModel tablemodel) throws SQLException
    {
        String sql = "SELECT * FROM "+table;
        if(sortColumn!=null && sortColumn!="")
            sql+=" ORDER BY "+sortColumn;
        query(sql, tablemodel);
    }        
    //执行sql表示的数据查询SELECT语句，将查询结果集显示在tablemodel表格中。通用方法
    public void query(String sql, DefaultTableModel tablemodel) throws SQLException
    {
    	Statement stmt=this.conn.createStatement();//1003,1007); //创建语句对象，结果集数据不敏感和只读
        ResultSet rset=stmt.executeQuery(sql);             //执行数据查询SELECT语句
//      System.out.println("rset.getType()="+rset.getType());
//      System.out.println("rset.getConcurrency()="+rset.getConcurrency());
//        System.out.println("rset.isBeforeFirst()="+rset.isBeforeFirst());
        
        //以下获得表中列数及各列名，作为表格组件的标题
        ResultSetMetaData rsmd = rset.getMetaData();       //返回表属性对象
        int count = rsmd.getColumnCount();                 //获得列数
        for(int j=1; j<=count; j++)                        //将各列名添加到表格模型作为列标题，序号≥1
            tablemodel.addColumn(rsmd.getColumnLabel(j));

        //以下将结果集中各行数据添加到表格模型，一次遍历
        Object[] columns=new Object[count];                //创建列对象数组，数组长度为列数
        while(rset.next())                                 //迭代遍历结果集，从前向后访问每行
        {
            for(int j=1; j<=columns.length; j++)           //获得每行各列值
                columns[j-1]=rset.getString(j);
            tablemodel.addRow(columns);                    //表格模型添加一行，参数指定各列值
        }        
        rset.close();
        stmt.close();
    }
    //说明：没有用String[] titles;指定表格列标题，否则就不需要使用结果集的元数据获得表结构

    //表格模型增加年龄计算列
    public void addAge(DefaultTableModel tablemodel) throws SQLException
    {
        int thisYear=Calendar.getInstance().get(Calendar.YEAR); //获得当前年份
        int column=tablemodel.getColumnCount();            //获得表格列数
        tablemodel.addColumn("年龄");                      //表格添加一列
        //以下遍历表格模型，每行设置年龄计算列值
        for(int row=0; row<tablemodel.getRowCount(); row++)
        {
            String birthdate = (String)tablemodel.getValueAt(row, 1);//获得出生日期列
        	String year=birthdate.substring(0, 4);         //获得出生日期的年份
        	int age = thisYear - Integer.parseInt(year);   //计算年龄，未处理异常
        	tablemodel.setValueAt(age, row, column);       //设置年龄计算列值
        }
    }    
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        //MySQL 5.7.18，2018年3月7日
        String driver = "com.mysql.jdbc.Driver";           //指定MySQL JDBC驱动程序
        String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//指定数据库URL
        new QueryJFrame(driver, url, "person", "province");//查询person表，按province排序

//        String[] titles={"姓名","出生日期","性别","省份","城市"};  //指定表格列标题
//        String url="jdbc:mysql://localhost/worldcup2006?user=root&password=";
//        new QueryJFrame(driver, url, "team");
        
    }
    public void finalize() throws SQLException             //析构方法，关闭数据库连接
    {
        this.conn.close();
    }
}
//@author：Yeheya，2018年3月10日，2018年8月27日
/*
//两次遍历
/*        int rowCount=0;
while (rset.next())                                //迭代遍历结果集，获得结果集行数
    rowCount++;
tablemodel.setRowCount(rowCount);                  //设置表格模型行数
rset.beforeFirst();                                //移动当前行指针到第一行之前

//将结果集中各行数据添加到表格模型
for (int i=0; rset.next(); i++)                    //迭代遍历结果集，从前向后访问每行，行序号≥0
    for (int j=1; j<=columns; j++)                 //获得每行各列值
        tablemodel.setValueAt(rset.getString(j),i,j-1);
//System.out.println("rset.isAfterLast()="+rset.isAfterLast());
 */
