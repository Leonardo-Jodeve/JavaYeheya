//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��26��
//��8.2.5  �ܵ��ֽ���
//����8.4��  ʹ�ùܵ��ֽ���ʵ�ַ��Ƴ���

import java.io.*;

public class CardSendToStreamThread extends Thread //ʹ���ֽ����ķ����߳���
{
    private OutputStream[] outs;                 //�ֽ�������������飬���鳤�ȱ�ʾ����
    private int cardMax;                         //�����ֵ����ֵΪ1��cardMax
    
    //���췽�������������ߴ��ݸ�outs���ǹܵ��ֽ����������
    public CardSendToStreamThread(OutputStream[] outs, int cardMax)
    {
        this.outs = outs;
        this.cardMax = cardMax;
        this.setPriority(Thread.MAX_PRIORITY);   //�����߳�������ȼ�10
    }

    public void run()                            //�߳����з���������
    {
        DataOutputStream[] dataouts = new DataOutputStream[this.outs.length];
        for(int i=0; i<dataouts.length; i++)
            dataouts[i] = new DataOutputStream(this.outs[i]);//������������Դ�ǹܵ��ֽ���
        try 
        {
            int value=1;
            while(value<=this.cardMax)           //����1��cardMax
                for(int i=0; value<=this.cardMax && i<dataouts.length; i++)
                    dataouts[i].writeInt(value++);
            
            for(int i=0; i<dataouts.length; i++)
            {
                dataouts[i].close();             //�ر������ֽ���������׳�EOFException�쳣
                this.outs[i].close();            //�رչܵ��ֽ������
            }                                    //���޴�for�������̲߳������IOException�쳣
        }
        catch(IOException ex) {}
    }
}
//@author��Yeheya��2017��8��26�գ�2017��11��28��