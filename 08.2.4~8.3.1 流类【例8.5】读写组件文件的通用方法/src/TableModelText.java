//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��11��19��
//��8.3.1  �ַ�����
//����8.5�� ��д����ļ���ͨ�÷�����
//�����ߣ�����6.6��������8.2�����ԡ�
//��5��̲��ᳫ���ֽ������ַ������ͽ�ԭ��

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//�����ı��ļ�������ģ���е����е�Ԫ���ַ�����ͨ�÷�����
public class TableModelText 
{
    //��tablemodel���ģ��ÿ����Ԫ��洢�ַ�����
    //�����������ַ��������ӻ��з���д����filenameָ���ļ������ı��ļ���
    //����д��������������д��ն��󣻷���д����ַ������������������������������
    //�����쳣���򵯳��Ի����֪��
    public static int writeTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount(), columns=tablemodel.getColumnCount(),n=0;//���������
        try
        {   //�¾䴴���ļ��ַ�����������ļ����ڣ�����д�����ļ�·����ȷ���ļ������ڣ� 
            //�򴴽��ļ��������׳��ļ��������쳣
            Writer wr = new FileWriter(filename); 
            wr.write(rows+"\n");                 //д����ģ�������ַ���
            wr.write(columns+"\n");              //д����ģ�������ַ���
            for(int i=0; i<rows; i++)           //ѭ����д����ģ��ÿ�С��е�Ԫ���ַ���
            {
                for(int j=0; j<columns; j++) 
                {
                    Object obj=tablemodel.getValueAt(i,j); //��ñ��ģ��ָ��Ԫ�ض���
                    if(obj!=null)                          //��д��ն���
                    {
                        wr.write(obj.toString()+"\n");     //д��һ���ַ����������˻��з�
                        n++;
                    }
                }
            }
            wr.close();                                    //�ر��ַ���
            JOptionPane.showMessageDialog(null, "д��\""+filename+"\"�ļ���"+rows+"�У�"+columns+"�У�"+n+"���ַ�����");
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
    
    //��filenameָ���ļ������ı��ļ����ڣ����ȶ�ȡ2��������Ϊtablemodel���ģ����������
    //�ٽ���ȡ�������ַ�����ӵ����ģ�ͣ��������ģ��ʣ�൥Ԫ������Ϊnull��
    //���ض�ȡ���ַ���������������������������������ļ������ڣ��򵯳��Ի����֪
    public static int readFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd = new BufferedReader(reader);//�����ַ�������
            //���¶�ȡ2��������Ϊ����������������ñ����������û����ձ��
            int rows=0, columns=0,  i=0, j=0,  n=0;
            String line="";
            try
            {   
                rows = Integer.parseInt(line=bufrd.readLine()); //��ȡһ���ַ�����ת����int����
                columns = Integer.parseInt(line=bufrd.readLine());
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "line=\""+line+"\"��ȡ������������");
                return 0;
            }

            tablemodel.setRowCount(rows);                  //���ñ��������û����ձ��
            tablemodel.setColumnCount(columns);            //���ñ������
            while((line=bufrd.readLine())!=null)           //��ȡһ���ַ����������ַ���������������null
            {
                tablemodel.setValueAt(line, i, j++);       //�����ַ��������(i,j)��Ԫ��
                if(j==columns)
                {
                    j=0;
                    i++;
                }
            }
            bufrd.close();                       //�رջ����ַ�������
            reader.close();                      //�ر��ļ���
            n=i*columns+j;                       //��ȡ���ַ������������������������
            while(i<rows && j<columns)           //�����һ��ʣ�൥Ԫ������Ϊnull
                tablemodel.setValueAt(null, i, j++);
            return n;                            //���ض�ȡ���ַ������������������������
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
//@author��Yeheya��2018��11��22�գ�ˤ�ӣ����ѣ�������