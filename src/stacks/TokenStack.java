
package stacks;

import calculators.InvalidExpression;
import entry.BadSymbolException;
import entry.BadTypeException;
import entry.Entry;
import entry.Function;
import entry.Symbol;
import entry.Type;

/**
 * This Class builds a {@link Stack} used in {@link RevPolishCalc},{@link StandardCalc}. <br>
 * input is taken as a {@link String} expression, accepts Math functions i.e.: pow(2,3).<br>
 * input is converted into a reversed stack for (postfix) computation).<br>
 * <br>
 * e.g.: for expression "((a+b)^c)/d"<br>
 * <br>
 * input: <br>
 * "pow &nbsp( &nbsp ( &nbsp a &nbsp + &nbsp b &nbsp ) &nbsp c &nbsp ) &nbsp / &nbsp 2 &nbsp"<br>
 * <br>
 * converts to:<br>
 * Stack Top -> [2]&nbsp [/]&nbsp[)]&nbsp[c]&nbsp[)]&nbsp e.t.c.
 * 
 * @author octavio
 *
 */
public class TokenStack {
  /**
   * {@link Stack} instance inherited.
   */
  private Stack tokenStack = new Stack();
  private String expression = "";

  public String getExpression() {
    return this.expression;
  }

  public TokenStack(String expression) {
  }

  public TokenStack() {
  }

  /**
   * pushes an existing entry into {@link #tokenStack}.
   * 
   * @param entry
   *        token to be pushed
   */
  public void push(Entry entry) {
    this.tokenStack.push(entry);

  }

  /**
   * creates an entry of type {@link Symbol} <br>
   * pushes into {@link #tokenStack}.
   * 
   * @param symbol
   *        found in {@linkplain Symbol}
   */
  public void push(Symbol symbol) {
    Entry entry = new Entry(symbol);
    this.tokenStack.push(entry);

  }

  /**
   * creates an {@linkplain Entry} of type {@link Symbol} <br>
   * . pushes into {@link #tokenStack}
   * 
   * @param number
   *        as a float to push
   */
  public void push(float number) {
    Entry entry = new Entry(number);
    this.tokenStack.push(entry);
  }

  /**
   * creates an {@linkplain Entry} of type {@link Function} <br>
   * pushes into {@link #tokenStack}.
   * 
   * @param function
   *        as a string used to create a new entry token and pushed to stack
   */
  public void push(Function function) {
    Entry entry = new Entry(function);
    this.tokenStack.push(entry);

  }

  /**
   * returns and removes the top element from {@link #tokenStack}.
   * 
   * @return {@link Entry}
   * @throws EmptyStackException
   *         when there are no more tokens in the stack
   */

  public Entry pop() throws EmptyStackException {
    return tokenStack.pop();
  }

  /**
   * returns true if {@link #tokenStack} is empty.
   * 
   * @return boolean stack is empty
   */
  public boolean isEmpty() {
    return tokenStack.size() == 0;
  }

  /**
   * Returns the current number of entries in the stack.
   * 
   * @return {@link Stack#size() }
   */
  public int size() {
    return this.tokenStack.size();
  }

  /**
   * This function splits a string by blank spaces.<br>
   * this is no longer used.
   * 
   * @param testString
   *        to split e.g. "a b c"
   * @return String Array of substrings e.g. ["a"]["b"]["c"]
   */

  public static String[] parse(String testString) {
    String[] resultString = testString.split(" ");
    return resultString;
  }

  /**
   * tests if a given string is a float <br>
   * .
   * 
   * @param string
   *        to test for float
   * @return boolean is a float
   */
  public boolean testFloat(String string) {
    try {
      Float.parseFloat(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }

  }

  /**
   * tests if a given string is a {@linkplain Function}.
   * 
   * @param string
   *        to test
   * @return boolean it is a function
   */
  public boolean testFunction(String string) {
    try {
      Function.stringToFunction(string);
      return true;
    } catch (BadSymbolException e) {
      return false;
    }
  }

  /**
   * tests if a given string is a {@linkplain Symbol} <br>
   * .
   * 
   * @param string
   *        to test
   * @return boolean it is a symbol
   */
  public boolean testSymbol(String string) {
    try {
      Symbol.stringToSymbol(string);
      return true;
    } catch (BadSymbolException e) {
      return false;
    }
  }

  /**
   * attempt at pushing String input onto the stack. <br>
   * 
   * @param input
   *        in string format
   * @throws InvalidExpression
   *         it is not a valid input string
   */
  public void pushString(String input) throws InvalidExpression {

    if (this.testFloat(input)) {
      try {
        this.push(Float.parseFloat(input));
      } catch (NumberFormatException | NullPointerException e) {
        e.printStackTrace();
      }
    } else if (this.testFunction(input)) {
      try {
        this.push(Function.stringToFunction(input));
      } catch (BadSymbolException e) {
        e.printStackTrace();
      }
    } else if (this.testSymbol(input)) {
      try {
        this.push(Symbol.stringToSymbol(input));
      } catch (BadSymbolException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * tests validity of string as a {@linkplain Float}, {@linkplain Symbol} or {@linkplain Function}.
   * 
   * @param input
   *        to be tested as a valid argument
   * @return boolean it is or not a valid argument
   */
  public boolean checkValidityString(String input) {
    return (testFloat(input) || testSymbol(input) || testFunction(input));
  }

  /**
   * returns and does not remove top entry in stack.
   * 
   * @return top entry in {@linkplain #tokenStack}
   * @throws EmptyStackException
   *         there is nothing at the top
   */
  public Entry top() throws EmptyStackException {
    return this.tokenStack.top();
  }

  /**
   * prints current entries in stack.
   * 
   */
  public void print() {
    this.tokenStack.print();

  }

  /**
   * formats {@linkplain TokenStack#expression} string to lower case and no spaces.
   */
  public void removeSpaceAndCaps() {
    this.expression = this.expression.toLowerCase();
    this.expression = this.expression.replaceAll(" ", "");
  }

  /**
   * function to push an unformatted expression, depending on post-fix or standard notation.<br>
   * if post-fix follow post-fix rules in test cases. <br>
   * if standardNotation then follow standard rules in test cases.
   * 
   * @param isStandardEquation
   *        boolean for standard or post-fix format
   * @throws InvalidExpression
   *         there are character elements that are invalid
   */

  public void pushUnformatedExpression(boolean isStandardEquation) throws InvalidExpression {

    // parse to a char array:

    this.removeSpaceAndCaps();
    if (isStandardEquation) {
      this.reducePlusMinusSigns();
    }

    while (!this.expression.equals("")) {

      if (this.expression.substring(0, 1).equals(",")) {
        if (isStandardEquation) {
          this.pushString(")");
        }
        this.expression = this.expression.substring(1, this.expression.length());
        this.testAndPushNextFunctionOrFloat(isStandardEquation);
        continue;
      } else if (this.testAndPushNextOperator(isStandardEquation)) {
        continue;
      } else if (this.testAndPushNextFunctionOrFloat(isStandardEquation)) {
        continue;
      } else {
        System.out.println(this.expression);
        throw new InvalidExpression(
            "expression entered has stopped processing here: " + this.expression);
      }
    }
    this.reverseStack();
  }

  /**
   * this class pushes the next function or float in a string in it's longest form, <br>
   * (sin < sinh), also if the equation is in standard format and there is a function <br>
   * that takes more than one operand it pushes an extra left bracket .
   * 
   * @param isStandardEquation
   *        is the equation in standard format
   * @return boolean something has been pushed
   */
  public boolean testAndPushNextFunctionOrFloat(boolean isStandardEquation) {
    String function = "";
    String longestFunction = "";
    // cycle through expression untill valid argument is found
    for (int index = 0; index <= this.expression.length(); index++) {
      function = this.expression.substring(0, index);
      if (this.testFunction(function) || this.testFloat(function)) {
        longestFunction = function;
      }
    }
    // if it's empty return false
    if (longestFunction.equals("")) {
      return false;
    }

    try {
      // if the longest element is a function push it
      if (this.testFunction(longestFunction)) {
        this.pushString(longestFunction);
        this.expression =
            this.expression.substring(longestFunction.length(), this.expression.length());
        // if it is a standard equation and the function accepts two operands then push left bracket
        if (isStandardEquation
            && Function.numberOfOperands(Function.stringToFunction(longestFunction)) == 2) {
          this.pushString("(");
        }
        return true;
      }
      // if it is a float instead: then push the float.
      if (this.testFloat(longestFunction)) {
        this.pushString(longestFunction);
        this.expression =
            this.expression.substring(longestFunction.length(), this.expression.length());
        return true;
      }
      return false;
    } catch (InvalidExpression | BadSymbolException e) {
      System.out.println("Identified a valid function but couldn't push it.");
      e.printStackTrace();
      return false;
    }

  }

  /**
   * this function pushes the next operator, if the equation is in standard notation,<br>
   * it decides when the operator acts as a sign or as an operator <br>
   * depending on the previous and next elements.
   * 
   * @param isStandardEquation
   *        is the expression in standard format
   * @return boolean something has been pushed
   */
  public boolean testAndPushNextOperator(boolean isStandardEquation) {

    // test first character and push if it's valid
    String firstCharacter = this.expression.substring(0, 1);
    if (this.testSymbol(firstCharacter)) {
      try {
        // case for special cases where +- acts as a sign not operator only with standard equations.
        if (isStandardEquation && (firstCharacter.equals("-") || firstCharacter.equals("+"))) {
          // if before the operator there is nothing or a symbol
          if (this.isEmpty() || this.top().getType() == Type.SYMBOL) {
            // if there is a right bracket:
            if (!this.isEmpty()) {
              if (this.top().getSymbol() == Symbol.RIGHT_BRACKET) {
                this.pushString(firstCharacter);
                this.expression = this.expression.substring(1, this.expression.length());
                return true;
              }
            }
            // if you can push a float do so:
            if (this.testAndPushNextFunctionOrFloat(isStandardEquation)) {
              return true;
            } else {
              // if the next element is a function and before it is nothing:
              // then multiply the function by the sign:
              if (firstCharacter.equals("-")) {
                this.pushString("-1");
                this.pushString("*");
                this.expression = this.expression.substring(1, this.expression.length());
                return true;
              } else {
                this.pushString("1");
                this.pushString("*");
                this.expression = this.expression.substring(1, this.expression.length());
                return true;
              }
            }
          }

        }
        // no special cases just push it as an operator:
        this.pushString(firstCharacter);
        this.expression = this.expression.substring(1, this.expression.length());
        return true;
      } catch (InvalidExpression | EmptyStackException | BadTypeException e) {
        System.out.println("Failed to push a tested Symbol character");
        e.printStackTrace();
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * this function reverses the stack.
   */
  // Reverses the stack as this needs to be done depending on method of use.
  public void reverseStack() {
    Stack reverse = new Stack();
    while (!this.isEmpty()) {
      try {
        reverse.push(this.pop());
      } catch (EmptyStackException e) {
        e.printStackTrace();
      }
    }
    this.tokenStack = reverse;

  }

  /**
   * this function removes any duplicate or more signs in a string i.e.: ab+-+c+-=ab-c-.
   */
  public void reducePlusMinusSigns() {
    char[] testArray = this.expression.toCharArray();
    String resultString = "";
    for (int i = 0; i < testArray.length; i++) {
      char input = testArray[i];
      if (input == '+' || input == '-') {
        // if there is a next character check if it's a sign
        if (i < testArray.length - 2) {
          char nextInput = testArray[i + 1];
          // condition met to change input to +
          if ((nextInput == '-' && input == '-') || (nextInput == '+' && input == '+')) {
            input = '+';
          } else if ((nextInput == '+' && input == '-') || (nextInput == '-' && input == '+')) {
            input = '-'; // condition met to change input to -
          }
        }
        // continue if the next input is a sign
        if (i < testArray.length - 1 && (testArray[i + 1] == '-' || testArray[i + 1] == '+')) {
          continue;
        }
      }
      resultString += input;
    }
    this.expression = String.valueOf(resultString);
  }

  /**
   * this is a setter for the expression held in the class.
   * 
   * @param string
   *        representing the expression to be set
   */
  public void setExpression(String string) {
    this.expression = string;

  }

}
