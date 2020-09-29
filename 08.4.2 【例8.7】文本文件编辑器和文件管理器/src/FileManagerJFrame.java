//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��29��
//��8.3   �ַ���
//��8.4   �ļ�����
//��8.4.1 �ļ��༰�������
//��8.4.2 �ļ�ѡ��Ի������
//����8.6��  �ı��ļ��༭�����ļ���������
//��3���ļ�������
//���ñ���·������6.5 �ı��༭����

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//�ļ�����������࣬�̳п���࣬��Ӧ�����¼�
public class FileManagerJFrame extends JFrame implements ActionListener
{
    private File dir;                                      //�ļ����󣬱�ʾָ��Ŀ¼
    private File[] files;                                  //����ָ��Ŀ¼�������ļ�
    private JTextField text;                               //��ַ������ʾĿ¼·����״̬��
    private JTable jtable;                                 //�����ʾָ��Ŀ¼�������ļ�����Ŀ¼������
    private DefaultTableModel tablemodel;                  //���ģ��

    public FileManagerJFrame()
    {
        super("�ļ�������");
        this.setBounds(300,200,600,480);                   //���ô���λ�ü���С
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addMenu();                                    //��Ӵ��ڲ˵�

        this.dir = new File(".");                          //������ʾ��ǰĿ¼���ļ�����
        String path = this.dir.getAbsolutePath();          //��þ���·��
        path = path.substring(0,path.length()-1);
        this.dir = new File(path);

//        this.dir = new File("../");                      //��ǰĿ¼����һ��Ŀ¼
//      this.dir = new File("");                           //��ǰĿ¼�����̵ĸ�Ŀ¼
//      this.dir = new File("D:\\");                       //��ʾD�̸�Ŀ¼D:\
        this.text = new JTextField(this.dir.getAbsolutePath());//��ʾĿ¼·��
        this.getContentPane().add(this.text,"North");
        this.text.addActionListener(this);
        
        String[] titles={"����","��С","����","�޸�����"};
        this.tablemodel = new DefaultTableModel(titles,0); //Ĭ�ϱ��ģ�ͣ�titlesָ���б��⣬0��
        this.jtable = new JTable(this.tablemodel);         //�������ָ�����ģ��
        this.getContentPane().add(new JScrollPane(this.jtable)); //�������񣨰��������ӵ�������ݴ����в�
        listFilesToTableModel();                           //���ģ����ʾ��ǰĿ¼���ļ��б�
        this.setVisible(true);
    }
       
    private void addMenu()                                 //��Ӵ��ڲ˵�
    {
        JMenuBar menubar = new JMenuBar();                 //�˵���
        this.setJMenuBar(menubar);                         //�������Ӳ˵���
        String[] menustr={"�ļ�","�༭","�鿴","����"};
        String[][] menuitemstr={{"��","������","ɾ��","ˢ��","����","|","�˳�"},
                                {"����","����","ճ��","|","���Ƶ������ļ���"},
                                {"�����ϼ�"},{}};
        JMenu[] menus = new JMenu[menustr.length];         //�˵�����
        for(int i=0; i<menuitemstr.length; i++)            //��Ӳ˵��Ͳ˵���
        {
            menus[i] = new JMenu(menustr[i]);              //�˵�
            menubar.add(menus[i]);                         //�˵����м���˵�
            for(int j=0; j<menuitemstr[i].length; j++)
                if(menuitemstr[i][j].equals("|"))
                    menus[i].addSeparator();               //�ӷָ���
                else 
                {
                    JMenuItem menuitem = new JMenuItem(menuitemstr[i][j]); //�����˵���
                    menus[i].add(menuitem);                //�˵�����뵽�˵�
                    menuitem.addActionListener(this);      //�˵���ע�ᶯ���¼�������
                }
        }
        JMenu menu_new = new JMenu("�½�");                  //���½����˵��ǡ��ļ����˵����Ӳ˵�
        menus[0].insert(menu_new,0);                         //�˵����뵽�˵��г�Ϊ�����˵�
        String[] menuitemstr_new={"�ļ���","�ı��ĵ�"};       //���´����˵����ӵ����½����˵�
        for(int i=0; i<menuitemstr_new.length; i++)
        {
            JMenuItem menuitem = new JMenuItem(menuitemstr_new[i]);//�����˵���
            menu_new.add(menuitem);                        //�˵�����뵽�˵�
            menuitem.addActionListener(this);              //Ϊ�˵���ע�ᶯ���¼�������
        }
    }
    
    private void listFilesToTableModel()                   //���ģ����ʾ��ǰĿ¼���ļ��б�
    {
        this.files = this.dir.listFiles();                 //����ָ��Ŀ¼���ļ������б�û�й���
        for(int i=this.tablemodel.getRowCount()-1; i>=0; i--)  //ɾ�����ģ����������
            this.tablemodel.removeRow(i);
        this.tablemodel.setRowCount(this.files.length);    //���ñ��ģ�͵����� 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(int i=0; i<this.files.length; i++)             //���ģ�������
        {
       	    this.tablemodel.setValueAt(this.files[i].getName(),i,0); //��ʾ�ļ���
       	    if(this.files[i].isFile())                     //��ǰFile�������ļ�
       	        this.tablemodel.setValueAt(this.files[i].length()+"B", i, 1); //��ʾ�ļ���С
       	    if(this.files[i].isDirectory())                //��ǰFile������Ŀ¼
                this.tablemodel.setValueAt("�ļ���", i, 2); //��ʾ�ļ�����
            String timestr=sdf.format(new Date(files[i].lastModified()));//�ļ��޸�ʱ��
       	    this.tablemodel.setValueAt(timestr, i, 3);
        }
    }
    
    public void actionPerformed(ActionEvent event)         //�����¼�������
    {
        if(event.getSource()==this.text)                   //�����ı���
            this.dir = new File(this.text.getText());      //����ָ���ļ���

        //����д����switch��䡣
        else if(event.getActionCommand().equals("�����ϼ�"))//�������鿴|�����ϼ����˵���ʱ
        {
//            System.out.println(this.dir.toString());
            this.dir = this.dir.getParentFile();
            if(this.dir!=null)
                this.text.setText(this.dir.getAbsolutePath());  //�����ı��У����ٸշ��ص�Ŀ¼��
            else JOptionPane.showMessageDialog(this, "û���ϼ�Ŀ¼");
        }

        else if(event.getActionCommand().equals("�ļ���"))    //�������ļ�|�½�|�ļ��С��˵���
            new File(this.dir,"�½��ļ���").mkdir();          //Ϊ�ļ����󴴽�һ��Ŀ¼

        else if(event.getActionCommand().equals("�ı��ĵ�"))  //�������ļ�|�½�|�ı��ĵ����˵���
        {
            try
            {
                new File(this.dir,"�½��ı��ĵ�.txt").createNewFile(); //Ϊ�ļ����󴴽�һ�����ļ�
            }
            catch (IOException ex){}
        }
        int i = this.jtable.getSelectedRow();               //���ر��ǰѡ�е��кţ���0����û��ѡ��ʱ����-1
        if(event.getActionCommand().equals("������") && i!=-1)   //�������ļ�|���������˵���
        {
            String name=(String)this.tablemodel.getValueAt(i,0);
            //�¾䵯������Ի����ͼ6-14(c)��������ȷ������ť���������ַ�����������ȡ������ť����null
            String filename = JOptionPane.showInputDialog(this, "�ļ���", name);
            if(filename!=null && filename!="")
            {
                if(this.files[i].isFile() && !(filename.endsWith(".txt") || filename.endsWith(".TXT")))
                    filename +=".txt";
                this.files[i].renameTo(new File(this.dir,filename));//�ļ�����������
            }
        }

        else if(event.getActionCommand().equals("��") && i!=-1) //�����˵���ʱ
        {
            if(this.files[i].isFile())
            {
                String fname = this.files[i].getName().toLowerCase();   //���ѡ���ļ�������ĸСд
                if(fname.endsWith(".txt") || fname.endsWith(".java"))  //ƥ���ļ���չ����������ĸ��Сд
                    new TextEditorJFrame(this.files[i]);   //���ı��ļ��༭��
                else
                    JOptionPane.showMessageDialog(this, "���ܴ����������ļ�");
            }
            else                                           //��ʾѡ��Ŀ¼���ļ��б�
            {
                this.dir = this.files[i];
                this.text.setText(this.dir.getAbsolutePath());
            }
        }
        else if(event.getActionCommand().equals("ɾ��") && i!=-1)
        {
            if(this.files[i].isFile())                     //��ǰ���ļ���������Ŀ¼
            {
                if(JOptionPane.showConfirmDialog(this, "ɾ��\""+this.files[i].getName()+"\"�ļ���")==0)
                    this.files[i].delete();                //ɾ���ļ�
            }
            else if(JOptionPane.showConfirmDialog(this, "ɾ��\""+this.files[i].getName()+"\"�ļ�����������Ŀ¼���ļ���")==0)
       	            this.deleteDir(files[i]);              //ɾ��ָ��Ŀ¼�е�������Ŀ¼���ļ�
        }
        if(event.getActionCommand().equals("���Ƶ������ļ���") && i!=-1) //�������༭ | ���ơ��˵���ʱ
        {
            if(this.files[i].isFile())                     //�������ļ���������Ŀ¼
            {
                File dir_copyto = new File(this.dir,"\\����");  //����ָ��Ŀ¼
                if(!dir_copyto.exists())                   //Ŀ¼������ʱ
                    dir_copyto.mkdir();                    //����Ŀ¼
                File file2 = new File(dir_copyto, this.files[i].getName());  //�������Ƶ��ļ�
                if(!file2.exists() || this.files[i].lastModified()>file2.lastModified())
                {                                          //�ļ������ڻ��ļ������Ҵ������ļ����ڽ���ʱ����
//                    FileStream.copy(this.files[i].getAbsolutePath(), f2.getAbsolutePath());   //ʹ���ֽ��������ļ�
                	FileStream.copy(this.files[i], file2); //�����ļ���ʹ���ֽ�����FileStream���ļ�����8.5��Ŀ
                    file2.setLastModified(this.files[i].lastModified());   //�����ļ�������޸�ʱ������Ϊԭ�ļ�������޸�ʱ��
                }
            }
        }
        listFilesToTableModel();                           //���ģ����ʾ��ǰĿ¼���ļ��б�

        if(event.getActionCommand().equals("����"))        //�������༭ | ���С��˵���ʱ
        {
        }
        if(event.getActionCommand().equals("����"))        //�������༭ | ���ơ��˵���ʱ
        {
        }
        if(event.getActionCommand().equals("ճ��"))        //�������༭ | ճ�����˵���ʱ
        {
        }
    }
    
    //ɾ��dirĿ¼�е�������Ŀ¼���ļ�����Ϊ�ļ�Ŀ¼�ṹ�����������ı����ǵݹ��㷨
    public void deleteDir(File dir)
    {
        File[] files = dir.listFiles();          //����ָ��Ŀ¼�������ļ��б�
        for(int i=0; i<files.length; i++)
        {
            if(files[i].isDirectory())
                deleteDir(files[i]);             //�ݹ���ã���������Ŀ¼
            files[i].delete();                   //ɾ���ļ����Ŀ¼
        }
        dir.delete();
    }
    
    public static void main(String[] arg)
    {
        new FileManagerJFrame();
    }
}
/*
˼���⣺
    ����״̬���������ļ�����Ŀ¼������ǰĿ¼���ļ����ֽ�����
    ����и��ļ���
*/
//@author��Yeheya��2017��8��29�գ�2018��11��4��