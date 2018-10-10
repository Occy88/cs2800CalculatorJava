package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculators.Calculator;
import calculators.InvalidExpression;
import entry.Function;
import entry.Symbol;

/**
 * This class tests the {@link Calculator} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * @author Octavio
 * 
 */

class CalculatorTest {
	private Calculator calc=new Calculator();
	private float operand1=4;
	private float operand2=2;
	@Test
	void calculatorExistance() {
		Calculator calc=new Calculator();
	}
	@Test
	void addition() throws InvalidExpression {
		float result=calc.calculate(operand1,Symbol.PLUS,operand2);
		assertEquals(result,operand1+operand2,"added Incorrectly");
		
	}
	@Test
	void subtraction() throws InvalidExpression {
		float result=calc.calculate(operand1,Symbol.MINUS,operand2);
		assertEquals(result,operand1-operand2,"added Incorrectly");
		
	}
	@Test
	void multiplication() throws InvalidExpression {
		float result=calc.calculate(operand1,Symbol.TIMES,operand2);
		assertEquals(result,operand1*operand2,"added Incorrectly");
		
	}
	@Test
	void division() throws InvalidExpression {
		float result=calc.calculate(operand1,Symbol.DIVIDE,operand2);
		assertEquals(result,operand1/operand2,"added Incorrectly");
	}
	@Test void invalidExpression() {
		try{
			calc.calculate(operand1,Symbol.DIVIDE,0);
			fail("invalid expression not thrown");
		}catch(InvalidExpression ex) {

		}
	}
	@Test
	void functionTest() throws InvalidExpression {
		float result=calc.calculateFunction(Function.POW, operand1, operand2);
		assertEquals(result,(float)Math.pow(operand1,operand2),"incorrect result");
	}
		
		
	
	
}
