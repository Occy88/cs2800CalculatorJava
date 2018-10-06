package calculators;

import cs2800Entry.Function;
import cs2800Entry.Symbol;
/**
 * calculates result between :<br>
 * two operands and an operator e.g. 4+4,<br>
 * two operands and a function e.g. pow(5,6)<br>
 * one operand and a function e.g. sqrt(4) <br>
 * @author octavio
 *
 */
public class Calculator {
	/**
	 * Calculates standard expression of two operands and an operator using {@linkplain Math}
	 * @param operand_1
	 * @param operator
	 * @param operand_2
	 * @return operand_1 --> operator --> operand_2 --> result
	 * @throws InvalidExpression
	 */
	public float calculate(float operand_1, Symbol operator, float operand_2) throws InvalidExpression {
		switch (operator) {
		case PLUS:
			return operand_1 +operand_2;
		case MINUS:
			return operand_1 -operand_2;
		case TIMES:
			return operand_1 *operand_2;
		case DIVIDE:
			if (operand_2==0) {
				throw new InvalidExpression(Float.toString(operand_1)+operator.toString()+Float.toString(operand_2));
			}
			return operand_1 /operand_2;
		default: throw new InvalidExpression(Float.toString(operand_1)+", "+operator.toString()+", "+Float.toString(operand_2));

		}
				
	}
	/**
	 * applies function variables using {@linkplain Math}
	 * @param function
	 * @param operand_1
	 * @param operand_2
	 * @return float result = function(operand_1) or function(operand_1,operand_2) depending on function.
	 * @throws InvalidExpression
	 */
	public float calculateFunction(Function function,float operand_1,float operand_2) throws InvalidExpression {
		switch (function) {
		case SIN:
			return (float) Math.sin(operand_1);
		case COS:
			return (float) Math.cos(operand_1);
		case TAN:
			return (float) Math.tan(operand_1);
		case ASIN:
			return (float) Math.asin(operand_1);
		case ACOS:
			return (float) Math.acos(operand_1);
		case ATAN:
			return (float) Math.atan(operand_1);
		case ATAN2:
			return (float) Math.atan2(operand_1,operand_2);
		case COSH:
			return (float) Math.cosh(operand_1);
		case SINH:
			return (float) Math.sinh(operand_1);
		case TANH:
			return (float) Math.tanh(operand_1);
		case LOG:
			return (float) Math.log(operand_1);
		case LOG10:
			return (float) Math.log10(operand_1);
		case LOG1P:
			return (float) Math.log1p(operand_1);
		case EXP:
			return (float) Math.exp(operand_1);
		case EXPM1:
			return (float) Math.expm1(operand_1);
		case CBRT:
			return (float) Math.cbrt(operand_1);
		case SQRT:
			return (float) Math.sqrt(operand_1);
		case HYPOT:
			return (float) Math.hypot(operand_1,operand_2);
		case SIGNUM:
			return (float) Math.signum(operand_1);
		case ULP:
			return (float) Math.ulp(operand_1);
		case ABS:
			return (float) Math.abs(operand_1);
		case CEIL:
			return (float) Math.ceil(operand_1);
		case FLOOR:
			return (float) Math.floor(operand_1);
		case IEEEREMAINDER:
			return (float) Math.IEEEremainder(operand_1, operand_2);
		case POW:
			return (float) Math.pow(operand_1,operand_2);
		default: throw new InvalidExpression(Float.toString(operand_1)+", "+function.toString()+", "+Float.toString(operand_2));

		}
		
		
	}



}
