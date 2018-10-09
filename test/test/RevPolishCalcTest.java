package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import calculators.Calculator;
import calculators.InvalidExpression;
import calculators.RevPolishCalc;
import cs2800Entry.Symbol;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in postfix notation using {@linkplain Entry} tokens
 * pushed to the {@linkplain TokenStack}containing fields:(TYPE) (NUMBER,
 * SYMBOL, FUNCTION) Tokens created from the expression are assumed to be valid
 * given they are created by the {@linkplain TokenStack} class.
 * 
 * @author Octavio
 * 
 */

class RevPolishCalcTest {
	private TokenStack ts = new TokenStack();
	private RevPolishCalc calc;
	@BeforeEach
	void declareClass() {
		ts=new TokenStack();
		calc=new RevPolishCalc();
	}
	
//	/**
//	 * Test #0 test for class constructor existence.
//	 */
//	@Test
//	void classExists() {
//		RevPolishCalc calculator = new RevPolishCalc();
//		
//	}

	/**
	 * Test #1 {@linkplain RevPolishClalc} accepts a reversed
	 * {@linkplain TokenStack}
	 * 
	 * 
	 * @throws InvalidExpression
	 * @throws EmptyStackException
	 */
	@Test
	void acceptTokenStack() {

		
		try {
			ts.setExpression("5,5,+");
			ts.pushUnformatedExpression(false);
			
			
		} catch (InvalidExpression e) {
			System.out.println(e.getMessage());

		}
	}

	/**
	 * Test #2 This test tests for simple single operator
	 * 
	 * @throws InvalidExpression
	 * @throws EmptyStackException
	 */
	@Test
	void testSingleOperator()  {
		float result;
		try {
			result = calc.calculateString("5 ,2, +");
			assertEquals((float) 7, result, "result is incorrect from expression");
		} catch (InvalidExpression e) {
			fail(e.getMessage());
		}
	

	}

	/**
	 * Test #3 This test tests for simple single operator
	 * 
	 * @throws InvalidExpression
	 * @throws EmptyStackException
	 */
	@Test
	void testMultipleOperators()  {
		float result;
		try {
			result = calc.calculateString("6,5 ,2, +,-");
			assertEquals((float) -1, result, "result is incorrect from expression");
		} catch (InvalidExpression e) {
			fail("should have gone throug");
			e.printStackTrace();
		}
		

	}

	@Test
	void testAlgorithm()  {
		float result;
		try {
			result = calc.calculateString("5 ,2, +");
			assertEquals((float) 7, result, "result is incorrect from expression");
		} catch (InvalidExpression e) {
			fail("should have gone throug");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		System.out.println(result);
	}

	/*
	 * for each token in the postfix expression: if token is an operator: operand_2
	 * ← pop from the stack operand_1 ← pop from the stack result ← evaluate token
	 * with operand_1 and operand_2 push result back onto the stack else if token is
	 * an operand: push token onto the stack result ← pop from the stack
	 */

}
