//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月18日
//§6.1.1 AWT组件
//【例6.1思考题】整数算术运算器。
//arithmetic operation 算术运算

import java.awt.*;                               //导入AWT包
import java.awt.event.*;
import javax.swing.*;

//算术运算器框架类，继承框架类；关闭窗口，响应动作事件
public class ArithOperJFrame extends JFrame implements ActionListener
{
    private JTextField[] texts;                  //文本行数组，操作数和运算结果
    private JComboBox<String> combox;            //组合框，算术运算符
    private JButton button;
    private String[] operators={"+","-","*","/","%"}; //算术运算符
    
    public ArithOperJFrame()
    {
        super("算术运算器");        
        this.setSize(460,100);
        this.setLocation(200,200);
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.getContentPane().setLayout(new FlowLayout()); //流布局

        this.texts = new JTextField[3];                    //文本行数组，2个操作数和1个运算结果
        for(int i=0; i<this.texts.length; i++)
        {
            this.texts[i] = new JTextField("",6);          //文本行
            this.getContentPane().add(this.texts[i]);
        }
        this.texts[this.texts.length-1].setEditable(false);//只能显示，不允许编辑
        
        this.combox = new JComboBox(operators);            //组合框，默认不可编辑
        this.getContentPane().add(this.combox,1);

        this.button = new JButton("=");
        this.getContentPane().add(button,3);
        this.button.addActionListener(this);               //按钮添加动作事件监听器
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)    //动作事件处理方法，单击按钮
    {
        try
        {
            int x = Integer.parseInt(this.texts[0].getText());
            int y = Integer.parseInt(this.texts[1].getText());
            switch(this.combox.getSelectedIndex())    //获得组合框选中序号
            {
                case 0: x+=y; break;                   //+
                case 1: x-=y; break;                   //-
                case 2: x*=y; break;                   //*
                case 3: if(y!=0)                       //“/”，整数除
                        {
                	        x/=y; break;
                        }
                        else 
                        {
                            JOptionPane.showMessageDialog(this, "除数为0，不能计算。");
                            this.texts[this.texts.length-1].setText("");
                            return;
                        }
                case 4: x%=y; break;                   //%，取余数
            }
            this.texts[this.texts.length-1].setText(x+"");
        }
        catch(NumberFormatException nfex)
        {
            try
            {
                double x = Double.parseDouble(this.texts[0].getText());
                double y = Double.parseDouble(this.texts[1].getText());
                switch(this.combox.getSelectedIndex())    //获得组合框选中序号
                {
                    case 0: x+=y; break;                   //+
                    case 1: x-=y; break;                   //-
                    case 2: x*=y; break;                   //*
                    case 3: if(y!=0)                       //“/”，整数除
                            {
                    	        x/=y;
                    	        break;
                            }
                            else 
                            {
                                JOptionPane.showMessageDialog(this, "除数为0，不能计算。");
                                this.texts[this.texts.length-1].setText("");
                                return;
                            }
                    case 4: JOptionPane.showMessageDialog(this, "浮点数不能进行%运算。");
                            this.texts[this.texts.length-1].setText("");
                            return;
                }
                this.texts[this.texts.length-1].setText(x+"");
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "字符串不能转换成浮点数。");
            }
        }
        finally{}
    }
    
    public static void main(String[] arg)
    {
        new ArithOperJFrame();
    }
}
//@author：Yeheya，2018年7月19日，2018年10月29日