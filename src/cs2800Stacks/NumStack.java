package cs2800Stacks;


import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
/**
 * This is a stack using only numeric values for the reverse Polish evaluation, uses the {@link Stack} class
 * @author octavio
 *
 */
public class NumStack {
	/**
	 * {@link Stack} instance inherited
	 */
	private Stack numStack=new Stack();
	/**
	 * pushes an {@link Entry} using a float into the {@link #numStack}
	 * @param number
	 */
	public void push(float number) {
		Entry entry=new Entry(number);
		numStack.push(entry);
	}
	/**
	 * returns and removes the top element from {@link #numStack}
	 * @return {@link Entry}
	 * @throws EmptyStackException
	 * @throws BadTypeException 
	 */
	public float pop() throws EmptyStackException, BadTypeException {
		return numStack.pop().getValue();
	}
	/**
	 * returns true if {@link #numStack} is empty
	 * 
	 */
	public boolean isEmpty() {
		return numStack.size()==0;
	}
	/**
	 * prints stack
	 */
	public String print() {
		
		return this.numStack.print();
	}

}
