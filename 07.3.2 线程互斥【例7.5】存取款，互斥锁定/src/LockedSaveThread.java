//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�������ߣ�Ҷ���ǣ�2017��8��1��
//��7.3 �߳�ͬ������
//��7.3.2  �����̵߳ľ����뻥��
//����7.5��  ��ȡ����߳̾���ʹ��ͬһ���˻���Դ���̻߳��⣬ͬ����䡣
//MyEclipse���ñ���·��������Ŀ����7.4��Account����

public class LockedSaveThread extends Thread     //���������Ĵ���߳���
{
    private Account account;                     //�˻�
    private double value;                        //�����
    
    public LockedSaveThread(Account account, double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //�߳����з��������
    {
    	synchronized(this.account)               //�����ٽ�����������ǰ�˻�����ʵ���̻߳���
        {
            double howmatch = this.account.balance;
            try
            {
                Thread.sleep(1);                 //��ʹ�ó���������Ҳ������
            }
            catch(InterruptedException ex)  {}
            this.account.put(this.value);
            System.out.println(this.getClass().getName()+"��"+this.account.name+"�˻�������"
            		+howmatch+", ����"+this.value+", ���"+this.account.balance);
        }
    }
}

class LockedFetchThread extends Thread           //����������ȡ���߳���
{
    private Account account;
    private double value;
    
    public LockedFetchThread(Account account,double value)
    {
        this.account = account;
        this.value = value;
    }
    
    public void run()                            //�߳����з�����ȡ��
    {
        synchronized(this.account)               //�����ٽ�����������ǰ�˻�����ʵ���̻߳���
        {
            double howmatch = this.account.balance;
            try
            {
                Thread.sleep(1);                 //��ʹ�ó���������Ҳ������
            }
            catch(InterruptedException ex)  {}
            System.out.println(this.getClass().getName()+"��"+this.account.name+"�˻�������"
            	    +howmatch+", ȡ��"+this.account.get(this.value)+", ���"+this.account.balance);
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
�������н�����£�        
LockedSaveThread��Wang�˻�������0.0, ����100.0, ���100.0
LockedFetchThread��Wang�˻�������100.0, ȡ��100.0, ���0.0
LockedSaveThread��Wang�˻�������0.0, ����200.0, ���200.0

*/
//@author��Yeheya��2017��10��1��