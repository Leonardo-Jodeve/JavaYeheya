//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��20��
//��6.3.8 ������
//����6.6�� �������д�����»�����Ϣ��
//MyEclipse���ñ���·��������Ŀ����3.2��MyDate����
//û�д����쳣�ʹ���
//��ʵ����6-25�� 
//�� �������һ�У�����ȶϢ�����
//�� �������һ�У��������ַ�ʽ�Ļ����ܶ
//�� ����΢���ı���֮��ʵ�ֹ��������ı��·�Ӱ�쵽���ʱ����Ӧ�ظ�����ݡ�

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

//����������࣬�̳п���࣬��Ӧ�����¼�����ֵ�ı��¼�
public class LoanJFrame extends JFrame implements ActionListener, ChangeListener
{
    private JTextField[] texts;                  //�ı������飬��ʾ��������ʡ�����
    private JSpinner spin_year, spin_month;      //΢���ı��У���ʾ��ʼ����
    private MyDate paydate;                      //��ʼ��������
    private JButton button;                      //���㰴ť
    private DefaultTableModel tablemodel;        //���ģ��
    private Font font = null;//new Font("����",1,28);   //�ֺŴ󣬽����ã���5��̲�ûд
    protected JPanel cmdpanel;                   //�������
    
    public LoanJFrame()
    {
        super("�������д�����»�����Ϣ");
        this.setBounds(300,240,800,360);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //���´���������壬�ṩ�����������ı��кͼ��㰴ť�����
        this.cmdpanel = new JPanel();            //������壬Ĭ�������֣�����
        this.getContentPane().add(this.cmdpanel, "North");
        String[] str={"������","Ԫ    ��������","%/��    ��������","��    ������ʼ","��","��"};   
        String[] str_text={"100000","0.5025","1"};   
        this.texts = new JTextField[str_text.length];
        int i=0;
        for(i=0; i<str_text.length; i++)         //��ӱ�ǩ���ı��У���ʾ��������
        {
        	this.cmdpanel.add(new JLabel(str[i]));
        	this.cmdpanel.add(this.texts[i]=new JTextField(str_text[i],6));
        }
        for(; i<str.length; i++)                 //������������3����ǩ
        	this.cmdpanel.add(new JLabel(str[i]));

        //���¼��㵱ǰ���ڵ����£�Ϊ����΢���ı��и���ֵ
        Calendar start = Calendar.getInstance(); //��õ�ǰ����
        int year=start.get(Calendar.YEAR);       //����
        int month=start.get(Calendar.MONTH)+1;   //���£�get(Calendar.MONTH)��Χ��0��11
        month = month%12+1;                      //����
        if(month==1)                             //12�µ������Ǵ���1��
            year++;
        int day = MyDate.daysOfMonth(year, month);    //���ָ�����µ�������MyDate��ľ�̬����
        this.paydate = new MyDate(year, month, day);  //Լ���������ڳ�ֵ���������һ��
        
        //�¾��������ֵ����ģʽ����ֵ��year����Χ��year��year+2��������1
        this.spin_year=new JSpinner(new SpinnerNumberModel(year, year, year+2, 1));
        this.cmdpanel.add(this.spin_year,7);     //����������΢���ı��У�����7ָ������λ��
        //�¾����·���ֵ����ģʽ����ֵ��month����Χ��1��12��0��13����������1  //�·ݿ�ȹ� 
        this.spin_month=new JSpinner(new SpinnerNumberModel(month, 0, 13, 1));
        this.cmdpanel.add(this.spin_month,9);
        this.spin_month.addChangeListener(this); //�·�΢���ı��м�����ֵ�ı��¼�
//        this.spin_month.setValue(0);         //����stateChanged(ChangeEvent)�¼�
        
        this.cmdpanel.add(this.button=new JButton("����"));
        this.button.addActionListener(this);
        MyJPanel.setFont(this.cmdpanel, font);   //���������������������壬����6.4����5��ûд��������ʾ��
        
        //�����ڿ�����ݴ����в���ӱ��
        String[] titles={"��������","������Ԫ��","�»�����Ԫ��","�»���Ϣ��Ԫ��","�»���Ϣ��Ԫ��","�ȶϢ��Ԫ��"};
        this.tablemodel = new DefaultTableModel(titles,1); //Ĭ�ϱ��ģ�ͣ�titlesָ���б��⣬1��
        JTable jtable = new  JTable(this.tablemodel);      //�������ָ�����ģ��
        this.getContentPane().add(new JScrollPane(jtable));//������ݴ�����ӹ������񣨰������
//        jtable.setFont(font);//���У����ÿ����и߲�������
//??        jtable.setDefaultRenderer(jtable.getColumnClass(0), new FontTableCellRenderer(PersonJFrame.font));
        this.setVisible(true);

//        //������Ⱦ����ûЧ��������δ�ɹ�����5��ûд��������ʾ�á�2018��2��9��
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//             //Ĭ�ϱ��Ԫ��Ⱦ�����̳�JLabel�����ԣ�renderer��JLabel
//        renderer.setFont(PersonJFrame.font);   //��������
//        jtable.setDefaultRenderer(jtable.getColumnClass(2),renderer);   //��ӱ��Ԫ��Ⱦ��
    }    
 
    //�����¼����������������㰴ť������ʹ�ñ����ʾ������ϸ��Լ��ÿ�����һ�컹��
    public void actionPerformed(ActionEvent event)
    {
        //���»�ø��ı��б�ʾ�Ĵ������ݣ�δ������ֵ��ʽ�쳣�͸���
        double leavings = Double.parseDouble(""+texts[0].getText()); //����𣬱������
        double rate = Double.parseDouble(""+texts[1].getText());     //����������
        int months = Integer.parseInt(texts[2].getText())*12;        //�����������ɴ������޼����
        double pay = leavings/months;                                //�»�����
        this.tablemodel.setRowCount(months);               //���ñ��ģ������
        
        double value=Math.pow(1+rate*0.01, months);        //�ȶϢ����5��˼����6-6
        double repayment = (leavings*rate*0.01*value)/(value-1);//�ȶϢ����5��˼����6-6

        //���»�û�����ʼ���ڣ�Ԥ�����������������µĴ���
        int year = (Integer)this.spin_year.getValue();     //������ʼ���
        int month = (Integer)this.spin_month.getValue();   //������ʼ�·�
        //����getValue()����Object��ʵ������Integer��ǿ��ת����Ĭ�ϵ���intValue()���int��
        //��SpinnerNumberModel�����ֵ���ͣ��������д���ȡ���ָ�ԭֵ��
        //��˲���Ҫ������ֵ��ʽ�쳣��          
        MyDate alterdate = new MyDate(year, month, MyDate.daysOfMonth(year, month));//��������
        if(alterdate.compareTo(this.paydate)<0)       //���޸ĵĻ��������������£����·ݴ���
        {
            JOptionPane.showMessageDialog(this, "���û�������Ϊ"+alterdate.toString()+
                    "���·ݴ����������¡�");
            return;
        }       
        for(int row=0; row<months; row++)             //���ÿ����ʾÿ�»�����ϸ
        {
            this.tablemodel.setValueAt(alterdate.toString(), row,0);                           //��������
            this.tablemodel.setValueAt(String.format("%9.2f",leavings), row, 1);               //�������
            this.tablemodel.setValueAt(String.format("%9.2f",pay), row, 2);                    //�»�����
            this.tablemodel.setValueAt(String.format("%9.2f",leavings*rate*0.01), row, 3);     //�»���Ϣ
            this.tablemodel.setValueAt(String.format("%9.2f",pay+leavings*rate*0.01), row, 4); //�»���Ϣ
            this.tablemodel.setValueAt(String.format("%9.2f",repayment), row, 5);              //�ȶϢ����˼����6-6�� ��
/*            try                                  //try��䣬��5��ûд
            {
                Thread.sleep(300);               //���У�sleep��������ʾ��
            }
            catch(InterruptedException ex)
            {
                return;
            }//2018��5��14�գ������̶߳���ʵ�ַ�������ʵ��7-5����������Ķ�̬��ʾ��
*/            
            month = month%12+1;                       //����
            if(month==1)
                year++;
            alterdate = new MyDate(year, month, MyDate.daysOfMonth(year, month));
            leavings -= pay;                          //��������ȥ�»�����
        }
        sum(this.tablemodel, 4,5);       //�������һ�У���4��5����ͣ���5��ʵ����6-25
    }
    //˵����û�е���MyDate���nextMonth()������
    
    public static void main(String[] arg)
    {
        new LoanJFrame();
    }

    //��5�桾ʵ����6-25���� �������һ�У�������columnsָ������֮�͡�
    //������������String��ת����double���㣻columns�ǿɱ������ʹ�÷�ʽͬ���顣
    //tablemodel���ø�ֵ���ڷ��������޸��䵥Ԫ��ֵ��������ʵ�ʲ�����
    public void sum(DefaultTableModel tablemodel, int... columns)
    {
        int rows = tablemodel.getRowCount();     //�������
        double[] sum=new double[columns.length]; //��ż����������飬��ֵĬ��0.0
        for(int row=0; row<rows; row++)          //���ÿ��
            for(int i=0; i<columns.length; i++)  //��������
                sum[i] += Double.parseDouble((String)tablemodel.getValueAt(row, columns[i]));

        String[] rowstr = new String[tablemodel.getColumnCount()];
        rowstr[0]="�ϼ�";
        for(int col=0; col<columns.length; col++)//��������
            rowstr[columns[col]] = String.format("%9.2f",sum[col]);
        tablemodel.addRow(rowstr);               //tablemodel���ģ������һ�У�rowstrָ��������ֵ
    }
    
    //�޸�JSpinnerֵʱ�����¼��Ĵ�������ʵ��ChangeListener�ӿڡ����������У�����
    //JSpinner������/���°�ť������JSpinner�а�Enter�����򰴼����ϵ�����/�¼�ͷ��
    public void stateChanged(ChangeEvent event)
    {
        int year = (Integer)this.spin_year.getValue();     //������ʼ���
        int month = (Integer)this.spin_month.getValue();   //������ʼ�·�
        System.out.println("stateChanged, year="+year+", month="+month);
        if(month==0 || month==13)               //Ҫ�޸�ֵ���������
        {
            switch(month)
            {
                case 13: month=1;  year++; break;
                case 0:  month=12; year--;
            }
            this.spin_year.setValue(year);
            this.spin_month.setValue(month);     //�ٴδ���stateChanged(ChangeEvent)�¼�
        }
    }
    //��˵������stateChanged(ChangeEvent)�¼��������У����µ������ı��·�JSpinnerֵ�����ٴδ������¼���
    //      this.spin_month.setValue(month);
    //���޸ĺ��ֵ��ȷ�������JSpinnerû����Ӧ���¼�����ˣ���һ�ε������2�Σ��������޵ݹ顣
}
//@author��Yeheya��2016-9-27��2017��2��13�գ�2017��7��22��