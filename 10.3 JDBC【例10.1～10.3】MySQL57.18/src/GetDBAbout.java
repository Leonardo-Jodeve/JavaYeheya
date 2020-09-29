//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��5��
//��10.2 MySQL 5.7.18 //��SQL Server 2012
//��10.3 JDBC
//��10.3.3 �������ݿ�
//����10.1��  ����MySQL���ݿ����ˣ����studentmis���ݿ�������Ϣ��
//��10.3.4  ִ��SQL���
//ִ�в����ɾ�����ݵ�SQL��䡣

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBAbout
{
    public static void main(String args[]) throws Exception
    {
    	//10.3.3 �������ݿ� ����10.1��  ����ָ�����ݿⲢ������ݿ�������Ϣ��
        Class.forName("com.mysql.jdbc.Driver");            //ָ��MySQL JDBC��������ClassNotFoundException
        String url = "jdbc:mysql://localhost/studentmis?user=root&password=1234";
        Connection conn = DriverManager.getConnection(url);//������urlָ�����ݿ�����Ӷ���

        DatabaseMetaData dbmd = conn.getMetaData();        //������������ݿ��������Ϣ
        System.out.println("JDBC��������"+dbmd.getDriverName()+"��"+dbmd.getDriverVersion()
            +"\nJDBC URL��"+dbmd.getURL()+"\n���ݿ⣺"+dbmd.getDatabaseProductName()
            +"���汾��"+dbmd.getDatabaseProductVersion()+"���û�����"+dbmd.getUserName());

        //10.3.4 ִ��SQL���   ִ�в����ɾ�����ݵ�SQL��䡣
        Statement stmt = conn.createStatement();           //����ִ��SQL����Statement����
        String sql="INSERT INTO person  VALUES ('��С��','2000-01-1','��','����','�Ͼ�');";
        int count = stmt.executeUpdate(sql);               //ִ��SQL��䣬����ִ�гɹ�Ӱ�������
        System.out.println("INSERT "+count);
        
        sql="DELETE FROM person WHERE(name='��С��')";
        count = stmt.executeUpdate(sql);                   //ִ��SQL��䣬����ִ�гɹ�Ӱ�������
        System.out.println("DELETE "+count);
        
        sql="INSERT INTO person  VALUES ('��С��','2000-01-1','��','����','����');";
        count = stmt.executeUpdate(sql);                   //ִ��SQL��䣬����ִ�гɹ�Ӱ�������
        System.out.println("INSERT "+count);
        
        sql="UPDATE person  SET province='����', city='�Ͼ�'  WHERE name='��С��';";
        count = stmt.executeUpdate(sql);                   //ִ��SQL��䣬����һ�У�����ִ�гɹ�Ӱ�������
        System.out.println("INSERT "+count);
        
        stmt.close();  
        conn.close();                                      //�ر����ݿ�����
    }
}
/*
�������н�����£�
Mon Mar 05 17:01:15 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
JDBC��������MySQL Connector Java��mysql-connector-java-5.1.41 ( Revision: 83c6dc41b96809df81444362933043b20a1d49d5 )
JDBC URL��jdbc:mysql://localhost/studentmis?user=root&password=1234
���ݿ⣺MySQL���汾��5.7.18-log���û�����root@localhost
INSERT 1
*/
//@author��Yeheya��2018��3��7�գ�2018��8��27��
/*
        //SQL Server 2012��2017��4��20�ճɹ�
//    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  //ָ��SQL Server JDBC��������
//        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student;user=sa;password=";  //ָ��SQL Server���ݿ�student��URL
*/