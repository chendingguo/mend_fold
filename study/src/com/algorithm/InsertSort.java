package com.algorithm;

public class InsertSort {
	public void insertSort(int[] array){
		
		int i,m;
		int num=array.length;
		for(i=1;i<num;i++){
			m=i-1;
			int temp=array[i];
			//从右往左开始比较
			while(m>=0&&array[m]>temp){
				//符合条件时开时插入,统统右移
				array[m+1]=array[m];
				m--;
			}
			//放置于合适位置
			array[m+1]=temp;
			
		}
	}

	public static void main(String[] args){
		int[] a = { 1, 4, 3, 7, 5, 8, 0, 19 };
		InsertSort is=new InsertSort();
		is.insertSort(a);
		for (int e : a) {
			System.out.println(e);
		}

	}
}
