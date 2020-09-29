//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��12��
//��12.4 ���ݿ�Ӧ��
//����12.7�� ���籭�������ɼ�ͳ�ơ�
//��5�������������ͼ�����ְ�

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;

//ս���ͻ��ְ��ڲ�����࣬�̳з�������ڲ�����࣬��Ӧ�����¼������ģ���¼�
class InputMatchJInFrame extends BrowseJInFrame implements ActionListener, TableModelListener
{
    private String table2;                                 //���ݿ��еı���
    private String sort_columns2;                          //ָ���������ݵ���
    private JTable jtable2;                                //������
    private DefaultTableModel tablemodel2;                 //���ģ��
    private ResultSet rset_match;                          //�����
    
    //���췽��������ָ������������б��⡢������к��������ݵ���
    InputMatchJInFrame(Connection conn, String table, String[] columnNames, String list_column,
            String sort_columns, String table2, String[] columnNames2, String sort_columns2)
            throws SQLException
    {
        super(conn, table, columnNames, list_column, sort_columns);//����������ڲ������
        this.setSize(800, 400); 
        //�¾�Ϊ����2�����õ�Ԫ��Ⱦ�������ͼ��
        this.jtable.setDefaultRenderer(this.tablemodel.getColumnClass(2), new IconTableCellRenderer());
        
        JToolBar toolbar = new JToolBar();       //���´��������������������2����ť
        this.getContentPane().add(toolbar,"North");
        String[] buttonstr={"���ɱ�������","������ְ�"};
        for(int j=0; j<buttonstr.length; j++)
        {
            JButton button = new JButton(buttonstr[j]);
            toolbar.add(button);
            button.addActionListener(this);
        }
        
        //���´��������ӵ���ֱ�ָ���²�        
        this.tablemodel2 = new DefaultTableModel(columnNames2,0);//���ģ��
        this.jtable2 = new JTable(this.tablemodel2); 
        split_ver.setDividerLocation(this.getHeight()/2);         //����ˮƽ�ָ�����λ��
        split_ver.setRightComponent(new JScrollPane(this.jtable2));//��ֱ�ָ���²���ӱ��
        this.tablemodel2.addTableModelListener(this);       //ע����ģ���¼�������

        //����ִ�����ݲ�ѯ����������¼���������ʾ����
        this.table2 = table2;
        this.sort_columns2 =sort_columns2;
        this.rset_match = query(table2, "", sort_columns2, tablemodel2);
//        this.jtable2.setDefaultRenderer(this.tablemodel2.getColumnClass(4), new IconTableCellRenderer());
//        this.jtable2.setDefaultRenderer(this.tablemodel2.getColumnClass(5), new IconTableCellRenderer());
    }

    //�б��ѡ���¼������������б����ѡ��������ʱ���������Ǹ���ͬ��������
    //���б��ѡ��������Ϊ������������table2���в�ѯ�����������ʾ��tablemodel2���ģ����
    public void valueChanged(ListSelectionEvent event)
    {
        super.valueChanged(event);         //���ø���ͬ���������������ϱ������ı��ģ��
        String item = this.jlist.getSelectedValue();       //�б��ѡ����
        if(item!=null) 
        {
            String where ="";
            if(!(item.equals("ȫ��")))
                where = this.list_column + " = '"+item+"'";
            this.rset_match = query(table2, where, sort_columns2, tablemodel2);
        }
    }

    //���ģ���¼��������������ģ�Ͳ��롢�޸ġ�ɾ��������ֵʱ������
    //�����޸ı��ģ��ֵʱ���ñ��ģ�͵�ǰ�༭ֵ���½�������ύ���ݿ�
    public void tableChanged(TableModelEvent event)
    {
        if(event.getType()==TableModelEvent.UPDATE)
        {
            int row=event.getFirstRow(), col=event.getColumn();//��ñ��ģ�͵�ǰ�޸ĵ��к���
            if(col==2 || col==3 || col==4)
                JOptionPane.showMessageDialog(this, "�����޸����Ͳ����ӡ�");
            else if(row!=-1 && col!=-1 && col!=2 && col!=3 && col!=4)
                try                                            //�����޸��������������
                {
                    String value=(String)tablemodel2.getValueAt(row, col);//��ñ��ģ���޸�ֵ
                    this.rset_match.absolute(row+1);           //���ý������ǰ��
                    this.rset_match.updateString(col+1,value); //�ñ��ģ���޸�ֵ���ý����ָ����
                    System.out.println("row+1="+(row+1)+"��col+1="+(col+1)+"��value="+value);
                    this.rset_match.updateRow();               //���������ǰ���ύ���ݿ⣬�������ݿ�
//2018��8��31�գ�Ӧ���Ǵ˾䣬�����޸Ĳ����ӡ��˴�ִ���׳��쳣
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        }
    }
    
    public void actionPerformed(ActionEvent event)    //�����¼�������
    {
        switch(event.getActionCommand())              //������ťʱ
        {
            case "���ɱ�������":  insertmatch();  break;
            case "������ְ�":  updateScore();  break;
        }
    }

    //��TeamScore��ò����ӣ�����ѭ�����ı������Σ�����MatchRecord���У�
    //��ѡ��һ�飬�����ɸ���ı������Σ�����ѡ�л�ѡ�С�ȫ������������ȫ����ı������Ρ�
    private void insertmatch()
    {
        String sql="SELECT xgroup,teamname FROM "+this.table, where="";
        String item = this.jlist.getSelectedValue();       //�б��ѡ����
        if(item!=null && !(item=="ȫ��"))                  //ѡ����
            where = " WHERE "+this.list_column + " = '"+item+"'";
        sql+=where+" ORDER BY "+this.list_column;
        try
        {        
            ResultSet rset=this.stmt.executeQuery(sql);    //���ָ�����ȫ����Ķ���
            int rowCount=0;
            while(rset.next())                             //�����������������ý��������
                rowCount++; 
            rset.beforeFirst();                            //���������ǰ��ָ��ָ���һ��֮ǰ
            String[][] teams=new String[rowCount][2];
            for(int i=0; rset.next(); i++)                 //���������Ͷ�
            {
                teams[i][0]=rset.getString(1);             //���xgroup
                teams[i][1]=rset.getString(2);             //���teamname
            }
            rset.close();
            for(int i=0; i<teams.length; i++)              //������¼��������ɱ�����¼
                for(int j=i+1; j<4*(1+i/4); j++)           //������¼ÿ�����������������
                    this.stmt.executeUpdate("INSERT INTO matchrecord(xgroup, teamname1, teamname2)"+
                        " VALUES ('"+teams[i][0]+"', '"+teams[i][1]+"', '"+teams[j][1]+"')"); 
            this.rset_match=query(table2, where, sort_columns2, tablemodel2);
                         //��table2��matchrecord�����в�ѯ����where�������У���sort_columns2��������ӵ����ģ��
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void updateScore()                             //������ְ�
    {
        try
        {
            int count = this.tablemodel.getRowCount();     //��ñ��ģ������
            String sql;
            ResultSet rset;
            for(int i=0; i<count; i++)
            {
                String teamname=(String)this.tablemodel.getValueAt(i,1);  //����
                sql="SELECT count(*) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"' AND (goalsfor1 is not null) or "+
                          "teamname2='"+teamname+"' AND (goalsfor2 is not null)";
//              System.out.println(sql);
                rset=stmt.executeQuery(sql);
                rset.next();
                int completed=rset.getInt(1);              //��������
            
                sql="SELECT count(*) FROM matchrecord "+  
                    " WHERE teamname1='"+teamname+"' AND (goalsfor1>goalsfor2) or "+
                           "teamname2='"+teamname+"' AND (goalsfor1<goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int win=rset.getInt(1);                    //ʤ����

                sql="SELECT count(*) FROM matchrecord  WHERE (teamname1='"+teamname+
                    "' or teamname2='"+teamname+"') AND (goalsfor1=goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int tie=rset.getInt(1);                    //ƽ����
 
                sql="SELECT count(*) FROM matchrecord "+  
                    " WHERE teamname1='"+teamname+"' AND (goalsfor1<goalsfor2) or "+
                           "teamname2='"+teamname+"' AND (goalsfor1>goalsfor2)";
                rset=stmt.executeQuery(sql);
                rset.next();
                int loss=rset.getInt(1);                   //������
        	
                sql="SELECT sum(goalsfor1) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                int goalsfor=rset.getInt(1);               //��Ϊ��1�Ľ�����

                sql="SELECT sum(goalsfor2) FROM matchrecord "+  
                    "WHERE teamname2='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                goalsfor+=rset.getInt(1);                  //����Ϊ��2�Ľ�����

                sql="SELECT sum(goalsfor2) FROM matchrecord "+  
                    "WHERE teamname1='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                int goalsagainst=rset.getInt(1);           //��Ϊ��1��ʧ����
            
                sql="SELECT sum(goalsfor1) FROM matchrecord "+  
                    "WHERE teamname2='"+teamname+"'";
                rset=stmt.executeQuery(sql);
                rset.next();
                goalsagainst+=rset.getInt(1);              //����Ϊ��2��ʧ����
                rset.close();
 
                //��ʤ��Ϊgoalsfor-goalsagainst������Ϊwin*3+tie
                sql="UPDATE TeamScore SET completed="+completed+" ,win="+win+" ,tie="+tie+
                    ",loss="+loss+", goalsfor="+goalsfor+", goalsagaint="+goalsagainst+
                    ", netvalue="+(goalsfor-goalsagainst)+", score="+(win*3+tie)+
                    " WHERE teamname='"+teamname+"'";
                stmt.executeUpdate(sql);                   //ִ��UPDATE�����±���ָ����
            }
        
            for(int i=0; i<count; i+=4)                    //����С������
            {
                String xgroup=(String)this.tablemodel.getValueAt(i, 0);
                sql="SELECT teamname FROM TeamScore WHERE xgroup='"+xgroup+
                    "' ORDER BY score DESC, netvalue DESC, goalsfor DESC";    
                rset=stmt.executeQuery(sql);               //��õ�ǰС�鰴��������Ľ����
                String[] teams=new String[4];
                for(int j=0; rset.next(); j++)             //�������4������������������
                    teams[j]=rset.getString("teamname");
                for(int j=0; j<teams.length; j++)          //����һ��С��ĸ�������
                    stmt.executeUpdate("UPDATE TeamScore SET rank="+(j+1)+
                                       " WHERE teamname='"+teams[j]+"'");
            }
            valueChanged(null);                            //ˢ��������������ʾ
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
//@author��Yeheya��2018��3��14�գ�2018��8��31��