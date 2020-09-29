//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��18��
//��6.1.1 AWT���
//����6.1˼���⡿��ʵ��6������������������������ʽ��
//arithmetic operation ��������

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��������������࣬�̳п���ࣻ��Ӧ�����¼���
//�ص㣺Swing�����������飬�����������ɱ䣻��ֵ��ʽ�쳣�����������Ϊ0����
public class ArithmeticOperationJFrame extends JFrame implements ActionListener
{
    private JTextField[] texts;                  //�ı������飬��������������
    private JComboBox<String>[] comboxs;         //��Ͽ����飬���������
    private JButton button;
    
    public ArithmeticOperationJFrame(int number)
    {
        super("����������������");        
        if(number<2 || number>6)
            throw new java.lang.IllegalArgumentException(number+"����2��6��Χ��"); //��Ч�����쳣
        this.setSize(160*(number+1),100);
        Dimension dim=getToolkit().getScreenSize();        //�����Ļ�ֱ���
        this.setLocation((dim.width-this.getWidth())/2, (dim.height-this.getHeight())/2);//���ھ���
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.getContentPane().setLayout(new FlowLayout()); //������

        this.texts = new JTextField[number+1];
        String[] operators={"+", "-", "*", "/"};           //��������������������
        this.comboxs = new JComboBox[number-1];            //��Ͽ�����
        for(int i=0; i<number; i++)
        {
            this.texts[i] = new JTextField("0.0",8);
            this.getContentPane().add(this.texts[i]);
            if(i<number-1)
            {
                this.comboxs[i] = new JComboBox(operators);//��Ͽ�Ĭ�ϲ��ɱ༭
                this.getContentPane().add(this.comboxs[i]);
            }
        }
        this.button = new JButton("=");
        this.getContentPane().add(button);
        this.button.addActionListener(this);               //��ťע�ᵥ���¼�������
        this.texts[number] = new JTextField(10);
        this.getContentPane().add(this.texts[number]);
        this.texts[number].setEditable(false);             //ֻ����ʾ��������༭
        this.setVisible(true);
    }
    
    public ArithmeticOperationJFrame()
    {
        this(3);
    }
    
    public void actionPerformed(ActionEvent e)             //�����¼���������������ť
    {
        try
        {
            double x = Double.parseDouble(texts[0].getText());
            for(int i=1; i<this.texts.length-1; i++)
            {
                double y = Double.parseDouble(this.texts[i].getText());
                switch(this.comboxs[i-1].getSelectedIndex())//�����Ͽ�ѡ�����
                {
                    case 0: x+=y; break;                   //'+'
                    case 1: x-=y; break;                   //'-'
                    case 2: x*=y; break;                   //'*'
                    case 3: if(y>0.000001)                 //'/'
                                x/=y;
                            else 
                            {
                                JOptionPane.showMessageDialog(this, "����Ϊ0�����ܼ��㣬����������!");
                                return;
                            }
                }
            }
            this.texts[this.texts.length-1].setText(x+"");
        }
        catch(NumberFormatException nfex)
        {
            JOptionPane.showMessageDialog(this,"�ַ�������ת��������������������!");
        }
        finally{}
    }
    
    public static void main(String arg[])
    {
        new ArithmeticOperationJFrame(4);
    }
}
//@author��Yeheya��2018��7��19��