//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月7日
//§1.2   JDK
//§1.2.3   包
//【例1.2】 创建及使用包。

package mypackage;                               //声明当前文件中的类在mypackage包中

public class Point
{
    public int x,y;                              //成员变量，点的X和Y方向坐标
    
    public Point(int x,int y)                    //构造方法
    {
        this.x = x;
        this.y = y;
    }
    public Point()                               //构造方法，重载
    {
        this(0,0);
    }
    public String toString()                     //成员方法，坐标点字符串描述，形式为(x,y)
    {
        return "("+this.x+","+this.y+")";
    }
}
//@author：Yeheya，2016-6-9，端午节