//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//【试题3】比较对象相等
//【试题4】比较对象大小

//import java.awt.Point;
import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//直线类，继承图形抽象类//【试题4】实现可比较接口，比较对象大小
public class Line extends Figure implements Comparable<Line>
{
    public Point point2;                         //直线的终点；继承父类的point1表示直线的起点
      
    //构造方法，两点确定一条直线。//第5章，若参数为null，调用方法时，Java将抛出空对象异常
    public Line(Point point1, Point point2)
    { 
        super(point1);                           //调用父类构造方法，初始化父类的point1
        this.point2 = point2; 
    }   
    
    public Line(int x1,int y1, int x2,int y2)    //构造方法重载，两点为(x1,y1)、(x2,y2)
    {
        this(new Point(x1,y1), new Point(x2,y2));
    }    
//    public Line()                              //构造方法。//何处用到？第5版书没写
//    {
//        this(new Point(0,0), new Point(0,0));
//    }
    //返回两点间的直线长度，若两点相同返回0。算法见例1.2。第5版书没写
    public static double length(Point point1, Point point2)
    {
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //Math.sqrt(x)返回x的平方根
    }  
    public double length()                       //返回this直线长度，算法见例1.2
    {
//        return Line.length(this.point1, this.point2);
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //Math.sqrt(x)返回x的平方根
    }
    public String toString()                     //直线对象描述字符串，输出两点位置属性和长度。覆盖
    {
        return this.getClass().getName()+        //第4章4.3.1节，【思考题4-4】
               "("+super.toString()+", "+this.point2.toString()+")"+
               "，长度"+String.format("%1.2f",this.length());
    }

    //【思考题3-8】【试题3】
    public boolean equals(Object obj)            //比较this与obj对象是否相等
    {
        if(this==obj)
            return true;
        if(obj instanceof Line) 
        {
            Line line=(Line)obj;
            return this.point1.equals(line.point1) && this.point2.equals(line.point2);
        }
        return false;
    }

    //【试题4】
    public int compareTo(Line line)              //按长度比较对象大小
    {
        return (int)(this.length() - line.length());
    }

    //【试题6】
    public void draw(java.awt.Graphics g)        //绘图
    {
        g.drawLine(this.point1.x, this.point1.y, this.point2.x, this.point2.y);
    }
    
    public static void main(String args[])
    {
        Line line1 = new Line(100,100,500,400);
        Line line2 = new Line(new Point(100,100), new Point(200,200));
        System.out.println("line1="+line1.toString()+"\nline2="+line2.toString()+
		           "\nline1比line2长多少？"+line1.compareTo(line2));
    }
}
/*
程序运行结果如下：
    line1=Line(Point(100,100), Point(500,400))，长度500.00
    line2=Line(Point(100,100), Point(200,200))，长度141.42
    line1比line2长多少？358

    line1=Line(Point(100,100), Point(400,400))，长度424.26
    line2=Line(Point(100,100), Point(200,200))，长度141.42
    line1比line2长多少？282
*/
/*
    //【思考题3-8】    //以下未完成
    public boolean contains(Point p)             //判断指定点是否在当前直线上，算法？？
    {
        return false;
        /*p.equals(this.start) || p.equals(this.end) ||
             (p.x>start.x && p.x<end.x || p.x>end.x && p.x<start.x) &&
             (p.y>start.y && p.y<end.y || p.y>end.y && p.y<start.y) &&
             Math.abs((end.y-start.y)/(end.x-start.x)) == Math.abs((p.y-start.y)/(p.x-start.x));
    }
    public boolean contains(int x, int y)        //判断指定点是否在当前直线上
    {
        return contains(new Point(x,y));
    }

    public Point intersects(Line line)           //判断两条直线是否相交
    {
        return null;
    }
  
    public double distance(Point p)              //返回从点到此线段的距离
    {
        return 0.0;    
    }
*/
//@author：Yeheya，2016-8-15