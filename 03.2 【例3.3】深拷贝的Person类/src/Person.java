//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月8日
//§3.2   类的封装性
//§3.2.5 浅拷贝与深拷贝
//【例3.3】  Person类，使用对象作为成员变量并实现深拷贝。
//② 深拷贝的Person类
//MyEclipse设置编译路径包含项目：例3.2（MyDate）。
//【思考题3-4】增加成员变量：性别、省份、城市。【例6.4】用  
//4.3.1 实现可比较接口，//序列化，例8.3

import java.lang.reflect.Field;

//表达人员共同属性的类
public class Person implements Comparable<Person>, java.io.Serializable
{
    public String name;                                    //姓名，实例成员变量，保护成员
    public MyDate birthdate;                               //出生日期，MyDate类见例3.2
    public String gender, province, city;                  //性别、省份、城市
    private static int count=0;                            //静态成员变量，本类及子类实例计数
    
    public Person(String name, MyDate birthdate, String gender, String province, String city) //构造方法
    {
        super();                                           //默认调用Object()
        this.set(name, birthdate, gender, province, city); //调用本类声明的成员方法
        count++;                                           //Person.count
    } 
    public Person(String name, MyDate birthdate)           //构造方法，重载
    {
        this(name, birthdate, "", "", "");                 //调用本类已声明的构造方法
    } 
    public Person()                                        //构造方法，重载
    {
        this("", new MyDate());
//        System.out.println("执行Person()构造方法");
    }
    public Person(Person per)                              //拷贝构造方法，重载，复制对象
    {
        //深拷贝，创建日期实例，图3.5(c)
        this(per.name, new MyDate(per.birthdate), per.gender, per.province, per.city);
    }

    public void finalize()                                 //析构方法，不能抛出异常
    {
        System.out.println("释放对象 ("+this.toString()+")");
        Person.count--;
    }

    //显示对象数，静态成员方法；只能访问静态成员变量，不能访问实例成员，也不能使用this
    public static void howMany()
    {
        System.out.print(Person.count+"个Person对象，");
    }
    
    //设置各属性值，各成员变量不是空对象，字符串初始值初值为空串，默认日期
    public void set(String name, MyDate birthdate, String gender, String province, String city)
    {
        this.name = name==null?"":name;          //将空对象转换成空串，避免equals()抛出空对象异常
        this.birthdate = birthdate;              //引用赋值，不用深拷贝，可修改；若null，方法抛出空对象异常
//        this.birthdate = birthdate==null?new MyDate():birthdate;  //不行，改变日期值，计算年龄结果不对，逻辑错误
        this.gender = gender==null?"":gender;
        this.province = province==null?"":province;
        this.city = city==null?"":city;
    }
    public void set(String name, MyDate birthdate)//设置属性，其他成员变量取默认值，重载
    {
        this.set(name, birthdate, "", "", "");
    }
    //描述对象字符串，成员变量之间以逗号","分隔。
    public String toString()                     //覆盖Object类的toString()方法
    {
        return this.name+","+(this.birthdate==null?"":birthdate.toString())+","+
               this.gender+","+this.province+","+this.city;  //""时多个","表示空串

/*        //若String类型成员变量值为""，则不输出，且不写多个","
        return this.name+(this.birthdate==null?"":","+birthdate.toString())+
            (this.gender.equals("")?"":","+gender)+(this.province.equals("")?"":","+province)+
            (this.city.equals("")?"":","+city);
            //若this.name==null，输出"null"，Java的字符串功能；此处用于提示没有人名。//不行，抛出空对象异常
            //若this.birthdate==null，Java抛出空对象异常，避免，且不输出，不写多余","
             */
    }   
    //以上【例3.3】  Person类，深拷贝。    
    
    //【思考题3-4】
    public int getAge(int year)                  //返回当前对象在year年份的年龄
    {
        return year - this.birthdate.getYear();  //year指参数
    }
    public int getAge()                          //返回当前对象今年的年龄，重载
    {
        return getAge(MyDate.getThisYear());     //MyDate.getThisYear()返回今年的年份
    }
    public int olderThen(Person per)             //返回this与per对象出生年份的差值
    {
        return this.birthdate.getYear() - per.birthdate.getYear();
        //return this.birthdate.yearsBetween(p.birthdate);
    }
    
    //3.4.2 类型的多态
    //比较当前对象与对象p是否相等，比较两实例的各成员变量是否相等
/*    public boolean equals(Person per)
    {
        System.out.print("执行Person类的equals(Person)方法，");
        return this==per || per!=null && this.name.equals(per.name) && this.birthdate.equals(per.birthdate) && 
               this.gender.equals(per.gender) && this.province.equals(per.province) && this.city.equals(per.city);
               //带短路功能的逻辑运算，按逻辑运算符出现次序进行运算，此处先计算||，再计算&&
//      boolean b=false;
//      return b=this==p || p!=null && this.name.equals(p.name) && this.birthdate.equals(b,p.birthdate);
                                         //逻辑运算，没有短路功能，|优先级低于&，先计算&，再计算|
    }*/
    
    //3.4.4   多态的方法实现
    //比较this与obj对象是否相等，覆盖Object类的方法。
    //当参数obj引用Person实例时，算法逐域比较各成员变量对象值是否相等
    public boolean equals(Object obj)
    {
        System.out.print("执行Person类的equals(Object)方法，");
        if(this==obj)                            //this指代调用当前方法的对象
            return true;
        if(obj instanceof Person)                //当obj引用实例属于Person及其子类（包括Student），obj==null时返回false 
        {
            Person p = (Person)obj;              //类型强制转换，per也引用obj引用的实例
            return this.name.equals(p.name) && this.birthdate.equals(p.birthdate) &&
                this.gender.equals(p.gender) && this.province.equals(p.province) &&
                this.city.equals(p.city);
                //其中，name等执行String类的equals(obj)，birthdate执行MyDate类的equals(obj)
        }
        return false;
    }

    //4.3.1   java.lang包中的基础类库
    //4.4   泛型
    public int compareTo(Person per)               //比较对象大小，实现Comparable接口
    {
//        return this.name.compareTo(per.name);      //比较姓名串大小，调用String类的compareTo()方法
        return this.birthdate.compareTo(per.birthdate);  //比较出生日期大小，调用MyDate类的compareTo()方法
    }
    
    //第5章习题，构造方法，以字符串表示，格式同toString()的字符串描述，成员变量之间以逗号分隔
    //使用String的split()方法
  /*  public Person(String personstr)
    {
        String[] str = personstr.split(",");
        try
        {
            this.set(str[0], new MyDate(str[1]), str[2], str[3], str[4]); //调用本类已声明的构造方法
            count++;
        }
        catch(Exception e){}//NumberFormatException, DateFormatException e)
    }     */
    
    /*
    public Object[] toArray()                              //返回由当前实例的成员变量值组成的对象数组，例9.3
    {
        Object[] arow = new Object[5];
        arow[0] = this.name;
        arow[1] = this.gender;
        arow[2] = this.birthdate;
        arow[3] = this.province;
        arow[4] = this.city;
        return arow;
    }*/
    //第12章 
    //构造方法，以字符串表示，格式同toString()的字符串描述，成员变量之间以逗号分隔
    //使用String的split()方法和Field，
    public Person(String personstr)            
    {
        Field[] fields = Person.class.getFields();//获得类中公有成员变量
        String[] str = personstr.split(",");
        for(int i=0; i<str.length; i++)
            try
            {
                if(fields[i].getType()==String.class)     //成员变量类型是String
                    fields[i].set(this,str[i]);            //将this对象的fields[i]成员变量值设置为str[i]
                else
                    fields[i].set(this,new MyDate(str[1]));
            }
            catch(IllegalAccessException ex){}
            catch(NumberFormatException | DateFormatException ex){}//JDK 8 |
            catch(Exception ex){}
    }
}

/*
程序正确:
    public Person(String str)                        //构造方法，重载，以","分割字符串
    {
        int start = 0;
    	int i = str.indexOf(",",start);                   //串中查找子串，返回 子串序号
        this.name = str.substring(0,i);
    	i = str.indexOf(",",start=i);                   //串中查找子串，返回 子串序号
        this.birthdate = new MyDate(str.substring(start,i));             //创建默认日期实例
    	i = str.indexOf(",",start=i);
        this.gender = str.substring(start,i);
    	i = str.indexOf(",",start=i);
        this.province = str.substring(start,i);
    	i = str.indexOf(",",start=i);
        this.city = str.substring(start,i);
    } 
1、当没有创建任何对象时，输出如下：
Person.count=0


    public void print()
    {
        System.out.println("本类名="+this.getClass().getName()+"  "+
             "超类名="+this.getClass().getSuperclass().getName()+"  ");
    } 
        System.out.println(this.getClass().getName()+"类  "+this.toString());  //可以调用实例方法

程序错误:
        public String str="";                   //编译错，局部变量，不能使用修饰符
        static String str="";                   //局部变量，不能使用修饰符
    
*/
//@author：Yeheya，2016-8-8