//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��2��
//��6.1.2 ���ֹ���
//2. �߲��ֹ�����

import java.awt.*;

public class BorderLayoutFrame extends Frame
{
    public BorderLayoutFrame()
    {
        super("BorderLayout");                        //���Ĭ�ϲ�����BorderLayout
        this.setSize(340,140);

        this.add(new Button("��"),BorderLayout.EAST);  //�������ڿ�ܶ���
        this.add(new Button("��"),"South");
        this.add(new Button("��"),"West");
        this.add(new Button("��"),"North");
        Button button_center = new Button("��"); 
        this.add(button_center,BorderLayout.CENTER);   //����ʡ��Ϊthis.add(button_center);      
        this.setVisible(true);
    }

    public static void main(String[] arg)
    {
        new BorderLayoutFrame();
    }
}
//@author��Yeheya��2016-9-2