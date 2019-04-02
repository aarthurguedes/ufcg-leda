package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.inputIsValid(array, leftIndex, rightIndex)) {
			int pivot = partition(array, leftIndex, rightIndex);

			sort(array, leftIndex, pivot - 1);
			sort(array, pivot + 1, rightIndex);
		}
	}

	/**
	 * The method starts with a pivot (the first element of the array).
	 * Choose "i" as the first indice of the array and go through the array
	 * using a "j" swapping so that elements smaller than or equal to the pivot
	 * are left before i.
	 *
	 * @param array the input array
	 * @param leftIndex the initial index for sorting
	 * @param rightIndex the limit index for sorting
	 *
	 * @return the integer representing the final value of "i"
	 */
	private int partition (T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i += 1;
				Util.swap(array, i, j);
			}
		}

		Util.swap(array, leftIndex, i);

		return i;
	}
}
