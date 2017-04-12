import java.util.concurrent.Semaphore;

/**
 * An implementation of the Buffer interface 
 * illustrating shared memory.
 *
 * Figure 3.18
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */


@SuppressWarnings("unchecked")

public class BoundedBuffer<E> implements Buffer<E>
{
	private static final int BUFFER_SIZE = 5;
	private E[] buffer;
	private int in, out;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;

	public BoundedBuffer() {
		in = 0;
		out = 0;
		buffer = (E[]) new Object[BUFFER_SIZE];

		mutex = new Semaphore(1);
		empty = new Semaphore(BUFFER_SIZE);
		full = new Semaphore(0);
	}


	// producers call this method
	public void insert(E item) {
		try {
			empty.acquire();
			mutex.acquire();

			// add an element to the buffer
			buffer[in] = item;
			in = (in + 1) % BUFFER_SIZE;

			mutex.release();
			full.release();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// consumers call this method
	public E remove() {
		try {
			full.acquire();
			mutex.acquire();

			// remove an item from the buffer
			E item = buffer[out];
			out = (out + 1) % BUFFER_SIZE;

			mutex.release();
			empty.release();

			return item;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
