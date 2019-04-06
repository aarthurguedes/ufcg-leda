package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (Util.inputIsValid(array, leftIndex, rightIndex)) {
			int max = this.getMax(array, leftIndex, rightIndex);
			int min = this.getMin(array, leftIndex, rightIndex);

			int[] count = new int[max - min + 1];
			int[] result = new int[rightIndex - leftIndex + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				count[array[i] - min] += 1;
			}

			this.getSumPrefixes(count);

			for (int i = rightIndex; i >= leftIndex; i--) {
				count[array[i] - min]--;
				result[count[array[i] - min]] = array[i];
			}

			this.sortOriginalArray(array, result, leftIndex);
		}
	}

	/**
	 * The method traverses the input array and returns the highest value.
	 *
	 * @param array the input array
	 * @param leftIndex the initial index for sorting
	 * @param rightIndex the limit index for sorting
	 *
	 * @return the integer representing the largest array value
	 */
	private int getMax(Integer[] array, int leftIndex, int rightIndex) {
		int max = array[leftIndex];

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (max < array[i]) {
				max = array[i];
			}
		}

		return max;
	}

	/**
	 * The method traverses the input array and returns the smallest value.
	 *
	 * @param array the input array
	 * @param leftIndex the initial index for sorting
	 * @param rightIndex the limit index for sorting
	 *
	 * @return the integer representing the lowest value
	 */
	private int getMin(Integer[] array, int leftIndex, int rightIndex) {
		int min = array[leftIndex];

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (min > array[i]) {
				min = array[i];
			}
		}

		return min;
	}

	/**
	 * The method traverses the received array as a parameter and sums the prefixes, that is,
	 * of the previous elements, starting from index 1.
	 *
	 * @param count the input array
	 *
	 */
	private void getSumPrefixes(int[] count) {
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
	}

	/**
	 * The method traverses the original array and updates the positions according to an already ordered array.
	 *
	 * @param array array to be sorted
	 * @param result the auxiliary array already ordered
	 * @param leftIndex the initial index for sorting
	 *
	 */
	private void sortOriginalArray(Integer[] array, int[] result, int leftIndex) {
		for (int i = 0; i < result.length; i++) {
			array[i + leftIndex] = result[i];
		}
	}
}
