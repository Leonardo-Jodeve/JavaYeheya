//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。
//【思考题3-7】椭圆类，使用外切矩形确定椭圆的位置和大小。

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//椭圆类，继承矩形类，使用外切矩形确定椭圆的位置和大小；//继承的point表示椭圆外切矩形的左上角点坐标，不是原心点坐标
//【试题4】继承父类ClosedFigure的比较对象大小的方法
public class Ellipse extends Rectangle
{
    public Ellipse(Point point, int length, int width)//构造方法，参数分别指定椭圆外切矩形的左上角点坐标、长度和宽度
    {
        super(point, length, width);
        this.shape = "椭圆";
    }    
    public Ellipse(Point point1, Point point2)  //构造方法重载，参数指定矩形左上角点和右下角点坐标
    {
        super(point1, point2);
        this.shape = "椭圆";
    }
    public Ellipse(int x1,int y1, int x2,int y2)//构造方法重载，两点为(x1,y1)、(x2,y2)
    {
        super(x1,y1, x2,y2);
        this.shape = "椭圆";
    }        
    public Ellipse()
    {
        super();
        this.shape = "椭圆";
    }
    
    public String toString()                     //对象描述字符串，输出外切矩形属性。覆盖
    {
        return "，"+"外切矩形"+super.toString();    //第3章，输出"椭圆外切矩形(……)"，希望的含义
//        return this.getClass().getName()+this.shape+"(外切"+super.toString()+")";//第4章，输出"Ellipse(外切Ellipse(……)"，不希望的含义
    }
    
    public double perimeter()                    //计算椭圆周长，公式为π(a轴半径+b轴半径)，覆盖
    {
        return Math.PI*(this.length/2+this.width/2);
    }
    public double area()                         //计算椭圆面积，公式为πab，覆盖
    {
        return Math.PI*(this.length/2)*(this.width/2);
    }
    
    //以下第5版教材未写
    public Ellipse(Ellipse e)                    //拷贝构造方法
    {
        super(e);                                //调用父类的拷贝构造方法
        this.shape = "椭圆";
    }

    //第6章
    public void draw(java.awt.Graphics g)        //绘图，覆盖
    {
        g.drawOval(this.point1.x, this.point1.y, this.length, this.width);//参数分别指定椭圆外切矩形的左上角点坐标、长度和宽度
    }
}
//@author：Yeheya，2016-8-15