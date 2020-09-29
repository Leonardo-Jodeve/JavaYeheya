//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��23��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��2��������ת����radix���Ʋ����ַ���
//�ر�˵����MyInteger.toString(i, radix)��java.lang.Integer.toString(i, radix)�������ܲ�ͬ��

public class MyInteger_toString_radix 
{
    public static void main(String args[])
    {
    	//��6��ͼ2-24(a)(b)����5��ͼ2-25(a)(b)
//      int value=-123, radix=2, n=1;           //��6��ͼ2-24(a)(b)
//      int value=-1000, radix=16, n=4;         //��5��ͼ2-25(a)(b)
        int value=0xfedcba, radix=16, n=4;      //��6��ͼ2-25(a)(b)
        System.out.println("MyInteger.toString("+value+",2)=\""+MyInteger.toString(value,2)+"\""+
                         "��\""+MyInteger.toString(value,16)+"\"");

    	 //��6��ͼ2-24(b)����5��ͼ2-25(b)
        System.out.print("MyInteger.toString("+value+">>>="+n+",2)=\"");
        value >>>=n; 
        String str2=MyInteger.toString(value,2);
        String str8=MyInteger.toString(value,8);
        String str16=MyInteger.toString(value,16);
        System.out.println(str2+"\"��\""+str8+"\"��\""+str16+"\"");
  	
        //�Զ�ʶ��������ʮ���ˡ�ʮ�����ƣ�����ʶ������Ľ���
        System.out.println("MyInteger.parseInt(\""+str8+"\")="+MyInteger.parseInt(str8));
        System.out.println("MyInteger.parseInt(\""+str16+"\")="+MyInteger.parseInt(str16));
      
      
        //��2��������ת����radix���Ʋ����ַ���
    	//���µ���MyInteger.toString(i, radix)��������������Ķ��ֽ��Ʋ����ַ���
        //��û�в����쳣�����������ֹͣ��
/*        int[] values={-1000,-123,-1,0,123,254,1000,+1024,  //ʮ����
	                    0256,                                //�˽��ƣ�ǰ׺��0��û��������
                        0x80000000,0Xfffffc18,0x0,0x3e8,0xabcedf,0x7fffffff};//ʮ�����ƣ�ǰ׺��0x��0X��û��������
        int[] radixs={2,4,8,16};                        //���Ƽ���
        for(int i=0; i<values.length; i++)
        {
            for(int j=0; j<radixs.length; j++)
                System.out.println("���룬MyInteger.toString("+values[i]+","+radixs[j]+")=\""+
                                         MyInteger.toString(values[i],radixs[j])+"\"");
            System.out.println();
        }
*/
    	
    }
}
/*
�������н�����£�
//��6��ͼ2-24(a)(b)
MyInteger.toString(123,2)=      "00000000000000000000000001111011"��"0x0000007b"
MyInteger.toString(123>>>=1,2)="00000000000000000000000000111101"��  "0x0000003d"
MyInteger.parseInt("0x0000003d")=61

MyInteger.toString(-123,2)=      "11111111111111111111111110000101"��"0xffffff85"
MyInteger.toString(-123>>>=1,2)="01111111111111111111111111000010"��  "0x7fffffc2"
MyInteger.parseInt("0x7fffffc2")=2147483586

//��5��ͼ2-25(a)(b)
MyInteger.toString(1000,2)=         "00000000000000000000001111101000"��"0x000003e8"
MyInteger.toString(1000>>>=4,2)="00000000000000000000000000111110"��        "0x0000003e"
MyInteger.parseInt("0x0000003e")=62

MyInteger.toString(-1000,2)=         "11111111111111111111110000011000"��"0xfffffc18"
MyInteger.toString(-1000>>>=4,2)="00001111111111111111111111000001"��        "0x0fffffc1"
MyInteger.parseInt("001777777701")=268435393
MyInteger.parseInt("0x0fffffc1")=268435393

MyInteger.toString(16702650,2)=         "00000000111111101101110010111010"��"0x00fedcba"
MyInteger.toString(16702650>>>=4,2)="00000000000011111110110111001011"��"000003766713"��"0x000fedcb"
MyInteger.parseInt("000003766713")=1043915
MyInteger.parseInt("0x000fedcb")=1043915


//��2��������ת����radix���Ʋ����ַ���
���룬MyInteger.toString(-1000,2)="11111111111111111111110000011000"
���룬MyInteger.toString(-1000,4)="3333333333300120"
���룬MyInteger.toString(-1000,8)="37777776030"
���룬MyInteger.toString(-1000,16)="fffffc18"

���룬MyInteger.toString(-123,2)="11111111111111111111111110000101"
���룬MyInteger.toString(-123,4)="3333333333332011"
���룬MyInteger.toString(-123,8)="37777777605"
���룬MyInteger.toString(-123,16)="ffffff85"

���룬MyInteger.toString(-1,2)="11111111111111111111111111111111"
���룬MyInteger.toString(-1,4)="3333333333333333"
���룬MyInteger.toString(-1,8)="37777777777"
���룬MyInteger.toString(-1,16)="ffffffff"

���룬MyInteger.toString(0,2)="00000000000000000000000000000000"
���룬MyInteger.toString(0,4)="0000000000000000"
���룬MyInteger.toString(0,8)="00000000000"
���룬MyInteger.toString(0,16)="00000000"

���룬MyInteger.toString(123,2)="00000000000000000000000001111011"
���룬MyInteger.toString(123,4)="0000000000001323"
���룬MyInteger.toString(123,8)="00000000173"
���룬MyInteger.toString(123,16)="0000007b"

���룬MyInteger.toString(254,2)="00000000000000000000000011111110"
���룬MyInteger.toString(254,4)="0000000000003332"
���룬MyInteger.toString(254,8)="00000000376"
���룬MyInteger.toString(254,16)="000000fe"

���룬MyInteger.toString(1000,2)="00000000000000000000001111101000"
���룬MyInteger.toString(1000,4)="0000000000033220"
���룬MyInteger.toString(1000,8)="00000001750"
���룬MyInteger.toString(1000,16)="000003e8"

���룬MyInteger.toString(1024,2)="00000000000000000000010000000000"
���룬MyInteger.toString(1024,4)="0000000000100000"
���룬MyInteger.toString(1024,8)="00000002000"
���룬MyInteger.toString(1024,16)="00000400"

���룬MyInteger.toString(174,2)="00000000000000000000000010101110"
���룬MyInteger.toString(174,4)="0000000000002232"
���룬MyInteger.toString(174,8)="00000000256"
���룬MyInteger.toString(174,16)="000000ae"

���룬MyInteger.toString(-2147483648,2)="10000000000000000000000000000000"
���룬MyInteger.toString(-2147483648,4)="2000000000000000"
���룬MyInteger.toString(-2147483648,8)="20000000000"
���룬MyInteger.toString(-2147483648,10)="-2147483648"
���룬MyInteger.toString(-2147483648,16)="80000000"

���룬MyInteger.toString(-1000,2)="11111111111111111111110000011000"
���룬MyInteger.toString(-1000,4)="3333333333300120"
���룬MyInteger.toString(-1000,8)="37777776030"
���룬MyInteger.toString(-1000,16)="fffffc18"

���룬MyInteger.toString(0,2)="00000000000000000000000000000000"
���룬MyInteger.toString(0,4)="0000000000000000"
���룬MyInteger.toString(0,8)="00000000000"
���룬MyInteger.toString(0,16)="00000000"

���룬MyInteger.toString(1000,2)="00000000000000000000001111101000"
���룬MyInteger.toString(1000,4)="0000000000033220"
���룬MyInteger.toString(1000,8)="00000001750"
���룬MyInteger.toString(1000,16)="000003e8"

���룬MyInteger.toString(11259615,2)="00000000101010111100111011011111"
���룬MyInteger.toString(11259615,4)="0000222330323133"
���룬MyInteger.toString(11259615,8)="00052747337"
���룬MyInteger.toString(11259615,16)="00abcedf"

���룬MyInteger.toString(2147483647,2)="01111111111111111111111111111111"
���룬MyInteger.toString(2147483647,4)="1333333333333333"
���룬MyInteger.toString(2147483647,8)="17777777777"
���룬MyInteger.toString(2147483647,16)="7fffffff"

*/