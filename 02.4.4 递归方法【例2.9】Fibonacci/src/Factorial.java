//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��11��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��10��14��
//��2.4.4 �ݹ��㷨
//����2.9�� ��n����

public class Factorial
{
    public static int fact(int n)                //��׳�n!��n��0���ݹ鷽��
    {
        if(n==0 || n==1)                         //�ݹ�߽���������������
            return 1;
        return n*fact(n-1);                      //����ͨʽ���ݹ����
    }

    public static void main(String args[])
    {
        int n=5;
        System.out.println(n+"!="+fact(n));      //5!=120
    }
}
/*
�������н�����£�
5!=120      
5!=5*4*3*2*1=120
*/
/*
�������˵�����¡�
��1����2��������ѧ������㷨���������쳣��
        if (n<0)                                 //��n<0���޶��壬�׳���Ч�����쳣
            throw new java.lang.IllegalArgumentException("n="+n);
*/
//author��Yeheya��2016-10-14