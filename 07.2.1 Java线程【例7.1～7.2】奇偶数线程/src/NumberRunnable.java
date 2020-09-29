//《Java程序设计实用教程（第4版）》，作者：叶核亚
//《Java程序设计实用教程（第5版）》， 作者：叶核亚，2017年3月11日
//§7.2.1   Runnable接口与Thread类
//【例7.2】  奇数/偶数序列线程，实现Runnable接口。

//线程的目标对象类，实现Runnable接口的类，提供线程运行方法
public class NumberRunnable implements Runnable
{
    private int first, end;                      //序列初值和终值
    
    public NumberRunnable(int first, int end)    //构造方法，first、end指定序列初值和终值
    {
        this.first = first;
        this.end = end;
    }
    
    public void run()                            //线程运行方法，实现Runnable接口
    {
//        System.out.println();
        for(int i=first; i<end; i+=2)
            System.out.print(i+"  ");
        System.out.println("结束！");
    }    
    public static void main(String[] args)
    {
        Runnable target = new NumberRunnable(1,20);       //创建目标对象，提供线程体
        Thread thread_odd = new Thread(target,"奇数线程");
                             //以目标对象target创建线程对象，thread_odd执行target的run()方法
        thread_odd.start();
        new Thread(new NumberRunnable(2,10),"偶数线程").start();
    }
}
/*
程序运行结果如下：
1  2  4  6  8  结束！
3  5  7  9  11  13  15  17  19  结束！


1  3  5  7  9  11  13  15  17  19  21  23  25  27 29
2  4  6  8  10  12  14  16  18  20  22  24  26  28  30  32  34
  36  38  40  42  44  46  48  结束！
31  33  35  37  39  41  43  45  47  49  结束！
*/

/*
    public NumberRunnable()
    {
        this(0);
    }
2014年6月5日
存在问题：
run()方法中，无法显示线程名？？是的
*/
//@author：Yeheya，2017-3-11，2017年7月19日