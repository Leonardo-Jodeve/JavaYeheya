//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//��4����������

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//�����࣬�̳бպ�ͼ�γ����ࣻ�̳�������Figure��point��ʾ������һ���㣻//������4���̳и���ClosedFigure�ıȽ϶����С�ķ���
public class Triangle extends ClosedFigure       //�������࣬�̳бպ�ͼ�γ�����
{
    public Point point2, point3;                 //�����ε�3���㣬�̳�point1
    protected double a,b,c;                      //3���߳���
    
    //3�㹹��һ�������Σ�//�����㹲�ߣ������ε��ܳ������Ϊ0
    //��5�£�������Ϊnull�����÷���ʱ��Java���׳��ն����쳣�������㹲�ߣ����ܹ���һ�������Σ��׳���Ч�����쳣
    public Triangle(Point p1, Point p2, Point p3) throws IllegalArgumentException
    {
        super("������",p1);
//        if (new Line(p1,p2).contains(p3) || new Line(p1,p3).contains(p2) || new Line(p2,p3).contains(p1))
//            throw new IllegalArgumentException("���㹲�ߣ����ܹ���һ�������Ρ�");
        this.point2 = p2; 
        this.point3 = p3; 
        this.a = new Line(p1,p2).length();
        this.b = new Line(p2,p3).length();
        this.c = new Line(p3,p1).length();
    }
    //����a��b��cָ�������߳��ȣ�point2��point3Ϊnull��
    //�ȱ��������ã���5��̲�δ��ʵ�֣���������ܷ񹹳�һ�������Σ�
    public Triangle(Point point1, double a, double b, double c)
    {
        super("������",point1);
        this.point2 = this.point3 = null; 
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public String toString()                     //���������ַ���������3��λ�ú�3�߳������ԡ����ǣ���չ����
    {
        return //this.getClass().getName()+this.shape+
               "(3������"+super.toString()+","+//+this.point2.toString()+ ","+this.point3.toString()+
               (this.point2==null ? "" : this.point2.toString())+ ","+
               (this.point3==null ? "" : this.point3.toString())+
               "��3�߳�"+String.format("%1.2f,%1.2f,%1.2f)",this.a,this.b,this.c);
               //����ն����쳣��ֻ��3�㲻�ǿն���ʱ�����������5�£���point2��point3Ϊnull��Java���׳��ն����쳣
    }
    public double perimeter()                    //�����������ܳ�
    {
        return a+b+c;
    }
    public double area()                         //��������������������㹲�ߣ����Ϊ0
    {
        double s=(a+b+c)/2;                      //������������ĺ��׹�ʽ
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));   //Math.sqrt(x)����x��ƽ����
    }
    //��6�� 
    public void draw(java.awt.Graphics g)        //��ͼ
    {
        g.drawLine(this.point1.x, this.point1.y, this.point2.x, this.point2.y);
        g.drawLine(this.point2.x, this.point2.y, this.point3.x, this.point3.y);
        g.drawLine(this.point3.x, this.point3.y, this.point1.x, this.point1.y);
    }
}    
    
    //��5��̲�δ��
/*    public static double area(Point p1, Point p2, Point p3)//�������������
    {
        return new Triangle(p1,p2,p3).area();    //��̬��Ա�����п��Դ���ʵ��
    }

    //��˼����3-8��δ���
    public boolean contains(Point p)             //�ж�ָ�����Ƿ��ڵ�ǰ�����εķ�Χ��
    {
        return false;
    }
   
    public boolean contains(int x, int y)        //�ж�ָ�����Ƿ��ڵ�ǰ�����εķ�Χ��
*/
//@author��Yeheya��2016-8-15