package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import calculators.InvalidExpression;
import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
import cs2800Entry.Function;
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
		System.out.println("Hello");
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
// this test was stupid as nobody enters expressions with consistent black characers.
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
		tokenStack.setExpression("E x P r E");
		tokenStack.removeSpaceAndCaps();
		assertEquals(tokenStack.getExpression(),"expre");
	}
	/**
	 * Test #
	 * check if TokenStack can push a valid single string and reject an invalid single string i.e. ("+") accepted ("b") rejected
	 * this can be a sign, function or float.
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test
	void pushString() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		try {

			tokenStack.pushString("pow");
			assertEquals(tokenStack.top().getFunction(),Function.POW);
		}catch(InvalidExpression e) {
			System.out.println(e.getMessage());
			fail("expected to push tokens with separator.");
		}
		
	}
	/**
	 * Test #
	 * evaluates expression in {@linkplain TokenStack} for multiple '+' '-' characters in sequence and reduces them to the correct sign.
	 * e.g.: <br>
	 * this.expression=5++-23-1 => this.expression = "5-23-1"
	 * jk12l => return false<br>
	 */
	@Test
	void reducePlusMinusCharArrayTest() {
		TokenStack tokenStack=new TokenStack();
		String test= "+-+--ab--+cd";
		tokenStack.setExpression(test);
		tokenStack.reducePlusMinusSigns();
		String expected="-ab+cd";
		assertEquals(tokenStack.getExpression(),expected);
	}
	/**
	 * This tests a function that tests for a first float or function in a string,
	 * pushes if it is valid and removes it from the expression held in the class.
	 * e.g.:<br>
	 * pow54bda => push("pow") this.expression = "54bda" return true<br>
	 * 43.1abc => push(43.1) this.expression = "abc" return true<br>
	 * jk12l => return false<br>
	 * 
	 * @return boolean 
	 * @throws BadTypeException
	 * @throws EmptyStackException
	 */
	@Test
	void testIfFirstFunctionOrFloatIsPushed() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		tokenStack.setExpression("powasdf");
		assertTrue(tokenStack.testAndPushNextFunctionOrFloat());
		
		tokenStack=new TokenStack();
		tokenStack.setExpression("sinhasdf");
		tokenStack.testAndPushNextFunctionOrFloat();
		assertEquals("asdf",tokenStack.getExpression());
		assertEquals(Function.SINH,tokenStack.top().getFunction());
		
		tokenStack=new TokenStack();
		tokenStack.setExpression("5powasdf");
		assertTrue(tokenStack.testAndPushNextFunctionOrFloat());
		
		tokenStack=new TokenStack();
		tokenStack.setExpression("5.2sinhasdf");
		tokenStack.testAndPushNextFunctionOrFloat();
		assertEquals("sinhasdf",tokenStack.getExpression());
		assertEquals((float)5.2,tokenStack.top().getValue());
		
	}
	@Test
	void testIfFirstOperatorIsPushed() throws BadTypeException, EmptyStackException {
		TokenStack tokenStack=new TokenStack();
		tokenStack.setExpression("+asdf");
		assertTrue(tokenStack.testAndPushNextOperator(false));
		
		tokenStack=new TokenStack();
		tokenStack.setExpression("-sinhasdf");
		tokenStack.testAndPushNextOperator(false);
		assertEquals(tokenStack.getExpression(),"sinhasdf");
		assertEquals(Symbol.MINUS,tokenStack.top().getSymbol());
		
	}
	@Test
	void pushValidFormatedExpressionTest(){
		try {
			TokenStack tokenStack=new TokenStack();
			tokenStack.setExpression("+--5+4.2");
			tokenStack.pushUnformatedExpression(true);//6 characters '+,-,-,5,+,4.2'
			assertEquals(5,tokenStack.size());
			}catch (InvalidExpression e) {
				System.out.println(e.getMessage());
			}
		
	}
	@Test
	void pushValidUnformatedExpressionTest()  {
		try {
		TokenStack tokenStack=new TokenStack();
		tokenStack.setExpression("+3-2");
		tokenStack.pushUnformatedExpression(true);//5 characters '+- == -' '135' 'pow' '(' '423'
		tokenStack.print();
		System.out.println("Size:"+tokenStack.size());
		assertEquals(5,tokenStack.size());
		}catch (InvalidExpression e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	void acceptCommaAsSeparator()  {
	
		try {
		TokenStack tokenStack=new TokenStack();
		tokenStack.setExpression("+-1,3,5 Pow(4,23)");
		tokenStack.pushUnformatedExpression(true);//10 variables (comma is just separator)
		tokenStack.print();
		assertEquals(10,tokenStack.size());
		}
		catch (InvalidExpression e) {
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * push postfix expression
	 */
	@Test
	void pushPostfix() {
		try {
			TokenStack tokenStack=new TokenStack();
			tokenStack.setExpression("+++--3");
			tokenStack.pushUnformatedExpression(false);//10 variables (comma is just separator)
			assertEquals(6,tokenStack.size());
			}
			catch (InvalidExpression e) {
				fail(e.getMessage());
			}
	}
	@Test
	void addZeroBeforeEmptyOperator() {
		try {
			TokenStack tokenStack=new TokenStack();
			tokenStack.setExpression("+3");
			tokenStack.pushUnformatedExpression(true);//10 variables (comma is just separator)
			assertEquals(3,tokenStack.size());
			}
			catch (InvalidExpression e) {
				fail(e.getMessage());
			}
		
	
	}
	
		
		
		
	

}

	