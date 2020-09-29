//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��7��23��
//��2.5 �ַ���
//����2.10���ַ����������������ƣ���ת����
//��2�� ��radix���ƽ������ַ���ת��������

public class MyInteger implements Comparable<MyInteger>
{
    public static final int MIN_VALUE=0x80000000;//��Сֵ������-2147483648
    public static final int MAX_VALUE=0x7fffffff;//���ֵ������2147483647
    private final int value;                     //˽�����ձ���

    public MyInteger(int value)                  //������������ȡֵΪvalue
    {
        this.value = value;
    }    
    //���ַ���str��ʾ��ʮ��������ֵ��������������str����ת�������������׳���ֵ��ʽ�쳣
    public MyInteger(String str) throws NumberFormatException
    {
        this.value = MyInteger.parseInt(str);
    }
    
    public int intValue()                        //���ص�ǰ�����е�����ֵ
    {
        return this.value;
    }

    public String toString()                     //���ص�ǰ������ʮ�����ַ���������Object�෽��
    {
        return this.value+"";
    }
        
    //����2.10����1�� ��radix���ƽ������ַ���ת��������
    //���ؽ��ַ���strת�����������Զ�ʶ��������ʮ���ˡ�ʮ�����ƣ��ֱ��������ż�1��9��0��0x��ͷ����
    //��str����ת�������������׳���ֵ��ʽ�쳣��
    //�ر�˵������������java.lang.Integer���е�ͬ���������ܲ�ͬ��
    public static int parseInt(String str) throws NumberFormatException
    {
        if(str==null || str.isEmpty() || str.equals("0x") || str.equals("0X"))
            throw new NumberFormatException("\""+str+"\"������ת��������");
  
        char ch=str.charAt(0);                        //������ַ���ʶ����ơ���str==null�����÷���ʱJava���׳��ն����쳣        
        int value=0, i=0, sign=1, radix=10;
        if(ch>='1' && ch<='9' || ch=='+' || ch=='-')  //ʮ�����������ż�1��9��ͷ��ֻ��ʮ���ƿ�����������
        {   
            if (ch=='+' || ch=='-')                   //����������
                i++;                                  //i��ס��ǰ�ַ����
            sign = ch=='-' ? -1 : 1;                  //ʶ�������ţ���ס��������� 
        }
        else if(ch=='0')               //�˽�����0��ͷ��"0"��Ϊ�ǰ˽��ƣ������0
        {
            radix=8;
            i++;
        	//ʮ��������0x��0X��ͷ��ch=����ֵ����Ľ��ֵΪ����ֵ
            if(i<str.length() && ((ch=str.charAt(i))=='x' || ch=='X'))
            {   
                radix=16;
                i++;
            }
        }
        else
            throw new NumberFormatException("\""+str+"\"��"+radix+"������������ʶ��'"+ch+"\'�ַ�");
        
        while(i<str.length())                              //����޷�����������ֵ
        {
            ch = str.charAt(i++);
            if(ch>='0' && ch-'0'<radix)                    //��radix��10ʱ��radix����ֻҪʶ������0��radix-1
                value = value*radix+ch-'0';                //value��ס��ǰ��õ�����ֵ
            else if(radix==16 && ch>='a' && ch<='f')
                value = value*radix+ch-'a'+10;             //ʮ�����ƻ���Ҫת��'a'��'f'��ʾ������ֵ
            else if(radix==16 && ch>='A' && ch<='F')
                value = value*radix+ch-'A'+10;
            else
                throw new NumberFormatException("\""+str+"\"��"+radix+"������������ʶ��'"+ch+"\'�ַ�");
        }
        return value*sign;                                 //�����з�������ֵ
    }   
    
    //����2.10����2�� ������ת����radix���Ʋ����ַ���
    //��������value��radix���Ʋ����ַ�����������0�ĸ�λ��0������32��������λ��
    //radixȡֵΪ2��4��8��10��16������λ���㣻�����ַ�����洢����ַ�����
    //�ر�˵������������java.lang.Integer���е�ͬ������������ͬ�������ͬ��
    //�����ͬ�ж���һ�Ǹ�λ����0�����ǰˡ�ʮ����������ǰ׺��
    public static String toString(int value, int radix)
    {
        if(radix==10) 
            return value+"";                               //���ؽ�valueֵת���ɵ�ʮ�����ַ�������+�����㹦��
        if(radix==2 || radix==4 || radix==8 || radix==16)
        {
            int mask, n=0;                                 //mask���radix���Ƶ��������
            for(mask=radix-1; mask>0; mask>>>=1) 
                n++;                                       //n���mask�Ķ�����λ������2��n�η���radix
            mask=radix-1;
            char[] buffer= new char[(int)(32.0/n+0.5)];    //�洢һ��int��ʾΪradix���Ƶĸ�λ
            for(int i=buffer.length-1; i>=0; i--)         //��radixȡ�෨����������buffer�ַ����飨���򣩣���λ��0
            {
                int bit = value & mask;                    //���radix���Ƶĸ�λ����
                buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'a'-10); //��0��9��10��15ת��Ϊ'0'��'9'��'a'��'f'
                value>>>=n;                                //����nλ����λ��0����value����radix
            }
//            if(radix==2 || radix==4)                       //�����Ľ���û��ǰ׺
                return new String(buffer);                 //�������ַ����鹹����ַ���
//            if(radix==8)
//                return "0"+new String(buffer);             //�����ַ������˽���ǰ׺��"0"
//            return "0x"+new String(buffer);                //�����ַ�����ʮ������ǰ׺��"0x"
        }
        throw new IllegalArgumentException("radix����ֵ"+radix+"��ʾ�Ľ�����Ч��"); //��Ч�����쳣
    }  
    
    public boolean equals(Object obj)                      //�Ƚ����������Ƿ���ȡ�����Object�෽��
    {
        return obj instanceof Integer && this.value==((Integer)obj).intValue();
    }
    
    public int compareTo(MyInteger iobj)                   //�Ƚ���������ֵ��С������-1��0��1
    {
        return this.value<iobj.value? -1 : (this.value==iobj.value ? 0 : 1);
    }
    
    //����2.26��
    //���ؽ��ַ���s��radix����ת����������radixȡֵΪ2��4��8��10��16����s����ת�������������׳���ֵ��ʽ�쳣
    //�㷨����ʶ�����λ������λʶ��radix�������֣�ͨ��ѭ�����ݵ�չ��ʽ���������ֵ
    public static int parseInt(String s, int radix) throws NumberFormatException
    {
        if (s==null) 
            throw new NumberFormatException("null");       //�׳���ֵ��ʽ�쳣
        if (!(radix==2 || radix==4 || radix==8 || radix==10 || radix==16))
            throw new NumberFormatException("�÷�����֧��"+radix+"���ơ�");
        int value=0, i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //����λ����ס��������� 
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //����������
            if (radix==10)                                 //ֻ��ʮ������Ҫ����������
                i++;                                       //i��ס��ǰ�ַ����
            else throw new NumberFormatException(radix+"������������ʶ��"+s.charAt(0));
        
        while (i<s.length())                               //����޷�����������ֵ
        {
            char ch=s.charAt(i++);
            if (ch>='0' && ch-'0'<radix)                   //��radix��10ʱ��radix����ֻҪʶ��0��radix-1����
                value = value*radix+ch-'0';                //value��ס��ǰ��õ�����ֵ
            else if (radix==16 && ch>='a' && ch<='f')
                value = value*radix+ch-'a'+10;             //ʮ�����ƻ���Ҫת��'a'��'f'��ʾ������ֵ
            else if (radix==16 && ch>='A' && ch<='F')
                value = value*radix+ch-'A'+10;
            else throw new NumberFormatException(radix+"������������ʶ��"+ch);
        }
        return value*sign;                                 //�����з�������ֵ
    }
    
    public static int parseInt10(String s)       //����ʮ���������ַ���s��ʾ������ֵ��δ�����쳣
    {
        int x=0, i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //����λ����ס��������� 
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //��������λ
            i++;                                           //i��ס��ǰ�ַ����
        while (i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9')
            x = x*10+s.charAt(i++)-'0';                    //x��ס��ǰ��õ�����ֵ
        return x*sign;                                     //��������ֵ�����������ַ�
    }
    
    //��ϰ2.4�� �����Ķ������������ۡ�����5��ϰ����δд��
    public static String toBinaryString(int value)         //��������value�Ķ������ַ���������λ���㣬��λ��0
    {
        char[] buffer = new char[32];                      //һ��int��32��������λ
        for (int i=buffer.length-1; i>=0; i--)             //ѭ��ִ��32�Σ���λ��0
        {
            buffer[i] = (char)((value & 1)+'0');           //��ø�λ�ַ��������顣&��������ȼ�����+
            value>>>=1;                                    //value����һλ����λ��0��䣬��value����2
        }
        return new String(buffer);                         //�������ַ����鹹����ַ���
    }
    
    public static String toOctalString(int value)          //��������value�İ˽����ַ���������λ���㣬��λ��0
    {
        char[] buffer = new char[32/3+1];                  //һ��int��11���˽���λ
        for (int i=buffer.length-1; i>=0; i--)             //ѭ��ִ��11�Σ���λ��0
        {
            buffer[i] = (char)((value & 7)+'0');           //��ø�λ�ַ��������顣&��������ȼ�����+
            value>>>=3;                                    //����3λ����λ��0��䣬��value����8
        }
        return new String(buffer);                         //�������ַ����鹹����ַ���
    }

    public static String toHexString(int value)            //��������value��ʮ�������ַ���������λ���㣬��λ��0
    {
        char[] buffer = new char[32/4];                    //һ��int��8��ʮ������λ
        for (int i=buffer.length-1; i>=0; i--)             //ѭ��ִ��8�Σ���λ��0
        {
            int bit = value & 15;                          //���ʮ�����Ƶĸ�λ
            buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'a'-10);   //��0��9��10��15ת��Ϊ'0'��'9'��'a'��'f'
            value>>>=4;                                    //����4λ����λ��0��䣬��value����16
        }
        return new String(buffer);                         //�������ַ����鹹����ַ���
    }
    
    //��ʵ��6.12��   �������������ͼ����ֽ����໥ת����
    //��������value��radix�����ַ�����radixȡֵΪ2��4��8��10��16������λ����
    //sizeָ���ֽ���
    public static String toString(int value, int radix, int size)
    {
        if (radix==10) 
            return value+"";
        if (radix==2 || radix==4 || radix==8 || radix==16)
        {
            int mask, n=0;                                 //mask���radix���Ƶ��������
            for (mask=radix-1; mask>0; mask>>>=1) 
                n++;                                       //n���mask�Ķ�����λ������2��n�η���radix
            mask=radix-1;
            switch (size)
            {
                case 1: value &= 0x000000ff;
                case 2: value &= 0x0000ffff;
            }
            char[] buffer = new char[(int)(size*8.0/n+0.7)];//�洢һ��int��ʾΪradix���Ƶĸ�λ
            for (int i=buffer.length-1; i>=0; i--)         //��radixȡ�෨����������buffer�ַ����飨���򣩣���λ��0
            {
                int bit = value & mask;                    //���radix���Ƶĸ�λ����
                buffer[i]=(char)(bit<=9 ? bit+'0' : bit+'A'-10); //��0��9��10��15ת��Ϊ'0'��'9'��'A'��'F'
                value>>>=n;                                //����nλ����λ��0����value����radix
            }
            return new String(buffer);                     //�������ַ����鹹����ַ���
        }
        throw new IllegalArgumentException("radix����ֵ"+radix+"��ʾ�Ľ�����Ч��"); //��Ч�����쳣
    }  
}  

/*��4��ϰ���𣬵�5��ϰ����δд
��ϰ2.4�� �����Ķ������������ۡ�
     //�㷨�����������ۡ�
    //�� ��2ȡ�෨
    public static String toBinaryString(int value)         //����������value�Ķ������ַ�������2ȡ�෨
    {
        String str="";
        while (value>0)                                    //��2ȡ�෨����������str�ַ���������
        {   str = value%2 + str;                           //value%2+str �� (char)(value%2+'0')+str
            value /= 2;
        }
        return str;  
    }
    //�����㷨����value>0ʱִ�У���value��0ʱ������""
    
    public static String toString(int value, int radix)    //����������value��radix�����ַ�����2��radix��16
    {
        String str="";
        while (value>0)                                    //��radixȡ�෨����������str�ַ���������
        {
            int bit = value % radix;
            str = (char)(bit<=9 ? bit+'0' : bit+'A'-10) + str; 
            value /= radix;
        }
        return str;
    }
     //�����㷨����value>0ʱִ�У���value��0ʱ������""

    public static String toBinaryString(int value)         //��������value�Ķ������ַ�������2ȡ�෨
    {
        if (value==0) 
            return "0";
        String str="";
        while (value!=0)                                   //��2ȡ�෨����������str�ַ���������
        {   str = value%2 + str;
            value /= 2;
        }
        return str; 
    }
    //�����㷨���󣬸������ò����ʾ������ԭ�롣
    //���磬��value=-1ʱ��value%2=-1��str="-1"��value/2=0��ѭ��ִ��һ�Σ�����"-1"��������"-1"�Ķ������ַ���"11111111111111111111111111111111"

    public static String toBinaryString(int value)         //��������value�Ķ������ַ�������2ȡ�෨
    {
        if (value==0) 
            return "0";
        String str="";
        while (value!=0)                                   //��2ȡ�෨����������str�ַ���������
        {   str = (char)(value%2+'0') + str;
            value /= 2;
        }
        return str; 
    }
    //�����㷨����value=-1ʱ��value%2���Ϊ-1��(char)(value%2 +'0')���Ϊ'/'��'0'��ǰһ�ַ��������Բ��С�

    public static String toString(int value, int radix)    //����������value��radix�����ַ�����2��radix��16
    {
        String str="";
        while (value>0)                                    //��radixȡ�෨����������str�ַ���������
        {
            int bit = value % radix;
            str = (bit<=9 ? bit+'0' : bit+'A'-10) + str; 
            value /= radix;
        }
        return str;
    }
//���𡿽�����ת�����ַ����£�����Ὣ���ֵ�ASCII��ת�����ַ��������磬��15ת����"15"����"F"��
//str = (char)(bit<=9 ? bit+'0' : bit+'A'-10) + str; 

    //�� λ����
    //�����㷨��ȷ���㷨����λ���㡣
    public static String toBinaryString(int value)         //��������value�Ķ������ַ���������λ����
    {
        String str="";
        for (int i=0x80000000; i!=0; i>>>=1)               //�Ӹ�λ���λ��λ��ȡ��ÿ����һλ����λ��0
            str += (value & i)==0 ? '0': '1';              //��ø�λ�ַ�
        return str;
    }
    //���㷨����0ֵ����1����ˣ��㷨��������չ�ԣ����ʺ�8��16���ơ�

    public static String toBinaryString(int value)         //��������value�Ķ������ַ���������λ����
    {
        String str="";
        for (int i=0x80000000; i!=0; i>>=1)               //�Ӹ�λ���λ��λ��ȡ��ÿ����һλ����λ��0
            str += (value & i)==0 ? '0': '1';
        return str;
    }
    //��i>>=1����λ��1��䣬i!=0������Զ��������ѭ����
 
    //һ��int��������һ����32λbit��ɵ��޷���������
    //ÿ�λ�ö����Ƶĸ�λ������ת��Ϊ�����ַ�����λ�����ַ����������ӳ��ַ�����
    public static String toBinaryString(int value)         //��������value�Ķ������ַ���
    {
        String str="";
        for (int i=0; i<32; i++)                           //ѭ��ִ��32�Σ���λ��0�����32��������λ
        {
            str = (value & 1) + str;                       //����λ�����ַ����������ӳ��ַ�����&��������ȼ�����+
            value>>>=1;                                    //value����һλ����λ��0��䣬��value����2
        }
        return str;
    }
    public static String toOctalString(int value)          //����value�����İ˽����ַ������㷨����λ����
    {
        String str="";
        for (int i=0; i<32/3+1; i++)                       //һ��int��11���˽���λ��ѭ��ִ��11�Σ���λ��0
        {
            str = (value & 7) + str; 
            value>>>=3;                                    //����3λ����λ��0��䣬��value����8
        }
        return str; 
    }

    public static String toHexString(int value)            //����value������ʮ�������ַ������㷨����λ����
    {
        String str="";
        for (int i=0; i<32/4; i++)                         //һ��int��8��ʮ������λ��ѭ��ִ��8�Σ���λ��0
        {
            int bit = value & 15;                          //���ʮ�����Ƶĸ�λ
            str = (char)(bit<=9 ? bit+'0' : bit+'a'-10) + str; //��0��9��10��15ת��Ϊ'0'��'9'��'a'��'f'
            value>>>=4;                                    //����4λ����λ��0��䣬��value����16
        }
        return str; 
    }
  
    //���µݹ��㷨
    public static void binary(int n)                       //���������n�Ķ������ַ������ݹ��㷨
    {
        if (n==0)
            return;
        binary(n/2);
        System.out.print((char)(n%2+'0')); 
    }
    
    public static void hex(int n)                          //���������n��ʮ�������ַ������ݹ��㷨
    {
        if (n==0)
        	return;
        hex(n/16);
        n %= 16;
        System.out.print((char)(n<=9 ? n+'0' : n+'A'-10)); 
    }
    
    public static String toBinary(int n)                   //����������n�Ķ������ַ������ݹ��㷨
    {
        if (n>0)
            return toBinary(n/2)+(char)(n%2+'0'); 
        return "";
    }
    
    public static String toHex(int n)                      //����������n��ʮ�������ַ������ݹ��㷨
    {
        int k= n % 16;
        if (n>0)
            return toHex(n/16)+(char)(k<=9 ? k+'0' : k+'A'-10); 
        return "";
    }

 */

//@author��Yeheya��2016-7-23