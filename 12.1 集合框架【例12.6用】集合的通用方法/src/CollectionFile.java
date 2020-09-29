//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月2日
//§8.2.5  对象字节流
//§12.1 集合框架

import java.util.*;
import javax.swing.*;
import java.io.*;

//集合对象文件类，为集合提供读写对象文件的通用方法
public class CollectionFile 
{
    //以下两个方法【例12.6】调用
    //将从filename对象文件中读取的T类对象，添加到coll集合中。
    //可接受LinkedList<T>等子类实例。
    public static <T> void readFrom(String filename, Collection<T> coll)  
    {
        try
        {
            InputStream in = new FileInputStream(filename);//文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(in);  //对象字节输入流
            coll.clear();                                  //清空集合
            while(true)
            {
                try
                {
                    coll.add((T)objin.readObject());       //集合添加读取的对象
                }
                catch(EOFException ex)           //当对象输入流结束时抛出文件尾异常
                {
                    break;
                }
            }
            objin.close();                       //关闭对象流
            in.close();                          //关闭文件流
        }
        catch(FileNotFoundException ex)         //若文件不存在，则忽略文件
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "指定类未找到错误");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
        //捕获多个异常，若文件不存在，读/写数据错误，类未找到，则结束操作
//也可      catch (IOException | ClassNotFoundException ex){}
    }
    
    //将coll集合中的所有T类对象，写到filename指定文件名的对象文件。【例12.6】调用
    public static <T> void writeTo(String filename, Collection<T> coll)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);//文件字节输出流
            ObjectOutputStream objout = new ObjectOutputStream(out);//对象字节输出流
//          for(Iterator<T> it=coll.iterator(); it.hasNext(); )    //迭代器
//              objout.writeObject(it.next());           //写入集合当前对象
            for(T obj : coll)                    //逐元循环，obj获得list列表中每个元素
                objout.writeObject(obj);         //写入集合当前的对象
            objout.close();                      //关闭对象流
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
//也可      catch(IOException ex)                   //包含文件不存在异常
//      {   JOptionPane.showMessageDialog(this, "文件名是\""+filename+"\"，写入数据错误");
//      }
    }
    
    //以下例12.6未调用， 【课程设计12-13】 电话簿，调用。
    //将从file对象文件中读取的T类对象，添加到coll集合中
    public static <T> void readFrom(File file, Collection<T> coll) 
    {
        try
        {
            InputStream in = new FileInputStream(file);    //文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(in);//对象字节输入流
            coll.clear();                                  //清空集合
            while(true)
            {
                try
                {
                    coll.add((T)objin.readObject());       //集合添加读取的对象
                }
                catch(EOFException ex)                     //当对象输入流结束时抛出文件尾异常
                {
                    break;
                }
            }
            objin.close();                                 //先关闭对象流
            in.close();                                    //再关闭文件流
        }
        catch(FileNotFoundException ex)                    //若文件不存在，则忽略文件
        {
            if (!file.getName().equals(""))
                JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"文件不存在。");
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "指定类未找到错误");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
        //捕获多个异常，若文件不存在，读/写数据错误，类未找到，则结束操作
//也可      catch (IOException | ClassNotFoundException ex){}
    }
        
    //将coll集合中的所有T类对象，写到file指定的对象文件，通用方法
    //【课程设计12-13】 电话簿，调用。
    public static <T> void writeTo(File file, Collection<T> coll) 
    {
        try
        {
            OutputStream out=new FileOutputStream(file);   //文件字节输出流
            ObjectOutputStream objout = new ObjectOutputStream(out);//对象字节输出流
            for(T obj : coll)                              //逐元循环，obj获得coll集合中每个元素
                objout.writeObject(obj);                   //向对象流写入一个对象
            objout.close();                                //关闭对象流
            out.close();                                   //关闭文件流
        }
        catch(FileNotFoundException ex)                    //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
//也可      catch(IOException ex)                            //包含文件不存在异常
//      {
//          JOptionPane.showMessageDialog(this, "文件名是\""+filename+"\"，写入数据错误");
//      }
    }
}
//@author：Yeheya，2018年8月23日