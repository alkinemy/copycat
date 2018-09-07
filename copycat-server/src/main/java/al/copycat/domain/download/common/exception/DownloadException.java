package al.copycat.domain.download.common.exception;

import al.copycat.domain.base.exception.CopycatException;
import al.copycat.domain.base.exception.ErrorCode;

public class DownloadException extends CopycatException {

	public DownloadException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public DownloadException(String message, ErrorCode errorCode, Object[] args) {
		super(message, errorCode, args);
	}

	public DownloadException(String message, ErrorCode errorCode, Throwable t, Object[] args) {
		super(message, errorCode, t, args);
	}

}
