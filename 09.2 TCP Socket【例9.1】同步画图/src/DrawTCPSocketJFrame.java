//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年7月25日
//§6.4.1 图形设计
//【例6.8思考题】 拖动鼠标随意画线。外部类控制。
//§9.2 TCP Socket通信
//【例9.1】 同步画图，建立一条TCP连接。
//本例目的：② 此例客户端必须使用线程接收数据。

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//同步画图框架类，继承框架类，响应鼠标事件和鼠标移动事件，显示鼠标轨迹；
//采用TCP Socket通信，使用对象字节流传送鼠标拖动时每个坐标的Point点对象；
//实现Runnable接口，作为线程的目标对象，使用线程接收数据
public class DrawTCPSocketJFrame extends JFrame 
    implements MouseListener, MouseMotionListener, Runnable
{
	private Point start, end;                    //分别记录直线的起点、终点
	private Canvas canvas;                       //画布组件
    private Socket socket;
    private ObjectOutputStream objout;           //对象字节输出流
    
    //构造方法，服务端和客户端调用，name指定网名
    public DrawTCPSocketJFrame(String name) throws IOException
    {   //下句框架标题栏添加本机的主机名和IP地址
        super("同步画图 "+name+"  "+InetAddress.getLocalHost().toString());
        this.setBounds(400,300,580,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.start = this.end=null;
        this.canvas = new DrawCanvas();
        this.getContentPane().add(this.canvas);  //添加画布组件
        this.canvas.addMouseListener(this);      //画布监听鼠标事件
        this.canvas.addMouseMotionListener(this);//画布监听鼠标移动事件
        this.setVisible(true);
        this.objout = null;
    }

    //构造方法，客户端调用，name指定自己网名，host和port指定服务端的Socket
    public DrawTCPSocketJFrame(String name, String host, int port) throws IOException
    {
        this(name);
        Socket socket = new Socket(host, port);  //客户端向服务端主机的端口发出TCP连接请求
        this.setTitle(this.getTitle()+" : "+socket.getLocalPort());//获得本机端口
        this.setSocket(socket);
    }
   
    public void setSocket(Socket socket) throws IOException  //连接成功时，设置Socket，启动线程
    {
        this.socket = socket; 
        this.objout = new ObjectOutputStream(this.socket.getOutputStream());//从Socket获得字节流
        new Thread(this).start();                //使用线程接收数据
    }

    public void run()        //线程运行方法，采用字节流接收对方发来的对象，必须处理异常
    {
        try
        {   //下句从Socket获得字节流，再创建对象字节输入流
            ObjectInputStream objin = new ObjectInputStream(this.socket.getInputStream());
            while(true)
            {
                try
                {
                    this.start = this.end;                 //start记住前一个点 
                    this.end=(Point)objin.readObject();    //读取一个对象
//                  System.out.println(end.toString());
                    this.canvas.repaint();                 //画布画直线(start,end)
                }
                catch(EOFException ex)                     //对象输入流结束时抛出该异常
                {
                    break;
                }
            }
            objin.close();                                 //关闭对象流
            this.objout.close();
            this.socket.close();                           //关闭TCP连接
        }
        catch(IOException | ClassNotFoundException ex){}   //捕获多个异常
    }//关闭窗口时，程序运行停止，强制while循环停止，关闭所有TCP连接和服务。
    
    //以下实现MouseListener鼠标事件接口，必须处理异常。
    //鼠标按下事件处理方法，将鼠标按下点作为直线起点，建立TCP连接后发送给对方
    public void mousePressed(MouseEvent event)
    {
        this.start = null;
        this.end = new Point(event.getX(), event.getY());  //记录鼠标当前点坐标
        try
        {
            if(this.objout!=null)
                this.objout.writeObject(this.end);         //发送给对方鼠标按下位置的点对象
        }
        catch(IOException ex){}
    }
    public void mouseReleased(MouseEvent event){}          //鼠标释放
    public void mouseClicked(MouseEvent event){}           //鼠标单击
    public void mouseEntered(MouseEvent event){}           //鼠标进入
    public void mouseExited(MouseEvent event){}            //鼠标离开

    //以下实现MouseMotionListener鼠标移动事件接口，必须处理异常
    public void mouseMoved(MouseEvent event){}             //鼠标移动

    //鼠标拖动事件处理方法，每拖动一个点执行画直线；建立TCP连接后将鼠标拖动点发送给对方
    public void mouseDragged(MouseEvent event)
    {
        this.start = this.end;                             //start记住前一个点
        this.end = new Point(event.getX(), event.getY());  //鼠标当前点
        this.canvas.repaint();                             //画直线(start, end)
        try
        {
            if(this.objout!=null)
                this.objout.writeObject(this.end);         //发送给对方鼠标拖动位置的点对象
        }
        catch(IOException ex){}
    }

    //随意画线画布组件内部类，继承画布组件类，仅画图
    private class DrawCanvas extends Canvas
    {
        public void paint(Graphics g)                      //画图方法
        {
            if(start!=null && end!=null)                   //访问外部类.this.start和end 
            {
                g.setColor(Color.blue);                    //设置画线颜色
                g.drawLine(start.x, start.y, end.x, end.y);//画直线(start, end)
            }
        }        
        public void update(Graphics g)           //组件更新，重画之前图形，自动执行
        {
            this.paint(g);                       //重画之前图形
        }
    }//内部类结束
        
    public static void main(String[] arg) throws IOException
    {
        //参数指定网名、服务端主机的IP地址和端口；“127.0.0.1”是指定本机的虚拟地址
        new DrawTCPSocketJFrame("小蜜蜂", "127.0.0.1", 10011);
    }
}
//@author：Yeheya，2016-10-5；2017年2月14日，2018年7月25日