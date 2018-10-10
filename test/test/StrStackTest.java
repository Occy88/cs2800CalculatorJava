package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.StrStack;


/**
 * This class tests the {@link StrStack} class.
 * 
 * @author Octavio
 * 
 */

class StrStackTest {
	private String string="string";
	
	/**
	 * Test #1
	 * check {@link StrStack} existence
	 */
	@Test
	void createStrStack() {
		StrStack strStack=new StrStack();
	}
	
	/**
	 * Test #2
	 * check if {@link StrStack#push(String)} pushes entry into stack
	 */
	@Test 
	void strStackPushFunction() {
		StrStack strStack=new StrStack();
		strStack.push(this.string);
		
	}
	
	
	/**
	 * Test #3
	 * check if {@link StrStack#pop()} pops string from stack
	 * @throws EmptyStackException 
	 */
	@Test 
	void strStackPopFunction() throws EmptyStackException {
		StrStack strStack=new StrStack();
		strStack.push(string);
		strStack.pop();
		
		
	}
	/**
	 * Test #4
	 * check if {@link StrStack#isEmpty()} returns true if stack is empty else false<br>
	 * makes push and pop test redundant
	 * @throws EmptyStackException 
	 */
	@Test 
	void strStackIsEmpty() throws EmptyStackException {
		StrStack strStack=new StrStack();
		strStack.push(string);
		assertFalse(strStack.isEmpty(),"stack is empty when it shouldt be");
		strStack.pop();
		assertTrue(strStack.isEmpty(),"stack isn't empty when it should be");
	}
}
