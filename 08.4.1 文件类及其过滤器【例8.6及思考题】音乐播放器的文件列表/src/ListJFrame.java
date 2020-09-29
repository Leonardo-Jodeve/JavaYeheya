//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��28��
//��8.4.1 �ļ��༰�������
//����8.6��  ���ֲ��������ļ��б�
//��2�����ֲ��������ļ��б�

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

//��ʾ���ֲ������ļ��б�Ŀ���࣬��Ӧ�����¼����б��ѡ���¼����ڲ���
public class ListJFrame extends JFrame implements ActionListener,ListSelectionListener
{
    private JTextField text_path, text_status;   //·���ı��У�״̬�ı���
    protected JComboBox<String> combox;          //����������Ͽ�
    private JList<File> jlist;                   //��ʾ�ļ��б���б��
    protected DefaultListModel<File> listmodel;  //�б��ģ��
    private int count=0, size=0;                 //�ļ����������ļ����ֽ���
    protected JToolBar toolbar;                  //������
    
    public ListJFrame()
    { 
        super("���ֲ��������ļ��б�");
        this.setBounds(300,240,650,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
        
        //�����ڿ�����ݴ��񱱱���ӹ��������������·���ı��С�����������Ͽ��ڿ���ϱ����״̬�ı���
        this.toolbar = new JToolBar();                     //��������Ĭ��ˮƽ����
        this.getContentPane().add(this.toolbar,"North");   //��ܱ�����ӹ�����
        this.toolbar.add(text_path=new JTextField("�ҵ�����",20));  //·���ı���
        this.text_path.addActionListener(this);
        this.getContentPane().add(text_status=new JTextField(),"South"); //����ϱ����״̬�ı���
        
        String[] filternames={"","*.mp3","*.wma","*.*"};   //��������������
        this.combox = new JComboBox<String>(filternames);  //����������Ͽ�
        this.combox.setEditable(true);                     //��Ͽ�ɱ༭
        this.combox.addActionListener(this);               //��Ͽ���������¼�
        this.toolbar.add(this.combox);       
        
        //�����ڿ�����ݴ����в�����б��
        this.listmodel = new DefaultListModel<File>();     //�б��ģ��
        this.jlist = new JList<File>(this.listmodel);      //�б��ָ���б��ģ�͹���������
        this.jlist.addListSelectionListener(this);         //�б�����ѡ���¼������ڲ���
        this.getContentPane().add(new JScrollPane(this.jlist)); //�����Ӱ����б��Ĺ�������
        actionPerformed(new ActionEvent(this.combox,0,"*.mp3"));//���ö����¼�������
        this.setVisible(true);
    }
  
    public void actionPerformed(ActionEvent event)         //�����¼�������
    {
        if(event.getSource()==this.text_path || event.getSource()==this.combox)//�˾�̲�д������������ 
        {
            String filter=(String)this.combox.getSelectedItem();//�����Ͽ�Ĺ�������
            if(filter!=null)    //��˼����8-5��combox.removeAllItems()������Ͽ����¼�������ѡ����Ϊnull
            {
                this.listmodel.removeAllElements();        //�б��ģ��ɾ������������
                count=0; size=0;                           //count��¼�ļ�����size��¼�����ļ����ֽ���
                addList(new File(this.text_path.getText()), new PrefixExtFileFilter(filter));
                this.text_status.setText("���� "+count+" ���ļ������ֽ���Ϊ "+size);
            }
        }
    }
    
    //��dirĿ¼�ļ��б���filterָ�������������е��ļ�������ӵ�listmodel�б��ģ���У�
    //�������ļ������ֽ��������ݹ鷽��
    private void addList(File dir, PrefixExtFileFilter filter)
    {
        File[] files = dir.listFiles(filter);              //����dirĿ¼��filterָ�������������ļ��б�
        count+=files.length;                               //�ļ���
        for(int i=0; i<files.length; i++)
        {
            this.listmodel.addElement(files[i]);           //�б��ģ������ļ�����
            size+=files[i].length();                       //�ļ�����
        }
        
        files = dir.listFiles();                           //����dirĿ¼���ļ��б�û�й��ˣ����������ļ�����Ŀ¼
        for(int i=0; i<files.length; i++)                  //������Ӹ���Ŀ¼�ļ��б��е��ļ�����
            if (files[i].isDirectory())                    //�ж�ָ��file�����Ƿ���Ŀ¼
                addList(files[i], filter);                 //���files[i]��Ŀ¼�ļ��б��е��ļ����󣬵ݹ����
    }
    
    public void valueChanged(ListSelectionEvent event)         //ѡ���б��������ʱ����
    {
//        if (e.getValueIsAdjusting())                     ///һ��ѡ��ִ�����θ��¼�����Ϊʲô
//          this.personPanel.set((Person)this.jlist.getSelectedValue());
    }
    
    public static void main(String[] arg)
    {
        new ListJFrame();
    }
}
/*
File dir = new File(".","");             //��ǰĿ¼
File dir = new File("./","");
File dir = new File(".\\","");

File dir = new File("../","");           //��ǰĿ¼����һ��Ŀ¼

File dir = new File("","");              //��ǰĿ¼�����̵ĸ�Ŀ¼

File dir = new File("C:","");            //C�̸�Ŀ¼C:\
File dir = new File("C:/","");
File dir = new File("C:\\","");
*/
//@author��Yeheya��2017��8��28�գ���Ϧ