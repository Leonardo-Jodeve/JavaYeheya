//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��10��
//��12.4 ���ݿ�Ӧ��
//����12.7�� ���籭�������ɼ�ͳ�ơ�
//��2�� ��ƶ��ĵ������JDBC���ݿ�Ӧ�ó���
//ͻ���㷨��û�д����������쳣

import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import java.sql.*;

//���ĵ���������ݿ�Ӧ�ó������࣬��Ӧ�����¼�
public class MDIWorldcupJFrame extends JFrame implements ActionListener
{
    private Connection conn;                               //���ݿ����Ӷ��� 
    private JDesktopPane desktop;                          //���洰��
    
    //���췽��������driver��urlָ��JDBC���������ݿ�·��
    public MDIWorldcupJFrame(String driver, String url) throws ClassNotFoundException, SQLException
    {
        super("��21�����籭������  2018�����˹");
        this.setSize(1024,600);
        this.setLocationRelativeTo(null);                  //������������Ļ����
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(desktop=new JDesktopPane());  //��ܵ����ݴ���������洰��

        JMenuBar menubar = new JMenuBar();                 //�˵�����������Ӵ��ڲ˵�
        this.setJMenuBar(menubar);
        String[][] menustr={{"������","���������","���������"},
                            {"С����","ս���ͻ��ְ�"}, {"��̭��","����","���"}, {"���ְ�"}};
        JMenu[] menu=new JMenu[menustr.length];
        for(int i=0; i<menu.length; i++)
        {
            menubar.add(menu[i]=new JMenu(menustr[i][0])); //�˵�����Ӳ˵�
            for(int j=1; j<menustr[i].length; j++)
            {
                JMenuItem menuitem=new JMenuItem(menustr[i][j]);//�˵���Ӳ˵���
                menu[i].add(menuitem);
                menuitem.addActionListener(this);
            }
        }        
        this.setVisible(true);
        Class.forName(driver);                             //ָ��JDBC��������
        this.conn=DriverManager.getConnection(url);        //�������ݿ����Ӷ���
    }

    //�����¼��������������˵���ʱ���ֱ����Ӧ���ݿ�ܣ����ظ���
    public void actionPerformed(ActionEvent event)
    {
        try
        {
        	String menustr=event.getActionCommand();       //��õ����˵�����
            JInternalFrame[] inners=desktop.getAllFrames();//�������洰���е������ڲ����
            int i=0; 
            while(i<inners.length && !inners[i].getTitle().equals(menustr))
                i++;                                       //���ҵ�ǰ�˵����Ӧ���ڲ�����Ƿ��Ѵ�
            if(i<inners.length)                            //����������ѡ��״̬
                inners[i].setSelected(true);               //ѡ���ڲ���ܣ����ظ���
            else                                           //���Ҳ��ɹ�ʱ�����ڲ����
            {
                JInternalFrame inframe=null;               //�ڲ���ܶ���
                String[] team={"���","���"};              //��������ı���
                switch(menustr)
                {
                    case "���������":
                        inframe=new BrowseJInFrame(this.conn,"TeamScore",team,"xgroup","xgroup");
                        break;
                    case "���������":
                        inframe=new InputTeamJInFrame(this.conn,"TeamScore",team,"xgroup","xgroup");
                        break;
                    case "ս���ͻ��ְ�":
                        String[] teamscore={"���","���","��������","ʤ","ƽ","��","����","ʧ��","��ʤ��","����","����"};
                        String[] matchrecord={"����","����ʱ��","���","���1","���2","��1������","��2������","�����ص�"};
                        inframe = new InputMatchJInFrame(this.conn, "TeamScore", teamscore, 
                                  "xgroup", "xgroup,rank", "MatchRecord", matchrecord, "xgroup,number");
                        break;
                }
                inframe.setTitle(menustr);
                desktop.add(inframe);                      //����ڲ���ܵ����洰��
                JInternalFrame inner=desktop.getSelectedFrame();
                if(inner!=null)                            //���ø��ڲ���ܼ�����ʾ
                    inframe.setLocation(inner.getX()+50, inner.getY()+50);
                inframe.setSelected(true);                 //ѡ�е�ǰ�ڲ����
            }
        }
        catch(SQLException | PropertyVetoException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        String driver="com.mysql.jdbc.Driver";             //ָ��MySQL JDBC��������
        String url="jdbc:mysql://localhost/worldcup2018?user=root&password=1234";
        new MDIWorldcupJFrame(driver, url);   
    }
    
    public void finalize() throws SQLException             //�����������ر����ݿ�����
    {
        this.conn.close();
    }
}
//@author��Yeheya��2018��3��10�գ�2018��8��30��