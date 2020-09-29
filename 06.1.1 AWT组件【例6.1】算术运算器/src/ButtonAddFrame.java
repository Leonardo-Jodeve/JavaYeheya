//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月18日
//§6.1.1 AWT组件
//【例6.1思考题】加法运算器，事件处理。

import java.awt.*;
import java.awt.event.*;

//加法运算器框架类，继承框架类，响应动作事件和窗口关闭事件。
//没有处理数值格式异常。
public class ButtonAddFrame extends Frame implements ActionListener
{
    private TextField[] texts;                  //文本行数组，操作数和运算结果
    private Button button;
    
    public ButtonAddFrame()
    {
        super("加法运算");                       //设置框架标题
        this.setSize(400, 90);                   //设置组件尺寸
        this.setLocation(300, 240);              //设置组件的显示位置
        this.setBackground(Color.lightGray);     //设置组件的背景颜色为浅灰色
        this.setLayout(new FlowLayout());        //设置框架流布局，居中

        this.texts = new TextField[3];           //文本行数组，2个操作数和1个运算结果
        for(int i=0; i<this.texts.length; i++)
        {
            this.texts[i] = new TextField("",6); //文本行
            this.add(this.texts[i]);
        }
        this.texts[this.texts.length-1].setEditable(false);//只能显示，不允许编辑
        this.add(new Label("+"),1);              //添加标签组件 

        this.button = new Button("=");
        this.add(button,3);
        this.button.addActionListener(this);     //按钮监听动作事件
        this.setVisible(true);
        this.addWindowListener(new WinClose());  //框架注册窗口事件监听器，委托WinClose对象处理事件
    }

    public void actionPerformed(ActionEvent e)   //动作事件处理方法，单击按钮
    {
        int x = Integer.parseInt(this.texts[0].getText());
        int y = Integer.parseInt(this.texts[1].getText());
        this.texts[this.texts.length-1].setText((x+y)+"");
    }
    
    public static void main(String[] arg)
    {
        new ButtonAddFrame();
    }
}
//@author：Yeheya，2018年10月27日