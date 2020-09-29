//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��6��28��
//��6.4.1 ͼ�����
//����6.8�� �϶���껭ֱ�ߡ�
//�����ص㣺�ⲿ����ơ�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��ֱ�߿���࣬��Ӧ����¼�������ƶ��¼�����ֱ�ߣ���ʾ���߹켣
public class DrawLineJFrame extends JFrame implements MouseListener, MouseMotionListener
{
	protected Point start, end, lastend;           //�ֱ��¼ֱ�ߵ���㡢�յ㡢ǰһ��ֱ�ߵ��յ�
    protected Canvas canvas;                       //�������

    public DrawLineJFrame()
    {
        super("�϶���껭ֱ��");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //������ӻ������
        this.start = this.end = this.lastend = null;
        this.canvas = new DrawLineCanvas();
        this.getContentPane().add(this.canvas);   //��ӻ������
        this.canvas.addMouseListener(this);       //������������¼�
        this.canvas.addMouseMotionListener(this); //������������ƶ��¼�
        this.setVisible(true);
    }
    
    //����ʵ��MouseListener����¼��ӿڣ�����갴�µ���Ϊֱ�����
    public void mousePressed(MouseEvent event)   //��갴���¼�������
    {
        this.start = new Point(event.getX(), event.getY());//��¼ֱ���������
    }
    public void mouseReleased(MouseEvent event){}//����ͷ�
    public void mouseClicked(MouseEvent event){} //��굥��
    public void mouseEntered(MouseEvent event){} //������
    public void mouseExited(MouseEvent event) {} //����뿪

    //����ʵ��MouseMotionListener����ƶ��¼��ӿ�
    public void mouseMoved(MouseEvent event){}   //����ƶ�
    
    //����϶��¼���������ÿ�϶�һ����ʱִ�л�ֱ��
    public void mouseDragged(MouseEvent event)
    {
    	this.lastend = this.end;                 //ǰһ����
    	this.end = new Point(event.getX(), event.getY());  //��굱ǰ��
        this.canvas.repaint();         //����ֱ��(start, lastend)���ٻ�ֱ��(start, end)
    }
        
    //��ֱ�߻�������ڲ��࣬�̳л�������࣬����ͼ
    private class DrawLineCanvas extends Canvas 
    {
        //��ͼ���������ñ���ɫ����������ֱ��(start, lastend)������ָ����ɫ��ֱ��(start, end)
        public void paint(Graphics g) 
        {
            if(start!=null && lastend!=null)
            {
                g.setColor(this.getBackground());          //���û�����ɫΪ����ɫ
                g.drawLine(start.x,start.y, lastend.x,lastend.y);//��ֱ��(start, lastend)������
//                g.drawOval(start.x, start.y, lastend.x, lastend.y);//����Բ��������������ͣ����ߣ����������䷽ʽ��

                g.setColor(Color.blue);                    //���û�����ɫ
                g.drawLine(start.x, start.y, end.x, end.y);//��ֱ��(start, end)
//                g.drawOval(start.x, start.y, end.x, end.y);//����Բ
//              g.drawRect(start.x, start.y, end.x, end.y);//��Բ�Ǿ���
            }
        }
        public void update(Graphics g)           //������£��ػ�֮ǰͼ�Σ��Զ�ִ�С�//��û�и÷�����ֻ��ʾһ��ֱ��
        {
            this.paint(g);
        }
    }//�ڲ������
    
    public static void main(String arg[])
    {
        new DrawLineJFrame();
    }
}
//@author��Yeheya��2016-10-5��2017��2��14�գ�2018��7��1��