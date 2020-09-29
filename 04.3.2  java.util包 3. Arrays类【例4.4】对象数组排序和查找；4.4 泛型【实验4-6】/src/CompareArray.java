//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.3.2  java.util包
//3. Arrays类
//【例4.4】  泛型对象数组的排序和二分法查找算法实现。
//§4.4 泛型
//【实验题4-6】泛类对象数组的查找等算法实现。

import java.util.Comparator;                               //比较器接口

public class CompareArray                        //数组类，元素可比较大小，实现排序和二分法查找算法
{
    //对value对象数组排序，泛型T必须实现Comparable<? super T>接口；
    //asc指定排序次序，取值true（升序，默认）或false（降序）。直接插入排序算法
    public static <T extends java.lang.Comparable<? super T>> void sort(T[] value) 
    {   sort(value, true);                       //对象数组排序，默认升序
    }
    public static <T extends java.lang.Comparable<? super T>> void sort(T[] value, boolean asc)
    {
//        System.out.println("直接插入排序");
        for(int i=1; i<value.length; i++)        //n-1趟扫描
        {
            T x = value[i];                      //每趟将value[i]插入到前面排序子序列中
            int j=i-1;
            while(j>=0 && (asc ? x.compareTo(value[j])<0 : x.compareTo(value[j])>0))
                value[j+1] = value[j--];         //将前面较大/小元素向后移动
            value[j+1] = x;                      //x值到达插入位置
//            System.out.print("第"+i+"趟: ");
//            print(value);                      //调用print(Object)输出排序中间结果，可省略
        }
    }

    //对象数组排序（升序），由比较器对象c提供比较T对象大小的方法。冒泡排序算法，第5版没写
    public static <T> void sort(T[] value, java.util.Comparator<? super T> c)
    {
//        System.out.println("冒泡排序");
        boolean exchange=true;                             //是否交换的标记
        for(int i=1; i<value.length && exchange; i++)      //有交换时再进行下一趟，最多n-1趟
        {
            exchange=false;                                //假定元素未交换 
            for(int j=0; j<value.length-i; j++)            //一趟比较、交换
                if(c.compare(value[j], value[j+1])>0)      //反序时，交换
                {
                    T temp = value[j];
                    value[j] = value[j+1];
                    value[j+1] = temp;
                    exchange=true;                         //有交换 
                }
//            System.out.print("第"+i+"趟: ");
//            print(value);                                  //调用print(Object)输出排序中间结果，可省略
        }
    }

    //第5版没写
    //在value排序（升序）对象数组从begin到end范围，二分法查找关键字为key的元素，
    //若查找成功返回下标，否则返回-1；若begin、end省略，表示0～value.length-1；
    //泛型T必须实现Comparable<? super T>接口。
    //二分法查找算法只能查找一个元素，不能查找所有元素？？
    public static <T extends Comparable<? super T>> int binarySearch(T[] value, T key)
    {
        return binarySearch(value, 0, value.length-1, key);
    }  
    public static <T extends Comparable<? super T>> int binarySearch(T[] value, int begin, int end, T key)
    {
        if(key!=null)
        {
            while(begin<=end)                               //边界有效
            {   int mid = (begin+end)/2;                    //中间位置，当前比较元素位置
//                System.out.print(value[mid]+"? ");
                if(key.compareTo(value[mid])==0)            //比较对象大小，若相等，
                    return mid;                             //则查找成功；
                if(key.compareTo(value[mid])<0)             //若key对象小，
                    end = mid-1;                            //则查找范围缩小到前半段，
                else begin = mid+1;                         //否则查找范围缩小到后半段
            }
        }
        return -1;                                          //查找不成功
    }
    
    
    //第5版【例4.4】泛类对象数组的二分法查找算法实现。
    //在value排序（升序）对象数组从begin到end范围，二分法查找关键字为key的元素，
    //若查找成功，则返回下标，否则返回-1；若begin、end省略，表示0～value.length-1；
    //由比较器对象c提供比较对象大小的方法。
    public static <T> int binarySearch(T[] value, T key, java.util.Comparator<? super T> c)
    {
        return binarySearch(value, 0, value.length-1, key, c);
    }  
    public static <T> int binarySearch(T[] value, int begin, int end, T key, java.util.Comparator<? super T> c)
    {
        if(key!=null)
            while(begin<=end)                              //边界有效
            {   int mid = (begin+end)/2;                    //中间位置，当前比较元素位置
//                System.out.print(value[mid].toString()+"? ");
                if(c.compare(key,value[mid])==0)           //对象比较大小，若相等，
                    return mid;                             //则查找成功；
                if(c.compare(key,value[mid])<0)            //若key对象小，
                    end = mid-1;                            //则查找范围缩小到前半段
                else begin = mid+1;                         //否则查找范围缩小到后半段
            }
        return -1;                                          //查找不成功
    }
    
    public static void print(Object[] value)                //输出对象数组元素，同例3.6
    {
        if(value!=null)
            for(int i=0; i<value.length; i++)
                if(value[i]!=null)
                    System.out.print(value[i].toString()+"  ");
        System.out.println();
    }
    public static void println(Object[] value)             //输出对象数组元素
    {
        if(value!=null)
            for (int i=0; i<value.length; i++)
                System.out.println(value[i].toString());
        System.out.println();
    }
    
    //输出value对象数组中所有与key相等的元素，顺序查找算法
    public static <T extends Comparable<? super T>> void printAll(T[] value, T key)
    {
        if(value!=null && key!=null)
            for(int i=0; i<value.length && key.compareTo(value[i])>=0; i++)
                if(key.compareTo(value[i])==0)            //执行key对象的比较规则
                    System.out.println(value[i].toString());
    }    

    //输出value对象数组中所有与key相等的元素，顺序查找算法
    //对象数组排序方法，由比较器对象c提供比较T对象大小的方法。
    public static <T> void printAll(T[] value, T key, java.util.Comparator<? super T> c)
    {
        if(value!=null && key!=null)
            for(int i=0; i<value.length && c.compare(key,value[i])>=0; i++)
                if(c.compare(key,value[i])==0)            //执行key对象的比较规则
                    System.out.println(value[i].toString());
    }
    
//    public static <T extends Comparable<? super T>> int max(T[] value) 	//返回数组元素最大值的下标
//    public static <T extends Comparable<? super T>> int min(T[] value) 	//返回数组元素最小值的下标
    
    //判断value对象数组是否已按升序排序，若已排序返回true，否则返回false
//    public static <T> boolean isSorted(Comparable<T>[] value)         //不行，因为，不能compareTo(Comparable<T>)，应该compareTo(T)。
    public static <T extends Comparable<? super T>> boolean isSorted(T[] value)
    {
        for(int i=0; i<value.length-1; i++)
            if(value[i].compareTo(value[i+1])>0)
                return false;
        return true;
    }

    //将元素x插入到value排序对象数组前n个元素中，插入位置由x值大小决定
    public static<T extends Comparable<? super T>> void insert(T[] value, int n, T x)
    {
        int i=0;
        while(i<n && value[i].compareTo(x)<=0)            //顺序查找x的插入位置
            i++;
        for(int j=n-1; j>=i; j--)
            value[j+1]=value[j];                           //元素向后移动
        value[i]=x;                                        //i是x的插入位置
    }
}    
    //设X、Y数组已按升序排序，merge()方法将两者合并成一个排序数组返回，一次归序算法
/*    public static <T extends java.lang.Comparable<? super T>> 
        T[] merge(T[] X, T[] Y)
    {
        int i=0, j=0, k=0;
        T[] Z=new T[X.length+Y.length];       //语法错，T类没有默认拷贝构造方法
        while(i<X.length && j<Y.length)                   //将X中两个相邻子序列归并到Y中
            if(X[i]<Y[j])                                 //较小值复制到Y中
                Z[k++]=X[i++];
            else
                Z[k++]=Y[j++];

        while(i<X.length)                                 //将前一个子序列剩余元素复制到Y中
            Z[k++]=X[i++];
        while(j<Y.length)                                 //将后一个子序列剩余元素复制到Y中
            Z[k++]=Y[j++];
        return Z;
    }
 
        
    //？？没用到，不行，语义不清楚
    //在table对象数组中查找cobj//有序对象数组的顺序查找算法，若查找成功返回元素下标，否则返回-1
    //顺序查找已排序（升序）对象数组//若查找成功返回元素下标，否则返回-1
    public static int indexOf(Comparable[] table, Comparable cobj)
    {
        if(table!=null && cobj!=null)
            for(int i=0; i<table.length && cobj.compareTo(table[i])<=0; i++) //依次比较
                if(cobj.compareTo(table[i])==0)  //相等
                    return i;                    //找到
        return -1;                               //没找到
    }    

*/