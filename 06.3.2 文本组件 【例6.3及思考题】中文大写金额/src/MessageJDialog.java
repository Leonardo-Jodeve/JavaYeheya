//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月9日
//§6.3.2 文本显示和编辑组件及事件
//【例6.3】  中文大写金额。【思考题6-3】

import javax.swing.*;

public class MessageJDialog extends JDialog           //显示提示信息的定控对话框类，通用功能
{
    private JFrame jframe;                             //对话框所依附的框架窗口
    private JLabel jlabel;                             //对话框中的显示信息
    
    public MessageJDialog(JFrame jframe)               //内部类的构造方法
    {
        super(jframe,"提示",true);                     //jframe引用对话框所依附的框架窗口；true表示模式窗口
        this.jframe = jframe;
        this.setSize(300,80);
        jlabel = new JLabel("", JLabel.CENTER);        //标签的字符串为空，居中对齐
        this.getContentPane().add(jlabel); 
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);  //单击对话框的关闭按钮时，隐藏对话框而不结束程序运行
    }
    public void show(String message)                   //对话框显示信息
    {
        jlabel.setText(message);                      //标签显示指定信息
        this.setLocation(jframe.getX()+100, jframe.getY()+100);//对话框位置在框架的右下方
        this.setVisible(true);                         //显示对话框
    }
}
//@author：Yeheya，2016-9-9
