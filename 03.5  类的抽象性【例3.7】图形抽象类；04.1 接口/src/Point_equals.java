//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月14日
//【例1.2】 创建及使用包。
//§3.5.2 抽象类
//【例3.7】 图形抽象类及其子类。

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类

public class Point_equals
{
    public static void main(String args[])
    {
        Point p1 = new Point(100,100);
        Point p2 = p1;
        System.out.println(p1+".equals("+p2+")？"+p1.equals(p2)); 
        p2 = new Point(p1);
        System.out.println(p1+".equals("+p2+")？"+p1.equals(p2)); 
        p2 = new Point(100,200);
        System.out.println(p1+".equals("+p2+")？"+p1.equals(p2)); 
        Object obj = new Object();
        System.out.println(p1+".equals("+obj+")？"+p1.equals(obj)); 
    }    
}

/*
java.awt.Point; 
public class Point extends Point2D implements java.io.Serializable 

Point(100,100).equals(Point(100,100))？true
Point(100,100).equals(Point(100,100))？true
Point(100,100).equals(Point(100,200))？false
Point(100,100).equals(java.lang.Object@18b3364)？false
*/
//@author：Yeheya，2015-7-8