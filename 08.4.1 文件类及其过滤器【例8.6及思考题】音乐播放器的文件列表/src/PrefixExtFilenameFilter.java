//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月28日
//【例8.5】  带过滤器的文件列表。
//（1）约定文件名前缀和文件扩展名的文件过滤器，实现文件过滤器接口
//？？按文件名过滤，存在问题，保留文件夹，例如，“文件夹.mp3”

import java.io.*;
public class PrefixExtFilenameFilter implements FilenameFilter  //类声明实现过滤器接口
{
    private String prefix="", extension="";                //文件名前缀，文件扩展名
    
    public PrefixExtFilenameFilter(String filterstr)       //filterstr指定过滤串，dir指定目录
    {
        filterstr = filterstr.toLowerCase();               //将字符串中字母全部转换小写
        int i = filterstr.indexOf('*');                    //寻找通配符
        if(i>0)
            this.prefix = filterstr.substring(0,i);        //获得*之前的字符串
        i = filterstr.lastIndexOf('.');                    //寻找文件扩展名
        if(i>0)
        {
            this.extension = filterstr.substring(i+1);     //获得.之后的文件扩展名字符串
            if(this.extension.equals("*"))                 //识别"*.*"
                this.extension = "";
        }
    }
    public PrefixExtFilenameFilter()
    {
        this("*.*");
    }
    public boolean accept(File dir, String filename)
    {
        filename = filename.toLowerCase();         //将文件名字符串转换成小写字母
        return filename.startsWith(this.prefix) && filename.endsWith(this.extension);//文件前缀和扩展名匹配
    }
}
