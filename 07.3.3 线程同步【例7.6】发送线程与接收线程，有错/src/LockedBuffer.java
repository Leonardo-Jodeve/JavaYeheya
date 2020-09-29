//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3 �߳�ͬ������
//��7.3.3 �����̵߳�Э����ͬ��
//����7.6��  �߳�ͨ�ţ������߳�������̣߳���Э���д���

//���������Ļ������࣬T��ʾ����������������ͣ���������put()��get()����Ϊͬ��������
//���Ƶ�����Щ�����ķ����̺߳ͽ����̻߳����ִ�У�
//��Ϊû��Э�����߲���һ�µ�ǰ������ˣ����ڲ�����һ�µĴ���
public class LockedBuffer<T>
{
    private T obj;                               //����������ٽ���Դ
    
    //��������put()��get()Ϊ���ⷽ�������������ٽ���������this.obj����������������
    //���뻥�⣬�ܳ�˼��
    public synchronized void put(T obj)          //�򻺳���д�����ݣ����ⷽ��
    {
        this.obj = obj; 
    }
    
    public synchronized T get()                  //�ӻ�����������ݣ����ⷽ��
    {
        return this.obj;
    }
}

class SendThread1<T> extends Thread              //�����߳���
{
    private LockedBuffer<T> buffer;              //���⻺��������ŷ��͵�����
    private T[] objs;                            //���Ͷ�������
    
    public SendThread1(T[] objs, LockedBuffer<T> buffer)//ָ��������
    {
        this.objs = objs;
        this.buffer = buffer;
    }
    
    public void run()                            //�߳����з����������򻺳�����������
    {
//    	synchronized (this)     //����϶����У��﷨��
//    	{
        for(int i=0; i<this.objs.length; i++)    //����objs���������ж���
        {
            buffer.put(this.objs[i]);
            System.out.println(this.getClass().getName()+" put : "+this.objs[i]);
        }
        buffer.put(null);              //����null��Ϊ������ǡ����޴˾䣬������߳���ѭ��
        System.out.println(this.getClass().getName()+" put : null");
//    	}
    }
}

class ReceiveThread1<T> extends Thread           //�����߳���
{
    private LockedBuffer<T> buffer;              //���⻺�������Ӵ˻�ȡ����
    
    public ReceiveThread1(LockedBuffer<T> buffer)
    {
        this.buffer = buffer ;
    }
    
    public void run()                            //�߳����з����������ӻ�������������
    {
//    	synchronized (this)     //����϶����У��﷨��
//    	{
        T obj;
        do
        {   obj=this.buffer.get();               //���ն���
            System.out.println("\t\t\t\t"+this.getClass().getName()+" get : "+obj);
        }
        while(obj!=null);                        //��null��ǽ���
//    	}
    }

    public static void main(String args[])
    {
        LockedBuffer<Integer> buffer = new LockedBuffer<Integer>();
        Integer[] objs={1,2,3,4,5};//,6,7,8}; 
        Thread sender = new SendThread1(objs,buffer); //�����߳����ȼ�Ĭ��Ϊ5
        sender.start();
        Thread receiver = new ReceiveThread1(buffer);
        receiver.setPriority(sender.getPriority()-1); //���ý����߳����ȼ�С�ڷ����߳����ȼ�
        receiver.start();
    }
}
/*
�������н�����£�
                 //���ý����߳����ȼ�С�ڷ����߳����ȼ�
SendThread1 put : 1
				ReceiveThread1 get : 1
				ReceiveThread1 get : 2
SendThread1 put : 2
				ReceiveThread1 get : 2
				ReceiveThread1 get : 3
SendThread1 put : 3
				ReceiveThread1 get : 3
				ReceiveThread1 get : 4
				ReceiveThread1 get : 4
				ReceiveThread1 get : 4
SendThread1 put : 4
				ReceiveThread1 get : 4
SendThread1 put : 5
				ReceiveThread1 get : 5
SendThread1 put : null
				ReceiveThread1 get : null



                    //û�������������ȼ�
SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
				ReceiveThread1 get : 4
SendThread1 put : 5
				ReceiveThread1 get : 5
				ReceiveThread1 get : null
SendThread1 put : null


SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
				ReceiveThread1 get : 4
				ReceiveThread1 get : 5
				ReceiveThread1 get : 5
SendThread1 put : 5
				ReceiveThread1 get : 5
				ReceiveThread1 get : null
SendThread1 put : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : null
				ReceiveThread1 get : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
SendThread1 put : null
				ReceiveThread1 get : 8
				ReceiveThread1 get : null


SendThread1 put : 1
SendThread1 put : 2
SendThread1 put : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
				ReceiveThread1 get : 7
SendThread1 put : null
				ReceiveThread1 get : null
	
	
				//���ý����߳����ȼ�
SendThread1 put : 1
				ReceiveThread1 get : 1
SendThread1 put : 2
				ReceiveThread1 get : 2
				ReceiveThread1 get : 3
				ReceiveThread1 get : 3
SendThread1 put : 3
				ReceiveThread1 get : 3
SendThread1 put : 4
SendThread1 put : 5
SendThread1 put : 6
SendThread1 put : 7
SendThread1 put : 8
SendThread1 put : null
				ReceiveThread1 get : null
				
*/
//@author��Yeheya��2017��8��2�գ�2017��10��1�գ�2017��10��5��