//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年7月23日
//§2.5 字符串
//【例2.10】字符串与整数（二进制）的转换。
//（2） 按radix进制将整数字符串转换成整数

public class MyInteger implements Comparable<MyInteger>
{
    public static final int MIN_VALUE=0x80000000;//最小值常量，-2147483648
    public static final int MAX_VALUE=0x7fffffff;//最大值常量，2147483647
    private final int value;                     //私有最终变量

    public MyInteger(int value)                  //构造整数对象，取值为value
    {
        this.value = value;
    }    
    //由字符串str表示的十进制整数值构造整数对象。若str不能转换成整数，则抛出数值格式异常
    public MyInteger(String str) throws NumberFormatException
    {
        this.value = MyInteger.parseInt(str);
    }
    
    public int intValue()                        //返回当前对象中的整数值
    {
        return this.value;
    }

    public String toString()                     //返回当前整数的十进制字符串。覆盖Object类方法
    {
        return this.value+"";
    }
        
    //【例2.10】（1） 按radix进制将整数字符串转换成整数
    //返回将字符串str转换的整数，自动识别整数的十、八、十六进制（分别以正负号及1～9、0、0x开头）。
    //若str不能转换成整数，则抛出数值格式异常。
    //特别说明：本方法与java.lang.Integer类中的同名方法功能不同。
    public static int parseInt(String str) throws NumberFormatException
    {
        if(str==null || str.isEmpty() || str.equals("0x") || str.equals("0X"))
            throw new NumberFormatException("\""+str+"\"，不能转换成整数");
  
        char ch=str.charAt(0);                        //获得首字符，识别进制。若str==null，调用方法时Java将抛出空对象异常        
        int value=0, i=0, sign=1, radix=10;
        if(ch>='1' && ch<='9' || ch=='+' || ch=='-')  //十进制以正负号及1～9开头，只有十进制可输入正负号
        {   
            if (ch=='+' || ch=='-')                   //跳过正负号
                i++;                                  //i记住当前字符序号
            sign = ch=='-' ? -1 : 1;                  //识别正负号，记住正负数标记 
        }
        else if(ch=='0')               //八进制以0开头。"0"认为是八进制，结果是0
        {
            radix=8;
            i++;
        	//十六进制以0x或0X开头。ch=…赋值运算的结果值为变量值
            if(i<str.length() && ((ch=str.charAt(i))=='x' || ch=='X'))
            {   
                radix=16;
                i++;
            }
        }
        else
            throw new NumberFormatException("\""+str+"\"，"+radix+"进制整数不能识别'"+ch+"\'字符");
        
        while(i<str.length())                              //获得无符号整数绝对值
        {
            ch = str.charAt(i++);
            if(ch>='0' && ch-'0'<radix)                    //当radix≤10时，radix进制只要识别数字0～radix-1
                value = value*radix+ch-'0';                //value记住当前获得的整数值
            else if(radix==16 && ch>='a' && ch<='f')
                value = value*radix+ch-'a'+10;             //十六进制还需要转换'a'～'f'表示的整数值
            else if(radix==16 && ch>='A' && ch<='F')
                value = value*radix+ch-'A'+10;
            else
                throw new NumberFormatException("\""+str+"\"，"+radix+"进制整数不能识别'"+ch+"\'字符");
        }
        return value*sign;                                 //返回有符号整数值
    }   
    
    //【例2.10】（2） 将整数转换成radix进制补码字符串
    //返回整数value的radix进制补码字符串，正数或0的高位以0填满至32个二进制位；
    //radix取值为2、4、8、10、16；采用位运算；采用字符数组存储结果字符串。
    //特别说明：本方法与java.lang.Integer类中的同名方法功能相同，结果不同。
    //结果不同有二，一是高位补满0；二是八、十六进制增加前缀。
    public static String toString(int value, int radix)
    {
        if(radix==10) 
            return value+"";                               //返回将value值转换成的十进制字符串，“+”运算功能
        if(radix==2 || radix==4 || radix==8 || radix==16)
        {
            int mask, n=0;                                 //mask获得radix进制的最大数字
            for(mask=radix-1; mask>0; mask>>>=1) 
                n++;                                       //n获得mask的二进制位数，即2的n次方是radix
            mask=radix-1;
            char[] buffer= new char[(int)(32.0/n+0.5)];    //存储一个int表示为radix进制的各位
            for(int i=buffer.length-1; i>=0; i--)         //除radix取余法，余数存入buffer字符数组（逆序），高位补0
            {
                int bit = value & mask;                    //获得radix进制的个位数字
                buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'a'-10); //将0～9、10～15转换为'0'～'9'、'a'～'f'
                value>>>=n;                                //右移n位，高位补0，即value除以radix
            }
//            if(radix==2 || radix==4)                       //二、四进制没有前缀
                return new String(buffer);                 //返回由字符数组构造的字符串
//            if(radix==8)
//                return "0"+new String(buffer);             //返回字符串，八进制前缀是"0"
//            return "0x"+new String(buffer);                //返回字符串，十六进制前缀是"0x"
        }
        throw new IllegalArgumentException("radix参数值"+radix+"表示的进制无效。"); //无效参数异常
    }  
    
    public boolean equals(Object obj)                      //比较两个对象是否相等。覆盖Object类方法
    {
        return obj instanceof Integer && this.value==((Integer)obj).intValue();
    }
    
    public int compareTo(MyInteger iobj)                   //比较两个对象值大小，返回-1、0或1
    {
        return this.value<iobj.value? -1 : (this.value==iobj.value ? 0 : 1);
    }
    
    //【试2.26】
    //返回将字符串s按radix进制转换的整数，radix取值为2、4、8、10、16。若s不能转换成整数，则抛出数值格式异常
    //算法首先识别符号位，再逐位识别radix进制数字，通过循环按幂的展开式获得其整数值
    public static int parseInt(String s, int radix) throws NumberFormatException
    {
        if (s==null) 
            throw new NumberFormatException("null");       //抛出数值格式异常
        if (!(radix==2 || radix==4 || radix==8 || radix==10 || radix==16))
            throw new NumberFormatException("该方法不支持"+radix+"进制。");
        int value=0, i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //符号位，记住正负数标记 
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //跳过正负号
            if (radix==10)                                 //只有十进制需要输入正负号
                i++;                                       //i记住当前字符序号
            else throw new NumberFormatException(radix+"进制整数不能识别"+s.charAt(0));
        
        while (i<s.length())                               //获得无符号整数绝对值
        {
            char ch=s.charAt(i++);
            if (ch>='0' && ch-'0'<radix)                   //当radix≤10时，radix进制只要识别0～radix-1数字
                value = value*radix+ch-'0';                //value记住当前获得的整数值
            else if (radix==16 && ch>='a' && ch<='f')
                value = value*radix+ch-'a'+10;             //十六进制还需要转换'a'～'f'表示的整数值
            else if (radix==16 && ch>='A' && ch<='F')
                value = value*radix+ch-'A'+10;
            else throw new NumberFormatException(radix+"进制整数不能识别"+ch);
        }
        return value*sign;                                 //返回有符号整数值
    }
    
    public static int parseInt10(String s)       //返回十进制整数字符串s表示的整数值，未处理异常
    {
        int x=0, i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //符号位，记住正负数标记 
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //跳过符号位
            i++;                                           //i记住当前字符序号
        while (i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9')
            x = x*10+s.charAt(i++)-'0';                    //x记住当前获得的整数值
        return x*sign;                                     //返回整数值，忽略其后的字符
    }
    
    //【习2.4】 整数的二进制问题讨论。（第5版习题解答未写）
    public static String toBinaryString(int value)         //返回整数value的二进制字符串。采用位运算，高位补0
    {
        char[] buffer = new char[32];                      //一个int有32个二进制位
        for (int i=buffer.length-1; i>=0; i--)             //循环执行32次，高位补0
        {
            buffer[i] = (char)((value & 1)+'0');           //获得个位字符存入数组。&运算符优先级低于+
            value>>>=1;                                    //value右移一位，高位以0填充，即value除以2
        }
        return new String(buffer);                         //返回由字符数组构造的字符串
    }
    
    public static String toOctalString(int value)          //返回整数value的八进制字符串。采用位运算，高位补0
    {
        char[] buffer = new char[32/3+1];                  //一个int有11个八进制位
        for (int i=buffer.length-1; i>=0; i--)             //循环执行11次，高位补0
        {
            buffer[i] = (char)((value & 7)+'0');           //获得个位字符存入数组。&运算符优先级低于+
            value>>>=3;                                    //右移3位，高位以0填充，即value除以8
        }
        return new String(buffer);                         //返回由字符数组构造的字符串
    }

    public static String toHexString(int value)            //返回整数value的十六进制字符串。采用位运算，高位补0
    {
        char[] buffer = new char[32/4];                    //一个int有8个十六进制位
        for (int i=buffer.length-1; i>=0; i--)             //循环执行8次，高位补0
        {
            int bit = value & 15;                          //获得十六进制的个位
            buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'a'-10);   //将0～9、10～15转换为'0'～'9'、'a'～'f'
            value>>>=4;                                    //右移4位，高位以0填充，即value除以16
        }
        return new String(buffer);                         //返回由字符数组构造的字符串
    }
    
    //【实验6.12】   整数的数据类型及多种进制相互转换。
    //返回整数value的radix进制字符串，radix取值为2、4、8、10、16。采用位运算
    //size指定字节数
    public static String toString(int value, int radix, int size)
    {
        if (radix==10) 
            return value+"";
        if (radix==2 || radix==4 || radix==8 || radix==16)
        {
            int mask, n=0;                                 //mask获得radix进制的最大数字
            for (mask=radix-1; mask>0; mask>>>=1) 
                n++;                                       //n获得mask的二进制位数，即2的n次方是radix
            mask=radix-1;
            switch (size)
            {
                case 1: value &= 0x000000ff;
                case 2: value &= 0x0000ffff;
            }
            char[] buffer = new char[(int)(size*8.0/n+0.7)];//存储一个int表示为radix进制的各位
            for (int i=buffer.length-1; i>=0; i--)         //除radix取余法，余数存入buffer字符数组（逆序），高位补0
            {
                int bit = value & mask;                    //获得radix进制的个位数字
                buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'A'-10); //将0～9、10～15转换为'0'～'9'、'A'～'F'
                value>>>=n;                                //右移n位，高位补0，即value除以radix
            }
            return new String(buffer);                     //返回由字符数组构造的字符串
        }
        throw new IllegalArgumentException("radix参数值"+radix+"表示的进制无效。"); //无效参数异常
    }  
}  

/*第4版习题解答，第5版习题解答未写
【习2.4】 整数的二进制问题讨论。
     //算法存在问题讨论。
    //① 除2取余法
    public static String toBinaryString(int value)         //返回正整数value的二进制字符串，除2取余法
    {
        String str="";
        while (value>0)                                    //除2取余法，余数存入str字符串（逆序）
        {   str = value%2 + str;                           //value%2+str 即 (char)(value%2+'0')+str
            value /= 2;
        }
        return str;  
    }
    //上述算法仅当value>0时执行，当value≤0时，返回""
    
    public static String toString(int value, int radix)    //返回正整数value的radix进制字符串，2≤radix≤16
    {
        String str="";
        while (value>0)                                    //除radix取余法，余数存入str字符串（逆序）
        {
            int bit = value % radix;
            str = (char)(bit<=9 ? bit+'0' : bit+'A'-10) + str; 
            value /= radix;
        }
        return str;
    }
     //上述算法仅当value>0时执行，当value≤0时，返回""

    public static String toBinaryString(int value)         //返回整数value的二进制字符串，除2取余法
    {
        if (value==0) 
            return "0";
        String str="";
        while (value!=0)                                   //除2取余法，余数存入str字符串（逆序）
        {   str = value%2 + str;
            value /= 2;
        }
        return str; 
    }
    //上述算法错误，负数采用补码表示而不是原码。
    //例如，当value=-1时，value%2=-1，str="-1"，value/2=0，循环执行一次，返回"-1"，而不是"-1"的二进制字符串"11111111111111111111111111111111"

    public static String toBinaryString(int value)         //返回整数value的二进制字符串，除2取余法
    {
        if (value==0) 
            return "0";
        String str="";
        while (value!=0)                                   //除2取余法，余数存入str字符串（逆序）
        {   str = (char)(value%2+'0') + str;
            value /= 2;
        }
        return str; 
    }
    //上述算法错误，value=-1时，value%2结果为-1，(char)(value%2 +'0')结果为'/'（'0'的前一字符），所以不行。

    public static String toString(int value, int radix)    //返回正整数value的radix进制字符串，2≤radix≤16
    {
        String str="";
        while (value>0)                                    //除radix取余法，余数存入str字符串（逆序）
        {
            int bit = value % radix;
            str = (bit<=9 ? bit+'0' : bit+'A'-10) + str; 
            value /= radix;
        }
        return str;
    }
//【答】将数字转换成字符以下，否则会将数字的ASCII码转换成字符串。例如，将15转换成"15"而非"F"。
//str = (char)(bit<=9 ? bit+'0' : bit+'A'-10) + str; 

    //② 位运算
    //以下算法正确。算法采用位运算。
    public static String toBinaryString(int value)         //返回整数value的二进制字符串。采用位运算
    {
        String str="";
        for (int i=0x80000000; i!=0; i>>>=1)               //从高位向低位逐位获取，每右移一位，高位补0
            str += (value & i)==0 ? '0': '1';              //获得各位字符
        return str;
    }
    //该算法将非0值算作1，因此，算法不具有扩展性，不适合8和16进制。

    public static String toBinaryString(int value)         //返回整数value的二进制字符串。采用位运算
    {
        String str="";
        for (int i=0x80000000; i!=0; i>>=1)               //从高位向低位逐位获取，每右移一位，高位补0
            str += (value & i)==0 ? '0': '1';
        return str;
    }
    //错，i>>=1，高位以1填充，i!=0条件永远成立，死循环。
 
    //一个int整数即是一个由32位bit组成的无符号整数，
    //每次获得二进制的个位，将其转换为数字字符，各位数字字符按反序连接成字符串。
    public static String toBinaryString(int value)         //返回整数value的二进制字符串
    {
        String str="";
        for (int i=0; i<32; i++)                           //循环执行32次，高位补0，获得32个二进制位
        {
            str = (value & 1) + str;                       //将各位余数字符按反序连接成字符串。&运算符优先级低于+
            value>>>=1;                                    //value右移一位，高位以0填充，即value除以2
        }
        return str;
    }
    public static String toOctalString(int value)          //返回value整数的八进制字符串。算法采用位运算
    {
        String str="";
        for (int i=0; i<32/3+1; i++)                       //一个int有11个八进制位；循环执行11次，高位补0
        {
            str = (value & 7) + str; 
            value>>>=3;                                    //右移3位，高位以0填充，即value除以8
        }
        return str; 
    }

    public static String toHexString(int value)            //返回value整数的十六进制字符串。算法采用位运算
    {
        String str="";
        for (int i=0; i<32/4; i++)                         //一个int有8个十六进制位；循环执行8次，高位补0
        {
            int bit = value & 15;                          //获得十六进制的个位
            str = (char)(bit<=9 ? bit+'0' : bit+'a'-10) + str; //将0～9、10～15转换为'0'～'9'、'a'～'f'
            value>>>=4;                                    //右移4位，高位以0填充，即value除以16
        }
        return str; 
    }
  
    //以下递归算法
    public static void binary(int n)                       //输出正整数n的二进制字符串，递归算法
    {
        if (n==0)
            return;
        binary(n/2);
        System.out.print((char)(n%2+'0')); 
    }
    
    public static void hex(int n)                          //输出正整数n的十六进制字符串，递归算法
    {
        if (n==0)
        	return;
        hex(n/16);
        n %= 16;
        System.out.print((char)(n<=9 ? n+'0' : n+'A'-10)); 
    }
    
    public static String toBinary(int n)                   //返回正整数n的二进制字符串，递归算法
    {
        if (n>0)
            return toBinary(n/2)+(char)(n%2+'0'); 
        return "";
    }
    
    public static String toHex(int n)                      //返回正整数n的十六进制字符串，递归算法
    {
        int k= n % 16;
        if (n>0)
            return toHex(n/16)+(char)(k<=9 ? k+'0' : k+'A'-10); 
        return "";
    }

 */

//@author：Yeheya，2016-7-23