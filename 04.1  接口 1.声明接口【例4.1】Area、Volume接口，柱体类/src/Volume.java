//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��17��
//��4.1 �ӿ���ʵ�ֽӿڵ���
//1. �����ӿ�
//2. ����ʵ�ֽӿڵ���
//����4.1��  Area��Volume�ӿ���ʵ����Щ�ӿڵ������ࡣ
import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public interface Volume                          //�ɼ�������ӿ�
{
    public abstract double volume();             //���󷽷����������
}


class AreaVolumn_ex                              //�ӿ���һ��������������
{
    public static void main(String args[])
    {
        Point point = new Point(100,100);
        ClosedFigure cfig = new Ellipse(point,10,20);      //�������cfig������Բ����ʵ��
        Area ar = cfig;                          //Area�ӿڶ���ar����ʵ��Area�ӿڵ�ClosedFigure���Ellipse����ʵ��
        System.out.println(cfig.toString()+"�����"+String.format("%1.2f", ar.area()));
        Cylinder cylinder = new Cylinder(cfig,10);//��Բ��
        ar = cylinder;                            //ar����ʵ��Area�ӿڵ�Cylinder���ʵ��
        Volume vol = cylinder;                    //Volume�ӿڶ���vol����ʵ��Volume�ӿڵ�Cylinder���ʵ��
        System.out.println("Elliptic Cylinder��Բ����"+cylinder.toString()
                           +"�������"+String.format("%1.2f�����%1.2f", ar.area(), vol.volume()));

        cylinder.cfigure = new Rectangle(point,10,20);     //����
        System.out.println("Cuboid�����壬"+cylinder.toString()
                +"�������"+String.format("%1.2f�����%1.2f", ar.area(), vol.volume()));
        cylinder.cfigure = new EuilateralTriangle(point,10);//�ȱ�������
        System.out.println("Trianglular Prism��������"+cylinder.toString()
                +"�������"+String.format("%1.2f�����%1.2f", ar.area(), vol.volume()));

        Globe globe = new Globe(point,10);                 //�� 
        ar = globe;                    //Area�ӿڶ���ar����ʵ��Area�ӿ�Solid�ӽӿڵ�Globe���ʵ��
        vol = globe;                   //Volume�ӿڶ���vol����ʵ��Volume�ӿ�Solid�ӽӿڵ�Globe���ʵ��
        System.out.println(globe.toString()+"�������"+String.format("%1.2f�����%1.2f",ar.area(), vol.volume()));
    }
}

/*
�������н�����£�
Ellipse��Բ��ԭ�ĵ�����Point(100,100)��a��뾶10.0��b��뾶20.0�����628.32
Elliptic Cylinder��Բ����Cylinder������Ellipse��Բ��ԭ�ĵ�����Point(100,100)��a��뾶10.0��b��뾶20.0����10.0�������2199.11�����6283.19
Cuboid�����壬Cylinder������Rectangle���Σ����Ͻǵ�����Point(100,100)������10.0�����20.0����10.0�������1000.00�����2000.00
Trianglular Prism��������Cylinder������EuilateralTriangle�ȱ������Σ�������Point(100,100)���߳�10.00����10.0�������386.60�����433.01
Globe�򣬰뾶10.0�������1256.64�����3141.59

*/
//@author��Yeheya��2016-8-17