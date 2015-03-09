package com.dp.abstractfactory;


	public class MailSender implements Sender {

		@Override
		public void send() {
			System.out.println("this is Mail Sender");
			
		}

	}

