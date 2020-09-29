//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.3.2   java.util包中的工具类库
//【例4.4】  Person对象数组调用排序和二分法查找算法，多种比较规则。//【思考题4-6】
//MyEclipse设置编译路径包含项目：例3.2（MyDate）、例3.3（Person）和例3.5（Student）。
//【例6.4】  Person对象信息管理。【思考题6-4】用

//import java.util.Comparator;                   //比较器接口

public class CompareNumber implements java.util.Comparator<Student>  //学号比较器类
{
    public int compare(Student str1, Student str2)  //按学号比较Student对象大小
    {
        return str1.number.compareTo(str2.number);
    }
}

class CompareNumber_ex
{
    public static void main(String[] args) 
    {
//      Comparator<Student> c = new CompareNumber(); //正确，
        
//    Comparator<Person> c = new CompareNumber(); //编译错，
        java.util.Comparator<? extends Person> c = new CompareNumber(); //正确，
      
//      java.util.Comparator<? super Person> c = new CompareNumber(); //编译错，
//      java.util.Comparator<?> c = new CompareNumber(); //正确，
    }
}
//@author：Yeheya，2016-8-22