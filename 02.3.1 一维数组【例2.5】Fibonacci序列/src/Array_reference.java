//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��11��
//��2.3.1  һά����

public class Array_reference
{
    static void print(int x[])                   //������Ϊ���������ø������鳤��
    {
        for(int i=0; i<x.length; i++)            //���һά����
            System.out.print(" "+x[i]);
        System.out.println();
    }

    public static void main(String args[]) 
    {
        int x[]={1,2,3,4,5};
        int y[]=null;
        System.out.println("x==null  "+(x==null));
        System.out.println("y==null  "+(y==null));

        y=x;                                     //���ø�ֵ
        System.out.println("x==y  "+(x==y));

        y[0]=100;

        print(x);
         
        int n=x.length;
        y=new int [n];
        for(int i=0;i<x.length;i++)
            y[n-i-1] = x[i];

        print(y);
    }
}

/*
�������н�����£�
x==null false
y==null true
x==y true
 100 2 3 4 5
 5 4 3 2 100
 
*/

/* 
�������
int y[];
y[0]=100;          //�����δ��ʼ��

y=100;             //�����incompatible types

*/
//@author��Yeheya��2016-6-11