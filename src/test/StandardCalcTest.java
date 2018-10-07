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
		String expression="(5*2)/pow(4,2)";
		TokenStack tokenStack=new TokenStack();
		tokenStack.pushUnformatedExpression(expression);
		float result=calc.shuntingAlgorithm(tokenStack);
		assertEquals((float)0.625,result,"Result from algoritm is unexpected.");
		System.out.println("FINAL RESULT:"+Float.toString(result));
		
	}

	
	
	

}
