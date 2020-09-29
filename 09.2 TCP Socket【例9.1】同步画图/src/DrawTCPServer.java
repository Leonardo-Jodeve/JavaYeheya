//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年6月26日
//§9.2 TCP Socket通信
//【例9.1】 同步画图，建立一条TCP连接。
//（1）服务端程序
//本例目的：① 启动服务端程序时，就显示界面。

import java.net.*;
import java.io.*;

//同步画图服务端，采用TCP Socket通信
public class DrawTCPServer
{
    //构造方法，name指定网名，port指定端口；本机IP地址和port端口构成服务端的Socket
    public DrawTCPServer(String name, int port) throws IOException
    {
        ServerSocket server = new ServerSocket(port);      //ServerSocket提供TCP连接服务，port端口
        DrawTCPSocketJFrame draw = new DrawTCPSocketJFrame(name); //画图程序图形用户界面
        draw.setTitle(draw.getTitle()+" : "+port);
        System.out.println(name+"在"+port+"端口等待，server.accept()");
        Socket socket = server.accept();                   //等待接收客户端的TCP连接申请
        draw.setSocket(socket);
        server.close();                                    //关闭TCP连接服务
    }

    public static void main(String args[]) throws IOException
    {
        new DrawTCPServer("花仙子", 10011);                //启动服务端，指定网名和端口
    }
}
//@author：Yeheya，2018年7月25日