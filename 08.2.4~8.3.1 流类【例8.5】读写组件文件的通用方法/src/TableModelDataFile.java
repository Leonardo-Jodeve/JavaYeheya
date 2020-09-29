//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月23日
//§8.2.3  数据字节流
//采用整数文件保存表格模型所有单元格的数据，通用方法。
//【例8.2】调用读文件方法。

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//表格模型整数文件类，为表格模型提供读写整数文件的通用方法
public class TableModelDataFile
{
    //将tablemodel表格模型中所有整数写入由filename指定文件名的文件，首先写入表格行列数；
    //若字符串表示整数，则将其转换成整数写入文件，忽略不能转换成整数的字符串及空或其他类型元素；
    //返回写入的整数个数（不包括表格行列数）；若有异常，则弹出对话框告知。
	public static int writeIntTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount(), columns=tablemodel.getColumnCount(),n=0; //获得表格模型行列数
        try
        {   //下句创建文件字节输出流，若文件存在，则重写；若文件路径正确但文件不存在，则创建文件， 
            //否则抛出文件不存在异常
            OutputStream out = new FileOutputStream(filename);
            DataOutputStream dataout = new DataOutputStream(out); //数据字节输出流
            dataout.writeInt(rows);                        //写入表格模型行数
            dataout.writeInt(columns);                     //写入表格模型列数
            for(int i=0; i<rows; i++)                      //循环，写入表格模型每行、列单元格整数
            {
                for(int j=0; j<columns; j++)
                {   
                    Object obj=tablemodel.getValueAt(i,j);  //获得表格指定单元格对象，父类对象obj引用子类实例
                    if(obj!=null && obj instanceof Integer) //若表格单元格中是整数，则写入；不写入null
                    {
                        dataout.writeInt(((Integer)obj).intValue());  //将整数写入数据字节输出流
                        n++;
                    }
                    //若表格单元格中是输入或修改的字符串，则转换成整数写入；不写入null
                    else if(obj!=null && obj instanceof String && !obj.equals(""))
                    {
                        try
                        {
                            dataout.writeInt(Integer.parseInt((String)obj)); //将字符串转换成整数写入流
                            n++;
                        }
                        catch(NumberFormatException ex) {}//忽略不能转换成整数的字符串
                    }
                }
            }
            dataout.close();                               //先关闭数据流
            out.close();                                   //再关闭文件流
            JOptionPane.showMessageDialog(null, "写入\""+filename+"\"文件，"+rows+"行，"+columns+"列，"+n+"个整数。");
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
	//注意，表格没有元素时，写入文件的是行数、列数和0个元素。

    //【例8.2】调用，第5版教材写。
    //若filename指定文件名的整数文件存在，首先读取2个整数作为tablemodel表格模型行列数；
    //再将读取的所有int整数添加到表格模型，并将表格模型剩余单元格设置为null；
    //返回读取的整数个数（不包括表格行列数）；若文件不存在，则弹出对话框告知。
    //tablemodel引用赋值，在方法体中修改其单元格值，作用于实际参数。通用方法
    public static int readIntFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//文件字节输入流
            DataInputStream datain=new DataInputStream(in);//数据字节输入流
            int rows=datain.readInt(), columns=datain.readInt();//读取2个整数作为表格行列数
            tablemodel.setRowCount(rows);        //设置表格行数，没有清空表格，原来的数据还在
            tablemodel.setColumnCount(columns);  //设置表格列数
            int i=0, j=0, n=0;                   //(i,j)指定表格单元格，n记得读取的整数个数
            while(true)                          //不知文件长度
            {
                try
                {
                    for(j=0; j<columns; j++)     //读取一行
                        tablemodel.setValueAt(datain.readInt(),i,j);//读取int整数，设置到表格(i,j)单元格
                    i++;
                }
                catch(EOFException eof)          //当数据字节输入流结束时抛出文件尾异常
                {
                    n=i*columns+j;               //读取的整数个数（不包括表格行数和列数）
                    while(i<rows && j<columns)   //将最后一行剩余单元格设置为null
                        tablemodel.setValueAt(null, i, j++);
                    break;                       //退出while(true)循环
                }
            }
            datain.close();                      //先关闭数据流
            in.close();                          //再关闭文件流
            return n;                            //返回读取的整数个数（不包括表格行列数）
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
//@author：Yeheya，2018年7月23日，2018年11月9日