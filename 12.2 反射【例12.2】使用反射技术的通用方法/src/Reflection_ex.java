
public class Reflection_ex 
{
    public static void main(String arg[])
    {
        Person per = new Person("李小明", new MyDate(1992,3,15),"男","江苏","南京");
        Object obj = per;
//        printField(obj);
//        printValue(obj);
        Reflection.print(Reflection.toArray(obj));
        
        Student stu = new Student(per,"计算机系","软件工程","001",true);//"系","专业","学号","团员",
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
Person类声明以下成员变量。
成员变量名：name；类型：class java.lang.String，通用属性：public java.lang.String Person.name，
成员变量名：birthdate；类型：class MyDate，通用属性：public MyDate Person.birthdate，
成员变量名：gender；类型：class java.lang.String，通用属性：public java.lang.String Person.gender，
成员变量名：province；类型：class java.lang.String，通用属性：public java.lang.String Person.province，
成员变量名：city；类型：class java.lang.String，通用属性：public java.lang.String Person.city，
(李小明，1992年03月15日，，，)
Student类声明以下成员变量。
成员变量名：department；类型：class java.lang.String，通用属性：public java.lang.String Student.department，
成员变量名：speciality；类型：class java.lang.String，通用属性：public java.lang.String Student.speciality，
成员变量名：number；类型：class java.lang.String，通用属性：public java.lang.String Student.number，
成员变量名：member；类型：boolean，通用属性：public boolean Student.member，
成员变量名：name；类型：class java.lang.String，通用属性：public java.lang.String Person.name，
成员变量名：birthdate；类型：class MyDate，通用属性：public MyDate Person.birthdate，
成员变量名：gender；类型：class java.lang.String，通用属性：public java.lang.String Person.gender，
成员变量名：province；类型：class java.lang.String，通用属性：public java.lang.String Person.province，
成员变量名：city；类型：class java.lang.String，通用属性：public java.lang.String Person.city，
(，，，true，李小明，1992年03月15日，，，)

*/
//@author：Yeheya，2018年2月2日