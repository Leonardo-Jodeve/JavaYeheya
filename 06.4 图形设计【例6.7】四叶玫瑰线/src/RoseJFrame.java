//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��29��
//��6.4.1 ͼ�����
//����6.7����Ҷõ���ߵ�ͼ����ơ�
//JDK 7.1 �ı������С ʱ��Ĭ��ִ��paint()��������ˣ��ɲ���Ӧ����¼���
//�޸ĵ�4�����RoseCanvas��Ϊ�ڲ��࣬���ӽ���ɫ�������ȹ��ܣ����������ܿ���ʱ���ֹͣ
//������ʵ�ֵ���sleep�Ķ��������6�¸����������ܵĽ��棬����ʵ����Ϊ��7��ʵ�����Ҫ��

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//������Ҷõ���߿���࣬�̳п���࣬��Ӧ�����¼�����ֵ�ı��¼������ԣ���Ӧ�����¼�
public class RoseJFrame extends JFrame implements ActionListener, ChangeListener, WindowListener
{
    private JButton button;                      //ѡ����ɫ��ť
    protected JCheckBox[] checkbox;              //��ѡ������
    private Color color;                         //��ɫ
    private JSpinner spin;                       //����ɫ�仯ֵ΢���ı���
    protected Canvas canvas;                     //����
    
    public RoseJFrame()
    {
        super("��Ҷõ����");                                //��ܱ߲���
        Dimension dim=this.getToolkit().getScreenSize();   //�����Ļ�ֱ���
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);  //���ô��ھ���
        this.setLocationRelativeTo(null);                  //������������Ļ����
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //���´���������壨���������ã������������ṩ��ͼ����
        JPanel cmdpanel = new JPanel();                    //������壬Ĭ�������֣����У����������ԭ��
                                                           //ע�⣺���ﲻ����toolbar�����˶��룬spin��ѿ�
        this.getContentPane().add(cmdpanel,"North");
        cmdpanel.add(this.button=new JButton("ѡ����ɫ"));
        this.button.addActionListener(this);
        String[] str={"����","����ɫ","��ʾ��������"};      //��˼����6-7��
        this.checkbox = new JCheckBox[str.length];         //��ѡ������
        for(int i=0; i<str.length; i++)
        {
            cmdpanel.add(this.checkbox[i] = new JCheckBox(str[i]));
            this.checkbox[i].addActionListener(this);      //��ѡ����������¼�
        }      
        cmdpanel.add(new JLabel("����"),3);
        //�¾�΢���ı��У�����ָ����ֵ����ģʽ����ֵ��4����Χ��1��8��������1
        this.spin=new JSpinner(new SpinnerNumberModel(4,1,8,1));
        this.spin.addChangeListener(this);       //΢���ı��м�����ֵ�ı��¼�
        cmdpanel.add(this.spin,4);
        cmdpanel.add(new JLabel("λ"),5);        
        
        this.color = Color.blue;
        this.canvas = new RoseCanvas();          //��Ҷõ���߻���������Ĭ�Ϲ��췽�������������������ʵ��
        this.getContentPane().add(this.canvas,"Center");
        this.setVisible(true);
        this.addWindowListener(this);            //��ܼ��������¼�
    }
    
    public void actionPerformed(ActionEvent event)  //�����¼�������
    {
        if(event.getSource()==this.button)       //������ѡ����ɫ����ť
        {
            //����ɫѡ��Ի��򣬵�����ȷ������ť����ѡ����ɫ��������ȡ������ť����null
            Color color=JColorChooser.showDialog(this, "ѡ����ɫ", this.color);
            if(color!=null)
            {
                this.color = color;
                this.canvas.repaint();           //����canvas��paint(Graphics)�������ػ�
            }
        }
        else if//(event.getSource() instanceof JCheckBox)   //������ѡ�򣬵�5��̲�
                //������ѡ�򣬡�˼����6-6��������ʾ�������̸�ѡ����ѡ��״̬
                (event.getSource()==this.checkbox[0] || event.getSource()==this.checkbox[1] 
                 || event.getSource()==this.checkbox[2] && this.checkbox[2].isSelected())
                this.canvas.repaint();           //�ػ�
    }
    
    public void stateChanged(ChangeEvent event)  //JSpinner��Ӧֵ�ı��¼�������
    {
        this.checkbox[1].setSelected(true);      //���ý���ɫ��ѡ��Ϊѡ��״̬
        this.canvas.repaint();                   //�ػ�
    }

    //��Ҷõ���߻�������࣬�̳л�������࣬����paint()������
    //˽��ʵ���ڲ��࣬����Ƕ�ף���ΪҪʹ���ⲿ�������������ɫ���Ƿ񽥱䡢�仯ֵ��ѡ������
    private class RoseCanvas extends Canvas 
    {
        public void paint(Graphics g)            //�����ͼ����������
        {
            if (RoseJFrame.this.checkbox[0].isSelected())  //�����ѡ��Ϊѡ��״̬
                g.setColor(RoseJFrame.this.color.darker());//���ü������ɫ
            else
                g.setColor(RoseJFrame.this.color);//���û�����ɫ��ʹ���ⲿ�൱ǰʵ������ɫ����ֵ
            
            final int x0 = this.getWidth()/2;    //(x0,y0)�ǻ���������е�����
            final int y0 = this.getHeight()/2; 
            g.drawLine(0,y0,x0*2,y0);            //��ֱ�ߣ�X��            
            g.drawLine(x0,0,x0,y0*2);            //��ֱ�ߣ�Y��
            //���»�XY���ͷ������fillPolygon()������η�����ʡ�ԡ��̲�ûд��˼����
            final int arrow=20;                            //��ͷ����
            g.drawString("X", x0*2-arrow/2, y0-arrow/2);   //дX���ַ���
            int XxPoints[]={x0*2, x0*2-arrow, x0*2-arrow}; //��X�������μ�ͷ
            int XyPoints[]={y0,   y0-arrow/3, y0+arrow/3};     
            g.fillPolygon(XxPoints, XyPoints, XxPoints.length);//�������

            g.drawString("Y", x0+arrow/2,arrow/2);         //дY���ַ���
            int YxPoints[]={x0, x0-arrow/3, x0+arrow/3};   //��Y�������μ�ͷ
            int YyPoints[]={0,  arrow,      arrow};     
            g.fillPolygon(YxPoints, YyPoints, YxPoints.length);

            int n = (Integer)RoseJFrame.this.spin.getValue();//ÿȦ��ɫ����λ��
            for(int length=40; length<200; length+=20)    //��8Ȧ��Ҷõ���ߣ�length��ʾÿȦ���
            {
                for(int i=1; i<1024; i++)        //��һȦ��Ҷõ���ߵ����ɵ㣬˳ʱ�룬�������෴
//                for (int i=1024; i>0; i--)       //��һȦ��Ҷõ���ߵ����ɵ㣬��ʱ�룬��������ͬ
                {
                    double angle=i*Math.PI/512;           //�Ƕȣ�Math.PI��ʾ��
                    double radius=length*Math.sin(2*angle);    //�뾶��Math.sin()���Һ���
                    int x=(int)(radius*Math.cos(angle)*2);//x���꣬Math.cos()���Һ���
                    int y=(int)(radius*Math.sin(angle));  //y����
                    g.fillOval(x0+x,y0+y,2,2);            //�����Բ��ֱ��С����ʾһ����
//                    g.drawOval(x0+x,y0+y,1,1);          //����Բ����ϸ��Ҳ��
                    //��˼����6-6�����ⲿ�൱ǰʵ���Ķ�����ʾ��ѡ����ѡ��״̬���߳�ÿ��������
                    if(RoseJFrame.this.checkbox[2].isSelected())
                        try
                        {
                            Thread.sleep(1);             //�ܹ���ӳ�滭���̣�����������Ҫsleeptime
                        }
                        catch(InterruptedException ex)
                        {
                            return;
                        }
                    //����д˵�����������̶߳���Ҳ���Ե���Thread.sleep(1)������
                    //��������ʵ�ֶ������޷���ʱֹͣ�����뻭�����ֹͣ��
                    //����ʵ����7-5�� ������ʹ���߳�ʵ�ֶ�����ơ�
                }
                //���ⲿ�൱ǰʵ���Ľ���ɫ��ѡ����ѡ��״̬���𽥸ı仭����ɫ
                if(RoseJFrame.this.checkbox[1].isSelected())
                    g.setColor(new Color(g.getColor().getRGB()<<n));//ÿȦ��ɫ����nλ
            }
        }
    }
    public static void main(String arg[])
    {
        new RoseJFrame();
    }
    
    //���µ�5��̲�ûд�����ԣ���ʾ����ʱ���رմ��ڲ���û������ִ��
    public void windowClosing(WindowEvent event)         //�رմ���ʱִ�е��¼�������
    {
        Thread.currentThread().interrupt();
              //��ǰ�����߳��жϡ������������е��˾䣬��Ч��û�в�𡣻����˲��ܹرմ���
    }
    public void windowOpened(WindowEvent event)     {}   //�򿪴��ں�ִ��
    public void windowActivated(WindowEvent event)  {}   //����ں�ִ��
    public void windowDeactivated(WindowEvent event){}   //��Ϊ������ں�ִ��
    public void windowIconified(WindowEvent event)  {}   //������С����ִ��
    public void windowDeiconified(WindowEvent event){}   //���ڻָ���ִ��
    public void windowClosed(WindowEvent event)     {}   //�رմ��ں�ִ��    
}
/*
        public void update(Graphics g)           //���������//�������������򶯻����ˣ���֪��Ϊʲô
        {
            this.paint(g);
        }
//˼���⣬���ӹ��ܣ����ƻ�ͼ�Ĵ�С���봰�ڴ�С�����ȣ����ƻ�ͼ��Ȧ����
*/
//@author��Yeheya��2016-10-5���޸�2017��1��29�գ����ڣ��������2017��2��14��