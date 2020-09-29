//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��8��23��
//��8.3  �ַ���
//��12.1 ���Ͽ��

import java.util.*;
import javax.swing.*;
import java.io.*;

//�����ı��ļ��࣬Ϊ�����ṩ��д�ı��ļ���ͨ�÷���//����
public class CollectionText 
{
    //����filename�ı��ļ��ж�ȡ���ַ�������ӵ�coll�����С�
    //ʹ�û����ַ������������ж�ȡ���ɽ���LinkedList<T>������ʵ����
    public static void readFrom(String filename, Collection<String> coll)  
    {
        try
        {
        	Reader reader = new FileReader(filename);      //�ļ��ַ�������
            BufferedReader bufrd = new BufferedReader(reader);//�����ַ�������
            coll.clear();                                  //��ռ���
            String line;
            while((line=bufrd.readLine())!=null)           //��ȡһ���ַ�������������������null
                coll.add(line);                            //������Ӷ�ȡ�Ķ���
            bufrd.close();
            reader.close();
        }
        catch(FileNotFoundException ex)                    //���ļ������ڣ�������ļ�
        {
            if(!filename.equals(""))
                JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ���ݴ���");
        }
        //�������쳣�����ļ������ڣ���/д���ݴ�����δ�ҵ������������
      //Ҳ��      catch (IOException | ClassNotFoundException ex){}
    }    
    //���γ����12-13�� �绰�������ܵ��á�
	//����T��ͨ�÷�������Ϊ���ܹ����T����

    
    //��coll�����е�����T�����д��fileָ���Ķ����ļ���ͨ�÷���
    //���γ����12-13�� �绰�������á�
    public static <T> void writeTo(File file, Collection<T> coll) 
    {
        try
        {
        	Writer wr = new FileWriter(file);              //�ļ��ַ������
            for(T obj : coll)                              //��Ԫѭ����obj���coll������ÿ��Ԫ��
                wr.write(obj.toString()+"\n");             //д�������ַ���
            wr.close();
        }
        catch(FileNotFoundException ex)                    //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
    }
}
//@author��Yeheya��2018��8��23��