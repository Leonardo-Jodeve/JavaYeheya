//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��22��
//��2.4.3 ��������
//3.  �ɱ���ʽ����

public class MutableAugument 
{
/*    public static void sum(int[] value)  //��������
    {  
    }*/
    public static int sum(int... value)  //�ɱ��βΣ��ɽ�value��Ϊint[]����
    {
        int s=0;
    	for(int i:value)
            System.out.print(i);
        for(int i=0; i<value.length; i++)
        {
            System.out.print(value[i]);
        }
        return 0;
    }
    public static void main(String args[]) 
    {
        sum(2,3,4,5);
    }
}
/*
2345
2345
*/
//@author��Yeheya��2015-7-15
