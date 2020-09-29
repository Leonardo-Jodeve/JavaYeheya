//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//4.3.2   java.util包中的工具类库
//2.  Comparator比较器接口
//3.  Arrays数组类
//【例4.4】  对象数组排序和查找。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）、例3.3（Person）和例3.5（Student）。
//4.4 泛型

import java.util.Arrays;                                   //数组类

class PersonSort_ex
{
    public static void main(String args[])
    {
        Person per = new Person("Li李小明",new MyDate(1994,3,15),"男","湖南","长沙");
/*        Person[] value={per, new Person("Bai白杨", new MyDate(1991,5,1)),
                        new Person("Bai白桦", new MyDate(1994,3,15)),
                        new Person("Hua华文", new MyDate(1992,10,3)),
                        new Person("Wang王伟", new MyDate(1990,4,3)),
                        new Student(p, "计算机","计算机科学与技术","001",true),
                        new Student("Li李小东", new MyDate(1990,2,1),"","","","计算机","计算机科学与技术","008",true),
                        new Student("Zhang张小莉", new MyDate(1989,5,1),"","","","自动化","自动控制","020",true)};
*/
        Student value[]={new Student("Li李小明", new MyDate(1994,3,15),"","","","","","",true),
                new Student("Bai白杨", new MyDate(1991,5,1),"","","","","","",true),
                new Student("Bai白桦", new MyDate(1993,5,5),"","","","","","",true),
                new Student("Hua华文", new MyDate(1992,10,3),"","","","","","",true),
                new Student("Wang王伟", new MyDate(1990,4,3),"","","","","","",true),                
                new Student("Li李小东", new MyDate(1990,2,1),"","","","计算机","","",true),
                new Student("Li李小明", new MyDate(1994,3,15),"","","","计算机","计算机科学与技术","001",true),
                new Student("Zhang张小莉", new MyDate(1989,5,1),"","","","电子信息","","",true)};
       
        //（1）对象数组按姓名排序和查找，默认由Person类compareTo()方法比较对象大小
//        Arrays.sort(value);                                //value对象数组按姓名升序排序
        CompareArray.sort(value);
        System.out.println("按姓名次序排序如下：");
        CompareArray.println(value);                   

        //按姓名查找
        String name="Bai白杨";//"Li李";
        Person key = new Person(name,null);
//      int i=Arrays.binarySearch(value, key);              //以二分法查找key指定对象
        int i=CompareArray.binarySearch(value, key);     //二分法查找
        System.out.println("查找姓名\""+name+"\"的结果： "+
                          ((i>=0 && i<value.length) ? value[i].toString() : "未找到"));
        name="Li李小明";
        key = new Person(name,null);
        System.out.println("查找所有姓名\""+name+"\"的结果： ");
        CompareArray.printAll(value, key);               //顺序查找所有姓名为name的对象
        
        //提供按姓氏查找
        name="Bai白";//"Li李";
        key = new Person(name,null);
        i=CompareArray.binarySearch(value, key, new CompareSurname());
        System.out.println("查找姓\""+name+"\"的结果："+
                          ((i>=0 && i<value.length) ? value[i].toString() : "未找到"));

        //习题，提供按姓名查找的模糊查询功能
        name="小";
        key = new Person(name,null);
//        i=Arrays.binarySearch(value, key, new CompareContainName());
        i=CompareArray.binarySearch(value, key, new CompareContainName());
        System.out.println("查找姓名包含\""+name+"\"的结果： "+
                          ((i>=0 && i<value.length) ? value[i].toString() : "未找到"));
        
        
        //（2）Person对象数组按出生日期排序
//        Arrays.sort(value, new CompareBirthday());           //按CompareByAge类compare()方法比较规则排序
        CompareArray.sort(value, new CompareBirthdate());  //默认对象数组实现Comparable接口按compareTo()方法约定的比较规则排序
        System.out.println("\n按出生日期排序如下：");
        CompareArray.println(value);
        
        MyDate date = new MyDate(1994,3,15);
        key = new Person("", date);
//        i = Arrays.binarySearch(value, key, new CompareBirthday());     //按CompareAge类compare()方法比较
        i=CompareArray.binarySearch(value, key, new CompareBirthdate());
        System.out.println("查找出生日期为"+date.toString()+"的结果："+
                          ((i>=0 && i<value.length) ? value[i].toString() : "未找到"));
        CompareArray.printAll(value, key, new CompareBirthdate());  //默认对象数组实现Comparable接口按compareTo()方法约定的比较规则排序

    }
}
/* 
程序运行结果如下,Person[]和Student[]结果相同，因为两者均实现Comparable<Person>接口，compareTo(Person)方法规则相同。

按姓名次序排序如下：
Bai白杨,1991年05月01日,,,,,,,团员
Bai白桦,1993年05月05日,,,,,,,团员
Hua华文,1992年10月03日,,,,,,,团员
Li李小东,1990年02月01日,,,,计算机,,,团员
Li李小明,1992年03月15日,,,,,,,团员
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
Wang王伟,1990年04月03日,,,,,,,团员
Zhang张小莉,1989年05月01日,,,,电子信息,,,团员

查找姓名"Bai白杨"的结果： Bai白杨,1991年05月01日,,,,,,,团员
查找所有姓名"Li李小明"的结果： 
Li李小明,1992年03月15日,,,,,,,团员
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
查找姓"Bai白"的结果：Bai白桦,1993年05月05日,,,,,,,团员
查找姓名包含"小"的结果： Li李小东,1990年02月01日,,,,计算机,,,团员

按出生日期排序如下：
Zhang张小莉,1989年05月01日,,,,电子信息,,,团员
Li李小东,1990年02月01日,,,,计算机,,,团员
Wang王伟,1990年04月03日,,,,,,,团员
Bai白杨,1991年05月01日,,,,,,,团员
Li李小明,1992年03月15日,,,,,,,团员
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
Hua华文,1992年10月03日,,,,,,,团员
Bai白桦,1993年05月05日,,,,,,,团员

查找出生日期为1992年03月15日的结果：Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
Li李小明,1992年03月15日,,,,,,,团员
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员

*/
