//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日
//§4.1 接口与实现接口的类
//4. 接口是多继承的
//【思考题4-1】球类实现多个接口。
//【试题4】比较对象大小

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

//球类，实现计算面积和体积接口；实现可比较接口，按体积比较对象大小
//public class Globe extends Object implements Solid, Comparable<Globe>
public class Globe extends Object implements Area,Volume, Comparable<Globe>
{
    private Point point;                         //原心位置
    private double radius;                       //半径

    public Globe(Point point, double radius)     //构造方法
    {
        this.point = point;
        this.radius = radius;
    }
    public Globe()
    {
        this(new Point(0,0), 0);
    }

    public double area()                         //计算球的表面积，实现Area接口
    {
        return 4*Math.PI * this.radius * this.radius;
    }

    public double volume()                       //计算球的体积，实现Volume接口
    {
        return 4/3*Math.PI * this.radius * this.radius * this.radius;
    }

    public String toString()
    {
        return this.getClass().getName()+"球("+
            String.format("半径%1.1f)，表面积%1.1f, 体积%1.1f", this.radius, this.area(), this.volume());
    }

    //比较this与obj对象是否相等，覆盖Object类的方法。
    //当参数obj引用Globe实例时，算法逐域比较各成员变量对象值是否相等
    public boolean equals(Object obj)
    {
        if (this==obj)                           //this指代调用当前方法的对象
            return true;
        if (obj instanceof Globe)                //当obj引用实例属于Globe及其子类，obj==null时返回false 
        {
            Globe globe = (Globe)obj;            //类型强制转换，globe也引用obj引用的实例
            return this.point.equals(globe.point) && this.radius==globe.radius;
        }
        return false;
    }
    
    public int compareTo(Globe globe)            //比较对象大小
    {
        return (int)(this.volume() - globe.volume()); //按体积比较对象大小
    }

    public static void main(String args[])
    {
        Point point=new Point(100,100); 
        Globe globe1= new Globe(point, 10), globe2= new Globe(point, 20);
        System.out.println("globe1="+globe1.toString()+"\nglobe2="+globe2.toString()+
                           "\nglobe1的体积比globe2的大多少？"+globe1.compareTo(globe2));
    }
}
/*
程序运行结果如下：
globe1=Globe球(半径10.0)，表面积1256.6, 体积3141.6
globe2=Globe球(半径20.0)，表面积5026.5, 体积25132.7
globe1的体积比globe2的大多少？-21991
*/

//@author：Yeheya，2016-8-17，2018年5月6日