//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.8】发牌。 

//存放牌的缓冲区管程类，一发多收，多个接收线程约定接收次序；T约定每张牌的数据类型
public class CardBuffer<T>
{
    private T obj;                               //共享变量，临界资源
    private boolean isEmpty=true;                //显示obj是否空的同步信号量
    private int number;                          //人数，即接收线程数
    private int order=0;                         //约定取牌线程次序的信号量
    
    public CardBuffer(int number)
    {
        this.number = number;
    }
    
    //以下声明put()和get()对this.obj共享变量互斥操作，并实施了同步控制，成为同步方法
    public synchronized void put(T obj)          //向缓冲区写入数据，同步方法
    {
//        if(!this.isEmpty)                      //运行错，为什么？【解释】见例7.7
        while(!this.isEmpty)                     //当this.obj不空时
        {
            try
            {
                this.wait();           //使调用的线程阻塞自己，让出处理器，进入阻塞队列等待；
            }
            catch(InterruptedException ex) {}
        }
        //以下临界区，对临界资源操作，互斥锁定
        this.obj = obj;                          //当this.obj空时，this.obj获得值
        this.isEmpty=false;                      //设置this.obj为不空状态
        this.notifyAll();                        //唤醒所有等待当前临界资源的阻塞线程
    }
    
    public synchronized T get(int order)         //取值，order指定取牌线程次序，同步方法。
    {
//        while(isEmpty)                          //取消取牌线程之间的次序关系，看看会怎样。
        while(this.isEmpty || this.order!=order) //当this.obj空或取牌次序不符时
        {
            try
            {
                this.wait();           //使调用的线程阻塞自己，让出处理器，进入阻塞队列等待；
            }
            catch(InterruptedException ex) {}
        }
        //以下临界区，对临界资源操作，互斥锁定
        this.isEmpty=true;                       //设置this.obj为空状态
        this.order=(this.order+1) % this.number; //加1使取牌次序轮转
        this.notifyAll();                        //唤醒所有等待当前临界资源的阻塞线程
        return this.obj; 
    }
}
//@author：Yeheya，2017年8月20日，2017年10月1日，2017年10月7日