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
				throw new InvalidExpression("expression entered has stopped processing here: " + this.expression);
			}
		}
		this.reverseStack();
	}

	/**
	 * this class pushes the next function or float in a string in it's longest
	 * form, (sin < sinh) if the equation is standard and there is a function that
	 * takes more than one operand it pushes an extra left bracket to encapsulate up
	 * to the comma.
	 * 
	 * @param isStandardEquation
	 * @return boolean pushed
	 */
	public boolean testAndPushNextFunctionOrFloat(boolean isStandardEquation) {
		int index = 0;
		String function = "";
		String longestFunction = "";
		for (index=0;index<= this.expression.length();index++) {
			function = this.expression.substring(0, index);
			if (this.testFunction(function) || this.testFloat(function)) {
				longestFunction = function;
			}
		}
		System.out.println(expression);
		System.out.println(longestFunction.length());
		if (longestFunction.equals("")) {
			return false;
		}
		try {
			if (this.testFunction(longestFunction)) {
				this.pushString(longestFunction);
				this.expression=this.expression.substring(longestFunction.length(),this.expression.length());
				if(Function.numberOfOperands(Function.stringToFunction(longestFunction))==2) {
					this.pushString("(");
				}
				return true;
			}
			if(this.testFloat(longestFunction)) {
				this.pushString(longestFunction);
				this.expression=this.expression.substring(longestFunction.length(),this.expression.length());
				return true;
			}
			return false;
		} catch (InvalidExpression  |BadSymbolException e) {
			System.out.println("Identified a valid function but couldn't push it.");
			e.printStackTrace();
			return false;
		} 

	}

	/**
	 * this function pushes the next operator, if the equation is in standard
	 * notation, it decides when the operator acts as a sign or as an operator
	 * depending on the previous and next elements.
	 * 
	 * @param isStandardEquation
	 * @return boolean
	 */
	public boolean testAndPushNextOperator(boolean isStandardEquation) {

		// test first character and push if it's valid
		String firstCharacter = this.expression.substring(0, 1);
		if (this.testSymbol(firstCharacter)) {
			try {
				// case for special cases where +- acts as a sign not operator.
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
						}
						// if the next element is a function and before it is nothing:
						// then multiply the function by the sign:
						else {
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
	 * this function is used for testing visually, it prints strings to command
	 * line.
	 * 
	 * @param string
	 */
	public void print(String string) {
		// print to understand how the above works
		System.out.println(string);
	}

	/**
	 * this function reverses the stack
	 */
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

	/**
	 * this function removes any duplicate or more signs in a string i.e.:
	 * ab+-+c+-=ab-c-.
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

	/**
	 * this is a setter for the expression held in the class
	 * 
	 * @param string
	 */
	public void setExpression(String string) {
		this.expression = string;
		// TODO Auto-generated method stub

	}

}
