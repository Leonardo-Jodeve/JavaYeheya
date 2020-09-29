//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年8月23日
//§8.3  字符流
//§12.1 集合框架

import java.util.*;
import javax.swing.*;
import java.io.*;

//集合文本文件类，为集合提供读写文本文件的通用方法//？？
public class CollectionText 
{
    //将从filename文本文件中读取的字符串，添加到coll集合中。
    //使用缓冲字符输入流，逐行读取。可接受LinkedList<T>等子类实例。
    public static void readFrom(String filename, Collection<String> coll)  
    {
        try
        {
        	Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd = new BufferedReader(reader);//缓冲字符输入流
            coll.clear();                                  //清空集合
            String line;
            while((line=bufrd.readLine())!=null)           //读取一行字符串，输入流结束返回null
                coll.add(line);                            //集合添加读取的对象
            bufrd.close();
            reader.close();
        }
        catch(FileNotFoundException ex)                    //若文件不存在，则忽略文件
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
        //捕获多个异常，若文件不存在，读/写数据错误，类未找到，则结束操作
      //也可      catch (IOException | ClassNotFoundException ex){}
    }    
    //【课程设计12-13】 电话簿，不能调用。
	//不是T的通用方法，因为不能构造的T对象。

    
    //将coll集合中的所有T类对象，写到file指定的对象文件，通用方法
    //【课程设计12-13】 电话簿，调用。
    public static <T> void writeTo(File file, Collection<T> coll) 
    {
        try
        {
        	Writer wr = new FileWriter(file);              //文件字符输出流
            for(T obj : coll)                              //逐元循环，obj获得coll集合中每个元素
                wr.write(obj.toString()+"\n");             //写入对象的字符串
            wr.close();
        }
        catch(FileNotFoundException ex)                    //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    }
}
//@author：Yeheya，2018年8月23日