//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年12月12日
//§9.3.2  UDP组播通信
//【例9.5】  控制网络考试时间。
//（2）接收时间组播的客户端程序

import java.awt.*; 
import javax.swing.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//接收时间组播框架类
public class ReceiverJFrame extends JFrame
{
    //group指定组播地址，port指定服务器组播端口，minutes指定运行时间（分） 
    public ReceiverJFrame(String group, int port, int minutes) throws java.io.IOException
    {
        super("接收时间组播  "+group+" : "+port);
        this.setBounds(320,240,500,120);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(2,4));
        
        //以下添加组件，显示多个时间
        String[] labelstr={"开始时间","结束时间","当前时间","剩余时间"};
        JTextField[] texts= new JTextField[labelstr.length];
        for(int i=0; i<texts.length; i++)
        {
            JLabel label=new JLabel(labelstr[i],JLabel.CENTER);
            this.getContentPane().add(label);//=new JLabel(labelstr[i],JLabel.CENTER));
            label.setFont(new Font("宋体", Font.BOLD, 20));//设置字体
            this.getContentPane().add(texts[i]=new JTextField());
            texts[i].setFont(new Font("宋体", Font.BOLD, 20));
            texts[i].setEditable(false);
        }
        texts[2].setForeground(new Color(255,0,0));        //以红色显示当前时间
        this.setVisible(true);
        
        //以下加入dip组播地址的组
        MulticastSocket mulsocket=new MulticastSocket(port); //在port端口接收组播
        InetAddress dip = InetAddress.getByName(group);    //组播地址
        mulsocket.joinGroup(dip);                          //加入组
        
        //以下接收数据包，从中获得当前时间，再计算时间
        byte[] data=new byte[512];
        DatagramPacket packet=new DatagramPacket(data, data.length, dip, port);//待接收数据报包
        mulsocket.receive(packet);                         //第一次接收
        //下句将packet包中字节数组转成字符串，再转换成long整数
        long time = Long.parseLong(new String(packet.getData(),0,packet.getLength()));
        SimpleDateFormat dataf = new SimpleDateFormat("HH:mm:ss");        
        texts[0].setText(dataf.format(new Date(time)));    //显示开始时间
        long lasttime=time+minutes*60*1000;
        texts[1].setText(dataf.format(new Date(lasttime)));//显示结束时间
        while(time<lasttime)                               //结束时间未到时运行
        {
            mulsocket.receive(packet);                     //接收数据报包
            time = Long.parseLong(new String(packet.getData(),0,packet.getLength()));
            texts[2].setText(dataf.format(new Date(time))); //显示当前时间
            texts[3].setText(((lasttime-time)/60000)+"分"); //显示剩余时间
        }
        JOptionPane.showMessageDialog(this, "考试结束，关闭程序");
        mulsocket.leaveGroup(dip);                         //离开组
        mulsocket.close();
        System.exit(0);                //若考试时间到，则弹出对话框，并终止应用程序运行
    }
    
    public static void main(String args[]) throws java.io.IOException 
    {
    	//参数指定组播地址、端口和考试时间长度，在其他计算机上运行也是这句
//    	new ReceiverJFrame("224.116.8.0", 20009, 60);       //考试时间为60分钟
        new ReceiverJFrame("224.116.8.0", 20009, 10);
    }
}
//@author：Yeheya，2017年12月15日，2018年7月26日