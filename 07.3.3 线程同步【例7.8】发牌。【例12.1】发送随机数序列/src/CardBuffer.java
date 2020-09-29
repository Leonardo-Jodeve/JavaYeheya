//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.8�����ơ� 

//����ƵĻ������ܳ��࣬һ�����գ���������߳�Լ�����մ���TԼ��ÿ���Ƶ���������
public class CardBuffer<T>
{
    private T obj;                               //����������ٽ���Դ
    private boolean isEmpty=true;                //��ʾobj�Ƿ�յ�ͬ���ź���
    private int number;                          //�������������߳���
    private int order=0;                         //Լ��ȡ���̴߳�����ź���
    
    public CardBuffer(int number)
    {
        this.number = number;
    }
    
    //��������put()��get()��this.obj������������������ʵʩ��ͬ�����ƣ���Ϊͬ������
    public synchronized void put(T obj)          //�򻺳���д�����ݣ�ͬ������
    {
//        if(!this.isEmpty)                      //���д�Ϊʲô�������͡�����7.7
        while(!this.isEmpty)                     //��this.obj����ʱ
        {
            try
            {
                this.wait();           //ʹ���õ��߳������Լ����ó��������������������еȴ���
            }
            catch(InterruptedException ex) {}
        }
        //�����ٽ��������ٽ���Դ��������������
        this.obj = obj;                          //��this.obj��ʱ��this.obj���ֵ
        this.isEmpty=false;                      //����this.objΪ����״̬
        this.notifyAll();                        //�������еȴ���ǰ�ٽ���Դ�������߳�
    }
    
    public synchronized T get(int order)         //ȡֵ��orderָ��ȡ���̴߳���ͬ��������
    {
//        while(isEmpty)                          //ȡ��ȡ���߳�֮��Ĵ����ϵ��������������
        while(this.isEmpty || this.order!=order) //��this.obj�ջ�ȡ�ƴ��򲻷�ʱ
        {
            try
            {
                this.wait();           //ʹ���õ��߳������Լ����ó��������������������еȴ���
            }
            catch(InterruptedException ex) {}
        }
        //�����ٽ��������ٽ���Դ��������������
        this.isEmpty=true;                       //����this.objΪ��״̬
        this.order=(this.order+1) % this.number; //��1ʹȡ�ƴ�����ת
        this.notifyAll();                        //�������еȴ���ǰ�ٽ���Դ�������߳�
        return this.obj; 
    }
}
//@author��Yeheya��2017��8��20�գ�2017��10��1�գ�2017��10��7��