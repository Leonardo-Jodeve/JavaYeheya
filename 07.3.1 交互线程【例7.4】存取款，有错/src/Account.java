//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�������ߣ�Ҷ���ǣ�2017��8��1��
//��7.3 �߳�ͬ������
//��7.3.1   �����̣߳���ʱ���йصĴ���
//����7.4��  �����˻��࣬��ȡ����̲߳���ִ�У���ʱ���йصĴ���
//����7.5˼���⡿���ⷽ��

public class Account                             //�˻���
{
    String name;                                 //��������
    double balance;                              //�˻����
    
    public Account(String name)                  //���췽����nameָ����������
    {
        this.name = name;
        this.balance = 0;
    }
    public void put(double value)    //����7.4��������������Ϊ������ṩ������̵߳���
//    public synchronized void put(double value)   //����7.5˼���⡿���ⷽ��
    {
        if(value>0)
            this.balance += value;               //������ʹ���ֵ����
    }
    
    public double get(double value)  //����7.4��ȡ�����������Ϊȡ�������ʵ��ȡ�����ṩ��ȡ���̵߳���
//    public synchronized double get(double value)    //����7.5˼���⡿���ⷽ��
    {
        if(value<=0)
            return 0;
        if(value<=this.balance)
            this.balance -= value;               //ȡ�����ʹ���ֵ����
        else                                     //�˻�������ȡʱ
        {
            value = this.balance;                //ȡ��ȫ�����
            this.balance = 0;
        }
        return value;                            //����ʵ��ȡ���
    }
}

class SaveThread extends Thread                  //����߳���
{
    private Account account;                     //�˻�   
    private double value;                        //�����
    
    public SaveThread(Account account, double value)//����account�˻�value���
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //�߳����з��������
    {
        double howmatch = this.account.balance;  //�鿴�˻����
        //�������˴�ִ�������������߳�ִ�п��ܱ���ϣ�Ҳ�ɵ���sleep(1)���ģ���̱߳����
//        try
//        {
//            Thread.sleep(1);                     //ģ���߳�ִ�б����
//        }
//        catch(InterruptedException ex)  {}
        this.account.put(this.value);
        System.out.println(this.getClass().getName()+"��"+this.account.name+"�˻�������"
                +howmatch+", ����"+this.value+", ���"+this.account.balance);
    }
}


class FetchThread extends Thread                 //ȡ���߳���
{
    private Account account;                     //�˻�   
    private double value;                        //ȡ����
    
    public FetchThread(Account account, double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //�߳����з�����ȡ��
    {
        double howmatch = this.account.balance;  //�鿴�˻����
        //�������˴�ִ�������������߳�ִ�п��ܱ���ϣ�Ҳ�ɵ���sleep(1)���ģ���̱߳����        
//        try
//        {
//        	Thread.sleep(1);                     //ģ���߳�ִ�б����
//        }
//        catch(InterruptedException ex) {}
        System.out.println(this.getClass().getName()+"��"+this.account.name+"�˻�������"+howmatch+
            ", ȡ��"+this.account.get(this.value)+", ���"+this.account.balance);
    }

    public static void main(String[] args)
    {
        Account wang = new Account("Wang");      //������Wang���˻�
        (new SaveThread(wang,100)).start();      //�ԡ�Wang���˻����100Ԫ
        (new SaveThread(wang,200)).start();      //�ԡ�Wang���˻����200Ԫ
        (new FetchThread(wang,300)).start();     //�ӡ�Wang���˻�ȡ��300Ԫ
        (new SaveThread(new Account("Li"),100)).start();   //�ԡ�Li���˻����100Ԫ
    }
}
/*
�������н�����£�û������ͬ������ʱ�������д�     
             //û��Thread.sleep(1);������������н�����̵߳����йء�
SaveThread��Wang�˻�������0.0, ����100.0, ���300.0        //�д��������ݲ���
SaveThread��Wang�˻�������100.0, ����200.0, ���0.0        //�д��������ݲ���
FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0
SaveThread��Li�˻�������0.0, ����100.0, ���100.0

FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0
SaveThread��Li�˻�������0.0, ����100.0, ���100.0
SaveThread��Wang�˻�������100.0, ����200.0, ���0.0
SaveThread��Wang�˻�������0.0, ����100.0, ���300.0

SaveThread��Li�˻�������0.0, ����100.0, ���100.0
SaveThread��Wang�˻�������0.0, ����100.0, ���300.0
FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0
SaveThread��Wang�˻�������100.0, ����200.0, ���0.0

SaveThread��Wang�˻�������100.0, ����200.0, ���300.0
SaveThread��Wang�˻�������0.0, ����100.0, ���0.0
SaveThread��Li�˻�������0.0, ����100.0, ���100.0
FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0

SaveThread��Wang�˻�������0.0, ����100.0, ���300.0
FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0
SaveThread��Wang�˻�������100.0, ����200.0, ���300.0
SaveThread��Li�˻�������0.0, ����100.0, ���100.0

//����7.5˼���⡿�������ⷽ��ʱ��������ȷ��
FetchThread��Wang�˻�������300.0, ȡ��300.0, ���0.0
SaveThread��Li�˻�������0.0, ����100.0, ���100.0
SaveThread��Wang�˻�������0.0, ����100.0, ���0.0
SaveThread��Wang�˻�������100.0, ����200.0, ���0.0

*/
//@author��Yeheya��2017��10��1�գ�2017��10��5��
/*    
public String getName()                      //�����˻���
{
    return name;
}

public double balance()                      //�鿴�˻����
{
    return balance;                      
}*/
