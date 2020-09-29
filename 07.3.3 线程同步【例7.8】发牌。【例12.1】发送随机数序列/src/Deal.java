//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.8】  发牌。

//deal：vt. [牌戏]分；分配；经营；施予
public class Deal
{
    public Deal(int cardMax, int number)     //牌值范围是1～cardMax；number指定人数
    {
        CardBuffer<Integer> buffer = new CardBuffer<Integer>(number); 
        new CardSendThread(buffer,cardMax,number).start(); //启动发牌线程，最高优先级，顺序序列
//        new RandomCardSendThread(buffer,cardMax,number).start(); //启动发牌线程，随机数序列
        String titles[]={"北","东","南","西"};
        int x[]={400,700,400,100}, y[]={200,320,440,320};
        for(int i=0; i<number; i++)                       
            new CardReceiveJFrame(buffer,i,titles[i],x[i],y[i]);//启动number个取牌线程，优先级是5
    }
    public static void main(String arg[])
    {
        new Deal(52,4);
//        new Deal(104,4);
    }
}
/*
CardReceiveJFrame 0结束。
CardReceiveJFrame 1结束。
CardReceiveJFrame 2结束。
CardSendThread 结束。
CardReceiveJFrame 3结束。
 */ 
//@author：Yeheya，2017年8月20日，2017年10月1日