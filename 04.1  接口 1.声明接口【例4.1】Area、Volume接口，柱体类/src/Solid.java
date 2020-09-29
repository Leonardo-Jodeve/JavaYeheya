//《Java程序设计实用教程（第4版）》 作者：叶核亚，2014年11月20日
//《Java程序设计实用教程（第5版）》 作者：叶核亚，2016年8月17日
//§4.1 接口与实现接口的类
//4. 接口是多继承的

//接口是多继承的
public interface Solid extends Area,Volume      //立体接口，继承多个接口
{}

//继承Area接口的area()抽象方法，计算表面积；继承Volume接口的volume抽象方法，计算体积
//@author：Yeheya，2016-8-17