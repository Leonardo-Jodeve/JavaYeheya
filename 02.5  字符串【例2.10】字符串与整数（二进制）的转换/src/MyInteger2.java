//��Java�������ʵ�ý̳̣���6�棩�� ���ߣ�Ҷ���ǣ�2019��1��28��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��2��������ת����radix���Ʋ����ַ���
//��2ȡ�෨
//�ر�˵������java.lang.Integer���ͬ���������ܲ�ͬ��

public class MyInteger2 
{
	public static String toBinaryString(int value) 	//����������value�Ķ������ַ�������2ȡ�෨
	{
	    String str="";
	    while(value>0) 
	    {
	        str = value%2 + str;                 //�⼴str=(char)(value%2+'0')+str��
	                                          	 //ȡ�࣬value%2��ö����Ƹ�λ�������������ӳ��ַ���
	        value /= 2;                          //����2
	    }
	    return str;                             	//�����ַ���
	}	
	
    public static void main(String args[])
    {
        int[] values = {-123,123};
        for(int i=0; i<values.length; i++)
            System.out.println("MyInteger2.toBinaryString("+values[i]+")="+MyInteger2.toBinaryString(values[i]));
    }
}
/*
�������н�����£�
MyInteger2.toBinaryString(-123)=
MyInteger2.toBinaryString(123)=1111011

*/
//@author��Yeheya��2019��1��28��