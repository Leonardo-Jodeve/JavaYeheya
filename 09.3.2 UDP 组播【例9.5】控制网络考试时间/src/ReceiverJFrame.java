//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��12��12��
//��9.3.2  UDP�鲥ͨ��
//����9.5��  �������翼��ʱ�䡣
//��2������ʱ���鲥�Ŀͻ��˳���

import java.awt.*; 
import javax.swing.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//����ʱ���鲥�����
public class ReceiverJFrame extends JFrame
{
    //groupָ���鲥��ַ��portָ���������鲥�˿ڣ�minutesָ������ʱ�䣨�֣� 
    public ReceiverJFrame(String group, int port, int minutes) throws java.io.IOException
    {
        super("����ʱ���鲥  "+group+" : "+port);
        this.setBounds(320,240,500,120);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(2,4));
        
        //��������������ʾ���ʱ��
        String[] labelstr={"��ʼʱ��","����ʱ��","��ǰʱ��","ʣ��ʱ��"};
        JTextField[] texts= new JTextField[labelstr.length];
        for(int i=0; i<texts.length; i++)
        {
            JLabel label=new JLabel(labelstr[i],JLabel.CENTER);
            this.getContentPane().add(label);//=new JLabel(labelstr[i],JLabel.CENTER));
            label.setFont(new Font("����", Font.BOLD, 20));//��������
            this.getContentPane().add(texts[i]=new JTextField());
            texts[i].setFont(new Font("����", Font.BOLD, 20));
            texts[i].setEditable(false);
        }
        texts[2].setForeground(new Color(255,0,0));        //�Ժ�ɫ��ʾ��ǰʱ��
        this.setVisible(true);
        
        //���¼���dip�鲥��ַ����
        MulticastSocket mulsocket=new MulticastSocket(port); //��port�˿ڽ����鲥
        InetAddress dip = InetAddress.getByName(group);    //�鲥��ַ
        mulsocket.joinGroup(dip);                          //������
        
        //���½������ݰ������л�õ�ǰʱ�䣬�ټ���ʱ��
        byte[] data=new byte[512];
        DatagramPacket packet=new DatagramPacket(data, data.length, dip, port);//���������ݱ���
        mulsocket.receive(packet);                         //��һ�ν���
        //�¾佫packet�����ֽ�����ת���ַ�������ת����long����
        long time = Long.parseLong(new String(packet.getData(),0,packet.getLength()));
        SimpleDateFormat dataf = new SimpleDateFormat("HH:mm:ss");        
        texts[0].setText(dataf.format(new Date(time)));    //��ʾ��ʼʱ��
        long lasttime=time+minutes*60*1000;
        texts[1].setText(dataf.format(new Date(lasttime)));//��ʾ����ʱ��
        while(time<lasttime)                               //����ʱ��δ��ʱ����
        {
            mulsocket.receive(packet);                     //�������ݱ���
            time = Long.parseLong(new String(packet.getData(),0,packet.getLength()));
            texts[2].setText(dataf.format(new Date(time))); //��ʾ��ǰʱ��
            texts[3].setText(((lasttime-time)/60000)+"��"); //��ʾʣ��ʱ��
        }
        JOptionPane.showMessageDialog(this, "���Խ������رճ���");
        mulsocket.leaveGroup(dip);                         //�뿪��
        mulsocket.close();
        System.exit(0);                //������ʱ�䵽���򵯳��Ի��򣬲���ֹӦ�ó�������
    }
    
    public static void main(String args[]) throws java.io.IOException 
    {
    	//����ָ���鲥��ַ���˿ںͿ���ʱ�䳤�ȣ������������������Ҳ�����
//    	new ReceiverJFrame("224.116.8.0", 20009, 60);       //����ʱ��Ϊ60����
        new ReceiverJFrame("224.116.8.0", 20009, 10);
    }
}
//@author��Yeheya��2017��12��15�գ�2018��7��26��