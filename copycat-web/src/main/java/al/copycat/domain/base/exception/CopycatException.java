package al.copycat.domain.base.exception;

public class CopycatException extends RuntimeException {

	public CopycatException(String message) {
		super(message);
	}

	public CopycatException(String message, Throwable cause) {
		super(message, cause);
	}

	public CopycatException(Throwable cause) {
		super(cause);
	}

	public CopycatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
