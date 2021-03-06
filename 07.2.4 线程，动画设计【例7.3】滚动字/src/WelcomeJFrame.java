//《Java程序设计实用教程（第4版）》，作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》，作者：叶核亚，2017年7月19日
//§7.2.4 使用线程实现动画设计
//【例7.3】滚动字。
//2017年10月1日改进，多个时刻分别显示线程NEW、运行、等待态；面板内部类中使用按钮数组
//不需要响应FocusListener焦点事件

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeJFrame extends JFrame        //滚动字框架类
{
    public WelcomeJFrame(String[] texts)         //texts指定移动字符串，数组长度决定窗口中的面板数
    {
        super("滚动字");        
        this.setBounds(300,300,690,260);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        if(texts==null || texts.length==0)
            this.getContentPane().add(new RollbyJPanel("Welcome!")); //至少有一行字符串
        else
        {
            this.getContentPane().setLayout(new GridLayout(texts.length,1));//网格布局，多行1列
            for(int i=0; i<texts.length; i++)
                this.getContentPane().add(new RollbyJPanel(texts[i])); 
        }
        this.setVisible(true);
    }    
    public WelcomeJFrame()
    {
        this(null);
    }  
    
    //以下声明滚动字面板类，私有内部类，实现动作事件监听器接口和可运行接口；对象嵌套
    private class RollbyJPanel extends JPanel implements ActionListener, Runnable
    {
        JTextField text_word, texts[];                     //滚动字文本行；文本行数组
        JButton[] buttons;                                 //按钮数组
        Thread thread;                                     //线程对象
        int sleeptime;                                     //线程睡眠时间
        Font font = new Font("宋体",1,20);                 //字体

        public RollbyJPanel(String text)                   //滚动字面板类构造方法
        {
            this.setLayout(new GridLayout(2,1));
            this.text_word = new JTextField(String.format("%60s", text));  //text后加空格字符串
            this.add(this.text_word);                      //滚动字文本行
            this.text_word.setFont(font);
            
            //以下创建命令面板，添加滚动字、线程睡眠时间和线程状态等文本行，以及启动、中断等按钮
            JPanel cmdpanel = new JPanel();
            this.add(cmdpanel);
            String[] textstr={"sleeptime","State1","State2","isAlive"};//线程睡眠时间和线程状态等
            int[] widths={5,9,9,5};
            this.texts = new JTextField[textstr.length];
            for(int i=0; i<this.texts.length; i++)
            {
                cmdpanel.add(new JLabel(textstr[i]));
                cmdpanel.add(this.texts[i] = new JTextField(widths[i]));
                this.texts[i].setEditable(false);          //文本行不可编辑
            }
            this.sleeptime = (int)(Math.random()*100);     //产生随机数作为间隔时间
            this.texts[0].setText(""+sleeptime);
            this.texts[0].setEditable(true);               //文本行可编辑
            this.texts[0].addActionListener(this);         //sleeptime文本行响应动作事件

            String[] buttonstr={"启动","中断"};
            this.buttons = new JButton[buttonstr.length];
            for(int i=0; i<this.buttons.length; i++)
            {
                cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
                this.buttons[i].addActionListener(this);   //按钮响应动作事件
            }
            this.buttons[1].setEnabled(false);             //设置“中断”按钮为无效状态

            //以下创建的线程对象由thread引用，线程目标对象是this；显示线程状态
            this.thread = new Thread(this);//, text);          //滚动字初值作为线程名
            this.texts[1].setText(""+this.thread.getState()); //线程NEW新建态
            this.texts[3].setText(""+this.thread.isAlive());  //线程是否是活动的：false
        }

        public void run()              //线程运行方法，必须是公有方法，必须处理异常
        {
//            System.out.println(this.thread.getName()+"，run()，"+this.thread.getState());//RUNNABLE运行态
//            while(true) 
            while(this.thread.isAlive() && !this.thread.isInterrupted())//线程活动且没中断时，等价
            {
                try
                {
                    String str = this.text_word.getText();
                    this.text_word.setText(str.substring(1)+ str.substring(0,1));
                    Thread.sleep(this.sleeptime);          //线程睡眠，抛出中断异常
//                    System.out.println("run()，"+this.thread.getState());//此处仍然是RUNNABLE运行态；测试不出线程睡眠是什么状态
                }
                catch(InterruptedException ex)            //捕获并处理中断异常
                {
                    break;                                 //退出循环
                }
            }
        }                                                  //run()方法结束，线程进入TERMINATED终止态

        public void actionPerformed(ActionEvent event)     //动作事件处理，必须是公有方法
        {
            if(event.getSource()==this.texts[0])           //在sleeptime文本行中按回车键
            {
                try
                {
                    this.sleeptime=Integer.parseInt(this.texts[0].getText());//空串抛出异常
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(this,"\""+this.texts[1].getText()+"\" 不能转换成整数。");
//                    JOptionPane.showMessageDialog(this, "\""+ex.getMessage()+"\" 不能转换成整数。");
                }
            }

            else if(event.getSource()==this.buttons[0])    //单击“启动”按钮
            {
//              System.out.print(this.thread.getName()+"，"+this.thread.getState()+"，");//显示单击按钮前的线程状态
                //若线程不是NEW态（TERMINATED态，不是null），表示之前中断了，再重新创建一个线程
                if(this.thread.getState()!=Thread.State.NEW)
                    this.thread = new Thread(this);//, this.text_word.getText().trim());  //重新创建一个线程对象，线程名是删除空格的滚动字
                    //若此处没有新建线程，则再次启动将抛出java.lang.IllegalThreadStateException
                this.thread.start();                       //启动线程
//                System.out.print("启动，"+this.thread.getState());//RUNNABLE运行态；
                this.texts[1].setText(""+this.thread.getState());  //线程启动后进入RUNNABLE运行态
                this.buttons[0].setEnabled(false);
                this.buttons[1].setEnabled(true);
                this.texts[2].setText(""+this.thread.getState()); //相邻时刻再次显示，线程睡眠TIMED_WAITING态
//                System.out.println("，"+this.thread.getState());//线程睡眠TIMED_WAITING态；
            }
            else if(event.getSource()==this.buttons[1])    //单击“中断”按钮
            {
                this.thread.interrupt();                   //设置当前线程对象中断标记
                this.texts[1].setText(""+this.thread.getState());  //线程TIMED_WAITING状态，为什么？
                this.buttons[0].setEnabled(true);
                this.buttons[1].setEnabled(false);
                //设置线程中断后，sleep()抛出异常，run()结束，下句显示线程进入TERMINATED终止态
                this.texts[2].setText(""+this.thread.getState());
//                System.out.println("中断，"+this.thread.getState());//Welcome，TIMED_WAITING，中断，TERMINATED
            }
            this.texts[3].setText(""+this.thread.isAlive());  //线程是否是活动的
        }
    }//内部类结束

    public static void main(String arg[])
    {
        String texts[]={"Welcome","Hello","Rollby"};
        new WelcomeJFrame(texts);
    }
}
/*
程序设计说明如下。
（1）构造方法 
        new WelcomeJFrame();
相当于
        String[] texts={"Welcome"};
        new WelcomeJFrame(texts);

（2）字符串填充空格，也可
        char space[]=new char[100];
        java.util.Arrays.fill(space, ' ');             //将字符数组space填充满空格
        text_word = new JTextField(text+new String(space));  //text后加空格字符串
        
（3）输出线程状态如下：
Hello，NEW，启动，RUNNABLE，TIMED_WAITING
Hello，TIMED_WAITING，中断，TERMINATED
Hello，TERMINATED，启动，RUNNABLE，TIMED_WAITING
Hello，TIMED_WAITING，中断，TERMINATED

（4）Thread线程类的suspend()、resume()方法已被废弃，
       但JDK8仍然可运行，用作此例的暂停、恢复；相当于操作系统的挂起和恢复状态。 
            else if (event.getSource()==this.buttons[3])      //单击suspend按钮时
            {
                this.thread.suspend();                     //线程暂停，挂起
                this.texts[1].setText(""+this.thread.getState()); 
            }
            else if (event.getSource()==this.buttons[4])      //单击resume按钮时
            {
                this.thread.resume();                      //线程恢复
                this.texts[1].setText(""+this.thread.getState()); 
            }

（5）此例不能显示调用wait()方法后线程的状态，以下语句语法错误。      
            else if (event.getSource()==this.buttons[2])      //单击wait按钮时
            {
                try
                {
                    this.thread.wait();                   //设置当前线程对象中断标记
                    this.text_state.setText(""+this.thread.getState());
                }
                catch (IOException ex){}
            }
   该测试功能在例7.7同步缓冲区中实现。

*/
//@author：Yeheya，2017年7月20日，2017年9月30日，2017年10月1日