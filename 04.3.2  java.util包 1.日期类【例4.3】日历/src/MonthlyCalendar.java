//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��18��
//��4.3.2   java.util���еĹ������
//����4.3������
//ʹ��Java��3�ֱ�ʾ���ں�ʱ�䷽ʽ

import java.util.*;                                        //����ʵ�ð�
import java.text.SimpleDateFormat;                         //�����ı����еļ����ڸ�ʽ��

public class MonthlyCalendar
{
    public static void main(String[] args)
    {
        String datestr="yyyy��MM��dd��E HHʱmm��ss��";     //a:�����磬hh:12Сʱ��
        SimpleDateFormat datef = new SimpleDateFormat(datestr);//����ʱ���ʽ
        System.out.print("������"+datef.format(new Date()));   //��ǰ���ں�ʱ��

        long now=System.currentTimeMillis();                //��ǰʱ��ĺ�����
        datef = new SimpleDateFormat("yyyy��MM��dd��E");     //���ڸ�ʽ
        System.out.println("��������"+datef.format(new Date(now+24*60*60*1000)));
        MonthlyCalendar.print(new GregorianCalendar());    //������µ�����
        
        //��˼����4-5�� �� ������µ�������
        Calendar calendar = Calendar.getInstance();        //��ǰ���ں�ʱ��
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);//���������·ݣ�����Զ��仯//set(int field, int value)����field��ʾ����ֵ
        MonthlyCalendar.print(calendar);                   //���ָ���·ݵ�����
//        for(int i=0; i<10; i++)                  
//        {
//            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);//���������·ݣ�����Զ��仯����2016��12�¡�������Ϊ��2017��1�¡�
//            MonthlyCalendar.print(calendar);               //���ָ���·ݵ�����
//        }
    }
    
    public static void print(Calendar calendar)            //���ָ���·ݵ�����
    {
        int year =calendar.get(Calendar.YEAR);             //��
        int month=calendar.get(Calendar.MONTH)+1;          //��
        calendar.set(year, month-1, 1);                    //����Ϊ����1��
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;   //����1�������ڼ�

        System.out.println(year+"��"+month+"�µ�����\n  ��  һ  ��  ��  ��  ��  ��");
/*ͬ��Ч��
        String str="��һ����������";
        for (int i=0; i<str.length(); i++)
            System.out.print(String.format("%4c", str.charAt(i)));
    	System.out.println();*/
        
        if(week>0)
            System.out.print(String.format("%"+4*week+"c", ' ')); //ǰ���ո�
        int days = MyDate.daysOfMonth(year, month);        //��������µ�����
        for(int i=1; i<=days; i++)                        //�������
        {
            System.out.print(String.format("%4d", i));
            if((week+i)%7==0)
                System.out.println();
        }
        System.out.println();
    }
}
/*
�������н�����£�
������2018��03��28�������� 15ʱ01��13�룬������2018��03��29��������
2018��3�µ�����
  ��  һ  ��  ��  ��  ��  ��
                   1   2   3
   4   5   6   7   8   9  10
  11  12  13  14  15  16  17
  18  19  20  21  22  23  24
  25  26  27  28  29  30  31

2018��4�µ�����
  ��  һ  ��  ��  ��  ��  ��
   1   2   3   4   5   6   7
   8   9  10  11  12  13  14
  15  16  17  18  19  20  21
  22  23  24  25  26  27  28
  29  30

2016��08��19�������� ���� 05ʱ59��15�룬������2016��08��20��������
2016��8�µ�����
   ��   һ   ��   ��   ��   ��   ��
       1   2   3   4   5   6
   7   8   9  10  11  12  13
  14  15  16  17  18  19  20
  21  22  23  24  25  26  27
  28  29  30  31
2016��9�µ�����
   ��   һ   ��   ��   ��   ��   ��
                   1   2   3
   4   5   6   7   8   9  10
  11  12  13  14  15  16  17
  18  19  20  21  22  23  24
  25  26  27  28  29  30

//����1�������ڼ���Ҳ��
        int day=calendar.get(Calendar.DATE);          //��
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1; //���ڼ�
        int first = (week - day%7 +1+7)%7;       //����1�������ڼ�
        
2016��10��12�������� ���� 02ʱ45��32�룬������2016��10��13��������
2016��10�µ�����
  ��  һ  ��  ��  ��  ��  ��
                           1
   2   3   4   5   6   7   8
   9  10  11  12  13  14  15
  16  17  18  19  20  21  22
  23  24  25  26  27  28  29
  30  31

������2018��10��09�����ڶ� 10ʱ52��47�룬������2018��10��10��������
2018��10�µ�����
  ��  һ  ��  ��  ��  ��  ��
       1   2   3   4   5   6
   7   8   9  10  11  12  13
  14  15  16  17  18  19  20
  21  22  23  24  25  26  27
  28  29  30  31
  
*/
//@author��Yeheya��2016��08��19�գ�2016��10��12�գ�2018��03��28�գ�2018��10��9��