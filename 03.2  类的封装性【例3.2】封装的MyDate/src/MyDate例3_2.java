//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§3.2  类的封装性
//【例3.2】  封装的日期类。

public class MyDate例3_2 
{
    public static void main(String args[])       //main方法也是静态成员方法
    {
        System.out.println("今年是"+MyDate.getThisYear()+
                "，闰年？"+MyDate.isLeapYear(MyDate.getThisYear()));//调用静态方法
        MyDate d1 = new MyDate(2016,1,31);//2017,12,31);//();(2018,1,1);       //调用构造方法
        System.out.println(d1.getYear()+"年，闰年？"+d1.isLeapYear());//调用实例成员方法
        MyDate d2 = new MyDate(d1), d3;                     //调用拷贝构造方法复制实例
        System.out.println("d1："+d1+"，d2："+d2+"，d1==d2？"+(d1==d2)+
                "，d1.equals(d2)？"+d1.equals(d2));          //区别关系运算==与比较相等方法
        System.out.print(d1+"的明天是 ");
        d1.tomorrow();
        System.out.println(d1+"\n"+d1+"的昨天是 "+(d3=d1.yestoday()));
        System.out.println("d1："+d1+"，d2："+d2+"，d3："+d3+"，d2==d3？"+(d2==d3)+"，d2.equals(d3)？"+d2.equals(d3));
    }
}
//  System.out.println("d1："+d1.toString()+"，d2： "+d2+"，d1>=d2？"+(d1>=d2));
/* 
程序运行结果如下：
        //MyDate d1 = new MyDate(2017,12,31);
今年是2016，闰年？true
2017年，闰年？false
d1：2017年12月31日，d2：2017年12月31日，d1==d2？false，d1.equals(d2)？true
2017年12月31日的明天是 2018年01月01日
2018年01月01日的昨天是 2017年12月31日
d1：2018年01月01日，d2：2017年12月31日，d3：2017年12月31日，d2==d3？false，d2.equals(d3)？true

        //MyDate d1 = new MyDate(2016,12,31);
今年是2016，闰年？true
2016年，闰年？true
d1：2016年12月31日，d2：2016年12月31日，d1==d2？false，d1.equals(d2)？true
2016年12月31日的明天是 2017年01月01日
2016年12月31日的昨天是 2016年12月30日
d1：2017年01月01日，d2：2016年12月31日，d3：2016年12月30日

        //MyDate d1 = new MyDate();
今年是2016，闰年？true
1970年，闰年？false
d1：1970年01月01日，d2：1970年01月01日，d1==d2？false，d1.equals(d2)？true
1970年01月01日的明天是 1970年01月02日
1970年01月01日的昨天是 1969年12月31日
d1：1970年01月02日，d2：1970年01月01日，d3：1969年12月31日

        //MyDate d1 = new MyDate(2017,1,1);  
今年是2016，闰年？true
2017年，闰年？false
d1：2017年01月01日，d2：2017年01月01日，d1==d2？false，d1.equals(d2)？true
2017年01月01日的明天是 2017年01月02日
2017年01月01日的昨天是 2016年12月31日
d1：2017年01月02日，d2：2017年01月01日，d3：2016年12月31日

*/
//@author：Yeheya，2016-8-11