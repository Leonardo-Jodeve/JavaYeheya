//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//§4.3.2 java.util.Comparator比较器接口
//第5版未用

import java.util.*;
public class CompareDays implements java.util.Comparator<MyDate> 
{
    public int compare(MyDate d1, MyDate d2)               //返回实际相差的天数
    {
        return d1.daysBetween(d2);                         //返回实际相差的天数
    }
}
//@author：Yeheya，2017年2月7日