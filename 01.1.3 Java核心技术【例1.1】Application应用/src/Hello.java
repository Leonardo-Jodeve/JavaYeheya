//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��6��7��
//��1.1.3 Java���ļ���
//1. ApplicationӦ��
//����1.1��  ���������в�����ApplicationӦ�ó���

//����̨Ӧ�ó��򣬹����ǽ��������в�����Ϊ�������ݣ�������������������в�������ʾHello!
public class Hello 
{    
    //main()��������ִ�еķ���������args��String�ַ������飬args���������в���
    public static void main(String[] args)
    {
        if(args.length==0)                       //��args���鳤��Ϊ0����ʾû�������в���
            System.out.println("Hello!");        //���ָ���ַ���
        else 
            for(int i=0; i<args.length; i++)     //ѭ����i��Χ��0�����鳤��-1 
                System.out.println(args[i]);     //���ÿ������Ԫ�أ������в����ַ�����
    }
}
//@author��Yeheya��2016-6-7