//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.2 ���̿������
//��2.2.3   ѭ�����
//3.  for���
//����2.4�� ��һ�����ڣ������գ���Ӧ�������ڼ���

public class ChineseWeek
{
    public static void main(String[] args) 
    {
        int year=2016, month=12, day=31;
        boolean leap = year%400==0 || year%100!=0 && year%4==0;  //�ж�����
        int total = year-1980+(year-1980+3)/4;             //��ƽ�������ۼƵ�������
        for(int i=month-1; i>0; i--)                       //���㵱��ǰmonth-1�����ۼƵ�����
        {
            switch(i)                      
            {
                case 1: case 3: case 5: case 7: case 8: case 10: total+=31; break;
                case 4: case 6: case 9: case 11: total+=30; break;
                case 2: total+= leap ? 29 : 28;
            }
        }
        total+=day;                                        //���µ�����
        int week=1;                                       //��ʼ�� 1979-12-31������һ
        week = (week+total) % 7;                           //������ڼ�
        System.out.print(year+"��"+month+"��"+day+"������");
        switch(week)
        {
            case 0: System.out.println("��"); break;
            case 1: System.out.println("һ"); break;
            case 2: System.out.println("��"); break;
            case 3: System.out.println("��"); break;
            case 4: System.out.println("��"); break;
            case 5: System.out.println("��"); break;
            case 6: System.out.println("��"); break;
        }
        
        //2.5 �ַ���
        String str="��һ����������";
        System.out.println("����"+str.substring(week, week+1));
    }
}

/* 
�������н�����£�
2009��7��13������һ
����һ
2012��9��8��������
������
2016��12��31��������
������

*/
//@author��Yeheya��2016-6-11