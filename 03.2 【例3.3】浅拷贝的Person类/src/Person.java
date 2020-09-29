//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.2  类的封装性  §3.2.5   浅拷贝与深拷贝
//【例3.3】  Person类使用对象作为成员变量。
//① 浅拷贝的Person类
//MyEclipse设置编译路径包含项目：例3.2（MyDate）。

//import mypackage.MyDate;
public class Person
{
    String name;                                 //姓名
    MyDate birthdate;                            //出生日期，MyDate类见例3.2

    public Person(String name, MyDate birthdate) //构造方法
    {
        this.name = name;
        this.birthdate = birthdate;              //引用赋值
    } 
    public Person(Person per)                    //拷贝构造方法，重载，复制对象
    {
        this(per.name, per.birthdate);           //浅拷贝，引用日期实例
    }
    public Person()                              //默认构造方法，测试，第5版教材未写
    {
//        this("", null); 
        this(null, null); 
//        this(null, new MyDate()); 
    }
    
    public String toString()
    {
//        return this.name+","+birthdate.toString();
                       //若this.name==null，Java输出"null"；若this.birthdate==null，抛出空对象异常
        return this.name+(this.birthdate==null ? "" : ","+birthdate.toString());
    }
              
    public static void main(String[] args)
    {
    	Person p = new Person();                 //默认构造方法
        System.out.println("p："+p);              //结果p：null

        //图3.5(a) 浅拷贝
    	Person p1 = new Person("李小明", new MyDate(1994,3,15));
//        Person p2 = p1;                        //引用赋值
        Person p2 = new Person(p1);              //执行拷贝构造方法
        System.out.println("p1："+p1+"，p2："+p2+"，p1==p2？"+(p1==p2)+"，p1.name==p2.name？"+
                  (p1.name==p2.name)+"，p1.birthdate==p2.birthdate？"+(p1.birthdate==p2.birthdate));
        
        //图3.5(b) 浅拷贝错误
        p2.name = "张小明";                        //字符串赋值，只改变p2引用的实例值。字符串特例，无法修改
        p2.birthdate.set(1987,2,27);             //同时改变p1.birthday引用的日期实例值
        System.out.println("p1："+p1+"，p2："+p2+"，p1==p2？"+(p1==p2)+"，p1.name==p2.name？"+
                (p1.name==p2.name)+"，p1.birthdate==p2.birthdate？"+(p1.birthdate==p2.birthdate));
        
        //图3.5(c) 深拷贝
        p2.birthdate = new MyDate(p1.birthdate); //创建生日实例，只改变p2引用的实例值
        System.out.println("p1："+p1+"，p2："+p2+"，p1==p2？"+(p1==p2)+"，p1.name==p2.name？"+
                (p1.name==p2.name)+"，p1.birthdate==p2.birthdate？"+(p1.birthdate==p2.birthdate));
    }
}
/*
程序运行结果如下：
p：null
p1：李小明,1994年03月15日，p2：李小明,1994年03月15日，p1==p2？false，p1.name==p2.name？true，p1.birthdate==p2.birthdate？true
p1：李小明,1987年02月27日，p2：张小明,1987年02月27日，p1==p2？false，p1.name==p2.name？false，p1.birthdate==p2.birthdate？true
p1：李小明,1987年02月27日，p2：张小明,1987年02月27日，p1==p2？false，p1.name==p2.name？false，p1.birthdate==p2.birthdate？false
*/
//@author：Yeheya，2016-8-11