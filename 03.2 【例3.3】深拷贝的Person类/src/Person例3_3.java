//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.2   ��ķ�װ��
//��3.2.5 ǳ���������
//����3.3��  Person�࣬ʹ�ö�����Ϊ��Ա������ʵ�������
//�� �����Person��

public class Person��3_3 
{
    public static void main(String[] args)
    {
        Person p1 = new Person("��С��", new MyDate(1994,3,15));
//        Person p2 = p1;                        //��������
//        System.out.println("p1.equals(p2)��"+p1.equals(p2));  //����equals(Person)
                
        Person p2 = new Person(p1);                        //�������췽�������
//        System.out.println("p1.equals(p2)��"+p1.equals(p2));  //����equals(Person)
        Person.howMany();                                  //ͨ�������������Ա����
        System.out.println("p1��"+p1+"��p2��"+p2+"\np1==p2��"+(p1==p2)+
            "��p1.name==p2.name��"+(p1.name==p2.name)+"��p1.birthdate==p2.birthdate��"+
            (p1.birthdate==p2.birthdate));                 //��ʾ���ù�ϵ

        //�����޸�p2������������
        p2.name = "��"+p2.name.substring(1);                //���գ�һ�����ֳ���Ϊ1�ַ�
        MyDate date = p2.birthdate;                         //������ڣ��������ڶ�������
        //�¾����dateֵ����Ӱ��p2.birthdateʵ��ֵ����Ϊdate��p2.birthdate����ͬһ��ʵ��
        date.set(date.getYear()+2, date.getMonth(), date.getDay());
//        p2.set(d);                                       //��������
        System.out.println("p1��"+p1+"��p2��"+p2);
        p1.finalize();                                     //���������������ͷŶ���
        Person.howMany();
    }
}
/*
�������н�����£�
2��Person����p1����С��,1994��03��15��,,,��p2����С��,1994��03��15��,,,
p1==p2��false��p1.name==p2.name��true��p1.birthdate==p2.birthdate��false
p1����С��,1994��03��15��,,,��p2����С��,1996��03��15��,,,
�ͷŶ��� (��С��,1994��03��15��,,,)
1��Person����
*/
//@author��Yeheya��2016-8-11