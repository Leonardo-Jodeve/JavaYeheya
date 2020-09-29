//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月9日
//§12.3.4 树
//【例12.6】 Person对象信息管理，以树结构显示中国城市。
//（3） 按对象的指定成员变量排序

import java.lang.reflect.Field;
import java.util.Comparator;

//按T对象的fieldname成员变量比较对象大小的比较器类。默认fieldname实现Comparable接口
public class CompareField<T> implements Comparator<T>
{
    String fieldname;                                      //成员变量名

    public CompareField(String fieldname)                  //构造方法，fieldname指定T类的成员变量名
    {
        this.fieldname = fieldname;
    }
    
    public int compare(T t1, T t2)  //比较t1、t2对象大小，两对象按fieldname成员变量值比较大小
    {
        try
        {
            Field field=t1.getClass().getField(fieldname); //获得fieldname指定的成员变量
            return ((Comparable)field.get(t1)).compareTo((Comparable)field.get(t2));//比较两值大小
        }
        catch(NoSuchFieldException ex){}                  //无此成员变量异常
        catch(IllegalAccessException ex){}                //无效存取异常
        return -1;                                         //没有执行到，仅语法意义
    }
}
//@author：Yeheya，2018年2月9日