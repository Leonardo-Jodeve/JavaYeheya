//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//4.3.2   java.util���еĹ������
//2.  Comparator�Ƚ����ӿ�
//3.  Arrays������
//����4.4��  ������������Ͳ��ҡ�
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person������3.5��Student����
//��˼����4-6�� �� ����CompareName�Ƚ����࣬�������Ƚ�Person�����С��
//4.4 ����

import java.util.Arrays;

public class StudentSort 
{
    public static void main(String args[])
    {
        Student value[]={new Student("Li��С��", new MyDate(1992,3,15),"","","","","","006",true),
                new Student("Bai����", new MyDate(1991,5,1),"","","","","","040",true),
                new Student("Bai����", new MyDate(1993,5,5),"","","","","","032",true),
                new Student("Hua����", new MyDate(1992,10,3),"","","","","","012",true),
                new Student("Wang��ΰ", new MyDate(1990,4,3),"","","","","","011",true),                
                new Student("Li��С��", new MyDate(1990,2,1),"","","","�����","","021",true),
                new Student("Li��С��", new MyDate(1992,3,15),"","","","�����","�������ѧ�뼼��","001",true),
                new Student("Zhang��С��", new MyDate(1989,5,1),"","","","������Ϣ","","001",true)};
       
        //��3��Student�������鰴ѧ������Ͳ���
//      Arrays.sort(value, new CompareNumber()); 
        CompareArray.sort(value, new CompareNumber());
        System.out.println("��ѧ�Ŵ����������£�");
        CompareArray.println(value);                   

        String number="001";
        Student key = new Student("", null,"","","","","",number,true);
//        i = Arrays.binarySearch(value, key, new CompareBirthday());
        int i=CompareArray.binarySearch(value, key, new CompareNumber());
        System.out.println("����ѧ��Ϊ"+number+"�Ľ����"+
                          ((i>=0 && i<value.length) ? value[i].toString() : "δ�ҵ�"));
        CompareArray.printAll(value, key, new CompareNumber());
    }
}
/* 
�������н������:
��ѧ�Ŵ����������£�
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
Zhang��С��,1989��05��01��,,,,������Ϣ,,001,��Ա
Li��С��,1992��03��15��,,,,,,006,��Ա
Wang��ΰ,1990��04��03��,,,,,,011,��Ա
Hua����,1992��10��03��,,,,,,012,��Ա
Li��С��,1990��02��01��,,,,�����,,021,��Ա
Bai����,1993��05��05��,,,,,,032,��Ա
Bai����,1991��05��01��,,,,,,040,��Ա

����ѧ��Ϊ001�Ľ����Zhang��С��,1989��05��01��,,,,������Ϣ,,001,��Ա
Li��С��,1992��03��15��,,,,�����,�������ѧ�뼼��,001,��Ա
Zhang��С��,1989��05��01��,,,,������Ϣ,,001,��Ա

*/
