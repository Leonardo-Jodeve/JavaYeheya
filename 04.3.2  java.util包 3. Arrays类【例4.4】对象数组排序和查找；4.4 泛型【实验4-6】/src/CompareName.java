//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.3.2   java.util包中的工具类库
//【例4.4】  Person对象数组调用排序和二分法查找算法，多种比较规则。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）、例3.3（Person）和例3.5（Student）。
//【思考题4-6】 ① 声明CompareName比较器类，按姓名比较Person对象大小。
//【例6.4】  Person对象信息管理。用

import java.util.Comparator;

public class CompareName implements java.util.Comparator<Person>//姓名比较器类
{
    public int compare(Person per1, Person per2)      //按姓名比较Person对象大小
    {
        return per1.name.compareTo(per2.name);        //调用String类的compareTo()
    }
}

class CompareBirthdate implements java.util.Comparator<Person>  //出生日期比较器类
{
    public int compare(Person per1, Person per2)      //按出生日期比较Person对象大小
    {
        return per1.birthdate.compareTo(per2.birthdate);//调用MyDate类compareTo()
    }
}


//【思考题4-7】
class CompareSurname implements java.util.Comparator<Person>//按姓氏比较Person对象大小
{
    public int compare(Person per1, Person per2)
    {
        if (per1.name.startsWith(per2.name))         //比较姓氏，调用String类的startsWith()
            return 0;                            //相等
        return per1.name.compareTo(per2.name);       //按字符串比较姓名大小
    }
}

class CompareAge implements java.util.Comparator<Person>   //按年龄比较对象大小，实现比较器接口
{
    public int compare(Person per1, Person per2)
    {
        return per2.birthdate.getYear()-per1.birthdate.getYear();//比较出生日期的年份，调用MyDate类方法
    }
}

//提供按姓氏查找的模糊查询功能，实现比较器接口
class CompareContainName implements java.util.Comparator<Person>
{
    public int compare(Person per1, Person per2)
    {
        if (per1.name.indexOf(per2.name)>=0)              //若per1.name姓名包含per2.name字符串，则相等
            return 0;
        return per1.name.compareTo(per2.name);            //按字符串比较姓名大小
    }
}

class Comparator_ex
{
    public static void main(String[] args) 
    {
        Comparator<Person> c = new CompareBirthdate();
        c = new CompareBirthdate();
        c = new CompareAge();
        Comparator<? extends Person> c2 = new CompareBirthdate();
        c2 = new CompareBirthdate();
        c2 = new CompareAge();

//        Comparator<Person> comps[] = {new CompareName()};//语法错
        Comparator<?> comps1[] = {new CompareBirthdate()};        //语法正确
//        Comparator<? extends Person> comps2[] = {new CompareName()};//语法错
//        Comparator<T extends Person> comps2[] = {new CompareName()};//语法错
    }
}
//@author：Yeheya，2016-10-18