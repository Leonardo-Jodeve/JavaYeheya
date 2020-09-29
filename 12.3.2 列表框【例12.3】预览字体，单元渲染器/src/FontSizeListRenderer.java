//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2016��10��2��
//��12.3.2 �б��
//2. �б��Ԫ��Ⱦ��
//�ڡ���12.3����Ŀ�С�����6.5˼���⡿�ֺ���Ͽ���

import java.awt.*;
import javax.swing.*;

//�ֺ��б��Ԫ��Ⱦ���࣬�̳�JCheckBox��ѡ���࣬ʵ���б��Ԫ��Ⱦ���ӿڣ�
//���б��洢�ֺ����������б��Ԫ��Ⱦ�ɸ�ѡ�򣬷ֱ��Ը����ֺ���ʾ��
public class FontSizeListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //���ø�ѡ�����ԡ�������jlist���б��value��jlist��ǰ�����isSelected�Ƿ�ѡ��
    public Component getListCellRendererComponent(JList<?> jlist, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //���ø�ѡ��������б��ǰ������
        this.setFont(new Font("Timers New Roman", Font.BOLD, (Integer)value));     //�������壬value���ֺ�
        this.setSelected(isSelected);                      //����ѡ��״̬���б��ǰ���ѡ��״̬
        this.setForeground(isSelected ? Color.red : Color.black);    //ѡ��������ɫ��ʾ
        this.setBackground(isSelected ? Color.lightGray : Color.white);//ѡ�����ǳ��ɫ
        return this;                                       //������Ⱦ���ĸ�ѡ�����
    }
}
//@author��Yeheya��2016-10-7��2018��8��8��