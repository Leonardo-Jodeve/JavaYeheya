//《Java程序设计实用教程（第4版）》，作者：叶核亚。JDK8_05，2014年7月10日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，JDK8_25，2016年6月11日
//§2.1.4   运算符与表达式
//1.  运算符
//（3） 位运算符
//图2.3  整数&和 | 位运算

public class Binary_图2_3 
{
    public static void main(String[] args) 
    {
        System.out.print("图2.3（a）运算，");
        String op="&";
        int x=173, y=107, z = x & y;
        System.out.println(x+op+y+"="+z);

        System.out.print("图2.3（b）运算，");
        op="|";                                  //图2.3（b）
        x=-123; y=78; z = x | y;
        System.out.println(x+op+y+"="+z);

        System.out.print("习题解答图2.2（a）运算，");
        op="&";
        x=125; y=10; z = x & y;
        System.out.println(x+op+y+"="+z);

        System.out.print("习题解答图2.2（b）运算，");
        op="|";
        z = x | y;
        System.out.println(x+op+y+"="+z);

        System.out.println("\n十进制，二进制，八进制，十六进制");
        int value[]={173,107,41,-123,78,-49,125};              //十进制
        for (int i=0; i<value.length; i++)
             System.out.println(value[i]+"，"+Integer.toBinaryString(value[i])+"，"+
                Integer.toOctalString(value[i])+"，"+Integer.toHexString(value[i]));
        
    }
}
/*
图2.3（a）运算，173&107=41
图2.3（b）运算，-123|78=-49
习题解答图2.2（a）运算，125&10=8
习题解答图2.2（b）运算，125|10=127

十进制，二进制，八进制，十六进制
173，10101101，255，ad
107，1101011，153，6b
41，101001，51，29
-123，11111111111111111111111110000101，37777777605，ffffff85
78，1001110，116，4e
-49，11111111111111111111111111001111，37777777717，ffffffcf
125，1111101，175，7d

*/
//@author：Yeheya，2016-6-11