//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��5��14��
//��6.3.4 �б�����Ͽ�
//����6.4˼���⡿����panel�����������������壬������ʾ�á�
//��5��̲�û��д����������Ӧ����¼���
//����6.4��ʹ�á������࣬������Ӧ�ó���Ҫ���á�

import java.awt.*;
import javax.swing.*;

public class MyJPanel 
{
    //��5��̲�ûд��ͨ�÷�����
    //����panel�����������������壬�ݹ��㷨
    public static void setFont(JPanel panel, Font font)
    {
        int n= panel.getComponentCount();        //���panel����е��������
        for(int i=0; i<n; i++)
        {
            Component comp = panel.getComponent(i); //���panel����е�i�����
            if((comp instanceof JPanel))         //����JPanel
            	setFont((JPanel)comp, font);     //�ݹ����
            else
            	comp.setFont(font);              //��������
        }
    }
}
//@author��Yeheya��2018��5��14��