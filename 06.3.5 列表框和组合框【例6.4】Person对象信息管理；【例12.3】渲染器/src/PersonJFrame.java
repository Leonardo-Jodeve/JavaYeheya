//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��9��
//��6.3.4 �б�����Ͽ�
//����6.4��  Person������Ϣ����
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate������3.3��Person����
//��2��Person������Ϣ������
//��12�£���12.3.2 �б��   1.�б�����ѡ��  
//���γ����12���б��ɾ��ѡ�ж��ʹ��ArrayList<T>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;                                        //��12�¼��Ͽ��

//Person������Ϣ�������࣬�̳п���࣬��Ӧ�����¼����б��ѡ���¼�
public class PersonJFrame extends JFrame
    implements ActionListener, ListSelectionListener
{
    protected JList<Person> jlist;                         //�б��
    protected DefaultListModel<Person> listmodel;          //�б��ģ��
    protected PersonJPanel person;                         //Person������Ϣ���
    protected JComboBox<String>[] comboxs;                 //���ҡ�����������Ͽ�
    private static Equalable[] equal={new EqualName(), new EqualBirthdate()}; //�Ժ�˵��
    private static Comparator[] comparators={new CompareName(), new CompareBirthdate()}; //��4.4
    private Font font = new Font("����",1,28);     //�ֺŴ󣬽����ã���5��̲�ûд
    
    //���췽����persָ����ʼ�������飬pers==nullʱ��û�г�ֵ
    public PersonJFrame(Person[] pers)
    {
        this(pers, new PersonJPanel());                   //����Person������Ϣ���
    }
    public PersonJFrame(Person[] pers, PersonJPanel person)//��˼����6-5��person��������ʵ��
    {
        super("Person������Ϣ����");
//        this.setSize(700,240);                             //��������ߴ磬��ͼ��û���ֺ�
        this.setSize(1200,768);                             //��������ߴ�
        this.setLocationRelativeTo(null);                  //������������Ļ����
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);      //�������ڹرհ�ť��������������
        
        //�����ڿ�����ݴ�������ӷָ����������Person������Ϣ���
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//ˮƽ�ָ��
        this.getContentPane().add(split);                  //������ݴ�����ӷָ��
        this.person = person;                              //Person�������������Ϣ���
        MyJPanel.setFont(this.person, font);               //����������������������

        split.add(this.person);                            //�ָ��������Person������Ϣ���
        split.setDividerLocation(200);                     //����ˮƽ�ָ�����λ��
        split.setOneTouchExpandable(true);                 //�ṩһ��չ����ť������չ��/�۵��ָ���
        
        //���´�������ڷָ���ұߵ���壬���а����б����ϲ����������
        JPanel rightpanel = new JPanel(new BorderLayout());//�����߲��֣������б�������
        split.add(rightpanel);                             //�ָ���ұ���������
        this.listmodel = new DefaultListModel<Person>();   //�����յ��б��ģ��
        if(pers!=null)
            for(int j=0; j<pers.length; j++)
                this.listmodel.addElement(pers[j]);        //�б��ģ�����������
        this.jlist = new JList<Person>(this.listmodel);    //�����б��ָ���б��ģ��
        this.jlist.setFont(font);//new Font("����",1,28));           //�����б�����������
        this.jlist.setBorder(new TitledBorder("Person�����б��"));  //��5��ûд
        this.jlist.addListSelectionListener(this);         //�б�����ѡ���¼�
        rightpanel.add(new JScrollPane(this.jlist));       //�������Ӱ����б��Ĺ�������
        JPanel cmdpanel = new JPanel();                    //������壬Ĭ�������֣����У�
        rightpanel.add(cmdpanel,"South");                  //������ϱ�����������
         
        //�����������������Ӱ�ť�Ͳ��ҡ�������Ͽ�
        String[][] str={{"���","ɾ��","ɾ��ѡ����","ɾ��ѡ�ж���"},{"���ҹؼ���","����ؼ���"},{"����","��������"}};
        for(int i=0; i<str[0].length; i++)                 //��Ӱ�ť
        {
            JButton button = new JButton(str[0][i]);
            button.addActionListener(this);
            cmdpanel.add(button);
        }
        this.comboxs = new JComboBox[str[1].length];       //<String>����
        for(int i=0; i<str[1].length; i++)                 //��Ӳ��ҡ�����ؼ�����Ͽ�
        {
            cmdpanel.add(new JLabel(str[1][i]));
            cmdpanel.add(this.comboxs[i]=new JComboBox<String>(str[2]));
            this.comboxs[i].addActionListener(this);       //��Ͽ���������¼�
        }
        MyJPanel.setFont(cmdpanel, font);        //���������������������壬��5��̲�ûд
        this.setVisible(true);
        this.jlist.setCellRenderer(new FontListRenderer(font)); //12.3.2 �б��Ԫ��Ⱦ�����ڡ���12.3����Ŀ�С�
        
        //�о�
//        this.jlist.setSelectedIndex(0);          //�б��ѡ�У������б��ѡ���¼� 
    }
    public PersonJFrame()
    {
        this(null, new PersonJPanel());          //����Person������Ϣ���
    }

    //�б��ѡ���¼���������ѡ���б��������ʱ����ִ��
    public void valueChanged(ListSelectionEvent event)
    {
    	System.out.println(this.jlist.getSelectedValue());
    	this.person.set(this.jlist.getSelectedValue());//���б��ѡ��������Person�������ȡֵ
        //����ʱ��̬��
        //��this.person����StudentJPanelʱ��this.jlist.getSelectedValue()����Student��
        //this.person.set()ִ��StudentJPanel.set(Person)�������������ͱ�����Person�������޷����ǣ��޷�����ʱ��̬
    }
    
    public void actionPerformed(ActionEvent event)            //�����¼���������������ť��ѡ����Ͽ�
    {    
//        System.out.println(this.getClass().getName()+"��"+event.getSource().getClass().getName()+"��"+event.getActionCommand());
        if(event.getSource() instanceof JButton)           //�����˰�ť
        {
            Person per=null;
            switch(event.getActionCommand())     //JDK 8��switch�������ʽ������String
            {
                case "���":                               //��������ӡ���ť
                    if((per=this.person.get())!=null)      //per���Personʵ��������ʱ��̬
                        this.listmodel.addElement(per);    //�б��ģ�����Person����
                    break;
                    
                case "ɾ��":                                 //������ɾ������ť
                	remove(this.listmodel, this.person.get());
                    break;
                    
                case "ɾ��ѡ����":
                    this.removeSelected(this.jlist, this.listmodel);
                    break;
                    
                case "ɾ��ѡ�ж���":  //��5���6��ûд����4����12.2
                    this.removeSelectedAll(this.jlist, this.listmodel);
                    break;
            }
        }
        else if(event.getSource() instanceof JComboBox)    //���Һ�������Ͽ�
        {
            jlist.clearSelection();                        //�������ѡ��״̬
            if(event.getSource()==this.comboxs[0])         //������Ͽ�
            {
                int i=this.comboxs[0].getSelectedIndex();  //��ò��ҹؼ������
                if(i<equal.length)                    //Ϊʲô�������������ӵ����������
                    selectAll(this.listmodel, this.jlist, this.person.get(), equal[i]);
                                       //���Ҳ�ѡ��������person��ȶ���
//                    ListModelOper.selectAll(this.listmodel, this.jlist, this.person.get(), equal[i]);
//              return;
            }
            else if(event.getSource()==this.comboxs[1])    //������Ͽ�
            {
                int i=this.comboxs[1].getSelectedIndex();  //�������ؼ������
                if(i<comparators.length)            //Ϊʲô�������������ӵ����������
                    sort(this.listmodel, comparators[i]);  //�б��ģ������������
//                   ListModelOper.sort(this.listmodel, comparators[i]);  //�б��ģ������������
            }
        }
    }
    
    //��listmodel�б��ģ����ɾ����obj��ȵ������ͨ�÷���
    public <T> void remove(DefaultListModel<T> listmodel, T obj)
    {
        //�¾䵯��ȷ�϶Ի����ͼ6-14(b)���������ǡ���ť����0���������񡱰�ť����1
        if(obj!=null && JOptionPane.showConfirmDialog(this, "ɾ�� "+obj.toString()+"��", "ȷ��",
            JOptionPane.YES_NO_OPTION)==0)
        {
            //�¾���listmodel�б��ģ����ɾ���׸���objָ��������ȵ������
            //˳������㷨��Ĭ�ϵ���obj��equals(Object)�����Ƚ϶�����ȡ�
            boolean remove = this.listmodel.removeElement(obj);
            JOptionPane.showMessageDialog(this, remove?"ɾ���ɹ�":"���Ҳ��ɹ���û��ɾ��");
        }
    }
    
    //��jlist�б��ѡ������������listmodel�б��ģ����ɾ����ͨ�÷���
    public <T> void removeSelected(JList jlist, DefaultListModel<T> listmodel)
    {
        if(this.listmodel.getSize()==0)               //�����б����������
            JOptionPane.showMessageDialog(this, "�б��Ϊ�գ�����ɾ��");
        else
        {   
   	        int i=this.jlist.getSelectedIndex();       //�б��ѡ�����������
            if(i==-1)
                JOptionPane.showMessageDialog(this, "��ѡ���б��������");
            else
            {
                String str=this.jlist.getSelectedValue().toString(); //�б��ѡ���������ַ���
                if(JOptionPane.showConfirmDialog(this, "ɾ�� ��"+i+"��("+str+")��","ȷ��", 
                    JOptionPane.YES_NO_OPTION)==0)     //ȷ�϶Ի��򣬵������ǡ���ť���򷵻�0
                    this.listmodel.removeElementAt(i); //ɾ���б���i������
            }
        }
    }
    //˵������1������б��գ���û��ѡ��һ�i==-1��jlist.getSelectedValue()����null��
    //��2��removeElementAt(-1)���׳��쳣ArrayIndexOutOfBoundsException: Array index out of range: -1

    
    //��12�£���12.3.2 �б��   1.�б�����ѡ��  
    //���γ����12���б��ɾ��ѡ�ж��ʹ��ArrayList<T>
    //��jlist�б��ѡ�ж���������listmodel�б��ģ����ȫ��ɾ����ͨ�÷�����
    public <T> void removeSelectedAll(JList jlist, DefaultListModel<T> listmodel)
    {
        try
        {   //�¾䵱�б��ѡ�ж���ʱ�������б��϶���
            ArrayList<Person> list=(ArrayList<Person>)this.jlist.getSelectedValuesList();
            for(Person per:list)                 //��Ԫѭ����per���list�����е�ÿ��Ԫ��
                this.listmodel.removeElement(per); //ɾ���б�����ֵΪper��������
        }
        catch(ClassCastException ex)             //����б��գ���û��ѡ��һ����׳��쳣
        {
            JOptionPane.showMessageDialog(this, "�б��գ���û��ѡ�����������ɾ��");
        }
    }
    //˵������1��jlist.getSelectedValues();�����ѱ�������
    //��2�������﷨����
//      List<Person> list=(ArrayList<Person>)this.jlist.getSelectedValuesList();
//      List<Person> list=(LinkedList<Person>)this.jlist.getSelectedValuesList();
    //  LinkedList<Person> list=(LinkedList<Person>)this.jlist.getSelectedValuesList();
	

    //�����������Ƕ��б��ģ���������������Ͳ��ҵ�ͨ���㷨�� 
    //���б��ģ���У�˳����Ҳ�ѡ��������key��ȵ������ 
    //��Equalable<? super T>����eq�ṩ�Ƚ�T������ȷ���
    public <T> void selectAll(DefaultListModel<? super T> listmodel, 
            JList<? super T> jlist, T key, Equalable<? super T> eq)
    {
        int n=listmodel.getSize();                         //�����б�����������
        for(int i=0; i<n; i++)
            if(eq.equals(key, (T)listmodel.elementAt(i)))  //�Ƚ�����T�����Ƿ����
                jlist.addSelectionInterval(i, i);          //�б�����ѡ�е�i���ѡ״̬����û�д����б��ѡ���¼�
//        jlist.setSelectedIndex(i);                 //�б��ѡ�е�i�û�д����б��ѡ���¼�
        //ֻѡ�����һ��
    }

    //��listmodel�б��ģ��������������Comparator<? super T>�ӿڶ���c�Ƚ�T�����С��ֱ��ѡ�������㷨
    //listmodel���ø�ֵ���ڷ��������޸��������������ʵ�ʲ�����
	//��12�£��﷨��ȷ���ܽ���Comparator<Student>����
    public <T> void sort(DefaultListModel<? super T> listmodel, Comparator<? super T> c)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)    //ֱ��ѡ�������㷨
        {
            int min=i; 
            for(int j=i+1; j<listmodel.getSize(); j++)
                if(c.compare((T)listmodel.getElementAt(j), (T)listmodel.getElementAt(min))<0)
                    min = j; 
            if(min!=i) 
            {   
                T temp = (T)listmodel.getElementAt(i);
                listmodel.setElementAt((T)listmodel.getElementAt(min), i);
                listmodel.setElementAt(temp, min);
            }
        }        
    }
    
    public static void main(String arg[])
    {
        Person[] pers={new Person("Li��С��",new MyDate(1994,3,15),"��","����","�Ͼ�"),
                       new Person("Bai����", new MyDate(1997,5,1),"Ů","����","����"),
                       new Person("Bai����", new MyDate(1994,3,15),"��","�㽭","����"),
                       new Person("Hua����", new MyDate(1999,10,1),"Ů","�㽭","����"),
                       new Person("Wang��ΰ", new MyDate(1998,4,3),"��","����","��ɳ"),
                       new Person("Zhang����",new MyDate(1998,4,3),"Ů","����","�人"),
                       new Person("Zhu��С��",new MyDate(1994,3,15),"Ů","�㶫","����"),
                       new Person("Zhao�Ծ�",new MyDate(1999,10,1),"��","����","����")};
        new PersonJFrame(pers);
//        new PersonJFrame();
    }
}
/*
�������˵�����¡�
1�������﷨�����������������飬��Ȼ�����ǶԵġ�
    private static Equalable<Person> equal[]={new EqualName(), new EqualBirthday()};
    private static Comparator<Person> comparators[]={new CompareName(), new CompareBirthday()};
    private static Equalable<? extends Person>[] equal={new EqualName(), new EqualBirthday()};

2�������﷨��ȷ��
    private static Equalable<?> equal[]={new EqualName(), new EqualBirthday()};
    private static Comparator<?> comparators[]={new CompareName(), new CompareBirthday()};
      ������������������﷨��Ϊʲô����
    public <T> void search(T obj, Equalable<? super T> e)
    public <T> void sort(java.util.Comparator<? super T> c)//��12�£��﷨��ȷ���ܽ���Comparator<Student>����
      
    //˼���⣺ɾ��ѡ�ж����Ⱦ������12��ʵ�֡�
    //˼���⣺���ֲ��Һ�������򣬰��������??
    //ʧ�ܣ�����ʵ������¼��������ӿڡ�
    //���ı��ܴ�Сʱ������������������������ֺţ�ʹ֮���ſ�ܳߴ���ı��С

3������setSelectedIndex(i)��setSelectedValue(obj,scroll)����
    �����б��ѡ��������б��ѡ���¼���event.getValueIsAdjusting()=false��ִ��һ�Ρ�
    
4��������ѡ���б��һ�ִ���б��ѡ���¼����Σ�ȡֵ��ͬ��
         System.out.println("event.getValueIsAdjusting()="+event.getValueIsAdjusting());
event.getValueIsAdjusting()=true
event.getValueIsAdjusting()=false

       //����������䣬��ѡ��һ��ʱ��ִ��һ�θ��¼�
//        if (!event.getValueIsAdjusting())
 */
//@author��Yeheya��2016-9-15 ����ڣ�2017��2��6�գ�2018��8��26��