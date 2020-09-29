//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//输出整数的多种进制形式字符串，得到以下结论。
//Integer类的toString()方法返回当前整数的十进制字符串（带正负号）；
//Integer.toString(i, radix)方法返回i的radix进制字符串（带正负号）；
//Integer.toBinaryString(i)、toOctalString(i)、toHexString(i)方法
//分别返回i的二、八、十六进制补码字符串，当i≥0时，省略高位0
//说明：八、十六进制补码字符串都没有前缀。

public class Integer_toString 
{
    public static void main(String args[])
    {
    	//（1）第6版图2-24(a)(b)、第5版图2-25(a)(b)
//      int value = 126;                         //第5版图2-24
//        int value=-123, radix=2, n=1;         //第6版图2-24(a)(b)
        int value=1000, radix=16, n=4;         //第5版图2-25(a)(b)
        System.out.println("Integer.toBinaryString("+value+")=\""+Integer.toBinaryString(value)+"\""
                          +"，\"0x"+Integer.toHexString(value)+"\"");
        System.out.print("Integer.toBinaryString("+value+">>>="+n+")=\"");
        value >>>=n; 
        System.out.println(Integer.toBinaryString(value)+"\"，\"0x"+Integer.toHexString(value)+"\"");
    	
    	
        //（2）以下输出整数的多种进制形式字符串，整数的表示形式有：常量、十进制、八进制、十六进制
        //调用Integer.toString(i, radix)方法返回i的radix进制字符串（带正负号）；
        //Integer.toBinaryString(i)、toOctalString(i)、toHexString(i)方法
/*        int[] values={//Integer.MAX_VALUE,Integer.MIN_VALUE,             //常量
                      -1000,-126,-123,-1,-0,0,+0,123,126,254,1000,+1024,   //十进制
	                  0,0256,                                              //八进制，前缀是0，没有正负号
                      0x80000000,0Xfffffc18,0x0,0x3e8,0xabcedf,0x7fffffff};//十六进制，前缀是0x或0X，没有正负号
//                      123abcd,0x,0X,x,0x0x};           //整数语法错误
        for (int i=0; i<values.length; i++)
        {
        	System.out.println("原码，Integer.toString(      "+values[i]+", 2)="+Integer.toString(values[i],2)
        	        +"，"+Integer.toString(values[i],8)+"，"+Integer.toString(values[i],16));
        	System.out.println("补码，Integer.toBinaryString("+values[i]+"   )="+Integer.toBinaryString(values[i])
                    +"，"+Integer.toOctalString(values[i])+"，"+Integer.toHexString(values[i])+"\n");
        }
*/        
    }
}
/*
程序运行结果如下：
//（1）第6版图2-24(a)(b)
Integer.toBinaryString(123)=1111011
Integer.toBinaryString(-123)=     "11111111111111111111111110000101"，"0xffffff85"
Integer.toBinaryString(-123>>>=1)="1111111111111111111111111000010"，"0x7fffffc2"

//（1）第5版图2-25(a)(b)
Integer.toBinaryString(1000)=     "1111101000"，"0x3e8"
Integer.toBinaryString(1000>>>=4)="111110"，        "0x3e"


//（2）以下输出整数的多种进制形式字符串，
原码，Integer.toString(      -1000, 2)=-1111101000，-1750，-3e8
补码，Integer.toBinaryString(-1000   )=11111111111111111111110000011000，37777776030，fffffc18

原码，Integer.toString(      -126, 2)=-1111110，-176，-7e
补码，Integer.toBinaryString(-126   )=11111111111111111111111110000010，37777777602，ffffff82

原码，Integer.toString(      -123, 2)=-1111011，-173，-7b
补码，Integer.toBinaryString(-123   )=11111111111111111111111110000101，37777777605，ffffff85

原码，Integer.toString(      -1, 2)=-1，-1，-1
补码，Integer.toBinaryString(-1   )=11111111111111111111111111111111，37777777777，ffffffff

原码，Integer.toString(      0, 2)=0，0，0
补码，Integer.toBinaryString(0   )=0，0，0

原码，Integer.toString(      123, 2)=1111011，173，7b
补码，Integer.toBinaryString(123   )=1111011，173，7b

原码，Integer.toString(      126, 2)=1111110，176，7e
补码，Integer.toBinaryString(126   )=1111110，176，7e

原码，Integer.toString(      254, 2)=11111110，376，fe
补码，Integer.toBinaryString(254   )=11111110，376，fe

原码，Integer.toString(      1000, 2)=1111101000，1750，3e8
补码，Integer.toBinaryString(1000   )=1111101000，1750，3e8

原码，Integer.toString(      1024, 2)=10000000000，2000，400
补码，Integer.toBinaryString(1024   )=10000000000，2000，400

原码，Integer.toString(      0, 2)=0，0，0
补码，Integer.toBinaryString(0   )=0，0，0

原码，Integer.toString(      174, 2)=10101110，256，ae
补码，Integer.toBinaryString(174   )=10101110，256，ae

原码，Integer.toString(      -2147483648, 2)=-10000000000000000000000000000000，-20000000000，-80000000
补码，Integer.toBinaryString(-2147483648   )=10000000000000000000000000000000，20000000000，80000000

原码，Integer.toString(      -1000, 2)=-1111101000，-1750，-3e8
补码，Integer.toBinaryString(-1000   )=11111111111111111111110000011000，37777776030，fffffc18

原码，Integer.toString(      0, 2)=0，0，0
补码，Integer.toBinaryString(0   )=0，0，0

原码，Integer.toString(      1000, 2)=1111101000，1750，3e8
补码，Integer.toBinaryString(1000   )=1111101000，1750，3e8

原码，Integer.toString(      11259615, 2)=101010111100111011011111，52747337，abcedf
补码，Integer.toBinaryString(11259615   )=101010111100111011011111，52747337，abcedf

原码，Integer.toString(      2147483647, 2)=1111111111111111111111111111111，17777777777，7fffffff
补码，Integer.toBinaryString(2147483647   )=1111111111111111111111111111111，17777777777，7fffffff
*/
//@author：Yeheya，2019年1月26日