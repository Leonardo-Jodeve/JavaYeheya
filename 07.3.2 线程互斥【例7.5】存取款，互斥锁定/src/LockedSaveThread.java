//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》，作者：叶核亚，2017年8月1日
//§7.3 线程同步机制
//§7.3.2  交互线程的竞争与互斥
//【例7.5】  存款、取款交互线程竞争使用同一个账户资源，线程互斥，同步语句。
//MyEclipse设置编译路径包含项目：例7.4（Account）。

public class LockedSaveThread extends Thread     //带互斥锁的存款线程类
{
    private Account account;                     //账户
    private double value;                        //存款金额
    
    public LockedSaveThread(Account account, double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //线程运行方法，存款
    {
    	synchronized(this.account)               //声明临界区，锁定当前账户对象，实现线程互斥
        {
            double howmatch = this.account.balance;
            try
            {
                Thread.sleep(1);                 //即使让出处理器，也不解锁
            }
            catch(InterruptedException ex)  {}
            this.account.put(this.value);
            System.out.println(this.getClass().getName()+"，"+this.account.name+"账户：现有"
            		+howmatch+", 存入"+this.value+", 余额"+this.account.balance);
        }
    }
}

class LockedFetchThread extends Thread           //带互斥锁的取款线程类
{
    private Account account;
    private double value;
    
    public LockedFetchThread(Account account,double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //线程运行方法，取款
    {
        synchronized(this.account)               //声明临界区，锁定当前账户对象，实现线程互斥
        {
            double howmatch = this.account.balance;
            try
            {
                Thread.sleep(1);                 //即使让出处理器，也不解锁
            }
            catch(InterruptedException ex)  {}
            System.out.println(this.getClass().getName()+"，"+this.account.name+"账户：现有"
            	    +howmatch+", 取走"+this.account.get(this.value)+", 余额"+this.account.balance);
        }
    }

    public static void main(String args[])
    {
        Account wang = new Account("Wang");
        (new LockedSaveThread(wang,100)).start();
        (new LockedSaveThread(wang,200)).start();
        (new LockedFetchThread(wang,300)).start();
    }
}
/*
程序运行结果如下：        
LockedSaveThread，Wang账户：现有0.0, 存入100.0, 余额100.0
LockedFetchThread，Wang账户：现有100.0, 取走100.0, 余额0.0
LockedSaveThread，Wang账户：现有0.0, 存入200.0, 余额200.0

*/
//@author：Yeheya，2017年10月1日