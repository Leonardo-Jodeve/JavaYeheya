//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年6月28日
//§6.4.1 图形设计
//【例6.8思考题】 拖动鼠标随意画线。
//技术特点：外部类控制。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//随意画线框架类，响应鼠标事件和鼠标移动事件，显示鼠标轨迹。外部类控制
//只记录一条直线的起点终点坐标；没有选择颜色。
public class DrawJFrame extends JFrame implements MouseListener, MouseMotionListener
{
    protected Point start, end;                  //分别记录直线的起点、终点
    protected Canvas canvas;                     //画布组件

    public DrawJFrame()
    {
        super("拖动鼠标随意画线");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.start = this.end=null;
        this.canvas = new DrawCanvas();
        this.getContentPane().add(this.canvas);  //添加画布组件
        this.canvas.addMouseListener(this);      //画布监听鼠标事件
        this.canvas.addMouseMotionListener(this);//画布监听鼠标移动事件
        this.setVisible(true);
    }
    
    
    //以下实现MouseListener鼠标事件接口，鼠标按下点作为直线起点
    public void mousePressed(MouseEvent event)   //鼠标按下事件处理方法
    {
        this.start = null;
        this.end = new Point(event.getX(), event.getY());//记录鼠标当前点坐标
    }
    public void mouseReleased(MouseEvent event){}//鼠标释放
    public void mouseClicked(MouseEvent event){} //鼠标单击
    public void mouseEntered(MouseEvent event){} //鼠标进入
    public void mouseExited(MouseEvent event){}  //鼠标离开

    
    //以下实现MouseMotionListener鼠标移动事件接口
    public void mouseMoved(MouseEvent event){}   //鼠标移动
    
    //鼠标拖动事件处理方法，每拖动一个点时执行画直线
    public void mouseDragged(MouseEvent event)
    {
        this.start = this.end;                   //start记住前一个点
        this.end = new Point(event.getX(), event.getY());//鼠标当前点
        this.canvas.repaint();                   //画直线(start, end)
    }    
    
    //随意画线画布组件内部类，继承画布组件类，仅画图
    private class DrawCanvas extends Canvas
    {
        public void paint(Graphics g)            //画图方法
        {
            if(start!=null && end!=null)         //访问外部类.this.start和end 
            {
                g.setColor(Color.blue);          //设置画线颜色
                g.drawLine(start.x, start.y, end.x, end.y);//画直线(start, end)
            }
        }
        public void update(Graphics g)           //组件更新，重画之前图形，自动执行。//若没有该方法，只显示一条线
        {
            this.paint(g);
        }
    }//内部类结束
    
    public static void main(String arg[])
    {
        new DrawJFrame();
    }
}
/*
存在问题如下：
【例6.8思考题6-7】④ 当窗口最小化再还原时，窗口上只剩一条直线，为什么？如何还原画的所有图形？
*/
//@author：Yeheya，2016-10-5；2017年2月14日，2018年7月11日