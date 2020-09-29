//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//（4）三角形类

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//矩形类，继承闭合图形抽象类；继承祖先类Figure的point表示三角形一个点；//【试题4】继承父类ClosedFigure的比较对象大小的方法
public class Triangle extends ClosedFigure       //三角形类，继承闭合图形抽象类
{
    public Point point2, point3;                 //三角形的3个点，继承point1
    protected double a,b,c;                      //3条边长度
    
    //3点构造一个三角形；//若三点共线，三角形的周长和面积为0
    //第5章，若参数为null，调用方法时，Java将抛出空对象异常；若三点共线，不能构成一个三角形，抛出无效参数异常
    public Triangle(Point p1, Point p2, Point p3) throws IllegalArgumentException
    {
        super("三角形",p1);
//        if (new Line(p1,p2).contains(p3) || new Line(p1,p3).contains(p2) || new Line(p2,p3).contains(p1))
//            throw new IllegalArgumentException("三点共线，不能构成一个三角形。");
        this.point2 = p2; 
        this.point3 = p3; 
        this.a = new Line(p1,p2).length();
        this.b = new Line(p2,p3).length();
        this.c = new Line(p3,p1).length();
    }
    //参数a、b、c指定三条边长度，point2、point3为null；
    //等边三角形用；第5版教材未给实现，检查三边能否构成一个三角形？
    public Triangle(Point point1, double a, double b, double c)
    {
        super("三角形",point1);
        this.point2 = this.point3 = null; 
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public String toString()                     //对象描述字符串，包括3点位置和3边长度属性。覆盖，扩展功能
    {
        return //this.getClass().getName()+this.shape+
               "(3点坐标"+super.toString()+","+//+this.point2.toString()+ ","+this.point3.toString()+
               (this.point2==null ? "" : this.point2.toString())+ ","+
               (this.point3==null ? "" : this.point3.toString())+
               "，3边长"+String.format("%1.2f,%1.2f,%1.2f)",this.a,this.b,this.c);
               //避免空对象异常。只有3点不是空对象时才能输出。第5章，若point2、point3为null，Java将抛出空对象异常
    }
    public double perimeter()                    //返回三角形周长
    {
        return a+b+c;
    }
    public double area()                         //返回三角形面积，若三点共线，面积为0
    {
        double s=(a+b+c)/2;                      //求三角形面积的海伦公式
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));   //Math.sqrt(x)返回x的平方根
    }
    //第6章 
    public void draw(java.awt.Graphics g)        //绘图
    {
        g.drawLine(this.point1.x, this.point1.y, this.point2.x, this.point2.y);
        g.drawLine(this.point2.x, this.point2.y, this.point3.x, this.point3.y);
        g.drawLine(this.point3.x, this.point3.y, this.point1.x, this.point1.y);
    }
}    
    
    //第5版教材未用
/*    public static double area(Point p1, Point p2, Point p3)//返回三角形面积
    {
        return new Triangle(p1,p2,p3).area();    //静态成员方法中可以创建实例
    }

    //【思考题3-8】未完成
    public boolean contains(Point p)             //判断指定点是否在当前三角形的范围内
    {
        return false;
    }
   
    public boolean contains(int x, int y)        //判断指定点是否在当前三角形的范围内
*/
//@author：Yeheya，2016-8-15