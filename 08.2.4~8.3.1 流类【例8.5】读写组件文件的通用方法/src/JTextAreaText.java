//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��27��
//��8.3.1  �ַ�����
//����8.5�� ��д����ļ���ͨ�÷�����
//�����ߣ�����8.7���ı��༭����

import javax.swing.*;
import java.io.*;

//�ı���������ı��ļ��࣬Ϊ�ı�������ṩ��д�ı��ļ���ͨ�÷���
public class JTextAreaText 
{
	//����8.5�� ��д����ļ���ͨ�÷����� 
    //��text�ı����е��ַ���д��filenameָ���ı��ļ���
    public static void writeTo(String filename, JTextArea text)
    {
        try
        {
            Writer wr = new FileWriter(filename);//�ļ��ַ������
            wr.write(text.getText());            //д���ı����е��ַ���
            wr.close();
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    }
    
    //��filenameָ���ļ������ļ����ڣ���ɾ��text�ı����������ַ������ٽ����ı��ļ���
    //��ȡ��ÿ���ַ�����ӵ�text�ı��������ļ������ڣ��򵯳��Ի����֪��
    //ʹ�û����ַ������������ж�ȡ
    public static void readFrom(String filename, JTextArea text)
    {
        try
        {
            Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd = new BufferedReader(reader);//�����ַ�������
            text.setText("");                    //����ı���
            String line;
            while((line=bufrd.readLine())!=null) //��ȡһ���ַ����������ַ���������������null
                text.append(line+"\r\n");        //text�ı������line�ַ������ӻ���
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
    
    //�����ߣ�����8.7���ı��༭����
    //��text�ı����е��ַ���д��fileָ���ı��ļ���
    public static void writeTo(File file, JTextArea text)
    {
        try
        {
            Writer wr = new FileWriter(file);    //�ļ��ַ������
            wr.write(text.getText());            //д���ı����е��ַ���
            wr.close();
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    }

    //��filenameָ���ļ������ļ����ڣ���ɾ��text�ı����������ַ������ٽ����ı��ļ���
    //��ȡ��ÿ���ַ�����ӵ�text�ı��������ļ������ڣ��򵯳��Ի����֪��
    //ʹ�û����ַ������������ж�ȡ
    public static void readFrom(File file, JTextArea text)
    {
        try
        {
            Reader reader = new FileReader(file);          //�ļ��ַ�������
            BufferedReader bufrd = new BufferedReader(reader);//�����ַ�������
            text.setText("");                              //����ı���
            String line;
            while((line=bufrd.readLine())!=null) //��ȡһ���ַ����������ַ���������������null
                text.append(line+"\r\n");        //text�ı������line�ַ������ӻ���
            bufrd.close();
            reader.close();
        }
        catch(FileNotFoundException ex)                    //���ļ������ڣ�������ļ�
        {
            if(!file.getName().equals(""))
        	    JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"�ļ������ڡ�");
//            JOptionPane.showMessageDialog(null, "\""+file.getAbsolutePath()+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
    }   
}
//@author��Yeheya��2018��8��2��