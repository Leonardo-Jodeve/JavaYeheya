//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��25��
//��3.2  ��ķ�װ��
//����3.2��  ��װ�������ࡣ
//��Ȼ�������������û��ʹ��3��������Ϊ���ڵĳ�Ա����������������
//����Ϊ������Ŀ���ǣ�һ��Ϊ����ʾ���ڼ�����㷨��
//����ÿ�ֳ���������������ڹ������ǲ�����ת����������������Ȼ�鷳��ȴ�ǹ�ͬ���õġ�
//��˼����3-3�� MyDate�����ӹ��ܡ�
//java.util.Date��Calendar���ܲ���������ĺܶ෽����Ȼ��Ҫͨ�������ս��бȽϣ�����д��MyDate����
//Ϊ��6�������������� �����׼��
//����5.3�� ��������쳣����

import java.util.*;
import java.text.SimpleDateFormat;

//�����࣬���У���Դ�����ļ�ͬ��//���л�����8.3
public class MyDate implements Comparable<MyDate>, java.io.Serializable
{ 
    private int year,month,day;                  //�����գ�˽�г�Ա����
    private static int thisYear=2018;            //��ǰ��ݣ�˽�о�̬��Ա����
    static                                       //��̬��Ա������ʼ��
    {
        thisYear=2018;
//        thisYear = java.util.Calendar.getInstance().get(Calendar.YEAR); //��õ�ǰ���ڶ����е����ֵ��4.3.2��
//        thisYear = new java.util.GregorianCalendar().get(Calendar.YEAR); //��õ�ǰ���ڶ����е����ֵ��4.3.2��
    } 
    public MyDate()                                        //�޲������췽����ָ��ȱʡ���ڣ�����
    {
        this(1970,1,1);                                    //���ñ������������������췽�� 
    } 
  
    //���췽����ָ�����ڡ�//��3����3.2��û���׳��쳣
    //���췽�������������ܹ������ڣ��������޷������쳣�����׳����ڸ�ʽ�쳣��//��5����5.2���׳��쳣 
    public MyDate(int year, int month, int day) throws DateFormatException
    {
//      super();                                         //Ĭ�ϵ���Object()
    	this.set(year, month, day);                        //��3.2�����ñ���ĳ�Ա������//��5.2���÷��������׳����ڸ�ʽ�쳣
    } 

    //�������췽��������ͬ���������ء�
    public MyDate(MyDate date) throws DateFormatException
    {
        this.set(date);
    }
  
/*    public void set(int year, int month, int day)        //��������ֵ���㷨��ȫ���Ľ���5.2.3��
    {
        this.year = year;                                  //this.yearָ��ǰ����ĳ�Ա������yearָ����
        this.month = (month>=1 && month<=12) ? month : 1;  //this���ò���ʡ��
        this.day = (day>=1 && day<=31) ? day : 1;
    } */
    
    public void set(MyDate date)                           //��������ֵ������
    {
        this.set(date.year, date.month, date.day);         //����ͬ����Ա����������ʹ��this()
    }
    //5.2.3�ڣ���5.2 �˷�������Ҫ�׳����ڸ�ʽ�쳣//throws DateFormatException
    //��Ϊ��date���������ڣ�û�д���

    public int getYear()                                   //������
    {
        return this.year;
    }
    public int getMonth()                                  //����·�
    {
        return this.month;
    }
    public int getDay()                                    //��õ�������
    {
        return this.day;
    }
  
    public String toString()                               //�������ڸ�ʽ�ַ�����2λ����
    {
        return this.year+"��"+String.format("%02d", this.month)+"��"+
               String.format("%02d", this.day)+"��";//+this.toWeekString();
    }

    public static int getThisYear()                        //��ý�����ݣ���̬����
    {
        return thisYear;                                   //���ʾ�̬��Ա����
    }

    public static boolean isLeapYear(int year)             //�ж�ָ������Ƿ����꣬��̬����
    {
        return year%400==0 || year%100!=0 && year%4==0;
    }
    public boolean isLeapYear()                            //�жϵ�ǰ���ڵ�����Ƿ����꣬����
    {
        return isLeapYear(this.year);                      //���þ�̬����
    }        

    //�Ƚ���this��date�������õ�ʵ��ֵ�Ƿ���ȡ�
    //��this��date����ͬһ��ʵ��������ȣ������Ƿֱ���������
    //ʵ������ֱ�Ƚ����ǵĸ���Ա����ֵ�Ƿ��Ӧ���
    public boolean equals(MyDate date)
    {
//        System.out.println(b+"��ִ��equals(MyDate date)����");
        //thisָ�����õ�ǰ�����Ķ���this.year�ȷ��ʵ�ǰ����ĳ�Ա��������3��this��ʡ�ԣ�
        return this==date ||           //��this��date����ͬһ��ʵ���������
               date!=null && this.year==date.year && this.month==date.month && this.day==date.day;
               //��this��date�ֱ���������ʵ������ֱ�Ƚ����ǵĸ���Ա����ֵ�Ƿ��Ӧ���
    } 
    
    public static int daysOfMonth(int year, int month)     //����ָ�����µ���������̬����
    {
        switch(month)                           //����ÿ�µ�����
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:  return 31; 
            case 4: case 6: case 9: case 11:  return 30;
            case 2:  return MyDate.isLeapYear(year) ? 29 : 28;
            default: return 0;
        }
    }
    public int daysOfMonth()                     //���ص�������
    {
        return daysOfMonth(this.year, this.month);
    } 

    //��4��
    public void tomorrow4()                      //��this����ʵ�������ڸ�Ϊ֮��һ�����ڣ�û�з���ֵ
    {
        this.day++;
        if(this.day>this.daysOfMonth())
        {
            this.day=1;
            this.month++;
            if(this.month>12)
            {
                this.month=1;
                this.year++;
            }
        }
    }   

    //��5�棬����%
    public void tomorrow()                       //��this����ʵ�������ڸ�Ϊ֮��һ�����ڣ�û�з���ֵ
    {
        this.day = this.day%this.daysOfMonth()+1;
        if(this.day==1)
        {
            this.month = this.month%12+1;        //����
            if(this.month==1)                   //12�µ�����������1��
                this.year++;
        }
    }    

    //��4��
    public MyDate yestoday4()                    //���ص�ǰ���ڵ�ǰһ������
    {
        MyDate date = new MyDate(this);          //ִ�п������췽��������ʵ����û�иı�this
        date.day--;
        if(date.day==0)
        {
            date.month--;
            if(date.month==0)
            {
                date.month=12;
                date.year--;
            }
            date.day = daysOfMonth(date.year, date.month);
        }
        return date;                             //���ض���date���õ�ʵ��
    }   
    
    //��5�棬����%
    public MyDate yestoday()                     //���ص�ǰ���ڵ�ǰһ������
    {
        MyDate date = new MyDate(this);          //ִ�п������췽��������ʵ����û�иı�this
        date.day--;
        if(date.day==0)
        {
            this.month = (this.month-2+12)%12+1; //����
            if(this.month==12)                   //1�µ�������ȥ��12��
                this.year--;
//            date.previousMonth();
        }
        return date;                             //���ض���date���õ�ʵ��
    }   
    
    //��3.2  main()

    
    //3.4.4   ��̬�ķ���ʵ��
    //��˼����3-5������3.17��
    public boolean equals(Object obj)            //����Object���equals()����
    {
        if(this==obj)                           //thisָ�����õ�ǰ�����Ķ���
            return true;
        if(obj instanceof MyDate)               //�жϵ�ǰ�����Ƿ�����MyDate��
        {
            MyDate date = (MyDate)obj;           //����ǿ��ת��
            return this.year==date.year && this.month==date.month && this.day==date.day;
        }
        return false;
    }    
    
    
    //��Java�������ʵ�ý̳̣���5�棩ϰ������ʵ��ָ����
    //��ϰ3.3��MyDate������daysAfter(int n)������//��5��Ľ���ʹ��%����
    public MyDate daysAfter(int n)                         //���ص�ǰ����֮��n�������
    {
        if(n<0)
            return daysBefore(-n);                         //�㷨���ƣ���������ʡ��
    
        MyDate date = new MyDate(this);                    //ִ�п������췽�������Ƶ�ǰ����
        while(n>0)
            if(date.day+n<=date.daysOfMonth())            //����
            {
                date.day+=n;
                n=0;
            }
            else                                           //����
            {
                n-=date.daysOfMonth()-date.day;
                date.day=0;
                date.month++;
                if(date.month>12)
                {
                    date.month=1;
                    date.year++;
                    while(n>365)                           //�������
                    {
                        n-= date.isLeapYear() ? 366 : 365;
                        date.year++;
                    }
                }
            }
//            System.out.println("n="+n);
        return date;
    }
    
    //��Java�������ʵ�ý̳̣���5�棩����⡷
    //��˼����3-3��MyDate�����ӵķ�����
    public static int daysOfYear(MyDate date)              //����date���ڵ�����ۼ�����
    {
        int sum=0;
        int days[]={31,28,31,30,31,30,31,31,30,31,30,31};  //ƽ��ÿ�µ�����
        for(int i=0; i<date.month-1; i++)
            sum+=days[i];
        if(date.isLeapYear() && date.month>2)              //����2�¶�һ��
            sum++;
        sum+=date.day;                                     //���µ�����
        return sum;
    }    
    
    public int daysOfYear()                                //���ص�ǰ���ڵ�����ۼ�����
    {
        return MyDate.daysOfYear(this);
    }
    public int getWeek()                             	   //���ص�ǰ���ڶ�Ӧ�����ڼ�����ΧΪ0��6
    {
        int total = year-1980+(year-1980+3)/4;             //��ƽ(��)���ۼƵ�������
        total += this.daysOfYear();                        //�ӵ�����ۼ�����
        return (1+total) % 7;                              //������ڼ�����ʼ�� 1979-12-31������һ
    }//����Java���������1970-1-1
    public String toWeekString()                       	   //���ص�ǰ���ڶ�Ӧ���ڼ��������ַ���
    {
        int week =this.getWeek();                          //������ڼ�
        return "����"+"��һ����������".substring(week, week+1);
    }
    
    public MyDate daysBefore(int n)                       //���ص�ǰ����֮ǰn�������
    {
        if(n<0)
            return daysAfter(-n);
    
        MyDate date = new MyDate(this); 
        while(n>0)
            if(date.day-n>0)                              //����
            {
                date.day-=n;
                n=0;
    		}
            else                                           //����
            {
                n-=date.day;
                date.month--;
                if(date.month==0)
                {
                    date.month=12;
                    date.year--;
                    while(n>365)                          //�������
                    {
                    	n-= date.isLeapYear() ? 366 : 365;
                    	date.year--;
                    }
                }
                date.day=date.daysOfMonth();
            }
//           System.out.println("n="+n);
        return date;
    }
    
    //��4�� 4.3.1 java.lang.Comparable�ɱȽϽӿ�
    public int compareTo(MyDate date)                         //Լ���Ƚ����ڴ�С�Ĺ��򣬷���-1��0��1
    {
        if(this==date || date!=null && this.year==date.year && this.month==date.month && this.day==date.day)
            return 0;
        return (this.year>date.year || this.year==date.year && this.month>date.month
            || this.year==date.year && this.month==date.month && this.day>date.day) ? 1 : -1; 
    }
    
    //����4.4�����¶������Լ�����ֱȽ��������ڴ�С�Ĺ���
    public int yearsBetween(MyDate date)                   //����this��date������������
    {
        return this.year - date.year;
    }
    public int monthsBetween(MyDate date)                  //����this��date������������
    {
        return yearsBetween(date)*12+this.month-date.month;
    }

    public static int daysBetween(MyDate d1, MyDate d2)    //����d1��d2������������
    {
        if(d1.compareTo(d2)==1)
            return daysBetween(d2, d1)*-1;                 //�ݹ����һ�Σ�������������
        
        int sum=0;
        if(d1.year == d2.year)                             //ͬ��
        {
            sum=d1.daysOfMonth() - d1.day;
            for(int m=d1.month+1; m<d2.month; m++)         //����d1.month<d2.month����
                sum+=daysOfMonth(d1.year, m);
            sum+=d2.day;
        }
        else                                               //����d1.year < date.year����
        {
            sum=(isLeapYear(d1.year) ? 366 : 365)-daysOfYear(d1);    //��d1����ʣ�������
            for(int y=d1.year+1; y<d2.year; y++)          //����d1+1��d2-1����ۼ�����
                sum+=isLeapYear(y) ? 366 : 365;
            sum+=daysOfYear(d2);                           //��d2������ۼ�����
        }
        return sum*-1;
    }
    public int daysBetween(MyDate date)                    //����this��date������������
    {
        return daysBetween(this, date);      
    }
    
    //��4�� 4.3.2 java.util������//��5��̲�ûд���μ��д�
    public static MyDate today()                           //���ص�������
    {
        Calendar now = java.util.Calendar.getInstance();   //����Calendarʵ������õ������ں�ʱ��
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DATE);                  //DAY_OF_MONTH��DATEͬ��
        return new MyDate(year, month, day);
    } 
    
    
    //��5�£��쳣����//��5.2.3 �����쳣��
    //�������ڣ����������ܹ������ڣ����׳����ڸ�ʽ�쳣
    public void set(int year, int month, int day) throws DateFormatException
    {
        String str = year+"��"+month+"��"+day+"�գ�";
        if(year<=-2000 || year>2500)
            throw new DateFormatException(str+"��ݲ����ʣ���Ч���Ϊ-2000��2500��");
        if(month<1 || month>12)
            throw new DateFormatException(str+"�·ݴ���");
        if(day<1 || day>MyDate.daysOfMonth(year, month))
            throw new DateFormatException(str+"���ڴ���");
        this.year = year;
        this.month = month;
        this.day = day;
    }   

    //���췽������datestr�ַ����������ڣ�Ĭ�������ַ�����ʽΪ"yyyy��MM��dd��"��
    //��datestr���������Ӵ�����ת�������������׳���ֵ��ʽ�쳣�������ܹ������ڣ����׳����ڸ�ʽ�쳣
    public MyDate(String datestr) throws NumberFormatException, DateFormatException
    {
        if(datestr.isEmpty())
            throw new DateFormatException("�մ������ڴ���");
        
        int i=datestr.indexOf('��'), j=datestr.indexOf('��',i), k=datestr.indexOf('��',j);
                                       //��datestr���д�i��ʼ����ָ���ַ��������ַ�����Ϊ1
        int year = Integer.parseInt(datestr.substring(0,i));     //��
        int month= Integer.parseInt(datestr.substring(i+1,j));   //��
        int day  = Integer.parseInt(datestr.substring(j+1,k));   //��
        this.set(year, month, day);    //���������ܹ������ڣ����׳����ڸ�ʽ�쳣
    } 
}
/*
    //��5�治Ҫ��
    //�޸�thisΪ����ͬ�ա���day������ȡ���һ�죬�硰12��31�ա��������ǡ�11��30�ա���
    //��6.6��������˼���⣬�ò�����
    public void nextMonth()
    {
        this.month = this.month%12+1;            //����
        if (this.month==1)                       //12�µ�����������1��
            this.year++;
        if (this.day>this.daysOfMonth())         //��day��������ȡ���һ�죬��6.6��
            this.day=this.daysOfMonth();
    }
//  public static void nextMonth(int year, int month)      //���£����У��޷�������������
    //�޸�thisΪ����ͬ�ա���day������ȡ���һ�죬�硰10��31�ա��������ǡ�9��30�ա�
    public void previousMonth()
    {
        this.month = (this.month-2+12)%12+1;     //����
        if (this.month==12)                      //1�µ�������ȥ��12��
            this.year--;
        if (this.day>this.daysOfMonth())         //��day��������ȡ���һ�죬��6.6��
            this.day=this.daysOfMonth();
    }

    //���ַ���strת�������ڣ�Ĭ�������ַ�����ʽΪ"yyyy��MM��dd��"������Ҳ��1λ
    public static MyDate parseDate(String str) throws NumberFormatException, DateFormatException
    {
        return new MyDate(str);
    }//�˷���û�����ã���Ϊ���������ʵ����ͬ�Ϲ��췽������5��̲�ɾ��
    
    //ʵ��5�����·���û�����ˡ�
    public MyDate(String year, String month, String day) throws NumberFormatException, DateFormatException //���췽��
    {
         this.set(year, month, day);
    }
    public void set(String year, String month, String day) throws NumberFormatException, DateFormatException //��������
    {
        this.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)); 
    }
 
    //��4��ʵ��5-1����5�治Ҫ��
//public MyDate(String s, String format) throws NumberFormatException,DateFormatException      //formatָ�����ڸ�ʽ

/*
�������˵�����¡�
1���������в����Ĺ��췽����û������MyDate()ʱ��ԭ���޲����Ĺ��췽������Ч�ˡ�
        MyDate d1 = new MyDate();             //�����,cannot find symbol symbol  : constructor MyDate()

    public void set(MyDate date)              //���еĳ�Ա��������������ֵ,����
    {
        this(date.year, date.month, date.day);   //�����,����ʹ��this(),call to this must be first statement in constructor
    } 

2��û�и���toString()ʱ���У�������£�
date is Data2@757aef
date is Data2@d9f9c3��

3��û�и���equals()ʱ���У�������£�
aday4.equals(aday1)  false       //��������������ֵ��ͬ
aday3.equals(aday2)  false       //������ɿ������췽����������һ������ 

ֻ��������������ͬһ������ʱ������Ŷԡ����磬
        MyDate aday5 = aday2;
aday5.equals(aday2)  false       //�����ȷ��������������ͬһ��ʵ��

4���������£����У���ΪCalendar��set(year,month,day)�Ⱥܶ෽������Ϊfinal�����ܱ����ǡ�
public class CHNDate extends Calendar

5�������������£���Ϊ��Ա������
    private GregorianCalendar calendar;

*/
//@author��Yeheya��2016-8-8��2017��2��7��