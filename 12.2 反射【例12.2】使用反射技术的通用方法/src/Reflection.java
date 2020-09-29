//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月2日
//§12.2 反射
//【例12.6】Person对象信息管理，以树结构显示中国城市。
//为【例12.6】做准备。

import java.lang.reflect.Field;                  //反射包的类

public class Reflection                          //使用反射技术的通用方法 
{
    //以下3个方法第5版例12.6调用，2018年2月6日。
	
    //返回obj引用实例的所有公有成员变量（已知n个），不包括私有成员变量。
    //调整成员变量次序为父类的在前，子类的在后。
    ////第5版改进，2018年2月11日。本例看不到差别，作用于Student表格，调整次序。
    public static Field[] getFields(Object obj, int n)
    {
        Class<?> c = obj.getClass();             //获得obj引用实例所属的类
        Field[] fields_super = c.getSuperclass().getFields();
            //获得c类的所有公有成员变量，包括父类声明的，子类成员变量在前，父类的在后
        Field[] fields_sub = c.getDeclaredFields();
            //获得c类中声明的成员变量，包含私有成员变量，不包括父类的成员变量
            //对于Person，返回6列，包含count；对于Student，返回5列，包含count。
        Field[] fields = new Field[n];
        int i=0;
        for(i=0; i<fields_super.length; i++)     //合并两个成员变量数组
            fields[i] = fields_super[i];
        for(int j=0; i<n; i++, j++)
            fields[i] = fields_sub[j];
        return fields;
    }	

    //返回fields成员变量数组元素的描述字符串
    public static String[] toString(Field[] fields)
    {
        String[] str = new String[fields.length];
        for(int i=0; i<str.length; i++)
            str[i] = fields[i].getName();        //获得成员变量名字符串
        return str;
    }

    //设obj引用实例的所有成员变量保存在fields数组，
    //返回保存obj引用实例的所有成员变量值的对象数组
    public static Object[] toArray(Object obj, Field[] fields)
    {
        Object[] arow = new Object[fields.length];
        for(int i=0; i<fields.length; i++)
        {
            try
            {
                arow[i] = fields[i].get(obj);    //获得obj引用实例的fields[i]成员变量值
            }
            catch(IllegalAccessException ex)     //无效存取异常
            {
                break;
            }
        }
        return arow;
    }
    
    //《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年7月15日
    //以下例12.6未调用。 
    //【实验6-22】Person对象信息管理，使用表格。调用//【课程设计12-13】 电话簿，调用。
    //获得对象obj引用实例的所有成员值，存储在arow数组中返回
    //Person、Student，获得成员变量的次序不同，在表格中显示的列不同。
    //由于getFields()方法获得obj引用实例所属类的所有公有成员变量，包括父类声明的，子类成员次序在前，父类成员在后，次序不对
    //所以，对于Student而言，先获得父类Person成员变量，再成员变量使用。Student
    public static Object[] toArray(Object obj)
    {
        Field[] fields_super = obj.getClass().getSuperclass().getFields();
            //获得obj引用实例所属类的所有公有成员变量，包括父类声明的，子类成员次序在前，父类成员在后
        Field[] fields = obj.getClass().getDeclaredFields();
            //获得类中声明的成员变量，包含私有成员变量，不包括父类声明的成员
            //对于Person，返回6列，包含count。
            //对于Student，返回5列，包含count。
        Object[] arow = new Object[fields_super.length+fields.length];
        int i=0;
        for(i=0; i<fields_super.length; i++)
        {
            try
            {
            	arow[i] = fields_super[i].get(obj);
            }
            catch(IllegalAccessException ex)               //无效存取异常
            {
                break;
            } 
        }
        for(int j=0; j<fields.length; j++)
        {
            try
            {
                arow[i++] = fields[j].get(obj);            //获得obj引用实例的fields[i]成员变量值
            }
            catch(IllegalAccessException ex)               //无效存取异常
            {
                break;
            } 
        }
        return arow;
    }
	
	
    //与例3.6应用背景不同，输出对象数组，方法参数类型是Object[]，适用于所有对象数组。
    //对象数组arow，表示一个对象的若干成员变量值
    public static void print(Object[] arow)
    {
//        for (Object obj:arow)                  //逐元循环，不行
    	System.out.print("(");
    	if(arow.length>=0)
            System.out.print(arow[0]==null?"null":arow[0].toString());
    	for(int i=1; i<arow.length; i++)
            System.out.print(", "+(arow[i]==null?"null":arow[i].toString()));
    	System.out.println(")");
    }

    //输出obj引用实例的所有成员变量名字符串。通用方法
    public static void printField(Object obj)
    {
        Class<?> c = obj.getClass();             //返回obj引用实例所属的类
        Field[] fields = c.getFields();
            //获得obj引用实例所属类的所有公有成员变量，包括父类声明的，子类成员次序在前，父类成员在后
//      Field[] fields = c.getDeclaredFields();  //获得类中声明的成员变量，不包括父类声明的

        System.out.println(obj.getClass().getName()+"类声明以下成员变量。");
        for(Field f : fields)                   //逐元循环，f获得fields数组中每个元素
        	System.out.println("成员变量名："+f.getName()+"；"+
        			           "类型："+f.getType()+"；"+
        			           "声明形式："+f.toGenericString());
//        			           f.getDeclaringClass()+"，");  //class Person
        System.out.println();
    }
    
    //输出obj引用实例的所有成员变量值
    public static void printValue(Object obj)
    {
        Field[] fields = obj.getClass().getFields();       //获得obj引用实例所属类的所有公有成员变量
        if(fields.length>0)
        {        	
            try
            {
                System.out.print("("+fields[0].get(obj)); //获得obj引用实例的fields[i]成员变量值
            }
            catch(IllegalAccessException ex) {}            //无效存取异常
            
            for(int i=1; i<fields.length; i++)
            {
                try
                {
                    System.out.print("，"+fields[i].get(obj));
                }
                catch(IllegalAccessException ex)          //无效存取异常
                {
                    break;
                }
            }
            System.out.println(")");
        }
    }    
}
//@author：Yeheya，2018年2月2日