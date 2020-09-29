//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.8��  ���ơ�
//�Ľ���4����򣬢� ����null��Ϊ������ǣ������ͽ�����4�������߳���Ȼ�ȴ���������

public class CardSendThread extends Thread       //�����߳���
{
    private CardBuffer<Integer> buffer;          //����ƵĻ������ܳ�
    private int cardMax, number;                 //�����ֵ��������ȡ���߳�����
    
    //���췽����bufferָ������Ƶ�ͬ������������ֵ��Χ��1��cardMax��numberָ������
    public CardSendThread(CardBuffer<Integer> buffer, int cardMax, int number)
    {
        this.buffer = buffer;
        this.cardMax = cardMax;
        this.number = number;
        this.setPriority(Thread.MAX_PRIORITY);   //�����߳�������ȼ�10
    }
    
    public void run()                            //�߳����з���������
    {
        for(int i=1; i<=this.cardMax; i++)       //������ָ����������
            this.buffer.put(i);                  //�Զ���iת����Integer����
        for(int i=0; i<this.number; i++)         //��number��ȡ���̷߳��ͽ������
            this.buffer.put(null);
        System.out.println(this.getClass().getName()+" ������");
    }
}
//@author��Yeheya��2017��8��20�գ�2017��10��1�գ�2018��1��31��