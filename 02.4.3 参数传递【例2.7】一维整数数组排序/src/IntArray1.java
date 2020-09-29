//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月22日
//§2.4.3  参数传递
//1.  实际参数向形式参数传递原则
//【例2.7】一维整数数组排序。
//直接选择排序、归并排序

import java.util.Arrays;

public class IntArray1                           //一维整数数组
{
    public static int[] random(int n, int range) //产生n个0～range之间的随机数，返回一维数组
    {
        int[] x = new int[n];                    //申请数组的存储空间，局部变量，动态数组
        while(n>0)                               //循环体中改变n值，不影响n的实际参数
            x[--n]=(int)(Math.random()*range);   //Math.random()返回0～1间的double随机数
                                                 //若n<0，抛出负数组长度异常若n<0，抛出负数组长度异常NegativeArraySizeException
        //第4版
 //       for (int i=0; i<x.length; i++) 
 //           x[i]=(int)(Math.random()*range);   //random()返回一个0～1间的double随机数
        return x;                                //返回x引用的数组，未释放数组空间
    }
    
    public static int[] random()                 //方法重载，参数取默认值
    {
        return random(10, 100);                  //产生10个100以内的随机数
    }

    public static void print(final int[] x)     //输出一维数组元素，数组作为参数
//    public static void print(int... x)        //可变形式参数，可将x视为int[]类型
    {
        System.out.print("{");
        if(x.length>0)
            System.out.print(x[0]);
        for(int i=0; i<x.length; i++)           //数组变量通过length属性获得存储单元数
            System.out.print(","+x[i]);
        System.out.println("}");
        
//      x = new int [4];                        //语法错，不能对常量赋值，即不能改变数组引用       
//      x[0]=0;                                 //可以更改常量数组元素值
    }

    //直接选择排序。数组作为引用类型参数，将改变实际参数的元素值
    public static void selectSort(int[] x)
    {
        for(int i=0; i<x.length-1; i++)         //n-1趟排序，每趟选择局部最小值再交换
        {
            int min=i;                          //设本趟待排序子序列首值最小，贪心选择策略
            for(int j=i; j<x.length; j++)       //在从x[i]开始的部分数组元素中
                if(x[j]<x[min])                 //寻找最小值
                    min=j;                      //min记下本趟最小值的下标
           
            if(i!=min)                          //本趟最小值交换到左边
            {
                int temp=x[i];
                x[i]=x[min];
                x[min]=temp;
            }
        }
    }
    
    //返回将x、y排序数组（升序）归并成的排序数组z，一次归并算法
    public static int[] merge(int[] x, int[] y)
    {
        int z[]=new int[x.length+y.length], i=0, j=0, k=0;
        while(i<x.length && j<y.length)          //将x、y排序数组归并到z中
        {
            if(x[i]<y[j])                        //较小值复制到z中
                z[k++]=x[i++];
            else
                z[k++]=y[j++];
        }

        while(i<x.length)                        //将x数组中剩余元素复制到z中
            z[k++]=x[i++];
        while(j<y.length)                        //将y数组中剩余元素复制到z中
            z[k++]=y[j++];
        return z;
    }
    
    public static void main(String[] args) 
    {
        int n1=7, range1=100;
        int[] value1=random(n1, range1), value2=random(6,100);   //产生随机数
        System.out.print("value1:");  print(value1);
        System.out.print("value2:");  print(value2);
//      java.util.Arrays.sort(value1);         //§4.3.2  java.util包 3.Arrays类
        selectSort(value1);
        selectSort(value2);
        System.out.print("sorted value1:");  print(value1);
        System.out.print("sorted value2:");  print(value2);
        System.out.print("merge:"); 
        print(merge(value1,value2));
    }
}
/*
程序运行结果如下：
value1:{14,92,38,54,78,36,97}
value2:{49,90,82,1,86,42,76,35,30,28}
sorted value1:{14,36,38,54,78,92,97}
sorted value2:{1,28,30,35,42,49,76,82,86,90}
merge:{1,14,28,30,35,36,38,42,49,54,76,78,82,86,90,92,97}

value1:{16,75,50,41,85,20,39}
value2:{73,35,62,1,91,79}
sorted value1:{16,20,39,41,50,75,85}
sorted value2:{1,35,62,73,79,91}
merge:{1,16,20,35,39,41,50,62,73,75,79,85,91}

 */

//2.  常量形式参数
//public static int[] random(final int n, int range)//产生n个0～range之间的随机数，返回一维数组
//{
//    n=10;                                    //语法错，不能对常量赋值
//}
//@author：Yeheya，2016-10-14