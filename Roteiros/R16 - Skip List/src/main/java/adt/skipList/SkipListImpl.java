package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	private SkipListNode<T> search(int key, SkipListNode[] update, SkipListNode<T> auxRoot) {
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (auxRoot.forward[i].key < key) {
				auxRoot = auxRoot.forward[i];
			}
			update[i] = auxRoot;
		}

		auxRoot = auxRoot.forward[0];
		return auxRoot;
	}
	
	@Override
	public void insert(int key, T newValue, int height) {
		if (height <= this.maxHeight) {
			SkipListNode[] update = new SkipListNode[this.maxHeight];
			SkipListNode<T> auxRoot = this.root;

			auxRoot = search(key, update, (SkipListNode<T>) auxRoot);

			if (auxRoot.key == key) {
				auxRoot.value = newValue;
			} else {
				auxRoot = new SkipListNode<>(key, height, newValue);

				for (int i = 0; i < height; i++) {
					auxRoot.forward[i] = update[i].forward[i];
					update[i].forward[i] = auxRoot;
				}
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> auxRoot = this.root;

		auxRoot = search(key, update, auxRoot);

		if (auxRoot.key == key) {
			int height = height();

			for (int i = 0; i <= height; i++) {
				if (update[i].forward[i] != auxRoot) {
					break;
				}
				update[i].forward[i] = auxRoot.forward[i];
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> auxRoot = this.root;
		int result = 0;

		for (int i = this.maxHeight - 1; i >= 0; i--) {
			if (auxRoot.forward[i] != NIL) {
				result = i;
				break;
			}
		}

		return result;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> result = null;
		SkipListNode<T> auxRoot = this.root;

		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (auxRoot.forward[i].key < key) {
				auxRoot = auxRoot.forward[i];
			}
		}

		auxRoot = auxRoot.forward[0];

		if (auxRoot.key == key) {
			result = auxRoot;
		}

		return result;
	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root.forward[0];
		int result = 0;

		while (!aux.equals(NIL)) {
			result++;
			aux = aux.forward[0];
		}

		return result;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int resultSize = this.size() + 2;
		SkipListNode<T>[] result = new SkipListNode[resultSize];
		SkipListNode<T> aux = this.root;

		for (int i = 0; i < resultSize; i++) {
			result[i] = aux;
			aux = aux.forward[0];
		}

		return result;
	}

}
