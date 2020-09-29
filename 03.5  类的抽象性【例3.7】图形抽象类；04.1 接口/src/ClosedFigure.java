//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。 
//§4.1 接口与实现接口的类

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//闭合图形抽象类，继承图形抽象类，实现可计算面积接口和可计算周长接口；【试题4】实现可比较接口
public abstract class ClosedFigure extends Figure implements Area,Perimeter, Comparable<ClosedFigure>
{
    protected String shape;            //形状，保护权限，子类可见。
                                       //第3版声明此，是因为，要说明可继承父类成员变量，此功能已实现，point1
                                       //第3章要声明的，含义同获得本类名，类名已表示其形状（第4章才能讲到）
    
    protected ClosedFigure(String shape, Point point1)//构造方法
    {
        super(point1);                           //调用父类构造方法，初始化父类的point1
        this.shape = shape;
    }
    protected ClosedFigure(String shape)         //第5版教材没写
    {
        this(shape, new Point());
    }
    protected ClosedFigure()
    {
        this("", new Point());
    }

//    public abstract double perimeter();          //计算周长，抽象方法
//    public abstract double area();               //计算面积，抽象方法，以分号";"结束 

    public void print()                          //输出对象属性及周长和面积，调用抽象方法
    {
        //下句中toString()继承Figure类，输出点；
    	//toString()、perimeter()、area()子类覆盖，运行时多态
    	System.out.println(this.shape+
           //this.getClass().getName()+          //第4章4.3.1节，【思考题4-4】
            this.toString()+
            "，"+String.format("周长%1.2f，面积%1.2f",this.perimeter(),this.area()));
    }
    
    //【思考题3-8】
    //【试题4】2015年第4版样卷 
    public int compareTo(ClosedFigure cfig)       //按面积比较对象大小
    {
        return (int)(this.area() - cfig.area());
    }
    //以下未完成
    //public abstract boolean contains(Point p); //判断p点是否在this闭合图形区域内

}
/*
    //第4版，第5版未用
    protected ClosedFigure(Point point1)         //构造方法，不能是抽象方法
    {
        super(point1);                           //调用父类构造方法，初始化父类的point1
    }
*/
//@author：Yeheya，2016-8-17