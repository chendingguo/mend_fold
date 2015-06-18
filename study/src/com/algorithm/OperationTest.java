package com.algorithm;

import java.util.Stack;

public class OperationTest {

	private static Stack<Character> opeStack = new Stack<Character>();
	private static Stack<Integer> numStack = new Stack<Integer>();

	private static StringBuilder in = new StringBuilder();
	private static StringBuilder number = new StringBuilder();

	public static void main(String[] args) {
		System.out.println(cal("89-3*2"));
	}

	public static int cal(String input) {
		in.append(input);
		while (in.length() > 0) {
			char temp = in.charAt(0);
			in.delete(0, 1);
			// judge the number
			if (temp >= '0' && temp <= '9') {
				number.append(temp);
			} else {
				if (!"".equals(number.toString())) {
					int num = Integer.parseInt(number.toString());
					numStack.push(num);
					number.delete(0, number.length());
				}
				while (!opeStack.isEmpty() && !compare(temp)) {
					int num2 = numStack.pop();
					int num1 = numStack.pop();
					char ope = opeStack.pop();
					cal0(ope, num1, num2);
				}
				// add operator
				if (temp != '#' && compare(temp)) {
					opeStack.push(temp);
				}
			}
		}
		if (number.length() > 0) {
			int num = Integer.parseInt(number.toString());
			numStack.push(num);
		}
		while (!opeStack.isEmpty()) {
			int num2 = numStack.pop();
			int num1 = numStack.pop();
			cal0(opeStack.pop(), num1, num2);
		}

		return numStack.pop();
	}

	public static void cal0(char ope, int num1, int num2) {
		switch (ope) {
		case '+':
			numStack.push(num1 + num2);
			break;
		case '-':
			numStack.push(num1 - num2);
			break;
		case '*':
			numStack.push(num1 * num2);
			break;
		case '/':
			numStack.push(num1 / num2);
			break;
		}
	}

	public static boolean compare(char operation) {
		if (opeStack.isEmpty())
			return true;
		char last = opeStack.peek();

		switch (operation) {
		case '+':
			return false;
		case '-':
			return false;
		case '*':
			if (last == '+' || last == '-')
				return true;
			return false;
		case '/':
			if (last == '+' || last == '-')
				return true;
			return false;
		}
		return true;
	}

}