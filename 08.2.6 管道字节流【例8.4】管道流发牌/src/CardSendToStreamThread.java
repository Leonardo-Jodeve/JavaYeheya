//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月26日
//§8.2.5  管道字节流
//【例8.4】  使用管道字节流实现发牌程序。

import java.io.*;

public class CardSendToStreamThread extends Thread //使用字节流的发牌线程类
{
    private OutputStream[] outs;                 //字节输出流对象数组，数组长度表示人数
    private int cardMax;                         //最大牌值，牌值为1～cardMax
    
    //构造方法，本例调用者传递给outs的是管道字节输出流数组
    public CardSendToStreamThread(OutputStream[] outs, int cardMax)
    {
        this.outs = outs;
        this.cardMax = cardMax;
        this.setPriority(Thread.MAX_PRIORITY);   //设置线程最高优先级10
    }

    public void run()                            //线程运行方法，发牌
    {
        DataOutputStream[] dataouts = new DataOutputStream[this.outs.length];
        for(int i=0; i<dataouts.length; i++)
            dataouts[i] = new DataOutputStream(this.outs[i]);//数据流的数据源是管道字节流
        try 
        {
            int value=1;
            while(value<=this.cardMax)           //发牌1～cardMax
                for(int i=0; value<=this.cardMax && i<dataouts.length; i++)
                    dataouts[i].writeInt(value++);
            
            for(int i=0; i<dataouts.length; i++)
            {
                dataouts[i].close();             //关闭数据字节输出流，抛出EOFException异常
                this.outs[i].close();            //关闭管道字节输出流
            }                                    //若无此for，接收线程捕获的是IOException异常
        }
        catch(IOException ex) {}
    }
}
//@author：Yeheya，2017年8月26日，2017年11月28日