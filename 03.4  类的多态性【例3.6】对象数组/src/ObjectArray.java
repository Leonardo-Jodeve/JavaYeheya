//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��3.4  ��Ķ�̬��
//����3.6�� �����������������Һͺϲ��㷨��

//Ϊ������������ͨ�÷�����Object[]���Ͳ���
public class ObjectArray
{    
    //����������飬�ն������"null"����������������Object[]�����������ж�������
    public static void print(Object[] objs)
    {
        if(objs!=null)
            for(int i=0; i<objs.length; i++)
                System.out.println(objs[i]==null?"null":objs[i].toString());
                    //objs[i]�������κ�ʵ����objs[i].toString()��������ʱ��̬
        
        for(Object obj:objs)          //��Ԫѭ����obj����objs�����е�ÿ��Ԫ��
            if (obj!=null)             //obj�������κ�ʵ��
                System.out.println(obj.toString());//toString()��������ʱ��̬
    }
//��:��֮���?  for (int i=0; i<objs.length && objs[i]!=null; i++)
    
    //��1�������û���������ʱ��̬���������ȷ
    public static void print1(Object objs[])    //�����������
    {
        for(int i=0; i<objs.length; i++)
            if(objs[i] instanceof Person)                //Person��Student����
                if(!(objs[i] instanceof Student))        //���ָ��������������Person����Student����
                    System.out.println(((Person)objs[i]).toString()); //����ǿ������ת����û�е�����������Ͳ���
                else
                    System.out.println(((Student)objs[i]).toString());   
    }

    //���غϲ��Ķ������飬ͨ�ù���
    public static Object[] concat(Object[] objs1, Object[] objs2)
    {
        if(objs1==null)  return objs2;
        if(objs2==null)  return objs1;
        Object[] result = new Object[objs1.length+objs2.length]; 
        int i=0, j=0;
        for(j=0; j<objs1.length; j++)
            result[i++] = objs1[j];             //�������ø�ֵ
        for(j=0; j<objs2.length; j++)
            result[i++] = objs2[j];
        return result;                           //���ض�����������
    }

    //���objs����������������key��ȵ�Ԫ�أ�˳������㷨��equals(Object)����Ӧ��
    public static void searchPrintAll(Object[] objs, Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(objs[i]!=null && key.equals(objs[i]))   //equals()��������ʱ��̬��ִ��key����ıȽϹ���
//                  if (objs[i].equals(key))                  //equals()��������ʱ��̬��ִ��objs[i]�Ķ���ȽϹ���
                    System.out.println(objs[i].toString()); //toString()��������ʱ��̬
    }
    
    //��˼����3-6�� �� �������·�����Object���Ͳ�����
    public static Object[] copy(Object[] objs)  //���ظ��ƵĶ������飬ͨ�ù��ܣ�û�и��ƶ����ò���
    {
        if(objs==null)  return null;
        Object[] result = new Object[objs.length]; 
        for(int i=0; i<objs.length; i++)
            result[i] = objs[i];                //�������ø�ֵ
        return result;                           //���ض�����������
    }
    
    
    //˳����Ҷ����������״γ��ֵ���key���Ԫ�أ������ҳɹ�����Ԫ�أ����򷵻�null
    public static Object search(Object objs[], Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(key.equals(objs[i]))        //�������equals()�����Ƚ��Ƿ���ȣ�����ʱ��̬
                    return objs[i];
        return null;                             //���Ҳ��ɹ�
    }
    
    //��˼����3-6����3.6˼�������ӷ�����
    //ɾ��objs����������������keyƥ�����ʹ��˳������㷨��equals()����Ӧ��
    public static void removeAll(Object objs[], Object key)
    {
        if(objs!=null && key!=null)
            for(int i=0; i<objs.length; i++)
                if(key.equals(objs[i]))        //equals()��������ʱ��̬��ִ��key�Ķ���ȽϹ���
//                  if (objs[i].equals(key))    //equals()��������ʱ��̬��ִ��objs[i]�Ķ���ȽϹ���
                {
                    System.out.println("ɾ����"+objs[i].toString()); //toString()��������ʱ��̬
                    objs[i]=null;
                }        
    }
}
//@author��Yeheya��2016-8-14��2017��7��26��