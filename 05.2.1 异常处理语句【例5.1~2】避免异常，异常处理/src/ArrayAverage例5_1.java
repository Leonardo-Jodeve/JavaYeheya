//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��25��
//��5.2 �쳣�����ʩ
//����5.1������Ӧ������쳣������

public class ArrayAverage��5_1 
{
	public static double average(int[] value)    //����������Ԫ��ƽ��ֵ
	{
		double sum = 0.0;
	    //������value==null��value.length�׳��ն����쳣��i<value.length�����������±�Խ���쳣
		for(int i=0; i<value.length; i++)
			sum += value[i];
		return sum/value.length;                 //��value.length==0��������ΪNaN
	}

	public static void main(String[] args) 
	{
		int[] x = {1,2,3,4};
		System.out.println("average(x)=" + average(x));   //���������Ϊ2.5

		x = null;
		// System.out.println("average(x)="+average(x));  //�ն����쳣��NullPointerException
		
		int[] y = {};                                     //y!=null��y.length==0
		System.out.println("average(y)=" + average(y));   //���ΪNaN����ȷ��ֵ��

		y = new int[0];                                   //y!=null��y.length==0
		System.out.println("average(y)=" + average(y));   //���ΪNaN
        
        System.out.println(""+(3/0));                     //��������������Ϊ0���׳������쳣
        System.out.println(""+(3/0.0));                   //����������������Ϊ0��û���׳��쳣�����н��ΪInfinity�������
        System.out.println(""+(3.0/0));                   //����������������Ϊ0��û���׳��쳣�����н��ΪInfinity�������
        System.out.println("Math.sqrt(-4)="+(Math.sqrt(-4))); //���ΪNaN??

        //���������±�Խ��������
        int[] a={1,2};
        for(int i=0; i<5; i++) 
            System.out.println("  a["+i+"]="+a[i]); 
	}
}
/*
�������н�����£� 
average(x)=2.5 
average(y)=NaN 
average(y)=NaN
  
Infinity Math.sqrt(-4)=NaN
a[0]=1 a[1]=2 Exception in thread "main"
 * java.lang.ArrayIndexOutOfBoundsException: 2 at
 * Outofbounds.main(Outofbounds.java:9)
 */
//@author��Yeheya��2016-8-25��2017��7��17��