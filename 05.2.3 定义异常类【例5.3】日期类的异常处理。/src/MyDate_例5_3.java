//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§3.2  类的封装性
//【例3.2】  封装的日期类。//【思考题3-3】 MyDate类增加功能。
//§5.2.3 定义异常类
//【例5.3】自定义异常类，日期类的异常处理。

class MyDate_例5_3 
{

    //main()方法没有捕获并处理异常，再抛出给Java虚拟机
    public static void main(String args[]) throws NumberFormatException, DateFormatException
    {
        MyDate date = new MyDate("2016年2月29日");
        MyDate today = MyDate.today();           //当前日期
        System.out.println(today+"与"+date+"相距"+today.yearsBetween(date)+"年，相距"+
                today.monthsBetween(date)+"月，相距"+today.daysBetween(date)+"天");
        int n=30;
        System.out.println(today+"之后"+n+"天的日期是"+today.daysAfter(n)+"");
//        n=431+366;
        System.out.println(today+"之前"+n+"天的日期是"+today.daysBefore(n)+"");
        
        //以下抛出异常
        new MyDate("2017年2月29日");  //抛出DateFormatException: 2017年2月29日，日期错误
        new MyDate("2017年2x月29日");   //抛出java.lang.NumberFormatException: For input string: "2x"
    }
/*
    //main()方法捕获并处理异常，显示结果同上
    public static void main(String args[])
    {
        try
        {
            new MyDate("2017年2x月29日"); 
        }
        catch (NumberFormatException ex)         //捕获数值格式异常
        {
            System.out.println(ex.toString());   //显示包含类名的异常信息
        }
        catch (DateFormatException ex)           //捕获日期格式异常
        {
            System.out.println(ex.toString());   //显示包含类名的异常信息
        }
        catch (Exception ex)                     //捕获其他所有异常
        {
            ex.printStackTrace();
        }
    }*/
}
/*
程序运行结果如下：
2017年02月07日与2016年02月29日相距1年，相距12月，相距344天
2017年02月07日之后30天的日期是2017年03月09日
2017年02月07日之前30天的日期是2017年01月08日
Exception in thread "main" DateFormatException: 2017年2月29日，日期错误
    at MyDate.set(MyDate.java:354)
    at MyDate.<init>(MyDate.java:369)
    at MyDate_例5_2.main(MyDate_例5_2.java:20)


    2008年8月8日与2009年10月5日相距-1年，相距-14月，相距-423天
    2010年3月7日与2000年1月1日相距10年，相距122月，相距3718天
    2010年1月1日与2000年1月1日相距10年，相距120月，相距3653天
    2010年8月17日与1994年8月17日相距16年，相距192月，相距5844天

    2000年1月1日之后366天的日期是2001年1月1日
    1994年8月17日之后5844天的日期是2010年8月17日

    2010年3月7日之前797天的日期是2007年12月31日
    2010年8月17日之前5844天的日期是1994年8月17日

    2013年02月17日与1994年08月17日相距19年，相距222月，相距6759天
    2013年02月17日之后30天的日期是2013年03月19日
    1994年08月17日之前30天的日期是1994年07月18日
    Exception in thread "main" DateFormatException: 2013年2月29日，日期错误
        at MyDate.set(MyDate.java:352)
        at MyDate.<init>(MyDate.java:362)
        at MyDate_ex.main(MyDate.java:398)

*/
//@author：Yeheya，2016-8-11，2017年2月8日