//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.2 流程控制语句
//§2.2.3   循环语句
//3.  for语句
//用for语句求累加和并显示计算公式。

public class Sum_for
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;
        for(i=1; i<=n; i++)                      //循环控制变量递增变化
            sum += i;
        System.out.println("Sum = 1+...+"+n+" = "+sum);
        
        System.out.print("Sum("+n+") = ");       //显示计算公式
        for(i=n,sum=0; i>1; i--)                 //循环控制变量递减变化
        {
            sum += i;
            System.out.print(i+"+");
        }
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
程序运行结果如下：
Sum = 1+...+10 = 55
Sum(10) = 10+9+8+7+6+5+4+3+2+1 = 55

        int n=10,sum=0;
        for(int i=1,j=1; i<=n; i++,j++)         //逗号用于分隔表达式
            sum += i;
        System.out.println("Sum = 1+...+"+n+" = "+sum);

*/
//@author：Yeheya，2016-10-14，用sum