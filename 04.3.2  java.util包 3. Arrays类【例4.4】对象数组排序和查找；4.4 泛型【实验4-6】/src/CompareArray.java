//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.3.2  java.util��
//3. Arrays��
//����4.4��  ���Ͷ������������Ͷ��ַ������㷨ʵ�֡�
//��4.4 ����
//��ʵ����4-6�������������Ĳ��ҵ��㷨ʵ�֡�

import java.util.Comparator;                               //�Ƚ����ӿ�

public class CompareArray                        //�����࣬Ԫ�ؿɱȽϴ�С��ʵ������Ͷ��ַ������㷨
{
    //��value�����������򣬷���T����ʵ��Comparable<? super T>�ӿڣ�
    //ascָ���������ȡֵtrue������Ĭ�ϣ���false�����򣩡�ֱ�Ӳ��������㷨
    public static <T extends java.lang.Comparable<? super T>> void sort(T[] value) 
    {   sort(value, true);                       //������������Ĭ������
    }
    public static <T extends java.lang.Comparable<? super T>> void sort(T[] value, boolean asc)
    {
//        System.out.println("ֱ�Ӳ�������");
        for(int i=1; i<value.length; i++)        //n-1��ɨ��
        {
            T x = value[i];                      //ÿ�˽�value[i]���뵽ǰ��������������
            int j=i-1;
            while(j>=0 && (asc ? x.compareTo(value[j])<0 : x.compareTo(value[j])>0))
                value[j+1] = value[j--];         //��ǰ��ϴ�/СԪ������ƶ�
            value[j+1] = x;                      //xֵ�������λ��
//            System.out.print("��"+i+"��: ");
//            print(value);                      //����print(Object)��������м�������ʡ��
        }
    }

    //���������������򣩣��ɱȽ�������c�ṩ�Ƚ�T�����С�ķ�����ð�������㷨����5��ûд
    public static <T> void sort(T[] value, java.util.Comparator<? super T> c)
    {
//        System.out.println("ð������");
        boolean exchange=true;                             //�Ƿ񽻻��ı��
        for(int i=1; i<value.length && exchange; i++)      //�н���ʱ�ٽ�����һ�ˣ����n-1��
        {
            exchange=false;                                //�ٶ�Ԫ��δ���� 
            for(int j=0; j<value.length-i; j++)            //һ�˱Ƚϡ�����
                if(c.compare(value[j], value[j+1])>0)      //����ʱ������
                {
                    T temp = value[j];
                    value[j] = value[j+1];
                    value[j+1] = temp;
                    exchange=true;                         //�н��� 
                }
//            System.out.print("��"+i+"��: ");
//            print(value);                                  //����print(Object)��������м�������ʡ��
        }
    }

    //��5��ûд
    //��value�������򣩶��������begin��end��Χ�����ַ����ҹؼ���Ϊkey��Ԫ�أ�
    //�����ҳɹ������±꣬���򷵻�-1����begin��endʡ�ԣ���ʾ0��value.length-1��
    //����T����ʵ��Comparable<? super T>�ӿڡ�
    //���ַ������㷨ֻ�ܲ���һ��Ԫ�أ����ܲ�������Ԫ�أ���
    public static <T extends Comparable<? super T>> int binarySearch(T[] value, T key)
    {
        return binarySearch(value, 0, value.length-1, key);
    }  
    public static <T extends Comparable<? super T>> int binarySearch(T[] value, int begin, int end, T key)
    {
        if(key!=null)
        {
            while(begin<=end)                               //�߽���Ч
            {   int mid = (begin+end)/2;                    //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
//                System.out.print(value[mid]+"? ");
                if(key.compareTo(value[mid])==0)            //�Ƚ϶����С������ȣ�
                    return mid;                             //����ҳɹ���
                if(key.compareTo(value[mid])<0)             //��key����С��
                    end = mid-1;                            //����ҷ�Χ��С��ǰ��Σ�
                else begin = mid+1;                         //������ҷ�Χ��С������
            }
        }
        return -1;                                          //���Ҳ��ɹ�
    }
    
    
    //��5�桾��4.4�������������Ķ��ַ������㷨ʵ�֡�
    //��value�������򣩶��������begin��end��Χ�����ַ����ҹؼ���Ϊkey��Ԫ�أ�
    //�����ҳɹ����򷵻��±꣬���򷵻�-1����begin��endʡ�ԣ���ʾ0��value.length-1��
    //�ɱȽ�������c�ṩ�Ƚ϶����С�ķ�����
    public static <T> int binarySearch(T[] value, T key, java.util.Comparator<? super T> c)
    {
        return binarySearch(value, 0, value.length-1, key, c);
    }  
    public static <T> int binarySearch(T[] value, int begin, int end, T key, java.util.Comparator<? super T> c)
    {
        if(key!=null)
            while(begin<=end)                              //�߽���Ч
            {   int mid = (begin+end)/2;                    //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
//                System.out.print(value[mid].toString()+"? ");
                if(c.compare(key,value[mid])==0)           //����Ƚϴ�С������ȣ�
                    return mid;                             //����ҳɹ���
                if(c.compare(key,value[mid])<0)            //��key����С��
                    end = mid-1;                            //����ҷ�Χ��С��ǰ���
                else begin = mid+1;                         //������ҷ�Χ��С������
            }
        return -1;                                          //���Ҳ��ɹ�
    }
    
    public static void print(Object[] value)                //�����������Ԫ�أ�ͬ��3.6
    {
        if(value!=null)
            for(int i=0; i<value.length; i++)
                if(value[i]!=null)
                    System.out.print(value[i].toString()+"  ");
        System.out.println();
    }
    public static void println(Object[] value)             //�����������Ԫ��
    {
        if(value!=null)
            for (int i=0; i<value.length; i++)
                System.out.println(value[i].toString());
        System.out.println();
    }
    
    //���value����������������key��ȵ�Ԫ�أ�˳������㷨
    public static <T extends Comparable<? super T>> void printAll(T[] value, T key)
    {
        if(value!=null && key!=null)
            for(int i=0; i<value.length && key.compareTo(value[i])>=0; i++)
                if(key.compareTo(value[i])==0)            //ִ��key����ıȽϹ���
                    System.out.println(value[i].toString());
    }    

    //���value����������������key��ȵ�Ԫ�أ�˳������㷨
    //�����������򷽷����ɱȽ�������c�ṩ�Ƚ�T�����С�ķ�����
    public static <T> void printAll(T[] value, T key, java.util.Comparator<? super T> c)
    {
        if(value!=null && key!=null)
            for(int i=0; i<value.length && c.compare(key,value[i])>=0; i++)
                if(c.compare(key,value[i])==0)            //ִ��key����ıȽϹ���
                    System.out.println(value[i].toString());
    }
    
//    public static <T extends Comparable<? super T>> int max(T[] value) 	//��������Ԫ�����ֵ���±�
//    public static <T extends Comparable<? super T>> int min(T[] value) 	//��������Ԫ����Сֵ���±�
    
    //�ж�value���������Ƿ��Ѱ����������������򷵻�true�����򷵻�false
//    public static <T> boolean isSorted(Comparable<T>[] value)         //���У���Ϊ������compareTo(Comparable<T>)��Ӧ��compareTo(T)��
    public static <T extends Comparable<? super T>> boolean isSorted(T[] value)
    {
        for(int i=0; i<value.length-1; i++)
            if(value[i].compareTo(value[i+1])>0)
                return false;
        return true;
    }

    //��Ԫ��x���뵽value�����������ǰn��Ԫ���У�����λ����xֵ��С����
    public static<T extends Comparable<? super T>> void insert(T[] value, int n, T x)
    {
        int i=0;
        while(i<n && value[i].compareTo(x)<=0)            //˳�����x�Ĳ���λ��
            i++;
        for(int j=n-1; j>=i; j--)
            value[j+1]=value[j];                           //Ԫ������ƶ�
        value[i]=x;                                        //i��x�Ĳ���λ��
    }
}    
    //��X��Y�����Ѱ���������merge()���������ߺϲ���һ���������鷵�أ�һ�ι����㷨
/*    public static <T extends java.lang.Comparable<? super T>> 
        T[] merge(T[] X, T[] Y)
    {
        int i=0, j=0, k=0;
        T[] Z=new T[X.length+Y.length];       //�﷨��T��û��Ĭ�Ͽ������췽��
        while(i<X.length && j<Y.length)                   //��X���������������й鲢��Y��
            if(X[i]<Y[j])                                 //��Сֵ���Ƶ�Y��
                Z[k++]=X[i++];
            else
                Z[k++]=Y[j++];

        while(i<X.length)                                 //��ǰһ��������ʣ��Ԫ�ظ��Ƶ�Y��
            Z[k++]=X[i++];
        while(j<Y.length)                                 //����һ��������ʣ��Ԫ�ظ��Ƶ�Y��
            Z[k++]=Y[j++];
        return Z;
    }
 
        
    //����û�õ������У����岻���
    //��table���������в���cobj//������������˳������㷨�������ҳɹ�����Ԫ���±꣬���򷵻�-1
    //˳��������������򣩶�������//�����ҳɹ�����Ԫ���±꣬���򷵻�-1
    public static int indexOf(Comparable[] table, Comparable cobj)
    {
        if(table!=null && cobj!=null)
            for(int i=0; i<table.length && cobj.compareTo(table[i])<=0; i++) //���αȽ�
                if(cobj.compareTo(table[i])==0)  //���
                    return i;                    //�ҵ�
        return -1;                               //û�ҵ�
    }    

*/