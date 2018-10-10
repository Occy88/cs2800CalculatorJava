package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entry.Symbol;
import stacks.EmptyStackException;
import stacks.OpStack;


/**
 * This class tests the {@link OpStack} class.
 * 
 * @author Octavio
 * 
 */

class OpStackTest {
	private Symbol symbol;
	
	/**
	 * Test #1
	 * check {@link OpStack} existence
	 */
	@Test
	void createOpStack() {
		OpStack opStack=new OpStack();
	}
	
	/**
	 * Test #2
	 * check if {@link OpStack#push(Symbol)} pushes entry into stack
	 */
	@Test 
	void opStackPushFunction() {
		OpStack opStack=new OpStack();
		opStack.push(this.symbol);
		
	}
	
	
	/**
	 * Test #3
	 * check if {@link OpStack#pop()} pops symbol from stack
	 * @throws EmptyStackException 
	 */
	@Test 
	void opStackPopFunction() throws EmptyStackException {
		OpStack opStack=new OpStack();
		opStack.push(symbol);
		opStack.pop();
		
		
	}
	/**
	 * Test #4
	 * check if {@link OpStack#isEmpty()} returns true if stack is empty else false<br>
	 * makes push and pop test redundant
	 * @throws EmptyStackException 
	 */
	@Test 
	void opStackIsEmpty() throws EmptyStackException {
		OpStack opStack=new OpStack();
		opStack.push(symbol);
		assertFalse(opStack.isEmpty(),"stack is empty when it shouldt be");
		opStack.pop();
		assertTrue(opStack.isEmpty(),"stack isn't empty when it should be");
	}
}
