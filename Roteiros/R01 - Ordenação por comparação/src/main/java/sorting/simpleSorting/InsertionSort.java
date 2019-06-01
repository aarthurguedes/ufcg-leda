package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.inputIsValid(array, leftIndex, rightIndex)) {
			for (int j = leftIndex + 1; j <= rightIndex; j++) {
				T key = array[j];
				int i = j - 1;

				while (i >= leftIndex && array[i].compareTo(key) > 0) {
					Util.swap(array, i + 1, i);
					i -= 1;
				}

				array[i + 1] = key;
			}
		}
	}
}
