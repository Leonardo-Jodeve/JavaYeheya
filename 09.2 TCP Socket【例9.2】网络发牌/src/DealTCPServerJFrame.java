//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��10��19��
//��9.2 TCP Socketͨ��
//����9.2��  ���緢�Ƴ��򣬶���TCP���ӡ�
//��1�����Ʒ����

import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.net.*;

//���Ʒ���˿���࣬���������������������
public class DealTCPServerJFrame extends JFrame
{
    //���췽����portָ����ʼ�˿ڣ���ֵ��Χ��1��cardMax��numberָ������
    public DealTCPServerJFrame(int port, int cardMax, int number) throws IOException 
    {
        super("���Ʒ����  "+InetAddress.getLocalHost()+" : "+port);
        this.setBounds(200,200,600,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //�����ڿ�����ݴ����в�����ı���
        JTextArea text = new JTextArea();
        text.setFont(new Font("����", Font.BOLD, 20));
        text.setLineWrap(true);                            //�ı����Զ�����
        this.getContentPane().add(text); 
        this.setVisible(true);
   
        //����ʹ��Socket����洢����TCP���ӣ�ÿ��TCP������������д��������ֵ
        Socket[] sockets=new Socket[number];
        DataOutputStream[] dataouts=new DataOutputStream[number];  //�����ֽ������
        for(int i=0; i<number; i++)                        //��������n���ͻ���
        {
            System.out.println("��"+port+"�˿ڵȴ���server.accept()");
            text.append("��"+port+"�˿ڵȴ�TCP���ӣ�");
            sockets[i] = new ServerSocket(port).accept();  //�ȴ����տͻ��˵���������
            text.append("����"+sockets[i].getInetAddress()+" : "+sockets[i].getPort()+"��\n");
            dataouts[i] = new DataOutputStream(sockets[i].getOutputStream());  //���Socket�ֽ������
            port++;                                        //����һ���˿ڵȴ���һ���ͻ���
        }
        text.append("Sender: \r\n");           
        for(int j=1; j<=cardMax; )                         //������n���ͻ��˹�����cardMax����
        {
            for(int i=0; j<=cardMax && i<dataouts.length; i++,j++)
            {
                dataouts[i].writeInt(j);
                text.append(String.format("%4d",j));
            }
        }
        for(int i=0; i<number; i++)
        {
            dataouts[i].close();                           //�ر������ֽ������Է����յ�EOFException�쳣
            sockets[i].close();                            //�ر�TCP����
        }
        //û���ر�ServerSocket�������ˡ�
    }

    public static void main(String args[]) throws IOException
    {
        new DealTCPServerJFrame(10001,52,4);               //�������Ʒ���ˣ�Լ���˿�
    }
}
/*
    ��������˵�����£�  
    ������ʾ���֣�Ϊʲô��  ����"Arial"���岻�ԣ��ĳ�����ͺ��ˡ� 
     ����ʲô�ַ�����Ĭ��GBK�� 
*/ 
//2017��10��21�գ����ԣ����Ƴ�����û��port++����һ���˿ڵȴ�������ӣ����У��׳�����Bind�쳣��
//2017��12��2�գ�����ʹ���̷߳��͡�
/*2018��7��25�գ� 
��1������˺Ϳͻ��˳��򶼽�����Ĵ��ˣ���5��̲�ûд��
��2������������̷߳������ݣ�������һ�Σ�δ�ɹ���
       �ܵ�����ԭ��Socket���飬��ֵ��̣߳���Ҫ���4�����У���Ҫ���ϣ���9�����м����޷�ʵ�֡� 
*/
//@author��Yeheya��2017��10��21�գ�2017��11��25�գ�2017��12��5�գ�2018��7��25��