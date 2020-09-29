//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月29日
//§6.4.1 图形设计
//【例6.7】四叶玫瑰线的图形设计。
//JDK 7.1 改变组件大小 时，默认执行paint()方法，因此，可不响应组件事件。
//修改第4版程序，RoseCanvas作为内部类，增加渐变色、动画等功能，但动画不能控制时间和停止
//本程序实现调用sleep的动画，书第6章给出动画功能的界面，动画实现作为第7章实验题的要求。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//绘制四叶玫瑰线框架类，继承框架类，响应动作事件、数值改变事件；测试，响应窗口事件
public class RoseJFrame extends JFrame implements ActionListener, ChangeListener, WindowListener
{
    private JButton button;                      //选择颜色按钮
    protected JCheckBox[] checkbox;              //复选框数组
    private Color color;                         //颜色
    private JSpinner spin;                       //渐变色变化值微调文本行
    protected Canvas canvas;                     //画布
    
    public RoseJFrame()
    {
        super("四叶玫瑰线");                                //框架边布局
        Dimension dim=this.getToolkit().getScreenSize();   //获得屏幕分辨率
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);  //设置窗口居中
        this.setLocationRelativeTo(null);                  //将窗口置于屏幕中央
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //以下创建命令面板（工具栏作用），并添加组件提供画图属性
        JPanel cmdpanel = new JPanel();                    //命令面板，默认流布局（居中），其中组件原样
                                                           //注意：这里不能用toolbar，两端对齐，spin最长难看
        this.getContentPane().add(cmdpanel,"North");
        cmdpanel.add(this.button=new JButton("选择颜色"));
        this.button.addActionListener(this);
        String[] str={"加深","渐变色","演示动画过程"};      //【思考题6-7】
        this.checkbox = new JCheckBox[str.length];         //复选框数组
        for(int i=0; i<str.length; i++)
        {
            cmdpanel.add(this.checkbox[i] = new JCheckBox(str[i]));
            this.checkbox[i].addActionListener(this);      //复选框监听动作事件
        }      
        cmdpanel.add(new JLabel("左移"),3);
        //下句微调文本行，参数指定数值序列模式，初值是4，范围是1～8，步长是1
        this.spin=new JSpinner(new SpinnerNumberModel(4,1,8,1));
        this.spin.addChangeListener(this);       //微调文本行监听数值改变事件
        cmdpanel.add(this.spin,4);
        cmdpanel.add(new JLabel("位"),5);        
        
        this.color = Color.blue;
        this.canvas = new RoseCanvas();          //四叶玫瑰线画布，调用默认构造方法，父类对象引用子类实例
        this.getContentPane().add(this.canvas,"Center");
        this.setVisible(true);
        this.addWindowListener(this);            //框架监听窗口事件
    }
    
    public void actionPerformed(ActionEvent event)  //动作事件处理方法
    {
        if(event.getSource()==this.button)       //单击“选择颜色”按钮
        {
            //打开颜色选择对话框，单击“确定”按钮返回选中颜色，单击“取消”按钮返回null
            Color color=JColorChooser.showDialog(this, "选择颜色", this.color);
            if(color!=null)
            {
                this.color = color;
                this.canvas.repaint();           //调用canvas的paint(Graphics)方法，重画
            }
        }
        else if//(event.getSource() instanceof JCheckBox)   //单击复选框，第5版教材
                //单击复选框，【思考题6-6】单击演示动画过程复选框且选中状态
                (event.getSource()==this.checkbox[0] || event.getSource()==this.checkbox[1] 
                 || event.getSource()==this.checkbox[2] && this.checkbox[2].isSelected())
                this.canvas.repaint();           //重画
    }
    
    public void stateChanged(ChangeEvent event)  //JSpinner响应值改变事件处理方法
    {
        this.checkbox[1].setSelected(true);      //设置渐变色复选框为选中状态
        this.canvas.repaint();                   //重画
    }

    //四叶玫瑰线画布组件类，继承画布组件类，覆盖paint()方法。
    //私有实例内部类，对象嵌套，因为要使用外部类的组件，获得颜色、是否渐变、变化值等选项数据
    private class RoseCanvas extends Canvas 
    {
        public void paint(Graphics g)            //组件画图方法，覆盖
        {
            if (RoseJFrame.this.checkbox[0].isSelected())  //若加深复选框为选中状态
                g.setColor(RoseJFrame.this.color.darker());//设置加深的颜色
            else
                g.setColor(RoseJFrame.this.color);//设置画线颜色，使用外部类当前实例的颜色变量值
            
            final int x0 = this.getWidth()/2;    //(x0,y0)是画布组件正中点坐标
            final int y0 = this.getHeight()/2; 
            g.drawLine(0,y0,x0*2,y0);            //画直线，X轴            
            g.drawLine(x0,0,x0,y0*2);            //画直线，Y轴
            //以下画XY轴箭头，调用fillPolygon()填充多边形方法，省略。教材没写，思考题
            final int arrow=20;                            //箭头长度
            g.drawString("X", x0*2-arrow/2, y0-arrow/2);   //写X轴字符串
            int XxPoints[]={x0*2, x0*2-arrow, x0*2-arrow}; //画X轴三角形箭头
            int XyPoints[]={y0,   y0-arrow/3, y0+arrow/3};     
            g.fillPolygon(XxPoints, XyPoints, XxPoints.length);//填充多边形

            g.drawString("Y", x0+arrow/2,arrow/2);         //写Y轴字符串
            int YxPoints[]={x0, x0-arrow/3, x0+arrow/3};   //画Y轴三角形箭头
            int YyPoints[]={0,  arrow,      arrow};     
            g.fillPolygon(YxPoints, YyPoints, YxPoints.length);

            int n = (Integer)RoseJFrame.this.spin.getValue();//每圈颜色左移位数
            for(int length=40; length<200; length+=20)    //画8圈四叶玫瑰线，length表示每圈最长度
            {
                for(int i=1; i<1024; i++)        //画一圈四叶玫瑰线的若干点，顺时针，与象限相反
//                for (int i=1024; i>0; i--)       //画一圈四叶玫瑰线的若干点，逆时针，与象限相同
                {
                    double angle=i*Math.PI/512;           //角度，Math.PI表示π
                    double radius=length*Math.sin(2*angle);    //半径，Math.sin()正弦函数
                    int x=(int)(radius*Math.cos(angle)*2);//x坐标，Math.cos()余弦函数
                    int y=(int)(radius*Math.sin(angle));  //y坐标
                    g.fillOval(x0+x,y0+y,2,2);            //填充椭圆，直径小的显示一个点
//                    g.drawOval(x0+x,y0+y,1,1);          //画椭圆，线细，也行
                    //【思考题6-6】若外部类当前实例的动画演示复选框是选中状态，线程每毫秒休眠
                    if(RoseJFrame.this.checkbox[2].isSelected())
                        try
                        {
                            Thread.sleep(1);             //能够反映绘画过程，很慢，不需要sleeptime
                        }
                        catch(InterruptedException ex)
                        {
                            return;
                        }
                    //这样写说明，不创建线程对象，也可以调用Thread.sleep(1)方法。
                    //但是这样实现动画，无法随时停止，必须画完才能停止。
                    //见【实验题7-5】 弹弹球，使用线程实现动画设计。
                }
                //若外部类当前实例的渐变色复选框是选中状态，逐渐改变画线颜色
                if(RoseJFrame.this.checkbox[1].isSelected())
                    g.setColor(new Color(g.getColor().getRGB()<<n));//每圈颜色左移n位
            }
        }
    }
    public static void main(String arg[])
    {
        new RoseJFrame();
    }
    
    //以下第5版教材没写，测试，演示动画时，关闭窗口操作没有立即执行
    public void windowClosing(WindowEvent event)         //关闭窗口时执行的事件处理方法
    {
        Thread.currentThread().interrupt();
              //当前运行线程中断。单步调试运行到此句，但效果没有差别。画完了才能关闭窗口
    }
    public void windowOpened(WindowEvent event)     {}   //打开窗口后执行
    public void windowActivated(WindowEvent event)  {}   //激活窗口后执行
    public void windowDeactivated(WindowEvent event){}   //变为不活动窗口后执行
    public void windowIconified(WindowEvent event)  {}   //窗口最小化后执行
    public void windowDeiconified(WindowEvent event){}   //窗口恢复后执行
    public void windowClosed(WindowEvent event)     {}   //关闭窗口后执行    
}
/*
        public void update(Graphics g)           //更新组件，//不能声明，否则动画死了，不知道为什么
        {
            this.paint(g);
        }
//思考题，增加功能，控制画图的大小，与窗口大小成正比；控制画图的圈数；
*/
//@author：Yeheya，2016-10-5；修改2017年1月29日，春节，年初二；2017年2月14日