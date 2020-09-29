//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��28��
//����8.5��  �����������ļ��б�
//��1��Լ���ļ���ǰ׺���ļ���չ�����ļ���������ʵ���ļ��������ӿ�
//�������ļ������ˣ��������⣬�����ļ��У����磬���ļ���.mp3��

import java.io.*;
public class PrefixExtFilenameFilter implements FilenameFilter  //������ʵ�ֹ������ӿ�
{
    private String prefix="", extension="";                //�ļ���ǰ׺���ļ���չ��
    
    public PrefixExtFilenameFilter(String filterstr)       //filterstrָ�����˴���dirָ��Ŀ¼
    {
        filterstr = filterstr.toLowerCase();               //���ַ�������ĸȫ��ת��Сд
        int i = filterstr.indexOf('*');                    //Ѱ��ͨ���
        if(i>0)
            this.prefix = filterstr.substring(0,i);        //���*֮ǰ���ַ���
        i = filterstr.lastIndexOf('.');                    //Ѱ���ļ���չ��
        if(i>0)
        {
            this.extension = filterstr.substring(i+1);     //���.֮����ļ���չ���ַ���
            if(this.extension.equals("*"))                 //ʶ��"*.*"
                this.extension = "";
        }
    }
    public PrefixExtFilenameFilter()
    {
        this("*.*");
    }
    public boolean accept(File dir, String filename)
    {
        filename = filename.toLowerCase();         //���ļ����ַ���ת����Сд��ĸ
        return filename.startsWith(this.prefix) && filename.endsWith(this.extension);//�ļ�ǰ׺����չ��ƥ��
    }
}
