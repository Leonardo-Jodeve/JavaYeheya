//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��10��
//��12.4 ���ݿ�Ӧ��
//����12.7�� ���籭�������ɼ�ͳ�ơ�
//��3�� �������������

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

//��������������ڲ�����࣬��Ӧ�б��ѡ���¼�
class BrowseJInFrame extends JInternalFrame implements ListSelectionListener
{
    protected String table;                                //���ݿ��еı���
    protected String list_column;                          //���з������
    protected String sort_columns;                         //�������ݵĶ���
    protected JList<String> jlist;                         //�б����ʾ�����еĲ��ظ�ֵ
    protected DefaultListModel<String> listmodel;          //Ĭ���б��ģ��
    protected JTable jtable;                               //����������ʾ���ݿ���ָ��������
    protected DefaultTableModel tablemodel;                //Ĭ�ϱ��ģ��
    protected JSplitPane split_ver;                        //��ֱ�ָ��
    protected Statement stmt;                              //������ 
    protected ResultSet rset_team;                         //�����
    
    //���췽������������ָ�����ݿ����ӡ��������е����ı��⡢������С��������ݵĶ���
    BrowseJInFrame(Connection conn, String table, String[] columnNames, String list_column,
            String sort_columns) throws SQLException
    {
        super("", true, true, true, true);
        this.setSize(500, 300);

        this.listmodel=new DefaultListModel<String>();     //���´����б��
        this.listmodel.addElement("ȫ��");
        this.jlist=new JList<String>(this.listmodel);
        this.jlist.addListSelectionListener(this);         //�б�����ѡ���¼�
//        this.jlist.setSelectedIndex(1);                    //ѡ�е�1�Ϊʲô����Ҫ��
        
        //���´���ˮƽ�ָ���������б���ұ���Ӵ�ֱ�ָ��
        JSplitPane split_hor=new JSplitPane(1, new JScrollPane(this.jlist), null);
        this.getContentPane().add(split_hor);
        split_hor.setDividerLocation(50);                  //���ô�ֱ�ָ�����λ��

        this.tablemodel=new DefaultTableModel(columnNames,0);  //���´������
        this.jtable=new JTable(this.tablemodel);           //�������
        split_ver=new JSplitPane(0,new JScrollPane(this.jtable),null); //��ֱ�ָ���ϲ���ӱ��
        split_hor.add(split_ver);
        
        //���´�����䣬ִ�����ݲ�ѯ���������ʾ��ȫ��������
        this.stmt = conn.createStatement(1005, 1008);  //������䣬������������кͿɸ���
//        this.stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        this.table=table;
        this.list_column=list_column;
        this.sort_columns=sort_columns;
        query(table,listmodel);                            //��ѯ�����б��ģ��ֵ
//        this.jlist.setSelectedIndex(0);                    //�б��ѡ�С�ȫ��������������б��ѡ���¼�������ѡ�У�������ְ����಻ͨ
        query(table, "", sort_columns, tablemodel);        //��ѯ���ñ��ģ��ֵ��""��ʾȫ��
//        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), new IconTableCellRenderer());
                //Ϊ����2������ͼ�굥Ԫ��Ⱦ����
                //���⣬֮������ʾѡ�б��״̬��Ϊʲô����
        this.setVisible(true);
    }

    //��ѯtable��������list_column�е����в��ظ�ֵ��ӵ��б��ģ����
    public void query(String table, DefaultListModel<String> listmodel) throws SQLException
    {
        String sql="SELECT DISTINCT "+this.list_column+" FROM "+table+
                      " ORDER BY "+this.list_column;       //���ָ���в��ظ���ֵ
        ResultSet rset=this.stmt.executeQuery(sql);        //ִ�����ݲ�ѯ��䣬���ؽ����
        while(rset.next())                                 //�������������
            listmodel.addElement(rset.getString(1));       //��ӵ�ǰ��ָ����ֵ���б��ģ��
        rset.close();
    }
    
    //�б��ѡ���¼�����������ѡ���б��������ʱ������
    //���б��ѡ����Ϊ��������table���в�ѯ���������������У����������ʾ�ڱ��ģ����
    public void valueChanged(ListSelectionEvent event)
    {
        String item = this.jlist.getSelectedValue();       //�б��ѡ��������
//        System.out.println(item);
        if(item!=null)
        {
            String where="";                               //""��ʾȫ��
            if(!(item.equals("ȫ��")))
                where=this.list_column + " = '"+item+"'";
            this.rset_team=query(table,where,sort_columns,tablemodel);//��ѯ�����ñ��ģ��
        }
    }
    
    //��table��������where�Ӿ�Ķ�����ӵ�tablemodel���ģ���У���sort_columns����
    public ResultSet query(String table, String where, String sort_columns, DefaultTableModel tablemodel)
    {
        for(int i=tablemodel.getRowCount()-1; i>=0; i--)   //��ձ��
            tablemodel.removeRow(i);                       //ɾ�����ģ�͵�i��

        String sql="SELECT * FROM "+table;                 //�������ݲ�ѯSQL���
        if(where!="")
            sql+=" WHERE " +where;
        sql+=" ORDER BY "+sort_columns;
        try
        {
            ResultSet rset = this.stmt.executeQuery(sql);  //ִ�����ݲ�ѯ���
            ResultSetMetaData rsmd = rset.getMetaData();   //���ؽ����Ԫ���ݶ���
            String[] columns=new String[rsmd.getColumnCount()];//�����ж������飬����Ϊ����
            while(rset.next())                             //�������������
            {
                for(int i=1; i<=columns.length; i++)       //���鱣�浱ǰ��������ֵ������š�1
                    columns[i-1]=rset.getString(i);
                tablemodel.addRow(columns);                //���ģ�����һ�У��������ģ���¼�
            }
            return rset;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public void finalize() throws SQLException             //��������
    {
        this.stmt.close();
    }
}
//@author��Yeheya��2018��3��10�գ�2018��8��30��