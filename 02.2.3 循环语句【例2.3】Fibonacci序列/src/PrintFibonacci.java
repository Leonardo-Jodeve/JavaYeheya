//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.2 流程控制语句
//§2.2.3   循环语句
//2. do-while语句
//【例2.3】  计算Fibonacci序列。

public class PrintFibonacci 
{
    public static void main(String[] args)
    {
        byte a=0, b=1;                           //最初两项的值
        do
        {
            System.out.print(" "+a+" "+b);
            a = (byte)(a+b);                     //每项值是其前两项值之和
            b = (byte)(a+b);                     //两个byte整数运算结果是int类型，类型强制转换后才能赋值
        } while(a>0);                            //byte整数溢出时循环停止，不是死循环
        System.out.println("，循环结束，a="+a);

        short i=0, j=1;                          //最初两项的值
        do
        {   System.out.print(" "+i+" "+j);
            i = (short)(i+j);                    //每项值是其前两项值之和
            j = (short)(i+j);                    //两个short整数运算结果是int类型，类型强制转换后才能赋值
         //   System.out.print(", "+i+", "+j);   //不行，i溢出
        } while(i>0);                            //short整数溢出时循环停止，不是死循环
        System.out.println("，循环结束，i="+i);
    }
}
/* 
程序运行结果如下：
 0 1 1 2 3 5 8 13 21 34 55 89，循环结束，a=-112
 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657，循环结束，i=-19168
*/
/*
程序设计说明如下：
1、循环中的赋值语句， 不能写成如下，有编译错， 两个byte或short整数的运算表达式结果类型是int。
       充分说明byte或short不是单独用1或2个字节存储的，而是用int存储和运算的，每次类型转换实际上是截尾。 
         b = (a+b);
     i = (i+j);                         //不能赋值，因为i+j表达式结果类型是int。充分说明short
     j = (i+j);
*/
//@author：Yeheya，2016-6-11