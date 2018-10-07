package calculators;

import cs2800Entry.BadSymbolException;
import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
import cs2800Entry.Function;
import cs2800Entry.Symbol;
import cs2800Entry.Type;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.NumStack;
import cs2800Stacks.TokenStack;
/**
 * Calculates mathematical expression given a string in postfix notation or {@linkplain TokenStack} of {@link Entry} vriables
 * 
 * @author octavio
 *
 */
public class RevPolishCalc {
	private NumStack numStack=new NumStack();

	private Calculator calculator=new Calculator();
	
	/**
	 * calculates string expression in postfix notation
	 * @param string expression
	 * @return float result
	 * @throws InvalidExpression
	 */
	public float calculateString(String string) throws InvalidExpression {
		TokenStack tokenStack =new TokenStack();
		tokenStack.pushUnformatedExpression(string);
		return calculateStack(tokenStack);
	}
	

	/**
	 * Implementation of postfix Algorithm provide by Wikepedia,<br>
	 * Modified to work for functions as well<br><br>
	 * for each token in the postfix expression:<br>
	 * ----if token is a function <br>
	 *--------if function requires 1 operand: <br>
	 *------------operand_1 ← pop from the stack <br>
	 *------------result ← evaluate token with operand_1 <br>
	 *--------else if function requires 2 operands: <br>
	 *------------operand_2 ← pop from the stack<br>
	 *------------operand_1 ← pop from the stack<br>
	 *------------result ← evaluate token with operand_1 and operand_2<br>
	 *----else if token is an operator:<br>
	 *--------operand_2 ← pop from the stack<br>
	 *--------operand_1 ← pop from the stack<br>
	 *--------result ← evaluate token with operand_1 and operand_2<br>
	 *--------push result back onto the stack<br>
	 *----else if token is an operand:<br>
	 *------------push token onto the stack<br>
	 *result ← pop from the stack
	 * @return float result
	 * @throws InvalidExpression 
	 * @throws EmptyStackException 
	 */
	
	public float calculateStack(TokenStack tokenStack) throws InvalidExpression {
		while (!tokenStack.isEmpty()) {
			
			try {
					
					Entry token= tokenStack.pop();
				if (token.getType()==Type.FUNCTION) {
					float operand_1,operand2,result;
					int numberOfOperands=Function.numberOfOperands(token.getFunction());
					switch (numberOfOperands) {
					
					case 1:
						operand_1=numStack.pop();
						result=calculator.calculateFunction(token.getFunction(), operand_1, 0);
						numStack.push(result);	
						break;
					case 2:
						operand2=numStack.pop();
						operand_1=numStack.pop();
						result=calculator.calculateFunction(token.getFunction(), operand_1, operand2);
						numStack.push(result);
						break;
					}
					
					
				}
				else if ((token.getType()==Type.SYMBOL)&&Symbol.isOperator(token.getSymbol())) {

					float operand_2=numStack.pop();
					float operand_1=numStack.pop();
					float result=calculator.calculate(operand_1, token.getSymbol(), operand_2);
					numStack.push(result);
				}
				else if (token.getType()==Type.NUMBER) {
					numStack.push(token.getValue());
				}
			}
			catch (BadSymbolException e) {
				
				throw new InvalidExpression("postfix expression entered is invalid have you entered a wrong symbol?: "+e.getMessage());
			}
			catch(EmptyStackException e) {
				throw new InvalidExpression("expression is invalid, have you entered missmatched brackets?: "+e.getMessage());
			}catch(BadTypeException e) {
				throw new InvalidExpression("This exception happens if the algorithm thought it popped an entry of a type x when it was actually of type y, it shouldn't happen unless tampered with: "+e.getMessage());
			}		
		}
		try {
			float result=numStack.pop();
			if( numStack.isEmpty()){
				return result;
			}
			else {
				throw new InvalidExpression("not a valid expression most likely due to missmatched brackets.");
			}
		} catch (EmptyStackException | BadTypeException e) {
			return 0;
		}
		
	}

}
