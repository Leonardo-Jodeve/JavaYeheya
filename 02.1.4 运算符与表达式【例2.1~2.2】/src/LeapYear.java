//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.1.4   运算符与表达式
//1.  运算符
//（1） 算术位运算符
//【例2.2】  判断一个年份是否为闰年。

public class LeapYear
{
    public static void main(String[] args) 
    {
        int year=2016;
        boolean leap = year%400==0 || year%100!=0 && year%4==0;
                             //先计算表达式，再赋值；带短路功能的逻辑运算，按逻辑运算符出现次序进行运算，此处先计算||，再计算&&
        System.out.println(year+" is a leap year？"+leap);
    }
}
/* 
程序运行结果如下：
2000 is a leap year？true
2016 is a leap year？true

        boolean leap = year%400==0 | year%100!=0 & year%4==0;
        //正确，逻辑运算，没有短路功能，|优先级低于&，先计算&，再计算|。如何调试知道？？
*/
//@author：Yeheya，2016-6-11
