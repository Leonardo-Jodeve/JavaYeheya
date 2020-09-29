//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年6月28日
//§6.4.1 图形设计
//【例6.8】 拖动鼠标画直线。
//技术特点：外部类控制。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//画直线框架类，响应鼠标事件和鼠标移动事件，画直线，显示画线轨迹
public class DrawLineJFrame extends JFrame implements MouseListener, MouseMotionListener
{
	protected Point start, end, lastend;           //分别记录直线的起点、终点、前一条直线的终点
    protected Canvas canvas;                       //画布组件

    public DrawLineJFrame()
    {
        super("拖动鼠标画直线");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下添加画布组件
        this.start = this.end = this.lastend = null;
        this.canvas = new DrawLineCanvas();
        this.getContentPane().add(this.canvas);   //添加画布组件
        this.canvas.addMouseListener(this);       //画布监听鼠标事件
        this.canvas.addMouseMotionListener(this); //画布监听鼠标移动事件
        this.setVisible(true);
    }
    
    //以下实现MouseListener鼠标事件接口，将鼠标按下点作为直线起点
    public void mousePressed(MouseEvent event)   //鼠标按下事件处理方法
    {
        this.start = new Point(event.getX(), event.getY());//记录直线起点坐标
    }
    public void mouseReleased(MouseEvent event){}//鼠标释放
    public void mouseClicked(MouseEvent event){} //鼠标单击
    public void mouseEntered(MouseEvent event){} //鼠标进入
    public void mouseExited(MouseEvent event) {} //鼠标离开

    //以下实现MouseMotionListener鼠标移动事件接口
    public void mouseMoved(MouseEvent event){}   //鼠标移动
    
    //鼠标拖动事件处理方法，每拖动一个点时执行画直线
    public void mouseDragged(MouseEvent event)
    {
    	this.lastend = this.end;                 //前一个点
    	this.end = new Point(event.getX(), event.getY());  //鼠标当前点
        this.canvas.repaint();         //擦掉直线(start, lastend)，再画直线(start, end)
    }
        
    //画直线画布组件内部类，继承画布组件类，仅画图
    private class DrawLineCanvas extends Canvas 
    {
        //画图方法，先用背景色画（擦除）直线(start, lastend)；再用指定颜色画直线(start, end)
        public void paint(Graphics g) 
        {
            if(start!=null && lastend!=null)
            {
                g.setColor(this.getBackground());          //设置画线颜色为背景色
                g.drawLine(start.x,start.y, lastend.x,lastend.y);//画直线(start, lastend)，擦除
//                g.drawOval(start.x, start.y, lastend.x, lastend.y);//画椭圆，如何设置线类型，粗线？如何设置填充方式？

                g.setColor(Color.blue);                    //设置画线颜色
                g.drawLine(start.x, start.y, end.x, end.y);//画直线(start, end)
//                g.drawOval(start.x, start.y, end.x, end.y);//画椭圆
//              g.drawRect(start.x, start.y, end.x, end.y);//画圆角矩形
            }
        }
        public void update(Graphics g)           //组件更新，重画之前图形，自动执行。//若没有该方法，只显示一条直线
        {
            this.paint(g);
        }
    }//内部类结束
    
    public static void main(String arg[])
    {
        new DrawLineJFrame();
    }
}
//@author：Yeheya，2016-10-5，2017年2月14日，2018年7月1日