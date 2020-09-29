//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.3.1  一维数组
//【例2.5】 用一维数组保存Fibonacci序列值。

public class FibonacciArray
{
    public static void main(String[] args) 
    {
        int n=25,  fib[]=new int[n];
        fib[0]=0;                                //最初两项的值
        fib[1]=1;
        for(int i=2; i<n; i++)
            fib[i] = fib[i-1] + fib[i-2];        //每项值是其前两项值之和
        for(int i=0; i<fib.length; i++)          //输出一维数组
            System.out.print(fib[i]+" ");
        System.out.println();

        for(int value : fib)                     //逐元循环，value逐个引用fib数组中的每个元素
            System.out.print(value+" ");
        System.out.println();
    }
}
/* 
程序运行结果如下：
0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 
*/
//@author：Yeheya，2016-6-11