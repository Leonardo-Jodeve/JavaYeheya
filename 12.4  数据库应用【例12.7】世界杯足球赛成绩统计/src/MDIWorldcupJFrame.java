//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年3月10日
//§12.4 数据库应用
//【例12.7】 世界杯足球赛成绩统计。
//（2） 设计多文档界面的JDBC数据库应用程序
//突出算法，没有处处都处理异常

import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import java.sql.*;

//多文档界面的数据库应用程序框架类，响应动作事件
public class MDIWorldcupJFrame extends JFrame implements ActionListener
{
    private Connection conn;                               //数据库连接对象 
    private JDesktopPane desktop;                          //桌面窗格
    
    //构造方法，参数driver、url指定JDBC驱动和数据库路径
    public MDIWorldcupJFrame(String driver, String url) throws ClassNotFoundException, SQLException
    {
        super("第21届世界杯足球赛  2018年俄罗斯");
        this.setSize(1024,600);
        this.setLocationRelativeTo(null);                  //将窗口置于屏幕中央
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(desktop=new JDesktopPane());  //框架的内容窗格添加桌面窗格

        JMenuBar menubar = new JMenuBar();                 //菜单栏，以下添加窗口菜单
        this.setJMenuBar(menubar);
        String[][] menustr={{"参赛队","输入参赛队","浏览参赛队"},
                            {"小组赛","战况和积分榜"}, {"淘汰赛","输入","浏览"}, {"射手榜"}};
        JMenu[] menu=new JMenu[menustr.length];
        for(int i=0; i<menu.length; i++)
        {
            menubar.add(menu[i]=new JMenu(menustr[i][0])); //菜单栏添加菜单
            for(int j=1; j<menustr[i].length; j++)
            {
                JMenuItem menuitem=new JMenuItem(menustr[i][j]);//菜单添加菜单项
                menu[i].add(menuitem);
                menuitem.addActionListener(this);
            }
        }        
        this.setVisible(true);
        Class.forName(driver);                             //指定JDBC驱动程序
        this.conn=DriverManager.getConnection(url);        //返回数据库连接对象
    }

    //动作事件处理方法，单击菜单项时，分别打开相应内容框架，不重复打开
    public void actionPerformed(ActionEvent event)
    {
        try
        {
        	String menustr=event.getActionCommand();       //获得单击菜单项名
            JInternalFrame[] inners=desktop.getAllFrames();//返回桌面窗格中的所有内部框架
            int i=0; 
            while(i<inners.length && !inners[i].getTitle().equals(menustr))
                i++;                                       //查找当前菜单项对应的内部框架是否已打开
            if(i<inners.length)                            //若打开则设置选中状态
                inners[i].setSelected(true);               //选中内部框架，不重复打开
            else                                           //查找不成功时，打开内部框架
            {
                JInternalFrame inframe=null;               //内部框架对象
                String[] team={"组别","球队"};              //表的列中文标题
                switch(menustr)
                {
                    case "浏览参赛队":
                        inframe=new BrowseJInFrame(this.conn,"TeamScore",team,"xgroup","xgroup");
                        break;
                    case "输入参赛队":
                        inframe=new InputTeamJInFrame(this.conn,"TeamScore",team,"xgroup","xgroup");
                        break;
                    case "战况和积分榜":
                        String[] teamscore={"组别","球队","已赛场数","胜","平","负","进球","失球","净胜球","积分","排名"};
                        String[] matchrecord={"场次","比赛时间","组别","球队1","球队2","队1进球数","队2进球数","比赛地点"};
                        inframe = new InputMatchJInFrame(this.conn, "TeamScore", teamscore, 
                                  "xgroup", "xgroup,rank", "MatchRecord", matchrecord, "xgroup,number");
                        break;
                }
                inframe.setTitle(menustr);
                desktop.add(inframe);                      //添加内部框架到桌面窗格
                JInternalFrame inner=desktop.getSelectedFrame();
                if(inner!=null)                            //设置各内部框架级联显示
                    inframe.setLocation(inner.getX()+50, inner.getY()+50);
                inframe.setSelected(true);                 //选中当前内部框架
            }
        }
        catch(SQLException | PropertyVetoException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        String driver="com.mysql.jdbc.Driver";             //指定MySQL JDBC驱动程序
        String url="jdbc:mysql://localhost/worldcup2018?user=root&password=1234";
        new MDIWorldcupJFrame(driver, url);   
    }
    
    public void finalize() throws SQLException             //析构方法，关闭数据库连接
    {
        this.conn.close();
    }
}
//@author：Yeheya，2018年3月10日，2018年8月30日