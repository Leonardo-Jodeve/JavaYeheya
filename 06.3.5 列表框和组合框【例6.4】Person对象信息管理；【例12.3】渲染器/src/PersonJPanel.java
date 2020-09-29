//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��9��
//��6.3.4 �б�����Ͽ�
//����6.4�� Person������Ϣ����
//��1��Person��壬����Ͽ�ģ��
//���ԣ�����Ͽ�ģ�ͣ��÷��Ͳ���
//��˼����6-3��  �� ʹ�������������������ڡ�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;                    //������ı߿�

//Person������Ϣ����࣬�̳�����࣬��Ӧ�����¼���Person�����3.3
//Itemѡ���¼����ڲ��ԣ���5��̲�ûд
public class PersonJPanel extends JPanel implements ActionListener, ItemListener
{
    private JTextField text_name, text_date;               //���������������ı���
    private JRadioButton[] radios;                         //�Ա�ť����
    public JComboBox<String> combox_province, combox_city; //ʡ�ݡ�������Ͽ�
    private static String[] provinces={"����", "�㽭","����","����","����","�㶫"};   //�洢��ʡ������
    private static String[][] cities={{"�Ͼ�","����","����"},{"����","����","����"},
                                      {"��ɳ"},{"�人"},{"����"},{"����"}};

    public PersonJPanel()                                  //���췽��
    {
        this.setBorder(new TitledBorder("Person"));        //���������д�����ı߿�
        int n = Person.class.getFields().length;           //���ָ����Ĺ��г�Ա������������12��12.2����
        this.setLayout(new GridLayout(n,1));               //������񲼾֣�5��1��
        
        this.add(this.text_name = new JTextField("����"));
        this.add(this.text_date = new JTextField("2000��1��1��"));

        String[] str={"��","Ů"};
        JPanel rbpanel=new JPanel(new GridLayout(1,2));    //�Ա���壬1��2�����񲼾֣���ѡ��ť
//        rbpanel.setBorder(new TitledBorder("�Ա�"));
        this.add(rbpanel);
        ButtonGroup bgroup = new ButtonGroup();            //��ť��
        this.radios = new JRadioButton[str.length];
        for(int i=0; i<this.radios.length; i++)
        {
            rbpanel.add(this.radios[i]=new JRadioButton(str[i])); //������ѡ��ť��Ĭ�ϲ�ѡ�У���ӵ��Ա����
            bgroup.add(this.radios[i]);                   //��ѡ��ť��ӵ���ť��
        }        
        this.radios[0].setSelected(true);                 //��ѡ��ťѡ��
        this.add(this.combox_province = new JComboBox<String>(PersonJPanel.provinces));//ʡ����Ͽ�
        this.add(this.combox_city = new JComboBox<String>(PersonJPanel.cities[0]));//������Ͽ�
        this.combox_province.addActionListener(this);      //ʡ����Ͽ���������¼�
        this.combox_province.addItemListener(this);        //ʡ����Ͽ����ѡ���¼�
    }

    public void set(Person per)                  //���ø�����ֱ���ʾper���������
    {
//        System.out.println("p="+(p==null?"null":p.toString()));
        if(per==null)
            return;
        this.text_name.setText(per.name);
        this.text_date.setText(per.birthdate.toString());
        if(per.gender.equals("��"))
            this.radios[0].setSelected(true);
        else
            this.radios[1].setSelected(true);
        this.combox_province.setSelectedItem(per.province);  //���������¼�
        this.combox_city.setSelectedItem(per.city);
    }
    public Person get()                //���ظ������ʾ����ֵ��Person���󣬴������ڸ�ʽ�쳣
    {
        //�¾��õ�ѡ��ť��ʾ���Ա��ַ���
        String gender = radios[0].isSelected() ? radios[0].getText() : radios[1].getText();
        try
        {
            //�¾���text���ܹ������ڣ����׳������쳣������5.3 
            MyDate birthdate = new MyDate(this.text_date.getText());
            return new Person(text_name.getText(), birthdate, gender,
                       (String)combox_province.getSelectedItem(), (String)combox_city.getSelectedItem());//�����Ͽ�ѡ������ַ���
        }
        catch(NumberFormatException ex)          //������ֵ��ʽ�쳣��Java����
        {
            JOptionPane.showMessageDialog(this, ex.getMessage()+" �ַ�������ת����������");
        }
        catch(DateFormatException ex)            //�������ڸ�ʽ�쳣������5.3
        {
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return null;
    }
    
    public void actionPerformed(ActionEvent event)  //�����¼�������������Ͽ�������б���ѡ��������ʱִ��
    {//ѡ��ʱִ��һ��
//        System.out.println(this.getClass().getName()+"��"+event.getClass().getName()+"��"+
//                event.getSource().getClass().getName()+"��"+event.getActionCommand()+"��"+
//                this.combox_province.getSelectedItem());
        
        int i=this.combox_province.getSelectedIndex();     //ʡ����Ͽ�ǰѡ�������
        if(cities!=null && i!=-1)
        {
            this.combox_city.removeAllItems();             //���������Ͽ���ԭ�����������ݣ�����this.combox_city�Ķ����¼�
            for(int j=0; j<PersonJPanel.cities[i].length; j++)
                this.combox_city.addItem(PersonJPanel.cities[i][j]);//������Ͽ����������
            
/*            //ʹ����Ͽ�ģ�����Ч����ͬ
            DefaultComboBoxModel<String> comboxModel = (DefaultComboBoxModel<String>)this.combox_city.getModel();
            comboxModel.removeAllElements();                //���������Ͽ���ԭ�������ݣ�����this.combox_city�Ķ����¼�
            for (int j=0; j<cities[i].length; j++)
                comboxModel.addElement(cities[i][j]);   //������Ͽ����������*/
        }
    }
        
    //���½̲�ûд
    //��Ͽ�ģ���о����̲�ûд
    private <T> void println(JComboBox<T> combox)
    {
        int n=combox.getItemCount();          //ʡ����Ͽ�ǰѡ�������
        for(int i=0; i<n; i++)
           System.out.print(combox.getItemAt(i));   //������Ͽ����������
        System.out.println();

        DefaultComboBoxModel<T> comboxModel = (DefaultComboBoxModel<T>)combox.getModel();
        n = comboxModel.getSize();          //ʡ����Ͽ�ǰѡ�������
        for(int i=0; i<n; i++)
           System.out.print(comboxModel.getElementAt(i));   //������Ͽ����������
        System.out.println();
    }
    //���ۣ�ÿ����Ͽ���Ĭ����Ͽ�ģ�ͣ�combox.addItem()�ȼ�����ӵ���Ͽ�ģ�͡�
    
    //ѡ���¼��о����̲�ûд���б��ѡ�У�����ʡ��ʱ�������¼�
    public void itemStateChanged(ItemEvent event)   //���û���ѡ����ȡ��ѡ��ĳ��ʱ����
    {
//        System.out.println(this.getClass().getName()+"��"+event.getClass().getName()+"��"+event.getSource().getClass().getName()
//                +"��event.getStateChange()="+event.getStateChange()+"��e.getItem()="+event.getItem());
        //˵����һ��ѡ��ִ��2�θ��¼���
        //e.getStateChange()=2��e.getItem()=ԭֵ
        //e.getStateChange()=1��e.getItem()=�ı�ֵ
        //���ı�ֵ==ԭֵ����ִ��
    }    
}

/*
1����Ͽ�ѡ�������ִ��2��ItemEvent�¼�����ev.getStateChange()״̬��ev.getItem()ȡֵ��ͬ
2����Ͽ�ѡ�������ִ��1��ActionEvent�¼���e.getActionCommand()ֵΪ"comboBoxChanged"
       ʡ����Ͽ��ֵΪ�����ա���ѡ���㽭�������н�����£�
PersonJPanel��java.awt.event.ItemEvent��javax.swing.JComboBox��e.getStateChange()=2��e.getItem()=����
PersonJPanel��java.awt.event.ItemEvent��javax.swing.JComboBox��e.getStateChange()=1��e.getItem()=�㽭
PersonJPanel��java.awt.event.ActionEvent��javax.swing.JComboBox��comboBoxChanged���㽭
       ��ѡ�񡰽��ա������н�����£�
PersonJPanel��java.awt.event.ItemEvent��javax.swing.JComboBox��e.getStateChange()=2��e.getItem()=�㽭
PersonJPanel��java.awt.event.ItemEvent��javax.swing.JComboBox��e.getStateChange()=1��e.getItem()=����
PersonJPanel��java.awt.event.ActionEvent��javax.swing.JComboBox��comboBoxChanged������
      ��ѡ�񡰽��ա�������Ͽ�ѡ������֮ǰ��ͬ���򲻴���ItemEvent�¼���
PersonJPanel��java.awt.event.ActionEvent��javax.swing.JComboBox��comboBoxChanged������


//ɾ��������
             int n=this.combox_city.getItemCount();          //ʡ����Ͽ�ǰѡ�������
            for (int j=n-1; j>=0; j--)
                this.combox_city.removeItemAt(j);   //������Ͽ����������

//        //��˼����6-4��  �� ʹ�������������������ڡ�
//        this.mydate = new MyDateSpinJPanel(new MyDate(2000,12,31));
//        this.remove(1);
//        this.add(this.mydate,1);                           //�滻�����������

        //�о�
/*        println(this.combox_province);
        println(this.combox_city);
        this.combox_city.addActionListener(this);      //��Ͽ�ע�ᶯ���¼�������
//        this.combox_province.addItem("����");
        this.combox_province.addActionListener(new ActionAdapter());//��Ͽ�����ע�ᶯ���¼������������ִ�У�
        this.combox_province.addActionListener(this);      //ʡ����Ͽ�ע�ᶯ���¼�������
        this.combox_province.setEditable(true);

//�������࣬û���õ�������Person������Ϣ���
class ActionAdapter implements ActionListener
{
    public void actionPerformed(ActionEvent e)             //�����¼�������������Ͽ�������б���ѡ��������ʱִ��
    {
        System.out.println(this.getClass().getName()+"��"+e.getClass().getName()+"��"+
                e.getSource().getClass().getName()+"��"+e.getActionCommand()+"��"+
                ((JComboBox<String>)e.getSource()).getSelectedItem());
    }
}
/*
�����о�����Ͽ�����ע�ᶯ���¼������������ִ�У�
˵������ִ�У�ִ�д����ǣ���ע�����ִ�С�
ActionAdapter��java.awt.event.ActionEvent��javax.swing.JComboBox��comboBoxChanged���㽭
PersonJPanel��java.awt.event.ActionEvent��javax.swing.JComboBox��comboBoxChanged���㽭


 */ 
//@author��Yeheya��2016-9-15 ����ڣ�2017��2��6��