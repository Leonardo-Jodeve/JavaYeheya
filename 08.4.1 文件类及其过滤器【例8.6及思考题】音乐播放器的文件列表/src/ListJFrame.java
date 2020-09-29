//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月28日
//§8.4.1 文件类及其过滤器
//【例8.6】  音乐播放器的文件列表
//（2）音乐播放器的文件列表

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

//显示音乐播放器文件列表的框架类，响应动作事件。列表框选择事件用于测试
public class ListJFrame extends JFrame implements ActionListener,ListSelectionListener
{
    private JTextField text_path, text_status;   //路径文本行，状态文本行
    protected JComboBox<String> combox;          //过滤条件组合框
    private JList<File> jlist;                   //显示文件列表的列表框
    protected DefaultListModel<File> listmodel;  //列表框模型
    private int count=0, size=0;                 //文件数，所有文件总字节数
    protected JToolBar toolbar;                  //工具栏
    
    public ListJFrame()
    { 
        super("音乐播放器的文件列表");
        this.setBounds(300,240,650,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
        
        //以下在框架内容窗格北边添加工具栏，其上添加路径文本行、过滤条件组合框；在框架南边添加状态文本行
        this.toolbar = new JToolBar();                     //工具栏，默认水平方向
        this.getContentPane().add(this.toolbar,"North");   //框架北边添加工具栏
        this.toolbar.add(text_path=new JTextField("我的音乐",20));  //路径文本行
        this.text_path.addActionListener(this);
        this.getContentPane().add(text_status=new JTextField(),"South"); //框架南边添加状态文本行
        
        String[] filternames={"","*.mp3","*.wma","*.*"};   //过滤条件数据项
        this.combox = new JComboBox<String>(filternames);  //过滤条件组合框
        this.combox.setEditable(true);                     //组合框可编辑
        this.combox.addActionListener(this);               //组合框监听动作事件
        this.toolbar.add(this.combox);       
        
        //以下在框架内容窗格中部添加列表框
        this.listmodel = new DefaultListModel<File>();     //列表框模型
        this.jlist = new JList<File>(this.listmodel);      //列表框，指定列表框模型管理数据项
        this.jlist.addListSelectionListener(this);         //列表框监听选择事件，用于测试
        this.getContentPane().add(new JScrollPane(this.jlist)); //框架添加包含列表框的滚动窗格
        actionPerformed(new ActionEvent(this.combox,0,"*.mp3"));//调用动作事件处理方法
        this.setVisible(true);
    }
  
    public void actionPerformed(ActionEvent event)         //动作事件处理方法
    {
        if(event.getSource()==this.text_path || event.getSource()==this.combox)//此句教材写，对子类有用 
        {
            String filter=(String)this.combox.getSelectedItem();//获得组合框的过滤条件
            if(filter!=null)    //【思考题8-5】combox.removeAllItems()触发组合框动作事件，导致选中项为null
            {
                this.listmodel.removeAllElements();        //列表框模型删除所有数据项
                count=0; size=0;                           //count记录文件数，size记录所有文件总字节数
                addList(new File(this.text_path.getText()), new PrefixExtFileFilter(filter));
                this.text_status.setText("共有 "+count+" 个文件，总字节数为 "+size);
            }
        }
    }
    
    //将dir目录文件列表（由filter指定过滤条件）中的文件对象，添加到listmodel列表框模型中，
    //并计算文件数和字节总数，递归方法
    private void addList(File dir, PrefixExtFileFilter filter)
    {
        File[] files = dir.listFiles(filter);              //返回dir目录由filter指定过滤条件的文件列表
        count+=files.length;                               //文件数
        for(int i=0; i<files.length; i++)
        {
            this.listmodel.addElement(files[i]);           //列表框模型添加文件对象
            size+=files[i].length();                       //文件长度
        }
        
        files = dir.listFiles();                           //返回dir目录的文件列表，没有过滤，包含所有文件和子目录
        for(int i=0; i<files.length; i++)                  //继续添加各子目录文件列表中的文件对象
            if (files[i].isDirectory())                    //判断指定file对象是否是目录
                addList(files[i], filter);                 //添加files[i]子目录文件列表中的文件对象，递归调用
    }
    
    public void valueChanged(ListSelectionEvent event)         //选中列表框数据项时触发
    {
//        if (e.getValueIsAdjusting())                     ///一次选择，执行两次该事件？？为什么
//          this.personPanel.set((Person)this.jlist.getSelectedValue());
    }
    
    public static void main(String[] arg)
    {
        new ListJFrame();
    }
}
/*
File dir = new File(".","");             //当前目录
File dir = new File("./","");
File dir = new File(".\\","");

File dir = new File("../","");           //当前目录的上一级目录

File dir = new File("","");              //当前目录所在盘的根目录

File dir = new File("C:","");            //C盘根目录C:\
File dir = new File("C:/","");
File dir = new File("C:\\","");
*/
//@author：Yeheya，2017年8月28日，七夕