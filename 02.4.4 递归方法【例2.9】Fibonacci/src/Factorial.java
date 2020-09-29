//《数据结构（Java版）（第4版）》，作者：叶核亚，2014年7月11日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年10月14日
//§2.4.4 递归算法
//【例2.9】 求n！。

public class Factorial
{
    public static int fact(int n)                //求阶乘n!，n≥0，递归方法
    {
        if(n==0 || n==1)                         //递归边界条件，结束条件
            return 1;
        return n*fact(n-1);                      //递推通式，递归调用
    }

    public static void main(String args[])
    {
        int n=5;
        System.out.println(n+"!="+fact(n));      //5!=120
    }
}
/*
程序运行结果如下：
5!=120      
5!=5*4*3*2*1=120
*/
/*
程序设计说明如下。
（1）第2章讨论数学问题和算法，不处理异常。
        if (n<0)                                 //若n<0，无定义，抛出无效参数异常
            throw new java.lang.IllegalArgumentException("n="+n);
*/
//author：Yeheya。2016-10-14