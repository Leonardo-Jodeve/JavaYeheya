//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月9日
//§6.3.2 文本显示和编辑组件及事件
//【例6.3】  中文大写金额。
//第5版字号随窗口大小写而改变。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//中文大写金额框架类，继承框架类；响应文本编辑事件（也可响应动作事件）、组件事件
public class MoneyJFrame extends JFrame implements CaretListener, ComponentListener
{
    private JTextField text_money, text_str;     //两个文本行
    private MessageJDialog jdialog;              //对话框，内部类对象

    public MoneyJFrame()
    {
        super("中文大写金额");
        this.setBounds(300,240,420,110);                   //窗口大小和位置
        this.setBackground(java.awt.Color.lightGray);      //JFrame背景色默认浅灰
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //单击窗口关闭按钮，结束程序运行
//        this.setResizable(false);                          //窗口大小不能改变
        this.addComponentListener(this);                   //框架注册组件事件监听器，改变字号
        
        //以下设置框架的内容窗格为流布局且右对齐，在内容窗格中添加组件
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
//        this.getContentPane().setLayout(new GridLayout(2,2)); //框架网格布局，2行2列，不行
        this.getContentPane().add(new JLabel("金额", JLabel.RIGHT));  //添加标签（右对齐）
        this.text_money = new JTextField("12345678.90",40);
        this.text_money.setHorizontalAlignment(JTextField.RIGHT);//设置水平方向右对齐
        this.getContentPane().add(this.text_money);
        this.text_money.addCaretListener(this);            //文本行注册编辑事件监听器

        this.getContentPane().add(new JLabel("中文大写", JLabel.RIGHT));
        this.text_str = new JTextField(40);                //截图24，演示40
        this.text_str.setHorizontalAlignment(JTextField.RIGHT);
        this.text_str.setEditable(false);                  //只能显示，不允许编辑
        this.getContentPane().add(this.text_str);
        caretUpdate(null);                                 //执行文本编辑事件
        this.setVisible(true);
        this.jdialog = new MessageJDialog();               //创建对话框对象，调用内部类的私有构造方法
//        this.jdialog = new MessageJDialog(this);         //创建对话框对象，static，或者声明为公有外部类
    }
    
    private class MessageJDialog extends JDialog           //消息对话框，私有实例内部类，对象嵌套
    {
        private JLabel jlabel;                             //显示消息的标签
        private MessageJDialog()                           //内部类的构造方法 
        {
            //下句中MoneyJFrame.this引用外部类当前对象（对话框依附的框架）；true表示模式窗口
            super(MoneyJFrame.this, "提示", true);
            this.setSize(900,100);
            this.jlabel = new JLabel("", JLabel.CENTER);   //标签的字符串为空，居中对齐
            this.getContentPane().add(this.jlabel);        //对话框的内容窗格添加标签
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);  //对话框关闭方式是隐藏，不结束运行
            //下句对话框注册组件事件监听器，委托外部类的this对象处理事件
            this.addComponentListener(MoneyJFrame.this);
        }
        private void show(String message)                  //对话框显示消息
        {
            this.jlabel.setText(message);                  //标签显示消息
            this.setLocation(MoneyJFrame.this.getX()+100, MoneyJFrame.this.getY()+100);
                             //对话框位置在框架的右下方，MoneyJFrame.this.getX()、getY()获得框架位置
            this.setVisible(true);                         //显示对话框
        }
    } //MessageJDialog内部类结束

    public void caretUpdate(CaretEvent event)              //文本编辑事件处理方法
    {
        String money = this.text_money.getText();          //获得输入金额字符串
        if(money.isEmpty())
            this.text_str.setText("");
        else
            try
            {
                double x = Double.parseDouble(money);      //将money串转换成浮点数，可能抛出异常
                this.text_str.setText(RMB.toString(x));    //获得x的中文大写形式
            }
            catch(NumberFormatException ex)                //捕获数值格式异常
            {
                //显示对话框，调用内部类的私有方法
//                this.jdialog.show("\""+money+"\" 不能转换成浮点数，请重新输入。");
//              jdialog.show(ex.getMessage()+" 不能转换成浮点数，请重新输入。");//显示对话框，调用内部类的私有方法
                JOptionPane.showMessageDialog(this, "\""+money+"\" 不能转换成浮点数，请重新输入。");
            }
            finally{}    	
    }

    //以下方法实现组件事件监听器接口。通用方法，但是不可独立成为一个类。
    //当改变框架大小时，设置其中所有组件的字体字号，使之随着框架尺寸而改变大小
    public void componentResized(ComponentEvent event)
    {
//        System.out.println(event.getComponent().getClass().getName()); //当前JFrame或JDialog
        Component comp=event.getComponent();               //获得事件源组件，引用JFrame或JDialog
        int size =(comp.getWidth()+comp.getHeight())/40;   //估算字号
        Font font = new Font("宋体",1,size);                //字体
        if(comp instanceof JFrame)              //comp==this
        {
            int n= this.getContentPane().getComponentCount();  //获得框架内容窗格中的组件个数
            for(int i=0; i<n; i++)                         //设置框架内容窗格中所有组件的字体
                this.getContentPane().getComponent(i).setFont(font);
        }
        else if(comp instanceof JDialog)
            this.jdialog.jlabel.setFont(font);   //设置对话框中标签组件的字体
    }
    public void componentMoved(ComponentEvent event) {}    //移动组件
    public void componentShown(ComponentEvent event) {}    //显示组件
    public void componentHidden(ComponentEvent event){}    //隐藏组件
    
    public static void main(String[] arg)
    {
        new MoneyJFrame();
    }
}
/*
内部类也可声明为static，必须声明JFrame变量，不能使用MoneyJFrame.this引用。
    也可声明MessageJDialog为公有外部类，定制一个显示消息的通用对话框。

/*

//        System.out.println(panel.getLayout().getClass().getName());//javax.swing.JToolBar$DefaultToolBarLayout
        this.getContentPane().add(panel,"North"); 
//      System.out.println(this.getContentPane().getLayout().getClass().getName());//javax.swing.JRootPane$1

*/
//@author：Yeheya，2016-9-9
