//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月10日
//§12.4 数据库应用
//【例12.7】 世界杯足球赛成绩统计。
//（4） 输入参赛队  
 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

//输入参赛队内部框架类，继承分组浏览内部框架类，响应动作事件、表格模型事件
class InputTeamJInFrame extends BrowseJInFrame implements ActionListener, TableModelListener
{
    private JTextField[] texts;                            //输入指定表各列的文本行数组

    //构造方法，参数依次指定数据库连接、表名、列的中文标题、分类的列、排序依据的多列
    InputTeamJInFrame(Connection conn, String table, String[] columnNames, String list_column, 
                      String sort_columns) throws SQLException
    {
        super(conn, table, columnNames, list_column,sort_columns);//分组浏览参赛队内部框架类
        this.tablemodel.addTableModelListener(this);       //注册表格模型事件监听器
        split_ver.setDividerLocation(this.getHeight()-90); //设置水平分隔条位置

        //以下创建命令面板，添加文本行数组输入对象属性，添加控制按钮
        JPanel cmdpanel=new JPanel();                      //面板默认流布局
        split_ver.setRightComponent(cmdpanel);             //垂直分割窗格下部添加命令面板
        this.texts = new JTextField[columnNames.length];
        for(int i=0; i<columnNames.length; i++)            //添加输入列
        {
            cmdpanel.add(new JLabel(columnNames[i], JLabel.RIGHT));
            cmdpanel.add(this.texts[i] = new JTextField(6));
        }
        String[] buttonstr={"添加","删除选中多行"};
        for(int j=0; j<buttonstr.length; j++)              //添加按钮
        {
            JButton button = new JButton(buttonstr[j]);
            cmdpanel.add(button);
            button.addActionListener(this);
        }
//？？        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), null);//删除表格第2列的图标单元渲染器，因为无法选中行       
    }

    public void actionPerformed(ActionEvent event)         //动作事件处理方法
    {
        switch(event.getActionCommand())                   //单击按钮
        {
            case "添加":
                String xgroup=this.texts[0].getText();    //组别
                //下句若在table数据库表中插入(组别,队名)元素，则列表框添加组名并选中
                if(add(this.table, xgroup, this.texts[1].getText()))
                     //下句在listmodel中查找、插入并选中xgroup，从1开始；更新表格模型
                     insert(this.listmodel, xgroup, 1);
                break;
                
            case "删除选中多行":
                removeSelectedAll(this.jtable, this.tablemodel, this.table, this.listmodel);
                break;
        }
    }
    
    //若串不空、组别范围合适、组不满、队名不重复，
    //则在table数据库表中插入(xgroup, name)一行，返回是否插入
    private boolean add(String table, String xgroup, String name)
    {
        if(xgroup.equals("") || name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "错误，组别和队名不能是空串。");
            return false;
        }
        xgroup = xgroup.toUpperCase();           //改成大写字母
        if(xgroup.compareTo("A")<0 || xgroup.compareTo("H")>0)
        {
            JOptionPane.showMessageDialog(this, "组别错误，"+xgroup+"超出A～H范围。");
            return false;
        }
        try
        {
            String sql="SELECT COUNT("+list_column+") FROM "+this.table+
                      " WHERE "+list_column+"='"+xgroup+"'";//统计xgroup组的队数
            ResultSet rset=this.stmt.executeQuery(sql);    //执行数据查询语句
            rset.next();
            if(rset.getInt(1)>=4)                          //结果值
            {
                JOptionPane.showMessageDialog(this, xgroup+"组已有4个队，不能添加。");
                return false;
            }
                
            sql="SELECT COUNT(" +list_column +") FROM "+this.table+
                " WHERE "+list_column+"='"+xgroup+"'"+" AND teamname='"+name+"'";
//                System.out.println(sql);
            rset=this.stmt.executeQuery(sql);              //查询xgroup组中name队
            rset.next();
            if(rset.getInt(1)>=1)                          //结果值>=1，表示name队已存在
            {
                JOptionPane.showMessageDialog(this, xgroup+"组已有"+name+"队，不能添加。");
                return false;
            }

            sql="INSERT INTO "+this.table+"(xgroup, teamname)"+
                " VALUES ('"+xgroup+"', '"+name+"')";
//                System.out.println(sql);
            if(this.stmt.executeUpdate(sql)==0)            //数据库表中插入一行，结果表示影响的行数
            {
                JOptionPane.showMessageDialog(this, "插入数据不成功。");
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    //已知列表框数据项按T类升序排序，T实现Comparable<? super T>接口。
    //将x对象插入到listmodel列表框模型中，不插入重复项。采用二分法查找，从begin开始
    public <T extends Comparable<? super T>> void insert(DefaultListModel<T> listmodel, T x, int begin)
    {
        int end=listmodel.getSize()-1, mid=end;
        while(begin<=end)                                  //边界有效
        {
            mid = (begin+end)/2;                           //中间位置，当前比较元素位置
            if(x.compareTo(listmodel.elementAt(mid))==0)   //比较对象大小，若相等，则查找成功，不插入
            {
                if(this.jlist.getSelectedIndex()==mid)     //选中项，不会触发列表框选择事件
                    valueChanged(null);                    //执行列表框选择事件处理方法
                else
                    this.jlist.setSelectedIndex(mid);      //选中mid数据项，触发列表框选择事件
                return;
            }
            if(x.compareTo(listmodel.elementAt(mid))<0)    //若x对象小，
                end = mid-1;                               //则查找范围缩小到前半段，
            else
                begin = mid+1;                             //否则查找范围缩小到后半段
        }
        listmodel.insertElementAt(x, begin);     //查找不成功，插入x到listmodel第begin项
        //下句列表框选中第begin项，触发列表框选择事件，表格中显示满足条件的结果集
        this.jlist.setSelectedIndex(begin);
    }    
    
    //将jtable表格的选中多行数据，在tablemodel表格模型和table数据库表中全部删除，
    private void removeSelectedAll(JTable jtable, DefaultTableModel tablemodel,
                                   String table, DefaultListModel<String> listmodel)
    {
        if (tablemodel.getRowCount()==0)
            JOptionPane.showMessageDialog(this, "表格空，不能删除数据项。");
        else
        {
            int[] rows = jtable.getSelectedRows();         //表格选中多行的行号
            if(rows.length==0)
                JOptionPane.showMessageDialog(this, "请选择表格的数据项。");
            else if(JOptionPane.showConfirmDialog(this, "删除选中多行？")==0)//确认对话框，单击Yes按钮返回0
                for(int i=rows.length-1; i>=0; i--)
                {
                    String xgroup=(String)tablemodel.getValueAt(rows[i],0);//组别
                    String name=(String)tablemodel.getValueAt(rows[i],1);  //队名
                    String sql="DELETE FROM "+table+" WHERE (teamname='"+name+"')";
                    try
                    {
                        if(this.stmt.executeUpdate(sql)==1)   //执行DELETE语句，删除一行
                        {
                            tablemodel.removeRow(rows[i]);     //表格模型删除指定行，//触发表格模型事件
                            //以下在table表中查询，若xgroup组中没有元素，则删除listmodel列表框中相应组名
                            sql="SELECT COUNT(" +list_column +") FROM "+table+
                                 " WHERE "+list_column+"='"+xgroup+"'";
//                      System.out.println(sql);
                            ResultSet rset=this.stmt.executeQuery(sql);
                            rset.next();
                            if(rset.getInt(1)==0)
                                listmodel.removeElement(xgroup);  //列表框删除数据项
                        }
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        }
    }
    
    //表格模型事件处理方法，当表格模型插入、修改、删除数据项值时触发
    //仅当修改表格模型值时，用表格模型当前编辑值更新结果集并提交数据库
    public void tableChanged(TableModelEvent event)
    {
//        System.out.println("event.getType()="+event.getType());
        if(event.getType()==TableModelEvent.UPDATE)
        {
            int row=event.getFirstRow(), col=event.getColumn();//获得表格模型当前修改的行和列
            if(col==0)
            {
                JOptionPane.showMessageDialog(this, "不能修改组别。");
                valueChanged(null);              //执行列表框选择事件处理方法，将组别改回原值 
            }
            else if(row!=-1 && col!=-1 && col==1)          //修改参赛队名
                try
                {
                    String value=(String)tablemodel.getValueAt(row, col);//获得表格模型修改的值
                    this.rset_team.absolute(row+1);        //设置结果集当前行
                    this.rset_team.updateString(col+1, value); //用表格模型修改值value设置结果集指定列，字符串
                    System.out.println("row+1="+(row+1)+"，col+1="+(col+1)+"，value="+value);
                    this.rset_team.updateRow();            //将结果集当前行提交数据库，更新数据库
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        }
    }
}
//@author：Yeheya，2018年3月12日，2018年8月30日
/*
{
    this.rset_team.absolute(e.getFirstRow()+1);
    this.rset_team.deleteRow();//删除当前行并提交
}*//*
//    private JComboBox<String> combox;                      //组合框，选择组别
//    private DefaultComboBoxModel<String> combomodel;       //默认组合框模型
//没有用组合框模型，因为组合框模型不能与列表框模型采用相同算法
 * 
    //将table表中list_column列的所有不重复值添加到组合框模型中，算法同列表框模型
//    public void query(DefaultComboBoxModel<String> combomodel)  //第6章没讲组合框模型,
     //讲也没有,此处无法与列表框模型共用方法
    public void query(JComboBox<String> combox)
    {
        String sql="SELECT DISTINCT "+list_column+" FROM "+table+" ORDER BY "+list_column;
        try
        {   ResultSet rset=statement.executeQuery(sql);    //执行数据查询SELECT语句，返回结果集
            while (rset.next())                            //迭代遍历结果集
               combox.addItem(rset.getString(1));   //添加当前行指定列值到组合框模型
            rset.close();
        }
        catch(SQLException e)
        {
            ex.printStackTrace();
        }
    } * 
            this.combox.insertItemAt(xgroup,j); //按升序插入到组合框模型指定位置
            combomodel.insertElementAt(xgroup,j); //按升序插入到组合框模型指定位置
            this.combox.addItem(xgroup); //按升序插入到组合框模型指定位置
            combomodel.addElement(xgroup); //添加到组合框模型最后
            this.combox.removeItem(xgroup); //组合框删除指定组别
             this.combomodel.removeElement(xgroup); //组合框删除指定组别
* */