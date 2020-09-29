//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月28日
//【例8.5】  带过滤器的文件列表。
//思考题：目录文件过滤器【习题解答习8.8】 

import java.io.*;
public class DirFilter implements FileFilter     //目录文件过滤器
{
    public boolean accept(File file)             //实现过滤操作，是否接受file文件对象保留在文件列表中
    {
        return file.isDirectory();
    }
}
