//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2016年10月2日
//§12.3.2 列表框
//2. 列表框单元渲染器
//在【例12.3】项目中。【例6.5思考题】字号组合框用

import java.awt.*;
import javax.swing.*;

//字号列表框单元渲染器类，继承JCheckBox复选框类，实现列表框单元渲染器接口；
//设列表框存储字号整数，将列表框单元渲染成复选框，分别以各自字号显示。
public class FontSizeListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //设置复选框属性。参数：jlist是列表框，value是jlist当前数据项，isSelected是否被选中
    public Component getListCellRendererComponent(JList<?> jlist, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //设置复选框标题是列表框当前数据项
        this.setFont(new Font("Timers New Roman", Font.BOLD, (Integer)value));     //设置字体，value是字号
        this.setSelected(isSelected);                      //设置选中状态是列表框当前项的选中状态
        this.setForeground(isSelected ? Color.red : Color.black);    //选中项标题红色显示
        this.setBackground(isSelected ? Color.lightGray : Color.white);//选中项背景浅灰色
        return this;                                       //返回渲染过的复选框组件
    }
}
//@author：Yeheya，2016-10-7，2018年8月8日