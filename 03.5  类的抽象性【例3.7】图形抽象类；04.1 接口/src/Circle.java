//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� �պ�ͼ�γ����༰�����ࡣ 
//��˼����3-7��Բ����Բ�������̳�

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public final class Circle extends Ellipse        //Բ�࣬�̳���Բ�࣬�����࣬���ܱ��̳�
{
    //���췽����point��length�����ֱ�ָ��Բ���о��ε����Ͻǵ����ꡢ����
    public Circle(Point point, int length)
    {
        super(point,length,length);              //Բ����Բ������
        this.shape = "Բ";                        //shapeȨ�ޱ���Ϊprotected������Ϊprivate
                                                 //ʡ�Դ˾䣬shapeֵΪ"��Բ"
    }
    public Circle()
    {
        this(new Point(),0);
    }
    public String toString()
    {
        return //this.getClass().getName()+
        		"������������(���Ͻǵ�"+this.point1+"���߳�"+this.length+")";
    }
}
//@author��Yeheya��2015-7-18