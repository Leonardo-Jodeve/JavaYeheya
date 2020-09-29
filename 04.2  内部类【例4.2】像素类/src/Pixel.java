//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��18��
//��4.2  �ڲ��� ����4.2�� ������
//MyEclipse���ñ���·��������Ŀ����1.2��Point����

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��
public class Pixel extends Point                 //�����࣬������ͣ��ⲿ�࣬ʹ����3.7��Point������
{
    //private 
    Color color;                                 //���ص���ɫ
 
    public static interface ColorConstant        //��ɫ�����ӿڣ���̬�ڲ��ӿڣ�����Ƕ�� 
    {
        public static final int BLACK=0xff000000;//��ɫ����ֵ����ɫ��Ĭ������ 
        public static final int RED  =0xffff0000;//��ɫ
        public static final int GREEN=0xff00ff00;//��ɫ
        public static final int BLUE =0xff0000ff;//��ɫ
        public static final int WHITE=0xffffffff;//��ɫ
    }
    
    //��ɫ�࣬ʵ����ɫ�����ӿڣ���̬�ڲ��࣬����Ƕ�ף���ΪColor��������Pixel�����ڣ���������������
    //�����������ڣ��˽���ɫֵ�����Ժʹ洢��ʽ��ʵ��ʹ��java.awt.Color�࣬ԭ����ͬ
    //��ʵ��3-2�� ��ɫ�ࡣ����ͬjava.awt.Color
    public static class Color extends Object implements ColorConstant
//    public class Color extends Object implements ColorConstant  //ʵ���ڲ��࣬����Ƕ��
    {
        public static final int black=BLACK;
        public static final int red  =RED;
        public static final int green=GREEN;
        public static final int blue =BLUE;
        public static final int white=WHITE;
        
        private int value;                       //��ɫֵ
        
        public Color(int rgb)                    //��������ʾ����ɫֵ����
        {
            this.value = 0xff000000 | rgb;
        }    
        public Color(int red, int green, int blue)//�Ժ졢�̡�����ԭɫֵ����
        {
            this.value = 0xff000000 | ((red & 0xFF)<<16) | ((green & 0xFF)<<8) | blue & 0xFF;
        }
        public Color()                           //���췽����Ĭ�Ϻ�ɫ��java.awt.Colorû������
        {
             this(BLACK);                        //��ColorConstant.BLACK��ʹ�ýӿڳ���
        }

        public int getRGB()                      //������ɫ�����RGBֵ
        {
            return this.value;
        }
        public int getRed()                      //������ɫ����ĺ�ɫֵ
        {
            return (this.value>>16) & 0xFF;
        }
        public int getGreen()                    //������ɫ�������ɫֵ
        {
            return (this.value>> 8) & 0xFF;
        }
        public int getBlue()                     //������ɫ�������ɫֵ
        {
            return  this.value & 0xFF;
        }
        public String toString()                 //���������ַ�������ʽΪ��[��,��,��]��
        {
            return //this.getClass().getName()+
                   "[r="+getRed()+",g="+getGreen()+",b="+getBlue()+"]";
        }
    }                                            //Color�ڲ������
    
    public Pixel(Point point, int colorvalue)    //�������ض�����������ʾ��ɫ 
    {
        super(point);                            //����Point(Point)�������췽��
        this.color = new Color(colorvalue);
    }    
    public Pixel()
    {
        this(new Point(), ColorConstant.BLACK);  //ʹ���ڲ��ӿڳ���������ʡ��ColorConstant�ӿ���
//        this(new Point(), new Color());        //Ҳ�ɣ�Ĭ�Ϻ�ɫ
    }
    public String toString()
    {
        //��ʹcolor.toString()��˽�еģ��˴�Ҳ�ܵ����ڲ����˽�г�Ա����
        return "����"+super.toString()+"����ɫ"+this.color.toString();
    }

    //�������ض�����Pixel.Color�����ʾ��ɫ����Pixel.Color����Ϊpublic staticʱ����
/*    public Pixel(Point p, Color color)
    {
        super(p);
        this.color = color;
    }*/

    public static enum Colors implements ColorConstant  //ö�����ͣ���4��5��̲�ûд��û���õ�����
    {
        BLACK, RED, GREEN, BLUE, WHITE;
    }
}

class Pixel_ex
{
    public static void main(String[] args)
    {
        //��4.2
        System.out.println(new Pixel().toString());        //Ĭ��Point(0,0)����ɫ
        Point p = new Point(100,100);
        Pixel pixel = new Pixel(p, Pixel.ColorConstant.RED);//�������ض���ʹ����ɫ�����ӿ��е���ɫ���� 
        System.out.println(pixel.toString());
        
        //����������Pixel.Color����Ϊpublic staticʱ���ã�����Ƕ��
        Pixel.Color color = new Pixel.Color(255,255,255);  //�Ժ졢�̡�����Ԫɫֵ������ɫ����
        pixel = new Pixel(p,color.getRGB());
        System.out.println(pixel.toString());        
        pixel.color = new Pixel.Color(0xff11007f);         //����colorȨ�޿ɼ�ʱ������ ���ã���������ʾ��ɫ
    	System.out.println(pixel.toString());
    	
        //����������Pixel.Color����Ϊpublicʱ���ã�����Ƕ�� static
        System.out.println(pixel.color.toString());
    }
}
/*
�������н�����£�
����Pixel(0,0)����ɫ[r=0,g=0,b=0]
����Pixel(100,100)����ɫ[r=255,g=0,b=0]


��1��û��static 
    public class Color extends Object implements ColorConstant
No enclosing instance of type Pixel is available due to some intermediate constructor invocation

��2������static������н��û�в��
/*        Pixel pixel1 = new Pixel();              //Ĭ��Point(0,0)����ɫ
        Pixel pixel2 = new Pixel(new Point(100,100), Pixel.ColorConstant.RED);//�������ض���ʹ����ɫ�����ӿ��е���ɫ���� 
        System.out.println("pixel1��"+pixel1.toString());
        System.out.println("pixel2��"+pixel2.toString());
//�������н��û�в��
  (1) Color��ʵ���ڲ��� 
pixel1��Pixel���أ�����Pixel(0,0)����ɫRGB(0,0,0),0xff000000
pixel2��Pixel���أ�����Pixel(100,100)����ɫRGB(255,0,0),0xffff0000

  (2) Color�Ǿ�̬�ڲ���
pixel1��Pixel���أ�����Pixel(0,0)����ɫRGB(0,0,0),0xff000000
pixel2��Pixel���أ�����Pixel(100,100)����ɫRGB(255,0,0),0xffff0000

//���µ�5��δ��
        private String toRGBString()   //�����Ժ졢�̡�����Ԫɫ��ʾ����ɫֵ�ַ�������ʽΪ��RGB(red,green,blue)��
        {
            return "RGB("+((this.value>>16) & 0xFF)+","+((this.value>> 8) & 0xFF)+","+(this.value & 0xFF)+"),"
                   +MyInteger.toString(this.value,16);       //��ʮ��������ʽ���������ַ�������������2.10
//                   +Integer.toHexString(this.value);       //��ʮ��������ʽ�������
        }
        public String toString()
        {
            return Pixel.this.toString();  //Pixel.this����ColorΪʵ����Աʱ����
        }

*/
//@author��Yeheya��2016-8-18
