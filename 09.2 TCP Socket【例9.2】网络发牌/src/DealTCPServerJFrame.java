//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年10月19日
//§9.2 TCP Socket通信
//【例9.2】  网络发牌程序，多条TCP连接。
//（1）发牌服务端

import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.net.*;

//发牌服务端框架类，采用数据输出流发送整数
public class DealTCPServerJFrame extends JFrame
{
    //构造方法，port指定初始端口，牌值范围是1～cardMax，number指定人数
    public DealTCPServerJFrame(int port, int cardMax, int number) throws IOException 
    {
        super("发牌服务端  "+InetAddress.getLocalHost()+" : "+port);
        this.setBounds(200,200,600,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下在框架内容窗格中部添加文本区
        JTextArea text = new JTextArea();
        text.setFont(new Font("宋体", Font.BOLD, 20));
        text.setLineWrap(true);                            //文本区自动换行
        this.getContentPane().add(text); 
        this.setVisible(true);
   
        //以下使用Socket数组存储多条TCP连接，每条TCP连接向数据流写入整数牌值
        Socket[] sockets=new Socket[number];
        DataOutputStream[] dataouts=new DataOutputStream[number];  //数据字节输出流
        for(int i=0; i<number; i++)                        //以下连接n个客户端
        {
            System.out.println("在"+port+"端口等待，server.accept()");
            text.append("在"+port+"端口等待TCP连接，");
            sockets[i] = new ServerSocket(port).accept();  //等待接收客户端的连接申请
            text.append("连接"+sockets[i].getInetAddress()+" : "+sockets[i].getPort()+"；\n");
            dataouts[i] = new DataOutputStream(sockets[i].getOutputStream());  //获得Socket字节输出流
            port++;                                        //在下一个端口等待下一个客户端
        }
        text.append("Sender: \r\n");           
        for(int j=1; j<=cardMax; )                         //以下向n个客户端共发送cardMax张牌
        {
            for(int i=0; j<=cardMax && i<dataouts.length; i++,j++)
            {
                dataouts[i].writeInt(j);
                text.append(String.format("%4d",j));
            }
        }
        for(int i=0; i<number; i++)
        {
            dataouts[i].close();                           //关闭数据字节流，对方接收到EOFException异常
            sockets[i].close();                            //关闭TCP连接
        }
        //没法关闭ServerSocket服务，算了。
    }

    public static void main(String args[]) throws IOException
    {
        new DealTCPServerJFrame(10001,52,4);               //启动发牌服务端，约定端口
    }
}
/*
    运行问题说明如下：  
    不能显示汉字，为什么？  【答】"Arial"字体不对，改成宋体就好了。 
     采用什么字符集，默认GBK。 
*/ 
//2017年10月21日，测试，发牌程序若没有port++，在一个端口等待多个连接，不行，抛出以下Bind异常。
//2017年12月2日，放弃使用线程发送。
/*2018年7月25日， 
（1）服务端和客户端程序都将字体改大了，第5版教材没写。
（2）服务端声明线程发送数据，又试了一次，未成功。
       管道流的原理，Socket数组，拆分到线程，则要拆分4个序列，还要集合，第9章现有技术无法实现。 
*/
//@author：Yeheya，2017年10月21日，2017年11月25日，2017年12月5日，2018年7月25日