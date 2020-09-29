//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��2��
//��8.2.5  �����ֽ���
//��12.1 ���Ͽ��

import java.util.*;
import javax.swing.*;
import java.io.*;

//���϶����ļ��࣬Ϊ�����ṩ��д�����ļ���ͨ�÷���
public class CollectionFile 
{
    //����������������12.6������
    //����filename�����ļ��ж�ȡ��T�������ӵ�coll�����С�
    //�ɽ���LinkedList<T>������ʵ����
    public static <T> void readFrom(String filename, Collection<T> coll)  
    {
        try
        {
            InputStream in = new FileInputStream(filename);//�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in);  //�����ֽ�������
            coll.clear();                                  //��ռ���
            while(true)
            {
                try
                {
                    coll.add((T)objin.readObject());       //������Ӷ�ȡ�Ķ���
                }
                catch(EOFException ex)           //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            }
            objin.close();                       //�رն�����
            in.close();                          //�ر��ļ���
        }
        catch(FileNotFoundException ex)         //���ļ������ڣ�������ļ�
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
    
    //��coll�����е�����T�����д��filenameָ���ļ����Ķ����ļ�������12.6������
    public static <T> void writeTo(String filename, Collection<T> coll)
    {
        try
        {
            OutputStream out = new FileOutputStream(filename);//�ļ��ֽ������
            ObjectOutputStream objout = new ObjectOutputStream(out);//�����ֽ������
//          for(Iterator<T> it=coll.iterator(); it.hasNext(); )    //������
//              objout.writeObject(it.next());           //д�뼯�ϵ�ǰ����
            for(T obj : coll)                    //��Ԫѭ����obj���list�б���ÿ��Ԫ��
                objout.writeObject(obj);         //д�뼯�ϵ�ǰ�Ķ���
            objout.close();                      //�رն�����
        }
        catch(FileNotFoundException ex)          //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
//Ҳ��      catch(IOException ex)                   //�����ļ��������쳣
//      {   JOptionPane.showMessageDialog(this, "�ļ�����\""+filename+"\"��д�����ݴ���");
//      }
    }
    
    //������12.6δ���ã� ���γ����12-13�� �绰�������á�
    //����file�����ļ��ж�ȡ��T�������ӵ�coll������
    public static <T> void readFrom(File file, Collection<T> coll) 
    {
        try
        {
            InputStream in = new FileInputStream(file);    //�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(in);//�����ֽ�������
            coll.clear();                                  //��ռ���
            while(true)
            {
                try
                {
                    coll.add((T)objin.readObject());       //������Ӷ�ȡ�Ķ���
                }
                catch(EOFException ex)                     //����������������ʱ�׳��ļ�β�쳣
                {
                    break;
                }
            }
            objin.close();                                 //�ȹرն�����
            in.close();                                    //�ٹر��ļ���
        }
        catch(FileNotFoundException ex)                    //���ļ������ڣ�������ļ�
        {
            if (!file.getName().equals(""))
                JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"�ļ������ڡ�");
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
        
    //��coll�����е�����T�����д��fileָ���Ķ����ļ���ͨ�÷���
    //���γ����12-13�� �绰�������á�
    public static <T> void writeTo(File file, Collection<T> coll) 
    {
        try
        {
            OutputStream out=new FileOutputStream(file);   //�ļ��ֽ������
            ObjectOutputStream objout = new ObjectOutputStream(out);//�����ֽ������
            for(T obj : coll)                              //��Ԫѭ����obj���coll������ÿ��Ԫ��
                objout.writeObject(obj);                   //�������д��һ������
            objout.close();                                //�رն�����
            out.close();                                   //�ر��ļ���
        }
        catch(FileNotFoundException ex)                    //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(null, "\""+file.getName()+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "д���ļ�ʱ���ݴ���");
        }
//Ҳ��      catch(IOException ex)                            //�����ļ��������쳣
//      {
//          JOptionPane.showMessageDialog(this, "�ļ�����\""+filename+"\"��д�����ݴ���");
//      }
    }
}
//@author��Yeheya��2018��8��23��