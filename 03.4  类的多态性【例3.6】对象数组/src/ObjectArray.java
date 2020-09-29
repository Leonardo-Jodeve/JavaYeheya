//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§3.4  类的多态性
//【例3.6】 对象数组的输出、查找和合并算法。

//为对象数组声明通用方法，Object[]类型参数
public class ObjectArray
{    
    //输出对象数组，空对象输出"null"；方法参数类型是Object[]，适用于所有对象数组
    public static void print(Object[] objs)
    {
        if(objs!=null)
            for(int i=0; i<objs.length; i++)
                System.out.println(objs[i]==null?"null":objs[i].toString());
                    //objs[i]可引用任何实例，objs[i].toString()方法运行时多态
        
        for(Object obj:objs)          //逐元循环，obj遍历objs数组中的每个元素
            if (obj!=null)             //obj可引用任何实例
                System.out.println(obj.toString());//toString()方法运行时多态
    }
//问:与之差别?  for (int i=0; i<objs.length && objs[i]!=null; i++)
    
    //第1版书程序，没有体会运行时多态概念，程序正确
    public static void print1(Object objs[])    //输出对象数组
    {
        for(int i=0; i<objs.length; i++)
            if(objs[i] instanceof Person)                //Person或Student对象
                if(!(objs[i] instanceof Student))        //区分父类与子类对象，是Person不是Student对象
                    System.out.println(((Person)objs[i]).toString()); //对象强制类型转换。没有道理，与参数类型不符
                else
                    System.out.println(((Student)objs[i]).toString());   
    }

    //返回合并的对象数组，通用功能
    public static Object[] concat(Object[] objs1, Object[] objs2)
    {
        if(objs1==null)  return objs2;
        if(objs2==null)  return objs1;
        Object[] result = new Object[objs1.length+objs2.length]; 
        int i=0, j=0;
        for(j=0; j<objs1.length; j++)
            result[i++] = objs1[j];             //对象引用赋值
        for(j=0; j<objs2.length; j++)
            result[i++] = objs2[j];
        return result;                           //返回对象数组引用
    }

    //输出objs对象数组中所有与key相等的元素，顺序查找算法；equals(Object)方法应用
    public static void searchPrintAll(Object[] objs, Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(objs[i]!=null && key.equals(objs[i]))   //equals()方法运行时多态，执行key对象的比较规则
//                  if (objs[i].equals(key))                  //equals()方法运行时多态，执行objs[i]的对象比较规则
                    System.out.println(objs[i].toString()); //toString()方法运行时多态
    }
    
    //【思考题3-6】 ② 增加以下方法，Object类型参数。
    public static Object[] copy(Object[] objs)  //返回复制的对象数组，通用功能；没有复制对象，用不了
    {
        if(objs==null)  return null;
        Object[] result = new Object[objs.length]; 
        for(int i=0; i<objs.length; i++)
            result[i] = objs[i];                //对象引用赋值
        return result;                           //返回对象数组引用
    }
    
    
    //顺序查找对象数组中首次出现的与key相等元素，若查找成功返回元素，否则返回null
    public static Object search(Object objs[], Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(key.equals(objs[i]))        //对象采用equals()方法比较是否相等，运行时多态
                    return objs[i];
        return null;                             //查找不成功
    }
    
    //【思考题3-6】例3.6思考题增加方法。
    //删除objs对象数组中所有与key匹配对象，使用顺序查找算法；equals()方法应用
    public static void removeAll(Object objs[], Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(key.equals(objs[i]))        //equals()方法运行时多态，执行key的对象比较规则
//                  if (objs[i].equals(key))    //equals()方法运行时多态，执行objs[i]的对象比较规则
                {
                    System.out.println("删除："+objs[i].toString()); //toString()方法运行时多态
                    objs[i]=null;
                }        
    }
}
//@author：Yeheya，2016-8-14，2017年7月26日