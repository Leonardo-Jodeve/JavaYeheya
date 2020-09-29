//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月7日
//§1.2   JDK
//§1.2.3   包
//【例1.2】 创建及使用包。

import mypackage.Point;                          //导入mypackage包中的Point类
public class Line
{
    public Point point1, point2;                 //直线的两点
   
    public Line(Point point1, Point point2)      //构造方法，两点确定 一条直线
    { 
        this.point1 = point1; 
        this.point2 = point2; 
    }
    public double length()                       //返回直线长度
    {
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //数学类Math.sqrt(x)返回x（≥0）的平方根
    }
    public String toString()                     //直线的描述字符串
    {
        return "一条直线，起点"+point1.toString()+"，终点"+point2.toString()+
               "，长度"+String.format("%1.2f", length());
    }
}

class Line1_ex                                   //调用直线类
{
    public static void main(String[] args)
    {
        Point point1=new Point(), point2=new Point(40,30);
        System.out.println(new Line(point1,point2).toString());
    }
}

/*
程序运行结果如下：
一条直线，起点(0,0)，终点(40,30)，长度50.00
*/
//@author：Yeheya，2016-6-9，端午节；2016-10-14