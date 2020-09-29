//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��8��
//��3.4   ��Ķ�̬��
//��3.4.1 �����ض��常���Ա
//����3.5��  ��Ķ�̬�ԣ�Student��������̬��Ա��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person����

//�����и������Ե���
public class Student extends Person //implements Comparable<Student> //�﷨���൱�� Comparable<Person>, Comparable<Student>
{
    public String department,speciality,number;  //ϵ��רҵ��ѧ��
    public boolean member;                       //��Ա 
    private static int count=0;                  //Student����������˽�С���̬������

    public Student(String name, MyDate birthdate, String gender, String province, String city, 
                   String department, String speciality, String number, boolean member) //���췽��
    {
        super(name, birthdate, gender, province, city);
             //���ø���ͬ�����Ĺ��췽����Person.count++���ɸ���ֱ��ʼ���Լ������ĳ�Ա����
        this.set(department, speciality, number, member);
        count++;                                 //ָStudent.count������Person.count
    } 
    public Student()
    {
        super();                                 //Ĭ�ϵ���Person()Person.count++
        this.set("","","", false);               //��ʼ�������Ա����
        Student.count++;
    }       
    //���췽�����ɸ���Personʵ���ṩ��ֵ�����person
    public Student(Person person, String department, String speciality, String number, boolean member)
    {
        super(person);                           //���ø���Ŀ������췽�������۸����Ա����Ȩ�ޣ�����ִ��
        this.set(department, speciality, number, member);
        Student.count++;
    }
    public Student(Student stu)                  //�������췽�������
    {
        this(stu, stu.department, stu.speciality, stu.number, stu.member); //�����������
    }

    public void finalize()                       //�������������Ǹ�������������������׳��쳣
    {
        super.finalize();                        //���ø�������������Person.count--����ʱ��Person.count����Ȩ��
//        Person.count--;                        //�����The field Person.count is not visible
        Student.count--;
    }

    public static void howMany()                 //��ʾ���������Ķ����������Ǹ��ྲ̬����
    {
        Person.howMany();                        //���ø���ľ�̬��Ա����
//        super.howMany();                       //�������̬�����в���ʹ��super
        System.out.println(Student.count+"��Student����");
    }

    //���ø�����ֵ�����ظ���set()��Ա�����������б�ͬ
    public void set(String department, String speciality, String number, boolean member)
    {
        this.department = department==null?"":department;
        this.speciality = speciality==null?"":speciality;
        this.number = number==null?"":number;
        this.member = member;
    }    

    public String toString()                     //���������ַ��������Ǹ��෽��
    {
        return super.toString()+","+this.department+","+this.speciality+","+this.number+
               (member?",��Ա":"");
/*     ??   return super.toString()+(this.department==null?"":","+department)+
                (this.speciality==null?"":","+speciality)+(this.number==null?"":","+number)+
                (member?",��Ա":"");*/
    }
    //��3.5
    
    
    //3.4.4   ��̬�ķ���ʵ��
    //�Ƚ�this��obj�����Ƿ���ȣ����ǣ��ȵ��ø��෽�����ٱȽϱ������Ա�����Ƿ����
    //�븸��ʵ�����ɱȣ�s1.equals(p1)���ȳ�Ա����������==ʱ����������
    public boolean equals(Object obj)
    {
        System.out.print("ִ��Student���equals(Object)������");
        if(this==obj)
            return true;
        if(obj instanceof Student)               //��obj����ʵ������Student�������࣬������Person��
        {
            Student stu = (Student)obj;          //����ǿ��ת����stuҲ����obj���õ�ʵ��
            return super.equals(stu)             //���ø��෽�������µ���String���equals(Object)
                   && this.department.equals(stu.department) && this.speciality.equals(stu.speciality) 
                   && this.number.equals(stu.number) && this.member==stu.member;
        }
        return false;
    }

    
/*    //4.4   ����
    //���أ���Comparable<Student>�ӿ��޹�
    public int compareTo(Student stu)               //�Ƚ϶����С��ʵ��Comparable�ӿ�
    {
        return super.compareTo(stu);//this.name.compareTo(stu.name);      //�Ƚ�������С������String���compareTo()����
    }*/

    //��6���ã�������һ��
    public Object[] toArray()                    //�����ɵ�ǰʵ���ĳ�Ա����ֵ��ɵĶ������飬��9.3,��12���������
    {
        Object[] arow = new Object[9];
        arow[0] = this.department;
        arow[1] = this.speciality;
        arow[2] = this.number;
        arow[3] = this.name;
        arow[4] = this.gender;
        arow[5] = this.birthdate;
        arow[6] = this.province;
        arow[7] = this.city;
        arow[8] = this.member;
        return arow;
    }
}
/*
�������˵�����¡�
��1����4�湹�췽�����¡�
    public Student()
    {   this("", new MyDate(), "","","","","", "", false);
    }   
    //���췽�����ɸ���Personʵ���ṩ��ֵ�����p���Ҹ���name�ȳ�Ա�����ɼ�
    public Student(Person p, String department, String speciality, String number, boolean member)
    {   this(p.name, new MyDate(p.birthday), p.sex, p.province, p.city,
            department, speciality, number, member);  
    }
    public Student(Student s)                           //�������췽�������
    {   this(s.name, new MyDate(s.birthday), s.sex, s.province, s.city, 
            s.department, s.speciality, s.number, s.member); 
    }
��5��Ľ�ԭ�򣬲����ø����Ա�����������Ա�����ĳ�ʼ�ɸ�����ɣ�������á���ˣ��븸���Ա������Ȩ���Ƿ�private��protected�޹ء�
//�й۵���Ϊprotected����Ҫ���ƻ���װ�ԡ�
//��������Ա����Ϊprivate����p.name��p.birthday���ɼ���
 
��2��set()���ַ������͵ĳ�Ա������ʼ��Ϊ�մ�������toString()��equals()�����׳��ն����쳣��
    public String toString()           //������ַ������������Ǹ��෽����ʡ�Զ���","����5��δ�á�
    {
        return super.toString()+(this.department==null?"":","+department)+
                (this.speciality.equals("")?"":","+speciality)+
                (this.number.equals("")?"":","+number)+
                (member?",��Ա":"");
    }

��3�� ��4���о������븸���Ƿ�ɱȣ�equals(Student s)��
��5����������븸��ɱȣ�ԭ��(1)����������������Ҫ�ҵ����������(2) ������3.6���ҽ��û�в��
    //�Ƚ�this�����p�Ƿ���ȣ��Ƚ���ʵ���ĸ���Ա�����Ƿ���ȣ����ظ���ͬ������
    public boolean equals(Student s)
    {
        System.out.print("ִ��Student���equals(Student)������");
        return this==s || s!=null && super.equals(s) && this.department.equals(s.department) && 
            this.speciality.equals(s.speciality) && this.number.equals(s.number) && this.member==s.member;
    }

    //�븸��ʵ���ɱȣ��ṩs1.equals(p1)��Person����Ƚϣ�����
    public boolean equals(Person p)                        //�Ƚϵ�ǰ���������p�Ƿ����
    {
        System.out.print("ִ��Student���equals(Person)������");
        boolean b = super.equals(p);                       //���ø���equals()�������Ƚϸ��������ĳ�Ա����
        if (b && p instanceof Student)                     //�������Ա������Ӧ�����p��������ʵ��ʱ
        {
            Student s = (Student)p;                        //����ǿ��ת����sҲ����p���õ�ʵ��
            return this.department.equals(s.department) && this.speciality.equals(s.speciality) 
                && this.number.equals(s.number) && this.member==s.member;
        }
        return b;
    }
    //s1.equals(p1)����   //��==ʱ����Ȼ�����ȣ�����this==obj����
    public boolean equals(Object obj)
    {
        return super.equals(obj) && obj instanceof Student &&
               this.speciality.equals(((Student)obj).speciality);
    }

    //�븸��ʵ���ɱȣ��ṩs1.equals(p1)��Person����Ƚ�
    public boolean equals(Object obj)
    {
        boolean b = super.equals(obj); 
        if (b && obj instanceof Student) 
            return this.speciality.equals(((Student)obj).speciality);
        return b;                    
    }
��4����˼����3-8�� �� ѧ���Զ���ţ����ܷŵ���12��ʵ�֡�
//        this.number = "0101"+MyDate.getThisYear()+String.format("%04d", Student.max);
                   //ѧ��ǰ8λ��רҵ���꼶��Ϣ����4λ�Ǳ�š�%04d��ʾ4λ����������ʱǰ��0        

*/
//@author��Yeheya��2016-8-14