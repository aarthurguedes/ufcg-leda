package adt.linkedList.reverseappend;

import adt.linkedList.SingleLinkedListNode;

/**
 * 
 * @see SingleLinkedListReverseAppend
 * 
 * @author campelo
 *
 * @param <T>
 */
public class SingleLinkedListReverseAppendImpl<T> implements SingleLinkedListReverseAppend<T> {
	
	/*
	 * Nao remover esse atributo nem criar outros
	 */
	protected SingleLinkedListNode<T> head;
	
	/*
	 * Nao modifique o construtor
	 * @param head
	 */
	public SingleLinkedListReverseAppendImpl() {
		head = new SingleLinkedListNode<T>();
	}

	/* (non-Javadoc)
	 * @see adt.linkedList.reverseappend.SingleLinkedListReverseAppend#doIt(java.lang.Object)
	 * 
	 * Implemente apenas este metodo de acordo com os comentários da interface.
	 * 
	 */
	@Override
    public void doIt(T elem) {
		if (elem != null) {
			if (this.head.isNIL()) {
				this.head.setData(elem);
				this.head.setNext(new SingleLinkedListNode<>());
			} else if (this.head.getNext().isNIL()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(elem, this.head);
				this.head = newHead;
			} else {
				SingleLinkedListNode<T> lastHead = new SingleLinkedListNode<>();
				SingleLinkedListNode<T> auxHead = this.head;
				SingleLinkedListNode<T> next = this.head;

				while (!next.isNIL()) {
					next = next.getNext();
					auxHead.setNext(lastHead);
					lastHead = auxHead;
					auxHead = next;
				}

				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(elem, lastHead);
				this.head = newHead;
			}
		}
    }
    
	
	
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     */
	@Override
    public String toString() {
    	String retorno = "";
    	SingleLinkedListNode<T> currentNode = this.head;
    	while (currentNode!=null) {
    		if (!retorno.equals("")) {
    			retorno += " ";
    		}
    		retorno += currentNode;
    		currentNode = currentNode.getNext();
    	}
		return retorno;
    }
}