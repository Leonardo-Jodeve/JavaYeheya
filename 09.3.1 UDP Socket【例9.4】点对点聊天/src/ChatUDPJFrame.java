//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年12月9日
//§9.3.1  UDP Socket点对点通信
//【例9.4】点对点聊天，采用UDP数据报通信实现。
//图形用户界面类似例9.0，没有“离线”按钮。

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

//采用UDP Socket通信的点对点聊天程序框架类，继承框架类，响应动作事件；
//没有用线程数据
public class ChatUDPJFrame extends JFrame implements ActionListener
{
    private String name;                         //网名
    private InetAddress destip;                  //目标主机名或IP地址
    private int destport;                        //目标主机的接收端口
    private JTextArea text_receiver;             //显示对话内容的文本区
    private JTextField text_sender, text_port;   //发送内容文本行和发送端口文本行
    
    //构造方法，name指定网名，receiveport指定本机接收端口；
    //host指定目标主机名或IP地址，destport指定目标主机的接收端口
    public ChatUDPJFrame(String name, int receiveport,  String host, int destport) throws Exception
    {
        super("聊天室"+name+"  "+InetAddress.getLocalHost().toString()+" : "+receiveport);
        this.setBounds(320,240,560,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下在框架内容窗格中部添加显示对话内容的文本区
        this.text_receiver = new JTextArea();              
        this.text_receiver.setEditable(false);
        this.text_receiver.setFont(new Font("宋体", Font.BOLD, 20));
        this.getContentPane().add(new JScrollPane(this.text_receiver));
        
        //以下工具栏，有发送内容、端口文本行和发送按钮
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"South");
        toolbar.add(this.text_sender=new JTextField(30));  //发送内容文本行
        JButton button = new JButton("发送");
        toolbar.add(button);
        button.addActionListener(this);   
        toolbar.add(new JLabel("端口"));
        toolbar.add(this.text_port=new JTextField());      //发送端口文本行
        this.text_port.setHorizontalAlignment(JTextField.CENTER); //设置水平对齐方式为居中
        this.setVisible(true);
        
        //以下获得自己网名、目标主机的IP地址和接收端口
        this.name = name;
        this.destip=InetAddress.getByName(host); //目标主机名或IP地址 
        this.destport=destport;                  //目标主机的接收端口

        //以下接收数据报包，解压缩获得包裹内容，将字节序列转换成字符串显示在文本区中
        byte[] data = new byte[512];             //声明字节数组存储数据报包内容
        DatagramPacket packet=new DatagramPacket(data,data.length);//创建接收数据报包
        DatagramSocket datasocket=new DatagramSocket(receiveport); //创建接收Socket
        while(datasocket!=null)   
        {
            datasocket.receive(packet);          //接收数据报包到packet
            //下句由packet包中字节数组构造字符串，使用默认字符集GBK
            this.text_receiver.append(new String(packet.getData(),0,packet.getLength())+"\r\n");
        }
    }
        
    public void actionPerformed(ActionEvent event)  //单击"发送"按钮
    {
        if(event.getActionCommand().equals("发送"))
        {
            //以下将字符串转换成字节数组，使用默认字符集GBK；再发送
            byte[] data=(name+" 说："+this.text_sender.getText()).getBytes();
            try
            {
                DatagramPacket packet=new DatagramPacket(data, data.length, destip, destport);
                DatagramSocket datasocket = new DatagramSocket();    //绑定一个可用端口用于发送
                datasocket.send(packet);                             //发送数据报包
                this.text_port.setText(datasocket.getLocalPort()+"");//显示本机发送端口
                this.text_receiver.append("我说："+this.text_sender.getText()+"\n");
                this.text_sender.setText("");
            }
            catch(SocketException ex)            //Socket异常
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误。");
                System.out.println(ex.getClass().getName());
            }
            catch(IOException ex)                //包含Socket异常
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误，发送错误。");
                System.out.println(ex.getClass().getName());
//                ex.printStackTrace();
            }   
        }
    }

    public static void main(String[] args) throws Exception
    {   //指定网名、本机接收端口、目标主机IP地址和接收端口
        new ChatUDPJFrame("玉公主", 20001, "127.0.0.1", 20002);
    }
}
//@author：Yeheya，2017年12月12日