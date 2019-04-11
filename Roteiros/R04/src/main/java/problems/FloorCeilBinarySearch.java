package problems;

import util.Util;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer result = null;

		if (Util.inputIsValid(array, x) && x >= array[0]) {
			Integer medium = (array.length) / 2;

			if (x > array[array.length - 1]) {
				result = array[array.length - 1];
			} else if (x == array[medium]) {
				result = x;
			} else if (x < array[medium]) {
				result = this.findFloor(array, 0, medium - 1, x);
			} else {
				result = this.findFloor(array, medium + 1, array.length - 1, x);
			}
		}

		return result;
	}

	private Integer findFloor(Integer[] array, Integer leftIndex, Integer rightIndex, Integer x) {
		Integer floor = null;

		if (leftIndex <= rightIndex) {
			if (array[leftIndex] == x) {
				floor = array[leftIndex];
			} else if (array[leftIndex] < x && array[leftIndex + 1] > x) {
				floor = array[leftIndex];
			} else {
				floor = this.findFloor(array, leftIndex + 1, rightIndex, x);
			}
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer result = null;

		if (Util.inputIsValid(array, x) && x <= array[array.length - 1]) {
			Integer medium = (array.length) / 2;

			if (x < array[0]) {
				result = array[0];
			} else if (x == array[medium]) {
				result = x;
			} else if (x < array[medium]) {
				result = this.findCeil(array, 0, medium, x);
			} else {
				result = this.findCeil(array, medium + 1, array.length - 1, x);
			}
		}

		return result;
	}

	private Integer findCeil(Integer[] array, Integer leftIndex, Integer rightIndex, Integer x) {
		Integer ceil = null;

		if (leftIndex <= rightIndex) {
			if (array[leftIndex] == x) {
				ceil = array[leftIndex];
			} else if ((array[leftIndex] > x) && leftIndex == array.length - 1) {
				ceil = array[leftIndex];
			} else if ((array[leftIndex] > x) && (array[leftIndex + 1] > array[leftIndex])) {
				ceil = array[leftIndex];
			} else {
				ceil = this.findCeil(array, leftIndex + 1, rightIndex, x);
			}
		}

		return ceil;
	}
}
