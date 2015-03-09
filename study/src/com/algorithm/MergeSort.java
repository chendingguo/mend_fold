package com.algorithm;

public class MergeSort {

	public void merge(int[] a, int from, int mid, int end) {
		int nl = mid - from + 1;
		int nr = end - mid;
		int[] left = new int[nl];
		int[] right = new int[nr];
		System.arraycopy(a, from, left, 0, nl);
		System.arraycopy(a, mid + 1, right, 0, nr);

		int i = 0, j = 0, k = from;
		while (i < nl && j < nr) {
			if (left[i] < right[j]) {
				a[k++] = left[i++];
			} else {
				a[k++] = right[j++];
			}
		}
		for (; i < nl; i++) {
			a[k++] = left[i];

		}

		for (; j < nr; j++) {
			a[k++] = right[j];
		}

	}

	public void mergeSort(int[] a, int from, int end) {
		//递归条件
		if (from < end) {
			int mid = (from + end) / 2;
			mergeSort(a, from, mid);
			mergeSort(a, mid + 1, end);
			//合并
			merge(a, from, mid, end);
		}

	}

	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		int[] a = { 1, 4, 3, 7, 5, 8, 0, 19 };
		ms.mergeSort(a, 0, a.length - 1);
		for (int e : a) {
			System.out.println(e);
		}

	}

}
