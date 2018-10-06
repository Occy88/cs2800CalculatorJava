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
	 * and pushes if it is.
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 * @returns boolean
	 */
	@Test
	void testStringForFloat() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		
		boolean hasPushed=tokenStack.testFloat("string1");
		assertEquals(tokenStack.size(),0,"string 'string1' pushed when testing for float");
		assertFalse(hasPushed,"returned should be false if string not a float");
		
		hasPushed=tokenStack.testFloat("1");
		assertEquals(tokenStack.pop().getValue(),(float)1,"string '1' pushed not converted to float or not pushed");
		assertTrue(hasPushed,"returned should be true if string is a float");	
	}
	/**
	 * function detects if string is a symbol from Symbol class and pushes it to the stack
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test
	void testStringForSymbol() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		
		boolean hasPushed=tokenStack.testSymbol("string1");
		assertEquals(tokenStack.size(),0,"string 'string1' pushed when testing for symbol");
		assertFalse(hasPushed,"returned should be false if string not a symbol");
		
		hasPushed=tokenStack.testSymbol("(");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.LEFT_BRACKET,"string '(' not pushed when testing for symbol");
		assertTrue(hasPushed,"returned should be true if string is a symbol");
		
		hasPushed=tokenStack.testSymbol(")");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.RIGHT_BRACKET,"string ')' not pushed when testing for symbol");
	
		hasPushed=tokenStack.testSymbol("*");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.TIMES,"string '*' not pushed when testing for symbol");
	
		hasPushed=tokenStack.testSymbol("/");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.DIVIDE,"string '/' not pushed when testing for symbol");
	
		hasPushed=tokenStack.testSymbol("+");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.PLUS,"string '+' not pushed when testing for symbol");
	
		hasPushed=tokenStack.testSymbol("-");
		assertEquals(tokenStack.pop().getSymbol(),Symbol.MINUS,"string '' not pushed when testing for symbol");
	}
	/**
	 * function that checks for parses expression, checks for float
	 * checks for symbol, pushes to stack, and throws if input is invalid;
	 * 
	 */
	@Test
	void pushExpressionToStackFromString()  {
		TokenStack tokenStack=new TokenStack();
		String expression1="string";
		String expression2="1 2 + - / ( )"; // 7 valid variables
		
		try {
			tokenStack.pushExpression(expression1);
			System.out.println(tokenStack.size());
			fail("expected following expression to be thrown: "+expression1);
		}catch (InvalidExpression ex) {};
		
		try {
			tokenStack.pushExpression(expression2);
			
			assertEquals(tokenStack.size(),7,"expected 7 variables to be pushed in the following expression: "+expression2);
		}catch (InvalidExpression ex) {
			System.out.println(ex.getMessage());
			fail("expected following expression to be pushed:"+expression2);
		};
		
		
	}

}

	