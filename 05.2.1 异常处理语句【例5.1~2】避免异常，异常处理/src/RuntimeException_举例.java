//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月25日
//§5.1.2 错误和异常
//3. RuntimeException运行异常类

public class RuntimeException_举例
{
    public static void main(String[] args) 
    {
        int[] a = null;
        a[0] = 0;                           //空对象异常NullPointerException
        
        String str = null;
        System.out.println(str.length());   //空对象异常NullPointerException
        
        int[] b = new int[-1];              //负数组长度异常NegativeArraySizeException
        
        Object obj = new Object();
//        String str = (String) obj;          //类型强制转换异常ClassCastException
    }
}
//@author：Yeheya，2016-8-25