//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��15��
//��3.4  ��Ķ�̬��
//����3.6�� �����������������Һͺϲ��㷨��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person������3.5��Student����
//��˼����3-6�� �� �������·�����Person���Ͳ�����

public class PersonArray 
{
    public static void main(String[] args) 
    {
        Person p1 = new Person("��С��",new MyDate(1994,3,15));
        Person[] pers={p1, new Student(p1,"","�����","",false)};
        Student[] stus={new Student("����",new MyDate(1998,4,5),"","","","","��Ϣ����","",false),
                        new Student("���",new MyDate(1995,3,12),"","","","","ͨ�Ź���","",false)};

        Object[] objs = ObjectArray.concat(pers, stus);      //���غϲ��Ķ������飬����Ԫ�ض�������
//        Person[] objs = ObjectArray.concat(objs1, objs2);  //����������������Ͳ�ƥ�䣬���ܽ�Object[]ת����Person[] 
        stus[1].birthdate.set(new MyDate(2001,10,1));//�޸ĳ������ڣ�Ӱ��objs����Ԫ�أ�˵�����ù�ϵ
        ObjectArray.print(objs);
        
        Person[] keys = {new Person(p1), new Student((Student)pers[1])};  //��������Ҷ���
        for(int i=0; i<keys.length; i++)
        {
            System.out.println("���ң�"+keys[i].toString()+"������� ");
            ObjectArray.searchPrintAll(objs, keys[i]);      //����ִ��keys[i]�����ࣨPerson��Student���Ķ���ȽϹ���
        }
        Student.howMany();
       
//      removeAll(objs3, key);
/*
 * //      Object obj = search(objs, key);                   //����
        System.out.println("���ң�"+key.toString()+"������� ");//+((obj==null) ? "δ�ҵ�" : obj.toString()));
        System.out.println("ƽ��������"+average(objs)+"��");*/
    }
    
    
    //��˼����3-6�� �� �������·�����Person���Ͳ�����
    public static Person oldest(Person value[])            //������������ߣ����ض���
	{
	    if(value==null)
	        return null;
	    int max=0;                                         //�������ֵ������±�
	    for(int i=1; i<value.length; i++)
	        if(value[i].getAge()>value[max].getAge())
	            max = i;
	    return value[max];                                 //���ض�������
	}

	public static double average(Person value[])           //����ƽ������
	{
        double sum=0;        
	    for(int i=0; i<value.length; i++)
	        sum += value[i].getAge();                      //double��int������Ϊdouble
	    return value.length>0 ? sum/value.length: 0;       //����0��Ԫ�صĽ�����������Ϊ0����
	}
}
/*
�������н�����£�
��С��,1994��03��15��,,,
��С��,1994��03��15��,,,,,�����,
����,1998��04��05��,,,,,��Ϣ����,
���,2001��10��01��,,,,,ͨ�Ź���,
���ң���С��,1994��03��15��,,,������� 
��С��,1994��03��15��,,,
��С��,1994��03��15��,,,,,�����,
���ң���С��,1994��03��15��,,,,,�����,������� 
��С��,1994��03��15��,,,,,�����,
6��Person����4��Student����
*/    
    /*
  //��1����4�����δ�ã�����̫�࿴�����
         Person p = new Person("��С��",new MyDate(1994,3,15),"��","����","��ɳ");
         Person pobjs[]={p, new Student(p,"�����","�������ѧ�뼼��","211994001",true), 
                             new Student(p,"�Զ���","�Զ�����","020",true)};
         Student sobjs[]={new Student("����",new MyDate(1998,4,5),"Ů","����","�人","���ù���","��Ϣ����","321998003",true),
                           new Student("��С��",new MyDate(1995,3,12),"Ů","�㶫","����","ͨ��","ͨ�Ź���","014",true),
                           new Student("��Ȫ",new MyDate(1997,10,1),"��","����","����","����","��������","009",false)};

          Person[] key = new Person(p);                        //���
          System.out.println("���ң�"+key.toString()+"������� ");
          print(objs, key);                                 //����ִ��Person����ȽϹ���

          key = new Student((Student)sobjs[1]);             //key��������ʵ��
          System.out.println("���ң�"+key.toString()+"������� ");
          print(objs, key);                                 //����ִ��Student����ȽϹ���
          Student.howMany();

  ��2��������Student�븸��Person�ɱ�
  */
//@author��Yeheya��2018��2��15�գ���Ϧ