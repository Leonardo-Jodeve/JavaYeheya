//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.4   类的多态性
//§3.4.1 子类重定义父类成员
//【例3.5】  类的多态性，Student类声明多态成员。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）和例3.3（Person）。

//表达具有个性属性的类
public class Student extends Person //implements Comparable<Student> //语法错，相当于 Comparable<Person>, Comparable<Student>
{
    public String department,speciality,number;  //系、专业、学号
    public boolean member;                       //团员 
    private static int count=0;                  //Student类对象计数，私有、静态，隐藏

    public Student(String name, MyDate birthdate, String gender, String province, String city, 
                   String department, String speciality, String number, boolean member) //构造方法
    {
        super(name, birthdate, gender, province, city);
             //调用父类同参数的构造方法，Person.count++；由各类分别初始化自己声明的成员变量
        this.set(department, speciality, number, member);
        count++;                                 //指Student.count，隐藏Person.count
    } 
    public Student()
    {
        super();                                 //默认调用Person()Person.count++
        this.set("","","", false);               //初始化子类成员变量
        Student.count++;
    }       
    //构造方法，由父类Person实例提供初值；深拷贝person
    public Student(Person person, String department, String speciality, String number, boolean member)
    {
        super(person);                           //调用父类的拷贝构造方法。无论父类成员何种权限，都能执行
        this.set(department, speciality, number, member);
        Student.count++;
    }
    public Student(Student stu)                  //拷贝构造方法，深拷贝
    {
        this(stu, stu.department, stu.speciality, stu.number, stu.member); //传递子类对象
    }

    public void finalize()                       //析构方法，覆盖父类的析构方法，不能抛出异常
    {
        super.finalize();                        //调用父类析构方法，Person.count--；此时无Person.count访问权限
//        Person.count--;                        //编译错，The field Person.count is not visible
        Student.count--;
    }

    public static void howMany()                 //显示父类和子类的对象数，覆盖父类静态方法
    {
        Person.howMany();                        //调用父类的静态成员方法
//        super.howMany();                       //编译错，静态方法中不能使用super
        System.out.println(Student.count+"个Student对象");
    }

    //设置各属性值；重载父类set()成员方法，参数列表不同
    public void set(String department, String speciality, String number, boolean member)
    {
        this.department = department==null?"":department;
        this.speciality = speciality==null?"":speciality;
        this.number = number==null?"":number;
        this.member = member;
    }    

    public String toString()                     //描述对象字符串，覆盖父类方法
    {
        return super.toString()+","+this.department+","+this.speciality+","+this.number+
               (member?",团员":"");
/*     ??   return super.toString()+(this.department==null?"":","+department)+
                (this.speciality==null?"":","+speciality)+(this.number==null?"":","+number)+
                (member?",团员":"");*/
    }
    //例3.5
    
    
    //3.4.4   多态的方法实现
    //比较this与obj对象是否相等，覆盖，先调用父类方法，再比较本类各成员变量是否相等
    //与父类实例不可比，s1.equals(p1)不比成员变量，引用==时，不继续比
    public boolean equals(Object obj)
    {
        System.out.print("执行Student类的equals(Object)方法，");
        if(this==obj)
            return true;
        if(obj instanceof Student)               //当obj引用实例属于Student及其子类，不包含Person类
        {
            Student stu = (Student)obj;          //类型强制转换，stu也引用obj引用的实例
            return super.equals(stu)             //调用父类方法，以下调用String类的equals(Object)
                   && this.department.equals(stu.department) && this.speciality.equals(stu.speciality) 
                   && this.number.equals(stu.number) && this.member==stu.member;
        }
        return false;
    }

    
/*    //4.4   泛型
    //重载，与Comparable<Student>接口无关
    public int compareTo(Student stu)               //比较对象大小，实现Comparable接口
    {
        return super.compareTo(stu);//this.name.compareTo(stu.name);      //比较姓名大小，调用String类的compareTo()方法
    }*/

    //第6章用，插入表格一行
    public Object[] toArray()                    //返回由当前实例的成员变量值组成的对象数组，例9.3,第12章则反射代替
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
程序设计说明如下。
（1）第4版构造方法如下。
    public Student()
    {   this("", new MyDate(), "","","","","", "", false);
    }   
    //构造方法，由父类Person实例提供初值；深拷贝p，且父类name等成员变量可见
    public Student(Person p, String department, String speciality, String number, boolean member)
    {   this(p.name, new MyDate(p.birthday), p.sex, p.province, p.city,
            department, speciality, number, member);  
    }
    public Student(Student s)                           //拷贝构造方法，深拷贝
    {   this(s.name, new MyDate(s.birthday), s.sex, s.province, s.city, 
            s.department, s.speciality, s.number, s.member); 
    }
第5版改进原则，不调用父类成员变量，父类成员变量的初始由父类完成，子类调用。因此，与父类成员变量的权限是否private或protected无关。
//有观点认为protected不必要，破坏封装性。
//如果父类成员变量为private，则p.name和p.birthday不可见。
 
（2）set()将字符串类型的成员变量初始化为空串，否则toString()、equals()方法抛出空对象异常。
    public String toString()           //对象的字符串描述，覆盖父类方法，省略多余","，第5版未用。
    {
        return super.toString()+(this.department==null?"":","+department)+
                (this.speciality.equals("")?"":","+speciality)+
                (this.number.equals("")?"":","+number)+
                (member?",团员":"");
    }

（3） 第4版研究子类与父类是否可比，equals(Student s)，
第5版放弃子类与父类可比，原因：(1)父类对象不是子类对象，要找的是子类对象；(2) 否则例3.6查找结果没有差别
    //比较this与对象p是否相等，比较两实例的各成员变量是否相等，重载父类同名方法
    public boolean equals(Student s)
    {
        System.out.print("执行Student类的equals(Student)方法，");
        return this==s || s!=null && super.equals(s) && this.department.equals(s.department) && 
            this.speciality.equals(s.speciality) && this.number.equals(s.number) && this.member==s.member;
    }

    //与父类实例可比，提供s1.equals(p1)按Person规则比较，覆盖
    public boolean equals(Person p)                        //比较当前对象与对象p是否相等
    {
        System.out.print("执行Student类的equals(Person)方法，");
        boolean b = super.equals(p);                       //调用父类equals()方法，比较父类声明的成员变量
        if (b && p instanceof Student)                     //当父类成员变量对应相等且p引用子类实例时
        {
            Student s = (Student)p;                        //类型强制转换，s也引用p引用的实例
            return this.department.equals(s.department) && this.speciality.equals(s.speciality) 
                && this.number.equals(s.number) && this.member==s.member;
        }
        return b;
    }
    //s1.equals(p1)不比   //当==时，仍然继续比，所以this==obj必须
    public boolean equals(Object obj)
    {
        return super.equals(obj) && obj instanceof Student &&
               this.speciality.equals(((Student)obj).speciality);
    }

    //与父类实例可比，提供s1.equals(p1)按Person规则比较
    public boolean equals(Object obj)
    {
        boolean b = super.equals(obj); 
        if (b && obj instanceof Student) 
            return this.speciality.equals(((Student)obj).speciality);
        return b;                    
    }
（4）【思考题3-8】 ① 学号自动编号，功能放到第12章实现。
//        this.number = "0101"+MyDate.getThisYear()+String.format("%04d", Student.max);
                   //学号前8位是专业、年级信息，后4位是编号。%04d表示4位整数，不足时前补0        

*/
//@author：Yeheya，2016-8-14