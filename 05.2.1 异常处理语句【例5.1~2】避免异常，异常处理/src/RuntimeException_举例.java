//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��25��
//��5.1.2 ������쳣
//3. RuntimeException�����쳣��

public class RuntimeException_����
{
    public static void main(String[] args) 
    {
        int[] a = null;
        a[0] = 0;                           //�ն����쳣NullPointerException
        
        String str = null;
        System.out.println(str.length());   //�ն����쳣NullPointerException
        
        int[] b = new int[-1];              //�����鳤���쳣NegativeArraySizeException
        
        Object obj = new Object();
//        String str = (String) obj;          //����ǿ��ת���쳣ClassCastException
    }
}
//@author��Yeheya��2016-8-25