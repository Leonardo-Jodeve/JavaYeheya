//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月29日
//§8.3.1  字符流类
//§8.4   文件操作
//§8.4.1 文件类及其过滤器
//§8.4.2 文件选择对话框组件
//【例8.6】  文本文件编辑器和文件管理器。
//（1）文本文件编辑器，继承例6.5文本编辑器
//MyEclipse设置编译路径：包含例6.5、例8.5项目。
//调用JTextAreaText和ComboBoxDataFile类见例8.5项目。

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//文本编辑器框架类，继承例6.5文本编辑器。【思考题8-6】实现窗口事件监听器
public class TextEditorJFrame extends EditorJFrame implements WindowListener
{
    private File file;                                     //文件对象
    protected JFileChooser fchooser;                       //文件选择对话框
    private String sizefilename;                           //字号整数文件名，【思考题8-6】
    private int itemcount=0;                               //组合框的项数，【思考题8-6】
    
    public TextEditorJFrame(File file)                     //构造方法，file指定文件对象
    {
        super();                                           //图形用户界面同例6.5文本编辑器
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   //释放窗口
        this.fchooser = new JFileChooser(new File("唐诗",""));//创建文件对话框，指定起始路径
        this.fchooser.setFileFilter(new ExtensionFileFilter("文本文件(*.txt)","txt"));//设置文件过滤器
        
        this.file = file;                                  //文件对象
        if(file==null)
            this.file = new File("");
        JTextAreaText.readFrom(this.file, this.text);      //读取文本到文本区，见【例8.5】
        this.setTitle("文本编辑器  "+this.file.getName());   //标题栏添加文件名
        
        //以下【思考题8-6】，教材没写
        this.sizefilename = "FontSize.int";
        ListModelDataFile.readFrom(this.sizefilename, this.combox_size);//读取整数文件到组合框数据项
        this.addWindowListener(this);                      //框架注册窗口事件监听器
        this.itemcount = this.combox_size.getItemCount();  //获得组合框的项数
    }
    public TextEditorJFrame(String filename)               //构造方法，filename指定文件路径名
    {
    	this(new File(filename));
    }
    public TextEditorJFrame()                              //构造方法
    {
        this(new File(""));        //？？
    }

    public void actionPerformed(ActionEvent event)         //动作事件处理方法，覆盖父类同名方法
    {
        super.actionPerformed(event);                      //调用父类的动作事件处理方法
        actionMenuItem(event);                             //文件菜单项的动作事件处理方法
    }
    
/*	//第5版
    protected void actionMenuItem(ActionEvent event)       //文件菜单项的动作事件处理方法
    {
        String mitem = event.getActionCommand();           //菜单项名
        //以下若单击“新建”菜单项，则创建一个文件，设置框架窗口标题，清空文本区
        if(mitem.equals("新建"))
        {
            this.file = new File("");                      //创建一个文件
            this.setTitle("文本编辑器  ");                   //设置框架窗口标题
            this.text.setText("");                         //清空文本区
            return;
        }
        
        //以下当单击“打开”菜单项弹出打开文件对话框且单击了“打开”按钮时，将选中文件读取到文本区
        if(mitem.equals("打开") && fchooser.showOpenDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {
        	this.file = fchooser.getSelectedFile();        //获得文件对话框的选中文件
            JTextAreaText.readFrom(this.file, this.text);  //读取文本文件到文本区，见例8.5
            this.setTitle("文本编辑器  "+this.file.getName());
            return;
        }
        
        if(mitem.equals("保存") && !this.file.getName().equals(""))//单击“保存”菜单项
            JTextAreaText.writeTo(this.file, this.text);   //保存文件内容，不显示文件对话框
        
        //以下若保存空文件或执行“另存为”菜单项，弹出保存文件对话框且单击了“保存”按钮，则保存文件内容
        else if((mitem.equals("保存") && this.file.getName().equals("") ||
                  mitem.equals("另存为")) && fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {
            this.file = fchooser.getSelectedFile();        //获得文件对话框的选中文件
            if(!file.getName().endsWith(".txt"))
                this.file= new File(this.file.getAbsolutePath()+".txt");  //添加文件扩展名
        	JTextAreaText.writeTo(this.file, this.text);   //保存文件内容
            this.setTitle("文本编辑器  "+this.file.getName());
        }
    }*/
    
    //第6版
    protected void actionMenuItem(ActionEvent event)       //文件菜单项的动作事件处理方法
    {
        switch(event.getActionCommand())                   //单击的菜单项名
        {
            case "新建":       //以下若单击“新建”菜单项，则创建一个文件，设置框架窗口标题，清空文本区
                this.file = new File("");                  //创建一个文件
                this.setTitle("文本编辑器  ");               //设置框架窗口标题
                this.text.setText("");                     //清空文本区
                break;
        
            //以下若单击“打开”菜单项，则弹出打开文件对话框且当单击了“打开”按钮时，将选中文件读取到文本区                
            case "打开":
                if(fchooser.showOpenDialog(this)==0)  //弹出打开文件对话框且当单击了“打开”按钮时//JFileChooser.APPROVE_OPTION)
                {
                    this.file = fchooser.getSelectedFile();        //获得文件对话框的选中文件
                    JTextAreaText.readFrom(this.file, this.text);  //读取文本文件到文本区，见例8.5
                    this.setTitle("文本编辑器  "+this.file.getName());
                }
                break;
                
            case "保存":       //以下若单击“保存”菜单项且file文件不空时，保存文件内容，不显示文件对话框
                if(!this.file.getName().equals(""))
                {
                    JTextAreaText.writeTo(this.file, this.text); //保存文件内容
                    break;
                }
                //注意，此处没有break;继续执行以下“另存为”操作。
                
//              else if((mitem.equals("保存") && this.file.getName().equals("") ||
//              mitem.equals("另存为")) && 
            //以下若单击“保存”菜单项且file文件为空，或单击“另存为”菜单项，
            //则弹出保存文件对话框且当单击了“保存”按钮时，保存文件内容
            case "另存为":
                if(fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
                {
                    this.file = fchooser.getSelectedFile();        //获得文件对话框的选中文件
                    if(!file.getName().endsWith(".txt"))
                        this.file= new File(this.file.getAbsolutePath()+".txt");  //添加文件扩展名
                    JTextAreaText.writeTo(this.file, this.text);   //保存文件内容
                    this.setTitle("文本编辑器  "+this.file.getName());
                }
        }
    }
    
    //以下两方法读/写file文本中字符串到text文本区。详见例8.5
//    public void readFrom(File file, JTextArea text)
//    public void writeTo(File file, JTextArea text)
    
    public static void main(String[] arg)
    {
//        new TextEditorJFrame();
//        new TextEditorJFrame(new File(""));
        new TextEditorJFrame(new File("唐诗\\凉州词.txt"));
    }
    
    //以下【思考题8-6】
    //① 文本编辑器增加功能：使用整数文件保存字号组合框的数据项，
    //当打开窗口时，读取文件中的整数添加到组合框的数据项；
    //如果修改或增加了字号，则关闭窗口时，将所有字号整数写入指定整数文件中。
    
    //以下两通用方法，详见【例8.5】项目ListModelDataFile类
//    public void readFrom(String filename, JComboBox<Integer> combox)
//    public void writeTo(String filename, JComboBox<Integer> combox)
    
    public void windowClosing(WindowEvent e)               //窗口关闭事件处理方法
    {
    //    if (this.itemcount<this.combox_size.getItemCount())//组合框项数增加时
        	ListModelDataFile.writeTo(this.sizefilename, this.combox_size);//将组合框数据项写入整数文件
    }
    public void windowOpened(WindowEvent e)      {}
    public void windowActivated(WindowEvent e)   {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e)      {}
    public void windowIconified(WindowEvent e)   {}
    public void windowDeiconified(WindowEvent e) {}
}

/*未用
    public TextEditorJFrame(String filename)               //构造方法，filename指定待打开的文件名
    {
        this(new File(filename));                          //若filename==null，抛出空对象异常
    }
    
    //读取file指定文本中的字符串，添加到text文本区中，使用字符流
    public void readFrom(File file, JTextArea text)
    {
        try
        {   //下句创建缓冲字符输入流对象，数据源是文件字符流
            BufferedReader bufreader = new BufferedReader(new FileReader(file));
            text.setText("");
            String line;
            while((line=bufreader.readLine())!=null) //读取一行字符串，输入流结束返回null
            {
                text.append(line+"\r\n");             //将字符串line添加到text文本区中
//                try                                  //try语句，第5版没写
//                {
//                    Thread.sleep(300);               //不行，sleep完了再显示
//                }
//                catch(InterruptedException ex)
//                {
//                    return;
//                }//2018年5月12日，例8.2有类似问题
            }
            bufreader.close();
        }
        catch (IOException ioex){}               //若文件不存在或IO错，不读取文件
    }
    
*/
//@author：Yeheya，2017年8月29日，2017年11月28日，2018年8月2日，2018年11月3日