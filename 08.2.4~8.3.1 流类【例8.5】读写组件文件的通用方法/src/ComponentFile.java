//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2015��7��16��
//��8.2 �ֽ���
//��8.3 �ַ���
//����8.5����д����ַ�����ͨ�÷���
//����6.7˼���⡿ʹ���ַ�����д�ı��ļ�����Ϊ��ѡ��ť���⡣
//����8.8��  �ı��ļ��༭����

import javax.swing.*;
import java.io.*;

public class ComponentFile 
{
    
    //������8��4-0��������6.7˼���⡿RoseNJFrame��
    //��filenameָ���ļ������ı��ļ��ж�ȡ�Ķ����ַ�����ÿ���ַ�����Ϊradios��ѡ��ť������һ����ť�ı���
    public static void readFromText(String filename, JRadioButton[] radios)
    {
        try
        {
            Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd = new BufferedReader(reader);//�����ַ������������ļ��ַ�����Ϊ����Դ
            String line;
                //ÿ�ζ�ȡһ���ַ�������ȡ��������ǰ�ť���������ַ�������ʱ����null
            for(int i=0; i<radios.length && (line=bufrd.readLine())!=null; i++)
            	radios[i].setText(line);                   //���ð�ť����Ϊ��ȡ���ַ���
            bufrd.close();
            reader.close();
        }
        catch(IOException e){}                             //����IOException�쳣���ļ�������ʱ������ȡ
    }
    
    //������8������6.7˼���⡿RoseNJFrame��
    //����ѡ��ť����radios�еĸ���ť���⣬д����filenameָ���ļ������ı��ļ���ÿ����ռһ��
    public static void writeToText(String filename, JRadioButton[] radios)
    {
        try
        {
            Writer wr = new FileWriter(filename);          //�ļ��ַ������
            for(int i=0; i<radios.length; i++)
            	wr.write(radios[i].getText()+"\r\n");      //д�뵥ѡ��ť����,һ���ַ���
            wr.close();
        }
        catch(IOException ex) {}                           //д�����ݴ���ʱ�׳�IOException�쳣
    }

    //������8������6.7˼���⡿RoseNJFrame��
    //����filenameָ���ļ����Ķ����ļ��ж�ȡ����objs�������顣ʹ�ö�����
    public static <T> void readObjectFrom(String filename, T[] objs)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in); //���ֽ���������Ϊ����Դ
            for (int i=0; i<objs.length; i++)
            {
                try
                {
                    objs[i] = (T)objin.readObject();       //��ȡ����
                }
                catch (EOFException eof)                   //��������������ʱ�׳�EOFException�쳣
                {
                    break;
                }
            }
            objin.close();                                 //�ȹرն�����
            in.close();                                    //�ٹر��ļ���
        }
        catch(Exception ex){}     //����IOException��ClassNotFoundException�쳣���ļ�������ʱ������ȡ
    }
    
    //������8������6.7˼���⡿RoseNJFrame��
    //��objs�����еĶ���д��filenameָ���ļ����Ķ����ļ���ʹ�ö�����
    public static <T> void writeObjectTo(String filename, T[] objs)
    {
    	try
        {
    	    OutputStream out = new FileOutputStream(filename);  //�ļ��ֽ������,true
            ObjectOutputStream objout = new ObjectOutputStream(out);//�����������ֽ��������Ϊ����Դ
            for(int i=0; i<objs.length; i++)
                objout.writeObject(objs[i]);               //�������д��һ������
            objout.close();                                //�ȹرն�����
            out.close();                                   //�ٹر��ļ���
        }
        catch(IOException ex) {}
    }
}
