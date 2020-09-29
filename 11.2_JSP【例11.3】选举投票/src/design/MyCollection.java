//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年4月5日
//§11.2 JSP
//【例11.3】选举投票。
//① 创建design包，其中存放包含通用方法的类，供JSP文档调用。
 
package design;                                  //声明包
import java.util.*;

public class MyCollection                        //为集合类增加通用方法
{
    //返回集合所有元素描述字符串，使用迭代器遍历集合，通用方法
    public static <T> String toString(Collection<T> coll)
    {  
        Iterator<T> it = coll.iterator();        //返回迭代器，集合元素类型是T
        String str =""; 
        while(it.hasNext())                      //若有后继元素，遍历集合
           str+=it.next()+"<br>";
        return str;
    }
}
//@author：Yeheya，2018年4月7日