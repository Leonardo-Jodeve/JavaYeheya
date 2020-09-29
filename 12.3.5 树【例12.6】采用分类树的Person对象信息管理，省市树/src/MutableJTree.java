//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��9��
//��12.3.4 ��
//����12.6��  �����ṹ��ʾ�й����С�
//��4���ɱ༭����
//δʵ��TreeModelListener??

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;

//�ɱ༭��������࣬���п�ݲ˵����ṩ�������ɾ���������ܣ���Ӧ����¼��������¼���
//�����ĺ������ʾ���������и�������ֵ������ָ���ı��ļ��С�
public class MutableJTree extends JTree implements MouseListener, ActionListener//, TreeModelListener
{
    private DefaultTreeModel treemodel;          //��ģ��
    DefaultMutableTreeNode root;                 //����㣬����private
    private String filename;                     //�ļ���
    private JPopupMenu popupmenu;                //��ݲ˵�
    private JMenuItem[] menuitems;               //�˵�������

    //����һ������filename����ָ�������������ļ�����editableָ���Ƿ�ɱ༭
//    public MutableJTree(String filename, boolean editable)
    public MutableJTree(String filename)
    {
        super();
        this.filename = filename;        
        this.treemodel = (DefaultTreeModel)this.getModel();//���Ĭ����ģ��
        this.root = null;
        this.readFrom(filename);                           //��filenameָ���ı��ļ��ж�ȡ���ṹ
//        this.setFont(PersonJFrame.font);                   //�������н�����壬��5��ûд��������ʾ��
//        this.setEditable(true);                            //�ɱ༭�����޸�����û��ס
//        this.treemodel.addTreeModelListener(this);//??
        
//        if (editable)
//        {
            this.popupmenu = new JPopupMenu();             //��ݲ˵�����
            String mitems[]={"���뺢�ӽ��", "����ǰһ���ֵܽ��", "������һ���ֵܽ��", "������", "ɾ��", "����"};
            this.menuitems = new JMenuItem[mitems.length];
            for(int i=0; i<this.menuitems.length; i++)
            {
                this.menuitems[i] = new JMenuItem(mitems[i]);
                this.popupmenu.add(this.menuitems[i]);     //����˵���
                this.menuitems[i].addActionListener(this); //Ϊ�˵���ע�ᶯ���¼�������
            }
            this.add(this.popupmenu);                      //�������ӿ�ݲ˵�
//        }
        this.addMouseListener(this);                       //�����ע������¼�������
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();//Ĭ������Ԫ��Ⱦ��
        renderer.setOpenIcon(new ImageIcon("open.gif"));   //����չ��ʱ��ͼ��
        this.setCellRenderer(renderer);                    //��������Ԫ��Ⱦ��
        
//??
//??        DefaultTreeCellEditor editor = new DefaultTreeCellEditor(this, renderer);
//      tree.setCellEditor(TreeCellEditor cellEditor)
//??        this.setCellEditor(editor);
    }

    //���·���ʵ��MouseListener����¼��ӿ�
    public void mouseClicked(MouseEvent event)             //�������ʱ����
    {
        if(event.getButton()==3)                           //��������Ҽ�
//        if (event.getModifiers()==MouseEvent.BUTTON3_MASK)    //��������Ҽ�
        {
            int row=this.getRowForLocation(event.getX(), event.getY()); //������λ�ô������к�
            if(this.root==null || this.root!=null && row!=-1)
            {
                this.setSelectionRow(row);                 //����ָ���кŵĽ��Ϊѡ��״̬
                for(int i=1; i<this.menuitems.length; i++)
                    this.menuitems[i].setEnabled(true);    //�˵�����Ч
                if(this.root==null)                        //����
                {
                    this.menuitems[3].setEnabled(false);   //�������˵�����Ч
                    this.menuitems[4].setEnabled(false);   //ɾ���˵�����Ч
                }
                if(this.root==null || row==0)              //������ѡ�и����
                {
                    this.menuitems[1].setEnabled(false);   //����ǰһ�ֵܲ˵�����Ч
                    this.menuitems[2].setEnabled(false);   //������һ�ֵܲ˵�����Ч
                }
                this.popupmenu.show(this, event.getX(), event.getY()); //����굥������ʾ��ݲ˵�
            }
        }
    }
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {} 
    public void mouseExited(MouseEvent event) {}
    
    public void actionPerformed(ActionEvent event)         //�����˵���ʱ����ִ��
    {
        if(event.getActionCommand().equals("����"))
        {                        //���������н�������ĺ������ʾ��д��ָ���ı��ļ�
            this.writeTo(this.root, this.filename);
            return;
        }
        
        DefaultMutableTreeNode selectnode=null, parent=null;
        selectnode=(DefaultMutableTreeNode)this.getLastSelectedPathComponent();//��ǰѡ�н��
        if(event.getActionCommand().equals("������"))
        {
            String s=JOptionPane.showInputDialog("����", selectnode.getUserObject().toString());
            if(s!=null)                                    //����Ի��򷵻طǿմ���ʾ��������ȷ����ť
                selectnode.setUserObject(s);               //����ѡ�н��Ķ���ֵ
            return;
        }
        
        if(event.getActionCommand().startsWith("����"))     //����"������"�˵���
        {
            String nodename=JOptionPane.showInputDialog("����");    //����Ի���û�г�ֵ
            if(nodename!=null)                             //����ȷ����ť
            {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodename);//�������
                if (this.root==null && selectnode==null)
                    this.root = selectnode = node;         //������������
                else
                {
                    if(event.getActionCommand().equals("���뺢�ӽ��"))
                        selectnode.add(node);              //������һ�����ӽ��
                    else                                   //�����ֵܽ��
                    {
                        parent = (DefaultMutableTreeNode)selectnode.getParent();
                                                           //��ø�ĸ��㣬parent!=null����Ϊ�����û������˵���
                        if(event.getActionCommand().equals("����ǰһ���ֵܽ��"))
                            parent.insert(node, parent.getIndex(selectnode));  //��������Ϊ�丸ĸ����ָ����ŵĺ��ӽ��
                        else                               //������һ���ֵܽ��
                            parent.insert(node, parent.getIndex(selectnode)+1);
                        selectnode=parent;                 //׼��չ����ǰ������ĸ�ĸ���
                    }
                }
                this.treemodel.setRoot(this.root);         //������ģ�͵ĸ���㣬ʹJTree��ʾ�޸�
                this.expandPath(new TreePath(selectnode.getPath())); //չ��ѡ�н��
            }
            return;
        }

        if(event.getActionCommand().equals("ɾ��") && JOptionPane.showConfirmDialog(null, 
                "ɾ��"+selectnode.toString()+"��㼰������?")==0) //����ȷ�϶Ի����Yes��ť
        {
            if(selectnode==root)                           //ѡ�и����
            {
                this.root = null;                           //ɾ����rootΪ������
                this.treemodel.setRoot(this.root);          //������ģ�͵ĸ���㣬ʹJTree��ʾ�޸�
            }
            else
            {
                parent=(DefaultMutableTreeNode)selectnode.getParent();//��ø�ĸ��㣬parent!=null
                selectnode.removeFromParent();             //ɾ����selectnode���Ϊ��������
                this.treemodel.setRoot(this.root);         //������ģ�͵ĸ���㣬ʹJTree��ʾ�޸�
                this.expandPath(new TreePath(parent.getPath()));  //չ����ǰɾ�����ĸ�ĸ���
            }
        }
    }
    
    //��ȡfilename�ı��ļ������������ʾ���洢�Ľ���ַ��������뵽��rootΪ��������
    //ÿ�б�ʾһ�����
    private void readFrom(String filename)
    { 
        try
        {
            BufferedReader bufr=new BufferedReader(new FileReader(filename));//�����ַ������� //�ļ��ַ�������
            String line=""; 
            while((line=bufr.readLine())!=null)  //��ȡһ���ַ���������������ʱ����null
                insert(this.root, line);         //��s���뵽��nodeΪ����������
            bufr.close();
        }
        catch(IOException ex) {}                 //���ļ������ڣ��򲻶�ȡ������
        this.treemodel.setRoot(this.root);       //����rootΪ��ǰ��ģ�͵ĸ����
    }
    
    //��str���뵽��nodeΪ���������У���s����������'\t'ȷ�����κͲ���λ�ã����ֵ������'\t'���ݹ鷽��
    private void insert(DefaultMutableTreeNode node, String str)
    {
        if(this.root==null)
            this.root = new DefaultMutableTreeNode(str);   //���������
        else
        {
            str = str.substring(1);                        //ȥ��str����һ��ǰ׺'\t'
            if(str.charAt(0)!='\t')                        //str�в�����'\t'����ʾһ�����ֵ
                node.add(new DefaultMutableTreeNode(str)); //����str��Ϊnode�����һ�����ӽ��
            else                                           //��str���뵽��node�����һ�����ӽ��Ϊ����������
                insert((DefaultMutableTreeNode)node.getLastChild(), str);//�ݹ����
        }
    }

    //����rootΪ�����������н����������ĺ������ʾ��д��filenameָ���ı��ļ�
    public void writeTo(DefaultMutableTreeNode root, String filename)
    {
        try
        {
            Writer wr = new FileWriter(filename);//�ļ��ַ������
            wr.write(preorder(root,""));         //��һ�����дӸ���ʼ������Ӧ���ַ���д���ı��ļ�
            wr.close();
        }
        catch(IOException ex) {}
    }
    
    //�ȸ����������nodeΪ����������������ĺ������ʾ����tab����ָ�����������ݹ��㷨
    private String preorder(DefaultMutableTreeNode node, String tab)   
    {
        String str="";
        if(node!=null)
        {
            str = tab + node.toString()+"\r\n";
            int n=node.getChildCount();                    //��ú��ӽ�����
            for(int i=0; i<n; i++)
                str += preorder((DefaultMutableTreeNode)node.getChildAt(i), tab+"\t"); //�ݹ����
        }
        return str;
    }  
    
    //������node�������к��ӽ��Ԫ����ӵ�combox��Ͽ�������
    public void addChild(TreeNode node, JComboBox<String> combox)
    {
        if(node!=null)
        {
            if(combox.getItemCount()>0)
                combox.removeAllItems();                   //ɾ����Ͽ��������������ItemEvent��ActionEvent�¼�
            int n = node.getChildCount();                  //��õ�ǰ���ĺ��ӽ����
            for(int i=0; i<n; i++)                         //��Ͽ���ӵ�ǰ�������к��ӽ��
                combox.addItem(node.getChildAt(i).toString());//������׸�������ʱ�����������¼�
        }
    }
  
    //�����в����״γ��ֵ�ֵΪstr��㣬���ҳɹ�ʱ���ز��ҵ��Ľ�㣬���򷵻�null
    public TreeNode search(String str)
    {
        return search(this.root, str);       
    }
    //����nodeΪ���������в����״γ��ֵ�ֵΪstr�Ľ�㣬���ز��ҵ��Ľ�㣬�ȸ�����������ݹ��㷨
    private TreeNode search(DefaultMutableTreeNode node, String str)   
    {
        if(node==null || str==null)
            return null;
        if(node.toString().equals(str))
            return node;
        
        int n=node.getChildCount();                    //��ú��ӽ�����
        for(int i=0; i<n; i++)
        {
            TreeNode find=search((DefaultMutableTreeNode)node.getChildAt(i),str);  //�ݹ����
            if(find!=null)
                return find;
        }
        return null;        
    } 
//@author��Yeheya��2018��2��9�գ���5�档2018��8��10��
    
    //���µ�5��ûд
    //����δ�ɹ�??
    //ʵ��TreeModelListener�ӿ�
    public void treeNodesChanged(TreeModelEvent event)
    {
        System.out.println("treeNodesChanged()"+event.getTreePath());
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //��ǰѡ�н��
    	System.out.print(node.getUserObject());
    }
    public void treeNodesInserted(TreeModelEvent event)
    {
        //e.getTreePath()�����Ѹ��Ľ��ĸ����
        System.out.println("treeNodesInserted()"+event.getTreePath());
        this.expandPath(event.getTreePath());  //չ��ѡ�н��
        
    }
    public void treeNodesRemoved(TreeModelEvent event)
    {
        System.out.println("treeNodesRemoved()"+event.getTreePath());        
        this.expandPath(event.getTreePath());  //չ��ѡ�н��
    }
    public void treeStructureChanged(TreeModelEvent event)
    {
        //�����в���\ɾ����㣬������ִ�и÷�����û��ִ��treeNodesInserted��treeNodesInserted

        System.out.print("treeStructureChanged()��"+event.getTreePath()+"��");
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //��ǰѡ�н��
        System.out.println(node.getUserObject());
        this.expandPath(new TreePath(event.getPath()));//new TreePath(node.getPath()));  //չ��ѡ�н��
//        this.expandPath(new TreePath(node.getPath()));  //չ��ѡ�н��
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