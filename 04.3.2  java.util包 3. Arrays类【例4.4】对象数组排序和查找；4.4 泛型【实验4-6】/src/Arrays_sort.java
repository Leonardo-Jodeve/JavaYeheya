//�����ݽṹ��Java�棩����4�棩����⡷�����ߣ�Ҷ���ǣ�2014��8��22�գ�JDK 8.25
//10.1.2   Arrays������
// Arrays����Ͳ����㷨��
//8.2 ���ַ����ң���

//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��22��
//��4.3.2  java.util��
//3. Arrays��
//������������Ͳ��ң�����

import java.util.Arrays;

public class Arrays_sort 
{
    //����n������������ظ�������Χ��0��size-1������������������
    public static Integer[] randomInteger(int n, int size)
    {
        Integer[] values = new Integer[n];       //java.lang.Integer��int���͵İ�װ��
        for(int i=0; i<values.length; i++)       //�������飬����ÿ��Ԫ�ؽ�һ��
//            values[i] = new Integer((int)(Math.random()*100));
            values[i] = (int)(Math.random()*size);   //Java�Զ���int������װ��Integer���󣬸�ֵ����
                        //java.lang.Math.random()��������һ��0��1֮��double���͵������
        return values;                                     //������������
    } 
    
    //����������飬������ն��������Ԫ�أ���������������Object[]�����������ж�������
    //��ʽΪ��{,}��
    public static void print(Object[] value)
    {
        System.out.print("{");        
        if(value!=null && value.length>0 && value[0]!=null)
        {
            System.out.print(value[0]);        
            for(int i=1; i<value.length; i++)
                if(value[i]!=null)                         //value[i]�������κ�ʵ��
                    System.out.print(","+value[i].toString());//toString()��������ʱ��̬
        }
        System.out.println("}");        
    }
    
    public static void main(String args[])
    {  
        //Integer����������������Ͷ��ַ�����
        int n=9, size=100;
//        Integer[] value1 = {5,4,3,2,1};
        Integer[] value1 = randomInteger(n, size);    //���������������������
        System.out.print("��������У� ");  print(value1);//�����������       
//        java.util.Arrays.sort(value1);                //��������������������
        CompareArray.sort(value1);
        System.out.print("�������У����򣩣� ");   print(value1);   
        CompareArray.sort(value1,false);
        System.out.print("value2�������У����򣩣� ");   print(value1);   
 
        int[] keys={value1[0],value1[value1.length/2],value1[value1.length-1],-1,50,100};
        for(int i=0; i<keys.length; i++)
        {
            int find=java.util.Arrays.binarySearch(value1, keys[i]);//��������������ַ�����
            System.out.println("���ַ�����"+keys[i]+"�������"+find+"������"+
                (find>=0 && find<value1.length?"":"��")+"�ɹ�");
        }
        
        //String���������������� 
        String[] value2 = {"e","d","c","b","a"};
        System.out.print("value2�� ");  print(value2);
        java.util.Arrays.sort(value2);                //String���������������� 
        System.out.print("value2�������У����򣩣� ");   print(value2);   
        CompareArray.sort(value2,false);
        System.out.print("value2�������У����򣩣� ");   print(value2);   
    }
}
/* 
�������н�����£�
��������У� {90,21,82,51,7,53,79,86,26}
�������У����򣩣� {7,21,26,51,53,79,82,86,90}
value2�������У����򣩣� {90,86,82,79,53,51,26,21,7}
���ַ�����90�������-10�����Ҳ��ɹ�
���ַ�����53�������4�����ҳɹ�
���ַ�����7�������-1�����Ҳ��ɹ�
���ַ�����-1�������-1�����Ҳ��ɹ�
���ַ�����50�������-1�����Ҳ��ɹ�
���ַ�����100�������-10�����Ҳ��ɹ�
value2�� {e,d,c,b,a}
value2�������У����򣩣� {a,b,c,d,e}
value2�������У����򣩣� {e,d,c,b,a}


��������У� {10,93,34,86,59,20,38,77,91,72}
�������У� {10,20,34,38,59,72,77,86,91,93}
���ַ�����10�������0�����ҳɹ�
���ַ�����72�������5�����ҳɹ�
���ַ�����93�������9�����ҳɹ�
���ַ�����-1�������-1�����Ҳ��ɹ�
���ַ�����50�������-5�����Ҳ��ɹ�
���ַ�����100�������-11�����Ҳ��ɹ�
value2�� {e,d,c,b,a}
value2�������У� {a,b,c,d,e}

��������У� {9,33,70,72,76,22,27,69,57,77}
�������У� {9,22,27,33,57,69,70,72,76,77}
���ַ�����9�������0�����ҳɹ�
���ַ�����69�������5�����ҳɹ�
���ַ�����77�������9�����ҳɹ�
���ַ�����-1�������-1�����Ҳ��ɹ�
���ַ�����50�������-5�����Ҳ��ɹ�
���ַ�����100�������-11�����Ҳ��ɹ�

��������У� {5,73,59,49,78,18,92,57,76}
�������У� {5,18,49,57,59,73,76,78,92}
���ַ�����5�������0�����ҳɹ�
���ַ�����59�������4�����ҳɹ�
���ַ�����92�������8�����ҳɹ�
���ַ�����-1�������-1�����Ҳ��ɹ�
���ַ�����50�������-4�����Ҳ��ɹ�
���ַ�����100�������-10�����Ҳ��ɹ�

*/
//@author��Yeheya��2014-8-22