//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��4��5��
//��11.2 JSP
//����11.3��ѡ��ͶƱ��
//�� ����design�������д�Ű���ͨ�÷������࣬��JSP�ĵ����á�
 
package design;                                  //������
import java.util.*;

public class MyCollection                        //Ϊ����������ͨ�÷���
{
    //���ؼ�������Ԫ�������ַ�����ʹ�õ������������ϣ�ͨ�÷���
    public static <T> String toString(Collection<T> coll)
    {  
        Iterator<T> it = coll.iterator();        //���ص�����������Ԫ��������T
        String str =""; 
        while(it.hasNext())                      //���к��Ԫ�أ���������
           str+=it.next()+"<br>";
        return str;
    }
}
//@author��Yeheya��2018��4��7��