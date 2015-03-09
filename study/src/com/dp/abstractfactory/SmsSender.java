package com.dp.abstractfactory;

public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is SMS Sender");
		
	}

}
