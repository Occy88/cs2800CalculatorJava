
package string.analysis;

import entry.BadSymbolException;
import entry.Function;
import entry.Symbol;
import stacks.TokenStack;

public class StringAnalysis {

  /**
   * This function splits a string by blank spaces.<br>
   * this is no longer used.
   * 
   * @param testString
   *        to split e.g. "a b c"
   * @return String Array of substrings e.g. ["a"]["b"]["c"]
   */

  public static String[] parse(String testString) {
    String[] resultString = testString.split(" ");
    return resultString;
  }

  /**
   * formats {@linkplain TokenStack#expression} string to lower case and no spaces.
   */
  public static String removeSpaceAndCaps(String string) {
    string = string.toLowerCase();
    string = string.replaceAll(" ", "");
    return string;
  }

  /**
   * tests if a given string is a {@linkplain Function}.
   * 
   * @param string
   *        to test
   * @return boolean it is a function
   */
  public static boolean testFunction(String string) {
    try {
      Function.stringToFunction(string);
      return true;
    } catch (BadSymbolException e) {
      return false;
    }
  }

  /**
   * tests if a given string is a {@linkplain Symbol} <br>
   * .
   * 
   * @param string
   *        to test
   * @return boolean it is a symbol
   */
  public static boolean testSymbol(String string) {
    try {
      Symbol.stringToSymbol(string);
      return true;
    } catch (BadSymbolException e) {
      return false;
    }
  }

  /**
   * tests if a given string is a float <br>
   * .
   * 
   * @param string
   *        to test for float
   * @return boolean is a float
   */
  public static boolean testFloat(String string) {
    try {
      Float.parseFloat(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }

  }

  /**
   * finds the longest valid string representing a function or float<br>
   * at the beginning of the {@linkplain #expression}.
   * 
   * @return longestValidString a valid string a representing a function or float.
   */
  public static String findLongestFunctionOrFloat(String expression) {
    String currentString = "";
    String longestValidString = "";
    for (int index = 0; index <= expression.length(); index++) {
      currentString = expression.substring(0, index);
      if (testFunction(currentString) || testFloat(currentString)) {
        longestValidString = currentString;
      }
    }
    return longestValidString;
  }

  /**
   * this function removes any duplicate or more signs in a string.<br>
   * i.e.: ab+-+c+-=ab-c-.
   * 
   */
  public static String reducePlusMinusSigns(String expression) {
    char[] testArray = expression.toCharArray();
    String resultString = "";
    char input = ' ';
    boolean changed = false;
    for (int i = 0; i < testArray.length; i++) {
      if (!changed) {
        input = testArray[i];
      }
      if (input == '+' || input == '-') {
        if (i < testArray.length - 2) {
          char nextInput = testArray[i + 1];
          if ((nextInput == '-' && input == '-') || (nextInput == '+' && input == '+')) {
            input = '+';
            changed = true;
          } else if ((nextInput == '+' && input == '-') || (nextInput == '-' && input == '+')) {
            input = '-';
            changed = true;
          }
        }
        // continue if the next input is a sign
        if (i < testArray.length - 1 && (testArray[i + 1] == '-' || testArray[i + 1] == '+')) {
          continue;
        } else {
          changed = false;
        }
      }
      resultString += input;
    }
    return resultString;
  }

}
