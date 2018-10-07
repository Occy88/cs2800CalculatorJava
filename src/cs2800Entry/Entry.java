package cs2800Entry;

/**
 * This class is the {@link Entry} class.<br>
 * -javadoc to be complete-
 * @author Octavio
 * 
 */
public class Entry {
	
	/**
	 * Holds variables with the following attributes: <br>
	 * {@linkplain Type} <br>
	 * Hold Data of the following attributes: <br>
	 * {@linkplain Symbol}<br>
	 * {@linkplain Function}<br>
	 * {@linkplain Float}
	 */
	private float number=0;
	/**
	 * use not yet available
	 */
	private String str;
	/**
	 * for throwing {@link BadTypeException} in 
	 * {@link #getString()},
	 * {@link #getValue()},
	 * {@link #getSymbol()}
	 */
	private Type type;
	/**
	 * use not yet available
	 */
	private Function function=Function.INVALID;
	private Symbol symbol=Symbol.INVALID;
	/**
	 * stores float into {@link #number}<br>
	 * declares type for {@link #type}
	 * @param number
	 */
	public Entry(float number) {
		this.number=number;
		this.type=Type.NUMBER;
	}
	/**
	 * stores string into {@link #str}<br>
	 * declares type for {@link #type}
	 * @param string
	 */
	public Entry(String string) {
		this.str=string;
		this.type = Type.STRING;
	}
	/**
	 * Empty constructor <br>
	 * used class existence test
	 */
	public Entry() {
	}
	/**
	 * stores string into {@link #other}<br>
	 * declares type for {@link #type}
	 *
	 * @param symbol
	 */
	public Entry(Symbol symbol) {
		this.symbol=symbol;
		this.type=Type.SYMBOL;
	}
	public Entry(Function function) {
		this.function=function;
		this.type=Type.FUNCTION;
	}
	/**
	 * 
	 * @return {@link #type}
	 */
	public Type getType() {
		return this.type;
		
	}
	/**
	 * 
	 * @return {@link #str}
	 * @throws BadTypeException
	 */
	public String toString() {
		String string="";
		if (this.symbol!=Symbol.INVALID) {
			string+=this.symbol.toString();
		}
		else if (this.number!=(float)0) {
			string +=Float.toString(this.number);	
		}
		else if (this.function!=Function.INVALID) {
			string+=this.function.toString();
		}	
		return string;
				}
	public String getString() throws BadTypeException {
		if(type != Type.STRING) {
			throw new BadTypeException(Type.STRING,type);
		}
		return this.str;
	
	}
	/**
	 * @return {@link #other}
	 * @throws BadTypeException
	 */
	
	public Symbol getSymbol() throws BadTypeException {
		if(type != Type.SYMBOL) {
			throw new BadTypeException(Type.SYMBOL,type);
		}
		return this.symbol;
	
	}
	/**
	 * 
	 * @return {@link #number}
	 * @throws BadTypeException
	 */
	public float getValue() throws BadTypeException {
		if(type != Type.NUMBER) {
			throw new BadTypeException(Type.NUMBER,type);
		}
		return this.number;
	
	}
	public Function getFunction() throws BadTypeException {
		if(type!=Type.FUNCTION) {
			throw new BadTypeException(Type.FUNCTION,type);
		}
		return this.function;
		// TODO Auto-generated method stub
	
	}
}

