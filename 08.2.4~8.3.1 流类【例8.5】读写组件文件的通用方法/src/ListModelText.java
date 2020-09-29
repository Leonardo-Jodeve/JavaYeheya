//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��7��31��
//��8.3.1  �ַ�����

import javax.swing.*;
import java.io.*;

//�б�����Ͽ�������ı��ļ���ͨ�÷���
public class ListModelText 
{
    //���������������ߣ�����8.6˼����ڡ� �����ļ�������������6.7˼���⡿��Ҷõ����
	//��filenameָ���ļ������ļ����ڣ���ɾ��combox��Ͽ�����������ٽ����ļ��ж�ȡ��ÿ���ַ�����
	//��ӵ���Ͽ����ļ������ڣ��򵯳��Ի��򣬸�֪����
    public static void readFrom(String filename, JComboBox<String> combox)
    {
        try
        {
            Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd=new BufferedReader(reader);//�����ַ�������
            combox.removeAllItems();             //ɾ����Ͽ����������ȫ��ɾ������ѡ����Ϊnull��������Ͽ����¼�
            String line = null;
            while((line=bufrd.readLine())!=null)           //��ȡһ���ַ����������ַ���������������null
                combox.addItem(line);                      //��Ͽ����һ��������
            bufrd.close();
            reader.close();
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
    
    //��combox��Ͽ��е������ַ���������д��filenameָ���ļ������ı��ļ���
    public static void writeTo(String filename, JComboBox<String> combox)
    {
        try
        {
            Writer wr = new FileWriter(filename);//�ļ��ַ������
            for(int i=0; i<combox.getItemCount(); i++)
                wr.write((String)combox.getItemAt(i)+"\r\n");//д��һ���ַ���
            wr.close();
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
    
    //���·����������б��ģ�ͻ���Ͽ�ģ�ͣ���������ͬ
    //��filenameָ���ļ������ļ����ڣ���ɾ��listmodel�б��ģ�����������
    //�ٽ����ļ��ж�ȡ��ÿ���ַ�������ӵ��б��ģ�ͣ����ļ������ڣ������Ի��������ʾ��Ϣ
//  public static void readFrom(String filename, ListModel<String> listmodel)//����
//    public static void readFrom(String filename, DefaultComboBoxModel<String> listmodel)//����
    public static void readFrom(String filename, DefaultListModel<String> listmodel)//����
    {
        try
        {
            Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd=new BufferedReader(reader);//�����ַ�������
            listmodel.removeAllElements();                 //ɾ���б��ģ������������
            String line = null;
            while((line=bufrd.readLine())!=null)           //��ȡһ���ַ����������ַ���������������null
                listmodel.addElement(line);                //�б��ģ�����line�ַ���
            bufrd.close();
            reader.close();
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
    
    //��listmodel�б��ģ�ͣ�����Ͽ�ģ�ͣ��е�ÿ���ַ��������д��filenameָ���ļ������ı��ļ���
    //DefaultListModel<T>��DefaultComboBoxModel<T>��ʵ��ListModel<T>�ӿ�
//    public static void writeTo(String filename, DefaultComboBoxModel<String> listmodel)//����
//    public static void writeTo(String filename, DefaultListModel<String> listmodel)    //����
    public static void writeTo(String filename, ListModel<String> listmodel)
    {
        try
        {
            Writer wr = new FileWriter(filename);          //�ļ��ַ������
            for(int i=0; i<listmodel.getSize(); i++)
                wr.write((String)listmodel.getElementAt(i)+"\r\n");//д��һ���ַ���
            wr.close();
        }
        catch(FileNotFoundException ex)                    //�ļ��������쳣
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