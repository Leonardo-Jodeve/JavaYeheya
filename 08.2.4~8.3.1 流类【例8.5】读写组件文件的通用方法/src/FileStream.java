//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2017��11��28��
//��8.4   �ļ�����
//����8.7��  �ı��ļ��༭�����ļ���������

import java.io.*;
import javax.swing.JOptionPane;

//�ṩ�ļ����ƵȲ�����ͨ�÷�����ʹ���ֽ��������������������ļ�
public class FileStream
{
	//���µ�5�桾��8.7���ļ�������������
	//��file1�ļ����ݸ��Ƶ�file2�ļ��У���д��ʽ��
    public static void copy(File file1, File file2)
    {
        try
        {
            InputStream in = new FileInputStream(file1);   //�����ļ��ֽ�����������
            OutputStream out = new FileOutputStream(file2);//�����ļ��ֽ����������
            byte[] buffer = new byte[512];       //�ֽڻ�����
            int n=0;                             //��ȡ�ֽ���
            while((n=in.read(buffer))!=-1)       //�����ֽ����飬���ض�ȡ�ֽ���������������-1
                out.write(buffer, 0, n);         //д��buffer�����0��ʼ��n��Ԫ��
            
            in.close();                          //�ر�������
            out.close();                         //�ر������
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣
        {
            JOptionPane.showMessageDialog(null, file1.getAbsoluteFile()+"\"�ļ������ڡ�");
        }
        catch(IOException ex)                    //��������쳣
        {
            JOptionPane.showMessageDialog(null, "\""+file1.getAbsoluteFile()+"\"�ļ����Ʋ��ɹ���");
        }
    }    

    //��8.2.2  �ļ��ֽ���
    //����4����8.1��  �����ʹ���ֽ�����
    //��3�� �ļ����Ʋ�����ʹ���ֽ�����ͨ���㷨��
    //��filename1�ļ����ݸ��Ƶ�filename2�ļ��У���д��ʽ��
    public static void copy(String filename1, String filename2)
    {
        try
        {
        	InputStream in = new FileInputStream(filename1);    //�����ļ��ֽ�����������
            OutputStream out = new FileOutputStream(filename2); //�����ļ��ֽ����������
            byte[] buffer = new byte[512];       //�ֽڻ�����
            int n=0;                             //��ȡ�ֽ���
            while((n=in.read(buffer))!=-1)       //�����ֽ����飬���ض�ȡ�ֽ���������������-1
                out.write(buffer, 0, n);         //д��buffer�����0��ʼ��n��Ԫ��
            
            in.close();                          //�ر�������
            out.close();                         //�ر������
        }
        catch(FileNotFoundException ex)          //ָ���ļ�������
        {
            JOptionPane.showMessageDialog(null, "\""+filename1+"\"�ļ������ڡ�");
        }
        catch(IOException ex)                    //IO�쳣
        {
            JOptionPane.showMessageDialog(null, "IO�쳣������\""+filename1+"\"�ļ�δ�ɹ�");
        }
    }    
}
//@author��Yeheya��2015-7-15��2017��8��23�գ�2018��11��3��