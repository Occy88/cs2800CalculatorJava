package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculators.Calculator;
import calculators.InvalidExpression;
import calculators.RevPolishCalc;
import cs2800Entry.Symbol;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * @author Octavio
 * 
 */

class RevPolishCalcTest {
	private RevPolishCalc calc=new RevPolishCalc();
	private TokenStack postfixExpressionStack=new TokenStack();
	
	@Test
	void classExists(){
		RevPolishCalc calculator=new RevPolishCalc();
		
	}
	
	@Test
	void testAlgorithm() throws InvalidExpression, EmptyStackException {
		float result=calc.calculateString("5 ,2 pow");
		assertEquals((float) 25,result,"result is incorrect from expression");
//		System.out.println(result);
	}
	
	/*
	 * for each token in the postfix expression:
		  if token is an operator:
		    operand_2 ← pop from the stack
		    operand_1 ← pop from the stack
		    result ← evaluate token with operand_1 and operand_2
		    push result back onto the stack
		  else if token is an operand:
		    push token onto the stack
		result ← pop from the stack
	 */
		
	
	
}
