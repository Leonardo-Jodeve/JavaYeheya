//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��9��
//��6.3.2 �ı���ʾ�ͱ༭������¼�
//����6.3��  ���Ĵ�д����˼����6-3��

import javax.swing.*;

public class MessageJDialog extends JDialog           //��ʾ��ʾ��Ϣ�Ķ��ضԻ����࣬ͨ�ù���
{
    private JFrame jframe;                             //�Ի����������Ŀ�ܴ���
    private JLabel jlabel;                             //�Ի����е���ʾ��Ϣ
    
    public MessageJDialog(JFrame jframe)               //�ڲ���Ĺ��췽��
    {
        super(jframe,"��ʾ",true);                     //jframe���öԻ����������Ŀ�ܴ��ڣ�true��ʾģʽ����
        this.jframe = jframe;
        this.setSize(300,80);
        jlabel = new JLabel("", JLabel.CENTER);        //��ǩ���ַ���Ϊ�գ����ж���
        this.getContentPane().add(jlabel); 
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);  //�����Ի���Ĺرհ�ťʱ�����ضԻ������������������
    }
    public void show(String message)                   //�Ի�����ʾ��Ϣ
    {
        jlabel.setText(message);                      //��ǩ��ʾָ����Ϣ
        this.setLocation(jframe.getX()+100, jframe.getY()+100);//�Ի���λ���ڿ�ܵ����·�
        this.setVisible(true);                         //��ʾ�Ի���
    }
}
//@author��Yeheya��2016-9-9
