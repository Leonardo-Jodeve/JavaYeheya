//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��8��7��
//��12.3.2 �б��
//����12.4�� �б�����ѡ���������ƶ���

import java.awt.*;
import javax.swing.*;

//ͼ���б��Ԫ��Ⱦ���࣬�̳�JCheckBox��ѡ���࣬ʵ���б��Ԫ��Ⱦ���ӿڣ�
//���б��洢ͼ���ļ����ַ��������б��Ԫ��Ⱦ�ɸ�ѡ�����ͼ�ꡣ
public class IconListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //���ø�ѡ�����ԡ�������jlist���б��value��jlist��ǰ�����isSelected�Ƿ�ѡ��
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //���ø�ѡ��������б��ǰ������
        this.setSelected(isSelected);                      //����ѡ��״̬���б��ǰ���ѡ��״̬
        this.setForeground(isSelected ? Color.red : Color.black);          //ѡ��������ɫ��ʾ
        this.setBackground(isSelected ? Color.lightGray : Color.white);    //ѡ�����ǳ��ɫ
        this.setIcon(new ImageIcon(".\\����\\"+value.toString()+".gif"));  //���ͼ��
        return this;                                       //������Ⱦ���ĸ�ѡ�����
    }
}
//@author��Yeheya��2018��8��9��
/*Ҳ�ɣ�ֻ��������߶Ƚ�С��̫�������ÿ�
public class IconRenderer extends JLabel implements ListCellRenderer<Object>
{
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());
        this.setBackground(isSelected ? Color.lightGray : Color.white);  //ѡ���������ǳ��ɫ
        this.setForeground(isSelected ? Color.red : Color.black);        //ѡ���������ɫ��ʾ
        this.setIcon(new ImageIcon(".\\����\\"+value.toString()+".gif")); //���ͼ��
        return this;
    }
}*/
