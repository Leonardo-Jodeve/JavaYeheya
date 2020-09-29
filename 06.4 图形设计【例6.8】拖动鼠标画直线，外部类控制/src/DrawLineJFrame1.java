//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��10��5��
//��6.4.1 ͼ�����
//����6.8�� �϶���껭ֱ�ߡ�
//�����ص㣺�ⲿ����ơ�
//����6.8��ͬ��ֻ��Ӧ����¼���û����Ӧ����϶��¼����ͷ����ʱ�Ż���û����ʾֱ�߹켣��

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��ֱ�߿���࣬��Ӧ����¼�����ֱ�ߣ�û����ʾ��ֱ�߹켣
public class DrawLineJFrame1 extends JFrame implements MouseListener
{
    private Point start, end;                    //�ֱ��¼ֱ�ߵ���㡢�յ�
    private Canvas canvas;                       //�������

    public DrawLineJFrame1()
    {
        super("ʹ����껭ֱ��");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.start = this.end = null;
        this.canvas = new DrawLineCanvas();
        this.getContentPane().add(this.canvas);            //��ӻ������
        this.canvas.addMouseListener(this);                //������������¼�
        this.setVisible(true);
    }
    
    //����ʵ��MouseListener����¼��ӿڣ�����갴�µ㡢����ͷŵ���Ϊֱ�������յ�
    public void mousePressed(MouseEvent event)             //��갴���¼�������
    {
        this.start = new Point(event.getX(), event.getY());//��¼ֱ���������
    }
    public void mouseReleased(MouseEvent event)            //����ͷ��¼�������
    {
        this.end = new Point(event.getX(), event.getY());  //��¼ֱ���յ�����
        this.canvas.repaint();                             //��ֱ��(start, end)
    } 
    public void mouseClicked(MouseEvent event){}           //��굥��
    public void mouseEntered(MouseEvent event){}           //������
    public void mouseExited(MouseEvent event) {}           //����뿪

    
    //��ֱ�߻�������ڲ��࣬�̳л�������࣬����ͼ
    private class DrawLineCanvas extends Canvas 
    {
        //��ͼ���������ñ���ɫ����������ֱ��(start, lastend)������ָ����ɫ��ֱ��(start, end)
        public void paint(Graphics g) 
        {
            if(start!=null && end!=null)         //�����ⲿ��.this.start��end 
            {
                g.setColor(Color.blue);                    //���û�����ɫ
                g.drawLine(start.x, start.y, end.x, end.y);//��ֱ��(start, end)
            }
        }
        
        public void update(Graphics g)           //������£��ػ�֮ǰͼ�Σ��Զ�ִ�С�//��û�и÷�����ֻ��ʾһ��ֱ��
        {
            this.paint(g);
        }
    }//�ڲ������
    
    public static void main(String arg[])
    {
        new DrawLineJFrame1();
    }
}
//@author��Yeheya��2016-10-5��2017��2��14�գ�2018��7��1��