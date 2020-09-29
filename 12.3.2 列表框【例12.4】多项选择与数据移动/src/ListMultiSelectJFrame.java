//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年8月7日
//§12.3.2 列表框
//【例12.4】 列表框多项选择与数据移动。

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

//列表框多项选择与数据移动框架类，继承框架类，响应动作事件，实现文件过滤器接口
public class ListMultiSelectJFrame extends JFrame implements FileFilter, ActionListener
{
    private JList<String> jlist_source;          //源列表框
    private DefaultListModel<String> listmodel_source, listmodel_dest; //列表框模型
    
    public ListMultiSelectJFrame()
    {
        super("列表框多项选择与数据移动"); 
        this.setBounds(200,200,450,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BoxLayout layout = new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS);
        this.getContentPane().setLayout(layout);           //设置框架内容窗格为盒式水平布局
//        this.getContentPane().setLayout(new FlowLayout()); //流布局，很难看
        
        //以下创建源列表框，其中添加指定目录下的文件名
        this.listmodel_source = new DefaultListModel<String>();//源列表框模型
        File[] files=new File("国旗","").listFiles(this);    //获得过滤的文件列表
        for(int i=0; i<files.length; i++)
        {      
            int index=files[i].getName().lastIndexOf(".gif");  //查找文件扩展名
            this.listmodel_source.addElement(files[i].getName().substring(0,index));
                                                   //源列表框模型添加文件名（不包含文件扩展名）
        }
        this.jlist_source = new JList<String>(this.listmodel_source);//创建列表框
        this.getContentPane().add(new JScrollPane(this.jlist_source));
        this.jlist_source.setCellRenderer(new IconListRenderer());   //设置单元渲染器
        
        //以下添加Box容器，在其上添加按钮
        Box box = new Box(BoxLayout.Y_AXIS);               //Box容器以盒式垂直布局 
        this.getContentPane().add(box);
        String[] buttonstr={"　〉","〉〉"};
        for(String str:buttonstr)
        {
            JButton button = new JButton(str);
            button.addActionListener(this);
            box.add(button);
        }

        this.listmodel_dest = new DefaultListModel<String>();//结果列表框模型
        this.getContentPane().add(new JScrollPane(new JList<String>(listmodel_dest)));
        this.setVisible(true);
    }

    public boolean accept(File file)             //文件过滤方法，实现文件过滤器接口
    {
        return file.getName().toLowerCase().endsWith(".gif");//文件扩展名匹配
    }    
    
    public void actionPerformed(ActionEvent event)         //动作事件处理方法，单击按钮
    {
        switch(event.getActionCommand())
        {
            case "　〉":                //单击"〉"按钮，复制源列表框选中多项到结果列表框
                try
                {
                    //下句当列表框选中多项时，返回列表集合对象。只能是ArrayList<T>，不能是LinkedList<T>
                    ArrayList<String> list = (ArrayList<String>)this.jlist_source.getSelectedValuesList();
                    for(String str : list)
                        this.listmodel_dest.addElement(str);   //列表框模型添加数据项
                }
                catch(ClassCastException ex)        //如果列表框空，或没有选中一项，则抛出异常
                {
                    JOptionPane.showMessageDialog(this, "列表框空，或没有选中数据项，不能操作");
                }
                break;
            
            case "〉〉":                //单击"〉〉"按钮，复制源列表框所有数据项到结果列表框
                int count=this.listmodel_source.getSize(); //获得源列表框的数据项数
                this.listmodel_dest.removeAllElements();   //删除结果列表框所有数据项
                for(int i=0; i<count; i++)                 //结果列表框模型添加数据项
                    this.listmodel_dest.addElement(listmodel_source.elementAt(i));
//                break;
        }
    }
    
    public static void main(String arg[])
    {
        new ListMultiSelectJFrame();
    }
}
//@author：Yeheya，2018年8月9日