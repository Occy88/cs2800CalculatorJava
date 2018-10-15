
package calculators;

import entry.BadSymbolException;
import entry.BadTypeException;
import entry.Entry;
import entry.Type;
import stacks.EmptyStackException;
import stacks.NumStack;
import stacks.TokenStack;

/**
 * Calculates mathematical expression given a string in post-fix notation <br>
 * or {@linkplain TokenStack} of {@link Entry} variables.
 * 
 * @author octavio
 *
 */
public class RevPolishCalc {
  private NumStack numStack = new NumStack();

  private Calculator calculator = new Calculator();

  /**
   * calculates string expression in postfix notation.
   * 
   * @param string
   *        expression
   * @return float result
   * @throws InvalidExpression
   *         if the expression is invalid
   */
  public float calculateString(String string) throws InvalidExpression {
    TokenStack tokenStack = new TokenStack();
    tokenStack.setExpression(string);
    tokenStack.pushUnformatedExpression(false);
    return calculateStack(tokenStack);
  }

  /**
   * Implementation of post-fix Algorithm provide by Wikepedia,<br>
   * Modified to work for functions as well<br>
   * . <br>
   * for each token in the post-fix expression:<br>
   * ----if token is a function <br>
   * --------if function requires 1 operand: <br>
   * ------------operand1 ← pop from the stack <br>
   * ------------result ← evaluate token with operand1 <br>
   * --------else if function requires 2 operands: <br>
   * ------------operand2 ← pop from the stack<br>
   * ------------operand1 ← pop from the stack<br>
   * ------------result ← evaluate token with operand1 and operand2<br>
   * ----else if token is an operator:<br>
   * --------operand2 ← pop from the stack<br>
   * --------operand1 ← pop from the stack<br>
   * --------result ← evaluate token with operand1 and operand2<br>
   * --------push result back onto the stack<br>
   * ----else if token is an operand:<br>
   * ------------push token onto the stack<br>
   * result ← pop from the stack
   * 
   * @return float result
   * @throws InvalidExpression
   *         if not a valid postfix expression
   * @throws EmptyStackException
   *         if not a valid postfix expression
   */

  public float calculateStack(TokenStack tokenStack) throws InvalidExpression {

    while (!tokenStack.isEmpty()) {
      Entry token = new Entry("Empty");
      try {
        token = tokenStack.pop();
        if (token.getType() == Type.FUNCTION) {
          this.resolveFunction(token);

        } else if ((token.getType() == Type.SYMBOL) && token.getSymbol().isOperator()) {
          this.resolveOperator(token);

        } else if (token.getType() == Type.NUMBER) {
          numStack.push(token.getValue());
        }
      } catch (BadTypeException e) {
        throw new InvalidExpression(e.getMessage() + " : errorType");
      } catch (EmptyStackException e) {
        throw new InvalidExpression(
            "INVALID REV EXPRESSION token responsible: " + token.toString());
      } catch (BadSymbolException e) {
        throw new InvalidExpression("ErrorSymbol: " + e.getMessage());
      }

    }
    // if expression entered is correct only one token should be remaining (result)
    try {
      float result = numStack.pop();
      if (numStack.isEmpty()) {
        return result;
      } else {
        throw new InvalidExpression("stack not empty, invalid expression: " + numStack.print());
      }
    } catch (EmptyStackException e) {
      e.printStackTrace();
      throw new InvalidExpression("empty Expression or invalid Expression" + this.numStack.print());
    }

  }

  /**
   * resolves state of {@linkplain #numStack} if the token is an operator.
   * 
   * @param token
   *        to be resolved
   * @throws InvalidExpression
   *         if the token is invalid
   * @throws BadTypeException
   *         if the token is invalid
   * @throws EmptyStackException
   *         if the token is invalid
   */
  private void resolveOperator(Entry token)
      throws InvalidExpression, BadTypeException, EmptyStackException {
    // case for symbol token
    float operand2 = numStack.pop();
    float operand1 = numStack.pop();
    float result = calculator.calculate(operand1, token.getSymbol(), operand2);
    numStack.push(result);

  }

  /**
   * resolves state of {@linkplain #numStack} if the token is an function,<br>
   * cases are different if function takes one or two variables.
   * 
   * @param token
   *        to be resolved
   * @throws InvalidExpression
   *         if the token is invalid
   * @throws BadTypeException
   *         if the token is invalid
   * @throws EmptyStackException
   *         if the token is invalid
   */

  private void resolveFunction(Entry token)
      throws BadSymbolException, BadTypeException, EmptyStackException, InvalidExpression {
    float operand1;
    float operand2;
    float result;
    int numberOfOperands = token.getFunction().getNumberOfOperands();
    // depending on number of operands function takes, pop that number
    // calculate the result then push it.

    switch (numberOfOperands) {
      case 1:
        operand1 = numStack.pop();

        result = calculator.calculateFunction(token.getFunction(), operand1, 0);
        numStack.push(result);
        break;
      case 2:
        operand2 = numStack.pop();
        operand1 = numStack.pop();

        result = calculator.calculateFunction(token.getFunction(), operand1, operand2);
        numStack.push(result);
        break;
      default:
        throw new InvalidExpression("function with invalid number of operands, add support.");
    }

  }

  /**
   * print all elements in stack to console.
   */
  public void printAll() {
    System.out.println("NUM STACK: ");
    this.numStack.print();

  }

}
