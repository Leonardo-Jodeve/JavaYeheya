//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月26日
//§5.2.3 定义异常类
//【例5.2】 定义日期格式异常类。

//日期格式异常类，继承无效参数异常类，只需要声明构造方法，作用是声明异常类名
//public class DateFormatException extends Exception              //必须捕获
public class DateFormatException extends IllegalArgumentException//调用者可以不捕获，由JVM处理
{
    public DateFormatException(String str)
    {
        super(str);
    }
    public DateFormatException()
    {
        super();
    }
}
//@author：Yeheya，2016-8-8，2017年2月7日