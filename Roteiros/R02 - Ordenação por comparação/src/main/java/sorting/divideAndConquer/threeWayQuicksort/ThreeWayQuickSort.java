package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot.
	 * <p>
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 * * arr[l..i] contem elementos menores que o pivot
	 * * arr[i+1..j-1] contem elementos iguais ao pivot.
	 * * arr[j..r] contem elementos maiores do que o pivot.
	 * <p>
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente.
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.inputIsValid(array, leftIndex, rightIndex)) {
			int[] pivot = partition(array, leftIndex, rightIndex);

			sort(array, leftIndex, pivot[0]);
			sort(array, pivot[1] + 1, rightIndex);
		}
	}

	/**
	 * The method starts with a pivot (the first element of the array).
	 * The "gt" starts as the last element of the ordering and will be decremented
	 * if the array in position "i" (next element of the loop) is larger than the
	 * pivot, so, before "lt" we have the elements smaller than pivot, between the
	 * "lt" and "gt" elements equal to the pivot and then the largest.
	 *
	 * @param array the input array
	 * @param leftIndex the initial index for sorting
	 * @param rightIndex the limit index for sorting
	 *
	 * @return the integer representing the final value of "i"
	 */
	private int[] partition(T[] array, int leftIndex, int rightIndex) {
		int lt = leftIndex;
		int gt = rightIndex;
		int i = leftIndex + 1;
		T pivot = array[leftIndex];

		while (i <= gt) {
			if (array[i].compareTo(pivot) < 0) {
				Util.swap(array, lt, i);
				lt += 1;
				i += 1;
			} else if (array[i].compareTo(pivot) > 0) {
				Util.swap(array, i, gt);
				gt -= 1;
			} else {
				i += 1;
			}
		}

		int[] result = {lt, gt};

		return result;
	}
}
