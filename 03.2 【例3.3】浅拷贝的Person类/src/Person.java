//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.2  ��ķ�װ��  ��3.2.5   ǳ���������
//����3.3��  Person��ʹ�ö�����Ϊ��Ա������
//�� ǳ������Person��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate����

//import mypackage.MyDate;
public class Person
{
    String name;                                 //����
    MyDate birthdate;                            //�������ڣ�MyDate�����3.2

    public Person(String name, MyDate birthdate) //���췽��
    {
        this.name = name;
        this.birthdate = birthdate;              //���ø�ֵ
    } 
    public Person(Person per)                    //�������췽�������أ����ƶ���
    {
        this(per.name, per.birthdate);           //ǳ��������������ʵ��
    }
    public Person()                              //Ĭ�Ϲ��췽�������ԣ���5��̲�δд
    {
//        this("", null); 
        this(null, null); 
//        this(null, new MyDate()); 
    }
    
    public String toString()
    {
//        return this.name+","+birthdate.toString();
                       //��this.name==null��Java���"null"����this.birthdate==null���׳��ն����쳣
        return this.name+(this.birthdate==null ? "" : ","+birthdate.toString());
    }
              
    public static void main(String[] args)
    {
    	Person p = new Person();                 //Ĭ�Ϲ��췽��
        System.out.println("p��"+p);              //���p��null

        //ͼ3.5(a) ǳ����
    	Person p1 = new Person("��С��", new MyDate(1994,3,15));
//        Person p2 = p1;                        //���ø�ֵ
        Person p2 = new Person(p1);              //ִ�п������췽��
        System.out.println("p1��"+p1+"��p2��"+p2+"��p1==p2��"+(p1==p2)+"��p1.name==p2.name��"+
                  (p1.name==p2.name)+"��p1.birthdate==p2.birthdate��"+(p1.birthdate==p2.birthdate));
        
        //ͼ3.5(b) ǳ��������
        p2.name = "��С��";                        //�ַ�����ֵ��ֻ�ı�p2���õ�ʵ��ֵ���ַ����������޷��޸�
        p2.birthdate.set(1987,2,27);             //ͬʱ�ı�p1.birthday���õ�����ʵ��ֵ
        System.out.println("p1��"+p1+"��p2��"+p2+"��p1==p2��"+(p1==p2)+"��p1.name==p2.name��"+
                (p1.name==p2.name)+"��p1.birthdate==p2.birthdate��"+(p1.birthdate==p2.birthdate));
        
        //ͼ3.5(c) ���
        p2.birthdate = new MyDate(p1.birthdate); //��������ʵ����ֻ�ı�p2���õ�ʵ��ֵ
        System.out.println("p1��"+p1+"��p2��"+p2+"��p1==p2��"+(p1==p2)+"��p1.name==p2.name��"+
                (p1.name==p2.name)+"��p1.birthdate==p2.birthdate��"+(p1.birthdate==p2.birthdate));
    }
}
/*
�������н�����£�
p��null
p1����С��,1994��03��15�գ�p2����С��,1994��03��15�գ�p1==p2��false��p1.name==p2.name��true��p1.birthdate==p2.birthdate��true
p1����С��,1987��02��27�գ�p2����С��,1987��02��27�գ�p1==p2��false��p1.name==p2.name��false��p1.birthdate==p2.birthdate��true
p1����С��,1987��02��27�գ�p2����С��,1987��02��27�գ�p1==p2��false��p1.name==p2.name��false��p1.birthdate==p2.birthdate��false
*/
//@author��Yeheya��2016-8-11