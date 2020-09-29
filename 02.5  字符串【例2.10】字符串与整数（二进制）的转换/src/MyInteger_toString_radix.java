//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月23日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//（2）将整数转换成radix进制补码字符串
//特别说明：MyInteger.toString(i, radix)与java.lang.Integer.toString(i, radix)方法功能不同。

public class MyInteger_toString_radix 
{
    public static void main(String args[])
    {
    	//第6版图2-24(a)(b)、第5版图2-25(a)(b)
//      int value=-123, radix=2, n=1;           //第6版图2-24(a)(b)
//      int value=-1000, radix=16, n=4;         //第5版图2-25(a)(b)
        int value=0xfedcba, radix=16, n=4;      //第6版图2-25(a)(b)
        System.out.println("MyInteger.toString("+value+",2)=\""+MyInteger.toString(value,2)+"\""+
                         "，\""+MyInteger.toString(value,16)+"\"");

    	 //第6版图2-24(b)、第5版图2-25(b)
        System.out.print("MyInteger.toString("+value+">>>="+n+",2)=\"");
        value >>>=n; 
        String str2=MyInteger.toString(value,2);
        String str8=MyInteger.toString(value,8);
        String str16=MyInteger.toString(value,16);
        System.out.println(str2+"\"，\""+str8+"\"，\""+str16+"\"");
  	
        //自动识别整数的十、八、十六进制，不能识别二、四进制
        System.out.println("MyInteger.parseInt(\""+str8+"\")="+MyInteger.parseInt(str8));
        System.out.println("MyInteger.parseInt(\""+str16+"\")="+MyInteger.parseInt(str16));
      
      
        //（2）将整数转换成radix进制补码字符串
    	//以下调用MyInteger.toString(i, radix)方法，输出整数的多种进制补码字符串
        //若没有捕获异常，则程序运行停止。
/*        int[] values={-1000,-123,-1,0,123,254,1000,+1024,  //十进制
	                    0256,                                //八进制，前缀是0，没有正负号
                        0x80000000,0Xfffffc18,0x0,0x3e8,0xabcedf,0x7fffffff};//十六进制，前缀是0x或0X，没有正负号
        int[] radixs={2,4,8,16};                        //进制集合
        for(int i=0; i<values.length; i++)
        {
            for(int j=0; j<radixs.length; j++)
                System.out.println("补码，MyInteger.toString("+values[i]+","+radixs[j]+")=\""+
                                         MyInteger.toString(values[i],radixs[j])+"\"");
            System.out.println();
        }
*/
    	
    }
}
/*
程序运行结果如下：
//第6版图2-24(a)(b)
MyInteger.toString(123,2)=      "00000000000000000000000001111011"，"0x0000007b"
MyInteger.toString(123>>>=1,2)="00000000000000000000000000111101"，  "0x0000003d"
MyInteger.parseInt("0x0000003d")=61

MyInteger.toString(-123,2)=      "11111111111111111111111110000101"，"0xffffff85"
MyInteger.toString(-123>>>=1,2)="01111111111111111111111111000010"，  "0x7fffffc2"
MyInteger.parseInt("0x7fffffc2")=2147483586

//第5版图2-25(a)(b)
MyInteger.toString(1000,2)=         "00000000000000000000001111101000"，"0x000003e8"
MyInteger.toString(1000>>>=4,2)="00000000000000000000000000111110"，        "0x0000003e"
MyInteger.parseInt("0x0000003e")=62

MyInteger.toString(-1000,2)=         "11111111111111111111110000011000"，"0xfffffc18"
MyInteger.toString(-1000>>>=4,2)="00001111111111111111111111000001"，        "0x0fffffc1"
MyInteger.parseInt("001777777701")=268435393
MyInteger.parseInt("0x0fffffc1")=268435393

MyInteger.toString(16702650,2)=         "00000000111111101101110010111010"，"0x00fedcba"
MyInteger.toString(16702650>>>=4,2)="00000000000011111110110111001011"，"000003766713"，"0x000fedcb"
MyInteger.parseInt("000003766713")=1043915
MyInteger.parseInt("0x000fedcb")=1043915


//（2）将整数转换成radix进制补码字符串
补码，MyInteger.toString(-1000,2)="11111111111111111111110000011000"
补码，MyInteger.toString(-1000,4)="3333333333300120"
补码，MyInteger.toString(-1000,8)="37777776030"
补码，MyInteger.toString(-1000,16)="fffffc18"

补码，MyInteger.toString(-123,2)="11111111111111111111111110000101"
补码，MyInteger.toString(-123,4)="3333333333332011"
补码，MyInteger.toString(-123,8)="37777777605"
补码，MyInteger.toString(-123,16)="ffffff85"

补码，MyInteger.toString(-1,2)="11111111111111111111111111111111"
补码，MyInteger.toString(-1,4)="3333333333333333"
补码，MyInteger.toString(-1,8)="37777777777"
补码，MyInteger.toString(-1,16)="ffffffff"

补码，MyInteger.toString(0,2)="00000000000000000000000000000000"
补码，MyInteger.toString(0,4)="0000000000000000"
补码，MyInteger.toString(0,8)="00000000000"
补码，MyInteger.toString(0,16)="00000000"

补码，MyInteger.toString(123,2)="00000000000000000000000001111011"
补码，MyInteger.toString(123,4)="0000000000001323"
补码，MyInteger.toString(123,8)="00000000173"
补码，MyInteger.toString(123,16)="0000007b"

补码，MyInteger.toString(254,2)="00000000000000000000000011111110"
补码，MyInteger.toString(254,4)="0000000000003332"
补码，MyInteger.toString(254,8)="00000000376"
补码，MyInteger.toString(254,16)="000000fe"

补码，MyInteger.toString(1000,2)="00000000000000000000001111101000"
补码，MyInteger.toString(1000,4)="0000000000033220"
补码，MyInteger.toString(1000,8)="00000001750"
补码，MyInteger.toString(1000,16)="000003e8"

补码，MyInteger.toString(1024,2)="00000000000000000000010000000000"
补码，MyInteger.toString(1024,4)="0000000000100000"
补码，MyInteger.toString(1024,8)="00000002000"
补码，MyInteger.toString(1024,16)="00000400"

补码，MyInteger.toString(174,2)="00000000000000000000000010101110"
补码，MyInteger.toString(174,4)="0000000000002232"
补码，MyInteger.toString(174,8)="00000000256"
补码，MyInteger.toString(174,16)="000000ae"

补码，MyInteger.toString(-2147483648,2)="10000000000000000000000000000000"
补码，MyInteger.toString(-2147483648,4)="2000000000000000"
补码，MyInteger.toString(-2147483648,8)="20000000000"
补码，MyInteger.toString(-2147483648,10)="-2147483648"
补码，MyInteger.toString(-2147483648,16)="80000000"

补码，MyInteger.toString(-1000,2)="11111111111111111111110000011000"
补码，MyInteger.toString(-1000,4)="3333333333300120"
补码，MyInteger.toString(-1000,8)="37777776030"
补码，MyInteger.toString(-1000,16)="fffffc18"

补码，MyInteger.toString(0,2)="00000000000000000000000000000000"
补码，MyInteger.toString(0,4)="0000000000000000"
补码，MyInteger.toString(0,8)="00000000000"
补码，MyInteger.toString(0,16)="00000000"

补码，MyInteger.toString(1000,2)="00000000000000000000001111101000"
补码，MyInteger.toString(1000,4)="0000000000033220"
补码，MyInteger.toString(1000,8)="00000001750"
补码，MyInteger.toString(1000,16)="000003e8"

补码，MyInteger.toString(11259615,2)="00000000101010111100111011011111"
补码，MyInteger.toString(11259615,4)="0000222330323133"
补码，MyInteger.toString(11259615,8)="00052747337"
补码，MyInteger.toString(11259615,16)="00abcedf"

补码，MyInteger.toString(2147483647,2)="01111111111111111111111111111111"
补码，MyInteger.toString(2147483647,4)="1333333333333333"
补码，MyInteger.toString(2147483647,8)="17777777777"
补码，MyInteger.toString(2147483647,16)="7fffffff"

*/