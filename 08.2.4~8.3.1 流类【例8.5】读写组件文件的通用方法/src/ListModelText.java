//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年7月31日
//§8.3.1  字符流类

import javax.swing.*;
import java.io.*;

//列表框和组合框组件的文本文件，通用方法
public class ListModelText 
{
    //以下两方法调用者：【例8.6思考题②】 保存文件过滤器；【例6.7思考题】多叶玫瑰线
	//若filename指定文件名的文件存在，先删除combox组合框所有数据项；再将从文件中读取的每行字符串，
	//添加到组合框；若文件不存在，则弹出对话框，告知错误。
    public static void readFrom(String filename, JComboBox<String> combox)
    {
        try
        {
            Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd=new BufferedReader(reader);//缓冲字符输入流
            combox.removeAllItems();             //删除组合框所有数据项，全部删除后导致选中项为null，触发组合框动作事件
            String line = null;
            while((line=bufrd.readLine())!=null)           //读取一行字符串，缓冲字符输入流结束返回null
                combox.addItem(line);                      //组合框添加一个数据项
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
    
    //将combox组合框中的所有字符串数据项写入filename指定文件名的文本文件中
    public static void writeTo(String filename, JComboBox<String> combox)
    {
        try
        {
            Writer wr = new FileWriter(filename);//文件字符输出流
            for(int i=0; i<combox.getItemCount(); i++)
                wr.write((String)combox.getItemAt(i)+"\r\n");//写入一行字符串
            wr.close();
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
    
    //以下方法参数是列表框模型或组合框模型，方法体相同
    //若filename指定文件名的文件存在，先删除listmodel列表框模型所有数据项；
    //再将从文件中读取的每行字符串，添加到列表框模型；若文件不存在，弹出对话框给出提示信息
//  public static void readFrom(String filename, ListModel<String> listmodel)//不行
//    public static void readFrom(String filename, DefaultComboBoxModel<String> listmodel)//可行
    public static void readFrom(String filename, DefaultListModel<String> listmodel)//可行
    {
        try
        {
            Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd=new BufferedReader(reader);//缓冲字符输入流
            listmodel.removeAllElements();                 //删除列表框模型所有数据项
            String line = null;
            while((line=bufrd.readLine())!=null)           //读取一行字符串，缓冲字符输入流结束返回null
                listmodel.addElement(line);                //列表框模型添加line字符串
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
    
    //将listmodel列表框模型（或组合框模型）中的每行字符串数据项，写入filename指定文件名的文本文件中
    //DefaultListModel<T>、DefaultComboBoxModel<T>都实现ListModel<T>接口
//    public static void writeTo(String filename, DefaultComboBoxModel<String> listmodel)//可行
//    public static void writeTo(String filename, DefaultListModel<String> listmodel)    //可行
    public static void writeTo(String filename, ListModel<String> listmodel)
    {
        try
        {
            Writer wr = new FileWriter(filename);          //文件字符输出流
            for(int i=0; i<listmodel.getSize(); i++)
                wr.write((String)listmodel.getElementAt(i)+"\r\n");//写入一行字符串
            wr.close();
        }
        catch(FileNotFoundException ex)                    //文件不存在异常
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    }    
}
//@author：Yeheya，2018年8月2日，2018年10月28日