//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.2   类的封装性
//§3.2.5 浅拷贝与深拷贝
//【例3.3】  Person类，使用对象作为成员变量并实现深拷贝。
//② 深拷贝的Person类

public class Person例3_3 
{
    public static void main(String[] args)
    {
        Person p1 = new Person("李小明", new MyDate(1994,3,15));
//        Person p2 = p1;                        //对象引用
//        System.out.println("p1.equals(p2)？"+p1.equals(p2));  //调用equals(Person)
                
        Person p2 = new Person(p1);                        //拷贝构造方法，深拷贝
//        System.out.println("p1.equals(p2)？"+p1.equals(p2));  //调用equals(Person)
        Person.howMany();                                  //通过类名调用类成员方法
        System.out.println("p1："+p1+"；p2："+p2+"\np1==p2？"+(p1==p2)+
            "；p1.name==p2.name？"+(p1.name==p2.name)+"；p1.birthdate==p2.birthdate？"+
            (p1.birthdate==p2.birthdate));                 //显示引用关系

        //以下修改p2的姓名和生日
        p2.name = "张"+p2.name.substring(1);                //改姓，一个汉字长度为1字符
        MyDate date = p2.birthdate;                         //获得日期，传递日期对象引用
        //下句更改date值，将影响p2.birthdate实例值，因为date与p2.birthdate引用同一个实例
        date.set(date.getYear()+2, date.getMonth(), date.getDay());
//        p2.set(d);                                       //对象引用
        System.out.println("p1："+p1+"；p2："+p2);
        p1.finalize();                                     //调用析构方法，释放对象
        Person.howMany();
    }
}
/*
程序运行结果如下：
2个Person对象，p1：李小明,1994年03月15日,,,；p2：李小明,1994年03月15日,,,
p1==p2？false；p1.name==p2.name？true；p1.birthdate==p2.birthdate？false
p1：李小明,1994年03月15日,,,；p2：张小明,1996年03月15日,,,
释放对象 (李小明,1994年03月15日,,,)
1个Person对象，
*/
//@author：Yeheya，2016-8-11