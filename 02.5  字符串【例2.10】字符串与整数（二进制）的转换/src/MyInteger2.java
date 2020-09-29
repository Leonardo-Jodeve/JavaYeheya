//《Java程序设计实用教程（第6版）》 作者：叶核亚，2019年1月28日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//（2）将整数转换成radix进制补码字符串
//除2取余法
//特别说明：与java.lang.Integer类的同名方法功能不同。

public class MyInteger2 
{
	public static String toBinaryString(int value) 	//返回正整数value的二进制字符串，除2取余法
	{
	    String str="";
	    while(value>0) 
	    {
	        str = value%2 + str;                 //意即str=(char)(value%2+'0')+str。
	                                          	 //取余，value%2获得二进制个位；再以逆序连接成字符串
	        value /= 2;                          //整除2
	    }
	    return str;                             	//返回字符串
	}	
	
    public static void main(String args[])
    {
        int[] values = {-123,123};
        for(int i=0; i<values.length; i++)
            System.out.println("MyInteger2.toBinaryString("+values[i]+")="+MyInteger2.toBinaryString(values[i]));
    }
}
/*
程序运行结果如下：
MyInteger2.toBinaryString(-123)=
MyInteger2.toBinaryString(123)=1111011

*/
//@author：Yeheya，2019年1月28日