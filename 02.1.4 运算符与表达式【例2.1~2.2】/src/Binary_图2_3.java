//��Java�������ʵ�ý̳̣���4�棩�������ߣ�Ҷ���ǡ�JDK8_05��2014��7��10��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�JDK8_25��2016��6��11��
//��2.1.4   ���������ʽ
//1.  �����
//��3�� λ�����
//ͼ2.3  ����&�� | λ����

public class Binary_ͼ2_3 
{
    public static void main(String[] args) 
    {
        System.out.print("ͼ2.3��a�����㣬");
        String op="&";
        int x=173, y=107, z = x & y;
        System.out.println(x+op+y+"="+z);

        System.out.print("ͼ2.3��b�����㣬");
        op="|";                                  //ͼ2.3��b��
        x=-123; y=78; z = x | y;
        System.out.println(x+op+y+"="+z);

        System.out.print("ϰ����ͼ2.2��a�����㣬");
        op="&";
        x=125; y=10; z = x & y;
        System.out.println(x+op+y+"="+z);

        System.out.print("ϰ����ͼ2.2��b�����㣬");
        op="|";
        z = x | y;
        System.out.println(x+op+y+"="+z);

        System.out.println("\nʮ���ƣ������ƣ��˽��ƣ�ʮ������");
        int value[]={173,107,41,-123,78,-49,125};              //ʮ����
        for (int i=0; i<value.length; i++)
             System.out.println(value[i]+"��"+Integer.toBinaryString(value[i])+"��"+
                Integer.toOctalString(value[i])+"��"+Integer.toHexString(value[i]));
        
    }
}
/*
ͼ2.3��a�����㣬173&107=41
ͼ2.3��b�����㣬-123|78=-49
ϰ����ͼ2.2��a�����㣬125&10=8
ϰ����ͼ2.2��b�����㣬125|10=127

ʮ���ƣ������ƣ��˽��ƣ�ʮ������
173��10101101��255��ad
107��1101011��153��6b
41��101001��51��29
-123��11111111111111111111111110000101��37777777605��ffffff85
78��1001110��116��4e
-49��11111111111111111111111111001111��37777777717��ffffffcf
125��1111101��175��7d

*/
//@author��Yeheya��2016-6-11