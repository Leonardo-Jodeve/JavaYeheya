//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月27日
//§8.3.1  字符流类
//【例8.5】 读写组件文件的通用方法。
//调用者：【例8.7】文本编辑器。

import javax.swing.*;
import java.io.*;

//文本区组件的文本文件类，为文本区组件提供读写文本文件的通用方法
public class JTextAreaText 
{
	//【例8.5】 读写组件文件的通用方法。 
    //将text文本区中的字符串写入filename指定文本文件中
    public static void writeTo(String filename, JTextArea text)
    {
        try
        {
            Writer wr = new FileWriter(filename);//文件字符输出流
            wr.write(text.getText());            //写入文本区中的字符串
            wr.close();
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    }
    
    //若filename指定文件名的文件存在，先删除text文本区中所有字符串；再将从文本文件中
    //读取的每行字符串添加到text文本区。若文件不存在，则弹出对话框告知。
    //使用缓冲字符输入流，逐行读取
    public static void readFrom(String filename, JTextArea text)
    {
        try
        {
            Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd = new BufferedReader(reader);//缓冲字符输入流
            text.setText("");                    //清空文本区
            String line;
            while((line=bufrd.readLine())!=null) //读取一行字符串，缓冲字符输入流结束返回null
                text.append(line+"\r\n");        //text文本区添加line字符串，加换行
            bufrd.close();
            reader.close();
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略文件
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
    }    
    
    //调用者：【例8.7】文本编辑器。
    //将text文本区中的字符串写入file指定文本文件中
    public static void writeTo(File file, JTextArea text)
    {
        try
        {
            Writer wr = new FileWriter(file);    //文件字符输出流
            wr.write(text.getText());            //写入文本区中的字符串
            wr.close();
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    }

    //若filename指定文件名的文件存在，先删除text文本区中所有字符串；再将从文本文件中
    //读取的每行字符串添加到text文本区。若文件不存在，则弹出对话框告知。
    //使用缓冲字符输入流，逐行读取
    public static void readFrom(File file, JTextArea text)
    {
        try
        {
            Reader reader = new FileReader(file);          //文件字符输入流
            BufferedReader bufrd = new BufferedReader(reader);//缓冲字符输入流
            text.setText("");                              //清空文本区
            String line;
            while((line=bufrd.readLine())!=null) //读取一行字符串，缓冲字符输入流结束返回null
                text.append(line+"\r\n");        //text文本区添加line字符串，加换行
            bufrd.close();
            reader.close();
        }
        catch(FileNotFoundException ex)                    //若文件不存在，则忽略文件
        {
            if(!file.getName().equals(""))
        	    JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"文件不存在。");
//            JOptionPane.showMessageDialog(null, "\""+file.getAbsolutePath()+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
    }   
}
//@author：Yeheya，2018年8月2日