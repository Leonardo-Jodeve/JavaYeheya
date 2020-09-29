//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��10��2��
//��12.3.2 �б��
//  2. �б��Ԫ��Ⱦ��
//����12.3�� Ԥ�����壬ʹ���б��Ԫ��Ⱦ����
//����6.5˼���⡿�ı��༭������������Ͽ�����Ⱦ��

import java.awt.*;
import javax.swing.*;

//�������б��Ԫ��Ⱦ���࣬�̳�JCheckBox��ѡ���࣬ʵ���б��Ԫ��Ⱦ���ӿڣ�
//���б��洢�������ַ��������б��Ԫ��Ⱦ�ɸ�ѡ�򣬷ֱ��Ը�����������ʾ��
public class FontNameListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //���ø�ѡ�����ԡ�������jlist���б��value��jlist��ǰ�����isSelected�Ƿ�ѡ��
    public Component getListCellRendererComponent(JList<?> jlist, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //���ø�ѡ��������б��ǰ������
        this.setFont(new Font(value.toString(), Font.BOLD, 16));     //�������壬value��������
        this.setSelected(isSelected);                      //����ѡ��״̬���б��ǰ���ѡ��״̬
        this.setForeground(isSelected ? Color.red : Color.black);    //ѡ��������ɫ��ʾ
        this.setBackground(isSelected ? Color.lightGray : Color.white);//ѡ�����ǳ��ɫ
        return this;                                       //������Ⱦ���ĸ�ѡ�����
    }
}
//@author��Yeheya��2016��10��2�գ�2018��2��1�գ�2018��8��8��