//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.4   ��Ķ�̬��
//��3.4.4 ��̬�ķ���ʵ��
//2.  ��̬��equals()����
//��2�� ���า��Object���equals(Object)����
//��˼����3-6�� �� ��������븸�����Ŀɱ��ԡ�
//��3�� ������չ�����equals(Object)����
        //Student�࣬equals()�������븸��Ŀɱ������⡣

public class Student_equals 
{
    public static void main(String[] args)
    {
        Person per = new Person("��С��",new MyDate(1994,3,15));
        Student stu1 = new Student(per,"","�����","",false);
        Student stu2 = new Student(per,"","��Ϣ����","",false);
        System.out.println("per��"+per.toString()+"\nstu1��"+stu1.toString()+"\nstu2��"+stu2);       
        
        //ִ��Person�෽�������Ƚ�Student��ĳ�Ա��������Student���Ƿ񸲸��޹�
        System.out.println("per.equals(stu1)��"+per.equals(stu1));  //���Ϊtrue
        
        //��2�� Person�า��Object���equals(Object)������Student��û�и���
        //��˼����3-6�� �� ��������븸�����Ŀɱ��ԡ�
        //����ִ��Person�෽�������Ƚ�Student��ĳ�Ա����
//        System.out.println("stu1.equals(per)��"+stu1.equals(per));  //���Ϊtrue
//        System.out.println("stu1.equals(stu2)��"+stu1.equals(stu2));  //���Ϊtrue
        
        //<2> ��Student�า��equals(Object)����������ִ��Student�෽��
        System.out.println("stu1.equals(per)��"+stu1.equals(per));
               //������Personʵ��������Ҫ�ҵĶ��󣬲��ñȽϳ�Ա���������Ϊfalse
        System.out.println("stu1.equals(stu2)��"+stu1.equals(stu2));
               //��ִ��Person�෽��������true���ٱȽ�Student��ĳ�Ա���������Ϊfalse

        Student stu3 = new Student(stu2);                        //�������췽��
        System.out.println("s3.equals(stu2)��"+stu3.equals(stu2));
               //��ִ��Person�෽��������true���ٱȽ�Student��ĳ�Ա���������Ϊtrue//���ʱ������˵������ͬ
    }
}
/*
 �������н�����£�
        //<1> ��Student��û�и���equals(Object)����
per����С��,1994��03��15��,,,
stu1����С��,1994��03��15��,,,,,�����,
stu2����С��,1994��03��15��,,,,,��Ϣ����,
per.equals(stu1)��true
stu1.equals(per)��true
stu1.equals(stu2)��true                               //�������

        //<2> ��Student�า��equals(Object)����
per����С��,1994��03��15��,,,
stu1����С��,1994��03��15��,,,,,�����,
stu2����С��,1994��03��15��,,,,,��Ϣ����,
per.equals(stu1)��true                               //�˾估���ϣ����ͬ��
stu1.equals(per)��false                              //��������������ͬ
stu1.equals(stu2)��false                              //�����ȷ�������������ͬ
stu3.equals(stu2)��true                               //���ʱ������˵������ͬ
*/
//@author��Yeheya��2016-8-14