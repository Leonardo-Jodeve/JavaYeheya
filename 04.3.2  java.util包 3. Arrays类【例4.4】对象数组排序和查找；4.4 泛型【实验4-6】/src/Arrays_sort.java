//《数据结构（Java版）（第4版）试题库》，作者：叶核亚，2014年8月22日，JDK 8.25
//10.1.2   Arrays数组类
// Arrays排序和查找算法。
//8.2 二分法查找；用

//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月22日
//§4.3.2  java.util包
//3. Arrays类
//对象数组排序和查找，测试

import java.util.Arrays;

public class Arrays_sort 
{
    //产生n个随机数（可重复），范围是0～size-1，返回整数对象数组
    public static Integer[] randomInteger(int n, int size)
    {
        Integer[] values = new Integer[n];       //java.lang.Integer是int类型的包装类
        for(int i=0; i<values.length; i++)       //遍历数组，访问每个元素仅一次
//            values[i] = new Integer((int)(Math.random()*100));
            values[i] = (int)(Math.random()*size);   //Java自动将int整数封装成Integer对象，赋值相容
                        //java.lang.Math.random()方法产生一个0～1之间double类型的随机数
        return values;                                     //返回数组引用
    } 
    
    //输出对象数组，不输出空对象的数组元素；方法参数类型是Object[]，适用于所有对象数组
    //形式为“{,}”
    public static void print(Object[] value)
    {
        System.out.print("{");        
        if(value!=null && value.length>0 && value[0]!=null)
        {
            System.out.print(value[0]);        
            for(int i=1; i<value.length; i++)
                if(value[i]!=null)                         //value[i]可引用任何实例
                    System.out.print(","+value[i].toString());//toString()方法运行时多态
        }
        System.out.println("}");        
    }
    
    public static void main(String args[])
    {  
        //Integer整数对象数组排序和二分法查找
        int n=9, size=100;
//        Integer[] value1 = {5,4,3,2,1};
        Integer[] value1 = randomInteger(n, size);    //返回整数对象随机数数组
        System.out.print("随机数序列： ");  print(value1);//输出对象数组       
//        java.util.Arrays.sort(value1);                //整数对象数组排序（升序）
        CompareArray.sort(value1);
        System.out.print("排序序列（升序）： ");   print(value1);   
        CompareArray.sort(value1,false);
        System.out.print("value2排序序列（降序）： ");   print(value1);   
 
        int[] keys={value1[0],value1[value1.length/2],value1[value1.length-1],-1,50,100};
        for(int i=0; i<keys.length; i++)
        {
            int find=java.util.Arrays.binarySearch(value1, keys[i]);//整数对象数组二分法查找
            System.out.println("二分法查找"+keys[i]+"，结果是"+find+"，查找"+
                (find>=0 && find<value1.length?"":"不")+"成功");
        }
        
        //String对象数组排序（升序） 
        String[] value2 = {"e","d","c","b","a"};
        System.out.print("value2： ");  print(value2);
        java.util.Arrays.sort(value2);                //String对象数组排序（升序） 
        System.out.print("value2排序序列（升序）： ");   print(value2);   
        CompareArray.sort(value2,false);
        System.out.print("value2排序序列（降序）： ");   print(value2);   
    }
}
/* 
程序运行结果如下：
随机数序列： {90,21,82,51,7,53,79,86,26}
排序序列（升序）： {7,21,26,51,53,79,82,86,90}
value2排序序列（降序）： {90,86,82,79,53,51,26,21,7}
二分法查找90，结果是-10，查找不成功
二分法查找53，结果是4，查找成功
二分法查找7，结果是-1，查找不成功
二分法查找-1，结果是-1，查找不成功
二分法查找50，结果是-1，查找不成功
二分法查找100，结果是-10，查找不成功
value2： {e,d,c,b,a}
value2排序序列（升序）： {a,b,c,d,e}
value2排序序列（降序）： {e,d,c,b,a}


随机数序列： {10,93,34,86,59,20,38,77,91,72}
排序序列： {10,20,34,38,59,72,77,86,91,93}
二分法查找10，结果是0，查找成功
二分法查找72，结果是5，查找成功
二分法查找93，结果是9，查找成功
二分法查找-1，结果是-1，查找不成功
二分法查找50，结果是-5，查找不成功
二分法查找100，结果是-11，查找不成功
value2： {e,d,c,b,a}
value2排序序列： {a,b,c,d,e}

随机数序列： {9,33,70,72,76,22,27,69,57,77}
排序序列： {9,22,27,33,57,69,70,72,76,77}
二分法查找9，结果是0，查找成功
二分法查找69，结果是5，查找成功
二分法查找77，结果是9，查找成功
二分法查找-1，结果是-1，查找不成功
二分法查找50，结果是-5，查找不成功
二分法查找100，结果是-11，查找不成功

随机数序列： {5,73,59,49,78,18,92,57,76}
排序序列： {5,18,49,57,59,73,76,78,92}
二分法查找5，结果是0，查找成功
二分法查找59，结果是4，查找成功
二分法查找92，结果是8，查找成功
二分法查找-1，结果是-1，查找不成功
二分法查找50，结果是-4，查找不成功
二分法查找100，结果是-10，查找不成功

*/
//@author：Yeheya。2014-8-22