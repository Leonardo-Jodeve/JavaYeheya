//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月18日
//§6.3.7   菜单组件
//【例6.5】  文本编辑器。
//（1）使用JTextArea，只有一种字体。
//（2） 组合框，JComboBox<String>字体名，JComboBox<Integer>字号；使用例12.3字体名、字号列表框单元渲染器
//（3） 组合框，动作事件，没有用组合框模型，没有响应Item事件
//（4） 字号组合框排序，编辑时添加不重复数据项，二分法查找
//（5） 增加撤销和恢复操作，UndoManager类。
//【例12.2】  增加例6.5功能，获得颜色常量的字符串。
//设置编译路径：【例12.3】  预览字体，使用列表框单元渲染器。

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.UndoManager;             //Undo/Redo管理器
import java.lang.reflect.Field;                  //反射包的类

//文本编辑器框架类，继承框架类，响应动作事件、鼠标事件
public class EditorJFrame extends JFrame implements ActionListener, MouseListener
{
    private JComboBox<String> combox_name;                 //字体名组合框，数据项类型为String
    protected JComboBox<Integer> combox_size;              //字号组合框，数据项类型为Integer
    private JCheckBox[] checkbox;                          //字形复选框数组  
    private JRadioButton[] radios;                        //颜色单选按钮数组
    protected Color[] colors={Color.red, Color.green, Color.blue};//, Color.magenta, Color.cyan};   //颜色常量对象数组
    private String[] colorname={"red", "green", "blue"};//, "magenta", "cyan"};//颜色常量名字符串数组
    protected JTextArea text;                              //文本区 
    protected JPopupMenu popupmenu;                        //快捷菜单
    protected JMenu[] menus;                               //菜单数组
    private JCheckBoxMenuItem[] cbmenuitem;                //复选菜单项数组
    protected JToolBar toolbar;                            //工具栏
    private UndoManager undoManager;                       //撤销和恢复操作，第5版教材没写
    
    public EditorJFrame()
    {
        super("文本编辑器");                                //默认BorderLayout布局
        this.setSize(840,600);                             //设置窗口大小
        this.setLocationRelativeTo(null);                  //窗口居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //单击窗口关闭按钮，程序运行结束

        //以下添加工具栏，其中包含字体、字号组合框，字形复选框，颜色单选按钮，打开、保存按钮
        this.toolbar = new JToolBar();                     //创建工具栏，默认水平方向
        this.getContentPane().add(this.toolbar,"North");   //框架内容窗格北边添加工具栏
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsName=ge.getAvailableFontFamilyNames();//获得所有系统字体名字符串
        this.combox_name = new JComboBox<String>(fontsName);//组合框显示系统字体
        this.combox_name.addActionListener(this);          //字体名组合框监听动作事件
        this.toolbar.add(this.combox_name);                //工具栏添加字体名组合框
        this.combox_name.setRenderer(new FontNameListRenderer());
                                       //组合框设置单元渲染器，【思考题6-5】② ，见例12.3
 
        Integer[] sizes={20,30,40,50,60,70};               //字号
        this.combox_size = new JComboBox<Integer>(sizes);  //字号组合框
        this.combox_size.setEditable(true);                //设置组合框可编辑
        this.combox_size.addActionListener(this);          //字号组合框监听动作事件
        this.toolbar.add(this.combox_size);
        this.combox_size.setRenderer(new FontSizeListRenderer());//字号列表框单元渲染器，【思考题6-5】② ，见例12.3

        String[] stylestr={"粗体", "斜体"};                 //字形
        this.checkbox = new JCheckBox[stylestr.length];    //字形复选框数组
        for(int i=0; i<stylestr.length; i++)
        {
            this.toolbar.add(this.checkbox[i] = new JCheckBox(stylestr[i]));
            this.checkbox[i].addActionListener(this);      //复选框监听动作事件
        }
//        this.colorname = toString(this.colors);            //§12.2 反射【例12.2】
        ButtonGroup bgroup_color = new ButtonGroup();      //按钮组
        this.radios = new JRadioButton[this.colorname.length];  //颜色单选按钮数组
        for(int i=0; i<this.radios.length; i++)
        {
            this.radios[i]=new JRadioButton(this.colorname[i]); //颜色单选按钮
            this.radios[i].setForeground(this.colors[i]); //设置单选按钮的文本颜色
            this.radios[i].addActionListener(this);
            bgroup_color.add(this.radios[i]);             //按钮组添加单选按钮
            this.toolbar.add(this.radios[i]);             //工具栏添加单选按钮
        }        
        this.radios[0].setSelected(true);                 //设置单选按钮为选中状态，没有触发动作事件
 
/*        JButton bopen = new JButton("打开", new ImageIcon("open.gif"));//按钮包含图标
        bopen.addActionListener(this);
        add(bopen);                           //工具栏添加按钮
        JButton bsave = new JButton("保存", new ImageIcon("save.gif"));
        bsave.addActionListener(this);
        this.toolbar.add(bsave); */

        //第6版
        String[][] buttonstr={{"打开","保存"},{"open.gif","save.gif"}};
        JButton[] buttons = new JButton[buttonstr[0].length];
        for(int i=0; i<buttonstr[0].length; i++)           //添加按钮（包含图标）
        {
            buttons[i] = new JButton(buttonstr[0][i], new ImageIcon(buttonstr[1][i]));
            buttons[i].addActionListener(this);
            this.toolbar.add(buttons[i]);
        }

        //以下在框架内容窗格中部添加文本区
        this.text = new JTextArea("Welcome 欢迎");
        this.text.addMouseListener(this);                  //文本区监听鼠标事件
        this.getContentPane().add(new JScrollPane(this.text));  //框架内容窗格中部添加包含文本区的滚动窗格
        this.text.setForeground(colors[0]);                //设置文本区颜色
        this.addMenu();                                    //添加窗口菜单和快捷菜单
        this.setVisible(true);
        
        //以下第5版教材没写
        this.combox_name.setSelectedItem("华文行楷");        //设置组合框取值，触发动作事件，执行actionPerformed()方法
        this.combox_size.setSelectedIndex(this.combox_size.getItemCount()-1);  //设置组合框取最后一项值，触发动作事件

        this.undoManager = new UndoManager();               //撤销和恢复操作
        this.text.getDocument().addUndoableEditListener(this.undoManager);
    }
       
    private void addMenu()                                 //添加窗口菜单和快捷菜单
    {
        //以下创建窗口菜单，将菜单栏添加到框架
        String[] menustr={"文件","编辑","插入","格式","工具","窗口","帮助"};
        String[][] menuitemstr={{"新建","打开","保存","另存为","|","退出"},
                                {"撤销","恢复","|","剪切","复制","粘贴","|","查找","替换"},
                                {"日期","文本"},
                                {"字体"},
                                {"字数统计","自动更正","拼写检查"},{},{}};
        JMenuBar menubar = new JMenuBar();                 //菜单栏
        this.setJMenuBar(menubar);                         //框架上添加菜单栏
        this.menus = new JMenu[menustr.length];            //菜单数组
        JMenuItem[][] menuitems = new JMenuItem[menuitemstr.length][]; //菜单项二维数组
        for(int i=0; i<menuitemstr.length; i++)            //添加菜单和菜单项
        {
            this.menus[i] = new JMenu(menustr[i]);         //菜单
            menubar.add(this.menus[i]);                    //菜单栏中加入菜单
            menuitems[i] = new JMenuItem[menuitemstr[i].length];
            for(int j=0; j<menuitemstr[i].length; j++)
            {
                if(menuitemstr[i][j].equals("|"))
                    this.menus[i].addSeparator();          //加分隔线
                else 
                {
                    menuitems[i][j] = new JMenuItem(menuitemstr[i][j]); //创建菜单项
                    this.menus[i].add(menuitems[i][j]);    //菜单项加入到菜单
                    menuitems[i][j].addActionListener(this);//菜单项监听动作事件
                }
            }
        }
        menuitems[0][1].setIcon(new ImageIcon("open.gif"));//设置菜单项的图标
        menuitems[0][2].setIcon(new ImageIcon("save.gif"));

        JMenu menu_style = new JMenu("字形");
        menus[3].add(menu_style);                          //菜单加入到菜单中成为二级菜单
        String[] stylestr={"粗体", "斜体"};
        this.cbmenuitem = new JCheckBoxMenuItem[stylestr.length];
        for(int i=0; i<stylestr.length; i++)
        {
            this.cbmenuitem[i] = new JCheckBoxMenuItem(stylestr[i]);  //字形复选菜单项
            menu_style.add(this.cbmenuitem[i]);
            this.cbmenuitem[i].addActionListener(this);    //菜单项监听动作事件
        }

        JMenu menu_color = new JMenu("颜色");
        menus[3].add(menu_color);
        ButtonGroup buttongroup = new ButtonGroup();       //按钮组
        for(int i=0; i<this.colorname.length; i++)         //添加单选菜单项
        {
            JRadioButtonMenuItem rbmi=new JRadioButtonMenuItem(this.colorname[i]);
            buttongroup.add(rbmi);                         //按钮组添加单选菜单项
            menu_color.add(rbmi);                          //菜单添加单选菜单项
            rbmi.setForeground(this.colors[i]);
            rbmi.addActionListener(this);
        }
        
        //以下创建快捷菜单，作用于文本区组件
        this.popupmenu = new JPopupMenu();                 //快捷菜单对象
        String[] menuitems_cut={"剪切","复制","粘贴"};
        JMenuItem[] popmenuitem = new JMenuItem[menuitems_cut.length];
        for(int i=0; i<popmenuitem.length; i++)
        {
            popmenuitem[i]=new JMenuItem(menuitems_cut[i]);
            this.popupmenu.add(popmenuitem[i]);            //快捷菜单添加菜单项
            popmenuitem[i].addActionListener(this);
        }
        popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));//设置快捷键Ctrl+X
        popmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));//设置快捷键Ctrl+C
        popmenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));//设置快捷键Ctrl+V
        this.text.add(this.popupmenu);                     //文本区添加快捷菜单
    }

    public void actionPerformed(ActionEvent event)         //动作事件处理方法
    {
        if(event.getSource() instanceof JRadioButton)      //单击颜色单选按钮
//        		|| event.getSource() instanceof JRadioButtonMenuItem)//单击单选菜单项，第5版教材没写
            //下句设置文本区的文本颜色同选中按钮
            this.text.setForeground(((JComponent)event.getSource()).getForeground());
            //以下第4版，功能同上一句
//            for (int i=0; i<this.radios.length; i++)      //寻找当前选中的颜色单选按钮
//                if (this.radios[i].isSelected())
//                {
//                    this.text.setForeground(this.colors[i]);//设置文本区颜色
//                    return;
//                }

        else if(event.getSource() instanceof JMenuItem)    //单击菜单项
        {
            switch(event.getActionCommand())     //JDK 8，switch条件表达式可以是String
            {
                case "退出":
                    if(JOptionPane.showConfirmDialog(this, "终止当前程序运行？","确认",JOptionPane.YES_NO_OPTION)==0)
                                                           //确认对话框，单击“是”、“否”按钮分别返回0、1
//                  if(JOptionPane.showConfirmDialog(this, "关闭窗口，保存当前文件？")==0)
                        //确认对话框，默认“是”、“否”、“取消”按钮，分别返回0、1、2
                        System.exit(0);                        //单击“是”按钮，结束程序运行

                case "剪切": this.text.cut();   break;     //将选中文本剪切送系统剪贴板
                case "复制": this.text.copy();  break;     //将选中文本复制送系统剪贴板
                case "粘贴": this.text.paste(); break;     //将剪贴板的文本粘贴在光标当前位置
                
                //以下第5版教材没写
                case "撤销": this.undoManager.undo(); break;
                case "恢复": this.undoManager.redo(); break;
            }
        }
        
        //以下当单击与字体有关的组合框、复选框时，修改文本区的字体
        else if(event.getSource() instanceof JComboBox<?> || event.getSource() instanceof JCheckBox)
        //   || event.getSource() instanceof JMenuItem)           //单击菜单项
        { 
            int size=0;
            String fontname = (String)this.combox_name.getSelectedItem();//获得字体名
            Object obj = this.combox_size.getSelectedItem();//获得字号组合框选中项，或输入值
            if(obj==null)                   //执行combox.removeAllItems()方法将导致obj==null
                return;
            try
            {
                if (obj instanceof Integer)                //判断obj是否引用Integer实例
                    size=((Integer)obj).intValue();        //获得整数值
                else if (obj instanceof String)            //字号组合框输入字符串
                    size = Integer.parseInt((String)obj);  //获得字号
                if (size<20 || size>200)                    //若字号超出范围，抛出异常
                    throw new Exception(size+" 字号超出20～200范围。");
                
                java.awt.Font font = this.text.getFont();  //获得文本区的当前字体对象
                int style = font.getStyle();               //获得字形
                switch(event.getActionCommand())           //复选菜单项和复选框
                {
                    case "粗体": style^=1; break;          //异或^是整数位运算，见2.1.4节
                    case "斜体": style^=2;
                }
                this.text.setFont(new Font(fontname, style, size)); //设置文本区字体
                
//                insert(this.combox_size, size);  //将输入字号插入到字号组合框，不插入重复项
                //说明：第5版JDK8.25，2016年9月20日。
                //组合框输入时，自动将"100"转换成Integer，无须调用Integer.parseInt()方法；
                //只有输入"200k"等不能转换成整数的串时，obj类型才是String，调用Integer.parseInt()方法是为了抛出异常；
                //组合框输入和选择的cmd都是"comboBoxChanged"，没有"comboBoxEdited"，因此，无法区分输入和选择两种状态，
                //导致每次输入和选择都将执行insert()方法。
                ComboBoxOper.insert(this.combox_size, size);//将输入字号插入到字号组合框，不插入重复项
            }
            catch(NumberFormatException ex)                //捕获数值格式异常
            {
                JOptionPane.showMessageDialog(this, "\""+ex.getMessage()+"\" 不能转换成整数。");
            }
            catch(Exception ex)                            //捕获所有类型的异常
            {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
            finally{}
        }
    } 
    
    //以下是对组合框数据项进行操作的通用方法。
    //设组合框数据项按T升序排序，T实现Comparable<? super T>接口，
    //将x插入到组合框数据项中，不插入重复项；x为null时抛出空对象异常。
    //采用二分法查找算法，二分法插入排序的一趟。
    //combox引用赋值，在方法体中修改其数据项，作用于实际参数。
    public <T extends Comparable<? super T>> void insert(JComboBox<T> combox, T x)
    {
        int begin=0, end=combox.getItemCount()-1, mid=end; //begin、end获得数据项序号边界
        while(begin<=end)                                  //边界有效
        {   mid = (begin+end)/2;                           //中间位置，当前比较元素位置
            if(x.compareTo(combox.getItemAt(mid))==0)      //比较对象大小，若相等
                return;                                    //则查找成功，不插入；
            if(x.compareTo(combox.getItemAt(mid))<0)       //若x对象小，
                end = mid-1;                               //则查找范围缩小到前半段，
            else
                begin = mid+1;                             //否则，查找范围缩小到后半段
        }
        combox.insertItemAt(x, begin);           //查找不成功，将x插入在组合框的第begin项
    }

    //以下方法实现MouseListener鼠标事件接口
    public void mouseClicked(MouseEvent event)      //鼠标单击事件
    {
        if(event.getButton()==3)                    //单击的是鼠标右键
            this.popupmenu.show(this.text, event.getX(), event.getY());//在鼠标单击处显示快捷菜单
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event){}
    public void mouseEntered(MouseEvent event) {} 
    public void mouseExited(MouseEvent event)  {}

    public static void main(String arg[])
    {
        new EditorJFrame();
    }
    
	//§12.2 反射【例12.2】增加例6.5功能，获得颜色常量的字符串。
    //colors指定颜色常量数组，返回各颜色对应的字符串数组
    public String[] toString(Color[] colors)
    {
        String[] str = new String[colors.length];
        Field[] fields = Color.class.getFields();     //获得Color类中所有成员变量
        for(int i=0; i<colors.length; i++)
            for(int j=0; j<fields.length; j++)        //在fields数组中顺序查找colors[i]颜色常量
                try
                {
                    if (colors[i].equals(fields[j].get(colors[i])))//比较两个颜色常量值
                    {
                        str[i]=fields[j].getName();   //获得fields[j]成员变量名字符串
                        break;
                    }
                }
                catch(IllegalAccessException ex) {}   //无效存取异常
        return str;
    }
}
//@author：Yeheya，2016-9-15 中秋节，2017年2月10日，2017年7月19日，2018年2月3日