//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��3.5.2 ������
//����3.7�� �պ�ͼ�γ����༰�����ࡣ
//��˼����3-8���������Ǿ����������̳�

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public final class Square extends Rectangle      //�������࣬�̳о����࣬�����࣬���ܱ��̳�
{
    //���췽����point��length�ֱ�ָ�����������Ͻǵ����ꡢ����
    public Square(Point point, int length)
    {
        super(point,length,length);              //�������Ǿ������������ȺͿ�����
        this.shape = "������";                     //shapeȨ�ޱ���Ϊprotected������Ϊprivate
    }
    public Square()
    {
        this(new Point(),0);
    }
    public String toString()
    {
        return //this.getClass().getName()+
               "(���Ͻǵ�����"+this.point1+"���߳�"+this.length+")";
    }
}
//@author��Yeheya��2016-8-16