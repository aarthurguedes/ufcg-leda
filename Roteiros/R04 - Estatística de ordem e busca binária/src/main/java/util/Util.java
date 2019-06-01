package util;

/**
 * Class containing useful methods for arrays manipulation.
 */
public class Util {

	/**
	 * Swaps the contents of two positions in an array.
	 *
	 * @param array
	 *            The array to be modified, not null
	 * @param i
	 *            One of the target positions
	 * @param j
	 *            The other target position
	 */
	public static void swap(Object[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * It says if a specific number is prime or not.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(long n) {
		boolean result = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0){
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Checks whether the parameters passed as input to sort are valid.
	 *
	 * @param array the input array
	 * @param k integer representing the order statistic
	 *
	 * @return the boolean representing the validity or otherwise of the input
	 */
	public static boolean inputIsValid(Object[] array, int k) {
		boolean validInput = true;

		if (array == null) {
			validInput = false;
		} else if (k < 1 || k > array.length) {
			validInput = false;
		}

		return validInput;
	}

	public static boolean inputIsValid(Object[] array, Integer k) {
		boolean validInput = true;

		if (array == null || array.length == 0) {
			validInput = false;
		} else if (k == null) {
			validInput = false;
		}

		return validInput;
	}
}