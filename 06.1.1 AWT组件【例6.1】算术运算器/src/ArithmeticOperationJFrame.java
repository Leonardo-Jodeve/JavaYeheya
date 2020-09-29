//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月18日
//§6.1.1 AWT组件
//【例6.1思考题】【实验6】浮点数算术运算器。（横式）
//arithmetic operation 算术运算

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//算术运算器框架类，继承框架类；响应动作事件。
//特点：Swing组件，组件数组，操作数个数可变；数值格式异常处理，处理除数为0错误
public class ArithmeticOperationJFrame extends JFrame implements ActionListener
{
    private JTextField[] texts;                  //文本行数组，操作数和运算结果
    private JComboBox<String>[] comboxs;         //组合框数组，算术运算符
    private JButton button;
    
    public ArithmeticOperationJFrame(int number)
    {
        super("浮点数算术运算器");        
        if(number<2 || number>6)
            throw new java.lang.IllegalArgumentException(number+"超出2～6范围。"); //无效参数异常
        this.setSize(160*(number+1),100);
        Dimension dim=getToolkit().getScreenSize();        //获得屏幕分辨率
        this.setLocation((dim.width-this.getWidth())/2, (dim.height-this.getHeight())/2);//窗口居中
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.getContentPane().setLayout(new FlowLayout()); //流布局

        this.texts = new JTextField[number+1];
        String[] operators={"+", "-", "*", "/"};           //浮点数参与的算术运算符
        this.comboxs = new JComboBox[number-1];            //组合框数组
        for(int i=0; i<number; i++)
        {
            this.texts[i] = new JTextField("0.0",8);
            this.getContentPane().add(this.texts[i]);
            if(i<number-1)
            {
                this.comboxs[i] = new JComboBox(operators);//组合框，默认不可编辑
                this.getContentPane().add(this.comboxs[i]);
            }
        }
        this.button = new JButton("=");
        this.getContentPane().add(button);
        this.button.addActionListener(this);               //按钮注册单击事件监听器
        this.texts[number] = new JTextField(10);
        this.getContentPane().add(this.texts[number]);
        this.texts[number].setEditable(false);             //只能显示，不允许编辑
        this.setVisible(true);
    }
    
    public ArithmeticOperationJFrame()
    {
        this(3);
    }
    
    public void actionPerformed(ActionEvent e)             //动作事件处理方法，单击按钮
    {
        try
        {
            double x = Double.parseDouble(texts[0].getText());
            for(int i=1; i<this.texts.length-1; i++)
            {
                double y = Double.parseDouble(this.texts[i].getText());
                switch(this.comboxs[i-1].getSelectedIndex())//获得组合框选中序号
                {
                    case 0: x+=y; break;                   //'+'
                    case 1: x-=y; break;                   //'-'
                    case 2: x*=y; break;                   //'*'
                    case 3: if(y>0.000001)                 //'/'
                                x/=y;
                            else 
                            {
                                JOptionPane.showMessageDialog(this, "除数为0，不能计算，请重新输入!");
                                return;
                            }
                }
            }
            this.texts[this.texts.length-1].setText(x+"");
        }
        catch(NumberFormatException nfex)
        {
            JOptionPane.showMessageDialog(this,"字符串不能转换成整数，请重新输入!");
        }
        finally{}
    }
    
    public static void main(String arg[])
    {
        new ArithmeticOperationJFrame(4);
    }
}
//@author：Yeheya，2018年7月19日