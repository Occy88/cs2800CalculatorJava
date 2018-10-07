package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import calculators.InvalidExpression;
import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
import cs2800Entry.Symbol;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.TokenStack;
import cs2800Stacks.TokenStack;

/**
 * This class tests the {@link TokenStack} class.
 * 
 * @author Octavio
 * 
 */

class TokenStackTest {
    private float float1=4,float2=5;
	private Symbol symbol;
	private String string="string";
	
	/**
	 * Test #1
	 * check {@link TokenStack} existence
	 */
	@Test
	void createTokenStack() {
		TokenStack tokenStack=new TokenStack();
	}
	/**
	 * add constructor with string to accept string expression
	 */
	@Test
	void acceptStringExpression() {
		TokenStack tokenStack=new TokenStack(this.string);
	}
	
	
	/**
	 * Test #2
	 * Test for pushing Symbol (operator) and float (operand)
	 */
	@Test
	void tokenStackPushSymbolFloat() {
		TokenStack tokenStack=new TokenStack();
		tokenStack.push(this.float1);
		tokenStack.push(this.symbol);
	}
	/**
	 * Test #3
	 * check if {@link TokenStack#pop()} pops string from stack
	 * @throws EmptyStackException 
	 */
	@Test 
	void tokenStackPopFunction() throws EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		tokenStack.push(float1);
		tokenStack.pop();
		
		
		
	}
	/**
	 * Test #4
	 * check if {@link TokenStack#isEmpty()} returns true if stack is empty else false<br>
	 * makes push and pop test redundant
	 * @throws EmptyStackException 
	 */
	@Test 
	void tokenStackIsEmpty() throws EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		tokenStack.push(this.float1);
		assertFalse(tokenStack.isEmpty(),"stack is empty when it shouldt be");
		tokenStack.pop();
		assertTrue(tokenStack.isEmpty(),"stack isn't empty when it should be");
	}
	/**
	 * returns stack size
	 */
	@Test
	void tokenStackSizeFunction() {
		TokenStack tokenStack=new TokenStack() ;
		tokenStack.push(float1);
		assertEquals(1,tokenStack.size(),"size returns wrong value");
				
		}
	/**
	 * String parse function by white spaces into String array
	 */
	@Test 
	void parseStringIntoSectionsByWhiteSpace() {
		TokenStack tokenStack=new TokenStack();
		String testString="test 1 2",section1="test",section2="1",section3="2";
		
		String[] result=tokenStack.parse(testString);
		assertEquals(section1,result[0],"string parsed incorrectly");
		assertEquals(section2,result[1],"string parsed incorrectly");
		assertEquals(section3,result[2],"string parsed incorrectly");
		
		
	}
	/**
	 * function detects if string is float
	 * 
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 * @returns boolean
	 */
	@Test
	void testStringForFloat() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		
		boolean isFloat=tokenStack.testFloat("string1");
		assertFalse(isFloat,"returned should be false if string not a float");
		
		isFloat=tokenStack.testFloat("1");
		assertTrue(isFloat,"returned should be true if string is a float");	
	}
	/**
	 * function detects if string is a symbol from Symbol class
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test
	void testStringForSymbol() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		
		boolean isSymbol=tokenStack.testSymbol("string1");
		assertFalse(isSymbol,"returned should be false if string not a symbol");
		
		isSymbol=tokenStack.testSymbol("(");
		assertTrue(isSymbol,"returned should be true if string is a symbol");
	}
	/**
	 * function detects if string is a Function from {@linkplain Function} class
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test
	void testStringForFunction() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		
		boolean isFunction=tokenStack.testFunction("string1");
		assertFalse(isFunction,"returned should be false if string not a symbol");
		
		isFunction=tokenStack.testFunction("pow");
		assertTrue(isFunction,"returned should be true if string is a symbol");
	}
// this test has now been replaced with a function to push any string rather than one of a specific format.
//	/**
//	 * function that checks for parses expression, checks for float
//	 * checks for symbol, pushes to stack, and throws if input is invalid;
//	 * 
//	 */
//	@Test
//	void pushExpressionToStackFromString()  {
//		TokenStack tokenStack=new TokenStack();
//		String expression1="string";
//		String expression2="1 2 + - / ( )"; // 7 valid variables
//		
//		try {
//			tokenStack.pushExpression(expression1);
//			System.out.println(tokenStack.size());
//			fail("expected following expression to be thrown: "+expression1);
//		}catch (InvalidExpression ex) {};
//		
//		try {
//			tokenStack.pushExpression(expression2);
//			
//			assertEquals(tokenStack.size(),7,"expected 7 variables to be pushed in the following expression: "+expression2);
//		}catch (InvalidExpression ex) {
//			System.out.println(ex.getMessage());
//			fail("expected following expression to be pushed:"+expression2);
//		};
//	}
	// now assume expression is entered in any form:
	/**
	 * Test
	 * this test is to convert an entered string into a standard form, no caps no spaces
	 */
	@Test
	void convertStringNoCapsNoSpaces() {
		TokenStack tokenStack=new TokenStack();
		String converted=tokenStack.formatString("Test s T R i n g");
		assertEquals(converted,"teststring","not converted correctly");
	}
 
	/**
	 * this test takes any string that represents a standard expression and decides if entered variables are legal or not
	 * and pushes them onto the stack.
	 * then pushes it
	 */
	@Test
	void pushAnyPostfixExpressionToStackFromString() {
		TokenStack tokenStack=new TokenStack();
		String expression1="7 * Pokw( 3/2 , 6 )"; //invalid expression
		String expression2="7 * Pow( 3/2 ,6 )"; // 9 valid variables, "," represents break and is not pushed
		
		try {
			tokenStack.pushUnformatedExpression(expression1);
			tokenStack.print();
			fail("expected following expression to be thrown: "+expression1);
		}catch (InvalidExpression ex) {};
		tokenStack=new TokenStack();
		try {
			tokenStack.pushUnformatedExpression(expression2);
			tokenStack.print();
			assertEquals(tokenStack.size(),9,"expected 9 variables to be pushed in the following expression: "+expression2);
		}catch (InvalidExpression ex) {
			System.out.println(ex.getMessage());
			fail("expected following expression to be pushed:"+expression2);
		};
	}
		
		
		
	

}

	