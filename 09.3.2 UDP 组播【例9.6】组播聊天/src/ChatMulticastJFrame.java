//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年12月15日
//§9.3.2  UDP组播通信
//【例9.6】 组播聊天。

import java.awt.event.*;
import javax.swing.*;
import java.net.*;

//组播聊天框架类，继承框架类，响应动作事件；采用UDP组播通信 
public class ChatMulticastJFrame extends JFrame implements ActionListener
{
    private String name;                                   //网名
    private InetAddress dip;                               //组播地址  
    private MulticastSocket mulsocket;                     //组播套接字  
    private int port;                                      //组播端口
    private JTextField text_sender;                        //发送内容文本行
    
    //构造方法，name指定网名，group、port指定组播地址和端口
    public ChatMulticastJFrame(String name, String group, int port) throws java.io.IOException
    {
        super("造车小队  "+name+"  "+group+"："+port);
        this.setBounds(320,240,560,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下在框架内容窗格中部添加显示对话内容的文本区
        JTextArea text_receiver = new JTextArea();
        text_receiver.setEditable(false);
        this.getContentPane().add(new JScrollPane(text_receiver));
        
        //以下工具栏，有发送内容文本行和发送按钮
        JToolBar toolbar = new JToolBar();
        this.getContentPane().add(toolbar,"South");
        toolbar.add(this.text_sender=new JTextField(20));
        JButton button = new JButton("发送");
        toolbar.add(button);
        button.addActionListener(this);
        this.setVisible(true);
        
        //以下获得自己网名、组播地址和端口
        this.name = name;
        this.dip = InetAddress.getByName(group);
        this.port = port;

        //以下接收数据报包，解压缩获得包裹内容，将字节序列转换成字符串显示在文本区中
        byte[] data = new byte[512];
        DatagramPacket packet = new DatagramPacket(data,data.length); //待接收数据报包
        this.mulsocket = new MulticastSocket(port);        //组播Socket
        this.mulsocket.joinGroup(this.dip);                //加入组
        while(this.mulsocket!=null)
        {
            this.mulsocket.receive(packet);                //接收数据报包
            text_receiver.append(new String(packet.getData(),0,packet.getLength())+"\r\n");
        }
    }
        
    public void actionPerformed(ActionEvent event)         //"发送"按钮单击事件处理方法
    {
        if(event.getActionCommand().equals("发送"))
        {
            //下句将字符串转换成字节数组，使用默认字符集GBK；再发送
            byte[] data=(this.name+" 说："+this.text_sender.getText()).getBytes();
            try
            {
                this.mulsocket.send(new DatagramPacket(data, data.length, this.dip, this.port)); //发送数据报
                this.text_sender.setText("");
            }
            catch(java.io.IOException ex)
            {
                JOptionPane.showMessageDialog(this, "IP地址或端口错误，发送错误。");
//                ex.printStackTrace();
            }   
        }
    }

    public static void main(String args[]) throws java.io.IOException
    {
//        new ChatMulticastJFrame("玉公主", "224.119.81.9", 30001);//参数指定网名、组播地址和端口
    	
        //组播地址与例9.5相同，端口不同；
    	//与例9.5同时运行，说明“组播地址和端口”代表一个组播组。
    	new ChatMulticastJFrame("玉公主", "224.116.8.0", 20010);
    }
}
//@author：Yeheya，2017年12月15日