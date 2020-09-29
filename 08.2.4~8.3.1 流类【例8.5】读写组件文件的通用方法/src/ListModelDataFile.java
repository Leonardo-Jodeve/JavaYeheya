//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��8��1��
//��8.2.5  �����ֽ���
//������8-8���б�����Ͽ�����������ļ���ͨ�÷�����
//�����ߣ�����8.7˼���⡿�ֺš�
//        ���γ����12-1���������������

import javax.swing.*;
import java.io.*;

//Ϊ�б����Ͽ��ṩ��д�����ļ���ͨ�÷������������б��ģ�͡���Ͽ��������Ͽ�ģ�ͣ�
//�б�����Ͽ��������������Integer
public class ListModelDataFile 
{
    //���¶�д����������combox��Ͽ�//�����ߣ�����8.7˼���⡿�ֺš�
	//��filenameָ���ļ������ļ����ڣ���ɾ��combox��Ͽ�����������ٽ����ļ��ж�ȡ������int
	//��������ӵ���Ͽ���������ļ������ڣ��򵯳��Ի����֪��
    public static void readFrom(String filename, JComboBox<Integer> combox)
    {
        try
        {
            InputStream in = new FileInputStream(filename);   //�ļ��ֽ�������
            DataInputStream datain = new DataInputStream(in); //�����ֽ�������
            combox.removeAllItems();             //ɾ����Ͽ����������������Ͽ����¼�������ѡ����Ϊnull 
            while (true) 
                try
                {   //�¾�������ֽ��������ж�ȡһ��int��������ӵ�combox��Ͽ�������
                    combox.addItem(new Integer(datain.readInt()));
                }
                catch (EOFException eof)         //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            datain.close();                      //�ر�������
            in.close();                          //�ر��ļ���
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ�������ļ�
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
    }    

    //��combox��Ͽ��е���������������д��filenameָ���ļ����������ļ���
    public static void writeTo(String filename, JComboBox<Integer> combox)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //�ļ��ֽ������
            DataOutputStream dataout = new DataOutputStream(out);//�����ֽ������
            for(int i=0; i<combox.getItemCount(); i++)
                dataout.writeInt((Integer)combox.getItemAt(i));
                             //�Ͼ佫combox��Ͽ��i����������д�������ֽ������
            dataout.close();
            out.close();
        }
        catch(FileNotFoundException ex)         //�ļ��������쳣
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    }
    
    //���¶�д�����������б��ģ�ͻ���Ͽ�ģ��
    //��filenameָ���ļ������ļ����ڣ���ɾ��listmodel�б��ģ�����������
    //�ٽ����ļ��ж�ȡ������int��������ӵ��б��ģ�͡����ļ������ڣ��򵯳��Ի����֪��
    //��Ͽ�ģ�Ͳ�������������ͬ
//  public static void readFrom(String filename, DefaultComboBoxModel<Integer> listmodel)//����
    public static void readFrom(String filename, DefaultListModel<Integer> listmodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);   //�ļ��ֽ�������
            DataInputStream datain = new DataInputStream(in); //�����ֽ�������
            listmodel.removeAllElements();       //ɾ���б��ģ�͵�����������
            while(true) 
                try
                {   //�¾�������ֽ��������ж�ȡһ��int��������ӵ�listmodel�б��ģ��
                    listmodel.addElement(new Integer(datain.readInt()));
                }
                catch(EOFException eof)          //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            datain.close();                      //�ر�������
            in.close();                          //�ر��ļ���
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ�������ļ�
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
    }    

    //��listmodel�б���ģ���е���������������д��filenameָ���ļ����������ļ���
//    public static void writeTo(String filename, DefaultComboBoxModel<Integer> listmodel)//����
//    public static void writeTo(String filename, DefaultListModel<Integer> listmodel)//����
    public static void writeTo(String filename, ListModel<Integer> listmodel)//����������������
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);   //�ļ��ֽ������
            DataOutputStream dataout = new DataOutputStream(out);//�����ֽ������
            for(int i=0; i<listmodel.getSize(); i++)
                dataout.writeInt((Integer)listmodel.getElementAt(i));
                             //�Ͼ佫listmodel�б��ģ�͵�i����������д�������ֽ������
            dataout.close();
            out.close();
        }
        catch(FileNotFoundException ex)         //�ļ��������쳣
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    }
}
//@author��Yeheya��2018��8��2�գ�2018��10��28��