//《Java程序设计实用教程（第4版）》 作者：叶核亚，2015年7月16日
//§8.2 字节流
//§8.3 字符流
//【例8.5】读写组件字符串的通用方法
//【例6.7思考题】使用字符流读写文本文件，作为单选按钮标题。
//【例8.8】  文本文件编辑器。

import javax.swing.*;
import java.io.*;

public class ComponentFile 
{
    
    //【试题8，4-0样卷】【例6.7思考题】RoseNJFrame用
    //从filename指定文件名的文本文件中读取的多行字符串，每行字符串作为radios单选按钮数组中一个按钮的标题
    public static void readFromText(String filename, JRadioButton[] radios)
    {
        try
        {
            Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd = new BufferedReader(reader);//缓冲字符输入流，以文件字符流作为数据源
            String line;
                //每次读取一行字符串，读取行数最多是按钮数，缓冲字符流结束时返回null
            for(int i=0; i<radios.length && (line=bufrd.readLine())!=null; i++)
            	radios[i].setText(line);                   //设置按钮标题为读取的字符串
            bufrd.close();
            reader.close();
        }
        catch(IOException e){}                             //捕获IOException异常，文件不存在时，不读取
    }
    
    //【试题8】【例6.7思考题】RoseNJFrame用
    //将单选按钮数组radios中的各按钮标题，写入由filename指定文件名的文本文件，每数据占一行
    public static void writeToText(String filename, JRadioButton[] radios)
    {
        try
        {
            Writer wr = new FileWriter(filename);          //文件字符输出流
            for(int i=0; i<radios.length; i++)
            	wr.write(radios[i].getText()+"\r\n");      //写入单选按钮标题,一行字符串
            wr.close();
        }
        catch(IOException ex) {}                           //写入数据错误时抛出IOException异常
    }

    //【试题8】【例6.7思考题】RoseNJFrame用
    //将从filename指定文件名的对象文件中读取对象到objs对象数组。使用对象流
    public static <T> void readObjectFrom(String filename, T[] objs)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(in); //以字节输入流作为数据源
            for (int i=0; i<objs.length; i++)
            {
                try
                {
                    objs[i] = (T)objin.readObject();       //读取对象
                }
                catch (EOFException eof)                   //对象输入流结束时抛出EOFException异常
                {
                    break;
                }
            }
            objin.close();                                 //先关闭对象流
            in.close();                                    //再关闭文件流
        }
        catch(Exception ex){}     //捕获IOException和ClassNotFoundException异常，文件不存在时，不读取
    }
    
    //【试题8】【例6.7思考题】RoseNJFrame用
    //将objs数组中的对象写到filename指定文件名的对象文件。使用对象流
    public static <T> void writeObjectTo(String filename, T[] objs)
    {
    	try
        {
    	    OutputStream out = new FileOutputStream(filename);  //文件字节输出流,true
            ObjectOutputStream objout = new ObjectOutputStream(out);//对象流，以字节输出流作为数据源
            for(int i=0; i<objs.length; i++)
                objout.writeObject(objs[i]);               //向对象流写入一个对象
            objout.close();                                //先关闭对象流
            out.close();                                   //再关闭文件流
        }
        catch(IOException ex) {}
    }
}
