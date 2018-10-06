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
 * input is taken as a {@link String} expression separating variables by a space.<br>
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
            push(Float.parseFloat(string));
            return  true;
            
        }  
        catch (NumberFormatException e) 
        { 
            return false;
        } 
		
	}
	/**
	 * tests if a given string is a {@linkplain Function} <br>
	 * if true pushes onto {@linkplain #tokenStack}
	 * @param string
	 * @return boolean
	 */
	public boolean testFunction(String string) {
		try {
			Function function=Function.stringToFunction(string);
			Entry entry = new Entry(function);
			this.tokenStack.push(entry);
			
			
			return true;
		} catch (BadSymbolException e) {
			return false;
		}
	}
	/**
	 * tests if a given string is a {@linkplain Symbol} <br>
	 * if true pushes onto {@linkplain #tokenStack} returns true <br>
	 * else returns false
	 * @param string
	 * @return boolean
	 */
	public boolean testSymbol(String string) {
		try {
			Symbol symbol = Symbol.stringToSymbol(string);
			Entry entry=new Entry(symbol);
			this.tokenStack.push(entry);
			return true;
		}catch (BadSymbolException e) {
			return false;
		}
	}

	/**
	 * Pushes a String expression onto the stack <br>
	 * uses:<br> {@linkplain #parse(String)}<br>
	 * {@linkplain #testFloat(String)},{@linkplain #testFunction(String)},{@linkplain #testSymbol(String)}<br>
	 * string is parsed tested for valid entries and pushed onto stack
	 * 
	 * @param expression
	 * @throws InvalidExpression
	 */
	public  void pushExpression(String expression)throws InvalidExpression {
		String[] variables= parse(expression);
		
		for(int i=variables.length-1;i>=0;i--) {
			String variable=variables[i];
			
			if (!(testFloat(variable) || testSymbol(variable)||testFunction(variable))){
			
				throw new InvalidExpression(variable);
			}		
			
		}
		
		// TODO Auto-generated method stub
		
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


	


	


}
