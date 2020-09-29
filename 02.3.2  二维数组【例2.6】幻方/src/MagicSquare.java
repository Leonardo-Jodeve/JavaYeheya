//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年6月11日
//§2.3.2  二维数组
//【例2.6】  幻方。

public class MagicSquare                         //幻方（奇数阶），连续摆数法
{
    public static void main(String[] args)       //计算n阶幻方，存于mat二维数组
    {
        int n=4;                                 //n指定阶数（奇数）
        if (n%2==0)                              //若n为偶数，则不操作
        {
            System.out.println("n="+n+"，不能计算偶数阶幻方，请重新输入!");
        	return;
        }
        int[][] mat = new int[n][n];             //申请n阶二维数组
        int i=0, j=(n-1)/2;                      //i和j是下标，初始位置是第0行中间
        for(int k=1; k<=n*n; k++)               //k是自然数
        {
            mat[i][j]=k;                         //为(i,j)位置元素赋值
            //以下计算下一元素位置
            if (k % n == 0)                      //若对角线已满
                i = (i+1) % n ;                  //则下一位置向下一行
            else
            {
                i = (i-1+n) % n ;                //否则，下一位置是右上方
                j = (j+1) % n;
            }
        }

        System.out.println("n="+n);
        for(i=0; i<mat.length; i++)              //输出二维数组，遍历二维数组每行 
        {
            for(j=0;j<mat[i].length;j++)         //访问一行中每列元素
//                System.out.print(mat[i][j]+" "); //元素间用空格分隔，教材用
                System.out.print(String.format("%4d", mat[i][j]));//格式化输出，还没讲到 
            System.out.println();                //每行结束输出换行符
        }
    }
}
/*
程序运行结果如下：
n=3
8   1   6
3   5   7
4   9   2
sum=15，isMagicSquare？true

【思考题2-4】 ① 采用连续摆数法构造4、5阶幻方，结果如何？是否正确？ 
5阶幻方阵，答课件
n=5
  17  24   1   8  15
  23   5   7  14  16
   4   6  13  20  22
  10  12  19  21   3
  11  18  25   2   9


幻方 和是34，不对，答课件
n=4
   9  15   1   7            //错，和32
  14   4   6  12            //错，和36
   3   5  11  13            //错，和32
   8  10  16   2            //错，和36
sum=34，第0行之和s=32，isMagicSquare？false

n=4
  15   1   7   9
   4   6  12  14
   5  11  13   3
  10  16   2   8

*/
//@author：Yeheya，2016-6-22