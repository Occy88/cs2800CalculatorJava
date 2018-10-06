package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import calculators.InvalidExpression;
import calculators.RevPolishCalc;
import calculators.StandardCalc;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * @author Octavio
 * 
 */

class StandardCalcTest {
	private StandardCalc calc=new StandardCalc();
	
	

	@Test
	void testAlgorithm() throws InvalidExpression, EmptyStackException {
		TokenStack tokenQueue=new TokenStack();
		tokenQueue.pushExpression("( 5 / 4 ) * sqrt 2  ");
		
		float result=calc.shuntingAlgorithm(tokenQueue);
		System.out.println("FINAL RESULT:"+Float.toString(result));
		
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
