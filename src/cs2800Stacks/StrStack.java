package cs2800Stacks;


import cs2800Entry.Entry;
/**
 * This is a stack using only string expression for the Shunting Algorithm, uses the {@link Stack} class
 * @author octavio
 *
 */
public class StrStack {
	/**
	 * {@link Stack} instance inherited
	 */
	private Stack strStack=new Stack();
	/**
	 * pushes an {@link Entry} using a string into the {@link #strStack}
	 * @param string
	 */
	public void push(String string) {
		Entry entry=new Entry(string);
		strStack.push(entry);
	}
	/**
	 * returns and removes the top element from {@link #strStack}
	 * @return {@link Entry}
	 * @throws EmptyStackException
	 */
	public Entry pop() throws EmptyStackException {
		return strStack.pop();
	}
	/**
	 * returns true if {@link #strStack} is empty
	 * 
	 */
	public boolean isEmpty() {
		return strStack.size()==0;
	}

}
