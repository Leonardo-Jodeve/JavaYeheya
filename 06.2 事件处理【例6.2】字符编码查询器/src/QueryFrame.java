//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月2日
//§6.2.1 委托事件模型
//【例6.2】 Unicode字符编码查询器。
//【思考题6-1】设置文本行字体，改变字号。讲课演示。

import java.awt.*;
import java.awt.event.*;

//字符编码查询器框架类，继承框架类；声明实现ActionListner接口表示要响应动作事件。
//第5版教材没写实现ComponentListener接口功能，讲课演示用。
public class QueryFrame extends Frame
    implements ActionListener, ComponentListener //, WindowListener
{
    private TextField text_char, text_uni;            //显示字符和编码文本行
    private Button button_char, button_uni;           //查询编码和字符按钮
    //上述对象在构造方法中创建引用的实例，在事件处理方法中使用，所以声明为成员变量
    private TextField text_hex;                       //【思考题6-1】文本行，显示十六进制

    public QueryFrame()                               //构造方法
    {
        super("字符编码查询器");                       //以下设置框架的标题等属性
//        this.setBounds(300,240,340,120);              //设置框架的位置和尺寸
        this.setBackground(Color.lightGray);          //设置框架的背景颜色为浅灰色
//        this.setLayout(new GridLayout(2,3));          //框架网格布局，2行3列

        this.setBounds(300,240,1024,320);             //设置框架的位置和尺寸【思考题6-1】
        this.setLayout(new GridLayout(3,3,2,2));      //框架网格布局，3行3列，组件间水平和垂直间距为2【思考题6-1】

        //以下在框架窗口上添加两行组件，每行各有一个标签、文本行、按钮组件
        this.add(new Label("字符", Label.RIGHT));     //添加标签组件（右对齐）
        this.text_char = new TextField("汉字",10);    //文本行组件
        this.add(this.text_char);
        this.text_char.addActionListener(this);       //【思考题6-1】文本行注册动作事件监听器，委托this对象处理事件
        
        this.button_char = new Button("查询Unicode码");  //按钮组件
        this.add(this.button_char);
        this.button_char.addActionListener(this);     //按钮注册动作事件监听器，委托this对象处理事件

        //以下再添加一行标签、文本行、按钮组件
        this.add(new Label("Unicode编码", Label.RIGHT));
        this.add(this.text_uni = new TextField(10));    
        this.text_uni.addActionListener(this);        //【思考题6-1】文本行注册动作事件监听器，委托this对象处理事件
        this.add(this.button_uni = new Button("查询字符"));
        this.button_uni.addActionListener(this);      //按钮注册动作事件监听器，委托this对象处理事件

        //【思考题6-1】以下在框架窗口最后添加一行组件，包括一个标签、文本行、按钮组件
        this.add(new Label("Unicode编码（十六进制）", Label.RIGHT));//添加标签（右对齐）
        this.add(this.text_hex = new TextField(10));
        this.text_hex.setEditable(false);             //只能显示，不能编辑

        this.setVisible(true);                        //显示框架
        this.addWindowListener(new WinClose());       //框架注册窗口事件监听器，委托WinClose对象处理事件
//        this.addWindowListener(this);               //框架注册窗口事件监听器，委托this对象处理事件
        this.addComponentListener(this);              //框架注册组件事件监听器，改变字号
    }

    //动作事件处理方法，实现ActionListener接口。
    //单击按钮和文本行时触发执行，event.getSource()返回单击的那个按钮（事件源组件）
    public void actionPerformed(ActionEvent event)
    {
        //单击“查询Unicode码”按钮，或在“字符”文本行中按了回车键。比较引用
        if(event.getSource()==this.button_char || event.getSource()==this.text_char)
        {
            String str = this.text_char.getText();    //获得文本行字符串
            if(!str.isEmpty())                        //输入非空串执行操作，功能同!str.equals("")，不能用str!=""
            {
                char ch = str.charAt(0);              //获得首字符
                this.text_char.setText(""+ch);        //重新设置文本，显示字符
                this.text_uni.setText(""+(int)ch);    //显示ch的Unicode码
                this.text_hex.setText("0x"+Integer.toHexString((int)ch));
                //以十六进制形式显示ch的Unicode码【思考题6-1】
            }
        }        
        
        //单击“查询字符”按钮，或在“编码”文本行中按了回车键
        else if(event.getSource()==this.button_uni || event.getSource()==this.text_uni)
        {
            String str = this.text_uni.getText();     //获得文本行字符串
            if(!str.isEmpty())
            {
                //以下将str字符串转换成整数，未处理异常；再显示Unicode编码为该整数的字符
//                this.text_char.setText(""+(char)Integer.parseInt(str));

            	int uni=Integer.parseInt(str);        //将文本行字符串转换成整数，未处理NumberFormatException异常
                this.text_char.setText(""+(char)uni); //显示uni中Unicode编码对应的字符
                this.text_hex.setText("0x"+Integer.toHexString(uni));//以十六进制形式显示uni的Unicode编码【思考题6-1】
            }
        }
    }   
    
    public static void main(String[] arg)
    { 
        new QueryFrame();
    }
    
    //以下实现窗口事件监听器接口，当关闭窗口时，结束程序运行
//    public void windowClosing(WindowEvent event)           //关闭窗口时执行的事件处理方法
//    {
//        System.exit(0);                                    //结束程序运行
//    }
//    public void windowOpened(WindowEvent event)     {}     //打开窗口后执行
//    public void windowActivated(WindowEvent event)  {}     //激活窗口后执行
//    public void windowDeactivated(WindowEvent event){}     //变为不活动窗口后执行
//    public void windowIconified(WindowEvent event)  {}     //窗口最小化后执行
//    public void windowDeiconified(WindowEvent event){}     //窗口恢复后执行
//    public void windowClosed(WindowEvent event)     {}     //关闭窗口后执行    
     
    //【思考题6-1】改变字号。
    //第5版教材没写，讲课演示用。例6.3写，不同之处，例6.2 Frame是容器；例6.3 JFrame的内容窗格是容器。
    //以下方法实现组件事件监听器接口。通用方法，但是不可独立成为一个类。
    //当改变组件大小时，设置框架内容窗格中所有组件的字体字号，使之随着框架尺寸而改变
    public void componentResized(ComponentEvent event)
    {
//        System.out.println(event.getComponent().getClass().getName()); //当前Frame
        int size=(this.getWidth()+this.getHeight())/40;    //估算字号
        Font font=new Font("宋体",1,size);                 //字体
        int n= this.getComponentCount();                   //获得框架内容窗格中的组件个数
        for(int i=0; i<n; i++)                             //设置框架内容窗格中所有组件的字体
            this.getComponent(i).setFont(font);
    }
    public void componentMoved(ComponentEvent event) {}    //移动组件
    public void componentShown(ComponentEvent event) {}    //显示组件
    public void componentHidden(ComponentEvent event){}    //隐藏组件
}
//@author：Yeheya，2016-9-17 中秋假期，2018年2月5日，2018年7月19日