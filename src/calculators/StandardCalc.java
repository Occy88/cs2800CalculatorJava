package calculators;

import cs2800Entry.BadSymbolException;
import cs2800Entry.BadTypeException;
import cs2800Entry.Entry;
import cs2800Entry.Symbol;
import cs2800Entry.Type;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.TokenStack;
/**
 * calculates string expression in standard notation.
 * @author octavio
 *
 */
public class StandardCalc {
	private TokenStack outputQueue = new TokenStack();
	private TokenStack operaterStack=new TokenStack();
	private RevPolishCalc revPolish=new RevPolishCalc();
	/** 
	 * implementation of Wikipedia Shunting Algorithm;
	*	// This implementation does not implement composite functions,functions with variable number of arguments, and unary operators. <br>
	*	
	*----while there are tokens to be read:<br>
	*--------read a token.<br>
	*--------if the token is a number, then:<br>
	*------------push it to the output queue.<br>
	*--------if the token is a function then:<br>
	*------------push it onto the operator stack <br>
	*--------if the token is an operator, then:<br>
	*------------while ((there is a function at the top of the operator stack)<br>
	*----------------or (there is an operator at the top of the operator stack with greater precedence)<br>
	*----------------or (the operator at the top of the operator stack has equal precedence and is left associative))<br>
	*----------------and (the operator at the top of the operator stack is not a left bracket):<br>
	*----------------pop operators from the operator stack onto the output queue.<br>
	*------------push it onto the operator stack.<br>
	*--------if the token is a left bracket (i.e. "("), then:<br>
	*------------push it onto the operator stack.<br>
	*--------if the token is a right bracket (i.e. ")"), then:<br>
	*------------while the operator at the top of the operator stack is not a left bracket:<br>
	*----------------pop the operator from the operator stack onto the output queue.<br>
	*------------pop the left bracket from the stack.<br>
	*------------//if the stack runs out without finding a left bracket, then there are mismatched parentheses.<br> 
	*----if there are no more tokens to read:<br>
	*--------while there are still operator tokens on the stack:<br>
	*------------//if the operator token on the top of the stack is a bracket, then there are mismatched parentheses. <br>
	*--------pop the operator from the operator stack onto the output queue.<br>
	*	exit.
	 * @throws InvalidExpression 
	*/
	public float shuntingAlgorithm(TokenStack tokenQueue) throws InvalidExpression {
		try {
			while(!tokenQueue.isEmpty()) {
					Entry token=tokenQueue.pop();
					if(token.getType()==Type.NUMBER) {
						this.outputQueue.push(token);
					}
					if (token.getType()==Type.FUNCTION) {
						this.operaterStack.push(token);
					}
					if (token.getType()==Type.SYMBOL && Symbol.isOperator(token.getSymbol())) {;
						while
							(	!this.operaterStack.isEmpty()
									&&
									( this.operaterStack.top().getType()==Type.SYMBOL && !(
											this.operaterStack.top().getSymbol()==Symbol.LEFT_BRACKET
											||this.operaterStack.top().getSymbol()==Symbol.RIGHT_BRACKET	
											)	
									)	
									&&
								(	(this.operaterStack.top().getType()==Type.FUNCTION)
									|| (Symbol.getPrecedence(token.getSymbol()) < Symbol.getPrecedence(this.operaterStack.top().getSymbol()))
									|| (Symbol.getPrecedence(token.getSymbol()) == Symbol.getPrecedence(this.operaterStack.top().getSymbol()))
										) 
								
							) {
							this.outputQueue.push(this.operaterStack.pop());
						}
						this.operaterStack.push(token);
					}
					if (token.getType()==Type.SYMBOL && token.getSymbol()==Symbol.LEFT_BRACKET) {
						this.operaterStack.push(token);
					}
					if (token.getType()==Type.SYMBOL && token.getSymbol()==Symbol.RIGHT_BRACKET) {;
						
						this.operaterStack.print();
						while(token.getType()==Type.SYMBOL &&(this.operaterStack.top().getSymbol()!=Symbol.LEFT_BRACKET)) {
							this.outputQueue.push(this.operaterStack.pop());
						}
						this.operaterStack.pop();
					}
				} 
			if (tokenQueue.isEmpty()) {
				while(!this.operaterStack.isEmpty()) {
					if (this.operaterStack.top().getType()==Type.SYMBOL && 
							(this.operaterStack.top().getSymbol()==Symbol.LEFT_BRACKET 
							||this.operaterStack.top().getSymbol()==Symbol.RIGHT_BRACKET)
						) {
						throw new InvalidExpression("missmatched parentheses");
					}
					this.outputQueue.push(this.operaterStack.pop());
						
				}
			}
//			if there are no more tokens to read:
//				*	    while there are still operator tokens on the stack:
//				*	        //if the operator token on the top of the stack is a bracket, then there are mismatched parentheses. 
//				*	        pop the operator from the operator stack onto the output queue.
//				
			}catch (EmptyStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (BadTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadSymbolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.outputQueue.print();
		TokenStack revOutputQueue=new TokenStack();
		while (!outputQueue.isEmpty()) {
			try {
				revOutputQueue.push(outputQueue.pop());
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return revPolish.calculateStack(revOutputQueue);
	}
	public void print(String string) {
		System.out.println(string);
	}
}