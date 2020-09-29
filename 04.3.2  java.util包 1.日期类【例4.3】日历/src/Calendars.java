//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年10月12日
//§4.3.2   java.util包中的工具类库
//【例4.3】日历
//Calendar类研究

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendars 
{
    //设置calendar为上月，年份自动变化，例如“2017年1月”的上月为“2016年12月”
    public static void previosMonth(Calendar calendar)
    {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
    }
    //设置calendar为下月，年份自动变化，例如，“2016年12月”的下月为“2017年1月”
    public static void nextMonth(Calendar calendar)
    {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
    }
    
    //设置calendar为昨天，月份、年份自动变化，例如，“2017年1月1日”的昨天为“2016年12月31日”
    public static void yestoday(Calendar calendar)
    {
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
    }
    //设置calendar为明天，月份、年份自动变化，例如，“2016年12月31日”的明天为“2017年1月1日”
    public static void tomorrow(Calendar calendar)
    {
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
    }
    
    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");//日期时间格式
        Calendar calendar = Calendar.getInstance();        //当前日期和时间
        System.out.println("今天是"+sdf.format(calendar.getTime()));  //返回日期

        int year =calendar.get(Calendar.YEAR);             //年
        calendar.set(year, 11, 31);
        System.out.println(sdf.format(calendar.getTime()));
        nextMonth(calendar);
        System.out.println("下月是"+sdf.format(calendar.getTime()));
        previosMonth(calendar); 
        System.out.println("上月是"+sdf.format(calendar.getTime()));

        tomorrow(calendar);
        System.out.println("明天是"+sdf.format(calendar.getTime()));
        yestoday(calendar);
        System.out.println("昨天是"+sdf.format(calendar.getTime()));
    }
}
/*
今天是2016年10月12日
2016年12月31日
下月是2017年01月31日
上月是2016年12月31日
明天是2017年01月01日
昨天是2016年12月31日
*/ 
//@author：Yeheya，2016-10-12