//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//4.3.2   java.util���еĹ������
//2.  Comparator�Ƚ����ӿ�
//3.  Arrays������
//����4.4��  ������������Ͳ��ҡ�
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person������3.5��Student����
//4.4 ����

import java.util.Arrays;                                   //������

class PersonSort_ex
{
    public static void main(String args[])
    {
        Person per = new Person("Li��С��",new MyDate(1994,3,15),"��","����","��ɳ");
/*        Person[] value={per, new Person("Bai����", new MyDate(1991,5,1)),
                        new Person("Bai����", new MyDate(1994,3,15)),
                        new Person("Hua����", new MyDate(1992,10,3)),
                        new Person("Wang��ΰ", new MyDate(1990,4,3)),
                        new Student(p, "�����","�������ѧ�뼼��","001",true),
                        new Student("Li��С��", new MyDate(1990,2,1),"","","","�����","�������ѧ�뼼��","008",true),
                        new Student("Zhang��С��", new MyDate(1989,5,1),"","","","�Զ���","�Զ�����","020",true)};
*/
        Student value[]={new Student("Li��С��", new MyDate(1994,3,15),"","","","","","",true),
                new Student("Bai����", new MyDate(1991,5,1),"","","","","","",true),
                new Student("Bai����", new MyDate(1993,5,5),"","","","","","",true),
                new Student("Hua����", new MyDate(1992,10,3),"","","","","","",true),
                new Student("Wang��ΰ", new MyDate(1990,4,3),"","","","","","",true),                
                new Student("Li��С��", new MyDate(1990,2,1),"","","","�����","","",true),
                new Student("Li��С��", new MyDate(1994,3,15),"","","","�����","�������ѧ�뼼��","001",true),
                new Student("Zhang��С��", new MyDate(1989,5,1),"","","","������Ϣ","","",true)};
       
        //��1���������鰴��������Ͳ��ң�Ĭ����Person��compareTo()�����Ƚ϶����С
//        Arrays.sort(value);                                //value�������鰴������������
        CompareArray.sort(value);
        System.out.println("�����������������£�");
        CompareArray.println(value);                   

        //����������
        String name="Bai����";//"Li��";
        Person key = new Person(name,null);
//      int i=Arrays.binarySearch(value, key);              //�Զ��ַ�����keyָ������
        int i=CompareArray.binarySearch(value, key);     //���ַ�����
        System.out.println("��������\""+name+"\"�Ľ���� "+
                          ((i>=0 && i<value.length) ? value[i].toString() : "δ�ҵ�"));
        name="Li��С��";
        key = new Person(name,null);
        System.out.println("������������\""+name+"\"�Ľ���� ");
        CompareArray.printAll(value, key);               //˳�������������Ϊname�Ķ���
        
        //�ṩ�����ϲ���
        name="Bai��";//"Li��";
        key = new Person(name,null);
        i=CompareArray.binarySearch(value, key, new CompareSurname());
        System.out.println("������\""+name+"\"�Ľ����"+
                          ((i>=0 && i<value.length) ? value[i].toString() : "δ�ҵ�"));

        //ϰ�⣬�ṩ���������ҵ�ģ����ѯ����
        name="С";
        key = new Person(name,null);
//        i=Arrays.binarySearch(value, key, new CompareContainName());
        i=CompareArray.binarySearch(value, key, new CompareContainName());
        System.out.println("������������\""+name+"\"�Ľ���� "+
                          ((i>=0 && i<value.length) ? value[i].toString() : "δ�ҵ�"));
        
        
        //��2��Person�������鰴������������
//        Arrays.sort(value, new CompareBirthday());           //��CompareByAge��compare()�����ȽϹ�������
        CompareArray.sort(value, new CompareBirthdate());  //Ĭ�϶�������ʵ��Comparable�ӿڰ�compareTo()����Լ���ıȽϹ�������
        System.out.println("\n�����������������£�");
        CompareArray.println(value);
        
        MyDate date = new MyDate(1994,3,15);
        key = new Person("", date);
//        i = Arrays.binarySearch(value, key, new CompareBirthday());     //��CompareAge��compare()�����Ƚ�
        i=CompareArray.binarySearch(value, key, new CompareBirthdate());
        System.out.println("���ҳ�������Ϊ"+date.toString()+"�Ľ����"+
                          ((i>=0 && i<value.length) ? value[i].toString() : "δ�ҵ�"));
        CompareArray.printAll(value, key, new CompareBirthdate());  //Ĭ�϶�������ʵ��Comparable�ӿڰ�compareTo()����Լ���ıȽϹ�������

    }
}
/* 
�������н������,Person[]��Student[]�����ͬ����Ϊ���߾�ʵ��Comparable<Person>�ӿڣ�compareTo(Person)����������ͬ��

�����������������£�
Bai����,1991��05��01��,,,,,,,��Ա
Bai����,1993��05��05��,,,,,,,��Ա
Hua����,1992��10��03��,,,,,,,��Ա
Li��С��,1990��02��01��,,,,�����,,,��Ա
Li��С��,1992��03��15��,,,,,,,��Ա
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
Wang��ΰ,1990��04��03��,,,,,,,��Ա
Zhang��С��,1989��05��01��,,,,������Ϣ,,,��Ա

��������"Bai����"�Ľ���� Bai����,1991��05��01��,,,,,,,��Ա
������������"Li��С��"�Ľ���� 
Li��С��,1992��03��15��,,,,,,,��Ա
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
������"Bai��"�Ľ����Bai����,1993��05��05��,,,,,,,��Ա
������������"С"�Ľ���� Li��С��,1990��02��01��,,,,�����,,,��Ա

�����������������£�
Zhang��С��,1989��05��01��,,,,������Ϣ,,,��Ա
Li��С��,1990��02��01��,,,,�����,,,��Ա
Wang��ΰ,1990��04��03��,,,,,,,��Ա
Bai����,1991��05��01��,,,,,,,��Ա
Li��С��,1992��03��15��,,,,,,,��Ա
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
Hua����,1992��10��03��,,,,,,,��Ա
Bai����,1993��05��05��,,,,,,,��Ա

���ҳ�������Ϊ1992��03��15�յĽ����Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
Li��С��,1992��03��15��,,,,,,,��Ա
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա

*/
