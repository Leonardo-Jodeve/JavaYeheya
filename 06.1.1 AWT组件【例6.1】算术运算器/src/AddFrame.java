//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月18日
//§6.1.1 AWT组件
//【例6.1】加法运算器。

import java.awt.*;                               //导入AWT包

//加法运算器框架类，继承框架类。
//特点：Frame，3个文本行，没有成员变量，没有事件。
public class AddFrame extends Frame
{
    public AddFrame()                            //构造方法
    {
        //以下设置框架的标题、尺寸、位置、背景色、布局等属性
        super("加法运算");                        //设置框架标题
        this.setSize(400, 90);                   //设置组件尺寸
        this.setLocation(300, 240);              //设置组件的显示位置
//        this.setBackground(Color.lightGray);     //设置组件的背景颜色为浅灰色
//        this.setLayout(new FlowLayout());        //设置框架流布局，居中
//        this.setLayout(new BorderLayout());        //窗口默认边布局
        
        //以下在框架上添加标签、文本行、按钮等组件
        this.add(new TextField("10",8));         //添加文本行(初值,宽度)
        this.add(new Label("+"));                //添加标签组件 
        this.add(new TextField("20",8));
        this.add(new Button("="));               //添加按钮(标题)
        this.add(new TextField(10));              //添加文本行(初值默认为"")
        this.setVisible(true);                   //显示框架，必须在添加组件后
    }
    
    public static void main(String[] arg)
    {
        new AddFrame();
    }
}
//@author：Yeheya，2018年7月19日