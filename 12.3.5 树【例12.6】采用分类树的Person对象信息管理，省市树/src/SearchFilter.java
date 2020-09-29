//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月9日
//§12.3.4 树
//【例12.6】 Person对象信息管理，以树结构显示中国城市。
//（2）查找条件过滤器接口及实现该接口的类

import java.lang.reflect.Field;                  //反射包的类

//查找条件过滤器接口
public interface SearchFilter<T>
{
    public abstract boolean accept(T obj);       //过滤操作，提供是否接受的过滤条件
}

//Person对象的省份城市过滤器类，实现查找条件过滤器接口，对Person对象按省份、城市过滤
class ProvinceCityFilter implements SearchFilter<Person>
{
    String province, city;                       //省份、城市字符串
    public ProvinceCityFilter(String province, String city)
    {
        this.province = province;
        this.city = city;
    }
    
    //实现过滤操作，若per对象的省市值与province、city指定省份城市字符串匹配，则返回true
    public boolean accept(Person per)
    {
        return (province.equals("") || per.province.equals(province) && (city.equals("") ||
                per.city.equals(city)));         //""表示忽略条件，意为全部
    }
}

//对象的成员变量过滤器类，实现查找条件过滤器接口，对象按指定成员变量进行过滤
class FieldFilter<T> implements SearchFilter<T>
{
    Field field;                                 //成员变量
    Object keyvalue;                             //field成员变量值
    
    public FieldFilter(T key, String fieldname)  //构造方法，key指定T类对象，fieldname指定T类的成员变量名
    {
        try
        {
            this.field=key.getClass().getField(fieldname); //获得key对象名为fieldname的成员变量Field对象
            this.keyvalue = this.field.get(key);           //获得key对象field成员变量值
        }
        catch(NoSuchFieldException ex){}         //无此成员变量异常
        catch(IllegalAccessException ex){}       //无效存取异常
    }
    
    //实现过滤操作，若obj对象的field成员变量值与keyvalue匹配，则返回true。
    //当keyvalue为null或""时，忽略条件，意为全部
    public boolean accept(T obj)
    {
        try
        {
            return keyvalue==null || keyvalue.equals(field.get(obj));
        }
        catch(IllegalAccessException ex){}       //无效存取异常
        return false;
    }
}

//以下第5版没写
//??多列过滤器??
class FieldsFilter<T> implements SearchFilter<T>           //T类指定成员变量的过滤器
{
    Field field1, field2;                                  //成员变量
    Object keyvalue1,keyvalue2;                            //field成员变量值
    
    public FieldsFilter(T key, String fieldname1, String fieldname2)            //构造方法，key指定T类对象，fieldname指定T类的成员变量名
    {
        if(key!=null && fieldname1!=null)
        {
            try
            {
                this.field1=key.getClass().getField(fieldname1); //获得key对象名为fieldname的成员变量Field对象
                this.keyvalue1 = this.field1.get(key);           //获得key对象field成员变量值
                this.field2=key.getClass().getField(fieldname2); 
                if(key!=null)
                    this.keyvalue2 = this.field2.get(key); //获得key对象field成员变量值
            }
            catch(NoSuchFieldException ex){}               //无此成员变量异常
            catch(IllegalAccessException ex){}             //无效存取异常
        }
    }
    
    //实现过滤操作，若t对象的field成员变量值与keyvalue匹配，则返回true。
    //实现过滤操作，若p对象的省市值与province、city指定省份城市字符串匹配，则返回true
    //当keyvalue为null或""时，忽略条件，意为全部
    public boolean accept(T t)
    {
        try
        {
            return (keyvalue1==null || keyvalue1.equals(field1.get(t))) &&
                   (keyvalue2==null || keyvalue2.equals(field2.get(t)));   //""表示忽略条件，意为全部
        }
        catch(IllegalAccessException e){}                  //无效存取异常
        return false;
    }
}
//@author：Yeheya，2018年2月9日