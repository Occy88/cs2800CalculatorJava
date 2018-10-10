
package stacks;

import entry.BadTypeException;
import entry.Entry;

/**
 * This is a stack using only numeric values for the reverse Polish evaluation, <br>
 * uses the {@link Stack} class.
 * 
 * @author octavio
 *
 */
public class NumStack {
  /**
   * {@link Stack} instance inherited.
   */
  private Stack numStack = new Stack();

  /**
   * pushes an {@link Entry} using a float into the {@link #numStack}.
   * 
   * @param number float to be pushed to stack
   */
  public void push(float number) {
    Entry entry = new Entry(number);
    numStack.push(entry);
  }

  /**
   * returns and removes the top element from {@link #numStack}.
   * 
   * @return {@link Entry}
   * @throws EmptyStackException if the stack is empty
   * @throws BadTypeException if the entry is not a float when it should be
   */
  public float pop() throws EmptyStackException, BadTypeException {
    return numStack.pop().getValue();
  }

  /**
   * returns true if {@link #numStack} is empty.
   * @return boolean the stack is empty
   * 
   */
  public boolean isEmpty() {
    return numStack.size() == 0;
  }

  /**
   * calls function to print stack.
   */
  public String print() {

    return this.numStack.print();
  }

}
