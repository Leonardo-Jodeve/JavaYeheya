//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��2��
//��12.3.3   ���ĵ�����
//����12.5��  ���ĵ�������ı��ļ��༭����  //�̳���8.7
//���ñ���·������6.5����8.5����8.7��

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.beans.PropertyVetoException;

//���ĵ�������ı��ļ��༭���࣬�̳���8.7�ı��༭��
public class MDITextEditorJFrame extends TextEditorJFrame
{
    private JDesktopPane desktop;                          //���洰��
    private ButtonGroup buttongroup;                       //��ť��
    
    public MDITextEditorJFrame(File file)                  //���췽����fileָ���ļ�����
    {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().remove(1);                   //ɾ��������ݴ����е�text�ı�������6.5����
        this.desktop = new JDesktopPane();                 //���洰��
        this.getContentPane().add(desktop);                //�����洰����ӵ���ܵ����ݴ���
        this.buttongroup = new ButtonGroup();              //��ť��
        new TextJIFrame(file);                             //������ʾ�ı��ļ����ڲ����
    }
    public MDITextEditorJFrame()                           //���췽��
    {
        this(new File(""));
    }    

    //��ʾ�ı��ļ����ڲ�����࣬�ڲ��࣬ʵ���ڲ���ܴ����¼��������ӿ�
    private class TextJIFrame extends JInternalFrame implements InternalFrameListener
    {
        File file;                                         //�ı��ļ�
        JTextArea text;                                    //�ı��� 
        JRadioButtonMenuItem rbmenuitem;                   //��ѡ�˵���
        
        TextJIFrame(File file)         //���췽���������ڲ���ܣ���ȡfile�ı��ļ�������ʾ��text�ı���
        {
            super("", true, true, true, true);
            this.setSize(640, 480);
            this.addInternalFrameListener(this);           //ע���ڲ���ܴ����¼�������
            desktop.add(this);                             //���洰������ڲ����
            JInternalFrame inner = desktop.getSelectedFrame();  //������洰��ǰѡ���ڲ����
            if(inner!=null)                                //���ø��ڲ���ܼ�����ʾ
                this.setLocation(inner.getX()+50, inner.getY()+50);

            this.text = new JTextArea();
            this.text.setForeground(colors[0]);            //�����ı�����ɫ
//          this.text.setFont(new Font("�����п�", 1, 50)); //�����ı�������
            this.getContentPane().add(new JScrollPane(this.text));    
//            actionPerformed(new ActionEvent(MDITextEditorJFrame.this.combox_size,0,"comboBoxChanged"));//�����¼�����������ʼ��
            this.text.add(MDITextEditorJFrame.this.popupmenu);  //�ı�������ⲿ��Ŀ�ݲ˵�
            this.text.addMouseListener(MDITextEditorJFrame.this);
                                       //�ı���ע������¼������������ⲿ�൱ǰʵ���ṩ�¼�������
            
            this.rbmenuitem = new JRadioButtonMenuItem(this.getTitle(),true);  //��ѡ�˵���
            this.rbmenuitem.addActionListener(MDITextEditorJFrame.this);//��ѡ�˵���ע�ᵥ���¼������������ⲿ�൱ǰʵ���ṩ�¼�������
            MDITextEditorJFrame.this.buttongroup.add(this.rbmenuitem);  //�ⲿ��İ�ť����ӵ�ǰ��ѡ�˵���
            MDITextEditorJFrame.this.menus[5].add(this.rbmenuitem);     //�ⲿ��Ĵ��ڲ˵���ӵ�ǰ��ѡ�˵���

            this.file = file;
            if(file==null)                                 //�ļ����󲻿�ʱ
                this.file = new File("");
            this.rbmenuitem.setText(this.file.getName());  //���ļ������ô��ڲ˵��µĵ�ѡ�˵������
            this.setTitle(this.file.getName());
            JTextAreaText.readFrom(this.file, this.text);  //��ȡfile�ļ�������ʾ��text�ı����У�����8.5
            this.setVisible(true);
        }
    
        //���·���ʵ��InternalFrameListener�ӿ�
        public void internalFrameOpened(InternalFrameEvent event)//��ʱ
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameActivated(InternalFrameEvent event)//�����ڲ����ʱ
        {
            System.out.println(event.paramString());
            MDITextEditorJFrame.this.text = this.text;     //�ı��ⲿ���text���ã�ʹ�����������ڵ�ǰtext
            this.rbmenuitem.setSelected(true);             //���ö�Ӧ��ѡ�˵���Ϊѡ��״̬
        }
        public void internalFrameDeactivated(InternalFrameEvent event)//ʧȥ����ʱ
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameClosing(InternalFrameEvent event)//�ر��ڲ����ʱ
        {
            MDITextEditorJFrame.this.buttongroup.remove(this.rbmenuitem);  //�ⲿ��İ�ť��ɾ����ǰ��ѡ�˵���
            MDITextEditorJFrame.this.menus[5].remove(this.rbmenuitem);     //�ⲿ��Ĵ��ڲ˵�ɾ����ǰ��ѡ�˵���
        }
        public void internalFrameClosed(InternalFrameEvent event)//�رպ�
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameIconified(InternalFrameEvent event)//��С��
        {
            System.out.println(event.paramString());            
        }
        public void internalFrameDeiconified(InternalFrameEvent event)//��С���ٻָ�
        {
            System.out.println(event.paramString());            
        }
        
    }//TextJIFrame�ڲ������

    public void actionPerformed(ActionEvent event)             //�����¼������������Ǹ���ͬ������
    {
        super.actionPerformed(event);                          //���ø���ĵ����¼�������
                                      //���е��õ�actionMenuItem(e)����ִ�б���ķ���ʵ�֣�����ʱ��̬
        if(event.getSource() instanceof JRadioButtonMenuItem)  //������ѡ�˵���
            this.setSelected(new File(event.getActionCommand()));//���õ�ǰ��ѡ�˵����Ӧ�ļ����ڲ����Ϊѡ��״̬
    }
    protected void actionMenuItem(ActionEvent event)       //�ļ��˵���ĵ����¼������������Ǹ���ͬ������
    {
        if(event.getActionCommand().equals("�½�"))
        {
            new TextJIFrame(null);                         //�����ڲ����
            return; 
        }
        if(event.getActionCommand().equals("��") && fchooser.showOpenDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {                                                  //��ʾ���ļ��Ի����ҵ���"��"��ť            
            File file = fchooser.getSelectedFile();        //����ļ��Ի���ĵ�ǰѡ���ļ�
            if(!this.setSelected(file))                    //����file�ļ��Ƿ��Ѵ�
                new TextJIFrame(file);                     //�����ڲ����            
            return;
        }
       
        if(desktop==null)
           return;
        TextJIFrame inner=(TextJIFrame)desktop.getSelectedFrame();//�������洰���е�ǰ����ڲ����
        if(inner==null)
            return;
        if(event.getActionCommand().equals("����") && !inner.file.getName().equals(""))
            JTextAreaText.writeTo(inner.file, inner.text); //�����ļ����ݣ�����8.5
        else if((event.getActionCommand().equals("����") && inner.file.getName().equals("") ||
                event.getActionCommand().equals("���Ϊ")) && fchooser.showSaveDialog(this)==0)//JFileChooser.APPROVE_OPTION)
        {                      //������ļ���ִ��"���Ϊ"�˵���ʱ����ʾ�����ļ��Ի����ҵ���"����"��ť
            inner.file = fchooser.getSelectedFile();
            if(!inner.file.getName().endsWith(".txt"))
                inner.file= new File(inner.file.getAbsolutePath()+".txt");  //����ļ���չ��
            inner.setTitle(inner.file.getName());          //�����ڲ���ܱ���
            JTextAreaText.writeTo(inner.file, inner.text); //�����ļ����ݣ�����8.5
            inner.rbmenuitem.setText(inner.file.getName());//��ѡ��ť�˵������
        }
    }
   
    public boolean setSelected(File file)                  //����file�ļ��Ƿ��Ѵ򿪣��Ѵ�������ѡ��״̬
    {
        JInternalFrame inners[] = desktop.getAllFrames();  //�������洰���е������ڲ����
        int i=0;
        for(i=0; i<inners.length; i++)                     //����file�ļ��Ƿ��Ѵ�
        {
            File f=((TextJIFrame)inners[i]).file;
            if(file.getName().equals(f.getName()))
                break;
        }
        if(i<inners.length)
            try
            {
                inners[i].setSelected(true);               //ѡ���ڲ���ܣ����ظ���
                return true;
            }
            catch(PropertyVetoException pve) {}            //
//            {   pve.printStackTrace();
//            }
        return false;
    }
    
    public static void main(String arg[])
    {
        new MDITextEditorJFrame(new File("��ʫ\\�佭��.txt"));
    }
}
//@author��Yeheya��2018��2��2��