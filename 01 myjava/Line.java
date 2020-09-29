//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��7��
//��1.2   JDK
//��1.2.3   ��
//����1.2�� ������ʹ�ð���

import mypackage.Point;                          //����mypackage���е�Point��
public class Line
{
    public Point point1, point2;                 //ֱ�ߵ�����
   
    public Line(Point point1, Point point2)      //���췽��������ȷ�� һ��ֱ��
    { 
        this.point1 = point1; 
        this.point2 = point2; 
    }
    public double length()                       //����ֱ�߳���
    {
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //��ѧ��Math.sqrt(x)����x����0����ƽ����
    }
    public String toString()                     //ֱ�ߵ������ַ���
    {
        return "һ��ֱ�ߣ����"+point1.toString()+"���յ�"+point2.toString()+
               "������"+String.format("%1.2f", length());
    }
}

class Line1_ex                                   //����ֱ����
{
    public static void main(String[] args)
    {
        Point point1=new Point(), point2=new Point(40,30);
        System.out.println(new Line(point1,point2).toString());
    }
}

/*
�������н�����£�
һ��ֱ�ߣ����(0,0)���յ�(40,30)������50.00
*/
//@author��Yeheya��2016-6-9������ڣ�2016-10-14