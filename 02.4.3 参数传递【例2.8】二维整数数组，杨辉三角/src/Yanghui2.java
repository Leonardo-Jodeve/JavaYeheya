//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月22日
//§2.4.3 参数传递
//【例2.8】 杨辉三角。
public class Yanghui2                            //杨辉三角，使用下三角形的二维整数数组
{
    public static int[][] create(final int n)    //计算n行杨辉三角，返回n行下三角形的二维数组
    {
        int[][] mat = new int [n][];             //申请第一维的存储空间。局部变量，动态数组
        for(int i=0; i<n; i++)
        {
            mat[i]= new int [i+1];               //申请第二维的存储空间，每次长度不同
            mat[i][0]=mat[i][i]=1;
            for(int j=1; j<i; j++)
                mat[i][j]=mat[i-1][j-1]+mat[i-1][j];
        }
        return mat;                              //返回二维数组引用，未释放数组空间
    }    
    public static void print(int[][] mat)        //输出二维数组，杨辉三角每行带有前导空格
    {
        for(int i=0; i<mat.length; i++)
        {   //以下String.format()格式化输出方法说明详见2.5节
            System.out.print(String.format("%"+(mat.length-i+1)*2+"c",' '));//输出前导空格
            for(int j=0; j<mat[i].length; j++)
//              System.out.print(" "+mat[i][j]);
                System.out.print(String.format("%4d",mat[i][j]));//以4位宽度输出十进制整数
            System.out.println();
        }
    }
    
    public static void main(String[] args)
    {
        Yanghui2.print(Yanghui2.create(4));
    }
}
/*
程序运行结果如下：
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
//@author：Yeheya，2016-6-22