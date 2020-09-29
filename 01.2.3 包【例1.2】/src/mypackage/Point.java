//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月7日
//§1.2   JDK
//§1.2.3   包
//【例1.2】 创建及使用包。
//调用：【例3.7】 图形抽象类及其子类。【例4.2】 像素类

package mypackage;                               //声明当前文件中的类在mypackage包中

public class Point                               //坐标点类
{
    public int x, y;                             //成员变量，点的X和Y方向坐标
    
    //构造方法，若x或y<0，抛出无效参数异常
    public Point(int x, int y)  throws IllegalArgumentException
    {
        if(x>=0 && y>=0)
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("长度或宽度不能为负数。");
    }
    public Point()                               //构造方法，重载
    {
        this(0, 0);
    }
    public Point(Point p)                        //拷贝构造方法
    {
        this(p.x, p.y);
    }
    public String toString()                     //成员方法，Point对象描述字符串，形式为(x,y)
    {
        return //this.getClass().getName()+        //第4章4.3.1节，【思考题4-4】
               "("+this.x+","+this.y+")";
//        return this.getClass().getName()+"[x="+x+",y="+y+"]"; //java.awt.Point
    }
    //以上【例1.2】 创建及使用包。
    
    //【试题3】 
    public void moveTo(int x, int y)             //移动到(x,y)，改变当前对象位置
    {
        this.x = x;
        this.y = y;
    }
    public void moveTo(double x, double y)       //移动到(x,y)，改变当前对象位置，成员方法重载
    {
        this.moveTo((int)Math.floor(x+0.5), (int)Math.floor(y+0.5));
    }
    public void moveTo(Point p)                  //移动到p，改变当前对象位置，成员方法重载
    {
        this.moveTo(p.x, p.y);
    }

    public void move(int dx, int dy)             //X和Y方向分别移动dx、dy距离
    {
        this.moveTo(this.x+dx, this.y+dy);
    }
    
    public boolean equals(Object obj)            //比较当前对象与obj是否相等
    {
        if(this==obj)
            return true;
        if(obj instanceof Point) 
        {
            Point p=(Point)obj;
            return this.x==p.x && this.y==p.y;
        }
        return false;

//        return this==obj || obj instanceof Point && this.x==((Point)obj).x && this.y==((Point)obj).y;
    }
    

}
//@author：Yeheya，2016-6-9，端午节