//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��4��5��
//��11.2 JSP
//����11.3��ѡ��ͶƱ��
//�� ͶƱ��Ʊ�ࡣ

package design;                                       //������

public class Ballot implements Comparable<Ballot>     //ѡƱ�࣬ʵ�ֿɱȽϽӿ�
{
    String name, time, candidate;                     //ͶƱ�ˡ�ͶƱʱ�䡢��ѡ��
    public Ballot(String name, String time, String candidate)
    {
        this.name = name;
        this.time = time;
        this.candidate = candidate;
    }
    
    public String toString()
    {
        return this.time+"��"+this.name+"ͶƱ��"+this.candidate;
    }
    
    public int compareTo(Ballot ballot)               //��ͶƱʱ��Ƚ϶����С
    {
        return this.time.compareTo(ballot.time);
    }
}
//@author��Yeheya��2018��4��7��