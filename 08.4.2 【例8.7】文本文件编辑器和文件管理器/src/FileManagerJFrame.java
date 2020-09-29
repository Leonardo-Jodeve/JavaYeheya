//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月29日
//§8.3   字符流
//§8.4   文件操作
//§8.4.1 文件类及其过滤器
//§8.4.2 文件选择对话框组件
//【例8.6】  文本文件编辑器和文件管理器。
//（3）文件管理器
//设置编译路径：例6.5 文本编辑器。

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//文件管理器框架类，继承框架类，响应动作事件
public class FileManagerJFrame extends JFrame implements ActionListener
{
    private File dir;                                      //文件对象，表示指定目录
    private File[] files;                                  //保存指定目录中所有文件
    private JTextField text;                               //地址栏，显示目录路径；状态栏
    private JTable jtable;                                 //表格，显示指定目录中所有文件和子目录的属性
    private DefaultTableModel tablemodel;                  //表格模型

    public FileManagerJFrame()
    {
        super("文件管理器");
        this.setBounds(300,200,600,480);                   //设置窗口位置及大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addMenu();                                    //添加窗口菜单

        this.dir = new File(".");                          //创建表示当前目录的文件对象
        String path = this.dir.getAbsolutePath();          //获得绝对路径
        path = path.substring(0,path.length()-1);
        this.dir = new File(path);

//        this.dir = new File("../");                      //当前目录的上一级目录
//      this.dir = new File("");                           //当前目录所在盘的根目录
//      this.dir = new File("D:\\");                       //表示D盘根目录D:\
        this.text = new JTextField(this.dir.getAbsolutePath());//显示目录路径
        this.getContentPane().add(this.text,"North");
        this.text.addActionListener(this);
        
        String[] titles={"名称","大小","类型","修改日期"};
        this.tablemodel = new DefaultTableModel(titles,0); //默认表格模型，titles指定列标题，0行
        this.jtable = new JTable(this.tablemodel);         //创建表格，指定表格模型
        this.getContentPane().add(new JScrollPane(this.jtable)); //滚动窗格（包含表格）添加到框架内容窗格中部
        listFilesToTableModel();                           //表格模型显示当前目录的文件列表
        this.setVisible(true);
    }
       
    private void addMenu()                                 //添加窗口菜单
    {
        JMenuBar menubar = new JMenuBar();                 //菜单栏
        this.setJMenuBar(menubar);                         //框架上添加菜单栏
        String[] menustr={"文件","编辑","查看","帮助"};
        String[][] menuitemstr={{"打开","重命名","删除","刷新","搜索","|","退出"},
                                {"剪切","复制","粘贴","|","复制到备份文件夹"},
                                {"返回上级"},{}};
        JMenu[] menus = new JMenu[menustr.length];         //菜单数组
        for(int i=0; i<menuitemstr.length; i++)            //添加菜单和菜单项
        {
            menus[i] = new JMenu(menustr[i]);              //菜单
            menubar.add(menus[i]);                         //菜单栏中加入菜单
            for(int j=0; j<menuitemstr[i].length; j++)
                if(menuitemstr[i][j].equals("|"))
                    menus[i].addSeparator();               //加分隔线
                else 
                {
                    JMenuItem menuitem = new JMenuItem(menuitemstr[i][j]); //创建菜单项
                    menus[i].add(menuitem);                //菜单项加入到菜单
                    menuitem.addActionListener(this);      //菜单项注册动作事件监听器
                }
        }
        JMenu menu_new = new JMenu("新建");                  //“新建”菜单是“文件”菜单的子菜单
        menus[0].insert(menu_new,0);                         //菜单加入到菜单中成为二级菜单
        String[] menuitemstr_new={"文件夹","文本文档"};       //以下创建菜单项，添加到“新建”菜单
        for(int i=0; i<menuitemstr_new.length; i++)
        {
            JMenuItem menuitem = new JMenuItem(menuitemstr_new[i]);//创建菜单项
            menu_new.add(menuitem);                        //菜单项加入到菜单
            menuitem.addActionListener(this);              //为菜单项注册动作事件监听器
        }
    }
    
    private void listFilesToTableModel()                   //表格模型显示当前目录的文件列表
    {
        this.files = this.dir.listFiles();                 //返回指定目录的文件对象列表，没有过滤
        for(int i=this.tablemodel.getRowCount()-1; i>=0; i--)  //删除表格模型中所有行
            this.tablemodel.removeRow(i);
        this.tablemodel.setRowCount(this.files.length);    //设置表格模型的行数 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(int i=0; i<this.files.length; i++)             //表格模型添加行
        {
       	    this.tablemodel.setValueAt(this.files[i].getName(),i,0); //显示文件名
       	    if(this.files[i].isFile())                     //当前File对象是文件
       	        this.tablemodel.setValueAt(this.files[i].length()+"B", i, 1); //显示文件大小
       	    if(this.files[i].isDirectory())                //当前File对象是目录
                this.tablemodel.setValueAt("文件夹", i, 2); //显示文件类型
            String timestr=sdf.format(new Date(files[i].lastModified()));//文件修改时间
       	    this.tablemodel.setValueAt(timestr, i, 3);
        }
    }
    
    public void actionPerformed(ActionEvent event)         //动作事件处理方法
    {
        if(event.getSource()==this.text)                   //单击文本行
            this.dir = new File(this.text.getText());      //进入指定文件夹

        //以下写不成switch语句。
        else if(event.getActionCommand().equals("返回上级"))//单击“查看|返回上级”菜单项时
        {
//            System.out.println(this.dir.toString());
            this.dir = this.dir.getParentFile();
            if(this.dir!=null)
                this.text.setText(this.dir.getAbsolutePath());  //更改文本行，减少刚返回的目录名
            else JOptionPane.showMessageDialog(this, "没有上级目录");
        }

        else if(event.getActionCommand().equals("文件夹"))    //单击“文件|新建|文件夹”菜单项
            new File(this.dir,"新建文件夹").mkdir();          //为文件对象创建一个目录

        else if(event.getActionCommand().equals("文本文档"))  //单击“文件|新建|文本文档”菜单项
        {
            try
            {
                new File(this.dir,"新建文本文档.txt").createNewFile(); //为文件对象创建一个空文件
            }
            catch (IOException ex){}
        }
        int i = this.jtable.getSelectedRow();               //返回表格当前选中的行号（≥0）；没有选中时返回-1
        if(event.getActionCommand().equals("重命名") && i!=-1)   //单击“文件|重命名”菜单项
        {
            String name=(String)this.tablemodel.getValueAt(i,0);
            //下句弹出输入对话框见图6-14(c)，单击“确定”按钮返回输入字符串，单击“取消”按钮返回null
            String filename = JOptionPane.showInputDialog(this, "文件名", name);
            if(filename!=null && filename!="")
            {
                if(this.files[i].isFile() && !(filename.endsWith(".txt") || filename.endsWith(".TXT")))
                    filename +=".txt";
                this.files[i].renameTo(new File(this.dir,filename));//文件对象重命名
            }
        }

        else if(event.getActionCommand().equals("打开") && i!=-1) //单击菜单项时
        {
            if(this.files[i].isFile())
            {
                String fname = this.files[i].getName().toLowerCase();   //获得选中文件名，字母小写
                if(fname.endsWith(".txt") || fname.endsWith(".java"))  //匹配文件扩展名，忽略字母大小写
                    new TextEditorJFrame(this.files[i]);   //打开文本文件编辑器
                else
                    JOptionPane.showMessageDialog(this, "不能打开这种类型文件");
            }
            else                                           //显示选中目录的文件列表
            {
                this.dir = this.files[i];
                this.text.setText(this.dir.getAbsolutePath());
            }
        }
        else if(event.getActionCommand().equals("删除") && i!=-1)
        {
            if(this.files[i].isFile())                     //当前是文件，不复制目录
            {
                if(JOptionPane.showConfirmDialog(this, "删除\""+this.files[i].getName()+"\"文件？")==0)
                    this.files[i].delete();                //删除文件
            }
            else if(JOptionPane.showConfirmDialog(this, "删除\""+this.files[i].getName()+"\"文件夹中所有子目录和文件？")==0)
       	            this.deleteDir(files[i]);              //删除指定目录中的所有子目录及文件
        }
        if(event.getActionCommand().equals("复制到备份文件夹") && i!=-1) //单击“编辑 | 复制”菜单项时
        {
            if(this.files[i].isFile())                     //仅复制文件，不复制目录
            {
                File dir_copyto = new File(this.dir,"\\备份");  //创建指定目录
                if(!dir_copyto.exists())                   //目录不存在时
                    dir_copyto.mkdir();                    //创建目录
                File file2 = new File(dir_copyto, this.files[i].getName());  //创建复制的文件
                if(!file2.exists() || this.files[i].lastModified()>file2.lastModified())
                {                                          //文件不存在或文件存在且待复制文件日期较新时复制
//                    FileStream.copy(this.files[i].getAbsolutePath(), f2.getAbsolutePath());   //使用字节流复制文件
                	FileStream.copy(this.files[i], file2); //复制文件，使用字节流。FileStream类文件在例8.5项目
                    file2.setLastModified(this.files[i].lastModified());   //将新文件的最后修改时间设置为原文件的最后修改时间
                }
            }
        }
        listFilesToTableModel();                           //表格模型显示当前目录的文件列表

        if(event.getActionCommand().equals("剪切"))        //单击“编辑 | 剪切”菜单项时
        {
        }
        if(event.getActionCommand().equals("复制"))        //单击“编辑 | 复制”菜单项时
        {
        }
        if(event.getActionCommand().equals("粘贴"))        //单击“编辑 | 粘贴”菜单项时
        {
        }
    }
    
    //删除dir目录中的所有子目录及文件。因为文件目录结构是树，对树的遍历是递归算法
    public void deleteDir(File dir)
    {
        File[] files = dir.listFiles();          //返回指定目录中所有文件列表
        for(int i=0; i<files.length; i++)
        {
            if(files[i].isDirectory())
                deleteDir(files[i]);             //递归调用，参数是子目录
            files[i].delete();                   //删除文件或空目录
        }
        dir.delete();
    }
    
    public static void main(String[] arg)
    {
        new FileManagerJFrame();
    }
}
/*
思考题：
    增加状态栏，计算文件数、目录数、当前目录中文件总字节数，
    表格中改文件名
*/
//@author：Yeheya，2017年8月29日，2018年11月4日