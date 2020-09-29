//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��28��
//��8.4.1 �ļ��༰�������
//����8.5��  ���ֲ��������ļ��б�
//��1��ʵ���ļ��������ӿڵ��࣬���ļ�����Ϊ����������

import java.io.*;

public class PrefixExtFileFilter implements FileFilter     //�ļ����������࣬ʵ�ֹ������ӿ�
{
    private String prefix="", extension="";                //�ļ���ǰ׺�Ӵ����ļ���չ��
    
    //���췽����filterstrָ�������������㷨������е��ļ���ǰ׺����չ���ֱ�洢��prefix��extension��
    //û�в�����"*.*"����ʾ�����ļ�
    public PrefixExtFileFilter(String filterstr)
    {
        filterstr = filterstr.toLowerCase();               //���ַ�������ĸȫ��ת��Сд
        int i = filterstr.indexOf('*');                    //Ѱ��ͨ���'*'
        if(i>0)
            this.prefix = filterstr.substring(0,i);        //'*'֮ǰ���ַ������ļ���ǰ׺
        i = filterstr.lastIndexOf('.');                    //Ѱ������'.'
        if(i>0)
        {
            this.extension = filterstr.substring(i+1);     //'.'����ַ������ļ���չ��
            if(this.extension.equals("*"))                 //ʶ��"*.*"
                this.extension = "";
        }
    }
    public PrefixExtFileFilter()                 //û�й�����������ʾ�����ļ�����Ŀ¼�б�
    {
        this("");//*.*");                        //"*.*"ͬ��
    }
    
    //���˲�������file�ļ�������ļ���ǰ׺����չ���� prefix��extensionƥ�䣬�򷵻�true��
    //����file�ļ����������ļ��б���
    public boolean accept(File file)
    {
        if(!file.isFile())                       //�ж�ָ��file�����Ƿ����ļ�
            return false;
        String filename = file.getName().toLowerCase(); //���ļ����ַ���ת����Сд��ĸ�ٱȽ�
        return filename.startsWith(this.prefix) && filename.endsWith(this.extension);
    }
}
//@author��Yeheya��2017��8��28�գ���Ϧ