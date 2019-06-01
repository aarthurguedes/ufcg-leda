package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		} else if (element != null) {
			if (this.tail == -1 && this.head == -1) {
				this.head++;
				this.tail++;
			} else {
				this.tail = (this.tail + 1) % this.array.length;
			}

			this.array[this.tail] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T removed = this.head();

		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (this.head + 1) % this.array.length;
		}

		this.elements--;
		return removed;
	}

	@Override
	public T head() {
		T head = null;

		if (!this.isEmpty()) {
			head = this.array[this.head];
		}

		return head;
	}

	@Override
	public boolean isEmpty() {
		boolean arrayEmpty = false;

		if (this.elements == 0) {
			arrayEmpty = true;
		}

		return arrayEmpty;
	}

	@Override
	public boolean isFull() {
		boolean arrayFull = false;

		if (this.elements == this.array.length) {
			arrayFull = true;
		}

		return arrayFull;
	}
}
