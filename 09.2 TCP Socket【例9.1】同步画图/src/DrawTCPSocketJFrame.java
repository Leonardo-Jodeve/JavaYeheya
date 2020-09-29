//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��7��25��
//��6.4.1 ͼ�����
//����6.8˼���⡿ �϶�������⻭�ߡ��ⲿ����ơ�
//��9.2 TCP Socketͨ��
//����9.1�� ͬ����ͼ������һ��TCP���ӡ�
//����Ŀ�ģ��� �����ͻ��˱���ʹ���߳̽������ݡ�

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//ͬ����ͼ����࣬�̳п���࣬��Ӧ����¼�������ƶ��¼�����ʾ���켣��
//����TCP Socketͨ�ţ�ʹ�ö����ֽ�����������϶�ʱÿ�������Point�����
//ʵ��Runnable�ӿڣ���Ϊ�̵߳�Ŀ�����ʹ���߳̽�������
public class DrawTCPSocketJFrame extends JFrame 
    implements MouseListener, MouseMotionListener, Runnable
{
	private Point start, end;                    //�ֱ��¼ֱ�ߵ���㡢�յ�
	private Canvas canvas;                       //�������
    private Socket socket;
    private ObjectOutputStream objout;           //�����ֽ������
    
    //���췽��������˺Ϳͻ��˵��ã�nameָ������
    public DrawTCPSocketJFrame(String name) throws IOException
    {   //�¾��ܱ�������ӱ�������������IP��ַ
        super("ͬ����ͼ "+name+"  "+InetAddress.getLocalHost().toString());
        this.setBounds(400,300,580,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.start = this.end=null;
        this.canvas = new DrawCanvas();
        this.getContentPane().add(this.canvas);  //��ӻ������
        this.canvas.addMouseListener(this);      //������������¼�
        this.canvas.addMouseMotionListener(this);//������������ƶ��¼�
        this.setVisible(true);
        this.objout = null;
    }

    //���췽�����ͻ��˵��ã�nameָ���Լ�������host��portָ������˵�Socket
    public DrawTCPSocketJFrame(String name, String host, int port) throws IOException
    {
        this(name);
        Socket socket = new Socket(host, port);  //�ͻ��������������Ķ˿ڷ���TCP��������
        this.setTitle(this.getTitle()+" : "+socket.getLocalPort());//��ñ����˿�
        this.setSocket(socket);
    }
   
    public void setSocket(Socket socket) throws IOException  //���ӳɹ�ʱ������Socket�������߳�
    {
        this.socket = socket; 
        this.objout = new ObjectOutputStream(this.socket.getOutputStream());//��Socket����ֽ���
        new Thread(this).start();                //ʹ���߳̽�������
    }

    public void run()        //�߳����з����������ֽ������նԷ������Ķ��󣬱��봦���쳣
    {
        try
        {   //�¾��Socket����ֽ������ٴ��������ֽ�������
            ObjectInputStream objin = new ObjectInputStream(this.socket.getInputStream());
            while(true)
            {
                try
                {
                    this.start = this.end;                 //start��סǰһ���� 
                    this.end=(Point)objin.readObject();    //��ȡһ������
//                  System.out.println(end.toString());
                    this.canvas.repaint();                 //������ֱ��(start,end)
                }
                catch(EOFException ex)                     //��������������ʱ�׳����쳣
                {
                    break;
                }
            }
            objin.close();                                 //�رն�����
            this.objout.close();
            this.socket.close();                           //�ر�TCP����
        }
        catch(IOException | ClassNotFoundException ex){}   //�������쳣
    }//�رմ���ʱ����������ֹͣ��ǿ��whileѭ��ֹͣ���ر�����TCP���Ӻͷ���
    
    //����ʵ��MouseListener����¼��ӿڣ����봦���쳣��
    //��갴���¼�������������갴�µ���Ϊֱ����㣬����TCP���Ӻ��͸��Է�
    public void mousePressed(MouseEvent event)
    {
        this.start = null;
        this.end = new Point(event.getX(), event.getY());  //��¼��굱ǰ������
        try
        {
            if(this.objout!=null)
                this.objout.writeObject(this.end);         //���͸��Է���갴��λ�õĵ����
        }
        catch(IOException ex){}
    }
    public void mouseReleased(MouseEvent event){}          //����ͷ�
    public void mouseClicked(MouseEvent event){}           //��굥��
    public void mouseEntered(MouseEvent event){}           //������
    public void mouseExited(MouseEvent event){}            //����뿪

    //����ʵ��MouseMotionListener����ƶ��¼��ӿڣ����봦���쳣
    public void mouseMoved(MouseEvent event){}             //����ƶ�

    //����϶��¼���������ÿ�϶�һ����ִ�л�ֱ�ߣ�����TCP���Ӻ�����϶��㷢�͸��Է�
    public void mouseDragged(MouseEvent event)
    {
        this.start = this.end;                             //start��סǰһ����
        this.end = new Point(event.getX(), event.getY());  //��굱ǰ��
        this.canvas.repaint();                             //��ֱ��(start, end)
        try
        {
            if(this.objout!=null)
                this.objout.writeObject(this.end);         //���͸��Է�����϶�λ�õĵ����
        }
        catch(IOException ex){}
    }

    //���⻭�߻�������ڲ��࣬�̳л�������࣬����ͼ
    private class DrawCanvas extends Canvas
    {
        public void paint(Graphics g)                      //��ͼ����
        {
            if(start!=null && end!=null)                   //�����ⲿ��.this.start��end 
            {
                g.setColor(Color.blue);                    //���û�����ɫ
                g.drawLine(start.x, start.y, end.x, end.y);//��ֱ��(start, end)
            }
        }        
        public void update(Graphics g)           //������£��ػ�֮ǰͼ�Σ��Զ�ִ��
        {
            this.paint(g);                       //�ػ�֮ǰͼ��
        }
    }//�ڲ������
        
    public static void main(String[] arg) throws IOException
    {
        //����ָ�������������������IP��ַ�Ͷ˿ڣ���127.0.0.1����ָ�������������ַ
        new DrawTCPSocketJFrame("С�۷�", "127.0.0.1", 10011);
    }
}
//@author��Yeheya��2016-10-5��2017��2��14�գ�2018��7��25��