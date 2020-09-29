//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年9月9日
//§6.3.2 文本显示和编辑组件及事件
//【例6.3】  中文大写金额。

public class RMB                                 //人民币类
{
    public static String toString(double x)      //将x表示的金额转换成中文大写形式
    {
        String yuan="亿仟佰拾万千百拾元角分";      //中文金额单位，汉字字符长度为1
        String digit="零壹贰叁肆伍陆柒捌玖";       //中文大写数字
        String result="";                        //存储结果
        int y=(int)(x*100);                      //浮点数扩充100倍后取整（保留两位小数）
        //以下循环，从y的低位到高位，每次获得y的个位，转换成中文金额，再连接成字符串
        for(int i=yuan.length()-1;  y>0 && i>0;  i--, y/=10)
            result = ""+digit.charAt(y % 10)+yuan.charAt(i)+result;
        return result;
    }
}
//@author：Yeheya，2016-9-18
