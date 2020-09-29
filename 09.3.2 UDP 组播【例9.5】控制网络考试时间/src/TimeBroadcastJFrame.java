//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年12月12日
//§9.3.2  UDP组播通信
//【例9.5】  控制网络考试时间。
//（1）提供时间组播的服务端程序
//使用线程，每秒发送

import java.awt.Font;
import javax.swing.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

//时间组播服务框架类
public class TimeBroadcastJFrame extends JFrame
{
    private MulticastSocket mulsocket;                     //组播套接字  
    
    //构造方法，group、port指定组播地址和端口
    public TimeBroadcastJFrame(String group, int port) throws java.io.IOException
    {
        super("发送时间组播  "+group+" : "+port);
        this.setBounds(200,240,480,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label=new JLabel("",JLabel.CENTER);         //显示当前时间的标签，居中对齐
        this.getContentPane().add(label);
        label.setFont(new Font("楷体", Font.BOLD, 20));    //设置字体
        this.setVisible(true);
        
        //以下加入dip组播地址的组
        InetAddress dip = InetAddress.getByName(group);    //组播地址 
        this.mulsocket = new MulticastSocket(port);        //在port端口进行组播通信
        this.mulsocket.setTimeToLive(1);                   //发送数据报范围为本地网络
        this.mulsocket.joinGroup(dip);                     //加入组
        
        //以下每秒将当前时间（长整数）发送到dip组播组中；时间格式中，HH表示24小时制
        SimpleDateFormat datef=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        while(true)
        {
            long time=System.currentTimeMillis();          //获得当前时间
            label.setText(datef.format(new Date(time)));   //按时间格式显示当前时间
            byte[] data=(time+"").getBytes();    //将time长整数转换成字符串，再转换成字节数组
            DatagramPacket packet=new DatagramPacket(data, data.length, dip, port);//待发送数据报包
            try
            {
                this.mulsocket.send(packet);     //组播发送数据报，可被dip组中成员接收到
                Thread.sleep(1000);              //每秒发送
            }
            catch(InterruptedException ex)
            {
                JOptionPane.showMessageDialog(this, "发送错误");
                break;
//              ex.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) throws java.io.IOException
    {
        new TimeBroadcastJFrame("224.116.8.0", 20009);   //参数指定组播地址和端口
    }

    //没写也能执行，效果一样
//    public void finalize()                       //析构方法，第5版教材没写
//    {
//        this.mulsocket.close();                  //关闭组播Socket。执行，端口关闭，下次可再用
//        System.out.println("this.mulsocket.close()");//没写，析构不起作用？
//    }
}
//@author：Yeheya，2017年12月15日，2018年7月26日
//2018年7月26日，   (new Long(time)).    //没有找到将整数直接转换成字节数组的方法