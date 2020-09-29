//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��8��25�գ�2017��7��17��
//��5.2 �쳣�����ʩ
//����5.1��  ����Ӧ������쳣������
//����5.2��  ��ֵ��ʽ�쳣����
//MyEclipse���ñ���·��������Ŀ����2.7��IntArray1����

public class IntArray5                           //һά�������飬��5��
{
    //����5.1���Ľ�
    public static double average(int[] value)    //����������Ԫ��ƽ��ֵ
    {
        if(value.length==0)                      //�������Ϊ0����
            return 0.0;
        double sum=0.0;
        for(int i=0; i<value.length; i++)        //���������±�Խ���쳣
            sum += value[i];  
        return sum/value.length;
    }
	
    //��˼����5-1�� ʵ�����·�����  
    //��value��������Ԫ�صļ�Ȩƽ��ֵ��weight����Ԫ��ָ��value������ӦԪ�ص�Ȩֵ��
    //��valueԪ�ظ���Ϊ0������0.0��
    //��weightΪnull��Ĭ��ȨֵΪ1������value����Ԫ�ص�ƽ��ֵ��
    //��weight����Ԫ�ظ���������Ĭ��ȨֵΪ1����weight����Ԫ�ظ������������Բ���
    public static double weightedAverage(int[] value, int[] weight) 
    {
        if(value.length==0)                      //��value==null��value.length�׳��ն����쳣
            return 0.0;
//        double sum=0.0;
        int i=0, sum=0;
        String exp="", exps="";                  //��¼���ʽ
        if(weight!=null && weight.length!=0)     //����weightΪnullʱ�Ŀն����쳣
        {
            for(i=0; i<value.length && i<weight.length; i++) 
            {
                sum += value[i]*weight[i]; 
                exp += (i==0?"":"+")+value[i]+"*"+weight[i];
             //   exps+= (i==0?"":"+")+value[i]*weight[i];
            }
        }
        for(; i<value.length; i++)               //���������±�Խ���쳣
        {
            sum += value[i];  
            exp += (i==0?"":"+")+value[i]+"*1";
            //exps+= (i==0?"":"+")+value[i];
        }
        System.out.print("("+exp+")/"+value.length+" = ");
        return (double)sum/value.length;
    }

    //����5.2����ֵ��ʽ�쳣����
    //�����ַ�������str�а�ʮ����ת���ɵ��������������Բ���ת�����������ַ���
    //��6�¡���8.2û�е��ã���8.2˼�������京�塣
    public static int[] getInts(String[] str)
    {
        if(str==null || str.length==0)
            return null;
        int x[]=new int[str.length],  n=0,i=0;   //x���鳤��ͬstr�����ܳ���
        while(i<str.length)                      //�˴���ʹ��for��ʡ������finally�Ӿ�
        {
            try
            {
                x[n]=Integer.parseInt(str[i]);   //��ʮ����ת��������������ת��ʱ�׳��쳣
                n++;                             //n��¼��������
            }
            catch(NumberFormatException ex)      //���񲢴���parseInt()�׳�����ֵ��ʽ�쳣
            {
                System.out.println("\""+str[i]+"\"�ַ������ܰ�ʮ����ת��Ϊ������"+ex.toString());    //�����쳣�������쳣��Ϣ
            }
            catch(Exception ex)                  //���񲢴������������쳣
            {
//            	System.out.println(ex.getMessage());       //ֻ���쳣��Ϣ
            	System.out.println(ex.toString());         //�����쳣�����������쳣���ַ���
//              ex.printStackTrace();	         //�����쳣�������쳣��Ϣ���쳣������Ϣ
                ex.printStackTrace();            //��ʾ�쳣ջ������Ϣ��ͬJava������׳���
            }
            finally                              //���ִ���Ӿ䣬��ʡ��
            {
                i++;
            }
        }
        if(n==x.length)                          //��x�����������ʱ
            return x;
        int[] y = new int[n];                    //��x���鲻��ʱ���������飬ȥ��������洢��Ԫ
        System.arraycopy(x,0,y,0,n);             //��x�����0��ʼn��Ԫ�ظ��Ƶ�y�����0��ʼ��
        return y;                                //���ذ���������������������
    }

    //��˼����5-2��ʮ�����ƣ���Ȩƽ����ʵ����Ŀ�����⣬��ʡƪ����
    //����ַ�������str�е��������Ȱ�ʮ������ʽת��������������ת��ʱ��
    //�ٰ�ʮ��������ʽת����������try���Ƕ�ס�
    public static int[] toIntArray(String[] str)
    {
        if(str==null || str.length==0)
            return null;
        int x[]=new int[str.length],  n=0,i=0;   //x���鳤��ͬstr�����ܳ���
        while(i<str.length)                      //�˴���ʹ��for��ʡ������finally�Ӿ�
        {
            try
            {
                x[n]=Integer.parseInt(str[i]);   //��ʮ����ת��������������ת��ʱ�׳��쳣
                n++;                             //n��¼��������
            }
            catch(NumberFormatException ex)      //���񲢴���parseInt()�׳�����ֵ��ʽ�쳣
            {
                try                              //try���Ƕ��
                {
                    x[n++]=Integer.parseInt(str[i],16);   //��ʮ������ת��������
                }
                catch(NumberFormatException nfex)//�쳣������nfex�����ex��ͬ
                {
//                    System.out.println(str[i]+"�ַ�������ת��Ϊ������"+nfex.getClass().getName());//��ʾ�쳣����
//                    System.out.println(str[i]+"�ַ�������ת��Ϊ������"+nfex.getMessage());//���쳣��Ϣ
                    System.out.println("\""+str[i]+"\"�ַ�������ת��Ϊ������"+nfex.toString());    //�����쳣�������쳣��Ϣ
                }
            }
            catch(Exception ex)                  //���񲢴������������쳣
            {
                ex.printStackTrace();            //��ʾ�쳣ջ������Ϣ��ͬJava������׳���
            }
            finally                              //���ִ���Ӿ䣬��ʡ��
            {
                i++;
            }
        }
        if (n==x.length)                         //��x�����������ʱ
            return x;
        int[] y = new int[n];                    //��x���鲻��ʱ���������飬ȥ��������洢��Ԫ
        System.arraycopy(x,0,y,0,n);             //��x�����0��ʼn��Ԫ�ظ��Ƶ�y�����0��ʼ��
        return y;                                //���ذ���������������������
    }

    public static void print(int[] value)        //�������Ԫ��
    {
        for(int i=0; i<value.length; i++)        //��value==null��value.length�׳��ն����쳣
            System.out.print(value[i]+"  ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        String valuestr[]={"10","20","30","40","50","x","y","60","70"};
        int value[] = getInts(valuestr);         //�����ַ�������valuestr�е���������
        System.out.print("value[]���飺");
        IntArray1.print(value);                  //�������Ԫ�أ����������2.7��{,}

/*
        int value[] = toIntArray(valuestr);           //������Ϊargs������������в���
        System.out.print("value[]���飺");
        IntArray1.print(value);                            //�������Ԫ�أ����������2.7��{,}
        String weightstr[]={"a","b","c","d","e","f","g"};
        int weight[] = toIntArray(weightstr);
        System.out.print("weight[]���飺");
        IntArray1.print(weight);
//        System.out.println("weightedAverage(value,wight)="+weightedAverage(value,weight));
        System.out.print("weightedAverage(value,wight)=");
        System.out.println(+weightedAverage(value,weight));
//        System.out.println("weightedAverage(value,null)="+weightedAverage(value,null));
        System.out.print("weightedAverage(value,null)=");
        System.out.println(weightedAverage(value,null));

        int x[] = {1,2,3,4};
        int y[] = {};                                      //y!=null��y.length==0
        System.out.println("average(x,y)="+average(x,y));
        */
    }
    
    //���µ�5��̲�ûд����5.1���Ľ�
    //����������Ԫ�ص�ƽ��ֵ��������һ������Ϊ�գ���Ԫ�ظ���Ϊ0ʱ���������ơ�
    public static double average(int[] value1, int[] value2)
    {
        int n = (value1==null ? 0 : value1.length) + (value2==null ? 0 : value2.length); 
        if(n==0)
            return 0.0;
        return (average(value1)*value1.length + average(value2)*value2.length)/n;
    }    
}
/*
�������н�����£�
"x"�ַ������ܰ�ʮ����ת��Ϊ������java.lang.NumberFormatException: For input string: "x"
"y"�ַ������ܰ�ʮ����ת��Ϊ������java.lang.NumberFormatException: For input string: "y"
value[]���飺{10,20,30,40,50,60,70}

g�ַ�������ת��Ϊ������java.lang.NumberFormatException: For input string: "g"
weight[]���飺{10,11,12,13,14,15} 
weightedAverage(value,wight)=410.0
weightedAverage(value,null)=40.0
average(x,y)=2.5

//��˼����5-2��д����ʽ
weightedAverage(value,wight)=(10*10+20*11+30*12+40*13+50*14+60*15+70*1)/7 = 410.0
weightedAverage(value,null)=(10*1+20*1+30*1+40*1+50*1+60*1+70*1)/7 = 40.0

*/

//@author��Yeheya��2016-8-25