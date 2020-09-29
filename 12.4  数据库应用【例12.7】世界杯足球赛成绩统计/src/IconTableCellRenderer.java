//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月14日
//§12.4 数据库应用
//【例12.7】 世界杯足球赛成绩统计。
//（6） 表格单元渲染器

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

//图标表格单元渲染器类，继承标签组件类，实现表格单元渲染器接口，
//将表格单元格渲染成带图标的标签组件
public class IconTableCellRenderer extends JLabel implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable jtable, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
    {
        this.setText(value.toString());          //显示球队名
        this.setIcon(new ImageIcon("国旗/"+value.toString()+".gif"));  //设置图标，文件名同队名
        return this;
    }
}
//@author：Yeheya，2018年3月14日