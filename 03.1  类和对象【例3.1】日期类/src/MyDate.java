//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��25��
//��3.1  ��Ͷ���
//����3.1��  ���������༰ʹ�����ڶ���

public class MyDate                                        //����������
{
    int year, month, day;                                  //��Ա��������ʾ�ꡢ�¡���
    
    void set(int y, int m, int d)                          //��Ա��������������ֵ
    { 
        year = y;
        month = m;
        day = d;
    } 
    void set(MyDate date)                                  //����ǰ����ֵ����Ϊ����ֵ������
    {
        set(date.year, date.month, date.day);              //�������ص�ͬ����Ա����������ʹ��this()
    } 
    
    public String toString()                               //�������������ַ������������ڸ�ʽ
    {
        return year+"��"+month+"��"+day+"��";
        //��˼����3-1���� ���toString()����ʵ��Ϊ���£���������
//      return year+'-'+month+'-'+day;                     //�����﷨����������int������+"-"+;
    }   
    
    public static void main(String[] args)
    {
        MyDate d1 = new MyDate();                          //�������󡢴���ʵ�������ø�ֵ
        System.out.println("d1��"+d1.toString());
        d1.set(2017,1,1);                                  //������ĳ�Ա����
        MyDate d2 = d1;                                    //�������ø�ֵ
        System.out.println("d1��"+d1.toString()+"��d2��"+d2.toString());
        d2.month = 10;                                     //�޸�ʵ����Ա����ֵ
        System.out.println("d1��"+d1+"��d2��"+d2);         //��������ַ���������Ĭ�ϵ���d1.toString()
        d2 = new MyDate();                                 //������һ��ʵ��
        d2.set(d1);
        System.out.println("d1��"+d1+"��d2��"+d2);
    } 
}
/* 
�������н�����£�
d1��0��0��0��
d1��2017��1��1�գ�d2��2017��1��1��
d1��2017��10��1�գ�d2��2017��10��1��
d1��2017��10��1�գ�d2��2017��10��1��
*/
/*
�������
        MyDate date;
        System.out.println("==null"+date.equals(null));//���������δ��ʼ��variable d might not have been initialized

        MyDate d3 = (MyDate)d2.clone();             //CloneNotSupportedException
        System.out.println("d3��"+d3+"��d3==d2��"+(d3==d2)+"��d3.equals(d2)�� "+(d3.equals(d2)));
       
*/
//@author��Yeheya��2016-7-25