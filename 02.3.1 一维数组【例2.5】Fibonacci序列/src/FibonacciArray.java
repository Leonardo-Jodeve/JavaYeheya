//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.3.1  һά����
//����2.5�� ��һά���鱣��Fibonacci����ֵ��

public class FibonacciArray
{
    public static void main(String[] args) 
    {
        int n=25,  fib[]=new int[n];
        fib[0]=0;                                //��������ֵ
        fib[1]=1;
        for(int i=2; i<n; i++)
            fib[i] = fib[i-1] + fib[i-2];        //ÿ��ֵ����ǰ����ֵ֮��
        for(int i=0; i<fib.length; i++)          //���һά����
            System.out.print(fib[i]+" ");
        System.out.println();

        for(int value : fib)                     //��Ԫѭ����value�������fib�����е�ÿ��Ԫ��
            System.out.print(value+" ");
        System.out.println();
    }
}
/* 
�������н�����£�
0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 
*/
//@author��Yeheya��2016-6-11