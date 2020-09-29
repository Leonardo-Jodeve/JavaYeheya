//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月9日
//§6.3.4 列表框和组合框
//【例6.4】 Person对象信息管理。
//（1）Person面板，用组合框模型
//测试，用组合框模型，用泛型参数
//【思考题6-3】  ① 使用日期组件输入出生日期。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;                    //带标题的边框

//Person对象信息面板类，继承面板类，响应动作事件；Person类见例3.3
//Item选择事件用于测试，第5版教材没写
public class PersonJPanel extends JPanel implements ActionListener, ItemListener
{
    private JTextField text_name, text_date;               //姓名、出生日期文本行
    private JRadioButton[] radios;                         //性别按钮数组
    public JComboBox<String> combox_province, combox_city; //省份、城市组合框
    private static String[] provinces={"江苏", "浙江","湖南","湖北","广西","广东"};   //存储多省及城市
    private static String[][] cities={{"南京","苏州","无锡"},{"杭州","宁波","温州"},
                                      {"长沙"},{"武汉"},{"南宁"},{"广州"}};

    public PersonJPanel()                                  //构造方法
    {
        this.setBorder(new TitledBorder("Person"));        //设置面板具有带标题的边框
        int n = Person.class.getFields().length;           //获得指定类的公有成员变量个数，第12章12.2反射
        this.setLayout(new GridLayout(n,1));               //面板网格布局，5行1列
        
        this.add(this.text_name = new JTextField("姓名"));
        this.add(this.text_date = new JTextField("2000年1月1日"));

        String[] str={"男","女"};
        JPanel rbpanel=new JPanel(new GridLayout(1,2));    //性别面板，1行2列网格布局，单选按钮
//        rbpanel.setBorder(new TitledBorder("性别"));
        this.add(rbpanel);
        ButtonGroup bgroup = new ButtonGroup();            //按钮组
        this.radios = new JRadioButton[str.length];
        for(int i=0; i<this.radios.length; i++)
        {
            rbpanel.add(this.radios[i]=new JRadioButton(str[i])); //创建单选按钮，默认不选中，添加到性别面板
            bgroup.add(this.radios[i]);                   //单选按钮添加到按钮组
        }        
        this.radios[0].setSelected(true);                 //单选按钮选中
        this.add(this.combox_province = new JComboBox<String>(PersonJPanel.provinces));//省份组合框
        this.add(this.combox_city = new JComboBox<String>(PersonJPanel.cities[0]));//城市组合框
        this.combox_province.addActionListener(this);      //省份组合框监听动作事件
        this.combox_province.addItemListener(this);        //省份组合框监听选择事件
    }

    public void set(Person per)                  //设置各组件分别显示per对象的属性
    {
//        System.out.println("p="+(p==null?"null":p.toString()));
        if(per==null)
            return;
        this.text_name.setText(per.name);
        this.text_date.setText(per.birthdate.toString());
        if(per.gender.equals("男"))
            this.radios[0].setSelected(true);
        else
            this.radios[1].setSelected(true);
        this.combox_province.setSelectedItem(per.province);  //触发动作事件
        this.combox_city.setSelectedItem(per.city);
    }
    public Person get()                //返回各组件表示属性值的Person对象，处理日期格式异常
    {
        //下句获得单选按钮表示的性别字符串
        String gender = radios[0].isSelected() ? radios[0].getText() : radios[1].getText();
        try
        {
            //下句若text不能构造日期，将抛出两种异常，见例5.3 
            MyDate birthdate = new MyDate(this.text_date.getText());
            return new Person(text_name.getText(), birthdate, gender,
                       (String)combox_province.getSelectedItem(), (String)combox_city.getSelectedItem());//获得组合框选中项的字符串
        }
        catch(NumberFormatException ex)          //捕获数值格式异常，Java声明
        {
            JOptionPane.showMessageDialog(this, ex.getMessage()+" 字符串不能转换成整数。");
        }
        catch(DateFormatException ex)            //捕获日期格式异常，见例5.3
        {
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return null;
    }
    
    public void actionPerformed(ActionEvent event)  //单击事件处理方法，在组合框的下拉列表中选择数据项时执行
    {//选择时执行一次
//        System.out.println(this.getClass().getName()+"，"+event.getClass().getName()+"，"+
//                event.getSource().getClass().getName()+"，"+event.getActionCommand()+"，"+
//                this.combox_province.getSelectedItem());
        
        int i=this.combox_province.getSelectedIndex();     //省份组合框当前选中项序号
        if(cities!=null && i!=-1)
        {
            this.combox_city.removeAllItems();             //清除城市组合框中原来的所有内容，触发this.combox_city的动作事件
            for(int j=0; j<PersonJPanel.cities[i].length; j++)
                this.combox_city.addItem(PersonJPanel.cities[i][j]);//城市组合框添加数据项
            
/*            //使用组合框模型与否，效果相同
            DefaultComboBoxModel<String> comboxModel = (DefaultComboBoxModel<String>)this.combox_city.getModel();
            comboxModel.removeAllElements();                //清除城市组合框中原所有内容，触发this.combox_city的动作事件
            for (int j=0; j<cities[i].length; j++)
                comboxModel.addElement(cities[i][j]);   //城市组合框添加数据项*/
        }
    }
        
    //以下教材没写
    //组合框模型研究，教材没写
    private <T> void println(JComboBox<T> combox)
    {
        int n=combox.getItemCount();          //省份组合框当前选中项序号
        for(int i=0; i<n; i++)
           System.out.print(combox.getItemAt(i));   //城市组合框添加数据项
        System.out.println();

        DefaultComboBoxModel<T> comboxModel = (DefaultComboBoxModel<T>)combox.getModel();
        n = comboxModel.getSize();          //省份组合框当前选中项序号
        for(int i=0; i<n; i++)
           System.out.print(comboxModel.getElementAt(i));   //城市组合框添加数据项
        System.out.println();
    }
    //结论：每个组合框都有默认组合框模型，combox.addItem()等价于添加到组合框模型。
    
    //选择事件研究，教材没写。列表框选中，设置省份时触发该事件
    public void itemStateChanged(ItemEvent event)   //在用户已选定或取消选定某项时调用
    {
//        System.out.println(this.getClass().getName()+"，"+event.getClass().getName()+"，"+event.getSource().getClass().getName()
//                +"，event.getStateChange()="+event.getStateChange()+"，e.getItem()="+event.getItem());
        //说明，一次选择执行2次该事件。
        //e.getStateChange()=2，e.getItem()=原值
        //e.getStateChange()=1，e.getItem()=改变值
        //若改变值==原值，则不执行
    }    
}

/*
1、组合框选择数据项，执行2次ItemEvent事件，但ev.getStateChange()状态和ev.getItem()取值不同
2、组合框选择数据项，执行1次ActionEvent事件，e.getActionCommand()值为"comboBoxChanged"
       省份组合框初值为“江苏”，选择”浙江“，运行结果如下：
PersonJPanel，java.awt.event.ItemEvent，javax.swing.JComboBox，e.getStateChange()=2，e.getItem()=江苏
PersonJPanel，java.awt.event.ItemEvent，javax.swing.JComboBox，e.getStateChange()=1，e.getItem()=浙江
PersonJPanel，java.awt.event.ActionEvent，javax.swing.JComboBox，comboBoxChanged，浙江
       再选择“江苏”，运行结果如下：
PersonJPanel，java.awt.event.ItemEvent，javax.swing.JComboBox，e.getStateChange()=2，e.getItem()=浙江
PersonJPanel，java.awt.event.ItemEvent，javax.swing.JComboBox，e.getStateChange()=1，e.getItem()=江苏
PersonJPanel，java.awt.event.ActionEvent，javax.swing.JComboBox，comboBoxChanged，江苏
      再选择“江苏”，即组合框选择项与之前相同，则不触发ItemEvent事件。
PersonJPanel，java.awt.event.ActionEvent，javax.swing.JComboBox，comboBoxChanged，江苏


//删除所有项
             int n=this.combox_city.getItemCount();          //省份组合框当前选中项序号
            for (int j=n-1; j>=0; j--)
                this.combox_city.removeItemAt(j);   //城市组合框添加数据项

//        //【思考题6-4】  ① 使用日期组件输入出生日期。
//        this.mydate = new MyDateSpinJPanel(new MyDate(2000,12,31));
//        this.remove(1);
//        this.add(this.mydate,1);                           //替换出生日期组件

        //研究
/*        println(this.combox_province);
        println(this.combox_city);
        this.combox_city.addActionListener(this);      //组合框注册动作事件监听器
//        this.combox_province.addItem("湖南");
        this.combox_province.addActionListener(new ActionAdapter());//组合框两次注册动作事件监听器，如何执行？
        this.combox_province.addActionListener(this);      //省份组合框注册动作事件监听器
        this.combox_province.setEditable(true);

//适配器类，没有用到。输入Person对象信息面板
class ActionAdapter implements ActionListener
{
    public void actionPerformed(ActionEvent e)             //动作事件处理方法，在组合框的下拉列表中选择数据项时执行
    {
        System.out.println(this.getClass().getName()+"，"+e.getClass().getName()+"，"+
                e.getSource().getClass().getName()+"，"+e.getActionCommand()+"，"+
                ((JComboBox<String>)e.getSource()).getSelectedItem());
    }
}
/*
问题研究：组合框两次注册动作事件监听器，如何执行？
说明：都执行，执行次序是，后注册的先执行。
ActionAdapter，java.awt.event.ActionEvent，javax.swing.JComboBox，comboBoxChanged，浙江
PersonJPanel，java.awt.event.ActionEvent，javax.swing.JComboBox，comboBoxChanged，浙江


 */ 
//@author：Yeheya，2016-9-15 中秋节；2017年2月6日