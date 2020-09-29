//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��10��12��
//��4.3.2   java.util���еĹ������
//����4.3������
//Calendar���о�

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendars 
{
    //����calendarΪ���£�����Զ��仯�����硰2017��1�¡�������Ϊ��2016��12�¡�
    public static void previosMonth(Calendar calendar)
    {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
    }
    //����calendarΪ���£�����Զ��仯�����磬��2016��12�¡�������Ϊ��2017��1�¡�
    public static void nextMonth(Calendar calendar)
    {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
    }
    
    //����calendarΪ���죬�·ݡ�����Զ��仯�����磬��2017��1��1�ա�������Ϊ��2016��12��31�ա�
    public static void yestoday(Calendar calendar)
    {
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
    }
    //����calendarΪ���죬�·ݡ�����Զ��仯�����磬��2016��12��31�ա�������Ϊ��2017��1��1�ա�
    public static void tomorrow(Calendar calendar)
    {
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
    }
    
    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");//����ʱ���ʽ
        Calendar calendar = Calendar.getInstance();        //��ǰ���ں�ʱ��
        System.out.println("������"+sdf.format(calendar.getTime()));  //��������

        int year =calendar.get(Calendar.YEAR);             //��
        calendar.set(year, 11, 31);
        System.out.println(sdf.format(calendar.getTime()));
        nextMonth(calendar);
        System.out.println("������"+sdf.format(calendar.getTime()));
        previosMonth(calendar); 
        System.out.println("������"+sdf.format(calendar.getTime()));

        tomorrow(calendar);
        System.out.println("������"+sdf.format(calendar.getTime()));
        yestoday(calendar);
        System.out.println("������"+sdf.format(calendar.getTime()));
    }
}
/*
������2016��10��12��
2016��12��31��
������2017��01��31��
������2016��12��31��
������2017��01��01��
������2016��12��31��
*/ 
//@author��Yeheya��2016-10-12