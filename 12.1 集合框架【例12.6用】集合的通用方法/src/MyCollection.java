import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MyCollection 
{
    //��objs[]Ԫ����ӵ�coll���ϡ�    //���γ����12-13�� �绰�������á�
    public static <T> void addAll(T[]objs, Collection<T> coll)
    { 
        if(objs!=null && objs.length>0)
            for(T obj:objs)
    	        coll.add(obj);
    }
    
    //���γ����12-13�� �绰�������á�����
    //��coll�����в���x���󣬷����״γ��ֵĶ����ɱȽ���cָ���ȽϹ���
    public static <T> T search(Collection<T> coll, T x, Comparator<T> c)
    { 
        Iterator<T> it = coll.iterator();
        while(it.hasNext())                      //δ�ҵ����к��Ԫ��ʱ����
        {
            T obj = it.next();
            if(c.compare(obj,x)==0)              //�ɱȽ���cָ���ȽϹ���
                return obj;
        }
        return null;                             //δ�ҵ�ʱ����null����ʱ�Ƚ�������Ԫ��
    }
}
/*	
    public Friend2 search(Friend2 x, Comparator<Friend2> c)   //����x���󣬱Ƚ���cָ���ȽϹ���
    { 
        Friend2 find = null;
        Iterator<Friend2> it = this.iterator();
        while(find==null && it.hasNext())                //δ�ҵ����к��Ԫ��ʱ����
        {
            Friend2 f = it.next();
            if(c.compare(f,x)==0)                      //�Ƚϵ绰�����ַ���
                find = f;
        }
        return find;                                   //δ�ҵ�ʱ����null����ʱ�Ƚ�������Ԫ��
    }
    /*    public Friend search(Friend x)                    //����x���󣬱Ƚ���cָ���ȽϹ���
    { 
        Friend find = null;
        Iterator<Friend> it = this.iterator();
        while(find==null && it.hasNext())                 //δ�ҵ����к��Ԫ��ʱ����
        {
            Friend f = it.next();
            if(f.compareTo(x)==0)                      //�Ƚϵ绰�����ַ���
                find = f;
            else
                if(f.compareTo(x)>0)                      //�Ƚϵ绰�����ַ���
            	    break;
        }
        return find;                                   //δ�ҵ�ʱ����null
    }*/
	
//@author��Yeheya��2018��8��23��