package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (Util.inputIsValid(array, leftIndex, rightIndex)) {
         int halfIndex = (leftIndex + rightIndex) / 2;

         sort(array, leftIndex, halfIndex);
         sort(array, halfIndex + 1, rightIndex);

         merge(array, leftIndex, halfIndex, rightIndex);
      }
   }

   /**
    * The method merges two sub-arrays and, at the end of all calls,
    * produces the ordered array.
    *
    * @param array the input array
    * @param leftIndex the initial index for sorting
    * @param halfIndex the middle index of the array
    * @param rightIndex the limit index for sorting
    *
    * @return the integer representing the final value of "i"
    */
   private void merge(T[] array, int leftIndex, int halfIndex, int rightIndex) {
      T[] firstHalf = Arrays.copyOfRange(array, leftIndex, halfIndex + 1);
      T[] secondHalf = Arrays.copyOfRange(array, halfIndex + 1, rightIndex + 1);

      int i = 0;
      int j = 0;
      int k = leftIndex;

      while (i < firstHalf.length && j < secondHalf.length) {
         if (firstHalf[i].compareTo(secondHalf[j]) <= 0) {
            array[k] = firstHalf[i];
            i += 1;
         } else {
            array[k] = secondHalf[j];
            j += 1;
         }

         k += 1;
      }

      while (i < firstHalf.length) {
         array[k] = firstHalf[i];
         i += 1;
         k += 1;
      }

      while (j < secondHalf.length) {
         array[k] = secondHalf[j];
         j += 1;
         k += 1;
      }
   }
}
