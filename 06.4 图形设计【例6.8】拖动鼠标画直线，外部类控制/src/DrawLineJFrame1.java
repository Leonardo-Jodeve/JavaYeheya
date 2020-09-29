//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年10月5日
//§6.4.1 图形设计
//【例6.8】 拖动鼠标画直线。
//技术特点：外部类控制。
//与例6.8不同，只响应鼠标事件，没有响应鼠标拖动事件，释放鼠标时才画，没有显示直线轨迹。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//画直线框架类，响应鼠标事件，画直线，没有显示画直线轨迹
public class DrawLineJFrame1 extends JFrame implements MouseListener
{
    private Point start, end;                    //分别记录直线的起点、终点
    private Canvas canvas;                       //画布组件

    public DrawLineJFrame1()
    {
        super("使用鼠标画直线");
        this.setBounds(400,300,400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.start = this.end = null;
        this.canvas = new DrawLineCanvas();
        this.getContentPane().add(this.canvas);            //添加画布组件
        this.canvas.addMouseListener(this);                //画布监听鼠标事件
        this.setVisible(true);
    }
    
    //以下实现MouseListener鼠标事件接口，将鼠标按下点、鼠标释放点作为直线起点和终点
    public void mousePressed(MouseEvent event)             //鼠标按下事件处理方法
    {
        this.start = new Point(event.getX(), event.getY());//记录直线起点坐标
    }
    public void mouseReleased(MouseEvent event)            //鼠标释放事件处理方法
    {
        this.end = new Point(event.getX(), event.getY());  //记录直线终点坐标
        this.canvas.repaint();                             //画直线(start, end)
    } 
    public void mouseClicked(MouseEvent event){}           //鼠标单击
    public void mouseEntered(MouseEvent event){}           //鼠标进入
    public void mouseExited(MouseEvent event) {}           //鼠标离开

    
    //画直线画布组件内部类，继承画布组件类，仅画图
    private class DrawLineCanvas extends Canvas 
    {
        //画图方法，先用背景色画（擦除）直线(start, lastend)；再用指定颜色画直线(start, end)
        public void paint(Graphics g) 
        {
            if(start!=null && end!=null)         //访问外部类.this.start和end 
            {
                g.setColor(Color.blue);                    //设置画线颜色
                g.drawLine(start.x, start.y, end.x, end.y);//画直线(start, end)
            }
        }
        
        public void update(Graphics g)           //组件更新，重画之前图形，自动执行。//若没有该方法，只显示一条直线
        {
            this.paint(g);
        }
    }//内部类结束
    
    public static void main(String arg[])
    {
        new DrawLineJFrame1();
    }
}
//@author：Yeheya，2016-10-5，2017年2月14日，2018年7月1日