//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//4.3.2   java.util包中的工具类库
//2.  Comparator比较器接口
//3.  Arrays数组类
//【例4.4】  对象数组排序和查找。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）、例3.3（Person）和例3.5（Student）。
//【思考题4-6】 ① 声明CompareName比较器类，按姓名比较Person对象大小。
//4.4 泛型

import java.util.Arrays;

public class StudentSort 
{
    public static void main(String args[])
    {
        Student value[]={new Student("Li李小明", new MyDate(1992,3,15),"","","","","","006",true),
                new Student("Bai白杨", new MyDate(1991,5,1),"","","","","","040",true),
                new Student("Bai白桦", new MyDate(1993,5,5),"","","","","","032",true),
                new Student("Hua华文", new MyDate(1992,10,3),"","","","","","012",true),
                new Student("Wang王伟", new MyDate(1990,4,3),"","","","","","011",true),                
                new Student("Li李小东", new MyDate(1990,2,1),"","","","计算机","","021",true),
                new Student("Li李小明", new MyDate(1992,3,15),"","","","计算机","计算机科学与技术","001",true),
                new Student("Zhang张小莉", new MyDate(1989,5,1),"","","","电子信息","","001",true)};
       
        //（3）Student对象数组按学号排序和查找
//      Arrays.sort(value, new CompareNumber()); 
        CompareArray.sort(value, new CompareNumber());
        System.out.println("按学号次序排序如下：");
        CompareArray.println(value);                   

        String number="001";
        Student key = new Student("", null,"","","","","",number,true);
//        i = Arrays.binarySearch(value, key, new CompareBirthday());
        int i=CompareArray.binarySearch(value, key, new CompareNumber());
        System.out.println("查找学号为"+number+"的结果："+
                          ((i>=0 && i<value.length) ? value[i].toString() : "未找到"));
        CompareArray.printAll(value, key, new CompareNumber());
    }
}
/* 
程序运行结果如下:
按学号次序排序如下：
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
Zhang张小莉,1989年05月01日,,,,电子信息,,001,团员
Li李小明,1992年03月15日,,,,,,006,团员
Wang王伟,1990年04月03日,,,,,,011,团员
Hua华文,1992年10月03日,,,,,,012,团员
Li李小东,1990年02月01日,,,,计算机,,021,团员
Bai白桦,1993年05月05日,,,,,,032,团员
Bai白杨,1991年05月01日,,,,,,040,团员

查找学号为001的结果：Zhang张小莉,1989年05月01日,,,,电子信息,,001,团员
Li李小明,1992年03月15日,,,,计算机,计算机科学与技术,001,团员
Zhang张小莉,1989年05月01日,,,,电子信息,,001,团员

*/
