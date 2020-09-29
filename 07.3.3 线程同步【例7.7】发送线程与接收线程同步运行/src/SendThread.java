//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.4.3 交互线程的协作与同步
//【例7.8】发送线程与接收线程同步运行，采用信号量和同步方法实现。

public class SendThread<T> extends Thread        //发送线程类
{
    private T[] objs;                            //发送对象数组
    private BufferMonitor<T> buffer;             //发送缓冲区管程
    
    public SendThread(T[] objs, BufferMonitor<T> buffer)
    {
    	super("sender");
        this.objs = objs;
        this.buffer = buffer;
    }
    
    public void run()                            //线程运行方法，连续向缓冲区发送数据
    {
        for(int i=0; i<this.objs.length; i++)    //发送objs数组所有对象
        {
            buffer.put(this.objs[i]);
            System.out.println(this.getClass().getName()+" put : "+this.objs[i]);
        }
        buffer.put(null);                        //发送null作为结束标记
        System.out.println(this.getClass().getName()+" put : null");
    }
}
//@author：Yeheya，2017年8月18日