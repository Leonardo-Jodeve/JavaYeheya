//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月7日
//§1.1.3 Java核心技术
//1. Application应用
//【例1.1】  接受命令行参数的Application应用程序。

//控制台应用程序，功能是接收命令行参数作为输入数据，逐行输出；若无命令行参数，显示Hello!
public class Hello 
{    
    //main()是类首先执行的方法，参数args是String字符串数组，args接受命令行参数
    public static void main(String[] args)
    {
        if(args.length==0)                       //若args数组长度为0，表示没有命令行参数
            System.out.println("Hello!");        //输出指定字符串
        else 
            for(int i=0; i<args.length; i++)     //循环，i范围是0～数组长度-1 
                System.out.println(args[i]);     //输出每个数组元素（命令行参数字符串）
    }
}
//@author：Yeheya，2016-6-7