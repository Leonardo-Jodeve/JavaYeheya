//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年7月24日
//§7.3.3 交互线程的协作与同步
//【例7.8】  发牌。【思考题】⑥ 修改发牌线程，发送由1～52组成的随机数序列（见例12.1）。
//§12.1 集合框架  2.迭代器  
//【例12.1】  增加例7.8功能，发牌线程发送随机数序列。

import java.util.*;

public class RandomCardSendThread extends Thread      //发牌线程类，发送随机数序列
{
    private CardBuffer<Integer> buffer;               //存放牌的缓冲区管程
    private int number;                               //人数，即取牌线程数
    private java.util.List<Integer> list;             //列表接口对象，可引用数组列表或链表
    
    //构造方法，buffer指定存放牌的同步缓冲区；牌值范围是1～cardMax；number指定人数
    public RandomCardSendThread(CardBuffer<Integer> buffer, int cardMax, int number)
    {
        this.buffer = buffer;
        this.number = number;
        this.setPriority(Thread.MAX_PRIORITY);        //设置线程最高优先级10
        
        this.list = new ArrayList<Integer>(cardMax);  //list引用数组列表
//        this.list = new LinkedList<Integer>();        //list引用链表
        for(int i=1; i<=cardMax; i++)
            list.add(new Integer(i));                 //列表中添加整数对象
        java.util.Collections.shuffle(list);          //将列表的元素序列打散成随机数序列

        //使用随机数序列对象Random，第5版没写
//        java.util.Random random=new Random(25);       //随机数序列对象，参数是种子，产生相同的随机数序列
//        java.util.Collections.shuffle(list, random);  //将列表的元素序列打散成随机数序列
    }
    
    public void run()                                   //线程运行方法，发牌
    {
//        for (int i=0; i<this.list.size(); i++)        //连续发指定张数的牌
//            this.buffer.put(this.list.get(i));

        //§12.1 集合框架  2.迭代器 
        Iterator<Integer> it = this.list.iterator();  //返回一个迭代器对象，集合元素类型是Integer
        while(it.hasNext())                           //若有后继元素，使用迭代器遍历一个集合
            this.buffer.put((Integer)it.next());      //将后继元素放入缓冲区
  //或      
//        for(Integer i : this.list)                 //逐元循环，i获得list集合中的每个元素，没有删除功能
//            this.buffer.put(i);                    //将元素i放入缓冲区

        for(int i=0; i<this.number; i++)         //向number个取牌线程发送结束标记
            this.buffer.put(null);
        System.out.println(this.getClass().getName()+" 结束。");
    }
}
//@author：Yeheya，2017年8月20日，2017年10月1日，2018年1月31日，2018年8月7日