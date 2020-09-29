//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年7月27日
//§8.4.1 文件类及其过滤器
//【例8.6】  音乐播放器的文件列表
//【思考题8-5】 ① 保存播放列表文件，采用对象文件保存列表框中的File对象。
//② 保存文件过滤器，采用文本文件保存文件过滤器组合框中的文件过滤字符串。
//① 使用通用方法，ListModelObjectFile和ComboBoxText，类见【例8.5】。
//② 没有用选择文件对话框，还没有讲到。
//没有做组合框添加数据项，课程设计题。

import java.awt.event.*;
import javax.swing.*;

//显示音乐播放器文件列表的框架类，采用对象文件和文本文件保存播放列表
public class FileListJFrame extends ListJFrame implements WindowListener
{
    protected JTextField text;                   //文本行
    private String filterfilename;               //过滤条件组合框的文件名
    private int itemcount=0;                     //组合框的项数

    //构造方法，filename指定文件名初值，filterfilename指定过滤条件组合框的文件名
    public FileListJFrame(String filename, String filterfilename)
    {
        super();
        //以下在工具栏上添加标签、文本行、按钮等组件
        this.toolbar.add(new JLabel("文件名"));
        this.toolbar.add(this.text=new JTextField(filename,10));
        String[] str={"打开","保存"};
        for(int i=0; i<str.length; i++)          //添加按钮
        {
            JButton button = new JButton(str[i]);
            button.addActionListener(this);
            this.toolbar.add(button);
        }
        
        //以下更改过滤条件组合框属性
        this.filterfilename = filterfilename;
        ListModelText.readFrom(this.filterfilename, this.combox);    //读取文本文件中的字符串，添加到组合框数据项
        this.itemcount = this.combox.getItemCount();  //获得组合框的项数
        this.addWindowListener(this);            //框架监听窗口事件
    }
    
    public void actionPerformed(ActionEvent event) //动作事件处理方法，覆盖
    {
        super.actionPerformed(event);
        
        if(event.getSource() instanceof JButton)
        {
            String filename = this.text.getText();
            switch(event.getActionCommand()) 
            {
                case "打开":                       //单击"打开"按钮
                    if(filename.endsWith(".obj"))
                        ListModelObjectFile.readFrom(filename, this.listmodel);
                        //读取filename指定对象文件，添加数据项到listmodel列表框模型中，类见【例8.5】
                    break;

                case "保存":
                    if(filename.endsWith(".obj"))
                        ListModelObjectFile.writeTo(filename, this.listmodel);
                        //将listmodel列表框模型中数据项写入filename指定对象文件，类见【例8.5】
            }
            this.setTitle("音乐播放器的文件列表  "+filename);   //标题栏添加文件名
        }
    }

    public void windowClosing(WindowEvent e)               //窗口关闭事件处理方法
    {
        if(this.itemcount<this.combox.getItemCount())      //组合框项数增加时
            ListModelText.writeTo(this.filterfilename, this.combox);
                                       //将组合框数据项写入文本文件，类见【例8.5】
    }
    public void windowOpened(WindowEvent e)      {}
    public void windowActivated(WindowEvent e)   {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e)      {}
    public void windowIconified(WindowEvent e)   {}
    public void windowDeiconified(WindowEvent e) {}
    
    public static void main(String[] args) 
    {
        new FileListJFrame("我的音乐.obj", "filter.txt");
//        new FileListJFrame("我的音乐.txt");
    }
}
//@author：Yeheya，2018年7月27日