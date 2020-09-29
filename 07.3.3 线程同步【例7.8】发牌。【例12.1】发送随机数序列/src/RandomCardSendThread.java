//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��7��24��
//��7.3.3 �����̵߳�Э����ͬ��
//����7.8��  ���ơ���˼���⡿�� �޸ķ����̣߳�������1��52��ɵ���������У�����12.1����
//��12.1 ���Ͽ��  2.������  
//����12.1��  ������7.8���ܣ������̷߳�����������С�

import java.util.*;

public class RandomCardSendThread extends Thread      //�����߳��࣬�������������
{
    private CardBuffer<Integer> buffer;               //����ƵĻ������ܳ�
    private int number;                               //��������ȡ���߳���
    private java.util.List<Integer> list;             //�б�ӿڶ��󣬿����������б������
    
    //���췽����bufferָ������Ƶ�ͬ������������ֵ��Χ��1��cardMax��numberָ������
    public RandomCardSendThread(CardBuffer<Integer> buffer, int cardMax, int number)
    {
        this.buffer = buffer;
        this.number = number;
        this.setPriority(Thread.MAX_PRIORITY);        //�����߳�������ȼ�10
        
        this.list = new ArrayList<Integer>(cardMax);  //list���������б�
//        this.list = new LinkedList<Integer>();        //list��������
        for(int i=1; i<=cardMax; i++)
            list.add(new Integer(i));                 //�б��������������
        java.util.Collections.shuffle(list);          //���б��Ԫ�����д�ɢ�����������

        //ʹ����������ж���Random����5��ûд
//        java.util.Random random=new Random(25);       //��������ж��󣬲��������ӣ�������ͬ�����������
//        java.util.Collections.shuffle(list, random);  //���б��Ԫ�����д�ɢ�����������
    }
    
    public void run()                                   //�߳����з���������
    {
//        for (int i=0; i<this.list.size(); i++)        //������ָ����������
//            this.buffer.put(this.list.get(i));

        //��12.1 ���Ͽ��  2.������ 
        Iterator<Integer> it = this.list.iterator();  //����һ�����������󣬼���Ԫ��������Integer
        while(it.hasNext())                           //���к��Ԫ�أ�ʹ�õ���������һ������
            this.buffer.put((Integer)it.next());      //�����Ԫ�ط��뻺����
  //��      
//        for(Integer i : this.list)                 //��Ԫѭ����i���list�����е�ÿ��Ԫ�أ�û��ɾ������
//            this.buffer.put(i);                    //��Ԫ��i���뻺����

        for(int i=0; i<this.number; i++)         //��number��ȡ���̷߳��ͽ������
            this.buffer.put(null);
        System.out.println(this.getClass().getName()+" ������");
    }
}
//@author��Yeheya��2017��8��20�գ�2017��10��1�գ�2018��1��31�գ�2018��8��7��