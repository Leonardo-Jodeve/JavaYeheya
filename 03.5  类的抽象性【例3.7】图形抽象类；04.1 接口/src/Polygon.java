//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//（5） 多边形类

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//凸多边形类，继承闭合图形抽象类。//说明：没有用到从Figure类继承的成员变量point1
public class Polygon extends ClosedFigure
{
    private Point[] points;                       //多边形的各点坐标，边数为数组长度

    //由points数组中的多点构造一个多边形；points数组长度≥3，且若不能构成指定点数的多边形时，抛出无效参数异常
    public Polygon(final Point[] points) throws IllegalArgumentException
    {
        super("多边形", points[0]);                //points[0]赋值给继承的point1，重复
        this.points = points;                    //引用赋值
//        if (points.length<3)
//           throw new IllegalArgumentException(this.getClass().getName()+"多边形必须至少有三点。");
    }
    
    public String toString()                     //对象描述字符串，包括多点位置属性。覆盖
    {
        String str=//this.getClass().getName()+this.shape+
                   "("+points.length+"个点"+this.points[0].toString();
        for(int i=1; i<points.length; i++)
            str += ","+this.points[i].toString();
        return str+")";
    }
    public double perimeter()                    //返回多边形周长
    {
        double perim=0;
        for(int i=0; i<points.length; i++)       //周长=每条直线长度之和
            perim += new Line(points[i], points[(i+1)%points.length]).length();
        return perim;
    }
    public double area()                         //返回凸多边形面积，将凸多边形分割成n-2个三角形
    {
        double sum=0;
        for(int i=1; i<points.length-1; i++)     //面积=points.length-2个三角形面积之和
            sum += new Triangle(points[0], points[i], points[i+1]).area();//以points[0]为基点
        return sum;
    }

    //【试题3，4-0样卷】
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj instanceof Polygon) 
        {
            Polygon poly=(Polygon)obj;
            for(int i=0; i<poly.points.length; i++)
                if(this.points[i].equals(poly.points[i]))
                    return false;
            return true;
        }
        return false;
    }
    
    //【试题6】
    public void draw(java.awt.Graphics g)        //绘图
    {
        for(int i=0; i<this.points.length; i++)
        {
        	int j = (i+1) % this.points.length;
            g.drawLine(this.points[i].x, this.points[i].y, this.points[j].x, this.points[j].y);
        }
    }
    
    public static void main(String[] args)
    {
        Point[] points4={new Point(100,100), new Point(200,100), new Point(200,200),
                      new Point(100,200)};
        Polygon quad = new Polygon(points4);     //四边形
        System.out.println("四边形的周长是 "+quad.perimeter()+"，面积是 "+ quad.area());
        Point[] points5={new Point(100,100), new Point(200,100), new Point(200,150),
                      new Point(200,200), new Point(100,200)};
        Polygon pentagon = new Polygon(points5); //五点，其中有三点共线，实际同上四边形
        System.out.println("五边形的周长是 "+pentagon.perimeter()+"，面积是 "+pentagon.area());
    }
}
/*
程序运行结果如下：
四边形的周长是 400.0，面积是 10000.0
五边形的周长是 400.0，面积是 10000.000000000004
*/
//@author：Yeheya，2016-8-15
/*
未完成
    public boolean contains(int x, int y)        //判断指定点是否在当前多边形的范围内
    public boolean contains(Point p)             //判断指定点是否在当前多边形的范围内
    public Polygon(final Point[] points) // throws Exception //能否构成一个多边形
    public Polygon(final Point[] points) // throws Exception 
    {
        this.points = points;    
//        if (new Line(p1,p2).contains(p3) || new Line(p1,p3).contains(p2) || new Line(p2,p3).contains(p1))
//            throw new Exception("三点共线，不能构成一个三角形。");
    }
    public boolean contains(int x, int y)        //判断指定点是否在当前多边形的范围内
    {
        return false;
    }
    public boolean contains(Point p)             //判断指定点是否在当前多边形的范围内
    {
        return contains(p.x, p.y);
    }
 * */
