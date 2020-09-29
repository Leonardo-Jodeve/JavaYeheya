//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.8】  发牌。
//改进第4版程序，① 发送null作为结束标记，否则发送结束后，4个接收线程仍然等待，饥饿。

public class CardSendThread extends Thread       //发牌线程类
{
    private CardBuffer<Integer> buffer;          //存放牌的缓冲区管程
    private int cardMax, number;                 //最大牌值和人数（取牌线程数）
    
    //构造方法，buffer指定存放牌的同步缓冲区；牌值范围是1～cardMax；number指定人数
    public CardSendThread(CardBuffer<Integer> buffer, int cardMax, int number)
    {
        this.buffer = buffer;
        this.cardMax = cardMax;
        this.number = number;
        this.setPriority(Thread.MAX_PRIORITY);   //设置线程最高优先级10
    }
    
    public void run()                            //线程运行方法，发牌
    {
        for(int i=1; i<=this.cardMax; i++)       //连续发指定张数的牌
            this.buffer.put(i);                  //自动将i转换成Integer对象
        for(int i=0; i<this.number; i++)         //向number个取牌线程发送结束标记
            this.buffer.put(null);
        System.out.println(this.getClass().getName()+" 结束。");
    }
}
//@author：Yeheya，2017年8月20日，2017年10月1日，2018年1月31日