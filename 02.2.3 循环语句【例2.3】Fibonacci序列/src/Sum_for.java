//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.2 ���̿������
//��2.2.3   ѭ�����
//3.  for���
//��for������ۼӺͲ���ʾ���㹫ʽ��

public class Sum_for
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;
        for(i=1; i<=n; i++)                      //ѭ�����Ʊ��������仯
            sum += i;
        System.out.println("Sum = 1+...+"+n+" = "+sum);
        
        System.out.print("Sum("+n+") = ");       //��ʾ���㹫ʽ
        for(i=n,sum=0; i>1; i--)                 //ѭ�����Ʊ����ݼ��仯
        {
            sum += i;
            System.out.print(i+"+");
        }
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
�������н�����£�
Sum = 1+...+10 = 55
Sum(10) = 10+9+8+7+6+5+4+3+2+1 = 55

        int n=10,sum=0;
        for(int i=1,j=1; i<=n; i++,j++)         //�������ڷָ����ʽ
            sum += i;
        System.out.println("Sum = 1+...+"+n+" = "+sum);

*/
//@author��Yeheya��2016-10-14����sum