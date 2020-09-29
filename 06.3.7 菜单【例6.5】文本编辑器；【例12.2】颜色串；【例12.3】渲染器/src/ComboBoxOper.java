//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��19��
//��6.3.7   �˵����
//����6.5��  �ı��༭����

import javax.swing.*;

//�ṩ����Ͽ���������в�����ͨ���㷨 
public class ComboBoxOper 
{
    //��x���뵽��Ͽ���������У��������ظ����Ͽ������T��������T����ʵ��Comparable<? super T>�ӿڡ�
    //���ö��ַ������㷨�����ַ����������һ�ˡ�xΪnullʱ�׳��ն����쳣
    //combox���ø�ֵ���ڷ��������޸��������������ʵ�ʲ�����
    public static <T extends Comparable<? super T>> void insert(JComboBox<T> combox, T x)
    {
        int begin=0, end=combox.getItemCount()-1, mid=end; //begin��end�����������ű߽�
        while(begin<=end)                                  //�߽���Ч
        {
            mid = (begin+end)/2;                           //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
            if(x.compareTo(combox.getItemAt(mid))==0)      //�Ƚ϶����С������ȣ�
                return;                                    //����ҳɹ��������룻
            if(x.compareTo(combox.getItemAt(mid))<0)       //��x����С��
                end = mid-1;                               //����ҷ�Χ��С��ǰ��Σ�
            else
                begin = mid+1;                             //������ҷ�Χ��С������
        }
        combox.insertItemAt(x, begin);           //���Ҳ��ɹ�����x��������Ͽ�ĵ�begin��
    }
    
    //��Ͽ�ģ��
    public static <T extends Comparable<? super T>> void insert(DefaultComboBoxModel<T> combox, T x)
    {
        int begin=0, end=combox.getSize()-1, mid=end;     //begin��end�����������ű߽�
        while(begin<=end)                                 //�߽���Ч
        {
            mid = (begin+end)/2;                           //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
            if(x.compareTo(combox.getElementAt(mid))==0)   //�Ƚ϶����С������ȣ�
                return;                                    //����ҳɹ��������룻
            if(x.compareTo(combox.getElementAt(mid))<0)    //��x����С��
                end = mid-1;                               //����ҷ�Χ��С��ǰ��Σ�
            else
                begin = mid+1;                             //������ҷ�Χ��С������
        }
        combox.insertElementAt(x, begin);        //���Ҳ��ɹ�����x��������Ͽ�ĵ�begin��
    }    
}
//@author��Yeheya��2018��8��8�գ�2018��10��28��