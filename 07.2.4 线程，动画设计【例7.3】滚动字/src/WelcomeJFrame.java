//��Java�������ʵ�ý̳̣���4�棩�������ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�������ߣ�Ҷ���ǣ�2017��7��19��
//��7.2.4 ʹ���߳�ʵ�ֶ������
//����7.3�������֡�
//2017��10��1�ոĽ������ʱ�̷ֱ���ʾ�߳�NEW�����С��ȴ�̬������ڲ�����ʹ�ð�ť����
//����Ҫ��ӦFocusListener�����¼�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeJFrame extends JFrame        //�����ֿ����
{
    public WelcomeJFrame(String[] texts)         //textsָ���ƶ��ַ��������鳤�Ⱦ��������е������
    {
        super("������");        
        this.setBounds(300,300,690,260);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        if(texts==null || texts.length==0)
            this.getContentPane().add(new RollbyJPanel("Welcome!")); //������һ���ַ���
        else
        {
            this.getContentPane().setLayout(new GridLayout(texts.length,1));//���񲼾֣�����1��
            for(int i=0; i<texts.length; i++)
                this.getContentPane().add(new RollbyJPanel(texts[i])); 
        }
        this.setVisible(true);
    }    
    public WelcomeJFrame()
    {
        this(null);
    }  
    
    //������������������࣬˽���ڲ��࣬ʵ�ֶ����¼��������ӿںͿ����нӿڣ�����Ƕ��
    private class RollbyJPanel extends JPanel implements ActionListener, Runnable
    {
        JTextField text_word, texts[];                     //�������ı��У��ı�������
        JButton[] buttons;                                 //��ť����
        Thread thread;                                     //�̶߳���
        int sleeptime;                                     //�߳�˯��ʱ��
        Font font = new Font("����",1,20);                 //����

        public RollbyJPanel(String text)                   //����������๹�췽��
        {
            this.setLayout(new GridLayout(2,1));
            this.text_word = new JTextField(String.format("%60s", text));  //text��ӿո��ַ���
            this.add(this.text_word);                      //�������ı���
            this.text_word.setFont(font);
            
            //���´���������壬��ӹ����֡��߳�˯��ʱ����߳�״̬���ı��У��Լ��������жϵȰ�ť
            JPanel cmdpanel = new JPanel();
            this.add(cmdpanel);
            String[] textstr={"sleeptime","State1","State2","isAlive"};//�߳�˯��ʱ����߳�״̬��
            int[] widths={5,9,9,5};
            this.texts = new JTextField[textstr.length];
            for(int i=0; i<this.texts.length; i++)
            {
                cmdpanel.add(new JLabel(textstr[i]));
                cmdpanel.add(this.texts[i] = new JTextField(widths[i]));
                this.texts[i].setEditable(false);          //�ı��в��ɱ༭
            }
            this.sleeptime = (int)(Math.random()*100);     //�����������Ϊ���ʱ��
            this.texts[0].setText(""+sleeptime);
            this.texts[0].setEditable(true);               //�ı��пɱ༭
            this.texts[0].addActionListener(this);         //sleeptime�ı�����Ӧ�����¼�

            String[] buttonstr={"����","�ж�"};
            this.buttons = new JButton[buttonstr.length];
            for(int i=0; i<this.buttons.length; i++)
            {
                cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
                this.buttons[i].addActionListener(this);   //��ť��Ӧ�����¼�
            }
            this.buttons[1].setEnabled(false);             //���á��жϡ���ťΪ��Ч״̬

            //���´������̶߳�����thread���ã��߳�Ŀ�������this����ʾ�߳�״̬
            this.thread = new Thread(this);//, text);          //�����ֳ�ֵ��Ϊ�߳���
            this.texts[1].setText(""+this.thread.getState()); //�߳�NEW�½�̬
            this.texts[3].setText(""+this.thread.isAlive());  //�߳��Ƿ��ǻ�ģ�false
        }

        public void run()              //�߳����з����������ǹ��з��������봦���쳣
        {
//            System.out.println(this.thread.getName()+"��run()��"+this.thread.getState());//RUNNABLE����̬
//            while(true) 
            while(this.thread.isAlive() && !this.thread.isInterrupted())//�̻߳��û�ж�ʱ���ȼ�
            {
                try
                {
                    String str = this.text_word.getText();
                    this.text_word.setText(str.substring(1)+ str.substring(0,1));
                    Thread.sleep(this.sleeptime);          //�߳�˯�ߣ��׳��ж��쳣
//                    System.out.println("run()��"+this.thread.getState());//�˴���Ȼ��RUNNABLE����̬�����Բ����߳�˯����ʲô״̬
                }
                catch(InterruptedException ex)            //���񲢴����ж��쳣
                {
                    break;                                 //�˳�ѭ��
                }
            }
        }                                                  //run()�����������߳̽���TERMINATED��ֹ̬

        public void actionPerformed(ActionEvent event)     //�����¼����������ǹ��з���
        {
            if(event.getSource()==this.texts[0])           //��sleeptime�ı����а��س���
            {
                try
                {
                    this.sleeptime=Integer.parseInt(this.texts[0].getText());//�մ��׳��쳣
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(this,"\""+this.texts[1].getText()+"\" ����ת����������");
//                    JOptionPane.showMessageDialog(this, "\""+ex.getMessage()+"\" ����ת����������");
                }
            }

            else if(event.getSource()==this.buttons[0])    //��������������ť
            {
//              System.out.print(this.thread.getName()+"��"+this.thread.getState()+"��");//��ʾ������ťǰ���߳�״̬
                //���̲߳���NEW̬��TERMINATED̬������null������ʾ֮ǰ�ж��ˣ������´���һ���߳�
                if(this.thread.getState()!=Thread.State.NEW)
                    this.thread = new Thread(this);//, this.text_word.getText().trim());  //���´���һ���̶߳����߳�����ɾ���ո�Ĺ�����
                    //���˴�û���½��̣߳����ٴ��������׳�java.lang.IllegalThreadStateException
                this.thread.start();                       //�����߳�
//                System.out.print("������"+this.thread.getState());//RUNNABLE����̬��
                this.texts[1].setText(""+this.thread.getState());  //�߳����������RUNNABLE����̬
                this.buttons[0].setEnabled(false);
                this.buttons[1].setEnabled(true);
                this.texts[2].setText(""+this.thread.getState()); //����ʱ���ٴ���ʾ���߳�˯��TIMED_WAITING̬
//                System.out.println("��"+this.thread.getState());//�߳�˯��TIMED_WAITING̬��
            }
            else if(event.getSource()==this.buttons[1])    //�������жϡ���ť
            {
                this.thread.interrupt();                   //���õ�ǰ�̶߳����жϱ��
                this.texts[1].setText(""+this.thread.getState());  //�߳�TIMED_WAITING״̬��Ϊʲô��
                this.buttons[0].setEnabled(true);
                this.buttons[1].setEnabled(false);
                //�����߳��жϺ�sleep()�׳��쳣��run()�������¾���ʾ�߳̽���TERMINATED��ֹ̬
                this.texts[2].setText(""+this.thread.getState());
//                System.out.println("�жϣ�"+this.thread.getState());//Welcome��TIMED_WAITING���жϣ�TERMINATED
            }
            this.texts[3].setText(""+this.thread.isAlive());  //�߳��Ƿ��ǻ��
        }
    }//�ڲ������

    public static void main(String arg[])
    {
        String texts[]={"Welcome","Hello","Rollby"};
        new WelcomeJFrame(texts);
    }
}
/*
�������˵�����¡�
��1�����췽�� 
        new WelcomeJFrame();
�൱��
        String[] texts={"Welcome"};
        new WelcomeJFrame(texts);

��2���ַ������ո�Ҳ��
        char space[]=new char[100];
        java.util.Arrays.fill(space, ' ');             //���ַ�����space������ո�
        text_word = new JTextField(text+new String(space));  //text��ӿո��ַ���
        
��3������߳�״̬���£�
Hello��NEW��������RUNNABLE��TIMED_WAITING
Hello��TIMED_WAITING���жϣ�TERMINATED
Hello��TERMINATED��������RUNNABLE��TIMED_WAITING
Hello��TIMED_WAITING���жϣ�TERMINATED

��4��Thread�߳����suspend()��resume()�����ѱ�������
       ��JDK8��Ȼ�����У�������������ͣ���ָ����൱�ڲ���ϵͳ�Ĺ���ͻָ�״̬�� 
            else if (event.getSource()==this.buttons[3])      //����suspend��ťʱ
            {
                this.thread.suspend();                     //�߳���ͣ������
                this.texts[1].setText(""+this.thread.getState()); 
            }
            else if (event.getSource()==this.buttons[4])      //����resume��ťʱ
            {
                this.thread.resume();                      //�ָ̻߳�
                this.texts[1].setText(""+this.thread.getState()); 
            }

��5������������ʾ����wait()�������̵߳�״̬����������﷨����      
            else if (event.getSource()==this.buttons[2])      //����wait��ťʱ
            {
                try
                {
                    this.thread.wait();                   //���õ�ǰ�̶߳����жϱ��
                    this.text_state.setText(""+this.thread.getState());
                }
                catch (IOException ex){}
            }
   �ò��Թ�������7.7ͬ����������ʵ�֡�

*/
//@author��Yeheya��2017��7��20�գ�2017��9��30�գ�2017��10��1��