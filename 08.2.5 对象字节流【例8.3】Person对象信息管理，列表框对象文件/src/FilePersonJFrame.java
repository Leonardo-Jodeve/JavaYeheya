//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2017��8��23��
//��8.2.5  �����ֽ���
//����8.3��  ���ö����ļ�����Person������Ϣ��
//�̳С���6.4��  ����Person������Ϣ��
//ʹ��JList��Person���
//MyEclipse���ñ���·���������3.2����3.3����6.4����8.5��Ŀ��

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//�̳С���6.4��Person������Ϣ�������࣬���ӵĶ�д�ļ����ܣ����򿪡��رմ���ʱִ��
public class FilePersonJFrame extends PersonJFrame implements WindowListener
{
    private String filename;                     //�ļ����ַ���
    
    public FilePersonJFrame(Person[] pers, PersonJPanel person, String filename)
    {
        super(pers, person);
        this.filename = filename;
        this.setTitle("��дPerson�����ļ�  "+filename);
        this.addWindowListener(this);            //��ܼ��������¼�
 
        if(filename.endsWith(".obj"))
        	ListModelObjectFile.readFrom(this.filename, this.listmodel);
                                       //��ȡ�����ļ����б��ģ�ͣ��������8.5��
    }
    public FilePersonJFrame(String filename)
    {
        this(null, new PersonJPanel(), filename);
    }
    
    public void windowClosing(WindowEvent event) //���ڹر��¼�������
    {
        if(filename.endsWith(".obj"))
        	ListModelObjectFile.writeTo(this.filename, this.listmodel);
                                       //���б��ģ��������д������ļ����������8.5��
    }
    public void windowOpened(WindowEvent event)     {}
    public void windowActivated(WindowEvent event)  {}
    public void windowDeactivated(WindowEvent event){}
    public void windowClosed(WindowEvent event)     {}
    public void windowIconified(WindowEvent event)  {}
    public void windowDeiconified(WindowEvent event){}
    
    public static void main(String[] arg)
    {
        Person[] pers={new Person("Li��С��",new MyDate(1992,3,15),"��","����","��ɳ"),
                       new Person("Bai����", new MyDate(1991,5,1),"Ů","����","�人"),
                       new Person("Bai����", new MyDate(1992,3,15),"��","����","����"),
                       new Person("Hua����", new MyDate(1992,10,3),"Ů","�㶫","����"),
                       new Person("Wang��ΰ", new MyDate(1990,4,3),"��","�㶫","����"),
                       new Person("Zhang����",new MyDate(1992,4,3),"Ů","����","�人"),
                       new Person("Zhu��С��",new MyDate(1991,3,12),"Ů","�㶫","����"),
                       new Person("Zhao��Ȫ",new MyDate(1993,10,1),"��","����","����")};
        new FilePersonJFrame(pers, new PersonJPanel(), "persons.obj");
//        new FilePersonJFrame(null, new PersonJPanel(), "persons.obj");
//        new FilePersonJFrame("persons.obj");
    }
}
/*
�о������µ�5��̲�ûд��
�� ����4����8.5��дreadFromText()��writeToText()�����������ַ���������󣬲����ʣ�
�����Ͼͽ�ԭ�򣬲��ᳫ����5��̲�ûд��
�� ������DefaultListModel<String>��ͨ�÷������������⡣
 */
//@author��Yeheya��2017��8��26�գ�2017��10��14�գ�2017��11��26�գ�2018��8��2��
//2018��7��27�գ�Ϊ����ʾ��Ӧ���ڹر��¼���û����Ӵ򿪡����水ť����12.6ͬ��