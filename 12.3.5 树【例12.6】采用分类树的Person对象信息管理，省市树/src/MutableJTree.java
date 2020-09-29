//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2018年2月9日
//§12.3.4 树
//【例12.6】  以树结构显示中国城市。
//（4）可编辑的树
//未实现TreeModelListener??

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;

//可编辑的树组件类，带有快捷菜单，提供插入结点和删除子树功能；响应鼠标事件、动作事件；
//以树的横向凹入表示法，将树中各结点对象值保存在指定文本文件中。
public class MutableJTree extends JTree implements MouseListener, ActionListener//, TreeModelListener
{
    private DefaultTreeModel treemodel;          //树模型
    DefaultMutableTreeNode root;                 //根结点，不能private
    private String filename;                     //文件名
    private JPopupMenu popupmenu;                //快捷菜单
    private JMenuItem[] menuitems;               //菜单项数组

    //构造一棵树，filename参数指定保存树结点的文件名；editable指定是否可编辑
//    public MutableJTree(String filename, boolean editable)
    public MutableJTree(String filename)
    {
        super();
        this.filename = filename;        
        this.treemodel = (DefaultTreeModel)this.getModel();//获得默认树模型
        this.root = null;
        this.readFrom(filename);                           //从filename指定文本文件中读取树结构
//        this.setFont(PersonJFrame.font);                   //设置树中结点字体，第5版没写，讲课演示用
//        this.setEditable(true);                            //可编辑，但修改内容没记住
//        this.treemodel.addTreeModelListener(this);//??
        
//        if (editable)
//        {
            this.popupmenu = new JPopupMenu();             //快捷菜单对象
            String mitems[]={"插入孩子结点", "插入前一个兄弟结点", "插入下一个兄弟结点", "重命名", "删除", "保存"};
            this.menuitems = new JMenuItem[mitems.length];
            for(int i=0; i<this.menuitems.length; i++)
            {
                this.menuitems[i] = new JMenuItem(mitems[i]);
                this.popupmenu.add(this.menuitems[i]);     //加入菜单项
                this.menuitems[i].addActionListener(this); //为菜单项注册动作事件监听器
            }
            this.add(this.popupmenu);                      //树组件添加快捷菜单
//        }
        this.addMouseListener(this);                       //树组件注册鼠标事件监听器
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();//默认树单元渲染器
        renderer.setOpenIcon(new ImageIcon("open.gif"));   //设置展开时的图标
        this.setCellRenderer(renderer);                    //设置树单元渲染器
        
//??
//??        DefaultTreeCellEditor editor = new DefaultTreeCellEditor(this, renderer);
//      tree.setCellEditor(TreeCellEditor cellEditor)
//??        this.setCellEditor(editor);
    }

    //以下方法实现MouseListener鼠标事件接口
    public void mouseClicked(MouseEvent event)             //单击鼠标时触发
    {
        if(event.getButton()==3)                           //单击鼠标右键
//        if (event.getModifiers()==MouseEvent.BUTTON3_MASK)    //单击鼠标右键
        {
            int row=this.getRowForLocation(event.getX(), event.getY()); //获得鼠标位置处结点的行号
            if(this.root==null || this.root!=null && row!=-1)
            {
                this.setSelectionRow(row);                 //设置指定行号的结点为选中状态
                for(int i=1; i<this.menuitems.length; i++)
                    this.menuitems[i].setEnabled(true);    //菜单项有效
                if(this.root==null)                        //空树
                {
                    this.menuitems[3].setEnabled(false);   //重命名菜单项无效
                    this.menuitems[4].setEnabled(false);   //删除菜单项无效
                }
                if(this.root==null || row==0)              //空树或选中根结点
                {
                    this.menuitems[1].setEnabled(false);   //插入前一兄弟菜单项无效
                    this.menuitems[2].setEnabled(false);   //插入下一兄弟菜单项无效
                }
                this.popupmenu.show(this, event.getX(), event.getY()); //在鼠标单击处显示快捷菜单
            }
        }
    }
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {} 
    public void mouseExited(MouseEvent event) {}
    
    public void actionPerformed(ActionEvent event)         //单击菜单项时触发执行
    {
        if(event.getActionCommand().equals("保存"))
        {                        //将树中所有结点以树的横向凹入表示法写入指定文本文件
            this.writeTo(this.root, this.filename);
            return;
        }
        
        DefaultMutableTreeNode selectnode=null, parent=null;
        selectnode=(DefaultMutableTreeNode)this.getLastSelectedPathComponent();//当前选中结点
        if(event.getActionCommand().equals("重命名"))
        {
            String s=JOptionPane.showInputDialog("名称", selectnode.getUserObject().toString());
            if(s!=null)                                    //输入对话框返回非空串表示单击的是确定按钮
                selectnode.setUserObject(s);               //设置选中结点的对象值
            return;
        }
        
        if(event.getActionCommand().startsWith("插入"))     //三个"插入结点"菜单项
        {
            String nodename=JOptionPane.showInputDialog("名称");    //输入对话框，没有初值
            if(nodename!=null)                             //单击确定按钮
            {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodename);//创建结点
                if (this.root==null && selectnode==null)
                    this.root = selectnode = node;         //空树插入根结点
                else
                {
                    if(event.getActionCommand().equals("插入孩子结点"))
                        selectnode.add(node);              //添加最后一个孩子结点
                    else                                   //插入兄弟结点
                    {
                        parent = (DefaultMutableTreeNode)selectnode.getParent();
                                                           //获得父母结点，parent!=null，因为根结点没有这个菜单项
                        if(event.getActionCommand().equals("插入前一个兄弟结点"))
                            parent.insert(node, parent.getIndex(selectnode));  //插入结点作为其父母结点的指定序号的孩子结点
                        else                               //插入下一个兄弟结点
                            parent.insert(node, parent.getIndex(selectnode)+1);
                        selectnode=parent;                 //准备展开当前插入结点的父母结点
                    }
                }
                this.treemodel.setRoot(this.root);         //设置树模型的根结点，使JTree显示修改
                this.expandPath(new TreePath(selectnode.getPath())); //展开选中结点
            }
            return;
        }

        if(event.getActionCommand().equals("删除") && JOptionPane.showConfirmDialog(null, 
                "删除"+selectnode.toString()+"结点及其子树?")==0) //单击确认对话框的Yes按钮
        {
            if(selectnode==root)                           //选中根结点
            {
                this.root = null;                           //删除以root为根的树
                this.treemodel.setRoot(this.root);          //设置树模型的根结点，使JTree显示修改
            }
            else
            {
                parent=(DefaultMutableTreeNode)selectnode.getParent();//获得父母结点，parent!=null
                selectnode.removeFromParent();             //删除以selectnode结点为根的子树
                this.treemodel.setRoot(this.root);         //设置树模型的根结点，使JTree显示修改
                this.expandPath(new TreePath(parent.getPath()));  //展开当前删除结点的父母结点
            }
        }
    }
    
    //读取filename文本文件以树横向凹入表示法存储的结点字符串，插入到以root为根的树中
    //每行表示一个结点
    private void readFrom(String filename)
    { 
        try
        {
            BufferedReader bufr=new BufferedReader(new FileReader(filename));//缓冲字符输入流 //文件字符输入流
            String line=""; 
            while((line=bufr.readLine())!=null)  //读取一行字符串，输入流结束时返回null
                insert(this.root, line);         //将s插入到以node为根的子树中
            bufr.close();
        }
        catch(IOException ex) {}                 //若文件不存在，则不读取，空树
        this.treemodel.setRoot(this.root);       //设置root为当前树模型的根结点
    }
    
    //将str插入到以node为根的子树中，由s包含的若干'\t'确定其层次和插入位置，结点值不包含'\t'，递归方法
    private void insert(DefaultMutableTreeNode node, String str)
    {
        if(this.root==null)
            this.root = new DefaultMutableTreeNode(str);   //创建根结点
        else
        {
            str = str.substring(1);                        //去除str串中一个前缀'\t'
            if(str.charAt(0)!='\t')                        //str中不包含'\t'，表示一个结点值
                node.add(new DefaultMutableTreeNode(str)); //插入str作为node的最后一个孩子结点
            else                                           //将str插入到以node的最后一个孩子结点为根的子树中
                insert((DefaultMutableTreeNode)node.getLastChild(), str);//递归调用
        }
    }

    //将以root为根的树中所有结点对象，以树的横向凹入表示法写入filename指定文本文件
    public void writeTo(DefaultMutableTreeNode root, String filename)
    {
        try
        {
            Writer wr = new FileWriter(filename);//文件字符输出流
            wr.write(preorder(root,""));         //将一棵树中从根开始各结点对应的字符串写入文本文件
            wr.close();
        }
        catch(IOException ex) {}
    }
    
    //先根次序遍历以node为根的子树，获得树的横向凹入表示串，tab参数指定缩进量，递归算法
    private String preorder(DefaultMutableTreeNode node, String tab)   
    {
        String str="";
        if(node!=null)
        {
            str = tab + node.toString()+"\r\n";
            int n=node.getChildCount();                    //获得孩子结点个数
            for(int i=0; i<n; i++)
                str += preorder((DefaultMutableTreeNode)node.getChildAt(i), tab+"\t"); //递归调用
        }
        return str;
    }  
    
    //将树中node结点的所有孩子结点元素添加到combox组合框数据项
    public void addChild(TreeNode node, JComboBox<String> combox)
    {
        if(node!=null)
        {
            if(combox.getItemCount()>0)
                combox.removeAllItems();                   //删除组合框所有数据项，触发ItemEvent和ActionEvent事件
            int n = node.getChildCount();                  //获得当前结点的孩子结点数
            for(int i=0; i<n; i++)                         //组合框添加当前结点的所有孩子结点
                combox.addItem(node.getChildAt(i).toString());//当添加首个数据项时，触发动作事件
        }
    }
  
    //在树中查找首次出现的值为str结点，查找成功时返回查找到的结点，否则返回null
    public TreeNode search(String str)
    {
        return search(this.root, str);       
    }
    //在以node为根的子树中查找首次出现的值为str的结点，返回查找到的结点，先根次序遍历，递归算法
    private TreeNode search(DefaultMutableTreeNode node, String str)   
    {
        if(node==null || str==null)
            return null;
        if(node.toString().equals(str))
            return node;
        
        int n=node.getChildCount();                    //获得孩子结点个数
        for(int i=0; i<n; i++)
        {
            TreeNode find=search((DefaultMutableTreeNode)node.getChildAt(i),str);  //递归调用
            if(find!=null)
                return find;
        }
        return null;        
    } 
//@author：Yeheya，2018年2月9日，第5版。2018年8月10日
    
    //以下第5版没写
    //以下未成功??
    //实现TreeModelListener接口
    public void treeNodesChanged(TreeModelEvent event)
    {
        System.out.println("treeNodesChanged()"+event.getTreePath());
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //当前选中结点
    	System.out.print(node.getUserObject());
    }
    public void treeNodesInserted(TreeModelEvent event)
    {
        //e.getTreePath()返回已更改结点的父结点
        System.out.println("treeNodesInserted()"+event.getTreePath());
        this.expandPath(event.getTreePath());  //展开选中结点
        
    }
    public void treeNodesRemoved(TreeModelEvent event)
    {
        System.out.println("treeNodesRemoved()"+event.getTreePath());        
        this.expandPath(event.getTreePath());  //展开选中结点
    }
    public void treeStructureChanged(TreeModelEvent event)
    {
        //在树中插入\删除结点，都触发执行该方法，没有执行treeNodesInserted，treeNodesInserted

        System.out.print("treeStructureChanged()，"+event.getTreePath()+"，");
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //当前选中结点
        System.out.println(node.getUserObject());
        this.expandPath(new TreePath(event.getPath()));//new TreePath(node.getPath()));  //展开选中结点
//        this.expandPath(new TreePath(node.getPath()));  //展开选中结点
    }
}
/*
//        tree.setCellEditor(TreeCellEditor cellEditor)
//        tree.setDragEnabled(true);
 //    void updateDragEnabled(boolean dragEnabled) 
//        {
//        tree.setDragEnabled(dragEnabled);
//    }
}
*/