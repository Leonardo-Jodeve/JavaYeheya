//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2017年11月28日
//§8.4   文件操作
//【例8.7】  文本文件编辑器和文件管理器。

import java.io.*;
import javax.swing.JOptionPane;

//提供文件复制等操作的通用方法，使用字节流，适用于任意类型文件
public class FileStream
{
	//以下第5版【例8.7】文件管理器。调用
	//将file1文件内容复制到file2文件中，重写方式。
    public static void copy(File file1, File file2)
    {
        try
        {
            InputStream in = new FileInputStream(file1);   //创建文件字节输入流对象
            OutputStream out = new FileOutputStream(file2);//创建文件字节输出流对象
            byte[] buffer = new byte[512];       //字节缓冲区
            int n=0;                             //读取字节数
            while((n=in.read(buffer))!=-1)       //读满字节数组，返回读取字节数，流结束返回-1
                out.write(buffer, 0, n);         //写入buffer数组从0开始的n个元素
            
            in.close();                          //关闭输入流
            out.close();                         //关闭输出流
        }
        catch(FileNotFoundException ex)          //文件不存在异常
        {
            JOptionPane.showMessageDialog(null, file1.getAbsoluteFile()+"\"文件不存在。");
        }
        catch(IOException ex)                    //输入输出异常
        {
            JOptionPane.showMessageDialog(null, "\""+file1.getAbsoluteFile()+"\"文件复制不成功。");
        }
    }    

    //§8.2.2  文件字节流
    //【第4版例8.1】  理解与使用字节流。
    //（3） 文件复制操作，使用字节流的通用算法。
    //将filename1文件内容复制到filename2文件中，重写方式。
    public static void copy(String filename1, String filename2)
    {
        try
        {
        	InputStream in = new FileInputStream(filename1);    //创建文件字节输入流对象
            OutputStream out = new FileOutputStream(filename2); //创建文件字节输出流对象
            byte[] buffer = new byte[512];       //字节缓冲区
            int n=0;                             //读取字节数
            while((n=in.read(buffer))!=-1)       //读满字节数组，返回读取字节数，流结束返回-1
                out.write(buffer, 0, n);         //写入buffer数组从0开始的n个元素
            
            in.close();                          //关闭输入流
            out.close();                         //关闭输出流
        }
        catch(FileNotFoundException ex)          //指定文件不存在
        {
            JOptionPane.showMessageDialog(null, "\""+filename1+"\"文件不存在。");
        }
        catch(IOException ex)                    //IO异常
        {
            JOptionPane.showMessageDialog(null, "IO异常，复制\""+filename1+"\"文件未成功");
        }
    }    
}
//@author：Yeheya，2015-7-15，2017年8月23日，2018年11月3日