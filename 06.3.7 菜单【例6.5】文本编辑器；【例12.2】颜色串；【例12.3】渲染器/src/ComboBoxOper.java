//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年7月19日
//§6.3.7   菜单组件
//【例6.5】  文本编辑器。

import javax.swing.*;

//提供对组合框数据项进行操作的通用算法 
public class ComboBoxOper 
{
    //将x插入到组合框的数据项中，不插入重复项；组合框数据项按T升序排序，T必须实现Comparable<? super T>接口。
    //采用二分法查找算法，二分法插入排序的一趟。x为null时抛出空对象异常
    //combox引用赋值，在方法体中修改其数据项，作用于实际参数。
    public static <T extends Comparable<? super T>> void insert(JComboBox<T> combox, T x)
    {
        int begin=0, end=combox.getItemCount()-1, mid=end; //begin、end获得数据项序号边界
        while(begin<=end)                                  //边界有效
        {
            mid = (begin+end)/2;                           //中间位置，当前比较元素位置
            if(x.compareTo(combox.getItemAt(mid))==0)      //比较对象大小，若相等，
                return;                                    //则查找成功，不插入；
            if(x.compareTo(combox.getItemAt(mid))<0)       //若x对象小，
                end = mid-1;                               //则查找范围缩小到前半段，
            else
                begin = mid+1;                             //否则查找范围缩小到后半段
        }
        combox.insertItemAt(x, begin);           //查找不成功，将x插入在组合框的第begin项
    }
    
    //组合框模型
    public static <T extends Comparable<? super T>> void insert(DefaultComboBoxModel<T> combox, T x)
    {
        int begin=0, end=combox.getSize()-1, mid=end;     //begin、end获得数据项序号边界
        while(begin<=end)                                 //边界有效
        {
            mid = (begin+end)/2;                           //中间位置，当前比较元素位置
            if(x.compareTo(combox.getElementAt(mid))==0)   //比较对象大小，若相等，
                return;                                    //则查找成功，不插入；
            if(x.compareTo(combox.getElementAt(mid))<0)    //若x对象小，
                end = mid-1;                               //则查找范围缩小到前半段，
            else
                begin = mid+1;                             //否则查找范围缩小到后半段
        }
        combox.insertElementAt(x, begin);        //查找不成功，将x插入在组合框的第begin项
    }    
}
//@author：Yeheya，2018年8月8日，2018年10月28日