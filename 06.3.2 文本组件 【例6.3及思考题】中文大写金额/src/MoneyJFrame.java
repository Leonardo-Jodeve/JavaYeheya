//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��9��
//��6.3.2 �ı���ʾ�ͱ༭������¼�
//����6.3��  ���Ĵ�д��
//��5���ֺ��洰�ڴ�Сд���ı䡣

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//���Ĵ�д������࣬�̳п���ࣻ��Ӧ�ı��༭�¼���Ҳ����Ӧ�����¼���������¼�
public class MoneyJFrame extends JFrame implements CaretListener, ComponentListener
{
    private JTextField text_money, text_str;     //�����ı���
    private MessageJDialog jdialog;              //�Ի����ڲ������

    public MoneyJFrame()
    {
        super("���Ĵ�д���");
        this.setBounds(300,240,420,110);                   //���ڴ�С��λ��
        this.setBackground(java.awt.Color.lightGray);      //JFrame����ɫĬ��ǳ��
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //�������ڹرհ�ť��������������
//        this.setResizable(false);                          //���ڴ�С���ܸı�
        this.addComponentListener(this);                   //���ע������¼����������ı��ֺ�
        
        //�������ÿ�ܵ����ݴ���Ϊ���������Ҷ��룬�����ݴ�����������
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
//        this.getContentPane().setLayout(new GridLayout(2,2)); //������񲼾֣�2��2�У�����
        this.getContentPane().add(new JLabel("���", JLabel.RIGHT));  //��ӱ�ǩ���Ҷ��룩
        this.text_money = new JTextField("12345678.90",40);
        this.text_money.setHorizontalAlignment(JTextField.RIGHT);//����ˮƽ�����Ҷ���
        this.getContentPane().add(this.text_money);
        this.text_money.addCaretListener(this);            //�ı���ע��༭�¼�������

        this.getContentPane().add(new JLabel("���Ĵ�д", JLabel.RIGHT));
        this.text_str = new JTextField(40);                //��ͼ24����ʾ40
        this.text_str.setHorizontalAlignment(JTextField.RIGHT);
        this.text_str.setEditable(false);                  //ֻ����ʾ��������༭
        this.getContentPane().add(this.text_str);
        caretUpdate(null);                                 //ִ���ı��༭�¼�
        this.setVisible(true);
        this.jdialog = new MessageJDialog();               //�����Ի�����󣬵����ڲ����˽�й��췽��
//        this.jdialog = new MessageJDialog(this);         //�����Ի������static����������Ϊ�����ⲿ��
    }
    
    private class MessageJDialog extends JDialog           //��Ϣ�Ի���˽��ʵ���ڲ��࣬����Ƕ��
    {
        private JLabel jlabel;                             //��ʾ��Ϣ�ı�ǩ
        private MessageJDialog()                           //�ڲ���Ĺ��췽�� 
        {
            //�¾���MoneyJFrame.this�����ⲿ�൱ǰ���󣨶Ի��������Ŀ�ܣ���true��ʾģʽ����
            super(MoneyJFrame.this, "��ʾ", true);
            this.setSize(900,100);
            this.jlabel = new JLabel("", JLabel.CENTER);   //��ǩ���ַ���Ϊ�գ����ж���
            this.getContentPane().add(this.jlabel);        //�Ի�������ݴ�����ӱ�ǩ
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);  //�Ի���رշ�ʽ�����أ�����������
            //�¾�Ի���ע������¼���������ί���ⲿ���this�������¼�
            this.addComponentListener(MoneyJFrame.this);
        }
        private void show(String message)                  //�Ի�����ʾ��Ϣ
        {
            this.jlabel.setText(message);                  //��ǩ��ʾ��Ϣ
            this.setLocation(MoneyJFrame.this.getX()+100, MoneyJFrame.this.getY()+100);
                             //�Ի���λ���ڿ�ܵ����·���MoneyJFrame.this.getX()��getY()��ÿ��λ��
            this.setVisible(true);                         //��ʾ�Ի���
        }
    } //MessageJDialog�ڲ������

    public void caretUpdate(CaretEvent event)              //�ı��༭�¼�������
    {
        String money = this.text_money.getText();          //����������ַ���
        if(money.isEmpty())
            this.text_str.setText("");
        else
            try
            {
                double x = Double.parseDouble(money);      //��money��ת���ɸ������������׳��쳣
                this.text_str.setText(RMB.toString(x));    //���x�����Ĵ�д��ʽ
            }
            catch(NumberFormatException ex)                //������ֵ��ʽ�쳣
            {
                //��ʾ�Ի��򣬵����ڲ����˽�з���
//                this.jdialog.show("\""+money+"\" ����ת���ɸ����������������롣");
//              jdialog.show(ex.getMessage()+" ����ת���ɸ����������������롣");//��ʾ�Ի��򣬵����ڲ����˽�з���
                JOptionPane.showMessageDialog(this, "\""+money+"\" ����ת���ɸ����������������롣");
            }
            finally{}    	
    }

    //���·���ʵ������¼��������ӿڡ�ͨ�÷��������ǲ��ɶ�����Ϊһ���ࡣ
    //���ı��ܴ�Сʱ������������������������ֺţ�ʹ֮���ſ�ܳߴ���ı��С
    public void componentResized(ComponentEvent event)
    {
//        System.out.println(event.getComponent().getClass().getName()); //��ǰJFrame��JDialog
        Component comp=event.getComponent();               //����¼�Դ���������JFrame��JDialog
        int size =(comp.getWidth()+comp.getHeight())/40;   //�����ֺ�
        Font font = new Font("����",1,size);                //����
        if(comp instanceof JFrame)              //comp==this
        {
            int n= this.getContentPane().getComponentCount();  //��ÿ�����ݴ����е��������
            for(int i=0; i<n; i++)                         //���ÿ�����ݴ������������������
                this.getContentPane().getComponent(i).setFont(font);
        }
        else if(comp instanceof JDialog)
            this.jdialog.jlabel.setFont(font);   //���öԻ����б�ǩ���������
    }
    public void componentMoved(ComponentEvent event) {}    //�ƶ����
    public void componentShown(ComponentEvent event) {}    //��ʾ���
    public void componentHidden(ComponentEvent event){}    //�������
    
    public static void main(String[] arg)
    {
        new MoneyJFrame();
    }
}
/*
�ڲ���Ҳ������Ϊstatic����������JFrame����������ʹ��MoneyJFrame.this���á�
    Ҳ������MessageJDialogΪ�����ⲿ�࣬����һ����ʾ��Ϣ��ͨ�öԻ���

/*

//        System.out.println(panel.getLayout().getClass().getName());//javax.swing.JToolBar$DefaultToolBarLayout
        this.getContentPane().add(panel,"North"); 
//      System.out.println(this.getContentPane().getLayout().getClass().getName());//javax.swing.JRootPane$1

*/
//@author��Yeheya��2016-9-9
