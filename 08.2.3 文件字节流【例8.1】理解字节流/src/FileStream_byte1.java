//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��23��
//��8.2.3  �ļ��ֽ���
//����8.1��  �����ʹ���ֽ�����
//��1�����ֽ����а��ֽڶ�/д������ֽ�����

import java.io.*;
public class FileStream_byte1 
{
    public static void main(String[] args) throws IOException //�׳��쳣����Java���������
    {
        //����ʹ���ļ��ֽ���������������-1д��filenameָ���ļ������ļ�
        String filename = "FileStream_byte1.byte";         //ָ���ļ���
        OutputStream out = new FileOutputStream(filename); //�����ļ��ֽ����������ָ���ļ������ڣ��򴴽����ļ���û���׳��쳣
        out.write(-1);                                     //д���������������1���ֽ�
        out.write(-1);
        out.close();                                       //�ر��ļ��ֽ������

        //����ʹ���ļ��ֽ���������filenameָ���ļ��а��ֽڶ�ȡ
        InputStream in = new FileInputStream(filename);    //�����ļ��ֽ�����������ָ���ļ������ڣ����׳�java.io.FileNotFoundException�쳣
        int i;                        //����filein.read()��1�ֽ���Ϊint�������1�ֽڣ���λ��0
        while ((i=in.read())!=-1)                          //�ֽ�����������-1
            System.out.print("  "+i);                      //��int���������255
            //System.out.print("  "+(byte)i);              //��byte���������-1
        in.close();                                        //�ر��ļ��ֽ�������
    }
}
/*
�������н�����£�
  255  255
*/
//@author��Yeheya��2015-7-15��2017��8��23��