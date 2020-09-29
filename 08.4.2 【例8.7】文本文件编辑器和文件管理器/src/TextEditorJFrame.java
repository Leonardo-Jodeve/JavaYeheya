//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��29��
//��8.3.1  �ַ�����
//��8.4   �ļ�����
//��8.4.1 �ļ��༰�������
//��8.4.2 �ļ�ѡ��Ի������
//����8.6��  �ı��ļ��༭�����ļ���������
//��1���ı��ļ��༭�����̳���6.5�ı��༭��
//MyEclipse���ñ���·����������6.5����8.5��Ŀ��
//����JTextAreaText��ComboBoxDataFile�����8.5��Ŀ��

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//�ı��༭������࣬�̳���6.5�ı��༭������˼����8-6��ʵ�ִ����¼�������
public class TextEditorJFrame extends EditorJFrame implements WindowListener
{
    private File file;                                     //�ļ�����
    protected JFileChooser fchooser;                       //�ļ�ѡ��Ի���
    private String sizefilename;                           //�ֺ������ļ�������˼����8-6��
    private int itemcount=0;                               //��Ͽ����������˼����8-6��
    
    public TextEditorJFrame(File file)                     //���췽����fileָ���ļ�����
    {
        super();                                           //ͼ���û�����ͬ��6.5�ı��༭��
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   //�ͷŴ���
        this.fchooser = new JFileChooser(new File("��ʫ",""));//�����ļ��Ի���ָ����ʼ·��
        this.fchooser.setFileFilter(new ExtensionFileFilter("�ı��ļ�(*.txt)","txt"));//�����ļ�������
        
        this.file = file;                                  //�ļ�����
        if(file==null)
            this.file = new File("");
        JTextAreaText.readFrom(this.file, this.text);      //��ȡ�ı����ı�����������8.5��
        this.setTitle("�ı��༭��  "+this.file.getName());   //����������ļ���
        
        //���¡�˼����8-6�����̲�ûд
        this.sizefilename = "FontSize.int";
        ListModelDataFile.readFrom(this.sizefilename, this.combox_size);//��ȡ�����ļ�����Ͽ�������
        this.addWindowListener(this);                      //���ע�ᴰ���¼�������
        this.itemcount = this.combox_size.getItemCount();  //�����Ͽ������
    }
    public TextEditorJFrame(String filename)               //���췽����filenameָ���ļ�·����
    {
    	this(new File(filename));
    }
    public TextEditorJFrame()                              //���췽��
    {
        this(new File(""));        //����
    }

    public void actionPerformed(ActionEvent event)         //�����¼������������Ǹ���ͬ������
    {
        super.actionPerformed(event);                      //���ø���Ķ����¼�������
        actionMenuItem(event);                             //�ļ��˵���Ķ����¼�������
    }
    
/*	//��5��
    protected void actionMenuItem(ActionEvent event)       //�ļ��˵���Ķ����¼�������
    {
        String mitem = event.getActionCommand();           //�˵�����
        //�������������½����˵���򴴽�һ���ļ������ÿ�ܴ��ڱ��⣬����ı���
        if(mitem.equals("�½�"))
        {
            this.file = new File("");                      //����һ���ļ�
            this.setTitle("�ı��༭��  ");                   //���ÿ�ܴ��ڱ���
            this.text.setText("");                         //����ı���
            return;
        }
        
        //���µ��������򿪡��˵�������ļ��Ի����ҵ����ˡ��򿪡���ťʱ����ѡ���ļ���ȡ���ı���
        if(mitem.equals("��") && fchooser.showOpenDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {
        	this.file = fchooser.getSelectedFile();        //����ļ��Ի����ѡ���ļ�
            JTextAreaText.readFrom(this.file, this.text);  //��ȡ�ı��ļ����ı���������8.5
            this.setTitle("�ı��༭��  "+this.file.getName());
            return;
        }
        
        if(mitem.equals("����") && !this.file.getName().equals(""))//���������桱�˵���
            JTextAreaText.writeTo(this.file, this.text);   //�����ļ����ݣ�����ʾ�ļ��Ի���
        
        //������������ļ���ִ�С����Ϊ���˵�����������ļ��Ի����ҵ����ˡ����桱��ť���򱣴��ļ�����
        else if((mitem.equals("����") && this.file.getName().equals("") ||
                  mitem.equals("���Ϊ")) && fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {
            this.file = fchooser.getSelectedFile();        //����ļ��Ի����ѡ���ļ�
            if(!file.getName().endsWith(".txt"))
                this.file= new File(this.file.getAbsolutePath()+".txt");  //����ļ���չ��
        	JTextAreaText.writeTo(this.file, this.text);   //�����ļ�����
            this.setTitle("�ı��༭��  "+this.file.getName());
        }
    }*/
    
    //��6��
    protected void actionMenuItem(ActionEvent event)       //�ļ��˵���Ķ����¼�������
    {
        switch(event.getActionCommand())                   //�����Ĳ˵�����
        {
            case "�½�":       //�������������½����˵���򴴽�һ���ļ������ÿ�ܴ��ڱ��⣬����ı���
                this.file = new File("");                  //����һ���ļ�
                this.setTitle("�ı��༭��  ");               //���ÿ�ܴ��ڱ���
                this.text.setText("");                     //����ı���
                break;
        
            //�������������򿪡��˵���򵯳����ļ��Ի����ҵ������ˡ��򿪡���ťʱ����ѡ���ļ���ȡ���ı���                
            case "��":
                if(fchooser.showOpenDialog(this)==0)  //�������ļ��Ի����ҵ������ˡ��򿪡���ťʱ//JFileChooser.APPROVE_OPTION)
                {
                    this.file = fchooser.getSelectedFile();        //����ļ��Ի����ѡ���ļ�
                    JTextAreaText.readFrom(this.file, this.text);  //��ȡ�ı��ļ����ı���������8.5
                    this.setTitle("�ı��༭��  "+this.file.getName());
                }
                break;
                
            case "����":       //���������������桱�˵�����file�ļ�����ʱ�������ļ����ݣ�����ʾ�ļ��Ի���
                if(!this.file.getName().equals(""))
                {
                    JTextAreaText.writeTo(this.file, this.text); //�����ļ�����
                    break;
                }
                //ע�⣬�˴�û��break;����ִ�����¡����Ϊ��������
                
//              else if((mitem.equals("����") && this.file.getName().equals("") ||
//              mitem.equals("���Ϊ")) && 
            //���������������桱�˵�����file�ļ�Ϊ�գ��򵥻������Ϊ���˵��
            //�򵯳������ļ��Ի����ҵ������ˡ����桱��ťʱ�������ļ�����
            case "���Ϊ":
                if(fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
                {
                    this.file = fchooser.getSelectedFile();        //����ļ��Ի����ѡ���ļ�
                    if(!file.getName().endsWith(".txt"))
                        this.file= new File(this.file.getAbsolutePath()+".txt");  //����ļ���չ��
                    JTextAreaText.writeTo(this.file, this.text);   //�����ļ�����
                    this.setTitle("�ı��༭��  "+this.file.getName());
                }
        }
    }
    
    //������������/дfile�ı����ַ�����text�ı����������8.5
//    public void readFrom(File file, JTextArea text)
//    public void writeTo(File file, JTextArea text)
    
    public static void main(String[] arg)
    {
//        new TextEditorJFrame();
//        new TextEditorJFrame(new File(""));
        new TextEditorJFrame(new File("��ʫ\\���ݴ�.txt"));
    }
    
    //���¡�˼����8-6��
    //�� �ı��༭�����ӹ��ܣ�ʹ�������ļ������ֺ���Ͽ�������
    //���򿪴���ʱ����ȡ�ļ��е�������ӵ���Ͽ�������
    //����޸Ļ��������ֺţ���رմ���ʱ���������ֺ�����д��ָ�������ļ��С�
    
    //������ͨ�÷������������8.5����ĿListModelDataFile��
//    public void readFrom(String filename, JComboBox<Integer> combox)
//    public void writeTo(String filename, JComboBox<Integer> combox)
    
    public void windowClosing(WindowEvent e)               //���ڹر��¼�������
    {
    //    if (this.itemcount<this.combox_size.getItemCount())//��Ͽ���������ʱ
        	ListModelDataFile.writeTo(this.sizefilename, this.combox_size);//����Ͽ�������д�������ļ�
    }
    public void windowOpened(WindowEvent e)      {}
    public void windowActivated(WindowEvent e)   {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e)      {}
    public void windowIconified(WindowEvent e)   {}
    public void windowDeiconified(WindowEvent e) {}
}

/*δ��
    public TextEditorJFrame(String filename)               //���췽����filenameָ�����򿪵��ļ���
    {
        this(new File(filename));                          //��filename==null���׳��ն����쳣
    }
    
    //��ȡfileָ���ı��е��ַ�������ӵ�text�ı����У�ʹ���ַ���
    public void readFrom(File file, JTextArea text)
    {
        try
        {   //�¾䴴�������ַ���������������Դ���ļ��ַ���
            BufferedReader bufreader = new BufferedReader(new FileReader(file));
            text.setText("");
            String line;
            while((line=bufreader.readLine())!=null) //��ȡһ���ַ�������������������null
            {
                text.append(line+"\r\n");             //���ַ���line��ӵ�text�ı�����
//                try                                  //try��䣬��5��ûд
//                {
//                    Thread.sleep(300);               //���У�sleep��������ʾ
//                }
//                catch(InterruptedException ex)
//                {
//                    return;
//                }//2018��5��12�գ���8.2����������
            }
            bufreader.close();
        }
        catch (IOException ioex){}               //���ļ������ڻ�IO������ȡ�ļ�
    }
    
*/
//@author��Yeheya��2017��8��29�գ�2017��11��28�գ�2018��8��2�գ�2018��11��3��