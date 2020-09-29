//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 闭合图形抽象类及其子类。 
//【思考题3-7】圆是椭圆特例，继承

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

public final class Circle extends Ellipse        //圆类，继承椭圆类，最终类，不能被继承
{
    //构造方法，point、length参数分别指定圆外切矩形的左上角点坐标、长度
    public Circle(Point point, int length)
    {
        super(point,length,length);              //圆是椭圆特例，
        this.shape = "圆";                        //shape权限必须为protected，不能为private
                                                 //省略此句，shape值为"椭圆"
    }
    public Circle()
    {
        this(new Point(),0);
    }
    public String toString()
    {
        return //this.getClass().getName()+
        		"，外切正方形(左上角点"+this.point1+"，边长"+this.length+")";
    }
}
//@author：Yeheya，2015-7-18