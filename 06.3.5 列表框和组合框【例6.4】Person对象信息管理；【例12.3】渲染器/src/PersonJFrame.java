//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月9日
//§6.3.4 列表框和组合框
//【例6.4】  Person对象信息管理。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）和例3.3（Person）。
//（2）Person对象信息管理框架
//第12章，§12.3.2 列表框   1.列表框多项选择  
//【课程设计12】列表框删除选中多项。使用ArrayList<T>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;                                        //第12章集合框架

//Person对象信息管理框架类，继承框架类，响应动作事件、列表框选择事件
public class PersonJFrame extends JFrame
    implements ActionListener, ListSelectionListener
{
    protected JList<Person> jlist;                         //列表框
    protected DefaultListModel<Person> listmodel;          //列表框模型
    protected PersonJPanel person;                         //Person对象信息面板
    protected JComboBox<String>[] comboxs;                 //查找、排序依据组合框
    private static Equalable[] equal={new EqualName(), new EqualBirthdate()}; //稍后说明
    private static Comparator[] comparators={new CompareName(), new CompareBirthdate()}; //例4.4
    private Font font = new Font("宋体",1,28);     //字号大，讲课用，第5版教材没写
    
    //构造方法，pers指定初始对象数组，pers==null时，没有初值
    public PersonJFrame(Person[] pers)
    {
        this(pers, new PersonJPanel());                   //创建Person对象信息面板
    }
    public PersonJFrame(Person[] pers, PersonJPanel person)//【思考题6-5】person引用子类实例
    {
        super("Person对象信息管理");
//        this.setSize(700,240);                             //设置组件尺寸，截图，没改字号
        this.setSize(1200,768);                             //设置组件尺寸
        this.setLocationRelativeTo(null);                  //将窗口置于屏幕中央
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //单击窗口关闭按钮，结束程序运行
        
        //以下在框架内容窗格中添加分割窗格，其左边添加Person对象信息面板
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//水平分割窗格
        this.getContentPane().add(split);                  //框架内容窗格添加分割窗格
        this.person = person;                              //Person对象或其子类信息面板
        MyJPanel.setFont(this.person, font);               //设置面板中所有组件的字体

        split.add(this.person);                            //分割窗格左边添加Person对象信息面板
        split.setDividerLocation(200);                     //设置水平分隔条的位置
        split.setOneTouchExpandable(true);                 //提供一键展开按钮，快速展开/折叠分隔条
        
        //以下创建添加在分割窗格右边的面板，其中包含列表框和南部的命令面板
        JPanel rightpanel = new JPanel(new BorderLayout());//右面板边布局，包含列表框和命令
        split.add(rightpanel);                             //分割窗格右边添加右面板
        this.listmodel = new DefaultListModel<Person>();   //创建空的列表框模型
        if(pers!=null)
            for(int j=0; j<pers.length; j++)
                this.listmodel.addElement(pers[j]);        //列表框模型添加数据项
        this.jlist = new JList<Person>(this.listmodel);    //创建列表框，指定列表框模型
        this.jlist.setFont(font);//new Font("宋体",1,28));           //设置列表框组件的字体
        this.jlist.setBorder(new TitledBorder("Person对象列表框"));  //第5版没写
        this.jlist.addListSelectionListener(this);         //列表框监听选择事件
        rightpanel.add(new JScrollPane(this.jlist));       //右面板添加包含列表框的滚动窗格
        JPanel cmdpanel = new JPanel();                    //命令面板，默认流布局（居中）
        rightpanel.add(cmdpanel,"South");                  //右面板南边添加命令面板
         
        //以下在命令面板上添加按钮和查找、排序组合框
        String[][] str={{"添加","删除","删除选中项","删除选中多项"},{"查找关键字","排序关键字"},{"姓名","出生日期"}};
        for(int i=0; i<str[0].length; i++)                 //添加按钮
        {
            JButton button = new JButton(str[0][i]);
            button.addActionListener(this);
            cmdpanel.add(button);
        }
        this.comboxs = new JComboBox[str[1].length];       //<String>不行
        for(int i=0; i<str[1].length; i++)                 //添加查找、排序关键字组合框
        {
            cmdpanel.add(new JLabel(str[1][i]));
            cmdpanel.add(this.comboxs[i]=new JComboBox<String>(str[2]));
            this.comboxs[i].addActionListener(this);       //组合框监听动作事件
        }
        MyJPanel.setFont(cmdpanel, font);        //设置面板中所有组件的字体，第5版教材没写
        this.setVisible(true);
        this.jlist.setCellRenderer(new FontListRenderer(font)); //12.3.2 列表框单元渲染器，在【例12.3】项目中。
        
        //研究
//        this.jlist.setSelectedIndex(0);          //列表框选中，触发列表框选择事件 
    }
    public PersonJFrame()
    {
        this(null, new PersonJPanel());          //创建Person对象信息面板
    }

    //列表框选择事件处理方法，选中列表框数据项时触发执行
    public void valueChanged(ListSelectionEvent event)
    {
    	System.out.println(this.jlist.getSelectedValue());
    	this.person.set(this.jlist.getSelectedValue());//按列表框选中项设置Person面板各组件取值
        //运行时多态，
        //当this.person引用StudentJPanel时，this.jlist.getSelectedValue()返回Student，
        //this.person.set()执行StudentJPanel.set(Person)方法，参数类型必须是Person，否则无法覆盖，无法运行时多态
    }
    
    public void actionPerformed(ActionEvent event)            //动作事件处理方法，单击按钮，选择组合框
    {    
//        System.out.println(this.getClass().getName()+"，"+event.getSource().getClass().getName()+"，"+event.getActionCommand());
        if(event.getSource() instanceof JButton)           //单击了按钮
        {
            Person per=null;
            switch(event.getActionCommand())     //JDK 8，switch条件表达式可以是String
            {
                case "添加":                               //单击“添加”按钮
                    if((per=this.person.get())!=null)      //per获得Person实例，运行时多态
                        this.listmodel.addElement(per);    //列表框模型添加Person对象
                    break;
                    
                case "删除":                                 //单击“删除”按钮
                	remove(this.listmodel, this.person.get());
                    break;
                    
                case "删除选中项":
                    this.removeSelected(this.jlist, this.listmodel);
                    break;
                    
                case "删除选中多项":  //第5版第6章没写，第4版例12.2
                    this.removeSelectedAll(this.jlist, this.listmodel);
                    break;
            }
        }
        else if(event.getSource() instanceof JComboBox)    //查找和排序组合框
        {
            jlist.clearSelection();                        //清除所有选中状态
            if(event.getSource()==this.comboxs[0])         //查找组合框
            {
                int i=this.comboxs[0].getSelectedIndex();  //获得查找关键字序号
                if(i<equal.length)                    //为什么？过滤子类增加的数据项序号
                    selectAll(this.listmodel, this.jlist, this.person.get(), equal[i]);
                                       //查找并选中所有与person相等对象
//                    ListModelOper.selectAll(this.listmodel, this.jlist, this.person.get(), equal[i]);
//              return;
            }
            else if(event.getSource()==this.comboxs[1])    //排序组合框
            {
                int i=this.comboxs[1].getSelectedIndex();  //获得排序关键字序号
                if(i<comparators.length)            //为什么？过滤子类增加的数据项序号
                    sort(this.listmodel, comparators[i]);  //列表框模型数据项排序
//                   ListModelOper.sort(this.listmodel, comparators[i]);  //列表框模型数据项排序
            }
        }
    }
    
    //在listmodel列表框模型中删除与obj相等的数据项，通用方法
    public <T> void remove(DefaultListModel<T> listmodel, T obj)
    {
        //下句弹出确认对话框见图6-14(b)，单击“是”按钮返回0，单击“否”按钮返回1
        if(obj!=null && JOptionPane.showConfirmDialog(this, "删除 "+obj.toString()+"？", "确认",
            JOptionPane.YES_NO_OPTION)==0)
        {
            //下句在listmodel列表框模型中删除首个与obj指定对象相等的数据项，
            //顺序查找算法，默认调用obj的equals(Object)方法比较对象相等。
            boolean remove = this.listmodel.removeElement(obj);
            JOptionPane.showMessageDialog(this, remove?"删除成功":"查找不成功，没有删除");
        }
    }
    
    //将jlist列表框选中项行数据在listmodel列表框模型中删除，通用方法
    public <T> void removeSelected(JList jlist, DefaultListModel<T> listmodel)
    {
        if(this.listmodel.getSize()==0)               //返回列表框数据项数
            JOptionPane.showMessageDialog(this, "列表框为空，不能删除");
        else
        {   
   	        int i=this.jlist.getSelectedIndex();       //列表框选中数据项序号
            if(i==-1)
                JOptionPane.showMessageDialog(this, "请选中列表框数据项");
            else
            {
                String str=this.jlist.getSelectedValue().toString(); //列表框选中数据项字符串
                if(JOptionPane.showConfirmDialog(this, "删除 第"+i+"项("+str+")？","确认", 
                    JOptionPane.YES_NO_OPTION)==0)     //确认对话框，单击“是”按钮，则返回0
                    this.listmodel.removeElementAt(i); //删除列表框第i数据项
            }
        }
    }
    //说明。（1）如果列表框空，或没有选中一项，i==-1，jlist.getSelectedValue()返回null。
    //（2）removeElementAt(-1)，抛出异常ArrayIndexOutOfBoundsException: Array index out of range: -1

    
    //第12章，§12.3.2 列表框   1.列表框多项选择  
    //【课程设计12】列表框删除选中多项。使用ArrayList<T>
    //将jlist列表框选中多项数据在listmodel列表框模型中全部删除，通用方法。
    public <T> void removeSelectedAll(JList jlist, DefaultListModel<T> listmodel)
    {
        try
        {   //下句当列表框选中多项时，返回列表集合对象
            ArrayList<Person> list=(ArrayList<Person>)this.jlist.getSelectedValuesList();
            for(Person per:list)                 //逐元循环，per获得list集合中的每个元素
                this.listmodel.removeElement(per); //删除列表框对象值为per的数据项
        }
        catch(ClassCastException ex)             //如果列表框空，或没有选中一项，则抛出异常
        {
            JOptionPane.showMessageDialog(this, "列表框空，或没有选中数据项，不能删除");
        }
    }
    //说明。（1）jlist.getSelectedValues();方法已被废弃。
    //（2）以下语法不行
//      List<Person> list=(ArrayList<Person>)this.jlist.getSelectedValuesList();
//      List<Person> list=(LinkedList<Person>)this.jlist.getSelectedValuesList();
    //  LinkedList<Person> list=(LinkedList<Person>)this.jlist.getSelectedValuesList();
	

    //以下两方法是对列表框模型数据项进行排序和查找的通用算法。 
    //在列表框模型中，顺序查找并选中所有与key相等的数据项。 
    //由Equalable<? super T>对象eq提供比较T对象相等方法
    public <T> void selectAll(DefaultListModel<? super T> listmodel, 
            JList<? super T> jlist, T key, Equalable<? super T> eq)
    {
        int n=listmodel.getSize();                         //返回列表框的数据项数
        for(int i=0; i<n; i++)
            if(eq.equals(key, (T)listmodel.elementAt(i)))  //比较两个T对象是否相等
                jlist.addSelectionInterval(i, i);          //列表框添加选中第i项（多选状态），没有触发列表框选中事件
//        jlist.setSelectedIndex(i);                 //列表框选中第i项，没有触发列表框选中事件
        //只选中最后一个
    }

    //将listmodel列表框模型数据项排序，由Comparator<? super T>接口对象c比较T对象大小。直接选择排序算法
    //listmodel引用赋值，在方法体中修改其数据项，作用于实际参数。
	//第12章，语法正确，能接受Comparator<Student>参数
    public <T> void sort(DefaultListModel<? super T> listmodel, Comparator<? super T> c)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)    //直接选择排序算法
        {
            int min=i; 
            for(int j=i+1; j<listmodel.getSize(); j++)
                if(c.compare((T)listmodel.getElementAt(j), (T)listmodel.getElementAt(min))<0)
                    min = j; 
            if(min!=i) 
            {   
                T temp = (T)listmodel.getElementAt(i);
                listmodel.setElementAt((T)listmodel.getElementAt(min), i);
                listmodel.setElementAt(temp, min);
            }
        }        
    }
    
    public static void main(String arg[])
    {
        Person[] pers={new Person("Li李小明",new MyDate(1994,3,15),"男","江苏","南京"),
                       new Person("Bai白杨", new MyDate(1997,5,1),"女","江苏","苏州"),
                       new Person("Bai白桦", new MyDate(1994,3,15),"男","浙江","杭州"),
                       new Person("Hua华文", new MyDate(1999,10,1),"女","浙江","宁波"),
                       new Person("Wang王伟", new MyDate(1998,4,3),"男","湖南","长沙"),
                       new Person("Zhang张莉",new MyDate(1998,4,3),"女","湖北","武汉"),
                       new Person("Zhu朱小红",new MyDate(1994,3,15),"女","广东","广州"),
                       new Person("Zhao赵军",new MyDate(1999,10,1),"男","广西","南宁")};
        new PersonJFrame(pers);
//        new PersonJFrame();
    }
}
/*
程序设计说明如下。
1、以下语法错，不能声明泛型数组，虽然语义是对的。
    private static Equalable<Person> equal[]={new EqualName(), new EqualBirthday()};
    private static Comparator<Person> comparators[]={new CompareName(), new CompareBirthday()};
    private static Equalable<? extends Person>[] equal={new EqualName(), new EqualBirthday()};

2、以下语法正确：
    private static Equalable<?> equal[]={new EqualName(), new EqualBirthday()};
    private static Comparator<?> comparators[]={new CompareName(), new CompareBirthday()};
      但其后以下声明出现语法错：为什么？？
    public <T> void search(T obj, Equalable<? super T> e)
    public <T> void sort(java.util.Comparator<? super T> c)//第12章，语法正确，能接受Comparator<Student>参数
      
    //思考题：删除选中多项；渲染器，第12章实现。
    //思考题：多种查找和排序规则，按年龄查找??
    //失败，不能实现组件事件监听器接口。
    //当改变框架大小时，设置其中所有组件的字体字号，使之随着框架尺寸而改变大小

3、调用setSelectedIndex(i)和setSelectedValue(obj,scroll)方法
    设置列表框选中项，触发列表框选择事件，event.getValueIsAdjusting()=false，执行一次。
    
4、鼠标操作选中列表框一项，执行列表框选择事件两次，取值不同：
         System.out.println("event.getValueIsAdjusting()="+event.getValueIsAdjusting());
event.getValueIsAdjusting()=true
event.getValueIsAdjusting()=false

       //增加以下语句，当选中一项时，执行一次该事件
//        if (!event.getValueIsAdjusting())
 */
//@author：Yeheya，2016-9-15 中秋节，2017年2月6日，2018年8月26日