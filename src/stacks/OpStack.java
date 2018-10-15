
package stacks;

import entry.BadTypeException;
import entry.Entry;
import entry.Symbol;

/**
 * This class is redundant, as it has been replaced with {@linkplain TokenStack}.<br>
 * It has been replaced as it does not accept {@linkplain Function} tokens.<br>
 * This is a stack using only symbols for the Shunting Algorithm, uses the {@link Stack} class.
 * 
 * @author octavio
 *
 */
public class OpStack {
  /**
   * {@link Stack} instance inherited.
   */
  private Stack opStack = new Stack();

  /**
   * pushes an {@link Entry} using {@link Symbol} into the {@link #opStack}.
   * 
   * @param symbol
   *        to be pushed
   */
  public void push(Symbol symbol) {
    Entry entry = new Entry(symbol);
    opStack.push(entry);
  }

  /**
   * returns and removes the top element from {@link #opStack}.
   * 
   * @return {@link Entry}
   * @throws EmptyStackException
   *         if the stack is empty
   */

  public Symbol pop() throws EmptyStackException {
    try {
      return opStack.pop().getSymbol();
    } catch (BadTypeException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * returns true if {@link #opStack} is empty.
   * 
   */
  public boolean isEmpty() {
    return opStack.size() == 0;
  }

}
