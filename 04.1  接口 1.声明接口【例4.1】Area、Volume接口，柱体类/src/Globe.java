//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��17��
//��4.1 �ӿ���ʵ�ֽӿڵ���
//4. �ӿ��Ƕ�̳е�
//��˼����4-1������ʵ�ֶ���ӿڡ�
//������4���Ƚ϶����С

import mypackage.Point;                          //������1.2��Ŀmypackage���е�Point��

//���࣬ʵ�ּ������������ӿڣ�ʵ�ֿɱȽϽӿڣ�������Ƚ϶����С
//public class Globe extends Object implements Solid, Comparable<Globe>
public class Globe extends Object implements Area,Volume, Comparable<Globe>
{
    private Point point;                         //ԭ��λ��
    private double radius;                       //�뾶

    public Globe(Point point, double radius)     //���췽��
    {
        this.point = point;
        this.radius = radius;
    }
    public Globe()
    {
        this(new Point(0,0), 0);
    }

    public double area()                         //������ı������ʵ��Area�ӿ�
    {
        return 4*Math.PI * this.radius * this.radius;
    }

    public double volume()                       //������������ʵ��Volume�ӿ�
    {
        return 4/3*Math.PI * this.radius * this.radius * this.radius;
    }

    public String toString()
    {
        return this.getClass().getName()+"��("+
            String.format("�뾶%1.1f)�������%1.1f, ���%1.1f", this.radius, this.area(), this.volume());
    }

    //�Ƚ�this��obj�����Ƿ���ȣ�����Object��ķ�����
    //������obj����Globeʵ��ʱ���㷨����Ƚϸ���Ա��������ֵ�Ƿ����
    public boolean equals(Object obj)
    {
        if (this==obj)                           //thisָ�����õ�ǰ�����Ķ���
            return true;
        if (obj instanceof Globe)                //��obj����ʵ������Globe�������࣬obj==nullʱ����false 
        {
            Globe globe = (Globe)obj;            //����ǿ��ת����globeҲ����obj���õ�ʵ��
            return this.point.equals(globe.point) && this.radius==globe.radius;
        }
        return false;
    }
    
    public int compareTo(Globe globe)            //�Ƚ϶����С
    {
        return (int)(this.volume() - globe.volume()); //������Ƚ϶����С
    }

    public static void main(String args[])
    {
        Point point=new Point(100,100); 
        Globe globe1= new Globe(point, 10), globe2= new Globe(point, 20);
        System.out.println("globe1="+globe1.toString()+"\nglobe2="+globe2.toString()+
                           "\nglobe1�������globe2�Ĵ���٣�"+globe1.compareTo(globe2));
    }
}
/*
�������н�����£�
globe1=Globe��(�뾶10.0)�������1256.6, ���3141.6
globe2=Globe��(�뾶20.0)�������5026.5, ���25132.7
globe1�������globe2�Ĵ���٣�-21991
*/

//@author��Yeheya��2016-8-17��2018��5��6��