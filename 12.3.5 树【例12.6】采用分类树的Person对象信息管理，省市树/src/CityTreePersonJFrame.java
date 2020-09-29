//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月2日
//§12.1 集合框架
//§12.2 反射
//§12.3.5 树
//【例12.6】采用分类树的Person对象信息管理，以树结构显示中国省份和城市。
//MyEclipse设置编译路径包含项目：例3.2 MyDate类，例3.3 Person类，例3.5 Student类，
//    例6.4 Person对象信息管理，例8.3对象流文件。12.1 集合框架，例12.2反射
//（1）图形用户界面

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.lang.reflect.Field;                  //反射包的类

//Person对象信息管理框架类（以树结构显示中国省份和城市），响应树选择事件、动作事件、窗口事件
public class CityTreePersonJFrame extends JFrame 
    implements TreeSelectionListener, ActionListener, WindowListener
{
    private String objectFilename;               //对象文件名
    private MutableJTree tree;                   //树组件，支持插入和删除操作
    protected DefaultTableModel tablemodel;      //表格模型
    protected JTable jtable;                     //表格组件
    protected LinkedList<Person> list;           //循环双链表，列表元素为Person实例
    protected PersonJPanel person;               //Person对象信息面板，见例6.4
    public JComboBox<String>[] comboxs;          //选择查找、排序关键字组合框
    protected JPanel cmdpanel;                   //命令面板
    protected Field[] fields;                    //Person实例成员变量数组
    
    //构造方法，treeFilename参数指定保存树结点的文件名，objectFilename指定对象文件名，
    //titles指定表格标题，person指定对象信息面板
    public CityTreePersonJFrame(String treeFilename, String objectFilename, String[] titles, 
                                PersonJPanel person)
    {
        super("Person对象信息管理  输入并按省市分类浏览");
        this.setBounds(100, 100, 800, 430);
//        this.setSize(getToolkit().getScreenSize());        //获得屏幕分辨率，窗口最大化
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(this);                      //注册窗口事件监听器
        this.objectFilename = objectFilename;
        this.list = new LinkedList<Person>();              //创建空的循环双链表
        CollectionFile.readFrom(this.objectFilename, this.list); //读取指定对象文件到列表，见“12.1集合框架【】集合的通用方法”项目
        
        this.tree = new MutableJTree(treeFilename);        //创建可编辑的树，参数指定文件名
        this.tree.addTreeSelectionListener(this);          //树监听选择事件器
        //以下创建水平分割窗格，左边添加树组件，右边添加垂直分割窗格
        JSplitPane split_hor=new JSplitPane(1,new JScrollPane(this.tree),null);
        split_hor.setDividerLocation(120);                 //设置垂直分隔条的位置
        this.getContentPane().add(split_hor);
        
        JSplitPane split_ver=new JSplitPane(JSplitPane.VERTICAL_SPLIT); //垂直分割窗格
        split_ver.setDividerLocation(260);                 //设置水平分隔条的位置
        split_hor.add(split_ver);                          //水平分割窗格右边添加一个垂直分割窗格

        this.tablemodel=new DefaultTableModel(titles,0);   //默认表格模型，指定列标题，0行
        this.jtable=new JTable(this.tablemodel);           //创建空表格，指定表格模型  
        split_ver.add(new JScrollPane(jtable));            //垂直分割窗格上边添加包含表格的滚动窗格
        
        JPanel panel = new JPanel(new GridLayout(2,1));    //输入和命令面板
        split_ver.add(panel);                              //添加到垂直分割窗格下边
        this.person = person;                              //Person对象信息面板，见例6.4
        panel.add(this.person);
        this.person.setLayout(new GridLayout(1,6));        //Person对象信息面板1行6列
        this.person.combox_province.removeActionListener(this.person);//取消原动作事件监听器
        this.person.combox_province.addActionListener(this);
            //重新注册Person对象信息面板中省份组合框的动作事件监听器，由this处理事件
//        PersonJFrame.setFont(this.person, PersonJFrame.font); //设置面板中所有组件的字体，见例6.4，第5版没写，讲课演示用
        
        this.tree.addChild(this.tree.root, this.person.combox_province);
            //将树根的所有孩子结点添加到Person对象信息面板中的省份组合框。
            //组合框添加首个元素时，导致选中数据项改变，触发动作事件，添加城市组合框数据项
        this.fields = Reflection.getFields(person.get(),titles.length);//返回Person实例的所有公有成员变量，见例12.2项目
        this.tree.addSelectionRow(0);                      //选中树的根结点，触发TreeSelectionEvent事件，执行valueChanged()方法，空树时不执行
        
        panel.add(this.cmdpanel = new JPanel());           //添加命令面板
        String[][] str={{"添加","删除选中多行","统计"},{"查找关键字","排序关键字"}};
        for(int i=0; i<str[0].length; i++)                 //添加按钮
        {
            JButton button = new JButton(str[0][i]);
            button.addActionListener(this);
            cmdpanel.add(button);
        }
        this.comboxs = new JComboBox[str[1].length];       //<String>[2];不行
        for(int i=0; i<str[1].length; i++)                 //添加查找、排序关键字组合框
        {
            cmdpanel.add(new JLabel(str[1][i]));
            cmdpanel.add(this.comboxs[i]=new JComboBox<String>(Reflection.toString(this.fields)));
                           //添加组合框，组合框数据项是Person实例的所有公有成员变量
            this.comboxs[i].addActionListener(this);       //组合框注册动作事件监听器
        }
//        PersonJFrame.setFont(this.cmdpanel, PersonJFrame.font); //设置面板中所有组件的字体，见例6.4，第5版没写，讲课演示用
        this.setVisible(true);
    }    
    
    //树选择事件处理方法，选中结点时触发
    public void valueChanged(TreeSelectionEvent event)
    {
        if(event!=null)
            this.tree.expandPath(event.getPath());         //展开当前选中结点
        TreeNode node = (TreeNode)this.tree.getLastSelectedPathComponent();  //当前选中结点
        if(node!=null && node==this.tree.root)             //若选中根结点
            addTable(new ProvinceCityFilter("", ""));      //添加列表全部数据到表格
//??            addTable(new FieldsFilter(null,"province","city"));      //添加列表全部数据到表格
        else if(node!=null && node.getParent()==this.tree.root)//若选中省份结点
        {
            this.person.combox_province.setSelectedItem(node.toString());//省份组合框选中当前省份结点值，触发省份组合框的动作事件，将更改城市组合框的数据项
            addTable(new ProvinceCityFilter(node.toString(), "")); //添加选中省份数据到表格
        }
        else if(node!=null && node.getParent()!=null && node.getParent().getParent()==this.tree.root)//选中的是城市结点
        {
            this.person.combox_province.setSelectedItem(node.getParent().toString());//设置省份结点，触发动作事件
            this.person.combox_city.setSelectedItem(node.toString());    //设置城市结点
            addTable(new ProvinceCityFilter(node.getParent().toString(), node.toString())); //添加选中省份、城市数据到表格
        }
    }
    
    //在list列表中查找指定省份和城市元素，委托filter过滤器指定查找条件，将其元素添加到表格模型
    //若省市均为""，表示全部数据；若城市为""，表示当前省份中的全部城市。
    //SearchFilter<T>是查找条件过滤器接口，稍后解释。
    public <T extends Person> void addTable(SearchFilter<T> filter)
    {
        for(int i=this.tablemodel.getRowCount()-1; i>=0; i--)   //清空表格
            this.tablemodel.removeRow(i);              
        for(Iterator<Person> it=this.list.iterator(); it.hasNext(); ) //迭代器
//        for (T p: this.list)                          //不行，逐元循环，p获得list列表中每个元素
        {
            T per = (T)it.next();
        	//以下由filter过滤器指定查找条件，若查找成功，则表格添加一行，参数数组指定各列值
            if(filter.accept(per))
               this.tablemodel.addRow(Reflection.toArray(per, this.fields));
        }
    }

    public void actionPerformed(ActionEvent event)         //动作事件处理方法
    {        
//        System.out.println(event.getSource().getClass().getName()+"，"+event.getActionCommand());
        
        //省份组合框添加首个数据项“北京”，触发动作事件两次，两次选中数据项分别为null和“北京”
        if(event.getSource()==this.person.combox_province)//省份组合框选择数据项
        {
            String province=(String)this.person.combox_province.getSelectedItem();//获得当前省份
            TreeNode node = this.tree.search(province);    //在树中查找指定省份结点
            if(node!=null)                                 //查找成功，将省份的所有孩子结点添加城市组合框
                this.tree.addChild(node, this.person.combox_city);
        }
            
        else if(event.getActionCommand().equals("添加"))    //单击按钮
        {
            Person per = this.person.get();
            this.tablemodel.addRow(Reflection.toArray(per, this.fields)); //表格模型添加一行，一行数据由数组提供
            this.list.add(per);                            //列表添加一个对象
        }

        else if(event.getActionCommand().equals("删除选中多行"))
            removeSelectedAll(this.jtable, this.tablemodel, this.list);

//        System.out.println(event.getSource().getClass().getName()+"，"+event.getActionCommand());
        else if(event.getSource()==this.comboxs[0])        //查找组合框
        {
            String fieldname = (String)this.comboxs[0].getSelectedItem();
                                                   //获得查找组合框当前选中项字符串，即以该成员变量作为查找依据
            addTable(new FieldFilter<Person>(this.person.get(), fieldname)); 
                                       //添加表格，参数指定按this.person.get()的fieldname成员变量值进行过滤操作
        }

        else if(event.getSource()==this.comboxs[1])       //排序组合框
        {
            String fieldname=(String)this.comboxs[1].getSelectedItem();//获得成员变量名作为排序关键字
            Collections.sort(this.list, new CompareField<Person>(fieldname));
                                             //列表排序，比较器对象指定Person对象按fieldname成员变量比较对象大小
            if(this.tree.getSelectionRows()[0]==0)         //当前选中根结点
                addTable(new ProvinceCityFilter("", ""));  //添加列表全部数据到表格
            else
                this.tree.setSelectionRow(0);              //选中树的根结点，触发树的选择事件，为表格添加数据项
        }
    }
    
    //将jtable表格的选中多行数据，在tablemodel表格模型和list列表集合中全部删除
    private void removeSelectedAll(JTable jtable, DefaultTableModel tablemodel, LinkedList<Person> list)
    {
        if(tablemodel.getRowCount()==0)
            JOptionPane.showMessageDialog(this, "表格空，不能删除数据项。");
        else
        {
            int[] rows = jtable.getSelectedRows();         //表格选中多行的行号
            if(rows.length==0)
                JOptionPane.showMessageDialog(this, "请选择表格的数据项。");
            else if(JOptionPane.showConfirmDialog(this, "删除选中多行？")==0)//确认对话框，单击Yes按钮返回0
                for(int i=rows.length-1; i>=0; i--)
                {
                    Person per=get(this.tablemodel, rows[i]);//返回表格模型指定行表示的对象
                    list.remove(per);                      //列表删除对象，以equals()查找识别对象
                    tablemodel.removeRow(rows[i]);         //表格中删除一行，指定行号
                }
        }
    }
    
    //返回tablemodel表格模型第i行表示的对象
    public Person get(TableModel tablemodel, int i)
    {                                  //表格各列为：姓名、出生日期、性别、省份、城市
        return new Person((String)tablemodel.getValueAt(i,0), (MyDate)tablemodel.getValueAt(i,1),
                          (String)tablemodel.getValueAt(i,2), (String)tablemodel.getValueAt(i,3), 
                          (String)tablemodel.getValueAt(i,4)); 
    }
    
    
    public void windowClosing(WindowEvent event)           //关闭窗口事件处理方法
    {
        CollectionFile.writeTo(this.objectFilename, this.list);//将列表元素写入指定对象文件，见“12.1集合框架【】集合的通用方法”项目
    }
    public void windowOpened(WindowEvent event) {}
    public void windowActivated(WindowEvent event) {}
    public void windowDeactivated(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowDeiconified(WindowEvent event) {}
    
    public static void main(String[] args) 
    {
        String[] titles={"姓名","出生日期","性别","省份","城市"};  //指定表格列标题
        new CityTreePersonJFrame("cities.txt", "persons.obj", titles, new PersonJPanel());    //指定默认文件名
    }
}
//@author：Yeheya，2018年2月2日，2018年8月10日