
package stacks;

import entry.Entry;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the {@link Stack} class.<br>
 * holds entries of type {@linkplain Entry}
 * 
 * @author Octavio
 */
public class Stack {
  /**
   * holds instances of {@link Entry} class pushed by {@link #push(Entry)}.
   *
   */
  private List<Entry> entries = new ArrayList<Entry>();

  /**
   * size variable holding the current number of entries in the stack.
   */
  private int size = 0;

  /**
   * function pushing {@link Entry} instance to {@link #entries} <br>
   * responsible for incrementing {@link #size} variable.
   */
  public void push(Entry entry) {
    this.size += 1;
    this.entries.add(entry);

  }

  /**
   * size of stack.
   * @return {@link #size}
   */
  public int size() {
    return this.size;
  }

  /**
   * returns and removes top element in {@link #entries}.
   * 
   * @throws EmptyStackException if no more entries in the stack
   */
  public Entry pop() throws EmptyStackException {
    if (this.size == 0) {
      throw new EmptyStackException();
    }
    this.size -= 1;
    Entry returnEntry = this.entries.get(this.size);
    this.entries.remove(this.size);

    return returnEntry;

  }

  /**
   * returns top element in stack without removing it.
   * @return top element in {@link #entries}.
   * @throws EmptyStackException if no more entries in stack
   */
  public Entry top() throws EmptyStackException {
    if (this.size == 0) {
      throw new EmptyStackException();
    }
    return this.entries.get(this.size - 1);
  }

  /**
   * prints all current entries in {@linkplain #entries}.
   */
  public String print() {
    String r = "";
    for (int i = this.size - 1; i >= 0; i--) {
      r += ("[" + this.entries.get(i).toString() + "]");
    }
    System.out.println(r);
    return r;

  }

}
