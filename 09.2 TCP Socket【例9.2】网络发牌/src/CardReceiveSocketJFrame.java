//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��10��19��
//��9.2 TCP Socketͨ��
//����9.2�� ���緢�Ƴ���
//��2�����տͻ��ˣ�ʹ���߳̽�������

import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.net.*;

//���տͻ��˿���࣬���߳���ʹ��������������������
public class CardReceiveSocketJFrame extends JFrame implements Runnable
{
    private JTextArea text;                      //�ı���
    private Socket socket;

    //���췽����nameָ���ͻ����Լ�������host��portָ������˵�Socket
    public CardReceiveSocketJFrame(String name, String host, int port) throws IOException
    {
        super(name+"  "+InetAddress.getLocalHost().toString());      //��������������IP��ַ
        this.socket = new Socket(host, port);                        //�ͻ�������TCP����
        this.setTitle(this.getTitle()+" : "+socket.getLocalPort());  //��������ӱ����˿�
        this.setBounds(800,200,500,120);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //�����ڿ�����ݴ����в�����ı���
        this.text = new JTextArea("");
        this.text.setLineWrap(true);                       //�ı����Զ�����
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        this.getContentPane().add(this.text); 
        this.setVisible(true);
        new Thread(this).start();
    }

    public void run()        //�߳����з����������ֽ������շ���˷�����������ֵ�����봦���쳣
    {
        try
        {   //�¾��Socket����ֽ������ٴ��������ֽ�������
            DataInputStream datain = new DataInputStream(this.socket.getInputStream());
            while(true)
            {
                try
                { 
                    this.text.append(datain.readInt()+"  ");//���������ж�ȡ��������ӵ��ı���
                }
                catch(EOFException ex)                     //��������������ʱ�׳����쳣
                {
                    break;
                }
            }
            datain.close();                                //�ر�������
            this.socket.close();                           //�ر�TCP����
//            System.out.println("close");
        }
        catch(IOException ex){}
    }
    //��˼���⡿���⣺��ʱ�������������ִ��socket.close()ʱ�������������close����
    
    public static void main(String[] args) throws IOException
    {
        //���տͻ��ˣ�ָ������˵�IP��ַ�Ͷ˿ڣ����Ǳ��������á�127.0.0.1��ָ�������ַ
        new CardReceiveSocketJFrame("��", "127.0.0.1", 10001);
        //"��"��"��"��"��"�ͻ���������������������˿ڷֱ���10002��10003��10004��ʡ��    
        new CardReceiveSocketJFrame("��", "127.0.0.1", 10002);
        new CardReceiveSocketJFrame("��", "127.0.0.1", 10003);
        new CardReceiveSocketJFrame("��", "127.0.0.1", 10004);
        
//      new CardReceiveSocketJFrame("��", "202.119.162.208", 10001);//��Ϣ¥A308������IP��ַ
    }
}
/*
�����������⣺
���ڷ����Լ���˿���4����һ���ͻ������֪����ǰ����˵ȴ����ӵĶ˿�����һ����
�����9.3�����߳̽�������⣬��������������̣߳�ÿ���߳���һ��Լ���˿ڵȴ���
һ���ͻ���Լ������һ���˿ڣ�˭��˭�󶼿��ԡ�
*/
//2017��12��2�գ�����ʹ���߳̽��ա�
/*2018��7��25�գ� 
��1������˺Ϳͻ��˳��򶼽�����Ĵ��ˣ���5��̲�ûд��
��3���ͻ��������߳̽������ݣ��ɹ���main()�п�4��new��
*/
//@author��Yeheya��2017��10��21�գ�2017��11��25�գ�2017��12��5�գ�2018��7��25��