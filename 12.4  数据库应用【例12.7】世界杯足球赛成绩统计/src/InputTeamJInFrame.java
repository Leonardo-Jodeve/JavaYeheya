//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��10��
//��12.4 ���ݿ�Ӧ��
//����12.7�� ���籭�������ɼ�ͳ�ơ�
//��4�� ���������  
 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

//����������ڲ�����࣬�̳з�������ڲ�����࣬��Ӧ�����¼������ģ���¼�
class InputTeamJInFrame extends BrowseJInFrame implements ActionListener, TableModelListener
{
    private JTextField[] texts;                            //����ָ������е��ı�������

    //���췽������������ָ�����ݿ����ӡ��������е����ı��⡢������С��������ݵĶ���
    InputTeamJInFrame(Connection conn, String table, String[] columnNames, String list_column, 
                      String sort_columns) throws SQLException
    {
        super(conn, table, columnNames, list_column,sort_columns);//��������������ڲ������
        this.tablemodel.addTableModelListener(this);       //ע����ģ���¼�������
        split_ver.setDividerLocation(this.getHeight()-90); //����ˮƽ�ָ���λ��

        //���´���������壬����ı�����������������ԣ���ӿ��ư�ť
        JPanel cmdpanel=new JPanel();                      //���Ĭ��������
        split_ver.setRightComponent(cmdpanel);             //��ֱ�ָ���²�����������
        this.texts = new JTextField[columnNames.length];
        for(int i=0; i<columnNames.length; i++)            //���������
        {
            cmdpanel.add(new JLabel(columnNames[i], JLabel.RIGHT));
            cmdpanel.add(this.texts[i] = new JTextField(6));
        }
        String[] buttonstr={"���","ɾ��ѡ�ж���"};
        for(int j=0; j<buttonstr.length; j++)              //��Ӱ�ť
        {
            JButton button = new JButton(buttonstr[j]);
            cmdpanel.add(button);
            button.addActionListener(this);
        }
//����        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), null);//ɾ������2�е�ͼ�굥Ԫ��Ⱦ������Ϊ�޷�ѡ����       
    }

    public void actionPerformed(ActionEvent event)         //�����¼�������
    {
        switch(event.getActionCommand())                   //������ť
        {
            case "���":
                String xgroup=this.texts[0].getText();    //���
                //�¾�����table���ݿ���в���(���,����)Ԫ�أ����б�����������ѡ��
                if(add(this.table, xgroup, this.texts[1].getText()))
                     //�¾���listmodel�в��ҡ����벢ѡ��xgroup����1��ʼ�����±��ģ��
                     insert(this.listmodel, xgroup, 1);
                break;
                
            case "ɾ��ѡ�ж���":
                removeSelectedAll(this.jtable, this.tablemodel, this.table, this.listmodel);
                break;
        }
    }
    
    //�������ա����Χ���ʡ��鲻�����������ظ���
    //����table���ݿ���в���(xgroup, name)һ�У������Ƿ����
    private boolean add(String table, String xgroup, String name)
    {
        if(xgroup.equals("") || name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "�������Ͷ��������ǿմ���");
            return false;
        }
        xgroup = xgroup.toUpperCase();           //�ĳɴ�д��ĸ
        if(xgroup.compareTo("A")<0 || xgroup.compareTo("H")>0)
        {
            JOptionPane.showMessageDialog(this, "������"+xgroup+"����A��H��Χ��");
            return false;
        }
        try
        {
            String sql="SELECT COUNT("+list_column+") FROM "+this.table+
                      " WHERE "+list_column+"='"+xgroup+"'";//ͳ��xgroup��Ķ���
            ResultSet rset=this.stmt.executeQuery(sql);    //ִ�����ݲ�ѯ���
            rset.next();
            if(rset.getInt(1)>=4)                          //���ֵ
            {
                JOptionPane.showMessageDialog(this, xgroup+"������4���ӣ�������ӡ�");
                return false;
            }
                
            sql="SELECT COUNT(" +list_column +") FROM "+this.table+
                " WHERE "+list_column+"='"+xgroup+"'"+" AND teamname='"+name+"'";
//                System.out.println(sql);
            rset=this.stmt.executeQuery(sql);              //��ѯxgroup����name��
            rset.next();
            if(rset.getInt(1)>=1)                          //���ֵ>=1����ʾname���Ѵ���
            {
                JOptionPane.showMessageDialog(this, xgroup+"������"+name+"�ӣ�������ӡ�");
                return false;
            }

            sql="INSERT INTO "+this.table+"(xgroup, teamname)"+
                " VALUES ('"+xgroup+"', '"+name+"')";
//                System.out.println(sql);
            if(this.stmt.executeUpdate(sql)==0)            //���ݿ���в���һ�У������ʾӰ�������
            {
                JOptionPane.showMessageDialog(this, "�������ݲ��ɹ���");
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    //��֪�б�������T����������Tʵ��Comparable<? super T>�ӿڡ�
    //��x������뵽listmodel�б��ģ���У��������ظ�����ö��ַ����ң���begin��ʼ
    public <T extends Comparable<? super T>> void insert(DefaultListModel<T> listmodel, T x, int begin)
    {
        int end=listmodel.getSize()-1, mid=end;
        while(begin<=end)                                  //�߽���Ч
        {
            mid = (begin+end)/2;                           //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
            if(x.compareTo(listmodel.elementAt(mid))==0)   //�Ƚ϶����С������ȣ�����ҳɹ���������
            {
                if(this.jlist.getSelectedIndex()==mid)     //ѡ������ᴥ���б��ѡ���¼�
                    valueChanged(null);                    //ִ���б��ѡ���¼�������
                else
                    this.jlist.setSelectedIndex(mid);      //ѡ��mid����������б��ѡ���¼�
                return;
            }
            if(x.compareTo(listmodel.elementAt(mid))<0)    //��x����С��
                end = mid-1;                               //����ҷ�Χ��С��ǰ��Σ�
            else
                begin = mid+1;                             //������ҷ�Χ��С������
        }
        listmodel.insertElementAt(x, begin);     //���Ҳ��ɹ�������x��listmodel��begin��
        //�¾��б��ѡ�е�begin������б��ѡ���¼����������ʾ���������Ľ����
        this.jlist.setSelectedIndex(begin);
    }    
    
    //��jtable����ѡ�ж������ݣ���tablemodel���ģ�ͺ�table���ݿ����ȫ��ɾ����
    private void removeSelectedAll(JTable jtable, DefaultTableModel tablemodel,
                                   String table, DefaultListModel<String> listmodel)
    {
        if (tablemodel.getRowCount()==0)
            JOptionPane.showMessageDialog(this, "���գ�����ɾ�������");
        else
        {
            int[] rows = jtable.getSelectedRows();         //���ѡ�ж��е��к�
            if(rows.length==0)
                JOptionPane.showMessageDialog(this, "��ѡ����������");
            else if(JOptionPane.showConfirmDialog(this, "ɾ��ѡ�ж��У�")==0)//ȷ�϶Ի��򣬵���Yes��ť����0
                for(int i=rows.length-1; i>=0; i--)
                {
                    String xgroup=(String)tablemodel.getValueAt(rows[i],0);//���
                    String name=(String)tablemodel.getValueAt(rows[i],1);  //����
                    String sql="DELETE FROM "+table+" WHERE (teamname='"+name+"')";
                    try
                    {
                        if(this.stmt.executeUpdate(sql)==1)   //ִ��DELETE��䣬ɾ��һ��
                        {
                            tablemodel.removeRow(rows[i]);     //���ģ��ɾ��ָ���У�//�������ģ���¼�
                            //������table���в�ѯ����xgroup����û��Ԫ�أ���ɾ��listmodel�б������Ӧ����
                            sql="SELECT COUNT(" +list_column +") FROM "+table+
                                 " WHERE "+list_column+"='"+xgroup+"'";
//                      System.out.println(sql);
                            ResultSet rset=this.stmt.executeQuery(sql);
                            rset.next();
                            if(rset.getInt(1)==0)
                                listmodel.removeElement(xgroup);  //�б��ɾ��������
                        }
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        }
    }
    
    //���ģ���¼��������������ģ�Ͳ��롢�޸ġ�ɾ��������ֵʱ����
    //�����޸ı��ģ��ֵʱ���ñ��ģ�͵�ǰ�༭ֵ���½�������ύ���ݿ�
    public void tableChanged(TableModelEvent event)
    {
//        System.out.println("event.getType()="+event.getType());
        if(event.getType()==TableModelEvent.UPDATE)
        {
            int row=event.getFirstRow(), col=event.getColumn();//��ñ��ģ�͵�ǰ�޸ĵ��к���
            if(col==0)
            {
                JOptionPane.showMessageDialog(this, "�����޸����");
                valueChanged(null);              //ִ���б��ѡ���¼��������������Ļ�ԭֵ 
            }
            else if(row!=-1 && col!=-1 && col==1)          //�޸Ĳ�������
                try
                {
                    String value=(String)tablemodel.getValueAt(row, col);//��ñ��ģ���޸ĵ�ֵ
                    this.rset_team.absolute(row+1);        //���ý������ǰ��
                    this.rset_team.updateString(col+1, value); //�ñ��ģ���޸�ֵvalue���ý����ָ���У��ַ���
                    System.out.println("row+1="+(row+1)+"��col+1="+(col+1)+"��value="+value);
                    this.rset_team.updateRow();            //���������ǰ���ύ���ݿ⣬�������ݿ�
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        }
    }
}
//@author��Yeheya��2018��3��12�գ�2018��8��30��
/*
{
    this.rset_team.absolute(e.getFirstRow()+1);
    this.rset_team.deleteRow();//ɾ����ǰ�в��ύ
}*//*
//    private JComboBox<String> combox;                      //��Ͽ�ѡ�����
//    private DefaultComboBoxModel<String> combomodel;       //Ĭ����Ͽ�ģ��
//û������Ͽ�ģ�ͣ���Ϊ��Ͽ�ģ�Ͳ������б��ģ�Ͳ�����ͬ�㷨
 * 
    //��table����list_column�е����в��ظ�ֵ��ӵ���Ͽ�ģ���У��㷨ͬ�б��ģ��
//    public void query(DefaultComboBoxModel<String> combomodel)  //��6��û����Ͽ�ģ��,
     //��Ҳû��,�˴��޷����б��ģ�͹��÷���
    public void query(JComboBox<String> combox)
    {
        String sql="SELECT DISTINCT "+list_column+" FROM "+table+" ORDER BY "+list_column;
        try
        {   ResultSet rset=statement.executeQuery(sql);    //ִ�����ݲ�ѯSELECT��䣬���ؽ����
            while (rset.next())                            //�������������
               combox.addItem(rset.getString(1));   //��ӵ�ǰ��ָ����ֵ����Ͽ�ģ��
            rset.close();
        }
        catch(SQLException e)
        {
            ex.printStackTrace();
        }
    } * 
            this.combox.insertItemAt(xgroup,j); //��������뵽��Ͽ�ģ��ָ��λ��
            combomodel.insertElementAt(xgroup,j); //��������뵽��Ͽ�ģ��ָ��λ��
            this.combox.addItem(xgroup); //��������뵽��Ͽ�ģ��ָ��λ��
            combomodel.addElement(xgroup); //��ӵ���Ͽ�ģ�����
            this.combox.removeItem(xgroup); //��Ͽ�ɾ��ָ�����
             this.combomodel.removeElement(xgroup); //��Ͽ�ɾ��ָ�����
* */