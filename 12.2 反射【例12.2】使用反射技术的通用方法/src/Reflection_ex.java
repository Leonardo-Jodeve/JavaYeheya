
public class Reflection_ex 
{
    public static void main(String arg[])
    {
        Person per = new Person("��С��", new MyDate(1992,3,15),"��","����","�Ͼ�");
        Object obj = per;
//        printField(obj);
//        printValue(obj);
        Reflection.print(Reflection.toArray(obj));
        
        Student stu = new Student(per,"�����ϵ","�������","001",true);//"ϵ","רҵ","ѧ��","��Ա",
        obj = stu;
//        printField(obj);
//        printValue(obj);
        Reflection.print(Reflection.toArray(obj));
    }

}
/*       
try
{
     Class c = Class.forName("Person");
     Constructor[]cs = c.getConstructors();
     int i=0;    
     for(i=0; i<cs.length; i++)
     {
         Class[] c1 = cs[i].getParameterTypes();
         for(int j=0; j<c1.length; j++)
             System.out.print(c1[j].getName()+", ");
         System.out.println();
     }
}
catch (Exception  e){}//ClassNotFoundException
}
}/*
Person���������³�Ա������
��Ա��������name�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.name��
��Ա��������birthdate�����ͣ�class MyDate��ͨ�����ԣ�public MyDate Person.birthdate��
��Ա��������gender�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.gender��
��Ա��������province�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.province��
��Ա��������city�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.city��
(��С����1992��03��15�գ�����)
Student���������³�Ա������
��Ա��������department�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Student.department��
��Ա��������speciality�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Student.speciality��
��Ա��������number�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Student.number��
��Ա��������member�����ͣ�boolean��ͨ�����ԣ�public boolean Student.member��
��Ա��������name�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.name��
��Ա��������birthdate�����ͣ�class MyDate��ͨ�����ԣ�public MyDate Person.birthdate��
��Ա��������gender�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.gender��
��Ա��������province�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.province��
��Ա��������city�����ͣ�class java.lang.String��ͨ�����ԣ�public java.lang.String Person.city��
(������true����С����1992��03��15�գ�����)

*/
//@author��Yeheya��2018��2��2��