//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��9��
//��12.3.4 ��
//����12.6�� Person������Ϣ���������ṹ��ʾ�й����С�
//��2�����������������ӿڼ�ʵ�ָýӿڵ���

import java.lang.reflect.Field;                  //���������

//���������������ӿ�
public interface SearchFilter<T>
{
    public abstract boolean accept(T obj);       //���˲������ṩ�Ƿ���ܵĹ�������
}

//Person�����ʡ�ݳ��й������࣬ʵ�ֲ��������������ӿڣ���Person����ʡ�ݡ����й���
class ProvinceCityFilter implements SearchFilter<Person>
{
    String province, city;                       //ʡ�ݡ������ַ���
    public ProvinceCityFilter(String province, String city)
    {
        this.province = province;
        this.city = city;
    }
    
    //ʵ�ֹ��˲�������per�����ʡ��ֵ��province��cityָ��ʡ�ݳ����ַ���ƥ�䣬�򷵻�true
    public boolean accept(Person per)
    {
        return (province.equals("") || per.province.equals(province) && (city.equals("") ||
                per.city.equals(city)));         //""��ʾ������������Ϊȫ��
    }
}

//����ĳ�Ա�����������࣬ʵ�ֲ��������������ӿڣ�����ָ����Ա�������й���
class FieldFilter<T> implements SearchFilter<T>
{
    Field field;                                 //��Ա����
    Object keyvalue;                             //field��Ա����ֵ
    
    public FieldFilter(T key, String fieldname)  //���췽����keyָ��T�����fieldnameָ��T��ĳ�Ա������
    {
        try
        {
            this.field=key.getClass().getField(fieldname); //���key������Ϊfieldname�ĳ�Ա����Field����
            this.keyvalue = this.field.get(key);           //���key����field��Ա����ֵ
        }
        catch(NoSuchFieldException ex){}         //�޴˳�Ա�����쳣
        catch(IllegalAccessException ex){}       //��Ч��ȡ�쳣
    }
    
    //ʵ�ֹ��˲�������obj�����field��Ա����ֵ��keyvalueƥ�䣬�򷵻�true��
    //��keyvalueΪnull��""ʱ��������������Ϊȫ��
    public boolean accept(T obj)
    {
        try
        {
            return keyvalue==null || keyvalue.equals(field.get(obj));
        }
        catch(IllegalAccessException ex){}       //��Ч��ȡ�쳣
        return false;
    }
}

//���µ�5��ûд
//??���й�����??
class FieldsFilter<T> implements SearchFilter<T>           //T��ָ����Ա�����Ĺ�����
{
    Field field1, field2;                                  //��Ա����
    Object keyvalue1,keyvalue2;                            //field��Ա����ֵ
    
    public FieldsFilter(T key, String fieldname1, String fieldname2)            //���췽����keyָ��T�����fieldnameָ��T��ĳ�Ա������
    {
        if(key!=null && fieldname1!=null)
        {
            try
            {
                this.field1=key.getClass().getField(fieldname1); //���key������Ϊfieldname�ĳ�Ա����Field����
                this.keyvalue1 = this.field1.get(key);           //���key����field��Ա����ֵ
                this.field2=key.getClass().getField(fieldname2); 
                if(key!=null)
                    this.keyvalue2 = this.field2.get(key); //���key����field��Ա����ֵ
            }
            catch(NoSuchFieldException ex){}               //�޴˳�Ա�����쳣
            catch(IllegalAccessException ex){}             //��Ч��ȡ�쳣
        }
    }
    
    //ʵ�ֹ��˲�������t�����field��Ա����ֵ��keyvalueƥ�䣬�򷵻�true��
    //ʵ�ֹ��˲�������p�����ʡ��ֵ��province��cityָ��ʡ�ݳ����ַ���ƥ�䣬�򷵻�true
    //��keyvalueΪnull��""ʱ��������������Ϊȫ��
    public boolean accept(T t)
    {
        try
        {
            return (keyvalue1==null || keyvalue1.equals(field1.get(t))) &&
                   (keyvalue2==null || keyvalue2.equals(field2.get(t)));   //""��ʾ������������Ϊȫ��
        }
        catch(IllegalAccessException e){}                  //��Ч��ȡ�쳣
        return false;
    }
}
//@author��Yeheya��2018��2��9��