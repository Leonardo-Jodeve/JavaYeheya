//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.2 流程控制语句
//§2.2.3   循环语句
//1. while语句
//用while语句求累加和。

public class Sum_while
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;
        while(i<=n)
        {
            sum += i;
            i++;             	                 //改变循环条件
        }                                        //循环结束后，i=11，sum=55
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        i=1;  sum=0;
        while(i>=n)			                     //循环体一次也不执行
            sum += i++;
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        //思考题1
        i=0;n=10;sum=0;
        while(i<n)
        {
            i++;
            sum += i;
        }
        System.out.println("Sum = 1+...+"+n+" = "+sum);

        //思考题2，显示计算公式
        i=1;n=16;sum=0;
        System.out.print("Sum("+n+")= ");
        while(i<n)
        {
            sum += i;
            System.out.print(i+"+");
            i++;
        }
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
程序运行结果如下：
sum = 55
i = 11

sum = 0
i = 1

Sum = 1+...+10 = 55

Sum(8) = 1+2+3+4+5+6+7+8 = 36
Sum(16)= 1+2+3+4+5+6+7+8+9+10+11+12+13+14+15+16 = 136
*/

/*程序说明：     
int i=1,n=10,sum=0;
while(i<=n)				// 死循环
    sum += i;
*/
//@author：Yeheya，2016-10-14，用sum