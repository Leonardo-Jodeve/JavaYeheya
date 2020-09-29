//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月23日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//（1）按radix进制将整数字符串转换成整数
//特别说明：MyInteger.parseInt(str)与java.lang.Integer类的同名方法功能不同。

public class MyInteger_parseInt
{
    public static void main(String args[])
    {
        //以下调用MyInteger.parseInt(str)方法，将整数字符串str转换成整数，
    	//自动识别整数的十、八、十六进制（分别以正负号及1～9、0、0x或0X开头）。
        //若没有捕获异常，则程序运行停止。
        String[] strs={"-123","-1","-0","+0","123","+1024",          //十进制
	                   "0","0256",                                   //八进制，前缀是0，没有正负号
                       "0x80000000","0Xfffffc18","0x0","0x3e8","0xabcedf","0x7fffffff",//十六进制，前缀是0x或0X，没有正负号
                       "","123abcd","123+","x","0x","0X","0x0x"};    //不能转换的字符串
        for(int i=0; i<strs.length; i++)
            try
            {
                System.out.print("MyInteger.parseInt(\""+strs[i]+"\")=");
                int value=MyInteger.parseInt(strs[i]);     //自动识别整数的十、八、十六进制
                System.out.println(value);
            }
            catch(NumberFormatException ex)
            {
            	System.out.println(ex.toString());         //包含异常类名、产生异常的字符串
            }
    }
}
/*
程序运行结果如下：
MyInteger.parseInt("-123")=-123
MyInteger.parseInt("-1")=-1
MyInteger.parseInt("-0")=0
MyInteger.parseInt("+0")=0
MyInteger.parseInt("123")=123
MyInteger.parseInt("+1024")=1024
MyInteger.parseInt("0")=0
MyInteger.parseInt("0256")=174
MyInteger.parseInt("0x80000000")=-2147483648
MyInteger.parseInt("0Xfffffc18")=-1000
MyInteger.parseInt("0x0")=0
MyInteger.parseInt("0x3e8")=1000
MyInteger.parseInt("0xabcedf")=11259615
MyInteger.parseInt("0x7fffffff")=2147483647
MyInteger.parseInt("")=java.lang.NumberFormatException: ""，不能转换成整数
MyInteger.parseInt("123abcd")=java.lang.NumberFormatException: "123abcd"，10进制整数不能识别'a'字符
MyInteger.parseInt("123+")=java.lang.NumberFormatException: "123+"，10进制整数不能识别'+'字符
MyInteger.parseInt("x")=java.lang.NumberFormatException: "x"，10进制整数不能识别'x'字符
MyInteger.parseInt("0x")=java.lang.NumberFormatException: "0x"，不能转换成整数
MyInteger.parseInt("0X")=java.lang.NumberFormatException: "0X"，不能转换成整数
MyInteger.parseInt("0x0x")=java.lang.NumberFormatException: "0x0x"，16进制整数不能识别'x'字符

*/
//@author：Yeheya，2019年1月27日