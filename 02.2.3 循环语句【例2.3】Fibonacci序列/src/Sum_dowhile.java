//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.2 ���̿������
//��2.2.3   ѭ�����
//2.  do-while���
//��do-while������ۼӺͲ���ʾ���㹫ʽ

public class Sum_dowhile
{
    public static void main(String args[])
    {
        int i=1,n=10,sum=0;        
        do 
        {
            sum += i;
            i++;
        } while(i<=n);
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);

        i=0;  sum=0;
        do
        {
            sum += i;
            i++;
        } while(i>=n);			                 //ѭ����ִ��һ��
        System.out.println("sum = "+sum);
        System.out.println("i = "+i);
        
        i=1;  sum=0;                             //��ʾ���㹫ʽ
        System.out.print("Sum("+n+")= ");
        do 
        {
            sum += i;
            System.out.print(i+"+");
            i++;
        } while(i<n);
        System.out.println(i+" = "+(sum+i));
    }
}

/* 
�������н�����£�
sum = 55
i = 11

sum = 0
i = 1

Sum(10) = 1+2+3+4+5+6+7+8+9+10 = 55

*/
//@author��Yeheya��2016-10-14����sum
/*����˵����     
     
    int i=1,n=10,sum=0;
    do 
    {
        sum += i;
        i++;
    } while(i>0);				// ��ѭ����ѭ��������ԶΪtrue

*/
//@author��Yeheya��2016-6-11