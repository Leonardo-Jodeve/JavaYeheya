//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.2 流程控制语句
//§2.2.3   循环语句
//2.  do-while语句
//用do-while语句求累加和并显示计算公式

public class Sum_dowhile
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;        
        do 
        {
            sum += i;
            i++;
        } while(i<=n);
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        i=0;  sum=0;
        do
        {
            sum += i;
            i++;
        } while(i>=n);			                 //循环体执行一次
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);
        
        i=1;  sum=0;                             //显示计算公式
        System.out.print("Sum("+n+")= ");
        do 
        {
            sum += i;
            System.out.print(i+"+");
            i++;
        } while(i<n);
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
程序运行结果如下：
sum = 55
i = 11

sum = 0
i = 1

Sum(10) = 1+2+3+4+5+6+7+8+9+10 = 55

*/
//@author：Yeheya，2016-10-14，用sum
/*程序说明：     
     
    int i=1,n=10,sum=0;
    do 
    {
        sum += i;
        i++;
    } while(i>0);				// 死循环，循环条件永远为true

*/
//@author：Yeheya，2016-6-11