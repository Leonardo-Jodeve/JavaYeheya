//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§2.5 字符串
//图2.21 字符串的引用模型。

public class String_equals
{
    public static void main(String args[])
    {
        String s1="abc", s2=s1;
        System.out.println("s1="+s1+"，s2="+s2+"，s1==s2？"+(s1==s2));
        
        s2="abc";
        System.out.println("s1="+s1+"，s2="+s2+"，s1==s2？"+(s1==s2));
        
        s2+="Hello";
        System.out.println("s1="+s1+"，s2="+s2+"，s1==s2？"+(s1==s2));

        String s3=s2.substring(0,3);
        System.out.println("s1="+s1+"，s2="+s2+"，s3="+s3+"，s3==s1？"+(s3==s1)+"，s3.equals(s1)？"+(s3.equals(s1)));

        s3=s1.substring(0,3);
        System.out.println("s1="+s1+"，s2="+s2+"，s3="+s3+"，s3==s1？"+(s3==s1)+"，s3.equals(s1)？"+(s3.equals(s1)));

        String s4="abc";
        System.out.println("s4="+s4+"，s4==s1？"+(s4==s1)+"，s4==s3？"+(s4==s3));
        
        swap(s1, s2);
        System.out.println("swap(s1,s2)，s1="+s1+"，s2="+s2);
        
        String table[]={"abc", "xyz"};
        swap(table, 0, 1);
        System.out.println("swap(table,0,1)，table："+table[0]+","+table[1]);
        
    }
    public static void swap(String x, String y)              //交换字符串x、y，未实现
    {
        String temp = x;
        x = y;
        y = temp;
        System.out.println("x="+x+"，y="+y);
    }
    
    public static void swap(String table[], int i, int j)    //交换table[i]、table[j]值
    {
        if (table!=null && i>=0 && i<table.length && j>=0 && j<table.length && i!=j) //避免下标越界
        {
            String temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }
}

/*
程序运行结果如下：
s1=abc，s2=abc，s1==s2？true
s1=abc，s2=abc，s1==s2？true
s1=abc，s2=abcHello，s1==s2？false
s1=abc，s2=abcHello，s3=abc，s3==s1？false，s3.equals(s1)？true
s1=abc，s2=abcHello，s3=abc，s3==s1？true，s3.equals(s1)？true
s4=abc，s4==s1？true，s4==s3？false
x=abcHello，y=abc
swap(s1,s2)，s1=abc，s2=abcHello
swap(table,0,1)，table：xyz,abc
*/
//@author：Yeheya，2016-7-25