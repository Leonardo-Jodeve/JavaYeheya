//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月11日
//§3.4   类的多态性
//§3.4.4 多态的方法实现
//2.  多态的equals()方法
//（2） 子类覆盖Object类的equals(Object)方法
//Person类，equals()方法的重载问题。

public class Person_equals 
{
    public static void main(String[] args)                 //main方法也是静态成员方法
    {
        //（1）设Person类声明equals(Person)方法，又继承equals(Object)方法，再者重载
        //（2）设Person类声明equals(Person)和equals(Object)方法（覆盖），再者重载
        //（3）设Person类声明equals(Object)方法（覆盖）
        Person p1 = new Person("李小明", new MyDate(1994,3,15));
        Person p2 = new Person(p1);                        //拷贝构造方法，深拷贝
        System.out.println("p1："+p1+"；p2："+p2);
        System.out.println("p1.equals(p2)？"+p1.equals(p2));//调用equals(Person)

        Object obj = new Person(p1);
        System.out.println("obj："+obj);
        System.out.println("p1.equals(obj)？"+p1.equals(obj));  //调用equals(Object)，重载，编译时多态
        //若没有Person类声明equals(Object)，单步调试，执行哪个类的方法，方法体是怎样的？运行结果是什么？

        //（3）覆盖（4）与父类的可比性【思考题3-7】 ① 理解运行时多态
        //Person类与父类Object不可比
        System.out.println("obj.equals(p1)？"+obj.equals(p1));  //调用Person类的equals(Object)，结果为true

        obj = new Object();
        System.out.println("obj.equals(p1)？"+obj.equals(p1));  //调用Object类的equals(Object)，结果为false
    }
}
/*
程序运行结果如下：
        //（1）设Person类声明equals(Person)方法
p1：李小明,1994年03月15日；p2：李小明,1994年03月15日
执行Person类的equals(Person)方法，p1.equals(p2)？true
obj：李小明,1994年03月15日
p1.equals(obj)？false                                       //结果错误

        //（2）设Person类声明equals(Person)和equals(Object)方法
p1：李小明,1994年03月15日；p2：李小明,1994年03月15日
执行Person类的equals(Person)方法，p1.equals(p2)？true
obj：李小明,1994年03月15日
执行Person类的equals(Object)方法，p1.equals(obj)？true         //结果正确

        //（3）设Person类声明equals(Object)方法
p1：李小明,1994年03月15日；p2：李小明,1994年03月15日
执行Person类的equals(Object)方法，p1.equals(p2)？true
obj：李小明,1994年03月15日
执行Person类的equals(Object)方法，p1.equals(obj)？true         //结果正确

        //（3）覆盖（4）与父类的可比性
执行Person类的equals(Object)方法，obj.equals(p1)？true         //结果正确，运行是多态
obj.equals(p1)？false

*/
//@author：Yeheya，2016-8-11