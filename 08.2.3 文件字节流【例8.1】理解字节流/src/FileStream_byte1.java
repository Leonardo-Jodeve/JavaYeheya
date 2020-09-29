//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月23日
//§8.2.3  文件字节流
//【例8.1】  理解与使用字节流。
//（1）从字节流中按字节读/写，理解字节流。

import java.io.*;
public class FileStream_byte1 
{
    public static void main(String[] args) throws IOException //抛出异常交由Java虚拟机处理
    {
        //以下使用文件字节输出流将多个整数-1写入filename指定文件名的文件
        String filename = "FileStream_byte1.byte";         //指定文件名
        OutputStream out = new FileOutputStream(filename); //创建文件字节输出流，若指定文件不存在，则创建新文件，没有抛出异常
        out.write(-1);                                     //写入整数参数的最低1个字节
        out.write(-1);
        out.close();                                       //关闭文件字节输出流

        //以下使用文件字节输入流从filename指定文件中按字节读取
        InputStream in = new FileInputStream(filename);    //创建文件字节输入流，若指定文件不存在，则抛出java.io.FileNotFoundException异常
        int i;                        //以下filein.read()读1字节作为int整数最低1字节，高位补0
        while ((i=in.read())!=-1)                          //字节流结束返回-1
            System.out.print("  "+i);                      //按int整数输出，255
            //System.out.print("  "+(byte)i);              //按byte整数输出，-1
        in.close();                                        //关闭文件字节输入流
    }
}
/*
程序运行结果如下：
  255  255
*/
//@author：Yeheya，2015-7-15，2017年8月23日