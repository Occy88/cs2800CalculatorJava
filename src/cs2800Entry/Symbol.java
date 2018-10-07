package cs2800Entry;

/**
 * 
 * @author octavio
 *
 */
public enum Symbol{
	LEFT_BRACKET,
	RIGHT_BRACKET,
	POWER,
	TIMES,
	DIVIDE,
	PLUS,
	MINUS,
	INVALID;
	
	public static Symbol stringToSymbol(String string) throws BadSymbolException {
		switch(string) {
			case "(":
				return Symbol.LEFT_BRACKET;
			case ")":
				return Symbol.RIGHT_BRACKET;
			case "*":
				return Symbol.TIMES;
			case "/":
				return Symbol.DIVIDE;
			case "+":
				return Symbol.PLUS;
			case "-":
				return Symbol.MINUS;
				
		}
	throw new BadSymbolException(string);
	}
	public static int getPrecedence(Symbol symbol) throws BadSymbolException {
		switch(symbol) {
		case POWER:
			return 4;
			
		case TIMES:
			return 3;
		
		case DIVIDE:
			return 3;
		
		case PLUS:
			return 2;
		
		case MINUS:
			return 2;
		
		}
		
		throw new BadSymbolException(symbol.toString());
	}
	public static boolean isOperator(Symbol symbol) throws BadSymbolException {
		switch(symbol) {
		case POWER:
			return true;
			
		case TIMES:
			return true;
		
		case DIVIDE:
			return true;
		
		case PLUS:
			return true;
		
		case MINUS:
			return true;
		case LEFT_BRACKET:
			return false;
		case RIGHT_BRACKET:
			return false;
		
		}
		
		throw new BadSymbolException(symbol.toString());
	}
	public static boolean isLeftAssociative(Symbol symbol) throws BadSymbolException {
		switch(symbol) {
		case POWER:
			return false;
			
		case TIMES:
			return true;
		
		case DIVIDE:
			return true;
		
		case PLUS:
			return true;
		
		case MINUS:
			return true;
		
		}
		
		throw new BadSymbolException(symbol.toString());
	}
}
