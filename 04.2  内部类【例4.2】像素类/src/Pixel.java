//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月18日
//§4.2  内部类 【例4.2】 像素类
//MyEclipse设置编译路径包含项目：例1.2（Point）。

import mypackage.Point;                          //导入例1.2项目mypackage包中的Point类
public class Pixel extends Point                 //像素类，外层类型，外部类，使用例3.7中Point类声明
{
    //private 
    Color color;                                 //像素的颜色
 
    public static interface ColorConstant        //颜色常量接口，静态内部接口，类型嵌套 
    {
        public static final int BLACK=0xff000000;//颜色常量值，黑色，默认修饰 
        public static final int RED  =0xffff0000;//红色
        public static final int GREEN=0xff00ff00;//绿色
        public static final int BLUE =0xff0000ff;//蓝色
        public static final int WHITE=0xffffffff;//白色
    }
    
    //颜色类，实现颜色常量接口；静态内部类，类型嵌套，因为Color不依赖于Pixel而存在，可用于声明对象
    //本例意义在于，了解颜色值的特性和存储方式，实际使用java.awt.Color类，原理相同
    //【实验3-2】 颜色类。声明同java.awt.Color
    public static class Color extends Object implements ColorConstant
//    public class Color extends Object implements ColorConstant  //实例内部类，对象嵌套
    {
        public static final int black=BLACK;
        public static final int red  =RED;
        public static final int green=GREEN;
        public static final int blue =BLUE;
        public static final int white=WHITE;
        
        private int value;                       //颜色值
        
        public Color(int rgb)                    //以整数表示的颜色值构造
        {
            this.value = 0xff000000 | rgb;
        }    
        public Color(int red, int green, int blue)//以红、绿、蓝三原色值构造
        {
            this.value = 0xff000000 | ((red & 0xFF)<<16) | ((green & 0xFF)<<8) | blue & 0xFF;
        }
        public Color()                           //构造方法，默认黑色；java.awt.Color没有声明
        {
             this(BLACK);                        //即ColorConstant.BLACK，使用接口常量
        }

        public int getRGB()                      //返回颜色对象的RGB值
        {
            return this.value;
        }
        public int getRed()                      //返回颜色对象的红色值
        {
            return (this.value>>16) & 0xFF;
        }
        public int getGreen()                    //返回颜色对象的绿色值
        {
            return (this.value>> 8) & 0xFF;
        }
        public int getBlue()                     //返回颜色对象的蓝色值
        {
            return  this.value & 0xFF;
        }
        public String toString()                 //对象描述字符串，形式为“[红,绿,蓝]”
        {
            return //this.getClass().getName()+
                   "[r="+getRed()+",g="+getGreen()+",b="+getBlue()+"]";
        }
    }                                            //Color内部类结束
    
    public Pixel(Point point, int colorvalue)    //构造像素对象，以整数表示颜色 
    {
        super(point);                            //调用Point(Point)拷贝构造方法
        this.color = new Color(colorvalue);
    }    
    public Pixel()
    {
        this(new Point(), ColorConstant.BLACK);  //使用内部接口常量，不能省略ColorConstant接口名
//        this(new Point(), new Color());        //也可，默认黑色
    }
    public String toString()
    {
        //即使color.toString()是私有的，此处也能调用内部类的私有成员方法
        return "像素"+super.toString()+"，颜色"+this.color.toString();
    }

    //构造像素对象，以Pixel.Color对象表示颜色，当Pixel.Color声明为public static时可用
/*    public Pixel(Point p, Color color)
    {
        super(p);
        this.color = color;
    }*/

    public static enum Colors implements ColorConstant  //枚举类型，第4、5版教材没写，没有用到？？
    {
        BLACK, RED, GREEN, BLUE, WHITE;
    }
}

class Pixel_ex
{
    public static void main(String[] args)
    {
        //例4.2
        System.out.println(new Pixel().toString());        //默认Point(0,0)，黑色
        Point p = new Point(100,100);
        Pixel pixel = new Pixel(p, Pixel.ColorConstant.RED);//构造像素对象，使用颜色常量接口中的颜色整数 
        System.out.println(pixel.toString());
        
        //以下语句仅当Pixel.Color声明为public static时可用，类型嵌套
        Pixel.Color color = new Pixel.Color(255,255,255);  //以红、绿、蓝三元色值构造颜色对象
        pixel = new Pixel(p,color.getRGB());
        System.out.println(pixel.toString());        
        pixel.color = new Pixel.Color(0xff11007f);         //仅当color权限可见时，对象 引用，以整数表示颜色
    	System.out.println(pixel.toString());
    	
        //以下语句仅当Pixel.Color声明为public时可用，类型嵌套 static
        System.out.println(pixel.color.toString());
    }
}
/*
程序运行结果如下：
像素Pixel(0,0)，颜色[r=0,g=0,b=0]
像素Pixel(100,100)，颜色[r=255,g=0,b=0]


（1）没有static 
    public class Color extends Object implements ColorConstant
No enclosing instance of type Pixel is available due to some intermediate constructor invocation

（2）此例static与否，运行结果没有差别
/*        Pixel pixel1 = new Pixel();              //默认Point(0,0)，黑色
        Pixel pixel2 = new Pixel(new Point(100,100), Pixel.ColorConstant.RED);//构造像素对象，使用颜色常量接口中的颜色整数 
        System.out.println("pixel1："+pixel1.toString());
        System.out.println("pixel2："+pixel2.toString());
//以下运行结果没有差别
  (1) Color是实例内部类 
pixel1：Pixel像素，坐标Pixel(0,0)，颜色RGB(0,0,0),0xff000000
pixel2：Pixel像素，坐标Pixel(100,100)，颜色RGB(255,0,0),0xffff0000

  (2) Color是静态内部类
pixel1：Pixel像素，坐标Pixel(0,0)，颜色RGB(0,0,0),0xff000000
pixel2：Pixel像素，坐标Pixel(100,100)，颜色RGB(255,0,0),0xffff0000

//以下第5版未用
        private String toRGBString()   //返回以红、绿、蓝三元色表示的颜色值字符串，形式为“RGB(red,green,blue)”
        {
            return "RGB("+((this.value>>16) & 0xFF)+","+((this.value>> 8) & 0xFF)+","+(this.value & 0xFF)+"),"
                   +MyInteger.toString(this.value,16);       //以十六进制形式返回整数字符串，声明见例2.10
//                   +Integer.toHexString(this.value);       //以十六进制形式输出整数
        }
        public String toString()
        {
            return Pixel.this.toString();  //Pixel.this仅当Color为实例成员时可用
        }

*/
//@author：Yeheya，2016-8-18
