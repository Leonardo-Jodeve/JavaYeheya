//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��23��
//��8.2.3  �����ֽ���
//���������ļ�������ģ�����е�Ԫ������ݣ�ͨ�÷�����
//����8.2�����ö��ļ�������

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//���ģ�������ļ��࣬Ϊ���ģ���ṩ��д�����ļ���ͨ�÷���
public class TableModelDataFile
{
    //��tablemodel���ģ������������д����filenameָ���ļ������ļ�������д������������
    //���ַ�����ʾ����������ת��������д���ļ������Բ���ת�����������ַ������ջ���������Ԫ�أ�
    //����д�������������������������������������쳣���򵯳��Ի����֪��
	public static int writeIntTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount(), columns=tablemodel.getColumnCount(),n=0; //��ñ��ģ��������
        try
        {   //�¾䴴���ļ��ֽ�����������ļ����ڣ�����д�����ļ�·����ȷ���ļ������ڣ��򴴽��ļ��� 
            //�����׳��ļ��������쳣
            OutputStream out = new FileOutputStream(filename);
            DataOutputStream dataout = new DataOutputStream(out); //�����ֽ������
            dataout.writeInt(rows);                        //д����ģ������
            dataout.writeInt(columns);                     //д����ģ������
            for(int i=0; i<rows; i++)                      //ѭ����д����ģ��ÿ�С��е�Ԫ������
            {
                for(int j=0; j<columns; j++)
                {   
                    Object obj=tablemodel.getValueAt(i,j);  //��ñ��ָ����Ԫ����󣬸������obj��������ʵ��
                    if(obj!=null && obj instanceof Integer) //�����Ԫ��������������д�룻��д��null
                    {
                        dataout.writeInt(((Integer)obj).intValue());  //������д�������ֽ������
                        n++;
                    }
                    //�����Ԫ������������޸ĵ��ַ�������ת��������д�룻��д��null
                    else if(obj!=null && obj instanceof String && !obj.equals(""))
                    {
                        try
                        {
                            dataout.writeInt(Integer.parseInt((String)obj)); //���ַ���ת��������д����
                            n++;
                        }
                        catch(NumberFormatException ex) {}//���Բ���ת�����������ַ���
                    }
                }
            }
            dataout.close();                               //�ȹر�������
            out.close();                                   //�ٹر��ļ���
            JOptionPane.showMessageDialog(null, "д��\""+filename+"\"�ļ���"+rows+"�У�"+columns+"�У�"+n+"��������");
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
        return n;
    }
	//ע�⣬���û��Ԫ��ʱ��д���ļ�����������������0��Ԫ�ء�

    //����8.2�����ã���5��̲�д��
    //��filenameָ���ļ����������ļ����ڣ����ȶ�ȡ2��������Ϊtablemodel���ģ����������
    //�ٽ���ȡ������int������ӵ����ģ�ͣ��������ģ��ʣ�൥Ԫ������Ϊnull��
    //���ض�ȡ����������������������������������ļ������ڣ��򵯳��Ի����֪��
    //tablemodel���ø�ֵ���ڷ��������޸��䵥Ԫ��ֵ��������ʵ�ʲ�����ͨ�÷���
    public static int readIntFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//�ļ��ֽ�������
            DataInputStream datain=new DataInputStream(in);//�����ֽ�������
            int rows=datain.readInt(), columns=datain.readInt();//��ȡ2��������Ϊ���������
            tablemodel.setRowCount(rows);        //���ñ��������û����ձ��ԭ�������ݻ���
            tablemodel.setColumnCount(columns);  //���ñ������
            int i=0, j=0, n=0;                   //(i,j)ָ�����Ԫ��n�ǵö�ȡ����������
            while(true)                          //��֪�ļ�����
            {
                try
                {
                    for(j=0; j<columns; j++)     //��ȡһ��
                        tablemodel.setValueAt(datain.readInt(),i,j);//��ȡint���������õ����(i,j)��Ԫ��
                    i++;
                }
                catch(EOFException eof)          //�������ֽ�����������ʱ�׳��ļ�β�쳣
                {
                    n=i*columns+j;               //��ȡ���������������������������������
                    while(i<rows && j<columns)   //�����һ��ʣ�൥Ԫ������Ϊnull
                        tablemodel.setValueAt(null, i, j++);
                    break;                       //�˳�while(true)ѭ��
                }
            }
            datain.close();                      //�ȹر�������
            in.close();                          //�ٹر��ļ���
            return n;                            //���ض�ȡ�����������������������������
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ�������ļ�
        {
//            if (!filename.equals(""))
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
        return 0;
    }    
}
//@author��Yeheya��2018��7��23�գ�2018��11��9��