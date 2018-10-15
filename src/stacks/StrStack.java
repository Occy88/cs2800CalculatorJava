
package stacks;

import entry.BadTypeException;
import entry.Entry;

/**
 * This is a stack using only string expression for the Shunting Algorithm,<br>
 * uses the {@link Stack} class.
 * 
 * @author octavio
 *
 */
public class StrStack {
  /**
   * {@link Stack} instance inherited.
   */
  private Stack strStack = new Stack();

  /**
   * pushes an {@link Entry} using a string into the {@link #strStack}.
   *
   * @param string
   *        to be pushed
   */
  public void push(String string) {
    Entry entry = new Entry(string);
    strStack.push(entry);
  }

  /**
   * returns and removes the top element from {@link #strStack}.
   * 
   * @return {@link Entry}
   * @throws EmptyStackException
   *         if the stack is empty during request
   */
  public String pop() throws EmptyStackException {
    try {
      return strStack.pop().getString();
    } catch (BadTypeException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * returns true if {@link #strStack} is empty.
   * 
   */
  public boolean isEmpty() {
    return strStack.size() == 0;
  }

}
