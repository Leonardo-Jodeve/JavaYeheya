//《Java程序设计实用教程（第4版）》，作者：叶核亚
//《Java程序设计实用教程（第5版）》， 作者：叶核亚，2017年3月11日
//§7.2.1   Runnable接口与Thread类
//【例7.1】  奇数/偶数序列线程，继承Thread类。

public class NumberThread extends Thread         //线程类，输出奇数/偶数序列；继承Thread类
{
    private int first,n;                         //序列初值和元素个数

    //构造方法，name指定线程名，first、n指定序列初值和和元素个数
    public NumberThread(String name, int first, int n)
    {
        super(name);                             //构造线程对象时指定线程名
        this.first = first;
        this.n = n;
    }
    public void run()                            //线程运行方法，覆盖Thread的run()
    {
        long time1=System.currentTimeMillis();   //开始时间
        System.out.print("\n"+this.getName()+"开始时间="+time1+"，");//输出线程名和时间
        for(int i=0; i<n; i++)                   //循环输出n个序列值，步长为2                   
            System.out.print((first+2*i)+"  ");
        long time2=System.currentTimeMillis();   //结束时间
        System.out.println(this.getName()+"结束时间="+time2+"，耗时"+(time2-time1)+"毫秒。");
    }
    public static void main(String args[])
    {
    	Thread thread = Thread.currentThread();            //当前运行线程是main
    	System.out.println("currentThread="+thread.getName());     //输出当前线程对象
        System.out.println("main Priority="+thread.getPriority()); //输出当前线程对象的优先级
        Thread thread_odd = new NumberThread("奇数线程",1,20);  //创建线程对象，父类对象引用子类实例
        Thread thread_even = new NumberThread("偶数线程",2,10);
//        thread_odd.setPriority(MIN_PRIORITY);//MAX_PRIORITY);//10);              //设置优先级为最高
        thread_odd.start();                                      //启动线程对象
        thread_even.start();
        System.out.println("activeCount="+Thread.activeCount()); //输出当前活动线程数
//        System.out.println("thread_odd Priority="+thread_odd.getPriority()); //输出当前线程对象的优先级
    }
}
/*
程序多次运行结果如下：
                             //("奇数线程",1,20);    ("偶数线程",2,10);
currentThread=main
main Priority=5
activeCount=3
奇数线程开始时间=1500512324364，
偶数线程开始时间=1500512324364，2  4  6  8  10  12  14  16  18  20  偶数线程结束时间=1500512324364，耗时0毫秒。
1  3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  奇数线程结束时间=1500512324365，耗时1毫秒。
//@author：Yeheya，2017年7月20日
 
currentThread=main
main Priority=5
奇数线程开始时间=1509173100210，activeCount=3
偶数线程开始时间=1509173100211，1  2  3  5  7  4  6  8  9  11  10  12  14  13  15  16  18  20  17  19  21  偶数线程结束时间=1509173100212，耗时1毫秒。
23  25  27  29  31  33  35  37  39  奇数线程结束时间=1509173100213，耗时3毫秒。

currentThread=main
main Priority=5
奇数线程开始时间=1509173191006，1  3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  奇数线程结束时间=1509173191006，耗时0毫秒。
activeCount=2
偶数线程开始时间=1509173191007，2  4  6  8  10  12  14  16  18  20  偶数线程结束时间=1509173191007，耗时0毫秒。

//@author：Yeheya，2017年10月28日

                                       //n=3个元素
currentThread=main
main Priority=5
奇数线程:  activeCount=3
偶数线程:  2  4  6  偶数线程结束！
1  3  5  奇数线程结束！

                                       //n=5个元素
currentThread=main
main Priority=5
奇数线程:  activeCount=3
1  3  5  7  9  奇数线程结束！
偶数线程:  2  4  6  8  10  偶数线程结束！

                                       //n=10个元素
currentThread=main
main Priority=5
奇数线程:  activeCount=3
1  3  5  7  9  11  13  15  17  19  奇数线程结束！
偶数线程:  2  4  6  8  10  12  14  16  18  20  偶数线程结束！

                                       //n=10个元素
currentThread=main
main Priority=5
奇数线程:  activeCount=3
偶数线程:  1  3  5  7  9  11  13  15  17  2  19  4  奇数线程结束！
6  8  10  12  14  16  18  20  偶数线程结束！

                                       //n=20个元素
currentThread=main
main Priority=5
奇数线程:  1  activeCount=3
3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  
偶数线程:  33  35  37  39  2  奇数线程结束！
4  6  8  10  12  14  16  18  20  22  24  26  28  30  32  34  36  38  40  偶数线程结束！
//@author：Yeheya，2017-3-12

thread_odd.setPriority(10);      //最高优先级
再次运行
thread_odd.setPriority(1);       //最低优先级
 
[lnftjghm]
*/
//@author：Yeheya，2017-3-11，2017年7月19日