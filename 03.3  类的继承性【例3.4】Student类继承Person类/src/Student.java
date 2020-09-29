//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.3   类的继承性
//§3.3.1 由继承派生类
//【例3.4】  Student类继承Person类。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）和例3.3（Person）。

public class Student extends Person              //Student类继承Person类
{
    String speciality;                           //专业，子类增加的成员变量

    public static void main(String args[])
    {
        Person per = new Person("李小明", new MyDate(1994,3,15));
        Student stu = new Student();              //默认构造方法，执行父类构造方法Person()
//        System.out.println("s1："+s1.toString());
        stu.set("张莉", new MyDate(1998,4,5));    //stu对象调用父类的成员方法
        stu.speciality="计算机";
        Student.howMany();                       //继承父类的静态方法，执行Person.howMany()方法
        System.out.println("per："+per.toString()+"；stu："+stu.toString());
        stu.finalize();                           //继承父类的析构方法
        Student.howMany();
/* 
程序运行结果如下：
2个Person对象，per：李小明,1994年03月15日,,,；stu：张莉,1998年04月05日,,,
释放对象 (张莉,1998年04月05日,,,)
1个Person对象，
*/
        
        //3.3.3 子类的构造方法
        stu = new Student("李小明", new MyDate(1994,3,15),"计算机");
    }

    //3.3.3 子类的构造方法
    public Student(String name, MyDate birthdate, String speciality)     //构造方法
    {
        super(name, birthdate);                   //调用父类指定参数的构造方法
        this.speciality = speciality;
    }
    public Student()                             //Java提供的默认构造方法
    {
        super();                                 //调用父类构造方法Person()
    }
}
/*
 //       System.out.println(s1+"，"+s1.getName()+"比"+p1.getName()+"大"+ s1.olderThen(p1)+"岁");
程序正确： 
    public Student()                             //Java提供的默认构造方法
    {
//        super();                               //默认调用
    	speciality="";
    }   
 
程序错误：
1. 不能继承父类的构造方法，Student类没有声明构造方法时，下列语句错误
    Student s1 = new Student("李小明", new MyDate(1979,3,15));    //编译错，cannot find symbol: constructor Person1()

2.如果父类Person没有声明构造方法Person()，则下列语句不能通过编译：
    Student s1 = new Student();                  //编译错，找不到构造方法Person()

3.如果父类Person声明私有成员变量,下列语句编译错:
    public Student(String name, MyDate birthdate)//构造方法
    {
        this.name = name;                        //编译错误，不能访问父类的私有成员
        this.birthdate = birthdate;              //编译错误，不能访问父类的私有成员
    } 
4. 如果
    public MyDate() 
    {
//        super();                               //默认调用Object()
    } 
运行结果如下：
执行Person()构造方法
s1：,0年00月00日
*/
//@author：Yeheya，2016-8-11