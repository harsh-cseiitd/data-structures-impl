package ds.linearstructures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class LinkedStackTest {

	@Test
	public void testLifoOrder() {
		Stack<Integer> stack = new LinkedStack<Integer>();
		assert(stack.empty());
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		
		assert(stack.pop() == 40);
		assert(stack.pop() == 30);
		assert(stack.pop() == 20);
		assert(stack.pop() == 10);
		assert(stack.pop() == null);
		assert(stack.empty());
	}
	
	@Test
	public void testIterator () {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		stack.push(40);
		stack.push(30);
		stack.push(20);
		stack.push(10);

		Iterator<Integer> listIterator = list.iterator();
		Iterator<Integer> stackIterator = stack.iterator();

		while (stackIterator.hasNext()) {
			Integer stackElement = stackIterator.next();
			Integer listElement  = listIterator.next();
			assert(stackElement.equals(listElement));
		}
	}
	
	@Test
	public void testClear() {
		Stack<Integer> stack = new LinkedStack<Integer>();
		assert(stack.empty());
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		assert(!stack.empty());
		stack.clear();
		assert(stack.empty());
	}
	
	@Test
	public void testSize() {
		Stack<Integer> stack = new LinkedStack<Integer>();
		assert(stack.size() == 0);
		stack.push(10);
		assert(stack.size() == 1);
		stack.push(20);
		assert(stack.size() == 2);
		stack.push(30);
		assert(stack.size() == 3);
		stack.push(40);
		assert(stack.size() == 4);
	}
}
