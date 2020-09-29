//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//������3���Ƚ϶������
//������4���Ƚ϶����С

//import java.awt.Point;
import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//ֱ���࣬�̳�ͼ�γ�����//������4��ʵ�ֿɱȽϽӿڣ��Ƚ϶����С
public class Line extends Figure implements Comparable<Line>
{
    public Point point2;                         //ֱ�ߵ��յ㣻�̳и����point1��ʾֱ�ߵ����
      
    //���췽��������ȷ��һ��ֱ�ߡ�//��5�£�������Ϊnull�����÷���ʱ��Java���׳��ն����쳣
    public Line(Point point1, Point point2)
    { 
        super(point1);                           //���ø��๹�췽������ʼ�������point1
        this.point2 = point2; 
    }   
    
    public Line(int x1,int y1, int x2,int y2)    //���췽�����أ�����Ϊ(x1,y1)��(x2,y2)
    {
        this(new Point(x1,y1), new Point(x2,y2));
    }    
//    public Line()                              //���췽����//�δ��õ�����5����ûд
//    {
//        this(new Point(0,0), new Point(0,0));
//    }
    //����������ֱ�߳��ȣ���������ͬ����0���㷨����1.2����5����ûд
    public static double length(Point point1, Point point2)
    {
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //Math.sqrt(x)����x��ƽ����
    }  
    public double length()                       //����thisֱ�߳��ȣ��㷨����1.2
    {
//        return Line.length(this.point1, this.point2);
        int a=point1.x-point2.x, b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);               //Math.sqrt(x)����x��ƽ����
    }
    public String toString()                     //ֱ�߶��������ַ������������λ�����Ժͳ��ȡ�����
    {
        return this.getClass().getName()+        //��4��4.3.1�ڣ���˼����4-4��
               "("+super.toString()+", "+this.point2.toString()+")"+
               "������"+String.format("%1.2f",this.length());
    }

    //��˼����3-8��������3��
    public boolean equals(Object obj)            //�Ƚ�this��obj�����Ƿ����
    {
        if(this==obj)
            return true;
        if(obj instanceof Line) 
        {
            Line line=(Line)obj;
            return this.point1.equals(line.point1) && this.point2.equals(line.point2);
        }
        return false;
    }

    //������4��
    public int compareTo(Line line)              //�����ȱȽ϶����С
    {
        return (int)(this.length() - line.length());
    }

    //������6��
    public void draw(java.awt.Graphics g)        //��ͼ
    {
        g.drawLine(this.point1.x, this.point1.y, this.point2.x, this.point2.y);
    }
    
    public static void main(String args[])
    {
        Line line1 = new Line(100,100,500,400);
        Line line2 = new Line(new Point(100,100), new Point(200,200));
        System.out.println("line1="+line1.toString()+"\nline2="+line2.toString()+
		           "\nline1��line2�����٣�"+line1.compareTo(line2));
    }
}
/*
�������н�����£�
    line1=Line(Point(100,100), Point(500,400))������500.00
    line2=Line(Point(100,100), Point(200,200))������141.42
    line1��line2�����٣�358

    line1=Line(Point(100,100), Point(400,400))������424.26
    line2=Line(Point(100,100), Point(200,200))������141.42
    line1��line2�����٣�282
*/
/*
    //��˼����3-8��    //����δ���
    public boolean contains(Point p)             //�ж�ָ�����Ƿ��ڵ�ǰֱ���ϣ��㷨����
    {
        return false;
        /*p.equals(this.start) || p.equals(this.end) ||
             (p.x>start.x && p.x<end.x || p.x>end.x && p.x<start.x) &&
             (p.y>start.y && p.y<end.y || p.y>end.y && p.y<start.y) &&
             Math.abs((end.y-start.y)/(end.x-start.x)) == Math.abs((p.y-start.y)/(p.x-start.x));
    }
    public boolean contains(int x, int y)        //�ж�ָ�����Ƿ��ڵ�ǰֱ����
    {
        return contains(new Point(x,y));
    }

    public Point intersects(Line line)           //�ж�����ֱ���Ƿ��ཻ
    {
        return null;
    }
  
    public double distance(Point p)              //���شӵ㵽���߶εľ���
    {
        return 0.0;    
    }
*/
//@author��Yeheya��2016-8-15