package com.dp.adapter.objectadapter;

import com.dp.adapter.classadapter.ComputerProduct;
import com.dp.adapter.classadapter.Machine;

public class Wrapper implements ComputerProduct{
	private Machine machine;
	public Wrapper(Machine machine){
		super();
		this.machine=machine;
	}
	@Override
	public void createKeyboard() {
		machine.createKeyboard();
		
		
	}
	@Override
	public void createCpu() {
		System.out.println("create Cpu");
		
	}
	
	

}
