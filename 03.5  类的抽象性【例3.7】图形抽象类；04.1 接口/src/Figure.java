//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��14��
//��3.5.2 ������
//����3.7�� ͼ�γ����༰�����ࡣ
//MyEclipse���ñ���·��������Ŀ����1.2��

//ͼ�γ����࣬Ϊ����������ʾλ�õ������
//���Բ��������󷽷��������ǲ��ܴ���ʵ��
//import java.awt.Graphics;

import java.awt.Color;
import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

public abstract class Figure
{
    //public 
    protected Point point1;            //����㣬��ʾλ�ã�һ��ͼ��������һ���㡣����Ȩ�ޣ�����ɼ�
    
    //���췽���������ǳ��󷽷���protectedȨ�ޱ�ʾ���ܴ���ʵ�������췽��ֻΪ���������
    protected Figure(Point point1)
    {
        this.point1 = point1;
//        this.set(point);
    }    
//    protected Figure()                           //���췽����//�δ��õ�����5����ûд
//    {
//        this(new Point());
//    }
    
    public String toString()                     //���������ַ����������λ�á����౸��
    {
        return //this.point.toString();            //��5�£���pointΪnull��Java���׳��ն����쳣
               this.point1==null ? "" : this.point1.toString();
               //��pointΪ�ն�������մ�������ն����쳣�������ܳ������ʱ������û�е�����
    }
    //���ϡ���3.7�����������п��Բ��������󷽷������ܴ���ʵ���������ǣ�Ϊ�����ṩͨ�õķ�������ˣ�����Ҫ��������ʵ���� 
    
    //���µ�5��̲�δд
    //����3.7�������ۡ� ������pointȨ�޷�public 
    public void set(Point point1)
    {
        this.point1 = point1;
    }
    public Point getPoint()
    {
        return this.point1;
    }
    
    //��˼����3-7��
//    public abstract void draw(java.awt.Graphics g);//��ͼ����������˵�������6��
//    public abstract void revolve(int angle);         	//��ת��angle����ָ���Ƕ�
//    public abstract void zoom(int percentage);      	//���ţ�percentage����ָ���ٷֱ�
}

class Figure_ex  
{
    public static void main(String args[])
    {
        Figure fig1 = new Line(100,100,500,400);           //�����������������ʵ��
        Figure fig2 = new Line(new Point(100,100), new Point(200,200));
        System.out.println("fig1��"+fig1.toString());       //toString()����ʱ��̬
        System.out.println("fig2��"+fig2.toString()); 
        //���ܱȴ�С
    }
}
/*
�������н�����£�
fig1��Line(Point(100,100), Point(500,400))������500.00
fig2��Line(Point(100,100), Point(200,200))������141.42
*/
//@author��Yeheya��2016-8-14