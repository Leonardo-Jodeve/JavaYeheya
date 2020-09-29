//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��18��
//��6.1.1 AWT���
//����6.1˼���⡿�ӷ����������¼�����

import java.awt.*;
import java.awt.event.*;

//�ӷ�����������࣬�̳п���࣬��Ӧ�����¼��ʹ��ڹر��¼���
//û�д�����ֵ��ʽ�쳣��
public class ButtonAddFrame extends Frame implements ActionListener
{
    private TextField[] texts;                  //�ı������飬��������������
    private Button button;
    
    public ButtonAddFrame()
    {
        super("�ӷ�����");                       //���ÿ�ܱ���
        this.setSize(400, 90);                   //��������ߴ�
        this.setLocation(300, 240);              //�����������ʾλ��
        this.setBackground(Color.lightGray);     //��������ı�����ɫΪǳ��ɫ
        this.setLayout(new FlowLayout());        //���ÿ�������֣�����

        this.texts = new TextField[3];           //�ı������飬2����������1��������
        for(int i=0; i<this.texts.length; i++)
        {
            this.texts[i] = new TextField("",6); //�ı���
            this.add(this.texts[i]);
        }
        this.texts[this.texts.length-1].setEditable(false);//ֻ����ʾ��������༭
        this.add(new Label("+"),1);              //��ӱ�ǩ��� 

        this.button = new Button("=");
        this.add(button,3);
        this.button.addActionListener(this);     //��ť���������¼�
        this.setVisible(true);
        this.addWindowListener(new WinClose());  //���ע�ᴰ���¼���������ί��WinClose�������¼�
    }

    public void actionPerformed(ActionEvent e)   //�����¼���������������ť
    {
        int x = Integer.parseInt(this.texts[0].getText());
        int y = Integer.parseInt(this.texts[1].getText());
        this.texts[this.texts.length-1].setText((x+y)+"");
    }
    
    public static void main(String[] arg)
    {
        new ButtonAddFrame();
    }
}
//@author��Yeheya��2018��10��27��