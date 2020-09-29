//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��29��
//��8.3   �ַ���
//��8.4   �ļ�����
//��8.4.1 �ļ��༰�������
//��8.4.2 �ļ�ѡ��Ի������
//����8.6��  �ı��ļ��༭�����ļ���������
//��2��JFileChooserʹ�õ��ļ�������
//����12.4��  ���ĵ�������ı��ļ��༭���� 

import java.io.File;

//�ļ��������࣬�ṩ���ļ���չ��Ϊ������������JFileChooser���ʹ�ã�
//�̳�JFileChooser���ļ�������������
public class ExtensionFileFilter extends javax.swing.filechooser.FileFilter
{
    private String description, extension;                 //�ļ������������ļ���չ��
    
    public ExtensionFileFilter(String description, String extension)
    {
        this.description = description;
        this.extension = extension.toLowerCase();
    }

    public boolean accept(File file)                       //���˲�����fileָ���������ļ�
    {
        return file.getName().toLowerCase().endsWith(this.extension);   //�ļ���չ��ƥ��
    }

    public String getDescription()                         //�ļ����������ַ���
    {
        return this.description;
    }
}
//@author��Yeheya��2017��8��29�գ�2017��10��14��