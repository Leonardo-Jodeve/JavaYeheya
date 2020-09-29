//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��7��27��
//��8.4.1 �ļ��༰�������
//����8.6��  ���ֲ��������ļ��б�
//��˼����8-5�� �� ���沥���б��ļ������ö����ļ������б���е�File����
//�� �����ļ��������������ı��ļ������ļ���������Ͽ��е��ļ������ַ�����
//�� ʹ��ͨ�÷�����ListModelObjectFile��ComboBoxText���������8.5����
//�� û����ѡ���ļ��Ի��򣬻�û�н�����
//û������Ͽ����������γ�����⡣

import java.awt.event.*;
import javax.swing.*;

//��ʾ���ֲ������ļ��б�Ŀ���࣬���ö����ļ����ı��ļ����沥���б�
public class FileListJFrame extends ListJFrame implements WindowListener
{
    protected JTextField text;                   //�ı���
    private String filterfilename;               //����������Ͽ���ļ���
    private int itemcount=0;                     //��Ͽ������

    //���췽����filenameָ���ļ�����ֵ��filterfilenameָ������������Ͽ���ļ���
    public FileListJFrame(String filename, String filterfilename)
    {
        super();
        //�����ڹ���������ӱ�ǩ���ı��С���ť�����
        this.toolbar.add(new JLabel("�ļ���"));
        this.toolbar.add(this.text=new JTextField(filename,10));
        String[] str={"��","����"};
        for(int i=0; i<str.length; i++)          //��Ӱ�ť
        {
            JButton button = new JButton(str[i]);
            button.addActionListener(this);
            this.toolbar.add(button);
        }
        
        //���¸��Ĺ���������Ͽ�����
        this.filterfilename = filterfilename;
        ListModelText.readFrom(this.filterfilename, this.combox);    //��ȡ�ı��ļ��е��ַ�������ӵ���Ͽ�������
        this.itemcount = this.combox.getItemCount();  //�����Ͽ������
        this.addWindowListener(this);            //��ܼ��������¼�
    }
    
    public void actionPerformed(ActionEvent event) //�����¼�������������
    {
        super.actionPerformed(event);
        
        if(event.getSource() instanceof JButton)
        {
            String filename = this.text.getText();
            switch(event.getActionCommand()) 
            {
                case "��":                       //����"��"��ť
                    if(filename.endsWith(".obj"))
                        ListModelObjectFile.readFrom(filename, this.listmodel);
                        //��ȡfilenameָ�������ļ�����������listmodel�б��ģ���У��������8.5��
                    break;

                case "����":
                    if(filename.endsWith(".obj"))
                        ListModelObjectFile.writeTo(filename, this.listmodel);
                        //��listmodel�б��ģ����������д��filenameָ�������ļ����������8.5��
            }
            this.setTitle("���ֲ��������ļ��б�  "+filename);   //����������ļ���
        }
    }

    public void windowClosing(WindowEvent e)               //���ڹر��¼�������
    {
        if(this.itemcount<this.combox.getItemCount())      //��Ͽ���������ʱ
            ListModelText.writeTo(this.filterfilename, this.combox);
                                       //����Ͽ�������д���ı��ļ����������8.5��
    }
    public void windowOpened(WindowEvent e)      {}
    public void windowActivated(WindowEvent e)   {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e)      {}
    public void windowIconified(WindowEvent e)   {}
    public void windowDeiconified(WindowEvent e) {}
    
    public static void main(String[] args) 
    {
        new FileListJFrame("�ҵ�����.obj", "filter.txt");
//        new FileListJFrame("�ҵ�����.txt");
    }
}
//@author��Yeheya��2018��7��27��