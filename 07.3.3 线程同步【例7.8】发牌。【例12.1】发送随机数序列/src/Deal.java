//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.8��  ���ơ�

//deal��vt. [��Ϸ]�֣����䣻��Ӫ��ʩ��
public class Deal
{
    public Deal(int cardMax, int number)     //��ֵ��Χ��1��cardMax��numberָ������
    {
        CardBuffer<Integer> buffer = new CardBuffer<Integer>(number); 
        new CardSendThread(buffer,cardMax,number).start(); //���������̣߳�������ȼ���˳������
//        new RandomCardSendThread(buffer,cardMax,number).start(); //���������̣߳����������
        String titles[]={"��","��","��","��"};
        int x[]={400,700,400,100}, y[]={200,320,440,320};
        for(int i=0; i<number; i++)                       
            new CardReceiveJFrame(buffer,i,titles[i],x[i],y[i]);//����number��ȡ���̣߳����ȼ���5
    }
    public static void main(String arg[])
    {
        new Deal(52,4);
//        new Deal(104,4);
    }
}
/*
CardReceiveJFrame 0������
CardReceiveJFrame 1������
CardReceiveJFrame 2������
CardSendThread ������
CardReceiveJFrame 3������
 */ 
//@author��Yeheya��2017��8��20�գ�2017��10��1��