//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//��˼����3-7������

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//�����࣬�̳бպ�ͼ�γ����ࣻ�̳�������Figure��point��ʾ�������Ͻǵ����ꣻ
//������4���̳и���ClosedFigure�ıȽ϶����С�ķ���
public class Rectangle extends ClosedFigure
{
    protected int length, width;                 //���εĳ��ȺͿ��

    //���췽���������ֱ�ָ���������Ͻǵ����ꡢ���ȺͿ�ȡ���length��width<0���׳���Ч�����쳣
    public Rectangle(Point point1, int length, int width) throws IllegalArgumentException
    {
        super("����", point1);
        if(length>=0 && width>=0)
        {
            this.length = length;
            this.width = width;
        }
        else throw new IllegalArgumentException("���Ȼ��Ȳ���Ϊ������");
    }
    
    public Rectangle(Point point1, Point point2) //���췽�����أ�����ָ���������Ͻǵ�����½ǵ�����
    {
        this(point1, Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));//Math.abs(x)����x�ľ���ֵ
    }
    public Rectangle(int x1,int y1, int x2,int y2)//���췽�����أ�����Ϊ(x1,y1)��(x2,y2)
    {
        this(new Point(x1,y1), new Point(x2,y2));
    }        
    public Rectangle()
    {
        this(new Point(),0,0);
    }
    
    public String toString()                     //���������ַ�����������λ�á����ȡ�������ԡ����ǣ���չ����
    {
        return "(���Ͻǵ�"+super.toString()+"������"+this.length+"�����"+this.width+")";
    }
    
    public double perimeter()                    //��������ܳ���ʵ�ָ���ĳ��󷽷�
    {
        return (this.width+this.length)*2;
    }
    public double area()                         //������������ʵ�ָ���ĳ��󷽷�
    {
        return this.width*this.length;
    }
    
    //���µ�5��̲�δд
    public Rectangle(Rectangle rec)              //�������췽��
    {
        this(rec.point1, rec.length, rec.width);
    }
   
    public void set(int length, int width)
    {
        this.length = length;
        this.width = width;
    }

    public double getLength()
    {
        return this.length;
    }

    public double getWidth()
    {
        return this.width;
    }
    
    //��˼����3-8��
    public boolean contains(Point p)             //�ж�p(x,y)���Ƿ��ڵ�ǰ����������
    {
        return p.x>=this.point1.x && p.x<=this.point1.x+this.length &&
               p.y>=this.point1.y && p.y<=this.point1.y+this.width;
    }
    
    //��6��
    public void draw(java.awt.Graphics g)        //��ͼ
    {
        g.drawRect(this.point1.x, this.point1.y, this.length, this.width);
    }
}
//@author��Yeheya��2016-8-15