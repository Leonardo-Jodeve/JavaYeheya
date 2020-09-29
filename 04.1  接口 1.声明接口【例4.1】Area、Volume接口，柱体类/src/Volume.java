//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日
//§4.1 接口与实现接口的类
//1. 声明接口
//2. 声明实现接口的类
//【例4.1】  Area、Volume接口与实现这些接口的柱体类。
import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

public interface Volume                          //可计算体积接口
{
    public abstract double volume();             //抽象方法，计算体积
}


class AreaVolumn_ex                              //接口是一种引用数据类型
{
    public static void main(String args[])
    {
        Point point = new Point(100,100);
        ClosedFigure cfig = new Ellipse(point,10,20);      //父类对象cfig引用椭圆子类实例
        Area ar = cfig;                          //Area接口对象ar引用实现Area接口的ClosedFigure类的Ellipse子类实例
        System.out.println(cfig.toString()+"；面积"+String.format("%1.2f", ar.area()));
        Cylinder cylinder = new Cylinder(cfig,10);//椭圆柱
        ar = cylinder;                            //ar引用实现Area接口的Cylinder类的实例
        Volume vol = cylinder;                    //Volume接口对象vol引用实现Volume接口的Cylinder类的实例
        System.out.println("Elliptic Cylinder椭圆柱，"+cylinder.toString()
                           +"；表面积"+String.format("%1.2f，体积%1.2f", ar.area(), vol.volume()));

        cylinder.cfigure = new Rectangle(point,10,20);     //矩形
        System.out.println("Cuboid长方体，"+cylinder.toString()
                +"；表面积"+String.format("%1.2f，体积%1.2f", ar.area(), vol.volume()));
        cylinder.cfigure = new EuilateralTriangle(point,10);//等边三角形
        System.out.println("Trianglular Prism三棱柱，"+cylinder.toString()
                +"；表面积"+String.format("%1.2f，体积%1.2f", ar.area(), vol.volume()));

        Globe globe = new Globe(point,10);                 //球 
        ar = globe;                    //Area接口对象ar引用实现Area接口Solid子接口的Globe类的实例
        vol = globe;                   //Volume接口对象vol引用实现Volume接口Solid子接口的Globe类的实例
        System.out.println(globe.toString()+"；表面积"+String.format("%1.2f，体积%1.2f",ar.area(), vol.volume()));
    }
}

/*
程序运行结果如下：
Ellipse椭圆，原心点坐标Point(100,100)，a轴半径10.0，b轴半径20.0；面积628.32
Elliptic Cylinder椭圆柱，Cylinder底面是Ellipse椭圆，原心点坐标Point(100,100)，a轴半径10.0，b轴半径20.0；高10.0；表面积2199.11，体积6283.19
Cuboid长方体，Cylinder底面是Rectangle矩形，左上角点坐标Point(100,100)，长度10.0，宽度20.0；高10.0；表面积1000.00，体积2000.00
Trianglular Prism三棱柱，Cylinder底面是EuilateralTriangle等边三角形，点坐标Point(100,100)，边长10.00；高10.0；表面积386.60，体积433.01
Globe球，半径10.0；表面积1256.64，体积3141.59

*/
//@author：Yeheya，2016-8-17