//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��18��
//��6.1.1 AWT���
//����6.1���ӷ���������

import java.awt.*;                               //����AWT��

//�ӷ�����������࣬�̳п���ࡣ
//�ص㣺Frame��3���ı��У�û�г�Ա������û���¼���
public class AddFrame extends Frame
{
    public AddFrame()                            //���췽��
    {
        //�������ÿ�ܵı��⡢�ߴ硢λ�á�����ɫ�����ֵ�����
        super("�ӷ�����");                        //���ÿ�ܱ���
        this.setSize(400, 90);                   //��������ߴ�
        this.setLocation(300, 240);              //�����������ʾλ��
//        this.setBackground(Color.lightGray);     //��������ı�����ɫΪǳ��ɫ
//        this.setLayout(new FlowLayout());        //���ÿ�������֣�����
//        this.setLayout(new BorderLayout());        //����Ĭ�ϱ߲���
        
        //�����ڿ������ӱ�ǩ���ı��С���ť�����
        this.add(new TextField("10",8));         //����ı���(��ֵ,���)
        this.add(new Label("+"));                //��ӱ�ǩ��� 
        this.add(new TextField("20",8));
        this.add(new Button("="));               //��Ӱ�ť(����)
        this.add(new TextField(10));              //����ı���(��ֵĬ��Ϊ"")
        this.setVisible(true);                   //��ʾ��ܣ���������������
    }
    
    public static void main(String[] arg)
    {
        new AddFrame();
    }
}
//@author��Yeheya��2018��7��19��