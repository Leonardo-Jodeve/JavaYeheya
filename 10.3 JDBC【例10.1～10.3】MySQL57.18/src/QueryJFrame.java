//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��4��27��
//��10.2 MySQL���ݿ�
//��10.3 JDBC
//��10.3.5 �������ݲ�ѯ�����
//����10.2�� ��ʾ���ݲ�ѯ�������
//ʹ�ñ��ģ��

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Calendar;

public class QueryJFrame extends JFrame          //���ݲ�ѯ�����
{
    protected Connection conn;                   //���ݿ����Ӷ���
    private DefaultTableModel tablemodel;        //���ģ��

    //���췽����driverָ��JDBC��������urlָ�����ݿ�URL��tableָ�����ݿ����sortColumn����
    public QueryJFrame(String driver, String url, String table, String sortColumn) 
            throws ClassNotFoundException, SQLException
    {
        super("���"+table+"��");
        this.setBounds(300,240,600,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Class.forName(driver);                             //ָ��JDBC��������
        this.conn=DriverManager.getConnection(url);        //�������ݿ����Ӷ���

        this.tablemodel = new DefaultTableModel();         //Ĭ�ϱ��ģ�ͣ�û���б��⣬0��
        JTable jtable = new JTable(this.tablemodel);       //�������ָ�����ģ��
        this.getContentPane().add(new JScrollPane(jtable));//������ݴ�����ӹ������񣨰������
        query(table, sortColumn, this.tablemodel);         //ִ�����ݲ�ѯ���������ʾ��tablemodel
        addAge(this.tablemodel);                 //���ģ���������������
        this.setVisible(true);
    }

    //ִ��table�����ݲ�ѯ����sortColumn���򣬲�ѯ�������ʾ��tablemodel���ͨ�÷���
    public void query(String table, String sortColumn, DefaultTableModel tablemodel) throws SQLException
    {
        String sql = "SELECT * FROM "+table;
        if(sortColumn!=null && sortColumn!="")
            sql+=" ORDER BY "+sortColumn;
        query(sql, tablemodel);
    }        
    //ִ��sql��ʾ�����ݲ�ѯSELECT��䣬����ѯ�������ʾ��tablemodel����С�ͨ�÷���
    public void query(String sql, DefaultTableModel tablemodel) throws SQLException
    {
    	Statement stmt=this.conn.createStatement();//1003,1007); //���������󣬽�������ݲ����к�ֻ��
        ResultSet rset=stmt.executeQuery(sql);             //ִ�����ݲ�ѯSELECT���
//      System.out.println("rset.getType()="+rset.getType());
//      System.out.println("rset.getConcurrency()="+rset.getConcurrency());
//        System.out.println("rset.isBeforeFirst()="+rset.isBeforeFirst());
        
        //���»�ñ�������������������Ϊ�������ı���
        ResultSetMetaData rsmd = rset.getMetaData();       //���ر����Զ���
        int count = rsmd.getColumnCount();                 //�������
        for(int j=1; j<=count; j++)                        //����������ӵ����ģ����Ϊ�б��⣬��š�1
            tablemodel.addColumn(rsmd.getColumnLabel(j));

        //���½�������и���������ӵ����ģ�ͣ�һ�α���
        Object[] columns=new Object[count];                //�����ж������飬���鳤��Ϊ����
        while(rset.next())                                 //�����������������ǰ������ÿ��
        {
            for(int j=1; j<=columns.length; j++)           //���ÿ�и���ֵ
                columns[j-1]=rset.getString(j);
            tablemodel.addRow(columns);                    //���ģ�����һ�У�����ָ������ֵ
        }        
        rset.close();
        stmt.close();
    }
    //˵����û����String[] titles;ָ������б��⣬����Ͳ���Ҫʹ�ý������Ԫ���ݻ�ñ�ṹ

    //���ģ���������������
    public void addAge(DefaultTableModel tablemodel) throws SQLException
    {
        int thisYear=Calendar.getInstance().get(Calendar.YEAR); //��õ�ǰ���
        int column=tablemodel.getColumnCount();            //��ñ������
        tablemodel.addColumn("����");                      //������һ��
        //���±������ģ�ͣ�ÿ���������������ֵ
        for(int row=0; row<tablemodel.getRowCount(); row++)
        {
            String birthdate = (String)tablemodel.getValueAt(row, 1);//��ó���������
        	String year=birthdate.substring(0, 4);         //��ó������ڵ����
        	int age = thisYear - Integer.parseInt(year);   //�������䣬δ�����쳣
        	tablemodel.setValueAt(age, row, column);       //�������������ֵ
        }
    }    
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        //MySQL 5.7.18��2018��3��7��
        String driver = "com.mysql.jdbc.Driver";           //ָ��MySQL JDBC��������
        String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//ָ�����ݿ�URL
        new QueryJFrame(driver, url, "person", "province");//��ѯperson����province����

//        String[] titles={"����","��������","�Ա�","ʡ��","����"};  //ָ������б���
//        String url="jdbc:mysql://localhost/worldcup2006?user=root&password=";
//        new QueryJFrame(driver, url, "team");
        
    }
    public void finalize() throws SQLException             //�����������ر����ݿ�����
    {
        this.conn.close();
    }
}
//@author��Yeheya��2018��3��10�գ�2018��8��27��
/*
//���α���
/*        int rowCount=0;
while (rset.next())                                //�����������������ý��������
    rowCount++;
tablemodel.setRowCount(rowCount);                  //���ñ��ģ������
rset.beforeFirst();                                //�ƶ���ǰ��ָ�뵽��һ��֮ǰ

//��������и���������ӵ����ģ��
for (int i=0; rset.next(); i++)                    //�����������������ǰ������ÿ�У�����š�0
    for (int j=1; j<=columns; j++)                 //���ÿ�и���ֵ
        tablemodel.setValueAt(rset.getString(j),i,j-1);
//System.out.println("rset.isAfterLast()="+rset.isAfterLast());
 */
