//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月19日
//§6.3.4 列表框和组合框
//【例6.4】  Person对象信息管理。

import java.util.Comparator;
import javax.swing.*;

//提供对列表框模型数据项进行排序和查找的通用算法，
public class ListModelOper 
{
    //在列表框模型中，顺序查找并选中所有与key相等的数据项。 
    //由Equalable<? super T>对象eq提供比较T对象相等方法
    public static <T> void selectAll(DefaultListModel<? super T> listmodel, 
            JList<? super T> jlist, T key, Equalable<? super T> eq)
    {
        int n=listmodel.getSize();                         //返回列表框的数据项数
        for(int i=0; i<n; i++)
//            if(eq.equals(key, (T)listmodel.elementAt(i))) //比较两个T对象是否相等
              if(eq.equals(key, (T)listmodel.getElementAt(i))) //比较两个T对象是否相等
                jlist.addSelectionInterval(i, i);          //列表框添加选中第i项（多选状态），没有触发列表框选中事件
//        jlist.setSelectedIndex(i);                 //列表框选中第i项，没有触发列表框选中事件
        //只选中最后一个
    }

    //将listmodel列表框模型数据项排序，由Comparator<? super T>接口对象c比较T对象大小。直接选择排序算法
    //listmodel引用赋值，在方法体中修改其数据项，作用于实际参数。
	//第12章，语法正确，能接受Comparator<Student>参数
    public static <T> void sort(DefaultListModel<? super T> listmodel, Comparator<? super T> c)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)        //直接选择排序算法
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
//  public static <T> void sort(DefaultComboBoxModel<? super T> listmodel, Comparator<? super T> c)//不行
  //组合框模型中没有setElementAt()方法。
    
    //以下【实验题6-13用】
    //将listmodel列表框模型数据项排序，T实现Comparable<T>接口，可比较对象大小。
    //asc取值为true（升序）、false（降序）。直接选择排序算法
    public static <T extends Comparable<? super T>>
        void sort(DefaultListModel<T> listmodel, boolean asc)
    {
        for(int i=0; i<listmodel.getSize()-1; i++)        //直接选择排序算法
        {
            int min=i;                                     //最小值或最大值
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

    //复制listmodel1列表框模型中所有数据项到listmodel2；清空listmodel2原数据项
    public static <T> void copy(DefaultListModel<T> listmodel1, DefaultListModel<T> listmodel2)
    {
        listmodel2.clear();                                //删除所有数据项
        for (int i=0; i<listmodel1.getSize(); i++)         //获得列表框的数据项数
            listmodel2.addElement(listmodel1.elementAt(i));//获得列表框所有选中数据项
    }
}
//@author：Yeheya，2018年7月19日，2018年10月6日