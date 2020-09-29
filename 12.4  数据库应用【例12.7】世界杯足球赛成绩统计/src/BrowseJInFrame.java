//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月10日
//§12.4 数据库应用
//【例12.7】 世界杯足球赛成绩统计。
//（3） 分组浏览参赛队

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

//分组浏览参赛队内部框架类，响应列表框选择事件
class BrowseJInFrame extends JInternalFrame implements ListSelectionListener
{
    protected String table;                                //数据库中的表名
    protected String list_column;                          //表中分类的列
    protected String sort_columns;                         //排序依据的多列
    protected JList<String> jlist;                         //列表框，显示分类列的不重复值
    protected DefaultListModel<String> listmodel;          //默认列表框模型
    protected JTable jtable;                               //表格组件，显示数据库中指定表内容
    protected DefaultTableModel tablemodel;                //默认表格模型
    protected JSplitPane split_ver;                        //垂直分割窗格
    protected Statement stmt;                              //语句对象 
    protected ResultSet rset_team;                         //结果集
    
    //构造方法，参数依次指定数据库连接、表名、列的中文标题、分类的列、排序依据的多列
    BrowseJInFrame(Connection conn, String table, String[] columnNames, String list_column,
            String sort_columns) throws SQLException
    {
        super("", true, true, true, true);
        this.setSize(500, 300);

        this.listmodel=new DefaultListModel<String>();     //以下创建列表框
        this.listmodel.addElement("全部");
        this.jlist=new JList<String>(this.listmodel);
        this.jlist.addListSelectionListener(this);         //列表框监听选择事件
//        this.jlist.setSelectedIndex(1);                    //选中第1项，为什么？不要了
        
        //以下创建水平分割窗格，左边添加列表框，右边添加垂直分割窗格
        JSplitPane split_hor=new JSplitPane(1, new JScrollPane(this.jlist), null);
        this.getContentPane().add(split_hor);
        split_hor.setDividerLocation(50);                  //设置垂直分隔条的位置

        this.tablemodel=new DefaultTableModel(columnNames,0);  //以下创建表格
        this.jtable=new JTable(this.tablemodel);           //创建表格
        split_ver=new JSplitPane(0,new JScrollPane(this.jtable),null); //垂直分割窗格上部添加表格
        split_hor.add(split_ver);
        
        //以下创建语句，执行数据查询，表格中显示“全部”数据
        this.stmt = conn.createStatement(1005, 1008);  //创建语句，结果集数据敏感和可更新
//        this.stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        this.table=table;
        this.list_column=list_column;
        this.sort_columns=sort_columns;
        query(table,listmodel);                            //查询设置列表框模型值
//        this.jlist.setSelectedIndex(0);                    //列表框选中“全部”数据项，触发列表框选择事件。不能选中，否则积分榜子类不通
        query(table, "", sort_columns, tablemodel);        //查询设置表格模型值，""表示全部
//        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), new IconTableCellRenderer());
                //为表格第2列设置图标单元渲染器。
                //问题，之后不能显示选中表格状态，为什么？？
        this.setVisible(true);
    }

    //查询table表，将表中list_column列的所有不重复值添加到列表框模型中
    public void query(String table, DefaultListModel<String> listmodel) throws SQLException
    {
        String sql="SELECT DISTINCT "+this.list_column+" FROM "+table+
                      " ORDER BY "+this.list_column;       //获得指定列不重复的值
        ResultSet rset=this.stmt.executeQuery(sql);        //执行数据查询语句，返回结果集
        while(rset.next())                                 //迭代遍历结果集
            listmodel.addElement(rset.getString(1));       //添加当前行指定列值到列表框模型
        rset.close();
    }
    
    //列表框选择事件处理方法，当选中列表框数据项时触发。
    //以列表框选中项为条件，在table表中查询满足条件的数据行，将结果集显示在表格模型中
    public void valueChanged(ListSelectionEvent event)
    {
        String item = this.jlist.getSelectedValue();       //列表框选中数据项
//        System.out.println(item);
        if(item!=null)
        {
            String where="";                               //""表示全部
            if(!(item.equals("全部")))
                where=this.list_column + " = '"+item+"'";
            this.rset_team=query(table,where,sort_columns,tablemodel);//查询，设置表格模型
        }
    }
    
    //将table表中满足where子句的多行添加到tablemodel表格模型中，按sort_columns排序
    public ResultSet query(String table, String where, String sort_columns, DefaultTableModel tablemodel)
    {
        for(int i=tablemodel.getRowCount()-1; i>=0; i--)   //清空表格
            tablemodel.removeRow(i);                       //删除表格模型第i行

        String sql="SELECT * FROM "+table;                 //创建数据查询SQL语句
        if(where!="")
            sql+=" WHERE " +where;
        sql+=" ORDER BY "+sort_columns;
        try
        {
            ResultSet rset = this.stmt.executeQuery(sql);  //执行数据查询语句
            ResultSetMetaData rsmd = rset.getMetaData();   //返回结果集元数据对象
            String[] columns=new String[rsmd.getColumnCount()];//创建列对象数组，长度为列数
            while(rset.next())                             //迭代遍历结果集
            {
                for(int i=1; i<=columns.length; i++)       //数组保存当前行所有列值，列序号≥1
                    columns[i-1]=rset.getString(i);
                tablemodel.addRow(columns);                //表格模型添加一行，触发表格模型事件
            }
            return rset;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public void finalize() throws SQLException             //析构方法
    {
        this.stmt.close();
    }
}
//@author：Yeheya，2018年3月10日，2018年8月30日