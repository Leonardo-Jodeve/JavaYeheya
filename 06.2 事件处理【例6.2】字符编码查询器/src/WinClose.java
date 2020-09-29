//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月5日
//§6.2.1 委托事件模型
//【例6.2】 Unicode字符查询器。

import java.awt.event.*;

//实现窗口事件监听器接口的类，当关闭窗口时，结束程序运行
public class WinClose implements WindowListener
{
    public void windowClosing(WindowEvent event)           //关闭窗口时执行的事件处理方法
    {
        System.exit(0);                                    //结束程序运行
    }
    public void windowOpened(WindowEvent event)     {}     //打开窗口后执行
    public void windowActivated(WindowEvent event)  {}     //激活窗口后执行
    public void windowDeactivated(WindowEvent event){}     //变为不活动窗口后执行
    public void windowIconified(WindowEvent event)  {}     //窗口最小化后执行
    public void windowDeiconified(WindowEvent event){}     //窗口恢复后执行
    public void windowClosed(WindowEvent event)     {}     //关闭窗口后执行    
}
//@author：Yeheya，2018年2月5日
/*
class WinClose implements WindowListener         //实现窗口事件监听器接口
{
    public void windowClosing(WindowEvent e)     //关闭时，窗口关闭事件处理方法
    {
      	System.out.println(e.paramString());
        System.exit(0);                          //结束程序运行
    }
    public void windowOpened(WindowEvent e)       //打开时 
    {
    	System.out.println(e.paramString());
    }
    public void windowActivated(WindowEvent e)    //激活时
    {
    	System.out.println(e.paramString());
    }
    public void windowDeactivated(WindowEvent e)   //失去焦点时，如最小化
    {
    	System.out.println(e.paramString());
    }
    public void windowClosed(WindowEvent e)        //关闭后
    {
    	System.out.println(e.paramString());
    }
    public void windowIconified(WindowEvent e)        //最小化
    {
    	System.out.println(e.paramString());
    }
    public void windowDeiconified(WindowEvent e)    //最小化后恢复
    {
    	System.out.println(e.paramString());
    }
}
/*
WINDOW_ACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_ICONIFIED,opposite=null,oldState=0,newState=0
WINDOW_DEACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_DEICONIFIED,opposite=null,oldState=0,newState=0
WINDOW_ACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_CLOSING,opposite=null,oldState=0,newState=0

/*
或者
class WinClose extends WindowAdapter             //继承适配类
{
    public void windowClosing(WindowEvent e)     //单击窗口关闭按钮时触发并执行
    {                                            //覆盖WindowAdapter类中方法
        System.exit(0);                          //结束程序运行
    }
}
*/
//@author：Yeheya，2016-9-17