//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��28��
//����8.5��  �����������ļ��б�
//˼���⣺Ŀ¼�ļ���������ϰ����ϰ8.8�� 

import java.io.*;
public class DirFilter implements FileFilter     //Ŀ¼�ļ�������
{
    public boolean accept(File file)             //ʵ�ֹ��˲������Ƿ����file�ļ����������ļ��б���
    {
        return file.isDirectory();
    }
}
