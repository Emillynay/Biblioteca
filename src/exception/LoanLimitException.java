package exception;

public class LoanLimitException extends RuntimeException {
	
	public LoanLimitException(String msg) {
		super(msg);
	}

}
