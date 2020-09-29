//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月26日
//§8.2.5  管道字节流
//【例8.4】  使用管道字节流实现发牌程序。
//功能同【例7.8】发牌程序，§7.3.3 线程同步；没有设置指向【例7.8】的编译路径。

import java.io.*;

public class PipedDeal                                     //使用管道流发牌
{
    //构造方法，牌值范围是1～cardMax，number指定人数
    public PipedDeal(int cardMax, int number) throws IOException
    {
        //以下创建管道字节输入/出流对象数组，
        PipedInputStream[] pipedins = new PipedInputStream[number];
        PipedOutputStream[] pipedouts=new PipedOutputStream[number];
        for(int i=0; i<number; i++)
        {
            pipedins[i] = new PipedInputStream();          //创建管道输入流对象
            pipedouts[i]= new PipedOutputStream(pipedins[i]);//创建管道输出流对象并建立连接
        }
        new CardSendToStreamThread(pipedouts, cardMax).start();//创建并启动发牌线程，cardMax张牌
        String[] titles={"北","东","南","西"};
        int x[]={300,550,300,50}, y[]={200,320,440,320};
        for(int i=0; i<number; i++)                        //创建并启动number个取牌线程
            new CardReceiveFromStreamJFrame(pipedins[i],titles[i],x[i],y[i]);
    }
    public static void main(String[] args) throws IOException
    {
        new PipedDeal(52,4);
    }
}
//@author：Yeheya，2017年8月26日