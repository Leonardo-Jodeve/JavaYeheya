//【例1.1】 Application应用程序。
//使用命令行参数作为输入数据。

public class Hello
{
    public static void main(String args[])
    {
        if (args.length==0)                                //没有命令行参数时
            System.out.println("Hello!");
        else                                               //显示命令行参数字符串
            for (int i=0; i<args.length; i++)
                System.out.println(args[i]);
    }
}
