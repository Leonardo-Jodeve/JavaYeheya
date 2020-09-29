//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.4   类的多态性
//§3.4.4 多态的方法实现
//2.  多态的equals()方法
//（2） 子类覆盖Object类的equals(Object)方法
//【思考题3-6】 ② 子类对象与父类对象的可比性。
//（3） 子类扩展父类的equals(Object)方法
        //Student类，equals()方法，与父类的可比性问题。

public class Student_equals 
{
    public static void main(String[] args)
    {
        Person per = new Person("李小明",new MyDate(1994,3,15));
        Student stu1 = new Student(per,"","计算机","",false);
        Student stu2 = new Student(per,"","信息管理","",false);
        System.out.println("per："+per.toString()+"\nstu1："+stu1.toString()+"\nstu2："+stu2);       
        
        //执行Person类方法，不比较Student类的成员变量，与Student类是否覆盖无关
        System.out.println("per.equals(stu1)？"+per.equals(stu1));  //结果为true
        
        //（2） Person类覆盖Object类的equals(Object)方法，Student类没有覆盖
        //【思考题3-6】 ② 子类对象与父类对象的可比性。
        //以下执行Person类方法，不比较Student类的成员变量
//        System.out.println("stu1.equals(per)？"+stu1.equals(per));  //结果为true
//        System.out.println("stu1.equals(stu2)？"+stu1.equals(stu2));  //结果为true
        
        //<2> 若Student类覆盖equals(Object)方法，以下执行Student类方法
        System.out.println("stu1.equals(per)？"+stu1.equals(per));
               //参数是Person实例，不是要找的对象，不用比较成员变量，结果为false
        System.out.println("stu1.equals(stu2)？"+stu1.equals(stu2));
               //先执行Person类方法，返回true；再比较Student类的成员变量，结果为false

        Student stu3 = new Student(stu2);                        //拷贝构造方法
        System.out.println("s3.equals(stu2)？"+stu3.equals(stu2));
               //先执行Person类方法，返回true；再比较Student类的成员变量，结果为true//相等时，不能说明规则不同
    }
}
/*
 程序运行结果如下：
        //<1> 若Student类没有覆盖equals(Object)方法
per：李小明,1994年03月15日,,,
stu1：李小明,1994年03月15日,,,,,计算机,
stu2：李小明,1994年03月15日,,,,,信息管理,
per.equals(stu1)？true
stu1.equals(per)？true
stu1.equals(stu2)？true                               //结果错误

        //<2> 若Student类覆盖equals(Object)方法
per：李小明,1994年03月15日,,,
stu1：李小明,1994年03月15日,,,,,计算机,
stu2：李小明,1994年03月15日,,,,,信息管理,
per.equals(stu1)？true                               //此句及以上，结果同上
stu1.equals(per)？false                              //结果与上述情况不同
stu1.equals(stu2)？false                              //结果正确，与上述情况不同
stu3.equals(stu2)？true                               //相等时，不能说明规则不同
*/
//@author：Yeheya，2016-8-14