//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年11月19日
//§8.3.1  字符流类
//【例8.5】 读写组件文件的通用方法。
//调用者：【例6.6】，【例8.2】测试。
//第5版教材提倡，字节流、字符流按就近原则

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//采用文本文件保存表格模型中的所有单元格字符串，通用方法。
public class TableModelText 
{
    //若tablemodel表格模型每个单元格存储字符串，
    //将其中所有字符串（增加换行符）写入由filename指定文件名的文本文件；
    //首先写入表格行列数，不写入空对象；返回写入的字符串数（不包括表格行数和列数）。
    //若有异常，则弹出对话框告知。
    public static int writeTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount(), columns=tablemodel.getColumnCount(),n=0;//表格行列数
        try
        {   //下句创建文件字符输出流，若文件存在，则重写；若文件路径正确但文件不存在， 
            //则创建文件，否则抛出文件不存在异常
            Writer wr = new FileWriter(filename); 
            wr.write(rows+"\n");                 //写入表格模型行数字符串
            wr.write(columns+"\n");              //写入表格模型列数字符串
            for(int i=0; i<rows; i++)           //循环，写入表格模型每行、列单元格字符串
            {
                for(int j=0; j<columns; j++) 
                {
                    Object obj=tablemodel.getValueAt(i,j); //获得表格模型指定元素对象
                    if(obj!=null)                          //不写入空对象
                    {
                        wr.write(obj.toString()+"\n");     //写入一个字符串，增加了换行符
                        n++;
                    }
                }
            }
            wr.close();                                    //关闭字符流
            JOptionPane.showMessageDialog(null, "写入\""+filename+"\"文件，"+rows+"行，"+columns+"列，"+n+"行字符串。");
        }
        catch(FileNotFoundException ex)          //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "写入文件时数据错误");
        }
        return n;
    }
    
    //若filename指定文件名的文本文件存在，首先读取2个整数作为tablemodel表格模型行列数；
    //再将读取的所有字符串添加到表格模型，并将表格模型剩余单元格设置为null；
    //返回读取的字符串数（不包括表格行数和列数）。若文件不存在，则弹出对话框告知
    public static int readFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            Reader reader = new FileReader(filename);      //文件字符输入流
            BufferedReader bufrd = new BufferedReader(reader);//缓冲字符输入流
            //以下读取2个整数作为表格行列数，再设置表格行列数，没有清空表格
            int rows=0, columns=0,  i=0, j=0,  n=0;
            String line="";
            try
            {   
                rows = Integer.parseInt(line=bufrd.readLine()); //读取一行字符串，转换成int整数
                columns = Integer.parseInt(line=bufrd.readLine());
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "line=\""+line+"\"读取的行列数错误。");
                return 0;
            }

            tablemodel.setRowCount(rows);                  //设置表格行数，没有清空表格
            tablemodel.setColumnCount(columns);            //设置表格列数
            while((line=bufrd.readLine())!=null)           //读取一行字符串，缓冲字符输入流结束返回null
            {
                tablemodel.setValueAt(line, i, j++);       //设置字符串到表格(i,j)单元格
                if(j==columns)
                {
                    j=0;
                    i++;
                }
            }
            bufrd.close();                       //关闭缓冲字符输入流
            reader.close();                      //关闭文件流
            n=i*columns+j;                       //读取的字符串数（不包括表格行列数）
            while(i<rows && j<columns)           //将最后一行剩余单元格设置为null
                tablemodel.setValueAt(null, i, j++);
            return n;                            //返回读取的字符串数（不包括表格行列数）
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略文件
        {
//            if (!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "读取文件时数据错误");
        }
        return 0;
    }
}
//@author：Yeheya，2018年11月22日，摔跤，骨裂，休养中