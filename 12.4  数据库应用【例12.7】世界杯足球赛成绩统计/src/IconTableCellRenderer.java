//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��14��
//��12.4 ���ݿ�Ӧ��
//����12.7�� ���籭�������ɼ�ͳ�ơ�
//��6�� ���Ԫ��Ⱦ��

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

//ͼ����Ԫ��Ⱦ���࣬�̳б�ǩ����࣬ʵ�ֱ��Ԫ��Ⱦ���ӿڣ�
//�����Ԫ����Ⱦ�ɴ�ͼ��ı�ǩ���
public class IconTableCellRenderer extends JLabel implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable jtable, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
    {
        this.setText(value.toString());          //��ʾ�����
        this.setIcon(new ImageIcon("����/"+value.toString()+".gif"));  //����ͼ�꣬�ļ���ͬ����
        return this;
    }
}
//@author��Yeheya��2018��3��14��