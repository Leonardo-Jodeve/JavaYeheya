//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//【思考题3-7】矩形

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//矩形类，继承闭合图形抽象类；继承祖先类Figure的point表示矩形左上角点坐标；
//【试题4】继承父类ClosedFigure的比较对象大小的方法
public class Rectangle extends ClosedFigure
{
    protected int length, width;                 //矩形的长度和宽度

    //构造方法，参数分别指定矩形左上角点坐标、长度和宽度。若length或width<0，抛出无效参数异常
    public Rectangle(Point point1, int length, int width) throws IllegalArgumentException
    {
        super("矩形", point1);
        if(length>=0 && width>=0)
        {
            this.length = length;
            this.width = width;
        }
        else throw new IllegalArgumentException("长度或宽度不能为负数。");
    }
    
    public Rectangle(Point point1, Point point2) //构造方法重载，参数指定矩形左上角点和右下角点坐标
    {
        this(point1, Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));//Math.abs(x)返回x的绝对值
    }
    public Rectangle(int x1,int y1, int x2,int y2)//构造方法重载，两点为(x1,y1)、(x2,y2)
    {
        this(new Point(x1,y1), new Point(x2,y2));
    }        
    public Rectangle()
    {
        this(new Point(),0,0);
    }
    
    public String toString()                     //对象描述字符串，包括点位置、长度、宽度属性。覆盖，扩展功能
    {
        return "(左上角点"+super.toString()+"，长度"+this.length+"，宽度"+this.width+")";
    }
    
    public double perimeter()                    //计算矩形周长，实现父类的抽象方法
    {
        return (this.width+this.length)*2;
    }
    public double area()                         //计算矩形面积，实现父类的抽象方法
    {
        return this.width*this.length;
    }
    
    //以下第5版教材未写
    public Rectangle(Rectangle rec)              //拷贝构造方法
    {
        this(rec.point1, rec.length, rec.width);
    }
   
    public void set(int length, int width)
    {
        this.length = length;
        this.width = width;
    }

    public double getLength()
    {
        return this.length;
    }

    public double getWidth()
    {
        return this.width;
    }
    
    //【思考题3-8】
    public boolean contains(Point p)             //判断p(x,y)点是否在当前矩形区域内
    {
        return p.x>=this.point1.x && p.x<=this.point1.x+this.length &&
               p.y>=this.point1.y && p.y<=this.point1.y+this.width;
    }
    
    //第6章
    public void draw(java.awt.Graphics g)        //绘图
    {
        g.drawRect(this.point1.x, this.point1.y, this.length, this.width);
    }
}
//@author：Yeheya，2016-8-15