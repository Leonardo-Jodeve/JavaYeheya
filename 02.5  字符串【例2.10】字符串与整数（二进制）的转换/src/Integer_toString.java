//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��25��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��������Ķ��ֽ�����ʽ�ַ������õ����½��ۡ�
//Integer���toString()�������ص�ǰ������ʮ�����ַ������������ţ���
//Integer.toString(i, radix)��������i��radix�����ַ������������ţ���
//Integer.toBinaryString(i)��toOctalString(i)��toHexString(i)����
//�ֱ𷵻�i�Ķ����ˡ�ʮ�����Ʋ����ַ�������i��0ʱ��ʡ�Ը�λ0
//˵�����ˡ�ʮ�����Ʋ����ַ�����û��ǰ׺��

public class Integer_toString 
{
    public static void main(String args[])
    {
    	//��1����6��ͼ2-24(a)(b)����5��ͼ2-25(a)(b)
//      int value = 126;                         //��5��ͼ2-24
//        int value=-123, radix=2, n=1;         //��6��ͼ2-24(a)(b)
        int value=1000, radix=16, n=4;         //��5��ͼ2-25(a)(b)
        System.out.println("Integer.toBinaryString("+value+")=\""+Integer.toBinaryString(value)+"\""
                          +"��\"0x"+Integer.toHexString(value)+"\"");
        System.out.print("Integer.toBinaryString("+value+">>>="+n+")=\"");
        value >>>=n; 
        System.out.println(Integer.toBinaryString(value)+"\"��\"0x"+Integer.toHexString(value)+"\"");
    	
    	
        //��2��������������Ķ��ֽ�����ʽ�ַ����������ı�ʾ��ʽ�У�������ʮ���ơ��˽��ơ�ʮ������
        //����Integer.toString(i, radix)��������i��radix�����ַ������������ţ���
        //Integer.toBinaryString(i)��toOctalString(i)��toHexString(i)����
/*        int[] values={//Integer.MAX_VALUE,Integer.MIN_VALUE,             //����
                      -1000,-126,-123,-1,-0,0,+0,123,126,254,1000,+1024,   //ʮ����
	                  0,0256,                                              //�˽��ƣ�ǰ׺��0��û��������
                      0x80000000,0Xfffffc18,0x0,0x3e8,0xabcedf,0x7fffffff};//ʮ�����ƣ�ǰ׺��0x��0X��û��������
//                      123abcd,0x,0X,x,0x0x};           //�����﷨����
        for (int i=0; i<values.length; i++)
        {
        	System.out.println("ԭ�룬Integer.toString(      "+values[i]+", 2)="+Integer.toString(values[i],2)
        	        +"��"+Integer.toString(values[i],8)+"��"+Integer.toString(values[i],16));
        	System.out.println("���룬Integer.toBinaryString("+values[i]+"   )="+Integer.toBinaryString(values[i])
                    +"��"+Integer.toOctalString(values[i])+"��"+Integer.toHexString(values[i])+"\n");
        }
*/        
    }
}
/*
�������н�����£�
//��1����6��ͼ2-24(a)(b)
Integer.toBinaryString(123)=1111011
Integer.toBinaryString(-123)=     "11111111111111111111111110000101"��"0xffffff85"
Integer.toBinaryString(-123>>>=1)="1111111111111111111111111000010"��"0x7fffffc2"

//��1����5��ͼ2-25(a)(b)
Integer.toBinaryString(1000)=     "1111101000"��"0x3e8"
Integer.toBinaryString(1000>>>=4)="111110"��        "0x3e"


//��2��������������Ķ��ֽ�����ʽ�ַ�����
ԭ�룬Integer.toString(      -1000, 2)=-1111101000��-1750��-3e8
���룬Integer.toBinaryString(-1000   )=11111111111111111111110000011000��37777776030��fffffc18

ԭ�룬Integer.toString(      -126, 2)=-1111110��-176��-7e
���룬Integer.toBinaryString(-126   )=11111111111111111111111110000010��37777777602��ffffff82

ԭ�룬Integer.toString(      -123, 2)=-1111011��-173��-7b
���룬Integer.toBinaryString(-123   )=11111111111111111111111110000101��37777777605��ffffff85

ԭ�룬Integer.toString(      -1, 2)=-1��-1��-1
���룬Integer.toBinaryString(-1   )=11111111111111111111111111111111��37777777777��ffffffff

ԭ�룬Integer.toString(      0, 2)=0��0��0
���룬Integer.toBinaryString(0   )=0��0��0

ԭ�룬Integer.toString(      123, 2)=1111011��173��7b
���룬Integer.toBinaryString(123   )=1111011��173��7b

ԭ�룬Integer.toString(      126, 2)=1111110��176��7e
���룬Integer.toBinaryString(126   )=1111110��176��7e

ԭ�룬Integer.toString(      254, 2)=11111110��376��fe
���룬Integer.toBinaryString(254   )=11111110��376��fe

ԭ�룬Integer.toString(      1000, 2)=1111101000��1750��3e8
���룬Integer.toBinaryString(1000   )=1111101000��1750��3e8

ԭ�룬Integer.toString(      1024, 2)=10000000000��2000��400
���룬Integer.toBinaryString(1024   )=10000000000��2000��400

ԭ�룬Integer.toString(      0, 2)=0��0��0
���룬Integer.toBinaryString(0   )=0��0��0

ԭ�룬Integer.toString(      174, 2)=10101110��256��ae
���룬Integer.toBinaryString(174   )=10101110��256��ae

ԭ�룬Integer.toString(      -2147483648, 2)=-10000000000000000000000000000000��-20000000000��-80000000
���룬Integer.toBinaryString(-2147483648   )=10000000000000000000000000000000��20000000000��80000000

ԭ�룬Integer.toString(      -1000, 2)=-1111101000��-1750��-3e8
���룬Integer.toBinaryString(-1000   )=11111111111111111111110000011000��37777776030��fffffc18

ԭ�룬Integer.toString(      0, 2)=0��0��0
���룬Integer.toBinaryString(0   )=0��0��0

ԭ�룬Integer.toString(      1000, 2)=1111101000��1750��3e8
���룬Integer.toBinaryString(1000   )=1111101000��1750��3e8

ԭ�룬Integer.toString(      11259615, 2)=101010111100111011011111��52747337��abcedf
���룬Integer.toBinaryString(11259615   )=101010111100111011011111��52747337��abcedf

ԭ�룬Integer.toString(      2147483647, 2)=1111111111111111111111111111111��17777777777��7fffffff
���룬Integer.toBinaryString(2147483647   )=1111111111111111111111111111111��17777777777��7fffffff
*/
//@author��Yeheya��2019��1��26��