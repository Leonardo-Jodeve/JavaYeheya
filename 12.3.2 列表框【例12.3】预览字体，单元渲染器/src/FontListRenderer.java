//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��8��8��
//��12.3.2 �б��
//  2. �б��Ԫ��Ⱦ��
//�ڡ���12.3����Ŀ�У�����6.4���б���á�

import java.awt.*;
import javax.swing.*;

//ָ�������б��Ԫ��Ⱦ���࣬���б��Ԫ��Ⱦ�ɸ�ѡ����ָ��������ʾ
public class FontListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    private Font font;                           //����
    
    public FontListRenderer(Font font)
    {
        this.font = font;
    }
    public FontListRenderer()
    {
       this(new Font("����", Font.BOLD, 12));
    }
    
    //���ø�ѡ�����ԡ�������jlist���б��value��jlist��ǰ�����isSelected�Ƿ�ѡ��
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //���ø�ѡ��������б��ǰ������
        this.setFont(this.font);                           //��������
        this.setSelected(isSelected);                      //����ѡ��״̬���б��ǰ���ѡ��״̬
        this.setForeground(isSelected ? Color.red : Color.black);    //ѡ��������ɫ��ʾ
        this.setBackground(isSelected ? Color.lightGray : Color.white);//ѡ�����ǳ��ɫ
        return this;                                       //������Ⱦ���ĸ�ѡ�����
    }
}
//@author��Yeheya��2018��8��8��