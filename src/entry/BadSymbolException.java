package entry;

public class BadSymbolException extends Exception {
	private String message;
	public BadSymbolException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return "The following symbol created and error: " +this.message;
	}

}
