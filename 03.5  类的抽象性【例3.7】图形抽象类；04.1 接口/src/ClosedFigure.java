//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ 
//��4.1 �ӿ���ʵ�ֽӿڵ���

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//�պ�ͼ�γ����࣬�̳�ͼ�γ����࣬ʵ�ֿɼ�������ӿںͿɼ����ܳ��ӿڣ�������4��ʵ�ֿɱȽϽӿ�
public abstract class ClosedFigure extends Figure implements Area,Perimeter, Comparable<ClosedFigure>
{
    protected String shape;            //��״������Ȩ�ޣ�����ɼ���
                                       //��3�������ˣ�����Ϊ��Ҫ˵���ɼ̳и����Ա�������˹�����ʵ�֣�point1
                                       //��3��Ҫ�����ģ�����ͬ��ñ������������ѱ�ʾ����״����4�²��ܽ�����
    
    protected ClosedFigure(String shape, Point point1)//���췽��
    {
        super(point1);                           //���ø��๹�췽������ʼ�������point1
        this.shape = shape;
    }
    protected ClosedFigure(String shape)         //��5��̲�ûд
    {
        this(shape, new Point());
    }
    protected ClosedFigure()
    {
        this("", new Point());
    }

//    public abstract double perimeter();          //�����ܳ������󷽷�
//    public abstract double area();               //������������󷽷����Էֺ�";"���� 

    public void print()                          //����������Լ��ܳ�����������ó��󷽷�
    {
        //�¾���toString()�̳�Figure�࣬����㣻
    	//toString()��perimeter()��area()���า�ǣ�����ʱ��̬
    	System.out.println(this.shape+
           //this.getClass().getName()+          //��4��4.3.1�ڣ���˼����4-4��
            this.toString()+
            "��"+String.format("�ܳ�%1.2f�����%1.2f",this.perimeter(),this.area()));
    }
    
    //��˼����3-8��
    //������4��2015���4������ 
    public int compareTo(ClosedFigure cfig)       //������Ƚ϶����С
    {
        return (int)(this.area() - cfig.area());
    }
    //����δ���
    //public abstract boolean contains(Point p); //�ж�p���Ƿ���this�պ�ͼ��������

}
/*
    //��4�棬��5��δ��
    protected ClosedFigure(Point point1)         //���췽���������ǳ��󷽷�
    {
        super(point1);                           //���ø��๹�췽������ʼ�������point1
    }
*/
//@author��Yeheya��2016-8-17