package al.copycat.domain.download.origin.common.exception;

public class OriginException extends RuntimeException {

	public OriginException(String message) {
		super(message);
	}

	public OriginException(String message, Throwable cause) {
		super(message, cause);
	}

	public OriginException(Throwable cause) {
		super(cause);
	}

	public OriginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
