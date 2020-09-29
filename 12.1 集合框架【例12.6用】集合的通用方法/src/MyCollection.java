import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MyCollection 
{
    //将objs[]元素添加到coll集合。    //【课程设计12-13】 电话簿，调用。
    public static <T> void addAll(T[]objs, Collection<T> coll)
    { 
        if(objs!=null && objs.length>0)
            for(T obj:objs)
    	        coll.add(obj);
    }
    
    //【课程设计12-13】 电话簿，调用。？？
    //在coll集合中查找x对象，返回首次出现的对象，由比较器c指定比较规则
    public static <T> T search(Collection<T> coll, T x, Comparator<T> c)
    { 
        Iterator<T> it = coll.iterator();
        while(it.hasNext())                      //未找到且有后继元素时迭代
        {
            T obj = it.next();
            if(c.compare(obj,x)==0)              //由比较器c指定比较规则
                return obj;
        }
        return null;                             //未找到时返回null，此时比较了所有元素
    }
}
/*	
    public Friend2 search(Friend2 x, Comparator<Friend2> c)   //查找x对象，比较器c指定比较规则
    { 
        Friend2 find = null;
        Iterator<Friend2> it = this.iterator();
        while(find==null && it.hasNext())                //未找到且有后继元素时迭代
        {
            Friend2 f = it.next();
            if(c.compare(f,x)==0)                      //比较电话号码字符串
                find = f;
        }
        return find;                                   //未找到时返回null，此时比较了所有元素
    }
    /*    public Friend search(Friend x)                    //查找x对象，比较器c指定比较规则
    { 
        Friend find = null;
        Iterator<Friend> it = this.iterator();
        while(find==null && it.hasNext())                 //未找到且有后继元素时迭代
        {
            Friend f = it.next();
            if(f.compareTo(x)==0)                      //比较电话号码字符串
                find = f;
            else
                if(f.compareTo(x)>0)                      //比较电话号码字符串
            	    break;
        }
        return find;                                   //未找到时返回null
    }*/
	
//@author：Yeheya，2018年8月23日