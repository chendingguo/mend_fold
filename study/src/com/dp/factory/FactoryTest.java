package com.dp.factory;

/**
 * 工厂模式
 * @author Administrator
 *
 */
public class FactoryTest {
	public static void main(String[] args) {
		SendFactory sf=new SendFactory();
		Sender sender=sf.produce("sms");
		sender.send();
		
	}
	
}
