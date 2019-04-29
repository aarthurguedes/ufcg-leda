package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected RecursiveDoubleLinkedListImpl<T> previous;

   public RecursiveDoubleLinkedListImpl() {

   }

   @Override
   public void insert(T element) {
      if (super.inputIsValid(element)) {
         if (this.isEmpty()) {
            RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
            next.setPrevious(this);

            this.setData(element);
            this.setNext(next);

            if (this.getPrevious() == null) {
               RecursiveDoubleLinkedListImpl<T> previous = new RecursiveDoubleLinkedListImpl<>();
               previous.setNext(this);

               this.setPrevious(previous);
            }
         } else {
            this.getNext().insert(element);
         }
      }
   }

   @Override
   public void insertFirst(T element) {
      if (super.inputIsValid(element)) {
         if (this.isEmpty()) {
            RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
            RecursiveDoubleLinkedListImpl<T> previous = new RecursiveDoubleLinkedListImpl<>();

            next.setPrevious(this);
            previous.setNext(this);

            this.setData(element);
            this.setNext(next);
            this.setPrevious(previous);
         } else if (this.getPrevious().isEmpty()) {
            RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();

            next.setData(this.getData());
            next.setNext(this.getNext());
            next.setPrevious(this);

            this.setData(element);
            this.setNext(next);
            ((RecursiveDoubleLinkedListImpl) next.getNext()).setPrevious(next);
         } else {
            this.getPrevious().insertFirst(element);
         }
      }
   }

   @Override
   public void remove(T element) {
      if (super.inputIsValid(element)) {
         if (!this.isEmpty()) {
            if (this.getData().equals(element)) {
               if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
                  this.setData(null);
               } else {
                  this.setData(this.getNext().getData());
                  this.setNext(this.getNext().getNext());

                  if (this.getNext() != null) {
                     ((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(this);
                  }
               }
            } else {
               this.getNext().remove(element);
            }
         }
      }
   }

   @Override
   public void removeFirst() {
      if (!this.isEmpty()) {
         if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
            this.setData(null);
         } else if (this.getPrevious().isEmpty() && !this.getNext().isEmpty()) {
            this.setData(this.getNext().getData());
            this.setNext(this.getNext().getNext());
            ((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(this.getPrevious());
         } else {
            this.getPrevious().removeFirst();
         }
      }
   }

   @Override
   public void removeLast() {
      if (!this.isEmpty()) {
         if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
            this.setData(null);
         } else if (!this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
            this.setData(this.getPrevious().getData());
            this.setPrevious(this.getPrevious().getPrevious());
            this.getPrevious().setNext(this);
         } else {
            ((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
         }
      }
   }

   public RecursiveDoubleLinkedListImpl<T> getPrevious() {
      return previous;
   }

   public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
      this.previous = previous;
   }
}
