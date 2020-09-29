//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��12��9��
//��9.3.1  UDP Socket��Ե�ͨ��
//����9.4����Ե����죬����UDP���ݱ�ͨ��ʵ�֡�
//ͼ���û�����������9.0��û�С����ߡ���ť��

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

//����UDP Socketͨ�ŵĵ�Ե�����������࣬�̳п���࣬��Ӧ�����¼���
//û�����߳�����
public class ChatUDPJFrame extends JFrame implements ActionListener
{
    private String name;                         //����
    private InetAddress destip;                  //Ŀ����������IP��ַ
    private int destport;                        //Ŀ�������Ľ��ն˿�
    private JTextArea text_receiver;             //��ʾ�Ի����ݵ��ı���
    private JTextField text_sender, text_port;   //���������ı��кͷ��Ͷ˿��ı���
    
    //���췽����nameָ��������receiveportָ���������ն˿ڣ�
    //hostָ��Ŀ����������IP��ַ��destportָ��Ŀ�������Ľ��ն˿�
    public ChatUDPJFrame(String name, int receiveport,  String host, int destport) throws Exception
    {
        super("������"+name+"  "+InetAddress.getLocalHost().toString()+" : "+receiveport);
        this.setBounds(320,240,560,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //�����ڿ�����ݴ����в������ʾ�Ի����ݵ��ı���
        this.text_receiver = new JTextArea();              
        this.text_receiver.setEditable(false);
        this.text_receiver.setFont(new Font("����", Font.BOLD, 20));
        this.getContentPane().add(new JScrollPane(this.text_receiver));
        
        //���¹��������з������ݡ��˿��ı��кͷ��Ͱ�ť
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"South");
        toolbar.add(this.text_sender=new JTextField(30));  //���������ı���
        JButton button = new JButton("����");
        toolbar.add(button);
        button.addActionListener(this);   
        toolbar.add(new JLabel("�˿�"));
        toolbar.add(this.text_port=new JTextField());      //���Ͷ˿��ı���
        this.text_port.setHorizontalAlignment(JTextField.CENTER); //����ˮƽ���뷽ʽΪ����
        this.setVisible(true);
        
        //���»���Լ�������Ŀ��������IP��ַ�ͽ��ն˿�
        this.name = name;
        this.destip=InetAddress.getByName(host); //Ŀ����������IP��ַ 
        this.destport=destport;                  //Ŀ�������Ľ��ն˿�

        //���½������ݱ�������ѹ����ð������ݣ����ֽ�����ת�����ַ�����ʾ���ı�����
        byte[] data = new byte[512];             //�����ֽ�����洢���ݱ�������
        DatagramPacket packet=new DatagramPacket(data,data.length);//�����������ݱ���
        DatagramSocket datasocket=new DatagramSocket(receiveport); //��������Socket
        while(datasocket!=null)   
        {
            datasocket.receive(packet);          //�������ݱ�����packet
            //�¾���packet�����ֽ����鹹���ַ�����ʹ��Ĭ���ַ���GBK
            this.text_receiver.append(new String(packet.getData(),0,packet.getLength())+"\r\n");
        }
    }
        
    public void actionPerformed(ActionEvent event)  //����"����"��ť
    {
        if(event.getActionCommand().equals("����"))
        {
            //���½��ַ���ת�����ֽ����飬ʹ��Ĭ���ַ���GBK���ٷ���
            byte[] data=(name+" ˵��"+this.text_sender.getText()).getBytes();
            try
            {
                DatagramPacket packet=new DatagramPacket(data, data.length, destip, destport);
                DatagramSocket datasocket = new DatagramSocket();    //��һ�����ö˿����ڷ���
                datasocket.send(packet);                             //�������ݱ���
                this.text_port.setText(datasocket.getLocalPort()+"");//��ʾ�������Ͷ˿�
                this.text_receiver.append("��˵��"+this.text_sender.getText()+"\n");
                this.text_sender.setText("");
            }
            catch(SocketException ex)            //Socket�쳣
            {
                JOptionPane.showMessageDialog(this, "IP��ַ��˿ڴ���");
                System.out.println(ex.getClass().getName());
            }
            catch(IOException ex)                //����Socket�쳣
            {
                JOptionPane.showMessageDialog(this, "IP��ַ��˿ڴ��󣬷��ʹ���");
                System.out.println(ex.getClass().getName());
//                ex.printStackTrace();
            }   
        }
    }

    public static void main(String[] args) throws Exception
    {   //ָ���������������ն˿ڡ�Ŀ������IP��ַ�ͽ��ն˿�
        new ChatUDPJFrame("����", 20001, "127.0.0.1", 20002);
    }
}
//@author��Yeheya��2017��12��12��