//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.2 流程控制语句
//§2.2.3   循环语句
//3.  for语句
//【例2.4】 求一个日期（年月日）对应的是星期几。

public class ChineseWeek
{
    public static void main(String[] args) 
    {
        int year=2016, month=12, day=31;
        boolean leap = year%400==0 || year%100!=0 && year%4==0;  //判断闰年
        int total = year-1980+(year-1980+3)/4;             //求平（闰）年累计的总天数
        for(int i=month-1; i>0; i--)                       //计算当年前month-1个月累计的天数
        {
            switch(i)                      
            {
                case 1: case 3: case 5: case 7: case 8: case 10: total+=31; break;
                case 4: case 6: case 9: case 11: total+=30; break;
                case 2: total+= leap ? 29 : 28;
            }
        }
        total+=day;                                        //当月的天数
        int week=1;                                       //起始日 1979-12-31是星期一
        week = (week+total) % 7;                           //求得星期几
        System.out.print(year+"年"+month+"月"+day+"日星期");
        switch(week)
        {
            case 0: System.out.println("日"); break;
            case 1: System.out.println("一"); break;
            case 2: System.out.println("二"); break;
            case 3: System.out.println("三"); break;
            case 4: System.out.println("四"); break;
            case 5: System.out.println("五"); break;
            case 6: System.out.println("六"); break;
        }
        
        //2.5 字符串
        String str="日一二三四五六";
        System.out.println("星期"+str.substring(week, week+1));
    }
}

/* 
程序运行结果如下：
2009年7月13日星期一
星期一
2012年9月8日星期六
星期六
2016年12月31日星期六
星期六

*/
//@author：Yeheya，2016-6-11