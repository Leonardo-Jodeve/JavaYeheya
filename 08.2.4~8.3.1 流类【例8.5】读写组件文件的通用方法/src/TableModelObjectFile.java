//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��23��
//��8.2.4  �����ֽ���
//������8�����ö����ļ�������ģ���е����е�Ԫ�����ͨ�÷�����

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//���ö����ļ�������ģ���е����е�Ԫ�����ͨ�÷���
public class TableModelObjectFile 
{
    //��tablemodel���ģ�������ж���д����filenameָ���ļ����Ķ����ļ���
    //����д��������������д��ն��󣻷���д��Ķ���������������������������
    //�����쳣���򵯳��Ի����֪
	public static int writeTo(String filename, DefaultTableModel tablemodel)
    {
        int rows=tablemodel.getRowCount(), columns=tablemodel.getColumnCount(),n=0; //��ñ��ģ��������
        try
        {   //�¾䴴���ļ��ֽ�����������ļ����ڣ�����д�����ļ�·����ȷ���ļ������ڣ� 
            //�򴴽��ļ��������׳��ļ��������쳣
        	OutputStream out = new FileOutputStream(filename);
            ObjectOutputStream objout = new ObjectOutputStream(out); //�����ֽ�����������ļ��ֽ�����Ϊ����Դ
            objout.writeInt(rows);                         //д����ģ��������������Ҳ��д��int����
            objout.writeInt(columns);                      //д����ģ������
//            objout.writeObject(new Integer(rows));         //д����ģ��������������
//            objout.writeObject(new Integer(columns));      //д����ģ��������������
            for(int i=0; i<rows; i++)                      //ѭ����д����ģ��ÿ�С��е�Ԫ�����
            {
                for(int j=0; j<columns; j++)
                {   
                    Object obj=tablemodel.getValueAt(i,j); //��ñ��ָ����Ԫ��Ķ��󣬸������obj��������ʵ��
                    objout.writeObject(obj);               //д��һ��������obj==null��Ҳд��
                    n++;
                }
            }
            objout.close();                                 //�رն�����
            out.close();                                    //�ر��ļ���
            JOptionPane.showMessageDialog(null, "д��\""+filename+"\"�ļ���"+rows+"�У�"+columns+"�У�"+n+"������");
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
	
    //��filenameָ���ļ����Ķ����ļ����ڣ����ȶ�ȡ2��������Ϊtablemodel���ģ����������
    //�ٽ���ȡ�����ж��󣨰���null����ӵ����ģ�ͣ�
    //���ض�ȡ�Ķ������������������������������ļ������ڣ��򵯳��Ի����֪
    public static int readFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);     //�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in);//�����ֽ�������
            int rows=objin.readInt(), columns=objin.readInt();  //��ȡ2��������Ϊ���������������
//            int rows=(Integer)objin.readObject(), columns=(Integer)objin.readObject();//��ȡ2������������Ϊ���������
            tablemodel.setRowCount(rows);        //���ñ��������û����ձ��
            tablemodel.setColumnCount(columns);  //���ñ������
            int i=0, j=0, n=0;                   //(i,j)ָ�����Ԫ��n�ǵö�ȡ����������
            while(true)                          //��֪�ļ�����
            {
                try
                {
                    for(j=0; j<columns; j++)     //��ȡһ��
                        tablemodel.setValueAt(objin.readObject(),i,j);//��ȡ�������õ����(i,j)��Ԫ��
                    i++;
                }
                catch(EOFException eof)          //�������ֽ�����������ʱ�׳��ļ�β�쳣
                {
                    n=i*columns+j;               //��ȡ���������������������������������
                    break;                       //�˳�while(true)ѭ��
                }
            }
            objin.close();                       //�ȹرն�����
            in.close();                          //�ٹر��ļ���
            return n;                            //���ض�ȡ�����������������������������
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
//            if (!filename.equals(""))
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "ָ����δ�ҵ�����");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
        return 0;
    }    
}    
//@author��Yeheya��2018��11��22�գ�ˤ�ӣ����ѣ�������