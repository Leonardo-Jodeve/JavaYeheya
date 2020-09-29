//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.8��  ���ơ�

import java.awt.*;
import javax.swing.*;

//ȡ�ƿ���࣬���������߳�
public class CardReceiveJFrame extends JFrame implements Runnable
{
    private CardBuffer<Integer> buffer;          //����ƵĻ������ܳ�
    private JTextArea text;                      //��ʾ��ֵ���ı���
    private int order;                           //Լ��ȡ���̴߳�����ź���
    
    //���췽����bufferָ��ȡ�Ƶ�ͬ����������orderָ��ȡ����ţ�titleָ�����ڱ��⣬x��yָ����������
    public CardReceiveJFrame(CardBuffer<Integer> buffer, int order, String title, int x, int y)
    {
        super(title);
        this.setBounds(x,y,290,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.buffer = buffer ;
        this.order = order;

        this.text = new JTextArea();
        this.getContentPane().add(this.text);
        this.text.setLineWrap(true);             //�����ı����Զ�����
        this.text.setEditable(false);
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setVisible(true);
        new Thread(this).start();                //ȡ���̣߳����ȼ�Ϊ5
    }
    
    public void run()                            //�߳����з�����ȡ��
    {
        while(true)
        {
            Integer value = this.buffer.get(this.order); //���ն��󣬲�֪�����ն��������
            if(value==null)                      //�����̷߳��͵Ľ������
            //    break;
                return;
            this.text.append(String.format("%4d", value)); //�ı��������
            try
            {
                Thread.sleep(100);               //������ʾÿ���Ƶ��ٶ�
            }
            catch(InterruptedException ex) {}
        }
//        System.out.println(this.getClass().getName()+" "+this.order+"������");
        //�������߳�û�з��ͽ�����ǣ���������ѭ�����߳�һֱ�ڵȴ�ȡ�ƣ�ֱ����������
    }
}
//@author��Yeheya��2017��8��20�գ�2017��10��1��