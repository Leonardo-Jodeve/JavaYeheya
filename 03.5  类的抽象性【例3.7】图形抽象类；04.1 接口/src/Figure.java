//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//MyEclipse设置编译路径包含项目：例1.2。

//图形抽象类，为子类声明表示位置的坐标点
//可以不包含抽象方法，作用是不能创建实例
//import java.awt.Graphics;

import java.awt.Color;
import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

public abstract class Figure
{
    //public 
    protected Point point1;            //坐标点，表示位置，一个图形至少有一个点。保护权限，子类可见
    
    //构造方法，不能是抽象方法。protected权限表示不能创建实例，构造方法只为了子类调用
    protected Figure(Point point1)
    {
        this.point1 = point1;
//        this.set(point);
    }    
//    protected Figure()                           //构造方法。//何处用到？第5版书没写
//    {
//        this(new Point());
//    }
    
    public String toString()                     //对象描述字符串，输出点位置。子类备用
    {
        return //this.point.toString();            //第5章，若point为null，Java将抛出空对象异常
               this.point1==null ? "" : this.point1.toString();
               //若point为空对象，输出空串，避免空对象异常。计算周长和面积时，可以没有点坐标
    }
    //以上【例3.7】，抽象类中可以不包含抽象方法，不能创建实例。作用是：为子类提供通用的方法，因此，不需要创建本类实例。 
    
    //以下第5版教材未写
    //【例3.7问题讨论】 若声明point权限非public 
    public void set(Point point1)
    {
        this.point1 = point1;
    }
    public Point getPoint()
    {
        return this.point1;
    }
    
    //【思考题3-7】
//    public abstract void draw(java.awt.Graphics g);//绘图，参数类型说明详见第6章
//    public abstract void revolve(int angle);         	//旋转，angle参数指定角度
//    public abstract void zoom(int percentage);      	//缩放，percentage参数指定百分比
}

class Figure_ex  
{
    public static void main(String args[])
    {
        Figure fig1 = new Line(100,100,500,400);           //抽象类对象引用子类实例
        Figure fig2 = new Line(new Point(100,100), new Point(200,200));
        System.out.println("fig1："+fig1.toString());       //toString()运行时多态
        System.out.println("fig2："+fig2.toString()); 
        //不能比大小
    }
}
/*
程序运行结果如下：
fig1：Line(Point(100,100), Point(500,400))，长度500.00
fig2：Line(Point(100,100), Point(200,200))，长度141.42
*/
//@author：Yeheya，2016-8-14