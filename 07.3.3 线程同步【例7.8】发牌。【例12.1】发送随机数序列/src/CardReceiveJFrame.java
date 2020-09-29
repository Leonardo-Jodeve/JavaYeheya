//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.8】  发牌。

import java.awt.*;
import javax.swing.*;

//取牌框架类，包含接收线程
public class CardReceiveJFrame extends JFrame implements Runnable
{
    private CardBuffer<Integer> buffer;          //存放牌的缓冲区管程
    private JTextArea text;                      //显示牌值的文本区
    private int order;                           //约定取牌线程次序的信号量
    
    //构造方法，buffer指定取牌的同步缓冲区，order指定取牌序号，title指定窗口标题，x、y指定窗口坐标
    public CardReceiveJFrame(CardBuffer<Integer> buffer, int order, String title, int x, int y)
    {
        super(title);
        this.setBounds(x,y,290,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.buffer = buffer ;
        this.order = order;

        this.text = new JTextArea();
        this.getContentPane().add(this.text);
        this.text.setLineWrap(true);             //设置文本区自动换行
        this.text.setEditable(false);
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setVisible(true);
        new Thread(this).start();                //取牌线程，优先级为5
    }
    
    public void run()                            //线程运行方法，取牌
    {
        while(true)
        {
            Integer value = this.buffer.get(this.order); //接收对象，不知道接收对象的数量
            if(value==null)                      //发送线程发送的结束标记
            //    break;
                return;
            this.text.append(String.format("%4d", value)); //文本区添加牌
            try
            {
                Thread.sleep(100);               //控制显示每张牌的速度
            }
            catch(InterruptedException ex) {}
        }
//        System.out.println(this.getClass().getName()+" "+this.order+"结束。");
        //若发送线程没有发送结束标记，则上述死循环，线程一直在等待取牌，直到被析构。
    }
}
//@author：Yeheya，2017年8月20日，2017年10月1日