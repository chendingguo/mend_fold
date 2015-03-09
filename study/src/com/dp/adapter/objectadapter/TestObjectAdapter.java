package com.dp.adapter.objectadapter;

import com.dp.adapter.classadapter.ComputerProduct;
import com.dp.adapter.classadapter.Machine;

/**
 * 持有machine类崦不是继承
 * @author Administrator
 *
 */
public class TestObjectAdapter {
	public static void main(String[] args) {
		Machine machine=new Machine();
		ComputerProduct cp=new Wrapper(machine);
		cp.createCpu();
		cp.createKeyboard();
	}

}
