//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年5月6日
//§10.4  JDBC
//【例10.3】  交叉统计表。

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

//交叉统计表框架类，继承例10.2数据查询框架类
public class CrosstabQueryJFrame extends QueryJFrame
{
    private DefaultTableModel crosstabmodel;        //交叉统计表的表格模型

    //构造方法，driver指定JDBC驱动程序，url指定数据库URL，table指定数据库表，
    //按sortColumn排序；row、column指定交叉统计表的行和列    
    public CrosstabQueryJFrame(String driver, String url, String table, String sortColumn,
            String row, String column)  throws ClassNotFoundException, SQLException
    {
        super(driver, url, table, sortColumn);
        this.setTitle(this.getTitle()+"，交叉统计表");
        JComponent comp = (JComponent)this.getContentPane().getComponent(0);//获得父类内容窗格中的组件，查询表格
        JSplitPane split = new JSplitPane(0,comp,null);    //垂直分割窗格，上部添加父类的查询表格
        this.getContentPane().add(split);                  //框架内容窗格添加分割窗格
        split.setDividerLocation(this.getHeight()/2);      //设置垂直分隔条的位置
        split.setOneTouchExpandable(true);                 //提供一键展开按钮，快速展开/折叠分隔条
        
        this.crosstabmodel = new DefaultTableModel();      //交叉统计表的表格模型，没有列标题，0行
        JTable jtable = new JTable(this.crosstabmodel);    //创建表格，指定表格模型
        split.add(new JScrollPane(jtable));                //分割窗格添加滚动窗格（包含表格）
        crosstab(table, row, column, this.crosstabmodel);  //计算交叉统计表，结果显示在表格组件
        this.setVisible(true);
    }
    
    //对table表的row、column列计算交叉统计表，结果显示在tablemodel中
    public void crosstab(String table, String row, String column, DefaultTableModel tablemodel) throws SQLException
    {
        String sql = "SELECT "+row+" , COUNT(*) AS 人数  FROM "+table +
                     " GROUP BY "+row+" ORDER BY "+row;    //按row列（省份）分类统计人数
        query(sql, tablemodel);                  //执行父类的数据查询，tablemodel添加执行sql的结果集
        
        int col = tablemodel.getColumnCount();             //表格当前列数
        sql="SELECT DISTINCT "+column+" FROM "+table;      //获得列（性别）的不重复取值
        Statement stmt=this.conn.createStatement();        //创建语句对象
        ResultSet rset=stmt.executeQuery(sql);             //执行数据查询SELECT语句
        while(rset.next())       //迭代遍历结果集，将列（性别）取值添加到表格，作为表格列标题
            tablemodel.addColumn(rset.getString(1));

        //为上述添加的每列，填写计算的统计值
        for(int j=col; j<tablemodel.getColumnCount(); j++)
        {
            sql="SELECT "+row+", COUNT(*) FROM "+table +
                  " WHERE "+column+"='"+tablemodel.getColumnName(j)+"' GROUP BY "+row; //列名
            stmt=this.conn.createStatement();              //创建语句对象
            rset=stmt.executeQuery(sql);                   //执行数据查询SELECT语句
//            System.out.println(tablemodel.getColumnName(j));//显示表格当前列名，作为查询条件
//            while(rset.next())                           //迭代遍历结果集，从前向后访问每行
//            {
//                System.out.println(rset.getString(1)+", "+rset.getString(2));
//            }        
            boolean next = rset.next();          //获得结果集首个元素，迭代遍历；不能作为循环条件
            for(int i=0; i<tablemodel.getRowCount() && next; i++)
            {
//                System.out.println(rset.getString(1)+", "+rset.getString(2));//显示结果集当前元素值
//                System.out.println(tablemodel.getValueAt(i,0)+", "+rset.getString(1));//显示参与以下比较的值
                if(tablemodel.getValueAt(i,0).equals(rset.getString(1)))
                {
                    tablemodel.setValueAt(rset.getString(2), i, j);
                    next = rset.next();          //当比较相等时，才获得结果集下个元素，迭代遍历 
                }
            }
        }
        rset.close();
        stmt.close();
    }
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        //MySQL 5.7.18，2018年3月7日
        String driver = "com.mysql.jdbc.Driver";           //指定MySQL JDBC驱动程序
        String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//指定数据库URL
        new CrosstabQueryJFrame(driver, url, "person", "province", "province", "gender"); 
    }
}
//@author：Yeheya，2018年3月10日，2018年8月27日