//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年12月9日
//§9.3.1  UDP Socket点对点通信
//【例9.4】点对点聊天，采用UDP数据报通信实现。

class FrogPrince
{
    public static void main(String args[]) throws Exception
    {
        new ChatUDPJFrame("青蛙王子", 20002, "127.0.0.1", 20001);//在其他计算机上运行
    }
}
