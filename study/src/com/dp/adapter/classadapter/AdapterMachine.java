package com.dp.adapter.classadapter;

public class AdapterMachine extends Machine implements ComputerProduct {

	@Override
	public void createCpu() {
		System.out.println("Create a cpu");
		
	}

}
