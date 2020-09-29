//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月15日
//§3.4  类的多态性
//【例3.6】 对象数组的输出、查找和合并算法。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）、例3.3（Person）和例3.5（Student）。
//【思考题3-6】 ② 增加以下方法，Person类型参数。

public class PersonArray 
{
    public static void main(String[] args) 
    {
        Person p1 = new Person("李小明",new MyDate(1994,3,15));
        Person[] pers={p1, new Student(p1,"","计算机","",false)};
        Student[] stus={new Student("张莉",new MyDate(1998,4,5),"","","","","信息管理","",false),
                        new Student("朱红",new MyDate(1995,3,12),"","","","","通信工程","",false)};

        Object[] objs = ObjectArray.concat(pers, stus);      //返回合并的对象数组，数组元素对象引用
//        Person[] objs = ObjectArray.concat(objs1, objs2);  //编译错，对象数组类型不匹配，不能将Object[]转换成Person[] 
        stus[1].birthdate.set(new MyDate(2001,10,1));//修改出生日期，影响objs数组元素，说明引用关系
        ObjectArray.print(objs);
        
        Person[] keys = {new Person(p1), new Student((Student)pers[1])};  //深拷贝，查找对象
        for(int i=0; i<keys.length; i++)
        {
            System.out.println("查找："+keys[i].toString()+"，结果： ");
            ObjectArray.searchPrintAll(objs, keys[i]);      //查找执行keys[i]所属类（Person、Student）的对象比较规则
        }
        Student.howMany();
       
//      removeAll(objs3, key);
/*
 * //      Object obj = search(objs, key);                   //查找
        System.out.println("查找："+key.toString()+"，结果： ");//+((obj==null) ? "未找到" : obj.toString()));
        System.out.println("平均年龄是"+average(objs)+"岁");*/
    }
    
    
    //【思考题3-6】 ② 增加以下方法，Person类型参数。
    public static Person oldest(Person value[])            //返回年龄最大者，返回对象
	{
	    if(value==null)
	        return null;
	    int max=0;                                         //记载最大值对象的下标
	    for(int i=1; i<value.length; i++)
	        if(value[i].getAge()>value[max].getAge())
	            max = i;
	    return value[max];                                 //返回对象引用
	}

	public static double average(Person value[])           //返回平均年龄
	{
        double sum=0;        
	    for(int i=0; i<value.length; i++)
	        sum += value[i].getAge();                      //double与int运算结果为double
	    return value.length>0 ? sum/value.length: 0;       //给出0个元素的结果，避免除数为0错误
	}
}
/*
程序运行结果如下：
李小明,1994年03月15日,,,
李小明,1994年03月15日,,,,,计算机,
张莉,1998年04月05日,,,,,信息管理,
朱红,2001年10月01日,,,,,通信工程,
查找：李小明,1994年03月15日,,,，结果： 
李小明,1994年03月15日,,,
李小明,1994年03月15日,,,,,计算机,
查找：李小明,1994年03月15日,,,,,计算机,，结果： 
李小明,1994年03月15日,,,,,计算机,
6个Person对象，4个Student对象
*/    
    /*
  //（1）第4版程序未用，数据太多看不清楚
         Person p = new Person("李小明",new MyDate(1994,3,15),"男","湖南","长沙");
         Person pobjs[]={p, new Student(p,"计算机","计算机科学与技术","211994001",true), 
                             new Student(p,"自动化","自动控制","020",true)};
         Student sobjs[]={new Student("张莉",new MyDate(1998,4,5),"女","湖北","武汉","经济管理","信息管理","321998003",true),
                           new Student("朱小红",new MyDate(1995,3,12),"女","广东","广州","通信","通信工程","014",true),
                           new Student("赵泉",new MyDate(1997,10,1),"男","广西","南宁","电力","电气工程","009",false)};

          Person[] key = new Person(p);                        //深拷贝
          System.out.println("查找："+key.toString()+"，结果： ");
          print(objs, key);                                 //查找执行Person对象比较规则

          key = new Student((Student)sobjs[1]);             //key引用子类实例
          System.out.println("查找："+key.toString()+"，结果： ");
          print(objs, key);                                 //查找执行Student对象比较规则
          Student.howMany();

  （2）不讨论Student与父类Person可比
  */
//@author：Yeheya，2018年2月15日，除夕