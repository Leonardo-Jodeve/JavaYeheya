//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月23日
//§8.2.5  对象字节流
//【例8.3】  采用对象文件保存Person对象信息。
//继承【例6.4】  输入Person对象信息。
//使用JList和Person面板
//MyEclipse设置编译路径：添加例3.2、例3.3、例6.4、例8.5项目。

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//继承【例6.4】Person对象信息管理框架类，增加的读写文件功能，当打开、关闭窗口时执行
public class FilePersonJFrame extends PersonJFrame implements WindowListener
{
    private String filename;                     //文件名字符串
    
    public FilePersonJFrame(Person[] pers, PersonJPanel person, String filename)
    {
        super(pers, person);
        this.filename = filename;
        this.setTitle("读写Person对象文件  "+filename);
        this.addWindowListener(this);            //框架监听窗口事件
 
        if(filename.endsWith(".obj"))
        	ListModelObjectFile.readFrom(this.filename, this.listmodel);
                                       //读取对象文件到列表框模型，类见【例8.5】
    }
    public FilePersonJFrame(String filename)
    {
        this(null, new PersonJPanel(), filename);
    }
    
    public void windowClosing(WindowEvent event) //窗口关闭事件处理方法
    {
        if(filename.endsWith(".obj"))
        	ListModelObjectFile.writeTo(this.filename, this.listmodel);
                                       //将列表框模型数据项写入对象文件，类见【例8.5】
    }
    public void windowOpened(WindowEvent event)     {}
    public void windowActivated(WindowEvent event)  {}
    public void windowDeactivated(WindowEvent event){}
    public void windowClosed(WindowEvent event)     {}
    public void windowIconified(WindowEvent event)  {}
    public void windowDeiconified(WindowEvent event){}
    
    public static void main(String[] arg)
    {
        Person[] pers={new Person("Li李小明",new MyDate(1992,3,15),"男","湖南","长沙"),
                       new Person("Bai白杨", new MyDate(1991,5,1),"女","湖北","武汉"),
                       new Person("Bai白桦", new MyDate(1992,3,15),"男","广西","南宁"),
                       new Person("Hua华文", new MyDate(1992,10,3),"女","广东","广州"),
                       new Person("Wang王伟", new MyDate(1990,4,3),"男","广东","广州"),
                       new Person("Zhang张莉",new MyDate(1992,4,3),"女","湖北","武汉"),
                       new Person("Zhu朱小红",new MyDate(1991,3,12),"女","广东","广州"),
                       new Person("Zhao赵泉",new MyDate(1993,10,1),"男","广西","南宁")};
        new FilePersonJFrame(pers, new PersonJPanel(), "persons.obj");
//        new FilePersonJFrame(null, new PersonJPanel(), "persons.obj");
//        new FilePersonJFrame("persons.obj");
    }
}
/*
研究，以下第5版教材没写。
① 【第4版例8.5】写readFromText()、writeToText()方法，采用字符流保存对象，不合适，
不符合就近原则，不提倡，第5版教材没写。
② 参数是DefaultListModel<String>的通用方法，详见试题库。
 */
//@author：Yeheya，2017年8月26日，2017年10月14日，2017年11月26日，2018年8月2日
//2018年7月27日，为了演示响应窗口关闭事件，没有添加打开、保存按钮。例12.6同理。