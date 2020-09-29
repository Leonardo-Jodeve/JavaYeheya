//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月2日
//§6.1.2 布局管理
//2. 边布局管理器

import java.awt.*;

public class BorderLayoutFrame extends Frame
{
    public BorderLayoutFrame()
    {
        super("BorderLayout");                        //框架默认布局是BorderLayout
        this.setSize(340,140);

        this.add(new Button("东"),BorderLayout.EAST);  //组件添加在框架东边
        this.add(new Button("南"),"South");
        this.add(new Button("西"),"West");
        this.add(new Button("北"),"North");
        Button button_center = new Button("中"); 
        this.add(button_center,BorderLayout.CENTER);   //可以省略为this.add(button_center);      
        this.setVisible(true);
    }

    public static void main(String[] arg)
    {
        new BorderLayoutFrame();
    }
}
//@author：Yeheya，2016-9-2