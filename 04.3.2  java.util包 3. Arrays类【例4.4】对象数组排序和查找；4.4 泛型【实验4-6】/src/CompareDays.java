//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��4.3.2 java.util.Comparator�Ƚ����ӿ�
//��5��δ��

import java.util.*;
public class CompareDays implements java.util.Comparator<MyDate> 
{
    public int compare(MyDate d1, MyDate d2)               //����ʵ����������
    {
        return d1.daysBetween(d2);                         //����ʵ����������
    }
}
//@author��Yeheya��2017��2��7��