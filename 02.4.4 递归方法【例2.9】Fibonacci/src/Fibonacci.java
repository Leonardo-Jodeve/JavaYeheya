//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��11��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��10��14��
//��2.4.4 �ݹ��㷨
//����2.9�� ��Fibonacci���е�n��ĵݹ鷽����

public class Fibonacci
{
    public static int fib(int n)                 //����Fibonacci���е�n����0����ݹ鷽��
    {
        if(n==0 || n==1)                         //�ݹ�߽���������������
            return n;
        return fib(n-2)+fib(n-1);                //����ͨʽ���ݹ����
    }
    
    public static void main(String[] args)
    {
        for (int i=0; i<=25; i++)
            System.out.print(" "+fib(i));
        System.out.println();
    }
}
/*
�������н�����£�
 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 75025
*/
/*
�������˵�����¡�
��1����2��������ѧ������㷨���������쳣��
if (n<0)                                 //��n<0���޶��壬�׳���Ч�����쳣
    throw new java.lang.IllegalArgumentException("n="+n); //�쳣����5��

 */
//author��Yeheya��2016-10-14