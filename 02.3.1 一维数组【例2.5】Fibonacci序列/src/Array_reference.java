//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.3.1  一维数组

public class Array_reference
{
    static void print(int x[])                   //数组作为参数，不用给出数组长度
    {
        for(int i=0; i<x.length; i++)            //输出一维数组
            System.out.print(" "+x[i]);
        System.out.println();
    }

    public static void main(String args[]) 
    {
        int x[]={1,2,3,4,5};
        int y[]=null;
        System.out.println("x==null  "+(x==null));
        System.out.println("y==null  "+(y==null));

        y=x;                                     //引用赋值
        System.out.println("x==y  "+(x==y));

        y[0]=100;

        print(x);
         
        int n=x.length;
        y=new int [n];
        for(int i=0;i<x.length;i++)
            y[n-i-1] = x[i];

        print(y);
    }
}

/*
程序运行结果如下：
x==null false
y==null true
x==y true
 100 2 3 4 5
 5 4 3 2 100
 
*/

/* 
程序错误：
int y[];
y[0]=100;          //编译错，未初始化

y=100;             //编译错incompatible types

*/
//@author：Yeheya，2016-6-11