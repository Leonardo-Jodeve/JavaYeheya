//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月25日，2017年7月17日
//§5.2 异常处理措施
//【例5.1】  程序应避免的异常分析。
//【例5.2】  数值格式异常处理。
//MyEclipse设置编译路径包含项目：例2.7（IntArray1）。

public class IntArray5                           //一维整数数组，第5章
{
    //【例5.1】改进
    public static double average(int[] value)    //求整数数组元素平均值
    {
        if(value.length==0)                      //避免除数为0错误
            return 0.0;
        double sum=0.0;
        for(int i=0; i<value.length; i++)        //避免数组下标越界异常
            sum += value[i];  
        return sum/value.length;
    }
	
    //【思考题5-1】 实现以下方法。  
    //求value整数数组元素的加权平均值，weight数组元素指定value数组相应元素的权值；
    //若value元素个数为0，返回0.0；
    //若weight为null，默认权值为1，即求value数组元素的平均值；
    //若weight数组元素个数不够，默认权值为1；若weight数组元素个数超过，忽略不用
    public static double weightedAverage(int[] value, int[] weight) 
    {
        if(value.length==0)                      //若value==null，value.length抛出空对象异常
            return 0.0;
//        double sum=0.0;
        int i=0, sum=0;
        String exp="", exps="";                  //记录表达式
        if(weight!=null && weight.length!=0)     //避免weight为null时的空对象异常
        {
            for(i=0; i<value.length && i<weight.length; i++) 
            {
                sum += value[i]*weight[i]; 
                exp += (i==0?"":"+")+value[i]+"*"+weight[i];
             //   exps+= (i==0?"":"+")+value[i]*weight[i];
            }
        }
        for(; i<value.length; i++)               //避免数组下标越界异常
        {
            sum += value[i];  
            exp += (i==0?"":"+")+value[i]+"*1";
            //exps+= (i==0?"":"+")+value[i];
        }
        System.out.print("("+exp+")/"+value.length+" = ");
        return (double)sum/value.length;
    }

    //【例5.2】数值格式异常处理。
    //返回字符串数组str中按十进制转换成的所有整数，忽略不能转换成整数的字符串
    //第6章、例8.2没有调用，例8.2思考题用其含义。
    public static int[] getInts(String[] str)
    {
        if(str==null || str.length==0)
            return null;
        int x[]=new int[str.length],  n=0,i=0;   //x数组长度同str，可能超长
        while(i<str.length)                      //此处可使用for，省略以下finally子句
        {
            try
            {
                x[n]=Integer.parseInt(str[i]);   //按十进制转换成整数，不能转换时抛出异常
                n++;                             //n记录整数个数
            }
            catch(NumberFormatException ex)      //捕获并处理parseInt()抛出的数值格式异常
            {
                System.out.println("\""+str[i]+"\"字符串不能按十进制转换为整数，"+ex.toString());    //包含异常类名的异常信息
            }
            catch(Exception ex)                  //捕获并处理所有其他异常
            {
//            	System.out.println(ex.getMessage());       //只有异常信息
            	System.out.println(ex.toString());         //包含异常类名、产生异常的字符串
//              ex.printStackTrace();	         //包含异常类名、异常信息和异常跟踪信息
                ex.printStackTrace();            //显示异常栈跟踪信息，同Java虚拟机抛出的
            }
            finally                              //最后执行子句，可省略
            {
                i++;
            }
        }
        if(n==x.length)                          //当x数组放满整数时
            return x;
        int[] y = new int[n];                    //当x数组不满时，复制数组，去除最后多余存储单元
        System.arraycopy(x,0,y,0,n);             //将x数组从0开始n个元素复制到y数组从0开始处
        return y;                                //返回包含所获整数个数的数组
    }

    //【思考题5-2】十六进制，加权平均（实验题目或试题，节省篇幅）
    //获得字符串数组str中的整数。先按十进制形式转换成整数；不能转换时，
    //再按十六进制形式转换成整数。try语句嵌套。
    public static int[] toIntArray(String[] str)
    {
        if(str==null || str.length==0)
            return null;
        int x[]=new int[str.length],  n=0,i=0;   //x数组长度同str，可能超长
        while(i<str.length)                      //此处可使用for，省略以下finally子句
        {
            try
            {
                x[n]=Integer.parseInt(str[i]);   //按十进制转换成整数，不能转换时抛出异常
                n++;                             //n记录整数个数
            }
            catch(NumberFormatException ex)      //捕获并处理parseInt()抛出的数值格式异常
            {
                try                              //try语句嵌套
                {
                    x[n++]=Integer.parseInt(str[i],16);   //按十六进制转换成整数
                }
                catch(NumberFormatException nfex)//异常对象名nfex与外层ex不同
                {
//                    System.out.println(str[i]+"字符串不能转换为整数，"+nfex.getClass().getName());//显示异常类名
//                    System.out.println(str[i]+"字符串不能转换为整数，"+nfex.getMessage());//仅异常信息
                    System.out.println("\""+str[i]+"\"字符串不能转换为整数，"+nfex.toString());    //包含异常类名的异常信息
                }
            }
            catch(Exception ex)                  //捕获并处理所有其他异常
            {
                ex.printStackTrace();            //显示异常栈跟踪信息，同Java虚拟机抛出的
            }
            finally                              //最后执行子句，可省略
            {
                i++;
            }
        }
        if (n==x.length)                         //当x数组放满整数时
            return x;
        int[] y = new int[n];                    //当x数组不满时，复制数组，去除最后多余存储单元
        System.arraycopy(x,0,y,0,n);             //将x数组从0开始n个元素复制到y数组从0开始处
        return y;                                //返回包含所获整数个数的数组
    }

    public static void print(int[] value)        //输出数组元素
    {
        for(int i=0; i<value.length; i++)        //若value==null，value.length抛出空对象异常
            System.out.print(value[i]+"  ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        String valuestr[]={"10","20","30","40","50","x","y","60","70"};
        int value[] = getInts(valuestr);         //返回字符串数组valuestr中的所有整数
        System.out.print("value[]数组：");
        IntArray1.print(value);                  //输出数组元素，方法体见例2.7，{,}

/*
        int value[] = toIntArray(valuestr);           //若参数为args，则接受命令行参数
        System.out.print("value[]数组：");
        IntArray1.print(value);                            //输出数组元素，方法体见例2.7，{,}
        String weightstr[]={"a","b","c","d","e","f","g"};
        int weight[] = toIntArray(weightstr);
        System.out.print("weight[]数组：");
        IntArray1.print(weight);
//        System.out.println("weightedAverage(value,wight)="+weightedAverage(value,weight));
        System.out.print("weightedAverage(value,wight)=");
        System.out.println(+weightedAverage(value,weight));
//        System.out.println("weightedAverage(value,null)="+weightedAverage(value,null));
        System.out.print("weightedAverage(value,null)=");
        System.out.println(weightedAverage(value,null));

        int x[] = {1,2,3,4};
        int y[] = {};                                      //y!=null但y.length==0
        System.out.println("average(x,y)="+average(x,y));
        */
    }
    
    //以下第5版教材没写【例5.1】改进
    //求两个数组元素的平均值，当其中一个数组为空，或元素个数为0时，跳过不计。
    public static double average(int[] value1, int[] value2)
    {
        int n = (value1==null ? 0 : value1.length) + (value2==null ? 0 : value2.length); 
        if(n==0)
            return 0.0;
        return (average(value1)*value1.length + average(value2)*value2.length)/n;
    }    
}
/*
程序运行结果如下：
"x"字符串不能按十进制转换为整数，java.lang.NumberFormatException: For input string: "x"
"y"字符串不能按十进制转换为整数，java.lang.NumberFormatException: For input string: "y"
value[]数组：{10,20,30,40,50,60,70}

g字符串不能转换为整数，java.lang.NumberFormatException: For input string: "g"
weight[]数组：{10,11,12,13,14,15} 
weightedAverage(value,wight)=410.0
weightedAverage(value,null)=40.0
average(x,y)=2.5

//【思考题5-2】写出算式
weightedAverage(value,wight)=(10*10+20*11+30*12+40*13+50*14+60*15+70*1)/7 = 410.0
weightedAverage(value,null)=(10*1+20*1+30*1+40*1+50*1+60*1+70*1)/7 = 40.0

*/

//@author：Yeheya，2016-8-25