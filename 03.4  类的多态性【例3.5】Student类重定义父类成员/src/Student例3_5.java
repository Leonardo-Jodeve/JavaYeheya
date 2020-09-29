//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.4   类的多态性
//§3.4.1 子类重定义父类成员
//【例3.5】  类的多态性，Student类声明多态成员。

public class Student例3_5 
{
    public static void main(String[] args)
    {
        Student.howMany();                                 //执行子类静态成员方法
        Person per = new Person("李小明",new MyDate(1994,3,15),"男","湖南省","长沙市");//per对象引用本类实例
        Student stu1 = new Student(per,"计算机系","计算机科学与技术专业","211994001",true); //由Person实例提供初值
        Student stu2 = new Student(stu1);                      //拷贝构造方法
        stu2.set("张莉",new MyDate(1998,4,5),"女","湖北省","武汉市");//调用父类的成员方法
        stu2.set("经济管理系","信息管理与信息系统专业","321998003",true); //调用子类重载的成员方法
        Student.howMany();                                 //调用子类静态成员方法
        System.out.println("per："+per.toString()+"\nstu1："+stu1.toString()+"\nstu2："+stu2);//编译时多态性        
        stu2.finalize();                                     //调用子类的析构方法
        Student.howMany();                                 //执行子类静态成员方法
    }
}
/*
 程序运行结果如下：
0个Person对象，0个Student对象
3个Person对象，2个Student对象
per：李小明,1994年03月15日,男,湖南省,长沙市
stu1：李小明,1994年03月15日,男,湖南省,长沙市,计算机系,计算机科学与技术专业,211994001,团员
stu2：张莉,1998年04月05日,女,湖北省,武汉市,经济管理系,信息管理与信息系统专业,321998003,团员
释放对象 (张莉,1998年04月05日,女,湖北省,武汉市,经济管理系,信息管理与信息系统专业,321998003,团员)
2个Person对象，1个Student对象
*/
//@author：Yeheya，2016-8-14