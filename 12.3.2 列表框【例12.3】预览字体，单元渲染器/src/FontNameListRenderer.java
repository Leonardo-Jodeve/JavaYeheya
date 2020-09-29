//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年10月2日
//§12.3.2 列表框
//  2. 列表框单元渲染器
//【例12.3】 预览字体，使用列表框单元渲染器。
//【例6.5思考题】文本编辑器。字体名组合框用渲染器

import java.awt.*;
import javax.swing.*;

//字体名列表框单元渲染器类，继承JCheckBox复选框类，实现列表框单元渲染器接口；
//设列表框存储字体名字符串，将列表框单元渲染成复选框，分别以各自字体名显示。
public class FontNameListRenderer extends JCheckBox implements ListCellRenderer<Object>
{
    //设置复选框属性。参数：jlist是列表框，value是jlist当前数据项，isSelected是否被选中
    public Component getListCellRendererComponent(JList<?> jlist, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(value.toString());                    //设置复选框标题是列表框当前数据项
        this.setFont(new Font(value.toString(), Font.BOLD, 16));     //设置字体，value是字体名
        this.setSelected(isSelected);                      //设置选中状态是列表框当前项的选中状态
        this.setForeground(isSelected ? Color.red : Color.black);    //选中项标题红色显示
        this.setBackground(isSelected ? Color.lightGray : Color.white);//选中项背景浅灰色
        return this;                                       //返回渲染过的复选框组件
    }
}
//@author：Yeheya，2016年10月2日，2018年2月1日，2018年8月8日