//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��7��
//��1.2   JDK
//��1.2.3   ��
//����1.2�� ������ʹ�ð���

package mypackage;                               //������ǰ�ļ��е�����mypackage����

public class Point
{
    public int x,y;                              //��Ա���������X��Y��������
    
    public Point(int x,int y)                    //���췽��
    {
        this.x = x;
        this.y = y;
    }
    public Point()                               //���췽��������
    {
        this(0,0);
    }
    public String toString()                     //��Ա������������ַ�����������ʽΪ(x,y)
    {
        return "("+this.x+","+this.y+")";
    }
}
//@author��Yeheya��2016-6-9�������