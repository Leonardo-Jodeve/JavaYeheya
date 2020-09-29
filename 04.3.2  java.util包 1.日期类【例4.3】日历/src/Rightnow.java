//《Java程序设计实用教程（第1版）》 作者：叶核亚，2014年11月20日
//【例4.5】获得系统当前日期和时间，并按指定格式输出。

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Rightnow
{
    public static void main(String args[])
    {
        Date today = new Date();                 //当前日期和时间
        System.out.println("当前时间是 "+today.toString());

        Date time2 = new Date(today.getTime()+10000);
        System.out.println("time2 ="+time2.toString());
        
        long time3=(time2.getTime() - today.getTime())/1000;
        System.out.println("时间差是 "+time3+"秒");
        
        time3=-50381;
        
/*        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒 a EEEEE");    //定义日期格式
        System.out.println("      即 "+sdf.format(today));

        long lg=System.currentTimeMillis();      //当前时间的毫秒数        
        Date tomorrow = new Date(lg+24*60*60*1000);
        System.out.println("    明天是 "+sdf.format(tomorrow));
        
        
        Calendar now = Calendar.getInstance();
        int year =now.get(Calendar.YEAR);                  //年份
        int month=now.get(Calendar.MONTH)+1;               //月份
        int day = now.get(Calendar.DATE);                  //日期
        System.out.print("今天是 "+year+"年"+month+"月"+day+"日");
        int week = now.get(Calendar.DAY_OF_WEEK);          //星期
        switch(week)
        {
            case 1: System.out.println("  星期日");break;
            case 2: System.out.println("  星期一");break;
            case 3: System.out.println("  星期二");break;
            case 4: System.out.println("  星期三");break;
            case 5: System.out.println("  星期四");break;
            case 6: System.out.println("  星期五");break;
            case 7: System.out.println("  星期六");break;
        }*/
    }
}

/*

当前时间=Sun Feb 02 16:17:01 GMT+08:00 2003
      即 2003年02月02日04时17分01秒 PM Sunday
    明天=2003年02月03日04时17分01秒 PM Monday
今天=2003年2月2日  星期日

*/