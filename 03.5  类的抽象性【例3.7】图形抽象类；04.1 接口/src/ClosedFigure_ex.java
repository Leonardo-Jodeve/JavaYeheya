//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。 

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

public class ClosedFigure_ex 
{
    public static void main(String[] args)       //main()方法也可写在ClosedFigure抽象类中
    {
        Point p1 = new Point(100,100);
        ClosedFigure cfig1;
        cfig1 = new Rectangle(100,100, 300,200); //矩形，长200，宽100，参数是两点坐标；父类对象引用子类实例
        ClosedFigure cfig2 = new Square(p1,100); //正方形，直径100；引用子孙类（后代类）实例
        System.out.print("cfig1：");  cfig1.print();
        System.out.print("cfig2：");  cfig2.print();
        System.out.println("cfig1比cfig2面积大多少？"+cfig1.compareTo(cfig2));//第4章
        
        cfig1 = new Ellipse(100,100, 300,200);   //椭圆，直径长200，宽100，参数是两点坐标；父类对象引用子类实例
        cfig2 = new Circle(p1,100);              //圆，外切正方形，直径100；引用子孙类（后代类）实例
        System.out.print("cfig1：");  cfig1.print();
        System.out.print("cfig2：");  cfig2.print();
        System.out.println("cfig1比cfig2面积大多少？"+cfig1.compareTo(cfig2));//第4章
        
        cfig1 = new Triangle(p1, new Point(100,130), new Point(140,130));  //直角三角形
        System.out.print("cfig1：");  cfig1.print(); //输出对象属性，其中toString()、perimeter()、area()运行时多态

        cfig2 = new EuilateralTriangle(p1, 20);  //等边三角形，【思考题3-8】
        System.out.print("cfig2：");  cfig2.print();
        System.out.println("cfig1比cfig2面积大多少？"+cfig1.compareTo(cfig2));//第4章

        Point[] pentagon={p1, new Point(200,100), new Point(250,150), new Point(200,200), new Point(100,200)};
        cfig1 = new Polygon(pentagon);           //cfig引用五边形实例
        System.out.print("cfig1：");  cfig1.print();
    }
}
/*
程序运行结果如下：
cfig1：矩形(左上角点(100,100)，长度200，宽度100)，周长600.00，面积20000.00
cfig2：正方形(左上角点坐标(100,100)，边长100)，周长400.00，面积10000.00
cfig1比cfig2面积大多少？10000
cfig1：椭圆，外切矩形(左上角点(100,100)，长度200，宽度100)，周长471.24，面积15707.96
cfig2：圆，外切正方形(左上角点(100,100)，边长100)，周长314.16，面积7853.98
cfig1比cfig2面积大多少？7853
cfig1：三角形(3点坐标(100,100),(100,130),(140,130)，3边长30.00,40.00,50.00)，周长120.00，面积600.00
cfig2：等边三角形((100,100)，边长20.00)，周长60.00，面积173.21
cfig1比cfig2面积大多少？426
cfig1：多边形(5个点(100,100),(200,100),(250,150),(200,200),(100,200))，周长441.42，面积12500.00

*/
/*
    Triangle t1 = new Triangle(new Point(100,100), new Point(200,100), new Point(200,200));
         new Point(150,100), new Point(200,100)));      //三点共线
程序运行结果如下：
三角形的面积是 0.0
*/
//@author：Yeheya，2016-8-15