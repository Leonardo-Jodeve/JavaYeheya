//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.2   ��ķ�װ��
//��3.2.5 ǳ���������
//����3.3��  Person�࣬ʹ�ö�����Ϊ��Ա������ʵ�������
//�� �����Person��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate����
//��˼����3-4�����ӳ�Ա�������Ա�ʡ�ݡ����С�����6.4����  
//4.3.1 ʵ�ֿɱȽϽӿڣ�//���л�����8.3

import java.lang.reflect.Field;

//�����Ա��ͬ���Ե���
public class Person implements Comparable<Person>, java.io.Serializable
{
    public String name;                                    //������ʵ����Ա������������Ա
    public MyDate birthdate;                               //�������ڣ�MyDate�����3.2
    public String gender, province, city;                  //�Ա�ʡ�ݡ�����
    private static int count=0;                            //��̬��Ա���������༰����ʵ������
    
    public Person(String name, MyDate birthdate, String gender, String province, String city) //���췽��
    {
        super();                                           //Ĭ�ϵ���Object()
        this.set(name, birthdate, gender, province, city); //���ñ��������ĳ�Ա����
        count++;                                           //Person.count
    } 
    public Person(String name, MyDate birthdate)           //���췽��������
    {
        this(name, birthdate, "", "", "");                 //���ñ����������Ĺ��췽��
    } 
    public Person()                                        //���췽��������
    {
        this("", new MyDate());
//        System.out.println("ִ��Person()���췽��");
    }
    public Person(Person per)                              //�������췽�������أ����ƶ���
    {
        //�������������ʵ����ͼ3.5(c)
        this(per.name, new MyDate(per.birthdate), per.gender, per.province, per.city);
    }

    public void finalize()                                 //���������������׳��쳣
    {
        System.out.println("�ͷŶ��� ("+this.toString()+")");
        Person.count--;
    }

    //��ʾ����������̬��Ա������ֻ�ܷ��ʾ�̬��Ա���������ܷ���ʵ����Ա��Ҳ����ʹ��this
    public static void howMany()
    {
        System.out.print(Person.count+"��Person����");
    }
    
    //���ø�����ֵ������Ա�������ǿն����ַ�����ʼֵ��ֵΪ�մ���Ĭ������
    public void set(String name, MyDate birthdate, String gender, String province, String city)
    {
        this.name = name==null?"":name;          //���ն���ת���ɿմ�������equals()�׳��ն����쳣
        this.birthdate = birthdate;              //���ø�ֵ��������������޸ģ���null�������׳��ն����쳣
//        this.birthdate = birthdate==null?new MyDate():birthdate;  //���У��ı�����ֵ���������������ԣ��߼�����
        this.gender = gender==null?"":gender;
        this.province = province==null?"":province;
        this.city = city==null?"":city;
    }
    public void set(String name, MyDate birthdate)//�������ԣ�������Ա����ȡĬ��ֵ������
    {
        this.set(name, birthdate, "", "", "");
    }
    //���������ַ�������Ա����֮���Զ���","�ָ���
    public String toString()                     //����Object���toString()����
    {
        return this.name+","+(this.birthdate==null?"":birthdate.toString())+","+
               this.gender+","+this.province+","+this.city;  //""ʱ���","��ʾ�մ�

/*        //��String���ͳ�Ա����ֵΪ""����������Ҳ�д���","
        return this.name+(this.birthdate==null?"":","+birthdate.toString())+
            (this.gender.equals("")?"":","+gender)+(this.province.equals("")?"":","+province)+
            (this.city.equals("")?"":","+city);
            //��this.name==null�����"null"��Java���ַ������ܣ��˴�������ʾû��������//���У��׳��ն����쳣
            //��this.birthdate==null��Java�׳��ն����쳣�����⣬�Ҳ��������д����","
             */
    }   
    //���ϡ���3.3��  Person�࣬�����    
    
    //��˼����3-4��
    public int getAge(int year)                  //���ص�ǰ������year��ݵ�����
    {
        return year - this.birthdate.getYear();  //yearָ����
    }
    public int getAge()                          //���ص�ǰ�����������䣬����
    {
        return getAge(MyDate.getThisYear());     //MyDate.getThisYear()���ؽ�������
    }
    public int olderThen(Person per)             //����this��per���������ݵĲ�ֵ
    {
        return this.birthdate.getYear() - per.birthdate.getYear();
        //return this.birthdate.yearsBetween(p.birthdate);
    }
    
    //3.4.2 ���͵Ķ�̬
    //�Ƚϵ�ǰ���������p�Ƿ���ȣ��Ƚ���ʵ���ĸ���Ա�����Ƿ����
/*    public boolean equals(Person per)
    {
        System.out.print("ִ��Person���equals(Person)������");
        return this==per || per!=null && this.name.equals(per.name) && this.birthdate.equals(per.birthdate) && 
               this.gender.equals(per.gender) && this.province.equals(per.province) && this.city.equals(per.city);
               //����·���ܵ��߼����㣬���߼���������ִ���������㣬�˴��ȼ���||���ټ���&&
//      boolean b=false;
//      return b=this==p || p!=null && this.name.equals(p.name) && this.birthdate.equals(b,p.birthdate);
                                         //�߼����㣬û�ж�·���ܣ�|���ȼ�����&���ȼ���&���ټ���|
    }*/
    
    //3.4.4   ��̬�ķ���ʵ��
    //�Ƚ�this��obj�����Ƿ���ȣ�����Object��ķ�����
    //������obj����Personʵ��ʱ���㷨����Ƚϸ���Ա��������ֵ�Ƿ����
    public boolean equals(Object obj)
    {
        System.out.print("ִ��Person���equals(Object)������");
        if(this==obj)                            //thisָ�����õ�ǰ�����Ķ���
            return true;
        if(obj instanceof Person)                //��obj����ʵ������Person�������ࣨ����Student����obj==nullʱ����false 
        {
            Person p = (Person)obj;              //����ǿ��ת����perҲ����obj���õ�ʵ��
            return this.name.equals(p.name) && this.birthdate.equals(p.birthdate) &&
                this.gender.equals(p.gender) && this.province.equals(p.province) &&
                this.city.equals(p.city);
                //���У�name��ִ��String���equals(obj)��birthdateִ��MyDate���equals(obj)
        }
        return false;
    }

    //4.3.1   java.lang���еĻ������
    //4.4   ����
    public int compareTo(Person per)               //�Ƚ϶����С��ʵ��Comparable�ӿ�
    {
//        return this.name.compareTo(per.name);      //�Ƚ���������С������String���compareTo()����
        return this.birthdate.compareTo(per.birthdate);  //�Ƚϳ������ڴ�С������MyDate���compareTo()����
    }
    
    //��5��ϰ�⣬���췽�������ַ�����ʾ����ʽͬtoString()���ַ�����������Ա����֮���Զ��ŷָ�
    //ʹ��String��split()����
  /*  public Person(String personstr)
    {
        String[] str = personstr.split(",");
        try
        {
            this.set(str[0], new MyDate(str[1]), str[2], str[3], str[4]); //���ñ����������Ĺ��췽��
            count++;
        }
        catch(Exception e){}//NumberFormatException, DateFormatException e)
    }     */
    
    /*
    public Object[] toArray()                              //�����ɵ�ǰʵ���ĳ�Ա����ֵ��ɵĶ������飬��9.3
    {
        Object[] arow = new Object[5];
        arow[0] = this.name;
        arow[1] = this.gender;
        arow[2] = this.birthdate;
        arow[3] = this.province;
        arow[4] = this.city;
        return arow;
    }*/
    //��12�� 
    //���췽�������ַ�����ʾ����ʽͬtoString()���ַ�����������Ա����֮���Զ��ŷָ�
    //ʹ��String��split()������Field��
    public Person(String personstr)            
    {
        Field[] fields = Person.class.getFields();//������й��г�Ա����
        String[] str = personstr.split(",");
        for(int i=0; i<str.length; i++)
            try
            {
                if(fields[i].getType()==String.class)     //��Ա����������String
                    fields[i].set(this,str[i]);            //��this�����fields[i]��Ա����ֵ����Ϊstr[i]
                else
                    fields[i].set(this,new MyDate(str[1]));
            }
            catch(IllegalAccessException ex){}
            catch(NumberFormatException | DateFormatException ex){}//JDK 8 |
            catch(Exception ex){}
    }
}

/*
������ȷ:
    public Person(String str)                        //���췽�������أ���","�ָ��ַ���
    {
        int start = 0;
    	int i = str.indexOf(",",start);                   //���в����Ӵ������� �Ӵ����
        this.name = str.substring(0,i);
    	i = str.indexOf(",",start=i);                   //���в����Ӵ������� �Ӵ����
        this.birthdate = new MyDate(str.substring(start,i));             //����Ĭ������ʵ��
    	i = str.indexOf(",",start=i);
        this.gender = str.substring(start,i);
    	i = str.indexOf(",",start=i);
        this.province = str.substring(start,i);
    	i = str.indexOf(",",start=i);
        this.city = str.substring(start,i);
    } 
1����û�д����κζ���ʱ��������£�
Person.count=0


    public void print()
    {
        System.out.println("������="+this.getClass().getName()+"  "+
             "������="+this.getClass().getSuperclass().getName()+"  ");
    } 
        System.out.println(this.getClass().getName()+"��  "+this.toString());  //���Ե���ʵ������

�������:
        public String str="";                   //������ֲ�����������ʹ�����η�
        static String str="";                   //�ֲ�����������ʹ�����η�
    
*/
//@author��Yeheya��2016-8-8