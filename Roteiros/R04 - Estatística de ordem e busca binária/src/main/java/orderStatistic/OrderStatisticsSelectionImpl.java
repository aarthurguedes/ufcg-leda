package orderStatistic;

import util.Util;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem
	 * desejada sem modificar o array original.
	 * <p>
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no
	 * array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 * elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 * como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null.
	 * - Considerar que k varia de 1 a N
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		T result = null;

		if (Util.inputIsValid(array, k)) {
			int indexOfMin = this.getIndexOfMin(array);

			if (k == 1) {
				result = array[indexOfMin];
			} else {
				int index = this.getNextIndexMin(array, indexOfMin, k, 2);
				result = array[index];
			}
		}

		return result;
	}

	/**
	 * Scrolls the array and returns the index of the smallest element.
	 *
	 * @param array the input array
	 *
	 * @return the integer representing the index of the smallest element
	 */
	private int getIndexOfMin(T[] array) {
		int indexOfMin = 0;

		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[indexOfMin]) < 0) {
				indexOfMin = i;
			}
		}

		return indexOfMin;
	}

	/**
	 * Scrolls the array in search of the next smaller element.
	 *
	 * @param array the input array
	 * @param previousMinIndex previous minimum element
	 * @param k k-th order statistic
	 * @param currentK k-th current-order statistic
	 *
	 * @return the integer representing the index of the smallest element
	 */
	private int getNextIndexMin(T[] array, int previousMinIndex, int k, int currentK) {
		int indexMin = 0;
		boolean foundBigger = false;

		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(array[previousMinIndex]) > 0 && !foundBigger) {
				indexMin = i;
				foundBigger = true;
			} else if (array[i].compareTo(array[previousMinIndex]) > 0 && array[i].compareTo(array[indexMin]) < 0) {
				indexMin = i;
			}
		}

		if (k == currentK) {
			return indexMin;
		} else {
			return getNextIndexMin(array, indexMin, k, currentK + 1);
		}
	}
}
