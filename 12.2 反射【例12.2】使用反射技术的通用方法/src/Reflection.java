//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��2��
//��12.2 ����
//����12.6��Person������Ϣ���������ṹ��ʾ�й����С�
//Ϊ����12.6����׼����

import java.lang.reflect.Field;                  //���������

public class Reflection                          //ʹ�÷��似����ͨ�÷��� 
{
    //����3��������5����12.6���ã�2018��2��6�ա�
	
    //����obj����ʵ�������й��г�Ա��������֪n������������˽�г�Ա������
    //������Ա��������Ϊ�������ǰ��������ں�
    ////��5��Ľ���2018��2��11�ա��������������������Student��񣬵�������
    public static Field[] getFields(Object obj, int n)
    {
        Class<?> c = obj.getClass();             //���obj����ʵ����������
        Field[] fields_super = c.getSuperclass().getFields();
            //���c������й��г�Ա�������������������ģ������Ա������ǰ��������ں�
        Field[] fields_sub = c.getDeclaredFields();
            //���c���������ĳ�Ա����������˽�г�Ա����������������ĳ�Ա����
            //����Person������6�У�����count������Student������5�У�����count��
        Field[] fields = new Field[n];
        int i=0;
        for(i=0; i<fields_super.length; i++)     //�ϲ�������Ա��������
            fields[i] = fields_super[i];
        for(int j=0; i<n; i++, j++)
            fields[i] = fields_sub[j];
        return fields;
    }	

    //����fields��Ա��������Ԫ�ص������ַ���
    public static String[] toString(Field[] fields)
    {
        String[] str = new String[fields.length];
        for(int i=0; i<str.length; i++)
            str[i] = fields[i].getName();        //��ó�Ա�������ַ���
        return str;
    }

    //��obj����ʵ�������г�Ա����������fields���飬
    //���ر���obj����ʵ�������г�Ա����ֵ�Ķ�������
    public static Object[] toArray(Object obj, Field[] fields)
    {
        Object[] arow = new Object[fields.length];
        for(int i=0; i<fields.length; i++)
        {
            try
            {
                arow[i] = fields[i].get(obj);    //���obj����ʵ����fields[i]��Ա����ֵ
            }
            catch(IllegalAccessException ex)     //��Ч��ȡ�쳣
            {
                break;
            }
        }
        return arow;
    }
    
    //��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��7��15��
    //������12.6δ���á� 
    //��ʵ��6-22��Person������Ϣ����ʹ�ñ�񡣵���//���γ����12-13�� �绰�������á�
    //��ö���obj����ʵ�������г�Աֵ���洢��arow�����з���
    //Person��Student����ó�Ա�����Ĵ���ͬ���ڱ������ʾ���в�ͬ��
    //����getFields()�������obj����ʵ������������й��г�Ա�������������������ģ������Ա������ǰ�������Ա�ں󣬴��򲻶�
    //���ԣ�����Student���ԣ��Ȼ�ø���Person��Ա�������ٳ�Ա����ʹ�á�Student
    public static Object[] toArray(Object obj)
    {
        Field[] fields_super = obj.getClass().getSuperclass().getFields();
            //���obj����ʵ������������й��г�Ա�������������������ģ������Ա������ǰ�������Ա�ں�
        Field[] fields = obj.getClass().getDeclaredFields();
            //������������ĳ�Ա����������˽�г�Ա���������������������ĳ�Ա
            //����Person������6�У�����count��
            //����Student������5�У�����count��
        Object[] arow = new Object[fields_super.length+fields.length];
        int i=0;
        for(i=0; i<fields_super.length; i++)
        {
            try
            {
            	arow[i] = fields_super[i].get(obj);
            }
            catch(IllegalAccessException ex)               //��Ч��ȡ�쳣
            {
                break;
            } 
        }
        for(int j=0; j<fields.length; j++)
        {
            try
            {
                arow[i++] = fields[j].get(obj);            //���obj����ʵ����fields[i]��Ա����ֵ
            }
            catch(IllegalAccessException ex)               //��Ч��ȡ�쳣
            {
                break;
            } 
        }
        return arow;
    }
	
	
    //����3.6Ӧ�ñ�����ͬ������������飬��������������Object[]�����������ж������顣
    //��������arow����ʾһ����������ɳ�Ա����ֵ
    public static void print(Object[] arow)
    {
//        for (Object obj:arow)                  //��Ԫѭ��������
    	System.out.print("(");
    	if(arow.length>=0)
            System.out.print(arow[0]==null?"null":arow[0].toString());
    	for(int i=1; i<arow.length; i++)
            System.out.print(", "+(arow[i]==null?"null":arow[i].toString()));
    	System.out.println(")");
    }

    //���obj����ʵ�������г�Ա�������ַ�����ͨ�÷���
    public static void printField(Object obj)
    {
        Class<?> c = obj.getClass();             //����obj����ʵ����������
        Field[] fields = c.getFields();
            //���obj����ʵ������������й��г�Ա�������������������ģ������Ա������ǰ�������Ա�ں�
//      Field[] fields = c.getDeclaredFields();  //������������ĳ�Ա����������������������

        System.out.println(obj.getClass().getName()+"���������³�Ա������");
        for(Field f : fields)                   //��Ԫѭ����f���fields������ÿ��Ԫ��
        	System.out.println("��Ա��������"+f.getName()+"��"+
        			           "���ͣ�"+f.getType()+"��"+
        			           "������ʽ��"+f.toGenericString());
//        			           f.getDeclaringClass()+"��");  //class Person
        System.out.println();
    }
    
    //���obj����ʵ�������г�Ա����ֵ
    public static void printValue(Object obj)
    {
        Field[] fields = obj.getClass().getFields();       //���obj����ʵ������������й��г�Ա����
        if(fields.length>0)
        {        	
            try
            {
                System.out.print("("+fields[0].get(obj)); //���obj����ʵ����fields[i]��Ա����ֵ
            }
            catch(IllegalAccessException ex) {}            //��Ч��ȡ�쳣
            
            for(int i=1; i<fields.length; i++)
            {
                try
                {
                    System.out.print("��"+fields[i].get(obj));
                }
                catch(IllegalAccessException ex)          //��Ч��ȡ�쳣
                {
                    break;
                }
            }
            System.out.println(")");
        }
    }    
}
//@author��Yeheya��2018��2��2��