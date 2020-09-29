//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��11��
//��3.4   ��Ķ�̬��
//��3.4.4 ��̬�ķ���ʵ��
//2.  ��̬��equals()����
//��2�� ���า��Object���equals(Object)����
//Person�࣬equals()�������������⡣

public class Person_equals 
{
    public static void main(String[] args)                 //main����Ҳ�Ǿ�̬��Ա����
    {
        //��1����Person������equals(Person)�������ּ̳�equals(Object)��������������
        //��2����Person������equals(Person)��equals(Object)���������ǣ�����������
        //��3����Person������equals(Object)���������ǣ�
        Person p1 = new Person("��С��", new MyDate(1994,3,15));
        Person p2 = new Person(p1);                        //�������췽�������
        System.out.println("p1��"+p1+"��p2��"+p2);
        System.out.println("p1.equals(p2)��"+p1.equals(p2));//����equals(Person)

        Object obj = new Person(p1);
        System.out.println("obj��"+obj);
        System.out.println("p1.equals(obj)��"+p1.equals(obj));  //����equals(Object)�����أ�����ʱ��̬
        //��û��Person������equals(Object)���������ԣ�ִ���ĸ���ķ������������������ģ����н����ʲô��

        //��3�����ǣ�4���븸��Ŀɱ��ԡ�˼����3-7�� �� �������ʱ��̬
        //Person���븸��Object���ɱ�
        System.out.println("obj.equals(p1)��"+obj.equals(p1));  //����Person���equals(Object)�����Ϊtrue

        obj = new Object();
        System.out.println("obj.equals(p1)��"+obj.equals(p1));  //����Object���equals(Object)�����Ϊfalse
    }
}
/*
�������н�����£�
        //��1����Person������equals(Person)����
p1����С��,1994��03��15�գ�p2����С��,1994��03��15��
ִ��Person���equals(Person)������p1.equals(p2)��true
obj����С��,1994��03��15��
p1.equals(obj)��false                                       //�������

        //��2����Person������equals(Person)��equals(Object)����
p1����С��,1994��03��15�գ�p2����С��,1994��03��15��
ִ��Person���equals(Person)������p1.equals(p2)��true
obj����С��,1994��03��15��
ִ��Person���equals(Object)������p1.equals(obj)��true         //�����ȷ

        //��3����Person������equals(Object)����
p1����С��,1994��03��15�գ�p2����С��,1994��03��15��
ִ��Person���equals(Object)������p1.equals(p2)��true
obj����С��,1994��03��15��
ִ��Person���equals(Object)������p1.equals(obj)��true         //�����ȷ

        //��3�����ǣ�4���븸��Ŀɱ���
ִ��Person���equals(Object)������obj.equals(p1)��true         //�����ȷ�������Ƕ�̬
obj.equals(p1)��false

*/
//@author��Yeheya��2016-8-11