//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年7月27日
//§8.2.5  对象字节流
//列表框模型（组合框模型）的对象文件。
//ListModel<T>参数也适用于组合框模型。
//调用者：【例8.3】，【例8.6思考题】。

import javax.swing.*;
import java.io.*;

//列表框模型对象文件类，为列表框和组合框组件提供读写对象文件的通用方法
public class ListModelObjectFile 
{
	//以下两方法【例8.3】调用，列表框模型
    //将listmodel列表框模型中的所有T类对象，写到filename指定文件名的对象文件
//  public static <T> void writeTo(String filename, DefaultComboBoxModel<T> listmodel)//可行
    public static <T> void writeTo(String filename, ListModel<T> listmodel)//可行，ListModel<T>参数适用于组合框模型
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);//文件字节输出流
            ObjectOutputStream objout = new ObjectOutputStream(out);//对象字节输出流
            for(int i=0; i<listmodel.getSize(); i++)
                objout.writeObject(listmodel.getElementAt(i)); //写入列表框模型第i个对象
            objout.close();                      //关闭对象流
            out.close();                         //关闭文件流
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
//也可      catch (IOException ex)                   //包含文件不存在异常
//    {   JOptionPane.showMessageDialog(this, "文件名是\""+filename+"\"，写入数据错误");
//    }
    }
  
    //若filename指定文件名的文件存在，则先删除listmodel列表框模型所有数据项；再将从文件中
    //读取的所有T类对象，添加到列表框模型；若文件不存在，则弹出对话框告知
//    public static <T> void readFrom(String filename, ListModel<T> listmodel)//不行
//    public static <T> void readFrom(String filename, DefaultComboBoxModel<T> listmodel)//可行
    public static <T> void readFrom(String filename, DefaultListModel<T> listmodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(in); //对象字节输入流
//            listmodel.clear();                 //删除列表框所有数据项
            listmodel.removeAllElements();       //删除列表框所有数据项，两句功能一样
            while(true)
            {
                try
                {
                    listmodel.addElement((T)objin.readObject()); //列表框模型添加读取的对象
                }
                catch(EOFException eof)          //当对象输入流结束时抛出文件尾异常
                {
                    break;
                }
            }
            objin.close();                       //关闭对象流
            in.close();                          //关闭文件流
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略文件
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

    //以下读写方法参数是combox组合框
    //将combox组合框中的所有T类对象，写到filename指定文件名的对象文件
    public static <T> void writeTo(String filename, JComboBox<T> combox)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //文件字节输出流
            ObjectOutputStream objout = new ObjectOutputStream(out);//对象字节输出流
            for(int i=0; i<combox.getItemCount(); i++)
                objout.writeObject(combox.getItemAt(i)); //写入组合框第i数据项对象
            objout.close();
            out.close();
        }
        catch(FileNotFoundException ex)          //文件不存在异常
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    } 
    
    //若filename指定文件名的文件存在，先删除combox组合框所有数据项；再将从文件中读取的
    //所有T类对象，添加到组合框；若文件不存在，则弹出对话框告知
    public static <T> void readFrom(String filename, JComboBox<T> combox)
    {
        try
        {
            InputStream in = new FileInputStream(filename);     //文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(in);//对象字节输入流
            combox.removeAllItems();             //删除组合框所有数据项，触发组合框动作事件，导致选中项为null 
            while(true) 
            {
                try
                {
                    combox.addItem((T)objin.readObject());//组合框添加读取的对象
                }
                catch(EOFException eof)          //当对象输入流结束时抛出文件尾异常
                {
                    break;
                }
            }
            objin.close();                       //关闭对象流
            in.close();                          //关闭文件流
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略文件
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
    }
}
//@author：Yeheya，2018年8月2日，2018年11月12日