//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��8��7��
//��12.3.2 �б��
//����12.4�� �б�����ѡ���������ƶ���

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

//�б�����ѡ���������ƶ�����࣬�̳п���࣬��Ӧ�����¼���ʵ���ļ��������ӿ�
public class ListMultiSelectJFrame extends JFrame implements FileFilter, ActionListener
{
    private JList<String> jlist_source;          //Դ�б��
    private DefaultListModel<String> listmodel_source, listmodel_dest; //�б��ģ��
    
    public ListMultiSelectJFrame()
    {
        super("�б�����ѡ���������ƶ�"); 
        this.setBounds(200,200,450,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BoxLayout layout = new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS);
        this.getContentPane().setLayout(layout);           //���ÿ�����ݴ���Ϊ��ʽˮƽ����
//        this.getContentPane().setLayout(new FlowLayout()); //�����֣����ѿ�
        
        //���´���Դ�б���������ָ��Ŀ¼�µ��ļ���
        this.listmodel_source = new DefaultListModel<String>();//Դ�б��ģ��
        File[] files=new File("����","").listFiles(this);    //��ù��˵��ļ��б�
        for(int i=0; i<files.length; i++)
        {      
            int index=files[i].getName().lastIndexOf(".gif");  //�����ļ���չ��
            this.listmodel_source.addElement(files[i].getName().substring(0,index));
                                                   //Դ�б��ģ������ļ������������ļ���չ����
        }
        this.jlist_source = new JList<String>(this.listmodel_source);//�����б��
        this.getContentPane().add(new JScrollPane(this.jlist_source));
        this.jlist_source.setCellRenderer(new IconListRenderer());   //���õ�Ԫ��Ⱦ��
        
        //�������Box��������������Ӱ�ť
        Box box = new Box(BoxLayout.Y_AXIS);               //Box�����Ժ�ʽ��ֱ���� 
        this.getContentPane().add(box);
        String[] buttonstr={"����","����"};
        for(String str:buttonstr)
        {
            JButton button = new JButton(str);
            button.addActionListener(this);
            box.add(button);
        }

        this.listmodel_dest = new DefaultListModel<String>();//����б��ģ��
        this.getContentPane().add(new JScrollPane(new JList<String>(listmodel_dest)));
        this.setVisible(true);
    }

    public boolean accept(File file)             //�ļ����˷�����ʵ���ļ��������ӿ�
    {
        return file.getName().toLowerCase().endsWith(".gif");//�ļ���չ��ƥ��
    }    
    
    public void actionPerformed(ActionEvent event)         //�����¼���������������ť
    {
        switch(event.getActionCommand())
        {
            case "����":                //����"��"��ť������Դ�б��ѡ�ж������б��
                try
                {
                    //�¾䵱�б��ѡ�ж���ʱ�������б��϶���ֻ����ArrayList<T>��������LinkedList<T>
                    ArrayList<String> list = (ArrayList<String>)this.jlist_source.getSelectedValuesList();
                    for(String str : list)
                        this.listmodel_dest.addElement(str);   //�б��ģ�����������
                }
                catch(ClassCastException ex)        //����б��գ���û��ѡ��һ����׳��쳣
                {
                    JOptionPane.showMessageDialog(this, "�б��գ���û��ѡ����������ܲ���");
                }
                break;
            
            case "����":                //����"����"��ť������Դ�б���������������б��
                int count=this.listmodel_source.getSize(); //���Դ�б�����������
                this.listmodel_dest.removeAllElements();   //ɾ������б������������
                for(int i=0; i<count; i++)                 //����б��ģ�����������
                    this.listmodel_dest.addElement(listmodel_source.elementAt(i));
//                break;
        }
    }
    
    public static void main(String arg[])
    {
        new ListMultiSelectJFrame();
    }
}
//@author��Yeheya��2018��8��9��