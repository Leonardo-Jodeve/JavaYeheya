//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//����3.7�� ͼ�γ����༰�����ࡣ
//��˼����3-7���ȱ����������������������̳�

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//�ȱ��������࣬�̳��������࣬�����࣬���ܱ��̳�
public final class EuilateralTriangle extends Triangle
{
    //���췽����length����ָ���߳���point2��point3Ϊnull
    public EuilateralTriangle(Point point, double length)
    {
        super(point,length,length,length);
        this.shape = "�ȱ�������";        //shapeȨ�ޱ���Ϊprotected������Ϊprivate��ʡ�Դ˾䣬shapeֵΪ"������"                                                           
    }
    public EuilateralTriangle()
    {
        this(new Point(),0);
    }
    public String toString()
    {
        return //this.getClass().getName()+this.shape+
               //super.toString()+"���߳�"+String.format("%1.2f",this.a);  //����super.toString()���ø��࣬���ܵ���Figure�෽��
                "("+(this.point1==null ? "" : this.point1.toString())+ //����ն����쳣�������ܳ������ʱ������û�е�����
               "���߳�"+String.format("%1.2f)",this.a); 
    }
}
//@author��Yeheya��2016-8-15