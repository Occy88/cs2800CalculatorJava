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
 * 
 * This Class builds a {@link Stack} of tokens fed into {@link RevPolishCalc} and {@link StandardCalc}<br>
 * input is taken as a {@link String} expression, accepts Math functions i.e.: pow(2,3).<br>
 * input is converted into a reversed stack for (postfix) computation).<br><br>
 * e.g.: for expression "((a+b)^c)/d"<br><br>
 * input: <br>
 * "pow &nbsp( &nbsp ( &nbsp a &nbsp + &nbsp b &nbsp ) &nbsp c &nbsp ) &nbsp  &nbsp / &nbsp 2 &nbsp"<br><br>
 * converts to:<br>
 * Stack Top -> [2]&nbsp [/]&nbsp[)]&nbsp[c]&nbsp[)]&nbsp e.t.c.
 * 
 * @author octavio
 *
 */
public class TokenStack {
	/**
	 * {@link Stack} instance inherited
	 */
	private Stack tokenStack=new Stack();
	public TokenStack(String expression) {
	
	}

	
	public TokenStack() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * pushes an existing entry into {@link #tokenStack}
	 * @param {@linkplain Entry}
	 */
	
	public void push(Entry entry)  {
		this.tokenStack.push(entry);
		
	}
	/**
	 * creates an entry of type {@link Symbol} <br>
	 * pushes into {@link #tokenStack}
	 * @param {@linkplain Symbol}
	 */
	public void push(Symbol symbol) {
		Entry entry=new Entry(symbol);
		this.tokenStack.push(entry);
		
	}
	/**
	 *  creates an {@linkplain Entry} of type {@link Symbol} <br>
	 * pushes into {@link #tokenStack}
	 * 
	 * @param float number
	 */
	public void push(float number) {
		Entry entry=new Entry(number);
		this.tokenStack.push(entry);		
	}
	/**
	 * creates an {@linkplain Entry} of type {@link Function} <br>
	 * pushes into {@link #tokenStack}
	 * @param function
	 */
	public void push(Function function) {
		Entry entry=new Entry(function);
		this.tokenStack.push(entry);
		
		
	}
	/**
	 * returns and removes the top element from {@link #tokenStack}
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
		return tokenStack.size()==0;
	}
	
	/**
	 * Returns the current number of entries in the stack
	 * @return {@link Stack#size()  }
	 */
	public int size() {
		return this.tokenStack.size();
	}
	/**
	 * This function splits a string by blank spaces
	 * @param string to split e.g. "a b c"
	 * @return String Array of substrings e.g. ["a"]["b"]["c"]
	 */

	public static String[] parse(String testString) {
		String[] resultString=testString.split(" ");
		return resultString;
	}

	/**
	 * tests if a given string is a float <br>
	 * if true pushes onto {@linkplain #tokenStack}  <br>
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean testFloat(String string) {
        try
        { 
        	Float.parseFloat(string);
            return  true;
            
        }  
        catch (NumberFormatException e) 
        { 
            return false;
        } 
		
	}
	/**
	 * tests if a given string is a {@linkplain Function}
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
	 * @param string
	 * @return boolean
	 */
	public boolean testSymbol(String string) {
		try {
			Symbol.stringToSymbol(string);
			return true;
		}catch (BadSymbolException e) {
			return false;
		}
	}

	/**
	 * attempt at pushing String input onto the stack <br>
	 * 
	 * @param input String
	 * @throws InvalidExpression
	 */
	public  void pushString(String input)throws InvalidExpression {			
		boolean passed= false;
		
		try {
			this.push(Float.parseFloat(input));
			passed=true;
		}catch (NumberFormatException e) { } 
		
		try {
			this.push(Symbol.stringToSymbol(input));
			passed=true;
		} catch (BadSymbolException e) {}
		
		try {
			this.push(Function.stringToFunction(input));
			passed=true;
		} catch (BadSymbolException e) {}	
	
		if (!passed) {throw new InvalidExpression(input);}
		
	}	
	/**
	 * tests validity of string as a {@linkplain Float}, {@linkplain Symbol} or {@linkplain Function}
	 * @param input
	 * @return
	 */
	public boolean checkValidityString(String input) {
		return(testFloat(input) || testSymbol(input)||testFunction(input));
	}
	/**
	 * does not remove top entry in stack
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
	public void print()  {
		this.tokenStack.print();
		
	}

	/**
	 * formats string to lower case and no spaces
	 * @param string
	 * @return string
	 */
	public String formatString(String string) {
		string=string.toLowerCase();
		string=string.replaceAll(" ", "");
		return string;
	}

	/**
	 * 
	 * @param expression
	 * @throws InvalidExpression
	 */
	/*
	 * 
	 * -----------------------------
	 * input expression --> Pow 5,4 +
	 * format expression--> pow5,4+
	 * convert to char array -->[p] [o] ...
	 * push each value onto string stack:
	 * two stacks:
	 * queue stack containing characters of expression in reverse i.e top of stack=start of expression
	 * output stack containing the expression to be pushed as a string onto the tokenStack used by calculators classes
	 * in this function char array and a string will be used instead of stacks to facilitate to string conversion given by java
	 * 
	 * check() function, tests if a string can be pushed to expression
	 * 
	 * Pseudo code for this function:
	 * inputStream stack= input expression
	 * outputStream stack = string created from input stream checked for validity for pushing onto Token Stack.
	 * 
	 * boolean isValid = false
	 * while input stack is not empty:
	 * 		char input=inputStack.pop()
	 * 		if input is a ","
	 * 			if boolean isValid:
	 * 				push input onto (outputStack)
	 * 				clear the outputStack
	 * 				reset boolean isValid=False
	 * 				continue;
	 * 			else
	 * 				throw invalid expression exception
	 * 		push input onto output stack
	 * 		if output stack is a valid expression):
	 * 			set boolean isValid=true
	 * 		else:
	 * 			if boolean isValid:
	 * 				inputStack.push(outputStack.pop())
	 * 				push outputStack onto the tokenStack
	 * 				outputStack.clear()
	 * 				isValid=false
	 * 				continue;
	 * 			else:
	 * 				throw invalid expression exception
	 * if isValid:
	 * 		push(outputStack) to tokenStack
	 * else:
	 * 		throw invalid expression exception
	 * 
	 * how do we deal with multiple signs (---2) (+++2)(2+-1)(2--1) e.t.c.
	 * well try
	 *
	 * if you come accross a sign:
	 * 		while the next item is a sign:
	 * 			if it's -+ or +- convert input to - continue
	 * 			if it's -- or ++ convert input to + continue
	 * 			break;
	 * check if the current item on the stack is valid and push it or throw error
	 * push the sign so it's not seen as a float by Float.parse
	 * 
	 * IN ORDER TO SEE HOW IT WORKS, UNCOMMENT THE PRINT FUNCTION AT THE BOTTOM AND THE STACK>PRINT COMMENT IN THE FOR LOOP>
	 */
	public void pushUnformatedExpression(String expression) throws InvalidExpression {
		expression=formatString(expression);
		char[] chars=expression.toCharArray();
		String outputStack="";
		boolean isValid=false;
		for (int i=0;i<chars.length;i++) {
			
			print("current state of TokenStack:");
//			this.tokenStack.print();
			print("\n==========NEW CYCLE==============");
			print("current input: "+chars[i]);
			char input=(chars[i]);
			if (input=='+'||input=='-') {
				while(i<chars.length-1) {
					if(chars[i+1]=='+'||chars[i+1]=='-') {
						if (input=='+') {
							if (chars[i+1]=='-') {
								input='-';
								i++;
								continue;
							}else {
								i++;
								continue;
							}
						}else {
							if (chars[i+1]=='-') {
								input='+';
								i++;
								continue;
							}
							else {
								i++;
								continue;
							}
						}
					}break;
					
				}
			}
			if (input=='+'||input=='-')//need to be pushed separately as don't want to evaluate to float but operator token
			{if (input=='+'||input=='-') {
				while(i<chars.length-1) {
					if(chars[i+1]=='+'||chars[i+1]=='-') {
						if (input=='+') {
							if (chars[i+1]=='-') {
								input='-';
								i++;
								continue;
							}else {
								i++;
								continue;
							}
						}else {if (input=='+'||input=='-') {
							while(i<chars.length-1) {
								if(chars[i+1]=='+'||chars[i+1]=='-') {
									if (input=='+') {
										if (chars[i+1]=='-') {
											input='-';
											i++;
											continue;
										}else {
											i++;
											continue;
										}
									}else {
										if (chars[i+1]=='-') {
											input='+';
											i++;
											continue;
										}
										else {
											i++;
											continue;
										}
									}
								}break;
								
							}
						}
							if (chars[i+1]=='-') {
								input='+';
								i++;
								continue;
							}
							else {
								i++;
								continue;
							}
						}
					}break;
					
				}
			}
				if (isValid){
					this.pushString(outputStack);
					this.pushString(""+input);
					
					outputStack="";
					isValid=false;
					continue;
				}
				else {
					throw new InvalidExpression("Invalid expression, stack:"+ outputStack);
				}	
			}
			if (input==',') {
				if (isValid){
					this.pushString(outputStack);	
					outputStack="";
					isValid=false;
					continue;
				}
				else {
					throw new InvalidExpression("Invalid expression, stack:"+ outputStack);
				}	
			}
			outputStack+=input;
			print("Current outputStack entries: "+outputStack);
			if (this.checkValidityString(outputStack)){
				isValid=true;
			}
			else {
				if (isValid) {
					i--;
					this.pushString(outputStack.substring(0, outputStack.length()-1));
				
				
					outputStack="";
					isValid=false;
					continue;
				}
				else if(i==chars.length){
					throw new InvalidExpression("Invalid expression, stack: "+ outputStack);
				}
				else {
					continue;
				}
			}
		}
		if (isValid) {
			this.pushString(outputStack);
		}
		else {
			throw new InvalidExpression("Invalid or empty string , stack:  "+outputStack);
		}
		this.reverseStack();//Place it into the correct order.
		this.tokenStack.print();
	}

	public void print(String string) {
		//print to understand how the above works
//		System.out.println(string);
	}

//Reverses the stack as this needs to be done depending on method of use.
	public void reverseStack() {
		Stack reverse=new Stack();
		while (!this.isEmpty()) {
			try {
				reverse.push(this.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		this.tokenStack=reverse;
		
	}
	


	


}
