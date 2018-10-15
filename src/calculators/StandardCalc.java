
package calculators;

import entry.BadSymbolException;
import entry.BadTypeException;
import entry.Entry;
import entry.Symbol;
import entry.Type;
import stacks.EmptyStackException;
import stacks.TokenStack;

/**
 * calculates string expression in standard notation.
 * 
 * @author octavio
 *
 */
public class StandardCalc {
  private TokenStack outputQueue = new TokenStack();
  private TokenStack operaterStack = new TokenStack();
  private RevPolishCalc revPolish = new RevPolishCalc();

  /**
   * create a tokenQueue using {@linkplain TokenStack}, calculate the expression using
   * {@linkplain #shuntingAlgorithm(TokenStack)}.
   * 
   * @param expression
   *        as string
   * @return float answer
   * @throws InvalidExpression
   *         if expression has errors
   */
  public Float calculateString(String expression) throws InvalidExpression {

    TokenStack tokenQueue = new TokenStack();
    tokenQueue.setExpression(expression);
    tokenQueue.pushUnformatedExpression(true);
    return this.shuntingAlgorithm(tokenQueue);
  }

  /**
   * implementation of Wikipedia Shunting Algorithm PseudoCode bellow for reference.<br>
   * // this implementations accepts functions given by java math library such as pow(a,b),<br>
   * but not composite functions <br>
   * ----while there are tokens to be read:<br>
   * --------read a token.<br>
   * --------if the token is a number, then:<br>
   * ------------push it to the output queue.<br>
   * --------if the token is a function then:<br>
   * ------------push it onto the operator stack <br>
   * --------if the token is an operator, then:<br>
   * ------------while ((there is a function on the operator stack)<br>
   * ----------------or (more important operator on the op stack )<br>
   * ----------------or (equal operator but left associative))<br>
   * ----------------and (the operator at the top of the operator stack is not a left bracket):<br>
   * ----------------------pop j from the operator stack onto the output queue.<br>
   * ------------push it onto the operator stack.<br>
   * --------if the token is a left bracket (i.e. "("), then:<br>
   * ------------push it onto the operator stack.<br>
   * --------if the token is a right bracket (i.e. ")"), then:<br>
   * ------------while the operator at the top of the operator stack is not a left bracket:<br>
   * ----------------pop the operator from the operator stack onto the output queue.<br>
   * ------------pop the left bracket from the stack.<br>
   * ------------//if the stack runs out without finding a left bracket, <br>
   * -------------- then there are mismatched parentheses.<br>
   * ----if there are no more tokens to read:<br>
   * --------while there are still operator tokens on the stack:<br>
   * ------------//if the operator token on the top of the stack is a bracket, <br>
   * -------------- then there are mismatched parentheses. <br>
   * --------pop the operator from the operator stack onto the output queue.<br>
   * exit.
   * 
   * @throws InvalidExpression
   *         if the expression is not valid
   */
  public float shuntingAlgorithm(TokenStack tokenQueue) throws InvalidExpression {
    try {
      while (!tokenQueue.isEmpty()) {
        Entry token = tokenQueue.pop();
        // if number push directly
        if (token.getType() == Type.NUMBER) {
          this.outputQueue.push(token);
        }
        // if function push to stack
        if (token.getType() == Type.FUNCTION) {
          this.operaterStack.push(token);
        }
        // if operator push by precedence
        this.pushByPrecedence(token);
        // if either bracket follow push bracket rules
        this.pushByBrackets(token);
      }
      if (tokenQueue.isEmpty()) {
        this.emptyQueuePushRemainingOperators();
      }

    } catch (EmptyStackException | BadTypeException | BadSymbolException e) {
      e.printStackTrace();
      throw new InvalidExpression(e.getMessage());
    }
    // reverse the stack as rev polish wants it reversed.
    this.outputQueue.reverseStack();
    return revPolish.calculateStack(this.outputQueue);
  }

  /**
   * if the token queue is finished, this function sorts out remaining tokens in operator queue.
   * 
   * @throws EmptyStackException
   *         invalid expression entered
   * @throws BadTypeException
   *         invalid expression entered
   * @throws InvalidExpression
   *         invalid expression entered
   */

  private void emptyQueuePushRemainingOperators()
      throws EmptyStackException, BadTypeException, InvalidExpression {
    // if no more tokens, push rest of operators, there shouldn't be any brackets left
    while (!this.operaterStack.isEmpty()) {
      if (this.operaterStack.top().getType() == Type.SYMBOL
          && (this.operaterStack.top().getSymbol() == Symbol.LEFT_BRACKET
              || this.operaterStack.top().getSymbol() == Symbol.RIGHT_BRACKET)) {

        throw new InvalidExpression("missmatched parentheses");
      }
      this.outputQueue.push(this.operaterStack.pop());
    }           

  }

  /**
   * if token is a bracket, we need to push operators between those brackets.<br>
   * then disregard brackets.
   * 
   * @param token
   *        to be tested invalid expression entered
   * @throws BadTypeException
   *         invalid expression entered
   * @throws EmptyStackException
   *         invalid expression entered
   */
  private void pushByBrackets(Entry token) throws BadTypeException, EmptyStackException {
    // if left bracket, push it to operator stack
    if (token.getType() == Type.SYMBOL && token.getSymbol() == Symbol.LEFT_BRACKET) {
      this.operaterStack.push(token);
    }
    // if right bracket, pop everything from operator stack to output until left bracket
    // pop left bracket off the operator stack.
    if (token.getType() == Type.SYMBOL && token.getSymbol() == Symbol.RIGHT_BRACKET) {
      while (!(this.operaterStack.top().getType() == Type.SYMBOL
          && this.operaterStack.top().getSymbol() == Symbol.LEFT_BRACKET)) {

        this.outputQueue.push(this.operaterStack.pop());
      }
      this.operaterStack.pop();
    }

  }

  /**
   * if we token is a operator/function not a bracket, then we test <br>
   * it's precedence with operators in the op stack and push the more important token.
   * 
   * @param token
   *        to be tested
   * @throws EmptyStackException
   *         invalid expression entered
   * @throws BadTypeException
   *         invalid expression entered
   * @throws BadSymbolException
   *         invalid expression entered
   */

  private void pushByPrecedence(Entry token)
      throws EmptyStackException, BadTypeException, BadSymbolException {
    // if symbol => while OPERATOR/FUNCTION on operator stack more important push item to output
    // then push symbol to operator stack.
    if (token.getType() == Type.SYMBOL && Symbol.isOperator(token.getSymbol())) {
      while (!this.operaterStack.isEmpty() && ((this.operaterStack.top().getType() == Type.FUNCTION)
          || ((this.operaterStack.top().getType() == Type.SYMBOL
              && !(this.operaterStack.top().getSymbol() == Symbol.LEFT_BRACKET
                  || this.operaterStack.top().getSymbol() == Symbol.RIGHT_BRACKET))
              && ((Symbol.getPrecedence(token.getSymbol()) < Symbol
                  .getPrecedence(this.operaterStack.top().getSymbol()))
                  || (Symbol.getPrecedence(token.getSymbol()) == Symbol
                      .getPrecedence(this.operaterStack.top().getSymbol())
                      && Symbol.isLeftAssociative(this.operaterStack.top().getSymbol())))))
      ) {
        this.outputQueue.push(this.operaterStack.pop());
      }
      this.operaterStack.push(token);
    }
  }

  /**
   * prints all entries held in the stack.
   */
  public void printAll() {
    System.out.println("OP STACK: ");
    this.operaterStack.print();
    System.out.println("OUT QUEUE STACK: ");
    this.outputQueue.print();
    this.revPolish.printAll();

  }
}
