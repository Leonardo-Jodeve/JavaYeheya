//��Java�������ʵ�ý̳̣���5�棩����⡷ ���ߣ�Ҷ���ǣ�2018��6��28��
//��6.4.1 ͼ�����
//����6.8˼���⡿ �϶�������⻭�ߡ�
//�����ص㣺�ⲿ����ơ�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//���⻭�߿���࣬��Ӧ����¼�������ƶ��¼�����ʾ���켣���ⲿ�����
//ֻ��¼һ��ֱ�ߵ�����յ����ꣻû��ѡ����ɫ��
public class DrawJFrame extends JFrame implements MouseListener, MouseMotionListener
{
    protected Point start, end;                  //�ֱ��¼ֱ�ߵ���㡢�յ�
    protected Canvas canvas;                     //�������

    public DrawJFrame()
    {
        super("�϶�������⻭��");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.start = this.end=null;
        this.canvas = new DrawCanvas();
        this.getContentPane().add(this.canvas);  //��ӻ������
        this.canvas.addMouseListener(this);      //������������¼�
        this.canvas.addMouseMotionListener(this);//������������ƶ��¼�
        this.setVisible(true);
    }
    
    
    //����ʵ��MouseListener����¼��ӿڣ���갴�µ���Ϊֱ�����
    public void mousePressed(MouseEvent event)   //��갴���¼�������
    {
        this.start = null;
        this.end = new Point(event.getX(), event.getY());//��¼��굱ǰ������
    }
    public void mouseReleased(MouseEvent event){}//����ͷ�
    public void mouseClicked(MouseEvent event){} //��굥��
    public void mouseEntered(MouseEvent event){} //������
    public void mouseExited(MouseEvent event){}  //����뿪

    
    //����ʵ��MouseMotionListener����ƶ��¼��ӿ�
    public void mouseMoved(MouseEvent event){}   //����ƶ�
    
    //����϶��¼���������ÿ�϶�һ����ʱִ�л�ֱ��
    public void mouseDragged(MouseEvent event)
    {
        this.start = this.end;                   //start��סǰһ����
        this.end = new Point(event.getX(), event.getY());//��굱ǰ��
        this.canvas.repaint();                   //��ֱ��(start, end)
    }    
    
    //���⻭�߻�������ڲ��࣬�̳л�������࣬����ͼ
    private class DrawCanvas extends Canvas
    {
        public void paint(Graphics g)            //��ͼ����
        {
            if(start!=null && end!=null)         //�����ⲿ��.this.start��end 
            {
                g.setColor(Color.blue);          //���û�����ɫ
                g.drawLine(start.x, start.y, end.x, end.y);//��ֱ��(start, end)
            }
        }
        public void update(Graphics g)           //������£��ػ�֮ǰͼ�Σ��Զ�ִ�С�//��û�и÷�����ֻ��ʾһ����
        {
            this.paint(g);
        }
    }//�ڲ������
    
    public static void main(String arg[])
    {
        new DrawJFrame();
    }
}
/*
�����������£�
����6.8˼����6-7���� ��������С���ٻ�ԭʱ��������ֻʣһ��ֱ�ߣ�Ϊʲô����λ�ԭ��������ͼ�Σ�
*/
//@author��Yeheya��2016-10-5��2017��2��14�գ�2018��7��11��