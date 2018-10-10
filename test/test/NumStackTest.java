package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entry.BadTypeException;
import stacks.EmptyStackException;
import stacks.NumStack;


/**
 * This class tests the {@link NumStack} class.
 * 
 * @author Octavio
 * 
 */

class NumStackTest {
	private float number=5/6;
	
	/**
	 * Test #1
	 * check {@link NumStack} existence
	 */
	@Test
	void createNumStack() {
		NumStack numStack=new NumStack();
	}
	
	/**
	 * Test #2
	 * check if {@link NumStack#push(float)} pushes entry into stack
	 */
	@Test 
	void numStackPushFunction() {
		NumStack numStack=new NumStack();
		numStack.push(this.number);
		
	}
	
	
	/**
	 * Test #3
	 * check if {@link NumStack#pop()} pops number from stack
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test 
	void numStackPopFunction() throws EmptyStackException, BadTypeException {
		NumStack numStack=new NumStack();
		numStack.push(number);
		numStack.pop();
		
		
	}
	/**
	 * Test #4
	 * check if {@link NumStack#isEmpty()} returns true if stack is empty else false<br>
	 * makes push and pop test redundant
	 * @throws EmptyStackException 
	 * @throws BadTypeException 
	 */
	@Test 
	void numStackIsEmpty() throws EmptyStackException, BadTypeException {
		NumStack numStack=new NumStack();
		numStack.push(number);
		assertFalse(numStack.isEmpty(),"stack is empty when it shouldt be");
		numStack.pop();
		assertTrue(numStack.isEmpty(),"stack isn't empty when it should be");
	}
}
