//����1.1�� ApplicationӦ�ó���
//ʹ�������в�����Ϊ�������ݡ�

public class Hello
{
    public static void main(String args[])
    {
        if (args.length==0)                                //û�������в���ʱ
            System.out.println("Hello!");
        else                                               //��ʾ�����в����ַ���
            for (int i=0; i<args.length; i++)
                System.out.println(args[i]);
    }
}
