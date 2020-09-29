//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��18��
//��6.1.1 AWT���
//����6.1˼���⡿����������������
//arithmetic operation ��������

import java.awt.*;                               //����AWT��
import java.awt.event.*;
import javax.swing.*;

//��������������࣬�̳п���ࣻ�رմ��ڣ���Ӧ�����¼�
public class ArithOperJFrame extends JFrame implements ActionListener
{
    private JTextField[] texts;                  //�ı������飬��������������
    private JComboBox<String> combox;            //��Ͽ����������
    private JButton button;
    private String[] operators={"+","-","*","/","%"}; //���������
    
    public ArithOperJFrame()
    {
        super("����������");        
        this.setSize(460,100);
        this.setLocation(200,200);
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.getContentPane().setLayout(new FlowLayout()); //������

        this.texts = new JTextField[3];                    //�ı������飬2����������1��������
        for(int i=0; i<this.texts.length; i++)
        {
            this.texts[i] = new JTextField("",6);          //�ı���
            this.getContentPane().add(this.texts[i]);
        }
        this.texts[this.texts.length-1].setEditable(false);//ֻ����ʾ��������༭
        
        this.combox = new JComboBox(operators);            //��Ͽ�Ĭ�ϲ��ɱ༭
        this.getContentPane().add(this.combox,1);

        this.button = new JButton("=");
        this.getContentPane().add(button,3);
        this.button.addActionListener(this);               //��ť��Ӷ����¼�������
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)    //�����¼���������������ť
    {
        try
        {
            int x = Integer.parseInt(this.texts[0].getText());
            int y = Integer.parseInt(this.texts[1].getText());
            switch(this.combox.getSelectedIndex())    //�����Ͽ�ѡ�����
            {
                case 0: x+=y; break;                   //+
                case 1: x-=y; break;                   //-
                case 2: x*=y; break;                   //*
                case 3: if(y!=0)                       //��/����������
                        {
                	        x/=y; break;
                        }
                        else 
                        {
                            JOptionPane.showMessageDialog(this, "����Ϊ0�����ܼ��㡣");
                            this.texts[this.texts.length-1].setText("");
                            return;
                        }
                case 4: x%=y; break;                   //%��ȡ����
            }
            this.texts[this.texts.length-1].setText(x+"");
        }
        catch(NumberFormatException nfex)
        {
            try
            {
                double x = Double.parseDouble(this.texts[0].getText());
                double y = Double.parseDouble(this.texts[1].getText());
                switch(this.combox.getSelectedIndex())    //�����Ͽ�ѡ�����
                {
                    case 0: x+=y; break;                   //+
                    case 1: x-=y; break;                   //-
                    case 2: x*=y; break;                   //*
                    case 3: if(y!=0)                       //��/����������
                            {
                    	        x/=y;
                    	        break;
                            }
                            else 
                            {
                                JOptionPane.showMessageDialog(this, "����Ϊ0�����ܼ��㡣");
                                this.texts[this.texts.length-1].setText("");
                                return;
                            }
                    case 4: JOptionPane.showMessageDialog(this, "���������ܽ���%���㡣");
                            this.texts[this.texts.length-1].setText("");
                            return;
                }
                this.texts[this.texts.length-1].setText(x+"");
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "�ַ�������ת���ɸ�������");
            }
        }
        finally{}
    }
    
    public static void main(String[] arg)
    {
        new ArithOperJFrame();
    }
}
//@author��Yeheya��2018��7��19�գ�2018��10��29��