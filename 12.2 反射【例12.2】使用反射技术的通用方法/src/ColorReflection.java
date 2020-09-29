//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��2��
//��12.2 ����
//��������������ת�����ɹ���

import java.awt.*;
import java.lang.reflect.Field;

public class ColorReflection 
{
	//��12.2 ���䡾��12.2��  ������6.5���ܣ������ɫ�������ַ�����
    //colorsָ����ɫ�������飬���ظ���ɫ��Ӧ���ַ�������
    public static String[] toString(Color[] colors)
    {
        String[] str = new String[colors.length];
        Field[] fields = Color.class.getFields();          //���Color�������г�Ա����
        for(int i=0; i<colors.length; i++)
            for(int j=0; j<fields.length; j++)             //��fields������˳�����colors[i]��ɫ����
                try
                {
                    if(colors[i].equals(fields[j].get(colors[i])))//�Ƚ�������ɫ����ֵ
                    {
                	    str[i]=fields[j].getName();        //���fields[j]��Ա�������ַ���
                        break;
                    }
                }
                catch(IllegalAccessException ex) {}        //��Ч��ȡ�쳣
//                catch (IllegalArgumentException ex){} //��Ч�����쳣��get(obj)���������׳����쳣
        return str;
    }

    //strָ��һ����ʾ��ɫ�������ַ������飬���ض�Ӧ����ɫ��������
    public static Color[] getColors(String[] str)
    {
        Field[] fields = Color.class.getFields();          //���Color�������г�Ա����
    	Color[] colors = new Color[str.length];
        for(int i=0; i<str.length; i++)
        {
            for(int j=0; j<fields.length; j++)             //��fields������˳�����str[i]��ɫ�ַ���
            {
                if(str[i].equals(fields[j].getName()))
                {
                    try
                    {
                        colors[i]=(Color)fields[j].get(colors[i]);
                    }
                    catch (IllegalAccessException ex) {}   //��Ч��ȡ�쳣
                }
            }
        }
    	return colors;
    }
    
    public static void main(String args[]) 
    {
        Color[] colors={Color.red, Color.green, Color.blue, Color.magenta, Color.cyan};   //��ɫ����
        Reflection.print(toString(colors));
        
        String[] colorname={"red", "green", "blue", "magenta", "cyan"};
        Reflection.print(getColors(colorname));
        
//      System.out.print(Color.decode("000000"));
//        System.out.print(Color.getColor("red"));//�����null�����������ԣ�ʲô���ԣ�
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
//@author��Yeheya��2018��2��3��