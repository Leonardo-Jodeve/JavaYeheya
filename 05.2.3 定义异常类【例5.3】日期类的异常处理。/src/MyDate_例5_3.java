//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��25��
//��3.2  ��ķ�װ��
//����3.2��  ��װ�������ࡣ//��˼����3-3�� MyDate�����ӹ��ܡ�
//��5.2.3 �����쳣��
//����5.3���Զ����쳣�࣬��������쳣����

class MyDate_��5_3 
{

    //main()����û�в��񲢴����쳣�����׳���Java�����
    public static void main(String args[]) throws NumberFormatException, DateFormatException
    {
        MyDate date = new MyDate("2016��2��29��");
        MyDate today = MyDate.today();           //��ǰ����
        System.out.println(today+"��"+date+"���"+today.yearsBetween(date)+"�꣬���"+
                today.monthsBetween(date)+"�£����"+today.daysBetween(date)+"��");
        int n=30;
        System.out.println(today+"֮��"+n+"���������"+today.daysAfter(n)+"");
//        n=431+366;
        System.out.println(today+"֮ǰ"+n+"���������"+today.daysBefore(n)+"");
        
        //�����׳��쳣
        new MyDate("2017��2��29��");  //�׳�DateFormatException: 2017��2��29�գ����ڴ���
        new MyDate("2017��2x��29��");   //�׳�java.lang.NumberFormatException: For input string: "2x"
    }
/*
    //main()�������񲢴����쳣����ʾ���ͬ��
    public static void main(String args[])
    {
        try
        {
            new MyDate("2017��2x��29��"); 
        }
        catch (NumberFormatException ex)         //������ֵ��ʽ�쳣
        {
            System.out.println(ex.toString());   //��ʾ�����������쳣��Ϣ
        }
        catch (DateFormatException ex)           //�������ڸ�ʽ�쳣
        {
            System.out.println(ex.toString());   //��ʾ�����������쳣��Ϣ
        }
        catch (Exception ex)                     //�������������쳣
        {
            ex.printStackTrace();
        }
    }*/
}
/*
�������н�����£�
2017��02��07����2016��02��29�����1�꣬���12�£����344��
2017��02��07��֮��30���������2017��03��09��
2017��02��07��֮ǰ30���������2017��01��08��
Exception in thread "main" DateFormatException: 2017��2��29�գ����ڴ���
    at MyDate.set(MyDate.java:354)
    at MyDate.<init>(MyDate.java:369)
    at MyDate_��5_2.main(MyDate_��5_2.java:20)


    2008��8��8����2009��10��5�����-1�꣬���-14�£����-423��
    2010��3��7����2000��1��1�����10�꣬���122�£����3718��
    2010��1��1����2000��1��1�����10�꣬���120�£����3653��
    2010��8��17����1994��8��17�����16�꣬���192�£����5844��

    2000��1��1��֮��366���������2001��1��1��
    1994��8��17��֮��5844���������2010��8��17��

    2010��3��7��֮ǰ797���������2007��12��31��
    2010��8��17��֮ǰ5844���������1994��8��17��

    2013��02��17����1994��08��17�����19�꣬���222�£����6759��
    2013��02��17��֮��30���������2013��03��19��
    1994��08��17��֮ǰ30���������1994��07��18��
    Exception in thread "main" DateFormatException: 2013��2��29�գ����ڴ���
        at MyDate.set(MyDate.java:352)
        at MyDate.<init>(MyDate.java:362)
        at MyDate_ex.main(MyDate.java:398)

*/
//@author��Yeheya��2016-8-11��2017��2��8��