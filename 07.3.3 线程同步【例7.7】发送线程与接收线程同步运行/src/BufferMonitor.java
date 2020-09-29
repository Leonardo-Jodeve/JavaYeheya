//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.7���߳�ͨ�ţ������߳�������߳�ͬ�����С�
//���ù̷ܳ�ʽ�����ź�����ͬ�������������ͻ��ѵ�ͬ�����Ʒ���

//�������ܳ��࣬���п���put()��get()����ͬ�����У�
//���Ƶ�����Щ�����ķ����̺߳ͽ����߳�ͬ����ִ�У�����һ�£��㷨��ȷ��
public class BufferMonitor<T>
{
    private T obj;                               //����������ٽ���Դ
    private boolean isEmpty=true;                //��ʾobj�Ƿ�Ϊ�յ�ͬ���ź���
    
    //��������put()��get()�Թ�����������������ʵʩ��ͬ�����ƣ�����ͬ������
    public synchronized void put(T obj)          //�򻺳���д�����ݣ�ͬ������
    {
//        synchronized(this)                     //�뻥����书����ͬ�������ٽ����Ƿ����壬������ǰ����������
//    	if(!this.isEmpty)                        //������������7.8��Ϊʲô������������
        while(!this.isEmpty)                     //��this.obj����ʱ���ȴ����̵ܳ����������������߳�ͬ��������
        {
        	try
            {
//                System.out.println("this="+this.getClass().getName()+" wait()");//thisָ��ǰ����put()�������̶߳��󣿣�
                //�¾�ʹ���õ��߳������Լ����ó��������������������еȴ���
                //�����̱߳����Ѻ��ٴβ����������������գ���ѭ��������
                this.wait();
                //�����͡����ԣ���ѭ��������if��
                //�����͡�������if�����У�����Ϊ��ֻ��2���̣߳������Ѻ���ȴ���
                //�����͡���7.8������Ϊ����5���̣߳������Ѻ����ٵȴ���
            }
            catch(InterruptedException ex) {}
        }
        
        //�����ٽ��������ٽ���Դ��������������
        this.obj = obj;                          //��this.obj��ʱ��Ϊthis.obj��ֵ
        this.isEmpty = false;                    //����this.objΪ����״̬���ı��ź���״̬
        this.notify();                           //����һ���ȴ���ǰ�ٽ���Դ�������̣߳���������̬
    }
    
    public synchronized T get()                  //�ӻ�����������ݣ�ͬ������
    {
        while(this.isEmpty)                      //��this.obj����ʱ���̵ܳ����������������߳�ͬ��������
        {
            try
            {
                this.wait();                     //ʹ���õ��߳������Լ����ó��������������������еȴ�
            }
            catch(InterruptedException ex) {}
        }

        //�����ٽ��������ٽ���Դ��������������
        this.isEmpty = true;                     //����this.objΪ��״̬���ı��ź���״̬
        this.notify();                           //����һ���ȴ���ǰ�ٽ���Դ�������̣߳���������̬
        return this.obj;                         //����ֵ
    }
}

class LockedBuffer_ex
{
    public static void main(String args[])
    {
        BufferMonitor<Integer> buffer = new BufferMonitor<Integer>();
        Integer[] objs={1,2,3,4,5}; //,6,7,8
        (new SendThread(objs,buffer)).start();
        (new ReceiveThread(buffer)).start();
    }
}
/*
�������н�����£�
�������н�����£�Ϊʲô����
SendThread put : 1
SendThread put : 2
				ReceiveThread get : 1
				ReceiveThread get : 2
SendThread put : 3
SendThread put : 4
				ReceiveThread get : 3
				ReceiveThread get : 4
SendThread put : 5
				ReceiveThread get : 5
SendThread put : 6
				ReceiveThread get : 6
				ReceiveThread get : 7
SendThread put : 7
SendThread put : 8
				ReceiveThread get : 8
SendThread put : null
				ReceiveThread get : null
*/
//@author��Yeheya��2017��8��18�գ�2017��10��1�գ�2017��10��5��