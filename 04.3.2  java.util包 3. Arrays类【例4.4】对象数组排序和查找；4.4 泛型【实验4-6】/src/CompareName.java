//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.3.2   java.util���еĹ������
//����4.4��  Person���������������Ͷ��ַ������㷨�����ֱȽϹ���
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person������3.5��Student����
//��˼����4-6�� �� ����CompareName�Ƚ����࣬�������Ƚ�Person�����С��
//����6.4��  Person������Ϣ������

import java.util.Comparator;

public class CompareName implements java.util.Comparator<Person>//�����Ƚ�����
{
    public int compare(Person per1, Person per2)      //�������Ƚ�Person�����С
    {
        return per1.name.compareTo(per2.name);        //����String���compareTo()
    }
}

class CompareBirthdate implements java.util.Comparator<Person>  //�������ڱȽ�����
{
    public int compare(Person per1, Person per2)      //���������ڱȽ�Person�����С
    {
        return per1.birthdate.compareTo(per2.birthdate);//����MyDate��compareTo()
    }
}


//��˼����4-7��
class CompareSurname implements java.util.Comparator<Person>//�����ϱȽ�Person�����С
{
    public int compare(Person per1, Person per2)
    {
        if (per1.name.startsWith(per2.name))         //�Ƚ����ϣ�����String���startsWith()
            return 0;                            //���
        return per1.name.compareTo(per2.name);       //���ַ����Ƚ�������С
    }
}

class CompareAge implements java.util.Comparator<Person>   //������Ƚ϶����С��ʵ�ֱȽ����ӿ�
{
    public int compare(Person per1, Person per2)
    {
        return per2.birthdate.getYear()-per1.birthdate.getYear();//�Ƚϳ������ڵ���ݣ�����MyDate�෽��
    }
}

//�ṩ�����ϲ��ҵ�ģ����ѯ���ܣ�ʵ�ֱȽ����ӿ�
class CompareContainName implements java.util.Comparator<Person>
{
    public int compare(Person per1, Person per2)
    {
        if (per1.name.indexOf(per2.name)>=0)              //��per1.name��������per2.name�ַ����������
            return 0;
        return per1.name.compareTo(per2.name);            //���ַ����Ƚ�������С
    }
}

class Comparator_ex
{
    public static void main(String[] args) 
    {
        Comparator<Person> c = new CompareBirthdate();
        c = new CompareBirthdate();
        c = new CompareAge();
        Comparator<? extends Person> c2 = new CompareBirthdate();
        c2 = new CompareBirthdate();
        c2 = new CompareAge();

//        Comparator<Person> comps[] = {new CompareName()};//�﷨��
        Comparator<?> comps1[] = {new CompareBirthdate()};        //�﷨��ȷ
//        Comparator<? extends Person> comps2[] = {new CompareName()};//�﷨��
//        Comparator<T extends Person> comps2[] = {new CompareName()};//�﷨��
    }
}
//@author��Yeheya��2016-10-18