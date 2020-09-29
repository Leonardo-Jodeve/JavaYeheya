//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//��˼����3-7����Բ�࣬ʹ�����о���ȷ����Բ��λ�úʹ�С��

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//��Բ�࣬�̳о����࣬ʹ�����о���ȷ����Բ��λ�úʹ�С��//�̳е�point��ʾ��Բ���о��ε����Ͻǵ����꣬����ԭ�ĵ�����
//������4���̳и���ClosedFigure�ıȽ϶����С�ķ���
public class Ellipse extends Rectangle
{
    public Ellipse(Point point, int length, int width)//���췽���������ֱ�ָ����Բ���о��ε����Ͻǵ����ꡢ���ȺͿ��
    {
        super(point, length, width);
        this.shape = "��Բ";
    }    
    public Ellipse(Point point1, Point point2)  //���췽�����أ�����ָ���������Ͻǵ�����½ǵ�����
    {
        super(point1, point2);
        this.shape = "��Բ";
    }
    public Ellipse(int x1,int y1, int x2,int y2)//���췽�����أ�����Ϊ(x1,y1)��(x2,y2)
    {
        super(x1,y1, x2,y2);
        this.shape = "��Բ";
    }        
    public Ellipse()
    {
        super();
        this.shape = "��Բ";
    }
    
    public String toString()                     //���������ַ�����������о������ԡ�����
    {
        return "��"+"���о���"+super.toString();    //��3�£����"��Բ���о���(����)"��ϣ���ĺ���
//        return this.getClass().getName()+this.shape+"(����"+super.toString()+")";//��4�£����"Ellipse(����Ellipse(����)"����ϣ���ĺ���
    }
    
    public double perimeter()                    //������Բ�ܳ�����ʽΪ��(a��뾶+b��뾶)������
    {
        return Math.PI*(this.length/2+this.width/2);
    }
    public double area()                         //������Բ�������ʽΪ��ab������
    {
        return Math.PI*(this.length/2)*(this.width/2);
    }
    
    //���µ�5��̲�δд
    public Ellipse(Ellipse e)                    //�������췽��
    {
        super(e);                                //���ø���Ŀ������췽��
        this.shape = "��Բ";
    }

    //��6��
    public void draw(java.awt.Graphics g)        //��ͼ������
    {
        g.drawOval(this.point1.x, this.point1.y, this.length, this.width);//�����ֱ�ָ����Բ���о��ε����Ͻǵ����ꡢ���ȺͿ��
    }
}
//@author��Yeheya��2016-8-15