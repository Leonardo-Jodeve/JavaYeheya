//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月28日
//§8.4.1 文件类及其过滤器
//【例8.5】  音乐播放器的文件列表
//（1）实现文件过滤器接口的类，以文件名作为过滤条件。

import java.io.*;

public class PrefixExtFileFilter implements FileFilter     //文件名过滤器类，实现过滤器接口
{
    private String prefix="", extension="";                //文件名前缀子串，文件扩展名
    
    //构造方法，filterstr指定过滤条件。算法获得其中的文件名前缀和扩展名分别存储在prefix和extension，
    //没有参数或"*.*"都表示所有文件
    public PrefixExtFileFilter(String filterstr)
    {
        filterstr = filterstr.toLowerCase();               //将字符串中字母全部转换小写
        int i = filterstr.indexOf('*');                    //寻找通配符'*'
        if(i>0)
            this.prefix = filterstr.substring(0,i);        //'*'之前的字符串是文件名前缀
        i = filterstr.lastIndexOf('.');                    //寻找最后的'.'
        if(i>0)
        {
            this.extension = filterstr.substring(i+1);     //'.'后的字符串是文件扩展名
            if(this.extension.equals("*"))                 //识别"*.*"
                this.extension = "";
        }
    }
    public PrefixExtFileFilter()                 //没有过滤条件，显示所有文件和子目录列表
    {
        this("");//*.*");                        //"*.*"同义
    }
    
    //过滤操作，若file文件对象的文件名前缀和扩展名与 prefix、extension匹配，则返回true，
    //接受file文件对象保留在文件列表中
    public boolean accept(File file)
    {
        if(!file.isFile())                       //判断指定file对象是否是文件
            return false;
        String filename = file.getName().toLowerCase(); //将文件名字符串转换成小写字母再比较
        return filename.startsWith(this.prefix) && filename.endsWith(this.extension);
    }
}
//@author：Yeheya，2017年8月28日，七夕