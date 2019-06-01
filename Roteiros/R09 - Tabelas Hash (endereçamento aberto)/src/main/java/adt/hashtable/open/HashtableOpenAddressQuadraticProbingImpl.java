package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	private boolean inputIsValid(T element) {
		boolean validInput = true;

		if (element == null) {
			validInput = false;
		}

		return validInput;
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) {
			throw new HashtableOverflowException();
		}

		if (this.inputIsValid(element) && this.search(element) == null) {
			int probe = 0;

			while (probe < this.table.length) {
				int key = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);

				if (this.table[key] == null || this.table[key].equals(this.deletedElement)) {
					this.table[key] = element;
					this.elements++;
					break;
				} else {
					this.COLLISIONS++;
					probe++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.inputIsValid(element)) {
			int index = this.indexOf(element);

			if (index != -1) {
				this.table[index] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if (this.inputIsValid(element)) {
			int index = this.indexOf(element);

			if (index != -1) {
				result = (T) this.table[index];
			}
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if (this.inputIsValid(element)) {
			int probe = 0;

			while (probe < this.table.length) {
				int key = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);

				if (this.table[key] == null) {
					probe++;
				} else if (this.table[key].equals(element)){
					index = key;
					break;
				} else {
					probe++;
				}
			}
		}

		return index;
	}
}
