//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月22日
//§2.4.3 参数传递
//3.  可变形式参数

public class MutableAugument 
{
/*    public static void sum(int[] value)  //不能重载
    {  
    }*/
    public static int sum(int... value)  //可变形参，可将value视为int[]类型
    {
        int s=0;
    	for(int i:value)
            System.out.print(i);
        for(int i=0; i<value.length; i++)
        {
            System.out.print(value[i]);
        }
        return 0;
    }
    public static void main(String args[]) 
    {
        sum(2,3,4,5);
    }
}
/*
2345
2345
*/
//@author：Yeheya，2015-7-15
