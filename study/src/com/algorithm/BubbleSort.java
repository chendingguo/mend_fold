package com.algorithm;

public class BubbleSort {
	public void bubbleSort(int[] a){
		int length=a.length;
		for(int i=length-1;i>=0;i--){
			for(int j=0;j<i;j++){
				if(a[i]>a[j]){
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
	}
	

	public static void main(String[] args){
		int[] a = { 1, 4, 3, 7, 5, 8, 0, 19 };
		BubbleSort bs=new BubbleSort();
		bs.bubbleSort(a);
		for (int e : a) {
			System.out.println(e);
		}

	}

}
