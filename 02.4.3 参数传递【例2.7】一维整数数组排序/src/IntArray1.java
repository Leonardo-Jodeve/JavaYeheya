//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��22��
//��2.4.3  ��������
//1.  ʵ�ʲ�������ʽ��������ԭ��
//����2.7��һά������������
//ֱ��ѡ�����򡢹鲢����

import java.util.Arrays;

public class IntArray1                           //һά��������
{
    public static int[] random(int n, int range) //����n��0��range֮��������������һά����
    {
        int[] x = new int[n];                    //��������Ĵ洢�ռ䣬�ֲ���������̬����
        while(n>0)                               //ѭ�����иı�nֵ����Ӱ��n��ʵ�ʲ���
            x[--n]=(int)(Math.random()*range);   //Math.random()����0��1���double�����
                                                 //��n<0���׳������鳤���쳣��n<0���׳������鳤���쳣NegativeArraySizeException
        //��4��
 //       for (int i=0; i<x.length; i++) 
 //           x[i]=(int)(Math.random()*range);   //random()����һ��0��1���double�����
        return x;                                //����x���õ����飬δ�ͷ�����ռ�
    }
    
    public static int[] random()                 //�������أ�����ȡĬ��ֵ
    {
        return random(10, 100);                  //����10��100���ڵ������
    }

    public static void print(final int[] x)     //���һά����Ԫ�أ�������Ϊ����
//    public static void print(int... x)        //�ɱ���ʽ�������ɽ�x��Ϊint[]����
    {
        System.out.print("{");
        if(x.length>0)
            System.out.print(x[0]);
        for(int i=0; i<x.length; i++)           //�������ͨ��length���Ի�ô洢��Ԫ��
            System.out.print(","+x[i]);
        System.out.println("}");
        
//      x = new int [4];                        //�﷨�����ܶԳ�����ֵ�������ܸı���������       
//      x[0]=0;                                 //���Ը��ĳ�������Ԫ��ֵ
    }

    //ֱ��ѡ������������Ϊ�������Ͳ��������ı�ʵ�ʲ�����Ԫ��ֵ
    public static void selectSort(int[] x)
    {
        for(int i=0; i<x.length-1; i++)         //n-1������ÿ��ѡ��ֲ���Сֵ�ٽ���
        {
            int min=i;                          //�豾�˴�������������ֵ��С��̰��ѡ�����
            for(int j=i; j<x.length; j++)       //�ڴ�x[i]��ʼ�Ĳ�������Ԫ����
                if(x[j]<x[min])                 //Ѱ����Сֵ
                    min=j;                      //min���±�����Сֵ���±�
           
            if(i!=min)                          //������Сֵ���������
            {
                int temp=x[i];
                x[i]=x[min];
                x[min]=temp;
            }
        }
    }
    
    //���ؽ�x��y�������飨���򣩹鲢�ɵ���������z��һ�ι鲢�㷨
    public static int[] merge(int[] x, int[] y)
    {
        int z[]=new int[x.length+y.length], i=0, j=0, k=0;
        while(i<x.length && j<y.length)          //��x��y��������鲢��z��
        {
            if(x[i]<y[j])                        //��Сֵ���Ƶ�z��
                z[k++]=x[i++];
            else
                z[k++]=y[j++];
        }

        while(i<x.length)                        //��x������ʣ��Ԫ�ظ��Ƶ�z��
            z[k++]=x[i++];
        while(j<y.length)                        //��y������ʣ��Ԫ�ظ��Ƶ�z��
            z[k++]=y[j++];
        return z;
    }
    
    public static void main(String[] args) 
    {
        int n1=7, range1=100;
        int[] value1=random(n1, range1), value2=random(6,100);   //���������
        System.out.print("value1:");  print(value1);
        System.out.print("value2:");  print(value2);
//      java.util.Arrays.sort(value1);         //��4.3.2  java.util�� 3.Arrays��
        selectSort(value1);
        selectSort(value2);
        System.out.print("sorted value1:");  print(value1);
        System.out.print("sorted value2:");  print(value2);
        System.out.print("merge:"); 
        print(merge(value1,value2));
    }
}
/*
�������н�����£�
value1:{14,92,38,54,78,36,97}
value2:{49,90,82,1,86,42,76,35,30,28}
sorted value1:{14,36,38,54,78,92,97}
sorted value2:{1,28,30,35,42,49,76,82,86,90}
merge:{1,14,28,30,35,36,38,42,49,54,76,78,82,86,90,92,97}

value1:{16,75,50,41,85,20,39}
value2:{73,35,62,1,91,79}
sorted value1:{16,20,39,41,50,75,85}
sorted value2:{1,35,62,73,79,91}
merge:{1,16,20,35,39,41,50,62,73,75,79,85,91}

 */

//2.  ������ʽ����
//public static int[] random(final int n, int range)//����n��0��range֮��������������һά����
//{
//    n=10;                                    //�﷨�����ܶԳ�����ֵ
//}
//@author��Yeheya��2016-10-14