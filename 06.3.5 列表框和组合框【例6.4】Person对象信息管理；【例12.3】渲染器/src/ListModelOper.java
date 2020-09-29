//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��7��19��
//��6.3.4 �б�����Ͽ�
//����6.4��  Person������Ϣ����

import java.util.Comparator;
import javax.swing.*;

//�ṩ���б��ģ���������������Ͳ��ҵ�ͨ���㷨��
public class ListModelOper 
{
    //���б��ģ���У�˳����Ҳ�ѡ��������key��ȵ������ 
    //��Equalable<? super T>����eq�ṩ�Ƚ�T������ȷ���
    public static <T> void selectAll(DefaultListModel<? super T> listmodel, 
            JList<? super T> jlist, T key, Equalable<? super T> eq)
    {
        int n=listmodel.getSize();                         //�����б�����������
        for(int i=0; i<n; i++)
//            if(eq.equals(key, (T)listmodel.elementAt(i))) //�Ƚ�����T�����Ƿ����
              if(eq.equals(key, (T)listmodel.getElementAt(i))) //�Ƚ�����T�����Ƿ����
                jlist.addSelectionInterval(i, i);          //�б�����ѡ�е�i���ѡ״̬����û�д����б��ѡ���¼�
//        jlist.setSelectedIndex(i);                 //�б��ѡ�е�i�û�д����б��ѡ���¼�
        //ֻѡ�����һ��
    }

    //��listmodel�б��ģ��������������Comparator<? super T>�ӿڶ���c�Ƚ�T�����С��ֱ��ѡ�������㷨
    //listmodel���ø�ֵ���ڷ��������޸��������������ʵ�ʲ�����
	//��12�£��﷨��ȷ���ܽ���Comparator<Student>����
    public static <T> void sort(DefaultListModel<? super T> listmodel, Comparator<? super T> c)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)        //ֱ��ѡ�������㷨
        {
        	int min=i; 
            for(int j=i+1; j<listmodel.getSize(); j++)
                if(c.compare((T)listmodel.getElementAt(j), (T)listmodel.getElementAt(min))<0)
                    min = j; 
            
            if(min!=i) 
            {   
                T temp = (T)listmodel.getElementAt(i);
                listmodel.setElementAt((T)listmodel.getElementAt(min), i);
                listmodel.setElementAt(temp, min);
            }
        }        
    }
//  public static <T> void sort(DefaultComboBoxModel<? super T> listmodel, Comparator<? super T> c)//����
  //��Ͽ�ģ����û��setElementAt()������
    
    //���¡�ʵ����6-13�á�
    //��listmodel�б��ģ������������Tʵ��Comparable<T>�ӿڣ��ɱȽ϶����С��
    //ascȡֵΪtrue�����򣩡�false�����򣩡�ֱ��ѡ�������㷨
    public static <T extends Comparable<? super T>>
        void sort(DefaultListModel<T> listmodel, boolean asc)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)        //ֱ��ѡ�������㷨
        {
            int min=i;                                     //��Сֵ�����ֵ
            for(int j=i+1; j<listmodel.getSize(); j++)
            {
                int comp=((T)listmodel.elementAt(j)).compareTo((T)listmodel.elementAt(min));
                if(asc ? comp<0 : comp>0)
                    min = j;
            }
            if(min!=i) 
            {   
                T temp = (T)listmodel.elementAt(i);
                listmodel.setElementAt((T)listmodel.elementAt(min), i);
                listmodel.setElementAt(temp, min);
            }
        }        
    }

    //����listmodel1�б��ģ�������������listmodel2�����listmodel2ԭ������
    public static <T> void copy(DefaultListModel<T> listmodel1, DefaultListModel<T> listmodel2)
    {
        listmodel2.clear();                                //ɾ������������
        for (int i=0; i<listmodel1.getSize(); i++)         //����б�����������
            listmodel2.addElement(listmodel1.elementAt(i));//����б������ѡ��������
    }
}
//@author��Yeheya��2018��7��19�գ�2018��10��6��