package com.dp.abstractfactory;

public class MailSenderFactory implements Provider {

	@Override
	public Sender produce() {
		return  new MailSender();
	}
	

}
