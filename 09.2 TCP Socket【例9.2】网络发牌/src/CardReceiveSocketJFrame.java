//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年10月19日
//§9.2 TCP Socket通信
//【例9.2】 网络发牌程序。
//（2）接收客户端，使用线程接收数据

import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.net.*;

//接收客户端框架类，在线程中使用数据输入流接收整数
public class CardReceiveSocketJFrame extends JFrame implements Runnable
{
    private JTextArea text;                      //文本区
    private Socket socket;

    //构造方法，name指定客户端自己网名，host和port指定服务端的Socket
    public CardReceiveSocketJFrame(String name, String host, int port) throws IOException
    {
        super(name+"  "+InetAddress.getLocalHost().toString());      //本机的主机名和IP地址
        this.socket = new Socket(host, port);                        //客户端请求TCP连接
        this.setTitle(this.getTitle()+" : "+socket.getLocalPort());  //标题栏添加本机端口
        this.setBounds(800,200,500,120);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下在框架内容窗格中部添加文本区
        this.text = new JTextArea("");
        this.text.setLineWrap(true);                       //文本区自动换行
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        this.getContentPane().add(this.text); 
        this.setVisible(true);
        new Thread(this).start();
    }

    public void run()        //线程运行方法，采用字节流接收服务端发来的整数牌值，必须处理异常
    {
        try
        {   //下句从Socket获得字节流，再创建数据字节输入流
            DataInputStream datain = new DataInputStream(this.socket.getInputStream());
            while(true)
            {
                try
                { 
                    this.text.append(datain.readInt()+"  ");//从数据流中读取整数，添加到文本区
                }
                catch(EOFException ex)                     //数据输入流结束时抛出该异常
                {
                    break;
                }
            }
            datain.close();                                //关闭数据流
            this.socket.close();                           //关闭TCP连接
//            System.out.println("close");
        }
        catch(IOException ex){}
    }
    //【思考题】问题：何时结束？当服务端执行socket.close()时，结束，输出“close”。
    
    public static void main(String[] args) throws IOException
    {
        //接收客户端，指定服务端的IP地址和端口；若是本机，则用“127.0.0.1”指定虚拟地址
        new CardReceiveSocketJFrame("东", "127.0.0.1", 10001);
        //"南"、"西"、"北"客户端运行在其他计算机，端口分别是10002、10003、10004，省略    
        new CardReceiveSocketJFrame("西", "127.0.0.1", 10002);
        new CardReceiveSocketJFrame("南", "127.0.0.1", 10003);
        new CardReceiveSocketJFrame("北", "127.0.0.1", 10004);
        
//      new CardReceiveSocketJFrame("东", "202.119.162.208", 10001);//信息楼A308机器的IP地址
    }
}
/*
本例存在问题：
由于服务端约定端口有4个，一个客户端如何知道当前服务端等待连接的端口是哪一个。
解答：例9.3已用线程解决该问题，服务端启动若干线程，每个线程在一个约定端口等待；
一个客户端约定连接一个端口，谁先谁后都可以。
*/
//2017年12月2日，放弃使用线程接收。
/*2018年7月25日， 
（1）服务端和客户端程序都将字体改大了，第5版教材没写。
（3）客户端声明线程接收数据，成功，main()中可4句new。
*/
//@author：Yeheya，2017年10月21日，2017年11月25日，2017年12月5日，2018年7月25日