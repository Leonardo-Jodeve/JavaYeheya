//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.2 ���̿������
//��2.2.3   ѭ�����
//1. while���
//��while������ۼӺ͡�

public class Sum_while
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;
        while(i<=n)
        {
            sum += i;
            i++;             	                 //�ı�ѭ������
        }                                        //ѭ��������i=11��sum=55
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        i=1;  sum=0;
        while(i>=n)			                     //ѭ����һ��Ҳ��ִ��
            sum += i++;
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        //˼����1
        i=0;n=10;sum=0;
        while(i<n)
        {
            i++;
            sum += i;
        }
        System.out.println("Sum = 1+...+"+n+" = "+sum);

        //˼����2����ʾ���㹫ʽ
        i=1;n=16;sum=0;
        System.out.print("Sum("+n+")= ");
        while(i<n)
        {
            sum += i;
            System.out.print(i+"+");
            i++;
        }
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
�������н�����£�
sum = 55
i = 11

sum = 0
i = 1

Sum = 1+...+10 = 55

Sum(8) = 1+2+3+4+5+6+7+8 = 36
Sum(16)= 1+2+3+4+5+6+7+8+9+10+11+12+13+14+15+16 = 136
*/

/*����˵����     
int i=1,n=10,sum=0;
while(i<=n)				// ��ѭ��
    sum += i;
*/
//@author��Yeheya��2016-10-14����sum