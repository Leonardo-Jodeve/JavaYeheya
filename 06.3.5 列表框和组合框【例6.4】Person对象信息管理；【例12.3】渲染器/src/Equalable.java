//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.3.2   java.util���еĹ������
//����4.5��  Person���������������Ͷ��ַ������㷨�����ֱȽϹ���//��˼����4-7��
//����6.4��  Person������Ϣ������

public interface Equalable<T>                    //�ɱȽ϶�����Ƚӿڣ��ṩ���ֲ�������
{
    public boolean equals(T t1, T t2);           //�Ƚ�t1��t2�����Ƿ����
}

class EqualName implements Equalable<Person>     //�������Ƚ�Person�����Ƿ����
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.name.equals(p2.name);          //�Ƚ������ַ����Ƿ���ȣ�����String���equals()
    }
}

class EqualBirthdate implements Equalable<Person> //���������ڱȽ�Person�����Ƿ����
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.birthdate.equals(p2.birthdate);//�Ƚϳ��������Ƿ���ȣ�����MyDate���equals()
    }
}

//��˼����4-7���̲�δ��
class EqualSurname implements Equalable<Person>  //�����ϱȽ�Person�����Ƿ����
{
    public boolean equals(Person p, Person key)
    {
        return p.name.startsWith(key.name);      //p.name�Ƿ���key.name��ͷ
    }
}

class EqualYear implements Equalable<Person>     //��������ݱȽ�Person�����Ƿ����
{
    public boolean equals(Person p1,Person p2)
    {
        return p1.birthdate.getYear()==p2.birthdate.getYear();//�Ƚϳ��������е����
    }
}
//@author��Yeheya��2016-8-22