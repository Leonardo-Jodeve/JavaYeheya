//��Java�������ʵ�ý̳̣���4�棩�� ���ߣ�Ҷ���ǣ�2014��11��20��
//��Java�������ʵ�ý̳̣���5�棩�� ���ߣ�Ҷ���ǣ�2016��9��2��
//��6.1.2 ���ֹ���
//3. GridLayout������ʾ��

import java.awt.*;

public class GridLayoutFrame extends Frame
{
    public GridLayoutFrame()
    {
        super("GridLayout");
        this.setSize(320,140);
        this.setLayout(new GridLayout(4,4));     //���񲼾֣����ҷָ�����
        
        String[] str={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
        for(int i=0; i<str.length; i++)
            this.add(new Button(str[i]));
        this.setVisible(true);
    }

    public static void main(String[] arg)
    {
        new GridLayoutFrame();
    }
}
//@author��Yeheya��2016-9-2