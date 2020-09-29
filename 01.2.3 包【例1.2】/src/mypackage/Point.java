//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��7��
//��1.2   JDK
//��1.2.3   ��
//����1.2�� ������ʹ�ð���
//���ã�����3.7�� ͼ�γ����༰�����ࡣ����4.2�� ������

package mypackage;                               //������ǰ�ļ��е�����mypackage����

public class Point                               //�������
{
    public int x, y;                             //��Ա���������X��Y��������
    
    //���췽������x��y<0���׳���Ч�����쳣
    public Point(int x, int y)  throws IllegalArgumentException
    {
        if(x>=0 && y>=0)
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("���Ȼ��Ȳ���Ϊ������");
    }
    public Point()                               //���췽��������
    {
        this(0, 0);
    }
    public Point(Point p)                        //�������췽��
    {
        this(p.x, p.y);
    }
    public String toString()                     //��Ա������Point���������ַ�������ʽΪ(x,y)
    {
        return //this.getClass().getName()+        //��4��4.3.1�ڣ���˼����4-4��
               "("+this.x+","+this.y+")";
//        return this.getClass().getName()+"[x="+x+",y="+y+"]"; //java.awt.Point
    }
    //���ϡ���1.2�� ������ʹ�ð���
    
    //������3�� 
    public void moveTo(int x, int y)             //�ƶ���(x,y)���ı䵱ǰ����λ��
    {
        this.x = x;
        this.y = y;
    }
    public void moveTo(double x, double y)       //�ƶ���(x,y)���ı䵱ǰ����λ�ã���Ա��������
    {
        this.moveTo((int)Math.floor(x+0.5), (int)Math.floor(y+0.5));
    }
    public void moveTo(Point p)                  //�ƶ���p���ı䵱ǰ����λ�ã���Ա��������
    {
        this.moveTo(p.x, p.y);
    }

    public void move(int dx, int dy)             //X��Y����ֱ��ƶ�dx��dy����
    {
        this.moveTo(this.x+dx, this.y+dy);
    }
    
    public boolean equals(Object obj)            //�Ƚϵ�ǰ������obj�Ƿ����
    {
        if(this==obj)
            return true;
        if(obj instanceof Point) 
        {
            Point p=(Point)obj;
            return this.x==p.x && this.y==p.y;
        }
        return false;

//        return this==obj || obj instanceof Point && this.x==((Point)obj).x && this.y==((Point)obj).y;
    }
    

}
//@author��Yeheya��2016-6-9�������