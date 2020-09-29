//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��26��
//��8.2.5  �ܵ��ֽ���
//����8.4��  ʹ�ùܵ��ֽ���ʵ�ַ��Ƴ���

import java.awt.Font;
import javax.swing.*;
import java.io.*;

//ʹ�ùܵ��ֽ�����ȡ�ƿ���࣬ʵ�ֿ����нӿڣ������߳�
public class CardReceiveFromStreamJFrame extends JFrame implements Runnable
{
    private InputStream in;                      //�ֽ�������
    private JTextArea text;                      //��ʾ��ֵ���ı���
    String title;                                //Ϊ�˲��ԣ���5��̲�ûд
    
    //���췽�������������ߴ��ݸ�in���ǹܵ��ֽ���������titleָ�����ڱ��⣻x��yָ����������
    public CardReceiveFromStreamJFrame(InputStream in, String title, int x, int y)
    {
        super(title);        
        this.setBounds(x,y,250,150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.in = in;
        this.title = title; 

        this.text = new JTextArea();
        this.getContentPane().add(this.text);
        this.text.setLineWrap(true);             //�����ı����Զ�����
        this.text.setEditable(false);            //���ɱ༭
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));//��������
        this.setVisible(true);
        new Thread(this).start();                //����ȡ���̣߳�this���̵߳�Ŀ�����
    }
    
    public void run()                            //�߳����з�����ȡ��
    {
        DataInputStream datain = new DataInputStream(this.in);//����������������Դ�ǹܵ��ֽ�������
        while(true)
        {
            try
            {
                this.text.append(String.format("%4d",datain.readInt()));//�ı�����Ӵ���������ȡ����
                Thread.sleep(100);               //������ʾÿ���Ƶ��ٶ�
            }
            catch(EOFException ex)               //���ڲ���
            {
                System.out.println(title+"EOF");
                break;
            }
            catch(IOException |InterruptedException ex)    //���������ֽ�����������ʱ�����EOFException���쳣
            {
                System.out.println(title+"IOE");
                break;
            }
        }
        try 
        {
            datain.close();                               //�ر������ֽ�������
            this.in.close();                              //�رչܵ��ֽ�������
        }
        catch(IOException ex) {}
    }
}
//@author��Yeheya��2017��8��26�գ�2017��11��28��