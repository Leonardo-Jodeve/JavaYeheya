//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.7】发送线程与接收线程同步运行，采用信号量和同步方法实现。

public class ReceiveThread<T> extends Thread     //接收线程类
{
    private BufferMonitor<T> buffer;             //接收缓冲区管程
    
    public ReceiveThread(BufferMonitor<T> buffer)//参数指定缓冲区管程
    {
        this.buffer = buffer;
    }
    
    public void run()                            //线程运行方法，连续从缓冲区接收数据
    {
        T obj;
        do
        {
            obj=this.buffer.get();               //接收对象，以null标记结束
            System.out.println("\t\t\t\t"+this.getClass().getName()+" get : "+obj);
        } while(obj!=null);                      //接收对象，以null标记结束
    }
}
//@author：Yeheya，2017年8月18日，2017年10月1日