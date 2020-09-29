//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��18��
//��6.3.7   �˵����
//����6.5��  �ı��༭����
//��1��ʹ��JTextArea��ֻ��һ�����塣
//��2�� ��Ͽ�JComboBox<String>��������JComboBox<Integer>�ֺţ�ʹ����12.3���������ֺ��б��Ԫ��Ⱦ��
//��3�� ��Ͽ򣬶����¼���û������Ͽ�ģ�ͣ�û����ӦItem�¼�
//��4�� �ֺ���Ͽ����򣬱༭ʱ��Ӳ��ظ���������ַ�����
//��5�� ���ӳ����ͻָ�������UndoManager�ࡣ
//����12.2��  ������6.5���ܣ������ɫ�������ַ�����
//���ñ���·��������12.3��  Ԥ�����壬ʹ���б��Ԫ��Ⱦ����

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.UndoManager;             //Undo/Redo������
import java.lang.reflect.Field;                  //���������

//�ı��༭������࣬�̳п���࣬��Ӧ�����¼�������¼�
public class EditorJFrame extends JFrame implements ActionListener, MouseListener
{
    private JComboBox<String> combox_name;                 //��������Ͽ�����������ΪString
    protected JComboBox<Integer> combox_size;              //�ֺ���Ͽ�����������ΪInteger
    private JCheckBox[] checkbox;                          //���θ�ѡ������  
    private JRadioButton[] radios;                        //��ɫ��ѡ��ť����
    protected Color[] colors={Color.red, Color.green, Color.blue};//, Color.magenta, Color.cyan};   //��ɫ������������
    private String[] colorname={"red", "green", "blue"};//, "magenta", "cyan"};//��ɫ�������ַ�������
    protected JTextArea text;                              //�ı��� 
    protected JPopupMenu popupmenu;                        //��ݲ˵�
    protected JMenu[] menus;                               //�˵�����
    private JCheckBoxMenuItem[] cbmenuitem;                //��ѡ�˵�������
    protected JToolBar toolbar;                            //������
    private UndoManager undoManager;                       //�����ͻָ���������5��̲�ûд
    
    public EditorJFrame()
    {
        super("�ı��༭��");                                //Ĭ��BorderLayout����
        this.setSize(840,600);                             //���ô��ڴ�С
        this.setLocationRelativeTo(null);                  //���ھ���
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //�������ڹرհ�ť���������н���

        //������ӹ����������а������塢�ֺ���Ͽ����θ�ѡ����ɫ��ѡ��ť���򿪡����水ť
        this.toolbar = new JToolBar();                     //������������Ĭ��ˮƽ����
        this.getContentPane().add(this.toolbar,"North");   //������ݴ��񱱱���ӹ�����
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsName=ge.getAvailableFontFamilyNames();//�������ϵͳ�������ַ���
        this.combox_name = new JComboBox<String>(fontsName);//��Ͽ���ʾϵͳ����
        this.combox_name.addActionListener(this);          //��������Ͽ���������¼�
        this.toolbar.add(this.combox_name);                //�����������������Ͽ�
        this.combox_name.setRenderer(new FontNameListRenderer());
                                       //��Ͽ����õ�Ԫ��Ⱦ������˼����6-5���� ������12.3
 
        Integer[] sizes={20,30,40,50,60,70};               //�ֺ�
        this.combox_size = new JComboBox<Integer>(sizes);  //�ֺ���Ͽ�
        this.combox_size.setEditable(true);                //������Ͽ�ɱ༭
        this.combox_size.addActionListener(this);          //�ֺ���Ͽ���������¼�
        this.toolbar.add(this.combox_size);
        this.combox_size.setRenderer(new FontSizeListRenderer());//�ֺ��б��Ԫ��Ⱦ������˼����6-5���� ������12.3

        String[] stylestr={"����", "б��"};                 //����
        this.checkbox = new JCheckBox[stylestr.length];    //���θ�ѡ������
        for(int i=0; i<stylestr.length; i++)
        {
            this.toolbar.add(this.checkbox[i] = new JCheckBox(stylestr[i]));
            this.checkbox[i].addActionListener(this);      //��ѡ����������¼�
        }
//        this.colorname = toString(this.colors);            //��12.2 ���䡾��12.2��
        ButtonGroup bgroup_color = new ButtonGroup();      //��ť��
        this.radios = new JRadioButton[this.colorname.length];  //��ɫ��ѡ��ť����
        for(int i=0; i<this.radios.length; i++)
        {
            this.radios[i]=new JRadioButton(this.colorname[i]); //��ɫ��ѡ��ť
            this.radios[i].setForeground(this.colors[i]); //���õ�ѡ��ť���ı���ɫ
            this.radios[i].addActionListener(this);
            bgroup_color.add(this.radios[i]);             //��ť����ӵ�ѡ��ť
            this.toolbar.add(this.radios[i]);             //��������ӵ�ѡ��ť
        }        
        this.radios[0].setSelected(true);                 //���õ�ѡ��ťΪѡ��״̬��û�д��������¼�
 
/*        JButton bopen = new JButton("��", new ImageIcon("open.gif"));//��ť����ͼ��
        bopen.addActionListener(this);
        add(bopen);                           //��������Ӱ�ť
        JButton bsave = new JButton("����", new ImageIcon("save.gif"));
        bsave.addActionListener(this);
        this.toolbar.add(bsave); */

        //��6��
        String[][] buttonstr={{"��","����"},{"open.gif","save.gif"}};
        JButton[] buttons = new JButton[buttonstr[0].length];
        for(int i=0; i<buttonstr[0].length; i++)           //��Ӱ�ť������ͼ�꣩
        {
            buttons[i] = new JButton(buttonstr[0][i], new ImageIcon(buttonstr[1][i]));
            buttons[i].addActionListener(this);
            this.toolbar.add(buttons[i]);
        }

        //�����ڿ�����ݴ����в�����ı���
        this.text = new JTextArea("Welcome ��ӭ");
        this.text.addMouseListener(this);                  //�ı�����������¼�
        this.getContentPane().add(new JScrollPane(this.text));  //������ݴ����в���Ӱ����ı����Ĺ�������
        this.text.setForeground(colors[0]);                //�����ı�����ɫ
        this.addMenu();                                    //��Ӵ��ڲ˵��Ϳ�ݲ˵�
        this.setVisible(true);
        
        //���µ�5��̲�ûд
        this.combox_name.setSelectedItem("�����п�");        //������Ͽ�ȡֵ�����������¼���ִ��actionPerformed()����
        this.combox_size.setSelectedIndex(this.combox_size.getItemCount()-1);  //������Ͽ�ȡ���һ��ֵ�����������¼�

        this.undoManager = new UndoManager();               //�����ͻָ�����
        this.text.getDocument().addUndoableEditListener(this.undoManager);
    }
       
    private void addMenu()                                 //��Ӵ��ڲ˵��Ϳ�ݲ˵�
    {
        //���´������ڲ˵������˵�����ӵ����
        String[] menustr={"�ļ�","�༭","����","��ʽ","����","����","����"};
        String[][] menuitemstr={{"�½�","��","����","���Ϊ","|","�˳�"},
                                {"����","�ָ�","|","����","����","ճ��","|","����","�滻"},
                                {"����","�ı�"},
                                {"����"},
                                {"����ͳ��","�Զ�����","ƴд���"},{},{}};
        JMenuBar menubar = new JMenuBar();                 //�˵���
        this.setJMenuBar(menubar);                         //�������Ӳ˵���
        this.menus = new JMenu[menustr.length];            //�˵�����
        JMenuItem[][] menuitems = new JMenuItem[menuitemstr.length][]; //�˵����ά����
        for(int i=0; i<menuitemstr.length; i++)            //��Ӳ˵��Ͳ˵���
        {
            this.menus[i] = new JMenu(menustr[i]);         //�˵�
            menubar.add(this.menus[i]);                    //�˵����м���˵�
            menuitems[i] = new JMenuItem[menuitemstr[i].length];
            for(int j=0; j<menuitemstr[i].length; j++)
            {
                if(menuitemstr[i][j].equals("|"))
                    this.menus[i].addSeparator();          //�ӷָ���
                else 
                {
                    menuitems[i][j] = new JMenuItem(menuitemstr[i][j]); //�����˵���
                    this.menus[i].add(menuitems[i][j]);    //�˵�����뵽�˵�
                    menuitems[i][j].addActionListener(this);//�˵�����������¼�
                }
            }
        }
        menuitems[0][1].setIcon(new ImageIcon("open.gif"));//���ò˵����ͼ��
        menuitems[0][2].setIcon(new ImageIcon("save.gif"));

        JMenu menu_style = new JMenu("����");
        menus[3].add(menu_style);                          //�˵����뵽�˵��г�Ϊ�����˵�
        String[] stylestr={"����", "б��"};
        this.cbmenuitem = new JCheckBoxMenuItem[stylestr.length];
        for(int i=0; i<stylestr.length; i++)
        {
            this.cbmenuitem[i] = new JCheckBoxMenuItem(stylestr[i]);  //���θ�ѡ�˵���
            menu_style.add(this.cbmenuitem[i]);
            this.cbmenuitem[i].addActionListener(this);    //�˵�����������¼�
        }

        JMenu menu_color = new JMenu("��ɫ");
        menus[3].add(menu_color);
        ButtonGroup buttongroup = new ButtonGroup();       //��ť��
        for(int i=0; i<this.colorname.length; i++)         //��ӵ�ѡ�˵���
        {
            JRadioButtonMenuItem rbmi=new JRadioButtonMenuItem(this.colorname[i]);
            buttongroup.add(rbmi);                         //��ť����ӵ�ѡ�˵���
            menu_color.add(rbmi);                          //�˵���ӵ�ѡ�˵���
            rbmi.setForeground(this.colors[i]);
            rbmi.addActionListener(this);
        }
        
        //���´�����ݲ˵����������ı������
        this.popupmenu = new JPopupMenu();                 //��ݲ˵�����
        String[] menuitems_cut={"����","����","ճ��"};
        JMenuItem[] popmenuitem = new JMenuItem[menuitems_cut.length];
        for(int i=0; i<popmenuitem.length; i++)
        {
            popmenuitem[i]=new JMenuItem(menuitems_cut[i]);
            this.popupmenu.add(popmenuitem[i]);            //��ݲ˵���Ӳ˵���
            popmenuitem[i].addActionListener(this);
        }
        popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));//���ÿ�ݼ�Ctrl+X
        popmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));//���ÿ�ݼ�Ctrl+C
        popmenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));//���ÿ�ݼ�Ctrl+V
        this.text.add(this.popupmenu);                     //�ı�����ӿ�ݲ˵�
    }

    public void actionPerformed(ActionEvent event)         //�����¼�������
    {
        if(event.getSource() instanceof JRadioButton)      //������ɫ��ѡ��ť
//        		|| event.getSource() instanceof JRadioButtonMenuItem)//������ѡ�˵����5��̲�ûд
            //�¾������ı������ı���ɫͬѡ�а�ť
            this.text.setForeground(((JComponent)event.getSource()).getForeground());
            //���µ�4�棬����ͬ��һ��
//            for (int i=0; i<this.radios.length; i++)      //Ѱ�ҵ�ǰѡ�е���ɫ��ѡ��ť
//                if (this.radios[i].isSelected())
//                {
//                    this.text.setForeground(this.colors[i]);//�����ı�����ɫ
//                    return;
//                }

        else if(event.getSource() instanceof JMenuItem)    //�����˵���
        {
            switch(event.getActionCommand())     //JDK 8��switch�������ʽ������String
            {
                case "�˳�":
                    if(JOptionPane.showConfirmDialog(this, "��ֹ��ǰ�������У�","ȷ��",JOptionPane.YES_NO_OPTION)==0)
                                                           //ȷ�϶Ի��򣬵������ǡ������񡱰�ť�ֱ𷵻�0��1
//                  if(JOptionPane.showConfirmDialog(this, "�رմ��ڣ����浱ǰ�ļ���")==0)
                        //ȷ�϶Ի���Ĭ�ϡ��ǡ������񡱡���ȡ������ť���ֱ𷵻�0��1��2
                        System.exit(0);                        //�������ǡ���ť��������������

                case "����": this.text.cut();   break;     //��ѡ���ı�������ϵͳ������
                case "����": this.text.copy();  break;     //��ѡ���ı�������ϵͳ������
                case "ճ��": this.text.paste(); break;     //����������ı�ճ���ڹ�굱ǰλ��
                
                //���µ�5��̲�ûд
                case "����": this.undoManager.undo(); break;
                case "�ָ�": this.undoManager.redo(); break;
            }
        }
        
        //���µ������������йص���Ͽ򡢸�ѡ��ʱ���޸��ı���������
        else if(event.getSource() instanceof JComboBox<?> || event.getSource() instanceof JCheckBox)
        //   || event.getSource() instanceof JMenuItem)           //�����˵���
        { 
            int size=0;
            String fontname = (String)this.combox_name.getSelectedItem();//���������
            Object obj = this.combox_size.getSelectedItem();//����ֺ���Ͽ�ѡ���������ֵ
            if(obj==null)                   //ִ��combox.removeAllItems()����������obj==null
                return;
            try
            {
                if (obj instanceof Integer)                //�ж�obj�Ƿ�����Integerʵ��
                    size=((Integer)obj).intValue();        //�������ֵ
                else if (obj instanceof String)            //�ֺ���Ͽ������ַ���
                    size = Integer.parseInt((String)obj);  //����ֺ�
                if (size<20 || size>200)                    //���ֺų�����Χ���׳��쳣
                    throw new Exception(size+" �ֺų���20��200��Χ��");
                
                java.awt.Font font = this.text.getFont();  //����ı����ĵ�ǰ�������
                int style = font.getStyle();               //�������
                switch(event.getActionCommand())           //��ѡ�˵���͸�ѡ��
                {
                    case "����": style^=1; break;          //���^������λ���㣬��2.1.4��
                    case "б��": style^=2;
                }
                this.text.setFont(new Font(fontname, style, size)); //�����ı�������
                
//                insert(this.combox_size, size);  //�������ֺŲ��뵽�ֺ���Ͽ򣬲������ظ���
                //˵������5��JDK8.25��2016��9��20�ա�
                //��Ͽ�����ʱ���Զ���"100"ת����Integer���������Integer.parseInt()������
                //ֻ������"200k"�Ȳ���ת���������Ĵ�ʱ��obj���Ͳ���String������Integer.parseInt()������Ϊ���׳��쳣��
                //��Ͽ������ѡ���cmd����"comboBoxChanged"��û��"comboBoxEdited"����ˣ��޷����������ѡ������״̬��
                //����ÿ�������ѡ�񶼽�ִ��insert()������
                ComboBoxOper.insert(this.combox_size, size);//�������ֺŲ��뵽�ֺ���Ͽ򣬲������ظ���
            }
            catch(NumberFormatException ex)                //������ֵ��ʽ�쳣
            {
                JOptionPane.showMessageDialog(this, "\""+ex.getMessage()+"\" ����ת����������");
            }
            catch(Exception ex)                            //�����������͵��쳣
            {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
            finally{}
        }
    } 
    
    //�����Ƕ���Ͽ���������в�����ͨ�÷�����
    //����Ͽ������T��������Tʵ��Comparable<? super T>�ӿڣ�
    //��x���뵽��Ͽ��������У��������ظ��xΪnullʱ�׳��ն����쳣��
    //���ö��ַ������㷨�����ַ����������һ�ˡ�
    //combox���ø�ֵ���ڷ��������޸��������������ʵ�ʲ�����
    public <T extends Comparable<? super T>> void insert(JComboBox<T> combox, T x)
    {
        int begin=0, end=combox.getItemCount()-1, mid=end; //begin��end�����������ű߽�
        while(begin<=end)                                  //�߽���Ч
        {   mid = (begin+end)/2;                           //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
            if(x.compareTo(combox.getItemAt(mid))==0)      //�Ƚ϶����С�������
                return;                                    //����ҳɹ��������룻
            if(x.compareTo(combox.getItemAt(mid))<0)       //��x����С��
                end = mid-1;                               //����ҷ�Χ��С��ǰ��Σ�
            else
                begin = mid+1;                             //���򣬲��ҷ�Χ��С������
        }
        combox.insertItemAt(x, begin);           //���Ҳ��ɹ�����x��������Ͽ�ĵ�begin��
    }

    //���·���ʵ��MouseListener����¼��ӿ�
    public void mouseClicked(MouseEvent event)      //��굥���¼�
    {
        if(event.getButton()==3)                    //������������Ҽ�
            this.popupmenu.show(this.text, event.getX(), event.getY());//����굥������ʾ��ݲ˵�
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event){}
    public void mouseEntered(MouseEvent event) {} 
    public void mouseExited(MouseEvent event)  {}

    public static void main(String arg[])
    {
        new EditorJFrame();
    }
    
	//��12.2 ���䡾��12.2��������6.5���ܣ������ɫ�������ַ�����
    //colorsָ����ɫ�������飬���ظ���ɫ��Ӧ���ַ�������
    public String[] toString(Color[] colors)
    {
        String[] str = new String[colors.length];
        Field[] fields = Color.class.getFields();     //���Color�������г�Ա����
        for(int i=0; i<colors.length; i++)
            for(int j=0; j<fields.length; j++)        //��fields������˳�����colors[i]��ɫ����
                try
                {
                    if (colors[i].equals(fields[j].get(colors[i])))//�Ƚ�������ɫ����ֵ
                    {
                        str[i]=fields[j].getName();   //���fields[j]��Ա�������ַ���
                        break;
                    }
                }
                catch(IllegalAccessException ex) {}   //��Ч��ȡ�쳣
        return str;
    }
}
//@author��Yeheya��2016-9-15 ����ڣ�2017��2��10�գ�2017��7��19�գ�2018��2��3��