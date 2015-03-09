package com.dp.adapter.interfaceadapter;
/**
 * 当一个接口中有多个抽象方法，写实现类时要实现所有方法，比较浪费
 * 引用了接口的甜酸　模式，借助一个抽象类，实现接口
 * 写类时只要继承抽象类重写需要的方法就ＯＫ
 * @author Administrator
 *
 */
public class TestAbstraceAdapter {
	public static void main(String[] args) {
		Sourceable s1 = new SubWrapper();
		s1.methodOne();
		s1.methodTwo();

	}

}
