//��Java�������ʵ�ý̳̣���4�棩�������ߣ�Ҷ����
//��Java�������ʵ�ý̳̣���5�棩���� ���ߣ�Ҷ���ǣ�2017��3��11��
//��7.2.1   Runnable�ӿ���Thread��
//����7.1��  ����/ż�������̣߳��̳�Thread�ࡣ

public class NumberThread extends Thread         //�߳��࣬�������/ż�����У��̳�Thread��
{
    private int first,n;                         //���г�ֵ��Ԫ�ظ���

    //���췽����nameָ���߳�����first��nָ�����г�ֵ�ͺ�Ԫ�ظ���
    public NumberThread(String name, int first, int n)
    {
        super(name);                             //�����̶߳���ʱָ���߳���
        this.first = first;
        this.n = n;
    }
    public void run()                            //�߳����з���������Thread��run()
    {
        long time1=System.currentTimeMillis();   //��ʼʱ��
        System.out.print("\n"+this.getName()+"��ʼʱ��="+time1+"��");//����߳�����ʱ��
        for(int i=0; i<n; i++)                   //ѭ�����n������ֵ������Ϊ2                   
            System.out.print((first+2*i)+"  ");
        long time2=System.currentTimeMillis();   //����ʱ��
        System.out.println(this.getName()+"����ʱ��="+time2+"����ʱ"+(time2-time1)+"���롣");
    }
    public static void main(String args[])
    {
    	Thread thread = Thread.currentThread();            //��ǰ�����߳���main
    	System.out.println("currentThread="+thread.getName());     //�����ǰ�̶߳���
        System.out.println("main Priority="+thread.getPriority()); //�����ǰ�̶߳�������ȼ�
        Thread thread_odd = new NumberThread("�����߳�",1,20);  //�����̶߳��󣬸��������������ʵ��
        Thread thread_even = new NumberThread("ż���߳�",2,10);
//        thread_odd.setPriority(MIN_PRIORITY);//MAX_PRIORITY);//10);              //�������ȼ�Ϊ���
        thread_odd.start();                                      //�����̶߳���
        thread_even.start();
        System.out.println("activeCount="+Thread.activeCount()); //�����ǰ��߳���
//        System.out.println("thread_odd Priority="+thread_odd.getPriority()); //�����ǰ�̶߳�������ȼ�
    }
}
/*
���������н�����£�
                             //("�����߳�",1,20);    ("ż���߳�",2,10);
currentThread=main
main Priority=5
activeCount=3
�����߳̿�ʼʱ��=1500512324364��
ż���߳̿�ʼʱ��=1500512324364��2  4  6  8  10  12  14  16  18  20  ż���߳̽���ʱ��=1500512324364����ʱ0���롣
1  3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  �����߳̽���ʱ��=1500512324365����ʱ1���롣
//@author��Yeheya��2017��7��20��
 
currentThread=main
main Priority=5
�����߳̿�ʼʱ��=1509173100210��activeCount=3
ż���߳̿�ʼʱ��=1509173100211��1  2  3  5  7  4  6  8  9  11  10  12  14  13  15  16  18  20  17  19  21  ż���߳̽���ʱ��=1509173100212����ʱ1���롣
23  25  27  29  31  33  35  37  39  �����߳̽���ʱ��=1509173100213����ʱ3���롣

currentThread=main
main Priority=5
�����߳̿�ʼʱ��=1509173191006��1  3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  �����߳̽���ʱ��=1509173191006����ʱ0���롣
activeCount=2
ż���߳̿�ʼʱ��=1509173191007��2  4  6  8  10  12  14  16  18  20  ż���߳̽���ʱ��=1509173191007����ʱ0���롣

//@author��Yeheya��2017��10��28��

                                       //n=3��Ԫ��
currentThread=main
main Priority=5
�����߳�:  activeCount=3
ż���߳�:  2  4  6  ż���߳̽�����
1  3  5  �����߳̽�����

                                       //n=5��Ԫ��
currentThread=main
main Priority=5
�����߳�:  activeCount=3
1  3  5  7  9  �����߳̽�����
ż���߳�:  2  4  6  8  10  ż���߳̽�����

                                       //n=10��Ԫ��
currentThread=main
main Priority=5
�����߳�:  activeCount=3
1  3  5  7  9  11  13  15  17  19  �����߳̽�����
ż���߳�:  2  4  6  8  10  12  14  16  18  20  ż���߳̽�����

                                       //n=10��Ԫ��
currentThread=main
main Priority=5
�����߳�:  activeCount=3
ż���߳�:  1  3  5  7  9  11  13  15  17  2  19  4  �����߳̽�����
6  8  10  12  14  16  18  20  ż���߳̽�����

                                       //n=20��Ԫ��
currentThread=main
main Priority=5
�����߳�:  1  activeCount=3
3  5  7  9  11  13  15  17  19  21  23  25  27  29  31  
ż���߳�:  33  35  37  39  2  �����߳̽�����
4  6  8  10  12  14  16  18  20  22  24  26  28  30  32  34  36  38  40  ż���߳̽�����
//@author��Yeheya��2017-3-12

thread_odd.setPriority(10);      //������ȼ�
�ٴ�����
thread_odd.setPriority(1);       //������ȼ�
 
[lnftjghm]
*/
//@author��Yeheya��2017-3-11��2017��7��19��