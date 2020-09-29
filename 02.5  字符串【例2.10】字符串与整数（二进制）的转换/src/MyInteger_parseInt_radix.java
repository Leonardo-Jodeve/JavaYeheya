//《Java程序设计实用教程（第5版）》 作者：叶核亚，2019年1月28日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//（3）测试MyInteger.parseInt(str,radix)方法功能，该方法教材没写。
//与java.lang.Integer.parseInt(str,radix)方法功能相同，八进制和十六进制没有前缀。

public class MyInteger_parseInt_radix 
{
    public static void main(String args[])
    {
        //以下调用MyInteger.toString(i,radix)和MyInteger.parseInt(str,radix)方法，
    	//先输出整数的多种进制补码字符串，再将各进制补码字符串转换成整数。
    	//MyInteger.toString(i,radix)方法，八进制和十六进制没有前缀。
    	//整数的表示形式有：常量、十进制、八进制、十六进制
//        int[] values={0x80000000,-1000,-123,-1,0,123,0256,0x3e8,+1024,0xabcedf,0x7fffffff};
        int[] values={-123,0xfedcba};
        int[] radixs={2,4,8,16};                           //进制集合
        for(int i=0; i<values.length; i++)
        {
            for(int j=0; j<radixs.length; j++)
            {
        	    String str = MyInteger.toString(values[i],radixs[j]);//补码，八进制和十六进制没有前缀
                System.out.print("MyInteger.toString("+values[i]+","+radixs[j]+")=\""+str+"\"，");
                System.out.println("MyInteger.parseInt(\""+str+"\","+radixs[j]+")="+MyInteger.parseInt(str,radixs[j]));
            }
            System.out.println();
        }
    }
}
/*
程序运行结果如下：
MyInteger.toString(-123,2)="11111111111111111111111110000101"，MyInteger.parseInt("11111111111111111111111110000101",2)=-123
MyInteger.toString(-123,4)="3333333333332011"，MyInteger.parseInt("3333333333332011",4)=-123
MyInteger.toString(-123,8)="37777777605"，MyInteger.parseInt("37777777605",8)=-123
MyInteger.toString(-123,16)="ffffff85"，MyInteger.parseInt("ffffff85",16)=-123

MyInteger.toString(16702650,2)="00000000111111101101110010111010"，MyInteger.parseInt("00000000111111101101110010111010",2)=16702650
MyInteger.toString(16702650,4)="0000333231302322"，MyInteger.parseInt("0000333231302322",4)=16702650
MyInteger.toString(16702650,8)="00077556272"，MyInteger.parseInt("00077556272",8)=16702650
MyInteger.toString(16702650,16)="00fedcba"，MyInteger.parseInt("00fedcba",16)=16702650

*/
//@author：Yeheya，2019年1月28日