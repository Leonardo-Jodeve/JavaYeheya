//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��12��12��
//��9.3.2  UDP�鲥ͨ��
//����9.5��  �������翼��ʱ�䡣
//��1���ṩʱ���鲥�ķ���˳���
//ʹ���̣߳�ÿ�뷢��

import java.awt.Font;
import javax.swing.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

//ʱ���鲥��������
public class TimeBroadcastJFrame extends JFrame
{
    private MulticastSocket mulsocket;                     //�鲥�׽���  
    
    //���췽����group��portָ���鲥��ַ�Ͷ˿�
    public TimeBroadcastJFrame(String group, int port) throws java.io.IOException
    {
        super("����ʱ���鲥  "+group+" : "+port);
        this.setBounds(200,240,480,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label=new JLabel("",JLabel.CENTER);         //��ʾ��ǰʱ��ı�ǩ�����ж���
        this.getContentPane().add(label);
        label.setFont(new Font("����", Font.BOLD, 20));    //��������
        this.setVisible(true);
        
        //���¼���dip�鲥��ַ����
        InetAddress dip = InetAddress.getByName(group);    //�鲥��ַ 
        this.mulsocket = new MulticastSocket(port);        //��port�˿ڽ����鲥ͨ��
        this.mulsocket.setTimeToLive(1);                   //�������ݱ���ΧΪ��������
        this.mulsocket.joinGroup(dip);                     //������
        
        //����ÿ�뽫��ǰʱ�䣨�����������͵�dip�鲥���У�ʱ���ʽ�У�HH��ʾ24Сʱ��
        SimpleDateFormat datef=new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
        while(true)
        {
            long time=System.currentTimeMillis();          //��õ�ǰʱ��
            label.setText(datef.format(new Date(time)));   //��ʱ���ʽ��ʾ��ǰʱ��
            byte[] data=(time+"").getBytes();    //��time������ת�����ַ�������ת�����ֽ�����
            DatagramPacket packet=new DatagramPacket(data, data.length, dip, port);//���������ݱ���
            try
            {
                this.mulsocket.send(packet);     //�鲥�������ݱ����ɱ�dip���г�Ա���յ�
                Thread.sleep(1000);              //ÿ�뷢��
            }
            catch(InterruptedException ex)
            {
                JOptionPane.showMessageDialog(this, "���ʹ���");
                break;
//              ex.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) throws java.io.IOException
    {
        new TimeBroadcastJFrame("224.116.8.0", 20009);   //����ָ���鲥��ַ�Ͷ˿�
    }

    //ûдҲ��ִ�У�Ч��һ��
//    public void finalize()                       //������������5��̲�ûд
//    {
//        this.mulsocket.close();                  //�ر��鲥Socket��ִ�У��˿ڹرգ��´ο�����
//        System.out.println("this.mulsocket.close()");//ûд�������������ã�
//    }
}
//@author��Yeheya��2017��12��15�գ�2018��7��26��
//2018��7��26�գ�   (new Long(time)).    //û���ҵ�������ֱ��ת�����ֽ�����ķ���