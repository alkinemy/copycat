package al.copycat.domain.base.exception;

public class CopycatException extends RuntimeException {

	private ErrorCode errorCode;
	private Object[] args;

	public CopycatException(String message, ErrorCode errorCode) {
		this(message, errorCode, null);
	}

	public CopycatException(String message, ErrorCode errorCode, Object[] args) {
		super(message);
		this.errorCode = errorCode;
		this.args = args;
	}

	public CopycatException(String message, ErrorCode errorCode, Throwable t, Object[] args) {
		super(message, t);
		this.errorCode = errorCode;
		this.args = args;
	}

}
