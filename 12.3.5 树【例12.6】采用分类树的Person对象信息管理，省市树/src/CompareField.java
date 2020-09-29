//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��9��
//��12.3.4 ��
//����12.6�� Person������Ϣ���������ṹ��ʾ�й����С�
//��3�� �������ָ����Ա��������

import java.lang.reflect.Field;
import java.util.Comparator;

//��T�����fieldname��Ա�����Ƚ϶����С�ıȽ����ࡣĬ��fieldnameʵ��Comparable�ӿ�
public class CompareField<T> implements Comparator<T>
{
    String fieldname;                                      //��Ա������

    public CompareField(String fieldname)                  //���췽����fieldnameָ��T��ĳ�Ա������
    {
        this.fieldname = fieldname;
    }
    
    public int compare(T t1, T t2)  //�Ƚ�t1��t2�����С��������fieldname��Ա����ֵ�Ƚϴ�С
    {
        try
        {
            Field field=t1.getClass().getField(fieldname); //���fieldnameָ���ĳ�Ա����
            return ((Comparable)field.get(t1)).compareTo((Comparable)field.get(t2));//�Ƚ���ֵ��С
        }
        catch(NoSuchFieldException ex){}                  //�޴˳�Ա�����쳣
        catch(IllegalAccessException ex){}                //��Ч��ȡ�쳣
        return -1;                                         //û��ִ�е������﷨����
    }
}
//@author��Yeheya��2018��2��9��