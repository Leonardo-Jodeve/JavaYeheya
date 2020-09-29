//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��26��
//��8.2.5  �ܵ��ֽ���
//����8.4��  ʹ�ùܵ��ֽ���ʵ�ַ��Ƴ���
//����ͬ����7.8�����Ƴ��򣬡�7.3.3 �߳�ͬ����û������ָ����7.8���ı���·����

import java.io.*;

public class PipedDeal                                     //ʹ�ùܵ�������
{
    //���췽������ֵ��Χ��1��cardMax��numberָ������
    public PipedDeal(int cardMax, int number) throws IOException
    {
        //���´����ܵ��ֽ�����/�����������飬
        PipedInputStream[] pipedins = new PipedInputStream[number];
        PipedOutputStream[] pipedouts=new PipedOutputStream[number];
        for(int i=0; i<number; i++)
        {
            pipedins[i] = new PipedInputStream();          //�����ܵ�����������
            pipedouts[i]= new PipedOutputStream(pipedins[i]);//�����ܵ���������󲢽�������
        }
        new CardSendToStreamThread(pipedouts, cardMax).start();//���������������̣߳�cardMax����
        String[] titles={"��","��","��","��"};
        int x[]={300,550,300,50}, y[]={200,320,440,320};
        for(int i=0; i<number; i++)                        //����������number��ȡ���߳�
            new CardReceiveFromStreamJFrame(pipedins[i],titles[i],x[i],y[i]);
    }
    public static void main(String[] args) throws IOException
    {
        new PipedDeal(52,4);
    }
}
//@author��Yeheya��2017��8��26��