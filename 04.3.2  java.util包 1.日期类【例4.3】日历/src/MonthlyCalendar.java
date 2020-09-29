//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月18日
//§4.3.2   java.util包中的工具类库
//【例4.3】日历
//使用Java的3种表示日期和时间方式

import java.util.*;                                        //导入实用包
import java.text.SimpleDateFormat;                         //导入文本包中的简单日期格式类

public class MonthlyCalendar
{
    public static void main(String[] args)
    {
        String datestr="yyyy年MM月dd日E HH时mm分ss秒";     //a:上下午，hh:12小时制
        SimpleDateFormat datef = new SimpleDateFormat(datestr);//日期时间格式
        System.out.print("今天是"+datef.format(new Date()));   //当前日期和时间

        long now=System.currentTimeMillis();                //当前时间的毫秒数
        datef = new SimpleDateFormat("yyyy年MM月dd日E");     //日期格式
        System.out.println("，明天是"+datef.format(new Date(now+24*60*60*1000)));
        MonthlyCalendar.print(new GregorianCalendar());    //输出当月的月历
        
        //【思考题4-5】 ① 输出下月的日历。
        Calendar calendar = Calendar.getInstance();        //当前日期和时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);//设置下月月份，年份自动变化//set(int field, int value)设置field表示的域值
        MonthlyCalendar.print(calendar);                   //输出指定月份的日历
//        for(int i=0; i<10; i++)                  
//        {
//            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);//设置下月月份，年份自动变化，“2016年12月”的下月为“2017年1月”
//            MonthlyCalendar.print(calendar);               //输出指定月份的日历
//        }
    }
    
    public static void print(Calendar calendar)            //输出指定月份的日历
    {
        int year =calendar.get(Calendar.YEAR);             //年
        int month=calendar.get(Calendar.MONTH)+1;          //月
        calendar.set(year, month-1, 1);                    //设置为当月1日
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;   //当月1日是星期几

        System.out.println(year+"年"+month+"月的日历\n  日  一  二  三  四  五  六");
/*同样效果
        String str="日一二三四五六";
        for (int i=0; i<str.length(); i++)
            System.out.print(String.format("%4c", str.charAt(i)));
    	System.out.println();*/
        
        if(week>0)
            System.out.print(String.format("%"+4*week+"c", ' ')); //前导空格
        int days = MyDate.daysOfMonth(year, month);        //计算出当月的天数
        for(int i=1; i<=days; i++)                        //输出日历
        {
            System.out.print(String.format("%4d", i));
            if((week+i)%7==0)
                System.out.println();
        }
        System.out.println();
    }
}
/*
程序运行结果如下：
今天是2018年03月28日星期三 15时01分13秒，明天是2018年03月29日星期四
2018年3月的日历
  日  一  二  三  四  五  六
                   1   2   3
   4   5   6   7   8   9  10
  11  12  13  14  15  16  17
  18  19  20  21  22  23  24
  25  26  27  28  29  30  31

2018年4月的日历
  日  一  二  三  四  五  六
   1   2   3   4   5   6   7
   8   9  10  11  12  13  14
  15  16  17  18  19  20  21
  22  23  24  25  26  27  28
  29  30

2016年08月19日星期五 下午 05时59分15秒，明天是2016年08月20日星期六
2016年8月的日历
   日   一   二   三   四   五   六
       1   2   3   4   5   6
   7   8   9  10  11  12  13
  14  15  16  17  18  19  20
  21  22  23  24  25  26  27
  28  29  30  31
2016年9月的日历
   日   一   二   三   四   五   六
                   1   2   3
   4   5   6   7   8   9  10
  11  12  13  14  15  16  17
  18  19  20  21  22  23  24
  25  26  27  28  29  30

//求当月1日是星期几，也可
        int day=calendar.get(Calendar.DATE);          //日
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1; //星期几
        int first = (week - day%7 +1+7)%7;       //当月1日是星期几
        
2016年10月12日星期三 下午 02时45分32秒，明天是2016年10月13日星期四
2016年10月的日历
  日  一  二  三  四  五  六
                           1
   2   3   4   5   6   7   8
   9  10  11  12  13  14  15
  16  17  18  19  20  21  22
  23  24  25  26  27  28  29
  30  31

今天是2018年10月09日星期二 10时52分47秒，明天是2018年10月10日星期三
2018年10月的日历
  日  一  二  三  四  五  六
       1   2   3   4   5   6
   7   8   9  10  11  12  13
  14  15  16  17  18  19  20
  21  22  23  24  25  26  27
  28  29  30  31
  
*/
//@author：Yeheya，2016年08月19日，2016年10月12日，2018年03月28日，2018年10月9日