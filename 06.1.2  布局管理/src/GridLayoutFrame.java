//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月2日
//§6.1.2 布局管理
//3. GridLayout布局演示。

import java.awt.*;

public class GridLayoutFrame extends Frame
{
    public GridLayoutFrame()
    {
        super("GridLayout");
        this.setSize(320,140);
        this.setLayout(new GridLayout(4,4));     //网格布局，左右分隔窗口
        
        String[] str={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
        for(int i=0; i<str.length; i++)
            this.add(new Button(str[i]));
        this.setVisible(true);
    }

    public static void main(String[] arg)
    {
        new GridLayoutFrame();
    }
}
//@author：Yeheya，2016-9-2