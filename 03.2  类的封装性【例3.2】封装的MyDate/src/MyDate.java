//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§3.2  类的封装性
//【例3.2】  封装的日期类。
//虽然程序设计语言中没有使用3个整数作为日期的成员变量这样的声明，
//本例为样做的目的是，一，为了演示日期计算的算法，
//二，每种程序设计语言中日期功能总是不够，转换成年月日整数虽然麻烦，却是共同可用的。
//【思考题3-3】 MyDate类增加功能。
//java.util.Date和Calendar功能不够，所需的很多方法仍然需要通过年月日进行比较，所以写在MyDate类中
//为第6章制作多种日期 组件做准备
//【例5.3】 日期类的异常处理。

import java.util.*;
import java.text.SimpleDateFormat;

//日期类，公有，与源程序文件同名//序列化，例8.3
public class MyDate implements Comparable<MyDate>, java.io.Serializable
{ 
    private int year,month,day;                  //年月日，私有成员变量
    private static int thisYear=2018;            //当前年份，私有静态成员变量
    static                                       //静态成员变量初始化
    {
        thisYear=2018;
//        thisYear = java.util.Calendar.getInstance().get(Calendar.YEAR); //获得当前日期对象中的年份值，4.3.2节
//        thisYear = new java.util.GregorianCalendar().get(Calendar.YEAR); //获得当前日期对象中的年份值，4.3.2节
    } 
    public MyDate()                                        //无参数构造方法，指定缺省日期，重载
    {
        this(1970,1,1);                                    //调用本类已声明的其他构造方法 
    } 
  
    //构造方法，指定日期。//第3章例3.2，没有抛出异常
    //构造方法，若参数不能构造日期，本方法无法处理异常，则抛出日期格式异常。//第5章例5.2，抛出异常 
    public MyDate(int year, int month, int day) throws DateFormatException
    {
//      super();                                         //默认调用Object()
    	this.set(year, month, day);                        //例3.2，调用本类的成员方法。//例5.2，该方法声明抛出日期格式异常
    } 

    //拷贝构造方法，日期同参数，重载。
    public MyDate(MyDate date) throws DateFormatException
    {
        this.set(date);
    }
  
/*    public void set(int year, int month, int day)        //设置日期值，算法不全，改进见5.2.3节
    {
        this.year = year;                                  //this.year指当前对象的成员变量，year指参数
        this.month = (month>=1 && month<=12) ? month : 1;  //this引用不能省略
        this.day = (day>=1 && day<=31) ? day : 1;
    } */
    
    public void set(MyDate date)                           //设置日期值，重载
    {
        this.set(date.year, date.month, date.day);         //调用同名成员方法，不能使用this()
    }
    //5.2.3节，例5.2 此方法不需要抛出日期格式异常//throws DateFormatException
    //因为，date参数是日期，没有错误。

    public int getYear()                                   //获得年份
    {
        return this.year;
    }
    public int getMonth()                                  //获得月份
    {
        return this.month;
    }
    public int getDay()                                    //获得当月日期
    {
        return this.day;
    }
  
    public String toString()                               //中文日期格式字符串，2位月日
    {
        return this.year+"年"+String.format("%02d", this.month)+"月"+
               String.format("%02d", this.day)+"日";//+this.toWeekString();
    }

    public static int getThisYear()                        //获得今年年份，静态方法
    {
        return thisYear;                                   //访问静态成员变量
    }

    public static boolean isLeapYear(int year)             //判断指定年份是否闰年，静态方法
    {
        return year%400==0 || year%100!=0 && year%4==0;
    }
    public boolean isLeapYear()                            //判断当前日期的年份是否闰年，重载
    {
        return isLeapYear(this.year);                      //调用静态方法
    }        

    //比较由this和date参数引用的实例值是否相等。
    //若this与date引用同一个实例，则相等；若它们分别引用两个
    //实例，则分别比较它们的各成员变量值是否对应相等
    public boolean equals(MyDate date)
    {
//        System.out.println(b+"，执行equals(MyDate date)方法");
        //this指代调用当前方法的对象，this.year等访问当前对象的成员变量（此3处this可省略）
        return this==date ||           //若this与date引用同一个实例，则相等
               date!=null && this.year==date.year && this.month==date.month && this.day==date.day;
               //若this与date分别引用两个实例，则分别比较它们的各成员变量值是否对应相等
    } 
    
    public static int daysOfMonth(int year, int month)     //返回指定年月的天数，静态方法
    {
        switch(month)                           //计算每月的天数
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:  return 31; 
            case 4: case 6: case 9: case 11:  return 30;
            case 2:  return MyDate.isLeapYear(year) ? 29 : 28;
            default: return 0;
        }
    }
    public int daysOfMonth()                     //返回当月天数
    {
        return daysOfMonth(this.year, this.month);
    } 

    //第4版
    public void tomorrow4()                      //将this引用实例的日期改为之后一天日期，没有返回值
    {
        this.day++;
        if(this.day>this.daysOfMonth())
        {
            this.day=1;
            this.month++;
            if(this.month>12)
            {
                this.month=1;
                this.year++;
            }
        }
    }   

    //第5版，采用%
    public void tomorrow()                       //将this引用实例的日期改为之后一天日期，没有返回值
    {
        this.day = this.day%this.daysOfMonth()+1;
        if(this.day==1)
        {
            this.month = this.month%12+1;        //下月
            if(this.month==1)                   //12月的下月是明年1月
                this.year++;
        }
    }    

    //第4版
    public MyDate yestoday4()                    //返回当前日期的前一天日期
    {
        MyDate date = new MyDate(this);          //执行拷贝构造方法，创建实例，没有改变this
        date.day--;
        if(date.day==0)
        {
            date.month--;
            if(date.month==0)
            {
                date.month=12;
                date.year--;
            }
            date.day = daysOfMonth(date.year, date.month);
        }
        return date;                             //返回对象date引用的实例
    }   
    
    //第5版，采用%
    public MyDate yestoday()                     //返回当前日期的前一天日期
    {
        MyDate date = new MyDate(this);          //执行拷贝构造方法，创建实例，没有改变this
        date.day--;
        if(date.day==0)
        {
            this.month = (this.month-2+12)%12+1; //上月
            if(this.month==12)                   //1月的上月是去年12月
                this.year--;
//            date.previousMonth();
        }
        return date;                             //返回对象date引用的实例
    }   
    
    //例3.2  main()

    
    //3.4.4   多态的方法实现
    //【思考题3-5】【试3.17】
    public boolean equals(Object obj)            //覆盖Object类的equals()方法
    {
        if(this==obj)                           //this指代调用当前方法的对象
            return true;
        if(obj instanceof MyDate)               //判断当前对象是否属于MyDate类
        {
            MyDate date = (MyDate)obj;           //类型强制转换
            return this.year==date.year && this.month==date.month && this.day==date.day;
        }
        return false;
    }    
    
    
    //《Java程序设计实用教程（第5版）习题解答与实验指导》
    //【习3.3】MyDate类增加daysAfter(int n)方法。//第5版改进，使用%？？
    public MyDate daysAfter(int n)                         //返回当前日期之后n天的日期
    {
        if(n<0)
            return daysBefore(-n);                         //算法类似，方法声明省略
    
        MyDate date = new MyDate(this);                    //执行拷贝构造方法，复制当前对象
        while(n>0)
            if(date.day+n<=date.daysOfMonth())            //当月
            {
                date.day+=n;
                n=0;
            }
            else                                           //当年
            {
                n-=date.daysOfMonth()-date.day;
                date.day=0;
                date.month++;
                if(date.month>12)
                {
                    date.month=1;
                    date.year++;
                    while(n>365)                           //按年计算
                    {
                        n-= date.isLeapYear() ? 366 : 365;
                        date.year++;
                    }
                }
            }
//            System.out.println("n="+n);
        return date;
    }
    
    //《Java程序设计实用教程（第5版）试题库》
    //【思考题3-3】MyDate类增加的方法。
    public static int daysOfYear(MyDate date)              //返回date日期当年的累计天数
    {
        int sum=0;
        int days[]={31,28,31,30,31,30,31,31,30,31,30,31};  //平年每月的天数
        for(int i=0; i<date.month-1; i++)
            sum+=days[i];
        if(date.isLeapYear() && date.month>2)              //闰年2月多一天
            sum++;
        sum+=date.day;                                     //当月的天数
        return sum;
    }    
    
    public int daysOfYear()                                //返回当前日期当年的累计天数
    {
        return MyDate.daysOfYear(this);
    }
    public int getWeek()                             	   //返回当前日期对应的星期几，范围为0～6
    {
        int total = year-1980+(year-1980+3)/4;             //求平(闰)年累计的总天数
        total += this.daysOfYear();                        //加当年的累计天数
        return (1+total) % 7;                              //求得星期几，起始日 1979-12-31是星期一
    }//？？Java日期起点是1970-1-1
    public String toWeekString()                       	   //返回当前日期对应星期几的中文字符串
    {
        int week =this.getWeek();                          //求得星期几
        return "星期"+"日一二三四五六".substring(week, week+1);
    }
    
    public MyDate daysBefore(int n)                       //返回当前日期之前n天的日期
    {
        if(n<0)
            return daysAfter(-n);
    
        MyDate date = new MyDate(this); 
        while(n>0)
            if(date.day-n>0)                              //当月
            {
                date.day-=n;
                n=0;
    		}
            else                                           //当年
            {
                n-=date.day;
                date.month--;
                if(date.month==0)
                {
                    date.month=12;
                    date.year--;
                    while(n>365)                          //按年计算
                    {
                    	n-= date.isLeapYear() ? 366 : 365;
                    	date.year--;
                    }
                }
                date.day=date.daysOfMonth();
            }
//           System.out.println("n="+n);
        return date;
    }
    
    //第4章 4.3.1 java.lang.Comparable可比较接口
    public int compareTo(MyDate date)                         //约定比较日期大小的规则，返回-1、0、1
    {
        if(this==date || date!=null && this.year==date.year && this.month==date.month && this.day==date.day)
            return 0;
        return (this.year>date.year || this.year==date.year && this.month>date.month
            || this.year==date.year && this.month==date.month && this.day>date.day) ? 1 : -1; 
    }
    
    //试题4.4，以下多个方法约定多种比较两个日期大小的规则
    public int yearsBetween(MyDate date)                   //返回this与date日期相距的年数
    {
        return this.year - date.year;
    }
    public int monthsBetween(MyDate date)                  //返回this与date日期相距的月数
    {
        return yearsBetween(date)*12+this.month-date.month;
    }

    public static int daysBetween(MyDate d1, MyDate d2)    //返回d1与d2日期相距的天数
    {
        if(d1.compareTo(d2)==1)
            return daysBetween(d2, d1)*-1;                 //递归调用一次，交换参数次序
        
        int sum=0;
        if(d1.year == d2.year)                             //同年
        {
            sum=d1.daysOfMonth() - d1.day;
            for(int m=d1.month+1; m<d2.month; m++)         //满足d1.month<d2.month条件
                sum+=daysOfMonth(d1.year, m);
            sum+=d2.day;
        }
        else                                               //满足d1.year < date.year条件
        {
            sum=(isLeapYear(d1.year) ? 366 : 365)-daysOfYear(d1);    //加d1当年剩余的天数
            for(int y=d1.year+1; y<d2.year; y++)          //计算d1+1～d2-1年的累计天数
                sum+=isLeapYear(y) ? 366 : 365;
            sum+=daysOfYear(d2);                           //加d2当年的累计天数
        }
        return sum*-1;
    }
    public int daysBetween(MyDate date)                    //返回this与date日期相距的天数
    {
        return daysBetween(this, date);      
    }
    
    //第4章 4.3.2 java.util日期类//第5版教材没写，课件有答
    public static MyDate today()                           //返回当天日期
    {
        Calendar now = java.util.Calendar.getInstance();   //创建Calendar实例，获得当天日期和时间
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DATE);                  //DAY_OF_MONTH与DATE同义
        return new MyDate(year, month, day);
    } 
    
    
    //第5章，异常处理//§5.2.3 定义异常类
    //设置日期，若参数不能构造日期，则抛出日期格式异常
    public void set(int year, int month, int day) throws DateFormatException
    {
        String str = year+"年"+month+"月"+day+"日，";
        if(year<=-2000 || year>2500)
            throw new DateFormatException(str+"年份不合适，有效年份为-2000～2500。");
        if(month<1 || month>12)
            throw new DateFormatException(str+"月份错误");
        if(day<1 || day>MyDate.daysOfMonth(year, month))
            throw new DateFormatException(str+"日期错误");
        this.year = year;
        this.month = month;
        this.day = day;
    }   

    //构造方法，由datestr字符串构造日期，默认日期字符串格式为"yyyy年MM月dd日"；
    //若datestr中年月日子串不能转换成整数，则抛出数值格式异常；若不能构造日期，则抛出日期格式异常
    public MyDate(String datestr) throws NumberFormatException, DateFormatException
    {
        if(datestr.isEmpty())
            throw new DateFormatException("空串，日期错误");
        
        int i=datestr.indexOf('年'), j=datestr.indexOf('月',i), k=datestr.indexOf('日',j);
                                       //在datestr串中从i开始查找指定字符，汉字字符长度为1
        int year = Integer.parseInt(datestr.substring(0,i));     //年
        int month= Integer.parseInt(datestr.substring(i+1,j));   //月
        int day  = Integer.parseInt(datestr.substring(j+1,k));   //日
        this.set(year, month, day);    //若参数不能构造日期，则抛出日期格式异常
    } 
}
/*
    //第5版不要了
    //修改this为下月同日。若day超出，取最后一天，如“12月31日”的上月是“11月30日”，
    //例6.6（计算贷款）思考题，用不起来
    public void nextMonth()
    {
        this.month = this.month%12+1;            //下月
        if (this.month==1)                       //12月的下月是明年1月
            this.year++;
        if (this.day>this.daysOfMonth())         //若day超出，则取最后一天，例6.6用
            this.day=this.daysOfMonth();
    }
//  public static void nextMonth(int year, int month)      //下月，不行，无法返回两个整数
    //修改this为上月同日。若day超出，取最后一天，如“10月31日”的上月是“9月30日”
    public void previousMonth()
    {
        this.month = (this.month-2+12)%12+1;     //上月
        if (this.month==12)                      //1月的上月是去年12月
            this.year--;
        if (this.day>this.daysOfMonth())         //若day超出，则取最后一天，例6.6用
            this.day=this.daysOfMonth();
    }

    //将字符串str转换成日期，默认日期字符串格式为"yyyy年MM月dd日"，月日也可1位
    public static MyDate parseDate(String str) throws NumberFormatException, DateFormatException
    {
        return new MyDate(str);
    }//此方法没有作用，因为，结果创建实例，同上构造方法。第5版教材删除
    
    //实验5，以下方法没有用了。
    public MyDate(String year, String month, String day) throws NumberFormatException, DateFormatException //构造方法
    {
         this.set(year, month, day);
    }
    public void set(String year, String month, String day) throws NumberFormatException, DateFormatException //设置日期
    {
        this.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)); 
    }
 
    //第4版实验5-1，第5版不要了
//public MyDate(String s, String format) throws NumberFormatException,DateFormatException      //format指定日期格式

/*
程序设计说明如下。
1、声明了有参数的构造方法后，没有重载MyDate()时，原先无参数的构造方法则无效了。
        MyDate d1 = new MyDate();             //编译错,cannot find symbol symbol  : constructor MyDate()

    public void set(MyDate date)              //公有的成员方法，设置日期值,重载
    {
        this(date.year, date.month, date.day);   //编译错,不能使用this(),call to this must be first statement in constructor
    } 

2、没有覆盖toString()时不行，输出如下：
date is Data2@757aef
date is Data2@d9f9c3等

3、没有覆盖equals()时不行，输出如下：
aday4.equals(aday1)  false       //结果错，两个对象的值相同
aday3.equals(aday2)  false       //结果错，由拷贝构造方法创建的另一个对象 

只有两个对象引用同一个对象时，结果才对。例如，
        MyDate aday5 = aday2;
aday5.equals(aday2)  false       //结果正确，两个对象引用同一个实例

4、声明以下，不行，因为Calendar中set(year,month,day)等很多方法声明为final，不能被覆盖。
public class CHNDate extends Calendar

5、可以声明如下，作为成员变量。
    private GregorianCalendar calendar;

*/
//@author：Yeheya，2016-8-8，2017年2月7日