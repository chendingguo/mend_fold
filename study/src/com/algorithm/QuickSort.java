package com.algorithm;

public class QuickSort {
	public static int part(int[] array, int left, int right) {
		int temp = array[left];
		while (left < right) {

			while (left < right && array[right] > temp) {
				right--;

			}
			// 找到比分界数小的数,放到数组左边,此时right右边的数都比分界数大
			array[left] = array[right];

			while (left < right && array[left] <= temp) {
				left++;
			}
			// 找到比分界数大的数,放到数组右边,此时left左边的数都比分界数小
			array[right] = array[left];

		}
		// left与right相等时,便找到分界数
		array[left] = temp;
		return left;
	}

	public void quickSort(int[] array, int left, int right) {
		if (left < right) {
			for (int e : array) {
				System.out.print(e + " ");
			}
			System.out.println();
			int p = part(array, left, right);
			System.out.println("point"+p);
			quickSort(array, left, p - 1);
			quickSort(array, p + 1, right);
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 3, 7, 5, 8, 0, 19 };
		QuickSort qs = new QuickSort();
		qs.quickSort(a, 0, a.length - 1);
		// for (int e : a) {
		// System.out.println(e);
		// }

	}

}
