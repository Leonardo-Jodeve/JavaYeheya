//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��17��
//��4.1 �ӿ���ʵ�ֽӿڵ���
//1. �����ӿ�
//2. ����ʵ�ֽӿڵ���
//����4.1��  Area��Volume�ӿ���ʵ����Щ�ӿڵ������ࡣ
//MyEclipse���ñ���·��������Ŀ����1.2����3.7��

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//�����࣬ʵ�ֿɼ�������ӿںͿɼ�������ӿ�
public class Cylinder extends Object implements Area,Volume
{
    //�����������������cfigure�����ñպ�ͼ�γ����ࣨ����3.7�����������ָ������ͼ��
	public ClosedFigure cfigure;
    protected double height;                     //�߶�

    public Cylinder(ClosedFigure cfigure, double height)    //���췽��
    {                                  
        this.cfigure = cfigure;
        this.height = height;
    }
    public double area()                         //��������ı������ʵ��Area�ӿ��еĳ��󷽷�
    {
        return cfigure.perimeter()*this.height + 2*cfigure.area();//����perimeter()�������ͼ���ܳ�
    }
    public double volume()                       //��������������ʵ��Volume�ӿ��еĳ��󷽷�
    {
        return cfigure.area() * this.height;     //����area()�������ͼ�����
    }
    public String toString()
    {
        return this.getClass().getName()+        //��4��4.3.1�ڣ���˼����4-4��
                "������"+this.cfigure.toString()+"����"+this.height;
            //   +"�������"+String.format("%1.2f�����%1.2f",this.area(),this.volume());
    }

    public static void main(String[] args)
    {
        Point point = new Point(100,100);
        
        ClosedFigure cfig = new Ellipse(point,10,20);      //��Բ
        System.out.println("Elliptic Cylinder��Բ����"+new Cylinder(cfig,10).toString());
        cfig = new Rectangle(point,10,20);                 //����
        System.out.println("Cuboid�����壬"+new Cylinder(cfig,10).toString());
        cfig = new EuilateralTriangle(point,10);           //�ȱ�������
        System.out.println("Trianglular Prism��������"+new Cylinder(cfig,10).toString());
        Point[] points5={point, new Point(200,100), new Point(250,150), new Point(200,200),new Point(100,200)};
        cfig = new Polygon(points5);                       //�����
        System.out.println("��������"+new Cylinder(cfig,10).toString());
    }
}

/*
�������н�����£�
Elliptic Cylinder��Բ������������Բ��ԭ�ĵ�����Point(100,100)��a��뾶10.0��b��뾶20.0����10.0�������2199.11�����6283.19
Cuboid�����壬�����Ǿ��Σ����Ͻǵ�����Point(100,100)������10.0�����20.0����10.0�������1000.00�����2000.00
Trianglular Prism�������������ǵȱ������Σ�������Point(100,100)���߳�10.00����10.0�������386.60�����433.01
�������������Ƕ���Σ�5����Point(100,100)��Point(200,100)��Point(250,150)��Point(200,200)��Point(100,200)����10.0�������29414.21�����125000.00

*/
//@author��Yeheya��2016-8-17