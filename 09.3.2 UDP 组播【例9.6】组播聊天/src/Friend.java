//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//【例9.7】组播聊天。

public class Friend 
{
    public static void main(String args[]) throws Exception
    {
//        new ChatMulticastJFrame("蜡笔小新", "224.119.81.9", 30001);//在其他计算机上运行
        new ChatMulticastJFrame("蜡笔小新", "224.116.8.0", 20010);
    }
}
