//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月25日
//§11.2 JSP
//【实验11-3】求和，JSP文档输入、计算并显示结果表达式，调用类中方法。

package design;                                  //声明包

public class Calculation                         //计算类，其中包含实现通用功能的方法
{
    public static int sum(int n)                 //返回计算的1到n之和
    {   int s=0;
        for(int i=1; i<=n; i++)
            s+=i;
        return s;
    }

    public static String sumToString(int n)      //计算1到n之和，返回计算过程和结果表达式字符串
    {  
        int sum=0;
        String expstr="Sum("+n+")=";
        for(int i=1; i<n; i++)  
        {
            expstr += i+"+";
            sum+=i;
        }
        return expstr+n+"="+(sum+n)+"\n";
    }
}
//@author：Yeheya，2018年4月11日