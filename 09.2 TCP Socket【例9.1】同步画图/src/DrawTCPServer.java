//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��6��26��
//��9.2 TCP Socketͨ��
//����9.1�� ͬ����ͼ������һ��TCP���ӡ�
//��1������˳���
//����Ŀ�ģ��� ��������˳���ʱ������ʾ���档

import java.net.*;
import java.io.*;

//ͬ����ͼ����ˣ�����TCP Socketͨ��
public class DrawTCPServer
{
    //���췽����nameָ��������portָ���˿ڣ�����IP��ַ��port�˿ڹ��ɷ���˵�Socket
    public DrawTCPServer(String name, int port) throws IOException
    {
        ServerSocket server = new ServerSocket(port);      //ServerSocket�ṩTCP���ӷ���port�˿�
        DrawTCPSocketJFrame draw = new DrawTCPSocketJFrame(name); //��ͼ����ͼ���û�����
        draw.setTitle(draw.getTitle()+" : "+port);
        System.out.println(name+"��"+port+"�˿ڵȴ���server.accept()");
        Socket socket = server.accept();                   //�ȴ����տͻ��˵�TCP��������
        draw.setSocket(socket);
        server.close();                                    //�ر�TCP���ӷ���
    }

    public static void main(String args[]) throws IOException
    {
        new DrawTCPServer("������", 10011);                //��������ˣ�ָ�������Ͷ˿�
    }
}
//@author��Yeheya��2018��7��25��