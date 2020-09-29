//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年8月1日
//§8.2.5  对象字节流
//【试题8-8】列表框的组合框组件的整数文件，通用方法。
//调用者：【例8.7思考题】字号。
//        【课程设计12-1】随机数序列排序。

import javax.swing.*;
import java.io.*;

//为列表框、组合框提供读写整数文件的通用方法，参数有列表框模型、组合框组件、组合框模型；
//列表框和组合框的数据项类型是Integer
public class ListModelDataFile 
{
    //以下读写方法参数是combox组合框。//调用者：【例8.7思考题】字号。
	//若filename指定文件名的文件存在，先删除combox组合框所有数据项；再将从文件中读取的所有int
	//整数，添加到组合框数据项。若文件不存在，则弹出对话框告知。
    public static void readFrom(String filename, JComboBox<Integer> combox)
    {
        try
        {
            InputStream in = new FileInputStream(filename);   //文件字节输入流
            DataInputStream datain = new DataInputStream(in); //数据字节输入流
            combox.removeAllItems();             //删除组合框所有数据项，触发组合框动作事件，导致选中项为null 
            while (true) 
                try
                {   //下句从数据字节输入流中读取一个int整数，添加到combox组合框数据项
                    combox.addItem(new Integer(datain.readInt()));
                }
                catch (EOFException eof)         //当数据输入流结束时抛出文件尾异常
                {
                    break;
                }
            datain.close();                      //关闭数据流
            in.close();                          //关闭文件流
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

    //将combox组合框中的所有整数数据项写入filename指定文件名的整数文件中
    public static void writeTo(String filename, JComboBox<Integer> combox)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //文件字节输出流
            DataOutputStream dataout = new DataOutputStream(out);//数据字节输出流
            for(int i=0; i<combox.getItemCount(); i++)
                dataout.writeInt((Integer)combox.getItemAt(i));
                             //上句将combox组合框第i数据项整数写入数据字节输出流
            dataout.close();
            out.close();
        }
        catch(FileNotFoundException ex)         //文件不存在异常
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
    }
    
    //以下读写方法参数是列表框模型或组合框模型
    //若filename指定文件名的文件存在，先删除listmodel列表框模型所有数据项；
    //再将从文件中读取的所有int整数，添加到列表框模型。若文件不存在，则弹出对话框告知。
    //组合框模型参数，方法体相同
//  public static void readFrom(String filename, DefaultComboBoxModel<Integer> listmodel)//可行
    public static void readFrom(String filename, DefaultListModel<Integer> listmodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);   //文件字节输入流
            DataInputStream datain = new DataInputStream(in); //数据字节输入流
            listmodel.removeAllElements();       //删除列表框模型的所有数据项
            while(true) 
                try
                {   //下句从数据字节输入流中读取一个int整数，添加到listmodel列表框模型
                    listmodel.addElement(new Integer(datain.readInt()));
                }
                catch(EOFException eof)          //当数据输入流结束时抛出文件尾异常
                {
                    break;
                }
            datain.close();                      //关闭数据流
            in.close();                          //关闭文件流
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

    //将listmodel列表框架模型中的所有整数数据项写入filename指定文件名的整数文件中
//    public static void writeTo(String filename, DefaultComboBoxModel<Integer> listmodel)//可行
//    public static void writeTo(String filename, DefaultListModel<Integer> listmodel)//可行
    public static void writeTo(String filename, ListModel<Integer> listmodel)//包含上述两个参数
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //文件字节输出流
            DataOutputStream dataout = new DataOutputStream(out);//数据字节输出流
            for(int i=0; i<listmodel.getSize(); i++)
                dataout.writeInt((Integer)listmodel.getElementAt(i));
                             //上句将listmodel列表框模型第i数据项整数写入数据字节输出流
            dataout.close();
            out.close();
        }
        catch(FileNotFoundException ex)         //文件不存在异常
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