//《Java程序设计实用教程（第5版）试题库》 作者：叶核亚，2018年8月8日
//§12.3.2 列表框
//  2. 列表框单元渲染器
//在【例12.3】项目中，【例6.4】列表框用。

import java.awt.*;
import javax.swing.*;

//指定字体列表框单元渲染器类，将列表框单元渲染成复选框，以指定字体显示
public class FontListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    private Font font;                           //字体
    
    public FontListRenderer(Font font)
    {
        this.font = font;
    }
    public FontListRenderer()
    {
       this(new Font("宋体", Font.BOLD, 12));
    }
    
    //设置复选框属性。参数：jlist是列表框，value是jlist当前数据项，isSelected是否被选中
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //设置复选框标题是列表框当前数据项
        this.setFont(this.font);                           //设置字体
        this.setSelected(isSelected);                      //设置选中状态是列表框当前项的选中状态
        this.setForeground(isSelected ? Color.red : Color.black);    //选中项标题红色显示
        this.setBackground(isSelected ? Color.lightGray : Color.white);//选中项背景浅灰色
        return this;                                       //返回渲染过的复选框组件
    }
}
//@author：Yeheya，2018年8月8日