package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void removeDuplicates() {
      if (!this.isEmpty()) {
         SingleLinkedListNode<T> aux = this.getHead();

         while (!aux.isNIL()) {
            SingleLinkedListNode<T> next = aux.getNext();

            while (!next.isNIL()) {
               if (aux.getData().equals(next.getData())) {
                  next.setData(next.getNext().getData());
                  next.setNext(next.getNext().getNext());
               } else {
                  next = next.getNext();
               }
            }

            aux = aux.getNext();
         }
      }
   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
      SetLinkedList<T> result = new SetLinkedListImpl<>();
      T[] arraySet = this.toArray();

      if (arraySet.length > 0) {
         for (int i = 0; i < arraySet.length; i++) {
            result.insert(arraySet[i]);
         }
      }

      if (otherSet != null) {
         T[] arrayOtherSet = otherSet.toArray();

         if (arrayOtherSet.length > 0) {
            for (int i = 0; i < arrayOtherSet.length; i++) {
               result.insert(arrayOtherSet[i]);
            }
         }
      }

      result.removeDuplicates();
      return result;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SetLinkedList<T> result = new SetLinkedListImpl<>();
      T[] arraySet = this.toArray();
      T[] arrayOtherSet = otherSet.toArray();

      for (int i = 0; i < arraySet.length; i++) {
         for (int j = 0; j < arrayOtherSet.length; j++) {
            if (arraySet[i].equals(arrayOtherSet[j])) {
               result.insert(arraySet[i]);
            }
         }
      }

      result.removeDuplicates();
      return result;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      if (otherSet != null) {
         T[] arrayOtherSet = otherSet.toArray();

         if (arrayOtherSet.length > 0) {
            for (int i = 0; i < arrayOtherSet.length; i++) {
               this.insert(arrayOtherSet[i]);
            }
         }
      }

      this.removeDuplicates();
   }
}
