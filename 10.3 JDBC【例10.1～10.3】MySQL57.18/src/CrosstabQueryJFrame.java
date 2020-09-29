//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��5��6��
//��10.4  JDBC
//����10.3��  ����ͳ�Ʊ�

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

//����ͳ�Ʊ����࣬�̳���10.2���ݲ�ѯ�����
public class CrosstabQueryJFrame extends QueryJFrame
{
    private DefaultTableModel crosstabmodel;        //����ͳ�Ʊ�ı��ģ��

    //���췽����driverָ��JDBC��������urlָ�����ݿ�URL��tableָ�����ݿ��
    //��sortColumn����row��columnָ������ͳ�Ʊ���к���    
    public CrosstabQueryJFrame(String driver, String url, String table, String sortColumn,
            String row, String column)  throws ClassNotFoundException, SQLException
    {
        super(driver, url, table, sortColumn);
        this.setTitle(this.getTitle()+"������ͳ�Ʊ�");
        JComponent comp = (JComponent)this.getContentPane().getComponent(0);//��ø������ݴ����е��������ѯ���
        JSplitPane split = new JSplitPane(0,comp,null);    //��ֱ�ָ���ϲ���Ӹ���Ĳ�ѯ���
        this.getContentPane().add(split);                  //������ݴ�����ӷָ��
        split.setDividerLocation(this.getHeight()/2);      //���ô�ֱ�ָ�����λ��
        split.setOneTouchExpandable(true);                 //�ṩһ��չ����ť������չ��/�۵��ָ���
        
        this.crosstabmodel = new DefaultTableModel();      //����ͳ�Ʊ�ı��ģ�ͣ�û���б��⣬0��
        JTable jtable = new JTable(this.crosstabmodel);    //�������ָ�����ģ��
        split.add(new JScrollPane(jtable));                //�ָ����ӹ������񣨰������
        crosstab(table, row, column, this.crosstabmodel);  //���㽻��ͳ�Ʊ������ʾ�ڱ�����
        this.setVisible(true);
    }
    
    //��table���row��column�м��㽻��ͳ�Ʊ������ʾ��tablemodel��
    public void crosstab(String table, String row, String column, DefaultTableModel tablemodel) throws SQLException
    {
        String sql = "SELECT "+row+" , COUNT(*) AS ����  FROM "+table +
                     " GROUP BY "+row+" ORDER BY "+row;    //��row�У�ʡ�ݣ�����ͳ������
        query(sql, tablemodel);                  //ִ�и�������ݲ�ѯ��tablemodel���ִ��sql�Ľ����
        
        int col = tablemodel.getColumnCount();             //���ǰ����
        sql="SELECT DISTINCT "+column+" FROM "+table;      //����У��Ա𣩵Ĳ��ظ�ȡֵ
        Statement stmt=this.conn.createStatement();        //����������
        ResultSet rset=stmt.executeQuery(sql);             //ִ�����ݲ�ѯSELECT���
        while(rset.next())       //������������������У��Ա�ȡֵ��ӵ������Ϊ����б���
            tablemodel.addColumn(rset.getString(1));

        //Ϊ������ӵ�ÿ�У���д�����ͳ��ֵ
        for(int j=col; j<tablemodel.getColumnCount(); j++)
        {
            sql="SELECT "+row+", COUNT(*) FROM "+table +
                  " WHERE "+column+"='"+tablemodel.getColumnName(j)+"' GROUP BY "+row; //����
            stmt=this.conn.createStatement();              //����������
            rset=stmt.executeQuery(sql);                   //ִ�����ݲ�ѯSELECT���
//            System.out.println(tablemodel.getColumnName(j));//��ʾ���ǰ��������Ϊ��ѯ����
//            while(rset.next())                           //�����������������ǰ������ÿ��
//            {
//                System.out.println(rset.getString(1)+", "+rset.getString(2));
//            }        
            boolean next = rset.next();          //��ý�����׸�Ԫ�أ�����������������Ϊѭ������
            for(int i=0; i<tablemodel.getRowCount() && next; i++)
            {
//                System.out.println(rset.getString(1)+", "+rset.getString(2));//��ʾ�������ǰԪ��ֵ
//                System.out.println(tablemodel.getValueAt(i,0)+", "+rset.getString(1));//��ʾ�������±Ƚϵ�ֵ
                if(tablemodel.getValueAt(i,0).equals(rset.getString(1)))
                {
                    tablemodel.setValueAt(rset.getString(2), i, j);
                    next = rset.next();          //���Ƚ����ʱ���Ż�ý�����¸�Ԫ�أ��������� 
                }
            }
        }
        rset.close();
        stmt.close();
    }
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        //MySQL 5.7.18��2018��3��7��
        String driver = "com.mysql.jdbc.Driver";           //ָ��MySQL JDBC��������
        String url="jdbc:mysql://localhost/studentmis?user=root&password=1234";//ָ�����ݿ�URL
        new CrosstabQueryJFrame(driver, url, "person", "province", "province", "gender"); 
    }
}
//@author��Yeheya��2018��3��10�գ�2018��8��27��