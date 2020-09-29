//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年8月7日
//§12.3.2 列表框
//【例12.4】 列表框多项选择与数据移动。

import java.awt.*;
import javax.swing.*;

//图标列表框单元渲染器类，继承JCheckBox复选框类，实现列表框单元渲染器接口；
//设列表框存储图标文件名字符串，将列表框单元渲染成复选框，添加图标。
public class IconListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //设置复选框属性。参数：jlist是列表框，value是jlist当前数据项，isSelected是否被选中
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //设置复选框标题是列表框当前数据项
        this.setSelected(isSelected);                      //设置选中状态是列表框当前项的选中状态
        this.setForeground(isSelected ? Color.red : Color.black);          //选中项标题红色显示
        this.setBackground(isSelected ? Color.lightGray : Color.white);    //选中项背景浅灰色
        this.setIcon(new ImageIcon(".\\国旗\\"+value.toString()+".gif"));  //添加图标
        return this;                                       //返回渲染过的复选框组件
    }
}
//@author：Yeheya，2018年8月9日
/*也可，只是数据项高度较小，太紧，不好看
public class IconRenderer extends JLabel implements ListCellRenderer<Object>
{
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());
        this.setBackground(isSelected ? Color.lightGray : Color.white);  //选中数据项背景浅灰色
        this.setForeground(isSelected ? Color.red : Color.black);        //选中数据项红色显示
        this.setIcon(new ImageIcon(".\\国旗\\"+value.toString()+".gif")); //添加图标
        return this;
    }
}*/
