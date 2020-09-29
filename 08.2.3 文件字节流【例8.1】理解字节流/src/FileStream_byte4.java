//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月23日
//§8.2.3  文件字节流
//【例8.1】  理解与使用字节流。
//（2）从字节流中读写4个字节作为1个int整数，理解字节流

import java.io.*;
public class FileStream_byte4 
{
    public static void main(String[] args) throws IOException
    {
        //方法一，读写4个字节，第4、5版书
        String filename = "FileStream_byte4.int";          //指定文件名
        OutputStream out = new FileOutputStream(filename); //创建文件字节输出流对象
        int value=-128;
        System.out.println(value+"("+MyInteger.toBinaryString(value)+")");
        out.write(value>>>24);                   //int整数右移24位，高位补0；即写入int整数最高位1个字节
        out.write(value>>>16);
        out.write(value>>>8);
        out.write(value);                        //写入整型最低位1个字节
        out.close();                             //关闭文件字节输出流

        InputStream in = new FileInputStream(filename);  //创建文件字节输入流
        while((value=in.read())!=-1)                     //读取1个字节，字节流结束返回-1
        {
            int tmp;
            System.out.print(value+"  ");                  //按int整数输出
            for(int j=0; j<3 && (tmp=in.read())!=-1; j++) //再读取3个字节，拼成一个int整数
            {
            	value = value<<8 | tmp;                    //左移8位，再加入低位1个字节，<<优先级比|高
//            value = (value<<8) + tmp;                    //也可,左移8位，再加入低位1个字节，<<比+优先级低
                System.out.print(value+"  ");              //按int整数输出
            }
        }
        System.out.println(value);        
        in.close();                                        //关闭文件字节输入流

    
        //方法二，读写长度为4的字节数组
//        String filename2 = "fileStream2.int";            //指定文件名
        out = new FileOutputStream(filename,true);         //创建文件字节输出流，若指定文件不存在，则创建新文件，没有抛出异常
        byte[] buffer = new byte[4];                       //int的字节数为4
        value=-129;
        System.out.println(value+"("+MyInteger.toBinaryString(value)+")");
        for(int i=buffer.length-1; i>=0; i--)              //将int整数value拆分成4个字节，保存在buffer字节数组中
        {
            buffer[i] = (byte)value;                       //将value最低1个字节，保存在buffer字节数组中
            value >>>=8;                                   //右移8位，高位0填充
        }
        out.write(buffer);                                 //将buffer字节数组写入字节输出流
        out.close();                                       //关闭文件字节输出流

        in = new FileInputStream(filename);      //创建文件字节输入流，若指定文件不存在，则抛出java.io.FileNotFoundException异常
        int count=0;
        while((count=in.read(buffer))!=-1)       //读满字节数组，返回读取的字节数，流结束返回-1
        {
            value=0;
            for(int i=0; i<buffer.length; i++)             //将int整数value拆分成4个字节，保存在buffer字节数组中   //再读取3个字节，拼成一个int整数
            {
                value = value<<8 | buffer[i];              //左移8位，再加入低位1个字节，<<比|优先级高
                System.out.print(value+"  ");              //按int整数输出
            }
            System.out.println();        
        }
        in.close();                                    //关闭文件字节输入流
        
//    	FileStream.copy(filename, filename+" - 副本");     //第4版复制文件，第5版移动到例8.6
    }
}
/*
程序运行结果如下：
-128(11111111111111111111111110000000)
65535  16777215  -128  
-129(11111111111111111111111101111111)
-1  -1  -1  -128  
-1  -1  -1  -129  

*/
//@author：Yeheya，2015-7-15，2017年8月23日