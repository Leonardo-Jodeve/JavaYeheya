//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��2��
//��12.1 ���Ͽ��
//��12.2 ����
//��12.3.5 ��
//����12.6�����÷�������Person������Ϣ���������ṹ��ʾ�й�ʡ�ݺͳ��С�
//MyEclipse���ñ���·��������Ŀ����3.2 MyDate�࣬��3.3 Person�࣬��3.5 Student�࣬
//    ��6.4 Person������Ϣ������8.3�������ļ���12.1 ���Ͽ�ܣ���12.2����
//��1��ͼ���û�����

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.lang.reflect.Field;                  //���������

//Person������Ϣ�������ࣨ�����ṹ��ʾ�й�ʡ�ݺͳ��У�����Ӧ��ѡ���¼��������¼��������¼�
public class CityTreePersonJFrame extends JFrame 
    implements TreeSelectionListener, ActionListener, WindowListener
{
    private String objectFilename;               //�����ļ���
    private MutableJTree tree;                   //�������֧�ֲ����ɾ������
    protected DefaultTableModel tablemodel;      //���ģ��
    protected JTable jtable;                     //������
    protected LinkedList<Person> list;           //ѭ��˫�����б�Ԫ��ΪPersonʵ��
    protected PersonJPanel person;               //Person������Ϣ��壬����6.4
    public JComboBox<String>[] comboxs;          //ѡ����ҡ�����ؼ�����Ͽ�
    protected JPanel cmdpanel;                   //�������
    protected Field[] fields;                    //Personʵ����Ա��������
    
    //���췽����treeFilename����ָ�������������ļ�����objectFilenameָ�������ļ�����
    //titlesָ�������⣬personָ��������Ϣ���
    public CityTreePersonJFrame(String treeFilename, String objectFilename, String[] titles, 
                                PersonJPanel person)
    {
        super("Person������Ϣ����  ���벢��ʡ�з������");
        this.setBounds(100, 100, 800, 430);
//        this.setSize(getToolkit().getScreenSize());        //�����Ļ�ֱ��ʣ��������
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(this);                      //ע�ᴰ���¼�������
        this.objectFilename = objectFilename;
        this.list = new LinkedList<Person>();              //�����յ�ѭ��˫����
        CollectionFile.readFrom(this.objectFilename, this.list); //��ȡָ�������ļ����б�����12.1���Ͽ�ܡ������ϵ�ͨ�÷�������Ŀ
        
        this.tree = new MutableJTree(treeFilename);        //�����ɱ༭����������ָ���ļ���
        this.tree.addTreeSelectionListener(this);          //������ѡ���¼���
        //���´���ˮƽ�ָ����������������ұ���Ӵ�ֱ�ָ��
        JSplitPane split_hor=new JSplitPane(1,new JScrollPane(this.tree),null);
        split_hor.setDividerLocation(120);                 //���ô�ֱ�ָ�����λ��
        this.getContentPane().add(split_hor);
        
        JSplitPane split_ver=new JSplitPane(JSplitPane.VERTICAL_SPLIT); //��ֱ�ָ��
        split_ver.setDividerLocation(260);                 //����ˮƽ�ָ�����λ��
        split_hor.add(split_ver);                          //ˮƽ�ָ���ұ����һ����ֱ�ָ��

        this.tablemodel=new DefaultTableModel(titles,0);   //Ĭ�ϱ��ģ�ͣ�ָ���б��⣬0��
        this.jtable=new JTable(this.tablemodel);           //�����ձ��ָ�����ģ��  
        split_ver.add(new JScrollPane(jtable));            //��ֱ�ָ���ϱ���Ӱ������Ĺ�������
        
        JPanel panel = new JPanel(new GridLayout(2,1));    //������������
        split_ver.add(panel);                              //��ӵ���ֱ�ָ���±�
        this.person = person;                              //Person������Ϣ��壬����6.4
        panel.add(this.person);
        this.person.setLayout(new GridLayout(1,6));        //Person������Ϣ���1��6��
        this.person.combox_province.removeActionListener(this.person);//ȡ��ԭ�����¼�������
        this.person.combox_province.addActionListener(this);
            //����ע��Person������Ϣ�����ʡ����Ͽ�Ķ����¼�����������this�����¼�
//        PersonJFrame.setFont(this.person, PersonJFrame.font); //���������������������壬����6.4����5��ûд��������ʾ��
        
        this.tree.addChild(this.tree.root, this.person.combox_province);
            //�����������к��ӽ����ӵ�Person������Ϣ����е�ʡ����Ͽ�
            //��Ͽ�����׸�Ԫ��ʱ������ѡ��������ı䣬���������¼�����ӳ�����Ͽ�������
        this.fields = Reflection.getFields(person.get(),titles.length);//����Personʵ�������й��г�Ա����������12.2��Ŀ
        this.tree.addSelectionRow(0);                      //ѡ�����ĸ���㣬����TreeSelectionEvent�¼���ִ��valueChanged()����������ʱ��ִ��
        
        panel.add(this.cmdpanel = new JPanel());           //����������
        String[][] str={{"���","ɾ��ѡ�ж���","ͳ��"},{"���ҹؼ���","����ؼ���"}};
        for(int i=0; i<str[0].length; i++)                 //��Ӱ�ť
        {
            JButton button = new JButton(str[0][i]);
            button.addActionListener(this);
            cmdpanel.add(button);
        }
        this.comboxs = new JComboBox[str[1].length];       //<String>[2];����
        for(int i=0; i<str[1].length; i++)                 //��Ӳ��ҡ�����ؼ�����Ͽ�
        {
            cmdpanel.add(new JLabel(str[1][i]));
            cmdpanel.add(this.comboxs[i]=new JComboBox<String>(Reflection.toString(this.fields)));
                           //�����Ͽ���Ͽ���������Personʵ�������й��г�Ա����
            this.comboxs[i].addActionListener(this);       //��Ͽ�ע�ᶯ���¼�������
        }
//        PersonJFrame.setFont(this.cmdpanel, PersonJFrame.font); //���������������������壬����6.4����5��ûд��������ʾ��
        this.setVisible(true);
    }    
    
    //��ѡ���¼���������ѡ�н��ʱ����
    public void valueChanged(TreeSelectionEvent event)
    {
        if(event!=null)
            this.tree.expandPath(event.getPath());         //չ����ǰѡ�н��
        TreeNode node = (TreeNode)this.tree.getLastSelectedPathComponent();  //��ǰѡ�н��
        if(node!=null && node==this.tree.root)             //��ѡ�и����
            addTable(new ProvinceCityFilter("", ""));      //����б�ȫ�����ݵ����
//??            addTable(new FieldsFilter(null,"province","city"));      //����б�ȫ�����ݵ����
        else if(node!=null && node.getParent()==this.tree.root)//��ѡ��ʡ�ݽ��
        {
            this.person.combox_province.setSelectedItem(node.toString());//ʡ����Ͽ�ѡ�е�ǰʡ�ݽ��ֵ������ʡ����Ͽ�Ķ����¼��������ĳ�����Ͽ��������
            addTable(new ProvinceCityFilter(node.toString(), "")); //���ѡ��ʡ�����ݵ����
        }
        else if(node!=null && node.getParent()!=null && node.getParent().getParent()==this.tree.root)//ѡ�е��ǳ��н��
        {
            this.person.combox_province.setSelectedItem(node.getParent().toString());//����ʡ�ݽ�㣬���������¼�
            this.person.combox_city.setSelectedItem(node.toString());    //���ó��н��
            addTable(new ProvinceCityFilter(node.getParent().toString(), node.toString())); //���ѡ��ʡ�ݡ��������ݵ����
        }
    }
    
    //��list�б��в���ָ��ʡ�ݺͳ���Ԫ�أ�ί��filter������ָ����������������Ԫ����ӵ����ģ��
    //��ʡ�о�Ϊ""����ʾȫ�����ݣ�������Ϊ""����ʾ��ǰʡ���е�ȫ�����С�
    //SearchFilter<T>�ǲ��������������ӿڣ��Ժ���͡�
    public <T extends Person> void addTable(SearchFilter<T> filter)
    {
        for(int i=this.tablemodel.getRowCount()-1; i>=0; i--)   //��ձ��
            this.tablemodel.removeRow(i);              
        for(Iterator<Person> it=this.list.iterator(); it.hasNext(); ) //������
//        for (T p: this.list)                          //���У���Ԫѭ����p���list�б���ÿ��Ԫ��
        {
            T per = (T)it.next();
        	//������filter������ָ�����������������ҳɹ����������һ�У���������ָ������ֵ
            if(filter.accept(per))
               this.tablemodel.addRow(Reflection.toArray(per, this.fields));
        }
    }

    public void actionPerformed(ActionEvent event)         //�����¼�������
    {        
//        System.out.println(event.getSource().getClass().getName()+"��"+event.getActionCommand());
        
        //ʡ����Ͽ�����׸�����������������������¼����Σ�����ѡ��������ֱ�Ϊnull�͡�������
        if(event.getSource()==this.person.combox_province)//ʡ����Ͽ�ѡ��������
        {
            String province=(String)this.person.combox_province.getSelectedItem();//��õ�ǰʡ��
            TreeNode node = this.tree.search(province);    //�����в���ָ��ʡ�ݽ��
            if(node!=null)                                 //���ҳɹ�����ʡ�ݵ����к��ӽ����ӳ�����Ͽ�
                this.tree.addChild(node, this.person.combox_city);
        }
            
        else if(event.getActionCommand().equals("���"))    //������ť
        {
            Person per = this.person.get();
            this.tablemodel.addRow(Reflection.toArray(per, this.fields)); //���ģ�����һ�У�һ�������������ṩ
            this.list.add(per);                            //�б����һ������
        }

        else if(event.getActionCommand().equals("ɾ��ѡ�ж���"))
            removeSelectedAll(this.jtable, this.tablemodel, this.list);

//        System.out.println(event.getSource().getClass().getName()+"��"+event.getActionCommand());
        else if(event.getSource()==this.comboxs[0])        //������Ͽ�
        {
            String fieldname = (String)this.comboxs[0].getSelectedItem();
                                                   //��ò�����Ͽ�ǰѡ�����ַ��������Ըó�Ա������Ϊ��������
            addTable(new FieldFilter<Person>(this.person.get(), fieldname)); 
                                       //��ӱ�񣬲���ָ����this.person.get()��fieldname��Ա����ֵ���й��˲���
        }

        else if(event.getSource()==this.comboxs[1])       //������Ͽ�
        {
            String fieldname=(String)this.comboxs[1].getSelectedItem();//��ó�Ա��������Ϊ����ؼ���
            Collections.sort(this.list, new CompareField<Person>(fieldname));
                                             //�б����򣬱Ƚ�������ָ��Person����fieldname��Ա�����Ƚ϶����С
            if(this.tree.getSelectionRows()[0]==0)         //��ǰѡ�и����
                addTable(new ProvinceCityFilter("", ""));  //����б�ȫ�����ݵ����
            else
                this.tree.setSelectionRow(0);              //ѡ�����ĸ���㣬��������ѡ���¼���Ϊ������������
        }
    }
    
    //��jtable����ѡ�ж������ݣ���tablemodel���ģ�ͺ�list�б�����ȫ��ɾ��
    private void removeSelectedAll(JTable jtable, DefaultTableModel tablemodel, LinkedList<Person> list)
    {
        if(tablemodel.getRowCount()==0)
            JOptionPane.showMessageDialog(this, "���գ�����ɾ�������");
        else
        {
            int[] rows = jtable.getSelectedRows();         //���ѡ�ж��е��к�
            if(rows.length==0)
                JOptionPane.showMessageDialog(this, "��ѡ����������");
            else if(JOptionPane.showConfirmDialog(this, "ɾ��ѡ�ж��У�")==0)//ȷ�϶Ի��򣬵���Yes��ť����0
                for(int i=rows.length-1; i>=0; i--)
                {
                    Person per=get(this.tablemodel, rows[i]);//���ر��ģ��ָ���б�ʾ�Ķ���
                    list.remove(per);                      //�б�ɾ��������equals()����ʶ�����
                    tablemodel.removeRow(rows[i]);         //�����ɾ��һ�У�ָ���к�
                }
        }
    }
    
    //����tablemodel���ģ�͵�i�б�ʾ�Ķ���
    public Person get(TableModel tablemodel, int i)
    {                                  //������Ϊ���������������ڡ��Ա�ʡ�ݡ�����
        return new Person((String)tablemodel.getValueAt(i,0), (MyDate)tablemodel.getValueAt(i,1),
                          (String)tablemodel.getValueAt(i,2), (String)tablemodel.getValueAt(i,3), 
                          (String)tablemodel.getValueAt(i,4)); 
    }
    
    
    public void windowClosing(WindowEvent event)           //�رմ����¼�������
    {
        CollectionFile.writeTo(this.objectFilename, this.list);//���б�Ԫ��д��ָ�������ļ�������12.1���Ͽ�ܡ������ϵ�ͨ�÷�������Ŀ
    }
    public void windowOpened(WindowEvent event) {}
    public void windowActivated(WindowEvent event) {}
    public void windowDeactivated(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowDeiconified(WindowEvent event) {}
    
    public static void main(String[] args) 
    {
        String[] titles={"����","��������","�Ա�","ʡ��","����"};  //ָ������б���
        new CityTreePersonJFrame("cities.txt", "persons.obj", titles, new PersonJPanel());    //ָ��Ĭ���ļ���
    }
}
//@author��Yeheya��2018��2��2�գ�2018��8��10��