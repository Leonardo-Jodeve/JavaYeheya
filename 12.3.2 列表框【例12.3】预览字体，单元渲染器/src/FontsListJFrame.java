//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��1��
//��12.3.2 �б��
//����12.3�� Ԥ�����壬ʹ���б��Ԫ��Ⱦ����

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

//Ԥ������Ŀ���࣬�̳п���࣬��Ӧ�б��ѡ���¼�
public class FontsListJFrame extends JFrame implements ListSelectionListener
{
    private JList<String> jlist;                 //�б�򣬴洢ϵͳ������
    private JTextArea text;                      //�ı�����Ԥ������
    
    public FontsListJFrame()
    {
        super("Ԥ������");
        this.setBounds(400,200,500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsName=ge.getAvailableFontFamilyNames();//�������ϵͳ�������ַ���
        this.getContentPane().add(new JScrollPane(this.jlist=new JList<String>(fontsName)));
        this.jlist.addListSelectionListener(this);         //�б�����ѡ���¼�
        this.jlist.setCellRenderer(new FontNameListRenderer());  //�б�����õ�Ԫ��Ⱦ��
        this.getContentPane().add(this.text=new JTextArea(" Welcome  ��ӭ"),"South");
        this.setVisible(true);
    }

    public void valueChanged(ListSelectionEvent event)      //���б����ѡ��������ʱ����
    {
        //�¾����б��ѡ����������Ϊ��������������(������,����,�ֺ�)
        String selected = (String)jlist.getSelectedValue();
//        System.out.println(selected);                      //һ��ѡ��ִ�����θ��¼�����Ϊʲô
        this.text.setFont(new Font(selected,Font.BOLD,56));
    }
    
    public static void main(String[] args) 
    {
        new FontsListJFrame();
    }
}
//@author��Yeheya��2018��2��1�գ�2018��8��8��