//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月25日
//§5.2 异常处理措施
//【例5.1】程序应避免的异常分析。

public class ArrayAverage例5_1 
{
	public static double average(int[] value)    //求整数数组元素平均值
	{
		double sum = 0.0;
	    //以下若value==null，value.length抛出空对象异常；i<value.length，避免数组下标越界异常
		for(int i=0; i<value.length; i++)
			sum += value[i];
		return sum/value.length;                 //若value.length==0，运算结果为NaN
	}

	public static void main(String[] args) 
	{
		int[] x = {1,2,3,4};
		System.out.println("average(x)=" + average(x));   //正常，结果为2.5

		x = null;
		// System.out.println("average(x)="+average(x));  //空对象异常，NullPointerException
		
		int[] y = {};                                     //y!=null，y.length==0
		System.out.println("average(y)=" + average(y));   //结果为NaN（不确定值）

		y = new int[0];                                   //y!=null，y.length==0
		System.out.println("average(y)=" + average(y));   //结果为NaN
        
        System.out.println(""+(3/0));                     //整数除法，除数为0，抛出算术异常
        System.out.println(""+(3/0.0));                   //浮点数除法，除数为0，没有抛出异常，运行结果为Infinity（无穷大）
        System.out.println(""+(3.0/0));                   //浮点数除法，除数为0，没有抛出异常，运行结果为Infinity（无穷大）
        System.out.println("Math.sqrt(-4)="+(Math.sqrt(-4))); //结果为NaN??

        //发现数组下标越界的语义错
        int[] a={1,2};
        for(int i=0; i<5; i++) 
            System.out.println("  a["+i+"]="+a[i]); 
	}
}
/*
程序运行结果如下： 
average(x)=2.5 
average(y)=NaN 
average(y)=NaN
  
Infinity Math.sqrt(-4)=NaN
a[0]=1 a[1]=2 Exception in thread "main"
 * java.lang.ArrayIndexOutOfBoundsException: 2 at
 * Outofbounds.main(Outofbounds.java:9)
 */
//@author：Yeheya，2016-8-25，2017年7月17日