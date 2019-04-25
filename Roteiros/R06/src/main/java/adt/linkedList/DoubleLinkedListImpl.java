package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected DoubleLinkedListNode<T> last;

   public DoubleLinkedListImpl() {
      this.last = new DoubleLinkedListNode<>();
      this.setHead(this.last);
   }

   @Override
   public void insert(T element) {
      if (this.inputIsValid(element)) {
         DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();

         newLast.setData(element);
         newLast.setNext(nil);
         newLast.setPrevious(this.getLast());

         if (this.isEmpty()) {
            this.setLast(newLast);
            this.setHead(newLast);
         } else {
            this.getLast().setNext(newLast);
            this.setLast(newLast);
         }
      }
   }

   @Override
   public void insertFirst(T element) {
      if (super.inputIsValid(element)) {
         DoubleLinkedListNode<T> head = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();

         head.setData(this.getHead().getData());
         head.setNext(this.getHead().getNext());
         head.setPrevious(newHead);

         newHead.setData(element);
         newHead.setNext(head);
         newHead.setPrevious(nil);

         if (this.getHead().isNIL()) {
            this.setLast(newHead);
         }

         this.setHead(newHead);
      }
   }

   @Override
   public void removeFirst() {
      if (!this.getHead().isNIL()) {
         this.setHead(this.getHead().getNext());

         if (this.getHead().isNIL()) {
            this.setLast((DoubleLinkedListNode<T>) this.getHead());
         }
         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> auxHead = new DoubleLinkedListNode<>();

         auxHead.setData(this.getHead().getData());
         auxHead.setNext(this.getHead().getNext());
         auxHead.setPrevious(nil);
      }
   }

   @Override
   public void removeLast() {
      if (!this.getLast().isNIL()) {
         this.setLast(this.getLast().getPrevious());

         if (this.getLast().isNIL()) {
            this.setHead(this.getLast());
         }

         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
         this.getLast().setNext(nil);
      }
   }

   public DoubleLinkedListNode<T> getLast() {
      return last;
   }

   public void setLast(DoubleLinkedListNode<T> last) {
      this.last = last;
   }
}
