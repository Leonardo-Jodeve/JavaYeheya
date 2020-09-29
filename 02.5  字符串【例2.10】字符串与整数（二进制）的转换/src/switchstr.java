//Jdk7-7，新增功能，switch(a)常量可用字符串，在JDK环境中运行通过
//在此环境下，编译未通过，因为更新JDK至7，未成功
//2012年12月29日
//《数据结构（Java版）（第5版）》，作者：叶核亚，2017年7月12日

public class switchstr 
{
    public static void main(String args[])
    {
        String s="123";
        switch(s)
        {
            case "123":  System.out.print("can");
        }
//         s.re                //没有逆转方法
    }
}
//@2014年10月26日安装2013版，更新至JDK 8.11 编译通过。
//2017年7月12日，JDK 8.121，MyEclipse 2015 编译通过。