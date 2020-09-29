//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//��5�� �������

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//͹������࣬�̳бպ�ͼ�γ����ࡣ//˵����û���õ���Figure��̳еĳ�Ա����point1
public class Polygon extends ClosedFigure
{
    private Point[] points;                       //����εĸ������꣬����Ϊ���鳤��

    //��points�����еĶ�㹹��һ������Σ�points���鳤�ȡ�3���������ܹ���ָ�������Ķ����ʱ���׳���Ч�����쳣
    public Polygon(final Point[] points) throws IllegalArgumentException
    {
        super("�����", points[0]);                //points[0]��ֵ���̳е�point1���ظ�
        this.points = points;                    //���ø�ֵ
//        if (points.length<3)
//           throw new IllegalArgumentException(this.getClass().getName()+"����α������������㡣");
    }
    
    public String toString()                     //���������ַ������������λ�����ԡ�����
    {
        String str=//this.getClass().getName()+this.shape+
                   "("+points.length+"����"+this.points[0].toString();
        for(int i=1; i<points.length; i++)
            str += ","+this.points[i].toString();
        return str+")";
    }
    public double perimeter()                    //���ض�����ܳ�
    {
        double perim=0;
        for(int i=0; i<points.length; i++)       //�ܳ�=ÿ��ֱ�߳���֮��
            perim += new Line(points[i], points[(i+1)%points.length]).length();
        return perim;
    }
    public double area()                         //����͹������������͹����ηָ��n-2��������
    {
        double sum=0;
        for(int i=1; i<points.length-1; i++)     //���=points.length-2�����������֮��
            sum += new Triangle(points[0], points[i], points[i+1]).area();//��points[0]Ϊ����
        return sum;
    }

    //������3��4-0����
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj instanceof Polygon) 
        {
            Polygon poly=(Polygon)obj;
            for(int i=0; i<poly.points.length; i++)
                if(this.points[i].equals(poly.points[i]))
                    return false;
            return true;
        }
        return false;
    }
    
    //������6��
    public void draw(java.awt.Graphics g)        //��ͼ
    {
        for(int i=0; i<this.points.length; i++)
        {
        	int j = (i+1) % this.points.length;
            g.drawLine(this.points[i].x, this.points[i].y, this.points[j].x, this.points[j].y);
        }
    }
    
    public static void main(String[] args)
    {
        Point[] points4={new Point(100,100), new Point(200,100), new Point(200,200),
                      new Point(100,200)};
        Polygon quad = new Polygon(points4);     //�ı���
        System.out.println("�ı��ε��ܳ��� "+quad.perimeter()+"������� "+ quad.area());
        Point[] points5={new Point(100,100), new Point(200,100), new Point(200,150),
                      new Point(200,200), new Point(100,200)};
        Polygon pentagon = new Polygon(points5); //��㣬���������㹲�ߣ�ʵ��ͬ���ı���
        System.out.println("����ε��ܳ��� "+pentagon.perimeter()+"������� "+pentagon.area());
    }
}
/*
�������н�����£�
�ı��ε��ܳ��� 400.0������� 10000.0
����ε��ܳ��� 400.0������� 10000.000000000004
*/
//@author��Yeheya��2016-8-15
/*
δ���
    public boolean contains(int x, int y)        //�ж�ָ�����Ƿ��ڵ�ǰ����εķ�Χ��
    public boolean contains(Point p)             //�ж�ָ�����Ƿ��ڵ�ǰ����εķ�Χ��
    public Polygon(final Point[] points) // throws Exception //�ܷ񹹳�һ�������
    public Polygon(final Point[] points) // throws Exception 
    {
        this.points = points;    
//        if (new Line(p1,p2).contains(p3) || new Line(p1,p3).contains(p2) || new Line(p2,p3).contains(p1))
//            throw new Exception("���㹲�ߣ����ܹ���һ�������Ρ�");
    }
    public boolean contains(int x, int y)        //�ж�ָ�����Ƿ��ڵ�ǰ����εķ�Χ��
    {
        return false;
    }
    public boolean contains(Point p)             //�ж�ָ�����Ƿ��ڵ�ǰ����εķ�Χ��
    {
        return contains(p.x, p.y);
    }
 * */
