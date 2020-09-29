//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月1日
//§12.3.2 列表框
//【例12.3】 预览字体，使用列表框单元渲染器。

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

//预览字体的框架类，继承框架类，响应列表框选择事件
public class FontsListJFrame extends JFrame implements ListSelectionListener
{
    private JList<String> jlist;                 //列表框，存储系统字体名
    private JTextArea text;                      //文本区，预览字体
    
    public FontsListJFrame()
    {
        super("预览字体");
        this.setBounds(400,200,500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsName=ge.getAvailableFontFamilyNames();//获得所有系统字体名字符串
        this.getContentPane().add(new JScrollPane(this.jlist=new JList<String>(fontsName)));
        this.jlist.addListSelectionListener(this);         //列表框监听选择事件
        this.jlist.setCellRenderer(new FontNameListRenderer());  //列表框设置单元渲染器
        this.getContentPane().add(this.text=new JTextArea(" Welcome  欢迎"),"South");
        this.setVisible(true);
    }

    public void valueChanged(ListSelectionEvent event)      //在列表框中选择数据项时触发
    {
        //下句以列表框选中数据项作为字体名设置字体(字体名,粗体,字号)
        String selected = (String)jlist.getSelectedValue();
//        System.out.println(selected);                      //一次选择，执行两次该事件？？为什么
        this.text.setFont(new Font(selected,Font.BOLD,56));
    }
    
    public static void main(String[] args) 
    {
        new FontsListJFrame();
    }
}
//@author：Yeheya，2018年2月1日，2018年8月8日