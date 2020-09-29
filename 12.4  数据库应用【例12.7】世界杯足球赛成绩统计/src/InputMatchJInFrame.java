//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月12日
//§12.4 数据库应用
//【例12.7】 世界杯足球赛成绩统计。
//（5）输入比赛结果和计算积分榜

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;

//战况和积分榜内部框架类，继承分组浏览内部框架类，响应动作事件、表格模型事件
class InputMatchJInFrame extends BrowseJInFrame implements ActionListener, TableModelListener
{
    private String table2;                                 //数据库中的表名
    private String sort_columns2;                          //指定排序依据的列
    private JTable jtable2;                                //表格组件
    private DefaultTableModel tablemodel2;                 //表格模型
    private ResultSet rset_match;                          //结果集
    
    //构造方法，参数指定两组表名、列标题、分类的列和排序依据的列
    InputMatchJInFrame(Connection conn, String table, String[] columnNames, String list_column,
            String sort_columns, String table2, String[] columnNames2, String sort_columns2)
            throws SQLException
    {
        super(conn, table, columnNames, list_column, sort_columns);//浏览参赛队内部框架类
        this.setSize(800, 400); 
        //下句为表格第2列设置单元渲染器，添加图标
        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), new IconTableCellRenderer());
        
        JToolBar toolbar = new JToolBar();       //以下创建工具栏并在其中添加2个按钮
        this.getContentPane().add(toolbar,"North");
        String[] buttonstr={"生成比赛场次","计算积分榜"};
        for(int j=0; j<buttonstr.length; j++)
        {
            JButton button = new JButton(buttonstr[j]);
            toolbar.add(button);
            button.addActionListener(this);
        }
        
        //以下创建表格并添加到垂直分割窗格下部        
        this.tablemodel2 = new DefaultTableModel(columnNames2,0);//表格模型
        this.jtable2 = new JTable(this.tablemodel2); 
        split_ver.setDividerLocation(this.getHeight()/2);         //设置水平分隔条的位置
        split_ver.setRightComponent(new JScrollPane(this.jtable2));//垂直分割窗格下部添加表格
        this.tablemodel2.addTableModelListener(this);       //注册表格模型事件监听器

        //以下执行数据查询，“比赛记录”表格中显示数据
        this.table2 = table2;
        this.sort_columns2 =sort_columns2;
        this.rset_match = query(table2, "", sort_columns2, tablemodel2);
//        this.jtable2.setDefaultRenderer(this.tablemodel2.getColumnClass(4), new IconTableCellRenderer());
//        this.jtable2.setDefaultRenderer(this.tablemodel2.getColumnClass(5), new IconTableCellRenderer());
    }

    //列表框选择事件处理方法，当列表框中选择数据项时触发。覆盖父类同名方法。
    //以列表框选中数据项为条件，增加在table2表中查询，将结果集显示在tablemodel2表格模型中
    public void valueChanged(ListSelectionEvent event)
    {
        super.valueChanged(event);         //调用父类同名方法，设置右上表格组件的表格模型
        String item = this.jlist.getSelectedValue();       //列表框选中项
        if(item!=null) 
        {
            String where ="";
            if(!(item.equals("全部")))
                where = this.list_column + " = '"+item+"'";
            this.rset_match = query(table2, where, sort_columns2, tablemodel2);
        }
    }

    //表格模型事件处理方法，当表格模型插入、修改、删除数据项值时触发。
    //仅当修改表格模型值时，用表格模型当前编辑值更新结果集并提交数据库
    public void tableChanged(TableModelEvent event)
    {
        if(event.getType()==TableModelEvent.UPDATE)
        {
            int row=event.getFirstRow(), col=event.getColumn();//获得表格模型当前修改的行和列
            if(col==2 || col==3 || col==4)
                JOptionPane.showMessageDialog(this, "不能修改组别和参赛队。");
            else if(row!=-1 && col!=-1 && col!=2 && col!=3 && col!=4)
                try                                            //不能修改组别、两个参赛队
                {
                    String value=(String)tablemodel2.getValueAt(row, col);//获得表格模型修改值
                    this.rset_match.absolute(row+1);           //设置结果集当前行
                    this.rset_match.updateString(col+1,value); //用表格模型修改值设置结果集指定列
                    System.out.println("row+1="+(row+1)+"，col+1="+(col+1)+"，value="+value);
                    this.rset_match.updateRow();               //将结果集当前行提交数据库，更新数据库
//2018年8月31日，应该是此句，可以修改参赛队。此处执行抛出异常
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        }
    }
    
    public void actionPerformed(ActionEvent event)    //动作事件处理方法
    {
        switch(event.getActionCommand())              //单击按钮时
        {
            case "生成比赛场次":  insertmatch();  break;
            case "计算积分榜":  updateScore();  break;
        }
    }

    //从TeamScore获得参赛队，生成循环赛的比赛场次，插入MatchRecord表中；
    //若选中一组，则生成该组的比赛场次；若不选中或选中“全部”，则生成全部组的比赛场次。
    private void insertmatch()
    {
        String sql="SELECT xgroup,teamname FROM "+this.table, where="";
        String item = this.jlist.getSelectedValue();       //列表框选中项
        if(item!=null && !(item=="全部"))                  //选中组
            where = " WHERE "+this.list_column + " = '"+item+"'";
        sql+=where+" ORDER BY "+this.list_column;
        try
        {        
            ResultSet rset=this.stmt.executeQuery(sql);    //获得指定组或全部组的队名
            int rowCount=0;
            while(rset.next())                             //迭代遍历结果集，获得结果集行数
                rowCount++; 
            rset.beforeFirst();                            //将结果集当前行指针指向第一行之前
            String[][] teams=new String[rowCount][2];
            for(int i=0; rset.next(); i++)                 //获得所有组和队
            {
                teams[i][0]=rset.getString(1);             //获得xgroup
                teams[i][1]=rset.getString(2);             //获得teamname
            }
            rset.close();
            for(int i=0; i<teams.length; i++)              //比赛记录表插入若干比赛记录
                for(int j=i+1; j<4*(1+i/4); j++)           //比赛记录每行有组和两个参赛队
                    this.stmt.executeUpdate("INSERT INTO matchrecord(xgroup, teamname1, teamname2)"+
                        " VALUES ('"+teams[i][0]+"', '"+teams[i][1]+"', '"+teams[j][1]+"')"); 
            this.rset_match=query(table2, where, sort_columns2, tablemodel2);
                         //从table2（matchrecord）表中查询满足where条件的行，按sort_columns2列排序，添加到表格模型
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void updateScore()                             //计算积分榜
    {
        try
        {
            int count = this.tablemodel.getRowCount();     //获得表格模型行数
            String sql;
            ResultSet rset;
            for(int i=0; i<count; i++)
            {
                String teamname=(String)this.tablemodel.getValueAt(i,1);  //队名
                sql="SELECT count(*) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"' AND (goalsfor1 is not null) or "+
                          "teamname2='"+teamname+"' AND (goalsfor2 is not null)";
//              System.out.println(sql);
                rset=stmt.executeQuery(sql);
                rset.next();
                int completed=rset.getInt(1);              //已赛场次
            
                sql="SELECT count(*) FROM matchrecord "+  
                    " WHERE teamname1='"+teamname+"' AND (goalsfor1>goalsfor2) or "+
                           "teamname2='"+teamname+"' AND (goalsfor1<goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int win=rset.getInt(1);                    //胜场数

                sql="SELECT count(*) FROM matchrecord  WHERE (teamname1='"+teamname+
                    "' or teamname2='"+teamname+"') AND (goalsfor1=goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int tie=rset.getInt(1);                    //平场数
 
                sql="SELECT count(*) FROM matchrecord "+  
                    " WHERE teamname1='"+teamname+"' AND (goalsfor1<goalsfor2) or "+
                           "teamname2='"+teamname+"' AND (goalsfor1>goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int loss=rset.getInt(1);                   //负场数
        	
                sql="SELECT sum(goalsfor1) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                int goalsfor=rset.getInt(1);               //作为队1的进球数

                sql="SELECT sum(goalsfor2) FROM matchrecord "+  
                    "WHERE teamname2='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                goalsfor+=rset.getInt(1);                  //加作为队2的进球数

                sql="SELECT sum(goalsfor2) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                int goalsagainst=rset.getInt(1);           //作为队1的失球数
            
                sql="SELECT sum(goalsfor1) FROM matchrecord "+  
                    "WHERE teamname2='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                goalsagainst+=rset.getInt(1);              //加作为队2的失球数
                rset.close();
 
                //净胜球为goalsfor-goalsagainst，积分为win*3+tie
                sql="UPDATE TeamScore SET completed="+completed+" ,win="+win+" ,tie="+tie+
                    ",loss="+loss+", goalsfor="+goalsfor+", goalsagaint="+goalsagainst+
                    ", netvalue="+(goalsfor-goalsagainst)+", score="+(win*3+tie)+
                    " WHERE teamname='"+teamname+"'";
                stmt.executeUpdate(sql);                   //执行UPDATE语句更新表中指定行
            }
        
            for(int i=0; i<count; i+=4)                    //计算小组排名
            {
                String xgroup=(String)this.tablemodel.getValueAt(i, 0);
                sql="SELECT teamname FROM TeamScore WHERE xgroup='"+xgroup+
                    "' ORDER BY score DESC, netvalue DESC, goalsfor DESC";    
                rset=stmt.executeQuery(sql);               //获得当前小组按积分排序的结果集
                String[] teams=new String[4];
                for(int j=0; rset.next(); j++)             //将排序的4个队名保存在数组中
                    teams[j]=rset.getString("teamname");
                for(int j=0; j<teams.length; j++)          //更新一个小组的各队排名
                    stmt.executeUpdate("UPDATE TeamScore SET rank="+(j+1)+
                                       " WHERE teamname='"+teams[j]+"'");
            }
            valueChanged(null);                            //刷新两个表格组件显示
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
//@author：Yeheya，2018年3月14日，2018年8月31日