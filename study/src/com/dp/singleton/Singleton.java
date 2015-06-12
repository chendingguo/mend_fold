package com.dp.singleton;

public class Singleton {
	private static Singleton instance = null;

	private Singleton() {

	}

//	public static Singleton getSingleton() {
//		if (instance == null) {
//			synchronized (instance) {
//				if (instance == null) {
//					instance = new Singleton();
//
//				}
//			}
//
//		}
//		return instance;
//	}
	
	/**
	 * 内部类维护单例
	 * @author Administrator
	 *
	 */
	private static class SingletonFatory{
		private static Singleton instance=new Singleton();
		
	}
	public static Singleton getInstance(){
		return SingletonFatory.instance;
		
	}
	

}
