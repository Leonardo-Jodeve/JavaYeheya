//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.4   泛型
//2.  泛型的必要性

public class Genericity_ex 
{
    public static void main(String args[])
    {
        //（1） Object[]对象数组
//        Object[] value={new Object(),"xyz", new Integer(123)};  //Object[]元素可引用任何类的实例
//        value[i].toString();                                 //运行时多态
//        value[1].length();                                   //编译错，Object类没有声明length()方法

        //（2） Comparable[]对象数组
        Comparable[] value={"abc", "xyz", "def", "ghi", new Integer(123)};      //数组元素类型不同
        value[0].compareTo(value[1]);                        //两个数组元素可比较对象大小
        value[3].compareTo(value[4]);                     //String实例与Integer实例不可比，抛出异常
//        Comparable<String>[] svalue={"abc","def"};      //语法错，无法创建泛型数组
        
        //（3） Comparable<T>限制类型为T的一种对象
        Comparable<String> scom="abc";                     //正确，String实现了Comparable<String>接口
        Comparable<Person> pcom = new Person("李小明",null,"男","","");  //正确，Person实现了Comparable<Person>接口
        pcom = new Student("张莉",null,"女","","","经济管理","","",true);
                  //正确，Student继承Person类，也实现了Comparable<Person>接口，注意并没有实现Comparable<Student>接口
    }
}
//@author：Yeheya，2016-8-22
