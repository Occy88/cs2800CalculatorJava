package test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entry.BadTypeException;
import entry.Entry;
import entry.Symbol;
/**
 * This class tests the {@link Entry} class.
 * 
 * @author Octavio
 * 
 */

class EntryTest {
	/** 
	 * An instance of the class {@link Entry} used for testing 
	 */
	private Entry entry;
	
	/**
	 * A float used within this class to test {@link Entry}
	 * 
	 */
	float number=5/4;
	
	/**
	 * Instance of the class {@link Symbol} used for testing
	 */
	private Symbol symbol;
	
	/**
	 * A string used to test {@link Entry}
	 */
	private String string="string";
	
	
	/**
	 * Test #1 
	 * Confirm the initialisation of the class {@link Entry} with a float
	 */
	@Test 
		void initialiseEntryConstructorWithFloat() {
		
		this.entry =new Entry(number);
	
	}
	/**
	 * Test #2
	 * Confirm class {@link Entry} contains method {@link Entry#getType()} which returns a type
	 */
	@Test 
	void getTypeFromEntry() throws BadTypeException {
		this.entry=new Entry();	
		entry.getType();
	}
	
	/**
	 * Test #3
	 * Confirm class {@link Entry} contains method {@link Entry#getString()} which returns a string
	 */
	@Test 
	void getStringFromEntry() throws BadTypeException {
		this.entry=new Entry(string);
		assertEquals(string,entry.getString(),"String returned is not string");
	}
	
	
	/**
	 * Test #5
	 * Confirm class {@link Entry} contains method {@link Entry#getSymbol()} which returns a tySpe
	 */
	@Test 
	void getSymbolFromEntry() throws BadTypeException {
		this.entry=new Entry(symbol);	
		entry.getSymbol();
	}

	/**
	 * Test #6
	 * Confirm class {@link Entry} contains method {@link Entry#getValue()} which returns a float
	 */
	@Test 
	void getValueFromEntry() throws BadTypeException{
		
		this.entry =new Entry(number);
		assertEquals(number, entry.getValue(),"number returned is not a float");
	}
	
	/**
	 * Test #7
	 * Check if Bad Type exception is thrown when calling {@link Entry#getString()} from {@link Entry}
	 */
	@Test 
	void getStringException(){
		this.entry=new Entry(number);	
		try {
			entry.getString();
			fail("Expected a BadTypeException");
		}catch(BadTypeException badType) {};
			
	}
	
	/**
	 * Test #8
	 * Check if Bad Type exception is thrown when calling {@link Entry#getSymbol()} from {@link Entry}
	 */
	@Test 
	void getSymbolException(){
		this.entry=new Entry(number);	
		try {
			entry.getSymbol();
			fail("Expected a BadTypeException");
		}catch(BadTypeException badType) {}
			
	}
	
	/**
	 * Test #9
	 * Check if Bad Type exception is thrown when calling {@link Entry#getValue()} from {@link Entry}
	 */
	@Test 
	void getValueException(){
		this.entry=new Entry(symbol);	
		try {
			entry.getValue();
			fail("Expected a BadTypeException");
		}catch(BadTypeException badType) {}
			
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

