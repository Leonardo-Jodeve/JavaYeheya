//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2019��1��28��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��3������MyInteger.parseInt(str,radix)�������ܣ��÷����̲�ûд��
//��java.lang.Integer.parseInt(str,radix)����������ͬ���˽��ƺ�ʮ������û��ǰ׺��

public class MyInteger_parseInt_radix 
{
    public static void main(String args[])
    {
        //���µ���MyInteger.toString(i,radix)��MyInteger.parseInt(str,radix)������
    	//����������Ķ��ֽ��Ʋ����ַ������ٽ������Ʋ����ַ���ת����������
    	//MyInteger.toString(i,radix)�������˽��ƺ�ʮ������û��ǰ׺��
    	//�����ı�ʾ��ʽ�У�������ʮ���ơ��˽��ơ�ʮ������
//        int[] values={0x80000000,-1000,-123,-1,0,123,0256,0x3e8,+1024,0xabcedf,0x7fffffff};
        int[] values={-123,0xfedcba};
        int[] radixs={2,4,8,16};                           //���Ƽ���
        for(int i=0; i<values.length; i++)
        {
            for(int j=0; j<radixs.length; j++)
            {
        	    String str = MyInteger.toString(values[i],radixs[j]);//���룬�˽��ƺ�ʮ������û��ǰ׺
                System.out.print("MyInteger.toString("+values[i]+","+radixs[j]+")=\""+str+"\"��");
                System.out.println("MyInteger.parseInt(\""+str+"\","+radixs[j]+")="+MyInteger.parseInt(str,radixs[j]));
            }
            System.out.println();
        }
    }
}
/*
�������н�����£�
MyInteger.toString(-123,2)="11111111111111111111111110000101"��MyInteger.parseInt("11111111111111111111111110000101",2)=-123
MyInteger.toString(-123,4)="3333333333332011"��MyInteger.parseInt("3333333333332011",4)=-123
MyInteger.toString(-123,8)="37777777605"��MyInteger.parseInt("37777777605",8)=-123
MyInteger.toString(-123,16)="ffffff85"��MyInteger.parseInt("ffffff85",16)=-123

MyInteger.toString(16702650,2)="00000000111111101101110010111010"��MyInteger.parseInt("00000000111111101101110010111010",2)=16702650
MyInteger.toString(16702650,4)="0000333231302322"��MyInteger.parseInt("0000333231302322",4)=16702650
MyInteger.toString(16702650,8)="00077556272"��MyInteger.parseInt("00077556272",8)=16702650
MyInteger.toString(16702650,16)="00fedcba"��MyInteger.parseInt("00fedcba",16)=16702650

*/
//@author��Yeheya��2019��1��28��