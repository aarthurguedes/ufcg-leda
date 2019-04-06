package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (Util.inputIsValid(array, leftIndex, rightIndex)) {
			int max = this.getMax(array, leftIndex, rightIndex);

			int[] count = new int[max + 1];
			int[] result = new int[rightIndex - leftIndex + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				count[array[i]] += 1;
			}

			this.getSumPrefixes(count);

			for (int i = rightIndex; i >= leftIndex; i--) {
				count[array[i]]--;
				result[count[array[i]]] = array[i];
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
	 * @return the boolean representing the validity or otherwise of the input
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
