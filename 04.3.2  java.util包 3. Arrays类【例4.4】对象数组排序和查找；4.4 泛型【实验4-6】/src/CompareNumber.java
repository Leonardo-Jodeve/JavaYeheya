//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.3.2   java.util���еĹ������
//����4.4��  Person���������������Ͷ��ַ������㷨�����ֱȽϹ���//��˼����4-6��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person������3.5��Student����
//����6.4��  Person������Ϣ������˼����6-4����

//import java.util.Comparator;                   //�Ƚ����ӿ�

public class CompareNumber implements java.util.Comparator<Student>  //ѧ�űȽ�����
{
    public int compare(Student str1, Student str2)  //��ѧ�űȽ�Student�����С
    {
        return str1.number.compareTo(str2.number);
    }
}

class CompareNumber_ex
{
    public static void main(String[] args) 
    {
//      Comparator<Student> c = new CompareNumber(); //��ȷ��
        
//    Comparator<Person> c = new CompareNumber(); //�����
        java.util.Comparator<? extends Person> c = new CompareNumber(); //��ȷ��
      
//      java.util.Comparator<? super Person> c = new CompareNumber(); //�����
//      java.util.Comparator<?> c = new CompareNumber(); //��ȷ��
    }
}
//@author��Yeheya��2016-8-22