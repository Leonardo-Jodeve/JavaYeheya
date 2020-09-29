//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》，作者：叶核亚，2017年8月1日
//§7.3 线程同步机制
//§7.3.1   交互线程，与时间有关的错误
//【例7.4】  银行账户类，存款、取款交互线程并发执行，与时间有关的错误。
//【例7.5思考题】互斥方法

public class Account                             //账户类
{
    String name;                                 //储户姓名
    double balance;                              //账户余额
    
    public Account(String name)                  //构造方法，name指定储户姓名
    {
        this.name = name;
        this.balance = 0;
    }
    public void put(double value)    //【例7.4】存款操作，参数为存入金额。提供给存款线程调用
//    public synchronized void put(double value)   //【例7.5思考题】互斥方法
    {
        if(value>0)
            this.balance += value;               //存款操作使余额值增加
    }
    
    public double get(double value)  //【例7.4】取款操作，参数为取款金额，返回实际取到金额。提供给取款线程调用
//    public synchronized double get(double value)    //【例7.5思考题】互斥方法
    {
        if(value<=0)
            return 0;
        if(value<=this.balance)
            this.balance -= value;               //取款操作使余额值减少
        else                                     //账户余额不够所取时
        {
            value = this.balance;                //取走全部余额
            this.balance = 0;
        }
        return value;                            //返回实际取款额
    }
}

class SaveThread extends Thread                  //存款线程类
{
    private Account account;                     //账户   
    private double value;                        //存款金额
    
    public SaveThread(Account account, double value)//存入account账户value金额
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //线程运行方法，存款
    {
        double howmatch = this.account.balance;  //查看账户余额
        //……，此处执行其他操作，线程执行可能被打断；也可调用sleep(1)语句模拟线程被打断
//        try
//        {
//            Thread.sleep(1);                     //模拟线程执行被打断
//        }
//        catch(InterruptedException ex)  {}
        this.account.put(this.value);
        System.out.println(this.getClass().getName()+"，"+this.account.name+"账户，现有"
                +howmatch+", 存入"+this.value+", 余额"+this.account.balance);
    }
}


class FetchThread extends Thread                 //取款线程类
{
    private Account account;                     //账户   
    private double value;                        //取款金额
    
    public FetchThread(Account account, double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //线程运行方法，取款
    {
        double howmatch = this.account.balance;  //查看账户余额
        //……，此处执行其他操作，线程执行可能被打断；也可调用sleep(1)语句模拟线程被打断        
//        try
//        {
//        	Thread.sleep(1);                     //模拟线程执行被打断
//        }
//        catch(InterruptedException ex) {}
        System.out.println(this.getClass().getName()+"，"+this.account.name+"账户，现有"+howmatch+
            ", 取走"+this.account.get(this.value)+", 余额"+this.account.balance);
    }

    public static void main(String[] args)
    {
        Account wang = new Account("Wang");      //创建“Wang”账户
        (new SaveThread(wang,100)).start();      //对“Wang”账户存款100元
        (new SaveThread(wang,200)).start();      //对“Wang”账户存款200元
        (new FetchThread(wang,300)).start();     //从“Wang”账户取款300元
        (new SaveThread(new Account("Li"),100)).start();   //对“Li”账户存款100元
    }
}
/*
程序运行结果如下，没有声明同步方法时，数据有错。     
             //没有Thread.sleep(1);，程序设计运行结果与线程调度有关。
SaveThread，Wang账户，现有0.0, 存入100.0, 余额300.0        //有错，三者数据不符
SaveThread，Wang账户，现有100.0, 存入200.0, 余额0.0        //有错，三者数据不符
FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0
SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0

FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0
SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0
SaveThread，Wang账户，现有100.0, 存入200.0, 余额0.0
SaveThread，Wang账户，现有0.0, 存入100.0, 余额300.0

SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0
SaveThread，Wang账户，现有0.0, 存入100.0, 余额300.0
FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0
SaveThread，Wang账户，现有100.0, 存入200.0, 余额0.0

SaveThread，Wang账户，现有100.0, 存入200.0, 余额300.0
SaveThread，Wang账户，现有0.0, 存入100.0, 余额0.0
SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0
FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0

SaveThread，Wang账户，现有0.0, 存入100.0, 余额300.0
FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0
SaveThread，Wang账户，现有100.0, 存入200.0, 余额300.0
SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0

//【例7.5思考题】声明互斥方法时，数据正确。
FetchThread，Wang账户，现有300.0, 取走300.0, 余额0.0
SaveThread，Li账户，现有0.0, 存入100.0, 余额100.0
SaveThread，Wang账户，现有0.0, 存入100.0, 余额0.0
SaveThread，Wang账户，现有100.0, 存入200.0, 余额0.0

*/
//@author：Yeheya，2017年10月1日，2017年10月5日
/*    
public String getName()                      //返回账户名
{
    return name;
}

public double balance()                      //查看账户余额
{
    return balance;                      
}*/
