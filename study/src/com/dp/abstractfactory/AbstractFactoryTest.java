package com.dp.abstractfactory;


/**
 * 工厂模式拓展时需要修改类，抽象工厂模式解决了这个问题
 * 比如加一个微信发送接口，只要一个类实现Sender接口，再一个工厂类实现provider接口，就可以扩展程序
 * 
 * @author Administrator
 *
 */
public class AbstractFactoryTest {
	public static void main(String[] args) {
		Provider smsProvider=new SmsSenderFactory();
		Sender smsSender=smsProvider.produce();
		smsSender.send();
		
	}

}
