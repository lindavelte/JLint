package exceptions;

public class JLintException extends Exception{

	private static final long serialVersionUID = 1L;

	public JLintException() {
	}

	public JLintException(String message) {
		super(message);
	}

	public JLintException(Throwable cause) {
		super(cause);
	}

	public JLintException(String message, Throwable cause) {
		super(message, cause);
	}
}
