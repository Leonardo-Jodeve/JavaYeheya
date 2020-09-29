//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日
//§4.1 接口与实现接口的类
//1. 声明接口
//2. 声明实现接口的类
//【例4.1】  Area、Volume接口与实现这些接口的柱体类。
//MyEclipse设置编译路径包含项目：例1.2、例3.7。

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//柱体类，实现可计算面积接口和可计算体积接口
public class Cylinder extends Object implements Area,Volume
{
    //以下声明抽象类对象cfigure，引用闭合图形抽象类（见例3.7）的子类对象，指定底面图形
	public ClosedFigure cfigure;
    protected double height;                     //高度

    public Cylinder(ClosedFigure cfigure, double height)    //构造方法
    {                                  
        this.cfigure = cfigure;
        this.height = height;
    }
    public double area()                         //计算柱体的表面积，实现Area接口中的抽象方法
    {
        return cfigure.perimeter()*this.height + 2*cfigure.area();//其中perimeter()计算底面图形周长
    }
    public double volume()                       //计算柱体的体积，实现Volume接口中的抽象方法
    {
        return cfigure.area() * this.height;     //其中area()计算底面图形面积
    }
    public String toString()
    {
        return this.getClass().getName()+        //第4章4.3.1节，【思考题4-4】
                "底面是"+this.cfigure.toString()+"；高"+this.height;
            //   +"，表面积"+String.format("%1.2f，体积%1.2f",this.area(),this.volume());
    }

    public static void main(String[] args)
    {
        Point point = new Point(100,100);
        
        ClosedFigure cfig = new Ellipse(point,10,20);      //椭圆
        System.out.println("Elliptic Cylinder椭圆柱，"+new Cylinder(cfig,10).toString());
        cfig = new Rectangle(point,10,20);                 //矩形
        System.out.println("Cuboid长方体，"+new Cylinder(cfig,10).toString());
        cfig = new EuilateralTriangle(point,10);           //等边三角形
        System.out.println("Trianglular Prism三棱柱，"+new Cylinder(cfig,10).toString());
        Point[] points5={point, new Point(200,100), new Point(250,150), new Point(200,200),new Point(100,200)};
        cfig = new Polygon(points5);                       //五边形
        System.out.println("五棱柱，"+new Cylinder(cfig,10).toString());
    }
}

/*
程序运行结果如下：
Elliptic Cylinder椭圆柱，底面是椭圆，原心点坐标Point(100,100)，a轴半径10.0，b轴半径20.0；高10.0，表面积2199.11，体积6283.19
Cuboid长方体，底面是矩形，左上角点坐标Point(100,100)，长度10.0，宽度20.0；高10.0，表面积1000.00，体积2000.00
Trianglular Prism三棱柱，底面是等边三角形，点坐标Point(100,100)，边长10.00；高10.0，表面积386.60，体积433.01
五棱柱，底面是多边形，5个点Point(100,100)，Point(200,100)，Point(250,150)，Point(200,200)，Point(100,200)；高10.0，表面积29414.21，体积125000.00

*/
//@author：Yeheya，2016-8-17