//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��2��
//��6.2.1 ί���¼�ģ��
//����6.2�� Unicode�ַ������ѯ����
//��˼����6-1�������ı������壬�ı��ֺš�������ʾ��

import java.awt.*;
import java.awt.event.*;

//�ַ������ѯ������࣬�̳п���ࣻ����ʵ��ActionListner�ӿڱ�ʾҪ��Ӧ�����¼���
//��5��̲�ûдʵ��ComponentListener�ӿڹ��ܣ�������ʾ�á�
public class QueryFrame extends Frame
    implements ActionListener, ComponentListener //, WindowListener
{
    private TextField text_char, text_uni;            //��ʾ�ַ��ͱ����ı���
    private Button button_char, button_uni;           //��ѯ������ַ���ť
    //���������ڹ��췽���д������õ�ʵ�������¼���������ʹ�ã���������Ϊ��Ա����
    private TextField text_hex;                       //��˼����6-1���ı��У���ʾʮ������

    public QueryFrame()                               //���췽��
    {
        super("�ַ������ѯ��");                       //�������ÿ�ܵı��������
//        this.setBounds(300,240,340,120);              //���ÿ�ܵ�λ�úͳߴ�
        this.setBackground(Color.lightGray);          //���ÿ�ܵı�����ɫΪǳ��ɫ
//        this.setLayout(new GridLayout(2,3));          //������񲼾֣�2��3��

        this.setBounds(300,240,1024,320);             //���ÿ�ܵ�λ�úͳߴ硾˼����6-1��
        this.setLayout(new GridLayout(3,3,2,2));      //������񲼾֣�3��3�У������ˮƽ�ʹ�ֱ���Ϊ2��˼����6-1��

        //�����ڿ�ܴ�����������������ÿ�и���һ����ǩ���ı��С���ť���
        this.add(new Label("�ַ�", Label.RIGHT));     //��ӱ�ǩ������Ҷ��룩
        this.text_char = new TextField("����",10);    //�ı������
        this.add(this.text_char);
        this.text_char.addActionListener(this);       //��˼����6-1���ı���ע�ᶯ���¼���������ί��this�������¼�
        
        this.button_char = new Button("��ѯUnicode��");  //��ť���
        this.add(this.button_char);
        this.button_char.addActionListener(this);     //��ťע�ᶯ���¼���������ί��this�������¼�

        //���������һ�б�ǩ���ı��С���ť���
        this.add(new Label("Unicode����", Label.RIGHT));
        this.add(this.text_uni = new TextField(10));    
        this.text_uni.addActionListener(this);        //��˼����6-1���ı���ע�ᶯ���¼���������ί��this�������¼�
        this.add(this.button_uni = new Button("��ѯ�ַ�"));
        this.button_uni.addActionListener(this);      //��ťע�ᶯ���¼���������ί��this�������¼�

        //��˼����6-1�������ڿ�ܴ���������һ�����������һ����ǩ���ı��С���ť���
        this.add(new Label("Unicode���루ʮ�����ƣ�", Label.RIGHT));//��ӱ�ǩ���Ҷ��룩
        this.add(this.text_hex = new TextField(10));
        this.text_hex.setEditable(false);             //ֻ����ʾ�����ܱ༭

        this.setVisible(true);                        //��ʾ���
        this.addWindowListener(new WinClose());       //���ע�ᴰ���¼���������ί��WinClose�������¼�
//        this.addWindowListener(this);               //���ע�ᴰ���¼���������ί��this�������¼�
        this.addComponentListener(this);              //���ע������¼����������ı��ֺ�
    }

    //�����¼���������ʵ��ActionListener�ӿڡ�
    //������ť���ı���ʱ����ִ�У�event.getSource()���ص������Ǹ���ť���¼�Դ�����
    public void actionPerformed(ActionEvent event)
    {
        //��������ѯUnicode�롱��ť�����ڡ��ַ����ı����а��˻س������Ƚ�����
        if(event.getSource()==this.button_char || event.getSource()==this.text_char)
        {
            String str = this.text_char.getText();    //����ı����ַ���
            if(!str.isEmpty())                        //����ǿմ�ִ�в���������ͬ!str.equals("")��������str!=""
            {
                char ch = str.charAt(0);              //������ַ�
                this.text_char.setText(""+ch);        //���������ı�����ʾ�ַ�
                this.text_uni.setText(""+(int)ch);    //��ʾch��Unicode��
                this.text_hex.setText("0x"+Integer.toHexString((int)ch));
                //��ʮ��������ʽ��ʾch��Unicode�롾˼����6-1��
            }
        }        
        
        //��������ѯ�ַ�����ť�����ڡ����롱�ı����а��˻س���
        else if(event.getSource()==this.button_uni || event.getSource()==this.text_uni)
        {
            String str = this.text_uni.getText();     //����ı����ַ���
            if(!str.isEmpty())
            {
                //���½�str�ַ���ת����������δ�����쳣������ʾUnicode����Ϊ���������ַ�
//                this.text_char.setText(""+(char)Integer.parseInt(str));

            	int uni=Integer.parseInt(str);        //���ı����ַ���ת����������δ����NumberFormatException�쳣
                this.text_char.setText(""+(char)uni); //��ʾuni��Unicode�����Ӧ���ַ�
                this.text_hex.setText("0x"+Integer.toHexString(uni));//��ʮ��������ʽ��ʾuni��Unicode���롾˼����6-1��
            }
        }
    }   
    
    public static void main(String[] arg)
    { 
        new QueryFrame();
    }
    
    //����ʵ�ִ����¼��������ӿڣ����رմ���ʱ��������������
//    public void windowClosing(WindowEvent event)           //�رմ���ʱִ�е��¼�������
//    {
//        System.exit(0);                                    //������������
//    }
//    public void windowOpened(WindowEvent event)     {}     //�򿪴��ں�ִ��
//    public void windowActivated(WindowEvent event)  {}     //����ں�ִ��
//    public void windowDeactivated(WindowEvent event){}     //��Ϊ������ں�ִ��
//    public void windowIconified(WindowEvent event)  {}     //������С����ִ��
//    public void windowDeiconified(WindowEvent event){}     //���ڻָ���ִ��
//    public void windowClosed(WindowEvent event)     {}     //�رմ��ں�ִ��    
     
    //��˼����6-1���ı��ֺš�
    //��5��̲�ûд��������ʾ�á���6.3д����֮ͬ������6.2 Frame����������6.3 JFrame�����ݴ�����������
    //���·���ʵ������¼��������ӿڡ�ͨ�÷��������ǲ��ɶ�����Ϊһ���ࡣ
    //���ı������Сʱ�����ÿ�����ݴ�������������������ֺţ�ʹ֮���ſ�ܳߴ���ı�
    public void componentResized(ComponentEvent event)
    {
//        System.out.println(event.getComponent().getClass().getName()); //��ǰFrame
        int size=(this.getWidth()+this.getHeight())/40;    //�����ֺ�
        Font font=new Font("����",1,size);                 //����
        int n= this.getComponentCount();                   //��ÿ�����ݴ����е��������
        for(int i=0; i<n; i++)                             //���ÿ�����ݴ������������������
            this.getComponent(i).setFont(font);
    }
    public void componentMoved(ComponentEvent event) {}    //�ƶ����
    public void componentShown(ComponentEvent event) {}    //��ʾ���
    public void componentHidden(ComponentEvent event){}    //�������
}
//@author��Yeheya��2016-9-17 ������ڣ�2018��2��5�գ�2018��7��19��