//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//【例3.7】 图形抽象类及其子类。
//【思考题3-7】等边三角形是三角形特例，继承

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//等边三角形类，继承三角形类，最终类，不能被继承
public final class EuilateralTriangle extends Triangle
{
    //构造方法，length参数指定边长，point2、point3为null
    public EuilateralTriangle(Point point, double length)
    {
        super(point,length,length,length);
        this.shape = "等边三角形";        //shape权限必须为protected，不能为private，省略此句，shape值为"三角形"                                                           
    }
    public EuilateralTriangle()
    {
        this(new Point(),0);
    }
    public String toString()
    {
        return //this.getClass().getName()+this.shape+
               //super.toString()+"，边长"+String.format("%1.2f",this.a);  //不行super.toString()调用父类，不能调用Figure类方法
                "("+(this.point1==null ? "" : this.point1.toString())+ //避免空对象异常。计算周长和面积时，可以没有点坐标
               "，边长"+String.format("%1.2f)",this.a); 
    }
}
//@author：Yeheya，2016-8-15