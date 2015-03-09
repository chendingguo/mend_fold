package com.dp.adapter.classadapter;

public class TestClassAdapter {
	public static void main(String[] args) {
		ComputerProduct cp=new AdapterMachine();
		cp.createCpu();
		cp.createKeyboard();
		
	}

	

}
