//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日
//§4.1 接口与实现接口的类
//7.  单继承和多继承
//图4.7，接口的多继承不存在二义性冲突，问题讨论

//(a) 接口声明与父接口同名方法，有重载与覆盖两种多态
public interface Volume1 extends Area  //接口继承
{
    public abstract double area();     //覆盖，与父接口方法的参数列表和返回值类型均相同，重新约定 
//  public abstract int area();        //编译错，覆盖含义但返回值类型不同 
    public abstract int area(int n);   //重载，与父接口方法的参数列表不同 
    public abstract double volume();   //增加的方法
}

//(b) 接口多继承，多个父接口中有相同的抽象方法声明
interface Solid1 extends Area,Volume1    
{}
//正确，Area,Volume1接口的area()约定相同，继承来以下
//public abstract double area(); 
//public abstract int area(int n);     //重载

//(b) 类实现多接口，多个父接口中有相同的抽象方法声明，继承原则相同
class Globe1 extends Object implements Solid1         //球类，实现接口
//class Globe1 extends Object implements Area,Volume1  //球类，实现接口
{
    public double area()  {return 0;}                //两种约定，一种实现，含义由类确定 
    public int area(int n){return 0;}                //重载 
    public double volume(){return 0;} 
}

//(c) 接口多继承，两个父接口中有同名方法，声明有冲突，语法错误
//编译错，Area1,Volume1接口的area()约定相同
interface Area1                            //可计算面积接口
{
    public abstract int area();               //抽象方法，计算面积 
}
/*
interface Volume2 extends Area,Area1   //编译错,Area,Area2中重复声明area()，且两者返回不同不能重载
{
    public abstract double volume();
}*/
//@author：Yeheya，2016-8-17，2018年7月18日