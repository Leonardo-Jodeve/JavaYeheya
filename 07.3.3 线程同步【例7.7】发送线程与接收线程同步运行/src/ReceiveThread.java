//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.7�������߳�������߳�ͬ�����У������ź�����ͬ������ʵ�֡�

public class ReceiveThread<T> extends Thread     //�����߳���
{
    private BufferMonitor<T> buffer;             //���ջ������ܳ�
    
    public ReceiveThread(BufferMonitor<T> buffer)//����ָ���������ܳ�
    {
        this.buffer = buffer;
    }
    
    public void run()                            //�߳����з����������ӻ�������������
    {
        T obj;
        do
        {
            obj=this.buffer.get();               //���ն�����null��ǽ���
            System.out.println("\t\t\t\t"+this.getClass().getName()+" get : "+obj);
        } while(obj!=null);                      //���ն�����null��ǽ���
    }
}
//@author��Yeheya��2017��8��18�գ�2017��10��1��