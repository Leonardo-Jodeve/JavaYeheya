//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.1.4   ���������ʽ
//1.  �����
//��1�� ����λ�����
//����2.2��  �ж�һ������Ƿ�Ϊ���ꡣ

public class LeapYear
{
    public static void main(String[] args) 
    {
        int year=2016;
        boolean leap = year%400==0 || year%100!=0 && year%4==0;
                             //�ȼ�����ʽ���ٸ�ֵ������·���ܵ��߼����㣬���߼���������ִ���������㣬�˴��ȼ���||���ټ���&&
        System.out.println(year+" is a leap year��"+leap);
    }
}
/* 
�������н�����£�
2000 is a leap year��true
2016 is a leap year��true

        boolean leap = year%400==0 | year%100!=0 & year%4==0;
        //��ȷ���߼����㣬û�ж�·���ܣ�|���ȼ�����&���ȼ���&���ټ���|����ε���֪������
*/
//@author��Yeheya��2016-6-11
