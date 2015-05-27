package com.algorithm;

/**
 * Algorithm
 * 
 * The divide-and-conquer strategy is used in quicksort. Below the recursion
 * step is described: Choose a pivot value. We take the value of the middle
 * element as pivot value, but it can be any value, which is in range of sorted
 * values, even if it doesn't present in the array. Partition. Rearrange
 * elements in such a way, that all elements which are lesser than the pivot go
 * to the left part of the array and all elements greater than the pivot, go to
 * the right part of the array. Values equal to the pivot can stay in any part
 * of the array. Notice, that array may be divided in non-equal parts. Sort both
 * parts. Apply quicksort algorithm recursively to the left and the right parts.
 * Partition algorithm in detail
 * 
 * There are two indices i and j and at the very beginning of the partition
 * algorithm i points to the first element in the array and j points to the last
 * one. Then algorithm moves i forward, until an element with value greater or
 * equal to the pivot is found. Index j is moved backward, until an element with
 * value lesser or equal to the pivot is found. If i ≤ j then they are swapped
 * and i steps to the next position (i + 1), j steps to the previous one (j -
 * 1). Algorithm stops, when i becomes greater than j.
 * 
 * After partition, all values before i-th element are less or equal than the
 * pivot and all values after j-th element are greater or equal to the pivot.
 * 
 * @author arisupply
 *
 */
public class QuickSort2 {
	int partition(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];

		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		;

		return i;
	}

	void quickSort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 3, 7, 5, 8, 0, 19 };
		QuickSort2 qs = new QuickSort2();
		qs.quickSort(a, 0, a.length - 1);
		for (int e : a) {
			System.out.println(e);
		}
	}

}
