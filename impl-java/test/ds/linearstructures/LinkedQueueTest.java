package ds.linearstructures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class LinkedQueueTest {

	@Test
	public void testFIFOOrder() {
		Queue<Integer> queue = new LinkedQueue<Integer>();
		assert(queue.empty());
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		
		assert(queue.dequeue() == 10);
		assert(queue.dequeue() == 20);
		assert(queue.dequeue() == 30);
		assert(queue.dequeue() == 40);
		assert(queue.dequeue() == null);
		assert(queue.empty());
	}
	
	@Test
	public void testIterator () {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
		List<Integer> list         = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);

		Iterator<Integer> listIterator  = list.iterator();
		Iterator<Integer> queueIterator = queue.iterator();

		while (queueIterator.hasNext()) {
			Integer queueElement = queueIterator.next();
			Integer listElement  = listIterator.next();
			assert(queueElement.equals(listElement));
		}
	}
	
	@Test
	public void testClear() {
		Queue<Integer> queue = new LinkedQueue<Integer>();
		assert(queue.empty());
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		assert(!queue.empty());
		queue.clear();
		assert(queue.empty());
	}
	
	@Test
	public void testSize() {
		Queue<Integer> queue = new LinkedQueue<Integer>();
		assert(queue.size() == 0);
		queue.enqueue(10);
		assert(queue.size() == 1);
		queue.enqueue(10);
		assert(queue.size() == 2);
		queue.enqueue(10);
		assert(queue.size() == 3);
		queue.enqueue(10);
		assert(queue.size() == 4);
	}

}
