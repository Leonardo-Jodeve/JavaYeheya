//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月20日
//§6.3.8 表格组件
//【例6.6】 计算银行贷款，按月还本付息。
//MyEclipse设置编译路径包含项目：例3.2（MyDate）。
//没有处理异常和错误。
//【实验题6-25】 
//② 表格增加一列，计算等额本息还款金额。
//③ 表格增加一行，计算两种方式的还款总额。
//④ 年月微调文本行之间实现关联，当改变月份影响到年份时，相应地更改年份。

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

//计算贷款框架类，继承框架类，响应动作事件、数值改变事件
public class LoanJFrame extends JFrame implements ActionListener, ChangeListener
{
    private JTextField[] texts;                  //文本行数组，表示贷款金额、利率、年限
    private JSpinner spin_year, spin_month;      //微调文本行，表示起始年月
    private MyDate paydate;                      //起始还款日期
    private JButton button;                      //计算按钮
    private DefaultTableModel tablemodel;        //表格模型
    private Font font = null;//new Font("宋体",1,28);   //字号大，讲课用，第5版教材没写
    protected JPanel cmdpanel;                   //命令面板
    
    public LoanJFrame()
    {
        super("计算银行贷款，按月还本付息");
        this.setBounds(300,240,800,360);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下创建命令面板，提供贷款条件的文本行和计算按钮等组件
        this.cmdpanel = new JPanel();            //命令面板，默认流布局，居中
        this.getContentPane().add(this.cmdpanel, "North");
        String[] str={"贷款金额","元    贷款利率","%/月    贷款年限","年    还款起始","年","月"};   
        String[] str_text={"100000","0.5025","1"};   
        this.texts = new JTextField[str_text.length];
        int i=0;
        for(i=0; i<str_text.length; i++)         //添加标签和文本行，表示贷款条件
        {
        	this.cmdpanel.add(new JLabel(str[i]));
        	this.cmdpanel.add(this.texts[i]=new JTextField(str_text[i],6));
        }
        for(; i<str.length; i++)                 //命令面板上添加3个标签
        	this.cmdpanel.add(new JLabel(str[i]));

        //以下计算当前日期的下月，为年月微调文本行赋初值
        Calendar start = Calendar.getInstance(); //获得当前日期
        int year=start.get(Calendar.YEAR);       //当年
        int month=start.get(Calendar.MONTH)+1;   //当月，get(Calendar.MONTH)范围是0～11
        month = month%12+1;                      //下月
        if(month==1)                             //12月的下月是次年1月
            year++;
        int day = MyDate.daysOfMonth(year, month);    //获得指定年月的天数，MyDate类的静态方法
        this.paydate = new MyDate(year, month, day);  //约定还款日期初值是下月最后一天
        
        //下句中年份数值序列模式，初值是year，范围是year～year+2，步长是1
        this.spin_year=new JSpinner(new SpinnerNumberModel(year, year, year+2, 1));
        this.cmdpanel.add(this.spin_year,7);     //命令面板添加微调文本行，参数7指定插入位置
        //下句中月份数值序列模式，初值是month，范围是1～12（0～13），步长是1  //月份宽度够 
        this.spin_month=new JSpinner(new SpinnerNumberModel(month, 0, 13, 1));
        this.cmdpanel.add(this.spin_month,9);
        this.spin_month.addChangeListener(this); //月份微调文本行监听数值改变事件
//        this.spin_month.setValue(0);         //触发stateChanged(ChangeEvent)事件
        
        this.cmdpanel.add(this.button=new JButton("计算"));
        this.button.addActionListener(this);
        MyJPanel.setFont(this.cmdpanel, font);   //设置面板中所有组件的字体，见例6.4，第5版没写，讲课演示用
        
        //以下在框架内容窗格中部添加表格
        String[] titles={"还款日期","本金余额（元）","月还本金（元）","月还利息（元）","月还本息（元）","等额本息（元）"};
        this.tablemodel = new DefaultTableModel(titles,1); //默认表格模型，titles指定列标题，1行
        JTable jtable = new  JTable(this.tablemodel);      //创建表格，指定表格模型
        this.getContentPane().add(new JScrollPane(jtable));//框架内容窗格添加滚动窗格（包含表格）
//        jtable.setFont(font);//不行，不好看，行高不够？？
//??        jtable.setDefaultRenderer(jtable.getColumnClass(0), new FontTableCellRenderer(PersonJFrame.font));
        this.setVisible(true);

//        //以下渲染器，没效果？？，未成功，第5版没写，讲课演示用。2018年2月9日
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//             //默认表格单元渲染器，继承JLabel。所以，renderer是JLabel
//        renderer.setFont(PersonJFrame.font);   //设置字体
//        jtable.setDefaultRenderer(jtable.getColumnClass(2),renderer);   //添加表格单元渲染器
    }    
 
    //动作事件处理方法，单击计算按钮触发，使用表格显示还款明细，约定每月最后一天还款
    public void actionPerformed(ActionEvent event)
    {
        //以下获得各文本行表示的贷款数据，未处理数值格式异常和负数
        double leavings = Double.parseDouble(""+texts[0].getText()); //贷款本金，本金余额
        double rate = Double.parseDouble(""+texts[1].getText());     //贷款月利率
        int months = Integer.parseInt(texts[2].getText())*12;        //还款月数，由贷款年限计算出
        double pay = leavings/months;                                //月还本金
        this.tablemodel.setRowCount(months);               //设置表格模型行数
        
        double value=Math.pow(1+rate*0.01, months);        //等额本息，第5版思考题6-6
        double repayment = (leavings*rate*0.01*value)/(value-1);//等额本息，第5版思考题6-6

        //以下获得还款起始日期，预处理还款日期早于下月的错误
        int year = (Integer)this.spin_year.getValue();     //还款起始年份
        int month = (Integer)this.spin_month.getValue();   //还款起始月份
        //以上getValue()返回Object，实际引用Integer，强制转换，默认调用intValue()获得int。
        //从SpinnerNumberModel获得数值类型，若数据有错，不取，恢复原值，
        //因此不需要处理数值格式异常。          
        MyDate alterdate = new MyDate(year, month, MyDate.daysOfMonth(year, month));//还款日期
        if(alterdate.compareTo(this.paydate)<0)       //若修改的还款日期早于下月，则月份错误
        {
            JOptionPane.showMessageDialog(this, "设置还款日期为"+alterdate.toString()+
                    "，月份错误，早于下月。");
            return;
        }       
        for(int row=0; row<months; row++)             //表格每行显示每月还款明细
        {
            this.tablemodel.setValueAt(alterdate.toString(), row,0);                           //还款日期
            this.tablemodel.setValueAt(String.format("%9.2f",leavings), row, 1);               //本金余额
            this.tablemodel.setValueAt(String.format("%9.2f",pay), row, 2);                    //月还本金
            this.tablemodel.setValueAt(String.format("%9.2f",leavings*rate*0.01), row, 3);     //月还利息
            this.tablemodel.setValueAt(String.format("%9.2f",pay+leavings*rate*0.01), row, 4); //月还本息
            this.tablemodel.setValueAt(String.format("%9.2f",repayment), row, 5);              //等额本息，【思考题6-6】 ③
/*            try                                  //try语句，第5版没写
            {
                Thread.sleep(300);               //不行，sleep完了再显示。
            }
            catch(InterruptedException ex)
            {
                return;
            }//2018年5月14日，创建线程对象，实现方法见【实验7-5】螺旋方阵的动态演示。
*/            
            month = month%12+1;                       //下月
            if(month==1)
                year++;
            alterdate = new MyDate(year, month, MyDate.daysOfMonth(year, month));
            leavings -= pay;                          //本金余额减去月还本金
        }
        sum(this.tablemodel, 4,5);       //表格增加一行，第4、5列求和，第5版实验题6-25
    }
    //说明：没有调用MyDate类的nextMonth()方法。
    
    public static void main(String[] arg)
    {
        new LoanJFrame();
    }

    //第5版【实验题6-25】④ 表格增加一行，计算由columns指定各列之和。
    //表格各列类型是String，转换成double计算；columns是可变参数，使用方式同数组。
    //tablemodel引用赋值，在方法体中修改其单元格值，作用于实际参数。
    public void sum(DefaultTableModel tablemodel, int... columns)
    {
        int rows = tablemodel.getRowCount();     //表格行数
        double[] sum=new double[columns.length]; //存放计算结果的数组，初值默认0.0
        for(int row=0; row<rows; row++)          //表格每行
            for(int i=0; i<columns.length; i++)  //表格列求和
                sum[i] += Double.parseDouble((String)tablemodel.getValueAt(row, columns[i]));

        String[] rowstr = new String[tablemodel.getColumnCount()];
        rowstr[0]="合计";
        for(int col=0; col<columns.length; col++)//表格列求和
            rowstr[columns[col]] = String.format("%9.2f",sum[col]);
        tablemodel.addRow(rowstr);               //tablemodel表格模型增加一行，rowstr指定各列数值
    }
    
    //修改JSpinner值时触发事件的处理方法，实现ChangeListener接口。触发操作有：单击
    //JSpinner的向上/向下按钮，或在JSpinner中按Enter键，或按键盘上的向上/下箭头键
    public void stateChanged(ChangeEvent event)
    {
        int year = (Integer)this.spin_year.getValue();     //还款起始年份
        int month = (Integer)this.spin_month.getValue();   //还款起始月份
        System.out.println("stateChanged, year="+year+", month="+month);
        if(month==0 || month==13)               //要修改值的两种情况
        {
            switch(month)
            {
                case 13: month=1;  year++; break;
                case 0:  month=12; year--;
            }
            this.spin_year.setValue(year);
            this.spin_month.setValue(month);     //再次触发stateChanged(ChangeEvent)事件
        }
    }
    //【说明】在stateChanged(ChangeEvent)事件处理方法中，以下调用语句改变月份JSpinner值，将再次触发该事件。
    //      this.spin_month.setValue(month);
    //而修改后的值正确，且年份JSpinner没有响应该事件，因此，这一次调用输出2次，不会无限递归。
}
//@author：Yeheya，2016-9-27，2017年2月13日，2017年7月22日