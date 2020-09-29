//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.3.2   java.util包中的工具类库
//【例4.5】  Person对象数组调用排序和二分法查找算法，多种比较规则。//【思考题4-7】
//【例6.4】  Person对象信息管理。用

public interface Equalable<T>                    //可比较对象相等接口，提供多种查找依据
{
    public boolean equals(T t1, T t2);           //比较t1、t2对象是否相等
}

class EqualName implements Equalable<Person>     //按姓名比较Person对象是否相等
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.name.equals(p2.name);          //比较姓名字符串是否相等，调用String类的equals()
    }
}

class EqualBirthdate implements Equalable<Person> //按出生日期比较Person对象是否相等
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.birthdate.equals(p2.birthdate);//比较出生日期是否相等，调用MyDate类的equals()
    }
}

//【思考题4-7】教材未用
class EqualSurname implements Equalable<Person>  //按姓氏比较Person对象是否相等
{
    public boolean equals(Person p, Person key)
    {
        return p.name.startsWith(key.name);      //p.name是否以key.name开头
    }
}

class EqualYear implements Equalable<Person>     //按出生年份比较Person对象是否相等
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.birthdate.getYear()==p2.birthdate.getYear();//比较出生日期中的年份
    }
}
//@author：Yeheya，2016-8-22