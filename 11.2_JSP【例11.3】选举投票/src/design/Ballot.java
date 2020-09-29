//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年4月5日
//§11.2 JSP
//【例11.3】选举投票。
//④ 投票的票类。

package design;                                       //声明包

public class Ballot implements Comparable<Ballot>     //选票类，实现可比较接口
{
    String name, time, candidate;                     //投票人、投票时间、候选人
    public Ballot(String name, String time, String candidate)
    {
        this.name = name;
        this.time = time;
        this.candidate = candidate;
    }
    
    public String toString()
    {
        return this.time+"，"+this.name+"投票给"+this.candidate;
    }
    
    public int compareTo(Ballot ballot)               //按投票时间比较对象大小
    {
        return this.time.compareTo(ballot.time);
    }
}
//@author：Yeheya，2018年4月7日