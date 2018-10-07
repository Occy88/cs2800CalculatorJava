package cs2800Entry;

public enum Function {
	/**
	 * this class holds all possible functions the calculator can handle <br>
	 * also contains function describing attributes of the functions.
	 */
	SIN,
	COS,
	TAN,
	ASIN,
	ACOS,
	ATAN,
	ATAN2,
	COSH,
	SINH,
	TANH,
	LOG,
	LOG10,
	LOG1P,
	EXP,
	EXPM1,
	CBRT,
	SQRT,
	HYPOT,
	SIGNUM,
	ULP,
	ABS,
	CEIL,
	FLOOR,
	IEEEREMAINDER,
	POW,
	RINT,
	ROUND,
	TODEGREES,
	TORADIANS, INVALID;
	public static Function stringToFunction(String string) throws BadSymbolException {
		switch(string) {
		case "sin":
			return Function.SIN;
		case "cos":
			return Function.COS;
		case "tan":
			return Function.TAN;
		case "asin":
			return Function.ASIN;
		case "acos":
			return Function.ACOS;
		case "atan":
			return Function.ATAN;
		case "atan2":
			return Function.ATAN2;
		case "cosh":
			return Function.COSH;
		case "sinh":
			return Function.SINH;
		case "tanh":
			return Function.TANH;
		case "log":
			return Function.LOG;
		case "log10":
			return Function.LOG10;
		case "log1p":
			return Function.LOG1P;
		case "exp":
			return Function.EXP;
		case "expm1":
			return Function.EXPM1;
		case "cbrt":
			return Function.CBRT;
		case "sqrt":
			return Function.SQRT;
		case "hypot":
			return Function.HYPOT;
		case "signum":
			return Function.SIGNUM;
		case "ulp":
			return Function.ULP;
		case "abs":
			return Function.ABS;
		case "ceil1":
			return Function.CEIL;
		case "floor1":
			return Function.FLOOR;
		case "ieeeremainder":
			return Function.IEEEREMAINDER;
		case "pow":
			return Function.POW;
		case "rint":
			return Function.RINT;
		case "round":
			return Function.ROUND;
		case "todegrees":
			return Function.TODEGREES;
		case "toradians":
			return Function.TORADIANS;
		
		}
		throw new BadSymbolException(string);
	}

	public static int numberOfOperands(Function function) throws BadSymbolException {
		switch(function) {
		case SIN:
			return 1;
		case COS:
			return 1;
		case TAN:
			return 1;
		case ASIN:
			return 1;
		case ACOS:
			return 1;
		case ATAN:
			return 1;
		case ATAN2:
			return 2;
		case COSH:
			return 1;
		case SINH:
			return 1;
		case TANH:
			return 1;
		case LOG:
			return 1;
		case LOG10:
			return 1;
		case LOG1P:
			return 1;
		case EXP:
			return 1;
		case EXPM1:
			return 1;
		case CBRT:
			return 1;
		case SQRT:
			return 1;
		case HYPOT:
			return 2;
		case SIGNUM:
			return 1;
		case ULP:
			return 1;
		case ABS:
			return 1;
		case CEIL:
			return 1;
		case FLOOR:
			return 1;
		case IEEEREMAINDER:
			return 2;
		case POW:
			return 2;
		default: throw new BadSymbolException(function.toString());

		}
	}
	
	

}
