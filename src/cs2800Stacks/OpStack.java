package cs2800Stacks;


import cs2800Entry.Entry;
import cs2800Entry.Symbol;
/**
 * This is a stack using only symbols for the Shunting Algorithm, uses the {@link Stack} class
 * @author octavio
 *
 */
public class OpStack {
	/**
	 * {@link Stack} instance inherited
	 */
	private Stack opStack=new Stack();
	/**
	 * pushes an {@link Entry} using {@link Symbol} into the {@link #opStack}
	 * @param symbol
	 */
	public void push(Symbol symbol) {
		Entry entry=new Entry(symbol);
		opStack.push(entry);
	}
	/**
	 * returns and removes the top element from {@link #opStack}
	 * @return {@link Entry}
	 * @throws EmptyStackException
	 */
	
	public Entry pop() throws EmptyStackException {
		return opStack.pop();
	}
	/**
	 * returns true if {@link #opStack} is empty
	 * 
	 */
	public boolean isEmpty() {
		return opStack.size()==0;
	}

}
