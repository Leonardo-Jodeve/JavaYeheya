//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月25日
//§3.1  类和对象
//【例3.1】  声明日期类及使用日期对象。

public class MyDate                                        //日期类声明
{
    int year, month, day;                                  //成员变量，表示年、月、日
    
    void set(int y, int m, int d)                          //成员方法，设置日期值
    { 
        year = y;
        month = m;
        day = d;
    } 
    void set(MyDate date)                                  //将当前对象值设置为参数值，重载
    {
        set(date.year, date.month, date.day);              //调用重载的同名成员方法，不能使用this()
    } 
    
    public String toString()                               //返回描述对象字符串，中文日期格式
    {
        return year+"年"+month+"月"+day+"日";
        //【思考题3-1】② 如果toString()方法实现为如下，会怎样？
//      return year+'-'+month+'-'+day;                     //【答】语法错，运算结果是int，改正+"-"+;
    }   
    
    public static void main(String[] args)
    {
        MyDate d1 = new MyDate();                          //声明对象、创建实例、引用赋值
        System.out.println("d1："+d1.toString());
        d1.set(2017,1,1);                                  //调用类的成员方法
        MyDate d2 = d1;                                    //对象引用赋值
        System.out.println("d1："+d1.toString()+"，d2："+d2.toString());
        d2.month = 10;                                     //修改实例成员变量值
        System.out.println("d1："+d1+"，d2："+d2);         //输出对象字符串描述，默认调用d1.toString()
        d2 = new MyDate();                                 //创建另一个实例
        d2.set(d1);
        System.out.println("d1："+d1+"，d2："+d2);
    } 
}
/* 
程序运行结果如下：
d1：0年0月0日
d1：2017年1月1日，d2：2017年1月1日
d1：2017年10月1日，d2：2017年10月1日
d1：2017年10月1日，d2：2017年10月1日
*/
/*
程序错误：
        MyDate date;
        System.out.println("==null"+date.equals(null));//编译错，变量未初始化variable d might not have been initialized

        MyDate d3 = (MyDate)d2.clone();             //CloneNotSupportedException
        System.out.println("d3："+d3+"，d3==d2？"+(d3==d2)+"，d3.equals(d2)？ "+(d3.equals(d2)));
       
*/
//@author：Yeheya，2016-7-25