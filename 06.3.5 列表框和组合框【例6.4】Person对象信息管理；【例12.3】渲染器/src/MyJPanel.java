//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年5月14日
//§6.3.4 列表框和组合框
//【例6.4思考题】设置panel面板中所有组件的字体，讲课演示用。
//第5版教材没有写，不能用响应组件事件。
//【例6.4】使用。独立类，其他的应用程序要调用。

import java.awt.*;
import javax.swing.*;

public class MyJPanel 
{
    //第5版教材没写。通用方法。
    //设置panel面板中所有组件的字体，递归算法
    public static void setFont(JPanel panel, Font font)
    {
        int n= panel.getComponentCount();        //获得panel面板中的组件个数
        for(int i=0; i<n; i++)
        {
            Component comp = panel.getComponent(i); //获得panel面板中第i个组件
            if((comp instanceof JPanel))         //又是JPanel
            	setFont((JPanel)comp, font);     //递归调用
            else
            	comp.setFont(font);              //设置字体
        }
    }
}
//@author：Yeheya，2018年5月14日