
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import entry.BadTypeException;
import entry.Entry;
import entry.Function;
import entry.Symbol;
import entry.Type;

import org.junit.jupiter.api.Test;

/**
 * This class tests the {@link Entry} class.
 * 
 * @author Octavio
 * 
 */

class EntryTest {
  /**
   * An instance of the class {@link Entry} used for testing.
   */
  private Entry entry;

  /**
   * Test #1 <br>
   * {@linkplain Entry} class with constructor taking {@linkplain Float} variable.
   * 
   */
  @Test
  void initialiseEntryConstructorWithFloat() {
    float number = 4 / 3;
    this.entry = new Entry(number);
  }

  /**
   * Test #2 <br>
   * {@linkplain Entry} class with constructor taking {@linkplain String} variable.
   * 
   */
  @Test
  void initialiseEntryConstructorWithString() {
    String string = "string";
    this.entry = new Entry(string);
  }

  /**
   * Test #3 <br>
   * {@linkplain Entry} class with constructor taking {@linkplain Symbol} variable.
   * 
   */
  @Test
  void initialiseEntryConstructorWithSymbol() {
    Symbol symbol = Symbol.DIVIDE;
    this.entry = new Entry(symbol);
  }

  /**
   * Test #4<br>
   * Confirm class {@link Entry} contains method {@link Entry#getType()}<br>
   * which returns the correct type.
   */
  @Test
  void getTypeFromEntry() {
    float number = 4 / 2;
    this.entry = new Entry(number);
    assertEquals(this.entry.getType(), Type.NUMBER, "wrong type returned expected number");
    String string = "abc";
    this.entry = new Entry(string);
    assertEquals(this.entry.getType(), Type.STRING, "wrong type returned expected string");
  }

  /**
   * Test #5 <br>
   * Confirm class {@link Entry} contains method {@link Entry#getString()}, <br>
   * which returns the same string that was pushed.
   */
  @Test
  void getStringFromEntry() {
    try {
      String string = "string";
      this.entry = new Entry(string);
      assertEquals(string, this.entry.getString(), "String returned is not equivalent");
      String string2 = "string2";
      this.entry = new Entry(string2);
      assertEquals(string2, this.entry.getString(), "String returned is not equivalent");
    } catch (BadTypeException e) {
      e.getMessage();
      fail("did not get correct type");
    }
    /*
     * required refactoring for storing private string variable
     */
  }

  /**
   * Test #5<br>
   * Confirm class {@link Entry} contains method {@link Entry#getSymbol()},<br>
   * which returns the same symbol that was pushed.
   * 
   */
  @Test
  void getSymbolFromEntry() {
    try {
      Symbol symbol = Symbol.DIVIDE;
      this.entry = new Entry(symbol);
      assertEquals(symbol, this.entry.getSymbol(), "Symbol returned is not equivalent");
      Symbol symbol2 = Symbol.TIMES;
      this.entry = new Entry(symbol2);
      assertEquals(symbol2, this.entry.getSymbol(), "Symbol returned is not equivalent");
    } catch (BadTypeException e) {
      e.getMessage();
      fail("did not get correct type");
    }
    /*
     * required refactoring for storing private symbol variable
     */
  }

  /**
   * Test #6 <br>
   * Confirm class {@link Entry} contains method {@link Entry#getValue()}.
   */
  @Test
  void getValueFromEntry() {
    try {
      float number = 3 / 2;
      this.entry = new Entry(number);
      assertEquals(number, this.entry.getValue(), "Float returned is not equivalent");
      float number2 = 4 / 3;
      this.entry = new Entry(number2);
      assertEquals(number2, this.entry.getValue(), "Float returned is not equivalent");
    } catch (BadTypeException e) {
      e.getMessage();
      fail("did not get correct type");
    }
    /*
     * required refactoring for storing private float variable
     */
  }

  /**
   * Test #7 <br>
   * Check if {@linkplain BadTypeException} is thrown when calling {@link Entry#getString()}.
   */
  @Test
  void getStringException() {
    float number = 3 / 4;
    this.entry = new Entry(number);
    try {
      this.entry.getString();
      fail("Expected a BadTypeException");
    } catch (BadTypeException badType) {
      badType.getMessage();
    }
    /*
     * required refactoring Entry(string) and storing a private type variable.
     */

  }

  /**
   * Test #8 <br>
   * Check if {@linkplain BadTypeException} is thrown when calling {@link Entry#getSymbol()}.
   */
  @Test
  void getSymbolException() {
    float number = 3 / 2;
    this.entry = new Entry(number);
    try {
      this.entry.getSymbol();
      fail("Expected a BadTypeException");
    } catch (BadTypeException badType) {
      badType.getMessage();
    }
    /*
     * required refactoring Entry(symbol)
     */
  }

  /**
   * Test #9<br>
   * Check if {@linkplain BadTypeException} is thrown when calling {@link Entry#getValue()}.
   */
  @Test
  void getValueException() {
    Symbol symbol = Symbol.DIVIDE;
    this.entry = new Entry(symbol);
    try {
      this.entry.getValue();
      fail("Expected a BadTypeException");
    } catch (BadTypeException badType) {
      badType.getMessage();
    }
    /*
     * required refactoring Entry(float)
     */
  }

  // This is extra stuff not part of assignment 1, for the calculator to accept functions.
  /**
   * Test #10 <br>
   * {@linkplain Entry} class with constructor taking {@linkplain Function} variable.
   * 
   */
  @Test
  void initialiseEntryConstructorWithFunction() {
    Function function = Function.SIN;
    this.entry = new Entry(function);
  }

  /**
   * Test #11 <br>
   * Confirm class {@link Entry} contains method {@link Entry#getFunction()}, <br>
   * which returns the same function that was pushed.
   */
  @Test
  void getFunctionFromEntry() {
    try {
      Function function = Function.SIN;
      this.entry = new Entry(function);
      assertEquals(function, this.entry.getFunction(), "function returned is not equivalent");
      Function function2 = Function.COS;
      this.entry = new Entry(function2);
      assertEquals(function2, this.entry.getFunction(), "function returned is not equivalent");
    } catch (BadTypeException e) {
      e.getMessage();
      fail("did not get correct type");
    }
    /*
     * required refactoring for storing private function variable and type.Function.
     */
  }

  /**
   * Test #12<br>
   * Check if {@linkplain BadTypeException} is thrown when calling {@link Entry#getFunction()}.
   */
  @Test
  void getFunctionException() {
    Symbol symbol = Symbol.DIVIDE;
    this.entry = new Entry(symbol);
    try {
      this.entry.getFunction();
      fail("Expected a BadTypeException");
    } catch (BadTypeException badType) {
      badType.getMessage();
    }
    /*
     * required refactoring Entry(float)
     */
  }

  // in case data needs to be printed or other reasons...
  /**
   * Test #13<br>
   * Additional method {@linkplain Entry#toString()}. <br>
   * test if it returns a string representing data stored in string form.
   */
  @Test
  void entryToString() {
    float number = (float) 3 / 2;
    this.entry = new Entry(number);
    assertEquals("1.5", this.entry.toString(),
        "string returned does not represent information correctly");
  }

}
