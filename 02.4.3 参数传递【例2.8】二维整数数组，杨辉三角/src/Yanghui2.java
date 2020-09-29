//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��22��
//��2.4.3 ��������
//����2.8�� ������ǡ�
public class Yanghui2                            //������ǣ�ʹ���������εĶ�ά��������
{
    public static int[][] create(final int n)    //����n��������ǣ�����n���������εĶ�ά����
    {
        int[][] mat = new int [n][];             //�����һά�Ĵ洢�ռ䡣�ֲ���������̬����
        for(int i=0; i<n; i++)
        {
            mat[i]= new int [i+1];               //����ڶ�ά�Ĵ洢�ռ䣬ÿ�γ��Ȳ�ͬ
            mat[i][0]=mat[i][i]=1;
            for(int j=1; j<i; j++)
                mat[i][j]=mat[i-1][j-1]+mat[i-1][j];
        }
        return mat;                              //���ض�ά�������ã�δ�ͷ�����ռ�
    }    
    public static void print(int[][] mat)        //�����ά���飬�������ÿ�д���ǰ���ո�
    {
        for(int i=0; i<mat.length; i++)
        {   //����String.format()��ʽ���������˵�����2.5��
            System.out.print(String.format("%"+(mat.length-i+1)*2+"c",' '));//���ǰ���ո�
            for(int j=0; j<mat[i].length; j++)
//              System.out.print(" "+mat[i][j]);
                System.out.print(String.format("%4d",mat[i][j]));//��4λ������ʮ��������
            System.out.println();
        }
    }
    
    public static void main(String[] args)
    {
        Yanghui2.print(Yanghui2.create(4));
    }
}
/*
�������н�����£�
   1
   1   1
   1   2   1
   1   3   3   1
   1   4   6   4   1
   1   5  10  10   5   1
   1   6  15  20  15   6   1
   1   7  21  35  35  21   7   1
   1   8  28  56  70  56  28   8   1
   1   9  36  84 126 126  84  36   9   1

                         1
                       1   1
                     1   2   1
                   1   3   3   1
                 1   4   6   4   1
               1   5  10  10   5   1
             1   6  15  20  15   6   1
           1   7  21  35  35  21   7   1
         1   8  28  56  70  56  28   8   1
       1   9  36  84 126 126  84  36   9   1

*/
//@author��Yeheya��2016-6-22