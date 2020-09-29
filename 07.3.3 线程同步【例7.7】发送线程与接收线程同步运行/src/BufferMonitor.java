//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.7】线程通信，发送线程与接收线程同步运行。
//采用管程方式，有信号量、同步方法、阻塞和唤醒的同步控制方法

//缓冲区管程类，其中控制put()、get()方法同步运行，
//控制调用这些方法的发送线程和接收线程同步地执行，步调一致，算法正确。
public class BufferMonitor<T>
{
    private T obj;                               //共享变量，临界资源
    private boolean isEmpty=true;                //显示obj是否为空的同步信号量
    
    //以下声明put()和get()对共享变量互斥操作，并实施了同步控制，两者同步运行
    public synchronized void put(T obj)          //向缓冲区写入数据，同步方法
    {
//        synchronized(this)                     //与互斥语句功能相同，声明临界区是方法体，锁定当前缓冲区对象
//    	if(!this.isEmpty)                        //本例不错，但例7.8错，为什么？？我明白了
        while(!this.isEmpty)                     //当this.obj不空时，等待。管程的条件变量，控制线程同步的条件
        {
        	try
            {
//                System.out.println("this="+this.getClass().getName()+" wait()");//this指当前调用put()方法的线程对象？？
                //下句使调用的线程阻塞自己，让出处理器，进入阻塞队列等待；
                //阻塞线程被唤醒后再次测试条件变量，若空，则循环结束。
                this.wait();
                //【解释】所以，是循环，不是if。
                //【解释】本例用if可运行，是因为，只有2个线程，被唤醒后不需等待；
                //【解释】例7.8错，是因为，有5个线程，被唤醒后还需再等待。
            }
            catch(InterruptedException ex) {}
        }
        
        //以下临界区，对临界资源操作，互斥锁定
        this.obj = obj;                          //当this.obj空时，为this.obj赋值
        this.isEmpty = false;                    //设置this.obj为不空状态，改变信号量状态
        this.notify();                           //唤醒一个等待当前临界资源的阻塞线程，其进入就绪态
    }
    
    public synchronized T get()                  //从缓冲区获得数据，同步方法
    {
        while(this.isEmpty)                      //当this.obj不空时。管程的条件变量，控制线程同步的条件
        {
            try
            {
                this.wait();                     //使调用的线程阻塞自己，让出处理器，进入阻塞队列等待
            }
            catch(InterruptedException ex) {}
        }

        //以下临界区，对临界资源操作，互斥锁定
        this.isEmpty = true;                     //设置this.obj为空状态，改变信号量状态
        this.notify();                           //唤醒一个等待当前临界资源的阻塞线程，其进入就绪态
        return this.obj;                         //返回值
    }
}

class LockedBuffer_ex
{
    public static void main(String args[])
    {
        BufferMonitor<Integer> buffer = new BufferMonitor<Integer>();
        Integer[] objs={1,2,3,4,5}; //,6,7,8
        (new SendThread(objs,buffer)).start();
        (new ReceiveThread(buffer)).start();
    }
}
/*
程序运行结果如下：
程序运行结果如下：为什么？？
SendThread put : 1
SendThread put : 2
				ReceiveThread get : 1
				ReceiveThread get : 2
SendThread put : 3
SendThread put : 4
				ReceiveThread get : 3
				ReceiveThread get : 4
SendThread put : 5
				ReceiveThread get : 5
SendThread put : 6
				ReceiveThread get : 6
				ReceiveThread get : 7
SendThread put : 7
SendThread put : 8
				ReceiveThread get : 8
SendThread put : null
				ReceiveThread get : null
*/
//@author：Yeheya，2017年8月18日，2017年10月1日，2017年10月5日