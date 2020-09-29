//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��7��27��
//��8.2.5  �����ֽ���
//�б��ģ�ͣ���Ͽ�ģ�ͣ��Ķ����ļ���
//ListModel<T>����Ҳ��������Ͽ�ģ�͡�
//�����ߣ�����8.3��������8.6˼���⡿��

import javax.swing.*;
import java.io.*;

//�б��ģ�Ͷ����ļ��࣬Ϊ�б�����Ͽ�����ṩ��д�����ļ���ͨ�÷���
public class ListModelObjectFile 
{
	//��������������8.3�����ã��б��ģ��
    //��listmodel�б��ģ���е�����T�����д��filenameָ���ļ����Ķ����ļ�
//  public static <T> void writeTo(String filename, DefaultComboBoxModel<T> listmodel)//����
    public static <T> void writeTo(String filename, ListModel<T> listmodel)//���У�ListModel<T>������������Ͽ�ģ��
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);//�ļ��ֽ������
            ObjectOutputStream objout = new ObjectOutputStream(out);//�����ֽ������
            for(int i=0; i<listmodel.getSize(); i++)
                objout.writeObject(listmodel.getElementAt(i)); //д���б��ģ�͵�i������
            objout.close();                      //�رն�����
            out.close();                         //�ر��ļ���
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
//Ҳ��      catch (IOException ex)                   //�����ļ��������쳣
//    {   JOptionPane.showMessageDialog(this, "�ļ�����\""+filename+"\"��д�����ݴ���");
//    }
    }
  
    //��filenameָ���ļ������ļ����ڣ�����ɾ��listmodel�б��ģ������������ٽ����ļ���
    //��ȡ������T�������ӵ��б��ģ�ͣ����ļ������ڣ��򵯳��Ի����֪
//    public static <T> void readFrom(String filename, ListModel<T> listmodel)//����
//    public static <T> void readFrom(String filename, DefaultComboBoxModel<T> listmodel)//����
    public static <T> void readFrom(String filename, DefaultListModel<T> listmodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in); //�����ֽ�������
//            listmodel.clear();                 //ɾ���б������������
            listmodel.removeAllElements();       //ɾ���б��������������书��һ��
            while(true)
            {
                try
                {
                    listmodel.addElement((T)objin.readObject()); //�б��ģ����Ӷ�ȡ�Ķ���
                }
                catch(EOFException eof)          //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            }
            objin.close();                       //�رն�����
            in.close();                          //�ر��ļ���
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ�������ļ�
        {
            if(!filename.equals(""))
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
        //�������쳣�����ļ������ڣ���/д���ݴ�����δ�ҵ������������
//Ҳ��      catch (IOException | ClassNotFoundException ex){}
    }

    //���¶�д����������combox��Ͽ�
    //��combox��Ͽ��е�����T�����д��filenameָ���ļ����Ķ����ļ�
    public static <T> void writeTo(String filename, JComboBox<T> combox)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //�ļ��ֽ������
            ObjectOutputStream objout = new ObjectOutputStream(out);//�����ֽ������
            for(int i=0; i<combox.getItemCount(); i++)
                objout.writeObject(combox.getItemAt(i)); //д����Ͽ��i���������
            objout.close();
            out.close();
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    } 
    
    //��filenameָ���ļ������ļ����ڣ���ɾ��combox��Ͽ�����������ٽ����ļ��ж�ȡ��
    //����T�������ӵ���Ͽ����ļ������ڣ��򵯳��Ի����֪
    public static <T> void readFrom(String filename, JComboBox<T> combox)
    {
        try
        {
            InputStream in = new FileInputStream(filename);     //�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in);//�����ֽ�������
            combox.removeAllItems();             //ɾ����Ͽ����������������Ͽ����¼�������ѡ����Ϊnull 
            while(true) 
            {
                try
                {
                    combox.addItem((T)objin.readObject());//��Ͽ���Ӷ�ȡ�Ķ���
                }
                catch(EOFException eof)          //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            }
            objin.close();                       //�رն�����
            in.close();                          //�ر��ļ���
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ�������ļ�
        {
            if(!filename.equals(""))
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
    }
}
//@author��Yeheya��2018��8��2�գ�2018��11��12��