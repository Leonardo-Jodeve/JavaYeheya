//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��17��
//��4.1 �ӿ���ʵ�ֽӿڵ���
//7.  ���̳кͶ�̳�
//ͼ4.7���ӿڵĶ�̳в����ڶ����Գ�ͻ����������

//(a) �ӿ������븸�ӿ�ͬ���������������븲�����ֶ�̬
public interface Volume1 extends Area  //�ӿڼ̳�
{
    public abstract double area();     //���ǣ��븸�ӿڷ����Ĳ����б�ͷ���ֵ���;���ͬ������Լ�� 
//  public abstract int area();        //��������Ǻ��嵫����ֵ���Ͳ�ͬ 
    public abstract int area(int n);   //���أ��븸�ӿڷ����Ĳ����б�ͬ 
    public abstract double volume();   //���ӵķ���
}

//(b) �ӿڶ�̳У�������ӿ�������ͬ�ĳ��󷽷�����
interface Solid1 extends Area,Volume1    
{}
//��ȷ��Area,Volume1�ӿڵ�area()Լ����ͬ���̳�������
//public abstract double area(); 
//public abstract int area(int n);     //����

//(b) ��ʵ�ֶ�ӿڣ�������ӿ�������ͬ�ĳ��󷽷��������̳�ԭ����ͬ
class Globe1 extends Object implements Solid1         //���࣬ʵ�ֽӿ�
//class Globe1 extends Object implements Area,Volume1  //���࣬ʵ�ֽӿ�
{
    public double area()  {return 0;}                //����Լ����һ��ʵ�֣���������ȷ�� 
    public int area(int n){return 0;}                //���� 
    public double volume(){return 0;} 
}

//(c) �ӿڶ�̳У��������ӿ�����ͬ�������������г�ͻ���﷨����
//�����Area1,Volume1�ӿڵ�area()Լ����ͬ
interface Area1                            //�ɼ�������ӿ�
{
    public abstract int area();               //���󷽷���������� 
}
/*
interface Volume2 extends Area,Area1   //�����,Area,Area2���ظ�����area()�������߷��ز�ͬ��������
{
    public abstract double volume();
}*/
//@author��Yeheya��2016-8-17��2018��7��18��