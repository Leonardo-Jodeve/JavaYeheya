//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.4   ����
//2.  ���͵ı�Ҫ��

public class Genericity_ex 
{
    public static void main(String args[])
    {
        //��1�� Object[]��������
//        Object[] value={new Object(),"xyz", new Integer(123)};  //Object[]Ԫ�ؿ������κ����ʵ��
//        value[i].toString();                                 //����ʱ��̬
//        value[1].length();                                   //�����Object��û������length()����

        //��2�� Comparable[]��������
        Comparable[] value={"abc", "xyz", "def", "ghi", new Integer(123)};      //����Ԫ�����Ͳ�ͬ
        value[0].compareTo(value[1]);                        //��������Ԫ�ؿɱȽ϶����С
        value[3].compareTo(value[4]);                     //Stringʵ����Integerʵ�����ɱȣ��׳��쳣
//        Comparable<String>[] svalue={"abc","def"};      //�﷨���޷�������������
        
        //��3�� Comparable<T>��������ΪT��һ�ֶ���
        Comparable<String> scom="abc";                     //��ȷ��Stringʵ����Comparable<String>�ӿ�
        Comparable<Person> pcom = new Person("��С��",null,"��","","");  //��ȷ��Personʵ����Comparable<Person>�ӿ�
        pcom = new Student("����",null,"Ů","","","���ù���","","",true);
                  //��ȷ��Student�̳�Person�࣬Ҳʵ����Comparable<Person>�ӿڣ�ע�Ⲣû��ʵ��Comparable<Student>�ӿ�
    }
}
//@author��Yeheya��2016-8-22
