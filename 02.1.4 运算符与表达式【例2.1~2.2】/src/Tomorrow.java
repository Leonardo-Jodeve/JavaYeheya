//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.1.4   运算符与表达式
//1.  运算符
//【例2.1】 求明天是星期几。
//【思考题2-1】 ① 怎样用%表示当前月份month的下月和上月？
         
public class Tomorrow
{
    public static void main(String[] args) 
    {
        int week=6;
        System.out.println("星期"+week+"的明天是星期"+(week=(week+1)%7));
        System.out.println("星期"+week+"的昨天是星期"+(week=(week-1+7)%7));
        
        //【思考题2-1】 ① 怎样用%表示当前月份month的下月和上月？
        int month=12;
        System.out.println(month+"月的下月是明年"+(month=(month%12+1))+"月");
        System.out.println(month+"月的上月是去年"+(month=((month-2+12)%12+1))+"月");
    }
}
/* 
程序运行结果如下：
星期6的明天是星期0
星期0的昨天是星期6
12月的下月是明年1月
1月的上月是去年12月

*/
//@author：Yeheya，2016-9-23