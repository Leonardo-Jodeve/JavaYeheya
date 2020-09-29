//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��23��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��1����radix���ƽ������ַ���ת��������
//�ر�˵����MyInteger.parseInt(str)��java.lang.Integer���ͬ���������ܲ�ͬ��

public class MyInteger_parseInt
{
    public static void main(String args[])
    {
        //���µ���MyInteger.parseInt(str)�������������ַ���strת����������
    	//�Զ�ʶ��������ʮ���ˡ�ʮ�����ƣ��ֱ��������ż�1��9��0��0x��0X��ͷ����
        //��û�в����쳣�����������ֹͣ��
        String[] strs={"-123","-1","-0","+0","123","+1024",          //ʮ����
	                   "0","0256",                                   //�˽��ƣ�ǰ׺��0��û��������
                       "0x80000000","0Xfffffc18","0x0","0x3e8","0xabcedf","0x7fffffff",//ʮ�����ƣ�ǰ׺��0x��0X��û��������
                       "","123abcd","123+","x","0x","0X","0x0x"};    //����ת�����ַ���
        for(int i=0; i<strs.length; i++)
            try
            {
                System.out.print("MyInteger.parseInt(\""+strs[i]+"\")=");
                int value=MyInteger.parseInt(strs[i]);     //�Զ�ʶ��������ʮ���ˡ�ʮ������
                System.out.println(value);
            }
            catch(NumberFormatException ex)
            {
            	System.out.println(ex.toString());         //�����쳣�����������쳣���ַ���
            }
    }
}
/*
�������н�����£�
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
MyInteger.parseInt("")=java.lang.NumberFormatException: ""������ת��������
MyInteger.parseInt("123abcd")=java.lang.NumberFormatException: "123abcd"��10������������ʶ��'a'�ַ�
MyInteger.parseInt("123+")=java.lang.NumberFormatException: "123+"��10������������ʶ��'+'�ַ�
MyInteger.parseInt("x")=java.lang.NumberFormatException: "x"��10������������ʶ��'x'�ַ�
MyInteger.parseInt("0x")=java.lang.NumberFormatException: "0x"������ת��������
MyInteger.parseInt("0X")=java.lang.NumberFormatException: "0X"������ת��������
MyInteger.parseInt("0x0x")=java.lang.NumberFormatException: "0x0x"��16������������ʶ��'x'�ַ�

*/
//@author��Yeheya��2019��1��27��