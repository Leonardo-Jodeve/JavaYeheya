//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��2��5��
//��6.2.1 ί���¼�ģ��
//����6.2�� Unicode�ַ���ѯ����

import java.awt.event.*;

//ʵ�ִ����¼��������ӿڵ��࣬���رմ���ʱ��������������
public class WinClose implements WindowListener
{
    public void windowClosing(WindowEvent event)           //�رմ���ʱִ�е��¼�������
    {
        System.exit(0);                                    //������������
    }
    public void windowOpened(WindowEvent event)     {}     //�򿪴��ں�ִ��
    public void windowActivated(WindowEvent event)  {}     //����ں�ִ��
    public void windowDeactivated(WindowEvent event){}     //��Ϊ������ں�ִ��
    public void windowIconified(WindowEvent event)  {}     //������С����ִ��
    public void windowDeiconified(WindowEvent event){}     //���ڻָ���ִ��
    public void windowClosed(WindowEvent event)     {}     //�رմ��ں�ִ��    
}
//@author��Yeheya��2018��2��5��
/*
class WinClose implements WindowListener         //ʵ�ִ����¼��������ӿ�
{
    public void windowClosing(WindowEvent e)     //�ر�ʱ�����ڹر��¼�������
    {
      	System.out.println(e.paramString());
        System.exit(0);                          //������������
    }
    public void windowOpened(WindowEvent e)       //��ʱ 
    {
    	System.out.println(e.paramString());
    }
    public void windowActivated(WindowEvent e)    //����ʱ
    {
    	System.out.println(e.paramString());
    }
    public void windowDeactivated(WindowEvent e)   //ʧȥ����ʱ������С��
    {
    	System.out.println(e.paramString());
    }
    public void windowClosed(WindowEvent e)        //�رպ�
    {
    	System.out.println(e.paramString());
    }
    public void windowIconified(WindowEvent e)        //��С��
    {
    	System.out.println(e.paramString());
    }
    public void windowDeiconified(WindowEvent e)    //��С����ָ�
    {
    	System.out.println(e.paramString());
    }
}
/*
WINDOW_ACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_ICONIFIED,opposite=null,oldState=0,newState=0
WINDOW_DEACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_DEICONIFIED,opposite=null,oldState=0,newState=0
WINDOW_ACTIVATED,opposite=null,oldState=0,newState=0
WINDOW_CLOSING,opposite=null,oldState=0,newState=0

/*
����
class WinClose extends WindowAdapter             //�̳�������
{
    public void windowClosing(WindowEvent e)     //�������ڹرհ�ťʱ������ִ��
    {                                            //����WindowAdapter���з���
        System.exit(0);                          //������������
    }
}
*/
//@author��Yeheya��2016-9-17