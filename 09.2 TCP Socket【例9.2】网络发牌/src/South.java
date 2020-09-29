//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年10月19日
//§9.2 TCP Socket通信
//【例9.2】 网络发牌程序。
//（2）接收客户端

import java.io.IOException;

public class South 
{
    public static void main(String args[]) throws IOException
    {
        new CardReceiveSocketJFrame("南", "127.0.0.1", 10002);
    }
}
