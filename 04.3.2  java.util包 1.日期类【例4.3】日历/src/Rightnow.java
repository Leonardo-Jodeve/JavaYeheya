//��Java�������ʵ�ý̳̣���1�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//����4.5�����ϵͳ��ǰ���ں�ʱ�䣬����ָ����ʽ�����

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Rightnow
{
    public static void main(String args[])
    {
        Date today = new Date();                 //��ǰ���ں�ʱ��
        System.out.println("��ǰʱ���� "+today.toString());

        Date time2 = new Date(today.getTime()+10000);
        System.out.println("time2 ="+time2.toString());
        
        long time3=(time2.getTime() - today.getTime())/1000;
        System.out.println("ʱ����� "+time3+"��");
        
        time3=-50381;
        
/*        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��hhʱmm��ss�� a EEEEE");    //�������ڸ�ʽ
        System.out.println("      �� "+sdf.format(today));

        long lg=System.currentTimeMillis();      //��ǰʱ��ĺ�����        
        Date tomorrow = new Date(lg+24*60*60*1000);
        System.out.println("    ������ "+sdf.format(tomorrow));
        
        
        Calendar now = Calendar.getInstance();
        int year =now.get(Calendar.YEAR);                  //���
        int month=now.get(Calendar.MONTH)+1;               //�·�
        int day = now.get(Calendar.DATE);                  //����
        System.out.print("������ "+year+"��"+month+"��"+day+"��");
        int week = now.get(Calendar.DAY_OF_WEEK);          //����
        switch(week)
        {
            case 1: System.out.println("  ������");break;
            case 2: System.out.println("  ����һ");break;
            case 3: System.out.println("  ���ڶ�");break;
            case 4: System.out.println("  ������");break;
            case 5: System.out.println("  ������");break;
            case 6: System.out.println("  ������");break;
            case 7: System.out.println("  ������");break;
        }*/
    }
}

/*

��ǰʱ��=Sun Feb 02 16:17:01 GMT+08:00 2003
      �� 2003��02��02��04ʱ17��01�� PM Sunday
    ����=2003��02��03��04ʱ17��01�� PM Monday
����=2003��2��2��  ������

*/