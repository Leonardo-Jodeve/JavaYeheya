//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.4.3 �����̵߳�Э����ͬ��
//����7.8�������߳�������߳�ͬ�����У������ź�����ͬ������ʵ�֡�

public class SendThread<T> extends Thread        //�����߳���
{
    private T[] objs;                            //���Ͷ�������
    private BufferMonitor<T> buffer;             //���ͻ������ܳ�
    
    public SendThread(T[] objs, BufferMonitor<T> buffer)
    {
    	super("sender");
        this.objs = objs;
        this.buffer = buffer;
    }
    
    public void run()                            //�߳����з����������򻺳�����������
    {
        for(int i=0; i<this.objs.length; i++)    //����objs�������ж���
        {
            buffer.put(this.objs[i]);
            System.out.println(this.getClass().getName()+" put : "+this.objs[i]);
        }
        buffer.put(null);                        //����null��Ϊ�������
        System.out.println(this.getClass().getName()+" put : null");
    }
}
//@author��Yeheya��2017��8��18��