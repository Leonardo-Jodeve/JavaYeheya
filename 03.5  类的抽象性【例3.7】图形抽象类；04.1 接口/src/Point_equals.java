//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//����1.2�� ������ʹ�ð���
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public class Point_equals
{
    public static void main(String args[])
    {
        Point p1 = new Point(100,100);
        Point p2 = p1;
        System.out.println(p1+".equals("+p2+")��"+p1.equals(p2)); 
        p2 = new Point(p1);
        System.out.println(p1+".equals("+p2+")��"+p1.equals(p2)); 
        p2 = new Point(100,200);
        System.out.println(p1+".equals("+p2+")��"+p1.equals(p2)); 
        Object obj = new Object();
        System.out.println(p1+".equals("+obj+")��"+p1.equals(obj)); 
    }    
}

/*
java.awt.Point; 
public class Point extends Point2D implements java.io.Serializable 

Point(100,100).equals(Point(100,100))��true
Point(100,100).equals(Point(100,100))��true
Point(100,100).equals(Point(100,200))��false
Point(100,100).equals(java.lang.Object@18b3364)��false
*/
//@author��Yeheya��2015-7-8