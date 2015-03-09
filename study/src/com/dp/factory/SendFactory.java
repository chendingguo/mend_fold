package com.dp.factory;

public class SendFactory {
	public Sender produce(String type){
	if("mail".equals(type)){
		return new MailSender();
	}else if("sms".equals(type)){
		return new SmsSender();
	}else{
		System.err.println(" please input the right type !");
		return null;
	}
	}
}
