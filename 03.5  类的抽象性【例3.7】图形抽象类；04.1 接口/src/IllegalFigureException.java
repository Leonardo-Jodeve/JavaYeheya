//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日

//无效图形异常类，继承无效参数异常类
public class IllegalFigureException extends IllegalArgumentException
{
    public IllegalFigureException(String s)
    {
        super(s);
    }
    public IllegalFigureException()
    {
        super();
    }
}
//@author：Yeheya，2015-7-8