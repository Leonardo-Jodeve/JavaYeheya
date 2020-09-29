//《数据结构（Java版）（第4版）》，作者：叶核亚，2014年7月11日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年10月14日
//§2.4.4 递归算法
//【例2.9】 求Fibonacci数列第n项的递归方法。

public class Fibonacci
{
    public static int fib(int n)                 //返回Fibonacci数列第n（≥0）项，递归方法
    {
        if(n==0 || n==1)                         //递归边界条件，结束条件
            return n;
        return fib(n-2)+fib(n-1);                //递推通式，递归调用
    }
    
    public static void main(String[] args)
    {
        for (int i=0; i<=25; i++)
            System.out.print(" "+fib(i));
        System.out.println();
    }
}
/*
程序运行结果如下：
 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 75025
*/
/*
程序设计说明如下。
（1）第2章讨论数学问题和算法，不处理异常。
if (n<0)                                 //若n<0，无定义，抛出无效参数异常
    throw new java.lang.IllegalArgumentException("n="+n); //异常见第5章

 */
//author：Yeheya。2016-10-14