//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月2日
//§12.3.3   多文档界面
//【例12.5】  多文档界面的文本文件编辑器。  //继承例8.7
//设置编译路径：例6.5、例8.5、例8.7。

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.beans.PropertyVetoException;

//多文档界面的文本文件编辑器类，继承例8.7文本编辑器
public class MDITextEditorJFrame extends TextEditorJFrame
{
    private JDesktopPane desktop;                          //桌面窗格
    private ButtonGroup buttongroup;                       //按钮组
    
    public MDITextEditorJFrame(File file)                  //构造方法，file指定文件对象
    {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().remove(1);                   //删除框架内容窗格中的text文本区，例6.5声明
        this.desktop = new JDesktopPane();                 //桌面窗格
        this.getContentPane().add(desktop);                //将桌面窗格添加到框架的内容窗格
        this.buttongroup = new ButtonGroup();              //按钮组
        new TextJIFrame(file);                             //创建显示文本文件的内部框架
    }
    public MDITextEditorJFrame()                           //构造方法
    {
        this(new File(""));
    }    

    //显示文本文件的内部框架类，内部类，实现内部框架窗口事件监听器接口
    private class TextJIFrame extends JInternalFrame implements InternalFrameListener
    {
        File file;                                         //文本文件
        JTextArea text;                                    //文本区 
        JRadioButtonMenuItem rbmenuitem;                   //单选菜单项
        
        TextJIFrame(File file)         //构造方法，创建内部框架，读取file文本文件内容显示在text文本区
        {
            super("", true, true, true, true);
            this.setSize(640, 480);
            this.addInternalFrameListener(this);           //注册内部框架窗口事件监听器
            desktop.add(this);                             //桌面窗格添加内部框架
            JInternalFrame inner = desktop.getSelectedFrame();  //获得桌面窗格当前选中内部框架
            if(inner!=null)                                //设置各内部框架级联显示
                this.setLocation(inner.getX()+50, inner.getY()+50);

            this.text = new JTextArea();
            this.text.setForeground(colors[0]);            //设置文本区颜色
//          this.text.setFont(new Font("华文行楷", 1, 50)); //设置文本区字体
            this.getContentPane().add(new JScrollPane(this.text));    
//            actionPerformed(new ActionEvent(MDITextEditorJFrame.this.combox_size,0,"comboBoxChanged"));//调用事件处理方法，初始化
            this.text.add(MDITextEditorJFrame.this.popupmenu);  //文本区添加外部类的快捷菜单
            this.text.addMouseListener(MDITextEditorJFrame.this);
                                       //文本区注册鼠标事件监听器，由外部类当前实例提供事件处理方法
            
            this.rbmenuitem = new JRadioButtonMenuItem(this.getTitle(),true);  //单选菜单项
            this.rbmenuitem.addActionListener(MDITextEditorJFrame.this);//单选菜单项注册单击事件监听器，由外部类当前实例提供事件处理方法
            MDITextEditorJFrame.this.buttongroup.add(this.rbmenuitem);  //外部类的按钮组添加当前单选菜单项
            MDITextEditorJFrame.this.menus[5].add(this.rbmenuitem);     //外部类的窗口菜单添加当前单选菜单项

            this.file = file;
            if(file==null)                                 //文件对象不空时
                this.file = new File("");
            this.rbmenuitem.setText(this.file.getName());  //以文件名设置窗口菜单下的单选菜单项标题
            this.setTitle(this.file.getName());
            JTextAreaText.readFrom(this.file, this.text);  //读取file文件内容显示在text文本区中，见例8.5
            this.setVisible(true);
        }
    
        //以下方法实现InternalFrameListener接口
        public void internalFrameOpened(InternalFrameEvent event)//打开时
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameActivated(InternalFrameEvent event)//激活内部框架时
        {
            System.out.println(event.paramString());
            MDITextEditorJFrame.this.text = this.text;     //改变外部类的text引用，使工具栏作用于当前text
            this.rbmenuitem.setSelected(true);             //设置对应单选菜单项为选中状态
        }
        public void internalFrameDeactivated(InternalFrameEvent event)//失去焦点时
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameClosing(InternalFrameEvent event)//关闭内部框架时
        {
            MDITextEditorJFrame.this.buttongroup.remove(this.rbmenuitem);  //外部类的按钮组删除当前单选菜单项
            MDITextEditorJFrame.this.menus[5].remove(this.rbmenuitem);     //外部类的窗口菜单删除当前单选菜单项
        }
        public void internalFrameClosed(InternalFrameEvent event)//关闭后
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameIconified(InternalFrameEvent event)//最小化
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameDeiconified(InternalFrameEvent event)//最小化再恢复
        {
            System.out.println(event.paramString());            
        }
        
    }//TextJIFrame内部类结束

    public void actionPerformed(ActionEvent event)             //单击事件处理方法，覆盖父类同名方法
    {
        super.actionPerformed(event);                          //调用父类的单击事件处理方法
                                      //其中调用的actionMenuItem(e)方法执行本类的方法实现，运行时多态
        if(event.getSource() instanceof JRadioButtonMenuItem)  //单击单选菜单项
            this.setSelected(new File(event.getActionCommand()));//设置当前单选菜单项对应文件的内部框架为选中状态
    }
    protected void actionMenuItem(ActionEvent event)       //文件菜单项的单击事件处理方法，覆盖父类同名方法
    {
        if(event.getActionCommand().equals("新建"))
        {
            new TextJIFrame(null);                         //创建内部框架
            return; 
        }
        if(event.getActionCommand().equals("打开") && fchooser.showOpenDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {                                                  //显示打开文件对话框，且单击"打开"按钮            
            File file = fchooser.getSelectedFile();        //获得文件对话框的当前选中文件
            if(!this.setSelected(file))                    //查找file文件是否已打开
                new TextJIFrame(file);                     //创建内部框架            
            return;
        }
       
        if(desktop==null)
           return;
        TextJIFrame inner=(TextJIFrame)desktop.getSelectedFrame();//返回桌面窗格中当前活动的内部框架
        if(inner==null)
            return;
        if(event.getActionCommand().equals("保存") && !inner.file.getName().equals(""))
            JTextAreaText.writeTo(inner.file, inner.text); //保存文件内容，见例8.5
        else if((event.getActionCommand().equals("保存") && inner.file.getName().equals("") ||
                event.getActionCommand().equals("另存为")) && fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {                      //保存空文件或执行"另存为"菜单项时，显示保存文件对话框，且单击"保存"按钮
            inner.file = fchooser.getSelectedFile();
            if(!inner.file.getName().endsWith(".txt"))
                inner.file= new File(inner.file.getAbsolutePath()+".txt");  //添加文件扩展名
            inner.setTitle(inner.file.getName());          //更改内部框架标题
            JTextAreaText.writeTo(inner.file, inner.text); //保存文件内容，见例8.5
            inner.rbmenuitem.setText(inner.file.getName());//单选按钮菜单项改名
        }
    }
   
    public boolean setSelected(File file)                  //查找file文件是否已打开，已打开则设置选中状态
    {
        JInternalFrame inners[] = desktop.getAllFrames();  //返回桌面窗格中的所有内部框架
        int i=0;
        for(i=0; i<inners.length; i++)                     //查找file文件是否已打开
        {
            File f=((TextJIFrame)inners[i]).file;
            if(file.getName().equals(f.getName()))
                break;
        }
        if(i<inners.length)
            try
            {
                inners[i].setSelected(true);               //选中内部框架，不重复打开
                return true;
            }
            catch(PropertyVetoException pve) {}            //
//            {   pve.printStackTrace();
//            }
        return false;
    }
    
    public static void main(String arg[])
    {
        new MDITextEditorJFrame(new File("唐诗\\忆江南.txt"));
    }
}
//@author：Yeheya，2018年2月2日