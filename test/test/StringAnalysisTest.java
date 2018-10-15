
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import entry.Function;

import org.junit.jupiter.api.Test;

import stacks.TokenStack;
import string.analysis.StringAnalysis;

/**
 * This class tests the {@link TokenStack} class.
 * 
 * @author Octavio
 * 
 */

class StringAnalysisTest {
  /**
   * Test #0<br>
   * test function for parsing a string by white spaces into array of substrings.
   */
  @Test
  void parseByWhiteSpaces() {
    String string = "1 2 3 4";
    String result = StringAnalysis.parse(string)[3];
    assertTrue(result.equals("4"), "string parsed incorrectly");
  }

  /**
   * Test #1<br>
   * {@linkplain StringAnalysis#removeSpaceAndCaps(String)} removes spaces and caps.
   */
  @Test
  void removeSpacesAndCapsTest() {
    String string = "1 A 3 Cd";
    assertEquals(StringAnalysis.removeSpaceAndCaps(string), "1a3cd",
        "string incorrectly formatted");
  }

  /**
   * Test #2<br>
   * {@linkplain StringAnalysis#testFunction(String)} checks if string is a {@linkplain Function}.
   */
  @Test
  void testFunctionString() {
    String string = "pow";
    assertTrue(StringAnalysis.testFunction(string), "string incorrectly checked");
    string = "kgb";
    assertFalse(StringAnalysis.testFunction(string), "string incorrectly checked");
  }

  /**
   * Test #3<br>
   * {@linkplain StringAnalysis#testFloat(String)} checks if string is a {@linkplain Float}.
   */
  @Test
  void testFloatString() {
    String string = "4.1";
    assertTrue(StringAnalysis.testFloat(string), "string incorrectly checked");
    string = "kgb";
    assertFalse(StringAnalysis.testFloat(string), "string incorrectly checked");
  }

  /**
   * Test #4<br>
   * {@linkplain StringAnalysis#testSymbol(String)} checks if string is a {@linkplain Symbol}.
   */
  @Test
  void testSymbolString() {
    String string = "+";
    assertTrue(StringAnalysis.testSymbol(string), "string incorrectly checked");
    string = "kgb";
    assertFalse(StringAnalysis.testSymbol(string), "string incorrectly checked");
  }

  /**
   * Test #5<br>
   * {@linkplain StringAnalysis#findLongestFunctionOrFloat(String)}<br>
   * returns the first longest valid function or float in a string.
   */
  @Test
  void findLongestValidFunctionOrFloatTest() {
    String string = "sinh......";
    assertEquals(StringAnalysis.findLongestFunctionOrFloat(string), "sinh",
        "string incorrectly checked");
    string = "4.25AAAAAAA";
    assertEquals(StringAnalysis.findLongestFunctionOrFloat(string), "4.25",
        "string incorrectly checked");
  }

  /**
   * Test #6<br>
   * {@linkplain StringAnalysis#reducePlusMinusSigns(String)}:<br>
   * test if it reduces any consecutive signs "+-".
   */
  @Test
  void reducePlusMinusSignsTest() {
    String string = "+-abc--ab+---+s-2";
    assertEquals(StringAnalysis.reducePlusMinusSigns(string), "-abc+ab-s-2",
        "string incorrectly checked");
  }

}
