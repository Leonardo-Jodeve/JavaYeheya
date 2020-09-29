//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��23��
//��8.2.3  �ļ��ֽ���
//����8.1��  �����ʹ���ֽ�����
//��2�����ֽ����ж�д4���ֽ���Ϊ1��int����������ֽ���

import java.io.*;
public class FileStream_byte4 
{
    public static void main(String[] args) throws IOException
    {
        //����һ����д4���ֽڣ���4��5����
        String filename = "FileStream_byte4.int";          //ָ���ļ���
        OutputStream out = new FileOutputStream(filename); //�����ļ��ֽ����������
        int value=-128;
        System.out.println(value+"("+MyInteger.toBinaryString(value)+")");
        out.write(value>>>24);                   //int��������24λ����λ��0����д��int�������λ1���ֽ�
        out.write(value>>>16);
        out.write(value>>>8);
        out.write(value);                        //д���������λ1���ֽ�
        out.close();                             //�ر��ļ��ֽ������

        InputStream in = new FileInputStream(filename);  //�����ļ��ֽ�������
        while((value=in.read())!=-1)                     //��ȡ1���ֽڣ��ֽ�����������-1
        {
            int tmp;
            System.out.print(value+"  ");                  //��int�������
            for(int j=0; j<3 && (tmp=in.read())!=-1; j++) //�ٶ�ȡ3���ֽڣ�ƴ��һ��int����
            {
            	value = value<<8 | tmp;                    //����8λ���ټ����λ1���ֽڣ�<<���ȼ���|��
//            value = (value<<8) + tmp;                    //Ҳ��,����8λ���ټ����λ1���ֽڣ�<<��+���ȼ���
                System.out.print(value+"  ");              //��int�������
            }
        }
        System.out.println(value);        
        in.close();                                        //�ر��ļ��ֽ�������

    
        //����������д����Ϊ4���ֽ�����
//        String filename2 = "fileStream2.int";            //ָ���ļ���
        out = new FileOutputStream(filename,true);         //�����ļ��ֽ����������ָ���ļ������ڣ��򴴽����ļ���û���׳��쳣
        byte[] buffer = new byte[4];                       //int���ֽ���Ϊ4
        value=-129;
        System.out.println(value+"("+MyInteger.toBinaryString(value)+")");
        for(int i=buffer.length-1; i>=0; i--)              //��int����value��ֳ�4���ֽڣ�������buffer�ֽ�������
        {
            buffer[i] = (byte)value;                       //��value���1���ֽڣ�������buffer�ֽ�������
            value >>>=8;                                   //����8λ����λ0���
        }
        out.write(buffer);                                 //��buffer�ֽ�����д���ֽ������
        out.close();                                       //�ر��ļ��ֽ������

        in = new FileInputStream(filename);      //�����ļ��ֽ�����������ָ���ļ������ڣ����׳�java.io.FileNotFoundException�쳣
        int count=0;
        while((count=in.read(buffer))!=-1)       //�����ֽ����飬���ض�ȡ���ֽ���������������-1
        {
            value=0;
            for(int i=0; i<buffer.length; i++)             //��int����value��ֳ�4���ֽڣ�������buffer�ֽ�������   //�ٶ�ȡ3���ֽڣ�ƴ��һ��int����
            {
                value = value<<8 | buffer[i];              //����8λ���ټ����λ1���ֽڣ�<<��|���ȼ���
                System.out.print(value+"  ");              //��int�������
            }
            System.out.println();        
        }
        in.close();                                    //�ر��ļ��ֽ�������
        
//    	FileStream.copy(filename, filename+" - ����");     //��4�渴���ļ�����5���ƶ�����8.6
    }
}
/*
�������н�����£�
-128(11111111111111111111111110000000)
65535  16777215  -128  
-129(11111111111111111111111101111111)
-1  -1  -1  -128  
-1  -1  -1  -129  

*/
//@author��Yeheya��2015-7-15��2017��8��23��