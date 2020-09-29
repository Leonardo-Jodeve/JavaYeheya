//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3 线程同步机制
//§7.3.3 交互线程的协作与同步
//【例7.6】  线程通信，发送线程与接收线程，不协调有错误。

//带互斥锁的缓冲区类，T表示共享变量的数据类型，其中声明put()、get()方法为同步方法，
//控制调用这些方法的发送线程和接收线程互斥地执行，
//因为没有协调两者步伐一致地前进，因此，存在步调不一致的错误。
public class LockedBuffer<T>
{
    private T obj;                               //共享变量，临界资源
    
    //以下声明put()和get()为互斥方法，方法体是临界区，锁定this.obj共享变量，互斥操作
    //必须互斥，管程思想
    public synchronized void put(T obj)          //向缓冲区写入数据，互斥方法
    {
        this.obj = obj; 
    }
    
    public synchronized T get()                  //从缓冲区获得数据，互斥方法
    {
        return this.obj;
    }
}

class SendThread1<T> extends Thread              //发送线程类
{
    private LockedBuffer<T> buffer;              //互斥缓冲区，存放发送的数据
    private T[] objs;                            //发送对象数组
    
    public SendThread1(T[] objs, LockedBuffer<T> buffer)//指定缓冲区
    {
        this.objs = objs;
        this.buffer = buffer;
    }
    
    public void run()                            //线程运行方法，连续向缓冲区发送数据
    {
//    	synchronized (this)     //语义肯定不行，语法，
//    	{
        for(int i=0; i<this.objs.length; i++)    //发送objs数组中所有对象
        {
            buffer.put(this.objs[i]);
            System.out.println(this.getClass().getName()+" put : "+this.objs[i]);
        }
        buffer.put(null);              //发送null作为结束标记。若无此句，则接收线程死循环
        System.out.println(this.getClass().getName()+" put : null");
//    	}
    }
}

class ReceiveThread1<T> extends Thread           //接收线程类
{
    private LockedBuffer<T> buffer;              //互斥缓冲区，从此获取数据
    
    public ReceiveThread1(LockedBuffer<T> buffer)
    {
        this.buffer = buffer ;
    }
    
    public void run()                            //线程运行方法，连续从缓冲区接收数据
    {
//    	synchronized (this)     //语义肯定不行，语法，
//    	{
        T obj;
        do
        {   obj=this.buffer.get();               //接收对象
            System.out.println("\t\t\t\t"+this.getClass().getName()+" get : "+obj);
        }
        while(obj!=null);                        //以null标记结束
//    	}
    }

    public static void main(String args[])
    {
        LockedBuffer<Integer> buffer = new LockedBuffer<Integer>();
        Integer[] objs={1,2,3,4,5};//,6,7,8}; 
        Thread sender = new SendThread1(objs,buffer); //发送线程优先级默认为5
        sender.start();
        Thread receiver = new ReceiveThread1(buffer);
        receiver.setPriority(sender.getPriority()-1); //设置接收线程优先级小于发送线程优先级
        receiver.start();
    }
}
/*
程序运行结果如下：
                 //设置接收线程优先级小于发送线程优先级
SendThread1 put : 1
				ReceiveThread1 get : 1
				ReceiveThread1 get : 2
SendThread1 put : 2
				ReceiveThread1 get : 2
				ReceiveThread1 get : 3
SendThread1 put : 3
				ReceiveThread1 get : 3
				ReceiveThread1 get : 4
				ReceiveThread1 get : 4
				ReceiveThread1 get : 4
SendThread1 put : 4
				ReceiveThread1 get : 4
SendThread1 put : 5
				ReceiveThread1 get : 5
SendThread1 put : null
				ReceiveThread1 get : null



                    //没有设置两者优先级
SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
				ReceiveThread1 get : 4
SendThread1 put : 5
				ReceiveThread1 get : 5
				ReceiveThread1 get : null
SendThread1 put : null


SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
				ReceiveThread1 get : 4
				ReceiveThread1 get : 5
				ReceiveThread1 get : 5
SendThread1 put : 5
				ReceiveThread1 get : 5
				ReceiveThread1 get : null
SendThread1 put : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : null
				ReceiveThread1 get : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
SendThread1 put : null
				ReceiveThread1 get : 8
				ReceiveThread1 get : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
				ReceiveThread1 get : 7
SendThread1 put : null
				ReceiveThread1 get : null
	
	
				//设置接收线程优先级
SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
				ReceiveThread1 get : 3
				ReceiveThread1 get : 3
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
SendThread1 put : null
				ReceiveThread1 get : null
				
*/
//@author：Yeheya，2017年8月2日，2017年10月1日，2017年10月5日