//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��10��21��
//��9.2 TCP Socketͨ��
//����9.3��  ���˷ֱ����죬���Ƿ����Ҳ�ǿͻ��ˡ�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

//���˷ֱ��������࣬��Ӧ�����¼�
public class ChatMultiTCPSocketJFrame extends JFrame implements ActionListener
{
    private String name;                         //����
    private JComboBox<String> combox;            //����IP��ַ����������Ͽ�
    private JTextField text_conn;                //ָ���Է��˿��ı���
    private JTabbedPane tab;                     //ѡ�����ÿҳ��ʾ��һ�˵������¼
    
    //���췽����portָ����ʼ�ȴ��˿ڣ�nameָ������
    public ChatMultiTCPSocketJFrame(int port, String name) throws IOException
    {
        super("������  "+name+"  "+InetAddress.getLocalHost().toString());
        this.setBounds(320,240,580,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //���¹���������ʾ�����ȴ�TCP���Ӷ˿ڡ��������ӷ���˵�����IP��ַ�Ͷ˿�
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"North"); 
        toolbar.add(new JLabel("�ȴ��˿�"));
        JTextField text_local=new JTextField(port+"",4);      //�����ȴ��˿��ı���
        text_local.setHorizontalAlignment(JTextField.CENTER); //����ˮƽ���뷽ʽΪ����
        toolbar.add(text_local);
        text_local.setEditable(false);
        toolbar.addSeparator();                               //��������ӷָ��ߣ�����
        toolbar.add(new JLabel("����"));
        String[] address={"","127.0.0.1","202.119.162.123"};  //��֪��IP��ַ
        toolbar.add(this.combox=new JComboBox<String>(address));
        this.combox.setEditable(true);
        toolbar.add(new JLabel("�˿�"));
        toolbar.add(this.text_conn=new JTextField(6));
        this.text_conn.setHorizontalAlignment(JTextField.CENTER); //����ˮƽ���뷽ʽΪ����
        JButton button = new JButton("��������");
        button.addActionListener(this);
        toolbar.add(button);
        this.getContentPane().add(this.tab=new JTabbedPane());  //���ѡ�����
        this.setVisible(true);

        //������Ϊ����ˣ���port��ʼ�˿ڵȴ����ӣ�ÿ����һ��TCP���ӣ������1��Tabҳ
        this.name = name;
        while(true)
        {
            Socket socket=new ServerSocket(port).accept(); //�ȴ����տͻ��˵���������
            this.tab.addTab(this.name, new TabPageJPanel(socket));   //tab�����ҳ��ҳ�����һ���ڲ������
            this.tab.setSelectedIndex(this.tab.getTabCount()-1);//tabָ����ҳΪѡ��״̬
            port++;                                        //���¸��˿ڵȴ��¸��ͻ���
            text_local.setText(port+"");
        }//�رմ���ʱ��whileѭ��ֹͣ���ر�����TCP���Ӻͷ���
    }
    
    public void actionPerformed(ActionEvent event)         //����"��������"��ť
    {
        if(event.getActionCommand().equals("��������"))
        {
            String host = (String)this.combox.getSelectedItem();  //�������IP��ַ
            int port = Integer.parseInt(this.text_conn.getText());//��ö˿ںţ�δ������ֵ��ʽ�쳣
            try
            {
                this.tab.addTab(this.name, new TabPageJPanel(new Socket(host, port))); //tab�����ҳ
                this.tab.setSelectedIndex(this.tab.getTabCount()-1); //tabָ����ҳΪѡ��״̬
            }
            catch(UnknownHostException ex)                 //δ֪�����쳣
            {
                JOptionPane.showMessageDialog(this, "����IP��ַ����");
                System.out.println(ex.getClass().getName());
            }
            catch(ConnectException ex)                     //�����쳣
            {
                JOptionPane.showMessageDialog(this, "IP��ַ��˿ڴ���δ����TCP����");
                System.out.println(ex.getClass().getName());
            }
            catch(IOException ex)
            {
 //               ex.printStackTrace();
                System.out.println(ex.getClass().getName());
            }
        }
    }

    //ѡ�����һҳ������ڲ��࣬����һ��Socket��һ���߳�
    private class TabPageJPanel extends JPanel implements Runnable, ActionListener
    {
        JTextArea text_receiver;                 //��ʾ�Ի����ݵ��ı���
        JTextField text_sender;                  //���뷢�����ݵ��ı���
        JButton[] buttons;                       //���͡����ߡ�ɾ��ҳ��ť
        PrintWriter cout;                        //��ʽ���ַ������
        Socket socket;
    
        TabPageJPanel(Socket socket)             //Ϊÿ��socket����һ��tabҳ
        {
            super(new BorderLayout());
            this.add(new JScrollPane(this.text_receiver=new JTextArea()));
            this.text_receiver.setEditable(false);
        
            //���´������������������ݣ���ӷ��͵����ť
            JToolBar toolbar = new JToolBar();
            this.add(toolbar,"South");
            toolbar.add(this.text_sender=new JTextField(16));
            this.text_sender.addActionListener(this);

            String[] strs={"����","����","ɾ��ҳ"};
            this.buttons = new JButton[strs.length];
            for(int i=0; i<this.buttons.length; i++)
            {
            	this.buttons[i] = new JButton(strs[i]);
                toolbar.add(buttons[i]);
                this.buttons[i].addActionListener(this);
            }
            this.buttons[2].setEnabled(false);   //ɾ��ҳ��ť��Ч
            
            this.socket = socket;
            (new Thread(this)).start();          //�����̣߳���ǰ�����Ϊ�߳�Ŀ�����
        }
        
        public void run()         //�߳����з��������նԷ���Ϣ�����Է��������ַ�����ӵ��ı���
        {
            try
            {   //�¾��Socket����ֽ���������ٴ�����ʽ���ַ������������flush
                this.cout = new PrintWriter(this.socket.getOutputStream(),true);
                this.cout.println(name);         //�����Լ��������Է��������ⲿ��.this.name

                //�������佫Socket���ֽ�������ת�����ַ�����Ĭ��GBK�ַ������ٴ��������ַ�������
                Reader reader = new InputStreamReader(this.socket.getInputStream());
                BufferedReader bufreader = new BufferedReader(reader);
                String line=bufreader.readLine();          //���նԷ����� 
                int index = tab.getSelectedIndex();        //��ǰҳ��tab�е���ţ��ⲿ��.this.tab
                tab.setTitleAt(index, line);               //���Է���������Ϊ��ǰҳ���� 
                while((line= bufreader.readLine())!=null && !line.equals("null"))
                {
                    tab.setSelectedIndex(index);           //�յ��Է���Ϣʱ����ʾ��ҳ
                    this.text_receiver.append(line+"\r\n");
                } 
                bufreader.close();
                reader.close();
                this.cout.close();
                this.socket.close();
                this.buttons[0].setEnabled(false);         //���Ͱ�ť��Ч
                this.buttons[1].setEnabled(false);         //���߰�ť��Ч
                this.buttons[2].setEnabled(true);          //ɾ��ҳ��ť��Ч
            }
            catch(IOException ex){} 
        }
        
        public void actionPerformed(ActionEvent event)     //����tabҳ�ϵ�"����"�Ȱ�ť
        {
            if(event.getSource()==this.buttons[0])         //����
            {
                this.cout.println(name+" ˵��"+this.text_sender.getText());
                this.text_receiver.append("��˵��"+this.text_sender.getText()+"\n");
                this.text_sender.setText("");
            }
            else if(event.getSource()==buttons[1])         //����
            {
            	this.text_receiver.append("������\n");
                this.cout.println(name+"����\n"+"null");
            }            
            else if(event.getSource()==this.buttons[2])
                tab.remove(this);//tab.getSelectedIndex());//ɾ��tab��ǰҳ
        }
    }                                            //TabPageJPanel�ڲ������
    
    public static void main(String[] args) throws IOException
    {
        new ChatMultiTCPSocketJFrame(10001, "������");        //ָ����ʼ�ȴ��˿ں�����
    }
    
    //��5��̲�ûд
    public void finalize()
    {
        //�ر�ServerSocket;
    }    
}
//@author��Yeheya��2017��10��21�գ�2017��12��12��