//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ 

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public class ClosedFigure_ex 
{
    public static void main(String[] args)       //main()����Ҳ��д��ClosedFigure��������
    {
        Point p1 = new Point(100,100);
        ClosedFigure cfig1;
        cfig1 = new Rectangle(100,100, 300,200); //���Σ���200����100���������������ꣻ���������������ʵ��
        ClosedFigure cfig2 = new Square(p1,100); //�����Σ�ֱ��100�����������ࣨ����ࣩʵ��
        System.out.print("cfig1��");  cfig1.print();
        System.out.print("cfig2��");  cfig2.print();
        System.out.println("cfig1��cfig2�������٣�"+cfig1.compareTo(cfig2));//��4��
        
        cfig1 = new Ellipse(100,100, 300,200);   //��Բ��ֱ����200����100���������������ꣻ���������������ʵ��
        cfig2 = new Circle(p1,100);              //Բ�����������Σ�ֱ��100�����������ࣨ����ࣩʵ��
        System.out.print("cfig1��");  cfig1.print();
        System.out.print("cfig2��");  cfig2.print();
        System.out.println("cfig1��cfig2�������٣�"+cfig1.compareTo(cfig2));//��4��
        
        cfig1 = new Triangle(p1, new Point(100,130), new Point(140,130));  //ֱ��������
        System.out.print("cfig1��");  cfig1.print(); //����������ԣ�����toString()��perimeter()��area()����ʱ��̬

        cfig2 = new EuilateralTriangle(p1, 20);  //�ȱ������Σ���˼����3-8��
        System.out.print("cfig2��");  cfig2.print();
        System.out.println("cfig1��cfig2�������٣�"+cfig1.compareTo(cfig2));//��4��

        Point[] pentagon={p1, new Point(200,100), new Point(250,150), new Point(200,200), new Point(100,200)};
        cfig1 = new Polygon(pentagon);           //cfig���������ʵ��
        System.out.print("cfig1��");  cfig1.print();
    }
}
/*
�������н�����£�
cfig1������(���Ͻǵ�(100,100)������200�����100)���ܳ�600.00�����20000.00
cfig2��������(���Ͻǵ�����(100,100)���߳�100)���ܳ�400.00�����10000.00
cfig1��cfig2�������٣�10000
cfig1����Բ�����о���(���Ͻǵ�(100,100)������200�����100)���ܳ�471.24�����15707.96
cfig2��Բ������������(���Ͻǵ�(100,100)���߳�100)���ܳ�314.16�����7853.98
cfig1��cfig2�������٣�7853
cfig1��������(3������(100,100),(100,130),(140,130)��3�߳�30.00,40.00,50.00)���ܳ�120.00�����600.00
cfig2���ȱ�������((100,100)���߳�20.00)���ܳ�60.00�����173.21
cfig1��cfig2�������٣�426
cfig1�������(5����(100,100),(200,100),(250,150),(200,200),(100,200))���ܳ�441.42�����12500.00

*/
/*
    Triangle t1 = new Triangle(new Point(100,100), new Point(200,100), new Point(200,200));
         new Point(150,100), new Point(200,100)));      //���㹲��
�������н�����£�
�����ε������ 0.0
*/
//@author��Yeheya��2016-8-15