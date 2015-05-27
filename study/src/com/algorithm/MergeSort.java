package com.algorithm;

/**
 * Merge algorithm
 * 
 * Assume, that both arrays are sorted in ascending order and we want resulting
 * array to maintain the same order. Algorithm to merge two arrays A[0..m-1] and
 * B[0..n-1] into an array C[0..m+n-1] is as following:
 * 
 * Introduce read-indices i, j to traverse arrays A and B, accordingly.
 * Introduce write-index k to store position of the first free cell in the
 * resulting array. By default i = j = k = 0. At each step: if both indices are
 * in range (i < m and j < n), choose minimum of (A[i], B[j]) and write it to
 * C[k]. Otherwise go to step 4. Increase k and index of the array, algorithm
 * located minimal value at, by one. Repeat step 2. Copy the rest values from
 * the array, which index is still in range, to the resulting array.
 * Enhancements
 * 
 * Algorithm could be enhanced in many ways. For instance, it is reasonable to
 * check, if A[m - 1] < B[0] or B[n - 1] < A[0]. In any of those cases, there is
 * no need to do more comparisons. Algorithm could just copy source arrays in
 * the resulting one in the right order. More complicated enhancements may
 * include searching for interleaving parts and run merge algorithm for them
 * only. It could save up much time, when sizes of merged arrays differ in
 * scores of times.
 * 
 * Complexity analysis
 * 
 * Merge algorithm's time complexity is O(n + m). Additionally, it requires O(n
 * + m) additional space to store resulting array.
 * 
 * @author arisupply
 *
 */
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
		// 递归条件
		if (from < end) {
			int mid = (from + end) / 2;
			mergeSort(a, from, mid);
			mergeSort(a, mid + 1, end);
			// 合并
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
