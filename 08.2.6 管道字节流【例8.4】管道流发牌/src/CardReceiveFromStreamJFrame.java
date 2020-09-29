//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月26日
//§8.2.5  管道字节流
//【例8.4】  使用管道字节流实现发牌程序。

import java.awt.Font;
import javax.swing.*;
import java.io.*;

//使用管道字节流的取牌框架类，实现可运行接口，包含线程
public class CardReceiveFromStreamJFrame extends JFrame implements Runnable
{
    private InputStream in;                      //字节输入流
    private JTextArea text;                      //显示牌值的文本区
    String title;                                //为了测试，第5版教材没写
    
    //构造方法，本例调用者传递给in的是管道字节输入流；title指定窗口标题；x、y指定窗口坐标
    public CardReceiveFromStreamJFrame(InputStream in, String title, int x, int y)
    {
        super(title);        
        this.setBounds(x,y,250,150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.in = in;
        this.title = title; 

        this.text = new JTextArea();
        this.getContentPane().add(this.text);
        this.text.setLineWrap(true);             //设置文本区自动换行
        this.text.setEditable(false);            //不可编辑
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));//设置字体
        this.setVisible(true);
        new Thread(this).start();                //启动取牌线程，this是线程的目标对象
    }
    
    public void run()                            //线程运行方法，取牌
    {
        DataInputStream datain = new DataInputStream(this.in);//数据输入流的数据源是管道字节输入流
        while(true)
        {
            try
            {
                this.text.append(String.format("%4d",datain.readInt()));//文本区添加从数据流读取整数
                Thread.sleep(100);               //控制显示每张牌的速度
            }
            catch(EOFException ex)               //用于测试
            {
                System.out.println(title+"EOF");
                break;
            }
            catch(IOException |InterruptedException ex)    //包含数据字节输入流结束时捕获的EOFException等异常
            {
                System.out.println(title+"IOE");
                break;
            }
        }
        try 
        {
            datain.close();                               //关闭数据字节输入流
            this.in.close();                              //关闭管道字节输入流
        }
        catch(IOException ex) {}
    }
}
//@author：Yeheya，2017年8月26日，2017年11月28日