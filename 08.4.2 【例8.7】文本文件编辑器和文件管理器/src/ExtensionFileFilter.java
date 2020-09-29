//《Java程序设计实用教程（第5版）》 作者：叶核亚，2017年8月29日
//§8.3   字符流
//§8.4   文件操作
//§8.4.1 文件类及其过滤器
//§8.4.2 文件选择对话框组件
//【例8.6】  文本文件编辑器和文件管理器。
//（2）JFileChooser使用的文件过滤器
//【例12.4】  多文档界面的文本文件编辑器。 

import java.io.File;

//文件过滤器类，提供以文件扩展名为过滤条件，给JFileChooser组件使用；
//继承JFileChooser的文件过滤器抽象类
public class ExtensionFileFilter extends javax.swing.filechooser.FileFilter
{
    private String description, extension;                 //文件类型描述，文件扩展名
    
    public ExtensionFileFilter(String description, String extension)
    {
        this.description = description;
        this.extension = extension.toLowerCase();
    }

    public boolean accept(File file)                       //过滤操作，file指定待过滤文件
    {
        return file.getName().toLowerCase().endsWith(this.extension);   //文件扩展名匹配
    }

    public String getDescription()                         //文件类型描述字符串
    {
        return this.description;
    }
}
//@author：Yeheya，2017年8月29日，2017年10月14日