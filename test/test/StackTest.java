package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs2800Entry.Entry;
import cs2800Stacks.EmptyStackException;
import cs2800Stacks.Stack;
/**
 * This class tests the {@link Stack} class.
 * 
 * @author Octavio
 * 
 */

class StackTest {
	
	/**
	 * Test #1
	 * Test to check for class {@link Stack} existence;
	 */
	@Test 
	void StackClassExistance() {
		Stack st=new Stack();
	}
	/**
	 * Test #2
	 * Test for function push({@link Entry}) in class {@link Stack};
	 */
	@Test 
	void pushEntryExistance() {
		Stack stack=new Stack();
		Entry entry=new Entry();
		stack.push(entry);
	}
	/**
	 * Test #3
	 * Test for function size() returns number of entries in class {@link Stack};
	 */
	@Test 
	void sizeReturnsIntegerEquivalentToNumberOfEntries() {
		int entriesPushed=2;
		Stack stack=new Stack();
		Entry entry=new Entry();
		stack.push(entry);
		stack.push(entry);
		int sizeReturned= stack.size();
		assertEquals(sizeReturned,entriesPushed,"size function incorrect return");
		
	}
	
	/**
	 * Test #4
	 * Test for function pop() which returns and removes newest entry in class {@link Stack};
	 * 
	 */
	@Test 
	void popReturnsAndRemovesNewestEntry()throws EmptyStackException{
		
		Stack stack=new Stack();
		
		Entry entry=new Entry("entry");
		stack.push(entry);
		Entry returnedEntry=stack.pop();
		assertEquals(entry,returnedEntry,"poped entry is not the same as pushed entry");
		assertEquals(0,stack.size(),"entry pused was not poped stack is not empty");
		
		
	}
	/**
	 * Test #5
	 * Test for function top() which returns and keeps newest entry in class {@link Stack};
	 */
	@Test 
	void popReturnsAndKeepsNewestEntry() throws EmptyStackException{
		
		Stack stack=new Stack();
		
		Entry entry=new Entry("entry");
		stack.push(entry);
		Entry returnedEntry=stack.top();
		assertEquals(entry,returnedEntry,"returned entry is not the same as pushed entry");
		assertEquals(1,stack.size(),"entry pused waspoped stack is empty");
		
	}
	/**
	 * Test #6
	 * Test for function pop()for EmptyStackException in class {@link Stack};
	 */
	@Test 
	void popEmptyStackExceptionTest() {
		Stack stack=new Stack();
		try {
			stack.pop();
			fail("Expected EmptyStackException on pop()");
		}catch (EmptyStackException emptyStack) {
			
		}
		
	}
	/**
	 * Test #7
	 * Test for function top() for EmptyStackException in class {@link Stack};
	 */
	@Test 
	void topEmptyStackExceptionTest() {
		Stack stack=new Stack();
		try {
			stack.top();
			fail("Expected EmptyStackException on top()");
		}catch (EmptyStackException emptyStack) {
			
		}
		
	}
	
}
