//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.3   ��ļ̳���
//��3.3.1 �ɼ̳�������
//����3.4��  Student��̳�Person�ࡣ
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person����

public class Student extends Person              //Student��̳�Person��
{
    String speciality;                           //רҵ���������ӵĳ�Ա����

    public static void main(String args[])
    {
        Person per = new Person("��С��", new MyDate(1994,3,15));
        Student stu = new Student();              //Ĭ�Ϲ��췽����ִ�и��๹�췽��Person()
//        System.out.println("s1��"+s1.toString());
        stu.set("����", new MyDate(1998,4,5));    //stu������ø���ĳ�Ա����
        stu.speciality="�����";
        Student.howMany();                       //�̳и���ľ�̬������ִ��Person.howMany()����
        System.out.println("per��"+per.toString()+"��stu��"+stu.toString());
        stu.finalize();                           //�̳и������������
        Student.howMany();
/* 
�������н�����£�
2��Person����per����С��,1994��03��15��,,,��stu������,1998��04��05��,,,
�ͷŶ��� (����,1998��04��05��,,,)
1��Person����
*/
        
        //3.3.3 ����Ĺ��췽��
        stu = new Student("��С��", new MyDate(1994,3,15),"�����");
    }

    //3.3.3 ����Ĺ��췽��
    public Student(String name, MyDate birthdate, String speciality)     //���췽��
    {
        super(name, birthdate);                   //���ø���ָ�������Ĺ��췽��
        this.speciality = speciality;
    }
    public Student()                             //Java�ṩ��Ĭ�Ϲ��췽��
    {
        super();                                 //���ø��๹�췽��Person()
    }
}
/*
 //       System.out.println(s1+"��"+s1.getName()+"��"+p1.getName()+"��"+ s1.olderThen(p1)+"��");
������ȷ�� 
    public Student()                             //Java�ṩ��Ĭ�Ϲ��췽��
    {
//        super();                               //Ĭ�ϵ���
    	speciality="";
    }   
 
�������
1. ���ܼ̳и���Ĺ��췽����Student��û���������췽��ʱ������������
    Student s1 = new Student("��С��", new MyDate(1979,3,15));    //�����cannot find symbol: constructor Person1()

2.�������Personû���������췽��Person()����������䲻��ͨ�����룺
    Student s1 = new Student();                  //������Ҳ������췽��Person()

3.�������Person����˽�г�Ա����,�����������:
    public Student(String name, MyDate birthdate)//���췽��
    {
        this.name = name;                        //������󣬲��ܷ��ʸ����˽�г�Ա
        this.birthdate = birthdate;              //������󣬲��ܷ��ʸ����˽�г�Ա
    } 
4. ���
    public MyDate() 
    {
//        super();                               //Ĭ�ϵ���Object()
    } 
���н�����£�
ִ��Person()���췽��
s1��,0��00��00��
*/
//@author��Yeheya��2016-8-11