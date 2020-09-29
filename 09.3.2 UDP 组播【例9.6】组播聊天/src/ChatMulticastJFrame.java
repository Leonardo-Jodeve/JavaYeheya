//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��12��15��
//��9.3.2  UDP�鲥ͨ��
//����9.6�� �鲥���졣

import java.awt.event.*;
import javax.swing.*;
import java.net.*;

//�鲥�������࣬�̳п���࣬��Ӧ�����¼�������UDP�鲥ͨ�� 
public class ChatMulticastJFrame extends JFrame implements ActionListener
{
    private String name;                                   //����
    private InetAddress dip;                               //�鲥��ַ  
    private MulticastSocket mulsocket;                     //�鲥�׽���  
    private int port;                                      //�鲥�˿�
    private JTextField text_sender;                        //���������ı���
    
    //���췽����nameָ��������group��portָ���鲥��ַ�Ͷ˿�
    public ChatMulticastJFrame(String name, String group, int port) throws java.io.IOException
    {
        super("�쳵С��  "+name+"  "+group+"��"+port);
        this.setBounds(320,240,560,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //�����ڿ�����ݴ����в������ʾ�Ի����ݵ��ı���
        JTextArea text_receiver = new JTextArea();
        text_receiver.setEditable(false);
        this.getContentPane().add(new JScrollPane(text_receiver));
        
        //���¹��������з��������ı��кͷ��Ͱ�ť
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"South");
        toolbar.add(this.text_sender=new JTextField(20));
        JButton button = new JButton("����");
        toolbar.add(button);
        button.addActionListener(this);
        this.setVisible(true);
        
        //���»���Լ��������鲥��ַ�Ͷ˿�
        this.name = name;
        this.dip = InetAddress.getByName(group);
        this.port = port;

        //���½������ݱ�������ѹ����ð������ݣ����ֽ�����ת�����ַ�����ʾ���ı�����
        byte[] data = new byte[512];
        DatagramPacket packet = new DatagramPacket(data,data.length); //���������ݱ���
        this.mulsocket = new MulticastSocket(port);        //�鲥Socket
        this.mulsocket.joinGroup(this.dip);                //������
        while(this.mulsocket!=null)
        {
            this.mulsocket.receive(packet);                //�������ݱ���
            text_receiver.append(new String(packet.getData(),0,packet.getLength())+"\r\n");
        }
    }
        
    public void actionPerformed(ActionEvent event)         //"����"��ť�����¼�������
    {
        if(event.getActionCommand().equals("����"))
        {
            //�¾佫�ַ���ת�����ֽ����飬ʹ��Ĭ���ַ���GBK���ٷ���
            byte[] data=(this.name+" ˵��"+this.text_sender.getText()).getBytes();
            try
            {
                this.mulsocket.send(new DatagramPacket(data, data.length, this.dip, this.port)); //�������ݱ�
                this.text_sender.setText("");
            }
            catch(java.io.IOException ex)
            {
                JOptionPane.showMessageDialog(this, "IP��ַ��˿ڴ��󣬷��ʹ���");
//                ex.printStackTrace();
            }   
        }
    }

    public static void main(String args[]) throws java.io.IOException
    {
//        new ChatMulticastJFrame("����", "224.119.81.9", 30001);//����ָ���������鲥��ַ�Ͷ˿�
    	
        //�鲥��ַ����9.5��ͬ���˿ڲ�ͬ��
    	//����9.5ͬʱ���У�˵�����鲥��ַ�Ͷ˿ڡ�����һ���鲥�顣
    	new ChatMulticastJFrame("����", "224.116.8.0", 20010);
    }
}
//@author��Yeheya��2017��12��15��