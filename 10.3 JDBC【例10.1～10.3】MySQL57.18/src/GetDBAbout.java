//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月5日
//§10.2 MySQL 5.7.18 //和SQL Server 2012
//§10.3 JDBC
//§10.3.3 连接数据库
//【例10.1】  连接MySQL数据库服务端，获得studentmis数据库属性信息。
//§10.3.4  执行SQL语句
//执行插入和删除数据的SQL语句。

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBAbout
{
    public static void main(String args[]) throws Exception
    {
    	//10.3.3 连接数据库 【例10.1】  连接指定数据库并获得数据库属性信息。
        Class.forName("com.mysql.jdbc.Driver");            //指定MySQL JDBC驱动程序，ClassNotFoundException
        String url = "jdbc:mysql://localhost/studentmis?user=root&password=1234";
        Connection conn = DriverManager.getConnection(url);//创建与url指定数据库的连接对象

        DatabaseMetaData dbmd = conn.getMetaData();        //获得所连接数据库的属性信息
        System.out.println("JDBC驱动程序："+dbmd.getDriverName()+"，"+dbmd.getDriverVersion()
            +"\nJDBC URL："+dbmd.getURL()+"\n数据库："+dbmd.getDatabaseProductName()
            +"，版本："+dbmd.getDatabaseProductVersion()+"，用户名："+dbmd.getUserName());

        //10.3.4 执行SQL语句   执行插入和删除数据的SQL语句。
        Statement stmt = conn.createStatement();           //创建执行SQL语句的Statement对象
        String sql="INSERT INTO person  VALUES ('齐小天','2000-01-1','男','江苏','南京');";
        int count = stmt.executeUpdate(sql);               //执行SQL语句，返回执行成功影响的行数
        System.out.println("INSERT "+count);
        
        sql="DELETE FROM person WHERE(name='齐小天')";
        count = stmt.executeUpdate(sql);                   //执行SQL语句，返回执行成功影响的行数
        System.out.println("DELETE "+count);
        
        sql="INSERT INTO person  VALUES ('齐小天','2000-01-1','男','江苏','扬州');";
        count = stmt.executeUpdate(sql);                   //执行SQL语句，返回执行成功影响的行数
        System.out.println("INSERT "+count);
        
        sql="UPDATE person  SET province='江苏', city='南京'  WHERE name='齐小天';";
        count = stmt.executeUpdate(sql);                   //执行SQL语句，更新一行，返回执行成功影响的行数
        System.out.println("INSERT "+count);
        
        stmt.close();  
        conn.close();                                      //关闭数据库连接
    }
}
/*
程序运行结果如下：
Mon Mar 05 17:01:15 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
JDBC驱动程序：MySQL Connector Java，mysql-connector-java-5.1.41 ( Revision: 83c6dc41b96809df81444362933043b20a1d49d5 )
JDBC URL：jdbc:mysql://localhost/studentmis?user=root&password=1234
数据库：MySQL，版本：5.7.18-log，用户名：root@localhost
INSERT 1
*/
//@author：Yeheya，2018年3月7日，2018年8月27日
/*
        //SQL Server 2012，2017年4月20日成功
//    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  //指定SQL Server JDBC驱动程序
//        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student;user=sa;password=";  //指定SQL Server数据库student的URL
*/