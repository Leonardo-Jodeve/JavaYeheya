//��Java�������ʵ�ý̳̣���4�棩�������ߣ�Ҷ����
//��Java�������ʵ�ý̳̣���5�棩���� ���ߣ�Ҷ���ǣ�2017��3��11��
//��7.2.1   Runnable�ӿ���Thread��
//����7.2��  ����/ż�������̣߳�ʵ��Runnable�ӿڡ�

//�̵߳�Ŀ������࣬ʵ��Runnable�ӿڵ��࣬�ṩ�߳����з���
public class NumberRunnable implements Runnable
{
    private int first, end;                      //���г�ֵ����ֵ
    
    public NumberRunnable(int first, int end)    //���췽����first��endָ�����г�ֵ����ֵ
    {
        this.first = first;
        this.end = end;
    }
    
    public void run()                            //�߳����з�����ʵ��Runnable�ӿ�
    {
//        System.out.println();
        for(int i=first; i<end; i+=2)
            System.out.print(i+"  ");
        System.out.println("������");
    }    
    public static void main(String[] args)
    {
        Runnable target = new NumberRunnable(1,20);       //����Ŀ������ṩ�߳���
        Thread thread_odd = new Thread(target,"�����߳�");
                             //��Ŀ�����target�����̶߳���thread_oddִ��target��run()����
        thread_odd.start();
        new Thread(new NumberRunnable(2,10),"ż���߳�").start();
    }
}
/*
�������н�����£�
1  2  4  6  8  ������
3  5  7  9  11  13  15  17  19  ������


1  3  5  7  9  11  13  15  17  19  21  23  25  27 29
2  4  6  8  10  12  14  16  18  20  22  24  26  28  30  32  34
  36  38  40  42  44  46  48  ������
31  33  35  37  39  41  43  45  47  49  ������
*/

/*
    public NumberRunnable()
    {
        this(0);
    }
2014��6��5��
�������⣺
run()�����У��޷���ʾ�߳��������ǵ�
*/
//@author��Yeheya��2017-3-11��2017��7��19��