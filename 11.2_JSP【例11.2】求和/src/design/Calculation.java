//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2018��3��25��
//��11.2 JSP
//��ʵ��11-3����ͣ�JSP�ĵ����롢���㲢��ʾ������ʽ���������з�����

package design;                                  //������

public class Calculation                         //�����࣬���а���ʵ��ͨ�ù��ܵķ���
{
    public static int sum(int n)                 //���ؼ����1��n֮��
    {   int s=0;
        for(int i=1; i<=n; i++)
            s+=i;
        return s;
    }

    public static String sumToString(int n)      //����1��n֮�ͣ����ؼ�����̺ͽ�����ʽ�ַ���
    {  
        int sum=0;
        String expstr="Sum("+n+")=";
        for(int i=1; i<n; i++)  
        {
            expstr += i+"+";
            sum+=i;
        }
        return expstr+n+"="+(sum+n)+"\n";
    }
}
//@author��Yeheya��2018��4��11��