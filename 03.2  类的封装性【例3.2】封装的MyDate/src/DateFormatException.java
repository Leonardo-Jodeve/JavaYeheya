//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��26��
//��5.2.3 �����쳣��
//����5.2�� �������ڸ�ʽ�쳣�ࡣ

//���ڸ�ʽ�쳣�࣬�̳���Ч�����쳣�ֻ࣬��Ҫ�������췽���������������쳣����
//public class DateFormatException extends Exception              //���벶��
public class DateFormatException extends IllegalArgumentException//�����߿��Բ�������JVM����
{
    public DateFormatException(String str)
    {
        super(str);
    }
    public DateFormatException()
    {
        super();
    }
}
//@author��Yeheya��2016-8-8��2017��2��7��