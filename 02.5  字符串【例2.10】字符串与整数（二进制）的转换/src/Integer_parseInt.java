//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��25��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��str�ַ�����radix����ת�����������õ����½��ۡ�
//Integer.parseInt(str)������str�ַ�����ʮ����ת����������
//Integer.parseInt(str,radix)������str�ַ�����radix����ת����������
//    str��radix�����ַ������������ţ�����ʽͬInteger.toString(i, radix)��������ֵ
//    radix���Ʒ�Χ��2��36������A��Z

public class Integer_parseInt 
{
    public static void main(String args[])
    {
        //��1���������ַ���str��ʮ��radix����ת����������
    	//����Integer.parseInt(str)��parseInt(str,radix)������
    	//��û�в����쳣�����������ֹͣ��
/*        String[] strs={"-123","-1","-0","0","+0","123","0256","+1024",//ʮ���ƣ�����0��ͷ
                       "","123abcd","123+","x","0x","0X","0x0x"};     //����ת�����ַ���
        for(int i=0; i<strs.length; i++)
            try
            {
                int value=Integer.parseInt(strs[i]);       //ʮ����
                System.out.print("Integer.parseInt(\""+strs[i]+"\")="+value+"��");
                
//            	String str = Integer.toBinaryString(value);//���룬������Ϊ�������
            	String str = Integer.toString(value,2);    //�������ŵ�ԭ��
                System.out.print("(\""+str+"\",2)="+Integer.parseInt(str, 2)+"��");
//            	str = Integer.toHexString(value);
            	str = Integer.toString(value,16);
                System.out.println("(\""+str+"\",16)="+Integer.parseInt(str,16));
            }
            catch(NumberFormatException ex)
            {
//            	System.out.println(ex.getMessage());       //ֻ���쳣��Ϣ
            	System.out.println(ex.toString());         //�����쳣�����������쳣���ַ���
//              ex.printStackTrace();	         //�����쳣�������쳣��Ϣ���쳣������Ϣ
            }
*/
        
    	//��2��Integer.parseInt(str,radix)������radix���Ʒ�Χ��2��36������A��Z
        for(int radix=Character.MIN_RADIX; radix<=Character.MAX_RADIX; radix++)
        {
             int value=radix-1; 
             String str=Integer.toString(value,radix);
             try
             {
                  System.out.println("Integer.toString("+value+","+radix+")=\""+str+
                      "\"��Integer.parseInt(\""+str+"\","+radix+")="+Integer.parseInt(str,radix));
             }
             catch(NumberFormatException ex)
             {
             	  System.out.println(ex.toString());         //�����쳣�����������쳣���ַ���
             }
        }
    }
}
/*
�������н�����£�
//��1�����½������ַ���str��ʮ����ת��������
Integer.parseInt("-123")=-123��("-1111011", 2)=-123��("-7b",16)=-123
Integer.parseInt("-1")=-1��("-1", 2)=-1��("-1",16)=-1
Integer.parseInt("-0")=0��("0", 2)=0��("0",16)=0
Integer.parseInt("0")=0��("0", 2)=0��("0",16)=0
Integer.parseInt("+0")=0��("0", 2)=0��("0",16)=0
Integer.parseInt("123")=123��("1111011", 2)=123��("7b",16)=123
Integer.parseInt("0256")=256��("100000000", 2)=256��("100",16)=256
Integer.parseInt("+1024")=1024��("10000000000", 2)=1024��("400",16)=1024
java.lang.NumberFormatException: For input string: ""
java.lang.NumberFormatException: For input string: "123abcd"
java.lang.NumberFormatException: For input string: "123+"
java.lang.NumberFormatException: For input string: "x"
java.lang.NumberFormatException: For input string: "0x"
java.lang.NumberFormatException: For input string: "0X"
java.lang.NumberFormatException: For input string: "0x0x"

//��2��Integer.parseInt(str,radix)������radix���Ʒ�Χ��2��36������A��Z
Integer.toString(1,2)="1"��Integer.parseInt("1",2)=1
Integer.toString(2,3)="2"��Integer.parseInt("2",3)=2
Integer.toString(3,4)="3"��Integer.parseInt("3",4)=3
Integer.toString(4,5)="4"��Integer.parseInt("4",5)=4
Integer.toString(5,6)="5"��Integer.parseInt("5",6)=5
Integer.toString(6,7)="6"��Integer.parseInt("6",7)=6
Integer.toString(7,8)="7"��Integer.parseInt("7",8)=7
Integer.toString(8,9)="8"��Integer.parseInt("8",9)=8
Integer.toString(9,10)="9"��Integer.parseInt("9",10)=9
Integer.toString(10,11)="a"��Integer.parseInt("a",11)=10
Integer.toString(11,12)="b"��Integer.parseInt("b",12)=11
Integer.toString(12,13)="c"��Integer.parseInt("c",13)=12
Integer.toString(13,14)="d"��Integer.parseInt("d",14)=13
Integer.toString(14,15)="e"��Integer.parseInt("e",15)=14
Integer.toString(15,16)="f"��Integer.parseInt("f",16)=15
Integer.toString(16,17)="g"��Integer.parseInt("g",17)=16
Integer.toString(17,18)="h"��Integer.parseInt("h",18)=17
Integer.toString(18,19)="i"��Integer.parseInt("i",19)=18
Integer.toString(19,20)="j"��Integer.parseInt("j",20)=19
Integer.toString(20,21)="k"��Integer.parseInt("k",21)=20
Integer.toString(21,22)="l"��Integer.parseInt("l",22)=21
Integer.toString(22,23)="m"��Integer.parseInt("m",23)=22
Integer.toString(23,24)="n"��Integer.parseInt("n",24)=23
Integer.toString(24,25)="o"��Integer.parseInt("o",25)=24
Integer.toString(25,26)="p"��Integer.parseInt("p",26)=25
Integer.toString(26,27)="q"��Integer.parseInt("q",27)=26
Integer.toString(27,28)="r"��Integer.parseInt("r",28)=27
Integer.toString(28,29)="s"��Integer.parseInt("s",29)=28
Integer.toString(29,30)="t"��Integer.parseInt("t",30)=29
Integer.toString(30,31)="u"��Integer.parseInt("u",31)=30
Integer.toString(31,32)="v"��Integer.parseInt("v",32)=31
Integer.toString(32,33)="w"��Integer.parseInt("w",33)=32
Integer.toString(33,34)="x"��Integer.parseInt("x",34)=33
Integer.toString(34,35)="y"��Integer.parseInt("y",35)=34
Integer.toString(35,36)="z"��Integer.parseInt("z",36)=35

*/
//@author��Yeheya��2019��1��27��