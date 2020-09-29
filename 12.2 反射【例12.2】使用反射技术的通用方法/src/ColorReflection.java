//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月2日
//§12.2 反射
//试验了两个方向转换，成功。

import java.awt.*;
import java.lang.reflect.Field;

public class ColorReflection 
{
	//§12.2 反射【例12.2】  增加例6.5功能，获得颜色常量的字符串。
    //colors指定颜色常量数组，返回各颜色对应的字符串数组
    public static String[] toString(Color[] colors)
    {
        String[] str = new String[colors.length];
        Field[] fields = Color.class.getFields();          //获得Color类中所有成员变量
        for(int i=0; i<colors.length; i++)
            for(int j=0; j<fields.length; j++)             //在fields数组中顺序查找colors[i]颜色常量
                try
                {
                    if(colors[i].equals(fields[j].get(colors[i])))//比较两个颜色常量值
                    {
                	    str[i]=fields[j].getName();        //获得fields[j]成员变量名字符串
                        break;
                    }
                }
                catch(IllegalAccessException ex) {}        //无效存取异常
//                catch (IllegalArgumentException ex){} //无效参数异常，get(obj)方法声明抛出该异常
        return str;
    }

    //str指定一个表示颜色常量的字符串数组，返回对应的颜色对象数组
    public static Color[] getColors(String[] str)
    {
        Field[] fields = Color.class.getFields();          //获得Color类中所有成员变量
    	Color[] colors = new Color[str.length];
        for(int i=0; i<str.length; i++)
        {
            for(int j=0; j<fields.length; j++)             //在fields数组中顺序查找str[i]颜色字符串
            {
                if(str[i].equals(fields[j].getName()))
                {
                    try
                    {
                        colors[i]=(Color)fields[j].get(colors[i]);
                    }
                    catch (IllegalAccessException ex) {}   //无效存取异常
                }
            }
        }
    	return colors;
    }
    
    public static void main(String args[]) 
    {
        Color[] colors={Color.red, Color.green, Color.blue, Color.magenta, Color.cyan};   //颜色数组
        Reflection.print(toString(colors));
        
        String[] colorname={"red", "green", "blue", "magenta", "cyan"};
        Reflection.print(getColors(colorname));
        
//      System.out.print(Color.decode("000000"));
//        System.out.print(Color.getColor("red"));//结果是null，参数是属性，什么属性？
    }
}    
/*    
red
green
blue
magenta
cyan
java.awt.Color[r=255,g=0,b=0]
java.awt.Color[r=0,g=255,b=0]
java.awt.Color[r=0,g=0,b=255]
java.awt.Color[r=255,g=0,b=255]
java.awt.Color[r=0,g=255,b=255]
*/
//@author：Yeheya，2018年2月3日