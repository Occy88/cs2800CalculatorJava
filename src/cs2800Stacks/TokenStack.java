package cs2800Stacks;

import java.util.List;

import calculators.InvalidExpression;
import cs2800Entry.BadSymbolException;
import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
import cs2800Entry.Function;
import cs2800Entry.Symbol;
import cs2800Entry.Type;

/**
 * This Class builds a {@link Stack} of tokens fed into {@link RevPolishCalc}
 * and {@link StandardCalc}<br>
 * input is taken as a {@link String} expression, accepts Math functions i.e.:
 * pow(2,3).<br>
 * input is converted into a reversed stack for (postfix) computation).<br>
 * <br>
 * e.g.: for expression "((a+b)^c)/d"<br>
 * <br>
 * input: <br>
 * "pow &nbsp( &nbsp ( &nbsp a &nbsp + &nbsp b &nbsp ) &nbsp c &nbsp ) &nbsp
 * &nbsp / &nbsp 2 &nbsp"<br>
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * pushes an existing entry into {@link #tokenStack}
	 * 
	 * @param {@linkplain Entry}
	 */

	public void push(Entry entry) {
		this.tokenStack.push(entry);

	}

	/**
	 * creates an entry of type {@link Symbol} <br>
	 * pushes into {@link #tokenStack}
	 * 
	 * @param {@linkplain Symbol}
	 */
	public void push(Symbol symbol) {
		Entry entry = new Entry(symbol);
		this.tokenStack.push(entry);

	}

	/**
	 * creates an {@linkplain Entry} of type {@link Symbol} <br>
	 * pushes into {@link #tokenStack}
	 * 
	 * @param float number
	 */
	public void push(float number) {
		Entry entry = new Entry(number);
		this.tokenStack.push(entry);
	}

	/**
	 * creates an {@linkplain Entry} of type {@link Function} <br>
	 * pushes into {@link #tokenStack}
	 * 
	 * @param function
	 */
	public void push(Function function) {
		Entry entry = new Entry(function);
		this.tokenStack.push(entry);

	}

	/**
	 * returns and removes the top element from {@link #tokenStack}
	 * 
	 * @return {@link Entry}
	 * @throws EmptyStackException
	 */

	public Entry pop() throws EmptyStackException {
		return tokenStack.pop();
	}

	/**
	 * returns true if {@link #tokenStack} is empty
	 * 
	 */
	public boolean isEmpty() {
		return tokenStack.size() == 0;
	}

	/**
	 * Returns the current number of entries in the stack
	 * 
	 * @return {@link Stack#size() }
	 */
	public int size() {
		return this.tokenStack.size();
	}

	/**
	 * This function splits a string by blank spaces.
	 * 
	 * @param string to split e.g. "a b c"
	 * @return String Array of substrings e.g. ["a"]["b"]["c"]
	 */

	public static String[] parse(String testString) {
		String[] resultString = testString.split(" ");
		return resultString;
	}

	/**
	 * tests if a given string is a float <br>
	 * if true pushes onto {@linkplain #tokenStack} <br>
	 * 
	 * @param string
	 * @return boolean
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
	 * tests if a given string is a {@linkplain Function}
	 * 
	 * @param string
	 * @return boolean
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
	 * 
	 * @param string
	 * @return boolean
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
	 * attempt at pushing String input onto the stack <br>
	 * 
	 * @param input String
	 * @throws InvalidExpression
	 */
	public void pushString(String input) throws InvalidExpression {
		boolean passed = false;

		try {
			this.push(Float.parseFloat(input));
			passed = true;
		} catch (NumberFormatException e) {
		}

		try {
			this.push(Symbol.stringToSymbol(input));
			passed = true;
		} catch (BadSymbolException e) {
		}

		try {
			this.push(Function.stringToFunction(input));
			passed = true;
		} catch (BadSymbolException e) {
		}

		if (!passed) {
			throw new InvalidExpression(input);
		}

	}

	/**
	 * tests validity of string as a {@linkplain Float}, {@linkplain Symbol} or
	 * {@linkplain Function}
	 * 
	 * @param input
	 * @return
	 */
	public boolean checkValidityString(String input) {
		return (testFloat(input) || testSymbol(input) || testFunction(input));
	}

	/**
	 * does not remove top entry in stack
	 * 
	 * @return top entry in {@linkplain #tokenStack}
	 * @throws EmptyStackException
	 */
	public Entry top() throws EmptyStackException {
		return this.tokenStack.top();
		// TODO Auto-generated method stub

	}

	/**
	 * prints current entries in stack
	 * 
	 */
	public void print() {
		this.tokenStack.print();

	}

	/**
	 * formats string to lower case and no spaces
	 * 
	 * @param string
	 * @return string
	 */
	public void removeSpaceAndCaps() {
		this.expression = this.expression.toLowerCase();
		this.expression = this.expression.replaceAll(" ", "");
	}

	/**
	 * 
	 * @param expression
	 * @throws InvalidExpression
	 */

	public void pushUnformatedExpression(boolean isStandardEquation) throws InvalidExpression {

		// parse to a char array:

		this.removeSpaceAndCaps();
		if (isStandardEquation) {
			this.reducePlusMinusSigns();
		}

		while (!this.expression.equals("")) {

			if (this.expression.substring(0, 1).equals(",")) {
				this.expression = this.expression.substring(1, this.expression.length());
				continue;
			} else if (this.testAndPushNextOperator(isStandardEquation)) {
				continue;
			} else if (this.testAndPushNextFunctionOrFloat()) {
				continue;
			} else {
				System.out.println(this.expression);
				throw new InvalidExpression("expression entered has stopped processing here: " + this.expression);
			}
		}
		this.reverseStack();
	}

	public boolean testAndPushNextFunctionOrFloat() {

		int index = 0;
		String function = "";
		// find a valid function or float
		while (index <= this.expression.length()) {
			function = this.expression.substring(0, index);
			// if current string is a function or float:
			if (this.testFunction(function) || this.testFloat(function)) {
				// if it's the longest it can be push it
				if (function.equals(this.expression)) {
					try {
						this.pushString(function.substring(0, function.length()));
						this.expression = "";
						return true;
					} catch (InvalidExpression e) {
						System.out.println("Identified a valid function but couldn't push it.");
						return false;
					}
				}
				// if it can be longer keep checking validity untill it can't be longer and push
				// it
				while (index < this.expression.length()) {
					index++;
					function = this.expression.substring(0, index);
					if (this.testFunction(function) || this.testFloat(function)) {
						continue;
					} else {
						try {
							this.pushString(function.substring(0, index - 1));
							this.expression = (this.expression.substring(index - 1, this.expression.length()));
							return true;
						} catch (InvalidExpression e) {
							System.out.println("Identified a valid function but couldn't push it: "
									+ function.substring(0, index));
							return false;
						}
					}
				}
			} else {
				index++;
			}
		}
		return false;

	}

	public boolean testAndPushNextOperator(boolean standardEquation) {

		// test first character and push if it's valid
		String firstCharacter = this.expression.substring(0, 1);
		if (this.testSymbol(firstCharacter)) {
			try {
				if (standardEquation && (firstCharacter.equals("-")||firstCharacter.equals("+"))) {
					if (this.isEmpty()) {
						try {
							pushString("0");
						} catch (InvalidExpression e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							if (!(this.top().getType() == Type.NUMBER)) {
								try {
									pushString("0");
								} catch (InvalidExpression e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} catch (EmptyStackException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				this.pushString(firstCharacter);
				this.expression = this.expression.substring(1, this.expression.length());
				return true;
			} catch (InvalidExpression e) {
				System.out.println("Failed to push a tested Symbol character");
				return false;
			}
		} else {
			return false;
		}
	}

	public void print(String string) {
		// print to understand how the above works
		System.out.println(string);
	}

//Reverses the stack as this needs to be done depending on method of use.
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
					}
					// condition met to change input to -
					else if ((nextInput == '+' && input == '-') || (nextInput == '-' && input == '+')) {
						input = '-';
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

	public void setExpression(String string) {
		this.expression = string;
		// TODO Auto-generated method stub

	}

}
