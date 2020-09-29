//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年10月21日
//§9.2 TCP Socket通信
//【例9.3】  多人分别点对点聊天，既是服务端也是客户端。

public class LittleBee 
{
    public static void main(String args[]) throws java.io.IOException
    {
      new ChatMultiTCPSocketJFrame(10102, "小蜜蜂");      //在其他计算机上运行
    }
}
